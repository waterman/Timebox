package org.wm.timebox.service;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TimelineDAOServiceImpl implements TimelineDAOService {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory aSessionFactory) {
		sessionFactory = aSessionFactory;
	}
}
