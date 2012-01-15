package org.wm.timebox;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.beans.binding.StringBinding;

import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;
import org.wm.timebox.app.bo.Activity;
import org.wm.timebox.app.bo.Project;
import org.wm.timebox.app.bo.Sponsor;
import org.wm.timebox.test.AbstractPersistenceTest;

/**
 * Generates some test data
 * 
 * @author Andi
 * 
 */
public class TestDataGenerator extends AbstractPersistenceTest {

	@Test
	public void generate() throws Exception {
		em.getTransaction().begin();
		List<Project> tempProjects = new ArrayList<Project>();
		tempProjects.add(new Project("ABC"));
		tempProjects.add(new Project("XYZ"));
		tempProjects.add(new Project("SW New"));
		tempProjects.add(new Project("HW 2.0"));
		tempProjects.add(new Project("Green Dot"));
		for (Project tempProject : tempProjects) {
			em.persist(tempProject);
		}

		List<Sponsor> tempSponsors = new ArrayList<Sponsor>();
		tempSponsors.add(new Sponsor("Microsoft"));
		tempSponsors.add(new Sponsor("Oracle"));
		tempSponsors.add(new Sponsor("SAP"));
		tempSponsors.add(new Sponsor("SAP"));
		tempSponsors.add(new Sponsor("Apple"));
		tempSponsors.add(new Sponsor("IBM"));
		for (Sponsor tempSponsor : tempSponsors) {
			em.persist(tempSponsor);
		}

		int tempDays = 100;
		Random tempRandom = new Random(System.currentTimeMillis());
		for (int i = tempDays; i > 0; i--) {
			for (int j = 0; j < 3; j++) {
				LocalDate tempDate = new LocalDate(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * i);
				Activity tempActivity = new Activity();
				tempActivity.setDate(tempDate);
				int tempStart = 7 + tempRandom.nextInt(3);
				int tempHours = tempRandom.nextInt(4);
				int tempEnd = tempStart + tempHours;
				int tempPause = tempRandom.nextInt(30);
				tempActivity.setStart(new LocalTime(tempStart, 0));
				tempActivity.setEnd(new LocalTime(tempEnd, 0));
				tempActivity.setPauseInMinutes(new Duration(tempPause * 1000 * 60));
				tempActivity.setProject(tempProjects.get(tempRandom.nextInt(tempProjects.size())));
				tempActivity.setSponsor(tempSponsors.get(tempRandom.nextInt(tempSponsors.size())));
				tempActivity.setSummary(createRandomString(tempRandom,50));
				em.persist(tempActivity);

				tempActivity = new Activity();
				tempActivity.setDate(tempDate);
				tempStart = tempEnd;
				tempHours = tempRandom.nextInt(4);
				tempPause = tempRandom.nextInt(30);
				tempActivity.setStart(new LocalTime(tempStart, 0));
				tempActivity.setEnd(new LocalTime(tempStart + tempHours, 0));
				tempActivity.setPauseInMinutes(new Duration(tempPause * 1000 * 60));
				tempActivity.setProject(tempProjects.get(tempRandom.nextInt(tempProjects.size())));
				tempActivity.setSponsor(tempSponsors.get(tempRandom.nextInt(tempSponsors.size())));
				tempActivity.setSummary(createRandomString(tempRandom,50));
				em.persist(tempActivity);
				
				tempActivity = new Activity();
				tempActivity.setDate(tempDate);
				tempStart = tempEnd;
				tempHours = tempRandom.nextInt(2);
				tempPause = tempRandom.nextInt(30);
				tempActivity.setStart(new LocalTime(tempStart, 0));
				tempActivity.setEnd(new LocalTime(tempStart + tempHours, 0));
				tempActivity.setPauseInMinutes(new Duration(tempPause * 1000 * 60));
				tempActivity.setProject(tempProjects.get(tempRandom.nextInt(tempProjects.size())));
				tempActivity.setSponsor(tempSponsors.get(tempRandom.nextInt(tempSponsors.size())));
				em.persist(tempActivity);
			}
		}
		em.getTransaction().commit();
	}

	private String createRandomString(Random aRandom, int aI) {
		StringBuilder tempBuilder = new StringBuilder();
		for(int i = 0; i < aI; i++){
			char tempChar = (char)(65+ aRandom.nextInt(25));
			tempBuilder.append(tempChar);
		}
		return tempBuilder.toString();
	}

}
