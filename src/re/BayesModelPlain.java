package re;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BayesModelPlain {
	//数据库连接相关字符
		public  final String DBDRIVER="com.mysql.jdbc.Driver";
		public final String DBURL="jdbc:mysql://localhost:3306/unicom";
		public  final String password="database";
		public  final String user="root";
		
		//存储客户的特征字段
		public String[] charact=new String[5];
		//存储概率矩阵
		public  ArrayList<double[][]> p;
		public int[] ks;
		
		public Connection conn;
		
	BayesModelPlain()
	{
		
		p=new ArrayList<double[][]>();
		connectDatabase();
		charact="income,entertainment,baby,gender,shopping".split(",");
		ks=new int[]{100,100,100,100,100};
		
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new  BayesModelPlain().buildBayesModel();

	}
	//建立概率矩阵
	public void buildBayesModel()
	{
		calMatricPro(1-1,new int[]{1,2,3,4},ks[1-1]);//income
		calMatricPro(2-1,new int[]{1,2,3,4,5,6,7},ks[2-1]);//entertainment
		calMatricPro(3-1,new int[]{0,1},ks[3-1]);//baby
		calMatricPro(4-1,new int[]{0,1},ks[4-1]);//gender
		calMatricPro(5-1,new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14},ks[5-1]);//shopping
	}
//   
	
    //计算关于第i个特征值的概率矩阵
       //m表示的是用户第i个特征值所能够取的数字集合，10表示的是商户的类别数总共有10,k表示的是参数
      public void calMatricPro(int i,int[] m,double k)
      {
    	  //m表示的是用户第i个特征值所能够取的数字，10表示的是商户的类别数总共有10    	  
    	   PreparedStatement ptst;
    	   ResultSet matchofClass;
    	   
        try {
				
				for(int l=1;l<=10;l++)
		    	   {   
					 //属于该类别的匹配数目
		    		   String countofClass="select count(*)  from user_match_train where class="+l;
		    		 //匹配该类别下用户 id
		    		   String matchedUserId="select * from user_match_train where class="+l;
		    		   matchofClass=conn.createStatement().executeQuery(matchedUserId);
		    		   
		    		  ResultSet rest=  conn.createStatement().executeQuery(countofClass);
		    		    rest.next();
		    		    
		    		    
		    		  //训练集中该类别的数量  
		    		   double cs=rest.getDouble(1);
		    		   System.out.println("-------------------------------"+"类别"+l+"该类别中的样本数"+cs);
		    		   //m[q]表示的是第i个特征值的取值为m[q]时
		    		    for(int q=0;q<m.length;q++)
		    		    {
		    		    	String  getcumsn="select count(*) from user_match_train where ( class="+l
		    		    			+") and ("+charact[i]+"="+m[q]+")";
		    		    	ResultSet res=conn.createStatement().executeQuery(getcumsn);
		    		    	res.next();
		    		    	
//		    		    	double cumsn=calUsers(matchofClass,charact[i],m[q]);
		    		    	double cumsn=res.getDouble(1);
		    		    	System.out.println(charact[i]+"取值为"+m[q]+"时的数目  ："+cumsn);
		    		    	double pro=(cumsn+k/(double)m.length)/(cs+k);
		    		    	
		    		   //将数据插入到数据库中
		    		    	String proinsert="insert into user_"+charact[i]+"(value,class,pro)values(?,?,?)";
		    		    	
		    		    	System.out.println(proinsert+":"+cumsn+"  "+k+"  "+m.length+"  "+cs+"  "+pro);
		    		    	ptst=conn.prepareStatement(proinsert);
		    		    	ptst.setInt(1, m[q]);
		    		    	ptst.setInt(2, l);
		    		    	ptst.setDouble(3,pro);
		    		    	ptst.executeUpdate();
		    		    	ptst.close();
		    		    	
		    		    }		    		   		    		 		    		   
		    	   }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
    	 
      }
      //解析字符到日期
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
}
