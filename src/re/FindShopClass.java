package re;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class FindShopClass {
	//据库连接相关字符
	public  final static String DBDRIVER="com.mysql.jdbc.Driver";
	public static final String DBDRIVER2="mysql-connector-java-5.0.8-bin.jar.com.mysql.jdbc.Driver";
	public final static String DBURL="jdbc:mysql://localhost:3306/unicom";
	public  final static String password="database";
	public  final static String user="root";
	
	public static  Connection conn=connectDatabase();
	//用户特征值字段
	public String[] charact="income,entertainment,baby,gender,shopping".split(",");
	//用户各特征值的权重
	public double[] weight=new double[5];
	public int[] uservalues=new int[5];
	public double[] class_Pro=new double[10];
	public  int userid;
	
	//针对某一特定取值的概率矩阵
	
//	public ArrayList<TreeMap<Integer,Double>> pro=new ArrayList<TreeMap<Integer,Double>>();
	
	
	public  FindShopClass(int userid)
	{
		this.userid=userid;
		calWeight();
		userValues();
		calClassPro();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		     String trandata="select userid,class from user_match_train";
		     ResultSet tranes;
		     int truecount=0;
		     int count=0;
			try {
				tranes = conn.createStatement().executeQuery(trandata);
				while(tranes.next())
			     {
					count++;
					FindShopClass fs=null;
			    	int recomendclass=0;
					try {
						fs=new FindShopClass(tranes.getInt(1));
						recomendclass = fs.maxProClass();
//						recomendclass=fs.top(1);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		 	  System.out.println("计算出推荐类别："+recomendclass+"  训练集实际类别： "+tranes.getInt(2)+"   是否一致"+(recomendclass==tranes.getInt(2)));
		      if(recomendclass==tranes.getInt(2))
		    	  truecount++;
		     
//		 	  System.out.println("各个特征值的权重："+fs.weight[0]+" "+fs.weight[1]+"  "+fs.weight[2]+"   "+fs.weight[3]+" "+fs.weight[4]);
			     }
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 System.out.println(truecount+"====="+count);
 
	}
	public void calClassPro()
	{
		//计算该用户对应于每个类别的概率
		for(int l=0;l<10;l++)
		  {
				  double pro=0;
	//			  System.out.println("类别---------------------"+l);
				  for(int a=0;a<5;a++)
				  {
					  String sql1="select pro from user_"+charact[a]+" where (value="+uservalues[a]+") and ( class="+l+")";
	//				  System.out.println(sql1);
					  
					  try {
						ResultSet proa=conn.createStatement().executeQuery(sql1);
						while(proa.next())
						{
	//						System.out.println(proa.getDouble(1)+"*"+weight[a]);
							pro+=(1/weight[a])*proa.getDouble(1);
						}
						
						
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				  }
			  class_Pro[l]=pro;  
		  }
				  
			  
	}
	//初始化关于该用户的权重矩阵和概率矩阵
	public void calWeight()
	{
		//Integer表示用户的
		
        ArrayList<ArrayList<Double>> weight_pro=new ArrayList<ArrayList<Double>>();			
		weight_pro.add(0,new ArrayList<Double>());
		weight_pro.add(1,new ArrayList<Double>());
		weight_pro.add(2,new ArrayList<Double>());
		weight_pro.add(3,new ArrayList<Double>());
		weight_pro.add(4,new ArrayList<Double>());
         userValues();
		
		//构建用户的概率矩阵
		for(int b=0;b<charact.length;b++)
		{
			//获取用户该特征值所对应的各个类别的概率，数目应该有10个，value表示的是用户的这个特征值所对应的取值
		String sqluser="select pro,value from user_"+charact[b]+" where value="+uservalues[b];
			try {
				ResultSet proes=conn.createStatement().executeQuery(sqluser);
				while(proes.next())
				{
					int value= proes.getInt(2);
					double pro=proes.getDouble(1);	
//					System.out.println(value+"  "+pro);
				    weight_pro.get(b).add(pro);		
				}
				//计算出各个特征值对应的权重
				weight[b]=calvar(weight_pro.get(b));
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//
		Double max=0.0;
		for(int i=0;i<charact.length;i++)
		{
			
			max+=weight[i];
		}
		for(int i=0;i<charact.length;i++)
		{
			
//			System.out.println("max:"+max);
			weight[i]=weight[i]/max;
		}

	}
	//初始化用户特征值
	public void userValues()
	{
		//找出该用户的特征值
				String sqluser="select * from use_profile where id="+userid;
				
				//计算用户各个特征值对应的权重
				
				
				//获取用户的信息
				try {
					ResultSet res=conn.createStatement().executeQuery(sqluser);
					res.next();
					uservalues[0]=res.getInt(2);
					uservalues[1]=res.getInt(3);
					uservalues[2]=res.getInt(4);
					uservalues[3]=res.getInt(5);
				    uservalues[4]=res.getInt(6);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}
	//暂时先选出最符合的，即概率最大的类别
	public int maxProClass()
	{
		double pro=Double.MIN_NORMAL;
		int maxProClass=0;
		
		for(int l=0;l<class_Pro.length;l++)
		{
			if(pro<class_Pro[l])
			{
				pro=class_Pro[l];
				maxProClass=l;
			}
		}
		return maxProClass;
	}
	public int top(int n)
	{
		 double max_pro=0;
		 int class_max=0;
		 
		//对10个类别，每个类别都计算对应的概率
		  for(int l=10;l>=1;l--)
		  {
			  double pro=0;

//			  System.out.println("类别---------------------"+l);
			  for(int a=0;a<5;a++)
			  {
				  String sql1="select pro from user_"+charact[a]+" where (value="+uservalues[a]+") and ( class="+l+")";
//				  System.out.println(sql1);
				  
				  try {
					ResultSet proa=conn.createStatement().executeQuery(sql1);
					while(proa.next())
					{
//						System.out.println(proa.getDouble(1)+"*"+weight[a]);
						pro+=(1/weight[a])*proa.getDouble(1);
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  
				  
			  }
			 
//			  System.out.println("类别!!!!!!!!!!!!!!!!!!!!!!!!!!"+l+"的概率："+pro);
			  if(pro>max_pro)
			  {
				  
				  max_pro=pro;
				  class_max=l;
			  }
			  
		  }
		  return class_max;
	}
	public int[]  topN(int n)
		{
			
			//分别计算到10个类别上的概率
			 double max_pro=0;
			 int class_max=0;
			 double second_pro=-1;
			 int class_second  =0;
			 double third_pro=-2;
			 int class_third=0;
			//对10个类别，每个类别都计算对应的概率
			  for(int l=10;l>=1;l--)
			  {
				  double pro=0;

//				  System.out.println("类别---------------------"+l);
				  for(int a=0;a<5;a++)
				  {
					  String sql1="select pro from user_"+charact[a]+" where (value="+uservalues[a]+") and ( class="+l+")";
//					  System.out.println(sql1);
					  
					  try {
						ResultSet proa=conn.createStatement().executeQuery(sql1);
						while(proa.next())
						{
//							System.out.println(proa.getDouble(1)+"*"+weight[a]);
							pro+=weight[a]*proa.getDouble(1);
						}
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  
					  
				  }
				 
//				  System.out.println("类别!!!!!!!!!!!!!!!!!!!!!!!!!!"+l+"的概率："+pro);
				  if(pro>max_pro)
				  {
					  third_pro=second_pro;
					  class_third=class_second;
					  second_pro=max_pro;
					  class_second=class_max;
					  max_pro=pro;
					  class_max=l;
				  }
				  
			  }
			  int [] classes={class_max,class_second,class_third};
			return  classes;
			
		}

	public double calvar(ArrayList<Double> doubles)
	{
		Iterator it=doubles.iterator();
		double squl=0;
		for(double d:doubles)
		{
			squl+=d;
		}
		squl=squl/doubles.size();
		double var=0;
		
		for(double d:doubles)
		{
			var+=Math.pow(d-squl, 2);
		}
		return Math.sqrt(var);
		
	}
	public static Connection  connectDatabase()
	{
		 try
         {
       	  Class.forName(DBDRIVER);
       	 
//       	    System.out.println(conn);
         }
         catch(Exception e)
         {
       	  e.printStackTrace();
         }
		try {
			return DriverManager.getConnection(DBURL, user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return null; 
	}
}
