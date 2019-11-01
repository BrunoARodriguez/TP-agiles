package LogicaDeNegocios.DTOs;

import java.time.LocalDateTime;

public class ContribuyenteDTO {

    private Long nroDocumento;
    private String nombre;
    private String apellido;
    private LocalDateTime fechaDeNacimiento;
    private String domicilio;

    public ContribuyenteDTO() {
    }

    public ContribuyenteDTO(String nombre, String apellido, LocalDateTime fechaDeNacimiento, String domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.domicilio = domicilio;
    }

    public Long getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(Long nroDocumento) {
        this.nroDocumento = nroDocumento;
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

    public LocalDateTime getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDateTime fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
}


