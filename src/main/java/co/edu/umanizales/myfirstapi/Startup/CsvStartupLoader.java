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

@Component
public class CsvStartupLoader implements CommandLineRunner {

    private final MunicipioService municipioService;

    public CsvStartupLoader(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Ubicaciones> datos = new ArrayList<>();

        try (CSVReader reader = new CSVReader(
                new InputStreamReader(new ClassPathResource("Departamentos_y_municipios_de_Colombia_20250409.csv").getInputStream()))) {
            String[] line;
            boolean first = true;
            while ((line = reader.readNext()) != null) {
                if (first) {
                    first = false;
                    continue;
                }

                // 👇 Muestra en consola lo que estás leyendo
                System.out.println("▶ Departamento: '" + line[0] + "', CodDepto: '" + line[1] +
                        "', Municipio: '" + line[2] + "', CodMunicipio: '" + line[3] + "'");

                Ubicaciones u = new Ubicaciones();
                u.setDepartamento(line[0].trim());
                u.setCodigoDepartamento(line[1].trim());
                u.setMunicipio(line[2].trim());
                u.setCodigoMunicipio(line[3].trim());
                datos.add(u);
            }
        }

        municipioService.cargarDatos(datos);
        System.out.println("✔ Datos cargados: " + datos.size());
    }
}