package LogicaDeNegocios.DTOs;

import LogicaDeNegocios.Enumerations.ClaseLicencia;

import java.time.LocalDateTime;
import java.util.List;

public class CriteriosDTO {
    private Long dniTitular;
    private String nombreTitular;
    private String apellidoTitular;
    private List<ClaseLicencia> claseLicencias;
    private LocalDateTime fechaAltaDesde;
    private LocalDateTime fechaAltaHasta;
    private LocalDateTime fechaVencimientoDesde;
    private LocalDateTime fechaVencimientoHasta;

    public CriteriosDTO() {
    }

    public CriteriosDTO(Long dniTitular, String nombreTitular, String apellidoTitular, List<ClaseLicencia> claseLicencias, LocalDateTime fechaAltaDesde, LocalDateTime fechaAltaHasta, LocalDateTime fechaVencimientoDesde, LocalDateTime fechaVencimientoHasta) {
        this.dniTitular = dniTitular;
        this.nombreTitular = nombreTitular;
        this.apellidoTitular = apellidoTitular;
        this.claseLicencias = claseLicencias;
        this.fechaAltaDesde = fechaAltaDesde;
        this.fechaAltaHasta = fechaAltaHasta;
        this.fechaVencimientoDesde = fechaVencimientoDesde;
        this.fechaVencimientoHasta = fechaVencimientoHasta;
    }

    public Long getDniTitular() {
        return dniTitular;
    }

    public void setDniTitular(Long dniTitular) {
        this.dniTitular = dniTitular;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getApellidoTitular() {
        return apellidoTitular;
    }

    public void setApellidoTitular(String apellidoTitular) {
        this.apellidoTitular = apellidoTitular;
    }

    public List<ClaseLicencia> getClaseLicencias() {
        return claseLicencias;
    }

    public void setClaseLicencias(List<ClaseLicencia> claseLicencias) {
        this.claseLicencias = claseLicencias;
    }

    public LocalDateTime getFechaAltaDesde() {
        return fechaAltaDesde;
    }

    public void setFechaAltaDesde(LocalDateTime fechaAltaDesde) {
        this.fechaAltaDesde = fechaAltaDesde;
    }

    public LocalDateTime getFechaAltaHasta() {
        return fechaAltaHasta;
    }

    public void setFechaAltaHasta(LocalDateTime fechaAltaHasta) {
        this.fechaAltaHasta = fechaAltaHasta;
    }

    public LocalDateTime getFechaVencimientoDesde() {
        return fechaVencimientoDesde;
    }

    public void setFechaVencimientoDesde(LocalDateTime fechaVencimientoDesde) {
        this.fechaVencimientoDesde = fechaVencimientoDesde;
    }

    public LocalDateTime getFechaVencimientoHasta() {
        return fechaVencimientoHasta;
    }

    public void setFechaVencimientoHasta(LocalDateTime fechaVencimientoHasta) {
        this.fechaVencimientoHasta = fechaVencimientoHasta;
    }
}