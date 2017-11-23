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

public class Town implements setsceneable {
	private Group root;
	public Scene scene;
	private Hero hero;
	private List<Environment> e = new ArrayList<>();
	private List<ReceiveAction> re = new ArrayList<>();
	private List<Pair<Rectangle, Integer>> WarpList = new ArrayList<>();

	public Town(int starthx, int starthy) {
		root = new Group();
		scene = new Scene(root, 1280, 720);
		Canvas bg = new Canvas(1280, 720);
		GraphicsContext gc = bg.getGraphicsContext2D();
		gc.setFill(Color.gray(0.9));
		gc.fillRect(0, 0, 1280, 720);
		root.getChildren().add(bg);

		e.add(new Environment(-10, 0, 10, 720, Color.BLACK));// boarderLEFT
		e.add(new Environment(0, -10, 1280, 10, Color.BLACK));// boarderTOP
		e.add(new Environment(1280, 0, 10, 720, Color.BLACK));// boarderRIGHT
		e.add(new Environment(0, 720, 1280, 10, Color.BLACK));// boarderBOTTOM

		e.add(new Environment(0, 0, 190, 270, Color.BLACK));// Blacksmith
		e.add(new Environment(190, 0, 120, 245, Color.BLACK));// Blacksmith
		e.add(new Environment(310, 0, 190, 270, Color.BLACK));// Blacksmith
		Rectangle warpblocktoblacksmith = new Rectangle(190, 245, 120, 25);
		warpblocktoblacksmith.setFill(Color.RED);
		root.getChildren().add(warpblocktoblacksmith);
		Pair<Rectangle, Integer> toblacksmith = new Pair<Rectangle, Integer>(warpblocktoblacksmith, 5);
		WarpList.add(toblacksmith);

		e.add(new Environment(1020, 0, 260, 82, Color.BLACK));// Seed Shop
		e.add(new Environment(1045, 82, 235, 120, Color.BLACK));// Seed Shop
		e.add(new Environment(1020, 202, 260, 83, Color.BLACK));// Seed Shop
		Rectangle warpblocktoseedshop = new Rectangle(1020, 82, 25, 120);
		warpblocktoseedshop.setFill(Color.RED);
		root.getChildren().addAll(warpblocktoseedshop);
		Pair<Rectangle, Integer> toseedshop = new Pair<Rectangle, Integer>(warpblocktoseedshop, 7);
		WarpList.add(toseedshop);
		
		e.add(new Environment(1020, 300, 260, 82, Color.BLACK));// Animal Shop
		e.add(new Environment(1045, 382, 235, 120, Color.BLACK));// Animal Shop
		e.add(new Environment(1020, 502, 260, 83, Color.BLACK));// Animal Shop
		Rectangle warpblocktoanimalshop = new Rectangle(1020, 382, 25, 120);
		warpblocktoanimalshop.setFill(Color.RED);
		root.getChildren().addAll(warpblocktoanimalshop);
		Pair<Rectangle, Integer> toanimalshop = new Pair<Rectangle, Integer>(warpblocktoanimalshop, 6);
		WarpList.add(toanimalshop);
		
		e.add(new Environment(630, 300, 225, 225, Color.AQUA));// Fountain

		root.getChildren().addAll(e);

		Rectangle warpblocktofarm = new Rectangle(0, 540, 25, 120);
		warpblocktofarm.setFill(Color.RED);
		root.getChildren().addAll(warpblocktofarm);
		Pair<Rectangle, Integer> tofarm = new Pair<Rectangle, Integer>(warpblocktofarm, 0);
		WarpList.add(tofarm);

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
