package Tool;

import ComponentMap.ActionByToolAble;
import ComponentMap.Feild;
import Logic.Backpack;
import Plant.PlantA;

public class SeedplantA extends Tool {
	private int seedamout;

	public SeedplantA(int amount) {
		this.seedamout = amount;
		this.name = "PlantA";
	}

	@Override
	public void Action(ActionByToolAble a) {
		if (a instanceof Feild) {
			if (((Feild) a).canplant() && seedamout > 0) {
				((Feild) a).setPlant(new PlantA());
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
	
}
