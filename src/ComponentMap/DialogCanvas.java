package ComponentMap;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DialogCanvas extends Canvas {
	public static DialogCanvas Dialog = new DialogCanvas();
	static final Font header = Font.loadFont(ClassLoader.getSystemResource("fonteiei.ttf").toString(), 60);
	static final Font body = Font.loadFont(ClassLoader.getSystemResource("fonteiei.ttf").toString(), 40);
	static final Image texture = new Image(ClassLoader.getSystemResource("texture.png").toString());
	public static Thread dialog;
	public static GraphicsContext gc;
	private static int time = 0;
	public static boolean hasDialog;
	public static String word = "";
	private FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();

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
			double len = fontLoader.getFontMetrics(body).computeStringWidth(newword);
			double lb = fontLoader.getFontMetrics(body).computeStringWidth(" ");
			String blank = "";
			for (int i = 0; i < Math.floorDiv(Math.floorDiv((int) (500 - len), 2), Math.floorDiv((int) lb, 1)); i++) {
				blank += " ";
			}
			while (true) {
				try {
					Thread.sleep(10);
					if (isHasDialog()) {
						gc.setFill(Color.BLACK);
						gc.fillRect(265, 625, 510, 90);
						gc.setStroke(Color.BLACK);
						gc.setFont(body);
						gc.drawImage(texture, 270, 630, 500, 80);
						gc.setFill(Color.BLACK);
						gc.fillText(blank + word, 290, 680, 460);
						time += 10;
						if (time == 2000) {
							setHasDialog(false);
							time = 0;
							word = "";
							gc.clearRect(0, 0, 1280, 720);
							stopDialog();
							break;
						}
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					stopDialog();
					break;
				}
			}
		});
		dialog.start();
	}

	public void Chat(String word1, String word2) {
		// Fill your code
		setHasDialog(true);
		dialog = new Thread(() -> {
			double len1 = fontLoader.getFontMetrics(body).computeStringWidth(word1);
			double len2 = fontLoader.getFontMetrics(body).computeStringWidth(word2);
			double lb = fontLoader.getFontMetrics(body).computeStringWidth(" ");
			String blank1 = "";
			for (int i = 0; i < Math.floorDiv(Math.floorDiv((int) (500 - len1), 2), Math.floorDiv((int) lb, 1)); i++) {
				blank1 += " ";
			}
			String blank2 = "";
			for (int i = 0; i < Math.floorDiv(Math.floorDiv((int) (500 - len2), 2), Math.floorDiv((int) lb, 1)); i++) {
				blank2 += " ";
			}
			while (true) {
				try {
					Thread.sleep(10);
					if (isHasDialog()) {
						gc.setFill(Color.BLACK);
						gc.fillRect(265, 625, 510, 90);
						gc.setStroke(Color.BLACK);
						gc.strokeRect(270, 630, 500, 80);
						gc.setFont(body);
						gc.drawImage(texture, 270, 630, 500, 80);
						gc.setFill(Color.BLACK);
						time += 10;
						if (time == 4000) {
							setHasDialog(false);
							time = 0;
							gc.clearRect(0, 0, 1280, 720);
							stopDialog();
							break;
						} else if (time >= 2000) {
							gc.fillText(blank2 + word2, 290, 680, 460);
						} else {
							gc.fillText(blank1 + word1, 290, 680, 460);
						}
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					stopDialog();
					break;
				}
			}
		});
		dialog.start();
	}

	public static void stopDialog() {
		if (dialog != null)
			dialog.interrupt();
	}

	public static boolean isHasDialog() {
		return hasDialog;
	}

	public static void setHasDialog(boolean hasDialog) {
		DialogCanvas.hasDialog = hasDialog;
	}

}
