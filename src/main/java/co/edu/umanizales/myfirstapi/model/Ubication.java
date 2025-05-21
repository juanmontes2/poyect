package co.edu.umanizales.myfirstapi.model;

public class Ubication {
    // Código del departamento según la codificación oficial (DIVIPOLA)
    private String codigoDepartamento;

    // Nombre del departamento (Ej: ANTIOQUIA)
    private String departamento;

    // Código del municipio (DIVIPOLA)
    private String codigoMunicipio;

    // Nombre del municipio (Ej: MEDELLÍN)
    private String municipio;

    // Tipo de ubicación: Municipio, Isla, Área no municipalizada, etc.
    private String tipo;


    // Getters y Setters

    // Devuelve el código del departamento
    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    // Asigna el código del departamento
    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    // Devuelve el nombre del departamento
    public String getDepartamento() {
        return departamento;
    }

    // Asigna el nombre del departamento
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    // Devuelve el código del municipio
    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    // Asigna el código del municipio
    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    // Devuelve el nombre del municipio
    public String getMunicipio() {
        return municipio;
    }

    // Asigna el nombre del municipio
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    // Devuelve el tipo de ubicación (Municipio, Isla, etc.)
    public String getTipo() {
        return tipo;
    }

    // Asigna el tipo de ubicación
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
