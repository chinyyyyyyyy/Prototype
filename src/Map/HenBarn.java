package Map;

import java.util.ArrayList;
import java.util.List;
import Animal.Animal;
import Animal.Hen;
import ComponentMap.Environment;
import ComponentMap.HasAnimal;
import ComponentMap.Hero;
import ComponentMap.ReceiveAction;
import Logic.World;
import NPC.Counter;
import NPC.HayGetter;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

public class HenBarn implements setsceneable, HasAnimal {
	private Group root;
	public Scene scene;
	private Hero hero;
	private HayGetter haygetter;
	private int CurrentAnimal = 0;
	private List<Environment> e = new ArrayList<>();
	private List<ReceiveAction> re = new ArrayList<>();
	private List<Pair<Rectangle, Integer>> WarpList = new ArrayList<>();
	public static List<Pair<Integer, Integer>> position = new ArrayList<Pair<Integer, Integer>>();
	public static List<Pair<Integer, Integer>> actionposition = new ArrayList<Pair<Integer, Integer>>();
	private Canvas bg;
	private Canvas egg;

	public HenBarn(int starthx, int starthy) {
		root = new Group();
		scene = new Scene(root, 1280, 720);
		bg = new Canvas(1280, 720);
		GraphicsContext gc = bg.getGraphicsContext2D();
		Image background = new Image(ClassLoader.getSystemResource("inHenhouse.png").toString());
		gc.drawImage(background,0,0);
		root.getChildren().add(bg);

		// Boarder
		e.add(new Environment(-10, 0, 10, 720, Color.BLACK));// boarderLEFT
		e.add(new Environment(0, 225, 1280, 10, Color.BLACK));// boarderTOP
		e.add(new Environment(1280, 0, 10, 720, Color.BLACK));// boarderRIGHT
		e.add(new Environment(0, 720, 1280, 10, Color.BLACK));// boarderBOTTOM
		// Partition TOP
		e.add(new Environment(300, 0, 15, 200, Color.BLACK));
		e.add(new Environment(433, 0, 15, 200, Color.BLACK));
		e.add(new Environment(566, 0, 15, 200, Color.BLACK));
		e.add(new Environment(699, 0, 15, 200, Color.BLACK));
		e.add(new Environment(832, 0, 15, 200, Color.BLACK));
		e.add(new Environment(965, 0, 15, 200, Color.BLACK));
		// stall TOP
		e.add(new Environment(315, 0, 118, 200, Color.CHOCOLATE));
		e.add(new Environment(448, 0, 118, 200, Color.CHOCOLATE));
		e.add(new Environment(581, 0, 118, 200, Color.CHOCOLATE));
		e.add(new Environment(714, 0, 118, 200, Color.CHOCOLATE));
		e.add(new Environment(847, 0, 118, 200, Color.CHOCOLATE));
		
		// Hay
		e.add(new Environment(0, 550, 150, 200, Color.BLACK));
		haygetter = new HayGetter(0, 550, 150, 200, Color.BLACK);
		re.add(haygetter);

		// Hen
		position.add(new Pair<Integer, Integer>(334, 260));
		position.add(new Pair<Integer, Integer>(467, 260));
		position.add(new Pair<Integer, Integer>(600, 260));
		position.add(new Pair<Integer, Integer>(733, 260));
		position.add(new Pair<Integer, Integer>(866, 260));
		actionposition.add(new Pair<Integer, Integer>(334, 340));
		actionposition.add(new Pair<Integer, Integer>(467, 340));
		actionposition.add(new Pair<Integer, Integer>(600, 340));
		actionposition.add(new Pair<Integer, Integer>(733, 340));
		actionposition.add(new Pair<Integer, Integer>(866, 340));
		
//		root.getChildren().addAll(e);
//		root.getChildren().addAll(re);

		Rectangle warpblocktofarm = new Rectangle(580, 695, 120, 25);
		warpblocktofarm.setFill(Color.RED);
		root.getChildren().add(warpblocktofarm);
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

	public void addAnimal() throws IndexOutOfBoundsException {
		for (int i = this.CurrentAnimal; i < getAnimalCount(); i++) {
			Animal x = Counter.hen.get(i);
			World.getListUpdate().add(x);
			if (x instanceof Hen) {
				re.add(x);
				Environment hen = new Environment(position.get(i).getKey(), position.get(i).getValue(), 80, 80,
						Color.ORANGERED);
				Environment egg = new Environment(actionposition.get(i).getKey(), actionposition.get(i).getValue(), 80, 80,
						Color.LIMEGREEN);
				e.add(hen);
				e.add(egg);
				root.getChildren().add(x);
				root.getChildren().add(hen);
			}
		}
	}

	public void update() {
		addAnimal();
		this.CurrentAnimal = getAnimalCount();
		egg = new Canvas(1280, 720);
		GraphicsContext gc = egg.getGraphicsContext2D();
		for (int i = 0; i < this.CurrentAnimal; i++) {
			if (Counter.hen.get(i).getProduceable()) {
				gc.setFill(Color.WHITE);
				gc.fillOval(actionposition.get(i).getKey() + 25, actionposition.get(i).getValue() + 20, 40, 50);
			} else {
				gc.setFill(Color.LIMEGREEN);
				gc.fillRect(actionposition.get(i).getKey(), actionposition.get(i).getValue(), 80, 80);
			}
		}
		root.getChildren().add(egg);
	}

	public static int getAnimalCount() {
		return Hen.getHenCount();
	}
}