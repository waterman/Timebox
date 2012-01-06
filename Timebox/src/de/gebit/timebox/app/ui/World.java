/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gebit.timebox.app.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransitionBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * 
 * @author Andi
 */
public class World {
	private class ScreenCell {

		private Screen screen;
		private int rowIdx;
		private int columnIdx;
		private Node node;
		private Pane pane;

		public ScreenCell(Screen aScreen, int aRow, int aColumn) {
			screen = aScreen;
			columnIdx = aColumn;
			rowIdx = aRow;
			BorderPane tempPane = new BorderPane();
			tempPane.setCenter(aScreen);
			pane = tempPane;
			if (isFirstInRow() || isLastInRow()) {
				Group tempGroup = new Group();
				tempGroup.getChildren().add(tempPane);
				node = tempGroup;
			} else {
				node = tempPane;
			}
		}

		public boolean isFirstInRow() {
			return columnIdx == 0;
		}

		public boolean isLastInRow() {
			return columnIdx == maxColumnCount;
		}

		public void setupBounds() {
			Point2D tempOrigin = calculateOrigin();
			pane.relocate(tempOrigin.getX(), tempOrigin.getY());
			pane.setPrefHeight(width);
			pane.setPrefWidth(height);
		}

		public boolean matches(int aColumnIdx, int aRowIdx) {
			return rowIdx == aRowIdx && columnIdx == aColumnIdx;
		}

		public Point2D calculateOrigin() {
			double tempX = (columnIdx + 1) * padding + columnIdx * width;
			double tempY = (rowIdx + 1) * padding + rowIdx * height;
			return new Point2D(tempX, tempY);
		}

		public Point2D calculateCenter() {
			Point2D tempOrigin = calculateOrigin();
			Point2D tempCenter = new Point2D(tempOrigin.getX() + width / 2, tempOrigin.getY() + height / 2);
			return tempCenter;
		}
	}

	/** Row of screens */
	private class ScreenRow extends Group {

		public ScreenRow() {
			super();
		}
	}

	private Group node = new Group();
	private Group screensNode = new Group();
	private Rectangle rect;
	private double width;
	private double height;
	private double animationDuration = 500;
	private Duration duration = Duration.millis(animationDuration);

	private int maxRowCount;
	private int maxColumnCount;

	private double padding = 20;

	private List<ScreenCell> screens = new ArrayList<ScreenCell>();
	private List<ScreenRow> rows = new ArrayList<ScreenRow>();

	public World(double aWidth, double aHeight) {
		this(aWidth, aHeight, 3, 3);
	}

	public World(double aWidth, double aHeight, int aRowCount, int aColumnCount) {
		if (aRowCount % 2 == 0 || aColumnCount % 2 == 0) {
			throw new IllegalArgumentException("Row- and column count must be odd numbers. Row=" + aRowCount
					+ ", column=" + aColumnCount);
		}
		node.getChildren().add(screensNode);
		width = aWidth;
		height = aHeight;
		rect = new Rectangle(aWidth, aHeight);
		rect.setStroke(Color.AZURE);
		node.getChildren().add(rect);

		maxRowCount = aRowCount;
		maxColumnCount = aColumnCount;
		for (int i = 0; i < aRowCount; i++) {
			ScreenRow tempRow = new ScreenRow();
			rows.add(tempRow);
			screensNode.getChildren().add(tempRow);
		}
	}

	public void setScreenAt(Screen aScreen, int aColumnIdx, int aRowIdx) {
		if (aColumnIdx >= maxColumnCount || aRowIdx >= maxRowCount) {
			throw new IllegalArgumentException("Illegal column/row idx. Max=" + maxColumnCount + "/" + maxRowCount
					+ ", requested=" + aColumnIdx + "/" + aRowIdx);
		}
		for (ScreenCell tempPosition : screens) {
			if (tempPosition.matches(aColumnIdx, aRowIdx)) {
				// TODO not complete
				tempPosition.screen = aScreen;
				return;
			}
		}
		ScreenCell tempPosition = new ScreenCell(aScreen, aRowIdx, aColumnIdx);
		tempPosition.setupBounds();
		screens.add(tempPosition);
		rows.get(tempPosition.rowIdx).getChildren().add(tempPosition.node);
	}

	public void displayScreen(Screen aScreen) {
		ScreenCell tempCell = getScreenCellFor(aScreen);
		if (tempCell == null) {
			return;
		}
		Point2D tempCenter = tempCell.calculateCenter();
//		TranslateTransitionBuilder.create().node(screensNode).duration(duration).toZ(0).toX(tempCenter.getX())
//				.toY(tempCenter.getY()).build().play();
		TranslateTransitionBuilder.create().node(screensNode).duration(duration).toZ(0).build().play();
	}

	private ScreenCell getScreenCellFor(Screen aScreen) {
		for (ScreenCell tempCell : screens) {
			if (tempCell.screen == aScreen) {
				return tempCell;
			}
		}
		return null;
	}

	private void animateScreenToXYWidthHeight(Screen aScreen, double anX, double anY, double aWidth, double aHeight) {
		Timeline tempAnimation = new Timeline();

		tempAnimation.getKeyFrames().add(
				new KeyFrame(Duration.millis(animationDuration), new KeyValue(aScreen.translateXProperty(), anX)));
		tempAnimation.getKeyFrames().add(
				new KeyFrame(Duration.millis(animationDuration), new KeyValue(aScreen.translateYProperty(), anY)));
		tempAnimation.getKeyFrames().add(
				new KeyFrame(Duration.millis(animationDuration), new KeyValue(aScreen.prefWidthProperty(), aWidth)));
		tempAnimation.getKeyFrames().add(
				new KeyFrame(Duration.millis(animationDuration), new KeyValue(aScreen.prefHeightProperty(), aHeight)));
		tempAnimation.getKeyFrames().add(
				new KeyFrame(Duration.millis(animationDuration), new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("finished");
					}
				}));

		tempAnimation.play();
	}

	public void displayOverview() {
		TranslateTransitionBuilder.create().node(screensNode).duration(duration).byZ(2000).build().play();
	}

	private void moveScreenToXYWidthHeight(int anIdx, double anX, double anY, double anWidth, double aHeight) {
		if (screens.size() <= anIdx) {
			return;
		}
		Screen tempScreen = screens.get(anIdx).screen;
		animateScreenToXYWidthHeight(tempScreen, anX, anY, anWidth, aHeight);

	}

	public void installOn(Group aParent) {
		aParent.getChildren().add(node);
	}

	public void displayScreen(int i) {
		Screen tempScreen = screens.get(i).screen;
		displayScreen(tempScreen);
	}

	public Group getNode() {
		return node;
	}
}
