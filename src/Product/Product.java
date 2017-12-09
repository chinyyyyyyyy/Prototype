package Product;

import ComponentMap.ActionByToolAble;
import ComponentMap.Dropbox;
import ComponentMap.StackAble;
import Logic.Backpack;
import Logic.InBackpack;
import Plant.OnHandAble;

public abstract class Product implements InBackpack, StackAble, OnHandAble {

	protected int price;

	public void cry() {
		System.out.println(this.getClass().getSimpleName());
	}
	
	public void Action(ActionByToolAble a) {
		if(a instanceof Dropbox) {
			Backpack.getBackpack().get(0).Action(a);
			Backpack.deleteItem();
		}
	}
	
	@Override
	public String toString() {
		return getName();
	}

	public String getName() {
		return this.getClass().getSimpleName();
	}

	public int getPrice() {
		return price;
	}
}
