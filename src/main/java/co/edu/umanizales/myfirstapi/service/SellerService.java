package co.edu.umanizales.myfirstapi.service;

import co.edu.umanizales.myfirstapi.model.Location;
import co.edu.umanizales.myfirstapi.model.Seller;
import co.edu.umanizales.myfirstapi.model.TypeDocument;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Service
public class SellerService {

    private List<Seller> sellers;

    private ParameterService parameterService;

    public SellerService(ParameterService parameterService) {
        this.sellers = new ArrayList<>();
        this.parameterService = parameterService;
    }

    @PostConstruct
    public void init() {
        loadSellersFromCSV(); // Ahora sí puedes usar parameterService
    }

    private void loadSellersFromCSV() {
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


                TypeDocument typeDoc = parameterService.findTypeDocByCode(typeDocCode);
                Location city = parameterService.findLocationByCode(cityCode);

                Seller seller = new Seller(id, typeDoc, name, lastName, age, city);
                sellers.add(seller);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Seller> getAllSellers() {
        return sellers;
    }

    public Seller findByIdentification(String id) {

        for (Seller s: sellers) {

            if(s.getIdentification().equalsIgnoreCase(id)) {
                return s;
            }
        }

        return null;
    }

    public boolean addSeller (Seller s) {
        return sellers.add(s);
    }

    public boolean deleteSeller(Seller s) {
        return sellers.remove(s);
    }

}

