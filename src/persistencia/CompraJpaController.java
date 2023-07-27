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
import modelo.DetalleCompra;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Compra;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class CompraJpaController implements Serializable {

    public CompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Compra compra) {
        if (compra.getDetalleCompra() == null) {
            compra.setDetalleCompra(new ArrayList<DetalleCompra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<DetalleCompra> attachedDetalleCompra = new ArrayList<DetalleCompra>();
            for (DetalleCompra detalleCompraDetalleCompraToAttach : compra.getDetalleCompra()) {
                detalleCompraDetalleCompraToAttach = em.getReference(detalleCompraDetalleCompraToAttach.getClass(), detalleCompraDetalleCompraToAttach.getCodigo());
                attachedDetalleCompra.add(detalleCompraDetalleCompraToAttach);
            }
            compra.setDetalleCompra(attachedDetalleCompra);
            em.persist(compra);
            for (DetalleCompra detalleCompraDetalleCompra : compra.getDetalleCompra()) {
                Compra oldCompraOfDetalleCompraDetalleCompra = detalleCompraDetalleCompra.getCompra();
                detalleCompraDetalleCompra.setCompra(compra);
                detalleCompraDetalleCompra = em.merge(detalleCompraDetalleCompra);
                if (oldCompraOfDetalleCompraDetalleCompra != null) {
                    oldCompraOfDetalleCompraDetalleCompra.getDetalleCompra().remove(detalleCompraDetalleCompra);
                    oldCompraOfDetalleCompraDetalleCompra = em.merge(oldCompraOfDetalleCompraDetalleCompra);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Compra compra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compra persistentCompra = em.find(Compra.class, compra.getCodigo());
            List<DetalleCompra> detalleCompraOld = persistentCompra.getDetalleCompra();
            List<DetalleCompra> detalleCompraNew = compra.getDetalleCompra();
            List<DetalleCompra> attachedDetalleCompraNew = new ArrayList<DetalleCompra>();
            for (DetalleCompra detalleCompraNewDetalleCompraToAttach : detalleCompraNew) {
                detalleCompraNewDetalleCompraToAttach = em.getReference(detalleCompraNewDetalleCompraToAttach.getClass(), detalleCompraNewDetalleCompraToAttach.getCodigo());
                attachedDetalleCompraNew.add(detalleCompraNewDetalleCompraToAttach);
            }
            detalleCompraNew = attachedDetalleCompraNew;
            compra.setDetalleCompra(detalleCompraNew);
            compra = em.merge(compra);
            for (DetalleCompra detalleCompraOldDetalleCompra : detalleCompraOld) {
                if (!detalleCompraNew.contains(detalleCompraOldDetalleCompra)) {
                    detalleCompraOldDetalleCompra.setCompra(null);
                    detalleCompraOldDetalleCompra = em.merge(detalleCompraOldDetalleCompra);
                }
            }
            for (DetalleCompra detalleCompraNewDetalleCompra : detalleCompraNew) {
                if (!detalleCompraOld.contains(detalleCompraNewDetalleCompra)) {
                    Compra oldCompraOfDetalleCompraNewDetalleCompra = detalleCompraNewDetalleCompra.getCompra();
                    detalleCompraNewDetalleCompra.setCompra(compra);
                    detalleCompraNewDetalleCompra = em.merge(detalleCompraNewDetalleCompra);
                    if (oldCompraOfDetalleCompraNewDetalleCompra != null && !oldCompraOfDetalleCompraNewDetalleCompra.equals(compra)) {
                        oldCompraOfDetalleCompraNewDetalleCompra.getDetalleCompra().remove(detalleCompraNewDetalleCompra);
                        oldCompraOfDetalleCompraNewDetalleCompra = em.merge(oldCompraOfDetalleCompraNewDetalleCompra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = compra.getCodigo();
                if (findCompra(id) == null) {
                    throw new NonexistentEntityException("The compra with id " + id + " no longer exists.");
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
            Compra compra;
            try {
                compra = em.getReference(Compra.class, id);
                compra.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compra with id " + id + " no longer exists.", enfe);
            }
            List<DetalleCompra> detalleCompra = compra.getDetalleCompra();
            for (DetalleCompra detalleCompraDetalleCompra : detalleCompra) {
                detalleCompraDetalleCompra.setCompra(null);
                detalleCompraDetalleCompra = em.merge(detalleCompraDetalleCompra);
            }
            em.remove(compra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Compra> findCompraEntities() {
        return findCompraEntities(true, -1, -1);
    }

    public List<Compra> findCompraEntities(int maxResults, int firstResult) {
        return findCompraEntities(false, maxResults, firstResult);
    }

    private List<Compra> findCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Compra.class));
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

    public Compra findCompra(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Compra.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Compra> rt = cq.from(Compra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Compra> ListarComprasDescentes() {
        List<Compra> compras = null;
        String sql = "SELECT  Object(u) FROM Compra u ORDER BY u.codigo DESC ";

        Query query = getEntityManager().createQuery(sql);
        compras = query.getResultList();

        return compras;
    }

    public List<Compra> ListarComprasPorFechas(String desde,String hasta) throws Exception{
        List<Compra> compras = null;
        String sql = "SELECT  Object(u) FROM Compra u where u.fecha between '" +desde+ "' and '" +hasta+ "'";
        Query query = getEntityManager().createQuery(sql);
        compras = query.getResultList();
        
        if(compras.isEmpty()){
            throw new Exception("No se encontraron registros");
        }
        return compras;
    }

}
