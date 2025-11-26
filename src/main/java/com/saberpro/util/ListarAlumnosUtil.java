package com.saberpro.util;

import com.saberpro.entity.Alumno;
import com.saberpro.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListarAlumnosUtil implements CommandLineRunner {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n========== ALUMNOS EN LA BASE DE DATOS ==========");
        List<Alumno> alumnos = alumnoRepository.findAll();
        System.out.println("Total de alumnos: " + alumnos.size());
        System.out.println("\nPrimeros 10 alumnos:");
        System.out.println("DOCUMENTO\tNOMBRE\t\t\t\tPROGRAMA");
        System.out.println("================================================================");
        
        alumnos.stream()
            .limit(10)
            .forEach(a -> {
                String nombreCompleto = a.getPrimerNombre() + " " + a.getPrimerApellido();
                System.out.printf("%s\t%-30s\t%s%n", 
                    a.getNumeroDocumento(), 
                    nombreCompleto, 
                    a.getPrograma());
            });
        
        System.out.println("================================================================");
        System.out.println("Para iniciar sesión como estudiante, use:");
        System.out.println("  Usuario: [número de documento]");
        System.out.println("  Contraseña: [número de documento]");
        System.out.println("================================================================\n");
    }
}
