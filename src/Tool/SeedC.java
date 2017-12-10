package Tool;

import ComponentMap.ActionByToolAble;
import ComponentMap.Feild;
import ComponentMap.SceneManager;
import Logic.Backpack;
import Logic.InBackpack;
import Map.Farm;
import Plant.PlantC;
import javafx.scene.image.Image;

public class SeedC extends Seed implements InBackpack {
	private static int seedamout;
	private String name;
	private Image seedimg = new Image(ClassLoader.getSystemResource("cucumberseed.png").toString());

	public SeedC(int amount) {
		seedamout = amount;
		this.name = "SeedC";
	}
	
	public void cry() {
		System.out.println(name);
	}

	@Override
	public void Action(ActionByToolAble a) {
		Farm scene = (Farm) SceneManager.getListMap().get(0);
		if (a instanceof Feild) {
			if (((Feild) a).canplant() && seedamout > 0) {
				((Feild) a).setPlant(new PlantC());
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

	public static void addSeed(int numC) {
		// TODO Auto-generated method stub
		seedamout+=numC;
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
