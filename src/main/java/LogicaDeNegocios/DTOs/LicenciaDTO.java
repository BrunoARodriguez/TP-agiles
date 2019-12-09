package LogicaDeNegocios.DTOs;

import LogicaDeNegocios.Entidades.CambioEstadoLicencia;
import LogicaDeNegocios.Entidades.Comprobante;
import LogicaDeNegocios.Entidades.Titular;
import LogicaDeNegocios.Enumerations.ClaseLicencia;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class LicenciaDTO {
    private Long idLicencia;
    private Long dni;
    private LocalDateTime fechaAltaLicencia;
    private LocalDateTime fechaVencimientoLicencia;
    private ArrayList<ClaseLicencia> claseLicencias;
    private String observacionesLicencia;
    private Boolean esDonante;

    private Comprobante comprobante;

    public LicenciaDTO() {
        claseLicencias = new ArrayList<ClaseLicencia>();
    }

    public LicenciaDTO(Long idLicencia, Long dni, LocalDateTime fechaAltaLicencia, LocalDateTime fechaVencimientoLicencia, ArrayList<ClaseLicencia> claseLicencias, String observacionesLicencia) {
        this.idLicencia = idLicencia;
        this.dni = dni;
        this.fechaAltaLicencia = fechaAltaLicencia;
        this.fechaVencimientoLicencia = fechaVencimientoLicencia;
        this.claseLicencias = claseLicencias;
        this.observacionesLicencia = observacionesLicencia;
    }

    public Long getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(Long idLicencia) {
        this.idLicencia = idLicencia;
    }

    public Long getDNI() {
        return dni;
    }

    public void setDNI(Long dni) {
        this.dni = dni;
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

    public ArrayList<ClaseLicencia> getClaseLicencias() {
        return claseLicencias;
    }

    public void setClaseLicencias(ArrayList<ClaseLicencia> claseLicencias) {
        this.claseLicencias = claseLicencias;
    }

    public String getObservacionesLicencia() {
        return observacionesLicencia;
    }

    public void setObservacionesLicencia(String observacionesLicencia) {
        this.observacionesLicencia = observacionesLicencia;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public Boolean getEsDonante() {
        return esDonante;
    }

    public void setEsDonante(Boolean esDonante) {
        this.esDonante = esDonante;
    }

    public Comprobante getComprobante() {
        return comprobante;
    }

    public void setComprobante(Comprobante comprobante) {
        this.comprobante = comprobante;
    }

    @Override
    public String toString() {
        return "LicenciaDTO{" +
                "idLicencia=" + idLicencia +
                ", dni=" + dni +
                ", fechaAltaLicencia=" + fechaAltaLicencia +
                ", fechaVencimientoLicencia=" + fechaVencimientoLicencia +
                ", esDonante=" + esDonante +
                '}';
    }
}
