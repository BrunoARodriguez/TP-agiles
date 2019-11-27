package gestores;

import LogicaDeNegocios.DTOs.*;
import LogicaDeNegocios.Entidades.*;
import LogicaDeNegocios.Entidades.Resources.CostoLicencia;
import LogicaDeNegocios.Enumerations.ClaseLicencia;
import LogicaDeNegocios.Enumerations.EstadoLicencia;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class GestorLicencia {

    public static int crearLicencia(LicenciaDTO licenciaDTO) {

        Titular titular = GestorTitular.buscarTitular(GestorTitular.titularAux.getDni());
        Licencia licencia = new Licencia(titular, licenciaDTO.getFechaAltaLicencia(), licenciaDTO.getFechaVencimientoLicencia(), licenciaDTO.getClaseLicencias(), licenciaDTO.getObservacionesLicencia(), new ArrayList<CambioEstadoLicencia>());
        CambioEstadoLicencia cambioEstadoLicencia = new CambioEstadoLicencia(null, licencia.getIdLicencia(), null, EstadoLicencia.VIGENTE, LocalDateTime.now(), GestorUsuario.getUsuario(), licencia.getObservacionesLicencia(), licencia);
        licencia.getCambioEstadoLicencias().add(cambioEstadoLicencia);
        if (!validarClasesLicencia(licencia.getClaseLicencias(), titular)) {
            if (!GestorTitular.titularAux.getTieneLicencias()) {
                int i = 0;
                while (!GestorBD.borrarTitular(GestorTitular.titularAux.getDni()) && i < 20) {
                    System.out.println("Error borrando: " + i);
                    i++;
                }
                if (i < 20) {
                    System.out.println("Titular borrado.");
                }
            }
            // ya tiene licencia de esta(s) clase(s)
            return -1;
        }
        titular.getLicencias().add(licencia);
        if (GestorBD.guardarLicencia(licencia)) {
            Float costoLicencia = calcularCostoLicencia(licencia.getFechaAltaLicencia(), licencia.getFechaVencimientoLicencia(), licencia.getClaseLicencias());
            String observaciones = "Se ha emitido la licencia a nombre de : \n" + licencia.getTitularLicencia().getContribuyente().getNombreContribuyente() + " " + licencia.getTitularLicencia().getContribuyente().getApellidoContribuyente() + "\nDe la(s) clase(s) : " + licencia.getClaseLicencias().toString();
            Comprobante comprobante = new Comprobante(licenciaDTO.getFechaAltaLicencia(), costoLicencia, licencia, observaciones);
            GestorImpresion.imprimirComprobante(comprobante);
            //Exito perro
            return 0;
        } else {
            if (!GestorTitular.titularAux.getTieneLicencias()) {
                int i = 0;
                while (!GestorBD.borrarTitular(GestorTitular.titularAux.getDni()) && i < 20) {
                    System.out.println("Error borrando: " + i);
                    i++;
                }
                if (i < 20) {
                    System.out.println("Titular borrado.");
                }
            }
            //Error al guardar
            return -2;
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


    public static Boolean validarClasesLicencia(List<ClaseLicencia> claseLicencias, Titular titular) {
        LocalDateTime fechaActual = LocalDateTime.now();
        Period periodo = Period.between(titular.getContribuyente().getFechaNacimientoContribuyente().toLocalDate(), fechaActual.toLocalDate());
        Integer edad = periodo.getYears();
        if (edad <= 17) {
            return false;
        } else {
            for (ClaseLicencia cl : claseLicencias) {
                if (validarExisteLicencia(cl, titular.getLicencias())) {
                    if ((cl.equals(ClaseLicencia.CLASE_C) || cl.equals(ClaseLicencia.CLASE_D) || cl.equals(ClaseLicencia.CLASE_E))) {
                        if (!validarLicenciasProfesional(titular.getLicencias())) {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }


        }

        return true;
    } //cierra validarClasesLicencias

    public static Boolean validarExisteLicencia(ClaseLicencia claseLicencia, List<Licencia> licencias) {
        for (Licencia l : licencias) {
            for (ClaseLicencia cl : l.getClaseLicencias()) {
                if (claseLicencia.equals(cl) && l.getCambioEstadoLicencias().get(l.getCambioEstadoLicencias().size() - 1).getEstadoNuevo().equals(EstadoLicencia.VIGENTE)) {
                    return false;
                }

            }

        }

        return true;
    } //cierra validarExisteLicencia

    public static Boolean validarLicenciasProfesional(List<Licencia> licencias) {
        for (Licencia l : licencias) {
            for (ClaseLicencia cl : l.getClaseLicencias()) {
                Period periodo = Period.between(l.getFechaAltaLicencia().toLocalDate(), LocalDateTime.now().toLocalDate());
                Integer anio = periodo.getYears();
                if ((cl.equals(ClaseLicencia.CLASE_B) || cl.equals(ClaseLicencia.CLASE_C) || cl.equals(ClaseLicencia.CLASE_D) || cl.equals(ClaseLicencia.CLASE_E)) && anio >= 1) {
                    return true;
                }
            }
        }
        return false;
    } //cierra validarConductoresProfesional

    public static List<DatosTablaDTO> listarLicencias(CriteriosDTO criteriosDTO) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Licencia> buscadas = new ArrayList<>();
        buscadas = GestorBD.buscarLicencias(criteriosDTO, casoDeBusqueda(criteriosDTO.getFechaAltaDesde(), criteriosDTO.getFechaAltaHasta(), criteriosDTO.getFechaVencimientoDesde(), criteriosDTO.getFechaVencimientoHasta()));
        List<DatosTablaDTO> aux = new ArrayList<>();

        for (Licencia l : buscadas) {
            DatosTablaDTO datosTablaDTO = new DatosTablaDTO();
            datosTablaDTO.setIdLicencia(l.getIdLicencia());
            datosTablaDTO.setNombre(l.getTitularLicencia().getContribuyente().getNombreContribuyente());
            datosTablaDTO.setApellido(l.getTitularLicencia().getContribuyente().getApellidoContribuyente());
            datosTablaDTO.setDni(l.getTitularLicencia().getContribuyente().getNroDocumento().toString());
            datosTablaDTO.setEstadoLicencia(l.getCambioEstadoLicencias().get(l.getCambioEstadoLicencias().size()-1).getEstadoNuevo().getName());
            datosTablaDTO.setClasesLicencia(new ArrayList<>());
            for(ClaseLicencia cl : l.getClaseLicencias()){
                datosTablaDTO.getClasesLicencia().add(cl.getName());
            }
            datosTablaDTO.setFechaAlta(dateTimeFormatter.format(l.getFechaAltaLicencia()));
            aux.add(datosTablaDTO);
        }

        return aux;
    } //cierra metodo listaLicenciasExpiradas

    private static int casoDeBusqueda(LocalDateTime fechaAltaDesde, LocalDateTime fechaAltaHasta, LocalDateTime fechaVencimientoDesde, LocalDateTime fechaVencimientoHasta) {

        if (fechaAltaDesde == null && fechaAltaHasta != null) {
            // buscamos las licencias dadas de alta esa    fecha
            return 1;
        } else if (fechaAltaDesde != null && fechaAltaHasta == null) {
            //buscamos las licencias dadas de alta desde esa fecha en adelante
            return 2;
        } else if (fechaVencimientoDesde == null && fechaVencimientoHasta != null) {
            //buscamos las licencias que vencen esa fecha
            return 3;
        } else if (fechaVencimientoDesde != null && fechaVencimientoHasta == null) {
            // buscamos las licencias dadas de alta esa fecha en adelante
            return 4;
        } else if (fechaAltaDesde != null && fechaAltaHasta != null) {
            //buscamos por intervalo de fechas altas
            return 5;
        } else {
            //buscamos por intervalo de fechas vencimiento
            return 6;
        }
    }

    public static CarnetDTO buscarCarnetDTO(Long idLicencia) {

        Licencia licencia = GestorBD.buscarLicencia(idLicencia);
        if (licencia == null) {
            return null;
        } else {
            CarnetDTO carnetDTO = new CarnetDTO();
            carnetDTO.setIdLicencia(licencia.getIdLicencia());
            carnetDTO.setNombre(licencia.getTitularLicencia().getContribuyente().getNombreContribuyente());
            carnetDTO.setApellido(licencia.getTitularLicencia().getContribuyente().getApellidoContribuyente());
            carnetDTO.setDomicilio(licencia.getTitularLicencia().getContribuyente().getDomicilioContribuyente());
            carnetDTO.setFechaDeNacimiento(licencia.getTitularLicencia().getContribuyente().getFechaNacimientoContribuyente());
            carnetDTO.setFechaAltaLicencia(licencia.getFechaAltaLicencia());
            carnetDTO.setFechaVencimientoLicencia(licencia.getFechaVencimientoLicencia());
            //carnetDTO.setClaseLicencias(licencia.getClaseLicencias());
            // datosTablaDTO.setClasesLicencia(new ArrayList<>());
            carnetDTO.setClasesLicencia(new ArrayList<>());
            for(ClaseLicencia cl : licencia.getClaseLicencias()){
                carnetDTO.getClasesLicencia().add(cl.getName());
            }
            carnetDTO.setEsDonante(licencia.getTitularLicencia().getDonante());
            carnetDTO.setTipoSangre(licencia.getTitularLicencia().getTipoSangre());
            carnetDTO.setObservacionesLicencia(licencia.getObservacionesLicencia());


            return carnetDTO;
        }

    }

}

