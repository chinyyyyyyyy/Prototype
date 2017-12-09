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
	private static int max_bagsize=11;
	private static int level;
	private static boolean full=false;

	public Backpack() {
		backpack = new ArrayList<>();
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
			Hand.setOnhand(null);
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
		if(isFull() == false) backpack.add(t);
		else {
			System.out.println("Your bag is full.");
			return;
		}
		if (backpack.size() >= max_bagsize) {	
			setFull(true);
		}
	}

	public static void deleteItem() {
		if (backpack.size() != 0) {
			if (backpack.get(indexitemonhand) instanceof StackAble) {
				((StackAble) backpack.get(indexitemonhand)).clear();
			}
			if (isFull()) {
				setFull(false);
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

	public static boolean isFull() {
		return full;
	}
	
	public static void setFull(boolean set) {
		full=set;
	}
}
