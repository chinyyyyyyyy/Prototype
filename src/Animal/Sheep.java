package Animal;

import Logic.Backpack;
import Logic.InBackpack;
import Logic.SceneManager;
import Map.CowBarn;
import Product.Wool;
import Tool.ActionByToolAble;
import Tool.Hay;
import Tool.Scissors;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Sheep extends Animal implements ActionByToolAble{

	protected String name;
	protected static int SheepCount = 0;
	protected int age;
	protected int love;
	public static final int COST = 3000;
	protected int daycount;
	private static CowBarn scene;
	public static AudioClip soundeffect = new AudioClip(ClassLoader.getSystemResource("sheepsound.mp3").toString());


	public Sheep(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
		this.age = 1;
		this.love = 0;
		this.daycount=0;
		this.produceable=false;
	}
	
	public void eat() {
		cry();
		this.love++;
		this.feedable=false;
		this.daycount++;
	}

	public void cry() {
		soundeffect.play();
	}

	public void produce() {
		if (Wool.getWoolCount() == 0) {
			if(Backpack.isFull()) {
				scene.chat("Your bag is full.");
				System.out.println("Your bag is full.");
				return;
			}
			else Backpack.addItem(new Wool());	
		}
		Wool.addWool();
		this.produceable=false;
		cry();
		this.love++;
		this.daycount=0;
		scene.chat("Cut Success");
		System.out.println("Cut Success");
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
			if(item instanceof Scissors) {
				((Scissors) item).Action(this);
			}else if(item instanceof Hay){
				((Hay) item).Action(this);
			}else {
				cry();
				this.love++;							
			}
//			System.out.println("Age = "+this.age+", daycount = "+this.daycount);
		}
	}
	
	public static void addSheep() {
		SheepCount++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static int getSheepCount() {
		return SheepCount;
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
		if(this.daycount>=5) this.produceable = true;
	}
	
	public boolean getProduceable() {
		return this.produceable;
	}
}
