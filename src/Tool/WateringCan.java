package Tool;

import ComponentMap.Feild;
import ComponentMap.Pond;
import SpecialScene.ToolStatus;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import ComponentMap.ActionByToolAble;

public class WateringCan extends Tool {
	private static final int MAX_CAPACITY = 10;
	private static int water_level;
	private static boolean max;
	private static boolean upgradeable;
	private static boolean upgrade02;
	private static boolean upgrade01;
	private static boolean upgrade12;
	private Image bronze = new Image(ClassLoader.getSystemResource("bronze_can.png").toString());
	private Image silver = new Image(ClassLoader.getSystemResource("silver_can.png").toString());
	private Image gold = new Image(ClassLoader.getSystemResource("gold_can.png").toString());
	public static AudioClip soundeffect = new AudioClip(ClassLoader.getSystemResource("watersound.mp3").toString());

	public WateringCan() {
		this.name = "WATERINGCAN";
		this.timeofuse = 0;
		water_level = 0;
	}

	@Override
	public void Action(ActionByToolAble a) {
		// TODO Auto-generated method stub
		if (a instanceof Pond) {
			soundeffect.play();
			water_level = MAX_CAPACITY;
			System.out.println("Water level MAX");
		} else if (a instanceof Feild && water_level > 0 && ((Feild) a).canwater()) {
			soundeffect.play();
			((Feild) a).watering();
			water_level--;
			this.timeofuse++;
		}
	}

	public void cry() {
		System.out.println(name + " has " + water_level);
	}

	public boolean CheckUpgrade() {
		if (this.level == 2) {
			upgradeable = false;
			max = true;
		} else if (this.timeofuse >= ToolStatus.getUpgradeLevel().get(this.level))
			upgradeable = true;
		else
			upgradeable = false;

		setUpgrade01(false);
		setUpgrade02(false);
		setUpgrade12(false);
		if (this.level == 1 && this.timeofuse >= ToolStatus.getUpgradeLevel().get(1)) {
			setUpgrade12(true);
		} else if (this.level == 0 && this.timeofuse >= ToolStatus.getUpgradeLevel().get(1)) {
			setUpgrade02(true);
		} else if (this.level == 0 && this.timeofuse >= ToolStatus.getUpgradeLevel().get(0)) {
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
		WateringCan.upgradeable = upgradeable;
	}

	public static double getWaterLevel() {
		return (water_level + 0.0) / MAX_CAPACITY;
	}

	public Image getImage() {
		if (this.level == 2) {
			return gold;
		} else if (this.level == 1) {
			return silver;
		} else {
			return bronze;
		}
	}

	public static String StateUpgradeable() {
		if (max)
			return "       MAX";
		if (WateringCan.isUpgrade12() || WateringCan.isUpgrade02())
			return "Upgrade to Gold";
		if (WateringCan.isUpgrade01())
			return "Upgrade to Silver";
		return "";
	}

	public static int UpgradeCost() {
		int cost = 0;
		if (WateringCan.isUpgrade12())
			cost = 5000;
		if (WateringCan.isUpgrade02())
			cost = 8000;
		if (WateringCan.isUpgrade01())
			cost = 3000;
		return cost;
	}

	public static boolean isUpgrade02() {
		return upgrade02;
	}

	public static void setUpgrade02(boolean upgrade2) {
		WateringCan.upgrade02 = upgrade2;
	}

	public static boolean isUpgrade01() {
		return upgrade01;
	}

	public static void setUpgrade01(boolean upgrade1) {
		WateringCan.upgrade01 = upgrade1;
	}

	public static boolean isUpgrade12() {
		return upgrade12;
	}

	public static void setUpgrade12(boolean upgrade12) {
		WateringCan.upgrade12 = upgrade12;
	}

}
