package LogicaDeNegocios.Entidades;

import LogicaDeNegocios.Enumerations.ClaseLicencia;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Licencia")
public class Licencia {

    private Long idLicencia;
    private Titular titularLicencia;
    private LocalDateTime fechaAltaLicencia;
    private LocalDateTime fechaVencimientoLicencia;
    private List<ClaseLicencia> claseLicencias;
    private String observacionesLicencia;
    private List<CambioEstadoLicencia> cambioEstadoLicencias;

    public Licencia() {
    }

    public Licencia(Titular titularLicencia, LocalDateTime fechaAltaLicencia, LocalDateTime fechaVencimientoLicencia, ArrayList<ClaseLicencia> claseLicencias, String observacionesLicencia, ArrayList<CambioEstadoLicencia> cambioEstadoLicencias) {
        this.titularLicencia = titularLicencia;
        this.fechaAltaLicencia = fechaAltaLicencia;
        this.fechaVencimientoLicencia = fechaVencimientoLicencia;
        this.claseLicencias = claseLicencias;
        this.observacionesLicencia = observacionesLicencia;
        this.cambioEstadoLicencias = cambioEstadoLicencias;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "licencia_seq")
    @SequenceGenerator(name = "licencia_seq", sequenceName = "licencia_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_LICENCIA")
    public Long getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(Long idLicencia) {
        this.idLicencia = idLicencia;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "DNI_TITULAR", nullable = false, foreignKey = @ForeignKey(name = "FK_licencia_titular"))
    public Titular getTitularLicencia() {
        return titularLicencia;
    }

    public void setTitularLicencia(Titular titularLicencia) {
        this.titularLicencia = titularLicencia;
    }

    @Column(name = "FECHA_ALTA_LICENCIA")
    public LocalDateTime getFechaAltaLicencia() {
        return fechaAltaLicencia;
    }

    public void setFechaAltaLicencia(LocalDateTime fechaAltaLicencia) {
        this.fechaAltaLicencia = fechaAltaLicencia;
    }

    @Column(name = "FECHA_VENCIMIENTO_LICENCIA")
    public LocalDateTime getFechaVencimientoLicencia() {
        return fechaVencimientoLicencia;
    }

    public void setFechaVencimientoLicencia(LocalDateTime fechaVencimientoLicencia) {
        this.fechaVencimientoLicencia = fechaVencimientoLicencia;
    }

    @ElementCollection
    @CollectionTable(name = "CLASE_LICENCIAS")
    public List<ClaseLicencia> getClaseLicencias() {
        return claseLicencias;
    }

    public void setClaseLicencias(List<ClaseLicencia> claseLicencias) {
        this.claseLicencias = claseLicencias;
    }

    @Column(name = "OBSERVACIONES_LICENCIA")
    public String getObservacionesLicencia() {
        return observacionesLicencia;
    }

    public void setObservacionesLicencia(String observacionesLicencia) {
        this.observacionesLicencia = observacionesLicencia;
    }

    @OneToMany(mappedBy = "licencia", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("idCambioEstado ASC")
    public List<CambioEstadoLicencia> getCambioEstadoLicencias() {
        return cambioEstadoLicencias;
    }

    public void setCambioEstadoLicencias(List<CambioEstadoLicencia> cambioEstadoLicencias) {
        this.cambioEstadoLicencias = cambioEstadoLicencias;
    }
}
