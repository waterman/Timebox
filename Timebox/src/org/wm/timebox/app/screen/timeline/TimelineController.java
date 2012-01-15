package org.wm.timebox.app.screen.timeline;

import javafx.collections.ObservableList;

import org.wm.timebox.app.model.ActivityVO;
import org.wm.timebox.app.screen.mvc.Controller;

public class TimelineController extends Controller<ObservableList<ActivityVO>> {

	
	public TimelineController() {
		super();
	}

	@Override
	public void init() {
		super.init();
		setModel(getTimeboxModel().getActivities());
	}
}
