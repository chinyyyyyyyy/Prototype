package Product;

import ComponentMap.StackAble;
import Logic.InBackpack;

public abstract class Product implements InBackpack,StackAble{

	protected int price;
	
	public void cry() {
		System.out.println(this.getClass().getSimpleName());
	}


}
