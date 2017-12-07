package Tool;

import ComponentMap.Feild;
import SpecialScene.ToolStatus;
import ComponentMap.ActionByToolAble;

public class Axe extends Tool {
	private static boolean upgradeable;
	private static boolean max;

	public Axe() {
		this.name = "AXE";
		this.timeofuse = 120;
		this.level=0;
	}

	@Override
	public void Action(ActionByToolAble a) {
		// TODO Auto-generated method stub
		if (a instanceof Feild) {
			if (((Feild) a).getWood() != null) {
				((Feild) a).delWood();
			}
		}

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
		Axe.upgradeable = upgradeable;
	}

}
