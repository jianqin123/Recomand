package re;

import java.util.Date;

public class UserTrace implements Comparable{
	private String uid;
	private String arrTime;
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		UserTrace ut=(UserTrace )obj;
		return uid.equals(ut.getUid()) && arrTime.equals(ut.getArrTime());
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getArrTime() {
		return arrTime;
	}
	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}
	public UserTrace(String uid,String arrTime){
		this.uid=uid;
		this.arrTime=arrTime;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
