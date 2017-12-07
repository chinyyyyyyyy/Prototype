package Map;

import java.util.ArrayList;
import java.util.List;

import Animal.Animal;
import Animal.Cow;
import Animal.Sheep;
import ComponentMap.Environment;
import ComponentMap.HasAnimal;
import ComponentMap.Hero;
import ComponentMap.ReceiveAction;
import Logic.World;
import NPC.Counter;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

public class CowBarn implements setsceneable,HasAnimal{
	private Group root;
	public Scene scene;
	private Hero hero;
	private int CurrentAnimal = 0;
	private List<Environment> e = new ArrayList<>();
	private List<ReceiveAction> re = new ArrayList<>();
	private  List<Pair<Rectangle,Integer>> WarpList = new ArrayList<>();
	public static List<Pair<Integer,Integer> > position = new ArrayList<Pair<Integer,Integer> >();

	public CowBarn(int starthx, int starthy) {
		root = new Group();
		scene = new Scene(root, 1280, 720);
		Canvas bg = new Canvas(1280, 720);
		GraphicsContext gc = bg.getGraphicsContext2D();
		gc.setFill(Color.TAN);
		gc.fillRect(0, 0, 1280, 720);
		root.getChildren().add(bg);

		// Boarder
		e.add(new Environment(-10, 0, 10, 720, Color.BLACK));// boarderLEFT
		e.add(new Environment(0, -10, 1280, 10, Color.BLACK));// boarderTOP
		e.add(new Environment(1280, 0, 10, 720, Color.BLACK));// boarderRIGHT
		e.add(new Environment(0, 720, 1280, 10, Color.BLACK));// boarderBOTTOM
		// Partition LEFT
		e.add(new Environment(0, 129, 200, 15, Color.BLACK));
		e.add(new Environment(0, 273, 200, 15, Color.BLACK));
		e.add(new Environment(0, 417, 200, 15, Color.BLACK));
		e.add(new Environment(0, 561, 200, 15, Color.BLACK));
		// Partition RIGHT
		e.add(new Environment(1080, 129, 200, 15, Color.BLACK));
		e.add(new Environment(1080, 273, 200, 15, Color.BLACK));
		e.add(new Environment(1080, 417, 200, 15, Color.BLACK));
		e.add(new Environment(1080, 561, 200, 15, Color.BLACK));
		// stall LEFT
		e.add(new Environment(0, 0, 200, 129, Color.CHOCOLATE));
		e.add(new Environment(0, 144, 200, 129, Color.CHOCOLATE));
		e.add(new Environment(0, 288, 200, 129, Color.CHOCOLATE));
		e.add(new Environment(0, 432, 200, 129, Color.CHOCOLATE));
		e.add(new Environment(0, 576, 200, 144, Color.CHOCOLATE));
		// stall RIGHT
		e.add(new Environment(1080, 0, 200, 129, Color.CHOCOLATE));
		e.add(new Environment(1080, 144, 200, 129, Color.CHOCOLATE));
		e.add(new Environment(1080, 288, 200, 129, Color.CHOCOLATE));
		e.add(new Environment(1080, 432, 200, 129, Color.CHOCOLATE));
		e.add(new Environment(1080, 576, 200, 144, Color.CHOCOLATE));
		//HAY
		e.add(new Environment(540, 0, 200, 150, Color.BLACK));

		root.getChildren().addAll(e);

		// stall LEFT
		position.add(new Pair<Integer, Integer>(100,15));
		position.add(new Pair<Integer, Integer>(100,159));
		position.add(new Pair<Integer, Integer>(100,303));
		position.add(new Pair<Integer, Integer>(100,447));
		position.add(new Pair<Integer, Integer>(100,598));
		// stall RIGHT
		position.add(new Pair<Integer, Integer>(1080,15));
		position.add(new Pair<Integer, Integer>(1080,169));
		position.add(new Pair<Integer, Integer>(1080,303));
		position.add(new Pair<Integer, Integer>(1080,447));
		position.add(new Pair<Integer, Integer>(1080,598));
		
		//HAY
		re.add(new ReceiveAction(540, 0, 200, 150, Color.BLACK));
		
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
	
	public void addAnimal() throws IndexOutOfBoundsException{
		for(int i = this.CurrentAnimal ; i< getAnimalCount() ; i++) {
			Animal x = Counter.animal.get(i);
			World.getListUpdate().add(x);
			if(x instanceof Cow) {
				re.add(x);
				Environment cow = new Environment((int) x.getX(),(int) x.getY(),100,100,Color.ALICEBLUE);
				e.add(cow);
				root.getChildren().add(x);
				root.getChildren().add(cow);
			}else if(x instanceof Sheep) {
				re.add(x);
				Environment sheep = new Environment((int) x.getX(),(int) x.getY(),100,100,Color.ANTIQUEWHITE);
				e.add(sheep);
				root.getChildren().add(x);
				root.getChildren().add(sheep);
			}
		}
	}
	
	public void update() {
		addAnimal();
		this.CurrentAnimal = getAnimalCount();
	}
	
	public static int getAnimalCount() {
		return Cow.getCowCount()+Sheep.getSheepCount();
	}
}
