package org.wm.timebox.app.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TimelineItemVO {

	private StringProperty start = new SimpleStringProperty();
	private StringProperty end = new SimpleStringProperty();

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
