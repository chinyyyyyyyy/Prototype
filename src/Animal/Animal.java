package Animal;

import ComponentMap.ReceiveAction;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Animal extends ReceiveAction{
	
	protected String name;
	
	public Animal(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}

	public abstract void cry();
	
	public abstract void produce();

	public abstract void checkAction(Rectangle r);

}
