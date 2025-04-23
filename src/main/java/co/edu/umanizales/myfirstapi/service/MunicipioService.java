package co.edu.umanizales.myfirstapi.service;

import co.edu.umanizales.myfirstapi.model.Ubicaciones;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MunicipioService {

    private final List<Ubicaciones> ubicaciones = new ArrayList<>();

    public void cargarDatos(List<Ubicaciones> data) {
        ubicaciones.clear();
        ubicaciones.addAll(data);
    }

    public List<Ubicaciones> getAll() {
        return ubicaciones;
    }

    public Ubicaciones getLocationByCode(String code) {
        return ubicaciones.stream()
                .filter(u -> u.getCodigoMunicipio().trim().equalsIgnoreCase(code.trim()))
                .findFirst().orElse(null);
    }

    public List<Ubicaciones> getLocationsByName(String name) {
        return ubicaciones.stream()
                .filter(u -> u.getMunicipio().trim().equalsIgnoreCase(name.trim()))
                .collect(Collectors.toList());
    }

    public List<Ubicaciones> getLocationsByInitialLetters(String letters) {
        return ubicaciones.stream()
                .filter(u -> u.getMunicipio().toLowerCase().startsWith(letters.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Ubicaciones> getLocationsByStateCode(String code) {
        return ubicaciones.stream()
                .filter(u -> u.getCodigoDepartamento().equals(code))
                .collect(Collectors.toList());
    }

    public List<String> getStates() {
        return ubicaciones.stream()
                .map(Ubicaciones::getDepartamento)
                .distinct()
                .collect(Collectors.toList());
    }

    public String getStateByCode(String code) {
        return ubicaciones.stream()
                .filter(u -> u.getCodigoDepartamento().equals(code))
                .map(Ubicaciones::getDepartamento)
                .findFirst().orElse(null);
    }

    public List<String> getCapitals() {
        return ubicaciones.stream()
                .filter(u -> u.getCodigoMunicipio().endsWith("001"))
                .map(Ubicaciones::getMunicipio)
                .collect(Collectors.toList());
    }
}
