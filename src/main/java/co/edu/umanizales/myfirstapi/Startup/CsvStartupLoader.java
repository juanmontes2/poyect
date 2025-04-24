package co.edu.umanizales.myfirstapi.Startup;

import co.edu.umanizales.myfirstapi.model.Ubicaciones;
import co.edu.umanizales.myfirstapi.service.MunicipioService;
import com.opencsv.CSVReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component // Marca esta clase como un componente de Spring para que se ejecute al iniciar la aplicación
public class CsvStartupLoader implements CommandLineRunner {

    // Servicio que gestiona la lógica de los municipios
    private final MunicipioService municipioService;

    // Constructor que permite inyectar el servicio de municipios
    public CsvStartupLoader(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    // Método que se ejecuta automáticamente al iniciar la aplicación
    @Override
    public void run(String... args) throws Exception {
        List<Ubicaciones> datos = new ArrayList<>(); // Lista para almacenar los datos cargados del CSV

        // Lee el archivo CSV desde los recursos del proyecto
        try (CSVReader reader = new CSVReader(
                new InputStreamReader(
                        new ClassPathResource("DIVIPOLA-_C_digos_municipios_20250423.csv").getInputStream()))) {

            String[] line; // Array para almacenar cada línea del archivo
            boolean first = true; // Bandera para saltarse la primera línea (cabecera)

            while ((line = reader.readNext()) != null) {
                if (first) {
                    first = false; // Se salta la primera línea del CSV
                    continue;
                }

                // Crea una nueva instancia de Ubicaciones y asigna los valores del CSV
                Ubicaciones u = new Ubicaciones();
                u.setCodigoDepartamento(line[0].trim());
                u.setDepartamento(line[1].trim());
                u.setCodigoMunicipio(line[2].trim());
                u.setMunicipio(line[3].trim());
                u.setTipo(line[4].trim());

                // Agrega la instancia a la lista
                datos.add(u);
            }
        }

        // Carga todos los datos leídos al servicio de municipios
        municipioService.cargarDatos(datos);

        // Imprime en consola la cantidad de datos cargados
        System.out.println("Datos cargados: " + datos.size());
    }
}
