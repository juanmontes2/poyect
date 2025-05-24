package co.edu.umanizales.myfirstapi.controller;

import co.edu.umanizales.myfirstapi.model.Location;

import co.edu.umanizales.myfirstapi.model.Seller;
import co.edu.umanizales.myfirstapi.model.TypeDocument;
import co.edu.umanizales.myfirstapi.service.LocationService;
import co.edu.umanizales.myfirstapi.service.ParameterService;
import co.edu.umanizales.myfirstapi.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;
    @Autowired
    private ParameterService parameterService;
    @Autowired
    private LocationService locationService;

    @GetMapping("/all-sellers")
    public List<Seller> get() {
        return sellerService.getAllSellers();
    }

    @PostMapping("/add-seller")
    public String addSeller(@RequestBody Seller nSeller) {

        Seller s = sellerService.findByIdentification(nSeller.getIdentification());
        System.out.print(s);

        TypeDocument typedoc = (TypeDocument) parameterService.getParameterByCode(nSeller.getTypeDoc().getCode());
        Location location = locationService.getLocationByCode(nSeller.getCity().getCode());

        if (s == null) {
            if (typedoc != null) {
                if (location != null) {
                    sellerService.addSeller(nSeller);
                    return "vendedor agregado";
                } else {
                    return "Ciudad no encontrada";
                }
            } else {
                return "Tipo de documento no encontrado";
            }
        } else {
            return "Vendedor ya existe";
        }
    }

    @GetMapping("/get-seller/{code}")
    public Seller getbyidentification (@PathVariable String code) {
        return sellerService.findByIdentification(code);
    }
}