package SpecialScene;

import ComponentMap.DialogCanvas;
import ComponentMap.SceneManager;
import Logic.Backpack;
import Logic.World;
import Map.setsceneable;
import Tool.Milker;
import Tool.Scissors;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ToolMenu extends BuyScene implements setsceneable, SpecialScene {

	private Group root;
	public Scene scene;
	private Canvas c = new Canvas(1280, 720);
	GraphicsContext gc = c.getGraphicsContext2D();
	private static int MAX_ROW = 2;
	private int row;
	private static int priceof1;
	private static int priceof2;
	private String type1;
	private String type2;
	private static boolean buyMilker = false;
	private static boolean buyScissors = false;

	public ToolMenu(String type1, int price1, String type2, int price2) {
		root = new Group();
		scene = new Scene(root);
		root.getChildren().add(c);
		row = 0;
		this.type1 = type1;
		this.type2 = type2;
		priceof1 = price1;
		priceof2 = price2;

		update();
		EventKeyPress(scene);
	}

	public void EventKeyPress(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.X)) {
					SceneManager.warpTo(13);
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
					if (buyMilker && buyScissors) {
						SceneManager.warpTo(13);
						return;
					}
					if (row == 0 && buyMilker == false) {
						World.setMoney(World.getMoney() - priceof1);
						if (World.getBuyable()) {
							Backpack.getBackpack().add(new Milker());
							System.out.println("Total cost is " + priceof1);
							chat("Total cost is " + priceof1);
							buyMilker = true;
						}
					} else {
						World.setMoney(World.getMoney() - priceof2);
						if (World.getBuyable()) {
							Backpack.getBackpack().add(new Scissors());
							System.out.println("Total cost is " + priceof2);
							chat("Total cost is " + priceof2);
							buyScissors = true;
							row--;
						}
					}

					if (World.getBuyable()) {
						if (DialogCanvas.isHasDialog() == false)
							SceneManager.warpTo(6);
						MAX_ROW--;
						row = 0;
					}
				}
			}
		});
	}

	private void addRow(boolean incresae) {
		if (MAX_ROW <= 0) {
			return;
		}
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
		gc.drawImage(Background, 0, 0);
		gc.setFill(Color.BLACK);
		gc.setFont(header);
		gc.fillText("Tool", 500, 50);
		gc.setStroke(Color.RED);
		gc.setFont(body);
		if (buyMilker && buyScissors) {
			gc.fillText("Back", 150, 200);
		} else if (buyMilker) {
			gc.fillText(type2, 150, 200);
			gc.fillText("" + priceof2, 1000, 200);
		} else if (buyScissors) {
			gc.fillText(type1, 150, 200);
			gc.fillText("" + priceof1, 1000, 200);
		} else {
			gc.fillText(type1, 150, 200);
			gc.fillText(type2, 150, 350);

			gc.fillText("" + priceof1, 1000, 200);
			gc.fillText("" + priceof2, 1000, 350);
		}

		gc.fillText("Tool", 150, 120);	
		gc.fillText("Cost", 1000, 120);		
		gc.drawImage(hand,50, 150 + row * 150);
	}

	public Scene getScene() {
		return scene;
	}

	public void chat(String word) {
		DialogCanvas d = DialogCanvas.Dialog;
		Platform.runLater(() -> {
			if (DialogCanvas.isHasDialog() == false) {
				root.getChildren().add(d);
				d.Chat(word);
			}
		});
		if (DialogCanvas.isHasDialog() == false) {
			DialogCanvas.stopDialog();
			root.getChildren().remove(d);
		}
	}
}
