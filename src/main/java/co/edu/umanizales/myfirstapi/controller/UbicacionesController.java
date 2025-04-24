package co.edu.umanizales.myfirstapi.controller;

import co.edu.umanizales.myfirstapi.model.Ubicaciones;
import co.edu.umanizales.myfirstapi.service.MunicipioService;
import org.springframework.web.bind.annotation.*; // Anotaciones para definir controladores REST y rutas
import java.util.List; // Importa la clase List para manejar listas

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/locations") // Prefijo para todas las rutas de este controlador
public class UbicacionesController {

    // Servicio que contiene la lógica de negocio relacionada con ubicaciones
    private final MunicipioService municipioService;

    // Constructor con inyección de dependencias del servicio
    public UbicacionesController(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    @GetMapping // Ruta GET base (/locations)
    public List<Ubicaciones> getAll() {
        return municipioService.getAll(); // Devuelve todas las ubicaciones disponibles
    }

    @GetMapping("/by-code/{code}") // Ruta GET para buscar por código de municipio
    public Ubicaciones getByCode(@PathVariable String code) {
        return municipioService.getLocationByCode(code); // Devuelve una ubicación específica por su código
    }

    @GetMapping("/by-name/{name}") // Ruta GET para buscar por nombre exacto de municipio
    public List<Ubicaciones> getByName(@PathVariable String name) {
        return municipioService.getLocationsByName(name); // Devuelve todas las ubicaciones que coincidan con el nombre
    }

    @GetMapping("/by-initials/{letters}") // Ruta GET para buscar por las letras iniciales del municipio
    public List<Ubicaciones> getByInitialLetters(@PathVariable String letters) {
        return municipioService.getLocationsByInitialLetters(letters); // Devuelve municipios que comiencen con esas letras
    }

    @GetMapping("/by-state-code/{code}") // Ruta GET para buscar por código de departamento
    public List<Ubicaciones> getByStateCode(@PathVariable String code) {
        return municipioService.getLocationsByStateCode(code); // Devuelve municipios del departamento correspondiente
    }

    @GetMapping("/states") // Ruta GET para obtener todos los departamentos
    public List<String> getStates() {
        return municipioService.getStates(); // Devuelve una lista única de departamentos
    }

    @GetMapping("/state-by-code/{code}") // Ruta GET para obtener el nombre de un departamento por su código
    public String getStateByCode(@PathVariable String code) {
        return municipioService.getStateByCode(code); // Devuelve el nombre del departamento que coincide con el código
    }

    @GetMapping("/by-start-and-end/{start}/{end}") // Ruta GET para buscar municipios que empiezan y terminan con una letra
    public List<Ubicaciones> getByStartAndEndLetter(@PathVariable String start, @PathVariable String end) {
        return municipioService.getLocationsByStartAndEndLetter(start, end); // Devuelve los municipios que cumplan esa condición
    }
}
