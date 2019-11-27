package LogicaDeNegocios.DTOs;

import java.util.List;

public class DatosTablaDTO {
    private Long idLicencia;
    private String dni;
    private String nombre;
    private String apellido;
    private String estadoLicencia;
    private List<String> clasesLicencia;
    private String fechaAlta;

    public DatosTablaDTO() {
    }

    public DatosTablaDTO(Long idLicencia, String dni, String nombre, String apellido, String estadoLicencia, List<String> clasesLicencia, String fechaAlta) {
        this.idLicencia = idLicencia;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estadoLicencia = estadoLicencia;
        this.clasesLicencia = clasesLicencia;
        this.fechaAlta = fechaAlta;
    }

    public Long getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(Long idLicencia) {
        this.idLicencia = idLicencia;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEstadoLicencia() {
        return estadoLicencia;
    }

    public void setEstadoLicencia(String estadoLicencia) {
        this.estadoLicencia = estadoLicencia;
    }

    public List<String> getClasesLicencia() {
        return clasesLicencia;
    }

    public void setClasesLicencia(List<String> clasesLicencia) {
        this.clasesLicencia = clasesLicencia;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Override
    public String toString() {
        return "DatosTablaDTO{" +
                "idLicencia=" + idLicencia +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", estadoLicencia='" + estadoLicencia + '\'' +
                ", clasesLicencia=" + clasesLicencia +
                ", fechaAlta='" + fechaAlta + '\'' +
                '}';
    }
}
