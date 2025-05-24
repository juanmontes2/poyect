package co.edu.umanizales.myfirstapi.service;

import co.edu.umanizales.myfirstapi.model.Store;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {

    private final List<Store> stores = new ArrayList<>();

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