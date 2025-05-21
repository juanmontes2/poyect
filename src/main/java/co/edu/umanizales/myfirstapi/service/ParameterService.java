package co.edu.umanizales.myfirstapi.service;

import co.edu.umanizales.myfirstapi.model.Parameter;
import co.edu.umanizales.myfirstapi.model.Product;
import co.edu.umanizales.myfirstapi.model.TypeDocument;
import co.edu.umanizales.myfirstapi.model.TypeProduct;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Service // Marca esta clase como un servicio de Spring para ser usada en la inyección de dependencias
public class ParameterService {
    private List<Parameter> parameters;

    @PostConstruct

    private void loadParameters() {
        parameters = new ArrayList<>();
        //lee diferentes csv

        parameters.add(new TypeDocument( "CC", "cedula de ciudadania"));
        parameters.add (new TypeDocument("NIT", "Numero De Identificacion Tributaria"));

        //type product
        TypeProduct pcs =new TypeProduct("1", "computadores");
        parameters.add(pcs);
        parameters.add(new TypeDocument("2", "pantallas"));


        //product

        parameters.add(new Product("A", "MAC", 1000000, 8,pcs));


    }

public List<Parameter> getParametersByType(int type) {
    List<Parameter> result = new ArrayList<>();
    for (Parameter p : parameters) {
        switch (type) {

            case 1:
                if (p instanceof TypeDocument) {
                    result.add(p);
                }
                break;

            case 2:
                if (p instanceof TypeProduct) {
                    result.add(p);
                }
                break;

            case 3:
                if (p instanceof Product) {
                }
                break;
        }
    }
    return result;}
}

