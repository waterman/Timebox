/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wm.timebox.app;

import java.util.logging.LogManager;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.wm.timebox.app.ui.world.Screen;
import org.wm.timebox.app.ui.world.World;

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
		LogManager.getLogManager().getLogger("bla");
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		final World tempWorld = initDI();
		double tempSceneWidth = 800;
		double tempSceneHeight = 600;

		// primaryStage.initStyle(StageStyle.TRANSPARENT);

		createContent(tempWorld);

		FlowPane tempButtonPane = new FlowPane(10, 10);

		Button tempButton = new Button("One");
		tempButtonPane.getChildren().add(tempButton);
		tempButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				tempWorld.displayScreen(0);

			}
		});
		tempButton = new Button("Two");
		tempButtonPane.getChildren().add(tempButton);
		tempButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				tempWorld.displayScreen(1);

			}
		});
		tempButton = new Button("Nine");
		tempButtonPane.getChildren().add(tempButton);
		tempButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				tempWorld.displayScreen(8);

			}
		});
		tempButton = new Button("Overview");
		tempButtonPane.getChildren().add(tempButton);
		tempButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				tempWorld.displayOverview();

			}
		});

		tempButton = new Button("Fade Out");
		tempButtonPane.getChildren().add(tempButton);
		tempButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				tempWorld.fadeRowIdx(0, true);

			}
		});

		tempButton = new Button("Fade In");
		tempButtonPane.getChildren().add(tempButton);
		tempButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				tempWorld.fadeRowIdx(0, false);

			}
		});

		// tempButton = new Button("Rotate Out");
		// tempButtonPane.getChildren().add(tempButton);
		// tempButton.setOnAction(new EventHandler<ActionEvent>() {
		//
		// @Override
		// public void handle(ActionEvent event) {
		// tempWorld.rotateRowIdx(0,true);
		//
		// }
		// });
		//
		// tempButton = new Button("Rotate In");
		// tempButtonPane.getChildren().add(tempButton);
		// tempButton.setOnAction(new EventHandler<ActionEvent>() {
		//
		// @Override
		// public void handle(ActionEvent event) {
		// tempWorld.rotateRowIdx(0, false);
		//
		// }
		// });

		Group tempRoot = new Group();
		tempWorld.installOn(tempRoot);
		tempRoot.getChildren().add(tempButtonPane);
		tempButtonPane.setTranslateY(tempSceneHeight - 30);

		// primaryStage.setResizable(false);
		Scene tempScene = new Scene(tempRoot, tempSceneWidth, tempSceneHeight, true);
		tempScene.setFill(Color.TRANSPARENT);
		primaryStage.setScene(tempScene);
		PerspectiveCamera tempCam = new PerspectiveCamera();
		primaryStage.getScene().setCamera(tempCam);

		primaryStage.show();

	}

	private World initDI() {
		ApplicationContext tempContext = new ClassPathXmlApplicationContext(new String[] { "spring.xml" });
		World tempBean = (World) tempContext.getBean("world");
		return tempBean;
	}

	public World createContent(World aWorld) {

		Screen tempScreen = new Screen();
		tempScreen.setStyle("-fx-background-color: red;");
		aWorld.setScreenAt(tempScreen, 0, 0);

		tempScreen = new Screen();
		tempScreen.setStyle("-fx-background-color: green;");
		aWorld.setScreenAt(tempScreen, 0, 1);

		tempScreen = new Screen();
		tempScreen.setStyle("-fx-background-color: blue;");
		aWorld.setScreenAt(tempScreen, 0, 2);

		tempScreen = new Screen();
		tempScreen.setStyle("-fx-background-color: yellow;");
		aWorld.setScreenAt(tempScreen, 1, 0);


		tempScreen = new Screen();
		tempScreen.setStyle("-fx-background-color: orange;");
		aWorld.setScreenAt(tempScreen, 1, 2);

		tempScreen = new Screen();
		tempScreen.setStyle("-fx-background-color: white;");
		aWorld.setScreenAt(tempScreen, 2, 0);

		tempScreen = new Screen();
		tempScreen.setStyle("-fx-background-color: pink;");
		aWorld.setScreenAt(tempScreen, 2, 1);

		tempScreen = new Screen();
		tempScreen.setStyle("-fx-background-color: black;");
		aWorld.setScreenAt(tempScreen, 2, 2);

		return aWorld;
	}
}
