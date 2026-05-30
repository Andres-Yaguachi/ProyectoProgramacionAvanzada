package LogicaControllers;

import Clases.AsignaPaquete;
import Clases.Paquete;
import Clases.Repartidor;
import LogicaControllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class AsignaPaqueteJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public AsignaPaqueteJpaController() {
        this.emf = Persistence.createEntityManagerFactory("GestionPaquetesPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignaPaquete asignaPaquete) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Paquete paquete = asignaPaquete.getPaquete();
            if (paquete != null) {
                paquete = em.getReference(paquete.getClass(), paquete.getCodigoUnico());
                asignaPaquete.setPaquete(paquete);
            }

            Repartidor repartidor = asignaPaquete.getRepartidor();
            if (repartidor != null) {
                repartidor = em.getReference(repartidor.getClass(), repartidor.getIdRepartidor());
                asignaPaquete.setRepartidor(repartidor);
            }

            em.persist(asignaPaquete);
            if (paquete != null) {
                paquete.getAsignaPaqueteCollection().add(asignaPaquete);
                paquete = em.merge(paquete);
            }
            if (repartidor != null) {
                repartidor.getAsignaPaqueteCollection().add(asignaPaquete);
                repartidor = em.merge(repartidor);
            }

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignaPaquete asignaPaquete) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            AsignaPaquete persistentAsignaPaquete = em.find(AsignaPaquete.class, asignaPaquete.getIdAsignaPaquete());
            Paquete paqueteOld = persistentAsignaPaquete.getPaquete();
            Paquete paqueteNew = asignaPaquete.getPaquete();
            Repartidor repartidorOld = persistentAsignaPaquete.getRepartidor();
            Repartidor repartidorNew = asignaPaquete.getRepartidor();

            if (paqueteNew != null) {
                paqueteNew = em.getReference(paqueteNew.getClass(), paqueteNew.getCodigoUnico());
                asignaPaquete.setPaquete(paqueteNew);
            }
            if (repartidorNew != null) {
                repartidorNew = em.getReference(repartidorNew.getClass(), repartidorNew.getIdRepartidor());
                asignaPaquete.setRepartidor(repartidorNew);
            }

            asignaPaquete = em.merge(asignaPaquete);

            if (paqueteOld != null && !paqueteOld.equals(paqueteNew)) {
                paqueteOld.getAsignaPaqueteCollection().remove(asignaPaquete);
                paqueteOld = em.merge(paqueteOld);
            }
            if (paqueteNew != null && !paqueteNew.equals(paqueteOld)) {
                paqueteNew.getAsignaPaqueteCollection().add(asignaPaquete);
                paqueteNew = em.merge(paqueteNew);
            }
            if (repartidorOld != null && !repartidorOld.equals(repartidorNew)) {
                repartidorOld.getAsignaPaqueteCollection().remove(asignaPaquete);
                repartidorOld = em.merge(repartidorOld);
            }
            if (repartidorNew != null && !repartidorNew.equals(repartidorOld)) {
                repartidorNew.getAsignaPaqueteCollection().add(asignaPaquete);
                repartidorNew = em.merge(repartidorNew);
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asignaPaquete.getIdAsignaPaquete();
                if (findAsignaPaquete(id) == null) {
                    throw new NonexistentEntityException("The AsignaPaquete with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignaPaquete asignaPaquete;
            try {
                asignaPaquete = em.getReference(AsignaPaquete.class, id);
                asignaPaquete.getIdAsignaPaquete();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The AsignaPaquete with id " + id + " no longer exists.", enfe);
            }

            Paquete paquete = asignaPaquete.getPaquete();
            if (paquete != null) {
                paquete.getAsignaPaqueteCollection().remove(asignaPaquete);
                paquete = em.merge(paquete);
            }
            Repartidor repartidor = asignaPaquete.getRepartidor();
            if (repartidor != null) {
                repartidor.getAsignaPaqueteCollection().remove(asignaPaquete);
                repartidor = em.merge(repartidor);
            }

            em.remove(asignaPaquete);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignaPaquete> findAsignaPaqueteEntities() {
        return findAsignaPaqueteEntities(true, -1, -1);
    }

    public List<AsignaPaquete> findAsignaPaqueteEntities(int maxResults, int firstResult) {
        return findAsignaPaqueteEntities(false, maxResults, firstResult);
    }

    private List<AsignaPaquete> findAsignaPaqueteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AsignaPaquete.class));
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

    public AsignaPaquete findAsignaPaquete(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignaPaquete.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignaPaqueteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AsignaPaquete> rt = cq.from(AsignaPaquete.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
