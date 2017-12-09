package SpecialScene;

import ComponentMap.SceneManager;
import Logic.Backpack;
import Logic.InBackpack;
import Map.setsceneable;
import Tool.Axe;
import Tool.Hammer;
import Tool.Hoe;
import Tool.Tool;
import Tool.WateringCan;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class BlackSmithMenu implements setsceneable, SpecialScene {
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
					InBackpack item = Backpack.getBackpack().get(row+1);
					if(((Tool) item).CheckUpgrade()) {
						((Tool) item).upgrade();
						update();
					}else
						System.out.println("Sorry Your Tool can't upgrade.");
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
		int count = 0;
		gc.drawImage(Background,0,0);

		gc.setFill(Color.ALICEBLUE);
		gc.fillRect(380, 240, 500, 50);
		gc.fillRect(380, 360, 500, 50);
		gc.fillRect(380, 480, 500, 50);
		gc.fillRect(380, 600, 500, 50);

		for (InBackpack i : Backpack.getBackpack()) {
			if (i instanceof Tool) {
				((Tool) i).CheckUpgrade();
				if (((Tool) i).getTimeOfUse() > ToolStatus.getUpgradeLevel().get(0)) {
					gc.setFill(Color.SILVER);
					gc.fillRect(380, 240 + count * 120, 500.0, 50);
					gc.setFill(Color.GOLD);
					double ratio = (((Tool) i).getTimeOfUse() - 100) / (ToolStatus.getUpgradeLevel().get(1) - 100);
					gc.fillRect(380, 240 + count * 120, ratio * 500.0, 50);
				} else {
					gc.setFill(Color.SILVER);
					double ratio = ((Tool) i).getTimeOfUse() / ToolStatus.getUpgradeLevel().get(0);
					gc.fillRect(380, 240 + count * 120, ratio * 500.0, 50);
				}
				if (((Tool) i).getLevel() == 0) {
					gc.setFill(Color.ALICEBLUE);
				} else if (((Tool) i).getLevel() == 1) {
					gc.setFill(Color.SILVER);
				} else {
					gc.setFill(Color.GOLD);
				}
				if (count < 4) {
					gc.fillRect(50, 220 + count * 120, 80, 80);
				}
				count++;
			}
		}
		gc.setFill(Color.BLACK);
		gc.setFont(new Font("abc", 50));
		gc.fillText("Tool Upgrade", 500, 80);
		gc.setStroke(Color.RED);
		gc.fillText("Tool List", 150, 180);
		gc.fillText("Status", 970, 180);

		gc.strokeRect(50, 220, 80, 80);
		gc.fillText("Axe", 150, 280);
		gc.fillText(Axe.isUpgradeable(), 950, 280);

		gc.strokeRect(50, 340, 80, 80);
		gc.fillText("Hammer", 150, 400);
		gc.fillText(Hammer.isUpgradeable(), 950, 400);

		gc.strokeRect(50, 460, 80, 80);
		gc.fillText("Hoe", 150, 520);
		gc.fillText(Hoe.isUpgradeable(), 950, 520);

		gc.strokeRect(50, 580, 80, 80);
		gc.fillText("Can", 150, 640);
		gc.fillText(WateringCan.isUpgradeable(), 950, 640);

		gc.strokeRect(1200, 240 + row * 120, 50, 50);
	}

	public Scene getScene() {
		return scene;
	}

}
