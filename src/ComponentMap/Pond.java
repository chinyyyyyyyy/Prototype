package ComponentMap;

import Logic.Backpack;
import Logic.World;
import Tool.ActionByToolAble;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Pond extends ReceiveAction implements ActionByToolAble{
	public Pond(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}

	@Override
	public void checkAction(Rectangle r) {
		Shape intersect = Shape.intersect(r, this);
		double wi = intersect.getBoundsInLocal().getWidth();
		double hi = intersect.getBoundsInLocal().getHeight();
		double wr = this.getWidth();
		double hr = this.getHeight();
		double wa = r.getWidth();
		double ha = r.getHeight();
		if (wi >= 0.9 * wa && hi >= 0.9 * ha)
			Backpack.CheckItemOnHand().Action(this);
	}
}
