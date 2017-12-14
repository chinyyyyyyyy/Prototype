package NPC;

import Animal.Cow;
import Animal.Hen;
import Animal.Sheep;
import Logic.SceneManager;
import Map.CowBarn;
import Map.HenBarn;
import SpecialScene.AnimalMenu;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CounterAnimal extends Counter {

	public static boolean canBuyBarn = true;
	public static boolean canBuyHen = true;
	private static boolean buyable;
	public CounterAnimal(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}

	public static void CheckBuyable(int numcow, int numsheep, int numhen) {

		int barncheck = CowBarn.getAnimalCount() + numcow + numsheep - 1;
		if (barncheck < 0)
			barncheck = 0;

		int hencheck = HenBarn.getAnimalCount() + numhen - 1;
		if (hencheck < 0)
			hencheck = 0;

		try {
			int testcow = CowBarn.position.get(barncheck).getKey();
			int testsheep = CowBarn.position.get(barncheck).getKey();
		} catch (IndexOutOfBoundsException e) {
			canBuyBarn = false;
		}

		try {
			int testhen = HenBarn.actionposition.get(hencheck).getKey();
		} catch (IndexOutOfBoundsException e) {
			canBuyHen = false;
		}
	}

	public static void BuyCow(int num) {
		for (int i = 0; i < num; i++) {
			Cow.addCow();
			int sx = CowBarn.position.get(CowBarn.getAnimalCount() - 1).getKey();
			int sy = CowBarn.position.get(CowBarn.getAnimalCount() - 1).getValue();
			Cow c = new Cow(sx, sy, 100, 100, Color.ALICEBLUE);
			c.setOpacity(0);
			animal.add(c);
		}
	}

	public static void BuySheep(int num) {

		for (int i = 0; i < num; i++) {
			Sheep.addSheep();
			int sx = CowBarn.position.get(CowBarn.getAnimalCount() - 1).getKey();
			int sy = CowBarn.position.get(CowBarn.getAnimalCount() - 1).getValue();
			Sheep s = new Sheep(sx, sy, 100, 100, Color.ALICEBLUE);
			s.setOpacity(0);
			animal.add(s);
		}
	}

	public static void BuyHen(int num) {
		for (int i = 0; i < num; i++) {
			Hen.addHen();
			int sx = HenBarn.actionposition.get(HenBarn.getAnimalCount() - 1).getKey();
			int sy = HenBarn.actionposition.get(HenBarn.getAnimalCount() - 1).getValue();
			hen.add(new Hen(sx, sy, 100,100, Color.LIMEGREEN));
		}
		
		
		
	}

	public void checkAction(Rectangle r) {
		Shape intersect = Shape.intersect(r, this);
		double wi = intersect.getBoundsInLocal().getWidth();
		double hi = intersect.getBoundsInLocal().getHeight();
		double wa = r.getWidth();
		double ha = r.getHeight();
		if (wi >= 0.5 * wa && hi >= 0.5 * ha) {
			System.out.println("What do you want Today?");
			SceneManager.warpTo(13);
		}
	}

	public static void update(int numcow, int numsheep, int numhen) {
		BuyCow(numcow);
		BuySheep(numsheep);
		BuyHen(numhen);
		System.out.println("Total cost is " + AnimalMenu.getTotalCost() + " $");
		System.out.println("Thank you very much ~");
		// System.out.println("You have " + Cow.getCowCount() + " Cow.");
		// System.out.println("You have " + Sheep.getSheepCount() + " Sheep.");
		// System.out.println("You have " + Hen.getHenCount() + " Hen.");
	}

	public static boolean isBuyable() {
		buyable = canBuyBarn && canBuyHen;
		return buyable;
	}

	public static void setBuyable(boolean buyable) {
		CounterAnimal.buyable = buyable;
	}
}
