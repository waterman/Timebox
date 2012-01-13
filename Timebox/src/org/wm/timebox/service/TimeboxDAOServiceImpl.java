package org.wm.timebox.service;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TimeboxDAOServiceImpl implements TimeboxDAOService {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory aSessionFactory) {
		sessionFactory = aSessionFactory;
	}
}
