package co.edu.umanizales.myfirstapi.controller;

import co.edu.umanizales.myfirstapi.model.Store;

import co.edu.umanizales.myfirstapi.service.LocationService;
import co.edu.umanizales.myfirstapi.service.StoreService;
import co.edu.umanizales.myfirstapi.model.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api-stores")
public class StoreController {


    @Autowired
    private StoreService storeService;

    @Autowired
    private LocationService locationService;


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
    public String addStore (@RequestBody Store nStore) {

        Location city = locationService.getLocationByCode(nStore.getCity().getCode());
        Store storefind= storeService.getStoreForCode(nStore.getCode());
        System.out.print(storefind);

        if (city != null) {

            if( storefind == null) {
                storeService.addStore(nStore);

                return "tienda agregada con exito";

            }else {
                return "El codigo ya existe porfa intenta otro";
            }


        }else {
            return "ciudad no encontrada por favor verifique la ciudad y vuelva a intentar";
        }
    }

    @PostMapping("/delete-stores/{code}")
    public String deletestore (@PathVariable String code) {

        Store S = storeService.getStoreForCode(code);

        if(S != null) {
            storeService.deleteStore(S);

            return "Tienda Eliminada";
        }else {
            return "No se encontro la tienda";
        }


    }


}