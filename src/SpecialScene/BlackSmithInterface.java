package SpecialScene;

import ComponentMap.SceneManager;
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

public class BlackSmithInterface implements setsceneable,SpecialScene{
	private Group root;
	public Scene scene;
	private Canvas c = new Canvas(1280, 720);
	GraphicsContext gc = c.getGraphicsContext2D();
	private static final int MAX_ROW = 2;
	private int row;

	public BlackSmithInterface() {
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
					if(row==0) SceneManager.warpTo(16);
					else SceneManager.warpTo(17);
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

	public void update() {
		gc.drawImage(Background,0,0);
		gc.setFill(Color.BLACK);
		gc.setFont(new Font("abc", 50));
		gc.fillText("BlackSmithJA", 500, 50);
		gc.setStroke(Color.RED);
		gc.fillText("Upgrade Tool", 150, 200);
		gc.fillText("Upgrade Bag", 150, 350);

		gc.strokeRect(50, 150 + row * 150, 50, 50);
	}

	public Scene getScene() {
		return scene;
	}

}
