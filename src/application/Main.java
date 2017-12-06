package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import Animal.Cow;
import Animal.Hen;
import Animal.Sheep;
import ComponentMap.SceneManager;
import Logic.Backpack;
import Logic.World;
import Map.AnimalShop;
import Map.Blacksmith;
import Map.CowBarn;
import Map.Farm;
import Map.HenBarn;
import Map.House;
import Map.SeedShop;
import Map.Town;
import Map.setsceneable;
import Plant.PlantA;
import Plant.PlantB;
import Plant.PlantC;
import SpecialScene.AnimalMenu;
import SpecialScene.SeedMenu;
import Tool.Axe;
import Tool.Hammer;
import Tool.Hand;
import Tool.Hoe;
import Tool.SeedplantA;
import Tool.WateringCan;

public class Main extends Application {

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
		Hand hand =  new Hand();
		SeedplantA seed1 = new SeedplantA(50);
		Backpack.addItem(axe);
		Backpack.addItem(hammer);
		Backpack.addItem(hoe);
		Backpack.addItem(wateringcan);
		Backpack.addItem(seed1 );
		Backpack.addItem(hand );
		
		//---------------set up map---------------------//

		// ---------------set up map---------------------//
		List<setsceneable> listmap = new ArrayList<>();
		Farm farm = new Farm(1000, 300);
		Town town = new Town(0, 560);
		House house = new House(603, 645);
		CowBarn cowbarn = new CowBarn(603, 645);
		HenBarn henbarn = new HenBarn(603, 600);
		Blacksmith blacksmith = new Blacksmith(603, 645);
		AnimalShop animalshop = new AnimalShop(0, 300);
		SeedShop seedshop = new SeedShop(0, 300);
		AnimalMenu animalmenu = new AnimalMenu("Cow", Cow.COST, "Sheep", Sheep.COST, "Hen", Hen.COST, 6);
		SeedMenu seedmenu = new SeedMenu("PlantA", PlantA.SeedCost, "PlantB", PlantB.SeedCost, "PlantC",
				PlantC.SeedCost, 7);

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
		
		
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		SceneManager sm = new SceneManager(primaryStage, listmap);
		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
			}
		};
		animation.start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
