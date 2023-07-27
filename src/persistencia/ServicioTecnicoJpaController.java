/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.EstadoServicio;
import modelo.ServicioIncluido;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.ProductoIncluido;
import modelo.ServicioTecnico;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class ServicioTecnicoJpaController implements Serializable {

    public ServicioTecnicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServicioTecnico servicioTecnico) {
        if (servicioTecnico.getServicioIncluido() == null) {
            servicioTecnico.setServicioIncluido(new ArrayList<ServicioIncluido>());
        }
        if (servicioTecnico.getProductoIncluido() == null) {
            servicioTecnico.setProductoIncluido(new ArrayList<ProductoIncluido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadoServicio estadoServicio = servicioTecnico.getEstadoServicio();
            if (estadoServicio != null) {
                estadoServicio = em.getReference(estadoServicio.getClass(), estadoServicio.getCodigo());
                servicioTecnico.setEstadoServicio(estadoServicio);
            }
            List<ServicioIncluido> attachedServicioIncluido = new ArrayList<ServicioIncluido>();
            for (ServicioIncluido servicioIncluidoServicioIncluidoToAttach : servicioTecnico.getServicioIncluido()) {
                servicioIncluidoServicioIncluidoToAttach = em.getReference(servicioIncluidoServicioIncluidoToAttach.getClass(), servicioIncluidoServicioIncluidoToAttach.getCodigo());
                attachedServicioIncluido.add(servicioIncluidoServicioIncluidoToAttach);
            }
            servicioTecnico.setServicioIncluido(attachedServicioIncluido);
            List<ProductoIncluido> attachedProductoIncluido = new ArrayList<ProductoIncluido>();
            for (ProductoIncluido productoIncluidoProductoIncluidoToAttach : servicioTecnico.getProductoIncluido()) {
                productoIncluidoProductoIncluidoToAttach = em.getReference(productoIncluidoProductoIncluidoToAttach.getClass(), productoIncluidoProductoIncluidoToAttach.getCodigo());
                attachedProductoIncluido.add(productoIncluidoProductoIncluidoToAttach);
            }
            servicioTecnico.setProductoIncluido(attachedProductoIncluido);
            em.persist(servicioTecnico);
            if (estadoServicio != null) {
                estadoServicio.getServicioTenico().add(servicioTecnico);
                estadoServicio = em.merge(estadoServicio);
            }
            for (ServicioIncluido servicioIncluidoServicioIncluido : servicioTecnico.getServicioIncluido()) {
                ServicioTecnico oldServicioTecnicoOfServicioIncluidoServicioIncluido = servicioIncluidoServicioIncluido.getServicioTecnico();
                servicioIncluidoServicioIncluido.setServicioTecnico(servicioTecnico);
                servicioIncluidoServicioIncluido = em.merge(servicioIncluidoServicioIncluido);
                if (oldServicioTecnicoOfServicioIncluidoServicioIncluido != null) {
                    oldServicioTecnicoOfServicioIncluidoServicioIncluido.getServicioIncluido().remove(servicioIncluidoServicioIncluido);
                    oldServicioTecnicoOfServicioIncluidoServicioIncluido = em.merge(oldServicioTecnicoOfServicioIncluidoServicioIncluido);
                }
            }
            for (ProductoIncluido productoIncluidoProductoIncluido : servicioTecnico.getProductoIncluido()) {
                ServicioTecnico oldServicioTecnicoOfProductoIncluidoProductoIncluido = productoIncluidoProductoIncluido.getServicioTecnico();
                productoIncluidoProductoIncluido.setServicioTecnico(servicioTecnico);
                productoIncluidoProductoIncluido = em.merge(productoIncluidoProductoIncluido);
                if (oldServicioTecnicoOfProductoIncluidoProductoIncluido != null) {
                    oldServicioTecnicoOfProductoIncluidoProductoIncluido.getProductoIncluido().remove(productoIncluidoProductoIncluido);
                    oldServicioTecnicoOfProductoIncluidoProductoIncluido = em.merge(oldServicioTecnicoOfProductoIncluidoProductoIncluido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServicioTecnico servicioTecnico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServicioTecnico persistentServicioTecnico = em.find(ServicioTecnico.class, servicioTecnico.getCodigo());
            EstadoServicio estadoServicioOld = persistentServicioTecnico.getEstadoServicio();
            EstadoServicio estadoServicioNew = servicioTecnico.getEstadoServicio();
            List<ServicioIncluido> servicioIncluidoOld = persistentServicioTecnico.getServicioIncluido();
            List<ServicioIncluido> servicioIncluidoNew = servicioTecnico.getServicioIncluido();
            List<ProductoIncluido> productoIncluidoOld = persistentServicioTecnico.getProductoIncluido();
            List<ProductoIncluido> productoIncluidoNew = servicioTecnico.getProductoIncluido();
            if (estadoServicioNew != null) {
                estadoServicioNew = em.getReference(estadoServicioNew.getClass(), estadoServicioNew.getCodigo());
                servicioTecnico.setEstadoServicio(estadoServicioNew);
            }
            List<ServicioIncluido> attachedServicioIncluidoNew = new ArrayList<ServicioIncluido>();
            for (ServicioIncluido servicioIncluidoNewServicioIncluidoToAttach : servicioIncluidoNew) {
                servicioIncluidoNewServicioIncluidoToAttach = em.getReference(servicioIncluidoNewServicioIncluidoToAttach.getClass(), servicioIncluidoNewServicioIncluidoToAttach.getCodigo());
                attachedServicioIncluidoNew.add(servicioIncluidoNewServicioIncluidoToAttach);
            }
            servicioIncluidoNew = attachedServicioIncluidoNew;
            servicioTecnico.setServicioIncluido(servicioIncluidoNew);
            List<ProductoIncluido> attachedProductoIncluidoNew = new ArrayList<ProductoIncluido>();
            for (ProductoIncluido productoIncluidoNewProductoIncluidoToAttach : productoIncluidoNew) {
                productoIncluidoNewProductoIncluidoToAttach = em.getReference(productoIncluidoNewProductoIncluidoToAttach.getClass(), productoIncluidoNewProductoIncluidoToAttach.getCodigo());
                attachedProductoIncluidoNew.add(productoIncluidoNewProductoIncluidoToAttach);
            }
            productoIncluidoNew = attachedProductoIncluidoNew;
            servicioTecnico.setProductoIncluido(productoIncluidoNew);
            servicioTecnico = em.merge(servicioTecnico);
            if (estadoServicioOld != null && !estadoServicioOld.equals(estadoServicioNew)) {
                estadoServicioOld.getServicioTenico().remove(servicioTecnico);
                estadoServicioOld = em.merge(estadoServicioOld);
            }
            if (estadoServicioNew != null && !estadoServicioNew.equals(estadoServicioOld)) {
                estadoServicioNew.getServicioTenico().add(servicioTecnico);
                estadoServicioNew = em.merge(estadoServicioNew);
            }
            for (ServicioIncluido servicioIncluidoOldServicioIncluido : servicioIncluidoOld) {
                if (!servicioIncluidoNew.contains(servicioIncluidoOldServicioIncluido)) {
                    servicioIncluidoOldServicioIncluido.setServicioTecnico(null);
                    servicioIncluidoOldServicioIncluido = em.merge(servicioIncluidoOldServicioIncluido);
                }
            }
            for (ServicioIncluido servicioIncluidoNewServicioIncluido : servicioIncluidoNew) {
                if (!servicioIncluidoOld.contains(servicioIncluidoNewServicioIncluido)) {
                    ServicioTecnico oldServicioTecnicoOfServicioIncluidoNewServicioIncluido = servicioIncluidoNewServicioIncluido.getServicioTecnico();
                    servicioIncluidoNewServicioIncluido.setServicioTecnico(servicioTecnico);
                    servicioIncluidoNewServicioIncluido = em.merge(servicioIncluidoNewServicioIncluido);
                    if (oldServicioTecnicoOfServicioIncluidoNewServicioIncluido != null && !oldServicioTecnicoOfServicioIncluidoNewServicioIncluido.equals(servicioTecnico)) {
                        oldServicioTecnicoOfServicioIncluidoNewServicioIncluido.getServicioIncluido().remove(servicioIncluidoNewServicioIncluido);
                        oldServicioTecnicoOfServicioIncluidoNewServicioIncluido = em.merge(oldServicioTecnicoOfServicioIncluidoNewServicioIncluido);
                    }
                }
            }
            for (ProductoIncluido productoIncluidoOldProductoIncluido : productoIncluidoOld) {
                if (!productoIncluidoNew.contains(productoIncluidoOldProductoIncluido)) {
                    productoIncluidoOldProductoIncluido.setServicioTecnico(null);
                    productoIncluidoOldProductoIncluido = em.merge(productoIncluidoOldProductoIncluido);
                }
            }
            for (ProductoIncluido productoIncluidoNewProductoIncluido : productoIncluidoNew) {
                if (!productoIncluidoOld.contains(productoIncluidoNewProductoIncluido)) {
                    ServicioTecnico oldServicioTecnicoOfProductoIncluidoNewProductoIncluido = productoIncluidoNewProductoIncluido.getServicioTecnico();
                    productoIncluidoNewProductoIncluido.setServicioTecnico(servicioTecnico);
                    productoIncluidoNewProductoIncluido = em.merge(productoIncluidoNewProductoIncluido);
                    if (oldServicioTecnicoOfProductoIncluidoNewProductoIncluido != null && !oldServicioTecnicoOfProductoIncluidoNewProductoIncluido.equals(servicioTecnico)) {
                        oldServicioTecnicoOfProductoIncluidoNewProductoIncluido.getProductoIncluido().remove(productoIncluidoNewProductoIncluido);
                        oldServicioTecnicoOfProductoIncluidoNewProductoIncluido = em.merge(oldServicioTecnicoOfProductoIncluidoNewProductoIncluido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = servicioTecnico.getCodigo();
                if (findServicioTecnico(id) == null) {
                    throw new NonexistentEntityException("The servicioTecnico with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServicioTecnico servicioTecnico;
            try {
                servicioTecnico = em.getReference(ServicioTecnico.class, id);
                servicioTecnico.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicioTecnico with id " + id + " no longer exists.", enfe);
            }
            EstadoServicio estadoServicio = servicioTecnico.getEstadoServicio();
            if (estadoServicio != null) {
                estadoServicio.getServicioTenico().remove(servicioTecnico);
                estadoServicio = em.merge(estadoServicio);
            }
            List<ServicioIncluido> servicioIncluido = servicioTecnico.getServicioIncluido();
            for (ServicioIncluido servicioIncluidoServicioIncluido : servicioIncluido) {
                servicioIncluidoServicioIncluido.setServicioTecnico(null);
                servicioIncluidoServicioIncluido = em.merge(servicioIncluidoServicioIncluido);
            }
            List<ProductoIncluido> productoIncluido = servicioTecnico.getProductoIncluido();
            for (ProductoIncluido productoIncluidoProductoIncluido : productoIncluido) {
                productoIncluidoProductoIncluido.setServicioTecnico(null);
                productoIncluidoProductoIncluido = em.merge(productoIncluidoProductoIncluido);
            }
            em.remove(servicioTecnico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServicioTecnico> findServicioTecnicoEntities() {
        return findServicioTecnicoEntities(true, -1, -1);
    }

    public List<ServicioTecnico> findServicioTecnicoEntities(int maxResults, int firstResult) {
        return findServicioTecnicoEntities(false, maxResults, firstResult);
    }

    private List<ServicioTecnico> findServicioTecnicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServicioTecnico.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ServicioTecnico findServicioTecnico(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServicioTecnico.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioTecnicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServicioTecnico> rt = cq.from(ServicioTecnico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
         public List<ServicioTecnico> ListarServiciosTecnicosDescentes() {
        List<ServicioTecnico> servicioTecnicos = null;
        String sql = "SELECT  Object(u) FROM ServicioTecnico u ORDER BY u.codigo DESC ";

        Query query = getEntityManager().createQuery(sql);
        servicioTecnicos = query.getResultList();
        return servicioTecnicos;
    }
     
     public List<ServicioTecnico> ListarServiciosTecnicosPorCliente(int codigo) {
        List<ServicioTecnico> servicioTecnicos = null;
        String sql = "SELECT Object(u) FROM ServicioTecnico u WHERE u.cliente.codigo = '" + codigo + "'";
         try {
             Query query = getEntityManager().createQuery(sql);
            servicioTecnicos = query.getResultList();
        
         } catch (Exception e) {
             return null;
         }
        return servicioTecnicos;
    }
     
     
    public List<ServicioTecnico> ListarServiciosPorFechas(String desde,String hasta) throws Exception {
        List<ServicioTecnico> servicios = null;
        String sql = "SELECT  Object(u) FROM ServicioTecnico u where u.fechaDeEgreso between '" +desde+ "' and '" +hasta+ "'";
        Query query = getEntityManager().createQuery(sql);
        servicios = query.getResultList();

        if(servicios.isEmpty()){
            throw new Exception("No se encontraron Registros");
        }
        
        return servicios;
    }
}
