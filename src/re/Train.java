package re;

public class Train implements Comparable<Train>{
	
	@Override
		// TODO Auto-generated method stub
	public int compareTo(Train arg0) {
		if(this.user_id<arg0.getUser_id())
			return 1;
		else if(this.user_id>arg0.getUser_id())
			return -1;
		else{
			if(!this.getArrival_time().equals(arg0.getArrival_time()))
				return 1;
			else
				return 0;
		}
	}
	private int user_id;
	private int income;
	private int entermament;
	private int baby;
	private int gender;
	private int shopping;
	private String shop_id;
	private int classification;
	private double userLongitude;
	private double userLatitude;
	private double shopLongitude;
	private double shopLatitude;
	private String arrival_time;
	private int duration;
	private String newShop_id;
	public Shop getShop()
	{
		return new Shop(Integer.parseInt(shop_id),classification,shopLongitude,shopLatitude);
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	public int getEntermament() {
		return entermament;
	}
	public void setEntermament(int entermament) {
		this.entermament = entermament;
	}
	public int getBaby() {
		return baby;
	}
	public void setBaby(int baby) {
		this.baby = baby;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getShopping() {
		return shopping;
	}
	public void setShopping(int shopping) {
		this.shopping = shopping;
	}
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	public int getClassification() {
		return classification;
	}
	public void setClassification(int classification) {
		this.classification = classification;
	}
	public double getUserLongitude() {
		return userLongitude;
	}
	public void setUserLongitude(double userLongitude) {
		this.userLongitude = userLongitude;
	}
	public double getUserLatitude() {
		return userLatitude;
	}
	public void setUserLatitude(double userLatitude) {
		this.userLatitude = userLatitude;
	}
	public double getShopLongitude() {
		return shopLongitude;
	}
	public void setShopLongitude(double shopLongitude) {
		this.shopLongitude = shopLongitude;
	}
	public double getShopLatitude() {
		return shopLatitude;
	}
	public void setShopLatitude(double shopLatitude) {
		this.shopLatitude = shopLatitude;
	}
	public String getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getNewShop_id() {
		return newShop_id;
	}
	public void setNewShop_id(String newShop_id) {
		this.newShop_id = newShop_id;
	}
}
