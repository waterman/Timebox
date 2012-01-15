package org.wm.timebox.app.screen.mvc;

import org.wm.timebox.app.model.TimeboxModel;

public class Controller<T> {

	private T model;
	private TimeboxModel timeboxModel;

	public void setModel(T aModel) {
		model = aModel;
	}

	public T getModel() {
		return model;
	}
	
	public void init(){}

	public TimeboxModel getTimeboxModel() {
		return timeboxModel;
	}

	public void setTimeboxModel(TimeboxModel aTimeboxModel) {
		timeboxModel = aTimeboxModel;
	}
}
