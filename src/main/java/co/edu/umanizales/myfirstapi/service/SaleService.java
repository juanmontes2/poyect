package co.edu.umanizales.myfirstapi.service;

import co.edu.umanizales.myfirstapi.model.Sale;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {

    private List<Sale> Sales = new ArrayList<>();

    public boolean addSale(Sale s) {

        return Sales.add(s);
    }

    public List<Sale> getAll() {
        return Sales;
    }




}