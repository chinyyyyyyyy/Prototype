package Tool;

import ComponentMap.ActionByToolAble;
import ComponentMap.Feild;
import ComponentMap.SceneManager;
import Logic.Backpack;
import Logic.InBackpack;
import Map.Farm;
import Plant.PlantB;
import javafx.scene.image.Image;

public class SeedB extends Seed implements InBackpack{
	private static int seedamout;
	private String name;
	private Image seedimg = new Image(ClassLoader.getSystemResource("potatoseed.png").toString());

	public SeedB(int amount) {
		seedamout = amount;
		this.name = "SeedB";
	}
	
	public void cry() {
		System.out.println(name);
	}

	@Override
	public void Action(ActionByToolAble a) {
		Farm scene = (Farm) SceneManager.getListMap().get(0);
		if (a instanceof Feild) {
			if (((Feild) a).canplant() && seedamout > 0) {
				((Feild) a).setPlant(new PlantB());
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

	public static void addSeed(int numB) {
		// TODO Auto-generated method stub
		seedamout+=numB;
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
