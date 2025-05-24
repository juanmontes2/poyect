package co.edu.umanizales.myfirstapi.service;


import co.edu.umanizales.myfirstapi.model.Parameter;
import co.edu.umanizales.myfirstapi.model.Product;
import co.edu.umanizales.myfirstapi.model.TypeDocument;
import co.edu.umanizales.myfirstapi.model.TypeProduct;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class ParameterService {

    private List<Parameter> parameters =new ArrayList<>();


    public Parameter getParameterByCode(String code) {
        for (Parameter p : parameters) {
            if (p.getCode().equals(code)) {
                return p;
            }
        }
        return null;
    }

    public boolean addParameter(Parameter parameter) {
        return parameters.add(parameter);
    }

    public List<? extends Parameter> getParametersByType(String tipo) {
        List<Parameter> resultado = new ArrayList<>();

        for (Parameter parametro : parameters) {
            switch (tipo.toLowerCase()) {
                case "producto":
                    if (parametro instanceof Product) {
                        resultado.add(parametro);
                    }
                    break;
                case "tipodoc":
                    if (parametro instanceof TypeDocument) {
                        resultado.add(parametro);
                    }
                    break;
                case "tipoproducto":
                    if (parametro instanceof TypeProduct) {
                        resultado.add(parametro);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Tipo no reconocido: " + tipo);
            }
        }

        return resultado;
    }
    public boolean deleteParameter(String code) {
        for (Parameter p : parameters) {
            if (p.getCode().equals(code)) {
                parameters.remove(p);
                return true;
            }
        }
        return false;
    }


    public List<Parameter> getAll(){
        return parameters;
    }
}



