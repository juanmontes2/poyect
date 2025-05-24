package co.edu.umanizales.myfirstapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends Parameter {
    private double price;
    private int stock;
    private TypeProduct type;

    // Constructor que usa el código y la descripción del producto (ya no es fija)
    public Product(String code, String description, double price, int stock, TypeProduct type) {
        super(code, description); // Usa la descripción real del CSV
        this.price = price;
        this.stock = stock;
        this.type = type;
    }

    public Product() {
        super(); // Corrección: se cierra correctamente con punto y coma
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public TypeProduct getType() {
        return type;
    }

    public void setType(TypeProduct type) {
        this.type = type;
    }


}