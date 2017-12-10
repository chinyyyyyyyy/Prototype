package ComponentMap;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class NPC extends ReceiveAction {
	
	protected String name;

	public NPC(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}

	public abstract String Welcome();
	
	public abstract String getDialog();

	public abstract void checkAction(Rectangle r);

}
