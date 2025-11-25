package com.saberpro.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "resultado")
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alumno_id", nullable = false)
    private Alumno alumno;

    @Column(name = "numero_registro", unique = true, nullable = false)
    private String numeroRegistro;

    // Puntaje Global
    @Column(name = "puntaje_global")
    private Integer puntajeGlobal;

    @Column(name = "nivel_global")
    private String nivelGlobal;

    // Comunicación Escrita
    @Column(name = "comunicacion_escrita")
    private Integer comunicacionEscrita;

    @Column(name = "comunicacion_escrita_nivel")
    private String comunicacionEscritaNivel;

    // Razonamiento Cuantitativo
    @Column(name = "razonamiento_cuantitativo")
    private Integer razonamientoCuantitativo;

    @Column(name = "razonamiento_cuantitativo_nivel")
    private String razonamientoCuantitativoNivel;

    // Lectura Crítica
    @Column(name = "lectura_critica")
    private Integer lecturaCritica;

    @Column(name = "lectura_critica_nivel")
    private String lecturaCriticaNivel;

    // Competencias Ciudadanas
    @Column(name = "competencias_ciudadanas")
    private Integer competenciasCiudadanas;

    @Column(name = "competencias_ciudadanas_nivel")
    private String competenciasCiudadanasNivel;

    // Inglés (puntaje numérico)
    @Column(name = "ingles")
    private Integer ingles;

    @Column(name = "ingles_nivel")
    private String inglesNivel;

    // Formulación De Proyectos De Ingeniería
    @Column(name = "formulacion_proyectos")
    private Integer formulacionProyectos;

    @Column(name = "formulacion_proyectos_nivel")
    private String formulacionProyectosNivel;

    // Pensamiento Científico - Matemáticas Y Estadística
    @Column(name = "pensamiento_cientifico")
    private Integer pensamientoCientifico;

    @Column(name = "pensamiento_cientifico_nivel")
    private String pensamientoCientificoNivel;

    // Diseño De Software
    @Column(name = "diseño_software")
    private Integer diseñoSoftware;

    @Column(name = "diseño_software_nivel")
    private String diseñoSoftwareNivel;

    // Nivel Inglés (A0, A1, A2, B1, B2, etc.)
    @Column(name = "nivel_ingles")
    private String nivelIngles;

    // Información adicional
    @Column(name = "fecha_presentacion")
    private LocalDate fechaPresentacion;

    @Column(name = "periodo_academico")
    private String periodoAcademico;

    @Column(name = "estado")
    private String estado = "VALIDO";

    // Constructores
    public Resultado() {
        this.fechaPresentacion = LocalDate.of(2024, 3, 15);
        this.periodoAcademico = "2024-1";
        this.estado = "VALIDO";
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public Integer getPuntajeGlobal() {
        return puntajeGlobal;
    }

    public void setPuntajeGlobal(Integer puntajeGlobal) {
        this.puntajeGlobal = puntajeGlobal;
    }

    public String getNivelGlobal() {
        return nivelGlobal;
    }

    public void setNivelGlobal(String nivelGlobal) {
        this.nivelGlobal = nivelGlobal;
    }

    public Integer getComunicacionEscrita() {
        return comunicacionEscrita;
    }

    public void setComunicacionEscrita(Integer comunicacionEscrita) {
        this.comunicacionEscrita = comunicacionEscrita;
    }

    public String getComunicacionEscritaNivel() {
        return comunicacionEscritaNivel;
    }

    public void setComunicacionEscritaNivel(String comunicacionEscritaNivel) {
        this.comunicacionEscritaNivel = comunicacionEscritaNivel;
    }

    public Integer getRazonamientoCuantitativo() {
        return razonamientoCuantitativo;
    }

    public void setRazonamientoCuantitativo(Integer razonamientoCuantitativo) {
        this.razonamientoCuantitativo = razonamientoCuantitativo;
    }

    public String getRazonamientoCuantitativoNivel() {
        return razonamientoCuantitativoNivel;
    }

    public void setRazonamientoCuantitativoNivel(String razonamientoCuantitativoNivel) {
        this.razonamientoCuantitativoNivel = razonamientoCuantitativoNivel;
    }

    public Integer getLecturaCritica() {
        return lecturaCritica;
    }

    public void setLecturaCritica(Integer lecturaCritica) {
        this.lecturaCritica = lecturaCritica;
    }

    public String getLecturaCriticaNivel() {
        return lecturaCriticaNivel;
    }

    public void setLecturaCriticaNivel(String lecturaCriticaNivel) {
        this.lecturaCriticaNivel = lecturaCriticaNivel;
    }

    public Integer getCompetenciasCiudadanas() {
        return competenciasCiudadanas;
    }

    public void setCompetenciasCiudadanas(Integer competenciasCiudadanas) {
        this.competenciasCiudadanas = competenciasCiudadanas;
    }

    public String getCompetenciasCiudadanasNivel() {
        return competenciasCiudadanasNivel;
    }

    public void setCompetenciasCiudadanasNivel(String competenciasCiudadanasNivel) {
        this.competenciasCiudadanasNivel = competenciasCiudadanasNivel;
    }

    public Integer getIngles() {
        return ingles;
    }

    public void setIngles(Integer ingles) {
        this.ingles = ingles;
    }

    public String getInglesNivel() {
        return inglesNivel;
    }

    public void setInglesNivel(String inglesNivel) {
        this.inglesNivel = inglesNivel;
    }

    public Integer getFormulacionProyectos() {
        return formulacionProyectos;
    }

    public void setFormulacionProyectos(Integer formulacionProyectos) {
        this.formulacionProyectos = formulacionProyectos;
    }

    public String getFormulacionProyectosNivel() {
        return formulacionProyectosNivel;
    }

    public void setFormulacionProyectosNivel(String formulacionProyectosNivel) {
        this.formulacionProyectosNivel = formulacionProyectosNivel;
    }

    public Integer getPensamientoCientifico() {
        return pensamientoCientifico;
    }

    public void setPensamientoCientifico(Integer pensamientoCientifico) {
        this.pensamientoCientifico = pensamientoCientifico;
    }

    public String getPensamientoCientificoNivel() {
        return pensamientoCientificoNivel;
    }

    public void setPensamientoCientificoNivel(String pensamientoCientificoNivel) {
        this.pensamientoCientificoNivel = pensamientoCientificoNivel;
    }

    public Integer getDiseñoSoftware() {
        return diseñoSoftware;
    }

    public void setDiseñoSoftware(Integer diseñoSoftware) {
        this.diseñoSoftware = diseñoSoftware;
    }

    public String getDiseñoSoftwareNivel() {
        return diseñoSoftwareNivel;
    }

    public void setDiseñoSoftwareNivel(String diseñoSoftwareNivel) {
        this.diseñoSoftwareNivel = diseñoSoftwareNivel;
    }

    public String getNivelIngles() {
        return nivelIngles;
    }

    public void setNivelIngles(String nivelIngles) {
        this.nivelIngles = nivelIngles;
    }

    public LocalDate getFechaPresentacion() {
        return fechaPresentacion;
    }

    public void setFechaPresentacion(LocalDate fechaPresentacion) {
        this.fechaPresentacion = fechaPresentacion;
    }

    public String getPeriodoAcademico() {
        return periodoAcademico;
    }

    public void setPeriodoAcademico(String periodoAcademico) {
        this.periodoAcademico = periodoAcademico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Métodos utilitarios según los beneficios UTS
    public String getBeneficio() {
        if (puntajeGlobal == null || estado.equals("ANULADO")) {
            return "Examen Anulado";
        }
        if (puntajeGlobal < 80) {
            return "NO PUEDE GRADUARSE";
        } else if (puntajeGlobal >= 180 && puntajeGlobal <= 210) {
            return "Nota 4.5";
        } else if (puntajeGlobal >= 211 && puntajeGlobal <= 240) {
            return "Beca 50% + Nota 4.7";
        } else if (puntajeGlobal >= 241) {
            return "Beca 100% + Nota 5.0";
        }
        return "Sin beneficio especial";
    }

    public boolean puedeGraduarse() {
        return puntajeGlobal != null && puntajeGlobal >= 80 && !estado.equals("ANULADO");
    }

    public boolean tieneBeneficios() {
        return puntajeGlobal != null && puntajeGlobal >= 180;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resultado resultado = (Resultado) o;
        return Objects.equals(id, resultado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Resultado{" +
                "id=" + id +
                ", numeroRegistro='" + numeroRegistro + '\'' +
                ", puntajeGlobal=" + puntajeGlobal +
                ", nivelGlobal='" + nivelGlobal + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
