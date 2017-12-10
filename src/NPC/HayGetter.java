package NPC;

import ComponentMap.NPC;
import Logic.Backpack;
import Tool.Hay;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class HayGetter extends NPC{

	public HayGetter(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}

	public String Welcome() {
		return "";
	}
	
	public String getDialog() {
		return "";
	}

	public void checkAction(Rectangle r) {
		Shape intersect = Shape.intersect(r, this);
		double wi = intersect.getBoundsInLocal().getWidth();
		double hi = intersect.getBoundsInLocal().getHeight();
		double wa = r.getWidth();
		double ha = r.getHeight();
		if (wi >= 0.9 * wa && hi >= 0.9 * ha) {
				if (Hay.getHayCount() == 0) {
					if (Backpack.isFull()) {
						System.out.println("Your bag is full.");
						return;
					}
					else Backpack.addItem(new Hay());
				}
				Hay.addHay();
				System.out.println("Get Hay!");
		}
	}
}
