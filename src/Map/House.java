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
import javafx.scene.image.Image;
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
		Image background = new Image(ClassLoader.getSystemResource("inHosue2.png").toString());
		gc.drawImage(background,0,0);
		//gc.setFill(Color.TOMATO);
		//gc.fillOval(440,210,400, 300);
		root.getChildren().add(bg);

		//Boarder
		e.add(new Environment(-10, 0, 10, 720, Color.BLACK));// boarderLEFT
		e.add(new Environment(0,200, 1280, 10, Color.BLACK));// boarderTOP
		e.add(new Environment(1280, 0, 10, 720, Color.BLACK));// boarderRIGHT
		e.add(new Environment(0, 720, 1280, 10, Color.BLACK));// boarderBOTTOM
		//Furniture
		e.add(new Environment(0, 600, 150,120, Color.BLACK));//ToolBlock
		e.add(new Environment(470, 200, 350, 50, Color.BLACK));// TV
		e.add(new Environment(1100, 100, 200, 300, Color.BLACK));//BED
		e.add(new Environment(820, 200, 95, 80, Color.BLACK));//box1
		e.add(new Environment(1010, 200, 95, 80, Color.BLACK));//box2
		//root.getChildren().addAll(e);
		
		
		re.add(new ReceiveAction(650, 200, 170, 50, Color.MEDIUMPURPLE));// TV
		re.add(new Bed(1100, 100, 230, 300, Color.RED));//BED
		//root.getChildren().addAll(re);
		
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
