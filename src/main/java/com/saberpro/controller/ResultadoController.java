package com.saberpro.controller;

import com.saberpro.entity.Resultado;
import com.saberpro.entity.Alumno;
import com.saberpro.service.ResultadoService;
import com.saberpro.service.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/resultados")
public class ResultadoController {

    @Autowired
    private ResultadoService resultadoService;

    @Autowired
    private AlumnoService alumnoService;

    /**
     * Listar todos los resultados
     */
    @GetMapping
    public String listar(Model model, @RequestParam(required = false) String programa) {
        if (programa != null && !programa.trim().isEmpty()) {
            model.addAttribute("resultados", resultadoService.obtenerPorPrograma(programa));
            model.addAttribute("programa", programa);
        } else {
            model.addAttribute("resultados", resultadoService.listarTodos());
        }
        
        // Estadísticas para la vista
        Object[] stats = resultadoService.obtenerEstadisticasGenerales();
        if (stats != null && stats.length >= 4) {
            model.addAttribute("promedio", stats[0]);
            model.addAttribute("minimo", stats[1]);
            model.addAttribute("maximo", stats[2]);
            model.addAttribute("total", stats[3]);
        }
        
        return "resultados/list";
    }

    /**
     * Mostrar formulario para nuevo resultado
     */
    @GetMapping("/nuevo")
    public String nuevo(Model model, @RequestParam(required = false) Long alumnoId) {
        try {
            Resultado resultado = new Resultado();
            
            // Si se especifica un alumno, verificar que exista y no tenga resultados
            if (alumnoId != null) {
                Optional<Alumno> alumno = alumnoService.buscarPorId(alumnoId);
                if (alumno.isPresent()) {
                    if (alumno.get().tieneResultados()) {
                        model.addAttribute("error", "El alumno ya tiene resultados registrados");
                        return "redirect:/resultados";
                    }
                    resultado.setAlumno(alumno.get());
                }
            }
            
            List<Alumno> alumnosSinResultados = alumnoService.obtenerAlumnosSinResultados();
            
            model.addAttribute("resultado", resultado);
            model.addAttribute("alumnos", alumnosSinResultados != null ? alumnosSinResultados : alumnoService.listarTodos());
            model.addAttribute("titulo", "Nuevo Resultado SABER PRO");
            model.addAttribute("accion", "Crear");
            return "resultados/form";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar el formulario: " + e.getMessage());
            return "redirect:/dashboard";
        }
    }

    /**
     * Mostrar formulario para editar resultado
     */
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Resultado> resultado = resultadoService.buscarPorId(id);
        if (resultado.isPresent()) {
            model.addAttribute("resultado", resultado.get());
            model.addAttribute("alumnos", alumnoService.listarTodos());
            model.addAttribute("titulo", "Editar Resultado SABER PRO");
            model.addAttribute("accion", "Actualizar");
            return "resultados/form";
        } else {
            redirectAttributes.addFlashAttribute("error", "Resultado no encontrado");
            return "redirect:/resultados";
        }
    }

    /**
     * Guardar resultado (nuevo o editado)
     */
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Resultado resultado, 
                         BindingResult bindingResult, 
                         Model model, 
                         RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("alumnos", resultado.getId() != null ? 
                               alumnoService.listarTodos() : alumnoService.obtenerAlumnosSinResultados());
            model.addAttribute("titulo", resultado.getId() != null ? "Editar Resultado SABER PRO" : "Nuevo Resultado SABER PRO");
            model.addAttribute("accion", resultado.getId() != null ? "Actualizar" : "Crear");
            return "resultados/form";
        }
        
        try {
            resultadoService.guardar(resultado);
            redirectAttributes.addFlashAttribute("success", 
                resultado.getId() != null ? "Resultado actualizado correctamente" : "Resultado creado correctamente");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        
        return "redirect:/resultados";
    }

    /**
     * Ver detalle de resultado
     */
    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Resultado> resultado = resultadoService.buscarPorId(id);
        if (resultado.isPresent()) {
            model.addAttribute("resultado", resultado.get());
            return "resultados/detalle";
        } else {
            redirectAttributes.addFlashAttribute("error", "Resultado no encontrado");
            return "redirect:/resultados";
        }
    }

    /**
     * Ver resultado por alumno
     */
    @GetMapping("/alumno/{alumnoId}")
    public String resultadoPorAlumno(@PathVariable Long alumnoId, Model model, RedirectAttributes redirectAttributes) {
        Optional<Resultado> resultado = resultadoService.buscarPorAlumno(alumnoId);
        if (resultado.isPresent()) {
            model.addAttribute("resultado", resultado.get());
            return "resultados/detalle";
        } else {
            redirectAttributes.addFlashAttribute("error", "El alumno no tiene resultados registrados");
            return "redirect:/alumnos";
        }
    }

    /**
     * Eliminar resultado
     */
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            resultadoService.eliminar(id);
            redirectAttributes.addFlashAttribute("success", "Resultado eliminado correctamente");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        
        return "redirect:/resultados";
    }

    /**
     * Top 10 mejores resultados
     */
    @GetMapping("/top10")
    public String top10(Model model) {
        List<Resultado> resultados = resultadoService.obtenerTop10();
        System.out.println("========== DEBUG TOP 10 ==========");
        System.out.println("Cantidad de resultados encontrados: " + resultados.size());
        for (Resultado r : resultados) {
            System.out.println("Resultado ID: " + r.getId() + 
                             ", Puntaje: " + r.getPuntajeGlobal() + 
                             ", Estado: " + r.getEstado() +
                             ", Alumno: " + (r.getAlumno() != null ? r.getAlumno().getId() : "null"));
        }
        System.out.println("==================================");
        
        model.addAttribute("resultados", resultados);
        model.addAttribute("titulo", "Top 10 Mejores Resultados");
        return "resultados/top10";
    }

    /**
     * Resultados por programa
     */
    @GetMapping("/programa/{programa}")
    public String resultadosPorPrograma(@PathVariable String programa, Model model) {
        model.addAttribute("resultados", resultadoService.obtenerPorPrograma(programa));
        model.addAttribute("programa", programa);
        model.addAttribute("titulo", "Resultados SABER PRO - " + programa);
        return "resultados/list";
    }
}