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
    public void calcularViegncia1() {

        LocalDateTime fechaPrueba1_18 = LocalDateTime.of(2001,Month.JULY,25,0,0,0);

        LocalDateTime resultadoAsertado1_18 = LocalDateTime.of(2020,Month.JANUARY,15,0,0,0);
        LocalDateTime resultadoAsertado2_18 = LocalDateTime.of(2022,Month.JANUARY,15,0,0,0);

        LocalDateTime result1 = GestorLicencia.calcularVigencia(fechaPrueba1_18,false);

        Assert.assertEquals(resultadoAsertado1_18,result1);

        LocalDateTime result2 = GestorLicencia.calcularVigencia(fechaPrueba1_18,true);

        Assert.assertEquals(resultadoAsertado2_18,result2);


    }

    @Test
    public void calcularViegncia2() {

        LocalDateTime fechaPrueba1_25 = LocalDateTime.of(1994,Month.JULY,25,0,0,0);

        LocalDateTime resultadoAsertado1_18 = LocalDateTime.of(20,Month.JANUARY,15,0,0,0);
        LocalDateTime resultadoAsertado2_18 = LocalDateTime.of(2022,Month.JANUARY,15,0,0,0);

        LocalDateTime result1 = GestorLicencia.calcularVigencia(fechaPrueba1_25,false);

        Assert.assertEquals(resultadoAsertado1_18,result1);


    }


}
