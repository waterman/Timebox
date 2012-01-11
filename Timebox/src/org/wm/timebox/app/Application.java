package org.wm.timebox.app;

import org.wm.timebox.app.screen.timeline.TimelineScreen;
import org.wm.timebox.app.ui.world.World;

public class Application {

	private World world;
	private TimelineScreen timelineScreen;

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
	}
}
