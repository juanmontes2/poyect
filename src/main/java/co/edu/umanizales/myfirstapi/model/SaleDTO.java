package co.edu.umanizales.myfirstapi.model;

import java.util.List;

public class SaleDTO {

    private String Seller;

    private String Store;

    private List<ProductSale> products;

    public SaleDTO(String seller, String store, List<ProductSale> products) {
        super();
        Seller = seller;
        Store = store;
        this.products = products;
    }

    public String getSeller() {
        return Seller;
    }

    public void setSeller(String seller) {
        Seller = seller;
    }

    public String getStore() {
        return Store;
    }

    public void setStore(String store) {
        Store = store;
    }

    public List<ProductSale> getProducts() {
        return products;
    }

    public void setProducts(List<ProductSale> products) {
        this.products = products;
    }


}