package gestores;

import LogicaDeNegocios.DTOs.ContribuyenteDTO;
import LogicaDeNegocios.DTOs.TitularDTO;
import LogicaDeNegocios.Entidades.Contribuyente;
import LogicaDeNegocios.Entidades.Licencia;
import LogicaDeNegocios.Entidades.Titular;

import java.util.ArrayList;

public abstract class GestorTitular {

    public static TitularDTO titularAux = null;

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
            if (buscarTitular(titularDTO.getDni()) == null) {
                Titular titular = new Titular(contribuyente, new ArrayList<Licencia>(), titularDTO.getObservaciones(), titularDTO.getDonante(), titularDTO.getTipoSangre());
                if (GestorBD.guardarTitular(titular)) {
                    //Exitos prro
                    return 0;
                }
                else{
                    //Error en base de datos.
                    return -2;
                }
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

    public static void buscarTitularDTO(Long dni) {
        Titular titular = buscarTitular(dni);
        if (titular != null) {
            titularAux = new TitularDTO();
            ContribuyenteDTO contribuyenteDTO = new ContribuyenteDTO();
            contribuyenteDTO.setNombre(titular.getContribuyente().getNombreContribuyente());
            contribuyenteDTO.setNroDocumento(titular.getContribuyente().getNroDocumento());
            contribuyenteDTO.setDomicilio(titular.getContribuyente().getDomicilioContribuyente());
            contribuyenteDTO.setFechaDeNacimiento(titular.getContribuyente().getFechaNacimientoContribuyente());
            contribuyenteDTO.setApellido(titular.getContribuyente().getApellidoContribuyente());
            titularAux.setContribuyente(contribuyenteDTO);
            titularAux.setDni(contribuyenteDTO.getNroDocumento());
            titularAux.setDonante(titular.getDonante());
            titularAux.setObservaciones(titular.getObservaciones());
            titularAux.setTipoSangre(titular.getTipoSangre());
            titularAux.setTieneLicencias(titular.getLicencias().isEmpty());
        } else {
            titularAux = null;
        }
        return;
    }//cierra buscarTitular
}
