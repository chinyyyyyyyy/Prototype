package NPC;

import ComponentMap.SceneManager;
import Logic.Backpack;
import Tool.SeedA;
import Tool.SeedB;
import Tool.SeedC;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CounterSeed extends Counter {

	private static boolean buyable;

	public CounterSeed(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
		setBuyable(true);
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
		if (numA != 0)
			BuySeedA(numA);
		if (numB != 0)
			BuySeedB(numB);
		if (numC != 0)
			BuySeedC(numC);
	}

	private static void BuySeedC(int numC) {
		// TODO Auto-generated method stub
		if (SeedC.getSeedAmount() == 0) {
			if (Backpack.isFull()) {
				System.out.println("Your bag is full.");
				setBuyable(isBuyable() && false);
				return;
			}
			Backpack.addItem(new SeedC(0));
		}
		SeedC.addSeed(numC);
	}

	private static void BuySeedB(int numB) {
		// TODO Auto-generated method stub
		if (SeedB.getSeedAmount() == 0) {
			if (Backpack.isFull()) {
				System.out.println("Your bag is full.");
				setBuyable(isBuyable() && false);
				return;
			}
			Backpack.addItem(new SeedB(0));
		}
		SeedB.addSeed(numB);
	}

	private static void BuySeedA(int numA) {
		// TODO Auto-generated method stub
		if (SeedA.getSeedAmount() == 0) {
			if (Backpack.isFull()) {
				System.out.println("Your bag is full.");
				setBuyable(isBuyable() && false);
				return;
			}
			Backpack.addItem(new SeedA(0));
		}
		SeedA.addSeed(numA);
	}

	public static boolean isBuyable() {
		return buyable;
	}

	public static void setBuyable(boolean buyable) {
		CounterSeed.buyable = buyable;
	}
}
