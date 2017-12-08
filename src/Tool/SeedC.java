package Tool;

import ComponentMap.ActionByToolAble;
import ComponentMap.Feild;
import Logic.Backpack;
import Logic.InBackpack;
import Plant.PlantA;
import Plant.PlantC;

public class SeedC extends Seed implements InBackpack {
	private static int seedamout;
	private String name;

	public SeedC(int amount) {
		seedamout = amount;
		this.name = "PlantC";
	}
	
	public void cry() {
		System.out.println(name);
	}

	@Override
	public void Action(ActionByToolAble a) {
		if (a instanceof Feild) {
			if (((Feild) a).canplant() && seedamout > 0) {
				((Feild) a).setPlant(new PlantC());
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

	public static void addSeed(int numC) {
		// TODO Auto-generated method stub
		seedamout+=numC;
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
