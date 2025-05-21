package co.edu.umanizales.myfirstapi.controller;

import co.edu.umanizales.myfirstapi.model.Product;
import co.edu.umanizales.myfirstapi.model.TypeProduct;
import co.edu.umanizales.myfirstapi.service.ParameterService;
import co.edu.umanizales.myfirstapi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;



@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    private final ParameterService parameterService;

    public ProductController(ProductService productService, ParameterService parameterService) {
        super();
        this.productService = productService;
        this.parameterService = parameterService;
    }

    @PostMapping("/addproduct")
    public String addProduct (@RequestParam String code,@RequestParam double price,@RequestParam int stock, @RequestParam String description, @RequestParam String type) {

        TypeProduct p = parameterService.findProductByCode(type);

        if (p != null) {

            Product newProduct = new Product (code, description, price, stock, p);

            if(productService.addProduct(newProduct)) {
                return "Producto ingresado con exito";
            }else {
                return "Producto no ingresado";
            }

        }else {return "El tipo de producto no fue encontrado";}
    }

    @GetMapping("/all-products")
    public List<Product> get() {
        return productService.getAllProducts();
    }
    @GetMapping("/find-product/{code}")
    public Product findProductByCode(@PathVariable String code) {
        Product product = productService.getProductByCode(code);

        if(product != null) {
            return product;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Producto no encontrado con código: " + code);
        }
    }


}