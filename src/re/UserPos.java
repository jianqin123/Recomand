package re;

public class UserPos implements Comparable<UserPos> {
	public int userid;
	public double user_lon;
	public double user_lat;
	public UserPos(int uid,double user_lon,double user_lat){
		this.user_lat=user_lat;
		this.user_lon=user_lon;
		this.userid=uid;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		UserPos ut=(UserPos )obj;
		return userid == ut.userid && user_lon==ut.user_lon && user_lat==ut.user_lat;
	}
	
	@Override
	public int compareTo(UserPos o) {
		// TODO Auto-generated method stub
		return new Integer(userid).compareTo(new Integer(o.userid));
	}

	

}
