package co.edu.umanizales.myfirstapi.controller;

import co.edu.umanizales.myfirstapi.model.Ubication;
import co.edu.umanizales.myfirstapi.service.MunicipeService;
import org.springframework.web.bind.annotation.*; // Anotaciones para definir controladores REST y rutas
import java.util.List; // Importa la clase List para manejar listas

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/locations") // Prefijo para todas las rutas de este controlador
public class UbicationController {

    // Servicio que contiene la lógica de negocio relacionada con ubicaciones
    private final MunicipeService municipeService;

    // Constructor con inyección de dependencias del servicio
    public UbicationController(MunicipeService municipeService) {
        this.municipeService = municipeService;
    }

    @GetMapping // Ruta GET base (/locations)
    public List<Ubication> getAll() {
        return municipeService.getAll(); // Devuelve todas las ubicaciones disponibles
    }

    @GetMapping("/by-code/{code}") // Ruta GET para buscar por código de municipio
    public Ubication getByCode(@PathVariable String code) {
        return municipeService.getLocationByCode(code); // Devuelve una ubicación específica por su código
    }

    @GetMapping("/by-name/{name}") // Ruta GET para buscar por nombre exacto de municipio
    public List<Ubication> getByName(@PathVariable String name) {
        return municipeService.getLocationsByName(name); // Devuelve todas las ubicaciones que coincidan con el nombre
    }

    @GetMapping("/by-initials/{letters}") // Ruta GET para buscar por las letras iniciales del municipio
    public List<Ubication> getByInitialLetters(@PathVariable String letters) {
        return municipeService.getLocationsByInitialLetters(letters); // Devuelve municipios que comiencen con esas letras
    }

    @GetMapping("/by-state-code/{code}") // Ruta GET para buscar por código de departamento
    public List<Ubication> getByStateCode(@PathVariable String code) {
        return municipeService.getLocationsByStateCode(code); // Devuelve municipios del departamento correspondiente
    }

    @GetMapping("/states") // Ruta GET para obtener todos los departamentos
    public List<String> getStates() {
        return municipeService.getStates(); // Devuelve una lista única de departamentos
    }

    @GetMapping("/state-by-code/{code}") // Ruta GET para obtener el nombre de un departamento por su código
    public String getStateByCode(@PathVariable String code) {
        return municipeService.getStateByCode(code); // Devuelve el nombre del departamento que coincide con el código
    }

    @GetMapping("/by-start-and-end/{start}/{end}") // Ruta GET para buscar municipios que empiezan y terminan con una letra
    public List<Ubication> getByStartAndEndLetter(@PathVariable String start, @PathVariable String end) {
        return municipeService.getLocationsByStartAndEndLetter(start, end); // Devuelve los municipios que cumplan esa condición
    }
}
