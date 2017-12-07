package Tool;

import ComponentMap.Feild;
import SpecialScene.ToolStatus;
import ComponentMap.ActionByToolAble;

public class Axe extends Tool {
	private static boolean upgradeable;
	private static String stat = "";

	public Axe() {
		this.name = "AXE";
		this.timeofuse = 0;
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
		if (this.timeofuse >= ToolStatus.getUpgradeLevel().get(0))
			upgradeable = true;
	}

	public static String isUpgradeable() {
		if (upgradeable)
			return "upgrade?";
		return "";
	}

	public static void setUpgradeable(boolean upgradeable) {
		Axe.upgradeable = upgradeable;
	}

	public static String getStat() {
		return stat;
	}

	public static void setStat(String stat) {
		Axe.stat = stat;
	}

}
