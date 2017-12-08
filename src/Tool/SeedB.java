package Tool;

import ComponentMap.ActionByToolAble;
import ComponentMap.Feild;
import Logic.Backpack;
import Logic.InBackpack;
import Plant.PlantA;
import Plant.PlantB;

public class SeedB extends Seed implements InBackpack{
	private static int seedamout;
	private String name;

	public SeedB(int amount) {
		seedamout = amount;
		this.name = "PlantB";
	}
	
	public void cry() {
		System.out.println(name);
	}


	@Override
	public void Action(ActionByToolAble a) {
		if (a instanceof Feild) {
			if (((Feild) a).canplant() && seedamout > 0) {
				((Feild) a).setPlant(new PlantB());
			}
			seedamout--;
			System.out.println("Seed Left = " + seedamout);
			if (seedamout == 0) {
				System.out.println("Seed Out");
				Backpack.deleteItem();
				Backpack.ChangeItem();
			}
		}
	}

	public void CheckUpgrade() {
	}

	public static void addSeed(int numB) {
		// TODO Auto-generated method stub
		seedamout+=numB;
	}
	
	public int getAmount() {
		return seedamout;
	}
	
	public static int getSeedAmount() {
		return seedamout;
	}
	
	public void clear() {
		seedamout=0;
	}
}
