package co.edu.umanizales.myfirstapi.controller;

import co.edu.umanizales.myfirstapi.dto.SaleDto;
import co.edu.umanizales.myfirstapi.model.*;
import co.edu.umanizales.myfirstapi.service.ParameterService;
import co.edu.umanizales.myfirstapi.service.SaleService;
import co.edu.umanizales.myfirstapi.service.SellerService;
import co.edu.umanizales.myfirstapi.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/sales")
public class SaleController {


    @Autowired
    private SellerService sellerService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private SaleService saleService;
    @Autowired
    private ParameterService parameterService;


    @PostMapping("/new-sale")
    public String newSale (@RequestBody SaleDto sall) {

        Seller selectSeller = sellerService.findByIdentification(sall.getSeller());
        Store selectStore =  storeService.getStoreForCode(sall.getStore());
        ArrayList<ProductSale> productsSales = (ArrayList<ProductSale>) sall.getProducts();

        System.out.print(selectSeller);

        System.out.print(selectStore);


        double Total =0;
        int totalQuatity = 0;

        if(selectSeller != null &&  selectStore != null) {

            for (ProductSale prosale : productsSales ) {

                Product p = (Product) parameterService.getParameterByCode(prosale.getCode());
                if (p != null) {

                    int q = prosale.getQuantity();
                    totalQuatity += q;
                    if(p.getStock()>q) {
                        int newStock  = p.getStock()-q;
                        p.setStock(newStock);
                        Total +=prosale.getSubtotal();
                    }else {
                        return "La cantidad vendida del producto" + prosale.getCode() + "es menor a la cantidad en stock";
                    }

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