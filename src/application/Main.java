package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import ComponentMap.SceneManager;
import Map.Blacksmith;
import Map.CowBarn;
import Map.Farm;
import Map.HenBarn;
import Map.House;
import Map.Town;
import Map.setsceneable;

public class Main extends Application {


	@Override
	public void start(Stage primaryStage) {
		
		List<setsceneable> listmap = new ArrayList<>();
		Farm farm = new Farm(340,345);
		Town town = new Town(0,560);
		House house = new House(603,645);
		CowBarn cowbarn = new CowBarn(603,645);
		HenBarn henbarn = new HenBarn(603,600);
		Blacksmith blacksmith = new Blacksmith(603,645);
		listmap.add(farm);
		listmap.add(town);
		listmap.add(house);
		listmap.add(cowbarn);
		listmap.add(henbarn);
		listmap.add(blacksmith);
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
