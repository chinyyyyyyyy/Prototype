package Tool;

import ComponentMap.Feild;
import SpecialScene.ToolStatus;
import ComponentMap.ActionByToolAble;

public class Hoe extends Tool {
	private static boolean upgradeable;
	private static boolean max;

	public Hoe() {
		this.name = "HOE";
		this.timeofuse = 300;
		upgradeable = false;
		this.level = 0;
	}

	@Override
	public void Action(ActionByToolAble a) {
		// TODO Auto-generated method stub
		if (a instanceof Feild && ((Feild) a).canshovel()) {
			((Feild) a).shovel();
			this.timeofuse++;
			CheckUpgrade();
		}
	}

	public boolean CheckUpgrade() {
		if (this.level == 2) {
			upgradeable = false;
			max = true;
		}else if (this.timeofuse >= ToolStatus.getUpgradeLevel().get(this.level))
			upgradeable = true;
		else
			upgradeable = false;
		return upgradeable;
	}

	public static String isUpgradeable() {
		if (max)
			return "MAX";
		if (upgradeable)
			return "upgrade?";
		return "";
	}

	public static void setUpgradeable(boolean upgradeable) {
		Hoe.upgradeable = upgradeable;
	}
}
