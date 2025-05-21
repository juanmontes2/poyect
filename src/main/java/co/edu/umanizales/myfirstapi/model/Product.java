package co.edu.umanizales.myfirstapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Product extends Parameter{
  private double price;
  private int stock;
  private TypeProduct type;

    public Product(String code, String description, double price, int stock, TypeProduct type) {
        super(code, description);
        this.price = price;
        this.stock = stock;
        this.type = type;
    }
}

