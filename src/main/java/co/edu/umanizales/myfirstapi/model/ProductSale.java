package co.edu.umanizales.myfirstapi.model;

public class ProductSale {

    private String code;

    private int quantity;

    private double subtotal;

    public ProductSale(String code, int quantity, double subtotal) {
        super();
        this.code = code;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }



}