package re;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class ConnectionDemo2 {
	public static final String DBDRIVER="com.mysql.jdbc.Driver";
	public static final String DBDRIVER2="mysql-connector-java-5.0.8-bin.jar.com.mysql.jdbc";
	public static final String DBURL="jdbc:mysql://localhost:3306/db";
	public static final String password="database";
	public static final String user="root";

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
	 Connection conn=null;
	 Statement stmt=null;
	 String showbases="show databases";
	 String usedb="use db";
	 String dropTable="drop table table1";
	 ResultSet res=null;
	 System.out.println("java 是大 sb");
          try
          {
        	  Class.forName(DBDRIVER);
          }
          catch(ClassNotFoundException e)
          {
        	  e.printStackTrace();
          }
         try
         {
        	 conn=DriverManager.getConnection(DBURL, user,password);
        	 stmt=conn.createStatement();
         }
         catch(SQLException e)
         {
        	 e.printStackTrace();
         }
         System.out.println(conn+"  :"+stmt+"sdfs");
         res=stmt.executeQuery(showbases);
       
     
         
          
	}

}

