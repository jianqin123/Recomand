package re;

public class Probabilty implements Comparable<Probabilty>{
	private double pro;
	private String shopid;
	
	public Probabilty(double pro,String shopid)
	{
		this.pro=pro;
		this.shopid=shopid;
	}
	public double getPro() {
		return pro;
	}
	public void setPro(double pro) {
		this.pro = pro;
	}
	public String getShopid() {
		return shopid;
	}
	public void setShopid(String shopid) {
		this.shopid = shopid;
	}
	@Override
	public int compareTo(Probabilty o) {
		// TODO Auto-generated method stub
		return new Double(o.getPro()).compareTo(getPro());
	}
	

}
