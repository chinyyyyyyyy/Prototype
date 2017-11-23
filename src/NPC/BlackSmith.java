package NPC;

import ComponentMap.NPC;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class BlackSmith extends NPC {

	private String name;

	public BlackSmith(int x, int y, int w, int h, Color c, String Name) {
		super(x, y, w, h, c);
		this.name = Name;
	}

	public void Welcome() {
		System.out.println("Hello, What do you want today?");
	}

	public void checkAction(Rectangle r) {
		Shape intersect = Shape.intersect(r, this);
		double wi = intersect.getBoundsInLocal().getWidth();
		double hi = intersect.getBoundsInLocal().getHeight();
		double wa = r.getWidth();
		double ha = r.getHeight();
		if (wi >= 0.9 * wa && hi >= 0.9 * ha) {
			System.out.println("Hello, My name is "+this.name);

		}
	}

	public String getName() {
		return this.name;
	}
}
