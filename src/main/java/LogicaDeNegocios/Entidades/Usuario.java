package LogicaDeNegocios.Entidades;

import LogicaDeNegocios.Enumerations.Roles;

import javax.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {

    private Roles rol;
    private String nombreUsuario;
    private Long claveUsuario;

    public Usuario() {
    }

    public Usuario(Roles rol, String nombreUsuario, Long claveUsuario) {
        this.rol = rol;
        this.nombreUsuario = nombreUsuario;
        this.claveUsuario = claveUsuario;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "ROL_USUARIO")
    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    @Id
    @Column(name = "NOMBRE_USUARIO")
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Column(name = "CLAVE_USUARIO")
    public Long getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(Long claveUsuario) {
        this.claveUsuario = claveUsuario;
    }
}
