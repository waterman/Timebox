package org.wm.timebox.app.screen.mvc;

public class Activity<T> {

	private Controller<T> controller;
	private View<T> view;

	public void init() {
		view.setController(controller);
		controller.init();
		view.init();
	}

	public View<T> getView() {
		return view;
	}

	public void setView(View<T> aView) {
		view = aView;
	}

	public Controller<T> getController() {
		return controller;
	}

	public void setController(Controller<T> aController) {
		controller = aController;
	}

}
