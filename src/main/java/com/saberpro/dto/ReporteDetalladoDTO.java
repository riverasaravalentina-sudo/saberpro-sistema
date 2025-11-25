package com.saberpro.dto;

import java.time.LocalDate;

public class ReporteDetalladoDTO {
    
    private String programa;
    private Long totalAlumnos;
    private Long alumnosConResultados;
    private Long alumnosSinResultados;
    private Double promedioPuntaje;
    private Long noPuedenGraduarse;
    private Long exoneracionParcial;
    private Long exoneracionTotal;
    private LocalDate fechaGeneracion;
    
    // Estadísticas por nivel
    private Long nivelInsuficiente;
    private Long nivelMinimo;
    private Long nivelSatisfactorio;
    private Long nivelAvanzado;

    // Constructores
    public ReporteDetalladoDTO() {
        this.fechaGeneracion = LocalDate.now();
    }

    public ReporteDetalladoDTO(String programa) {
        this();
        this.programa = programa;
    }

    // Getters y Setters
    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public Long getTotalAlumnos() {
        return totalAlumnos;
    }

    public void setTotalAlumnos(Long totalAlumnos) {
        this.totalAlumnos = totalAlumnos;
    }

    public Long getAlumnosConResultados() {
        return alumnosConResultados;
    }

    public void setAlumnosConResultados(Long alumnosConResultados) {
        this.alumnosConResultados = alumnosConResultados;
    }

    public Long getAlumnosSinResultados() {
        return alumnosSinResultados;
    }

    public void setAlumnosSinResultados(Long alumnosSinResultados) {
        this.alumnosSinResultados = alumnosSinResultados;
    }

    public Double getPromedioPuntaje() {
        return promedioPuntaje;
    }

    public void setPromedioPuntaje(Double promedioPuntaje) {
        this.promedioPuntaje = promedioPuntaje;
    }

    public Long getNoPuedenGraduarse() {
        return noPuedenGraduarse;
    }

    public void setNoPuedenGraduarse(Long noPuedenGraduarse) {
        this.noPuedenGraduarse = noPuedenGraduarse;
    }

    public Long getExoneracionParcial() {
        return exoneracionParcial;
    }

    public void setExoneracionParcial(Long exoneracionParcial) {
        this.exoneracionParcial = exoneracionParcial;
    }

    public Long getExoneracionTotal() {
        return exoneracionTotal;
    }

    public void setExoneracionTotal(Long exoneracionTotal) {
        this.exoneracionTotal = exoneracionTotal;
    }

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDate fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public Long getNivelInsuficiente() {
        return nivelInsuficiente;
    }

    public void setNivelInsuficiente(Long nivelInsuficiente) {
        this.nivelInsuficiente = nivelInsuficiente;
    }

    public Long getNivelMinimo() {
        return nivelMinimo;
    }

    public void setNivelMinimo(Long nivelMinimo) {
        this.nivelMinimo = nivelMinimo;
    }

    public Long getNivelSatisfactorio() {
        return nivelSatisfactorio;
    }

    public void setNivelSatisfactorio(Long nivelSatisfactorio) {
        this.nivelSatisfactorio = nivelSatisfactorio;
    }

    public Long getNivelAvanzado() {
        return nivelAvanzado;
    }

    public void setNivelAvanzado(Long nivelAvanzado) {
        this.nivelAvanzado = nivelAvanzado;
    }

    // Métodos calculados
    public Double getPorcentajeConResultados() {
        if (totalAlumnos == null || totalAlumnos == 0) return 0.0;
        return (alumnosConResultados != null) ? (alumnosConResultados.doubleValue() / totalAlumnos) * 100 : 0.0;
    }

    public Double getPorcentajeSinResultados() {
        if (totalAlumnos == null || totalAlumnos == 0) return 0.0;
        return (alumnosSinResultados != null) ? (alumnosSinResultados.doubleValue() / totalAlumnos) * 100 : 0.0;
    }

    public Double getPorcentajePuedenGraduarse() {
        if (alumnosConResultados == null || alumnosConResultados == 0) return 0.0;
        Long puedenGraduarse = (alumnosConResultados != null && noPuedenGraduarse != null) ? 
                              alumnosConResultados - noPuedenGraduarse : 0L;
        return (puedenGraduarse.doubleValue() / alumnosConResultados) * 100;
    }

    @Override
    public String toString() {
        return "ReporteDetalladoDTO{" +
                "programa='" + programa + '\'' +
                ", totalAlumnos=" + totalAlumnos +
                ", alumnosConResultados=" + alumnosConResultados +
                ", promedioPuntaje=" + promedioPuntaje +
                ", fechaGeneracion=" + fechaGeneracion +
                '}';
    }
}