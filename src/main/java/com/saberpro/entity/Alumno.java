package com.saberpro.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "alumno")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_documento")
    private String tipoDocumento = "CC";

    @NotBlank(message = "El documento es obligatorio")
    @Column(name = "numero_documento", unique = true, nullable = false)
    private String numeroDocumento;

    @Column(name = "primer_apellido")
    private String primerApellido;

    @Column(name = "segundo_apellido")
    private String segundoApellido;

    @Column(name = "primer_nombre")
    private String primerNombre;

    @Column(name = "segundo_nombre")
    private String segundoNombre;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    @Column(name = "numero_telefonico")
    private String numeroTelefonico;

    @Column(nullable = false)
    private String programa = "Ingeniería de Sistemas";

    private Integer semestre;

    private Boolean activo = true;

    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Resultado> resultados = new ArrayList<>();

    // Constructores
    public Alumno() {}

    public Alumno(String numeroDocumento, String primerNombre, String primerApellido) {
        this.numeroDocumento = numeroDocumento;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.programa = "Ingeniería de Sistemas";
        this.activo = true;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public List<Resultado> getResultados() {
        return resultados;
    }

    public void setResultados(List<Resultado> resultados) {
        this.resultados = resultados;
    }

    // Método utilitario para obtener nombre completo
    public String getNombreCompleto() {
        StringBuilder nombre = new StringBuilder();
        if (primerNombre != null) nombre.append(primerNombre).append(" ");
        if (segundoNombre != null) nombre.append(segundoNombre).append(" ");
        if (primerApellido != null) nombre.append(primerApellido).append(" ");
        if (segundoApellido != null) nombre.append(segundoApellido);
        return nombre.toString().trim();
    }

    // Método para verificar si tiene resultados
    public boolean tieneResultados() {
        return resultados != null && !resultados.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return Objects.equals(numeroDocumento, alumno.numeroDocumento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroDocumento);
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", nombreCompleto='" + getNombreCompleto() + '\'' +
                ", programa='" + programa + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                '}';
    }
}