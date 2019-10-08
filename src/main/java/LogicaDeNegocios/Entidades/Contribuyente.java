package LogicaDeNegocios.Entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Contribuyente")
public class Contribuyente {

    private Long nroDocumento;

    @Column(name = "NOMBRE")
    private String nombreContribuyente;

    @Column(name = "APELLIDO")
    private String apellidoContribuyente;

    @Column(name = "FECHA_NACIMIENTO")
    private LocalDateTime fechaNacimientoContribuyente;

    @Column(name = "DOMICILIO")
    private String domicilioContribuyente;

    public Contribuyente() {
    }

    public Contribuyente(Long nroDocumento, String nombreContribuyente, String apellidoContribuyente, LocalDateTime fechaNacimientoContribuyente, String domicilioContribuyente) {
        this.nroDocumento = nroDocumento;
        this.nombreContribuyente = nombreContribuyente;
        this.apellidoContribuyente = apellidoContribuyente;
        this.fechaNacimientoContribuyente = fechaNacimientoContribuyente;
        this.domicilioContribuyente = domicilioContribuyente;
    }

    @Id
    @Column(name = "NRO_DOCUMENTO")
    public Long getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(Long nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNombreContribuyente() {
        return nombreContribuyente;
    }

    public void setNombreContribuyente(String nombreContribuyente) {
        this.nombreContribuyente = nombreContribuyente;
    }

    public String getApellidoContribuyente() {
        return apellidoContribuyente;
    }

    public void setApellidoContribuyente(String apellidoContribuyente) {
        this.apellidoContribuyente = apellidoContribuyente;
    }

    public LocalDateTime getFechaNacimientoContribuyente() {
        return fechaNacimientoContribuyente;
    }

    public void setFechaNacimientoContribuyente(LocalDateTime fechaNacimientoContribuyente) {
        this.fechaNacimientoContribuyente = fechaNacimientoContribuyente;
    }

    public String getDomicilioContribuyente() {
        return domicilioContribuyente;
    }

    public void setDomicilioContribuyente(String domicilioContribuyente) {
        this.domicilioContribuyente = domicilioContribuyente;
    }
}
