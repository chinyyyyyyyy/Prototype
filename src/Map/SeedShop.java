package Map;

import java.util.ArrayList;
import java.util.List;

import ComponentMap.ClockCanvas;
import ComponentMap.DialogCanvas;
import ComponentMap.Environment;
import ComponentMap.Hero;
import ComponentMap.ReceiveAction;
import NPC.Counter;
import NPC.CounterSeed;
import NPC.Florist;
import NPC.NPC;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

public class SeedShop implements SetsSeneable, HasNPC, HasTime ,HasDialog{
	private Group root;
	public Scene scene;
	private Hero hero;
	private Florist florist;
	private NPC npc;
	private List<Environment> e = new ArrayList<>();
	private List<ReceiveAction> re = new ArrayList<>();
	private List<Pair<Rectangle, Integer>> WarpList = new ArrayList<>();
	private ClockCanvas clock = new ClockCanvas();

	public SeedShop(int starthx, int starthy) {
		root = new Group();
		scene = new Scene(root, 1280, 720);
		Canvas bg = new Canvas(1280, 720);
		GraphicsContext gc = bg.getGraphicsContext2D();
		Image background = new Image(ClassLoader.getSystemResource("inSeedshop.png").toString());
		gc.drawImage(background, 0, 0);
		root.getChildren().add(bg);

		// Boarder
		e.add(new Environment(-10, 0, 10, 720, Color.BLACK));// boarderLEFT
		e.add(new Environment(0, 225, 1280, 10, Color.BLACK));// boarderTOP
		e.add(new Environment(1280, 0, 10, 720, Color.BLACK));// boarderRIGHT
		e.add(new Environment(0, 720, 1280, 10, Color.BLACK));// boarderBOTTOM

		// shelf
		e.add(new Environment(135, 300, 800, 70, Color.LIMEGREEN));
		e.add(new Environment(135, 550, 800, 70, Color.LIMEGREEN));
		e.add(new Environment(1155, 465, 100, 370, Color.LIMEGREEN));

		// counter
		e.add(new Environment(1090, 350, 60, 145, Color.BLACK));
		e.add(new Environment(1170, 370, 75, 75, Color.BLACK));
		Counter counter = new CounterSeed(1090, 370, 60, 100, Color.YELLOW);
		re.add(counter);

		// npc
		florist = new Florist(1170, 370, 75, 75, Color.STEELBLUE, "Flora");
		npc = florist;
		re.add(florist);

		// root.getChildren().addAll(e);
		// root.getChildren().addAll(re);

		Rectangle warpblocktotown = new Rectangle(0, 350, 25, 170);
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
}