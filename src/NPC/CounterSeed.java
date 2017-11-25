package NPC;

import ComponentMap.SceneManager;
import Plant.PlantA;
import Plant.PlantB;
import Plant.PlantC;
import SpecialScene.SeedMenu;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CounterSeed extends Counter {

	public CounterSeed(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}

	public void checkAction(Rectangle r) {
		Shape intersect = Shape.intersect(r, this);
		double wi = intersect.getBoundsInLocal().getWidth();
		double hi = intersect.getBoundsInLocal().getHeight();
		double wa = r.getWidth();
		double ha = r.getHeight();
		if (wi >= 0.5 * wa && hi >= 0.5 * ha) {
			System.out.println("What do you want Today?");
			SceneManager.warpTo(9);
		}
	}

	public static void update(int numA, int numB, int numC) {
		BuySeedA(numA);
		BuySeedB(numB);
		BuySeedC(numC);
		System.out.println("Total cost is " + SeedMenu.getTotalCost() + " $");
		System.out.println("Thank you very much ~");
	}

	private static void BuySeedC(int numC) {
		// TODO Auto-generated method stub
		PlantC.addSeed();
	}

	private static void BuySeedB(int numB) {
		// TODO Auto-generated method stub
		PlantB.addSeed();
	}

	private static void BuySeedA(int numA) {
		// TODO Auto-generated method stub
		PlantA.addSeed();
	}
}
