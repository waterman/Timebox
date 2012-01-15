package org.wm.timebox.service;

import java.util.List;

import org.wm.timebox.app.bo.Activity;

public interface TimeboxDAOService {

	public List<Activity> getActivities(ActivityQuery aQuery);
}
