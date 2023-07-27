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
import modelo.Equipo;
import modelo.TipoEquipo;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class EquipoJpaController implements Serializable {

    public EquipoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Equipo equipo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoEquipo tipoEquipo = equipo.getTipoEquipo();
            if (tipoEquipo != null) {
                tipoEquipo = em.getReference(tipoEquipo.getClass(), tipoEquipo.getCodigo());
                equipo.setTipoEquipo(tipoEquipo);
            }
            em.persist(equipo);
            if (tipoEquipo != null) {
                tipoEquipo.getEquipos().add(equipo);
                tipoEquipo = em.merge(tipoEquipo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Equipo equipo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipo persistentEquipo = em.find(Equipo.class, equipo.getCodigo());
            TipoEquipo tipoEquipoOld = persistentEquipo.getTipoEquipo();
            TipoEquipo tipoEquipoNew = equipo.getTipoEquipo();
            if (tipoEquipoNew != null) {
                tipoEquipoNew = em.getReference(tipoEquipoNew.getClass(), tipoEquipoNew.getCodigo());
                equipo.setTipoEquipo(tipoEquipoNew);
            }
            equipo = em.merge(equipo);
            if (tipoEquipoOld != null && !tipoEquipoOld.equals(tipoEquipoNew)) {
                tipoEquipoOld.getEquipos().remove(equipo);
                tipoEquipoOld = em.merge(tipoEquipoOld);
            }
            if (tipoEquipoNew != null && !tipoEquipoNew.equals(tipoEquipoOld)) {
                tipoEquipoNew.getEquipos().add(equipo);
                tipoEquipoNew = em.merge(tipoEquipoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = equipo.getCodigo();
                if (findEquipo(id) == null) {
                    throw new NonexistentEntityException("The equipo with id " + id + " no longer exists.");
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
            Equipo equipo;
            try {
                equipo = em.getReference(Equipo.class, id);
                equipo.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The equipo with id " + id + " no longer exists.", enfe);
            }
            TipoEquipo tipoEquipo = equipo.getTipoEquipo();
            if (tipoEquipo != null) {
                tipoEquipo.getEquipos().remove(equipo);
                tipoEquipo = em.merge(tipoEquipo);
            }
            em.remove(equipo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Equipo> findEquipoEntities() {
        return findEquipoEntities(true, -1, -1);
    }

    public List<Equipo> findEquipoEntities(int maxResults, int firstResult) {
        return findEquipoEntities(false, maxResults, firstResult);
    }

    private List<Equipo> findEquipoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Equipo.class));
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

    public Equipo findEquipo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Equipo.class, id);
        } finally {
            em.close();
        }
    }

    public int getEquipoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Equipo> rt = cq.from(Equipo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Equipo> FiltrarEquipoPorTodo(String nombre,TipoEquipo tipoEquipo ) {
        List<Equipo> equipo = null;
        String jpql = "SELECT Object(u) FROM Equipo u WHERE u.modelo LIKE '%"+ nombre +"%' AND u.tipoEquipo.codigo= '"+tipoEquipo.getCodigo()+ "'";
        Query query = getEntityManager().createQuery(jpql);
        equipo = query.getResultList();
        return equipo;
    }
    
     public List<Equipo> FiltrarEquipoPorNombre(String nombre) {
        List<Equipo> equipo = null;
        String jpql = "SELECT Object(u) FROM Equipo u WHERE u.modelo LIKE '%" + nombre + "%'";
        Query query = getEntityManager().createQuery(jpql);
        equipo = query.getResultList();
        return equipo;
    }
     
     public List<Equipo> BuscarEquipoPorTipo(TipoEquipo tipoEquipo ) {
        List<Equipo> equipo = null;
        String jpql = "SELECT Object(u) FROM Equipo u WHERE u.tipoEquipo.codigo= '"+tipoEquipo.getCodigo()+  "'";
        Query query = getEntityManager().createQuery(jpql);
        equipo = query.getResultList();
        return equipo;
    }
     
    
    
}
