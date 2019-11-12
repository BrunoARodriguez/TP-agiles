package LogicaDeNegocios.Entidades;

import java.time.LocalDateTime;

public class Comprobante {

    private LocalDateTime fechaCreacion;
    private Float costoTramite;
    private Licencia licenciaAsociada;
    private String observaciones;

    public Comprobante() {
    }

    public Comprobante(LocalDateTime fechaCreacion, Float costoTramite, Licencia licenciaAsociada, String observaciones) {
        this.fechaCreacion = fechaCreacion;
        this.costoTramite = costoTramite;
        this.licenciaAsociada = licenciaAsociada;
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Comprobante{" +
                "fechaCreacion=" + fechaCreacion +
                ", costoTramite=" + costoTramite +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Float getCostoTramite() {
        return costoTramite;
    }

    public void setCostoTramite(Float costoTramite) {
        this.costoTramite = costoTramite;
    }

    public Licencia getLicenciaAsociada() {
        return licenciaAsociada;
    }

    public void setLicenciaAsociada(Licencia licenciaAsociada) {
        this.licenciaAsociada = licenciaAsociada;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
