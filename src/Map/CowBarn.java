package Map;

import java.util.ArrayList;
import java.util.List;
import Animal.Animal;
import Animal.Cow;
import Animal.Sheep;
import ComponentMap.ClockCanvas;
import ComponentMap.DialogCanvas;
import ComponentMap.Environment;
import ComponentMap.Hero;
import ComponentMap.ReceiveAction;
import Logic.World;
import NPC.Counter;
import NPC.HayGetter;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;


public class CowBarn implements SetsSeneable, HasAnimal, HasTime , HasDialog {

	private Group root;
	public Scene scene;
	private Hero hero;
	private HayGetter haygetter;
	private int CurrentAnimal = 0;
	private List<Environment> e = new ArrayList<>();
	private List<ReceiveAction> re = new ArrayList<>();
	private List<Pair<Rectangle, Integer>> WarpList = new ArrayList<>();
	private Canvas barn;
	public static List<Pair<Integer, Integer>> position = new ArrayList<Pair<Integer, Integer>>();
	private ClockCanvas clock;
	public static final ImagePattern cowleftimg = new ImagePattern(
			new Image(ClassLoader.getSystemResource("cowleft.png").toString()));
	public static final ImagePattern sheepleftimg = new ImagePattern(
			new Image(ClassLoader.getSystemResource("sheepleft.png").toString()));
	public static final ImagePattern cowrightimg = new ImagePattern(
			new Image(ClassLoader.getSystemResource("cowright.png").toString()));
	public static final ImagePattern sheeprightimg = new ImagePattern(
			new Image(ClassLoader.getSystemResource("sheepright.png").toString()));

	public CowBarn(int starthx, int starthy) {
		root = new Group();
		scene = new Scene(root, 1280, 720);
		Canvas bg = new Canvas(1280, 720);
		GraphicsContext gc = bg.getGraphicsContext2D();
		Image background = new Image(ClassLoader.getSystemResource("inBarnhouse.png").toString());
		gc.drawImage(background, 0, 0);
		root.getChildren().add(bg);

		// Boarder
		e.add(new Environment(-10, 0, 10, 720, Color.BLACK));// boarderLEFT
		e.add(new Environment(0, 225, 1280, 10, Color.BLACK));// boarderTOP
		e.add(new Environment(1280, 0, 10, 720, Color.BLACK));// boarderRIGHT
		e.add(new Environment(0, 720, 1280, 10, Color.BLACK));// boarderBOTTOM
		// Partition LEFT
		e.add(new Environment(0, 273, 200, 15, Color.BLACK));
		e.add(new Environment(0, 417, 200, 15, Color.BLACK));
		e.add(new Environment(0, 561, 200, 15, Color.BLACK));
		// Partition RIGHT
		e.add(new Environment(1080, 273, 200, 15, Color.BLACK));
		e.add(new Environment(1080, 417, 200, 15, Color.BLACK));
		e.add(new Environment(1080, 561, 200, 15, Color.BLACK));
		// stall LEFT

		e.add(new Environment(0, 144, 200, 129, Color.CHOCOLATE));
		e.add(new Environment(0, 288, 200, 129, Color.CHOCOLATE));
		e.add(new Environment(0, 432, 200, 129, Color.CHOCOLATE));
		e.add(new Environment(0, 576, 200, 144, Color.CHOCOLATE));
		// stall RIGHT
		e.add(new Environment(1080, 144, 200, 129, Color.CHOCOLATE));
		e.add(new Environment(1080, 288, 200, 129, Color.CHOCOLATE));
		e.add(new Environment(1080, 432, 200, 129, Color.CHOCOLATE));
		e.add(new Environment(1080, 576, 200, 144, Color.CHOCOLATE));

		// HAY
		e.add(new Environment(600, 200, 150, 100, Color.BLACK));
		haygetter = new HayGetter(600, 200, 150, 100, Color.BLACK);
		re.add(haygetter);

		// root.getChildren().addAll(e);

		// stall LEFT
		position.add(new Pair<Integer, Integer>(100, 225));
		position.add(new Pair<Integer, Integer>(100, 340));
		position.add(new Pair<Integer, Integer>(100, 450));
		position.add(new Pair<Integer, Integer>(100, 580));
		// stall RIGHT
		position.add(new Pair<Integer, Integer>(1080, 225));
		position.add(new Pair<Integer, Integer>(1080, 340));
		position.add(new Pair<Integer, Integer>(1080, 450));
		position.add(new Pair<Integer, Integer>(1080, 580));

		// root.getChildren().addAll(re);

		Rectangle warpblocktofarm = new Rectangle(575, 695, 140, 30);
		warpblocktofarm.setFill(Color.RED);
		root.getChildren().add(warpblocktofarm);
		Pair<Rectangle, Integer> tofarm = new Pair<Rectangle, Integer>(warpblocktofarm, 0);
		WarpList.add(tofarm);

		hero = new Hero(scene, starthx, starthy, e, re, true);
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
			Animal x = Counter.animal.get(i);
			World.getListUpdate().add(x);
			if (Counter.animal.indexOf(x) <= 3) {
				if (x instanceof Cow) {
					re.add(x);
					Environment cow = new Environment((int) x.getX(), (int) x.getY(), 100, 100, Color.ALICEBLUE);
					cow.setFill(cowrightimg);
					e.add(cow);
					root.getChildren().add(x);
					root.getChildren().add(cow);
				} else if (x instanceof Sheep) {
					re.add(x);
					Environment sheep = new Environment((int) x.getX(), (int) x.getY(), 100, 100, Color.ANTIQUEWHITE);
					sheep.setFill(sheeprightimg);
					e.add(sheep);
					root.getChildren().add(x);
					root.getChildren().add(sheep);
				}

			} else {
				if (x instanceof Cow) {
					re.add(x);
					Environment cow = new Environment((int) x.getX(), (int) x.getY(), 100, 100, Color.ALICEBLUE);
					cow.setFill(cowleftimg);
					e.add(cow);
					root.getChildren().add(x);
					root.getChildren().add(cow);
				} else if (x instanceof Sheep) {
					re.add(x);
					Environment sheep = new Environment((int) x.getX(), (int) x.getY(), 100, 100, Color.ANTIQUEWHITE);
					sheep.setFill(sheepleftimg);
					e.add(sheep);
					root.getChildren().add(x);
					root.getChildren().add(sheep);
				}
			}
		}
	}

	public void update() {
		addAnimal();
		this.CurrentAnimal = getAnimalCount();
		barn = new Canvas(1280, 720);
		root.getChildren().add(barn);
	}

	public static int getAnimalCount() {
		return Cow.getCowCount() + Sheep.getSheepCount();
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
