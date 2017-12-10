package NPC;

import ComponentMap.NPC;
import ComponentMap.SceneManager;
import Map.SeedShop;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Florist extends NPC {

	protected String name;

	public Florist(int x, int y, int w, int h, Color c, String Name) {
		super(x, y, w, h, c);
		this.name = Name;
	}

	public String Welcome() {
		return "Plant make our world beautiful~. Do you think so ?";
	}
	
	public String getDialog() {
		return "Hello, My name is " + this.name;
	}

	public void checkAction(Rectangle r) {
		Shape intersect = Shape.intersect(r, this);
		double wi = intersect.getBoundsInLocal().getWidth();
		double hi = intersect.getBoundsInLocal().getHeight();
		double wa = r.getWidth();
		double ha = r.getHeight();
		if (wi >= 0.5 * wa && hi >= 0.5 * ha) {
			System.out.println("Hello, My name is "+this.name);
			SeedShop scene = (SeedShop) SceneManager.getListMap().get(7);
			scene.chat(getDialog());
		}
	}

	public String getName() {
		return this.name;
	}
}
