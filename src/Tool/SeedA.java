package Tool;

import ComponentMap.Field;
import Logic.Backpack;
import Logic.InBackpack;
import Logic.SceneManager;
import Map.Farm;
import Plant.PlantA;
import javafx.scene.image.Image;

public class SeedA extends Seed implements InBackpack {
	private static int seedamout;
	private String name;
	private Image seedimg = new Image(ClassLoader.getSystemResource("radishseed.png").toString());

	public SeedA(int amount) {
		seedamout = amount;
		this.name = "SeedA";
	}
	
	public void cry() {
		System.out.println(name);
	}

	@Override
	public void Action(ActionByToolAble a) {
		Farm scene = (Farm) SceneManager.getListMap().get(0);
		if (a instanceof Field) {
			if (((Field) a).canplant() && seedamout > 0) {
				((Field) a).setPlant(new PlantA());
			}
			seedamout--;
			scene.chat("Seed Left = " + seedamout);
			System.out.println("Seed Left = " + seedamout);
			if (seedamout == 0) {
				scene.chat("Seed Out");
				System.out.println("Seed Out");
				Backpack.deleteItem();
			}
		}
	}

	public void CheckUpgrade() {
	}

	public static void addSeed(int numA) {
		// TODO Auto-generated method stub
		seedamout+=numA;
	}
	
	public int getAmount() {
		return seedamout;
	}
	
	public static int getSeedAmount() {
		return seedamout;
	}
	
	public void clear() {
		seedamout=0;
	}
	
	public Image getImage() {
		return seedimg;
	}
}
