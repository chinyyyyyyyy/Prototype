package ComponentMap;

import java.util.List;

import Logic.World;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Bed extends ReceiveAction {

	public Bed(int x, int y, int w, int h, Color c) {
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
			World.nextDay();
	}

}
