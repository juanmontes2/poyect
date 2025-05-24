package co.edu.umanizales.myfirstapi.controller;

import co.edu.umanizales.myfirstapi.model.Location;
import co.edu.umanizales.myfirstapi.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/all-locations")
    public List<Location> get() {
        return locationService.getAllLocations();
    }

    @GetMapping("/get-location/{code}")
    public Location getByCode(@PathVariable String code) {
        return locationService.getLocationByCode(code);
    }

    @PostMapping("/add-location")
    public String addLocation(@RequestBody Location nLocation) {
        Location location = locationService.getLocationByCode(nLocation.getCode());
        if (location == null) {
            locationService.addLocation(nLocation);
            return "Ubicación agregada";
        } else {
            return "Ubicación ya existe";
        }
    }

    @PostMapping("/delete-location")
    public String deleteLocation(@RequestBody Location nLocation) {
        Location location = locationService.getLocationByCode(nLocation.getCode());
        if (location != null) {
            locationService.deleteLocation(location);
            return "Ubicación eliminada";
        } else {
            return "Ubicación no existe";
        }
    }
}
