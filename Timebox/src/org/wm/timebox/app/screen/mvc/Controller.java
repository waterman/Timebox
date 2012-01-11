package org.wm.timebox.app.screen.mvc;

public class Controller<T> {

	private T model;

	public void setModel(T aModel) {
		model = aModel;
	}

	public T getModel() {
		return model;
	}
	
	public void init(){};
}
