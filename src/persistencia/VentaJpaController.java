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
import modelo.Cliente;
import modelo.DetalleVenta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Venta;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class VentaJpaController implements Serializable {

    public VentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venta venta) {
        if (venta.getDetalleVenta() == null) {
            venta.setDetalleVenta(new ArrayList<DetalleVenta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente = venta.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getCodigo());
                venta.setCliente(cliente);
            }
            List<DetalleVenta> attachedDetalleVenta = new ArrayList<DetalleVenta>();
            for (DetalleVenta detalleVentaDetalleVentaToAttach : venta.getDetalleVenta()) {
                detalleVentaDetalleVentaToAttach = em.getReference(detalleVentaDetalleVentaToAttach.getClass(), detalleVentaDetalleVentaToAttach.getCodigo());
                attachedDetalleVenta.add(detalleVentaDetalleVentaToAttach);
            }
            venta.setDetalleVenta(attachedDetalleVenta);
            em.persist(venta);
            if (cliente != null) {
                cliente.getVentas().add(venta);
                cliente = em.merge(cliente);
            }
            for (DetalleVenta detalleVentaDetalleVenta : venta.getDetalleVenta()) {
                Venta oldVentaOfDetalleVentaDetalleVenta = detalleVentaDetalleVenta.getVenta();
                detalleVentaDetalleVenta.setVenta(venta);
                detalleVentaDetalleVenta = em.merge(detalleVentaDetalleVenta);
                if (oldVentaOfDetalleVentaDetalleVenta != null) {
                    oldVentaOfDetalleVentaDetalleVenta.getDetalleVenta().remove(detalleVentaDetalleVenta);
                    oldVentaOfDetalleVentaDetalleVenta = em.merge(oldVentaOfDetalleVentaDetalleVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venta venta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta persistentVenta = em.find(Venta.class, venta.getCodigo());
            Cliente clienteOld = persistentVenta.getCliente();
            Cliente clienteNew = venta.getCliente();
            List<DetalleVenta> detalleVentaOld = persistentVenta.getDetalleVenta();
            List<DetalleVenta> detalleVentaNew = venta.getDetalleVenta();
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getCodigo());
                venta.setCliente(clienteNew);
            }
            List<DetalleVenta> attachedDetalleVentaNew = new ArrayList<DetalleVenta>();
            for (DetalleVenta detalleVentaNewDetalleVentaToAttach : detalleVentaNew) {
                detalleVentaNewDetalleVentaToAttach = em.getReference(detalleVentaNewDetalleVentaToAttach.getClass(), detalleVentaNewDetalleVentaToAttach.getCodigo());
                attachedDetalleVentaNew.add(detalleVentaNewDetalleVentaToAttach);
            }
            detalleVentaNew = attachedDetalleVentaNew;
            venta.setDetalleVenta(detalleVentaNew);
            venta = em.merge(venta);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getVentas().remove(venta);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getVentas().add(venta);
                clienteNew = em.merge(clienteNew);
            }
            for (DetalleVenta detalleVentaOldDetalleVenta : detalleVentaOld) {
                if (!detalleVentaNew.contains(detalleVentaOldDetalleVenta)) {
                    detalleVentaOldDetalleVenta.setVenta(null);
                    detalleVentaOldDetalleVenta = em.merge(detalleVentaOldDetalleVenta);
                }
            }
            for (DetalleVenta detalleVentaNewDetalleVenta : detalleVentaNew) {
                if (!detalleVentaOld.contains(detalleVentaNewDetalleVenta)) {
                    Venta oldVentaOfDetalleVentaNewDetalleVenta = detalleVentaNewDetalleVenta.getVenta();
                    detalleVentaNewDetalleVenta.setVenta(venta);
                    detalleVentaNewDetalleVenta = em.merge(detalleVentaNewDetalleVenta);
                    if (oldVentaOfDetalleVentaNewDetalleVenta != null && !oldVentaOfDetalleVentaNewDetalleVenta.equals(venta)) {
                        oldVentaOfDetalleVentaNewDetalleVenta.getDetalleVenta().remove(detalleVentaNewDetalleVenta);
                        oldVentaOfDetalleVentaNewDetalleVenta = em.merge(oldVentaOfDetalleVentaNewDetalleVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = venta.getCodigo();
                if (findVenta(id) == null) {
                    throw new NonexistentEntityException("The venta with id " + id + " no longer exists.");
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
            Venta venta;
            try {
                venta = em.getReference(Venta.class, id);
                venta.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venta with id " + id + " no longer exists.", enfe);
            }
            Cliente cliente = venta.getCliente();
            if (cliente != null) {
                cliente.getVentas().remove(venta);
                cliente = em.merge(cliente);
            }
            List<DetalleVenta> detalleVenta = venta.getDetalleVenta();
            for (DetalleVenta detalleVentaDetalleVenta : detalleVenta) {
                detalleVentaDetalleVenta.setVenta(null);
                detalleVentaDetalleVenta = em.merge(detalleVentaDetalleVenta);
            }
            em.remove(venta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venta> findVentaEntities() {
        return findVentaEntities(true, -1, -1);
    }

    public List<Venta> findVentaEntities(int maxResults, int firstResult) {
        return findVentaEntities(false, maxResults, firstResult);
    }

    private List<Venta> findVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venta.class));
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

    public Venta findVenta(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venta.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venta> rt = cq.from(Venta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     public List<Venta> ListarVentasDescentes() {
        List<Venta> ventas = null;
        String sql = "SELECT  Object(u) FROM Venta u ORDER BY u.codigo DESC ";

        Query query = getEntityManager().createQuery(sql);
        ventas = query.getResultList();
        return ventas;
    }
    
    public List<Venta> ListarVentasPorCliente(int dni) throws Exception{
        List<Venta> venta = null;
        String sql = "SELECT Object(u) FROM Venta u WHERE u.cliente.codigo = '" + dni + "'";
         try {
             Query query = getEntityManager().createQuery(sql);
            venta= query.getResultList();
        
         } catch (Exception e) {
             return null;
         }
        return venta;
    }
    
    
    public List<Venta> ListarVentasPorFechas(String desde,String hasta) throws Exception {
        List<Venta> ventas = null;
        String sql = "SELECT  Object(u) FROM Venta u where u.fecha between '" +desde+ "' and '" +hasta+ "'";
        Query query = getEntityManager().createQuery(sql);
        ventas = query.getResultList();

        if(ventas.isEmpty()){
            throw new Exception("No se encontraron registros");
        }
        
        return ventas;
    }

    
}
