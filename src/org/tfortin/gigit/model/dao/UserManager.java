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
 * Home object for domain model class User.
 * @see dao.User
 * @author Hibernate Tools
 */
public class UserManager extends HibernateUtil {

	private static final Log log = LogFactory.getLog(UserManager.class);

	public void persist(User transientInstance) {
		log.debug("persisting User instance");
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

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
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

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
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

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			currentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		} finally {
			closeSession();
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) currentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		} finally {
			closeSession();
		}
	}

	public User findById(long id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) currentSession().get(
					User.class, id);
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

	public List findByExample(User instance) {
		log.debug("finding User instance by example");
		try {
			List results = currentSession()
					.createCriteria(User.class).add(Example.create(instance))
					.list();
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

	public boolean exists(User user) {
		return this.exists(user, true);
	}

	public boolean exists(User user, boolean connect) throws HibernateException {
		boolean exists = false;
		Session session = currentSession();
		Query query;
		if(connect) {
			query = session.createQuery("from User u where u.login=:login and u.pwd=:pwd and u.active=:active");
			query.setBoolean("active", true);
			query.setString("pwd", user.getPwd());
		}
		else {
			query = session.createQuery("from User u where u.login=:login");
		}
		query.setString("login", user.getLogin());
		if(query.list().size() > 0) {
			exists = true;
			if(connect) {
				User dbUser = (User)query.list().get(0);
				user.setFirstName(dbUser.getFirstName());
				user.setLastName(dbUser.getLastName());
				user.setId(dbUser.getId());
			}
		}
		closeSession();
		return exists;
	}

	public void register(User user) throws HibernateException {
		Session session = currentSession();
		session.beginTransaction();
		
		session.save(user);

		session.getTransaction().commit();
		closeSession();
	}

	public User findByLogin(String login) {
		log.debug("getting User instance with login: " + login);
		try {
			Query query = currentSession().createQuery("from User u where u.login=:login");
			query.setString("login", login);
			User instance = (User)query.list().get(0);
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

}
