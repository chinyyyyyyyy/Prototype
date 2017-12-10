package ComponentMap;

import java.util.ArrayList;
import java.util.List;

import Logic.World;
import Map.AnimalShop;
import Map.HasDialog;
import Map.HaveTime;
import Map.House;
import Map.setsceneable;
import NPC.Counter;
import SpecialScene.AnimalBuyer;
import SpecialScene.BuyInterface;
import SpecialScene.BuyScene;
import SpecialScene.SpecialScene;
import SpecialScene.Summary;
import application.Main;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Pair;

public class SceneManager extends Rectangle {
	private static Stage primaryStage;
	private static List<setsceneable> listmap = new ArrayList<>();
	private static int sceneNumber;
	private static int previousScene;
	private static int currentScene;

	public static AudioClip bgm = new AudioClip(ClassLoader.getSystemResource("bgm.mp3").toString());

	public SceneManager(Stage s, List<setsceneable> lm) {
		Main.setPause(true);
		primaryStage = s;
		listmap = lm;

		Group root = new Group();
		Scene scene = new Scene(root, 1280, 760);
		Canvas c = new Canvas(1280, 760);
		Image OpenSceneImg = new Image(ClassLoader.getSystemResource("MainMenu.jpg").toString());
		GraphicsContext gc = c.getGraphicsContext2D();
		gc.drawImage(OpenSceneImg, 0, 0);
		root.getChildren().add(c);

		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Harvest Sun");

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					primaryStage.setScene(lm.get(10).getScene());
					bgm.play();
				}
			}
		});

	}

	public static void warpTo(int mapno) {
		setPreviousScene(currentScene);
		if (listmap.get(mapno) instanceof HasAnimal) {
			((HasAnimal) listmap.get(mapno)).update();
		}

		if (listmap.get(mapno) instanceof SpecialScene) {
			((SpecialScene) listmap.get(mapno)).update();
			Main.setPause(true);
		}

		if (listmap.get(mapno) instanceof SpecialScene == false) {
			setSceneNumber(mapno);
			Main.setPause(false);
		}

		primaryStage.setScene(listmap.get(mapno).getScene());

		if (listmap.get(mapno) instanceof HaveTime) {
			((HaveTime) listmap.get(mapno)).removeClock();
			((HaveTime) listmap.get(mapno)).addClock();
			if (listmap.get(previousScene) instanceof HaveTime)
				((HaveTime) listmap.get(previousScene)).stopClock();
		}

		// --------------------------------------------------------Dialog--------------------------------------------------------
		if (listmap.get(mapno) instanceof HasDialog && listmap.get(mapno) instanceof HasNPC) {
			if (listmap.get(previousScene) instanceof SpecialScene == false) {
				((HasDialog) listmap.get(mapno)).chat(((HasNPC) listmap.get(mapno)).getNPC().Welcome());
			}else if(listmap.get(previousScene) instanceof BuyScene) {
				((HasDialog) listmap.get(mapno)).chat(BuyScene.getThank());			
			}else if (listmap.get(previousScene) instanceof BuyInterface) {
				((HasDialog) listmap.get(mapno)).chat(BuyScene.getThank2());
			}
		}

		if (listmap.get(mapno) instanceof BuyInterface && listmap.get(previousScene) instanceof BuyScene == false) {
			((BuyInterface) listmap.get(mapno)).chat(Counter.getQuestion());
		}
		
		if (listmap.get(mapno) instanceof Summary) {
			((Summary) listmap.get(mapno)).chat("Press Enter to continue");
		}
		
		if (listmap.get(mapno) instanceof House && listmap.get(previousScene) instanceof Summary) {
			((House) listmap.get(mapno)).chat("Good Morning " + Hero.getName(),"Today is day " + World.getDay());
		}

		// ---------------------------------------------------------------------------------------------------------------------------
		setCurrentScene(mapno);
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

	public static int getSceneNumber() {
		return sceneNumber;
	}

	public static void setSceneNumber(int sceneNumber) {
		SceneManager.sceneNumber = sceneNumber;
	}

	public static List<setsceneable> getListMap() {
		return listmap;
	}

	public static int getPreviousScene() {
		return previousScene;
	}

	public static void setPreviousScene(int previousScene) {
		SceneManager.previousScene = previousScene;
	}

	public static int getCurrentScene() {
		return currentScene;
	}

	public static void setCurrentScene(int currentScene) {
		SceneManager.currentScene = currentScene;
	}

	public static void stopMusic() {
		bgm.stop();
	}
}
