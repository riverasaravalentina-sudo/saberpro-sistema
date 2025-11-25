package com.saberpro.controller;

import com.saberpro.dto.EstadisticasDTO;
import com.saberpro.service.AlumnoService;
import com.saberpro.service.ResultadoService;
import com.saberpro.service.BeneficioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private ResultadoService resultadoService;

    @Autowired
    private BeneficioService beneficioService;

    /**
     * Página principal - redirecciona al login o dashboard según autenticación
     */
    @GetMapping("/")
    public String home(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/dashboard";
        }
        return "redirect:/login";
    }

    /**
     * Dashboard principal con estadísticas
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        // Información del usuario autenticado
        model.addAttribute("usuario", authentication.getName());
        
        // Verificar rol para mostrar diferentes opciones
        boolean esCoordinacion = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_COORDINACION"));
        model.addAttribute("esCoordinacion", esCoordinacion);
        
        // Obtener estadísticas básicas
        try {
            model.addAttribute("totalAlumnos", alumnoService.contarTotalAlumnos());
            model.addAttribute("alumnosConResultados", resultadoService.contarAlumnosConResultados());
            model.addAttribute("alumnosPendientes", resultadoService.contarAlumnosPendientes());
            model.addAttribute("alumnosExcelencia", resultadoService.contarAlumnosExcelencia());
        } catch (Exception e) {
            // Si hay error, usar valores por defecto
            model.addAttribute("totalAlumnos", 0L);
            model.addAttribute("alumnosConResultados", 0L);
            model.addAttribute("alumnosPendientes", 0L);
            model.addAttribute("alumnosExcelencia", 0L);
        }
        
        // Usar la plantilla dashboard.html que ya existe
        return "dashboard";
    }

    /**
     * Página de acceso denegado
     */
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "auth/access-denied";
    }

    /**
     * Generar estadísticas para el dashboard
     */
    private EstadisticasDTO generarEstadisticas() {
        EstadisticasDTO estadisticas = new EstadisticasDTO();
        
        // Estadísticas básicas
        estadisticas.setTotalAlumnos(alumnoService.contarTotalAlumnos());
        estadisticas.setTotalResultados(resultadoService.contarTotalResultados());
        estadisticas.setAlumnosPendientes(estadisticas.getTotalAlumnos() - estadisticas.getTotalResultados());
        
        // Estadísticas de puntajes
        Object[] stats = resultadoService.obtenerEstadisticasGenerales();
        if (stats != null && stats.length >= 4) {
            estadisticas.setPromedioPuntajeGlobal(stats[0] != null ? ((Number) stats[0]).doubleValue() : 0.0);
            estadisticas.setPeorPuntaje(stats[1] != null ? ((Number) stats[1]).intValue() : 0);
            estadisticas.setMejorPuntaje(stats[2] != null ? ((Number) stats[2]).intValue() : 0);
        }
        
        // Estadísticas por módulos
        Object[] modulos = resultadoService.obtenerPromediosPorModulo();
        if (modulos != null && modulos.length >= 3) {
            estadisticas.setPromedioLectura(modulos[0] != null ? ((Number) modulos[0]).doubleValue() : 0.0);
            estadisticas.setPromedioRazonamiento(modulos[1] != null ? ((Number) modulos[1]).doubleValue() : 0.0);
            estadisticas.setPromedioIngles(modulos[2] != null ? ((Number) modulos[2]).doubleValue() : 0.0);
        }
        
        // Estadísticas por nivel de desempeño
        Object[] niveles = resultadoService.contarPorNivelDesempeno();
        if (niveles != null && niveles.length >= 4) {
            estadisticas.setNivelInsuficiente(niveles[0] != null ? ((Number) niveles[0]).longValue() : 0L);
            estadisticas.setNivelMinimo(niveles[1] != null ? ((Number) niveles[1]).longValue() : 0L);
            estadisticas.setNivelSatisfactorio(niveles[2] != null ? ((Number) niveles[2]).longValue() : 0L);
            estadisticas.setNivelAvanzado(niveles[3] != null ? ((Number) niveles[3]).longValue() : 0L);
        }
        
        // Estadísticas de beneficios
        Object[] beneficios = beneficioService.obtenerEstadisticasBeneficios();
        if (beneficios != null && beneficios.length >= 4) {
            estadisticas.setNoPuedenGraduarse(beneficios[0] != null ? ((Number) beneficios[0]).longValue() : 0L);
            estadisticas.setExoneracionParcial(beneficios[1] != null ? ((Number) beneficios[1]).longValue() : 0L);
            estadisticas.setExoneracionTotal50(beneficios[2] != null ? ((Number) beneficios[2]).longValue() : 0L);
            estadisticas.setExoneracionTotal100(beneficios[3] != null ? ((Number) beneficios[3]).longValue() : 0L);
        }
        
        return estadisticas;
    }
}