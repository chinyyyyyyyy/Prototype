package SpecialScene;

import ComponentMap.SceneManager;
import ComponentMap.StackAble;
import Logic.Backpack;
import Logic.InBackpack;
import Map.setsceneable;
import Tool.Hay;
import Tool.Seed;
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

public class Inventory implements setsceneable, SpecialScene {

	private Group root;
	public Scene scene;
	private Canvas c = new Canvas(1280, 720);
	GraphicsContext gc = c.getGraphicsContext2D();
	private int column;
	private int row;
	private static int MAX_ROW = 1;
	private static final int MAX_COLUMN = 10;
	private static int size = 11;

	public Inventory() {
		root = new Group();
		scene = new Scene(root);
		root.getChildren().add(c);
		EventKeyPress(scene);
		update();
	}

	public void EventKeyPress(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.X)) {
					SceneManager.warpTo(SceneManager.getSceneNumber());
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
					try {
						Backpack.SelectItem(row, column);
					} catch (IndexOutOfBoundsException e) {
						System.out.println("This slot is empty.");
					}
				}
				if (event.getCode().equals(KeyCode.I)) {
					SceneManager.warpTo(SceneManager.getSceneNumber());
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

	public void update() {
		gc.setFill(Color.ANTIQUEWHITE);
		gc.fillRect(0, 0, 1280, 720);

		gc.setFill(Color.BLACK);
		gc.setFont(new Font("abc", 50));
		gc.fillText("Backpack", 500, 80);
		gc.setStroke(Color.RED);
		gc.fillText("Item List", 150, 180);

		for (int i = 0; i < MAX_ROW; i++) {
			for (int j = 1; j <= MAX_COLUMN; j++) {
				gc.setStroke(Color.BLACK);
				gc.setFill(Color.ALICEBLUE);
				if(i==0 && j<4) {
					InBackpack x = Backpack.getBackpack().get(j);
					if(((Tool) x).getLevel()==2) {
						gc.setFill(Color.GOLD);
					}else if(((Tool) x).getLevel()==1)
						gc.setFill(Color.SILVER);
				}
				int index = (10 * i + j) % size;
				if(index < Backpack.getBackpack().size() && Backpack.getBackpack().get(index) instanceof Hay) gc.setFill(Color.GREENYELLOW);
				else if(index < Backpack.getBackpack().size() && Backpack.getBackpack().get(index) instanceof Seed) gc.setFill(Color.SPRINGGREEN);
				gc.fillRect(150 + (j-1) * 100, 250 + i * 150, 100, 100);
				gc.strokeRect(150 + (j-1) * 100, 250 + i * 150, 100, 100);
				if (index < Backpack.getBackpack().size()) {
					InBackpack x = Backpack.getBackpack().get(index);
					if(x instanceof WateringCan) {
						gc.setFill(Color.DEEPSKYBLUE);
						gc.fillRect(150 + (j-1) * 100, 250 + i * 150 + (1-WateringCan.getWaterLevel())*100, 100, WateringCan.getWaterLevel()*100);
					}
					gc.setFill(Color.BLACK);
					gc.setFont(new Font("abc", 20));
					gc.fillText(x.getClass().getSimpleName(), 180 + (j-1) * 100,
							310 + i * 150);
					if(x instanceof StackAble) {
						gc.fillText("x" + ((StackAble) x).getAmount(), 210 + (j-1) * 100,
								340 + i * 150);
					}
				}
			}
		}
		gc.setStroke(Color.RED);
		gc.strokeRect(152 + column * 100, 252 + row * 150, 96, 96);
	}

	public Scene getScene() {
		return scene;
	}
	
	public static void upgrade() {
		MAX_ROW++;
		size=Backpack.getMaxSize();
	}
}
