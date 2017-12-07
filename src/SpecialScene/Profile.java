package SpecialScene;

import Animal.Cow;
import Animal.Hen;
import Animal.Sheep;
import ComponentMap.Hero;
import ComponentMap.SceneManager;
import Logic.World;
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

public class Profile implements setsceneable, SpecialScene {
	private Group root;
	public Scene scene;
	private Canvas c = new Canvas(1280, 720);
	GraphicsContext gc = c.getGraphicsContext2D();

	public Profile() {
		root = new Group();
		scene = new Scene(root);
		root.getChildren().add(c);

		update();
		EventKeyPress(scene);
	}

	private void EventKeyPress(Scene scene2) {
		// TODO Auto-generated method stub
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.TAB)) {
					SceneManager.warpTo(SceneManager.getSceneNumber());
				}
				if (event.getCode().equals(KeyCode.LEFT) || event.getCode().equals(KeyCode.RIGHT)) {
					SceneManager.warpTo(12);
				}
			}
		});
	}

	public void update() {
		gc.setFill(Color.ANTIQUEWHITE);
		gc.fillRect(0, 0, 1280, 720);
		gc.setFill(Color.BLACK);
		gc.setFont(new Font("abc", 50));
		gc.setStroke(Color.RED);
		gc.strokeRect(120, 80, 150, 150);
		gc.fillText("Name : " + Hero.getName(), 400, 80);
		gc.fillText("Money : " + World.getMoney(), 800, 80);
		gc.fillText("" + World.getSeason(), 400, 180);
		gc.fillText("day " + World.getDay(), 800, 180);

		gc.fillText("Animal List", 120, 300);
		gc.fillText("Unit", 800, 300);
		
		gc.strokeRect(200, 350, 80, 80);
		gc.fillText("Hen", 500, 410);
		gc.fillText("" + Hen.getHenCount(), 820, 410);

		gc.strokeRect(200, 470, 80, 80);
		gc.fillText("Cow", 500, 530);
		gc.fillText("" + Cow.getCowCount(), 820, 530);

		gc.strokeRect(200, 590, 80, 80);
		gc.fillText("Sheep", 500, 650);
		gc.fillText("" + Sheep.getSheepCount(), 820, 650);
	}

	public Scene getScene() {
		return scene;
	}

}
