package gestores;

import LogicaDeNegocios.Entidades.Usuario;

public abstract class GestorUsuario {
    private static Usuario usuario;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        GestorUsuario.usuario = usuario;
    }
}
