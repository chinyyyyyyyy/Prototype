package SpecialScene;

import ComponentMap.DialogCanvas;
import ComponentMap.Dropbox;
import ComponentMap.SceneManager;
import Logic.World;
import Map.setsceneable;
import Plant.OnHandAble;
import Plant.Plant;
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
import javafx.util.Pair;

public class Summary implements setsceneable, SpecialScene {

	private Group root;
	public Scene scene;
	private Canvas c = new Canvas(1280, 720);
	GraphicsContext gc = c.getGraphicsContext2D();

	public Summary() {
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
				if (event.getCode().equals(KeyCode.ENTER)) {
					World.Tomorrow();
					if (DialogCanvas.isHasDialog() == false)
						SceneManager.warpTo(2);
				}
			}
		});
	}

	public void update() {
		gc.drawImage(Background, 0, 0);
		gc.setFill(Color.BLACK);
		gc.setFont(header);
		gc.fillText("Summary", 470, 100);
		gc.setStroke(Color.BLACK);
		gc.setFont(body);
		gc.fillText("Sell List", 150, 180);
		gc.fillText("Price", 970, 180);

		gc.setFont(new Font("abc", 40));
		for (int i = 0; i < Dropbox.getListindropbox().size(); i++) {
			gc.strokeRect(50, 220 + 60 * i, 50, 50);
			Pair<OnHandAble, Integer> x = Dropbox.getListindropbox().get(i);
			String name;
			if (x.getKey() instanceof Plant) {
				name = ((Plant) x.getKey()).getName();
			} else {
				name = x.getKey().getClass().getSimpleName();
			}
			gc.drawImage(x.getKey().getImage(), 50, 220 + 60 * i, 50, 50);
			gc.fillText(name, 150, 270 + 60 * i);
			gc.fillText("x" + x.getValue(), 800, 270 + 60 * i);
			gc.fillText("" + x.getKey().getPrice() * x.getValue(), 970, 270 + 60 * i);
		}
		gc.fillText("Total Selling : " + Dropbox.getTotalsell(), 730, 670);

		// gc.setFill(Color.RED);
		// gc.fillText("Press Enter to continue", 150, 670);
		chat("Press Enter to continue");
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
