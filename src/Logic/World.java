package Logic;

import java.util.ArrayList;
import java.util.List;
import ComponentMap.Dropbox;
import ComponentMap.HasAnimal;
import ComponentMap.Hero;
import ComponentMap.SceneManager;
import Map.House;
import Map.setsceneable;
import SpecialScene.BuyScene;

public class World {
	private static boolean nextday=false;
	private static int days;
	private static List<updateEveryday> listupdate = new ArrayList<>();
	private static int money;
	private static boolean Buyable;
	private static List<String> season = new ArrayList<>();
	private static int ss = 0;
	private static setsceneable scene;
	public World() {
		days = 1;
		money = 50000;
		Buyable = true;
		season.add("Spring");
		season.add("Summer");
		season.add("Autumn");
		season.add("Winter");
	}

	public static void nextDay() {
		for (updateEveryday i : listupdate) {
			i.updateafterendday();
		}
	
		for(setsceneable i : SceneManager.getListMap()) {
			if(i instanceof HasAnimal) {
				((HasAnimal) i).update();
			}
		}
		SceneManager.warpTo(19);
	}
	
	public static void Tomorrow() {
		days += 1;
		if (days > 30) {
			ss = (ss + 1) % 4;
			days = 1;
		}	
		setMoney(money+Dropbox.getTotalsell());
		scene = SceneManager.getListMap().get(2);
		((House) scene).chat("Good Morning " + Hero.getName());
		((House) scene).chat("Today is day " + World.getDay());
		System.out.println("Good Morning " + Hero.getName());
		System.out.println("Today is day " + World.getDay());
		Dropbox.clear();
		setNextday(true);
	}

	public static int getDay() {
		return days;
	}

	public static List<updateEveryday> getListUpdate() {
		return listupdate;
	}

	public static String getSeason() {
		return season.get(ss);
	}

	public static int getMoney() {
		return money;
	}

	public static void setMoney(int x) {
		scene = SceneManager.getListMap().get(SceneManager.getCurrentScene());
		if (x < 0) {
			((BuyScene) scene).chat("You don't have enough money.");
			System.out.println("You don't have enough money.");
			System.out.println("You have " + money + "$");
			Buyable = false;
		} else {
			money = x;
			Buyable = true;
			System.out.println("You have " + money + "$");
		}
	}

	public static boolean getBuyable() {
		return Buyable;
	}

	public static boolean isNextday() {
		return nextday;
	}

	public static void setNextday(boolean nextday) {
		World.nextday = nextday;
	}
}
