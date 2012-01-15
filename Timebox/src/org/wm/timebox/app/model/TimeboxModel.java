package org.wm.timebox.app.model;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.wm.timebox.app.bo.Activity;

public class TimeboxModel {

	private ObservableList<MonthVO> months = FXCollections.observableArrayList();
	private ObservableList<WeekVO> weeks = FXCollections.observableArrayList();
	private ObservableList<DayVO> days = FXCollections.observableArrayList();
	private ObservableList<ActivityVO> activities = FXCollections.observableArrayList();

	public void initWith(List<Activity> someActivities) {
		activities.clear();

		List<ActivityVO> tempActivities = new ArrayList<ActivityVO>();
		for (Activity tempActivity : someActivities) {
			ActivityVO tempVO = new ActivityVO(tempActivity);
			tempActivities.add(tempVO);
		}
		activities.addAll(tempActivities);
	}

	public ObservableList<ActivityVO> getActivities() {
		return activities;
	}

}
