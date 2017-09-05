package re;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import java.util.Vector;

import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;






public class Classification_plus2 {
	public static final String FILETRAINDATA="E:\\ChinaUnicomContest\\123.csv";
	public static final String FILETEST="E:\\ChinaUnicomContest\\testResult.csv";
	public static final String FILEPATHSHOPPROFILE="E:\\ChinaUnicomContest\\shop_good.csv";
	public static int [] classes22015={6,8};
	public static int[] classes12115={1,8};
	public static int[] classes21127={1,6};
	//设置svm模型采用的概率间隔 顺序为21127,12115,22015
	public static double[] proInterval={0.30,0.15,0.1};
	public static ArrayList<Train>  trainArr;
	public static ArrayList<Train>  basicTestArr;
	public static ArrayList<Train>  trainArrNew;
	public static TreeMap<Integer,Shop> shopMap;
	public static svm_model model22015;
	public static svm_model model12115;
	public static svm_model model21127;
	public static int number=0;
	public static void main(String[] args) throws IOException{
//		 model22015=new ClassifySVM().modelTrain(2048.0, 0.5, "E:/ChinaUnicomContest/22015_svm.csv", classes22015, 104,22015,0.05);
//		model12115=new ClassifySVM().modelTrain(0.5, 2.0, "E:/ChinaUnicomContest/12115_svm.csv", classes12115, 94,12115,0.005) ;
//		 model21127=new ClassifySVM().modelTrain(0.5, 8.0, "E:/ChinaUnicomContest/21127_svm.csv", classes21127, 376,21127,150);
		Object[] Obj1= readShop();                             
		ArrayList<Shop> shopArr=(ArrayList<Shop>)Obj1[0];
		//System.out.println(shopArr.size());
	    shopMap=(TreeMap<Integer,Shop>)Obj1[1];
//		System.out.println(trainArr.size()+"  "+basicTestArr.size());
		ArrayList<Train> testArr=readTestData();
		//将训练集中的数据分为两部分
		int K=2;
		Object[] obj=readTrainData(K,20);
		trainArr=(ArrayList<Train>)obj[0]; 
	    basicTestArr=(ArrayList<Train>)obj[1];
	    System.out.println("trainArr.size:"+trainArr.size()+"basicTestArr.size  "+basicTestArr.size());
		
		trainArrNew=readTrainData();
		double d=0.0005;
//		testModel(trainArrNew,trainArrNew,d);
//		writeData(trainArrNew,trainArrNew,d);
		writeDataAll(d,10,2);
		System.out.println("number   :"+number);
//		int count12115=0;
//		int predict12115=0;
//		int count21127=0;
//		int predict21127=0;
//		int count22015=0;
//		int predict22015=0;
//		double rmax=0;
//		double premax=0;
//		   for(double r=0.011;r<0.1;r+=0.002)
//		   {
//			   model12115=new ClassifySVM().modelTrain(0.5, 2.0, "E:/ChinaUnicomContest/12115_svm.csv", classes12115, 94,12115,r) ;
//			   model21127=new ClassifySVM().modelTrain(0.5, 8.0, "E:/ChinaUnicomContest/21127_svm.csv", classes21127, 376,21127,r);
//			for(Train tr:trainArrNew)
//			{
//				if (tr.getIncome()==1&&tr.getEntermament()==2&&tr.getBaby()==1&&tr.getGender()==1&&tr.getShopping()==5)
//				{
//					int classfication=tr.getClassification();
//					Vector classCaled=getClassification(tr,classes12115,model12115,r,0.05);
//					if((boolean) classCaled.get(0))
//					{
//						predict12115++;
//						if((int)classCaled.get(1)==tr.getClassification())
//						{
//							count12115++;
//						}
//					}
						
//				}
				
//				else if (tr.getIncome()==2&&tr.getEntermament()==2&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==5)
//				{
//					int classfication=tr.getClassification();
//					Vector classCaled=getClassification(tr,classes22015,model22015,r,0.08);
//					if((boolean) classCaled.get(0))
//					{
//						predict22015++;
//						if((int)classCaled.get(1)==tr.getClassification())
//						{
//							count22015++;
//						}
//					}
//						
//				}
//				else if (tr.getIncome()==2&&tr.getEntermament()==1&&tr.getBaby()==1&&tr.getGender()==2&&tr.getShopping()==7)
//				{
//					int classfication=tr.getClassification();
//					Vector classCaled=getClassification(tr,classes21127,model21127,100,0.25);
//					if((boolean) classCaled.get(0))
//					{
//						predict21127++;
//						if((int)classCaled.get(1)==tr.getClassification())
//						{
//							count21127++;
//						}
//					}
//						
//				}
//			}
//			if(premax<(double)predict21127/(double) count21127)
//			{
//				premax=(double)predict21127/(double) count21127;
//				rmax=r;
//			}
//	System.out.println(12115+": "+count12115+"==="+predict12115+"  : "+r);
//	System.out.println(21127+": "+count21127+"==="+predict21127+"  : ");
//	System.out.println(22015+": "+count22015+"==="+predict22015+"  : "+r);
//	count12115=0;
//	 predict12115=0;
//	 count21127=0;
//	 predict21127=0;
//	 count22015=0;
//	 predict22015=0;
//		   }
//		   System.out.println("premax"+"    :"+premax+"   rmax   :"+rmax);
			
//		TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim= user_trace_Sim(trainArr, basicTestArr,d);
//		int num=0;
//		for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map1:user_trace_Sim.entrySet()){
//			Train trs=map1.getKey();
//			if(map1.getValue().size()==0){
//				map1.getKey().setNewShop_id("");
//			}
//			else
//				map1.getKey().setNewShop_id(map1.getValue().get(0).getKey().getShop_id());
//		}
//		fillValue(user_trace_Sim,shopMap);
//		totalCode(user_trace_Sim, shopMap);
//		//test(user_trace_Sim, shopArr);
//		int count=0;
//		for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map1:user_trace_Sim.entrySet()){
//			Train trs=map1.getKey();
//			ArrayList<MyMap<Train,Double>> arr=map1.getValue();
//			if(trs.getNewShop_id()==null||trs.getNewShop_id().equals("")){
//				num+=1;
//			}
//			if(trs.getShop_id().equals(trs.getNewShop_id()))
//				count+=1;
//		}
//		System.out.println(num+"==="+count);
//		
//		//新数据
//		
		
//      TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Simnew= user_trace_Sim(trainArrNew, testArr,d);
//		fillValue(user_trace_Simnew,shopMap);		
//		totalCode(user_trace_Simnew, shopMap);
//		writerCSV( user_trace_Simnew) ;
//		System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+number);
	}
	static class Vector_info
	{
		public String shopid;
		public double distance;
		public int label;
	 Vector_info(String shopid,double distance,int label)
		{
			this.shopid=shopid;
			this.distance=distance;
			this.label=label;
		}
	Vector_info(String shop_id, Double value) {
		// TODO Auto-generated constructor stub
		this.shopid=shopid;
		this.distance=distance;
	}
	}
	 public static Vector fillValueWithDen(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> train_test)
	    {

	    	LinkedList<Vector_info> ides=new LinkedList<Vector_info>();
			ArrayList<String> shopides=new ArrayList<String>();
			ArrayList<MyMap<Train, Double>> traes=train_test.getValue();
			Train tra=train_test.getKey();
			if(tra.getIncome()==2&&tra.getEntermament()==6&&tra.getBaby()==0&&tra.getGender()==1&&tra.getShopping()==5){
				//如果判断正确
					for(MyMap<Train,Double> tr_double:traes)
					{
						Train train=tr_double.getKey();
						//如果为正确推荐商户
						if(!(shopides.contains(train.getShop_id())))
						{
							
							ides.addLast(new Vector_info(train.getShop_id(),tr_double.getValue()));
						    shopides.add(train.getShop_id());
						}
						
					}
			}
			Vector vec=new Vector();
			vec.add(0, false);
			if(!ides.isEmpty())
			{
				vec.set(0, false);
				vec.add(1,ides.get(0).shopid);
			}
			
			if(ides.size()>=2)
			{
				boolean condition1=(ides.get(1).distance-ides.get(0).distance)>100 && (ides.get(1).distance/ides.get(0).distance)>2;
				if(ides.get(0).distance<50 || ides.get(0).distance>1000 || condition1)
				{
					return  vec ;
				}
				else
				{
					vec.set(0, true);
					vec.set(1, ides.get(1).shopid);
					return vec;			
				}
			}
			return vec;
	    }
	/*
	 * d为用户轨迹参数
	 * count:交叉验证次数
	 * k:训练集分类比例，测试集的大小为1/k
	 * */
	 public static void writeDataAll(double d,int count,int k) throws NumberFormatException, IOException
	{	
		
		FileWriter fw=new FileWriter("E:/ChinaUnicomContest/33019_aLLData_2.csv");
		fw.write("id,lon,latti,first_id,distance1,label1,second_id,distance2,label2,param2,mindistance,diff_dis1,dis_pro1\n");
		LinkedList<Vector_info> ides=new LinkedList<Vector_info>();
		ArrayList<String> shopides=new ArrayList<String>();
		for(int j=1;j<count;j++){
			int seed=10+10*j;
			Object[] obj=readTrainData(k,seed);
			ArrayList<Train> traines=(ArrayList<Train>)obj[0];
			ArrayList<Train> test=(ArrayList<Train>)obj[1];
			
			TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_sim=user_trace_Sim(traines,test,d);
			fillValue(user_sim,shopMap);
			for(Entry<Train, ArrayList<MyMap<Train, Double>>> entry:user_sim.entrySet())
			{
				shopides.clear();
				ides.clear();
				ArrayList<MyMap<Train, Double>> traes=entry.getValue();
				Train tra=entry.getKey();
				if(tra.getIncome()==3&&tra.getEntermament()==3&&tra.getBaby()==0&&tra.getGender()==1&&tra.getShopping()==9){
					//如果判断正确
					if(tra.getShop_id().equals(tra.getNewShop_id())){
						for(MyMap<Train,Double> tr_double:traes)
						{
							Train train=tr_double.getKey();
							//如果为正确推荐商户
							if(tra.getShop_id().equals(train.getShop_id()) &&!(shopides.contains(train.getShop_id())) && train.getClassification()==6)
							{
								
								ides.addLast(new Vector_info(train.getShop_id(),tr_double.getValue(),1));
							    shopides.add(train.getShop_id());
							}
							else if(!(shopides.contains(train.getShop_id())) && train.getClassification()==6)
							{
								 ides.addLast(new Vector_info(train.getShop_id(),tr_double.getValue(),2));
							    shopides.add(train.getShop_id());
							}
						}
					}
						else
						{
							for(MyMap<Train,Double> tr_double:traes)
							{
								Train train=tr_double.getKey();
								//应该推荐商户
								if(tra.getShop_id().equals(train.getShop_id()) &&!(shopides.contains(train.getShop_id())) && train.getClassification()==6)
								{
									 ides.addLast(new Vector_info(train.getShop_id(),tr_double.getValue(),1));
								    shopides.add(train.getShop_id());
								}
								else if(!(shopides.contains(train.getShop_id())) && train.getClassification()==6 )
								{
									//错误推荐商户
									if(tra.getNewShop_id().equals(train.getShop_id()))
									{
										 ides.addLast(new Vector_info(train.getShop_id(),tr_double.getValue(),3));
									    shopides.add(train.getShop_id());
									}
									else
									{
										 ides.addLast(new Vector_info(train.getShop_id(),tr_double.getValue(),2));
									    shopides.add(train.getShop_id());
									}
								
								}
							}
							
						}
					
				
				}
				if(ides.size()>2)
				{
					fw.write(tra.getUser_id()+","+tra.getUserLongitude()+","+tra.getUserLatitude()
					       +","+ides.get(0).shopid+","+ides.get(0).distance+","+ides.get(0).label
					       +","+ides.get(1).shopid+","+ides.get(1).distance+","+ides.get(1).label+","+calParam(ides.get(0).distance,ides.get(1).distance)
	               +","+ides.get(0).distance+","+((double)ides.get(1).distance-(double)ides.get(0).distance)+","+((double)ides.get(1).distance/(double)ides.get(0).distance)+"\n");
				}
		
			}
		}
		fw.flush();
		fw.close();
	}
	 public static double calParam(double distance1,double distance)
	 {
	         return	 Math.pow(1+Math.sqrt((distance-distance1)/3000), ((distance/distance1)/3000)*(distance-distance1));
		 
	 }
	public static void writeData(ArrayList<Train> traines,ArrayList<Train> test,double d) throws NumberFormatException, IOException
	{
		TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_sim=user_trace_Sim(traines,test,d);
		
		int count21127=0;
		int count12115=0;
		int count22015=0;
		for(Entry<Train, ArrayList<MyMap<Train, Double>>> entry:user_sim.entrySet())
		{
			Train tra=entry.getKey();
			String shopid=tra.getShop_id();
			int userid=tra.getUser_id();
			if(tra.getIncome()==2&&tra.getEntermament()==1&&tra.getBaby()==1&&tra.getGender()==2&&tra.getShopping()==7){
				for(MyMap<Train,Double> entry2:entry.getValue())
				{
					if(entry2.getKey().getUser_id()==userid)
					{
						continue;
					}
					if(entry2.getKey().getShop_id().equals(shopid))
					{
						count21127++;
						break;
					}
				}
			}
			else if(tra.getIncome()==1&&tra.getEntermament()==2&&tra.getBaby()==1&&tra.getGender()==1&&tra.getShopping()==5){
				for(MyMap<Train,Double> entry2:entry.getValue())
				{
					if(entry2.getKey().getUser_id()==userid)
					{
						continue;
					}
					if(entry2.getKey().getShop_id().equals(shopid))
					{
						count12115++;
						break;
					}
				}
			}
			else if(tra.getIncome()==2&&tra.getEntermament()==2&&tra.getBaby()==0&&tra.getGender()==1&&tra.getShopping()==5){
				for(MyMap<Train,Double> entry2:entry.getValue())
				{
					if(entry2.getKey().getUser_id()==userid)
					{
						continue;
					}
					if(entry2.getKey().getShop_id().equals(shopid))
					{
						count22015++;
						break;
					}
				}	
			}
		}
		System.out.println("\n正确数21127  "+count21127  	+"\n12115  "+count12115+"\n22015  "+count22015);
		fillValue(user_sim,shopMap);
		FileWriter fw1=new FileWriter("E:/ChinaUnicomContest/26015_correct.csv");
		fw1.write("id ,lon,lati,label,distance \n");
		FileWriter fw2=new FileWriter("E:/ChinaUnicomContest/26015_error.csv");
		fw2.write("id ,lon,lati,label,distance \n");
		FileWriter fw3=new FileWriter("E:/ChinaUnicomContest/26015_all.csv");
		fw3.write("id ,lon,lati,label ,distance \n");
		ArrayList<String> shopides=new ArrayList<String>();
		for(Entry<Train, ArrayList<MyMap<Train, Double>>> entry:user_sim.entrySet())
		{
			shopides.clear();
			ArrayList<MyMap<Train, Double>> traes=entry.getValue();
			Train tra=entry.getKey();
		if(tra.getIncome()==2&&tra.getEntermament()==6&&tra.getBaby()==0&&tra.getGender()==1&&tra.getShopping()==5){
				//如果判断正确
				if(tra.getShop_id().equals(tra.getNewShop_id()))
				{
					//写入用户数据
					fw1.write(tra.getUser_id()+","+tra.getUserLongitude()+","+tra.getUserLatitude()+","+0+","+0+"\n");
					fw3.write(tra.getUser_id()+","+tra.getUserLongitude()+","+tra.getUserLatitude()+","+0+","+0+"\n");
					//写入用户轨迹对应商户数据
					for(MyMap<Train,Double> tr_double:traes)
					{
						Train train=tr_double.getKey();
						//如果为正确推荐商户
						if(tra.getShop_id().equals(train.getShop_id()) &&!(shopides.contains(train.getShop_id())))
						{
							fw1.write(train.getShop_id()+","+train.getShopLongitude()+","+train.getShopLatitude()+","+1+","+tr_double.getValue()+"\n");
						    fw3.write(train.getShop_id()+","+train.getShopLongitude()+","+train.getShopLatitude()+","+1+","+tr_double.getValue()+"\n");
						    shopides.add(train.getShop_id());
						}
						else if(!(shopides.contains(train.getShop_id())))
						{
							fw1.write(train.getShop_id()+","+train.getShopLongitude()+","+train.getShopLatitude()+","+2+","+tr_double.getValue()+"\n");
						    fw3.write(train.getShop_id()+","+train.getShopLongitude()+","+train.getShopLatitude()+","+2+","+tr_double.getValue()+"\n");
						    shopides.add(train.getShop_id());
						}
					}
				}
				//如果没有正确推荐商户
				else
				{
					//写入用户数据
					fw2.write(tra.getUser_id()+","+tra.getUserLongitude()+","+tra.getUserLatitude()+","+0+","+0+"\n");
					fw3.write(tra.getUser_id()+","+tra.getUserLongitude()+","+tra.getUserLatitude()+","+0+","+0+"\n");
					for(MyMap<Train,Double> tr_double:traes)
					{
						Train train=tr_double.getKey();
						//如果为正确推荐商户
						if(tra.getShop_id().equals(train.getShop_id())&&!(shopides.contains(train.getShop_id())))
						{
							fw2.write(train.getShop_id()+","+train.getShopLongitude()+","+train.getShopLatitude()+","+1+","+tr_double.getValue()+"\n");
						    fw3.write(train.getShop_id()+","+train.getShopLongitude()+","+train.getShopLatitude()+","+1+","+tr_double.getValue()+"\n");
						    shopides.add(train.getShop_id());
						}
						//如果不是应该正确推荐商户
						else if(!(shopides.contains(train.getShop_id())))
						{
							//算法错误推荐的商户
							if(tra.getNewShop_id().equals(train.getShop_id())){
							fw2.write(train.getShop_id()+","+train.getShopLongitude()+","+train.getShopLatitude()+","+3+","+tr_double.getValue()+"\n");
						    fw3.write(train.getShop_id()+","+train.getShopLongitude()+","+train.getShopLatitude()+","+3+","+tr_double.getValue()+"\n");
						    shopides.add(train.getShop_id());
							}
							//错误商户
							else
							{
								fw2.write(train.getShop_id()+","+train.getShopLongitude()+","+train.getShopLatitude()+","+2+","+tr_double.getValue()+"\n");
							    fw3.write(train.getShop_id()+","+train.getShopLongitude()+","+train.getShopLatitude()+","+2+","+tr_double.getValue()+"\n");
							    shopides.add(train.getShop_id());
							}
						}
					}
				}
				
			}
		}
		fw1.flush();fw2.flush();fw3.flush();
		fw1.close();fw2.close();fw3.close();
		
		
		
	}
	//测试函数，用于测试加入svm判别类别之后，正确率是否有所提高
	public static ArrayList<Train> testModel (ArrayList<Train> traines,ArrayList<Train> test,double d) throws IOException
	{
		
		int correct21127=0;
		int count21127=0;
		int correct22015=0;
		int count22015=0;
		int correct12115=0;
		int count12115=0;
		TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_sim=user_trace_Sim(traines,test,d);
		
//		for(Train tra:user_sim.keySet())
//		{
//			if(tra.getIncome()==2&&tra.getEntermament()==1&&tra.getBaby()==1&&tra.getGender()==2&&tra.getShopping()==7)
//			{
//				Vector vec=getClassification(tra,classes21127,model21127,80,proInterval[0]);
//				if((boolean) vec.get(0))
//				{
//					count21127++;
//					if((int)vec.get(1)==tra.getClassification())
//					{
//						correct21127++;
//					}
//				}
//				
//			}
//			else if(tra.getIncome()==1&&tra.getEntermament()==2&&tra.getBaby()==1&&tra.getGender()==1&&tra.getShopping()==5)
//			{
//				Vector vec=getClassification(tra,classes12115,model12115,100,proInterval[0]);
//				if((boolean) vec.get(0))
//				{
//					count12115++;
//					if((int)vec.get(1)==tra.getClassification())
//					{
//						correct12115++;
//					}
//				}
//				
//			}
//		}
//		System.out.println("\n类别"+21127+"  :  "+correct21127+" ==  "+ count21127+" 类别准确率 ："+(double)correct21127/(double)count21127);
//		System.out.println("\n类别"+12115+"  :  "+correct12115+" ==  "+ count12115+" 类别准确率 ："+(double)correct12115/(double)count12115);
//		System.out.println("\n类别"+22015+"  :  "+correct22015+" ==  "+ count22015+" 类别准确率 ："+(double)correct22015/(double)count22015);
		 correct21127=0;
		 count21127=0;
		 correct22015=0;
		 count22015=0;
		 correct12115=0;
		 count12115=0;
		fillValue(user_sim,shopMap);		
		totalCode(user_sim, shopMap);
		ArrayList<Train> alt=new ArrayList<Train>();
		
		for(Train tra:user_sim.keySet())
		{
			
		if(tra.getIncome()==2&&tra.getEntermament()==1&&tra.getBaby()==1&&tra.getGender()==2&&tra.getShopping()==7){
			    count21127++;
				if(tra.getShop_id()==tra.getNewShop_id()){
					correct21127++;
				}
				else
				{
					alt.add(tra);
				}
			}
		else if(tra.getIncome()==2&&tra.getEntermament()==2&&tra.getBaby()==0&&tra.getGender()==1&&tra.getShopping()==5){
		    count22015++;
			if(tra.getShop_id()==tra.getNewShop_id()){
				correct22015++;
			}
			else
			{
				alt.add(tra);
			}
		}
		else if(tra.getIncome()==1&&tra.getEntermament()==2&&tra.getBaby()==1&&tra.getGender()==1&&tra.getShopping()==5){
		    count12115++;
			if(tra.getShop_id()==tra.getNewShop_id()){
				correct12115++;
			}
			else
			{
				alt.add(tra);
			}
		}
		
		}
		System.out.println("\n"+21127+"  :  "+correct21127+" ==  "+ count21127+" 准确率 ："+(double)correct21127/(double)count21127);
		System.out.println("\n"+12115+"  :  "+correct12115+" ==  "+ count12115+" 准确率 ："+(double)correct12115/(double)count12115);
		System.out.println("\n"+22015+"  :  "+correct22015+" ==  "+ count22015+" 准确率 ："+(double)correct22015/(double)count22015);
		return alt;
		
		
		
	}
	public static Vector calDensityOrder(Vector pos_user,int userid,int classfication1,int classfication2,double r)
	{
		TreeMap<Integer,Integer> map=new TreeMap<Integer,Integer>();
		List<java.util.Map.Entry<Integer, Double>> neibors=new FilterShop().findNeibors((double)pos_user.get(0),(double)pos_user.get(1),r);
		 
		for(java.util.Map.Entry<Integer, Double> neibor:neibors)
		{
//			System.out.println(neibor.getKey()+":::::"+neibor.getValue());
			int shopid_=neibor.getKey();
			String classSql="select class from shop_profile where id= "+shopid_;
			ResultSet classfi;
			try {
				classfi = new FilterShop().conn.createStatement().executeQuery(classSql);
				classfi.next();
				int class_shop=classfi.getInt(1);
				
				if(!map.keySet().contains(new Integer(class_shop)))
				{
					map.put(class_shop, 1);
				}
				else 
				{
					map.put(class_shop, map.get(class_shop)+1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		List<Map.Entry<Integer, Integer>> list=new ArrayList<Map.Entry<Integer,Integer>>(map.entrySet());
		int size=list.size();
//		System.out.println("该用户"+userid+"邻居商户类别数目"+size);
		Collections.sort(list,new CompareWithCount());
		int order=0;
		Vector vec=new Vector();
		vec.add(0, 0);
		vec.add(1, 0);
		vec.add(2, 0);
		vec.add(3, 0);
		for(Map.Entry<Integer, Integer> entry:list)
		{
			order++;
			if(entry.getKey()==classfication1)
			{
//				System.out.println("类别"+entry.getKey()+"的数目为："+entry.getValue());
				vec.set(0, entry.getValue());
				vec.set(1,order);
				
			}
			if(entry.getKey()==classfication2)
			{
//				System.out.println("类别"+entry.getKey()+"的数目为："+entry.getValue());
				vec.set(2, entry.getValue());
				vec.set(3,order);
			}
		}
		return vec;
	}
	 public static Vector getClassification(Train test,int[] classes,svm_model model,int neiborNumber,double interval) throws NumberFormatException, IOException
	    {
		
		 Vector pos_user=new Vector();
			pos_user.add(0,test.getUserLongitude());
			pos_user.add(1,test.getUserLatitude());
			
			int userid=test.getUser_id();
	    	Vector vec=calDensityOrder(pos_user, userid, classes[0], classes[1],neiborNumber);
	    	int numberOffirst=(int) vec.get(0);
			int orderOffirst=(int) vec.get(1);
			int numberOfSec=(int) vec.get(2);
			int orderOfSec=(int) vec.get(3);
			double diffNum=(double)numberOffirst-(double)numberOfSec;
			double diffOrder=(double)orderOffirst-(double)orderOfSec;
			double [] para=new double[6];
			    para[0]=log(1+(double)numberOffirst,100);
			    para[2]=log(1+(double)numberOfSec,100);
				para[1]=(double)orderOffirst;
				para[3]=(double)orderOfSec;
				para[5]=(double)diffOrder;
				para[4]=(double)diffNum/100;
			svm_node[] testdata=new svm_node[6];
			for(int j=0;j<6;j++)
			{
				testdata[j]=new svm_node();
				testdata[j].index=j+1;	
				testdata[j].value=para[j];
			}
		 double[] pro=new double[2];
		 int label=(int) svm.svm_predict_probability(model,testdata,pro);
		 Vector vec2=new Vector();
		 vec2.add(0, false);
		 vec2.add(1,0);
		 if(pro[0]-pro[1]>interval)
		 {
//			 System.out.println("label"+label+"  pro:"+pro[0]+","+pro[1]);
			 vec2.set(0, true);
			 vec2.set(1, classes[0]);
//			 System.out.println("label"+label+"  pro:"+pro[0]+","+pro[1]+"class  :"+vec2.get(1));
		 }
			 
		 else if(pro[1]-pro[0]>interval)
		 {
//			 System.out.println("label"+label+"  pro:"+pro[0]+","+pro[1]);
			 vec2.set(0, true);
			 vec2.set(1, classes[1]);
//			 System.out.println("label"+label+"  pro:"+pro[0]+","+pro[1]+"class  :"+vec2.get(1));
		 }
		 
		 return vec2;
			
			
	    	
	    	
	    }
	 public static Vector getClassification(Train test,int[] classes,svm_model model,double r,double interval) throws NumberFormatException, IOException
	    {
		
		 Vector pos_user=new Vector();
			pos_user.add(0,test.getUserLongitude());
			pos_user.add(1,test.getUserLatitude());
			
			int userid=test.getUser_id();
	    	Vector vec=calDensityOrder(pos_user, userid, classes[0], classes[1],r);
	    	int numberOffirst=(int) vec.get(0);
			int orderOffirst=(int) vec.get(1);
			int numberOfSec=(int) vec.get(2);
			int orderOfSec=(int) vec.get(3);
			double diffNum=(double)numberOffirst-(double)numberOfSec;
			double diffOrder=(double)orderOffirst-(double)orderOfSec;
			double [] para=new double[6];
			    para[0]=log(1+(double)numberOffirst,100);
			    para[2]=log(1+(double)numberOfSec,100);
				para[1]=(double)orderOffirst;
				para[3]=(double)orderOfSec;
				para[5]=(double)diffOrder;
				para[4]=(double)diffNum/100;
			svm_node[] testdata=new svm_node[6];
			for(int j=0;j<6;j++)
			{
				testdata[j]=new svm_node();
				testdata[j].index=j+1;	
				testdata[j].value=para[j];
			}
		 double[] pro=new double[2];
		 int label=(int) svm.svm_predict_probability(model,testdata,pro);
		 Vector vec2=new Vector();
		 vec2.add(0, false);
		 vec2.add(1,0);
		 System.out.println("label"+label+"  pro:"+pro[0]+","+pro[1]+"class  :"+vec2.get(1));
		 if(pro[0]-pro[1]>interval)
		 {
//			 System.out.println("label"+label+"  pro:"+pro[0]+","+pro[1]);
			 vec2.set(0, true);
			 vec2.set(1, classes[0]);
			 System.out.println("label"+label+"  pro:"+pro[0]+","+pro[1]+"class  :"+vec2.get(1));
		 }
			 
		 else if(pro[1]-pro[0]>interval)
		 {
//			 System.out.println("label"+label+"  pro:"+pro[0]+","+pro[1]);
			 vec2.set(0, true);
			 vec2.set(1, classes[1]);
			 System.out.println("label"+label+"  pro:"+pro[0]+","+pro[1]+"class  :"+vec2.get(1));
		 }
		 
		 return vec2;
			
			
	    	
	    	
	    }
	 public static double log(double value, double base) {

	    	return Math.log(value) / Math.log(base);

	    }
	 public static Vector calDensityOrder(Vector pos_user,int userid,int classfication1,int classfication2,int neiborNumber)
		{
			
			TreeMap<Integer,Integer> map=new TreeMap<Integer,Integer>();
			List<java.util.Map.Entry<Integer, Double>> neibors=new FilterShop().findNeibors((double)pos_user.get(0),(double)pos_user.get(1),neiborNumber);
			 
			for(java.util.Map.Entry<Integer, Double> neibor:neibors)
			{
//				System.out.println(neibor.getKey()+":::::"+neibor.getValue());
				int shopid_=neibor.getKey();
				String classSql="select class from shop_profile where id= "+shopid_;
				ResultSet classfi;
				try {
					classfi = new FilterShop().conn.createStatement().executeQuery(classSql);
					classfi.next();
					int class_shop=classfi.getInt(1);
					
					if(!map.keySet().contains(new Integer(class_shop)))
					{
						map.put(class_shop, 1);
					}
					else 
					{
						map.put(class_shop, map.get(class_shop)+1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			List<Map.Entry<Integer, Integer>> list=new ArrayList<Map.Entry<Integer,Integer>>(map.entrySet());
			int size=list.size();
//			System.out.println("该用户"+userid+"邻居商户类别数目"+size);
			Collections.sort(list,new CompareWithCount());
			int order=0;
			Vector vec=new Vector();
			vec.add(0, 0);
			vec.add(1, 0);
			vec.add(2, 0);
			vec.add(3, 0);
			for(Map.Entry<Integer, Integer> entry:list)
			{
				order++;
				if(entry.getKey()==classfication1)
				{
//					System.out.println("类别"+entry.getKey()+"的数目为："+entry.getValue());
					vec.set(0, entry.getValue());
					vec.set(1,order);
					
				}
				if(entry.getKey()==classfication2)
				{
//					System.out.println("类别"+entry.getKey()+"的数目为："+entry.getValue());
					vec.set(2, entry.getValue());
					vec.set(3,order);
				}
			}
			return vec;
			
		}
	//函数功能：进行609个店铺的分类,热度进行统计
	public static TreeMap<String,Integer> shopClassification()throws IOException{
		File f=new File(FILETRAINDATA);
		FileInputStream fis=new FileInputStream(f);
		InputStreamReader isr=new InputStreamReader(fis);
		BufferedReader bufr=new BufferedReader(isr);
		String temp=bufr.readLine();
		ArrayList<Train> trainArr=new ArrayList<Train>();
		while((temp=bufr.readLine())!=null){
			String[] str=temp.split(",");
			int user_id=Integer.parseInt(str[0]);
			int income=Integer.parseInt(str[1]);
			int entermament=Integer.parseInt(str[2]);
			int baby=Integer.parseInt(str[3]);
			int gender=Integer.parseInt(str[4]);
			int shopping=Integer.parseInt(str[5]);
			int shop_id=Integer.parseInt(str[6]);
			int classification=Integer.parseInt(str[7]);
			double userLongitude=Double.parseDouble(str[8]);
			double userLatitude=Double.parseDouble(str[9]);
			double shopLongitude=Double.parseDouble(str[10]);
			double shopLatitude=Double.parseDouble(str[11]);
			String arrival_time=str[12];
			int duration=Integer.parseInt(str[13]);
			Train tr=new Train();
			tr.setArrival_time(arrival_time);
			tr.setBaby(baby);
			tr.setClassification(classification);
			tr.setDuration(duration);
			tr.setEntermament(entermament);
			tr.setGender(gender);
			tr.setIncome(income);
			tr.setShop_id(shop_id+"");
			tr.setShopLatitude(shopLatitude);
			tr.setShopLongitude(shopLongitude);
			tr.setShopping(shopping);
			tr.setUser_id(user_id);
			tr.setUserLongitude(userLongitude);
			tr.setUserLatitude(userLatitude);
			trainArr.add(tr);
		}
		bufr.close();
		isr.close();
		fis.close();
		TreeMap<String,Integer> tm=new TreeMap<String,Integer>();
		for(int i=0;i<trainArr.size();i++){
			Train tr=trainArr.get(i);
			String shop_id=tr.getShop_id();
			if(tm.get(shop_id)==null){
				tm.put(shop_id,0);
			}
			tm.put(shop_id,tm.get(shop_id)+1);
		}
		return tm;
	}
	//函数功能：按照609个店铺进行空值填充
	public static void totalCode(TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Simnew,TreeMap<Integer,Shop> shopMap) throws IOException{
		TreeMap<String,Integer> tm=shopClassification();
		fillLastNull(user_trace_Simnew, tm, shopMap);
	}
	//函数功能：进行最后的空值填充
	public static void fillLastNull(TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Simnew,TreeMap<String,Integer> tm,TreeMap<Integer,Shop> shopMap){
		for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map1:user_trace_Simnew.entrySet()){
			Train tr=map1.getKey();
			if(map1.getKey().getNewShop_id()==null||map1.getKey().getNewShop_id().equals("")){
				ArrayList<MyMap<Shop,Double>> arr=new ArrayList<MyMap<Shop,Double>>();
				for(Map.Entry<String, Integer> map2:tm.entrySet()){
					String shop_id=map2.getKey();
					Shop shop=shopMap.get(Integer.parseInt(shop_id));
					double d=calDistance2(tr,shop);
					MyMap<Shop,Double> mm=new MyMap<Shop,Double>(shop,d);
					arr.add(mm);
				}
				Collections.sort(arr,new MyComparators());
				if(tr.getIncome()==3&&tr.getEntermament()==3&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==9){//33019
					for(int i=0;i<arr.size();i++){
						if(arr.get(i).getKey().getClassification()==6){
							map1.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
							break;
						}
					}
				}
				else if(tr.getIncome()==2&&tr.getEntermament()==1&&tr.getBaby()==1&&tr.getGender()==2&&tr.getShopping()==2){//21122
					for(int i=0;i<arr.size();i++){
						if(arr.get(i).getKey().getClassification()==1){
							map1.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
							break;
						}
					}
				}
				else if(tr.getIncome()==2&&tr.getEntermament()==1&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==5){//21015
					for(int i=0;i<arr.size();i++){
						if(arr.get(i).getKey().getClassification()==8){
							map1.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
							break;
						}
					}
				}
				else if(tr.getIncome()==2&&tr.getEntermament()==1&&tr.getBaby()==1&&tr.getGender()==2&&tr.getShopping()==7){//21127
					for(int i=0;i<arr.size();i++){
						if(arr.get(i).getKey().getClassification()==1||arr.get(i).getKey().getClassification()==6){
							map1.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
							break;
						}
					}
				}
				else if(tr.getIncome()==2&&tr.getEntermament()==2&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==5){//22015
					for(int i=0;i<arr.size();i++){
						if(arr.get(i).getKey().getClassification()==6||arr.get(i).getKey().getClassification()==8){
							map1.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
							break;
						}
					}
				}
				else if(tr.getIncome()==1&&tr.getEntermament()==2&&tr.getBaby()==1&&tr.getGender()==1&&tr.getShopping()==5){//12115
					for(int i=0;i<arr.size();i++){
						if(arr.get(i).getKey().getClassification()==1||arr.get(i).getKey().getClassification()==8){
							map1.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
							break;
						}
					}
				}
				else if(tr.getIncome()==1&&tr.getEntermament()==1&&tr.getBaby()==1&&tr.getGender()==2&&tr.getShopping()==1){//11121
					for(int i=0;i<arr.size();i++){
						if(arr.get(i).getKey().getClassification()==3){
							map1.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
							break;
						}
					}
				}
				else if(tr.getIncome()==2&&tr.getEntermament()==6&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==5){//26015
					for(int i=0;i<arr.size();i++){
						if(arr.get(i).getKey().getClassification()==1||arr.get(i).getKey().getClassification()==8||arr.get(i).getKey().getClassification()==6){
							map1.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
							break;
						}
					}
				}
				else if(tr.getIncome()==2&&tr.getEntermament()==7&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==5){//27015
					for(int i=0;i<arr.size();i++){
						if(arr.get(i).getKey().getClassification()==5){
							map1.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
							break;
						}
					}
				}
				else{
					map1.getKey().setNewShop_id(arr.get(0).getKey().getShop_id()+"");
				}
				
				
			}
		}
	}
	//函数功能：如果之前出现过，那么直接复制，这个方法不行
		public static void copy(TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Simnew){
			for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map1:user_trace_Simnew.entrySet()){
				Train tr=map1.getKey();
				int user_id=tr.getUser_id();
				if(tr.getNewShop_id()==null||tr.getNewShop_id().equals("")){
					int min=Integer.MAX_VALUE;
					for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map2:user_trace_Simnew.entrySet()){
						if(map2.getKey().getUser_id()==user_id&&map2.getKey().getNewShop_id()!=null&&!map2.getKey().getNewShop_id().equals("")){
							String str1=tr.getArrival_time();
							String str2=tr.getArrival_time();
							int time1=Integer.parseInt(str1.split("2014")[1]);
							int time2=Integer.parseInt(str2.split("2014")[1]);
							int timeDif=Math.abs(time1-time2);
							if(timeDif<min){
								min=timeDif;
								map1.getKey().setNewShop_id(map2.getKey().getNewShop_id());
							}
						}
					}
				}
			}
		}

	//函数功能：shop_id赋值
	public static void shopIDValue(TreeMap<Train,ArrayList<MyMap<Shop,Double>>> tm){
		int num=0;
		for(Map.Entry<Train,ArrayList<MyMap<Shop,Double>>> map:tm.entrySet()){
			Train tr=map.getKey();
			ArrayList<MyMap<Shop,Double>> arr=map.getValue();
			if(tr.getIncome()==3&&tr.getEntermament()==3&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==9){//33019===6
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==6){
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
						break;
					}
				}
			}
			else if(tr.getIncome()==2&&tr.getEntermament()==1&&tr.getBaby()==1&&tr.getGender()==2&&tr.getShopping()==2){//21122
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==1){
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
						break;
					}
				}

			}
			else if(tr.getIncome()==2&&tr.getEntermament()==1&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==5){//21015
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==8){
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
						break;
					}
				}
			}
			else if(tr.getIncome()==2&&tr.getEntermament()==1&&tr.getBaby()==1&&tr.getGender()==2&&tr.getShopping()==7){//21127
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==1||arr.get(i).getKey().getClassification()==6){
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
						break;
					}
				}
			}
			else if(tr.getIncome()==2&&tr.getEntermament()==2&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==5){//22015
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==6||arr.get(i).getKey().getClassification()==8){
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
						break;
					}
				}
//				for(int i=0;i<arr.size();i++){
//					if(arr.get(i).getKey().getClassification()==6&&map.getKey().getNewShop_id()==null){
//						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
//					}
//				}
			}
			else if(tr.getIncome()==1&&tr.getEntermament()==2&&tr.getBaby()==1&&tr.getGender()==1&&tr.getShopping()==5){//12115
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==1||arr.get(i).getKey().getClassification()==8){
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
						break;
					}
				}

			}
			else if(tr.getIncome()==1&&tr.getEntermament()==1&&tr.getBaby()==1&&tr.getGender()==2&&tr.getShopping()==1){//11121
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==3){
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
						break;
					}
				}
			}
			else if (tr.getIncome()==2&&tr.getEntermament()==6&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==5){//26015
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==1||arr.get(i).getKey().getClassification()==6||arr.get(i).getKey().getClassification()==8){
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
						break;
					}
				}
			}
			else if(tr.getIncome()==2&&tr.getEntermament()==7&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==5){//27015
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==5){
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
						break;
					}
				}
			}
			if(arr.size()==0){
//					System.out.println("***********");
					map.getKey().setNewShop_id("");
				}
//			if(map.getKey().getNewShop_id()==null||map.getKey().getNewShop_id().equals("")&&arr.size()!=0)
//			{
//				map.getKey().setNewShop_id(arr.get(0).getKey().getShop_id()+"");
//				}
		}
		
		for(Map.Entry<Train,ArrayList<MyMap<Shop,Double>>> map:tm.entrySet()){
			if(map.getKey().getNewShop_id()==null||map.getKey().getNewShop_id().equals(""))
				num+=1;
		}
//		System.out.println(num);
	}
	//函数功能：填空值总程序
	public static void test(TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim,ArrayList<Shop> shopArr){
		TreeMap<Train,ArrayList<MyMap<Shop,Double>>>tm= fillNull( user_trace_Sim, shopArr);
		shopIDValue( tm);
	}
	//函数功能：空值填充.找出最近的店铺

	public static TreeMap<Train,ArrayList<MyMap<Shop,Double>>> fillNull(TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim,ArrayList<Shop> shopArr){
		ArrayList<Train> notNullArr=new ArrayList<Train>();
		ArrayList<Train>  nullArr=new ArrayList<Train>();
		for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map:user_trace_Sim.entrySet()){
			if(map.getKey().getNewShop_id()==null||map.getKey().getNewShop_id().equals("")){
				nullArr.add(map.getKey());
			}
			else
				notNullArr.add(map.getKey());
		}
//		System.out.println(nullArr.size()+"-----");
		TreeMap<Train,ArrayList<MyMap<Shop,Double>>> tm=new TreeMap<Train,ArrayList<MyMap<Shop,Double>>>();
		for(int i=0;i<nullArr.size();i++){
			ArrayList<MyMap<Shop,Double>> arr=getDisArr(nullArr.get(i), shopArr);
			tm.put(nullArr.get(i), arr);
		}
		return tm;
	}
	public static Vector getSimShop(int userid,double lon,double lati)
	{
		Vector vec=new Vector();
		vec.add(0,false);
		vec.add(1, 0);
		for(Train train:trainArrNew)
		{
			if(train.getUser_id()==userid && (Math.abs(train.getUserLatitude()-lati))<0.0001 && (Math.abs(train.getUserLongitude()-lon))<0.0001)
			{
				vec.set(0, true);
				vec.set(1, train.getShop_id());
				break;
				
			}
				
		}
		return vec;
	}
	//求出满足距离的N个店铺
	public static ArrayList<MyMap<Shop,Double>> getDisArr(Train tr,ArrayList<Shop> shopArr){
		//System.out.println(shopArr.size()+"------------------------");
		ArrayList<MyMap<Shop,Double>> arr=new ArrayList<MyMap<Shop,Double>>();
		for(int i=0;i<shopArr.size();i++){
			double d=calDistance2(tr,shopArr.get(i));
			if(d<300){
				MyMap<Shop,Double> mm=new MyMap(shopArr.get(i),d);
				arr.add(mm);
			}
			
			
		}
		Collections.sort(arr,new MyComparators());
//		System.out.println("");
		for(int i=0;i<arr.size();i++){
			System.out.print(arr.get(i).getValue()+"---");
		}
		return arr;
	}
	//函数功能：计算距离
	public static double calDistance2(Train tr,Shop shop){
		  double lat1 = tr.getUserLatitude();
		  double lat2 = shop.getLatitude();  
	      double lon1 = tr.getUserLongitude();
	      double lon2 =shop.getLongitude();  
	      double latDif=Math.abs(lat1-lat2);
	      double lonDif=Math.abs(lon1-lon2);
	      double MeLat=108000*latDif;
	      double MeLon=108000*Math.cos(lat1)*lonDif;
	      double d=Math.sqrt(Math.pow(MeLat, 2)+Math.pow(MeLon, 2));
	      return d;  
	}
	public static void fillValue(TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim,TreeMap<Integer,Shop> shopMap) throws NumberFormatException, IOException{
		int num=0;
		for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map:user_trace_Sim.entrySet()){
			Train tr=map.getKey();
			ArrayList<MyMap<Train,Double>> arr=map.getValue();
			//如果该用户能够在原训练集中找到相似的则直接推荐
//			int userid =tr.getUser_id();
//			double user_lon=tr.getUserLongitude();
//			double user_lati=tr.getUserLatitude();
//			Vector vec= getSimShop(userid,user_lon,user_lati);
//			if((boolean) vec.get(0))
//			{
//				tr.setNewShop_id((String) vec.get(1));
//				continue;
//			}
			
			if(tr.getIncome()==2&&tr.getEntermament()==1&&tr.getBaby()==1&&tr.getGender()==2&&tr.getShopping()==2){//2,1,1,2,2
				TreeMap<String,Integer> tm=new TreeMap<String,Integer>();
				for(int i=0;i<arr.size();i++){
					//如果用户轨迹与被预测用户的id一致时，不做处理
//					if(arr.get(i).getKey().getUser_id()==tr.getUser_id())
//					{
//						continue;
//					}
					if((arr.get(i).getValue()-arr.get(0).getValue())/arr.get(0).getValue()<0.01){
						String shop_id=arr.get(i).getKey().getShop_id();
						if(tm.get(shop_id)==null){
							tm.put(shop_id, 0);
						}
						tm.put(shop_id,tm.get(shop_id)+1);
					}
				}
				int max=-1;
				String shop="";
				for(Map.Entry<String, Integer> maps:tm.entrySet()){
					if(maps.getValue()>max&&shopMap.get(Integer.parseInt(maps.getKey())).getClassification()==1){
						max=maps.getValue();
						shop=maps.getKey();
					}
				}
				map.getKey().setNewShop_id(shop);
//				for(int i=0;i<arr.size();i++){
//					if(arr.get(i).getKey().getClassification()==1){//判断类别
//						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id());
//						break;
//					}
//					else
//						continue;
//						
//				}
				if(map.getKey().getNewShop_id()==null&&arr.size()!=0){
					map.getKey().setNewShop_id(arr.get(0).getKey().getShop_id());
					num+=1;
				}
			}
			else if(tr.getIncome()==3&&tr.getEntermament()==3&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==9){//33019
				TreeMap<String,Integer> tm=new TreeMap<String,Integer>();
				for(int i=0;i<arr.size();i++){
					//如果用户轨迹与被预测用户的id一致时，不做处理
//					if(arr.get(i).getKey().getUser_id()==tr.getUser_id())
//					{
//						continue;
//					}
					if((arr.get(i).getValue()-arr.get(0).getValue())/arr.get(0).getValue()<0.01){
						String shop_id=arr.get(i).getKey().getShop_id();
						if(tm.get(shop_id)==null){
							tm.put(shop_id, 0);
						}
						tm.put(shop_id,tm.get(shop_id)+1);
					}
				}
				int max=-1;
				String shop="";
				for(Map.Entry<String, Integer> maps:tm.entrySet()){
					if(maps.getValue()>max&&shopMap.get(Integer.parseInt(maps.getKey())).getClassification()==6){
						max=maps.getValue();
						shop=maps.getKey();
					}
				}
				map.getKey().setNewShop_id(shop);
				if(map.getKey().getUser_id()==1554){
					System.out.println(map.getKey().getNewShop_id()+"--"+tm.size());
				}
//				for(int i=0;i<arr.size();i++){
//					if(arr.get(i).getKey().getClassification()==6){//判断类别
//						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id());
//						break;
//					}
//					else
//						continue;
//				}
				if(map.getKey().getNewShop_id()==null&&arr.size()!=0){
					map.getKey().setNewShop_id(arr.get(0).getKey().getShop_id());
					num+=1;
				}
			}
			else if (tr.getIncome()==2&&tr.getEntermament()==1&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==5){//21015
				TreeMap<String,Integer> tm=new TreeMap<String,Integer>();
				for(int i=0;i<arr.size();i++){
					//如果用户轨迹与被预测用户的id一致时，不做处理
//					if(arr.get(i).getKey().getUser_id()==tr.getUser_id())
//					{
//						continue;
//					}
					if((arr.get(i).getValue()-arr.get(0).getValue())/arr.get(0).getValue()<0.01){
						String shop_id=arr.get(i).getKey().getShop_id();
						if(tm.get(shop_id)==null){
							tm.put(shop_id, 0);
						}
						tm.put(shop_id,tm.get(shop_id)+1);
					}
				}
				int max=-1;
				String shop="";
				for(Map.Entry<String, Integer> maps:tm.entrySet()){
					if(maps.getValue()>max&&(shopMap.get(Integer.parseInt(maps.getKey())).getClassification()==8)){
						max=maps.getValue();
						shop=maps.getKey();
					}
				}
				map.getKey().setNewShop_id(shop);
//				for(int i=0;i<arr.size();i++){
//					if(arr.get(i).getKey().getClassification()==8){//判断类别
//						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id());
//						break;
//					}
//					else
//						continue;
//				}
				if(map.getKey().getNewShop_id()==null&&arr.size()!=0){
					map.getKey().setNewShop_id(arr.get(0).getKey().getShop_id());
					num+=1;
				}
			}
			else if (tr.getIncome()==1&&tr.getEntermament()==1&&tr.getBaby()==1&&tr.getGender()==2&&tr.getShopping()==1){//11121
				TreeMap<String,Integer> tm=new TreeMap<String,Integer>();
				for(int i=0;i<arr.size();i++){
					//如果用户轨迹与被预测用户的id一致时，不做处理
//					if(arr.get(i).getKey().getUser_id()==tr.getUser_id())
//					{
//						continue;
//					}
					if((arr.get(i).getValue()-arr.get(0).getValue())/arr.get(0).getValue()<0.01){
						String shop_id=arr.get(i).getKey().getShop_id();
						if(tm.get(shop_id)==null){
							tm.put(shop_id, 0);
						}
						tm.put(shop_id,tm.get(shop_id)+1);
					}
				}
				int max=-1;
				String shop="";
				for(Map.Entry<String, Integer> maps:tm.entrySet()){
					if(maps.getValue()>max&&shopMap.get(Integer.parseInt(maps.getKey())).getClassification()==3){
						max=maps.getValue();
						shop=maps.getKey();
					}
				}
				map.getKey().setNewShop_id(shop);
//				for(int i=0;i<arr.size();i++){
//					if(arr.get(i).getKey().getClassification()==3){//判断类别
//						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id());
//						break;
//					}
//					else
//						continue;
//				}
				if(map.getKey().getNewShop_id()==null&&arr.size()!=0){
					map.getKey().setNewShop_id(arr.get(0).getKey().getShop_id());
					num+=1;
				}
			}
			else if (tr.getIncome()==2&&tr.getEntermament()==1&&tr.getBaby()==1&&tr.getGender()==2&&tr.getShopping()==7){//21127
//				Vector classfication=getClassification(tr,classes21127,model21127,100,proInterval[0]);
//				int classfication=tr.getClassification();
				TreeMap<String,Integer> tm=new TreeMap<String,Integer>();
				for(int i=0;i<arr.size();i++){
					//如果用户轨迹与被预测用户的id一致时，不做处理
//					if(arr.get(i).getKey().getUser_id()==tr.getUser_id())
//					{
//						continue;
//					}
					if((arr.get(i).getValue()-arr.get(0).getValue())/arr.get(0).getValue()<0.01){
						String shop_id=arr.get(i).getKey().getShop_id();
						if(tm.get(shop_id)==null){
							tm.put(shop_id, 0);
						}
						tm.put(shop_id,tm.get(shop_id)+1);
					}
				}
				int max=-1;
				String shop="";
				for(Map.Entry<String, Integer> maps:tm.entrySet()){
//					if((boolean) classfication.get(0))
//					{
//						number++;
//						if(maps.getValue()>max&&(shopMap.get(Integer.parseInt(maps.getKey())).getClassification()==(int)classfication.get(1)))
//						{
//						   max=maps.getValue();
//						   shop=maps.getKey();
//						}
//					}
//					else
//					{
//						if(maps.getValue()>max&&(shopMap.get(Integer.parseInt(maps.getKey())).getClassification()==1||shopMap.get(Integer.parseInt(maps.getKey())).getClassification()==6))
//						{
//						    max=maps.getValue();
//							shop=maps.getKey();
//						}
//						
//					}
					if(maps.getValue()>max&&(shopMap.get(Integer.parseInt(maps.getKey())).getClassification()==1||shopMap.get(Integer.parseInt(maps.getKey())).getClassification()==6))
					{
					    max=maps.getValue();
						shop=maps.getKey();
					}
				}
				
				map.getKey().setNewShop_id(shop);
				if(map.getKey().getNewShop_id()==null&&arr.size()!=0){
					map.getKey().setNewShop_id(arr.get(0).getKey().getShop_id());
					num+=1;
				}
			}
			else if (tr.getIncome()==2&&tr.getEntermament()==2&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==5){//22015
//			
//				Vector classfication=getClassification(tr,classes22015,model22015,64,0.01);
				TreeMap<String,Integer> tm=new TreeMap<String,Integer>();
				int classfication=tr.getClassification();
				for(int i=0;i<arr.size();i++){
					//如果用户轨迹与被预测用户的id一致时，不做处理
//					if(arr.get(i).getKey().getUser_id()==tr.getUser_id())
//					{
//						continue;
//					}
					if((arr.get(i).getValue()-arr.get(0).getValue())/arr.get(0).getValue()<0.01){
						String shop_id=arr.get(i).getKey().getShop_id();
						if(tm.get(shop_id)==null){
							tm.put(shop_id, 0);
						}
						tm.put(shop_id,tm.get(shop_id)+1);
					}
				}
				int max=-1;
				String shop="";
				for(Map.Entry<String, Integer> maps:tm.entrySet()){
//					if((boolean) classfication.get(0)){
//						if(maps.getValue()>max&&(shopMap.get(Integer.parseInt(maps.getKey())).getClassification()==(int)classfication.get(1)))
//						{
//							max=maps.getValue();
//							shop=maps.getKey();
//						}
//					}
//					else{
//						if(maps.getValue()>max&&(shopMap.get(Integer.parseInt(maps.getKey())).getClassification()==6||shopMap.get(Integer.parseInt(maps.getKey())).getClassification()==8))
//						{
//							max=maps.getValue();
//							shop=maps.getKey();
//						}
//					}
					if(maps.getValue()>max&&(shopMap.get(Integer.parseInt(maps.getKey())).getClassification()==classfication))
					{
						max=maps.getValue();
						shop=maps.getKey();
					}

				}
				map.getKey().setNewShop_id(shop);
				if(map.getKey().getNewShop_id()==null&&arr.size()!=0){
					map.getKey().setNewShop_id(arr.get(0).getKey().getShop_id());
					num+=1;
				}
			}
			else if (tr.getIncome()==1&&tr.getEntermament()==2&&tr.getBaby()==1&&tr.getGender()==1&&tr.getShopping()==5){//12115
//				Vector classfication=getClassification(tr,classes12115,model12115,80,0.005);
				TreeMap<String,Integer> tm=new TreeMap<String,Integer>();
				int classfication=tr.getClassification();
				for(int i=0;i<arr.size();i++){
					//如果用户轨迹与被预测用户的id一致时，不做处理
//					if(arr.get(i).getKey().getUser_id()==tr.getUser_id())
//					{
//						continue;
//					}
					if((arr.get(i).getValue()-arr.get(0).getValue())/arr.get(0).getValue()<0.01){
						String shop_id=arr.get(i).getKey().getShop_id();
						if(tm.get(shop_id)==null){
							tm.put(shop_id, 0);
						}
						tm.put(shop_id,tm.get(shop_id)+1);
					}
				}
				int max=-1;
				String shop="";
				for(Map.Entry<String, Integer> maps:tm.entrySet()){
//					if((boolean) classfication.get(0))
//					{
//						if(maps.getValue()>max&&(shopMap.get(Integer.parseInt(maps.getKey())).getClassification()==(int)classfication.get(1)))
//						{
//							max=maps.getValue();
//							shop=maps.getKey();
//						}
//						
//					}
//					else
//					{
//						if(maps.getValue()>max&&(shopMap.get(Integer.parseInt(maps.getKey())).getClassification()==1||shopMap.get(Integer.parseInt(maps.getKey())).getClassification()==8))
//						{
//							max=maps.getValue();
//							shop=maps.getKey();
//						}
//					}
					if(maps.getValue()>max&&(shopMap.get(Integer.parseInt(maps.getKey())).getClassification()==classfication))
					{
						max=maps.getValue();
						shop=maps.getKey();
					}
				}
				map.getKey().setNewShop_id(shop);
//				for(int i=0;i<arr.size();i++){
//					if(arr.get(i).getKey().getClassification()==1||arr.get(i).getKey().getClassification()==8){//判断类别
//						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id());
//						break;
//					}
//					else 
//						continue;
//				}
				if(map.getKey().getNewShop_id()==null&&arr.size()!=0){
					map.getKey().setNewShop_id(arr.get(0).getKey().getShop_id());
					num+=1;
				}
			}
			else if (tr.getIncome()==2&&tr.getEntermament()==7&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==5){//27015
				TreeMap<String,Integer> tm=new TreeMap<String,Integer>();
				for(int i=0;i<arr.size();i++){
					//如果用户轨迹与被预测用户的id一致时，不做处理
//					if(arr.get(i).getKey().getUser_id()==tr.getUser_id())
//					{
//						continue;
//					}
					if((arr.get(i).getValue()-arr.get(0).getValue())/arr.get(0).getValue()<0.01){
						String shop_id=arr.get(i).getKey().getShop_id();
						if(tm.get(shop_id)==null){
							tm.put(shop_id, 0);
						}
						tm.put(shop_id,tm.get(shop_id)+1);
					}
				}
				int max=-1;
				String shop="";
				for(Map.Entry<String, Integer> maps:tm.entrySet()){
					if(maps.getValue()>max&&(shopMap.get(Integer.parseInt(maps.getKey())).getClassification()==5)){//||shopMap.get(maps.getKey()).getClassification()==6)){
						max=maps.getValue();
						shop=maps.getKey();
					}
				}
				map.getKey().setNewShop_id(shop);
				if(map.getKey().getNewShop_id()==null&&arr.size()!=0){
					map.getKey().setNewShop_id(arr.get(0).getKey().getShop_id());
					num+=1;
				}
			}
			else {
				if(arr.size()==0){
					map.getKey().setNewShop_id("");
				}
				else
					map.getKey().setNewShop_id(arr.get(0).getKey().getShop_id());
			}
		}
		//System.out.println(num+"---------------------");
	}
	//函数功能：读取训练集,没有将训练集划分为训练集与测试集
	public static ArrayList<Train> readTrainData()throws IOException{
		File f=new File(FILETRAINDATA);
		FileInputStream fis=new FileInputStream(f);
		InputStreamReader isr=new InputStreamReader(fis);
		BufferedReader bufr=new BufferedReader(isr);
		String temp=bufr.readLine();
		ArrayList<Train> trainArr=new ArrayList<Train>();
		while((temp=bufr.readLine())!=null){
			String[] str=temp.split(",");
			int user_id=Integer.parseInt(str[0]);
			int income=Integer.parseInt(str[1]);
			int entermament=Integer.parseInt(str[2]);
			int baby=Integer.parseInt(str[3]);
			int gender=Integer.parseInt(str[4]);
			int shopping=Integer.parseInt(str[5]);
			int shop_id=Integer.parseInt(str[6]);
			int classification=Integer.parseInt(str[7]);
			double userLongitude=Double.parseDouble(str[8]);
			double userLatitude=Double.parseDouble(str[9]);
			double shopLongitude=Double.parseDouble(str[10]);
			double shopLatitude=Double.parseDouble(str[11]);
			String arrival_time=str[12];
			int duration=Integer.parseInt(str[13]);
			Train tr=new Train();
			tr.setArrival_time(arrival_time);
			tr.setBaby(baby);
			tr.setClassification(classification);
			tr.setDuration(duration);
			tr.setEntermament(entermament);
			tr.setGender(gender);
			tr.setIncome(income);
			tr.setShop_id(shop_id+"");
			tr.setShopLatitude(shopLatitude);
			tr.setShopLongitude(shopLongitude);
			tr.setShopping(shopping);
			tr.setUser_id(user_id);
			tr.setUserLongitude(userLongitude);
			tr.setUserLatitude(userLatitude);
			trainArr.add(tr);
		}
		bufr.close();
		isr.close();
		fis.close();
		return trainArr;
	}
	//函数功能：读取训练集数据，并将其划分为训练集与测试集
	public static Object[] readTrainData(int K,int seed)throws IOException{
		File f=new File(FILETRAINDATA);
		FileInputStream fis=new FileInputStream(f);
		InputStreamReader isr=new InputStreamReader(fis);
		BufferedReader bufr=new BufferedReader(isr);
		String temp=bufr.readLine();
		ArrayList<Train> trainArr=new ArrayList<Train>();
		ArrayList<Train> basicTestArr=new ArrayList<Train>();
		Random rand=new Random(seed);
		while((temp=bufr.readLine())!=null){
			String[] str=temp.split(",");
			//System.out.println(str.length);
			int user_id=Integer.parseInt(str[0]);
			int income=Integer.parseInt(str[1]);
			int entermament=Integer.parseInt(str[2]);
			int baby=Integer.parseInt(str[3]);
			int gender=Integer.parseInt(str[4]);
			int shopping=Integer.parseInt(str[5]);
			int shop_id=Integer.parseInt(str[6]);
			int classification=Integer.parseInt(str[7]);
			double userLongitude=Double.parseDouble(str[8]);
			double userLatitude=Double.parseDouble(str[9]);
			double shopLongitude=Double.parseDouble(str[10]);
			double shopLatitude=Double.parseDouble(str[11]);
			String arrival_time=str[12];
			int duration=Integer.parseInt(str[13]);
			Train tr=new Train();
			tr.setArrival_time(arrival_time);
			tr.setBaby(baby);
			tr.setClassification(classification);
			tr.setDuration(duration);
			tr.setEntermament(entermament);
			tr.setGender(gender);
			tr.setIncome(income);
			tr.setShop_id(shop_id+"");
			tr.setShopLatitude(shopLatitude);
			tr.setShopLongitude(shopLongitude);
			tr.setShopping(shopping);
			tr.setUser_id(user_id);
			tr.setUserLongitude(userLongitude);
			tr.setUserLatitude(userLatitude);
			if(rand.nextInt(K)==1)
				basicTestArr.add(tr);
			else
				trainArr.add(tr);
		}
		bufr.close();
		isr.close();
		fis.close();
		Object[] obj=new Object[]{trainArr,basicTestArr};
		return obj;
	}

	//函数功能：读取测试集相关数据
	public static ArrayList<Train> readTestData()throws IOException{
		File f=new File(FILETEST);
		FileInputStream fis=new FileInputStream(f);
		InputStreamReader isr=new InputStreamReader(fis);
		BufferedReader bufr=new BufferedReader(isr);
		String temp=bufr.readLine();
		ArrayList<Train> testArr=new ArrayList<Train>();
		while((temp=bufr.readLine())!=null){
			String[] str=temp.split(",");
			int user_id=Integer.parseInt(str[0]);
			int income=Integer.parseInt(str[1]);
			int entermament=Integer.parseInt(str[2]);
			int baby=Integer.parseInt(str[3]);
			int gender=Integer.parseInt(str[4]);
			int shopping=Integer.parseInt(str[5]);
			double userLongitude=Double.parseDouble(str[6]);
			double userLatitude=Double.parseDouble(str[7]);
			String arrival_time=str[8];
			int duration=Integer.parseInt(str[9]);
			int classification=Integer.parseInt(str[10]);
			Train tr=new Train();
			tr.setArrival_time(arrival_time);
			tr.setBaby(baby);
			tr.setClassification(classification);
			tr.setDuration(duration);
			tr.setEntermament(entermament);
			tr.setGender(gender);
			tr.setIncome(income);
			tr.setShopping(shopping);
			tr.setUser_id(user_id);
			tr.setUserLongitude(userLongitude);
			tr.setUserLatitude(userLatitude);
			testArr.add(tr);	
		}
		bufr.close();
		isr.close();
		fis.close();
		return testArr;
	}
	//函数功能：读取店铺信息
	public static Object[] readShop()throws IOException{
		File f=new File(FILEPATHSHOPPROFILE);
		FileInputStream fis=new FileInputStream(f);
		InputStreamReader isr=new InputStreamReader(fis);
		BufferedReader bufr=new BufferedReader(isr);
		String temp="";
		ArrayList<Shop> shopArr=new ArrayList<Shop>();
		TreeMap<Integer,Shop> shopMap=new TreeMap<Integer,Shop>();
		while((temp=bufr.readLine())!=null){
			String[] str=temp.split(",");
			int shop_id=(int)Double.parseDouble(str[4]);
			//System.out.println(shop_id);
			String name=str[0];
			int classification=Integer.parseInt(str[3]);
			double longitude=Double.parseDouble(str[1]);
			double latitude=Double.parseDouble(str[2]);
			Shop shop=new Shop();
			shop.setShop_id(shop_id);
			shop.setClassification(classification);
			shop.setName(name);
			shop.setLatitude(latitude);
			shop.setLongitude(longitude);
			shopArr.add(shop);
			shopMap.put(shop_id,shop);
		}
		bufr.close();
		isr.close();
		fis.close();
		Object[] obj=new Object[]{shopArr,shopMap};
		return obj;
	}
	//函数功能:相似度计算
	public static TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim(ArrayList<Train> trainArr,ArrayList<Train> testArr,double d){
		TreeMap<Train,ArrayList<MyMap<Train,Double>>> simMap=new TreeMap<Train,ArrayList<MyMap<Train,Double>>>();
		for(int i=0;i<testArr.size();i++){
			ArrayList<MyMap<Train,Double>> arr=distance( testArr.get(i), trainArr,d);
			simMap.put(testArr.get(i), arr);
		}
		return simMap;
	}
//函数功能：计算两个轨迹之间的距离
	public static ArrayList<MyMap<Train,Double>> distance(Train test,ArrayList<Train> trainArr,double d){
			ArrayList<MyMap<Train,Double>> arr=new ArrayList<MyMap<Train,Double>>();
			for(int i=0;i<trainArr.size();i++){
				Train train=trainArr.get(i);
				double longDif=longDis( test, train);
				double latDif=latDis( test, train);
//				double incomeDif=incomeDif( test, train);
//				double entermamentDif= entermamentDif( test, train);
//				double babyDif=babyDif( test, train);
//				double genderDif= genderDif( test, train);
//				double shoppingDif= shoppingDif( test, train);
//				double sum=incomeDif+entermamentDif+babyDif+genderDif+shoppingDif;
				/*参数调节：
				 * 0.01,0.01，2，性能为0.1406.
				 * 0.01，0.01,3,性能为0.104；
				 * 0.02,0.02,2，性能为0.04*/
				//double d=0.0005;
				if(longDif<=d&&latDif<=d){
					double d1=108000*Math.cos(test.getUserLatitude())*longDif;
					double d2=108000*latDif;
					double dis=Math.sqrt(Math.pow(d1, 2)+Math.pow(d2, 2));
					MyMap<Train,Double> mm=new MyMap<Train,Double>(train,dis);
					arr.add(mm);
				}
			}
			Collections.sort(arr,new MyComparator());
//			System.out.print("***********"+test.getIncome()+test.getEntermament()+test.getBaby()+test.getGender()+test.getShopping());
//			for(int i=0;i<arr.size();i++){
//				System.out.print("   "+arr.get(i).getValue()+"***"+arr.get(i).getKey().getClassification()+"***"+arr.get(i).getKey().getShop_id()+"====");
//			}
//			System.out.println("");
			ArrayList<MyMap<Train,Double>> newArr=new ArrayList<MyMap<Train,Double>>();
			for(int i=0;i<arr.size();i++){
				Train tr=arr.get(i).getKey();
				double dis=calDistance3(test,tr);
				MyMap<Train,Double> mm=new MyMap<Train,Double>(tr,dis);
				newArr.add(mm);
			}
			Collections.sort(newArr,new MyComparator());
			if(test.getUser_id()==1554){
				for(int i=0;i<newArr.size();i++){
					//System.out.print(newArr.get(i).getValue()+"--"+newArr.get(i).getKey().getShop_id()+"--"+newArr.get(i).getKey().getClassification()+"==");
					System.out.print(newArr.get(i).getValue()+"--"+newArr.get(i).getKey().getShop_id()+"--"+newArr.get(i).getKey().getClassification()+"==");
				}
//				System.out.println("");
				
			}
//			for(int i=0;i<newArr.size();i++){
//				System.out.print(newArr.get(i).getKey().getShop_id()+"=="+newArr.get(i).getValue()+"   ");
//			}
//			System.out.println("");
			return newArr;
	}
	
	public static double calDistance3(Train test,Train tr){
		  double shopLong=tr.getShopLongitude();
		  double shopLati=tr.getShopLatitude();
		  double userLong=test.getShopLongitude();
		  double userLati=test.getUserLatitude();
	      double latDif=Math.abs(shopLong-userLong);
	      double lonDif=Math.abs(shopLati-userLati);
	      double MeLat=108000*latDif;
	      double MeLon=108000*Math.cos(userLati)*lonDif;
	      double d=Math.sqrt(Math.pow(MeLat, 2)+Math.pow(MeLon, 2));
	      return d;  
	}
	public static double  longDis(Train test,Train train){
		double longDif=Math.abs(test.getUserLongitude()-train.getUserLongitude());
		return longDif;
	}
	public static double  latDis(Train test,Train train){
		double latDif=Math.abs(test.getUserLatitude()-train.getUserLatitude());
		return latDif;
	}
	
	public static double shoppingDif(Train test,Train train){
		if(test.getShopping()!=train.getShopping()){
			return 1;
		}
		else
			return 0;
}
	public static void writerCSV(TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim) throws IOException{
		File f=new File("E:\\ChinaUnicomContest\\finalResylt1112_1.csv");
		FileWriter fw=new FileWriter(f);
		fw.write("USERID");
		fw.write(",");
		fw.write("SHOPID");
		fw.write(",");
		fw.write("ARRIVAL_TIME");
		fw.write("\r\n");
		for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> me:user_trace_Sim.entrySet()){
			int user_id=me.getKey().getUser_id();
			String shop_id=me.getKey().getNewShop_id();
			//System.out.println(shop_id);
			String arrival_time=me.getKey().getArrival_time();
			fw.write(user_id+"");
			fw.write(",");
			if(shop_id==null)
				fw.write("");
			else
				fw.write(shop_id);
			fw.write(",");
			fw.write(arrival_time);
			fw.write("\r\n");
		}
		fw.flush();
		fw.close();
	}
}





























