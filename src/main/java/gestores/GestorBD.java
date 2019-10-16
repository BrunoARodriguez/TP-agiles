package gestores;

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


    }


}
