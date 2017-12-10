package ComponentMap;

import Logic.Backpack;
import Logic.InBackpack;
import Plant.Plant;
import SpecialScene.BuyScene;
import application.Main;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ClockCanvas extends Canvas {

	public static Thread clock;
	public static GraphicsContext gc;
	private int timeD=0;
	private int timeC=0;
	private int timeT=0;

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
						gc.fillRect(10, 620, 150, 80);
						gc.strokeRect(10, 620, 150, 80);
						gc.fillRect(170, 620, 80, 80);
						gc.strokeRect(170, 620, 80, 80);

						if (SceneManager.isHasDialog()) {
							// Dialog
							gc.fillRect(260, 620, 500, 80);
							gc.strokeRect(260, 620, 500, 80);
							gc.setFill(Color.BLACK);
							gc.fillText(SceneManager.getDialog(), 280, 665, 460);
							timeD+=10;
							if(timeD==2000) {
								SceneManager.setHasDialog(false);
								timeD=0;
							}
						}
						
						
						if (SceneManager.isHasConversation()) {
							// Dialog
							gc.setFill(Color.ALICEBLUE);
							gc.fillRect(260, 620, 500, 80);
							gc.strokeRect(260, 620, 500, 80);
							gc.setFill(Color.BLACK);
							gc.fillText(SceneManager.getConversation(), 280, 665, 460);
							timeC+=10;
							if(timeC==2000) {
								SceneManager.setHasConversation(false);
								timeC=0;
							}
						}
						
						if (SceneManager.isHasThank()) {
							// Dialog
							gc.setFill(Color.ALICEBLUE);
							gc.fillRect(260, 620, 500, 80);
							gc.strokeRect(260, 620, 500, 80);
							gc.setFill(Color.BLACK);
							timeT+=10;
							if(timeT==4000) {
								SceneManager.setHasThank(false);
								timeT=0;
							}else if(timeT>=2000) {
								gc.fillText("Hope to see you again ~", 280, 665, 460);
							}else {
								gc.fillText(BuyScene.getThank(), 280, 665, 460);
							}
						}

						gc.setFill(Color.BLACK);
						// Clock
						gc.setFont(new Font("abc", 40));
						gc.fillText(Main.getTime(), 35, 675, 100);

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
						gc.drawImage(x.getImage(), 180,630,60,60);

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
		gc.fillRect(10, 620, 150, 80);
		gc.strokeRect(10, 620, 150, 80);
		gc.fillRect(170, 620, 80, 80);
		gc.strokeRect(170, 620, 80, 80);

		gc.setFill(Color.BLACK);
		gc.setFont(new Font("abc", 20));
		gc.fillText(Backpack.CheckItemOnHand().getClass().getSimpleName(), 180, 665, 60);
		gc.setFont(new Font("abc", 40));
		gc.fillText("", 35, 675, 100);
	}

	public void stopClock() {
		clock.interrupt();
	}
}
