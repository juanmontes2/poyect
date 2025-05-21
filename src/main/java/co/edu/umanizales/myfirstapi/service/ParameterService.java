package co.edu.umanizales.myfirstapi.service;

import co.edu.umanizales.myfirstapi.model.Location;
import co.edu.umanizales.myfirstapi.model.Parameter;
import co.edu.umanizales.myfirstapi.model.TypeDocument;
import co.edu.umanizales.myfirstapi.model.TypeProduct;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Service
public class ParameterService {

    private List<Location> locations = new ArrayList<>();
    private List<TypeDocument> typesDoc = new ArrayList<>();
    private List<TypeProduct> typesProducts = new ArrayList<>();
    private List<Parameter> parameters =new ArrayList<>();

    @PostConstruct
    public void init() {
        loadLocationsFromCsv("src/main/resources/parameters.csv");
    }

    private void loadLocationsFromCsv(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // encabezado

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String type = parts[0];
                String code = parts[1];
                String description = parts[2];

                switch (type.toUpperCase()) {
                    case "LOCATION" -> locations.add(new Location(code, description));
                    case "DOCUMENT_TYPE" -> typesDoc.add(new TypeDocument(code, description));
                    case "PRODUCT_TYPE" -> typesProducts.add(new TypeProduct(code, description));
                    default -> System.out.println("Tipo de parámetro desconocido: " + type);
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Location> getLocations() {
        return locations;
    }

    public Location findLocation (String city) {
        for (Location l: locations) {
            if(l.getDescription().equalsIgnoreCase(city)) {
                return l;
            }
        }
        return null;
    }

    public TypeDocument findTypeDocByCode (String code) {
        for(TypeDocument t : typesDoc) {
            if(t.getCode().equalsIgnoreCase(code)) {
                return t;
            }
        }
        return null;
    }

    public Location findLocationByCode (String code) {
        for(Location t : locations) {
            if(t.getCode().equalsIgnoreCase(code)) {
                return t;
            }
        }
        return null;
    }

    public TypeProduct findProductByCode (String code) {
        for(TypeProduct t : typesProducts) {
            if(t.getCode().equalsIgnoreCase(code)) {
                return t;
            }
        }
        return null;
    }

    public Parameter getParameterByCode(String code) {
        for (Parameter p : parameters) {
            if (p.getCode().equals(code)) {
                return p;
            }
        }
        return null;
    }

    public void addParameter(Parameter parameter) {
        parameters.add(parameter);
    }
}

