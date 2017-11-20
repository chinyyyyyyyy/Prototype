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

public class CowBarn {
	private Group root;
	public Scene scene;
	private Hero hero;
	private List<Environment> e = new ArrayList<>();
	private List<ReceiveAction> re = new ArrayList<>();

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
		re.add(new ReceiveAction(0, 0, 200, 129, Color.CHOCOLATE));
		re.add(new ReceiveAction(0, 144, 200, 129, Color.CHOCOLATE));
		re.add(new ReceiveAction(0, 288, 200, 129, Color.CHOCOLATE));
		re.add(new ReceiveAction(0, 432, 200, 129, Color.CHOCOLATE));
		re.add(new ReceiveAction(0, 576, 200, 144, Color.CHOCOLATE));
		// stall RIGHT
		re.add(new ReceiveAction(1080, 0, 200, 129, Color.CHOCOLATE));
		re.add(new ReceiveAction(1080, 144, 200, 129, Color.CHOCOLATE));
		re.add(new ReceiveAction(1080, 288, 200, 129, Color.CHOCOLATE));
		re.add(new ReceiveAction(1080, 432, 200, 129, Color.CHOCOLATE));
		re.add(new ReceiveAction(1080, 576, 200, 144, Color.CHOCOLATE));
		//HAY
		re.add(new ReceiveAction(540, 0, 200, 150, Color.BLACK));

		root.getChildren().addAll(re);

		hero = new Hero(scene, starthx, starthy, e, re);
		root.getChildren().addAll(hero.getUnitblock());
		for (Rectangle r : hero.getActionblock()) {
			root.getChildren().add(r);
		}
	}

	public Scene getCowBarnScene() {
		return this.scene;
	}
}
