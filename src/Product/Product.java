package Product;

import Logic.InBackpack;

public abstract class Product implements InBackpack{

	protected int price;
	
	public void cry() {
		System.out.println(this.getClass().getSimpleName());
	}


}
