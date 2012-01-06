/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gebit.timebox.app.ui;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author Andi
 */
public class Screen extends BorderPane {

	public Screen() {
		setStyle("-fx-background-color: red;");

		Rectangle tempRect = new Rectangle(10, 10, Color.YELLOW);
		setCenter(tempRect);
		setPadding(new Insets(3));
	}
}
