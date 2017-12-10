package SpecialScene;

import ComponentMap.SceneManager;
import Logic.Backpack;
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

public class UpgradeBag extends BuyScene implements setsceneable, SpecialScene{

	private Group root;
	public Scene scene;
	private Canvas c = new Canvas(1280, 720);
	GraphicsContext gc = c.getGraphicsContext2D();
	private static final int MAX_UPGRADE = 2;
	private static int countupgrade = 0;
	private static boolean upgradeable = true;

	public UpgradeBag() {
		root = new Group();
		scene = new Scene(root);
		root.getChildren().add(c);
		update();
		EventKeyPress(scene);
	}

	public void EventKeyPress(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.X)) {
					SceneManager.warpTo(18);
					return;
				}
				if (event.getCode().equals(KeyCode.Z)) {
					int cost = 5000;
					if (countupgrade == MAX_UPGRADE) {
						SceneManager.warpTo(18);
					} else if (countupgrade == 0) {
						cost = 2000;
					} 
					if(upgradeable) {
						World.setMoney(World.getMoney() - cost);
						if (World.getBuyable()) {
							Backpack.upgrade();
							countupgrade++;
							System.out.println("Total cost is " + cost);
							if(countupgrade==MAX_UPGRADE)upgradeable=false;
							SceneManager.warpTo(18);
						}				
					}
				}
			}
		});
	}

	public void update() {
		gc.drawImage(Background,0,0);
		gc.setFill(Color.BLACK);
		gc.setFont(header);
		gc.fillText("Upgrade Bag", 470, 100);
		gc.setStroke(Color.RED);
		gc.setFont(body);

		if (countupgrade == MAX_UPGRADE) {
			gc.fillText("Back", 150, 300);
		} else if (countupgrade == 0) {
			gc.fillText("Bag with " + ((Backpack.getMaxSize() - 1)+10) + " slots", 150, 300);
			gc.fillText("2000", 1000, 300);
		} else if (countupgrade == 1) {
			gc.fillText("Bag with " + ((Backpack.getMaxSize() - 1)+10) + " slots", 150, 300);
			gc.fillText("5000", 1000, 300);
		}
		gc.fillText("Bag", 150, 200);
		gc.fillText("Cost", 1000, 200);
		gc.drawImage(hand,50, 250);
	}

	public Scene getScene() {
		return scene;
	}

}
