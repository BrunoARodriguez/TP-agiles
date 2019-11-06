package test;

import gestores.GestorLicencia;
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

        LocalDateTime fechaNacimiento = LocalDateTime.of(1997, Month.JANUARY, 15,0,0,0);

        LocalDateTime fechaPrueba1_18 = LocalDateTime.of(2015,Month.JULY,25,0,0,0);
        LocalDateTime fechaPrueba2_18 = LocalDateTime.of(2015,Month.JANUARY, 10,0,0,0);
        LocalDateTime fechaPrueba3_18 = LocalDateTime.of(2015,Month.MARCH,10,0,0,0);

        LocalDateTime resultadoAsertado1_18 = LocalDateTime.of(2016,Month.JANUARY,15,0,0,0);
        LocalDateTime resultadoAsertado2_18 = LocalDateTime.of(2017,Month.JANUARY,15,0,0,0);
        LocalDateTime resultadoAsertado3_18 = LocalDateTime.of(2018,Month.JANUARY,15,0,0,0);
        LocalDateTime resultadoAsertado4_18 = LocalDateTime.of(2019,Month.JANUARY,15,0,0,0);

        LocalDateTime result = GestorLicencia.calcularVigencia(fechaNacimiento,fechaPrueba1_18)
    }


}
