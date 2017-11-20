package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import Map.CowBarn;
import Map.Farm;
import Map.HenBarn;
import Map.House;
import Map.Town;

public class Main extends Application {


	@Override
	public void start(Stage primaryStage) {
		Farm farm = new Farm(340,345);
		Town town = new Town(0,600);
		House house = new House(0,500);
		CowBarn cowbarn = new CowBarn(300,300);
		HenBarn henbarn = new HenBarn(300,300);
		
		primaryStage.setScene(cowbarn.getCowBarnScene());
		primaryStage.show();


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
