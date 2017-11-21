package ComponentMap;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ReceiveAction extends Rectangle {

	public ReceiveAction(int x, int y, int w, int h, Color c) {
		super(x, y, w, h);
		this.setFill(c);
	}

	public void checkAction(Rectangle r) {
		Shape intersect = Shape.intersect(r, this);
		double wi = intersect.getBoundsInLocal().getWidth();
		double hi = intersect.getBoundsInLocal().getHeight();
		double wr = this.getWidth();
		double hr = this.getHeight();
		double wa = r.getWidth();
		double ha = r.getHeight();
		if (wr < wa && hr < ha) {
			if (wi == wr && hi == hr)
				System.out.println("OK");
		} else if (wr >= wa && hr < ha) {
			if (wi >= 0.8 * wa && hi == hr)
				System.out.println("OK");
		} else if (wr < wa && hr >= ha) {
			if (wi == wr && hi >= 0.8 * ha)
				System.out.println("OK");
		} else if (wr >= wa && hr >= ha) {
			if (wi >= 0.8 * wa && hi >= 0.8 * ha)
				System.out.println("OK");
		}

	}

}
