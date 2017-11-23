package NPC;

import ComponentMap.NPC;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Counter extends NPC {

	public Counter(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}

	public void Welcome() {
	}

	public void checkAction(Rectangle r) {
		Shape intersect = Shape.intersect(r, this);
		double wi = intersect.getBoundsInLocal().getWidth();
		double hi = intersect.getBoundsInLocal().getHeight();
		double wa = r.getWidth();
		double ha = r.getHeight();
		if (wi >= 0.5 * wa && hi >= 0.5 * ha) {
			System.out.println("What do you want Today?");

		}
	}

}
