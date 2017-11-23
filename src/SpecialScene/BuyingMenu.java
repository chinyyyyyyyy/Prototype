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

	public BuyingMenu() {
		root = new Group();
		scene = new Scene(root);
		Canvas c = new Canvas(1280, 720);
		GraphicsContext gc = c.getGraphicsContext2D();
		gc.setFill(Color.ANTIQUEWHITE);
		gc.fillRect(0, 0, 1280, 720);
		gc.setFill(Color.BLACK);
		gc.setFont(new Font("Consola", 200));
		gc.fillText("MenuJA", 400, 300);
		root.getChildren().add(c);
		backtomap(scene);
	}

	public void backtomap(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.X)) {
					SceneManager.warpTo(7);
				}
			}
		});
	}

	public Scene getScene() {
		return scene;
	}

}
