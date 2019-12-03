package gestores;

import LogicaDeNegocios.DTOs.CriteriosDTO;
import LogicaDeNegocios.Entidades.Contribuyente;
import LogicaDeNegocios.Entidades.Licencia;
import LogicaDeNegocios.Entidades.Resources.CostoLicencia;
import LogicaDeNegocios.Entidades.Titular;
import LogicaDeNegocios.Enumerations.ClaseLicencia;
import LogicaDeNegocios.Enumerations.EstadoLicencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

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
            if (titular == null) {
                return null;
            }
            titular.getLicencias().size();
            for (Licencia l : titular.getLicencias()) {
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
            if (manager.find(Titular.class, titular.getContribuyente().getNroDocumento()) != null) {
                titular = manager.merge(titular);
            } else {
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
            if (licencia.getIdLicencia() == null || manager.find(Licencia.class, licencia.getIdLicencia()) != null) {
                licencia = manager.merge(licencia);
            } else {
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

    public static CostoLicencia buscarCosto(CostoLicencia ct) {
        try {
            EntityManager manager = emf.createEntityManager();
            CostoLicencia costo;
            manager.getTransaction().begin();
            costo = manager.find(CostoLicencia.class, new CostoLicencia.Atributos(ct.getAtributos().getClaseLicencia(), ct.getAtributos().getVigenciaLicencia()));
            manager.getTransaction().commit();
            manager.close();
            return costo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean borrarTitular(Long dni) {
        Titular titular;
        try {
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            titular = manager.find(Titular.class, dni);
            manager.remove(titular);
            manager.getTransaction().commit();
            manager.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static List<Licencia> buscarLicencias(CriteriosDTO criteriosDTO, Integer caso) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("''yyyy-MM-dd HH:mm:ss''");
        EntityManager manager = emf.createEntityManager();
        StringBuffer query = new StringBuffer()
                .append("SELECT l FROM Licencia l, Titular t, Contribuyente c WHERE l.titularLicencia LIKE ")
                .append("'%")
                .append(criteriosDTO.getDniTitular())
                .append("%'")
                .append(" AND t.contribuyente = l.titularLicencia AND c.nroDocumento = l.titularLicencia AND c.nombreContribuyente LIKE ")
                .append("'%")
                .append(criteriosDTO.getNombreTitular())
                .append("%'")
                .append(" AND c.apellidoContribuyente LIKE ")
                .append("'%")
                .append(criteriosDTO.getApellidoTitular())
                .append("%'");

        switch (caso) {
            case 1:
                query.append(" AND l.fechaAltaLicencia BETWEEN ")
                        .append(dateTimeFormatter.format(criteriosDTO.getFechaAltaHasta()))
                        .append(" AND ")
                        .append(dateTimeFormatter.format(criteriosDTO.getFechaAltaHasta().plusDays(1)));
                break;
            case 2:
                query.append(" AND l.fechaAltaLicencia >= ")
                        .append(dateTimeFormatter.format(criteriosDTO.getFechaAltaDesde()));
                break;
            case 3:
                query.append(" AND l.fechaVencimientoLicencia = ")
                        .append(dateTimeFormatter.format(criteriosDTO.getFechaVencimientoHasta()));
                break;
            case 4:
                query.append(" AND l.fechaVencimientoLicencia >= ")
                        .append(dateTimeFormatter.format(criteriosDTO.getFechaVencimientoDesde()));
                break;
            case 5:
                query.append(" AND l.fechaAltaLicencia BETWEEN ")
                        .append(dateTimeFormatter.format(criteriosDTO.getFechaAltaDesde()))
                        .append(" AND ")
                        .append(dateTimeFormatter.format(criteriosDTO.getFechaAltaHasta().plusDays(1)));
                break;
            case 6:
                query.append(" AND l.fechaVencimientoLicencia BETWEEN ")
                        .append(dateTimeFormatter.format(criteriosDTO.getFechaVencimientoDesde()))
                        .append(" AND ")
                        .append(dateTimeFormatter.format(criteriosDTO.getFechaVencimientoHasta().plusDays(1)));
                break;
        }
        Query query1 = manager.createQuery(query.toString());

        List<Licencia> licenciaList= query1.getResultList();

        if(!criteriosDTO.getClaseLicencias().isEmpty()){
            Iterator<Licencia> iterator = licenciaList.iterator();
            while(iterator.hasNext()){
                Licencia licencia = iterator.next();
                int i = 0;
                Boolean match = false;
                while(!match && i<criteriosDTO.getClaseLicencias().size()){
                    if(licencia.getClaseLicencias().contains(criteriosDTO.getClaseLicencias().get(i))){
                        match = true;
                    }
                    i++;
                }
                if(!match || licencia.getCambioEstadoLicencias().get(licencia.getCambioEstadoLicencias().size()-1).getEstadoNuevo() == EstadoLicencia.NO_VIGENTE){
                    iterator.remove();
                }
            }
        }

        return licenciaList;
    }//cierra buscarLicencias

    public static Licencia buscarLicencia(Long idLicencia) {
        try {
            EntityManager manager = emf.createEntityManager();
            Licencia licencia;
            manager.getTransaction().begin();
             licencia = manager.find(Licencia.class, idLicencia);
            manager.getTransaction().commit();
            System.out.println("tamaño clases : " +  licencia.getClaseLicencias().size());
            manager.close();
            return licencia;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }//cierra buscarLicencia
}
