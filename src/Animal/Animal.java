package Animal;

import ComponentMap.ReceiveAction;
import Logic.updateEveryday;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class Animal extends ReceiveAction implements updateEveryday{
	protected String name;
	protected boolean produceable;
	protected boolean feedable = true;
	public static final ImagePattern traywithegg = new ImagePattern(
			new Image(ClassLoader.getSystemResource("traywithegg.png").toString()));
	public static final ImagePattern tray = new ImagePattern(
			new Image(ClassLoader.getSystemResource("tray.png").toString()));
	public static final ImagePattern traywithhay = new ImagePattern(
			new Image(ClassLoader.getSystemResource("traywithhay.png").toString()));
	
	public Animal(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}

	public abstract void cry();
	
	public abstract void eat();
	
	public abstract void produce();
	
	public abstract boolean getProduceable();

	public abstract void checkAction(Rectangle r);
	
	public boolean getFeedable() {
		return feedable;
	}
	
	public void setTray() {
		this.setFill(tray);
	}
	
	public void setTraywithegg() {
		this.setFill(traywithegg);
	}
	
	public void setTraywithhay() {
		this.setFill(traywithhay);
	}


}
