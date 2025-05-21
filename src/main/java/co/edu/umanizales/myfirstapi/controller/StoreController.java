package co.edu.umanizales.myfirstapi.controller;

import co.edu.umanizales.myfirstapi.model.Store;
import co.edu.umanizales.myfirstapi.service.ParameterService;
import co.edu.umanizales.myfirstapi.service.StoreService;
import co.edu.umanizales.myfirstapi.model.Location;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api-stores")
public class StoreController {

    private final StoreService storeService;
    private final ParameterService parameterService;

    public StoreController(StoreService storeService, ParameterService parameterService) {
        this.storeService = storeService;
        this.parameterService = parameterService;
    }




    @GetMapping("/all-stores")
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/find-store/{code}")
    public Store findCode(@PathVariable String code) {

        Store u = storeService.getStoreForCode(code);

        if(u!=null) {
            return u;
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found with code: " + code);
        }

    }

    @PostMapping("/add-store")
    public String addStore (@RequestParam String cit, @RequestParam String code, @RequestParam String name, @RequestParam String adress ) {

        Location city = parameterService.findLocation(cit);
        Store storefind= storeService.getStoreForCode(code);
        System.out.print(storefind);

        if (city != null) {

            if( storefind == null) {

                Store newStore = new Store(city,code,name,adress);
                storeService.addStore(newStore);

                return "tienda agregada con exito";

            }else {
                return "El codigo ya existe porfa intenta otro";
            }


        }else {
            return "ciudad no encontrada por favor verifique la ciudad y vuelva a intentar";
        }
    }

    @PostMapping("/delete-stores")
    public String deletestore (@RequestParam String code) {

        Store S = storeService.getStoreForCode(code);

        if(S != null) {
            storeService.deleteStore(S);

            return "Tienda Eliminada";
        }else {
            return "No se encontro la tienda";
        }


    }


}