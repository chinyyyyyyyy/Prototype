package Tool;

import ComponentMap.Feild;
import ComponentMap.Pond;
import SpecialScene.ToolStatus;
import javafx.scene.image.Image;
import ComponentMap.ActionByToolAble;

public class WateringCan extends Tool {
	private static final int MAX_CAPACITY = 10;
	private static int water_level;
	private static boolean max;
	private static boolean upgradeable;
	private Image bronze = new Image(ClassLoader.getSystemResource("bronze_can.png").toString());
	private Image silver = new Image(ClassLoader.getSystemResource("silver_can.png").toString());
	private Image gold = new Image(ClassLoader.getSystemResource("gold_can.png").toString());

	

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
		WateringCan.upgradeable = upgradeable;
	}
	
	public static double getWaterLevel() {
		return (water_level+0.0)/MAX_CAPACITY;
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
