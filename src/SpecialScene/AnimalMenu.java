package SpecialScene;

import ComponentMap.DialogCanvas;
import ComponentMap.SceneManager;
import Logic.World;
import Map.setsceneable;
import NPC.CounterAnimal;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class AnimalMenu extends BuyScene implements setsceneable, SpecialScene {

	private Group root;
	public Scene scene;
	private Canvas c = new Canvas(1280, 720);
	GraphicsContext gc = c.getGraphicsContext2D();
	private static final int MAX_ROW = 4;
	private static final int MAX_COLUMN = 2;
	private int row;
	private int column;
	private static int amouttype1;
	private static int amouttype2;
	private static int amouttype3;
	private static int priceof1;
	private static int priceof2;
	private static int priceof3;
	private int sc;
	private String type1;
	private String type2;
	private String type3;

	public AnimalMenu(String type1, int price1, String type2, int price2, String type3, int price3, int sc) {
		root = new Group();
		scene = new Scene(root);
		root.getChildren().add(c);
		row = 0;
		column = 0;
		amouttype1 = 0;
		amouttype2 = 0;
		amouttype3 = 0;
		this.type1 = type1;
		this.type2 = type2;
		this.type3 = type3;
		priceof1 = price1;
		priceof2 = price2;
		priceof3 = price3;
		this.sc = sc;

		update();
		EventKeyPress(scene);
	}

	public void EventKeyPress(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.X)) {
					SceneManager.warpTo(sc);
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
					editAmount();
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
		gc.drawImage(Background, 0, 0);
		gc.setFill(Color.BLACK);
		gc.setFont(header);
		gc.fillText("AnimalShop", 470, 100);
		gc.setStroke(Color.RED);
		gc.setFont(body);
		gc.fillText(type1, 50, 250);
		gc.fillText(type2, 50, 400);
		gc.fillText(type3, 50, 550);
		gc.fillText("Total Cost", 50, 650);
		gc.fillText("" + getTotalCost(), 350, 650);
		gc.fillText("X" + priceof1, 725, 250);
		gc.fillText("X" + priceof2, 725, 400);
		gc.fillText("X" + priceof3, 725, 550);
		gc.fillText("Amount", 675, 170);
		gc.fillText("Cost", 1000, 170);
		gc.fillText(amouttype1 + "", 675, 250);
		gc.fillText(amouttype2 + "", 675, 400);
		gc.fillText(amouttype3 + "", 675, 550);
		gc.fillText(amouttype1 * priceof1 + "", 1000, 250);
		gc.fillText(amouttype2 * priceof2 + "", 1000, 400);
		gc.fillText(amouttype3 * priceof3 + "", 1000, 550);
		gc.fillText("RESET", 675, 700);
		gc.fillText("OK", 925, 700);
		gc.drawImage(plus, 875, 200);
		gc.drawImage(plus, 875, 350);
		gc.drawImage(plus, 875, 500);
		gc.drawImage(minus, 600, 200);
		gc.drawImage(minus, 600, 350);
		gc.drawImage(minus, 600, 500);
		if (row == MAX_ROW - 1) {
			gc.drawImage(hand, 600 + column * 275, 200 + row * 150);
		} else {
			gc.strokeRect(600 + column * 275, 200 + row * 150, 50, 50);
		}
	}

	protected void editAmount() {
		if (column == 0) {
			if (row == 0 && amouttype1 > 0)
				amouttype1--;
			else if (row == 1 && amouttype2 > 0)
				amouttype2--;
			else if (row == 2 && amouttype3 > 0)
				amouttype3--;
			else if (row == 3) {
				reset();
			}

		} else if (column == 1) {
			if (row == 0)
				amouttype1++;
			else if (row == 1)
				amouttype2++;
			else if (row == 2)
				amouttype3++;
			else if (row == 3) {
				try {
					CounterAnimal.CheckBuyable(amouttype1, amouttype2, amouttype3);
				} catch (IndexOutOfBoundsException e) {
					reset();
				}
				if (CounterAnimal.isBuyable()) {
					World.setMoney(World.getMoney() - AnimalMenu.getTotalCost());
					if (CounterAnimal.canBuyBarn && CounterAnimal.canBuyHen && World.getBuyable()) {
						CounterAnimal.update(amouttype1, amouttype2, amouttype3);
						if (AnimalMenu.getTotalCost() != 0)
							chat("Buying Success !", "Total cost is " + AnimalMenu.getTotalCost() + " $");
					} else {
						chat("You don't have enough money.");
					}
				} else {
					chat("Your barn is full.");
				}
				reset();
			}
		}
		update();
	}

	public Scene getScene() {
		return scene;
	}

	public void reset() {
		amouttype1 = 0;
		amouttype2 = 0;
		amouttype3 = 0;
		CounterAnimal.canBuyBarn = true;
		CounterAnimal.canBuyHen = true;
	}

	public static int getTotalCost() {
		return amouttype1 * priceof1 + amouttype2 * priceof2 + amouttype3 * priceof3;
	}

	public static int getAmouttype1() {
		return amouttype1;
	}

	public static int getAmouttype2() {
		return amouttype2;
	}

	public static int getAmouttype3() {
		return amouttype3;
	}

	public static int getPriceof1() {
		return priceof1;
	}

	public static int getPriceof2() {
		return priceof2;
	}

	public static int getPriceof3() {
		return priceof3;
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
			if (DialogCanvas.isHasDialog() == false) {
				root.getChildren().add(d);
				d.Chat(word1, word2);
			}
		});
		if (DialogCanvas.isHasDialog() == false) {
			DialogCanvas.stopDialog();
			root.getChildren().remove(d);
		}
	}
}
