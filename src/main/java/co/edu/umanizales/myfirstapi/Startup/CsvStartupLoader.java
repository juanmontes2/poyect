package co.edu.umanizales.myfirstapi.Startup;

import co.edu.umanizales.myfirstapi.model.Location;

import co.edu.umanizales.myfirstapi.model.Product;
import co.edu.umanizales.myfirstapi.model.Seller;
import co.edu.umanizales.myfirstapi.model.Store;
import co.edu.umanizales.myfirstapi.model.TypeDocument;
import co.edu.umanizales.myfirstapi.model.TypeProduct;
import co.edu.umanizales.myfirstapi.service.LocationService;
import co.edu.umanizales.myfirstapi.service.ParameterService;
import co.edu.umanizales.myfirstapi.service.SellerService;
import co.edu.umanizales.myfirstapi.service.StoreService;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@Component
public class CsvStartupLoader implements CommandLineRunner {

    @Autowired
    private  ParameterService parameterService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StoreService storeService;


    @Override
    public void run(String... args) throws Exception {

        //cargar los parametros
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/parameters.csv"))) {
            String line = reader.readLine(); // encabezado

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String type = parts[0];
                String code = parts[1];
                String description = parts[2];

                switch (type.toUpperCase()) {
                    case "DOCUMENT_TYPE" -> parameterService.addParameter(new TypeDocument(code, description));
                    case "PRODUCT_TYPE" -> parameterService.addParameter(new TypeProduct(code, description));
                    default -> System.out.println("Tipo de parámetro desconocido: " + type);
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Cargar productos
        try {
            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(',') // Forzar el separador coma
                    .build();

            CSVReader reader = new CSVReaderBuilder(
                    new InputStreamReader(
                            new ClassPathResource("products.csv").getInputStream()))
                    .withCSVParser(parser)
                    .build();

            String[] line;
            boolean isFirst = true;

            while ((line = reader.readNext()) != null) {
                if (isFirst) {
                    isFirst = false;
                    continue;
                }

                System.out.println("Línea leída: " + Arrays.toString(line)); // Debug

                String code = line[0].trim();
                String description = line[1].trim();
                double price = Double.parseDouble(line[2].trim());
                int stock = Integer.parseInt(line[3].trim());
                String typeProductCode = line[4].trim();

                TypeProduct typeProduct = (TypeProduct) parameterService.getParameterByCode(typeProductCode);

                if (typeProduct == null) {
                    System.out.println("El tipo de producto " + typeProductCode + " no existe. Se omitirá.");
                    continue;
                }

                if (parameterService.getParameterByCode(code) != null) {
                    System.out.println("El código " + code + " ya existe. Se omitirá.");
                    continue;
                }

                Product product = new Product(code, description, price, stock, typeProduct);
                parameterService.addParameter(product);
            }

        } catch (Exception e) {
            System.err.println("Error cargando productos: " + e.getMessage());
            e.printStackTrace();
        }


        //Cargar las locaciones

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/locations.csv"))) {
            String line = reader.readLine(); // encabezado

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String code = parts[0];
                String description = parts[1];

                Location newLocation = new Location(code, description);

                locationService.addLocation(newLocation);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Cargar los vendedores(sellers)

        try (BufferedReader br = Files.newBufferedReader(Paths.get("src/main/resources/sellers.csv"))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] fields = line.split(",");

                String id = fields[0];
                String typeDocCode = fields[1];
                String name = fields[2];
                String lastName = fields[3];
                byte age = Byte.parseByte(fields[4]);
                String cityCode = fields[5];


                TypeDocument typeDoc = (TypeDocument) parameterService.getParameterByCode(typeDocCode);
                Location city = locationService.getLocationByCode(cityCode);

                Seller seller = new Seller(id, typeDoc, name, lastName, age, city);
                sellerService.addSeller(seller);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        //Cargar las tiendas(stores)
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/stores.csv"))) {
            reader.readLine(); // skip header

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String cityCode = parts[0];
                String code = parts[1];
                String name = parts[2];
                String address = parts[3];
                Location location = locationService.getLocationByCode(cityCode);

                if (location != null) {
                    storeService.addStore(new Store( location, code, name, address));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
