package org.wm.timebox.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

public class AbstractPersistenceTest extends AbstractDITest {

	protected EntityManager em;

	@BeforeClass
	public static void initORM() {
	}

	@Before
	public void initEntityManager() {
		EntityManagerFactory tempFactory = getBean("entityManagerFactory");
		em = tempFactory.createEntityManager();
	}

	@After
	public void closeEntityManager() {
		if (em != null && em.isOpen()) {
			em.close();
		}
	}

}
