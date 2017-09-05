package re;

public class Shop implements Comparable<Shop>{
	private int shop_id;
	private String name;
	private int classification;
	private double longitude;
	private double latitude;
	public Shop(int shopid,int classification,double longitude,double latitude)
	{
		this.classification=classification;
		this.latitude=latitude;
		this.longitude=longitude;
		this.name=null;
	}
	public Shop()
	{}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getClassification() {
		return classification;
	}
	public void setClassification(int classification) {
		this.classification = classification;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	@Override
	public int compareTo(Shop o) {
		// TODO Auto-generated method stub
		if(this.shop_id==o.shop_id)
		  return 0;
		else
			return 1;
	}
}
