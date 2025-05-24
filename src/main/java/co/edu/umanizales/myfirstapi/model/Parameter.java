package co.edu.umanizales.myfirstapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Parameter {

    private String code;
    private String description;


    public Parameter(String code, String description) {
        this.code = code;
        this.description = description;
    }


    public Parameter() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}