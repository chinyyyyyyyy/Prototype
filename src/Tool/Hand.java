package Tool;

import ComponentMap.ActionByToolAble;
import ComponentMap.Dropbox;
import ComponentMap.Feild;
import ComponentMap.SceneManager;
import Logic.Backpack;
import Logic.InBackpack;
import Map.Farm;
import Plant.OnHandAble;
import Plant.Plant;
import Plant.PlantA;
import Plant.PlantB;
import Plant.PlantC;
import javafx.scene.image.Image;

public class Hand implements InBackpack {
	private static OnHandAble onhand;
	private String name;

	public Hand() {
		onhand = null;
		this.name = "Hand";
	}

	@Override
	public void Action(ActionByToolAble a) {
		// TODO Auto-generated method stub
		Farm scene = (Farm) SceneManager.getListMap().get(0);
		if (onhand == null && a instanceof Feild && ((Feild) a).getPlant() != null) {
			Plant x = ((Feild) a).getPlant();
			if (x instanceof PlantA) {
				if (((PlantA) x).getAmount() == 0) {
					if(Backpack.isFull()) {
						scene.chat("Your bag is full.");
						System.out.println("Your bag is full.");
						return;
					}
					else Backpack.addItem(new PlantA());
				}
				((PlantA) x).Harvest();
			} else if (x instanceof PlantB) {
				if (((PlantB) x).getAmount() == 0) {
					if(Backpack.isFull()) {
						scene.chat("Your bag is full.");
						System.out.println("Your bag is full.");
						return;
					}
					else Backpack.addItem(new PlantB());
				}
				((PlantB) x).Harvest();
			} else {
				if (((PlantC) x).getAmount() == 0) {
					if(Backpack.isFull()) {
						scene.chat("Your bag is full.");
						System.out.println("Your bag is full.");
						return;
					}
					else Backpack.addItem(new PlantC());
				}
				((PlantC) x).Harvest();
			}
			((Feild) a).resetPlant();
		} 
		
		if (onhand != null && a instanceof Dropbox) {
			Dropbox.addProduct(onhand);
			onhand = null;
		}
	}

	public void cry() {
		if (onhand == null) {
			System.out.println(name);
		} else {
			System.out.println(name + " is holding " + onhand.getName());
		}
	}

	public void CheckUpgrade() {
	}

	public static void setOnhand(OnHandAble a) {
		onhand = a;
	}
	
	public Image getImage() {
		return null;
	}
}
