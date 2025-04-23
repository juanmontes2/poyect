package co.edu.umanizales.myfirstapi.model;

public class Ubicaciones {
    // Código del departamento
    private String codigoDepartamento;

    // Nombre del departamento
    private String departamento;

    // Código del municipio
    private String codigoMunicipio;

    // Nombre del municipio
    private String municipio;

    // Getters y Setters
    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}
