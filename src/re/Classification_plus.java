package re;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.Vector;

import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;

import java.util.Map.Entry;



/**
 * @author SJP
 * 版本号：11-03
 *
 */

public class Classification_plus {
	public static final String FILETRAINDATA="E:\\data_contest\\ChinaUnicomContest\\123.csv";
	public static final String FILETEST="E:\\data_contest\\ChinaUnicomContest\\testResult.csv";
	public static final String FILEPATHSHOPPROFILE="E:\\data_contest\\ChinaUnicomContest\\shop_good.csv";
	//复赛数据
	public static final String FILETRAINDATA2= "E:/data_contest/ChinaUnicomContest/复赛数据/123.csv";
	public static final String FILETEST2="E:/data_contest/ChinaUnicomContest/复赛数据/testpre.csv";
	public static final String FILEPATHSHOPPROFILE2="E:/data_contest/ChinaUnicomContest/复赛数据/shop_profile.csv";
	public static int [] classes22015={6,8};
	public static int[] classes12115={1,8};
	public static int[] classes21127={1,6};
	public static svm_model model22015;
	public static svm_model model12115;
	public static svm_model model21127;
	public static void main(String[] args) throws IOException{
//		 model22015=new ClassifySVM().modelTrain(2048.0, 0.5, "E:/ChinaUnicomContest/22015_svm.csv", classes22015, 104,22015,64);
//		 model12115=new ClassifySVM().modelTrain(0.5, 2.0, "E:/ChinaUnicomContest/12115_svm.csv", classes12115, 94,12115) ;
//		 model21127=new ClassifySVM().modelTrain(0.5, 8.0, "E:/ChinaUnicomContest/21127_svm.csv", classes21127, 376,21127);
	    int K=10;
	    Object[] obj=readTrainData(FILETRAINDATA,K);
	    ArrayList<Train> trainArr=(ArrayList<Train>)obj[0];
	
        ArrayList<Train> basicTestArr=(ArrayList<Train>)obj[1];
//        ArrayList<Train> trainData=readTrainData("E:/data_contest/ChinaUnicomContest/复赛数据/123.csv");
        
	
		writeFileNeiborALL(user_trace_Sim(trainArr,basicTestArr,0.005));
		
//		usersAdd(trainArrNew);
//		int count2=0;
//		int predict=0;
//		int nummax=0;
//		int predictMax=0;
//		for(int number=200;number<=200;number++)
//		{
//			 model12115=new ClassifySVM().modelTrain(0.5, 2.0, "E:/ChinaUnicomContest/12115_svm.csv", classes12115, 94,12115,number);
//			for(Train tr:trainArr)
//			{
//				if (tr.getIncome()==1&&tr.getEntermament()==2&&tr.getBaby()==1&&tr.getGender()==1&&tr.getShopping()==5)
//				{
//					count2++;
//					int classfication=tr.getClassification();
//					int classCaled=getClassification(tr,classes12115,model12115,number);
//					if(classCaled==classfication)
//						predict++;
//				}
//				
//			}
//			
//			System.out.println("12115:"+number+":"+predict+"===="+count2);
//			if(predictMax<predict)
//			{
//				predictMax=predict;
//				nummax=number;
//			}
//			count2=0;
//			predict=0;
//		}
//		System.out.println("12115:nummax:"+nummax+":   "+predictMax+"=="+count2);
		
//		count=0;
//		predict=0;
//		for(Train tr:trainArr)
//		{
//			if (tr.getIncome()==1&&tr.getEntermament()==2&&tr.getBaby()==1&&tr.getGender()==1&&tr.getShopping()==5)
//			{
//				count++;
//				int classfication=tr.getClassification();
//				int classCaled=getClassification(tr,classes12115,model12115);
//				if(classCaled==classfication)
//					predict++;
//			}
//			
//		}
//		System.out.println("12115:"+predict+"===="+count);
//		count=0;
//		predict=0;
//		for(Train tr:trainArr)
//		{
//			if (tr.getIncome()==2&&tr.getEntermament()==1&&tr.getBaby()==1&&tr.getGender()==2&&tr.getShopping()==7)
//			{
//				count++;
//				int classfication=tr.getClassification();
//				int classCaled=getClassification(tr,classes21127,model21127);
//				if(classCaled==classfication)
//					predict++;
//			}
//			
//		}
//		System.out.println("21127:"+predict+"===="+count);
		
//		Object[] Obj1= readShop();
//		ArrayList<Shop> shopArr=(ArrayList<Shop>)Obj1[0];
//		//System.out.println(shopArr.size());
//		TreeMap<Integer,Shop> shopMap=(TreeMap<Integer,Shop>)Obj1[1];
//		System.out.println(trainArr.size()+"  "+basicTestArr.size());
//		ArrayList<Train> testArr=readTestData();
//		double d=0.0005;
////		TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim= user_trace_Sim(trainArr, basicTestArr,d);
//		TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim= user_trace_Sim(trainArrNew, trainArrNew,d);
//		int [] att={2,2,0,1,5};
//		writeFileNeibor(user_trace_Sim,att);
		
//		int num=0;
//        
//		fillValue(user_trace_Sim);
//		totalCode(user_trace_Sim, shopMap);
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

//		int num1=0;
//		int count1=0;
//		int ff=0;
//		TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Simnew= user_trace_Sim(trainArrNew, testArr,d);
//		for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map1:user_trace_Simnew.entrySet()){
//			if(map1.getValue().size()==0)
//				ff+=1;
//		}
//		//System.out.println(ff+"****");
//		fillValue(user_trace_Simnew);
//		totalCode(user_trace_Simnew, shopMap);
//		//copy( user_trace_Simnew);//这个程序没用
//		for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map1:user_trace_Simnew.entrySet()){
//			Train trs=map1.getKey();
//			//if(trs.getNewShop_id().equals(""))
//				count+=1;
//		}	
//		writerCSV( user_trace_Simnew) ;
	}
	public static ArrayList<Train> usersAdd(ArrayList<Train> trainAll)
	{
		ArrayList<Train> usersAddre=new ArrayList<Train>();
		int number=0;
		int yes=0;
		for(Train tr1:trainAll)
		{
			for(Train tr2:trainAll)
			{
				if(tr1.equals(tr2))
					continue;
				if(tr1.getUser_id()==tr2.getUser_id() && (Math.abs(tr1.getUserLatitude()-tr2.getUserLatitude())<0.000001 && Math.abs(tr1.getUserLongitude()-tr2.getUserLongitude())<0.000001 ))
				{
					usersAddre.add(tr1);
					usersAddre.add(tr2);
					System.out.println(tr1.getUserLatitude()+"-"+tr2.getUserLatitude()+"="+(tr1.getUserLatitude()-tr2.getUserLatitude()));
					number++;
					if(tr1.getShop_id().equals(tr2.getShop_id()))
						yes++;
					
				}
			}
		}
		System.out.println(yes+"=="+number);
		return usersAddre;
	}
	//找出所有用户最近邻商户的类别
	public static void writeFileNeiborALL(TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim ) throws IOException
	{
		FileWriter fw=new FileWriter("E:\\data_contest\\ChinaUnicomContest\\user_simNeibor.csv");
		//label=0,表示用户，1表示实际推荐商户，2表示邻居商户
		fw.write("id ,lon,lati,label"+"\n");
		for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map1:user_trace_Sim.entrySet()){
			Train trs=map1.getKey();

				fw.write(trs.getUser_id()+",");
				fw.write(trs.getUserLongitude()+",");
				fw.write(trs.getUserLatitude()+",");
				fw.write(0+"\n");
				ArrayList<MyMap<Train,Double>> arr=map1.getValue();
				for(MyMap<Train,Double> mymap:arr)
				{
					Train tr=mymap.getKey();
					int label=2;
				     if(trs.getShop_id()==tr.getShop_id())
				     {
				    	 label=1;
				     }
				     fw.write(tr.getShop_id()+",");
						fw.write(tr.getShopLongitude()+",");
						fw.write(tr.getShopLatitude()+",");
						fw.write(label+"\n");  
				}

			
			
		}
		fw.flush();
		fw.close();
		
	}
	
	//找出指定标签组合的邻居用户分布数据
	
	public static void writeFileNeibor(TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim ,int [] att) throws IOException
	{
		FileWriter fw=new FileWriter("E:/ChinaUnicomContest/22015_neibors_userSim.csv");
		//label=0,表示用户，1表示实际推荐商户，2表示邻居商户
		fw.write("id ,lon,lati,label"+"\n");
		for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map1:user_trace_Sim.entrySet()){
			Train trs=map1.getKey();
			if(trs.getIncome()==att[0]&&trs.getEntermament()==att[1]&&trs.getBaby()==att[2]&&trs.getGender()==att[3]&&trs.getShopping()==att[4])
			{
				fw.write(trs.getUser_id()+",");
				fw.write(trs.getUserLongitude()+",");
				fw.write(trs.getUserLatitude()+",");
				fw.write(0+"\n");
				ArrayList<MyMap<Train,Double>> arr=map1.getValue();
				for(MyMap<Train,Double> mymap:arr)
				{
					int label=2;
				     if(trs.getShop_id()==mymap.getKey().getShop_id())
				     {
				    	 label=1;
				     }
				     fw.write(mymap.getKey().getShop_id()+",");
						fw.write(mymap.getKey().getShopLongitude()+",");
						fw.write(mymap.getKey().getShopLatitude()+",");
						fw.write(label+"\n");  
				}
			}
			
			
		}
		fw.flush();
		fw.close();
		
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
				System.out.println("");
				for(int i=0;i<arr.size();i++){
					System.out.print(arr.get(i).getValue()+"====");
				}
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
					System.out.println("***********");
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
		System.out.println(num);
	}
	//函数功能：填空值总程序
//	public static void test(TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim,ArrayList<Shop> shopArr){
//		TreeMap<Train,ArrayList<MyMap<Shop,Double>>>tm= fillNull( user_trace_Sim, shopArr);
//		shopIDValue( tm);
//	}
//	//函数功能：空值填充.找出最近的店铺
//	public static TreeMap<Train,ArrayList<MyMap<Shop,Double>>> fillNull(TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim,ArrayList<Shop> shopArr){
//		ArrayList<Train> notNullArr=new ArrayList<Train>();
//		ArrayList<Train>  nullArr=new ArrayList<Train>();
//		for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map:user_trace_Sim.entrySet()){
//			if(map.getKey().getNewShop_id()==null||map.getKey().getNewShop_id().equals("")){
//				nullArr.add(map.getKey());
//			}
//			else
//				notNullArr.add(map.getKey());
//		}
//		System.out.println(nullArr.size()+"-----");
//		TreeMap<Train,ArrayList<MyMap<Shop,Double>>> tm=new TreeMap<Train,ArrayList<MyMap<Shop,Double>>>();
//		for(int i=0;i<nullArr.size();i++){
//			ArrayList<MyMap<Shop,Double>> arr=getDisArr(nullArr.get(i), shopArr);
//			tm.put(nullArr.get(i), arr);
//		}
//		return tm;
//	}
//	//求出满足距离的N个店铺
//	public static ArrayList<MyMap<Shop,Double>> getDisArr(Train tr,ArrayList<Shop> shopArr){
//		//System.out.println(shopArr.size()+"------------------------");
//		ArrayList<MyMap<Shop,Double>> arr=new ArrayList<MyMap<Shop,Double>>();
//		for(int i=0;i<shopArr.size();i++){
//			double d=calDistance2(tr,shopArr.get(i));
//			if(d<300){
//				MyMap<Shop,Double> mm=new MyMap(shopArr.get(i),d);
//				arr.add(mm);
//			}
//			
//			
//		}
//		Collections.sort(arr,new MyComparators());
//		System.out.println("");
//		for(int i=0;i<arr.size();i++){
//			System.out.print(arr.get(i).getValue()+"---");
//		}
//		return arr;
//	}
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
	public static void fillValue(TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim) throws NumberFormatException, IOException{
		int num=0;
		for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map:user_trace_Sim.entrySet()){
			Train tr=map.getKey();
			ArrayList<MyMap<Train,Double>> arr=map.getValue();
			if(tr.getIncome()==2&&tr.getEntermament()==1&&tr.getBaby()==1&&tr.getGender()==2&&tr.getShopping()==2){//2,1,1,2,2
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==1){//判断类别
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id());
						break;
					}
					else
						continue;
						
				}
				if(map.getKey().getNewShop_id()==null&&arr.size()!=0){
					map.getKey().setNewShop_id(arr.get(0).getKey().getShop_id());
					num+=1;
				}
			}
			else if(tr.getIncome()==3&&tr.getEntermament()==3&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==9){//33019
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==6){//判断类别
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id());
						break;
					}
					else
						continue;
				}
				if(map.getKey().getNewShop_id()==null&&arr.size()!=0){
					map.getKey().setNewShop_id(arr.get(0).getKey().getShop_id());
					num+=1;
				}
			}
			else if (tr.getIncome()==2&&tr.getEntermament()==1&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==5){//21015
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==8){//判断类别
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id());
						break;
					}
					else
						continue;
				}
				if(map.getKey().getNewShop_id()==null&&arr.size()!=0){
					map.getKey().setNewShop_id(arr.get(0).getKey().getShop_id());
					num+=1;
				}
			}
			else if (tr.getIncome()==1&&tr.getEntermament()==1&&tr.getBaby()==1&&tr.getGender()==2&&tr.getShopping()==1){//11121
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==3){//判断类别
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id());
						break;
					}
					else
						continue;
				}
				if(map.getKey().getNewShop_id()==null&&arr.size()!=0){
					map.getKey().setNewShop_id(arr.get(0).getKey().getShop_id());
					num+=1;
				}
			}
			else if (tr.getIncome()==2&&tr.getEntermament()==1&&tr.getBaby()==1&&tr.getGender()==2&&tr.getShopping()==7){//21127
//				int [] classes={1,6};
//				int classfication=getClassification(tr,classes21127,model21127,64);
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==1){//判断类别arr.get(i).getKey().getClassification()==1||arr.get(i).getKey().getClassification()==6
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id());
						break;
					}
				}
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==6&&map.getKey().getNewShop_id()==null){//判断类别arr.get(i).getKey().getClassification()==1||arr.get(i).getKey().getClassification()==6
					
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id());
						break;
					}
				}
				if(map.getKey().getNewShop_id()==null&&arr.size()!=0){
					map.getKey().setNewShop_id(arr.get(0).getKey().getShop_id());
					num+=1;
				}
			}
			else if (tr.getIncome()==2&&tr.getEntermament()==2&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==5){//22015
				
				int classfication=getClassification(tr,classes22015,model22015,64);
				for(int i=0;i<arr.size();i++){
//					if(arr.get(i).getKey().getClassification()==6||arr.get(i).getKey().getClassification()==8)
					if(arr.get(i).getKey().getClassification()==classfication)
					{//判断类别
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id());
						break;
					}
					else 
						continue;
				}
				if(map.getKey().getNewShop_id()==null&&arr.size()!=0){
					map.getKey().setNewShop_id(arr.get(0).getKey().getShop_id());
					num+=1;
				}
			}
			else if (tr.getIncome()==1&&tr.getEntermament()==2&&tr.getBaby()==1&&tr.getGender()==1&&tr.getShopping()==5){//12115
//				int classfication=getClassification(tr,classes12115,model12115);
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==1||arr.get(i).getKey().getClassification()==8)
//					if(arr.get(i).getKey().getClassification()==classfication)
					{//判断类别
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id());
						break;
					}
					else 
						continue;
				}
				if(map.getKey().getNewShop_id()==null&&arr.size()!=0){
					map.getKey().setNewShop_id(arr.get(0).getKey().getShop_id());
					num+=1;
				}
			}
//			else if (tr.getIncome()==2&&tr.getEntermament()==6&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==5){//26015
//				for(int i=0;i<arr.size();i++){
//					if(arr.get(i).getKey().getClassification()==1){//||arr.get(i).getKey().getClassification()==1||arr.get(i).getKey().getClassification()==8){//判断类别
//						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id());
//						break;
//					}
//					else 
//						continue;
//				}
//				for(int i=0;i<arr.size();i++){
//					if(arr.get(i).getKey().getClassification()==6&&map.getKey().getNewShop_id()==null){//||arr.get(i).getKey().getClassification()==1||arr.get(i).getKey().getClassification()==8){//判断类别
//						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id());
//						break;
//					}
//					else 
//						continue;
//				}
//				for(int i=0;i<arr.size();i++){
//					if(arr.get(i).getKey().getClassification()==8&&map.getKey().getNewShop_id()==null){//||arr.get(i).getKey().getClassification()==1||arr.get(i).getKey().getClassification()==8){//判断类别
//						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id());
//						break;
//					}
//					else 
//						continue;
//				}
//				if(map.getKey().getNewShop_id()==null&&arr.size()!=0){
//					map.getKey().setNewShop_id(arr.get(0).getKey().getShop_id());
//					num+=1;
//				}
//			}
			else if (tr.getIncome()==2&&tr.getEntermament()==7&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==5){//27015
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==5){//判断类别
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id());
						break;
					}
					else 
						continue;
				}
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
	//
	 public static int getClassification(Train test,int[] classes,svm_model model,int neiborNumber) throws NumberFormatException, IOException
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
			
		 int label=(int) svm.svm_predict(model,testdata);
		 if(label==1)
			 return classes[0];
		 else
			 return classes[1];
			
			
	    	
	    	
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
	//函数功能：读取训练集,没有将训练集划分为训练集与测试集
	public static ArrayList<Train> readTrainData(String FILETRAINDATA)throws IOException{
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
	public static Object[] readTrainData(String FILETRAINDATA,int K)throws IOException{
		File f=new File(FILETRAINDATA);
		FileInputStream fis=new FileInputStream(f);
		InputStreamReader isr=new InputStreamReader(fis);
		BufferedReader bufr=new BufferedReader(isr);
		String temp=bufr.readLine();
		ArrayList<Train> trainArr=new ArrayList<Train>();
		ArrayList<Train> basicTestArr=new ArrayList<Train>();
		Random rand=new Random(20);
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
			if(rand.nextInt(K)==0)
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
	public static ArrayList<Train> readTestData(String FILETEST )throws IOException{
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
	public static Object[] readShop(String FILEPATHSHOPPROFILE)throws IOException{
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
//					double d1=108000*Math.cos(test.getUserLatitude())*longDif;
//					double d2=108000*latDif;
					double dis=Math.sqrt(Math.pow(longDif, 2)+Math.pow(latDif, 2));
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
		File f=new File("E:\\ChinaUnicomContest\\result1104_.csv");
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




























