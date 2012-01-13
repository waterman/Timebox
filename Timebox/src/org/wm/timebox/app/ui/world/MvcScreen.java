package org.wm.timebox.app.ui.world;

import org.wm.timebox.app.screen.mvc.ApplicationActivity;

public class MvcScreen extends Screen {

	private ApplicationActivity activity;

	public void setActivity(ApplicationActivity aActivity) {
		activity = aActivity;
	}

	public ApplicationActivity getActivity() {
		return activity;
	}

	public void init() {
		setCenter(getActivity().getView());
	}

}
