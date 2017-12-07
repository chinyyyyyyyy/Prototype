package Logic;

import java.util.ArrayList;
import java.util.List;

import Tool.Tool;

public class Backpack {
	private static List<Tool> backpack;
	private static int indexitemonhand;
	private static int max_bagsize;
	
	public Backpack() {
		max_bagsize = 10;
		backpack = new ArrayList<>(10);
		indexitemonhand = 0;
	}
	
	public static Tool  CheckItemOnHand() {
		return backpack.get(indexitemonhand);
	}
	
	public static void ChangeItem() {
		if(indexitemonhand<backpack.size()-1) {
			indexitemonhand += 1;
			backpack.get(indexitemonhand).cry();
		}else { 
			indexitemonhand = 0;
			backpack.get(indexitemonhand).cry();
		}
	}
	
	public static void addItem(Tool t) {
		if (backpack.size() < max_bagsize) backpack.add(t);
	}
	
	public static void deleteItem() {
		if (backpack.size()!=0) backpack.remove(indexitemonhand);
	}

}
