package org.tfortin.gigit.model.dao;

// Generated 4 avr. 2013 02:35:38 by Hibernate Tools 3.4.0.CR1

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
 * Home object for domain model class Bookmark.
 * @see dao.Bookmark
 * @author Hibernate Tools
 */
public class BookmarkManager extends HibernateUtil {

	private static final Log log = LogFactory.getLog(BookmarkManager.class);

	public void persist(Bookmark transientInstance) {
		log.debug("persisting Bookmark instance");
		try {
			currentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Bookmark instance) {
		log.debug("attaching dirty Bookmark instance");
		try {
			currentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Bookmark instance) {
		log.debug("attaching clean Bookmark instance");
		try {
			currentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Bookmark persistentInstance) {
		log.debug("deleting Bookmark instance");
		try {
			currentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Bookmark merge(Bookmark detachedInstance) {
		log.debug("merging Bookmark instance");
		try {
			Bookmark result = (Bookmark) currentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Bookmark findById(long id) {
		log.debug("getting Bookmark instance with id: " + id);
		try {
			Bookmark instance = (Bookmark) currentSession()
					.get("dao.Bookmark", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Bookmark instance) {
		log.debug("finding Bookmark instance by example");
		try {
			List results = currentSession()
					.createCriteria("dao.Bookmark")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Bookmark> findByUserId(long userId) {
		Session session = currentSession();
		log.debug("getting History instance with user id: " + userId);
		try {
			Query query = session.createQuery("from Bookmark b where b.user=:userId");
			query.setLong("userId", userId);
			return (List<Bookmark>)query.list();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} finally {
			closeSession();
		}
	}

	public boolean existsForUserAndProject(long userId, String project) {
		Session session = currentSession();
		log.debug("getting History instance with project: " + project);
		try {
			Query query = session.createQuery("from Bookmark b where b.project=:project and b.user=:userId");
			query.setString("project", project);
			query.setLong("userId", userId);
			return query.list().size() > 0;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} finally {
			closeSession();
		}
	}

	public void save(Bookmark bookmark) throws HibernateException {
		Session session = currentSession();
		session.beginTransaction();
		
		session.save(bookmark);

		session.getTransaction().commit();
		closeSession();
	}

}
