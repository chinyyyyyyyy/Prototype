package Logic;

import java.util.ArrayList;
import java.util.List;
import ComponentMap.Dropbox;
import ComponentMap.HasAnimal;
import ComponentMap.Hero;
import ComponentMap.SceneManager;
import Map.setsceneable;

public class World {
	private static boolean nextday=false;
	private static int days;
	private static List<updateEveryday> listupdate = new ArrayList<>();
	private static int money;
	private static boolean Buyable;
	private static List<String> season = new ArrayList<>();
	private static int ss = 0;
	public World() {
		days = 1;
		money = 5000;
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
		if (x < 0) {
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
