package gestores;

import LogicaDeNegocios.Entidades.Titular;

import java.util.Timer;

public abstract class GestorTitular {

    private static Titular titular;


    public static Boolean validarTitularExistente(TitularDAO titularDAO) {
    titular = GestorBD.buscarTitular(titularDAO.getDni());
    Titular dao = new Titular(titularDAO);
    if (titular.equals(dao)) return true;
    else return false;
    }


}
