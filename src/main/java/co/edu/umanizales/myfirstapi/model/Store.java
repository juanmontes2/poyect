package co.edu.umanizales.myfirstapi.model;

public class Store {

    private Location city;
    private String code;
    private String name;
    private String address;

    public Store(Location city, String code, String name, String address) {
        super();
        this.city = city;
        this.code = code;
        this.name = name;
        this.address = address;
    }

    public Store() {
        super();
    }

    public Location getCity() {
        return city;
    }

    public void setCity(Location city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
