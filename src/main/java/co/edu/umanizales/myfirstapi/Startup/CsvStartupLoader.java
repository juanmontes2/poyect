package co.edu.umanizales.myfirstapi.Startup;

import co.edu.umanizales.myfirstapi.model.Product;
import co.edu.umanizales.myfirstapi.model.TypeDocument;
import co.edu.umanizales.myfirstapi.model.TypeProduct;
import co.edu.umanizales.myfirstapi.model.Ubication;
import co.edu.umanizales.myfirstapi.service.MunicipeService;
import co.edu.umanizales.myfirstapi.service.ParameterService;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CsvStartupLoader implements CommandLineRunner {

    private final MunicipeService municipeService;
    private final ParameterService parameterService;

    public CsvStartupLoader(MunicipeService municipeService, ParameterService parameterService) {
        this.municipeService = municipeService;
        this.parameterService = parameterService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Ubication> datos = new ArrayList<>();

        // Cargar municipios
        try (CSVReader reader = new CSVReader(
                new InputStreamReader(
                        new ClassPathResource("DIVIPOLA-_C_digos_municipios_20250423.csv").getInputStream()))) {

            String[] line;
            boolean first = true;

            while ((line = reader.readNext()) != null) {
                if (first) {
                    first = false;
                    continue;
                }

                Ubication u = new Ubication();
                u.setCodigoDepartamento(line[0].trim());
                u.setDepartamento(line[1].trim());
                u.setCodigoMunicipio(line[2].trim());
                u.setMunicipio(line[3].trim());
                u.setTipo(line[4].trim());

                datos.add(u);
            }
        }

        municipeService.cargarDatos(datos);
        System.out.println("Datos cargados: " + datos.size());

        // Cargar tipos de documentos
        try (CSVReader reader = new CSVReader(
                new InputStreamReader(
                        new ClassPathResource("tipos_documento.csv").getInputStream()))) {

            String[] line;
            boolean isFirst = true;

            while ((line = reader.readNext()) != null) {
                if (isFirst) {
                    isFirst = false;
                    continue;
                }

                String code = line[0].trim();
                String description = line[1].trim();

                if (parameterService.getParameterByCode(code) != null) {
                    System.out.println("El código " + code + " ya existe. Se omitirá.");
                    continue;
                }

                TypeDocument doc = new TypeDocument(code, description);
                parameterService.addParameter(doc);
            }
        }

        // Cargar tipos de productos
        try (CSVReader reader = new CSVReader(
                new InputStreamReader(
                        new ClassPathResource("tipos_producto.csv").getInputStream()))) {

            String[] line;
            boolean isFirst = true;

            while ((line = reader.readNext()) != null) {
                if (isFirst) {
                    isFirst = false;
                    continue;
                }

                String code = line[0].trim();
                String description = line[1].trim();

                if (parameterService.getParameterByCode(code) != null) {
                    System.out.println("El código " + code + " ya existe. Se omitirá.");
                    continue;
                }

                TypeProduct doc = new TypeProduct(code, description);
                parameterService.addParameter(doc);
            }
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
    }
}
