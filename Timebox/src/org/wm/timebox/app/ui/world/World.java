/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wm.timebox.app.ui.world;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransitionBuilder;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wm.timebox.app.di.EventSupport;
import org.wm.timebox.app.di.Observer;

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
			pane.setPrefHeight(height);
			pane.setPrefWidth(width);
		}

		public boolean matches(int aColumnIdx, int aRowIdx) {
			return rowIdx == aRowIdx && columnIdx == aColumnIdx;
		}

		public Point2D calculateOrigin() {
			double tempX = columnIdx * padding + columnIdx * width;
			double tempY = rowIdx * padding + rowIdx * height;
			return new Point2D(tempX, tempY);
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
	private boolean inDisplayOverview;

	private int maxRowCount;
	private int maxColumnCount;

	private double padding = 40;

	private List<ScreenCell> screens = new ArrayList<ScreenCell>();
	private List<ScreenRow> rows = new ArrayList<ScreenRow>();

	private EventSupport<ShowOverviewEvent> showOverviewEventSupport;

	private Log logger = LogFactory.getLog(World.class);

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
		// node.getChildren().add(rect);

		maxRowCount = aRowCount;
		maxColumnCount = aColumnCount;
		for (int i = 0; i < aRowCount; i++) {
			ScreenRow tempRow = new ScreenRow();
			rows.add(tempRow);
			screensNode.getChildren().add(tempRow);
		}
	}

	public void setScreenAt(final Screen aScreen, int aColumnIdx, int aRowIdx) {
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

		aScreen.addEventFilter(Event.ANY, new EventHandler<Event>() {
			@Override
			public void handle(Event aEvent) {
				if (inDisplayOverview) {
					aEvent.consume();
					if (aEvent instanceof MouseEvent && ((MouseEvent) aEvent).getClickCount() > 0) {
						inDisplayOverview = false;
						displayScreen(aScreen);
					}
				}
			}
		});
	}

	private ScreenCell getScreenCellFor(Screen aScreen) {
		for (ScreenCell tempCell : screens) {
			if (tempCell.screen == aScreen) {
				return tempCell;
			}
		}
		return null;
	}

	public void displayScreen(Screen aScreen) {
		ScreenCell tempCell = getScreenCellFor(aScreen);
		if (tempCell == null) {
			logger.debug("Displaying screen: Unknown");
			return;
		}
		logger.debug("Displaying screen: " + tempCell.columnIdx + "/" + tempCell.rowIdx);

		Point2D tempOrigin = tempCell.calculateOrigin();
		TranslateTransition tempTranslation = TranslateTransitionBuilder.create().node(screensNode).duration(duration)
				.toZ(0).toX(-tempOrigin.getX()).toY(-tempOrigin.getY()).build();

		ParallelTransition tempParallel = new ParallelTransition(tempTranslation);
		for (int tempRowIdx = 0; tempRowIdx < rows.size(); tempRowIdx++) {
			ScreenRow tempRow = rows.get(tempRowIdx);
			if (tempRowIdx == tempCell.rowIdx) {
				tempParallel.getChildren().add(
						RotateTransitionBuilder.create().node(tempRow).duration(duration).toAngle(0)
								.axis(Rotate.Y_AXIS).build());
				continue;
			}
			double tempAngle = tempCell.rowIdx < tempRowIdx ? 90 : -90;
			tempParallel.getChildren().add(
					RotateTransitionBuilder.create().node(tempRow).duration(duration).toAngle(tempAngle)
							.axis(Rotate.Y_AXIS).build());
		}
		tempParallel.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				inDisplayOverview = false;
			}
		});
		tempParallel.play();
	}

	public void displayOverview() {
		logger.debug("Displaying overview.");
		TranslateTransition tempTranslation = TranslateTransitionBuilder.create().node(screensNode).duration(duration)
				.toZ(4000).toX(-(width * maxColumnCount) / 2).toY(-(height * maxRowCount) / 2).build();

		ParallelTransition tempParallel = new ParallelTransition(tempTranslation);

		for (ScreenRow tempRow : rows) {
			tempParallel.getChildren().add(
					RotateTransitionBuilder.create().node(tempRow).duration(duration).toAngle(0).axis(Rotate.Y_AXIS)
							.build());
		}

		tempParallel.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				inDisplayOverview = true;
			}
		});

		tempParallel.play();
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

	public void fadeRowIdx(int aRowIdx, boolean aFadeOutFlag) {
		rows.get(0).getChildren().get(0).opacityProperty().set(aFadeOutFlag ? 0.5 : 1);
	}

	public void rotateRowIdx(int aRowIdx, boolean aRotateFlag) {
		rows.get(0).getChildren().get(0).rotationAxisProperty().set(Rotate.Y_AXIS);
		rows.get(0).getChildren().get(0).rotateProperty().set(aRotateFlag ? 30 : 0);
	}

	public void setShowOverviewEventSupport(EventSupport<ShowOverviewEvent> aShowOverviewEventSupport) {
		showOverviewEventSupport = aShowOverviewEventSupport;
		if (aShowOverviewEventSupport != null) {
			aShowOverviewEventSupport.addListener(new Observer<ShowOverviewEvent>() {
				@Override
				public void notify(ShowOverviewEvent aAnEvent) {
					displayOverview();
				}
			});
		}
	}

	public EventSupport<ShowOverviewEvent> getShowOverviewEventSupport() {
		return showOverviewEventSupport;
	}
}
