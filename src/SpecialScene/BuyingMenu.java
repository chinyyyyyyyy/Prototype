package SpecialScene;

import ComponentMap.Hero;
import ComponentMap.ReceiveAction;
import ComponentMap.SceneManager;
import Logic.Backpack;
import Map.setsceneable;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class BuyingMenu implements setsceneable {
	private Group root;
	public Scene scene;
	private Canvas c = new Canvas(1280, 720);
	GraphicsContext gc = c.getGraphicsContext2D();
	private static final int MAX_ROW = 4;
	private static final int MAX_COLUMN = 2;
	private int row;
	private int column;
	private int amouttype1;
	private int amouttype2;
	private int amouttype3;
	private int priceof1;
	private int priceof2;
	private int priceof3;
	
	

	public BuyingMenu() {
		root = new Group();
		scene = new Scene(root);
		root.getChildren().add(c);
		row = 0;
		column = 0;
		amouttype1 = 0;
		amouttype2 = 0;
		amouttype3 = 0;
		priceof1 = 50;
		priceof2 = 100;
		priceof3 = 300;
		
		update();
		EventKeyPress(scene);
	}

	public void EventKeyPress(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.X)) {
					SceneManager.warpTo(7);
				}
				if (event.getCode().equals(KeyCode.UP)) {
					gc.clearRect(0, 0, 1280, 720);
					addRow(false);
					update();
				}
				if (event.getCode().equals(KeyCode.DOWN)) {
					gc.clearRect(0, 0, 1280, 720);
					addRow(true);
					update();
				}
				if (event.getCode().equals(KeyCode.LEFT)) {
					gc.clearRect(0, 0, 1280, 720);
					addColumn(false);
					update();
				}
				if (event.getCode().equals(KeyCode.RIGHT)) {
					gc.clearRect(0, 0, 1280, 720);
					addColumn(true);
					update();
				}
				if (event.getCode().equals(KeyCode.Z)) {
					editAmount() ;
				}
			}
		});
	}

	private void addRow(boolean incresae) {
		if (incresae) {
			if (row == MAX_ROW - 1) {
				row = 0;
			} else {
				row += 1;
			}
		} else {
			if (row == 0) {
				row = MAX_ROW - 1;
			} else {
				row -= 1;
			}
		}
	}

	private void addColumn(boolean incresae) {
		if (incresae) {
			if (column == MAX_COLUMN - 1) {
				column = 0;
			} else {
				column += 1;
			}
		} else {
			if (column == 0) {
				column = MAX_COLUMN - 1;
			} else {
				column -= 1;
			}
		}
	}

	private void update() {
		gc.setFill(Color.ANTIQUEWHITE);
		gc.fillRect(0, 0, 1280, 720);
		gc.setFill(Color.BLACK);
		gc.setFont(new Font("abc", 50));
		gc.fillText("MenuJA", 500, 50);
		gc.setStroke(Color.RED);
		gc.fillText("Type1", 50, 200);
		gc.fillText("Type2", 50, 350);
		gc.fillText("Type2", 50, 500);
		gc.fillText("X"+priceof1, 725, 200);
		gc.fillText("X"+priceof2, 725, 350);
		gc.fillText("X"+priceof3, 725, 500);
		gc.fillText(amouttype1 + "", 675, 200);
		gc.fillText(amouttype2 + "", 675, 350);
		gc.fillText(amouttype3 + "", 675, 500);
		gc.fillText(amouttype1*priceof1+"", 1000, 200);
		gc.fillText(amouttype2*priceof2+"",1000, 350);
		gc.fillText(amouttype3*priceof3+"",1000, 500);

		gc.strokeRect(600 + column * 250, 150 + row * 150, 50, 50);
	}

	private void editAmount() {
		if (column == 0) {
			if (row == 0 && amouttype1 > 0)
				amouttype1--;
			else if (row == 1 && amouttype2 > 0)
				amouttype2--;
			else if (row == 2 && amouttype3 > 0)
				amouttype3--;
		} else if (column == 1) {
			if (row == 0)
				amouttype1++;
			else if (row == 1)
				amouttype2++;
			else if (row == 2)
				amouttype3++;
		}
		update();
	}

	public Scene getScene() {
		return scene;
	}

}
