package re;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;
import libsvm.svm_parameter;
import libsvm.svm_problem;

public class SVMBuild {
	public  final static String DBDRIVER="com.mysql.jdbc.Driver";
	public static final String DBDRIVER2="mysql-connector-java-5.0.8-bin.jar.com.mysql.jdbc.Driver";
	public final static String DBURL="jdbc:mysql://localhost:3306/unicom";
	public  final static String password="database";
	public  final static String user="root";
	public String [] charact="income,entertainment,baby,gender,shopping".split(",");
	private  final  double EARTH_RADIUS = 6378137;//赤道半径(单位m)  
	public static  Connection conn=connectDatabase();
	//svm参数
	public svm_model[] models;
	//惩罚因子
	public double C=32;
    //gama参数
	public double gama=2.0;
    //eps参数
	public double eps=0.00001;
	public double [] weight={1,1};

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SVMBuild svm=new SVMBuild();
		
		svm.modelsBuild(1,8023,1);
//		svm.saveModels();
//		svm.modelTrain(10);
		svm.predict(2000);
		svm.testDataByModel(0,608);
//		svm.testDataByModel();
		

	}
	public void saveModels()
	{
		try
		{
			for(int index=0;index<models.length;index++)
			{
				svm.svm_save_model("models"+index, models[index]);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void modelsBuild(int length,int number,int interval)
	{
		models=new svm_model[length];
		for(int index=0;index<length;index++)
		{
			modelTrainFor(number,interval,index);
			System.out.println("第"+index+"个模型建立完成");
		}
	}
	//对训练集user_match_train数据集进行测试，看是否准确,end表示预测的前end位数据
	public void predict(int end)
	{
//		System.out.println("user_match_train predict");
		String test="select userid ,shopid from user_match_train limit 0,"+end;
//		String addShopid="alter table user_test add shopid int(5) after userid";
		int countOfTruePredict=0;
		try {
			
//			conn.createStatement().executeUpdate(addShopid);
			ResultSet testes=conn.createStatement().executeQuery(test);
			testes.next();
			int predicted=0;
				while(end-->0)
				{
					System.out.println("testes.next()");
					int userid=testes.getInt(1);
					int recomendShopActual=testes.getInt(2);					
					testes.relative(1);
					//以下对邻居商户计算
					List<java.util.Map.Entry<Integer, Double>> neibors=new FilterShop().findNeibors(userid);
					int diffmax=0;
					int diffmax_shopid=0;
							for(java.util.Map.Entry<Integer, Double> neibor:neibors)
							{
		//						System.out.println("for(java.util.Map.Entry<Integer, Double> neibor:neibors)");
								//该用户与邻居商户的距离
								double distance=neibor.getValue();
								//邻居商户的id
						    	int	 caledShopid=neibor.getKey();
								String classOfThisShop="select class from shop_profile where id="+caledShopid;
								ResultSet classofshop=conn.createStatement().executeQuery(classOfThisShop);
								   classofshop.next();
								 //商户的类别
								   int classOfshop=classofshop.getInt(1);
								//邻居商户的类别概率
								   double pro_class=new FindShopClass(userid).class_Pro[classOfshop-1];
								//如果该邻居商户与实际推荐商户的id一致，说明是正样本
								
								//将测试集中数据的信息加载进来
								 svm_node [] datatest=new svm_node[3];
								 for(int j=0;j<3;j++)
								 {
									 datatest[j]=new svm_node();
								 }
								 datatest[0].index=1;
								 datatest[0].value=(double)classOfshop/10;
								 datatest[1].index=2;
								 datatest[1].value=pro_class;
								 datatest[2].index=3;
								 datatest[2].value=distance;
								 int length=models.length;
								 int counfOfPositive=0;
								 int countOfNegative=0;
								 
								 for(int index=0;index<length;index++)
								 {
									 double testlabel=svm.svm_predict(models[index],datatest);
									 if(testlabel==1.0)
										 counfOfPositive++;
									 else
										 countOfNegative++;
								 }
								 if(countOfNegative<counfOfPositive )
									{
										 countOfTruePredict++;
									   if(caledShopid==recomendShopActual)
										 System.out.println("user_match_train中预测为正确的userid="+userid+"用户id="+diffmax_shopid+"  正确预测数目:"+countOfTruePredict+" 总预测数："+predicted);
										break;
								
									}
								
								 
		//						System.out.println("for(java.util.Map.Entry<Integer, Double> neibor:neibors) end");
						}
							
						
					predicted++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double rate=((double)countOfTruePredict)/2000;
		System.out.println("预测正确数目"+countOfTruePredict+"预测正确率"+rate);
	}
	//对test数据进行预测，并将结果保存到其中,end表示预测test中前end位数据
	public void testDataByModel(int start,int end)
	{
		String test="select userid from user_test limit  "+start+" , "+end;
//		String addShopid="alter table user_test add shopid int(5) after userid";
		try {
//			conn.createStatement().executeUpdate(addShopid);
			ResultSet testes=conn.createStatement().executeQuery(test);
			
				while(testes.next())
				{
					System.out.println("testDataByModel while(testes.next())");
					int userid=testes.getInt(1);
					//用户与商户的距离
					double distance=0;
					int caledShopid=0;
					int classOfshop;
					double pro_class;
					//以下对邻居商户计算
					List<java.util.Map.Entry<Integer, Double>> neibors=new FilterShop().findNeibors(userid);
					for(java.util.Map.Entry<Integer, Double> neibor:neibors)
					{
						//该用户与邻居商户的距离
						distance=neibor.getValue();
						//邻居商户的id
						 caledShopid=neibor.getKey();
						String classOfThisShop="select class from shop_profile where id="+caledShopid;
						ResultSet classofshop=conn.createStatement().executeQuery(classOfThisShop);
						   classofshop.next();
						 //商户的类别
						 classOfshop=classofshop.getInt(1);
						//邻居商户的类别概率
						pro_class=new FindShopClass(userid).class_Pro[classOfshop-1];
						//如果该邻居商户与实际推荐商户的id一致，说明是正样本
						
						//将测试集中数据的信息加载进来
						 svm_node [] datatest=new svm_node[3];
						 for(int j=0;j<3;j++)
						 {
							 datatest[j]=new svm_node();
						 }
						 datatest[0].index=1;
						 datatest[0].value=(double)classOfshop/10;;
						 datatest[1].index=2;
						 datatest[1].value=pro_class;
						 datatest[2].index=3;
						 datatest[2].value=distance;
						 int length=models.length;
						 int counfOfPositive=0;
						 int countOfNegative=0;
						 for(int index=0;index<length;index++)
						 {
							 double testlabel=svm.svm_predict(models[index],datatest);
							 if(testlabel==1.0)
								 counfOfPositive++;
							 else
								 countOfNegative++;
						 }
						 
						String update="update user_test set shopid="+caledShopid+" where userid="+userid;
				  //如果预测label==1,说明该预测的邻居
			        if(counfOfPositive>countOfNegative)
						{
							conn.createStatement().executeUpdate(update);
							System.out.println("user_test中预测为正确的userid="+userid+"用户id="+caledShopid);
					//此处break保证返回的是预测的最近的 并且预测的是为应该推荐商户
							break;
							
						}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//训练SVM模型，并抽取构造训练集中的部分数据来测试,numbers表示数据长度，interval表示数据间隔
	public svm_model modelTrain(int numbers,int interval,int order)
	{
		//初始化形成训练集
		  svm_node[][] testData = new svm_node[numbers+2000][3];
		  double [] labels=new  double[numbers+2000];
		  String traindata="select userid, shopid,class ,dist from user_match_train limit 0,2000";
		  ResultSet trainDataes;
		 //对训练集中的2000条数据加入到训练模型中 
		 try {
			trainDataes=conn.createStatement().executeQuery(traindata);
			
			trainDataes.next();
			for(int index=0;index<2000;index++)
			{
//				System.out.println("训练集中正样本测试点"+index+"开始");
				int userid=trainDataes.getInt(1);
				int shopid=trainDataes.getInt(2);
				int class_shop=trainDataes.getInt(3);
				double dist=trainDataes.getDouble(4);
				
				double pro_class=new FindShopClass(userid).class_Pro[class_shop-1];
				trainDataes.relative(1);
				for(int j=0;j<3;j++)
				{
					testData[index][j]=new svm_node(); 
//					testData[index][j].index=j
				}
				testData[index][0].index=1;
				testData[index][1].index=2;
				testData[index][2].index=3;
				testData[index][0].value=(double)class_shop/10;
				testData[index][1].value=pro_class;
				testData[index][2].value=dist;
				labels[index]=1;
				System.out.println("训练集中正样本测试点"+index+"结束");
				
				
			}

					
					
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  
		 try {
				  String testdata="select class_shop,pro_class,dist,label from train_data2 limit "+order+" , "+interval*numbers;
				  ResultSet dataSet = conn.prepareStatement(testdata,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery();
				 dataSet.next();
				for(int i=2000;i<2000+numbers;i++)
					 {					
						
						 int class_shop=dataSet.getInt(1);
						 double pro_class=dataSet.getDouble(2);
						 double dist=dataSet.getDouble(3);
						 labels[i]=dataSet.getDouble(4);
						 dataSet.relative(interval);
						
						 
						for(int j=0;j<3;j++)
						{
							testData[i][j]=new svm_node();
						}
//						System.out.println("测试点"+i+"开始");
						
						 
						 testData[i][0].index=1;
						 testData[i][1].index=2;
						 testData[i][2].index=3;
						 testData[i][0].value=(double)class_shop/10;
						 testData[i][1].value=pro_class;
						 testData[i][2].value=dist;	 
						 System.out.println("测试点"+i+"结束"+"商户类别"+class_shop);
						
					 }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		//初始化模型参数
	      	svm_problem sp = new svm_problem(); 
	      		sp.x=testData;
	      		sp.y=labels;
	      		sp.l=numbers+2000; 
		
		 //初始化svm参数
        svm_parameter prm = new svm_parameter();  
         //
        prm.svm_type = svm_parameter.C_SVC;  
        prm.kernel_type = svm_parameter.RBF; 
        //numbers=20000
//        prm.C = 0.125;    
//        prm.gamma = 0.0078125; 
        //numbers=?
//        prm.C = 512.0;    
//        prm.gamma = 8.0; 
        //numbers=20000
//        prm.C = 32768.0;    
//        prm.gamma = 8.0; 
        //number=8000
        prm.C = C;    
       prm.gamma =gama; 
        prm.eps = eps;
        //处理不均衡数据集
        prm.nr_weight=2;
         prm.weight=weight;
        int [] weight_label={-1,1};
        prm.weight_label=weight_label;
        
        prm.probability = 0;  
        prm.cache_size=1024;  
        
        //初始化svm问题
      
      		
     //检查参数是否正确
        System.out.println("Param Check " + (svm.svm_check_parameter(sp, prm)==null)); 
        //训练分类
        
        svm_model  model = svm.svm_train(sp, prm);      
//          svm.//训练分类  
      
        System.out.println("分类结束");
       
   
//        
		return model;
		  
		
	}
    public void modelTrainFor(int numbers,int interval,int i)
    {
    	//初始化形成训练集
		models[i]=modelTrain(numbers,interval,i);
		
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
