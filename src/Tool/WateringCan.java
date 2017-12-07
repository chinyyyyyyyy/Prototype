package Tool;

import ComponentMap.Feild;
import ComponentMap.Pond;
import SpecialScene.ToolStatus;
import ComponentMap.ActionByToolAble;

public class WateringCan extends Tool {
	private final int MAX_CAPACITY = 10;
	private int water_level;
	private static boolean max;
	private static boolean upgradeable;

	public WateringCan() {
		this.name = "WATERINGCAN";
		this.timeofuse = 0;
		water_level = 0;
	}

	@Override
	public void Action(ActionByToolAble a) {
		// TODO Auto-generated method stub
		if (a instanceof Pond) {
			water_level = MAX_CAPACITY;
			System.out.println("Water level MAX");
		} else if (a instanceof Feild && water_level > 0 && ((Feild) a).canwater()) {

			((Feild) a).watering();
			water_level--;
		}
	}


	public void cry() {
		System.out.println(name + " has " + water_level);
	}

	public void CheckUpgrade() {
		if (this.level == 2) {
			upgradeable = false;
			max = true;
			return;
		}
		if (this.timeofuse >= ToolStatus.getUpgradeLevel().get(this.level))
			upgradeable = true;
		else
			upgradeable = false;
	}

	public static String isUpgradeable() {
		if (max)
			return "MAX";
		if (upgradeable)
			return "upgrade?";
		return "";
	}

	public static void setUpgradeable(boolean upgradeable) {
		WateringCan.upgradeable = upgradeable;
	}

}
