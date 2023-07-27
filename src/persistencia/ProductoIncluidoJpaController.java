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
import modelo.ProductoIncluido;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class ProductoIncluidoJpaController implements Serializable {

    public ProductoIncluidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductoIncluido productoIncluido) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(productoIncluido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductoIncluido productoIncluido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            productoIncluido = em.merge(productoIncluido);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = productoIncluido.getCodigo();
                if (findProductoIncluido(id) == null) {
                    throw new NonexistentEntityException("The productoIncluido with id " + id + " no longer exists.");
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
            ProductoIncluido productoIncluido;
            try {
                productoIncluido = em.getReference(ProductoIncluido.class, id);
                productoIncluido.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productoIncluido with id " + id + " no longer exists.", enfe);
            }
            em.remove(productoIncluido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductoIncluido> findProductoIncluidoEntities() {
        return findProductoIncluidoEntities(true, -1, -1);
    }

    public List<ProductoIncluido> findProductoIncluidoEntities(int maxResults, int firstResult) {
        return findProductoIncluidoEntities(false, maxResults, firstResult);
    }

    private List<ProductoIncluido> findProductoIncluidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductoIncluido.class));
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

    public ProductoIncluido findProductoIncluido(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductoIncluido.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoIncluidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductoIncluido> rt = cq.from(ProductoIncluido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
