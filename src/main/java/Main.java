import LogicaDeNegocios.DTOs.LicenciaDTO;
import LogicaDeNegocios.DTOs.TitularDTO;
import LogicaDeNegocios.Entidades.Licencia;
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
    }
}
