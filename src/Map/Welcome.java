package Map;

import ComponentMap.Hero;
import ComponentMap.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Welcome implements setsceneable {
	TextField nameField;
	private Canvas c = new Canvas(1280, 720);
	GraphicsContext gc = c.getGraphicsContext2D();
	private Scene scene;

	public Welcome() {
		Group root = new Group();
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(350,300,250,550));
		
		gc.setFill(Color.ANTIQUEWHITE);
		gc.fillRect(0, 0, 1280, 720);
		gc.setFill(Color.BLACK);
		gc.setFont(new Font("abc", 30));
		gc.fillText("Please enter your name :", 480, 300);
		
		this.nameField = new TextField();
		nameField.setPromptText("Enter Your name here.");
		nameField.setPrefSize(200, 50);
		nameField.setAlignment(Pos.CENTER);
		
		pane.add(nameField,0,0);
		
		root.getChildren().add(c);
		root.getChildren().add(pane);
		scene = new Scene(root);
		
		nameField.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Hero.setName(nameField.getText());
				SceneManager.warpTo(0);
			}
		});
	}

	public Scene getScene() {
		return scene;
	}
}
