package Map;

import java.util.ArrayList;
import java.util.List;

import ComponentMap.ClockCanvas;
import ComponentMap.Environment;
import ComponentMap.HasNPC;
import ComponentMap.Hero;
import ComponentMap.NPC;
import ComponentMap.ReceiveAction;
import NPC.AnimalSeller;
import NPC.Counter;
import NPC.CounterAnimal;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

public class AnimalShop implements setsceneable, HasNPC, HaveTime {
	private Group root;
	public Scene scene;
	private Hero hero;
	private AnimalSeller animalseller;
	private NPC npc;
	private List<Environment> e = new ArrayList<>();
	private List<ReceiveAction> re = new ArrayList<>();
	private List<Pair<Rectangle, Integer>> WarpList = new ArrayList<>();
	private ClockCanvas clock = new ClockCanvas();

	public AnimalShop(int starthx, int starthy) {
		root = new Group();
		scene = new Scene(root, 1280, 720);
		Canvas bg = new Canvas(1280, 720);
		GraphicsContext gc = bg.getGraphicsContext2D();
		Image background = new Image(ClassLoader.getSystemResource("inAnimalshop.png").toString());
		gc.drawImage(background, 0, 0);
		root.getChildren().add(bg);

		// Boarder
		e.add(new Environment(-10, 0, 10, 720, Color.BLACK));// boarderLEFT
		e.add(new Environment(0, 225, 1280, 10, Color.BLACK));// boarderTOP
		e.add(new Environment(1280, 0, 10, 720, Color.BLACK));// boarderRIGHT
		e.add(new Environment(0, 720, 1280, 10, Color.BLACK));// boarderBOTTOM

		// shelf
		e.add(new Environment(0, 200, 600, 105, Color.SEAGREEN));
		e.add(new Environment(0, 550, 1000, 100, Color.SEAGREEN));
		e.add(new Environment(1010, 225, 275, 470, Color.SEAGREEN));

		// counter
		e.add(new Environment(765, 347, 70, 185, Color.BLACK));
		Counter counter = new CounterAnimal(765, 392, 50, 100, Color.YELLOW);
		re.add(counter);
		
		//npc
		e.add(new Environment(850, 412, 75, 75, Color.STEELBLUE));
		animalseller = new AnimalSeller(850, 412, 80, 80, Color.STEELBLUE,"Peter");

		npc = animalseller;
		re.add(animalseller);

		// root.getChildren().addAll(e);
		// root.getChildren().addAll(re);

		Rectangle warpblocktotown = new Rectangle(0, 420, 25, 100);
		warpblocktotown.setFill(Color.RED);
		// root.getChildren().add(warpblocktotown);
		Pair<Rectangle, Integer> totown = new Pair<Rectangle, Integer>(warpblocktotown, 1);
		WarpList.add(totown);

		hero = new Hero(scene, starthx, starthy, e, re,true);
		root.getChildren().addAll(hero.getUnitblock());
		for (Rectangle r : hero.getActionblock()) {
			root.getChildren().add(r);
		}

		hero.setWarpBlockList(WarpList);
	}

	public Scene getScene() {
		return this.scene;
	}

	public NPC getNPC() {
		return this.npc;
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