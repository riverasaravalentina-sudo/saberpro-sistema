package com.saberpro.service;

import com.saberpro.entity.Resultado;
import com.saberpro.entity.Alumno;
import com.saberpro.repository.ResultadoRepository;
import com.saberpro.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ResultadoService {

    @Autowired
    private ResultadoRepository resultadoRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    /**
     * Obtener todos los resultados
     */
    public List<Resultado> listarTodos() {
        return resultadoRepository.findAllByOrderByPuntajeGlobalDesc();
    }

    /**
     * Guardar o actualizar resultado
     */
    public Resultado guardar(Resultado resultado) {
        // Validar que el alumno existe
        if (resultado.getAlumno() == null || resultado.getAlumno().getId() == null) {
            throw new RuntimeException("Debe especificar un alumno válido");
        }

        Optional<Alumno> alumno = alumnoRepository.findById(resultado.getAlumno().getId());
        if (!alumno.isPresent()) {
            throw new RuntimeException("No se encontró el alumno especificado");
        }

        // Verificar si el alumno ya tiene resultados (solo permitir uno por alumno)
        if (resultado.getId() == null) {
            Optional<Resultado> existente = resultadoRepository.findByAlumnoId(resultado.getAlumno().getId());
            if (existente.isPresent()) {
                throw new RuntimeException("El alumno ya tiene resultados registrados. Solo se permite un resultado por alumno.");
            }
        }

        // Establecer fecha de presentación si no existe
        if (resultado.getFechaPresentacion() == null) {
            resultado.setFechaPresentacion(LocalDate.now());
        }

        // Validar rangos de puntajes
        validarPuntajes(resultado);

        return resultadoRepository.save(resultado);
    }

    /**
     * Buscar resultado por ID
     */
    public Optional<Resultado> buscarPorId(Long id) {
        return resultadoRepository.findById(id);
    }

    /**
     * Buscar resultado por ID de alumno
     */
    public Optional<Resultado> buscarPorAlumno(Long alumnoId) {
        return resultadoRepository.findByAlumnoId(alumnoId);
    }

    /**
     * Buscar resultado por documento de alumno
     */
    public Optional<Resultado> buscarPorDocumentoAlumno(String numeroDocumento) {
        return resultadoRepository.findByAlumnoNumeroDocumento(numeroDocumento);
    }

    /**
     * Obtener resultados por programa
     */
    public List<Resultado> obtenerPorPrograma(String programa) {
        return resultadoRepository.findByPrograma(programa);
    }

    /**
     * Obtener resultados por periodo académico
     */
    public List<Resultado> obtenerPorPeriodo(String periodo) {
        return resultadoRepository.findByPeriodoAcademico(periodo);
    }

    /**
     * Obtener resultados por rango de puntaje
     */
    public List<Resultado> obtenerPorRangoPuntaje(Integer puntajeMin, Integer puntajeMax) {
        return resultadoRepository.findByPuntajeGlobalBetween(puntajeMin, puntajeMax);
    }

    /**
     * Obtener resultados por rango de fechas
     */
    public List<Resultado> obtenerPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return resultadoRepository.findByFechaPresentacionBetween(fechaInicio, fechaFin);
    }

    /**
     * Obtener top 10 mejores resultados
     */
    public List<Resultado> obtenerTop10() {
        return resultadoRepository.findTop10ByOrderByPuntajeGlobalDesc();
    }

    /**
     * Obtener estadísticas generales
     */
    public Object[] obtenerEstadisticasGenerales() {
        return resultadoRepository.getEstadisticasPuntajeGlobal();
    }

    /**
     * Obtener promedios por módulo
     */
    public Object[] obtenerPromediosPorModulo() {
        return resultadoRepository.getPromediosPorModulo();
    }

    /**
     * Contar resultados por nivel de desempeño
     */
    public Object[] contarPorNivelDesempeno() {
        return resultadoRepository.contarPorNivelDesempeno();
    }

    /**
     * Obtener promedio por programa
     */
    public List<Object[]> obtenerPromedioPorPrograma() {
        return resultadoRepository.getPromedioPorPrograma();
    }

    /**
     * Eliminar resultado
     */
    public void eliminar(Long id) {
        if (!resultadoRepository.existsById(id)) {
            throw new RuntimeException("No se encontró el resultado con ID: " + id);
        }
        resultadoRepository.deleteById(id);
    }

    /**
     * Contar total de resultados
     */
    public long contarTotalResultados() {
        return resultadoRepository.count();
    }

    /**
     * Validar rangos de puntajes
     */
    private void validarPuntajes(Resultado resultado) {
        if (resultado.getPuntajeGlobal() != null && (resultado.getPuntajeGlobal() < 0 || resultado.getPuntajeGlobal() > 300)) {
            throw new RuntimeException("El puntaje global debe estar entre 0 y 300");
        }
        if (resultado.getLecturaCritica() != null && (resultado.getLecturaCritica() < 0 || resultado.getLecturaCritica() > 300)) {
            throw new RuntimeException("El puntaje de lectura crítica debe estar entre 0 y 300");
        }
        if (resultado.getRazonamientoCuantitativo() != null && (resultado.getRazonamientoCuantitativo() < 0 || resultado.getRazonamientoCuantitativo() > 300)) {
            throw new RuntimeException("El puntaje de razonamiento cuantitativo debe estar entre 0 y 300");
        }
        if (resultado.getIngles() != null && (resultado.getIngles() < 0 || resultado.getIngles() > 300)) {
            throw new RuntimeException("El puntaje de inglés debe estar entre 0 y 300");
        }
        if (resultado.getComunicacionEscrita() != null && (resultado.getComunicacionEscrita() < 0 || resultado.getComunicacionEscrita() > 300)) {
            throw new RuntimeException("El puntaje de comunicación escrita debe estar entre 0 y 300");
        }
        if (resultado.getCompetenciasCiudadanas() != null && (resultado.getCompetenciasCiudadanas() < 0 || resultado.getCompetenciasCiudadanas() > 300)) {
            throw new RuntimeException("El puntaje de competencias ciudadanas debe estar entre 0 y 300");
        }
        if (resultado.getFormulacionProyectos() != null && (resultado.getFormulacionProyectos() < 0 || resultado.getFormulacionProyectos() > 300)) {
            throw new RuntimeException("El puntaje de formulación de proyectos debe estar entre 0 y 300");
        }
        if (resultado.getPensamientoCientifico() != null && (resultado.getPensamientoCientifico() < 0 || resultado.getPensamientoCientifico() > 300)) {
            throw new RuntimeException("El puntaje de pensamiento científico debe estar entre 0 y 300");
        }
        if (resultado.getDiseñoSoftware() != null && (resultado.getDiseñoSoftware() < 0 || resultado.getDiseñoSoftware() > 300)) {
            throw new RuntimeException("El puntaje de diseño de software debe estar entre 0 y 300");
        }
    }

    /**
     * Actualizar periodo académico en lote
     */
    public void actualizarPeriodoAcademico(String periodoAnterior, String periodoNuevo) {
        List<Resultado> resultados = obtenerPorPeriodo(periodoAnterior);
        for (Resultado resultado : resultados) {
            resultado.setPeriodoAcademico(periodoNuevo);
            resultadoRepository.save(resultado);
        }
    }

    /**
     * Contar total de alumnos distintos con resultados
     */
    public long contarAlumnosConResultados() {
        return resultadoRepository.countAlumnosConResultadosValidos();
    }

    /**
     * Contar alumnos pendientes (sin resultados)
     */
    public long contarAlumnosPendientes() {
        return resultadoRepository.countAlumnosPendientes();
    }

    /**
     * Contar alumnos con excelencia (puntaje >= 180)
     */
    public long contarAlumnosExcelencia() {
        return resultadoRepository.countAlumnosExcelencia();
    }

    /**
     * Contar total de resultados válidos
     */
    public long contarResultadosValidos() {
        return resultadoRepository.countByEstado("VALIDO");
    }
}