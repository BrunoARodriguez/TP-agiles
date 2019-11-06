package gestores;

import LogicaDeNegocios.DTOs.LicenciaDTO;
import LogicaDeNegocios.Entidades.CambioEstadoLicencia;
import LogicaDeNegocios.Entidades.Comprobante;
import LogicaDeNegocios.Entidades.Licencia;
import LogicaDeNegocios.Entidades.Resources.CostoLicencia;
import LogicaDeNegocios.Entidades.Titular;
import LogicaDeNegocios.Enumerations.ClaseLicencia;
import LogicaDeNegocios.Enumerations.EstadoLicencia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class GestorLicencia {

    public static int crearLicencia(LicenciaDTO licenciaDTO) {

        Titular titular = GestorTitular.buscarTitular(GestorTitular.titularAux.getDni());
        Licencia licencia = new Licencia(titular, licenciaDTO.getFechaAltaLicencia(), licenciaDTO.getFechaVencimientoLicencia(), licenciaDTO.getClaseLicencias(), licenciaDTO.getObservacionesLicencia(), new ArrayList<CambioEstadoLicencia>());
        CambioEstadoLicencia cambioEstadoLicencia = new CambioEstadoLicencia(null, licencia.getIdLicencia(), null, EstadoLicencia.VIGENTE, LocalDateTime.now(), GestorUsuario.getUsuario(), licencia.getObservacionesLicencia(), licencia);
        licencia.getCambioEstadoLicencias().add(cambioEstadoLicencia);
        titular.getLicencias().add(licencia);
        if (GestorBD.guardarLicencia(licencia)) {
            Float costoLicencia = calcularCostoLicencia(licencia.getFechaAltaLicencia(), licencia.getFechaVencimientoLicencia(), licencia.getClaseLicencias());
            System.out.println("Costo de licencia: "+costoLicencia.toString());
            //TODO Ver como imprimir el comprobante por pantalla y por pdf.

            String observaciones = "Se ha emitido la licencia a nombre de : " + licencia.getTitularLicencia().getContribuyente().getNombreContribuyente() + " "+ licencia.getTitularLicencia().getContribuyente().getApellidoContribuyente() + " de la clases : " + licencia.getClaseLicencias().toString();
            Comprobante comprobante = new Comprobante(licenciaDTO.getFechaAltaLicencia(), costoLicencia, licencia, observaciones);
            System.out.println(comprobante.toString());
            //Exito perro



            return 0;
        } else {
            if(!GestorTitular.titularAux.getTieneLicencias()){
                int i = 0;
                while(!GestorBD.borrarTitular(GestorTitular.titularAux.getDni()) && i<20){
                    System.out.println("Error borrando: "+i);
                    i++;
                }
                if(i<20){
                    System.out.println("Titular borrado.");
                }
            }
            //Error al guardar
            return -2;
        }

    }//cierra crearLicencia

    public static LocalDateTime calcularVigencia(LocalDateTime fechaNacimiento, Boolean tieneLicencias) {

        LocalDateTime fechaActual = LocalDateTime.now();

        Integer edad = fechaActual.getYear() - fechaNacimiento.getYear();

        if (fechaActual.getMonthValue() >= fechaNacimiento.getMonthValue() && fechaActual.getDayOfMonth() >= fechaNacimiento.getDayOfMonth()) {
        } else {
            edad -= 1;
        }

        //Se encarga de ver que fecha esta mas cerca para ver si sumarle o restarle uno al año o dejarlo igual
        Integer sumadorAñoVigencia = calcularAñoFechaVigencia(fechaActual.getMonthValue(), fechaNacimiento.getMonthValue());

        if (edad <= 21) {
            if (tieneLicencias) {
                sumadorAñoVigencia += 3;
            } else {
                sumadorAñoVigencia += 1;
            }
        } else if (edad <= 46) {
            sumadorAñoVigencia += 5;
        } else if (edad <= 60) {
            sumadorAñoVigencia += 4;
        } else if (edad <= 70) {
            sumadorAñoVigencia += 3;
        } else {
            sumadorAñoVigencia += 1;
        }
        LocalDateTime localDateTime = LocalDateTime.of(fechaActual.getYear() + sumadorAñoVigencia, fechaNacimiento.getMonth(), fechaNacimiento.getDayOfMonth(), 0, 0, 0);

        return localDateTime;

    }//cierra calcularVigencia

    private static Integer calcularAñoFechaVigencia(Integer mesActual, Integer mesNacimiento) {

        if (mesActual == mesNacimiento) {
            return 0;
        } else if (Math.abs(mesActual - mesNacimiento) > 6) {
            if (mesActual - mesNacimiento > 0) return 1;
            else return -1;
        } else return 0;
    }//cierra calcularAñoFechaVigencia

    //para el costo de la licencia
    public static Float calcularCostoLicencia(LocalDateTime fechaAlta, LocalDateTime fechaVencimiento, List<ClaseLicencia> clases) {
        Float costo = 0F;
        Integer vigencia = fechaVencimiento.getYear() - fechaAlta.getYear();
        vigencia -= calcularAñoFechaVigencia(fechaAlta.getMonthValue(), fechaVencimiento.getMonthValue());
        for (ClaseLicencia cl : clases) {
            CostoLicencia ct = new CostoLicencia(cl, vigencia);
            costo += GestorBD.buscarCosto(ct).getCostoLicencia();
        }
        costo += CostoLicencia.COSTO_ADMINISTRATIVO;
        return costo;
    }//cierra calcularCostoLicencia
}

