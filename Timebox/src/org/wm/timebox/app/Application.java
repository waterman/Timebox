package org.wm.timebox.app;

import java.util.List;

import org.wm.timebox.app.bo.Activity;
import org.wm.timebox.app.model.TimeboxModel;
import org.wm.timebox.app.screen.timeline.TimelineScreen;
import org.wm.timebox.app.ui.world.World;
import org.wm.timebox.service.ActivityQuery;
import org.wm.timebox.service.TimeboxDAOService;

public class Application {

	private World world;
	private TimelineScreen timelineScreen;
	private TimeboxModel timeboxModel;
	private TimeboxDAOService timeboxService;

	public void setTimelineScreen(TimelineScreen aTimelineScreen) {
		timelineScreen = aTimelineScreen;
	}

	public TimelineScreen getTimelineScreen() {
		return timelineScreen;
	}

	public void setWorld(World aWorld) {
		world = aWorld;
	}

	public World getWorld() {
		return world;
	}

	public void init() {
		world.setScreenAt(timelineScreen, 1, 1);
		List<Activity> tempActivities = timeboxService.getActivities(new ActivityQuery());
		timeboxModel.initWith(tempActivities);
	}

	public TimeboxModel getTimeboxModel() {
		return timeboxModel;
	}

	public void setTimeboxModel(TimeboxModel aTimeboxModel) {
		timeboxModel = aTimeboxModel;
	}

	public TimeboxDAOService getTimeboxService() {
		return timeboxService;
	}

	public void setTimeboxService(TimeboxDAOService aTimeboxService) {
		timeboxService = aTimeboxService;
	}
}
