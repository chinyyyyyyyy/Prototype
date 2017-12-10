package Tool;

import ComponentMap.Feild;
import SpecialScene.ToolStatus;
import javafx.scene.image.Image;
import ComponentMap.ActionByToolAble;

public class Hammer extends Tool {
	private static boolean upgradeable;
	private static boolean max;
	private static boolean upgrade02;
	private static boolean upgrade12;
	private static boolean upgrade01;
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
		
		setUpgrade01(false);
		setUpgrade02(false);
		setUpgrade12(false);
		if(this.level==1 && this.timeofuse >= ToolStatus.getUpgradeLevel().get(1)) {
			setUpgrade12(true);
		}else if(this.level==0 && this.timeofuse >= ToolStatus.getUpgradeLevel().get(1)) {
			setUpgrade02(true);
		}else if(this.level==0 && this.timeofuse >= ToolStatus.getUpgradeLevel().get(0)) {
			setUpgrade01(true);
		}
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
	
	public static String StateUpgradeable() {
		if (max)
			return "MAX";
		if (Hammer.isUpgrade12() || Hammer.isUpgrade02())
			return "Upgrade to Gold";
		if(Hammer.isUpgrade01())
			return "Upgrade to Silver";
		return "";
	}
	
	public static int UpgradeCost() {
		int cost=0;
		if (Hammer.isUpgrade12())
			cost=5000;
		if (Hammer.isUpgrade02())
			cost=8000;
		if(Hammer.isUpgrade01())
			cost=3000;
		return cost;
	}
	
	public static boolean isUpgrade02() {
		return upgrade02;
	}

	public static void setUpgrade02(boolean upgrade2) {
		Hammer.upgrade02 = upgrade2;
	}

	public static boolean isUpgrade01() {
		return upgrade01;
	}

	public static void setUpgrade01(boolean upgrade1) {
		Hammer.upgrade01 = upgrade1;
	}

	public static boolean isUpgrade12() {
		return upgrade12;
	}

	public static void setUpgrade12(boolean upgrade12) {
		Hammer.upgrade12 = upgrade12;
	}

	
}
