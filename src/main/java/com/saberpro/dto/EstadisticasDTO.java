package com.saberpro.dto;

public class EstadisticasDTO {
    
    private Long totalAlumnos;
    private Long totalResultados;
    private Long alumnosPendientes;
    private Double promedioPuntajeGlobal;
    private Integer mejorPuntaje;
    private Integer peorPuntaje;
    
    // Estadísticas por nivel de desempeño
    private Long nivelInsuficiente;
    private Long nivelMinimo;
    private Long nivelSatisfactorio;
    private Long nivelAvanzado;
    
    // Estadísticas por módulos
    private Double promedioLectura;
    private Double promedioRazonamiento;
    private Double promedioIngles;
    
    // Beneficios
    private Long noPuedenGraduarse;
    private Long exoneracionParcial;
    private Long exoneracionTotal50;
    private Long exoneracionTotal100;

    // Constructores
    public EstadisticasDTO() {}

    // Getters y Setters
    public Long getTotalAlumnos() {
        return totalAlumnos;
    }

    public void setTotalAlumnos(Long totalAlumnos) {
        this.totalAlumnos = totalAlumnos;
    }

    public Long getTotalResultados() {
        return totalResultados;
    }

    public void setTotalResultados(Long totalResultados) {
        this.totalResultados = totalResultados;
    }

    public Long getAlumnosPendientes() {
        return alumnosPendientes;
    }

    public void setAlumnosPendientes(Long alumnosPendientes) {
        this.alumnosPendientes = alumnosPendientes;
    }

    public Double getPromedioPuntajeGlobal() {
        return promedioPuntajeGlobal;
    }

    public void setPromedioPuntajeGlobal(Double promedioPuntajeGlobal) {
        this.promedioPuntajeGlobal = promedioPuntajeGlobal;
    }

    public Integer getMejorPuntaje() {
        return mejorPuntaje;
    }

    public void setMejorPuntaje(Integer mejorPuntaje) {
        this.mejorPuntaje = mejorPuntaje;
    }

    public Integer getPeorPuntaje() {
        return peorPuntaje;
    }

    public void setPeorPuntaje(Integer peorPuntaje) {
        this.peorPuntaje = peorPuntaje;
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

    public Double getPromedioLectura() {
        return promedioLectura;
    }

    public void setPromedioLectura(Double promedioLectura) {
        this.promedioLectura = promedioLectura;
    }

    public Double getPromedioRazonamiento() {
        return promedioRazonamiento;
    }

    public void setPromedioRazonamiento(Double promedioRazonamiento) {
        this.promedioRazonamiento = promedioRazonamiento;
    }

    public Double getPromedioIngles() {
        return promedioIngles;
    }

    public void setPromedioIngles(Double promedioIngles) {
        this.promedioIngles = promedioIngles;
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

    public Long getExoneracionTotal50() {
        return exoneracionTotal50;
    }

    public void setExoneracionTotal50(Long exoneracionTotal50) {
        this.exoneracionTotal50 = exoneracionTotal50;
    }

    public Long getExoneracionTotal100() {
        return exoneracionTotal100;
    }

    public void setExoneracionTotal100(Long exoneracionTotal100) {
        this.exoneracionTotal100 = exoneracionTotal100;
    }

    // Métodos calculados
    public Double getPorcentajeConResultados() {
        if (totalAlumnos == null || totalAlumnos == 0) return 0.0;
        return (totalResultados != null) ? (totalResultados.doubleValue() / totalAlumnos) * 100 : 0.0;
    }

    public Double getPorcentajePendientes() {
        if (totalAlumnos == null || totalAlumnos == 0) return 0.0;
        return (alumnosPendientes != null) ? (alumnosPendientes.doubleValue() / totalAlumnos) * 100 : 0.0;
    }

    public Long getTotalConBeneficios() {
        return (exoneracionParcial != null ? exoneracionParcial : 0L) +
               (exoneracionTotal50 != null ? exoneracionTotal50 : 0L) +
               (exoneracionTotal100 != null ? exoneracionTotal100 : 0L);
    }

    @Override
    public String toString() {
        return "EstadisticasDTO{" +
                "totalAlumnos=" + totalAlumnos +
                ", totalResultados=" + totalResultados +
                ", promedioPuntajeGlobal=" + promedioPuntajeGlobal +
                ", mejorPuntaje=" + mejorPuntaje +
                ", peorPuntaje=" + peorPuntaje +
                '}';
    }
}