package co.edu.umanizales.myfirstapi.service;

import co.edu.umanizales.myfirstapi.model.Product;
import co.edu.umanizales.myfirstapi.model.TypeProduct;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private List<Product> products;

    private ParameterService parameterService;

    public ProductService(ParameterService parameterService) {
        this.products = new ArrayList<>();
        this.parameterService = parameterService;
    }

    @PostConstruct
    public void init() {
        loadSellersFromCSV(); // ✅ Ahora sí puedes usar parameterService
    }

    private void loadSellersFromCSV() {
        try (BufferedReader br = Files.newBufferedReader(Paths.get("src/main/resources/products.csv"))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] fields = line.split(",");


                String code = fields[0];
                String description = fields[1];
                double price = Double.parseDouble(fields[2]);
                int stock = Integer.parseInt(fields[3]);
                String typeProduct = fields[4];

                TypeProduct typeDoc = parameterService.findProductByCode(typeProduct);
                Product product = new Product(code, description, price, stock, typeDoc);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean addProduct (Product p) {
        return products.add(p);
    }

    public List<Product> getAllProducts(){
        return products;
    }

    public Product getProductByCode (String cod) {
        for(Product p :products) {
            if (p.getCode().equalsIgnoreCase(cod)) {
                return p;
            }
        }
        return null;
    }

}