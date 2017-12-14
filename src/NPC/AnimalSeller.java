package NPC;

import Logic.SceneManager;
import Map.AnimalShop;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class AnimalSeller extends NPC {

	protected String name;

	public AnimalSeller(int x, int y, int w, int h, Color c, String Name) {
		super(x, y, w, h, c);
		this.name = Name;
	}

	public String Welcome() {
		return "Yo ho ho, Today is a good day.";
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
			System.out.println("Hello, My name is " + this.name);
			AnimalShop scene = (AnimalShop) SceneManager.getListMap().get(6);
			scene.chat(getDialog());
		}
	}

	public String getName() {
		return this.name;
	}
	
}
