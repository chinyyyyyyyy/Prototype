package Animal;

import ComponentMap.ActionByToolAble;
import ComponentMap.SceneManager;
import Logic.Backpack;
import Logic.InBackpack;
import Map.CowBarn;
import Product.Milk;
import Tool.Hay;
import Tool.Milker;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Cow extends Animal implements ActionByToolAble {
	protected String name;
	protected static int CowCount = 0;
	protected int age;
	protected int love;
	private int daycount;
	public static final int COST = 5000;
	private static CowBarn scene;
	public static AudioClip soundeffect = new AudioClip(ClassLoader.getSystemResource("cowsound.mp3").toString());


	public Cow(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
		this.age = 1;
		this.love = 0;
		this.daycount=0;
		this.produceable = false;
	}

	public void cry() {
		soundeffect.play();
	}

	public void produce() {
		if (Milk.getMilkCount() == 0) {
			if(Backpack.isFull()) {
				scene.chat("Your bag is full.");
				System.out.println("Your bag is full.");
				return;
			}
			else Backpack.addItem(new Milk());
		}
		Milk.addMilk();
		this.produceable=false;
		cry();
		this.love++;
		this.daycount=0;
		scene.chat("Milk Success");
		System.out.println("Milk Success");
	}
	
	public void eat() {
		cry();
		this.love++;
		this.feedable=false;
		this.daycount++;
	}

	public void checkAction(Rectangle r) {
		Shape intersect = Shape.intersect(r, this);
		double wi = intersect.getBoundsInLocal().getWidth();
		double hi = intersect.getBoundsInLocal().getHeight();
		double wa = r.getWidth();
		double ha = r.getHeight();
		if (wi >= 0.9 * wa && hi >= 0.9 * ha) {
			scene = (CowBarn) SceneManager.getListMap().get(3);
			InBackpack item = Backpack.CheckItemOnHand();
			if(item instanceof Milker) {
				((Milker) item).Action(this);
			}else if(item instanceof Hay){
				((Hay) item).Action(this);
			}else {
				cry();
				this.love++;							
			}
		}
	}

	public static void addCow() {
		CowCount++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static int getCowCount() {
		return CowCount;
	}

	public int getAge() {
		return age;
	}

	public int getLove() {
		return love;
	}

	public void updateafterendday() {
		this.age++;
		this.feedable=true;
		if(this.daycount>=7) this.produceable = true;
	}
	
	public boolean getProduceable() {
		return this.produceable;
	}
}
