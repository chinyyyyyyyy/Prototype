package ComponentMap;

import Logic.Backpack;
import Logic.InBackpack;
import application.Main;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ClockCanvas extends Canvas {

	public static Thread clock;
	public static GraphicsContext gc;
	static final Font header = Font.loadFont(ClassLoader.getSystemResource("fonteiei.ttf").toString(), 60);
	static final Font body = Font.loadFont(ClassLoader.getSystemResource("fonteiei.ttf").toString(), 40);
	static final Image texture = new Image(ClassLoader.getSystemResource("texture.png").toString());
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

						gc.setFill(Color.GREEN);
						gc.setStroke(Color.BLACK);
						gc.drawImage(texture,10, 630, 150, 80);
						gc.drawImage(texture,170, 630, 80, 80);
						gc.setFill(Color.BLACK);
						
						// Clock
						gc.setFont(header);
						gc.fillText(Main.getTime(), 20, 690);
						// Tool
						InBackpack x = Backpack.CheckItemOnHand();
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
		gc.drawImage(texture,10, 630, 150, 80);
		gc.drawImage(texture,170, 630, 80, 80);

		gc.setFill(Color.BLACK);
		gc.fillText(Backpack.CheckItemOnHand().getClass().getSimpleName(), 180, 675, 60);
		gc.setFont(body);
		gc.fillText("", 35, 685, 100);
	}

	public void stopClock() {
		clock.interrupt();
	}
}
