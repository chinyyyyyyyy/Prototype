package ComponentMap;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DialogCanvas extends Canvas {
	public static DialogCanvas Dialog;

	public static Thread dialog;
	public static GraphicsContext gc;
	private static int time = 0;
	public static boolean hasDialog;
	public static String word = "";

	public DialogCanvas() {
		super(1280, 720);
		gc = this.getGraphicsContext2D();
		// TODO Auto-generated constructor stub
		Dialog = this;
	}

	public void Chat(String newword) {
		// Fill your code
		word = newword;
		setHasDialog(true);
		dialog = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(10);
					if (isHasDialog()) {
						gc.setFill(Color.ALICEBLUE);
						gc.setStroke(Color.BLACK);
						gc.fillRect(260, 620, 500, 80);
						gc.strokeRect(260, 620, 500, 80);
						gc.setFill(Color.BLACK);
						gc.fillText(word, 280, 665, 460);
						time += 10;
						if (time == 2000) {
							setHasDialog(false);
							time = 0;
							word = "";
							gc.clearRect(0, 0, 1280, 720);
							break;
						}
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					break;
				}
			}
		});
		dialog.start();
	}

	public static void stopDialog() {
		dialog.interrupt();
	}

	public static boolean isHasDialog() {
		return hasDialog;
	}

	public static void setHasDialog(boolean hasDialog) {
		DialogCanvas.hasDialog = hasDialog;
	}

}
