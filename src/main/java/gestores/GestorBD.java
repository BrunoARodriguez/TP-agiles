package gestores;

import LogicaDeNegocios.Entidades.Contribuyente;
import LogicaDeNegocios.Entidades.Licencia;
import LogicaDeNegocios.Entidades.Titular;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class GestorBD {
    private static EntityManagerFactory emf;

    public static void setEmf(EntityManagerFactory factory) {
        emf = factory;
    }//cierra setEmf

    public static Titular buscarTitular(Long dni) {
        try {
            EntityManager manager = emf.createEntityManager();
            Titular titular;
            manager.getTransaction().begin();
            titular = manager.find(Titular.class, dni);
            manager.getTransaction().commit();
            titular.getLicencias().size();
            for(Licencia l : titular.getLicencias()){
                l.getClaseLicencias().size();
                l.getCambioEstadoLicencias().size();
            }
            manager.close();
            return titular;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }//cierra buscarTitular

    public static Contribuyente buscarContribuyente(Long dni) {
        try {
            EntityManager manager = emf.createEntityManager();
            Contribuyente contribuyente;
            manager.getTransaction().begin();
            contribuyente = manager.find(Contribuyente.class, dni);
            manager.getTransaction().commit();
            manager.close();
            return contribuyente;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    } //cierra buscarContribuyente

    public static Boolean guardarTitular(Titular titular) {
        try {
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            if(manager.find(Titular.class, titular.getContribuyente().getNroDocumento())!=null){
                titular = manager.merge(titular);
            }
            else{
                manager.persist(titular);
            }
            manager.getTransaction().commit();
            manager.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }//cierra guardarTitular

    public static Boolean guardarLicencia(Licencia licencia) {
        try {
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            if(licencia.getIdLicencia()== null || manager.find(Licencia.class, licencia.getIdLicencia())!=null){
                licencia = manager.merge(licencia);
            }
            else{
                manager.persist(licencia);
            }
            manager.getTransaction().commit();
            manager.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }//cierra guardarLicencia
}
