package org.wm.timebox.app.bo;

import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;
import org.wm.timebox.test.AbstractPersistenceTest;
import org.wm.timebox.test.DataUtil;

public class ActivityTest extends AbstractPersistenceTest {
	@Test
	public void testCreateActivity() throws Exception {
		em.getTransaction().begin();
		Project tempProject = DataUtil.getAny(em, Project.class, false);
		if (tempProject == null) {
			tempProject = new Project();
			tempProject.setName(getClass().getSimpleName());
			em.persist(tempProject);
		}
		Sponsor tempSponsor = DataUtil.getAny(em, Sponsor.class, false);
		if (tempSponsor == null) {
			tempSponsor = new Sponsor();
			tempSponsor.setName(getClass().getSimpleName());
			em.persist(tempSponsor);
		}
		Activity tempActivity = new Activity();
		tempActivity.setSponsor(tempSponsor);
		tempActivity.setProject(tempProject);
		tempActivity.setSummary(getClass().getName());
		tempActivity.setStart(new LocalTime(System.currentTimeMillis()));
		tempActivity.setEnd(new LocalTime(System.currentTimeMillis()));
		tempActivity.setDate(new LocalDate(System.currentTimeMillis()));
		tempActivity.setPauseInMinutes(new Duration(10*1000));
		em.persist(tempActivity);
		em.getTransaction().commit();
	}
}
