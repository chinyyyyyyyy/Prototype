package Tool;

import ComponentMap.Feild;
import SpecialScene.ToolStatus;
import ComponentMap.ActionByToolAble;

public class Hammer extends Tool {
	private static boolean upgradeable;
	private static boolean max;

	public Hammer() {
		this.name = "HAMMER";
		this.timeofuse = 0;
	}

	@Override
	public void Action(ActionByToolAble a) {
		// TODO Auto-generated method stub
		if (a instanceof Feild) {
			if (((Feild) a).getStone() != null) {
				((Feild) a).delStone();
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
		Hammer.upgradeable = upgradeable;
	}
	
}
