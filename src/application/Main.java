package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

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
import Tool.Axe;
import Tool.Hammer;
import Tool.Hoe;
import Tool.WateringCan;

public class Main extends Application {


	@Override
	public void start(Stage primaryStage) {
		//--------------set up world---------------------//
		World w = new World();
		Backpack b = new Backpack();
		//---------------set up tool---------------------//
		Axe axe = new Axe();
		Hammer hammer = new Hammer();
		Hoe hoe = new Hoe();
		WateringCan wateringcan = new WateringCan();
		Backpack.addItem(axe);
		Backpack.addItem(hammer);
		Backpack.addItem(hoe);
		Backpack.addItem(wateringcan);
		
		//---------------set up map---------------------//
		List<setsceneable> listmap = new ArrayList<>();
		Farm farm = new Farm(340,345);
		Town town = new Town(0,560);
		House house = new House(603,645);
		CowBarn cowbarn = new CowBarn(603,645);
		HenBarn henbarn = new HenBarn(603,600);
		Blacksmith blacksmith = new Blacksmith(603,645);
		AnimalShop animalshop = new AnimalShop(0,300);
		SeedShop seedshop = new SeedShop(0,300);
		
		listmap.add(farm);
		listmap.add(town);
		listmap.add(house);
		listmap.add(cowbarn);
		listmap.add(henbarn);
		listmap.add(blacksmith);
		listmap.add(animalshop);
		listmap.add(seedshop);
		SceneManager sm = new  SceneManager(primaryStage,listmap);
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
