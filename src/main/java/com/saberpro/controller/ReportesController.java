package com.saberpro.controller;

import com.saberpro.dto.BeneficioDTO;
import com.saberpro.dto.ReporteDetalladoDTO;
import com.saberpro.service.BeneficioService;
import com.saberpro.service.AlumnoService;
import com.saberpro.service.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/reportes")
public class ReportesController {

    @Autowired
    private BeneficioService beneficioService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private ResultadoService resultadoService;

    /**
     * Página principal de reportes
     */
    @GetMapping
    public String reportes() {
        return "reportes/index";
    }

    /**
     * Reporte de beneficios generales
     */
    @GetMapping("/beneficios")
    public String reporteBeneficios(Model model, @RequestParam(required = false) String programa) {
        List<BeneficioDTO> beneficios;
        
        if (programa != null && !programa.trim().isEmpty()) {
            beneficios = beneficioService.obtenerBeneficiosPorPrograma(programa);
            model.addAttribute("programa", programa);
        } else {
            beneficios = beneficioService.obtenerTodosBeneficios();
        }
        
        model.addAttribute("beneficios", beneficios);
        
        // Estadísticas de beneficios
        Object[] stats = beneficioService.obtenerEstadisticasBeneficios();
        if (stats != null && stats.length >= 4) {
            model.addAttribute("noPuedenGraduarse", stats[0]);
            model.addAttribute("exoneracionParcial", stats[1]);
            model.addAttribute("exoneracionTotal50", stats[2]);
            model.addAttribute("exoneracionTotal100", stats[3]);
        }
        
        // Ahorro total estimado
        model.addAttribute("ahorroTotal", beneficioService.calcularAhorroTotalDerechos());
        
        return "reportes/beneficios";
    }

    /**
     * Reporte detallado por programa
     */
    @GetMapping("/detallado")
    public String reporteDetallado(Model model, @RequestParam(required = false) String programa) {
        if (programa != null && !programa.trim().isEmpty()) {
            ReporteDetalladoDTO reporte = generarReportePrograma(programa);
            model.addAttribute("reporte", reporte);
            return "reportes/detallado-programa";
        } else {
            // Reporte general de todos los programas
            List<Object[]> programas = alumnoService.contarAlumnosPorPrograma();
            model.addAttribute("programas", programas);
            return "reportes/detallado-general";
        }
    }

    /**
     * Reporte de estadísticas generales
     */
    @GetMapping("/estadisticas")
    public String reporteEstadisticas(Model model) {
        // DEBUG: Ver qué datos tenemos
        List<com.saberpro.entity.Resultado> todosResultados = resultadoService.listarTodos();
        System.out.println("========== DEBUG ESTADÍSTICAS ==========");
        System.out.println("Total resultados en BD: " + todosResultados.size());
        for (com.saberpro.entity.Resultado r : todosResultados) {
            System.out.println("Resultado ID: " + r.getId() + 
                             ", Puntaje: " + r.getPuntajeGlobal() + 
                             ", Estado: " + r.getEstado() +
                             ", Lectura: " + r.getLecturaCritica() +
                             ", Razonamiento: " + r.getRazonamientoCuantitativo() +
                             ", Inglés: " + r.getIngles());
        }
        
        Object[] statsGlobal = resultadoService.obtenerEstadisticasGenerales();
        System.out.println("Stats Global Array length: " + (statsGlobal != null ? statsGlobal.length : "null"));
        if (statsGlobal != null) {
            // Hibernate devuelve un array dentro de otro array, acceder al primer elemento
            if (statsGlobal.length > 0 && statsGlobal[0] instanceof Object[]) {
                Object[] realStats = (Object[]) statsGlobal[0];
                System.out.println("Real Stats Array length: " + realStats.length);
                for (int i = 0; i < realStats.length; i++) {
                    System.out.println("  [" + i + "]: " + realStats[i]);
                }
                statsGlobal = realStats; // Usar el array real
            } else {
                for (int i = 0; i < statsGlobal.length; i++) {
                    System.out.println("  [" + i + "]: " + statsGlobal[i]);
                }
            }
        }
        
        Object[] statsModulos = resultadoService.obtenerPromediosPorModulo();
        System.out.println("Stats Modulos Array length: " + (statsModulos != null ? statsModulos.length : "null"));
        if (statsModulos != null) {
            // Mismo problema con módulos
            if (statsModulos.length > 0 && statsModulos[0] instanceof Object[]) {
                Object[] realStats = (Object[]) statsModulos[0];
                System.out.println("Real Stats Modulos length: " + realStats.length);
                for (int i = 0; i < realStats.length; i++) {
                    System.out.println("  [" + i + "]: " + realStats[i]);
                }
                statsModulos = realStats; // Usar el array real
            } else {
                for (int i = 0; i < statsModulos.length; i++) {
                    System.out.println("  [" + i + "]: " + statsModulos[i]);
                }
            }
        }
        System.out.println("========================================");
        
        // Estadísticas generales
        model.addAttribute("totalAlumnos", alumnoService.contarTotalAlumnos());
        model.addAttribute("totalResultados", resultadoService.contarTotalResultados());
        model.addAttribute("alumnosConResultados", alumnoService.obtenerAlumnosConResultados().size());
        model.addAttribute("alumnosSinResultados", alumnoService.obtenerAlumnosSinResultados().size());
        
        // Estadísticas de puntajes
        if (statsGlobal != null && statsGlobal.length >= 4) {
            model.addAttribute("promedioPuntaje", statsGlobal[0] != null ? statsGlobal[0] : 0);
            model.addAttribute("peorPuntaje", statsGlobal[1] != null ? statsGlobal[1] : 0);
            model.addAttribute("mejorPuntaje", statsGlobal[2] != null ? statsGlobal[2] : 0);
        } else {
            model.addAttribute("promedioPuntaje", 0);
            model.addAttribute("peorPuntaje", 0);
            model.addAttribute("mejorPuntaje", 0);
        }
        
        // Promedios por módulo
        if (statsModulos != null && statsModulos.length >= 3) {
            model.addAttribute("promedioLectura", statsModulos[0] != null ? statsModulos[0] : 0);
            model.addAttribute("promedioRazonamiento", statsModulos[1] != null ? statsModulos[1] : 0);
            model.addAttribute("promedioIngles", statsModulos[2] != null ? statsModulos[2] : 0);
        } else {
            model.addAttribute("promedioLectura", 0);
            model.addAttribute("promedioRazonamiento", 0);
            model.addAttribute("promedioIngles", 0);
        }
        
        // Distribución por nivel de desempeño
        Object[] niveles = resultadoService.contarPorNivelDesempeno();
        if (niveles != null && niveles.length >= 4) {
            model.addAttribute("nivelInsuficiente", niveles[0] != null ? niveles[0] : 0L);
            model.addAttribute("nivelMinimo", niveles[1] != null ? niveles[1] : 0L);
            model.addAttribute("nivelSatisfactorio", niveles[2] != null ? niveles[2] : 0L);
            model.addAttribute("nivelAvanzado", niveles[3] != null ? niveles[3] : 0L);
        } else {
            model.addAttribute("nivelInsuficiente", 0L);
            model.addAttribute("nivelMinimo", 0L);
            model.addAttribute("nivelSatisfactorio", 0L);
            model.addAttribute("nivelAvanzado", 0L);
        }
        
        // Reporte de beneficios por nivel
        model.addAttribute("reporteNiveles", beneficioService.generarReportePorNivelDesempeno());
        
        // Promedio por programa
        model.addAttribute("promediosPorPrograma", resultadoService.obtenerPromedioPorPrograma());
        
        return "reportes/estadisticas";
    }

    /**
     * Alumnos que no pueden graduarse
     */
    @GetMapping("/no-pueden-graduarse")
    public String alumnosNoPuedenGraduarse(Model model) {
        List<BeneficioDTO> alumnos = beneficioService.obtenerAlumnosNoPuedenGraduarse();
        model.addAttribute("alumnos", alumnos);
        model.addAttribute("titulo", "Alumnos que NO pueden graduarse");
        return "reportes/no-pueden-graduarse";
    }

    /**
     * Alumnos con beneficios máximos
     */
    @GetMapping("/beneficios-maximos")
    public String alumnosConBeneficiosMaximos(Model model) {
        List<BeneficioDTO> alumnos = beneficioService.obtenerAlumnosConBeneficiosMaximos();
        model.addAttribute("alumnos", alumnos);
        model.addAttribute("titulo", "Alumnos con Beneficios Máximos (100% descuento)");
        return "reportes/beneficios-maximos";
    }

    /**
     * Generar reporte detallado para un programa específico
     */
    private ReporteDetalladoDTO generarReportePrograma(String programa) {
        ReporteDetalladoDTO reporte = new ReporteDetalladoDTO(programa);
        
        // Estadísticas básicas del programa
        List<com.saberpro.entity.Alumno> alumnos = alumnoService.buscarPorPrograma(programa);
        reporte.setTotalAlumnos((long) alumnos.size());
        
        long conResultados = alumnos.stream().filter(com.saberpro.entity.Alumno::tieneResultados).count();
        reporte.setAlumnosConResultados(conResultados);
        reporte.setAlumnosSinResultados(reporte.getTotalAlumnos() - conResultados);
        
        // Promedio de puntaje del programa
        List<Object[]> promedios = resultadoService.obtenerPromedioPorPrograma();
        for (Object[] promedio : promedios) {
            if (programa.equals(promedio[0])) {
                reporte.setPromedioPuntaje(((Number) promedio[1]).doubleValue());
                break;
            }
        }
        
        // Beneficios del programa
        List<BeneficioDTO> beneficios = beneficioService.obtenerBeneficiosPorPrograma(programa);
        
        long noPueden = beneficios.stream().filter(b -> !b.isPuedeGraduarse()).count();
        long parcial = beneficios.stream().filter(b -> b.getBeneficio().contains("parcial")).count();
        long total = beneficios.stream().filter(b -> b.getBeneficio().contains("100%") || b.getBeneficio().contains("50%")).count();
        
        reporte.setNoPuedenGraduarse(noPueden);
        reporte.setExoneracionParcial(parcial);
        reporte.setExoneracionTotal(total);
        
        return reporte;
    }
}