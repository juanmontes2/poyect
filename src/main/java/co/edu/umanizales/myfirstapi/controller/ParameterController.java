package co.edu.umanizales.myfirstapi.controller;

import co.edu.umanizales.myfirstapi.model.Location;
import co.edu.umanizales.myfirstapi.model.TypeDocument;
import co.edu.umanizales.myfirstapi.model.TypeProduct;
import co.edu.umanizales.myfirstapi.service.ParameterService;
import co.edu.umanizales.myfirstapi.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-parameter")
public class ParameterController {

    private final ParameterService parameterService;

    public ParameterController(ProductService productService, ParameterService parameterService) {
        super();
        this.parameterService = parameterService;
    }


    @PostMapping("/add-parameter")
    public String newParameter(@RequestParam String type, @RequestParam String code, @RequestParam String description) {

        if(type.equalsIgnoreCase("LOCATION")) {
            Location l = new Location (code, description);
            parameterService.addParameter(l);
        }else if(type.equalsIgnoreCase("DOCUMENT_TYPE")) {
            TypeDocument l = new TypeDocument (code, description);
            parameterService.addParameter(l);
        }else if(type.equalsIgnoreCase("PRODUCT_TYPE")) {
            TypeProduct l = new TypeProduct(code, description);
            parameterService.addParameter(l);
        }else {return "tipo de parametro no valido";}


        return null;

    }
}