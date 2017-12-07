package Tool;

import ComponentMap.ActionByToolAble;
import Logic.InBackpack;

public abstract class Tool implements InBackpack{
	protected String name;
	protected double timeofuse;

	public void cry() {
		System.out.println(name);
	}

	public abstract void Action(ActionByToolAble a);

	public double getTimeOfUse() {
		if(timeofuse>300) timeofuse=300;
		return timeofuse;
	}

	public abstract void CheckUpgrade();
}
