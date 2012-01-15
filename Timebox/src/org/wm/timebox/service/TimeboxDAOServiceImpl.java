package org.wm.timebox.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.wm.timebox.app.bo.Activity;

@Repository
public class TimeboxDAOServiceImpl implements TimeboxDAOService {

	@PersistenceContext
	private EntityManager em;

	public TimeboxDAOServiceImpl() {
		super();
	}

	@Override
	public List<Activity> getActivities(ActivityQuery aQuery) {
		TypedQuery<Activity> tempQuery = em.createQuery("select a from Activity a", Activity.class);
		List<Activity> tempActivities = tempQuery.getResultList();
		return tempActivities;
	}
}
