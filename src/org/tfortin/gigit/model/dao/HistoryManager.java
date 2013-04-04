package org.tfortin.gigit.model.dao;

// Generated 2 avr. 2013 21:30:28 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.tfortin.gigit.util.HibernateUtil;

/**
 * Home object for domain model class History.
 * @see dao.History
 * @author Hibernate Tools
 */
public class HistoryManager extends HibernateUtil {

	private static final Log log = LogFactory.getLog(HistoryManager.class);

	public void persist(History transientInstance) {
		log.debug("persisting History instance");
		try {
			currentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		} finally {
			closeSession();
		}
	}

	public void attachDirty(History instance) {
		log.debug("attaching dirty History instance");
		try {
			currentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		} finally {
			closeSession();
		}
	}

	public void attachClean(History instance) {
		log.debug("attaching clean History instance");
		try {
			currentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		} finally {
			closeSession();
		}
	}

	public void delete(History persistentInstance) {
		Session session = currentSession();
		session.beginTransaction();
		log.debug("deleting History instance");
		try {
			session.delete(persistentInstance);
			session.getTransaction().commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		} finally {
			closeSession();
		}
	}

	public History merge(History detachedInstance) {
		Session session = currentSession();
		session.beginTransaction();
		log.debug("merging History instance");
		try {
			History result = (History) session
					.merge(detachedInstance);
			session.getTransaction().commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		} finally {
			closeSession();
		}
	}

	public History findById(long id) {
		Session session = currentSession();
		log.debug("getting History instance with id: " + id);
		try {
			History instance = (History) session
					.get(History.class, id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} finally {
			closeSession();
		}
	}

	public List findByExample(History instance) {
		Session session = currentSession();
		log.debug("finding History instance by example");
		try {
			List results = session
					.createCriteria(History.class)
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		} finally {
			closeSession();
		}
	}

	public List<History> findByUserId(long userId) {
		Session session = currentSession();
		log.debug("getting History instance with user id: " + userId);
		try {
			Query query = session.createQuery("from History h where h.user=:userId");
			query.setLong("userId", userId);
			return (List<History>)query.list();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} finally {
			closeSession();
		}
	}

	public void save(History history) throws HibernateException {
		Session session = currentSession();
		session.beginTransaction();
		
		session.save(history);

		session.getTransaction().commit();
		closeSession();
	}

}
