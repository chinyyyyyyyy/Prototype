package NPC;

import java.util.ArrayList;
import java.util.List;

import Animal.Animal;
import Animal.Cow;
import ComponentMap.NPC;
import Map.CowBarn;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Counter extends NPC {

	public static List<Animal> animal = new ArrayList<Animal>();


	public Counter(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}

	public void Welcome() {
	}
	
	public void Buy() {
		Cow.addCow();
		int sx = CowBarn.position.get(CowBarn.getAnimalCount()-1).getKey();
		int sy = CowBarn.position.get(CowBarn.getAnimalCount()-1).getValue();
		animal.add(new Cow(sx,sy,100,100,Color.ALICEBLUE));
	}


	public void checkAction(Rectangle r) {
		Shape intersect = Shape.intersect(r, this);
		double wi = intersect.getBoundsInLocal().getWidth();
		double hi = intersect.getBoundsInLocal().getHeight();
		double wa = r.getWidth();
		double ha = r.getHeight();
		if (wi >= 0.5 * wa && hi >= 0.5 * ha) {
			System.out.println("What do you want Today?");
			Buy();
			System.out.println("Buying Success");
			System.out.println("You have "+Cow.getCowCount()+" Cow.");
		}
//		SceneManager.warpTo(8);
	}

	public static List<Animal> getAnimal() {
		return animal;
	}
}
