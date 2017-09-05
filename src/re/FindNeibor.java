package re;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FindNeibor {
	public static final String FILEPATHUSERPROFILE="E:\\ChinaUnicomContest\\SYS.CCF_USER_PROFILE.csv";//用户信息文件
	public static final String FILEPATHUSERTRACE="E:\\ChinaUnicomContest\\user_trace.csv";//用户轨迹文件
	public static final String FILEPATHSHOPPROFILE="E:\\ChinaUnicomContest\\shop_good.csv";//店铺信息文件
	public static final String FILEPATHTRAIN="E:\\ChinaUnicomContest\\SYS.CCF_US_MATCH_TRAIN.csv";
	public static final String FILEPATHTEST="E:\\ChinaUnicomContest\\SYS.CCF_US_MATCH_TEST2.csv";
	//数据库连接相关字符
	public  final String DBDRIVER="com.mysql.jdbc.Driver";
	public static final String DBDRIVER2="mysql-connector-java-5.0.8-bin.jar.com.mysql.jdbc.Driver";
	public final String DBURL="jdbc:mysql://localhost:3306/unicom";
	public  final String password="database";
	public  final String user="root";
	//规定距离内的商户
	public int distance=300;
	
	public Connection conn;
	public PreparedStatement ptst;
	public Statement state;
	public FindNeibor()
	{
		connectDatabase();
		try {
			state=conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	FindNeibor fn=	new FindNeibor();
	ArrayList<Integer> neiborsof=null;
		String findUser_Id="select useid from use_trace ";
		try {
			ResultSet users=fn.state.executeQuery(findUser_Id);
			while(users.next())
			{
				int user_id=users.getInt(1);
				System.out.println(user_id);
			    neiborsof=fn.findNeibors(user_id);
			    break;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	neiborsof=fn.findNeibors(2544);

	}
	public void connectDatabase()
	{
		 try
         {
       	  Class.forName(DBDRIVER);
       	  conn=DriverManager.getConnection(DBURL, user,password);
       	    System.out.println(conn);
         }
         catch(Exception e)
         {
       	  e.printStackTrace();
         }
		 
	}
	//找出规定距离内的商户
	public ArrayList<Integer> findNeibors(int userid)
	{
		ArrayList<Integer> neibors=new ArrayList<Integer>();
		double lonti=0;
		double attitude=0;
		ResultSet rs=null;
		//获取用户的经纬度
		String findUser_Pos="select longti,attitude from use_trace where useid="+userid;
		try {
			state=conn.createStatement();
			rs=state.executeQuery(findUser_Pos);
			while(rs.next())
			{
				lonti=rs.getDouble("longti");
				attitude=rs.getDouble("attitude");
				break;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//计算并筛选在指定范围内的商户
		String findShop="select id,longti,attitude from shop_profile";
		try {
			state=conn.createStatement();
			rs=state.executeQuery(findShop);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//对查询得到的商户位置数据集进行计算
		try {
			while(rs.next())
			{
				double shop_lonti=rs.getDouble(2);
				double shop_attitude=rs.getDouble(3);
				if(isNeibor(lonti,attitude,shop_lonti,shop_attitude))
				{
					int shop_id=rs.getInt(1);
					System.out.println(shop_id);
					neibors.add(new Integer(shop_id));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return neibors;
		
	}
	//根据经纬度计算距离,直接用勾股定理计算
	private boolean isNeibor(double user_lon,double user_atti,double shop_lon,double shop_attitude)
	{
		
		return Math.sqrt(Math.pow(user_lon-shop_lon, 2)+Math.pow(user_atti-shop_attitude,2))<distance;
	}
	//
	private boolean isNeiborPlus()
	{
		
		return false;
	};

}
