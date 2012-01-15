package org.wm.timebox.app.bo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import junit.framework.Assert;

import org.junit.Test;
import org.wm.timebox.test.AbstractPersistenceTest;
import org.wm.timebox.test.DataUtil;

public class ProjectTest extends AbstractPersistenceTest {

	@Test
	public void testReadProjects() throws Exception {
		EntityManager tempEntityManager = em;

		TypedQuery<Project> tempQuery = tempEntityManager.createQuery("select p from Project p ", Project.class);
		Assert.assertNotNull(tempQuery.getResultList());

		Project tempProject = new Project();
		tempProject.setName("Timebox");

		tempEntityManager.getTransaction().begin();
		tempEntityManager.persist(tempProject);
		tempEntityManager.getTransaction().commit();

		DataUtil.getAny(tempEntityManager, Project.class, true);
	}
}
