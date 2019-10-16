package gestores;

import LogicaDeNegocios.Entidades.Contribuyente;
import LogicaDeNegocios.Entidades.Titular;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class GestorBD {
private static EntityManagerFactory emf;

public  static  void  setEmf(EntityManagerFactory factory){
            emf=factory;
}

    public static Titular buscarTitular(Long dni){
        try {
            EntityManager manager= emf.createEntityManager();
            Titular titular;
            manager.getTransaction().begin();
            titular=manager.find(Titular.class,dni);
            manager.getTransaction().commit();
            manager.close();
            return  titular;
        }
        catch (Exception e){
            e.printStackTrace();
            return  null;
        }

        public static Contribuyente buscarContribuyente(Long dni){
            try {
                EntityManager manager= emf.createEntityManager();
                Contribuyente contribuyente;
                manager.getTransaction().begin();
                contribuyente=manager.find(Contribuyente.class,dni);
                manager.getTransaction().commit();
                manager.close();
                return contribuyente;
            }
            catch (Exception e){
                e.printStackTrace();
                return  null;
            }


    } //cierra buscarTitular

    public  static Boolean guardarTitular(Titular titular){
        try {
            EntityManager manager=emf.createEntityManager();
            manager.getTransaction().begin();
            manager.merge(titular);
            manager.persist(titular);
            manager.getTransaction().commit();
            manager.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }



}
