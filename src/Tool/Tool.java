package Tool;

import Logic.InBackpack;
import SpecialScene.ToolStatus;

public abstract class Tool implements InBackpack {
	protected String name;
	protected double timeofuse;
	protected int level;

	public void cry() {
		System.out.println(name);
	}

	public abstract void Action(ActionByToolAble a);

	public void upgrade() {
		if (getTimeOfUse() == 300) {
			this.level += 2;
		} else if (getTimeOfUse() >= ToolStatus.getUpgradeLevel().get(level)) {
			this.level++;
		}
		System.out.println("Upgrade Successful !");
		CheckUpgrade();
	}

	public double getTimeOfUse() {
		if (timeofuse > 300)
			timeofuse = 300;
		return timeofuse;
	}

	public int getLevel() {
		return level;
	}

	public abstract boolean CheckUpgrade();
}
