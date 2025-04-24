package co.edu.umanizales.myfirstapi.service;

import co.edu.umanizales.myfirstapi.model.Ubicaciones;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service // Marca esta clase como un servicio de Spring para ser usada en la inyección de dependencias
public class MunicipioService {

    // Lista en memoria donde se almacenan las ubicaciones cargadas
    private final List<Ubicaciones> ubicaciones = new ArrayList<>();

    // Método para cargar datos desde una fuente externa (como un archivo CSV)
    public void cargarDatos(List<Ubicaciones> data) {
        ubicaciones.clear(); // Limpia la lista actual
        ubicaciones.addAll(data); // Agrega todos los datos nuevos
    }

    // Devuelve todas las ubicaciones almacenadas
    public List<Ubicaciones> getAll() {
        return ubicaciones;
    }

    // Busca una ubicación específica por el código del municipio
    public Ubicaciones getLocationByCode(String code) {
        return ubicaciones.stream()
                .filter(u -> u.getCodigoMunicipio().trim().equalsIgnoreCase(code.trim()))
                .findFirst().orElse(null); // Devuelve null si no encuentra nada
    }

    //Método para normalizar cadenas de texto quita tildes y mayusculas
    private String normalize(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}", "") // Elimina tildes
                .toLowerCase()
                .trim();
    }

    // Devuelve las ubicaciones cuyo nombre de municipio coincida (sin importar tildes ni mayúsculas)
    public List<Ubicaciones> getLocationsByName(String name) {
        String normalizedInput = normalize(name);
        return ubicaciones.stream()
                .filter(u -> normalize(u.getMunicipio()).equals(normalizedInput))
                .collect(Collectors.toList());
    }

    // Devuelve las ubicaciones cuyo municipio comience con las letras dadas
    public List<Ubicaciones> getLocationsByInitialLetters(String letters) {
        return ubicaciones.stream()
                .filter(u -> u.getMunicipio().toLowerCase().startsWith(letters.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Devuelve las ubicaciones que pertenecen a un departamento específico por su código
    public List<Ubicaciones> getLocationsByStateCode(String code) {
        return ubicaciones.stream()
                .filter(u -> u.getCodigoDepartamento().equals(code))
                .collect(Collectors.toList());
    }

    // Devuelve la lista de departamentos únicos
    public List<String> getStates() {
        return ubicaciones.stream()
                .map(Ubicaciones::getDepartamento)
                .distinct()
                .collect(Collectors.toList());
    }

    // Devuelve el nombre del departamento a partir de su código
    public String getStateByCode(String code) {
        return ubicaciones.stream()
                .filter(u -> u.getCodigoDepartamento().equals(code))
                .map(Ubicaciones::getDepartamento)
                .findFirst().orElse(null);
    }

    // Devuelve las ubicaciones cuyos municipios comiencen y terminen con letras específicas
    public List<Ubicaciones> getLocationsByStartAndEndLetter(String start, String end) {
        // Solo acepta una letra para cada parámetro
        if (start.length() != 1 || end.length() != 1) {
            return new ArrayList<>(); // Devuelve una lista vacía si no se cumple la condición
        }

        String startLetter = normalize(start);
        String endLetter = normalize(end);

        return ubicaciones.stream()
                .filter(u -> {
                    String nombreMunicipio = normalize(u.getMunicipio());
                    return nombreMunicipio.startsWith(startLetter) && nombreMunicipio.endsWith(endLetter);
                })
                .collect(Collectors.toList());
    }
}


