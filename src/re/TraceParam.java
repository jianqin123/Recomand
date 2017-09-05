package re;

public class TraceParam implements Comparable{
  
	private double dbu;
	private double dbs;
	
	private  int shopHeat;
	private double shopPro;
	
	private  int userHisClaCou;
	private int userHisShopCou;
	private double userHisClaPro;
	private double userHisShopPro;
	
	private int shopHisLabelCou;
	private double shopHisLabelPro;
	public String shopid;

	public TraceParam( double dbu, double dbs,  int shopHeat ,double shopPro,
			int userHisClaCou,int userHisShopCou, double userHisClaPro, double userHisShopPro, int shopHisLabelCou,double shopHisLabelPro,String shopid)
	{
		this.dbs=dbs;
		this.dbu=dbu;
		
		this.shopHeat=shopHeat;
		this.shopPro=shopPro;
		
		this.userHisClaCou=userHisClaCou;
		this.userHisShopCou=userHisShopCou;
		this.userHisClaPro=userHisClaPro;
		this.userHisShopPro=userHisShopPro;
		
		this.shopHisLabelCou=shopHisLabelCou;
		this.shopHisLabelPro=shopHisLabelPro;
		this.shopid=shopid;
	}
	public double getDbu() {
		return dbu;
	}
	public void setDbu(double dbu) {
		this.dbu = dbu;
	}
	public double getDbs() {
		return dbs;
	}
	public void setDbs(double dbs) {
		this.dbs = dbs;
	}
	public int getShopHeat() {
		return shopHeat;
	}
	public void setShopHeat(int shopHeat) {
		this.shopHeat = shopHeat;
	}
	public double getShopPro() {
		return shopPro;
	}
	public void setShopPro(double shopPro) {
		this.shopPro = shopPro;
	}
	public int getUserHisClaCou() {
		return userHisClaCou;
	}
	public void setUserHisClaCou(int userHisClaCou) {
		this.userHisClaCou = userHisClaCou;
	}
	public int getUserHisShopCou() {
		return userHisShopCou;
	}
	public void setUserHisShopCou(int userHisShopCou) {
		this.userHisShopCou = userHisShopCou;
	}
	public double getUserHisClaPro() {
		return userHisClaPro;
	}
	public void setUserHisClaPro(double userHisClaPro) {
		this.userHisClaPro = userHisClaPro;
	}
	public double getUserHisShopPro() {
		return userHisShopPro;
	}
	public void setUserHisShopPro(double userHisShopPro) {
		this.userHisShopPro = userHisShopPro;
	}
	public int getShopHisLabelCou() {
		return shopHisLabelCou;
	}
	public void setShopHisLabelCou(int shopHisLabelCou) {
		this.shopHisLabelCou = shopHisLabelCou;
	}
	public double getShopHisLabelPro() {
		return shopHisLabelPro;
	}
	public void setShopHisLabelPro(double shopHisLabelPro) {
		this.shopHisLabelPro = shopHisLabelPro;
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		TraceParam obj=(TraceParam)o;
//		  if(obj.getUserHisShopPro()>getUserHisShopPro()) return 1;
//		  else if(obj.getUserHisShopPro()<getUserHisShopPro()) return -1;
//		  else{
//			  if(obj.getUserHisClaPro()>getUserHisClaPro()) return 1;
//			  else if(obj.getUserHisClaPro()<getUserHisClaPro()) return -1;
//			  else{
//				  if(obj.getShopPro()>getShopPro()) return 1;
//				  else if(obj.getShopPro()>getShopPro()) return -1;
//				  else {
//					  
//				  }
//			  }
//		  }
		if(obj.getUserHisShopPro()==getUserHisShopPro()){
			if(obj.getUserHisClaPro()==getUserHisClaPro()){
				if(obj.getShopPro()==getShopPro()){
					if(obj.getShopHisLabelPro()==getShopHisLabelPro()){
						return new Double(obj.getDbs())	.compareTo(new Double(getDbs()));				
					}
					else return  new Double(obj.getShopHisLabelPro()).compareTo(new Double(getShopHisLabelPro()));
				}
				else return new Double(obj.getShopPro()).compareTo(new Double(getShopPro()));
			}
			else return new Double(obj.getUserHisClaPro()).compareTo(new Double(getUserHisClaPro()));
			
		}
		else return new Double(obj.getUserHisShopPro()).compareTo(new Double(getUserHisShopPro()));
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
