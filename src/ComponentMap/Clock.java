package ComponentMap;

import Logic.Backpack;
import Logic.InBackpack;
import Plant.Plant;
import application.Main;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Clock extends GridPane{
	
	public static Button ClockField;
	public static Button ToolField;
	public static Button DialogField;
	public static Thread clock;

	public Clock() {
		super();
		// TODO Auto-generated constructor stub
		initializeGUI();
		this.setHgap(10);
	}
	
	public static void TurnClock() throws InterruptedException {
		// Fill your code
		clock = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(10);
					Platform.runLater(() -> {
						//Clock
						ClockField.setText(Main.getTime());
						
						//Tool
						InBackpack x = Backpack.CheckItemOnHand();
						String name;
						if(x instanceof Plant) {
							name=((Plant) x).getName();
						}else {
							name = x.getClass().getSimpleName();
						}
						ToolField.setText(name);
						
						//Dialog
						DialogField.setText("eiei");
					});
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				}
			}
		});
		clock.start();
	}

	private void initializeGUI() {
		ClockField = new Button();
		ClockField.setText("");
		ClockField.setPrefSize(150, 80);
		ClockField.setDisable(true);
		
		ToolField = new Button();
		ToolField.setText(Backpack.CheckItemOnHand().getClass().getSimpleName());
		ToolField.setPrefSize(80, 80);
		ToolField.setDisable(true);
		
		DialogField = new Button();
		DialogField.setText("Kuy");
		DialogField.setPrefSize(500, 80);
		DialogField.setDisable(true);
		
		this.add(ClockField, 0, 0);
		this.add(ToolField, 1, 0);
		this.add(DialogField, 2, 0);
	}
	
	public static void stopClock() {
		clock.interrupt();
	}
}
