package test;

import LogicaDeNegocios.DTOs.CriteriosDTO;
import LogicaDeNegocios.Entidades.Usuario;
import LogicaDeNegocios.Enumerations.ClaseLicencia;
import LogicaDeNegocios.Enumerations.Roles;
import gestores.GestorBD;
import gestores.GestorUsuario;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Test_Database {
    @Test
    public void buscarLicencias() {
        EntityManagerFactory emf = null;
        try{
            emf = Persistence.createEntityManagerFactory("persistencia");
        }catch (Exception e){
            System.out.println("No se pudo conectar la base de datos");
            e.printStackTrace();
        }
        GestorBD.setEmf(emf);
        GestorUsuario.setUsuario(new Usuario(Roles.OPERADOR, "Ematomas",123L));
        CriteriosDTO criteriosDTO = new CriteriosDTO();
        criteriosDTO.setNombreTitular("DIO");
        criteriosDTO.setFechaVencimientoDesde(LocalDateTime.now());//4

        Assert.assertEquals(GestorBD.buscarLicencias(criteriosDTO, 4).size(), 1);
    }
}
