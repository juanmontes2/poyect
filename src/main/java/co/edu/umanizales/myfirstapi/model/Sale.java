package co.edu.umanizales.myfirstapi.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Sale {

    private Store store;

    private Seller seller;

    private LocalDate dateSale;

    private int quantity;

    private ArrayList<ProductSale> products = new ArrayList<ProductSale>();

    private double totalSale;


    public Sale(Store store, Seller seller, LocalDate dateSale, int quantity, ArrayList<ProductSale> products, double totalSale) {
        super();
        this.store = store;
        this.seller = seller;
        this.dateSale = dateSale;
        this.quantity = quantity;
        this.products = products;
        this.totalSale = totalSale;
    }



    public int getQuantity() {
        return quantity;
    }



    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public double getTotalSale() {
        return totalSale;
    }



    public void setTotalSale(double totalSale) {
        this.totalSale = totalSale;
    }



    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public LocalDate getDateSale() {
        return dateSale;
    }

    public void setDateSale(LocalDate dateSale) {
        this.dateSale = dateSale;
    }

    public ArrayList<ProductSale> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductSale> products) {
        this.products = products;
    }



}
