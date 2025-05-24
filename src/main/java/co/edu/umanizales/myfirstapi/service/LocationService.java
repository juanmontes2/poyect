package co.edu.umanizales.myfirstapi.service;

import co.edu.umanizales.myfirstapi.model.Location;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class LocationService {

    private List<Location> locations = new ArrayList<>();

    public void addLocation(Location l) {
        locations.add(l);
    }

    public Location getLocationByCode(String code) {

        for (Location l: locations) {
            if(l.getCode().equalsIgnoreCase(code)) {
                return l;
            }
        }
        return null;
    }
    public boolean deleteLocation(Location l) {
        return locations.remove(l);
    }

    public List<Location> getAllLocations() {
        return locations;
    }


}