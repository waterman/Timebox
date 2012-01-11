package org.wm.timebox.app.ui.world;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import org.wm.timebox.app.di.EventSupport;

/**
 * The typcial header of a {@link Screen}.
 * 
 * @author Andi
 * 
 */
public class HeaderPanel extends BorderPane {

	private Button cOverviewButton = new Button();
	private Label cTitleLabel = new Label();

	private EventSupport<ShowOverviewEvent> eventSupport;

	public HeaderPanel() {
		init();
	}

	private void init() {
		cOverviewButton.setText("Overview");
		cTitleLabel.setText("Title");
		setLeft(cTitleLabel);
		setRight(cOverviewButton);

		cOverviewButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent aEvent) {
				performDisplayOverview();
			}
		});
	}

	protected void performDisplayOverview() {
		eventSupport.fire(new ShowOverviewEvent());
	}

	public void setEventSupport(EventSupport<ShowOverviewEvent> aEventSupport) {
		eventSupport = aEventSupport;
	}

	public EventSupport<ShowOverviewEvent> getEventSupport() {
		return eventSupport;
	}

	public void setTitle(String aTitle) {
		cTitleLabel.setText(aTitle);
	}
}
