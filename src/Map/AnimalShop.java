package Map;

import java.util.ArrayList;
import java.util.List;

import ComponentMap.Environment;
import ComponentMap.Hero;
import ComponentMap.ReceiveAction;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

public class AnimalShop implements setsceneable {
	private Group root;
	public Scene scene;
	private Hero hero;
	private List<Environment> e = new ArrayList<>();
	private List<ReceiveAction> re = new ArrayList<>();
	private List<Pair<Rectangle, Integer>> WarpList = new ArrayList<>();

	public AnimalShop(int starthx, int starthy) {
		root = new Group();
		scene = new Scene(root, 1280, 720);
		Canvas bg = new Canvas(1280, 720);
		GraphicsContext gc = bg.getGraphicsContext2D();
		gc.setFill(Color.SPRINGGREEN);
		gc.fillRect(0, 0, 1280, 720);
		root.getChildren().add(bg);

		// Boarder
		e.add(new Environment(-10, 0, 10, 720, Color.BLACK));// boarderLEFT
		e.add(new Environment(0, -10, 1280, 10, Color.BLACK));// boarderTOP
		e.add(new Environment(1280, 0, 10, 720, Color.BLACK));// boarderRIGHT
		e.add(new Environment(0, 720, 1280, 10, Color.BLACK));// boarderBOTTOM

		// shelf
		e.add(new Environment(100, 100, 720, 70, Color.SEAGREEN));
		e.add(new Environment(100, 530, 720, 70, Color.SEAGREEN));

		// counter
		e.add(new Environment(940, 160, 200, 70, Color.BLACK));
		e.add(new Environment(940, 230, 70, 250, Color.BLACK));
		e.add(new Environment(940, 480, 200, 70, Color.BLACK));
		e.add(new Environment(950, 285, 50, 100, Color.YELLOW));
		re.add(new ReceiveAction(950, 285, 50, 100, Color.YELLOW));
		
		//npc
		e.add(new Environment(1030, 300, 75, 75, Color.STEELBLUE));
		re.add(new ReceiveAction(1030, 300, 75, 75, Color.STEELBLUE));

		root.getChildren().addAll(e);
		root.getChildren().addAll(re);

		Rectangle warpblocktotown = new Rectangle(0, 280, 25, 120);
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