package NPC;

import java.util.ArrayList;
import java.util.List;
import Animal.Animal;
import ComponentMap.NPC;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Counter extends NPC {

	public static List<Animal> animal = new ArrayList<Animal>();
	public static List<Animal> hen = new ArrayList<Animal>();

	public Counter(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}

	public String Welcome() {
		return "";
	}
	
	public String getDialog() {
		return "What do you want today?";
	}
	
	public static String getQuestion() {
		return "What do you want today?";
	}

	public void checkAction(Rectangle r) {
	}

}
