package gestores;

import LogicaDeNegocios.DTOs.LicenciaDTO;
import LogicaDeNegocios.Entidades.CambioEstadoLicencia;
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

            Titular titular=null;
            if (titular != null) {
                if (!licenciaDTO.getClaseLicencias().isEmpty()) {
                    Licencia licencia = new Licencia(titular, licenciaDTO.getFechaAltaLicencia(), GestorLicencia.calcularVigencia(titular.getContribuyente().getFechaNacimientoContribuyente(), titular.getLicencias().isEmpty()), licenciaDTO.getClaseLicencias(), licenciaDTO.getObservacionesLicencia(), new ArrayList<CambioEstadoLicencia>());
                    CambioEstadoLicencia cambioEstadoLicencia = new CambioEstadoLicencia(null, licencia.getIdLicencia(), null, EstadoLicencia.VIGENTE, LocalDateTime.now(), GestorUsuario.getUsuario(), licencia.getObservacionesLicencia(), licencia);
                    licencia.getCambioEstadoLicencias().add(cambioEstadoLicencia);
                    titular.getLicencias().add(licencia);
                    if (GestorBD.guardarLicencia(licencia) && GestorBD.guardarTitular(titular)) {
                        //Exito prro
                        Float costoLicencia = calcularCostoLicencia(licencia.getFechaAltaLicencia(), licencia.getFechaVencimientoLicencia(), licencia.getClaseLicencias());
                        //TODO Agregar costoLicencia a clase Comprobante
                        return 0;
                    } else {
                        //Error al guardar
                        return -2;
                    }
                } else {
                    //No se seleccionaron clases de licencia
                    return -3;
                }
            } else {
                //Error en la base de datos
                return -4;
            }

    }//cierra crearLicencia

    public static LocalDateTime calcularVigencia(LocalDateTime fechaNacimiento, Boolean tieneLicencias, LocalDateTime fechaActual) {

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
        LocalDateTime localDateTime = LocalDateTime.of(fechaActual.getYear() + sumadorAñoVigencia, fechaNacimiento.getMonth(), fechaNacimiento.getDayOfMonth() + 1, 0, 0, 0);

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
        for (ClaseLicencia cl : clases) {
            CostoLicencia ct = new CostoLicencia(cl, vigencia);
            costo += GestorBD.buscarCosto(ct).getCostoLicencia();
        }
        costo += CostoLicencia.COSTO_ADMINISTRATIVO;
        return costo;
    }//cierra calcularCostoLicencia
}

