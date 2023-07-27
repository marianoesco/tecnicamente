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
import modelo.Pago;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.TipoPago;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class TipoPagoJpaController implements Serializable {

    public TipoPagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoPago tipoPago) {
        if (tipoPago.getPago() == null) {
            tipoPago.setPago(new ArrayList<Pago>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pago> attachedPago = new ArrayList<Pago>();
            for (Pago pagoPagoToAttach : tipoPago.getPago()) {
                pagoPagoToAttach = em.getReference(pagoPagoToAttach.getClass(), pagoPagoToAttach.getCodigo());
                attachedPago.add(pagoPagoToAttach);
            }
            tipoPago.setPago(attachedPago);
            em.persist(tipoPago);
            for (Pago pagoPago : tipoPago.getPago()) {
                TipoPago oldTipopagoOfPagoPago = pagoPago.getTipopago();
                pagoPago.setTipopago(tipoPago);
                pagoPago = em.merge(pagoPago);
                if (oldTipopagoOfPagoPago != null) {
                    oldTipopagoOfPagoPago.getPago().remove(pagoPago);
                    oldTipopagoOfPagoPago = em.merge(oldTipopagoOfPagoPago);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoPago tipoPago) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoPago persistentTipoPago = em.find(TipoPago.class, tipoPago.getCodigo());
            List<Pago> pagoOld = persistentTipoPago.getPago();
            List<Pago> pagoNew = tipoPago.getPago();
            List<Pago> attachedPagoNew = new ArrayList<Pago>();
            for (Pago pagoNewPagoToAttach : pagoNew) {
                pagoNewPagoToAttach = em.getReference(pagoNewPagoToAttach.getClass(), pagoNewPagoToAttach.getCodigo());
                attachedPagoNew.add(pagoNewPagoToAttach);
            }
            pagoNew = attachedPagoNew;
            tipoPago.setPago(pagoNew);
            tipoPago = em.merge(tipoPago);
            for (Pago pagoOldPago : pagoOld) {
                if (!pagoNew.contains(pagoOldPago)) {
                    pagoOldPago.setTipopago(null);
                    pagoOldPago = em.merge(pagoOldPago);
                }
            }
            for (Pago pagoNewPago : pagoNew) {
                if (!pagoOld.contains(pagoNewPago)) {
                    TipoPago oldTipopagoOfPagoNewPago = pagoNewPago.getTipopago();
                    pagoNewPago.setTipopago(tipoPago);
                    pagoNewPago = em.merge(pagoNewPago);
                    if (oldTipopagoOfPagoNewPago != null && !oldTipopagoOfPagoNewPago.equals(tipoPago)) {
                        oldTipopagoOfPagoNewPago.getPago().remove(pagoNewPago);
                        oldTipopagoOfPagoNewPago = em.merge(oldTipopagoOfPagoNewPago);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = tipoPago.getCodigo();
                if (findTipoPago(id) == null) {
                    throw new NonexistentEntityException("The tipoPago with id " + id + " no longer exists.");
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
            TipoPago tipoPago;
            try {
                tipoPago = em.getReference(TipoPago.class, id);
                tipoPago.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoPago with id " + id + " no longer exists.", enfe);
            }
            List<Pago> pago = tipoPago.getPago();
            for (Pago pagoPago : pago) {
                pagoPago.setTipopago(null);
                pagoPago = em.merge(pagoPago);
            }
            em.remove(tipoPago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoPago> findTipoPagoEntities() {
        return findTipoPagoEntities(true, -1, -1);
    }

    public List<TipoPago> findTipoPagoEntities(int maxResults, int firstResult) {
        return findTipoPagoEntities(false, maxResults, firstResult);
    }

    private List<TipoPago> findTipoPagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoPago.class));
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

    public TipoPago findTipoPago(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoPago.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoPagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoPago> rt = cq.from(TipoPago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
