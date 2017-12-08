package Logic;

import java.util.ArrayList;
import java.util.List;

import ComponentMap.StackAble;
import Plant.OnHandAble;
import SpecialScene.Inventory;
import Tool.Hand;

public class Backpack {
	private static List<InBackpack> backpack;
	private static int indexitemonhand;
	private static int max_bagsize;
	private static int level;

	public Backpack() {
		max_bagsize = 11;
		backpack = new ArrayList<>(11);
		indexitemonhand = 0;
		setLevel(0);
	}

	public static InBackpack CheckItemOnHand() {
		return backpack.get(indexitemonhand);
	}

	public static void ChangeItem() {
		if (indexitemonhand < backpack.size() - 1) {
			indexitemonhand += 1;
			InBackpack item = backpack.get(indexitemonhand);
			if (item instanceof OnHandAble) {
				Hand.setOnhand(((OnHandAble) item));
				backpack.get(0).cry();
			} else
				backpack.get(indexitemonhand).cry();
		} else {
			indexitemonhand = 0;
			backpack.get(indexitemonhand).cry();
		}
	}

	public static void SelectItem(int row, int column) throws IndexOutOfBoundsException {
		indexitemonhand = (10 * row + column) % 10 + 1;
		InBackpack item = backpack.get(indexitemonhand);
		if (item instanceof OnHandAble) {
			Hand.setOnhand(((OnHandAble) item));
			backpack.get(0).cry();
		} else
			backpack.get(indexitemonhand).cry();
	}

	public static void addItem(InBackpack t) {
		if (backpack.size() < max_bagsize)
			backpack.add(t);
		else
			System.out.println("Your bag is full.");
	}

	public static void deleteItem() {
		if (backpack.size() != 0) {
			if(backpack.get(indexitemonhand) instanceof StackAble) {
				((StackAble) backpack.get(indexitemonhand)).clear();
			}	
			backpack.remove(indexitemonhand);
		}
		indexitemonhand = 0;
	}

	public static List<InBackpack> getBackpack() {
		return backpack;
	}

	public static void upgrade() {
		setLevel(getLevel() + 1);
		max_bagsize += 10;
		Inventory.upgrade();
	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) {
		Backpack.level = level;
	}

	public static int getMaxSize() {
		return max_bagsize;
	}
}
