package test;

import gestores.GestorLicencia;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;


public class Test_Licencia {

    @Test
    public void calcularViegencia18() {

        LocalDateTime fechaNacimiento_18 = LocalDateTime.of(1997, Month.JANUARY, 15, 0, 0, 0);

        LocalDateTime fechaPrueba1_18 = LocalDateTime.of(2015, Month.JULY, 25, 0, 0, 0);
        LocalDateTime fechaPrueba2_18 = LocalDateTime.of(2015, Month.JANUARY, 10, 0, 0, 0);
        LocalDateTime fechaPrueba3_18 = LocalDateTime.of(2015, Month.NOVEMBER, 10, 0, 0, 0);

        LocalDateTime result1 = GestorLicencia.calcularVigencia(fechaNacimiento_18, false, fechaPrueba1_18);
        LocalDateTime result2 = GestorLicencia.calcularVigencia(fechaNacimiento_18, false, fechaPrueba2_18);
        LocalDateTime result3 = GestorLicencia.calcularVigencia(fechaNacimiento_18, false, fechaPrueba3_18);
        LocalDateTime result4 = GestorLicencia.calcularVigencia(fechaNacimiento_18, true, fechaPrueba1_18);
        LocalDateTime result5 = GestorLicencia.calcularVigencia(fechaNacimiento_18, true, fechaPrueba2_18);
        LocalDateTime result6 = GestorLicencia.calcularVigencia(fechaNacimiento_18, true, fechaPrueba3_18);

        LocalDateTime fechaEsperada1 = LocalDateTime.of(2016, Month.JANUARY, 15, 0, 0, 0);
        LocalDateTime fechaEsperada2 = LocalDateTime.of(2017, Month.JANUARY, 15, 0, 0, 0);
        LocalDateTime fechaEsperada3 = LocalDateTime.of(2018, Month.JANUARY, 15, 0, 0, 0);
        LocalDateTime fechaEsperada4 = LocalDateTime.of(2019, Month.JANUARY, 15, 0, 0, 0);

        Assert.assertEquals(fechaEsperada1, result1);
        Assert.assertEquals(fechaEsperada1, result2);
        Assert.assertEquals(fechaEsperada2, result3);
        Assert.assertEquals(fechaEsperada3, result4);
        Assert.assertEquals(fechaEsperada3, result5);
        Assert.assertEquals(fechaEsperada4, result6);


    }

    @Test
    public void calcularViegencia22() {

        LocalDateTime fechaNacimiento_22 = LocalDateTime.of(1997, Month.JANUARY, 15, 0, 0, 0);

        LocalDateTime fechaPrueba1_22 = LocalDateTime.of(2020, Month.JULY, 25, 0, 0, 0);
        LocalDateTime fechaPrueba2_22 = LocalDateTime.of(2020, Month.JANUARY, 10, 0, 0, 0);
        LocalDateTime fechaPrueba3_22 = LocalDateTime.of(2020, Month.NOVEMBER, 10, 0, 0, 0);

        LocalDateTime result1 = GestorLicencia.calcularVigencia(fechaNacimiento_22, false, fechaPrueba1_22);
        LocalDateTime result2 = GestorLicencia.calcularVigencia(fechaNacimiento_22, false, fechaPrueba2_22);
        LocalDateTime result3 = GestorLicencia.calcularVigencia(fechaNacimiento_22, false, fechaPrueba3_22);
        LocalDateTime result4 = GestorLicencia.calcularVigencia(fechaNacimiento_22, true, fechaPrueba1_22);
        LocalDateTime result5 = GestorLicencia.calcularVigencia(fechaNacimiento_22, true, fechaPrueba2_22);
        LocalDateTime result6 = GestorLicencia.calcularVigencia(fechaNacimiento_22, true, fechaPrueba3_22);

        LocalDateTime fechaEsperada1 = LocalDateTime.of(2025, Month.JANUARY, 15, 0, 0, 0);
        LocalDateTime fechaEsperada2 = LocalDateTime.of(2026, Month.JANUARY, 15, 0, 0, 0);

        Assert.assertEquals(fechaEsperada1, result1);
        Assert.assertEquals(fechaEsperada1, result2);
        Assert.assertEquals(fechaEsperada2, result3);
        Assert.assertEquals(fechaEsperada1, result4);
        Assert.assertEquals(fechaEsperada1, result5);
        Assert.assertEquals(fechaEsperada2, result6);

    }

    @Test
    public void calcularVigencia50() {
        LocalDateTime fechaNacimiento_50 = LocalDateTime.of(1997, Month.JANUARY, 15, 0, 0, 0);

        LocalDateTime fechaPrueba1_50 = LocalDateTime.of(2047, Month.JULY, 25, 0, 0, 0);
        LocalDateTime fechaPrueba2_50 = LocalDateTime.of(2047, Month.JANUARY, 10, 0, 0, 0);
        LocalDateTime fechaPrueba3_50 = LocalDateTime.of(2047, Month.NOVEMBER, 10, 0, 0, 0);

        LocalDateTime result1 = GestorLicencia.calcularVigencia(fechaNacimiento_50, false, fechaPrueba1_50);
        LocalDateTime result2 = GestorLicencia.calcularVigencia(fechaNacimiento_50, false, fechaPrueba2_50);
        LocalDateTime result3 = GestorLicencia.calcularVigencia(fechaNacimiento_50, false, fechaPrueba3_50);
        LocalDateTime result4 = GestorLicencia.calcularVigencia(fechaNacimiento_50, true, fechaPrueba1_50);
        LocalDateTime result5 = GestorLicencia.calcularVigencia(fechaNacimiento_50, true, fechaPrueba2_50);
        LocalDateTime result6 = GestorLicencia.calcularVigencia(fechaNacimiento_50, true, fechaPrueba3_50);

        LocalDateTime fechaEsperada1 = LocalDateTime.of(2051, Month.JANUARY, 15, 0, 0, 0);
        LocalDateTime fechaEsperada2 = LocalDateTime.of(2052, Month.JANUARY, 15, 0, 0, 0);

        Assert.assertEquals(fechaEsperada1, result1);
        Assert.assertEquals(fechaEsperada1, result2);
        Assert.assertEquals(fechaEsperada2, result3);
        Assert.assertEquals(fechaEsperada1, result4);
        Assert.assertEquals(fechaEsperada1, result5);
        Assert.assertEquals(fechaEsperada2, result6);
    }

    @Test
    public void calcularVigencia65() {
        LocalDateTime fechaNacimiento_65 = LocalDateTime.of(1997, Month.JANUARY, 15, 0, 0, 0);

        LocalDateTime fechaPrueba1_65 = LocalDateTime.of(2062, Month.JULY, 25, 0, 0, 0);
        LocalDateTime fechaPrueba2_65 = LocalDateTime.of(2062, Month.JANUARY, 10, 0, 0, 0);
        LocalDateTime fechaPrueba3_65 = LocalDateTime.of(2062, Month.NOVEMBER, 10, 0, 0, 0);

        LocalDateTime result1 = GestorLicencia.calcularVigencia(fechaNacimiento_65, false, fechaPrueba1_65);
        LocalDateTime result2 = GestorLicencia.calcularVigencia(fechaNacimiento_65, false, fechaPrueba2_65);
        LocalDateTime result3 = GestorLicencia.calcularVigencia(fechaNacimiento_65, false, fechaPrueba3_65);
        LocalDateTime result4 = GestorLicencia.calcularVigencia(fechaNacimiento_65, true, fechaPrueba1_65);
        LocalDateTime result5 = GestorLicencia.calcularVigencia(fechaNacimiento_65, true, fechaPrueba2_65);
        LocalDateTime result6 = GestorLicencia.calcularVigencia(fechaNacimiento_65, true, fechaPrueba3_65);

        LocalDateTime fechaEsperada1 = LocalDateTime.of(2065, Month.JANUARY, 15, 0, 0, 0);
        LocalDateTime fechaEsperada2 = LocalDateTime.of(2066, Month.JANUARY, 15, 0, 0, 0);

        Assert.assertEquals(fechaEsperada1, result1);
        Assert.assertEquals(fechaEsperada1, result2);
        Assert.assertEquals(fechaEsperada2, result3);
        Assert.assertEquals(fechaEsperada1, result4);
        Assert.assertEquals(fechaEsperada1, result5);
        Assert.assertEquals(fechaEsperada2, result6);
    }

    @Test
    public void calcularVigencia75() {
        LocalDateTime fechaNacimiento_75 = LocalDateTime.of(1997, Month.JANUARY, 15, 0, 0, 0);

        LocalDateTime fechaPrueba1_75 = LocalDateTime.of(2072, Month.JULY, 25, 0, 0, 0);
        LocalDateTime fechaPrueba2_75 = LocalDateTime.of(2072, Month.JANUARY, 10, 0, 0, 0);
        LocalDateTime fechaPrueba3_75 = LocalDateTime.of(2072, Month.NOVEMBER, 10, 0, 0, 0);

        LocalDateTime result1 = GestorLicencia.calcularVigencia(fechaNacimiento_75, false, fechaPrueba1_75);
        LocalDateTime result2 = GestorLicencia.calcularVigencia(fechaNacimiento_75, false, fechaPrueba2_75);
        LocalDateTime result3 = GestorLicencia.calcularVigencia(fechaNacimiento_75, false, fechaPrueba3_75);
        LocalDateTime result4 = GestorLicencia.calcularVigencia(fechaNacimiento_75, true, fechaPrueba1_75);
        LocalDateTime result5 = GestorLicencia.calcularVigencia(fechaNacimiento_75, true, fechaPrueba2_75);
        LocalDateTime result6 = GestorLicencia.calcularVigencia(fechaNacimiento_75, true, fechaPrueba3_75);

        LocalDateTime fechaEsperada1 = LocalDateTime.of(2073, Month.JANUARY, 15, 0, 0, 0);
        LocalDateTime fechaEsperada2 = LocalDateTime.of(2074, Month.JANUARY, 15, 0, 0, 0);

        Assert.assertEquals(fechaEsperada1, result1);
        Assert.assertEquals(fechaEsperada1, result2);
        Assert.assertEquals(fechaEsperada2, result3);
        Assert.assertEquals(fechaEsperada1, result4);
        Assert.assertEquals(fechaEsperada1, result5);
        Assert.assertEquals(fechaEsperada2, result6);
    }


}
