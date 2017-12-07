package Tool;

import ComponentMap.Feild;
import SpecialScene.ToolStatus;
import ComponentMap.ActionByToolAble;

public class Hoe extends Tool {
	private static boolean upgradeable;
	private static String stat = "";

	public Hoe() {
		this.name = "HOE";
		this.timeofuse = 150;
		upgradeable = false;
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

	public void CheckUpgrade() {
		if (this.timeofuse >= ToolStatus.getUpgradeLevel().get(0))
			upgradeable = true;
	}

	public static String isUpgradeable() {
		if (upgradeable)
			return "upgrade?";
		return "";
	}

	public static void setUpgradeable(boolean upgradeable) {
		Hoe.upgradeable = upgradeable;
	}

	public static String getStat() {
		return stat;
	}

	public static void setStat(String stat) {
		Hoe.stat = stat;
	}
}
