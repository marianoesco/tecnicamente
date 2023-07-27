/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.EstadoServicio;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author EcobarM
 */
public class EstadoServicioJpaController implements Serializable {

    public EstadoServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstadoServicio estadoServicio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estadoServicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstadoServicio estadoServicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            estadoServicio = em.merge(estadoServicio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = estadoServicio.getCodigo();
                if (findEstadoServicio(id) == null) {
                    throw new NonexistentEntityException("The estadoServicio with id " + id + " no longer exists.");
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
            EstadoServicio estadoServicio;
            try {
                estadoServicio = em.getReference(EstadoServicio.class, id);
                estadoServicio.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoServicio with id " + id + " no longer exists.", enfe);
            }
            em.remove(estadoServicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstadoServicio> findEstadoServicioEntities() {
        return findEstadoServicioEntities(true, -1, -1);
    }

    public List<EstadoServicio> findEstadoServicioEntities(int maxResults, int firstResult) {
        return findEstadoServicioEntities(false, maxResults, firstResult);
    }

    private List<EstadoServicio> findEstadoServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadoServicio.class));
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

    public EstadoServicio findEstadoServicio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadoServicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstadoServicio> rt = cq.from(EstadoServicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
