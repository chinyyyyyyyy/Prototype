package Tool;

import ComponentMap.Feild;
import SpecialScene.ToolStatus;
import javafx.scene.image.Image;
import ComponentMap.ActionByToolAble;

public class Hammer extends Tool {
	private static boolean upgradeable;
	private static boolean max;
	private Image bronze = new Image(ClassLoader.getSystemResource("bronze_hammer.png").toString());
	private Image silver = new Image(ClassLoader.getSystemResource("silver_hammer.png").toString());
	private Image gold = new Image(ClassLoader.getSystemResource("gold_hammer.png").toString());


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
		Hammer.upgradeable = upgradeable;
	}
	
	public Image getImage() {
		if(this.level == 2) {
			return gold;
		}else if (this.level == 1) {
			return silver;
		}else {
			return bronze;
		}
	}
	
}
