package re;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import libsvm.svm_model;

//将训练集中的数据存储到文件中
public class StoreDataForLibsvm {
	public  final static String DBDRIVER="com.mysql.jdbc.Driver";
	public static final String DBDRIVER2="mysql-connector-java-5.0.8-bin.jar.com.mysql.jdbc.Driver";
	public final static String DBURL="jdbc:mysql://localhost:3306/unicom";
	public  final static String password="database";
	public  final static String user="root";
	public String [] charact="income,entertainment,baby,gender,shopping".split(",");
	public static  Connection conn=connectDatabase();
	public svm_model model;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file="E:/ChinaUnicomContest/libsvm-3.21/libsvm-3.21/tools/train_data2";
       new StoreDataForLibsvm().writeDataToFile(file,60,1);
	}
     public void  writeDataToFile(String file,int number,int length)
     {
    	 try {
			FileWriter fw=new FileWriter(file);
			String sql="select label,class_shop , pro_class,dist from train_data2";
			try {
					ResultSet res=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery();
					
					res.next();
					for(int index=0;index<number;index++)
					{
					double label=res.getDouble(1);
					double class_shop=(double)res.getInt(2)/10;
					double pro_class=res.getDouble(3);
					double dist=res.getDouble(4);
					String data=label+" "+1+":"+class_shop+" "+2+":"+pro_class+" "+3+":"+dist+"\n";
					res.relative(length);
					System.out.println(data);
					fw.write(data);
					}
					sql="select userid,class,dist from user_match_train";
					
					res=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery();
					
					while(res.next())
					{
						int class_shop=res.getInt(2);
						int userid=res.getInt(1);
						if(class_shop==0)
							break;
						double pro_class=new FindShopClass(userid).class_Pro[class_shop-1];
						double dist=res.getDouble(3);
						String data=1+" "+1+":"+(double)class_shop/10+" "+2+":"+pro_class+" "+3+":"+dist+"\n";
						System.out.println(data);
						fw.write(data);
						
					}
					
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fw.flush();
				fw.close();
				
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
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
}
