package gestores;

import LogicaDeNegocios.Entidades.CambioEstadoLicencia;
import LogicaDeNegocios.Entidades.Licencia;
import LogicaDeNegocios.Entidades.Titular;
import LogicaDeNegocios.Enumerations.ClaseLicencia;
import LogicaDeNegocios.Enumerations.EstadoLicencia;
import org.hibernate.CacheMode;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class GestorLicencia {

    public  int crearLicencia(LicenciaDAO licenciaDAO){
        if(GestorTitular.validarTitularExistente(licenciaDAO.getDNI())){
            Titular titular = GestorTitular.buscarTitular(licenciaDAO.getDNI());
            if(titular!=null){
                if(!licenciaDAO.getClaseLicencia().isEmpty()){
                    Licencia licencia = new Licencia(titular, LocalDateTime.now(),GestorLicencia.calcularVigencia(titular.getContribuyente().getFechaNacimientoContribuyente()),licenciaDAO.getClaseLicencia(), licenciaDAO.getObservaciones(), new ArrayList<CambioEstadoLicencia>());
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

}
