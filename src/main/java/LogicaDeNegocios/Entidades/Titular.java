package LogicaDeNegocios.Entidades;

import LogicaDeNegocios.DTOs.TitularDTO;
import LogicaDeNegocios.Enumerations.TipoSangre;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Titular")
public class Titular implements Serializable{

    private Contribuyente contribuyente;
    private ArrayList<Licencia> licencias;
    private String observaciones;
    private Boolean donante;
    private TipoSangre tipoSangre;

    public Titular() {
    }

    public Titular(Contribuyente contribuyente, ArrayList<Licencia> licencias, String observaciones, Boolean donante, TipoSangre tipoSangre) {
        this.contribuyente = contribuyente;
        this.licencias = licencias;
        this.observaciones = observaciones;
        this.donante = donante;
        this.tipoSangre = tipoSangre;
    }

    @Id
    @OneToOne
    @JoinColumn(name = "Contribuyente", nullable = false, foreignKey = @ForeignKey(name = "FK_titular_contribuyente"))
    public Contribuyente getContribuyente() {
        return contribuyente;
    }

    public void setContribuyente(Contribuyente contribuyente) {
        this.contribuyente = contribuyente;
    }

    @ElementCollection
    @CollectionTable(name = "TITULAR_LICENCIAS")
    public List<Licencia> getLicencias() {
        return licencias;
    }

    public void setLicencias(ArrayList<Licencia> licencias) {
        this.licencias = licencias;
    }

    @Column(name = "OBSERVACIONES")
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Column(name = "ES_DONANTE")
    public Boolean getDonante() {
        return donante;
    }

    public void setDonante(Boolean donante) {
        this.donante = donante;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_SANGRE")
    public TipoSangre getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(TipoSangre tipoSangre) {
        this.tipoSangre = tipoSangre;
    }
}
