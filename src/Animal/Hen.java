package Animal;

import ComponentMap.ActionByToolAble;
import ComponentMap.SceneManager;
import Logic.Backpack;
import Product.Egg;
import Tool.Hay;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Hen extends Animal implements ActionByToolAble {

	protected String name;
	protected static int HenCount = 0;
	protected int age;
	protected int love;
	public static final int COST = 1000;

	public Hen(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
		this.age = 1;
		this.love = 0;
		this.produceable = false;

	}

	public void cry() {
		System.out.println("Pokkkk Pokk Pokkk");
	}

	public void produce() {
		if (Egg.getEggCount() == 0) {
			if (Backpack.isFull()) {
				System.out.println("Your bag is full.");
				return;
			} else
				Backpack.addItem(new Egg());
		}
		Egg.addEgg();
		System.out.println("Get Egg");
		this.produceable = false;
	}

	public void eat() {
		cry();
		this.love++;
		this.feedable = false;
	}

	public void checkAction(Rectangle r) {
		Shape intersect = Shape.intersect(r, this);
		double wi = intersect.getBoundsInLocal().getWidth();
		double hi = intersect.getBoundsInLocal().getHeight();
		double wa = r.getWidth();
		double ha = r.getHeight();
		if (wi >= 0.9 * wa && hi >= 0.9 * ha) {
			if (this.getProduceable()) {
				produce();
				SceneManager.warpTo(SceneManager.getSceneNumber());
			} else if (Backpack.CheckItemOnHand() instanceof Hay) {
				((Hay) Backpack.CheckItemOnHand()).Action(this);
				SceneManager.warpTo(SceneManager.getSceneNumber());
			} else {
				cry();
				this.love++;
			}
		}
	}

	public static void addHen() {
		HenCount++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static int getHenCount() {
		return HenCount;
	}

	public int getAge() {
		return age;
	}

	public int getLove() {
		return love;
	}

	public void updateafterendday() {
		this.age++;
		this.produceable = true;
		this.feedable = true;
	}

	public boolean getProduceable() {
		return this.produceable;
	}

}
