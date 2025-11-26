package com.saberpro.service;

import com.saberpro.dto.BeneficioDTO;
import com.saberpro.entity.Alumno;
import com.saberpro.entity.Resultado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BeneficioService {

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private ResultadoService resultadoService;

    /**
     * Obtener todos los beneficios de los alumnos con resultados
     */
    public List<BeneficioDTO> obtenerTodosBeneficios() {
        List<Alumno> alumnosConResultados = alumnoService.obtenerAlumnosConResultados();
        
        return alumnosConResultados.stream()
                .map(this::calcularBeneficio)
                .collect(Collectors.toList());
    }

    /**
     * Obtener beneficios por programa académico
     */
    public List<BeneficioDTO> obtenerBeneficiosPorPrograma(String programa) {
        List<Alumno> alumnos = alumnoService.buscarPorPrograma(programa);
        
        return alumnos.stream()
                .filter(Alumno::tieneResultados)
                .map(this::calcularBeneficio)
                .collect(Collectors.toList());
    }

    /**
     * Calcular beneficio individual para un alumno
     */
    public BeneficioDTO calcularBeneficio(Alumno alumno) {
        BeneficioDTO beneficio = new BeneficioDTO();
        beneficio.setAlumno(alumno);

        if (!alumno.tieneResultados()) {
            beneficio.setBeneficio("Sin resultados SABER PRO");
            beneficio.setDescripcion("El estudiante debe presentar las pruebas SABER PRO");
            beneficio.setColor("warning");
            beneficio.setPuedeGraduarse(false);
            return beneficio;
        }

        // Obtener el último resultado (más reciente)
        Resultado resultado = alumno.getResultados().stream()
                .filter(r -> "VALIDO".equals(r.getEstado()))
                .findFirst()
                .orElse(null);
        
        if (resultado == null) {
            beneficio.setBeneficio("Sin resultados válidos");
            beneficio.setDescripcion("No hay resultados válidos SABER PRO");
            beneficio.setColor("warning");
            beneficio.setPuedeGraduarse(false);
            return beneficio;
        }
        
        Integer puntaje = resultado.getPuntajeGlobal();

        if (puntaje < 80) {
            beneficio.setBeneficio("NO PUEDE GRADUARSE");
            beneficio.setDescripcion("Debe volver a presentar las pruebas SABER PRO");
            beneficio.setColor("danger");
            beneficio.setPuedeGraduarse(false);
        } else if (puntaje <= 150) {
            beneficio.setBeneficio("Exoneración parcial de derechos");
            beneficio.setDescripcion("Descuento del 25% en derechos de grado");
            beneficio.setColor("info");
            beneficio.setPuedeGraduarse(true);
        } else if (puntaje <= 170) {
            beneficio.setBeneficio("Exoneración total + 50% descuento en grado");
            beneficio.setDescripcion("Sin derechos administrativos + 50% descuento en ceremonia de grado");
            beneficio.setColor("success");
            beneficio.setPuedeGraduarse(true);
        } else {
            beneficio.setBeneficio("Exoneración total + 100% descuento en grado");
            beneficio.setDescripcion("Sin derechos administrativos + ceremonia de grado gratuita");
            beneficio.setColor("primary");
            beneficio.setPuedeGraduarse(true);
        }

        beneficio.setPuntaje(puntaje);
        beneficio.setNivelDesempeno(resultado.getNivelGlobal());
        
        return beneficio;
    }

    /**
     * Obtener estadísticas de beneficios
     */
    public Object[] obtenerEstadisticasBeneficios() {
        List<BeneficioDTO> beneficios = obtenerTodosBeneficios();
        
        long noPuedeGraduarse = beneficios.stream()
                .filter(b -> !b.isPuedeGraduarse())
                .count();
        
        long exoneracionParcial = beneficios.stream()
                .filter(b -> b.getBeneficio().contains("parcial"))
                .count();
        
        long exoneracionTotal50 = beneficios.stream()
                .filter(b -> b.getBeneficio().contains("50%"))
                .count();
        
        long exoneracionTotal100 = beneficios.stream()
                .filter(b -> b.getBeneficio().contains("100%"))
                .count();
        
        return new Object[]{noPuedeGraduarse, exoneracionParcial, exoneracionTotal50, exoneracionTotal100};
    }

    /**
     * Obtener alumnos que no pueden graduarse
     */
    public List<BeneficioDTO> obtenerAlumnosNoPuedenGraduarse() {
        return obtenerTodosBeneficios().stream()
                .filter(b -> !b.isPuedeGraduarse())
                .collect(Collectors.toList());
    }

    /**
     * Obtener alumnos con beneficios máximos
     */
    public List<BeneficioDTO> obtenerAlumnosConBeneficiosMaximos() {
        return obtenerTodosBeneficios().stream()
                .filter(b -> b.getBeneficio().contains("100%"))
                .collect(Collectors.toList());
    }

    /**
     * Calcular ahorro total en derechos por beneficios
     */
    public double calcularAhorroTotalDerechos() {
        List<BeneficioDTO> beneficios = obtenerTodosBeneficios();
        double ahorroTotal = 0.0;
        
        // Valores simulados de derechos (pueden configurarse)
        final double DERECHOS_COMPLETOS = 500000.0; // $500,000
        final double CEREMONIA_GRADO = 200000.0;    // $200,000
        
        for (BeneficioDTO beneficio : beneficios) {
            if (beneficio.getBeneficio().contains("parcial")) {
                ahorroTotal += DERECHOS_COMPLETOS * 0.25;
            } else if (beneficio.getBeneficio().contains("50%")) {
                ahorroTotal += DERECHOS_COMPLETOS + (CEREMONIA_GRADO * 0.50);
            } else if (beneficio.getBeneficio().contains("100%")) {
                ahorroTotal += DERECHOS_COMPLETOS + CEREMONIA_GRADO;
            }
        }
        
        return ahorroTotal;
    }

    /**
     * Generar reporte de beneficios por nivel de desempeño
     */
    public List<Object[]> generarReportePorNivelDesempeno() {
        List<BeneficioDTO> beneficios = obtenerTodosBeneficios();
        List<Object[]> reporte = new ArrayList<>();
        
        // Agrupar por rango de puntaje (no por nivelDesempeno)
        long insuficiente = beneficios.stream()
                .filter(b -> b.getPuntaje() != null && b.getPuntaje() < 80)
                .count();
        
        long minimo = beneficios.stream()
                .filter(b -> b.getPuntaje() != null && b.getPuntaje() >= 80 && b.getPuntaje() <= 150)
                .count();
        
        long satisfactorio = beneficios.stream()
                .filter(b -> b.getPuntaje() != null && b.getPuntaje() >= 151 && b.getPuntaje() <= 170)
                .count();
        
        long avanzado = beneficios.stream()
                .filter(b -> b.getPuntaje() != null && b.getPuntaje() > 170)
                .count();
        
        reporte.add(new Object[]{"INSUFICIENTE", insuficiente, "NO PUEDE GRADUARSE"});
        reporte.add(new Object[]{"MÍNIMO", minimo, "Exoneración parcial"});
        reporte.add(new Object[]{"SATISFACTORIO", satisfactorio, "Exoneración + 50% grado"});
        reporte.add(new Object[]{"AVANZADO", avanzado, "Exoneración + 100% grado"});
        
        return reporte;
    }
}