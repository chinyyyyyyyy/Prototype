package Map;

import java.util.ArrayList;
import java.util.List;

import ComponentMap.ClockCanvas;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

public class Town implements setsceneable, HaveTime {
	private Group root;
	public Scene scene;
	private Hero hero;
	private List<Environment> e = new ArrayList<>();
	private List<ReceiveAction> re = new ArrayList<>();
	private List<Pair<Rectangle, Integer>> WarpList = new ArrayList<>();
	private Image fountainimg = new Image(ClassLoader.getSystemResource("fountain.gif").toString());
	private ClockCanvas clock = new ClockCanvas();

	public Town(int starthx, int starthy) {
		root = new Group();
		scene = new Scene(root, 1280, 720);
		Canvas bg = new Canvas(1280, 720);
		GraphicsContext gc = bg.getGraphicsContext2D();
		Image Bg = new Image(ClassLoader.getSystemResource("MapTown.png").toString());
		Image blacksmithimg = new Image(ClassLoader.getSystemResource("Blacksmith2.png").toString());
		Image petshopimg = new Image(ClassLoader.getSystemResource("Petshop2.png").toString());
		Image plantshopimg = new Image(ClassLoader.getSystemResource("Plantshop.png").toString());
		gc.drawImage(Bg, 0, 0);
		gc.drawImage(blacksmithimg, 27, 0);
		gc.drawImage(petshopimg, 1000, 300);
		gc.drawImage(plantshopimg, 1000, 0);
		root.getChildren().add(bg);

		e.add(new Environment(-10, 0, 10, 720, Color.BLACK));// boarderLEFT
		e.add(new Environment(0, -10, 1280, 10, Color.BLACK));// boarderTOP
		e.add(new Environment(1280, 0, 10, 720, Color.BLACK));// boarderRIGHT
		Environment tree = new Environment(0, 640, 1280, 10, Color.BLACK);
		tree.setOpacity(0);
		e.add(tree);// boarderBOTTOM

		// ------------------Black Smith------------------------------------//
		Environment blacksmith1 = new Environment(0, 0, 190, 300, Color.BLACK);
		Environment blacksmith2 = new Environment(190, 0, 120, 250, Color.BLACK);
		Environment blacksmith3 = new Environment(310, 0, 120, 300, Color.BLACK);
		blacksmith1.setOpacity(0);
		blacksmith2.setOpacity(0);
		blacksmith3.setOpacity(0);
		e.add(blacksmith1);
		e.add(blacksmith2);
		e.add(blacksmith3);
		Rectangle warpblocktoblacksmith = new Rectangle(195, 250, 100, 25);
		warpblocktoblacksmith.setOpacity(0);
		root.getChildren().add(warpblocktoblacksmith);
		Pair<Rectangle, Integer> toblacksmith = new Pair<Rectangle, Integer>(warpblocktoblacksmith, 5);
		WarpList.add(toblacksmith);

		// ---------------------- seed shop------------------------------------//
		Environment seedshop1 = new Environment(1020, 75, 260, 82, Color.BLACK);
		Environment seedshop2 = new Environment(1045, 157, 235, 120, Color.BLACK);
		Environment seedshop3 = new Environment(1020, 277, 260, 83, Color.BLACK);
		seedshop1.setOpacity(0);
		seedshop2.setOpacity(0);
		seedshop3.setOpacity(0);
		e.add(seedshop1);
		e.add(seedshop2);
		e.add(seedshop3);
		Rectangle warpblocktoseedshop = new Rectangle(1020, 157, 25, 120);
		warpblocktoseedshop.setOpacity(0);
		root.getChildren().addAll(warpblocktoseedshop);
		Pair<Rectangle, Integer> toseedshop = new Pair<Rectangle, Integer>(warpblocktoseedshop, 7);
		WarpList.add(toseedshop);

		// ------------------------Animal Shop------------------------------------//
		Environment animalshop1 = new Environment(1020, 385, 260, 82, Color.BLACK);
		Environment animalshop2 = new Environment(1045, 467, 235, 120, Color.BLACK);
		Environment animalshop3 = new Environment(1020, 587, 260, 83, Color.BLACK);
		animalshop1.setOpacity(0);
		animalshop2.setOpacity(0);
		animalshop3.setOpacity(0);
		e.add(animalshop1);
		e.add(animalshop2);
		e.add(animalshop3);
		Rectangle warpblocktoanimalshop = new Rectangle(1020, 467, 25, 120);
		warpblocktoanimalshop.setOpacity(0);
		root.getChildren().addAll(warpblocktoanimalshop);
		Pair<Rectangle, Integer> toanimalshop = new Pair<Rectangle, Integer>(warpblocktoanimalshop, 6);
		WarpList.add(toanimalshop);

		Environment fountain = new Environment(630, 300, 210, 210, Color.AQUA);
		fountain.setFill(new ImagePattern(fountainimg));
		e.add(fountain);// Fountain

		root.getChildren().addAll(e);

		Rectangle warpblocktofarm = new Rectangle(0, 520, 15, 120);
		warpblocktofarm.setOpacity(0);
		root.getChildren().addAll(warpblocktofarm);
		Pair<Rectangle, Integer> tofarm = new Pair<Rectangle, Integer>(warpblocktofarm, 0);
		WarpList.add(tofarm);

		hero = new Hero(scene, starthx, starthy, e, re,false);
		root.getChildren().addAll(hero.getUnitblock());
		for (Rectangle r : hero.getActionblock()) {
			root.getChildren().add(r);
		}
		hero.setWarpBlockList(WarpList);
	}

	public Scene getScene() {
		return this.scene;
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
	
	public void stopClock() {
		if (root.getChildren().contains(clock)) {
			Platform.runLater(() -> {
				clock.stopClock();
			});
		}
	}

}
