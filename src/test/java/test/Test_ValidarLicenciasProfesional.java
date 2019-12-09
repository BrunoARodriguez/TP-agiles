package test;

import LogicaDeNegocios.Entidades.Licencia;
import LogicaDeNegocios.Entidades.Titular;
import LogicaDeNegocios.Enumerations.ClaseLicencia;
import gestores.GestorLicencia;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Test_ValidarLicenciasProfesional {

    @Test
    public void validarLicenciasProfesional() {

        Licencia licencia1 = new Licencia();
        licencia1.setFechaAltaLicencia(LocalDateTime.of(2018, Month.JANUARY,01,00,00,00));
        licencia1.getClaseLicencias().add(ClaseLicencia.CLASE_B);
        Licencia licencia2 = new Licencia();
        licencia2.getClaseLicencias().add(ClaseLicencia.CLASE_A);
        licencia2.setFechaAltaLicencia(LocalDateTime.of(2018, Month.JANUARY,01,00,00,00));
        Licencia licencia3 = new Licencia();
        licencia3.getClaseLicencias().add(ClaseLicencia.CLASE_B);
        licencia3.setFechaAltaLicencia(LocalDateTime.of(2019, Month.JANUARY,01,00,00,00));

        List<Licencia> licenciasAprobadas = new ArrayList<Licencia>();
        licenciasAprobadas.add(licencia1);

        List<Licencia> licenciasDesaprobadas1 = new ArrayList<Licencia>();
        licenciasDesaprobadas1.add(licencia2);

        List<Licencia> licenciasDesaprobadas2 = new ArrayList<Licencia>();
        licenciasDesaprobadas2.add(licencia3);

        Assert.assertTrue(GestorLicencia.validarLicenciasProfesional(licenciasAprobadas));
        Assert.assertFalse(GestorLicencia.validarLicenciasProfesional(licenciasDesaprobadas1));
        Assert.assertFalse(GestorLicencia.validarLicenciasProfesional(licenciasDesaprobadas2));

    }

}
