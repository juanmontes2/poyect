package co.edu.umanizales.myfirstapi.controller;
import co.edu.umanizales.myfirstapi.model.Ubicaciones;
import co.edu.umanizales.myfirstapi.service.MunicipioService;
import org.springframework.web.bind.annotation.*;
// Importa la clase List de Java para manejar listas de objetos
import java.util.List;

@RestController
@RequestMapping("/locations")
public class UbicacionesController {

    private final MunicipioService municipioService;

    // 🔧 Inyección de dependencias a través del constructor
    public UbicacionesController(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    @GetMapping //
    public List<Ubicaciones> getAll() {
        return municipioService.getAll(); // Devuelve todas las ubicaciones disponibles
    }

    @GetMapping("/by-code/{code}")
    public Ubicaciones getByCode(@PathVariable String code) {
        return municipioService.getLocationByCode(code); // Devuelve una ubicación específica por código
    }

    @GetMapping("/by-name/{name}")
    public List<Ubicaciones> getByName(@PathVariable String name) {
        return municipioService.getLocationsByName(name); // Devuelve ubicaciones que coincidan con el nombre
    }

    @GetMapping("/by-initials/{letters}")
    public List<Ubicaciones> getByInitialLetters(@PathVariable String letters) {
        return municipioService.getLocationsByInitialLetters(letters); // Devuelve municipios que empiecen por esas letras
    }

    @GetMapping("/by-state-code/{code}")
    public List<Ubicaciones> getByStateCode(@PathVariable String code) {
        return municipioService.getLocationsByStateCode(code); // Devuelve los municipios del departamento dado
    }

    @GetMapping("/states")
    public List<String> getStates() {
        return municipioService.getStates(); // Devuelve una lista de departamentos únicos
    }

    @GetMapping("/state-by-code/{code}")
    public String getStateByCode(@PathVariable String code) {
        return municipioService.getStateByCode(code); // Devuelve el nombre del departamento según su código
    }

    @GetMapping("/capitals")
    public List<String> getCapitals() {
        return municipioService.getCapitals(); // Devuelve municipios que son capitales (terminan en "001")
    }
}
