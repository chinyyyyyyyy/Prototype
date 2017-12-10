package Map;

import java.util.ArrayList;
import java.util.List;

import ComponentMap.Bed;
import ComponentMap.ClockCanvas;
import ComponentMap.DialogCanvas;
import ComponentMap.Environment;
import ComponentMap.Hero;
import ComponentMap.ReceiveAction;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

public class House implements setsceneable, HaveTime {
	private Group root;
	public Scene scene;
	private Hero hero;
	private List<Environment> e = new ArrayList<>();
	private List<ReceiveAction> re = new ArrayList<>();
	private List<Pair<Rectangle, Integer>> WarpList = new ArrayList<>();
	private ClockCanvas clock = new ClockCanvas();

	public House(int starthx, int starthy) {
		root = new Group();
		scene = new Scene(root, 1280, 720);
		Canvas bg = new Canvas(1280, 720);
		GraphicsContext gc = bg.getGraphicsContext2D();
		Image background = new Image(ClassLoader.getSystemResource("inHouse.png").toString());
		gc.drawImage(background, 0, 0);
		// gc.setFill(Color.TOMATO);
		// gc.fillOval(440,210,400, 300);
		root.getChildren().add(bg);

		// Boarder
		e.add(new Environment(-10, 0, 10, 720, Color.BLACK));// boarderLEFT
		e.add(new Environment(0, 225, 1280, 10, Color.BLACK));// boarderTOP
		e.add(new Environment(1280, 0, 10, 720, Color.BLACK));// boarderRIGHT
		e.add(new Environment(0, 720, 1280, 10, Color.BLACK));// boarderBOTTOM
		// Furniture
		e.add(new Environment(0, 625, 95, 95, Color.BLACK));// ToolBlock
		e.add(new Environment(600, 225, 200, 87, Color.BLACK));// TV
		e.add(new Environment(1095, 225, 230, 240, Color.BLACK));//BED
		e.add(new Environment(800, 225, 80, 80, Color.BLACK));//box1
		e.add(new Environment(1000, 225, 95, 80, Color.BLACK));//box2
		//root.getChildren().addAll(e);
		
		
		re.add(new ReceiveAction(600, 225, 150, 50, Color.MEDIUMPURPLE));// TV
		re.add(new Bed(1095, 225, 230, 240, Color.RED));//BED
		//root.getChildren().addAll(re);
		
		Rectangle warpblocktofarm = new Rectangle(580,695,120,25);
		warpblocktofarm.setFill(Color.RED);
		// root.getChildren().add(warpblocktofarm);
		Pair<Rectangle, Integer> tofarm = new Pair<Rectangle, Integer>(warpblocktofarm, 0);
		WarpList.add(tofarm);

		hero = new Hero(scene, starthx, starthy, e, re,true);
		root.getChildren().addAll(hero.getUnitblock());
		for (Rectangle r : hero.getActionblock()) {
			root.getChildren().add(r);
		}
		hero.setWarpBlockList(WarpList);
	}
	
	public void chat(String word) {
		DialogCanvas d = DialogCanvas.Dialog;
		Platform.runLater(() -> {
			if(DialogCanvas.isHasDialog() == false) {
				root.getChildren().add(d);
				d.Chat(word);
			}
		});
		if(DialogCanvas.isHasDialog() == false) {
			DialogCanvas.stopDialog();
			root.getChildren().remove(d);
		}
	}
	
	public void chat(String word1,String word2) {
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

	public void addClock() {
		clock = new ClockCanvas();
		Platform.runLater(() -> {
			root.getChildren().add(clock);
		});
		try {
			clock.TurnClock();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removeClock() {
		if (root.getChildren().contains(clock)) {
			Platform.runLater(() -> {
				root.getChildren().remove(clock);
			});
		}
	}

	public Scene getScene() {
		return this.scene;
	}

	public void stopClock() {
		if (root.getChildren().contains(clock)) {
			Platform.runLater(() -> {
				clock.stopClock();
			});
		}
	}
}
