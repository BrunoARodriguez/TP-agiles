import LogicaDeNegocios.DTOs.LicenciaDTO;
import LogicaDeNegocios.DTOs.TitularDTO;
import LogicaDeNegocios.Entidades.Titular;
import LogicaDeNegocios.Entidades.Usuario;
import LogicaDeNegocios.Enumerations.ClaseLicencia;
import LogicaDeNegocios.Enumerations.Roles;
import LogicaDeNegocios.Enumerations.TipoSangre;
import gestores.GestorBD;
import gestores.GestorLicencia;
import gestores.GestorTitular;
import gestores.GestorUsuario;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        EntityManagerFactory emf = null;
        try{
            emf = Persistence.createEntityManagerFactory("persistencia");
        }catch (Exception e){
            System.out.println("No se pudo conectar la base de datos");
            e.printStackTrace();
        }
        GestorBD.setEmf(emf);
        GestorUsuario.setUsuario(new Usuario(Roles.OPERADOR, "Ematomas",123L));
        //GestorTitular.crearTitular(new TitularDTO(23456789L, "Es un pinche vampiro we", false, TipoSangre.A_NEGATIVO));
        /*Titular titular = GestorBD.buscarTitular(12345678L);
        titular.setTipoSangre(TipoSangre.O_NEGATIVO);
        GestorBD.guardarTitular(titular);*/
        /*ArrayList<ClaseLicencia> claseLicencias = new ArrayList<>();
        claseLicencias.add(ClaseLicencia.CLASE_C);
        GestorLicencia.crearLicencia(new LicenciaDTO(null, 23456789L, LocalDateTime.now(), LocalDateTime.of(1868, 04, 04, 00, 00, 00), claseLicencias, "No puede manejar de dia"));
        Titular titular = GestorTitular.buscarTitular(23456789L);
        System.out.println(titular.getLicencias().get(0).getClaseLicencias());*/
    }
}
