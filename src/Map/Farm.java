package Map;

import java.util.ArrayList;
import java.util.List;

import ComponentMap.ClockCanvas;
import ComponentMap.DialogCanvas;
import ComponentMap.Dropbox;
import ComponentMap.Environment;
import ComponentMap.Feild;
import ComponentMap.Hero;
import ComponentMap.Pond;
import ComponentMap.ReceiveAction;
import ComponentMap.Stone;
import ComponentMap.Wood;
import Logic.World;
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

public class Farm implements setsceneable, HaveTime, HasDialog {
	private Group root;
	private Scene scene;
	private static Hero hero;
	private List<Environment> e = new ArrayList<>();
	private List<ReceiveAction> re = new ArrayList<>();
	private List<Pair<Rectangle, Integer>> WarpList = new ArrayList<>();
	private ClockCanvas clock = new ClockCanvas();

	public Farm(int starthx, int starthy) {
		root = new Group();
		scene = new Scene(root, 1280, 720);
		Canvas bg = new Canvas(1280, 720);
		GraphicsContext gc = bg.getGraphicsContext2D();
		Image Bg = new Image(ClassLoader.getSystemResource("BgFarm.png").toString());
		Image house = new Image(ClassLoader.getSystemResource("House2.png").toString());
		Image henhouse = new Image(ClassLoader.getSystemResource("HenHosue.png").toString());
		Image barn = new Image(ClassLoader.getSystemResource("Barn.png").toString());
		gc.drawImage(Bg, 0, 0);
		gc.drawImage(house, 900, 0);
		gc.drawImage(henhouse, 25, 0);
		gc.drawImage(barn, 365, 0);
		root.getChildren().add(bg);
		hero = new Hero(scene, starthx, starthy);

		// CreateEnvironment
		e.add(new Environment(-10, 0, 10, 720, Color.BLACK));// boarderLEFT
		e.add(new Environment(0, -10, 1280, 10, Color.BLACK));// boarderTOP
		e.add(new Environment(1280, 0, 10, 720, Color.BLACK));// boarderRIGHT
		e.add(new Environment(0, 720, 1280, 10, Color.BLACK));// boarderBOTTOM

		// -----------------------HenHouse---------------------------------//
		Environment henhouse1 = new Environment(0, 0, 90, 140, Color.BLACK);
		Environment henhouse2 = new Environment(90, 0, 120, 115, Color.BLACK);
		Environment henhouse3 = new Environment(210, 0, 60, 140, Color.BLACK);
		henhouse1.setOpacity(0);
		henhouse2.setOpacity(0);
		henhouse3.setOpacity(0);
		e.add(henhouse1);
		e.add(henhouse2);
		e.add(henhouse3);
		Rectangle warpblocktohenbarn = new Rectangle(90, 120, 120, 25);
		warpblocktohenbarn.setOpacity(0);
		root.getChildren().add(warpblocktohenbarn);
		Pair<Rectangle, Integer> tohenbarn = new Pair<Rectangle, Integer>(warpblocktohenbarn, 4);
		WarpList.add(tohenbarn);

		// -----------------------Barn---------------------------------//
		Environment barn1 = new Environment(380, 0, 60, 140, Color.BLACK);
		Environment barn2 = new Environment(440, 0, 120, 115, Color.BLACK);
		Environment barn3 = new Environment(560, 0, 40, 140, Color.BLACK);
		barn1.setOpacity(0);
		barn2.setOpacity(0);
		barn3.setOpacity(0);
		e.add(barn1);
		e.add(barn2);
		e.add(barn3);
		Rectangle warpblocktocowbarn = new Rectangle(440, 120, 110, 25);
		warpblocktocowbarn.setOpacity(0);
		root.getChildren().add(warpblocktocowbarn);
		Pair<Rectangle, Integer> tocowbarn = new Pair<Rectangle, Integer>(warpblocktocowbarn, 3);
		WarpList.add(tocowbarn);

		// -----------------------House---------------------------------//
		Environment house1 = new Environment(910, 0, 125, 210, Color.BLACK);
		Environment house2 = new Environment(1035, 0, 120, 180, Color.BLACK);
		Environment house3 = new Environment(1155, 0, 125, 210, Color.BLACK);
		house1.setOpacity(0);
		house2.setOpacity(0);
		house3.setOpacity(0);
		e.add(house1);
		e.add(house2);
		e.add(house3);
		Rectangle warpblocktohouse = new Rectangle(1050, 185, 110, 25);
		warpblocktohouse.setOpacity(0);
		root.getChildren().add(warpblocktohouse);
		Pair<Rectangle, Integer> tohouse = new Pair<Rectangle, Integer>(warpblocktohouse, 2);
		WarpList.add(tohouse);

		// ---------------------------Feild------------------------------//
		for (int i = 90; i < 800; i += 80) {
			for (int j = 220; j < 620; j += 80) {
				double random = Math.random();
				if (0 <= random && random < 0.7) {
					Feild r = new Feild(i, j, 80, 80, Color.rgb(185, 156, 107));
					re.add(r);
					World.getListUpdate().add(r);
				} else if (0.7 <= random && random < 0.85) {
					Stone s = new Stone();
					Feild r = new Feild(i, j, 80, 80, Color.rgb(185, 156, 107), s);
					re.add(r);
					World.getListUpdate().add(r);
				} else {
					Wood w = new Wood();
					Feild r = new Feild(i, j, 80, 80, Color.rgb(185, 156, 107), w);
					re.add(r);
					World.getListUpdate().add(r);
				}

			}
		}

		// ---------------------Add POND-----------------------//
		Environment pondenv = new Environment(910, 420, 280, 200, Color.AQUAMARINE);
		pondenv.setOpacity(0);
		e.add(pondenv);// Ponds
		Pond actionpond = new Pond(910, 420, 280, 200, Color.AQUAMARINE);
		actionpond.setOpacity(0);
		re.add(actionpond);// Pond

		// ---------------------Add dropbox-----------------------//
		Image dropbox = new Image(ClassLoader.getSystemResource("Dropbox2.png").toString());
		Environment dropboxenv = new Environment(720, 50, 90, 90, Color.BURLYWOOD);
		Dropbox dropboxact = new Dropbox(720, 50, 90, 90, Color.BURLYWOOD);
		World.getListUpdate().add(dropboxact);
		dropboxenv.setOpacity(0);
		dropboxact.setFill(new ImagePattern(dropbox));
		e.add(dropboxenv);
		re.add(dropboxact);

		root.getChildren().addAll(e);
		root.getChildren().addAll(re);

		Rectangle warpblocktotown = new Rectangle(1255, 210, 25, 165);
		warpblocktotown.setOpacity(0);
		root.getChildren().addAll(warpblocktotown);
		Pair<Rectangle, Integer> totown = new Pair<Rectangle, Integer>(warpblocktotown, 1);
		WarpList.add(totown);

		hero.addEnvlist(e);
		hero.addReclist(re);

		root.getChildren().addAll(hero.getUnitblock());
		for (Rectangle r : hero.getActionblock()) {
			root.getChildren().add(r);
		}
		hero.setWarpBlockList(WarpList);
	}

	public Scene getScene() {
		return this.scene;
	}

	public static List<Environment> getFarmEnvList() {
		return hero.getEnvList();
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
			root.getChildren().remove(d);
		}
	}
}
