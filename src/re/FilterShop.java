package re;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

//据库连接相关字符

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;


public class FilterShop {
	//据库连接相关字符
		public  final static String DBDRIVER="com.mysql.jdbc.Driver";
		public static final String DBDRIVER2="mysql-connector-java-5.0.8-bin.jar.com.mysql.jdbc.Driver";
		public final static String DBURL="jdbc:mysql://localhost:3306/unicom";
		public  final static String password="database";
		public  final static String user="root";
		public String [] charact="income,entertainment,baby,gender,shopping".split(",");
		private  final  double EARTH_RADIUS = 6378137;//赤道半径(单位m)  
		public double diff=0.005;
		public static  Connection conn=connectDatabase();
		
	public static void main(String[] args) throws IOException { 
		// TODO Auto-generated method stub
		FilterShop fs=new FilterShop();
//		fs.readFile("E:\\result3.csv");
//		fs.geneResult("E:\\result1.csv");
//		fs.testModel();
//		fs.buildTrainData(0,60);
//		fs.buildTrainData(163, 160);
//		fs.buildTrainData(323, 160);
//		fs.buildTrainData(1839, 161);
//		fs.buildTrainData(1990, 10);
//		fs.buildTrainData(1000, 1000, 1);
//		String file="E:/ChinaUnicomContest/libsvm-3.21/libsvm-3.21/tools/train_data2";
//		new StoreDataForLibsvm().writeDataToFile(file,8023,1);
//		fs.calShopHeat();
		
//		for(int i=0;i<5;i++)
//		{
//			fs.buildTrainData(i*60, 60);
//		}
//		
		

	}
	
	//创建训练集的数据库，对于原始训练集，提取出符合概率要求和符合距离要求的所有用户和推荐商户对
	public  void  buildTrainData(int start,int length,int interval) throws IOException
	{
		String sql ="select userid,shopid ,arrival_time from user_match_train limit "+start+","+ length;
		try {
			ResultSet userAndShop=conn.createStatement().executeQuery(sql);
			List<java.util.Map.Entry<Integer, Double>> neibors;
			int userid;
			int shopid;
			while(userAndShop.next())
			{
				userid=userAndShop.getInt(1);
				shopid=userAndShop.getInt(2);
				int point=0;
				//找出正样本的序位
				neibors=findNeibors(userid);
				for(int index=0;index<neibors.size();index++)
				{					
					int caledShopid=neibors.get(index).getKey();			
					//如果该邻居商户与实际推荐商户的id一致，说明是正样本
					if(shopid==caledShopid)
					{
						point=index;
					}
										
				}
		
				
				//对point附件的负样本加入到构造的训练集中
				for(int index2=Math.max(0, point-interval);index2<Math.min(point+interval+1, neibors.size());index2++)
				{
					int label=-1;
					if(index2==point)
						label=1;
					double distance=neibors.get(index2).getValue();
					//邻居商户的id
					int  caledShopid=neibors.get(index2).getKey();
//					System.out.println("用户id"+userid+"邻居商户id"+caledShopid);
					String classOfThisShop="select class from shop_profile where id="+caledShopid;
					ResultSet classofshop=conn.createStatement().executeQuery(classOfThisShop);
					   classofshop.next();
					int classOfshop=classofshop.getInt(1);
					//邻居商户的类别概率
					double pro_class=new FindShopClass(userid).class_Pro[classOfshop-1];
					String insertData="insert ignore into train_data2 ( userid,shopid,class_shop,pro_class ,dist,label ) values"
							+ "("+userid+","+caledShopid+","+classOfshop+","+pro_class+","+distance+","+label+")";
					conn.createStatement().executeUpdate(insertData);
					 label=-1;
				}
				System.out.println("用户"+userid+"的邻居商户数目");
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public void geneResult(String filepath)
	{
		//选取数据源
				String sql="select * from user_test";
				try {
					ResultSet test=conn.createStatement().executeQuery(sql);
					//对每一条测试集的数据计算 
					String insert="insert into test_result(userid,shopid,arrival_time) values(?,?,?)";
					PreparedStatement ptst=conn.prepareStatement(insert);;
					while(test.next())
					{
						int userid=test.getInt(1);					
						Timestamp time=test.getTimestamp(2);					
						int shopid=filterShop(userid);
						ptst.setInt(1, userid);
						ptst.setInt(2, shopid);
						ptst.setTimestamp(3, time);
						System.out.println("推荐商家的id:"+shopid);
						ptst.executeUpdate();	
					}
					ptst.close();
//					fs.conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		//将数据库中数据读取到文件中
				try {
					readFile(filepath);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	//测试训练集准确度
	public void  testModel()
	{
		FilterShop fs=new FilterShop();
		//选取数据源
		String sql="select userid,shopid from user_match_train";
		try {
			ResultSet test=conn.createStatement().executeQuery(sql);
			//对每一条测试集的数据计算 
			int corrcet=0;
			while(test.next())
			{
				int userid=test.getInt(1);
				int shopid=test.getInt(2);
				int recomendshopid=fs.filterShop(userid);
		if(shopid==recomendshopid)
		System.out.println("实际推荐商家的id:"+shopid+"模型计算出推荐商户id"+recomendshopid +"是否正确推荐"+(shopid==recomendshopid));
				if(shopid==recomendshopid)
					corrcet++;
			}
			System.out.println("训练集中准确推荐的商户"+corrcet);
//			fs.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//从数据库中将数据读到文件中
    public static void readFile(String filepath) throws IOException
    {
//    	File file =new File(filepath);
    	FileWriter fw=null;
//    	FileWriter fw=new FileWriter(new OutputStreamWriter(new FileOutputStream(file)));
    	try {
			fw=new FileWriter(filepath);
			fw.write("USERID");
			fw.write(",");
			fw.write("ARRIVAL_TIME");
			fw.write(",");
			fw.write("SHOPID");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	String sql="select * from user_test";
    	try {
			ResultSet res=conn.createStatement().executeQuery(sql);
			while(res.next())
			{
				fw.write(res.getInt(1)+" , ");
				fw.write(res.getInt(2)+" , ");
				fw.write(res.getTimestamp(3)+" "+"\n");
			}
			fw.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
	
    public double distBetweenUserAndShop(int userid,int shopid)
    {
    	String user_pos="select longti,attitude from use_trace where useid="+userid;
		String shop_pos="select longti,attitude from shop_profile where id="+shopid;
		double distance=0;
		try {
			ResultSet user_po = conn.createStatement().executeQuery(user_pos);
			ResultSet shop_po=conn.createStatement().executeQuery(shop_pos);
		      user_po.next();
		      shop_po.next();
		     distance =getDistWith(user_po.getDouble(1),user_po.getDouble(2),shop_po.getDouble(1),user_po.getDouble(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return distance;
		
	      
    }
    //查看训练集中用户与推荐商户的距离
	//发挥推荐商户的id
	public int filterShop(int userid)
	{
		//找出对应用户所有的邻居商户
		List<java.util.Map.Entry<Integer, Double>> neibors=findNeibors(userid);
		//对应于该用户概率排名前n的类别
		int classfi=new FindShopClass(userid).top(1);
		
		
		//对于每一个商户做概率分析
		int order=0;
	
//			System.out.println("推荐类别"+classfi[j]);
			for( java.util.Map.Entry<Integer, Double> neibor:neibors)
			{
				int neibor_id=neibor.getKey();
	//			System.out.println("邻居商户id:"+neibor_id);
				ResultSet shop=null;
				int shoprecomend=0;
				//选出该邻居商户对应的类别
				String classoFShop ="select class from shop_profile where id="+neibor_id;
				try {
					//shop容器放的是该邻居商户的类别
					 shop=conn.createStatement().executeQuery(classoFShop);
					 shop.next();
					 shoprecomend=shop.getInt(1);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				order++;
				//如果该邻居商户的类别与推荐的类别一致
				if(shoprecomend==classfi)
					
					
				{
					System.out.println("第"+order+"远的距离,其距离为："+neibor.getValue());
					return neibor_id;
				}
				else continue;
			}
		
		return 0;
	}
    private class CompareWithCount implements  Comparator<Map.Entry<Integer,Integer>>
    {

		@Override
		public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
			// TODO Auto-generated method stub
			return o2.getValue().compareTo(o1.getValue());
		}
    	
    }
    
	private class CompareWithDist implements Comparator<Map.Entry<Integer,Double>>
	{
        //升序排序
		@Override
		public int compare(java.util.Map.Entry<Integer, Double> o1, java.util.Map.Entry<Integer, Double> o2) {
			// TODO Auto-generated method stub
			return o1.getValue().compareTo(o2.getValue());
		}
	}
	public List<java.util.Map.Entry<Integer, Double>> findNeibors(double user_lon,double user_atitu,double r)
	{
		Map<Integer,Double> all_neibors=new TreeMap<Integer,Double>();
		
		ResultSet rs=null;
		//计算并筛选在指定范围内的商户
		String findShop="select id,longti,attitude from shop_profile";
		try {
			
			rs=conn.createStatement().executeQuery(findShop);
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
//				System.out.println("商户位置:"+shop_lonti+"  , "+shop_attitude);
				  //如果是邻居
				if(isNeiborWithR(user_lon,user_atitu,shop_lonti,shop_attitude,r) )
				{		
					all_neibors.put(rs.getInt(1),getDistWith( user_atitu,user_lon,shop_attitude,shop_lonti));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
List<Map.Entry<Integer,Double>> list=new ArrayList<Map.Entry<Integer,Double>>(all_neibors.entrySet());
Collections.sort(list,new CompareWithDist());
  
	
return list;

	}
	private boolean isNeiborWithR(double user_lon, double user_atitu, double shop_lonti, double shop_attitude,double r) {
		// TODO Auto-generated method stub
		double londiff=user_lon-shop_lonti;
		double atitudiff=user_atitu-shop_attitude;
		double distance=Math.sqrt(Math.pow(londiff, 2)+Math.pow(atitudiff, 2));
	
		return distance<r;
	}

	public List<java.util.Map.Entry<Integer, Double>> findNeibors(double user_lon,double user_atitu,int neiborNumber)
	{
	
		Map<Integer,Double> all_neibors=new TreeMap<Integer,Double>();
	
		ResultSet rs=null;
		//计算并筛选在指定范围内的商户
		String findShop="select id,longti,attitude from shop_profile";
		try {
			
			rs=conn.createStatement().executeQuery(findShop);
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
//				System.out.println("商户位置:"+shop_lonti+"  , "+shop_attitude);
				  //如果是邻居
				if(isNeibor(user_lon,user_atitu,shop_lonti,shop_attitude) )
				{		
					all_neibors.put(rs.getInt(1),getDistWith( user_atitu,user_lon,shop_attitude,shop_lonti));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
List<Map.Entry<Integer,Double>> list=new ArrayList<Map.Entry<Integer,Double>>(all_neibors.entrySet());
Collections.sort(list,new CompareWithDist());
  if(list.size()<neiborNumber)
	  return list;
	
return list.subList(0,neiborNumber);
		
	}
	//找出规定距离内的商户,选取前500个距离近的
	public List<java.util.Map.Entry<Integer, Double>> findNeibors(int userid)
		{
		
			Map<Integer,Double> all_neibors=new TreeMap<Integer,Double>();
			double lonti=0;
			double attitude=0;
//			int classfication=0;
			ResultSet rs=null;
			//获取用户的经纬度
			String findUser_Pos="select longti,attitude from use_trace where useid="+userid;
			try {
				
				rs=conn.createStatement().executeQuery(findUser_Pos);				
				while(rs.next())
				{
					lonti=rs.getDouble("longti");
					attitude=rs.getDouble("attitude");			
				}
//				ResultSet classfi=conn.createStatement().executeQuery("select class from user_match_train where userid= "+userid);
				  
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//计算并筛选在指定范围内的商户
			String findShop="select id,longti,attitude from shop_profile";
			try {
				
				rs=conn.createStatement().executeQuery(findShop);
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
//					System.out.println("商户位置:"+shop_lonti+"  , "+shop_attitude);
					  //如果是邻居
					if(isNeibor(lonti,attitude,shop_lonti,shop_attitude) )
					{		
						all_neibors.put(rs.getInt(1),getDistWith(attitude,lonti,shop_attitude,shop_lonti));
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			
	List<Map.Entry<Integer,Double>> list=new ArrayList<Map.Entry<Integer,Double>>(all_neibors.entrySet());
	Collections.sort(list,new CompareWithDist());
	  if(list.size()<400)
		  return list;
		
	return list.subList(0,400);
			
		}
	//根据用户以及用户对应类别找出该用户邻居商户
	public List<java.util.Map.Entry<Integer, Double>> findNeibors(int userid,int classfication)
	{
	
		Map<Integer,Double> all_neibors=new TreeMap<Integer,Double>();
		double lonti=0;
		double attitude=0;
//		int classfication=0;
		ResultSet rs=null;
		//获取用户的经纬度
		String findUser_Pos="select longti,attitude from use_trace where useid="+userid;
		try {
			
			rs=conn.createStatement().executeQuery(findUser_Pos);				
			while(rs.next())
			{
				lonti=rs.getDouble("longti");
				attitude=rs.getDouble("attitude");			
			}
//			ResultSet classfi=conn.createStatement().executeQuery("select class from user_match_train where userid= "+userid);
			  
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//计算并筛选在指定范围内的商户
		String findShop="select id,longti,attitude,class from shop_profile";
		try {
			
			rs=conn.createStatement().executeQuery(findShop);
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
//				System.out.println("商户位置:"+shop_lonti+"  , "+shop_attitude);
				  //如果是邻居 并且类别一致
				if(isNeibor(lonti,attitude,shop_lonti,shop_attitude)  && classfication==rs.getInt(4))
				{		
					all_neibors.put(rs.getInt(1),getDistWith(attitude,lonti,shop_attitude,shop_lonti));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
List<Map.Entry<Integer,Double>> list=new ArrayList<Map.Entry<Integer,Double>>(all_neibors.entrySet());
Collections.sort(list,new CompareWithDist());
  if(list.size()<400)
  { 
	  System.out.println("符合条件的商户数目    "+list.size());
	  return list;
  }
	 
  System.out.println("符合条件的商户数目    "+400);
     return list.subList(0,400);
		
	}
		
      
    /** 
     * 转化为弧度(rad) 
     * */  
    private  double rad(double d)  
    {  
       return d * Math.PI / 180.0;  
    }  
      
    /** 
     * 基于余弦定理求两经纬度距离 
     * @param lon1 第一点的精度 
     * @param lat1 第一点的纬度 
     * @param lon2 第二点的精度 
     * @param lat3 第二点的纬度 
     * @return 返回的距离，单位km 
     * */  
    public  double getDist(double lat1, double lng1, double lat2, double lng2)
	{
			   double radLat1 = rad(lat1);
			   double radLat2 = rad(lat2);
			   double a = radLat1 - radLat2;
			   double b = rad(lng1) - rad(lng2);
			
			   double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
			    Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
			   s = s * EARTH_RADIUS;
			   s = Math.round(s * 10000) / 10000;
			   return s;
	}
    public double getDistWith(double lat1, double lng1, double lat2, double lng2)
    {
    	double latdiff=lat1-lat2;
    	double londiff=lng1-lng2;
    	double dist=Math.sqrt(Math.pow(latdiff,2)+Math.pow(londiff,2));
//    	System.out.println("计算的距离"+dist);
    	return dist;
    }
       
	private boolean isNeibor(double user_lon,double user_atti,double shop_lon,double shop_attitude)
		{
		double londiff=user_lon-shop_lon;
		double attidiff=user_atti-shop_attitude;
		return (londiff<0.055)&&(attidiff<0.10);
			
		}
	
	
	

	//选出类别排名中前n名的类别
	
	public static Connection connectDatabase()
	{
		 try
       {
     	  Class.forName(DBDRIVER);
     	  return DriverManager.getConnection(DBURL, user,password);
     	    
       }
       catch(Exception e)
       {
     	  e.printStackTrace();
       }
		 return null;
		 
	}
	public Timestamp parseString(String date)
	{
		//解析日期数据		
		java.util.Date begin=null;
		try {
			begin = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Timestamp(begin.getTime());
	}	
  public static String time()
	{
	Date date=new Date();
	DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String time=format.format(date);
	return time;
	}

}
