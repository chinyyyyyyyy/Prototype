package Plant;

public abstract class Plant implements OnHandAble {
	protected int dayofgrowth;
	protected String name;
	protected int amount;
	protected int price;
	
	public void addDayOfGrowth() {
		dayofgrowth+= 1;
		System.out.println(dayofgrowth);
	}
	
	public String getName() {
		return this.name;
	}
	
	public abstract int checkState();
	
	@Override 
	public String toString() {
		return name;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public int getPrice() {
		return price;
	}

}


	
