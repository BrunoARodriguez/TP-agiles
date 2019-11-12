package test;

import LogicaDeNegocios.Entidades.Comprobante;
import LogicaDeNegocios.Entidades.Licencia;
import gestores.GestorImpresion;
import org.junit.Test;

import java.time.LocalDateTime;

public class Test_Comprobante_Emitir {

    //TODO ver si hacemos mas pruebas respecto del comprobante
    @Test
    public void emitirCoprobante(){
        Comprobante comprobante = new Comprobante();
        comprobante.setCostoTramite(150.0F);
        comprobante.setFechaCreacion(LocalDateTime.now());
        comprobante.setObservaciones("Este es un nuevo comprobante jaja salu2");

        assert(GestorImpresion.imprimirComprobante(comprobante)==true);

    }
}
