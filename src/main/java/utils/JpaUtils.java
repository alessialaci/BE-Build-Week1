package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JpaUtils {

	protected static final Logger logger = LoggerFactory.getLogger(JpaUtils.class);
	private static final String persistenceUnit = "Build-Week1";
	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
	protected static final EntityManager em = emf.createEntityManager();
	protected static final EntityTransaction t = em.getTransaction();
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory(persistenceUnit);
		} catch (Throwable ex) {
			logger.error("Initial EntityManagerFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}
	
}