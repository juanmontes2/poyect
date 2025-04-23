package co.edu.umanizales.myfirstapi.controller;

import co.edu.umanizales.myfirstapi.model.Ubicaciones;
import co.edu.umanizales.myfirstapi.service.MunicipioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class UbicacionesController {

    private final MunicipioService municipioService;

    public UbicacionesController(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    @GetMapping
    public List<Ubicaciones> getAll() {
        return municipioService.getAll();
    }

    @GetMapping("/by-code/{code}")
    public Ubicaciones getByCode(@PathVariable String code) {
        return municipioService.getLocationByCode(code);
    }

    @GetMapping("/by-name/{name}")
    public List<Ubicaciones> getByName(@PathVariable String name) {
        return municipioService.getLocationsByName(name);
    }

    @GetMapping("/by-initials/{letters}")
    public List<Ubicaciones> getByInitialLetters(@PathVariable String letters) {
        return municipioService.getLocationsByInitialLetters(letters);
    }

    @GetMapping("/by-state-code/{code}")
    public List<Ubicaciones> getByStateCode(@PathVariable String code) {
        return municipioService.getLocationsByStateCode(code);
    }

    @GetMapping("/states")
    public List<String> getStates() {
        return municipioService.getStates();
    }

    @GetMapping("/state-by-code/{code}")
    public String getStateByCode(@PathVariable String code) {
        return municipioService.getStateByCode(code);
    }

    @GetMapping("/capitals")
    public List<String> getCapitals() {
        return municipioService.getCapitals();
    }
}
