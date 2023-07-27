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
import modelo.ServicioIncluido;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class ServicioIncluidoJpaController implements Serializable {

    public ServicioIncluidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServicioIncluido servicioIncluido) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(servicioIncluido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServicioIncluido servicioIncluido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            servicioIncluido = em.merge(servicioIncluido);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = servicioIncluido.getCodigo();
                if (findServicioIncluido(id) == null) {
                    throw new NonexistentEntityException("The servicioIncluido with id " + id + " no longer exists.");
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
            ServicioIncluido servicioIncluido;
            try {
                servicioIncluido = em.getReference(ServicioIncluido.class, id);
                servicioIncluido.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicioIncluido with id " + id + " no longer exists.", enfe);
            }
            em.remove(servicioIncluido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServicioIncluido> findServicioIncluidoEntities() {
        return findServicioIncluidoEntities(true, -1, -1);
    }

    public List<ServicioIncluido> findServicioIncluidoEntities(int maxResults, int firstResult) {
        return findServicioIncluidoEntities(false, maxResults, firstResult);
    }

    private List<ServicioIncluido> findServicioIncluidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServicioIncluido.class));
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

    public ServicioIncluido findServicioIncluido(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServicioIncluido.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioIncluidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServicioIncluido> rt = cq.from(ServicioIncluido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
