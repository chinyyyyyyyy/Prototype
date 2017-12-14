package Map;

import java.util.ArrayList;
import java.util.List;

import ComponentMap.ClockCanvas;
import ComponentMap.DialogCanvas;
import ComponentMap.Environment;
import ComponentMap.HasNPC;
import ComponentMap.Hero;
import ComponentMap.NPC;
import ComponentMap.ReceiveAction;
import NPC.BlackSmith;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

public class Blacksmith implements setsceneable, HasNPC, HaveTime,HasDialog {
	private Group root;
	public Scene scene;
	private Hero hero;
	private NPC npc;
	private BlackSmith blacksmith;
	private List<Environment> e = new ArrayList<>();
	private List<ReceiveAction> re = new ArrayList<>();
	private List<Pair<Rectangle, Integer>> WarpList = new ArrayList<>();
	private ClockCanvas clock = new ClockCanvas();

	public Blacksmith(int starthx, int starthy) {
		root = new Group();
		scene = new Scene(root, 1280, 720);
		Canvas bg = new Canvas(1280, 720);
		GraphicsContext gc = bg.getGraphicsContext2D();
		Image background = new Image(ClassLoader.getSystemResource("inBlacksmith.png").toString());
		Image furnance = new Image(ClassLoader.getSystemResource("furnance.png").toString());
		gc.drawImage(background, 0, 0);
		gc.drawImage(furnance, 640, 0);
		root.getChildren().add(bg);

		// Boarder
		e.add(new Environment(-10, 0, 10, 720, Color.BLACK));// boarderLEFT
		e.add(new Environment(0, 225, 1280, 10, Color.BLACK));// boarderTOP
		e.add(new Environment(1280, 0, 10, 720, Color.BLACK));// boarderRIGHT
		e.add(new Environment(0, 720, 1280, 10, Color.BLACK));// boarderBOTTOM

		// shelf
		e.add(new Environment(160, 350, 120, 240, Color.SADDLEBROWN));
		e.add(new Environment(0, 230, 150, 150, Color.SADDLEBROWN));

		// oven
		e.add(new Environment(640, 0, 200, 300, Color.BROWN));

		// npc
		e.add(new Environment(1080, 250, 180, 200, Color.STEELBLUE));
		blacksmith = new BlackSmith(1080, 250, 75, 75, Color.STEELBLUE, "Gill");
		npc = blacksmith;
		re.add(blacksmith);

		// root.getChildren().addAll(e);
		// root.getChildren().addAll(re);

		Rectangle warpblocktotown = new Rectangle(580, 695, 120, 25);
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