package co.edu.umanizales.myfirstapi.controller;

import co.edu.umanizales.myfirstapi.model.*;
import co.edu.umanizales.myfirstapi.service.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SellerService sellerService;
    private final StoreService storeService;
    private final ProductService productService;
    private final SaleService saleService;
    public SaleController(SellerService sellerService, ParameterService parameterService, StoreService storeService, ProductService productService, SaleService saleService) {
        this.sellerService = sellerService;
        this.storeService = storeService;
        this.productService =productService;
        this.saleService= saleService;
    }

    @PostMapping("/new-sale")
    public String newSale (@RequestBody SaleDTO sall) {

        Seller selectSeller = sellerService.findByIdentification(sall.getSeller());
        Store selectStore =  storeService.getStoreForCode(sall.getStore());
        ArrayList<ProductSale> productsSales = (ArrayList<ProductSale>) sall.getProducts();
        double Total =0;
        int totalQuatity = 0;

        if(selectSeller != null &&  selectStore != null) {

            for (ProductSale prosale : productsSales ) {

                Product          p = productService.getProductByCode(prosale.getCode());
                if (p != null) {

                    int q = prosale.getQuantity();
                    totalQuatity += q;
                    int newStock  = p.getStock()-q;
                    p.setStock(newStock);
                    Total +=prosale.getSubtotal();

                }else {
                    return "Un producto no fue encontrado, la venta no se guardo, porfavor verifica";
                }

            }

            LocalDate hoy = LocalDate.now();

            Sale newSale = new Sale(selectStore,selectSeller,hoy, totalQuatity,productsSales,Total);

            saleService.addSale(newSale);

            return "venta añadida exitosamente";

        }else {return "No se encontro el vendedor o la tienda";}
    }

    @GetMapping("/all-sales")
    public List<Sale> getAll(){
        return saleService.getAll();
    }

}