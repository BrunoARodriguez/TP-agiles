package LogicaDeNegocios.Entidades;

import LogicaDeNegocios.Enumerations.ClaseLicencia;

import java.time.LocalDateTime;
import java.util.List;

public class Comprobante {

    private LocalDateTime fechaCreacion;
    private Float costoTramite;
   // private Licencia licenciaAsociada;
    private String observaciones;

   // String observaciones = "Se ha emitido la licencia a nombre de : \n" + licencia.getTitularLicencia().getContribuyente().getNombreContribuyente() + " " + licencia.getTitularLicencia().getContribuyente().getApellidoContribuyente() + "\nDe la(s) clase(s) : " + licencia.getClaseLicencias().toString();


    public Comprobante() {
    }

    public Comprobante(LocalDateTime fechaCreacion, Float costoTramite, String observaciones) {
        this.fechaCreacion = fechaCreacion;
        this.costoTramite = costoTramite;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
