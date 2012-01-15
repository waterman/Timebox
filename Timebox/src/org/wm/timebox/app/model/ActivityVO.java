package org.wm.timebox.app.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import org.wm.timebox.app.bo.Activity;

public class ActivityVO {

	private StringProperty start = new SimpleStringProperty();
	private StringProperty end = new SimpleStringProperty();

	public ActivityVO() {
		super();
	}

	public ActivityVO(Activity aActivity) {
		setStart(aActivity.getStart() == null ? null : aActivity.getStart().toString());
		setEnd(aActivity.getEnd() == null ? null : aActivity.getEnd().toString());
	}

	public String getStart() {
		return start.get();
	}

	public void setStart(String aStart) {
		start.set(aStart);
	}

	public String getEnd() {
		return end.get();
	}

	public void setEnd(String aEnd) {
		end.set(aEnd);
	}

	public StringProperty startProperty() {
		return start;
	}

	public StringProperty endProperty() {
		return end;
	}

}
