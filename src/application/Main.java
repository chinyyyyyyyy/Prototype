package application;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import Logic.Environment;
import Logic.Hero;
import Logic.ReceiveActionBlock;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Main extends Application {
	private Rectangle[] nodes = Environment.getEnvironment();

	@Override
	public void start(Stage primaryStage) {
		Group root = new Group();
		Scene scene = new Scene(root, 1280, 720);
		Canvas bg = new Canvas(1280, 720);
		GraphicsContext gc = bg.getGraphicsContext2D();
		gc.setFill(Color.GREEN);
		gc.fillRect(0, 0, 1280, 720);
		gc.setFill(Color.BROWN);
		gc.fillRect(340, 120, 600, 600);
		root.getChildren().add(bg);

		Hero h = new Hero(scene);
		root.getChildren().addAll(nodes);
		
		ReceiveActionBlock rcb = new ReceiveActionBlock();
		root.getChildren().addAll(rcb.getActionableblock());

		root.getChildren().addAll(h.getUnitblock());
		for (Rectangle r : Hero.getActionblock()) {
			root.getChildren().add(r);
		}
		
		
		primaryStage.setScene(scene);
		primaryStage.show();

		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				Environment.checkShapeIntersection(h.getUnitblock());
				
			}
		};
		animation.start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
