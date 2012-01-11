package org.wm.timebox.app.screen.mvc;

public class DataEntryContext<T> {

	private T model;

	public DataEntryContext(T aModel) {
		model = aModel;
	}

	public T getModel() {
		return model;
	}

	public void setModel(T aModel) {
		model = aModel;
	};
}
