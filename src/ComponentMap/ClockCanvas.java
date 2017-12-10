package ComponentMap;

import Logic.Backpack;
import Logic.InBackpack;
import application.Main;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ClockCanvas extends Canvas {

	public static Thread clock;
	public static GraphicsContext gc;

	public ClockCanvas() {
		super(1280, 720);
		gc = this.getGraphicsContext2D();
		// TODO Auto-generated constructor stub
		initializeGUI();
	}

	public void TurnClock() throws InterruptedException {
		// Fill your code
		clock = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(10);
					Platform.runLater(() -> {
						gc.clearRect(0, 0, 1280, 720);

						gc.setFill(Color.ALICEBLUE);
						gc.setStroke(Color.BLACK);
						gc.fillRect(10, 630, 150, 80);
						gc.strokeRect(10, 630, 150, 80);
						gc.fillRect(170, 630, 80, 80);
						gc.strokeRect(170, 630, 80, 80);
						gc.setFill(Color.BLACK);
						
						// Clock
						gc.setFont(new Font("abc", 40));
						gc.fillText(Main.getTime(), 35, 685, 100);

						gc.setFont(new Font("abc", 20));

						// Tool
						InBackpack x = Backpack.CheckItemOnHand();
						//String name;
//						if (x instanceof Plant) {
//							name = ((Plant) x).getName();
//						} else {
//							name = x.getClass().getSimpleName();
//						}
//						gc.fillText(name, 180, 665, 60);
						gc.drawImage(x.getImage(), 180,640,60,60);

					});

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					break;
				}
			}
		});
		clock.start();
	}

	private void initializeGUI() {
		gc.setFill(Color.ALICEBLUE);
		gc.setStroke(Color.BLACK);
		gc.fillRect(10, 630, 150, 80);
		gc.strokeRect(10, 630, 150, 80);
		gc.fillRect(170, 630, 80, 80);
		gc.strokeRect(170, 630, 80, 80);

		gc.setFill(Color.BLACK);
		gc.setFont(new Font("abc", 20));
		gc.fillText(Backpack.CheckItemOnHand().getClass().getSimpleName(), 180, 675, 60);
		gc.setFont(new Font("abc", 40));
		gc.fillText("", 35, 685, 100);
	}

	public void stopClock() {
		clock.interrupt();
	}
}
