package com.saberpro.repository;

import com.saberpro.entity.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Long> {

    /**
     * Buscar resultado por ID de alumno
     */
    Optional<Resultado> findByAlumnoId(Long alumnoId);

    /**
     * Buscar resultado por documento de alumno
     */
    @Query("SELECT r FROM Resultado r WHERE r.alumno.numeroDocumento = :numeroDocumento")
    Optional<Resultado> findByAlumnoNumeroDocumento(@Param("numeroDocumento") String numeroDocumento);

    /**
     * Obtener resultados por rango de puntaje global
     */
    List<Resultado> findByPuntajeGlobalBetween(Integer puntajeMin, Integer puntajeMax);

    /**
     * Obtener resultados por programa académico
     */
    @Query("SELECT r FROM Resultado r WHERE r.alumno.programa = :programa")
    List<Resultado> findByPrograma(@Param("programa") String programa);

    /**
     * Obtener resultados por periodo académico
     */
    List<Resultado> findByPeriodoAcademico(String periodoAcademico);

    /**
     * Obtener resultados por fecha de presentación
     */
    List<Resultado> findByFechaPresentacionBetween(LocalDate fechaInicio, LocalDate fechaFin);

    /**
     * Obtener estadísticas de puntajes (solo resultados válidos)
     */
    @Query("SELECT AVG(r.puntajeGlobal), MIN(r.puntajeGlobal), MAX(r.puntajeGlobal), COUNT(r) FROM Resultado r WHERE r.estado = 'VALIDO'")
    Object[] getEstadisticasPuntajeGlobal();

    /**
     * Obtener estadísticas por módulo (solo resultados válidos)
     */
    @Query("SELECT AVG(r.lecturaCritica), AVG(r.razonamientoCuantitativo), AVG(r.ingles) FROM Resultado r WHERE r.estado = 'VALIDO'")
    Object[] getPromediosPorModulo();

    /**
     * Contar resultados por nivel de desempeño (solo resultados válidos)
     */
    @Query("SELECT " +
           "SUM(CASE WHEN r.puntajeGlobal < 80 THEN 1 ELSE 0 END) as insuficiente, " +
           "SUM(CASE WHEN r.puntajeGlobal >= 80 AND r.puntajeGlobal <= 150 THEN 1 ELSE 0 END) as minimo, " +
           "SUM(CASE WHEN r.puntajeGlobal >= 151 AND r.puntajeGlobal <= 170 THEN 1 ELSE 0 END) as satisfactorio, " +
           "SUM(CASE WHEN r.puntajeGlobal > 170 THEN 1 ELSE 0 END) as avanzado " +
           "FROM Resultado r WHERE r.estado = 'VALIDO'")
    Object[] contarPorNivelDesempeno();

    /**
     * Obtener top 10 mejores resultados (solo resultados válidos)
     */
    @Query("SELECT r FROM Resultado r WHERE r.estado = 'VALIDO' ORDER BY r.puntajeGlobal DESC LIMIT 10")
    List<Resultado> findTop10ByOrderByPuntajeGlobalDesc();

    /**
     * Obtener resultados ordenados por puntaje global descendente
     */
    List<Resultado> findAllByOrderByPuntajeGlobalDesc();

    /**
     * Buscar resultados por programa y periodo
     */
    @Query("SELECT r FROM Resultado r WHERE r.alumno.programa = :programa AND r.periodoAcademico = :periodo")
    List<Resultado> findByProgramaAndPeriodo(@Param("programa") String programa, @Param("periodo") String periodo);

    /**
     * Obtener promedio de puntaje por programa (solo resultados válidos)
     */
    @Query("SELECT r.alumno.programa, AVG(r.puntajeGlobal) FROM Resultado r WHERE r.estado = 'VALIDO' GROUP BY r.alumno.programa ORDER BY AVG(r.puntajeGlobal) DESC")
    List<Object[]> getPromedioPorPrograma();
    
    /**
     * Contar total de estudiantes
     */
    @Query("SELECT COUNT(DISTINCT r.alumno.id) FROM Resultado r")
    Long countDistinctAlumnos();
    
    /**
     * Contar estudiantes con resultados válidos
     */
    @Query("SELECT COUNT(DISTINCT r.alumno.id) FROM Resultado r WHERE r.estado = 'VALIDO'")
    Long countAlumnosConResultadosValidos();
    
    /**
     * Contar estudiantes pendientes (sin resultados o con estado ANULADO)
     */
    @Query("SELECT COUNT(a) FROM Alumno a WHERE a.id NOT IN (SELECT r.alumno.id FROM Resultado r WHERE r.estado = 'VALIDO')")
    Long countAlumnosPendientes();
    
    /**
     * Contar estudiantes con excelencia (puntaje >= 180)
     */
    @Query("SELECT COUNT(r) FROM Resultado r WHERE r.puntajeGlobal >= 180 AND r.estado = 'VALIDO'")
    Long countAlumnosExcelencia();
    
    /**
     * Contar resultados por estado
     */
    Long countByEstado(String estado);
}