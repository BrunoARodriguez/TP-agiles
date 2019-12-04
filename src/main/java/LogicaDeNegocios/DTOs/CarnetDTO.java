package LogicaDeNegocios.DTOs;

import LogicaDeNegocios.Entidades.Comprobante;
import LogicaDeNegocios.Enumerations.ClaseLicencia;
import LogicaDeNegocios.Enumerations.TipoSangre;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CarnetDTO {

    private Long idLicencia;
    private String nombre;
    private String apellido;
    private String domicilio;
    private LocalDateTime fechaDeNacimiento;
    private LocalDateTime fechaAltaLicencia;
    private LocalDateTime fechaVencimientoLicencia;
    private List<String> clasesLicencia;
    private Boolean esDonante;
    private TipoSangre tipoSangre;
    private String observacionesLicencia;
    private Comprobante comprobante;

    public Long getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(Long idLicencia) {
        this.idLicencia = idLicencia;
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

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public LocalDateTime getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDateTime fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public LocalDateTime getFechaAltaLicencia() {
        return fechaAltaLicencia;
    }

    public void setFechaAltaLicencia(LocalDateTime fechaAltaLicencia) {
        this.fechaAltaLicencia = fechaAltaLicencia;
    }

    public LocalDateTime getFechaVencimientoLicencia() {
        return fechaVencimientoLicencia;
    }

    public void setFechaVencimientoLicencia(LocalDateTime fechaVencimientoLicencia) {
        this.fechaVencimientoLicencia = fechaVencimientoLicencia;
    }

    public List<String> getClasesLicencia() {
        return clasesLicencia;
    }

    public void setClasesLicencia(List<String> clasesLicencia) {
        this.clasesLicencia = clasesLicencia;
    }

    public Boolean getEsDonante() {
        return esDonante;
    }

    public void setEsDonante(Boolean esDonante) {
        this.esDonante = esDonante;
    }

    public TipoSangre getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(TipoSangre tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getObservacionesLicencia() {
        return observacionesLicencia;
    }

    public void setObservacionesLicencia(String observacionesLicencia) {
        this.observacionesLicencia = observacionesLicencia;
    }

    @Override
    public String toString() {
        return "CarnetDTO{" +
                "idLicencia=" + idLicencia +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", fechaDeNacimiento=" + fechaDeNacimiento +
                ", fechaAltaLicencia=" + fechaAltaLicencia +
                ", fechaVencimientoLicencia=" + fechaVencimientoLicencia +
                ", clasesLicencia=" + clasesLicencia +
                ", esDonante=" + esDonante +
                ", tipoSangre=" + tipoSangre +
                ", observacionesLicencia='" + observacionesLicencia + '\'' +
                '}';
    }

    public Comprobante getComprobante() {
        return comprobante;
    }

    public void setComprobante(Comprobante comprobante) {
        this.comprobante = comprobante;
    }
}
