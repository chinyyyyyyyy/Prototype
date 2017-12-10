package ComponentMap;

import Logic.Backpack;
import Logic.InBackpack;
import Tool.Hay;
import Tool.Seed;
import Tool.Tool;
import Tool.WateringCan;
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

						gc.setStroke(Color.BLACK);
						gc.setFill(Color.BLACK);
						gc.fillRect(5, 625, 245, 90);
						gc.setFill(Color.BLACK);

						// --------------------------- Clock --------------------------------
						gc.drawImage(texture, 10, 630, 150, 80);
						gc.setFont(header);
						gc.fillText(Main.getTime(), 20, 690);

						// ---------------------------- Tool --------------------------
						gc.drawImage(texture, 165, 630, 80, 80);
						InBackpack x = Backpack.CheckItemOnHand();
						if (x instanceof Tool) {
							if (((Tool) x).getLevel() != 0) {
								int index = Backpack.getIndexitemonhand();
								if (index <= 4) {
									if (((Tool) x).getLevel() == 2) {
										gc.setFill(Color.GOLD);
									} else if (((Tool) x).getLevel() == 1)
										gc.setFill(Color.SILVER);
								}
								if (x instanceof Hay)
									gc.setFill(Color.GREENYELLOW);
								else if (x instanceof Seed)
									gc.setFill(Color.SPRINGGREEN);
								gc.fillRect(165, 630, 80, 80);
								gc.strokeRect(165, 630, 80, 80);
							}
							if (x instanceof WateringCan) {
								gc.setFill(Color.DEEPSKYBLUE);
								gc.fillRect(165, 630 + (1 - WateringCan.getWaterLevel()) * 80, 80,
										WateringCan.getWaterLevel() * 80);
							}
						}

						gc.drawImage(x.getImage(), 175, 640, 60, 60);

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
		gc.drawImage(texture, 10, 630, 150, 80);
		gc.drawImage(texture, 170, 630, 80, 80);

		gc.setFill(Color.BLACK);
		gc.fillText(Backpack.CheckItemOnHand().getClass().getSimpleName(), 180, 675, 60);
		gc.setFont(body);
		gc.fillText("", 35, 685, 100);
	}

	public void stopClock() {
		clock.interrupt();
	}
}
