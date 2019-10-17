package gestores;

import LogicaDeNegocios.Entidades.CambioEstadoLicencia;
import LogicaDeNegocios.Entidades.Licencia;
import LogicaDeNegocios.Entidades.Titular;
import LogicaDeNegocios.Enumerations.ClaseLicencia;
import LogicaDeNegocios.Enumerations.EstadoLicencia;
import org.hibernate.CacheMode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public abstract class GestorLicencia {

    public static int crearLicencia(LicenciaDTO licenciaDTO){
        if(GestorTitular.validarTitularExistente(licenciaDTO.getDNI())){
            Titular titular = GestorTitular.buscarTitular(licenciaDTO.getDNI());//TODO ver si usamos Gestor DB directamente o pasamos por gestor titular
            if(titular!=null){
                if(!licenciaDTO.getClaseLicencia().isEmpty()){
                    Licencia licencia = new Licencia(titular, LocalDateTime.now(),GestorLicencia.calcularVigencia(titular.getContribuyente().getFechaNacimientoContribuyente(), licenciaDTO.getClaseLicencia(),titular.getLicencias().isEmpty()),licenciaDTO.getClaseLicencia(), licenciaDTO.getObservaciones(), new ArrayList<CambioEstadoLicencia>());
                    CambioEstadoLicencia cambioEstadoLicencia = new CambioEstadoLicencia(null, licencia.getIdLicencia(), null, EstadoLicencia.VIGENTE, LocalDateTime.now(), GestorUsuario.usuarioActual(), licencia.getObservacionesLicencia(), licencia);
                    licencia.getCambioEstadoLicencias().add(cambioEstadoLicencia);
                    if(GestorBD.guardarLicelcia(licencia)){
                        //Exito prro
                        return 0;
                    }
                    else{
                        //Error al guardar
                        return -2;
                    }
                }
                else{
                    //No se seleccionaron clases de licencia
                    return -3;
                }
            }
            else{
                //Error en la base de datos
                return -4;
            }
        }
        else{
            //Titular no encontrado en base de datos.
            return -1;
        }

    }

    public static LocalDateTime calcularVigencia(LocalDateTime fechaNacimiento, ArrayList<ClaseLicencia> clasesLicencia, Boolean tieneLicencias) {

        LocalDateTime fechaActual = LocalDateTime.now();

        Integer edad = fechaActual.getYear() - fechaNacimiento.getYear();

        if (fechaActual.getMonthValue() >= fechaNacimiento.getMonthValue() && fechaActual.getDayOfMonth() >= fechaNacimiento.getDayOfMonth()) {

        } else {
            edad -= 1;
        }

        //Se encarga de ver que fecha esta mas cerca para ver si sumarle o restarle uno al año o dejarlo igual
        Integer sumadorAñoVigencia = calcularAñoFechaVigencia(fechaActual.getMonthValue(), fechaNacimiento.getMonthValue());

        if (edad <= 21) {

            if(tieneLicencias){
                return new LocalDateTime(new LocalDate(fechaActual.getYear() + sumadorAñoVigencia,fechaNacimiento.getMonthValue(),fechaNacimiento.getDayOfMonth()),LocalTime.now());
            }
            else {

            }

        } else if (edad <= 46) {

        } else if (edad <= 60) {

        } else if (edad <= 70) {

        } else {

        }

        return null;

    }

    private static Integer calcularAñoFechaVigencia(Integer mesActual, Integer mesNacimiento){

        if(mesActual == mesNacimiento) {
            return 0;
        } else if (Math.abs(mesActual - mesNacimiento) > 6) {
            if(mesActual - mesNacimiento > 0) return 1;
            else return -1;
        } else return 0;

    }

}
