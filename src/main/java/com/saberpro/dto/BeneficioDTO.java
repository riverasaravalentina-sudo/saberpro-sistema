package com.saberpro.dto;

import com.saberpro.entity.Alumno;

public class BeneficioDTO {
    
    private Alumno alumno;
    private String beneficio;
    private String descripcion;
    private String color;
    private boolean puedeGraduarse;
    private Integer puntaje;
    private String nivelDesempeno;

    // Constructores
    public BeneficioDTO() {}

    public BeneficioDTO(Alumno alumno, String beneficio, String descripcion, 
                       String color, boolean puedeGraduarse) {
        this.alumno = alumno;
        this.beneficio = beneficio;
        this.descripcion = descripcion;
        this.color = color;
        this.puedeGraduarse = puedeGraduarse;
    }

    // Getters y Setters
    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(String beneficio) {
        this.beneficio = beneficio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isPuedeGraduarse() {
        return puedeGraduarse;
    }

    public void setPuedeGraduarse(boolean puedeGraduarse) {
        this.puedeGraduarse = puedeGraduarse;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public String getNivelDesempeno() {
        return nivelDesempeno;
    }

    public void setNivelDesempeno(String nivelDesempeno) {
        this.nivelDesempeno = nivelDesempeno;
    }

    @Override
    public String toString() {
        return "BeneficioDTO{" +
                "alumno=" + (alumno != null ? alumno.getNombreCompleto() : "null") +
                ", beneficio='" + beneficio + '\'' +
                ", puedeGraduarse=" + puedeGraduarse +
                ", puntaje=" + puntaje +
                '}';
    }
}