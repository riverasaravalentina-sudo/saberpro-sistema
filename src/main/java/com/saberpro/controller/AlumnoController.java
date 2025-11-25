package com.saberpro.controller;

import com.saberpro.entity.Alumno;
import com.saberpro.service.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    /**
     * Listar todos los alumnos
     */
    @GetMapping
    public String listar(Model model, @RequestParam(required = false) String buscar) {
        if (buscar != null && !buscar.trim().isEmpty()) {
            model.addAttribute("alumnos", alumnoService.buscarPorNombreOApellido(buscar));
            model.addAttribute("buscar", buscar);
        } else {
            model.addAttribute("alumnos", alumnoService.listarTodos());
        }
        
        // Estadísticas para la vista
        model.addAttribute("totalAlumnos", alumnoService.contarTotalAlumnos());
        model.addAttribute("alumnosConResultados", alumnoService.obtenerAlumnosConResultados().size());
        model.addAttribute("alumnosSinResultados", alumnoService.obtenerAlumnosSinResultados().size());
        
        return "alumnos/list";
    }

    /**
     * Mostrar formulario para nuevo alumno
     */
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("alumno", new Alumno());
        model.addAttribute("titulo", "Nuevo Alumno");
        model.addAttribute("accion", "Crear");
        return "alumnos/form";
    }

    /**
     * Mostrar formulario para editar alumno
     */
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Alumno> alumno = alumnoService.buscarPorId(id);
        if (alumno.isPresent()) {
            model.addAttribute("alumno", alumno.get());
            model.addAttribute("titulo", "Editar Alumno");
            model.addAttribute("accion", "Actualizar");
            return "alumnos/form";
        } else {
            redirectAttributes.addFlashAttribute("error", "Alumno no encontrado");
            return "redirect:/alumnos";
        }
    }

    /**
     * Guardar alumno (nuevo o editado)
     */
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Alumno alumno, 
                         BindingResult result, 
                         Model model, 
                         RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            model.addAttribute("titulo", alumno.getId() != null ? "Editar Alumno" : "Nuevo Alumno");
            model.addAttribute("accion", alumno.getId() != null ? "Actualizar" : "Crear");
            return "alumnos/form";
        }
        
        try {
            alumnoService.guardar(alumno);
            redirectAttributes.addFlashAttribute("success", 
                alumno.getId() != null ? "Alumno actualizado correctamente" : "Alumno creado correctamente");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        
        return "redirect:/alumnos";
    }

    /**
     * Ver detalle de alumno
     */
    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Alumno> alumno = alumnoService.buscarPorId(id);
        if (alumno.isPresent()) {
            model.addAttribute("alumno", alumno.get());
            return "alumnos/detalle";
        } else {
            redirectAttributes.addFlashAttribute("error", "Alumno no encontrado");
            return "redirect:/alumnos";
        }
    }

    /**
     * Eliminar alumno
     */
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Optional<Alumno> alumno = alumnoService.buscarPorId(id);
            if (alumno.isPresent()) {
                // Eliminar alumno (ON DELETE CASCADE eliminará sus resultados automáticamente)
                alumnoService.eliminar(id);
                String mensaje = alumno.get().tieneResultados() 
                    ? "Alumno y sus resultados eliminados correctamente" 
                    : "Alumno eliminado correctamente";
                redirectAttributes.addFlashAttribute("success", mensaje);
            } else {
                redirectAttributes.addFlashAttribute("error", "Alumno no encontrado");
            }
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        
        return "redirect:/alumnos";
    }

    /**
     * Buscar alumnos por programa
     */
    @GetMapping("/programa/{programa}")
    public String listarPorPrograma(@PathVariable String programa, Model model) {
        model.addAttribute("alumnos", alumnoService.buscarPorPrograma(programa));
        model.addAttribute("programa", programa);
        model.addAttribute("titulo", "Alumnos de " + programa);
        return "alumnos/list";
    }

    /**
     * Obtener alumnos pendientes de presentar SABER PRO
     */
    @GetMapping("/pendientes")
    public String alumnosPendientes(Model model) {
        model.addAttribute("alumnos", alumnoService.obtenerAlumnosSinResultados());
        model.addAttribute("titulo", "Alumnos Pendientes de Presentar SABER PRO");
        return "alumnos/list";
    }
}