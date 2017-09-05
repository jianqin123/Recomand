package re;

public class Pos implements Comparable<Pos> {
	
	public double user_lon;
	public double user_lat;
	public Pos(double user_lon,double user_lat){
		this.user_lat=user_lat;
		this.user_lon=user_lon;
	
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		UserPos ut=(UserPos )obj;
		return  user_lon==ut.user_lon && user_lat==ut.user_lat;
	}
	
	@Override
	public int compareTo(Pos o) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
