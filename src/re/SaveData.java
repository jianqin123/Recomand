package re;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.crypto.Data;

public class SaveData {
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
	public Connection conn;
	public PreparedStatement ptst;
	public SaveData()
	{
		connectDatabase();
	}

	public static void main(String[] args) throws SQLException, IOException {
		// TODO Auto-generated method stub
	SaveData sd=	new SaveData();
	       
	       
//	       sd.readUser_Profile();
//	       sd.readShop();
//	       sd.readUser_Trace();
//	       sd.readTrain();
//	       sd.readTest();
	       sd.addClassToMatch();
//	       sd.addUser_ProfileToMatch();
//           sd.addDist();
//           sd.calShopHeat();
//	         sd.addOrder();
//           sd.writeToFile("E:/ChinaUnicomContest/all_train.csv");
	
          
	}
	//将商户位置次序加入
	public  void addOrder() 
	{
		
		String userid_sql="select userid,shopid from user_match_train";
		try {
			ResultSet userides=conn.createStatement().executeQuery(userid_sql);
			while(userides.next())
			{
				int userid=userides.getInt(1);
				int shopid=userides.getInt(2);
				int order=0;
				for(java.util.Map.Entry<Integer, Double> neibor:new FilterShop().findNeibors(userid))
				{
					order++;
					if(neibor.getKey()==shopid)
					{
						String updateOrder="update user_match_train set order_class= "+order+" where userid= "+userid;
						conn.createStatement().executeUpdate(updateOrder);
						break;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//将user_train_match中的数据写到文件中
	
	   public void writeToFile(String file) throws SQLException
	   {
		   try {
			FileWriter fs=new FileWriter(file);
			
			String  querytData="select * from user_match_train ";
			ResultSet res=conn.createStatement().executeQuery(querytData);
			String str="userid ,shopid, classfication ,heat ,count ,income ,entertainment,"
					+ " shopping,baby, gender,dist,order"+"\n";
			fs.write(str);
			while(res.next())
			{
			   int userid=res.getInt(1);
			   int shopid=res.getInt(2);
			   int classfication=res.getInt(3);
			   double heat=res.getDouble(4);
			   int count=res.getInt(5);
			  
			   int income=res.getInt(7);
			   int entertainment=res.getInt(8);
			   int shopping=res.getInt(9);
			   int baby=res.getInt(10);
			   int gender=res.getInt(11);
			   double dist=res.getDouble(12);
			   int order=res.getInt(13);
			   fs.write(userid+",");
			   fs.write(shopid+",");
			   
			   
			   fs.write(classfication+",");
			   fs.write(heat+",");
			   fs.write(count+",");
			   fs.write(income+",");
			   fs.write(entertainment+",");
			   fs.write(shopping+",");
			   fs.write(baby+",");
			   fs.write(gender+",");
			   fs.write(dist+",");
			   fs.write(order+",");
			   fs.write("\n");
			   
			   
			   
				 
			}
			fs.flush();
			fs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	//计算商户热度
		public void calShopHeat()
		{
			String shopid_sql="select shopid from user_match_train";
			try {
				ResultSet shopides=conn.prepareStatement(shopid_sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery();
				int order=1;
				while(shopides.next())
				{
					int shop_id=shopides.getInt(1);
					shopides.absolute(1);
					int count=0;
					while(shopides.next())
					{
						if(shop_id==shopides.getInt(1))
							count++;
					}
					double heat=(double)count/(double)2000;
					String addHeat="update user_match_train set heat = "+heat +", count= "+count+" where shopid= "+shop_id;
					conn.createStatement().executeUpdate(addHeat);
					shopides.absolute(order);
					order++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	//连接数据库
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
	//将用户与实际推荐商店的距离加入到训练集中
	   public void addDist()
	   {
		   int userid=0;
		   int shopid=0;
		   String  sql="select userid,shopid from user_match_train";
//		   String addDist="alter table user_match_train add dist double(8,4)";
		 
		 
		   try {
			ResultSet userAndShop=conn.createStatement().executeQuery(sql);
			while(userAndShop.next())
			{
				userid=userAndShop.getInt(1);
				shopid=userAndShop.getInt(2);
				String user_pos="select longti,attitude from use_trace where useid="+userid;
				String shop_pos="select longti,attitude from shop_profile where id="+shopid;
				ResultSet user_po=conn.createStatement().executeQuery(user_pos);
				ResultSet shop_po=conn.createStatement().executeQuery(shop_pos);
			      user_po.next();
			      shop_po.next();
			      double distance=getDistWith(user_po.getDouble(1),user_po.getDouble(2),shop_po.getDouble(1),user_po.getDouble(2));
			    String updataDist="update user_match_train set dist="+distance+"where ( userid="+userid+") and (shopid="
			    		+shopid+")";
			    conn.createStatement().executeUpdate(updataDist);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
	   }
	//将用户的信息添加到训练集中
		public void  addUser_ProfileToMatch()
		{
		//获取用户id
		 int userid=0;
         String  sql="select userid from user_match_train";
         //增加用户信息
         String addUser_Profile="alter table user_match_train add income int(2),"+
                                "add entertainment int(2),add baby int(2),add gender int(2),"+
                                "add shopping int(2) after userid";
	     try {
	    	   conn.createStatement().executeUpdate(addUser_Profile);
				ResultSet useridset=  conn.createStatement().executeQuery(sql);
				while(useridset.next())
				{
					userid=useridset.getInt(1);
					System.out.println(useridset.getInt(1));
					String getUserprofile="select income,entertainment,baby,gender,shopping from use_profile where id="+useridset.getInt(1);
					ResultSet user_pro=conn.createStatement().executeQuery(getUserprofile);
					user_pro.next();
					  
					String insertUser_profile="update user_match_train set income="+user_pro.getInt(1)+" ,entertainment="+user_pro.getInt(2)+
					                           ",baby="+user_pro.getInt(3)+",gender="+user_pro.getInt(4)+" ,shopping="+user_pro.getInt(5)+" where userid="+userid;
					
					System.out.println(insertUser_profile);
					conn.createStatement().executeUpdate(insertUser_profile);
				}
		//根据训练集的每一条信息的用户id,获取用户特征值，并且将用户的特征值添加到训练集中
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 	
		}
		//将用户类别的信息添加到训练集的数据库中
	    public void addClassToMatch()
	    {
	    	String  sql="select shopid from user_match_train";
	    	String addClass="alter table user_match_train add class int(1) after shopid";
//	    	Statement state;
	    	ResultSet shop_ids=null;
	    	//从数据库中读取shopid
	    	try {
				
//				conn.createStatement().executeUpdate(addClass);
				shop_ids=conn.createStatement().executeQuery(sql);
								
				while(shop_ids.next())
				{
					//对每条训练数据添加字段classification
					int shop_id=shop_ids.getInt(1);
					String classficaiton ="select class from shop_profile where  id="+shop_id;
					ResultSet classfi=conn.createStatement().executeQuery(classficaiton);
					classfi.next();
					int cla=classfi.getInt(1);
					System.out.println(shop_id+"   "+ cla);
					String updateClass="update user_match_train set class="+cla+" where shopid="+shop_id;
					System.out.println(updateClass);
					conn.createStatement().executeUpdate(updateClass);			
				}
				
//				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	         	
	    }
		//将用户的数据存入数据库中
		public void readUser_Profile()throws IOException{
			File f=new File(FILEPATHUSERPROFILE);
			FileInputStream fis=new FileInputStream(f);
			InputStreamReader isr=new InputStreamReader(fis);
			BufferedReader bufr=new BufferedReader(isr);
			String temp=bufr.readLine();
			
			String sql="insert into  use_profile(id,income,entertainment,baby,gender,shopping )values(?,?,?,?,?,?)";
			while((temp=bufr.readLine())!=null){
				String[] str=temp.split(",");
				int user_id=(int)Double.parseDouble(str[0]);
				int income=Integer.parseInt(str[1]);
				int ENTERTAINMENT=Integer.parseInt(str[2]);
				int BABY=Integer.parseInt(str[3]);
				int gender=Integer.parseInt(str[4]);
				int SHOPPING=Integer.parseInt(str[5]);
				System.out.println(user_id+"  "+income+"  "+ENTERTAINMENT+"  "+BABY+"  "+gender+"  "+SHOPPING);
	    //插入用户数据
						try {
								ptst=conn.prepareStatement(sql);
								ptst.setInt(1, user_id);
								ptst.setInt(2, income);
								ptst.setInt(3,ENTERTAINMENT);
								ptst.setInt(4, BABY);
								ptst.setInt(5, gender);
								ptst.setInt(6, SHOPPING);
								ptst.executeUpdate();
								ptst.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					
			    }
			
			bufr.close();
			isr.close();
			fis.close();
			
		}
		/*函数功能：读取用户轨迹相关信息。属性：用户编码(USERID),开始时间(BEGIN_TIME),经度(STARTLONGTITUDE),纬度(STARTLANTITUDE),停留时间(DURATION)*/
		public  void readUser_Trace()throws IOException{
			File f=new File(FILEPATHUSERTRACE);
			FileInputStream fis=new FileInputStream(f);
			InputStreamReader isr=new InputStreamReader(fis);
			BufferedReader bufr=new BufferedReader(isr);
			String temp=bufr.readLine();			
			String sql="insert into use_trace(useid,  begin_time,longti, attitude, duration)values(?,?,?,?,?)";
			while((temp=bufr.readLine())!=null){
				String[] str=temp.split(",");
				
				int user_id=(int)Double.parseDouble(str[0]);					
			    Timestamp begin_time=parseString(str[1]);
				double startLongitude=Double.parseDouble(str[2]);
				double startLatitude=Double.parseDouble(str[3]);
				int duration=(int)Double.parseDouble(str[4]);
				
				System.out.println(user_id+" " +begin_time+"  "+startLongitude+"  "+startLatitude+"  "+duration);
				try {
					ptst=conn.prepareStatement(sql);
					ptst.setInt(1, user_id);
					ptst.setTimestamp(2, begin_time);
					ptst.setDouble(3, startLongitude);
					ptst.setDouble(4, startLatitude);
					ptst.setInt(5, duration);
					ptst.executeUpdate();
					ptst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			bufr.close();
			isr.close();
			fis.close();
			
			
		}
		/*函数功能：读取店铺相关信息*/
		public  void readShop()throws IOException{
			File f=new File(FILEPATHSHOPPROFILE);
			FileInputStream fis=new FileInputStream(f);
			InputStreamReader isr=new InputStreamReader(fis);
			BufferedReader bufr=new BufferedReader(isr);
			String temp="";				
//			TreeMap<Integer,Shop> shopMap=new TreeMap<Integer,Shop>();
			String sql="insert into shop_profile(id, shop_name,class, longti,attitude)values(?,?,?,?,?)";
			while((temp=bufr.readLine())!=null){
				String[] str=temp.split(",");
				int shop_id=(int)Double.parseDouble(str[4]);
				String name=str[0];
				int classification=Integer.parseInt(str[3]);
				double longitude=Double.parseDouble(str[1]);
				double latitude=Double.parseDouble(str[2]);
				System.out.print(shop_id+"  ");
				System.out.print(name+"   ");
				System.out.print(classification+"   ");
				System.out.print(longitude+"   ");
				System.out.println( latitude+"   ");
				
				
				
	//插入商户数据
					try {
						ptst=conn.prepareStatement(sql);
						ptst.setInt(1, shop_id);
						
						ptst.setString(2, name);
						ptst.setInt(3, classification);
						ptst.setDouble(4, longitude);
						ptst.setDouble(5, latitude);
						ptst.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
			
//				shopMap.put(shop_id,shop);
			}
				bufr.close();
				isr.close();
				fis.close();
//			Object[] obj=new Object[]{shopArr,shopMap};
//			return obj;
			
		}

		//函数功能：读取训练集数据
		public  void  readTrain()throws IOException{
			File f=new File(FILEPATHTRAIN);
			FileInputStream fis=new FileInputStream(f);
			InputStreamReader isr=new InputStreamReader(fis);
			BufferedReader bufr=new BufferedReader(isr);
			String temp=bufr.readLine();//将第一行去掉，第一行为属性标签
			
			String sql="insert into  user_match_train(userid,shopid, arrival_time) values(?,?,?)";
			while((temp=bufr.readLine())!=null){
				String[] str=temp.split(",");
				int user_id=(int)Double.parseDouble(str[0]);
				int shop_id=(int)Double.parseDouble(str[1]);
				Timestamp arrival_time=parseString(str[2]);
				System.out.println(user_id+"  "+shop_id+"  "+arrival_time);
				try {
					ptst=conn.prepareStatement(sql);
					ptst.setInt(1, user_id);
					ptst.setInt(2, shop_id);
					ptst.setTimestamp(3, arrival_time);
					ptst.executeUpdate();
					ptst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			bufr.close();
			isr.close();
			fis.close();
			
		}
		//函数功能：读取测试集数据
		public void readTest()throws IOException{
			File f=new File(FILEPATHTEST);
			FileInputStream fis=new FileInputStream(f);
			InputStreamReader isr=new InputStreamReader(fis);
			BufferedReader bufr=new BufferedReader(isr);
			String temp=bufr.readLine();//将第一行去掉，第一行为属性标签
		
			String sql="insert into  user_test(userid,arrival_time)values(?,?) ";
			while((temp=bufr.readLine())!=null){
				String[] str=temp.split(",");
				System.out.println(str[0]+"  "+str[1]);
				int user_id=(int)Double.parseDouble(str[0]);
				Timestamp arrival_time=parseString(str[1]);
				System.out.print(user_id+"  ");
				System.out.println(arrival_time);
				try {
					ptst=conn.prepareStatement(sql);
					ptst.setInt(1, user_id);
					ptst.setTimestamp(2,arrival_time);
					ptst.executeUpdate();
					ptst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			bufr.close();
			isr.close();
			fis.close();
			
		}
		//解析日期数据
		public Timestamp parseString(String date)
		{
			//解析日期数据		
			java.util.Date begin=null;
			try {
				begin = new SimpleDateFormat("yyyyMMddhhmmss").parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return new Timestamp(begin.getTime());
		}
		public double getDistWith(double lat1, double lng1, double lat2, double lng2)
	    {
	    	double latdiff=lat1-lat2;
	    	double londiff=lng1-lng2;
	    	return Math.sqrt(Math.pow(latdiff,2)+Math.pow(londiff,2));
	    }
}
