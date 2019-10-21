package gestores;

import LogicaDeNegocios.Entidades.Contribuyente;
import LogicaDeNegocios.Entidades.Licencia;
import LogicaDeNegocios.Entidades.Titular;

import java.util.ArrayList;
import java.util.Timer;

public abstract class GestorTitular {

//TODO ver que hacemos con el retorno de este metodo: Boolean vs Titular
    public static Boolean validarTitularExistente(Long dni) {
        Titular titular = GestorBD.buscarTitular(dni);

        if(titular!=null){
            return true;
        }
        else{
            return false;
        }

    }

    public  static  int  crearTitular(TitularDAO titularDAO) {
        Contribuyente contribuyente=GestorBD.buscarContribuyente(titularDAO.getDni());
if (contribuyente != null){
    Titular titular=new Titular(contribuyente,new ArrayList<Licencia>(),titularDAO.getObservaciones(),titularDAO.getDonante(),titularDAO.getTipoSangre());
if (GestorBD.guardarTitular(titular)){
    //Exitos prro
return  0;
}
else {
    //Error al guardar el titular
    return -2;
}
}
else {
    //Contribuyente no encontrado en base de datos
    return  -1;
}
    }

    public static Titular buscarTitular(Long dni){
        return GestorBD.buscarTitular(dni);
    }
}
