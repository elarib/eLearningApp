package dao;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import exception.AuthenticationException;

/**
 * 
 *
 * @param <T>
 *            the entity type
 * @param <K>
 *            the key type
 */

public abstract class GenericDAO<T extends Serializable, K extends Serializable> implements DaoInterface<T, K> {

	protected Class<T> entityType;
	protected Class<K> idType;

	public GenericDAO(Class<T> entityType, Class<K> idType) {
		this.entityType = entityType;
		this.idType = idType;
	}

	/**
	 * 
	 * @return a new entity manager; shall be implemented in children
	 */
	protected abstract EntityManager getEntityManager();

	public T create(T obj) throws Exception {
		final EntityManager em = getEntityManager();
		final EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(obj);
			em.flush();
			em.refresh(obj);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();

			throw new AuthenticationException(ex.getMessage());
		} finally {
			em.close();
		}
		return obj;
	}

	public T edit(T obj) {
		final EntityManager em = getEntityManager();
		em.getTransaction().begin();
		try {
			em.merge(obj);
			em.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return obj;
	}

	public T remove(K key) {
		final EntityManager em = getEntityManager();
		em.getTransaction().begin();
		T found = null;
		try {
			found = em.find(entityType, key);
			if (found == null)
				throw new RuntimeException("No data found for the current key : " + key);
			else
				em.remove(found);
			em.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return found;
	}

	public T findById(K key) {
		final EntityManager em = getEntityManager();
		em.getTransaction().begin();
		T found = null;
		try {
			found = em.find(entityType, key);
			if (found == null)
				throw new RuntimeException("No data found for the current key : " + key);
			em.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return found;
	}

	@SuppressWarnings("unchecked")

	public Collection<T> findAll() {
		final EntityManager em = getEntityManager();
		try {
			CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(entityType);
			cq.select(cq.from(entityType));
			Query q = em.createQuery(cq);
			return (Collection<T>) q.getResultList();
		} finally {
			em.close();
		}
	}

	public Long count() {
		final EntityManager em = getEntityManager();
		try {
			CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
			Root<T> rt = cq.from(entityType);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).longValue();
		} finally {
			em.close();
		}
	}

}