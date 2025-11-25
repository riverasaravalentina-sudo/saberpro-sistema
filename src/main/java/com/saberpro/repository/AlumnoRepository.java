package com.saberpro.repository;

import com.saberpro.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    /**
     * Buscar alumno por documento
     */
    Optional<Alumno> findByNumeroDocumento(String numeroDocumento);

    /**
     * Buscar alumno por correo
     */
    Optional<Alumno> findByCorreoElectronico(String correoElectronico);

    /**
     * Buscar alumnos por programa académico
     */
    List<Alumno> findByProgramaIgnoreCase(String programa);

    /**
     * Buscar alumnos que contengan el texto en nombres o apellidos
     */
    @Query("SELECT a FROM Alumno a WHERE LOWER(a.primerNombre) LIKE LOWER(CONCAT('%', :texto, '%')) OR LOWER(a.segundoNombre) LIKE LOWER(CONCAT('%', :texto, '%')) OR LOWER(a.primerApellido) LIKE LOWER(CONCAT('%', :texto, '%')) OR LOWER(a.segundoApellido) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<Alumno> buscarPorNombreOApellido(@Param("texto") String texto);

    /**
     * Obtener alumnos con resultados SABER PRO
     */
    @Query("SELECT a FROM Alumno a INNER JOIN FETCH a.resultados r WHERE r.estado = 'VALIDO'")
    List<Alumno> findAlumnosConResultados();
    
    /**
     * Buscar alumno por documento con resultados cargados
     */
    @Query("SELECT DISTINCT a FROM Alumno a LEFT JOIN FETCH a.resultados WHERE a.numeroDocumento = :documento")
    Optional<Alumno> findByNumeroDocumentoWithResultados(@Param("documento") String documento);

    /**
     * Obtener alumnos sin resultados SABER PRO
     */
    @Query("SELECT a FROM Alumno a WHERE a.resultados IS EMPTY")
    List<Alumno> findAlumnosSinResultados();

    /**
     * Contar alumnos por programa
     */
    @Query("SELECT a.programa, COUNT(a) FROM Alumno a GROUP BY a.programa ORDER BY a.programa")
    List<Object[]> contarAlumnosPorPrograma();

    /**
     * Verificar si existe alumno por documento
     */
    boolean existsByNumeroDocumento(String numeroDocumento);

    /**
     * Verificar si existe alumno por correo
     */
    boolean existsByCorreoElectronico(String correoElectronico);

    /**
     * Buscar alumnos ordenados por apellidos
     */
    List<Alumno> findAllByOrderByPrimerApellidoAsc();
}