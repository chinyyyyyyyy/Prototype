package Map;

import java.util.ArrayList;
import java.util.List;

import ComponentMap.Bed;
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

public class House implements setsceneable{
	private Group root;
	public Scene scene;
	private Hero hero;
	private List<Environment> e = new ArrayList<>();
	private List<ReceiveAction> re = new ArrayList<>();
	private  List<Pair<Rectangle,Integer>> WarpList = new ArrayList<>();

	public House(int starthx, int starthy) {
		root = new Group();
		scene = new Scene(root, 1280, 720);
		Canvas bg = new Canvas(1280, 720);
		GraphicsContext gc = bg.getGraphicsContext2D();
		gc.setFill(Color.TAN);
		gc.fillRect(0, 0, 1280, 720);
		gc.setFill(Color.TOMATO);
		gc.fillOval(440,210,400, 300);
		root.getChildren().add(bg);

		//Boarder
		e.add(new Environment(-10, 0, 10, 720, Color.BLACK));// boarderLEFT
		e.add(new Environment(0, -10, 1280, 10, Color.BLACK));// boarderTOP
		e.add(new Environment(1280, 0, 10, 720, Color.BLACK));// boarderRIGHT
		e.add(new Environment(0, 720, 1280, 10, Color.BLACK));// boarderBOTTOM
		//Furniture
		e.add(new Environment(0, 0, 100, 100, Color.BLACK));// Lamp
		e.add(new Environment(0, 600, 150,120, Color.BLACK));//ToolBlock
		e.add(new Environment(490, 0, 300, 50, Color.BLACK));// TV
		e.add(new Environment(1080, 0, 200, 300, Color.BLACK));//BED
		e.add(new Environment(1265, 500, 15, 120, Color.BLACK));//Calendar
		root.getChildren().addAll(e);
		
		
		re.add(new ReceiveAction(490, 0, 300, 50, Color.MEDIUMPURPLE));// TV
		re.add(new Bed(1080, 0, 200, 300, Color.RED));//BED
		re.add(new ReceiveAction(1265, 500, 15, 120, Color.ALICEBLUE));//Calendar
		root.getChildren().addAll(re);
		
		Rectangle warpblocktofarm = new Rectangle(580,695,120,25);
		warpblocktofarm.setFill(Color.RED);
		root.getChildren().add(warpblocktofarm);
		Pair<Rectangle,Integer> tofarm = new Pair<Rectangle,Integer>(warpblocktofarm,0);
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
