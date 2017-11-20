package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import Map.Farm;

public class Main extends Application {


	@Override
	public void start(Stage primaryStage) {
		Farm farm = new Farm(340,345);

		primaryStage.setScene(farm.getFarmScene() );
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
