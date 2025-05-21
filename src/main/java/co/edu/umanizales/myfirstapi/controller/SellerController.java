package co.edu.umanizales.myfirstapi.controller;

import co.edu.umanizales.myfirstapi.model.Location;
import co.edu.umanizales.myfirstapi.model.Seller;
import co.edu.umanizales.myfirstapi.model.TypeDocument;
import co.edu.umanizales.myfirstapi.service.ParameterService;
import co.edu.umanizales.myfirstapi.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ParameterService parameterService;

    @GetMapping("/all-sellers")
    public List<Seller> get() {
        return sellerService.getAllSellers();
    }

    @PostMapping("/add-seller")
    public String addSeller(@RequestParam String identification,
                            @RequestParam String typeDoc,
                            @RequestParam String name,
                            @RequestParam String lastname,
                            @RequestParam byte age,
                            @RequestParam String city) {

        Seller s = sellerService.findByIdentification(identification);
        System.out.print(s);

        TypeDocument typedoc = parameterService.findTypeDocByCode(typeDoc);
        Location location = parameterService.findLocation(city);

        if (s == null) {
            if (typedoc != null) {
                if (location != null) {
                    Seller newSeller = new Seller(identification, typedoc, name, lastname, age, location);
                    sellerService.addSeller(newSeller);
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
}
