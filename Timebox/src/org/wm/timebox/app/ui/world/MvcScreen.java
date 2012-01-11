package org.wm.timebox.app.ui.world;

import org.wm.timebox.app.screen.mvc.Activity;

public class MvcScreen extends Screen {

	private Activity activity;

	public void setActivity(Activity aActivity) {
		activity = aActivity;
	}

	public Activity getActivity() {
		return activity;
	}

	public void init() {
		setCenter(getActivity().getView());
	}

}
