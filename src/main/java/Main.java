import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args){
        EntityManagerFactory emf = null;
        try{
            emf = Persistence.createEntityManagerFactory("persistencia");
        }catch (Exception e){
            System.out.println("No se pudo conectar la base de datos");
            e.printStackTrace();
        }
    }
}
