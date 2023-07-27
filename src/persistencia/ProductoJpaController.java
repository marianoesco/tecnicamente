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
import modelo.Marca;
import modelo.Producto;
import modelo.TipoProducto;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author USUARI
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Marca marca = producto.getMarca();
            if (marca != null) {
                marca = em.getReference(marca.getClass(), marca.getCodigo());
                producto.setMarca(marca);
            }
            TipoProducto tipoProducto = producto.getTipoProducto();
            if (tipoProducto != null) {
                tipoProducto = em.getReference(tipoProducto.getClass(), tipoProducto.getCodigo());
                producto.setTipoProducto(tipoProducto);
            }
            em.persist(producto);
            if (marca != null) {
                marca.getProducto().add(producto);
                marca = em.merge(marca);
            }
            if (tipoProducto != null) {
                tipoProducto.getProducto().add(producto);
                tipoProducto = em.merge(tipoProducto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getCodigo());
            Marca marcaOld = persistentProducto.getMarca();
            Marca marcaNew = producto.getMarca();
            TipoProducto tipoProductoOld = persistentProducto.getTipoProducto();
            TipoProducto tipoProductoNew = producto.getTipoProducto();
            if (marcaNew != null) {
                marcaNew = em.getReference(marcaNew.getClass(), marcaNew.getCodigo());
                producto.setMarca(marcaNew);
            }
            if (tipoProductoNew != null) {
                tipoProductoNew = em.getReference(tipoProductoNew.getClass(), tipoProductoNew.getCodigo());
                producto.setTipoProducto(tipoProductoNew);
            }
            producto = em.merge(producto);
            if (marcaOld != null && !marcaOld.equals(marcaNew)) {
                marcaOld.getProducto().remove(producto);
                marcaOld = em.merge(marcaOld);
            }
            if (marcaNew != null && !marcaNew.equals(marcaOld)) {
                marcaNew.getProducto().add(producto);
                marcaNew = em.merge(marcaNew);
            }
            if (tipoProductoOld != null && !tipoProductoOld.equals(tipoProductoNew)) {
                tipoProductoOld.getProducto().remove(producto);
                tipoProductoOld = em.merge(tipoProductoOld);
            }
            if (tipoProductoNew != null && !tipoProductoNew.equals(tipoProductoOld)) {
                tipoProductoNew.getProducto().add(producto);
                tipoProductoNew = em.merge(tipoProductoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = producto.getCodigo();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            Marca marca = producto.getMarca();
            if (marca != null) {
                marca.getProducto().remove(producto);
                marca = em.merge(marca);
            }
            TipoProducto tipoProducto = producto.getTipoProducto();
            if (tipoProducto != null) {
                tipoProducto.getProducto().remove(producto);
                tipoProducto = em.merge(tipoProducto);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Producto> FiltrarProductoPorNombre(String nombre) {
        List<Producto> producto = null;
        String jpql = "SELECT Object(u) FROM Producto u WHERE u.nombre LIKE '%" + nombre + "%'";
        Query query = getEntityManager().createQuery(jpql);
        producto = query.getResultList();
        return producto;
    }
    
 
    public List<Producto> FiltrarProductoPorTodo(String nombre,Marca marca,TipoProducto tipoProducto ) {
        List<Producto> producto = null;
        String jpql = "SELECT Object(u) FROM Producto u WHERE u.nombre LIKE '%"+ nombre +"%' AND u.marca.codigo= '"+marca.getCodigo()+ "'AND u.tipoProducto.codigo= '" +tipoProducto.getCodigo() +  "'";
        Query query = getEntityManager().createQuery(jpql);
        producto = query.getResultList();
        return producto;
    }
      public List<Producto> FiltrarProductoPorMarca(Marca marca ) {
        List<Producto> producto = null;
        String jpql = "SELECT Object(u) FROM Producto u WHERE u.marca.codigo= '"+marca.getCodigo()+  "'";
        Query query = getEntityManager().createQuery(jpql);
        producto = query.getResultList();
        return producto;
    }
      
    public List<Producto> FiltrarProductoPorMarcayTipo( Marca marca,TipoProducto tipoProducto ) {
        List<Producto> producto = null;
        String jpql = "SELECT Object(u) FROM Producto u WHERE u.marca.codigo= '"+marca.getCodigo()+ "'AND u.tipoProducto.codigo= '" +tipoProducto.getCodigo()+  "'";
        Query query = getEntityManager().createQuery(jpql);
        producto = query.getResultList();
        return producto;
    }
    
}
