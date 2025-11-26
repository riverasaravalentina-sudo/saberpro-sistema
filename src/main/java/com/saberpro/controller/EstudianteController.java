package com.saberpro.controller;

import com.saberpro.entity.Alumno;
import com.saberpro.entity.Resultado;
import com.saberpro.service.AlumnoService;
import com.saberpro.dto.BeneficioDTO;
import com.saberpro.service.BeneficioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private BeneficioService beneficioService;

    @GetMapping("/mi-perfil")
    public String miPerfil(Model model, Authentication authentication) {
        String username = authentication.getName();
        
        // DEBUG: Ver todos los alumnos disponibles
        System.out.println("========== DEBUG MI-PERFIL ==========");
        System.out.println("Usuario logueado: " + username);
        System.out.println("Buscando alumno por documento: " + username);
        
        Optional<Alumno> alumnoOpt = alumnoService.buscarPorDocumentoConResultados(username);
        
        if (alumnoOpt.isEmpty()) {
            System.out.println("Alumno NO encontrado con documento: " + username);
            
            // Intentar buscar sin cargar resultados
            Optional<Alumno> alumnoSinResultados = alumnoService.buscarPorDocumento(username);
            if (alumnoSinResultados.isPresent()) {
                System.out.println("Alumno SÍ encontrado sin resultados precargados");
                Alumno alumno = alumnoSinResultados.get();
                model.addAttribute("alumno", alumno);
                
                if (alumno.getResultados() != null && !alumno.getResultados().isEmpty()) {
                    Resultado resultado = alumno.getResultados().stream()
                            .filter(r -> "VALIDO".equals(r.getEstado()))
                            .findFirst()
                            .orElse(null);
                    
                    if (resultado != null) {
                        model.addAttribute("resultado", resultado);
                        BeneficioDTO beneficio = beneficioService.calcularBeneficio(alumno);
                        model.addAttribute("beneficio", beneficio);
                    }
                }
                
                return "estudiante/mi-perfil";
            }
            
            model.addAttribute("error", "No se encontró información del estudiante");
            model.addAttribute("username", username);
            return "estudiante/mi-perfil";
        }
        
        Alumno alumno = alumnoOpt.get();
        System.out.println("Alumno encontrado: " + alumno.getPrimerNombre() + " " + alumno.getPrimerApellido());
        model.addAttribute("alumno", alumno);
        
        if (alumno.getResultados() != null && !alumno.getResultados().isEmpty()) {
            Resultado resultado = alumno.getResultados().stream()
                    .filter(r -> "VALIDO".equals(r.getEstado()))
                    .findFirst()
                    .orElse(null);
            
            if (resultado != null) {
                model.addAttribute("resultado", resultado);
                BeneficioDTO beneficio = beneficioService.calcularBeneficio(alumno);
                model.addAttribute("beneficio", beneficio);
            }
        }
        
        return "estudiante/mi-perfil";
    }

    @GetMapping("/mis-resultados")
    public String misResultados(Model model, Authentication authentication) {
        String username = authentication.getName();
        
        Optional<Alumno> alumnoOpt = alumnoService.buscarPorDocumentoConResultados(username);
        
        if (alumnoOpt.isEmpty()) {
            model.addAttribute("error", "No se encontró información del estudiante");
            return "estudiante/mis-resultados";
        }
        
        Alumno alumno = alumnoOpt.get();
        model.addAttribute("alumno", alumno);
        model.addAttribute("resultados", alumno.getResultados());
        
        return "estudiante/mis-resultados";
    }

    @GetMapping("/mis-beneficios")
    public String misBeneficios(Model model, Authentication authentication) {
        String username = authentication.getName();
        
        Optional<Alumno> alumnoOpt = alumnoService.buscarPorDocumentoConResultados(username);
        
        if (alumnoOpt.isEmpty()) {
            Optional<Alumno> alumnoSinResultados = alumnoService.buscarPorDocumento(username);
            if (alumnoSinResultados.isPresent()) {
                Alumno alumno = alumnoSinResultados.get();
                model.addAttribute("alumno", alumno);
                
                if (alumno.getResultados() != null && !alumno.getResultados().isEmpty()) {
                    Resultado resultado = alumno.getResultados().stream()
                            .filter(r -> "VALIDO".equals(r.getEstado()))
                            .findFirst()
                            .orElse(null);
                    
                    if (resultado != null) {
                        model.addAttribute("resultado", resultado);
                        BeneficioDTO beneficio = beneficioService.calcularBeneficio(alumno);
                        model.addAttribute("beneficio", beneficio);
                    }
                }
                
                return "estudiante/mis-beneficios";
            }
            
            model.addAttribute("error", "No se encontró información del estudiante");
            return "estudiante/mis-beneficios";
        }
        
        Alumno alumno = alumnoOpt.get();
        model.addAttribute("alumno", alumno);
        
        if (alumno.getResultados() != null && !alumno.getResultados().isEmpty()) {
            Resultado resultado = alumno.getResultados().stream()
                    .filter(r -> "VALIDO".equals(r.getEstado()))
                    .findFirst()
                    .orElse(null);
            
            if (resultado != null) {
                model.addAttribute("resultado", resultado);
                BeneficioDTO beneficio = beneficioService.calcularBeneficio(alumno);
                model.addAttribute("beneficio", beneficio);
            }
        }
        
        return "estudiante/mis-beneficios";
    }
}
