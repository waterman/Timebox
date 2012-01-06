/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gebit.timebox.app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import de.gebit.timebox.app.ui.Screen;
import de.gebit.timebox.app.ui.World;

/**
 * 
 * @author Andi
 */
public class Timebox extends Application {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {


		BorderPane tempRootPane = new BorderPane();
//		tempRootPane.setStyle("-fx-background-color: green;");
		
		final World tempWorld = createContent();
//		Group tempWorldContainer = new Group();
//		tempWorld.installOn(tempWorldContainer);
//		tempRootPane.setCenter(tempWorldContainer);
//
//		FlowPane tempButtonPane = new FlowPane(10, 10);
//
//		Button tempButton = new Button("One");
//		tempButtonPane.getChildren().add(tempButton);
//		tempButton.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				tempWorld.displayScreen(0);
//
//			}
//		});
//		tempButton = new Button("Overview");
//		tempButtonPane.getChildren().add(tempButton);
//		tempButton.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				tempWorld.displayOverview();
//
//			}
//		});
//
//		tempRootPane.setBottom(tempButtonPane);

//		primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(tempWorld.getNode(), 800, 600, true));
		PerspectiveCamera tempCam = new PerspectiveCamera();
		primaryStage.getScene().setCamera(tempCam);


		primaryStage.show();

	}

	public World createContent() {
		World tempWorld = new World(170, 170);

		Screen tempScreen = new Screen();
		tempScreen.setStyle("-fx-background-color: red;");
		tempWorld.setScreenAt(tempScreen, 0, 0);

		tempScreen = new Screen();
		tempScreen.setStyle("-fx-background-color: green;");
		tempWorld.setScreenAt(tempScreen, 0, 1);

		tempScreen = new Screen();
		tempScreen.setStyle("-fx-background-color: blue;");
		tempWorld.setScreenAt(tempScreen, 0, 2);

		tempScreen = new Screen();
		tempScreen.setStyle("-fx-background-color: yellow;");
		tempWorld.setScreenAt(tempScreen, 1, 0);

		tempScreen = new Screen();
		tempScreen.setStyle("-fx-background-color: grey;");
		tempWorld.setScreenAt(tempScreen, 1, 1);

		tempScreen = new Screen();
		tempScreen.setStyle("-fx-background-color: orange;");
		tempWorld.setScreenAt(tempScreen, 1, 2);

		tempScreen = new Screen();
		tempScreen.setStyle("-fx-background-color: white;");
		tempWorld.setScreenAt(tempScreen, 2, 0);

		tempScreen = new Screen();
		tempScreen.setStyle("-fx-background-color: pink;");
		tempWorld.setScreenAt(tempScreen, 2, 1);

		tempScreen = new Screen();
		tempScreen.setStyle("-fx-background-color: black;");
		tempWorld.setScreenAt(tempScreen, 2, 2);

		return tempWorld;
	}
}
