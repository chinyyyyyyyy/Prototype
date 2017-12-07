package Animal;

import ComponentMap.ReceiveAction;
import Logic.updateEveryday;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Animal extends ReceiveAction implements updateEveryday{
	
	protected String name;
	protected boolean produceable;
	
	public Animal(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}

	public abstract void cry();
	
	public abstract void produce();
	
	public abstract boolean getProduceable();

	public abstract void checkAction(Rectangle r);

}
