package Map;

import java.util.ArrayList;
import java.util.List;

import ComponentMap.Environment;
import ComponentMap.Hero;
import ComponentMap.ReceiveAction;
import NPC.BlackSmith;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

public class Blacksmith implements setsceneable {
	private Group root;
	public Scene scene;
	private Hero hero;
	private List<Environment> e = new ArrayList<>();
	private List<ReceiveAction> re = new ArrayList<>();
	private List<Pair<Rectangle, Integer>> WarpList = new ArrayList<>();

	public Blacksmith(int starthx, int starthy) {
		root = new Group();
		scene = new Scene(root, 1280, 720);
		Canvas bg = new Canvas(1280, 720);
		GraphicsContext gc = bg.getGraphicsContext2D();
		gc.setFill(Color.BURLYWOOD);
		gc.fillRect(0, 0, 1280, 720);
		root.getChildren().add(bg);

		// Boarder
		e.add(new Environment(-10, 0, 10, 720, Color.BLACK));// boarderLEFT
		e.add(new Environment(0, -10, 1280, 10, Color.BLACK));// boarderTOP
		e.add(new Environment(1280, 0, 10, 720, Color.BLACK));// boarderRIGHT
		e.add(new Environment(0, 720, 1280, 10, Color.BLACK));// boarderBOTTOM

		// shelf
		e.add(new Environment(100, 50, 100, 620, Color.SADDLEBROWN));

		// oven
		e.add(new Environment(640, 0, 400, 170, Color.BROWN));
		e.add(new Environment(715, 40, 250, 130, Color.ORANGE));
		
		//npc
		e.add(new Environment(1030, 250, 75, 75, Color.STEELBLUE));
		BlackSmith blacksmith = new BlackSmith(1030, 250, 75, 75, Color.STEELBLUE,"Kuy");
//		blacksmith.Welcome();
		re.add(blacksmith);

		root.getChildren().addAll(e);
		root.getChildren().addAll(re);

		Rectangle warpblocktotown = new Rectangle(580, 695, 120, 25);
		warpblocktotown.setFill(Color.RED);
		root.getChildren().add(warpblocktotown);
		Pair<Rectangle, Integer> totown = new Pair<Rectangle, Integer>(warpblocktotown, 1);
		WarpList.add(totown);

		hero = new Hero(scene, starthx, starthy, e, re);
		root.getChildren().addAll(hero.getUnitblock());
		for (Rectangle r : hero.getActionblock()) {
			root.getChildren().add(r);
		}

		hero.setWarpBlockList(WarpList);
	}

	public Scene getScene() {
		return this.scene;
	}
}