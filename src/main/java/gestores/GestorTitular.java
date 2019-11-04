package gestores;

import LogicaDeNegocios.DTOs.ContribuyenteDTO;
import LogicaDeNegocios.DTOs.TitularDTO;
import LogicaDeNegocios.Entidades.Contribuyente;
import LogicaDeNegocios.Entidades.Licencia;
import LogicaDeNegocios.Entidades.Titular;

import java.util.ArrayList;

public abstract class GestorTitular {

    public static Titular titularAux = null;

    //TODO ver que hacemos con el retorno de este metodo: Boolean vs Titular
    public static Boolean validarTitularExistente(Long dni) {
        Titular titular = GestorBD.buscarTitular(dni);

        if (titular != null) {
            return true;
        } else {
            return false;
        }
    }//cierra validarTitularExistente

    public static ContribuyenteDTO buscarContribuyente(ContribuyenteDTO contribuyenteDTO) {
        Contribuyente contribuyente = GestorBD.buscarContribuyente(contribuyenteDTO.getNroDocumento());
        if (contribuyente == null) {
            return null;
        } else {
            contribuyenteDTO.setApellido(contribuyente.getApellidoContribuyente());
            contribuyenteDTO.setNombre(contribuyente.getNombreContribuyente());
            contribuyenteDTO.setFechaDeNacimiento(contribuyente.getFechaNacimientoContribuyente());
            contribuyenteDTO.setDomicilio(contribuyente.getDomicilioContribuyente());
            return contribuyenteDTO;
        }
    } //cierra buscarContribuyente

    public static int crearTitular(TitularDTO titularDTO) {
        Contribuyente contribuyente = GestorBD.buscarContribuyente(titularDTO.getDni());
        if (contribuyente != null) {
            if(buscarTitular(titularDTO.getDni())==null){
                Titular titular = new Titular(contribuyente, new ArrayList<Licencia>(), titularDTO.getObservaciones(), titularDTO.getDonante(), titularDTO.getTipoSangre());
                GestorTitular.titularAux=titular;
                //Exitos prro
                    return 0;

        } else {
              //Titular ya existe con este documento.
              return -3;
            }
        } else {
            //Contribuyente no encontrado en base de datos
            return -1;
        }
    }//cierra crearTitular

    public static Titular buscarTitular(Long dni) {
        return GestorBD.buscarTitular(dni);
    }//cierra buscarTitular
}
