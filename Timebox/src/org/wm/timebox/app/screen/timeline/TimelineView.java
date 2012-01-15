package org.wm.timebox.app.screen.timeline;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import org.wm.timebox.app.model.ActivityVO;
import org.wm.timebox.app.screen.mvc.View;

public class TimelineView extends View<ObservableList<ActivityVO>> {

	private TableView<ActivityVO> cTimelineTable = new TableView<ActivityVO>();

	public TimelineView() {
		super();
	}

	private void initControls() {
		cTimelineTable.setItems(getController().getModel());

		TableColumn<ActivityVO, String> tempColumn = new TableColumn<ActivityVO, String>("Start");
		tempColumn.setCellValueFactory(new PropertyValueFactory<ActivityVO, String>("start"));
		cTimelineTable.getColumns().add(tempColumn);

		tempColumn = new TableColumn<ActivityVO, String>("End");
		tempColumn.setCellValueFactory(new PropertyValueFactory<ActivityVO, String>("end"));
		cTimelineTable.getColumns().add(tempColumn);

		setCenter(cTimelineTable);
	}

	@Override
	public void init() {
		super.init();
		initControls();
	}
}
