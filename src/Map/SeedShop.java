package Map;

import java.util.ArrayList;
import java.util.List;

import ComponentMap.Environment;
import ComponentMap.HasNPC;
import ComponentMap.Hero;
import ComponentMap.NPC;
import ComponentMap.ReceiveAction;
import NPC.Counter;
import NPC.Florist;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

public class SeedShop implements setsceneable,HasNPC {
	private Group root;
	public Scene scene;
	private Hero hero;
	private Florist florist;
	private NPC npc;
	private List<Environment> e = new ArrayList<>();
	private List<ReceiveAction> re = new ArrayList<>();
	private List<Pair<Rectangle, Integer>> WarpList = new ArrayList<>();

	public SeedShop(int starthx, int starthy) {
		root = new Group();
		scene = new Scene(root, 1280, 720);
		Canvas bg = new Canvas(1280, 720);
		GraphicsContext gc = bg.getGraphicsContext2D();
		gc.setFill(Color.LIGHTSKYBLUE);
		gc.fillRect(0, 0, 1280, 720);
		root.getChildren().add(bg);

		// Boarder
		e.add(new Environment(-10, 0, 10, 720, Color.BLACK));// boarderLEFT
		e.add(new Environment(0, -10, 1280, 10, Color.BLACK));// boarderTOP
		e.add(new Environment(1280, 0, 10, 720, Color.BLACK));// boarderRIGHT
		e.add(new Environment(0, 720, 1280, 10, Color.BLACK));// boarderBOTTOM

		// shelf
		e.add(new Environment(100, 100, 640, 70, Color.LIMEGREEN));
		e.add(new Environment(100, 530, 640, 70, Color.LIMEGREEN));

		// counter
		e.add(new Environment(850, 90, 70, 200, Color.BLACK));
		e.add(new Environment(920, 220, 250, 70, Color.BLACK));
		e.add(new Environment(1170, 90, 70, 200, Color.BLACK));
		e.add(new Environment(1000, 230, 100, 50, Color.YELLOW));
		Counter counter = new Counter(1000, 230, 100, 50, Color.YELLOW);
		re.add(counter);
		
		
		//npc 
		e.add(new Environment(1010, 125, 75, 75, Color.STEELBLUE));
		florist = new Florist(1010, 125, 75, 75, Color.STEELBLUE,"Ann");
		npc = florist;
		re.add(florist);

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
	
	public NPC getNPC() {
		return this.npc;
	}
}