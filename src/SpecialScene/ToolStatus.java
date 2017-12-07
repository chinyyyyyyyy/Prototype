package SpecialScene;

import java.util.ArrayList;
import java.util.List;

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

public class ToolStatus implements setsceneable, SpecialScene {
	private Group root;
	public Scene scene;
	private Canvas c = new Canvas(1280, 720);
	GraphicsContext gc = c.getGraphicsContext2D();
	private static List<Integer> upgradeLevel = new ArrayList<>();

	public ToolStatus() {
		root = new Group();
		scene = new Scene(root);
		root.getChildren().add(c);
		EventKeyPress(scene);
		upgradeLevel.add(100);
		upgradeLevel.add(200);
		update();
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
					SceneManager.warpTo(11);
				}
			}
		});
	}

	public void update() {	
		int count=0;
		gc.setFill(Color.ANTIQUEWHITE);
		gc.fillRect(0, 0, 1280, 720);
		
		gc.setFill(Color.ALICEBLUE);
		gc.fillRect(380, 240, 500, 50);
		gc.fillRect(380, 360, 500, 50);
		gc.fillRect(380, 480, 500, 50);
		gc.fillRect(380, 600, 500, 50);
		
		for (InBackpack i : Backpack.getBackpack()) {
			if(i instanceof Tool) {
				((Tool) i).CheckUpgrade();
				if(((Tool) i).getTimeOfUse() > upgradeLevel.get(0)){
					gc.setFill(Color.SILVER);
					gc.fillRect(380, 240 + count*120, 500.0, 50);
					gc.setFill(Color.GOLD);
					double ratio = (((Tool) i).getTimeOfUse()-100)/upgradeLevel.get(1);
					gc.fillRect(380, 240 + count*120, ratio*500.0, 50);
				}else {
					gc.setFill(Color.SILVER);		
					double ratio = ((Tool) i).getTimeOfUse()/upgradeLevel.get(0);
					gc.fillRect(380, 240 + count*120, ratio*500.0, 50);			
				}
				count++;				
			}
		}
		gc.setFill(Color.BLACK);
		gc.setFont(new Font("abc", 50));
		gc.fillText("Tool Status", 500, 80);
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
	}

	public Scene getScene() {
		return scene;
	}

	public static List<Integer> getUpgradeLevel(){
		return upgradeLevel;
	}
}
