package ComponentMap;

import java.util.ArrayList;
import java.util.List;

import Logic.Backpack;
import Map.setsceneable;
import NPC.Counter;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Pair;

public class SceneManager extends Rectangle {
	private static Stage primaryStage;
	private static List<setsceneable> listmap = new ArrayList<>();

	public SceneManager(Stage s, List<setsceneable> lm) {
		primaryStage = s;
		listmap = lm;
		
		Group root = new Group();
		Scene scene = new Scene(root,1280,760);
		Canvas c = new Canvas(1280,760);
		Image OpenSceneImg = new Image(ClassLoader.getSystemResource("MainMenu.jpg").toString());
		GraphicsContext gc = c.getGraphicsContext2D();
		gc.drawImage(OpenSceneImg ,0,0);
		root.getChildren().add(c);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Harvest Sun");
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					primaryStage.setScene(lm.get(0).getScene());
				} 
			}
		});
		

	}

	public static void warpTo(int mapno) {
		if (listmap.get(mapno) instanceof HasAnimal) {
			((HasAnimal) listmap.get(mapno)).update();
		}
		primaryStage.setScene(listmap.get(mapno).getScene());
		if (listmap.get(mapno) instanceof HasNPC) {
			((HasNPC) listmap.get(mapno)).getNPC().Welcome();
		}
	}

	public static int CheckOnWarpBlock(List<Pair<Rectangle, Integer>> WarpList, Rectangle unitblock) {
		int pos = -1;
		for (Pair<Rectangle, Integer> p : WarpList) {
			Rectangle r = p.getKey();
			Shape intersect = Shape.intersect(r, unitblock);
			double wi = intersect.getBoundsInLocal().getWidth();
			double hi = intersect.getBoundsInLocal().getHeight();
			double wr = unitblock.getWidth();
			double hr = unitblock.getHeight();
			double wa = r.getWidth();
			double ha = r.getHeight();
			if (wr < wa && hr < ha) {
				if (wi == wr && hi == hr)
					pos = p.getValue();
				;
			} else if (wr >= wa && hr < ha) {
				if (wi >= 0.8 * wa && hi == hr)
					pos = p.getValue();
				;
			} else if (wr < wa && hr >= ha) {
				if (wi == wr && hi >= 0.8 * ha)
					pos = p.getValue();
				;
			} else if (wr >= wa && hr >= ha) {
				if (wi >= 0.8 * wa && hi >= 0.8 * ha)
					pos = p.getValue();
				;
			}
		}
		return pos;

	}

}
