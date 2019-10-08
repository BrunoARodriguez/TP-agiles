package LogicaDeNegocios.Entidades;

import LogicaDeNegocios.Enumerations.EstadoLicencia;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CambioEstadoLicencia")
public class CambioEstadoLicencia {

    private Long idCambioEstado;
    private Long codigoAnterior, codigoNuevo;
    private EstadoLicencia estadoAnterior, estadoNuevo;
    private LocalDateTime fechaHoraCambio;
    private Usuario responsableCambio;
    private String observaciones;
    private Licencia licencia;

    public CambioEstadoLicencia() {
    }

    public CambioEstadoLicencia(Long idCambioEstado, Long codigoAnterior, Long codigoNuevo, EstadoLicencia estadoAnterior, EstadoLicencia estadoNuevo, LocalDateTime fechaHoraCambio, Usuario responsableCambio, String observaciones, Licencia licencia) {
        this.idCambioEstado = idCambioEstado;
        this.codigoAnterior = codigoAnterior;
        this.codigoNuevo = codigoNuevo;
        this.estadoAnterior = estadoAnterior;
        this.estadoNuevo = estadoNuevo;
        this.fechaHoraCambio = fechaHoraCambio;
        this.responsableCambio = responsableCambio;
        this.observaciones = observaciones;
        this.licencia = licencia;
    }

    @Id
    @Column(name = "ID_CAMBIO_ESTADO")
    public Long getIdCambioEstado() {
        return idCambioEstado;
    }

    public void setIdCambioEstado(Long idCambioEstado) {
        this.idCambioEstado = idCambioEstado;
    }

    @Column(name = "CODIGO_COPIA_ANTERIOR")
    public Long getCodigoAnterior() {
        return codigoAnterior;
    }

    public void setCodigoAnterior(Long codigoAnterior) {
        this.codigoAnterior = codigoAnterior;
    }

    @Column(name = "CODIGO_LICENCIA_NUEVO")
    public Long getCodigoNuevo() {
        return codigoNuevo;
    }

    public void setCodigoNuevo(Long codigoNuevo) {
        this.codigoNuevo = codigoNuevo;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO_ANTERIOR_LICENCIA")
    public EstadoLicencia getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(EstadoLicencia estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO_NUEVO_LICENCIA")
    public EstadoLicencia getEstadoNuevo() {
        return estadoNuevo;
    }

    public void setEstadoNuevo(EstadoLicencia estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    @Column(name = "FECHA_HORA_CAMBIO_DE_ESTADO")
    public LocalDateTime getFechaHoraCambio() {
        return fechaHoraCambio;
    }

    public void setFechaHoraCambio(LocalDateTime fechaHoraCambio) {
        this.fechaHoraCambio = fechaHoraCambio;
    }

    @Column(name = "OBSERVACIONES")
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @ManyToOne
    @JoinColumn(name = "LICENCIA", nullable = false, foreignKey = @ForeignKey(name = "FK_CambioEstado_Licencia"))
    public Licencia getLicencia() {
        return licencia;
    }

    public void setLicencia(Licencia licencia) {
        this.licencia = licencia;
    }

    @ManyToOne
    @JoinColumn(name = "RESPONSABLE_CAMBIO", nullable = false, foreignKey = @ForeignKey(name = "FK_cambioEstado_Usuario"))
    public Usuario getResponsableCambio() {
        return responsableCambio;
    }

    public void setResponsableCambio(Usuario responsableCambio) {
        this.responsableCambio = responsableCambio;
    }
}

