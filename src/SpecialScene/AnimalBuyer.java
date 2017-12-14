package SpecialScene;

import ComponentMap.DialogCanvas;
import Logic.SceneManager;
import Map.SetsSeneable;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class AnimalBuyer implements SetsSeneable,SpecialScene,BuyInterface{
	private Group root;
	public Scene scene;
	private Canvas c = new Canvas(1280, 720);
	private GraphicsContext gc = c.getGraphicsContext2D();
	private static final int MAX_ROW = 2;
	private int row;

	public AnimalBuyer() {
		root = new Group();
		scene = new Scene(root);
		root.getChildren().add(c);
		row = 0;

		update();
		EventKeyPress(scene);
	}

	public void EventKeyPress(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.X)) {
					SceneManager.warpTo(6);
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
				if (event.getCode().equals(KeyCode.Z)) {
					if(row==0) SceneManager.warpTo(8);
					else SceneManager.warpTo(14);
				}
			}
		});
	}

	private void addRow(boolean increase) {
		if (increase) {
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

	public void update() {
		gc.drawImage(Background,0,0);
		gc.setFill(Color.BLACK);
		gc.setFont(header);
		gc.fillText("AnimalShop", 470, 100);
		gc.setStroke(Color.RED);
		gc.setFont(body);
		gc.fillText("Buy Animal", 150, 200);
		gc.fillText("Buy Tool", 150, 350);
		gc.drawImage(hand,50, 150 + row * 150);
	}

	public Scene getScene() {
		return scene;
	}
	
	public void chat(String word) {
		DialogCanvas d = DialogCanvas.Dialog;
		Platform.runLater(() -> {
			if(DialogCanvas.isHasDialog() == false) {
				root.getChildren().add(d);
				d.Chat(word);
			}
		});
		if(DialogCanvas.isHasDialog() == false) {
			DialogCanvas.stopDialog();
			root.getChildren().remove(d);
		}
	}
}
