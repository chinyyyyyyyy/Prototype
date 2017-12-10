package SpecialScene;

import ComponentMap.DialogCanvas;
import ComponentMap.SceneManager;
import Logic.Backpack;
import Logic.InBackpack;
import Logic.World;
import Map.setsceneable;
import Tool.Axe;
import Tool.Hammer;
import Tool.Hoe;
import Tool.Tool;
import Tool.WateringCan;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class BlackSmithMenu extends BuyScene implements setsceneable, SpecialScene {
	private Group root;
	public Scene scene;
	private Canvas c = new Canvas(1280, 720);
	GraphicsContext gc = c.getGraphicsContext2D();
	private static int MAX_ROW = 4;
	private int row;

	public BlackSmithMenu() {
		root = new Group();
		scene = new Scene(root);
		root.getChildren().add(c);
		EventKeyPress(scene);
		update();
	}

	private void EventKeyPress(Scene scene2) {
		// TODO Auto-generated method stub
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.X)) {
					SceneManager.warpTo(18);
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
					InBackpack item = Backpack.getBackpack().get(row + 1);
					if (((Tool) item).CheckUpgrade()) {
						int cost = 0;
						if (item instanceof Axe) {
							cost = Axe.UpgradeCost();
						} else if (item instanceof Hoe) {
							cost = Hoe.UpgradeCost();
						} else if (item instanceof Hammer) {
							cost = Hammer.UpgradeCost();
						} else if (item instanceof WateringCan) {
							cost = WateringCan.UpgradeCost();
						}
						World.setMoney(World.getMoney() - cost);
						if (World.getBuyable()) {
							((Tool) item).upgrade();
							update();
							chat("Upgrade Success !","Total cost is "+cost+" $");	
						}else {
							chat("You don't have enough money.");	
						}
					} else {
						System.out.println("Sorry Your Tool can't upgrade.");
						chat("Sorry Your Tool can't upgrade.");	
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
		
		for(int i = 1 ; i<5 ;i++) {
			if (((Tool) Backpack.getBackpack().get(i)).getLevel() == 0) {
				gc.setFill(Color.ALICEBLUE);
			} else if (((Tool) Backpack.getBackpack().get(i)).getLevel() == 1) {
				gc.setFill(Color.SILVER);
			} else {
				gc.setFill(Color.GOLD);
			}
			gc.fillRect(50, 220 + (i-1) * 120, 80, 80);
		}

		gc.setFill(Color.BLACK);
		gc.setFont(header);
		gc.fillText("Tool Upgrade", 470, 100);
		gc.setStroke(Color.RED);
		gc.setFont(body);
		gc.fillText("Tool List", 150, 180);
		gc.fillText("Status", 530, 180);
		gc.fillText("Cost", 970, 180);

		gc.strokeRect(50, 220, 80, 80);
		gc.drawImage(Backpack.getBackpack().get(1).getImage(), 50, 220);
		gc.fillText("Axe", 150, 280);
		gc.fillText(Axe.StateUpgradeable(), 400, 280, 500);
		if (Axe.isUpgradeable().equals("") == false && Axe.UpgradeCost() != 0)
			gc.fillText("" + Axe.UpgradeCost(), 970, 280);

		gc.strokeRect(50, 340, 80, 80);
		gc.drawImage(Backpack.getBackpack().get(2).getImage(), 50, 340);
		gc.fillText("Hammer", 150, 400);
		gc.fillText(Hammer.StateUpgradeable(), 400, 400, 500);
		if (Hammer.isUpgradeable().equals("") == false && Hammer.UpgradeCost() != 0)
			gc.fillText("" + Hammer.UpgradeCost(), 970, 400);

		gc.strokeRect(50, 460, 80, 80);
		gc.drawImage(Backpack.getBackpack().get(3).getImage(), 50, 460);
		gc.fillText("Hoe", 150, 520);
		gc.fillText(Hoe.StateUpgradeable(), 400, 520, 500);
		if (Hoe.isUpgradeable().equals("") == false && Hoe.UpgradeCost() != 0)
			gc.fillText("" + Hoe.UpgradeCost(), 970, 520);

		gc.strokeRect(50, 580, 80, 80);
		gc.drawImage(Backpack.getBackpack().get(4).getImage(), 50, 580);
		gc.fillText("Watering Can", 150, 640, 200);
		gc.fillText(WateringCan.StateUpgradeable(), 400, 640, 500);
		if (WateringCan.isUpgradeable().equals("") == false && WateringCan.UpgradeCost() != 0)
			gc.fillText("" + WateringCan.UpgradeCost(), 970, 640);

		gc.drawImage(hand,1200, 240 + row * 120);
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
	
	public void chat(String word1, String word2) {
		// TODO Auto-generated method stub
		DialogCanvas d = DialogCanvas.Dialog;
		Platform.runLater(() -> {
			if(DialogCanvas.isHasDialog() == false) {
				root.getChildren().add(d);
				d.Chat(word1,word2);
			}
		});
		if(DialogCanvas.isHasDialog() == false) {
			DialogCanvas.stopDialog();
			root.getChildren().remove(d);
		}
	}
}
