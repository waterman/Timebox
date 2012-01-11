package org.wm.timebox.app.screen.mvc;

import javafx.scene.layout.BorderPane;

import org.wm.timebox.app.ui.world.HeaderPanel;

public class View<T> extends BorderPane {

	private Controller<T> controller;

	private HeaderPanel cHeaderPanel;

	public void init() {
		setTop(cHeaderPanel);
		cHeaderPanel.setTitle("Timeline");
	}

	public void setController(Controller<T> aController) {
		controller = aController;
	}

	public Controller<T> getController() {
		return controller;
	}

	public HeaderPanel getHeaderPanel() {
		return cHeaderPanel;
	}

	public void setHeaderPanel(HeaderPanel aHeaderPanel) {
		cHeaderPanel = aHeaderPanel;
	}

}
