package Plant;

import ComponentMap.ActionByToolAble;
import ComponentMap.Dropbox;
import ComponentMap.StackAble;
import Logic.Backpack;
import Logic.InBackpack;
import javafx.scene.paint.ImagePattern;

public abstract class Plant implements InBackpack, StackAble, OnHandAble {

	protected int dayofgrowth;
	protected String name;
	protected int price;

	public void cry() {
		System.out.println(this.getClass().getSimpleName());
	}

	public void Action(ActionByToolAble a) {
		if (a instanceof Dropbox) {
			Backpack.getBackpack().get(0).Action(a);
			Backpack.deleteItem();
		}
	}

	public void addDayOfGrowth() {
		dayofgrowth += 1;
		System.out.println(dayofgrowth);
	}

	public String getName() {
		return this.name;
	}

	public abstract ImagePattern checkState();

	public abstract ImagePattern getWateredstate();

	@Override
	public String toString() {
		return name;
	}

	public abstract void Harvest();

	public abstract int getAmount();

	public int getPrice() {
		return price;
	}

}
