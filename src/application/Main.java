package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import Animal.Cow;
import Animal.Hen;
import Animal.Sheep;
import ComponentMap.DialogCanvas;
import ComponentMap.SceneManager;
import Logic.Backpack;
import Logic.World;
import Map.AnimalShop;
import Map.Blacksmith;
import Map.CowBarn;
import Map.Farm;
import Map.HaveTime;
import Map.HenBarn;
import Map.House;
import Map.SeedShop;
import Map.Town;
import Map.Welcome;
import Map.setsceneable;
import Plant.PlantA;
import Plant.PlantB;
import Plant.PlantC;
import SpecialScene.AnimalBuyer;
import SpecialScene.AnimalMenu;
import SpecialScene.BlackSmithInterface;
import SpecialScene.BlackSmithMenu;
import SpecialScene.Inventory;
import SpecialScene.Profile;
import SpecialScene.SeedMenu;
import SpecialScene.Summary;
import SpecialScene.ToolMenu;
import SpecialScene.ToolStatus;
import SpecialScene.UpgradeBag;
import Tool.Axe;
import Tool.Hammer;
import Tool.Hand;
import Tool.Hoe;
import Tool.Milker;
import Tool.Scissors;
import Tool.SeedA;
import Tool.SeedB;
import Tool.SeedC;
import Tool.WateringCan;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application {

	public static boolean Pause = false;
	private static int currentTime;
	private static int hour;
	private static int minute;
	private static int checktime;
	private  Thread timerThread;
	public static String time="";


	@Override
	public void start(Stage primaryStage) {
		// --------------set up world---------------------//
		World w = new World();
		Backpack b = new Backpack();
		// ---------------set up tool---------------------//
		Axe axe = new Axe();
		Hammer hammer = new Hammer();
		Hoe hoe = new Hoe();
		WateringCan wateringcan = new WateringCan();
		Hand hand = new Hand();
		SeedA seed1 = new SeedA(5);
		SeedB seed2 = new SeedB(5);
		SeedC seed3 = new SeedC(5);
		Backpack.addItem(hand);
		Backpack.addItem(axe);
		Backpack.addItem(hammer);
		Backpack.addItem(hoe);
		Backpack.addItem(wateringcan);
		Backpack.addItem(seed1);
		Backpack.addItem(seed2);
		Backpack.addItem(seed3);
		// ---------------set up map---------------------//
		List<setsceneable> listmap = new ArrayList<>();
		Farm farm = new Farm(1000, 300);
		Town town = new Town(0, 560);
		House house = new House(603, 610);
		CowBarn cowbarn = new CowBarn(603, 610);
		HenBarn henbarn = new HenBarn(603, 610);
		Blacksmith blacksmith = new Blacksmith(603, 610);
		AnimalShop animalshop = new AnimalShop(0, 420);
		SeedShop seedshop = new SeedShop(0, 420);
		AnimalMenu animalmenu = new AnimalMenu("Cow", Cow.COST, "Sheep", Sheep.COST, "Hen", Hen.COST, 13);
		SeedMenu seedmenu = new SeedMenu("Radish", PlantA.SeedCost, "Potato", PlantB.SeedCost, "Cucumber",
				PlantC.SeedCost, 7);
		Welcome welcome = new Welcome();
		Profile profile = new Profile();
		ToolStatus toolstatus = new ToolStatus();
		AnimalBuyer animalbuyer = new AnimalBuyer();
		ToolMenu toolmenu = new ToolMenu("Milker", Milker.COST, "Scissors", Scissors.COST);
		Inventory inventory = new Inventory();
		BlackSmithMenu blackmenu = new BlackSmithMenu();
		UpgradeBag upgradebag = new UpgradeBag();
		BlackSmithInterface blackinter = new BlackSmithInterface();
		Summary summary = new Summary();

		listmap.add(farm); // 0
		listmap.add(town); // 1
		listmap.add(house); // 2
		listmap.add(cowbarn); // 3
		listmap.add(henbarn); // 4
		listmap.add(blacksmith); // 5
		listmap.add(animalshop); // 6
		listmap.add(seedshop); // 7
		listmap.add(animalmenu); // 8
		listmap.add(seedmenu); // 9
		listmap.add(welcome); // 10
		listmap.add(profile); // 11
		listmap.add(toolstatus); // 12
		listmap.add(animalbuyer); // 13
		listmap.add(toolmenu); // 14
		listmap.add(inventory); // 15
		listmap.add(blackmenu); // 16
		listmap.add(upgradebag); // 17
		listmap.add(blackinter); // 18
		listmap.add(summary); // 19
		//---------------------------------------------------------------------------------------------------------------------------

		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		SceneManager sm = new SceneManager(primaryStage, listmap);
		
		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				// clock(hour,minute);
			}
		};
		animation.start();

		// ------------------------------------------------Clock------------------------------------------------
		checktime = 0;
		currentTime = -1;
		hour = 5;
		minute = 0;
		timerThread = new Thread(() -> {
			while (true) {
//				System.out.println("Pause Game " + isPause());
				while (true) {
					try {
						Thread.sleep(10);
						if (isPause())
							break;
						if (World.isNextday())
							resetTime();
						checktime += 10;
						if (checktime == 2000 || currentTime == -1) {
							currentTime++;
							minute = (currentTime * 10) % 60;
							if (currentTime % 6 == 0) {
								hour = (hour + 1) % 24;
							}
							clock(hour, minute);
							checktime = 0;
						}
						if (currentTime == 120) {
							Platform.runLater(() -> {
								World.nextDay();
							});
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println("End Game");
						return;
					}
				}
			}
		});
		timerThread.setName("Timerthread");
		this.timerThread.start();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void clock(int hour, int minute) {
		String h;
		String m;

		if (hour < 10) {
			h = "0" + hour;
		} else
			h = "" + hour;

		if (minute < 10) {
			m = "0" + minute;
		} else
			m = "" + minute;
		// System.out.println(h + " : " + m);
		time = h + ":" + m;
	}

	public static boolean isPause() {
		return Pause;
	}

	public static void setPause(boolean pause) {
		Pause = pause;
	}

	public static void resetTime() {
		if (currentTime < 108) {
			hour = 5;
		} else if (currentTime < 120) {
			hour = 7;
		} else {
			hour = 9;
		}
		minute = 0;
		currentTime = -1 + (hour - 5) * 6;
		checktime = 0;
		time = "";
		World.setNextday(false);
	}

	public static String getTime() {
		return time;
	}

	@Override
	public void stop() throws InterruptedException {
		// TODO Auto-generated method stub
		this.timerThread.interrupt();
		for (setsceneable i : SceneManager.getListMap()) {
			if (i instanceof HaveTime) {
				((HaveTime) i).stopClock();
			}
		}
		SceneManager.stopMusic();
		Thread.interrupted();
		for (int i=0 ; i< SceneManager.getListMap().size();i++) {
			DialogCanvas.dialog.interrupt();
		}
		DialogCanvas.stopDialog();
		
		
	}
	
}
