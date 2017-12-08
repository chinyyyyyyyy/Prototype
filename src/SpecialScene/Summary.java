package SpecialScene;

import ComponentMap.Dropbox;
import ComponentMap.SceneManager;
import Logic.World;
import Map.setsceneable;
import Plant.OnHandAble;
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
					SceneManager.warpTo(2);
				}
			}
		});
	}

	public void update() {	
		gc.setFill(Color.ANTIQUEWHITE);
		gc.fillRect(0, 0, 1280, 720);
		
		gc.setFill(Color.BLACK);
		gc.setFont(new Font("abc", 50));
		gc.fillText("Summary", 500, 80);
		gc.setStroke(Color.BLACK);
		gc.fillText("Sell List", 150, 180);
		gc.fillText("Price", 970, 180);
		
		gc.setFont(new Font("abc", 40));
		for(int i=0 ; i < Dropbox.getListindropbox().size() ; i++) {
			gc.strokeRect(50, 220+60*i, 50, 50);
			Pair<OnHandAble, Integer> x = Dropbox.getListindropbox().get(i);
			gc.fillText(x.getKey().getClass().getSimpleName(), 150, 270+60*i);
			gc.fillText("x"+x.getValue(), 800, 270+60*i);
			gc.fillText(""+x.getKey().getPrice()*x.getValue(), 970, 270+60*i);		
		}
		gc.fillText("Total Selling : "+Dropbox.getTotalsell(), 730, 670);
		
		gc.setFill(Color.RED);
		gc.fillText("Press Enter to continue", 150, 670);
	}
	
	public Scene getScene() {
		return scene;
	}

}
