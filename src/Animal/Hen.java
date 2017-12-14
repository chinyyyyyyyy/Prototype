package Animal;

import Logic.Backpack;
import Logic.SceneManager;
import Map.HenBarn;
import Product.Egg;
import Tool.ActionByToolAble;
import Tool.Hay;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Hen extends Animal implements ActionByToolAble {

	protected String name;
	protected static int HenCount = 0;
	protected int age;
	protected int love;
	public static final int COST = 1000;
	private HenBarn scene ;
	public static AudioClip soundeffect = new AudioClip(ClassLoader.getSystemResource("chickensound.mp3").toString());


	public Hen(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
		this.age = 1;
		this.love = 0;
		this.produceable = false;
		scene = (HenBarn) SceneManager.getListMap().get(4);

	}

	public void cry() {
		soundeffect.play();
	}

	public void produce() {
		if (Egg.getEggCount() == 0) {
			if (Backpack.isFull()) {
				scene.chat("Your bag is full.");
				System.out.println("Your bag is full.");
				return;
			} else
				Backpack.addItem(new Egg());
		}
		Egg.addEgg();
		scene.chat("Get Egg");
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
				((HenBarn) SceneManager.getListMap().get(4)).update();
			} else if (Backpack.CheckItemOnHand() instanceof Hay) {
				((Hay) Backpack.CheckItemOnHand()).Action(this);
				((HenBarn) SceneManager.getListMap().get(4)).update();
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
