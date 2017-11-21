package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import Map.Farm;
import Map.Town;

public class Main extends Application {


	@Override
	public void start(Stage primaryStage) {
		Farm farm = new Farm(340,345);
		Town town = new Town(0,600);
		
		//primaryStage.setScene(farm.getFarmScene());
		primaryStage.setScene(town.getTownScene());
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
