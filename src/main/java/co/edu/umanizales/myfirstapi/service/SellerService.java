package co.edu.umanizales.myfirstapi.service;



import co.edu.umanizales.myfirstapi.model.Seller;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SellerService {

    private List<Seller> sellers = new ArrayList<>();



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
