package org.wm.timebox.app.screen.timeline;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.wm.timebox.app.model.TimelineItemVO;
import org.wm.timebox.app.screen.mvc.Controller;
import org.wm.timebox.app.screen.mvc.DataEntryContext;

public class TimelineController extends Controller<DataEntryContext<ObservableList<TimelineItemVO>>> {

	public TimelineController() {
		super();
	}

	@Override
	public void init() {
		super.init();
		ObservableList<TimelineItemVO> tempList = FXCollections.observableList(new ArrayList<TimelineItemVO>());
		for (int i = 0; i < 100; i++) {
			TimelineItemVO tempItem = new TimelineItemVO();
			tempItem.setStart("Start "+i);
			tempItem.setEnd("End "+i);
			tempList.add(tempItem);
			
		}
		setModel(new DataEntryContext<ObservableList<TimelineItemVO>>(tempList));
	}
}
