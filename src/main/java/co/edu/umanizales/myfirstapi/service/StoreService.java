package co.edu.umanizales.myfirstapi.service;

import co.edu.umanizales.myfirstapi.model.Store;
import co.edu.umanizales.myfirstapi.model.Location;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {

    private final List<Store> stores = new ArrayList<>();

    public StoreService(ParameterService parameterService) {
        List<Location> locations = parameterService.getLocations();
        this.stores.addAll(loadStoresFromCsv("src/main/resources/stores.csv", locations));
    }

    private List<Store> loadStoresFromCsv(String filePath, List<Location> locations) {
        List<Store> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // skip header

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String cityCode = parts[0];
                String code = parts[1];
                String name = parts[2];
                String address = parts[3];

                Location location = locations.stream()
                        .filter(l -> l.getCode().equals(cityCode))
                        .findFirst()
                        .orElse(null);

                if (location != null) {
                    result.add(new Store( location, code, name, address));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Store> getAllStores() {
        return stores;
    }

    public Store getStoreForCode(String code){

        for(Store store: stores) {
            if (store.getCode().equalsIgnoreCase(code)) {
                return store;
            }
        }

        return null;
    }

    public boolean addStore (Store s) {
        stores.add(s);
        return true;
    }

    public boolean deleteStore (Store s) {
        return stores.remove(s);

    }
}