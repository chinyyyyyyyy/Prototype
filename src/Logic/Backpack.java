package Logic;

import java.util.ArrayList;
import java.util.List;

public class Backpack {
	private static List<InBackpack> backpack;
	private static int indexitemonhand;
	private static int max_bagsize;
	
	public Backpack() {
		max_bagsize = 10;
		backpack = new ArrayList<>(10);
		indexitemonhand = 0;
	}
	
	public static InBackpack  CheckItemOnHand() {
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
	
	public static void SelectItem(int row,int column) throws IndexOutOfBoundsException{
		indexitemonhand = (10*row + column)%10;
		backpack.get(indexitemonhand).cry();
	}
	
	public static void addItem(InBackpack t) {
		if (backpack.size()<max_bagsize) backpack.add(t);
		else System.out.println("Your bag is full.");
	}
	
	public static void deleteItem() {
		if (backpack.size()!=0) backpack.remove(indexitemonhand);
	}
	
	public static List<InBackpack> getBackpack(){
		return backpack;
	}

}
