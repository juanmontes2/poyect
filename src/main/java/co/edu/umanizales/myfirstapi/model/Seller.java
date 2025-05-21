package co.edu.umanizales.myfirstapi.model;

public class Seller {

    private String identification;

    private TypeDocument typeDoc;

    private String name;

    private String lastName;

    private byte age;

    private Location city;






    public Seller(String identification, TypeDocument typeDoc, String name, String lastName, byte age, Location city) {
        super();
        this.identification = identification;
        this.typeDoc = typeDoc;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.city = city;

    }



    public Seller() {
        super();
        // TODO Auto-generated constructor stub
    }



    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public TypeDocument getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(TypeDocument typeDoc) {
        this.typeDoc = typeDoc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public Location getCity() {
        return city;
    }

    public void setCity(Location city) {
        this.city = city;
    }

}