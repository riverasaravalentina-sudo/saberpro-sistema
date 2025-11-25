package com.saberpro.service;

import com.saberpro.entity.Alumno;
import com.saberpro.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    /**
     * Obtener todos los alumnos
     */
    public List<Alumno> listarTodos() {
        return alumnoRepository.findAllByOrderByPrimerApellidoAsc();
    }

    /**
     * Guardar o actualizar alumno
     */
    @Transactional
    public Alumno guardar(Alumno alumno) {
        // Validaciones antes de guardar
        if (alumno.getId() == null) {
            // Es un nuevo alumno, verificar duplicados
            if (existePorDocumento(alumno.getNumeroDocumento())) {
                throw new RuntimeException("Ya existe un alumno con el documento: " + alumno.getNumeroDocumento());
            }
            if (existePorCorreo(alumno.getCorreoElectronico())) {
                throw new RuntimeException("Ya existe un alumno con el correo: " + alumno.getCorreoElectronico());
            }
        } else {
            // Es actualización, verificar que no se duplique con otros registros
            Optional<Alumno> existente = alumnoRepository.findByNumeroDocumento(alumno.getNumeroDocumento());
            if (existente.isPresent() && !existente.get().getId().equals(alumno.getId())) {
                throw new RuntimeException("Ya existe otro alumno con el documento: " + alumno.getNumeroDocumento());
            }
            
            existente = alumnoRepository.findByCorreoElectronico(alumno.getCorreoElectronico());
            if (existente.isPresent() && !existente.get().getId().equals(alumno.getId())) {
                throw new RuntimeException("Ya existe otro alumno con el correo: " + alumno.getCorreoElectronico());
            }
        }
        
        return alumnoRepository.save(alumno);
    }

    /**
     * Buscar alumno por ID
     */
    public Optional<Alumno> buscarPorId(Long id) {
        return alumnoRepository.findById(id);
    }

    /**
     * Buscar alumno por documento
     */
    public Optional<Alumno> buscarPorDocumento(String numeroDocumento) {
        return alumnoRepository.findByNumeroDocumento(numeroDocumento);
    }
    
    /**
     * Buscar alumno por documento con resultados cargados
     */
    @Transactional(readOnly = true)
    public Optional<Alumno> buscarPorDocumentoConResultados(String numeroDocumento) {
        return alumnoRepository.findByNumeroDocumentoWithResultados(numeroDocumento);
    }

    /**
     * Buscar alumno por correo
     */
    public Optional<Alumno> buscarPorCorreo(String correoElectronico) {
        return alumnoRepository.findByCorreoElectronico(correoElectronico);
    }

    /**
     * Buscar alumnos por programa
     */
    public List<Alumno> buscarPorPrograma(String programa) {
        return alumnoRepository.findByProgramaIgnoreCase(programa);
    }

    /**
     * Buscar alumnos por nombre o apellido
     */
    public List<Alumno> buscarPorNombreOApellido(String texto) {
        return alumnoRepository.buscarPorNombreOApellido(texto);
    }

    /**
     * Obtener alumnos con resultados SABER PRO
     */
    @Transactional(readOnly = true)
    public List<Alumno> obtenerAlumnosConResultados() {
        return alumnoRepository.findAlumnosConResultados();
    }

    /**
     * Obtener alumnos sin resultados SABER PRO
     */
    public List<Alumno> obtenerAlumnosSinResultados() {
        return alumnoRepository.findAlumnosSinResultados();
    }

    /**
     * Eliminar alumno por ID
     */
    @Transactional
    public void eliminar(Long id) {
        if (!alumnoRepository.existsById(id)) {
            throw new RuntimeException("No se encontró el alumno con ID: " + id);
        }
        alumnoRepository.deleteById(id);
    }

    /**
     * Verificar si existe alumno por documento
     */
    public boolean existePorDocumento(String numeroDocumento) {
        return alumnoRepository.existsByNumeroDocumento(numeroDocumento);
    }

    /**
     * Verificar si existe alumno por correo
     */
    public boolean existePorCorreo(String correoElectronico) {
        return alumnoRepository.existsByCorreoElectronico(correoElectronico);
    }

    /**
     * Contar alumnos por programa
     */
    public List<Object[]> contarAlumnosPorPrograma() {
        return alumnoRepository.contarAlumnosPorPrograma();
    }

    /**
     * Obtener total de alumnos
     */
    public long contarTotalAlumnos() {
        return alumnoRepository.count();
    }

    /**
     * Obtener alumnos que necesitan presentar SABER PRO
     */
    public List<Alumno> obtenerAlumnosParaPresentar() {
        return obtenerAlumnosSinResultados();
    }
}