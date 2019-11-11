package test;

import gestores.GestorLicencia;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;


public class Test_Licencia {

    @Test
    public void calcularVigencia() {



        LocalDateTime fechaPrueba1_22 = LocalDateTime.of(2019,Month.JULY,25,0,0,0);
        LocalDateTime fechaPrueba2_22 = LocalDateTime.of(2019,Month.JANUARY, 10,0,0,0);
        LocalDateTime fechaPrueba3_22 = LocalDateTime.of(2019,Month.MARCH,10,0,0,0);

        LocalDateTime fechaPrueba1_49 = LocalDateTime.of(2046,Month.JULY,25,0,0,0);
        LocalDateTime fechaPrueba2_49 = LocalDateTime.of(2046,Month.JANUARY, 10,0,0,0);
        LocalDateTime fechaPrueba3_49 = LocalDateTime.of(2046,Month.MARCH,10,0,0,0);

        LocalDateTime fechaPrueba1_63 = LocalDateTime.of(2060,Month.JULY,25,0,0,0);
        LocalDateTime fechaPrueba2_63 = LocalDateTime.of(2060,Month.JANUARY, 10,0,0,0);
        LocalDateTime fechaPrueba3_63 = LocalDateTime.of(2060,Month.MARCH,10,0,0,0);

        LocalDateTime fechaPrueba1_73 = LocalDateTime.of(2015,Month.JULY,25,0,0,0);
        LocalDateTime fechaPrueba2_73 = LocalDateTime.of(2015,Month.JANUARY, 10,0,0,0);
        LocalDateTime fechaPrueba3_73 = LocalDateTime.of(2015,Month.MARCH,10,0,0,0);



        //Si tiene 18 años



    }

    @Test
    public void calcularViegencia18() {

        LocalDateTime fechaNacimiento_18 = LocalDateTime.of(1997,Month.JANUARY,15,0,0,0);

        LocalDateTime fechaPrueba1_18 = LocalDateTime.of(2015,Month.JULY,25,0,0,0);
        LocalDateTime fechaPrueba2_18 = LocalDateTime.of(2015,Month.JANUARY, 10,0,0,0);
        LocalDateTime fechaPrueba3_18 = LocalDateTime.of(2015,Month.NOVEMBER,10,0,0,0);

        LocalDateTime result1 = GestorLicencia.calcularVigencia(fechaNacimiento_18,false, fechaPrueba1_18);
        LocalDateTime result2 = GestorLicencia.calcularVigencia(fechaNacimiento_18,false, fechaPrueba2_18);
        LocalDateTime result3 = GestorLicencia.calcularVigencia(fechaNacimiento_18,false, fechaPrueba3_18);
        LocalDateTime result4 = GestorLicencia.calcularVigencia(fechaNacimiento_18,true, fechaPrueba1_18);
        LocalDateTime result5 = GestorLicencia.calcularVigencia(fechaNacimiento_18,true, fechaPrueba2_18);
        LocalDateTime result6 = GestorLicencia.calcularVigencia(fechaNacimiento_18,true, fechaPrueba3_18);

        LocalDateTime fechaEsperada1 = LocalDateTime.of(2016,Month.JANUARY,15,0,0,0);
        LocalDateTime fechaEsperada2 = LocalDateTime.of(2017,Month.JANUARY,15,0,0,0);
        LocalDateTime fechaEsperada3 = LocalDateTime.of(2018,Month.JANUARY,15,0,0,0);
        LocalDateTime fechaEsperada4 = LocalDateTime.of(2019,Month.JANUARY,15,0,0,0);

        Assert.assertEquals(fechaEsperada1,result1);
        Assert.assertEquals(fechaEsperada1,result2);
        Assert.assertEquals(fechaEsperada2,result3);
        Assert.assertEquals(fechaEsperada3,result4);
        Assert.assertEquals(fechaEsperada3,result5);
        Assert.assertEquals(fechaEsperada4,result6);


    }

    @Test
    public void calcularViegencia22() {

        LocalDateTime fechaNacimiento_22 = LocalDateTime.of(1997,Month.JANUARY,15,0,0,0);

        LocalDateTime fechaPrueba1_22 = LocalDateTime.of(2020,Month.JULY,25,0,0,0);
        LocalDateTime fechaPrueba2_22 = LocalDateTime.of(2020,Month.JANUARY, 10,0,0,0);
        LocalDateTime fechaPrueba3_22 = LocalDateTime.of(2020,Month.NOVEMBER,10,0,0,0);

        LocalDateTime result1 = GestorLicencia.calcularVigencia(fechaNacimiento_22,false, fechaPrueba1_22);
        LocalDateTime result2 = GestorLicencia.calcularVigencia(fechaNacimiento_22,false, fechaPrueba2_22);
        LocalDateTime result3 = GestorLicencia.calcularVigencia(fechaNacimiento_22,false, fechaPrueba3_22);
        LocalDateTime result4 = GestorLicencia.calcularVigencia(fechaNacimiento_22,true, fechaPrueba1_22);
        LocalDateTime result5 = GestorLicencia.calcularVigencia(fechaNacimiento_22,true, fechaPrueba2_22);
        LocalDateTime result6 = GestorLicencia.calcularVigencia(fechaNacimiento_22,true, fechaPrueba3_22);

        LocalDateTime fechaEsperada1 = LocalDateTime.of(2024,Month.JANUARY,15,0,0,0);
        LocalDateTime fechaEsperada2 = LocalDateTime.of(2025,Month.JANUARY,15,0,0,0);

        Assert.assertEquals(fechaEsperada1,result2);
        Assert.assertEquals(fechaEsperada1,result2);
        Assert.assertEquals(fechaEsperada2,result3);
        Assert.assertEquals(fechaEsperada1,result4);
        Assert.assertEquals(fechaEsperada1,result5);
        Assert.assertEquals(fechaEsperada2,result6);

    }


}
