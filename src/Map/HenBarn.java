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

public class HenBarn {
	private Group root;
	public Scene scene;
	private Hero hero;
	private List<Environment> e = new ArrayList<>();
	private List<ReceiveAction> re = new ArrayList<>();

	public HenBarn(int starthx, int starthy) {
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
		//Hay
		e.add(new Environment(0, 400, 150, 200, Color.BLACK));


		root.getChildren().addAll(e);



		// re.add(new ReceiveAction(490, 0, 300, 50, Color.MEDIUMPURPLE));// TV
		
		re.add(new ReceiveAction(315, 0, 118, 200, Color.CHOCOLATE));
		re.add(new ReceiveAction(448, 0, 118, 200, Color.CHOCOLATE));
		re.add(new ReceiveAction(581, 0, 118, 200, Color.CHOCOLATE));
		re.add(new ReceiveAction(714, 0, 118, 200, Color.CHOCOLATE));
		re.add(new ReceiveAction(847, 0, 118, 200, Color.CHOCOLATE));
		//HAY
		e.add(new Environment(0, 400, 150, 200, Color.BLACK));
		root.getChildren().addAll(re);

		hero = new Hero(scene, starthx, starthy, e, re);
		root.getChildren().addAll(hero.getUnitblock());
		for (Rectangle r : hero.getActionblock()) {
			root.getChildren().add(r);
		}
	}

	public Scene getHenBarnScene() {
		return this.scene;
	}
}