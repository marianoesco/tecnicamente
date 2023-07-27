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
import modelo.NivelDeUsuario;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author USUARI
 */
public class NivelDeUsuarioJpaController implements Serializable {

    public NivelDeUsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NivelDeUsuario nivelDeUsuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(nivelDeUsuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NivelDeUsuario nivelDeUsuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            nivelDeUsuario = em.merge(nivelDeUsuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = nivelDeUsuario.getCodigo();
                if (findNivelDeUsuario(id) == null) {
                    throw new NonexistentEntityException("The nivelDeUsuario with id " + id + " no longer exists.");
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
            NivelDeUsuario nivelDeUsuario;
            try {
                nivelDeUsuario = em.getReference(NivelDeUsuario.class, id);
                nivelDeUsuario.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nivelDeUsuario with id " + id + " no longer exists.", enfe);
            }
            em.remove(nivelDeUsuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NivelDeUsuario> findNivelDeUsuarioEntities() {
        return findNivelDeUsuarioEntities(true, -1, -1);
    }

    public List<NivelDeUsuario> findNivelDeUsuarioEntities(int maxResults, int firstResult) {
        return findNivelDeUsuarioEntities(false, maxResults, firstResult);
    }

    private List<NivelDeUsuario> findNivelDeUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NivelDeUsuario.class));
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

    public NivelDeUsuario findNivelDeUsuario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NivelDeUsuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getNivelDeUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NivelDeUsuario> rt = cq.from(NivelDeUsuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
      public List<NivelDeUsuario> FiltrarPorNombreNivel(String nombre) {
        List<NivelDeUsuario> nivel = null;
        String jpql = "SELECT Object(u) FROM NivelDeUsuario u WHERE u.nombre LIKE '%"+nombre+"%'";
        Query query = getEntityManager().createQuery(jpql); 
        nivel = query.getResultList();

        return nivel;
    }
}
