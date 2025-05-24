package co.edu.umanizales.myfirstapi.controller;

import co.edu.umanizales.myfirstapi.model.Parameter;
import co.edu.umanizales.myfirstapi.model.Product;
import co.edu.umanizales.myfirstapi.model.TypeDocument;
import co.edu.umanizales.myfirstapi.model.TypeProduct;
import co.edu.umanizales.myfirstapi.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api-parameter")
public class ParameterController {

    @Autowired
    private ParameterService parameterService;


    @PostMapping("/add-typedoc")
    public String newtypeDoc (@RequestBody TypeDocument newtd) {

        TypeDocument a = (TypeDocument) parameterService.getParameterByCode(newtd.getCode());

        if (a == null) {

            if (parameterService.addParameter(newtd)) {
                return "agregado con exito";
            }else {
                return "no se agrego el nuevo tipo de documento";
            }
        }else {
            return "ya existe el tipo de documento";
        }

    }

    @DeleteMapping("/delete-typedoc/{code}")
    public String deleteTypeDocument(@PathVariable String code) {
        boolean isDeleted = parameterService.deleteParameter(code);

        if (isDeleted) {
            return "Tipo de documento eliminado con éxito";
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Tipo de documento no encontrado con código: " + code
            );
        }
    }


    @PostMapping("/add-typeproduct")
    public String newtypeprod (@RequestBody TypeProduct newtp) {

        TypeProduct a = (TypeProduct) parameterService.getParameterByCode(newtp.getCode());

        if (a == null) {

            if (parameterService.addParameter(newtp)) {
                return "agregado con exito";
            }else {
                return "no se agrego el nuevo tipo de producto";
            }
        }else {
            return "ya existe el tipo de producto";
        }

    }
    @DeleteMapping("/delete-typeproduct/{code}")
    public String deleteTypeProduct(@PathVariable String code) {
        boolean deleted = parameterService.deleteParameter(code);

        if (deleted) {
            return "Tipo de producto eliminado con éxito";
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Tipo de producto no encontrado con código: " + code
            );
        }
    }


    @GetMapping ("/all-parameters")
    public List<Parameter> all (){
        return parameterService.getAll();
    }

    @GetMapping("/get-typeproducts")
    public List<? extends Parameter> getTypesProducts(){
        return parameterService.getParametersByType("tipoproducto");
    }

    @GetMapping("/get-typeproducts-by-code/{code}")
    public List<? extends Parameter> getTypeProduct(@PathVariable String code) {
        Parameter param = parameterService.getParameterByCode(code);

        if (param instanceof TypeProduct) {
            return List.of(param);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Producto no encontrado  código: " + code);
        }


    }

    @GetMapping("/get-typedocument")
    public List<? extends Parameter> getTypesDocument() {
        return parameterService.getParametersByType("tipodoc");
    }
    @PostMapping("/addproduct")
    public String addProduct (@RequestBody Product newProduct) {

        TypeProduct p = (TypeProduct) parameterService.getParameterByCode(newProduct.getType().getCode());

        if (p != null) {

            if(parameterService.addParameter(newProduct)) {
                return "Producto ingresado con exito";
            }else {
                return "Producto no ingresado";
            }

        }else {return "El tipo de producto no fue encontrado";}
    }
    @DeleteMapping("/delete-product/{code}")
    public String deleteProduct(@PathVariable String code) {
        boolean deleted = parameterService.deleteParameter(code);

        if (deleted) {
            return "Producto eliminado con éxito";
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Producto no encontrado con código: " + code
            );
        }
    }


    @GetMapping("/all-products")
    public List<? extends Parameter> get() {

        return parameterService.getParametersByType("producto");

    }


    @GetMapping("/find-product/{code}")
    public Product findProductByCode(@PathVariable String code) {
        Product product = (Product) parameterService.getParameterByCode(code);

        if(product != null) {
            return product;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Producto no encontrado con código: " + code);
        }
    }
}

