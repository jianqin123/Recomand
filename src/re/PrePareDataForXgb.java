package re;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import javax.management.RuntimeErrorException;

import java.util.Map.Entry;

public class PrePareDataForXgb {
	        //初赛数据
			public static final String FILETRAINDATA="E:\\data_contest\\ChinaUnicomContest\\123.csv";
			public static final String FILETEST="E:\\data_contest\\ChinaUnicomContest\\testResult.csv";
			public static final String FILEPATHSHOPPROFILE="E:\\data_contest\\ChinaUnicomContest\\shop_good.csv";
			
			
			//复赛数据
			public static final String FILETRAINDATA2= "E:/data_contest/ChinaUnicomContest/复赛数据/123.csv";
			public static final String FILETEST2="E:/data_contest/ChinaUnicomContest/复赛数据/testpre.csv";
			public static final String FILEPATHSHOPPROFILE2="E:/data_contest/ChinaUnicomContest/复赛数据/shop_profile.csv";
			
			public static final String ResultFile="E:/data_contest/ChinaUnicomContest/复赛数据/result12_02_1.csv";
			public static final String FILEUSERSIM="E:\\data_contest\\ChinaUnicomContest\\user_simNeibor.csv";
			public static final String OptimazeParam="E:/data_contest/ChinaUnicomContest/复赛数据/OptimazeParam.csv";
			public static final String ModelFile="E:/data_contest/ChinaUnicomContest/复赛数据/model.csv";
			
			//生成结果数据
			  public static final String resultFile="E:/data_contest/ChinaUnicomContest/复赛数据/resultAnalysis.csv";
			  //训练数据
			  public static final String preparedTrainData="E:/data_contest/ChinaUnicomContest/复赛数据/trainData.csv";
			  public static final String preparedTrainData2="E:/data_contest/ChinaUnicomContest/复赛数据/trainData2.csv";
			  public static final String preparedTrainData3="E:/data_contest/ChinaUnicomContest/复赛数据/trainData3.csv";
			  public static final String preparedTrainData4="E:/data_contest/ChinaUnicomContest/复赛数据/trainData4.csv";
			  public static final String preparedTrainData5="E:/data_contest/ChinaUnicomContest/复赛数据/trainData5.csv";
			  public static final String preparedTrainData6="E:/data_contest/ChinaUnicomContest/复赛数据/trainData6.csv";
			  public static final String preparedTrainData7="E:/data_contest/ChinaUnicomContest/复赛数据/trainData7.csv";
			  public static final String preparedTrainData9="E:/data_contest/ChinaUnicomContest/复赛数据/trainData9.csv";
			  public static final String preparedTrainData10="E:/data_contest/ChinaUnicomContest/复赛数据/trainData10.csv";
			  public static final String preparedTrainData11="E:/data_contest/ChinaUnicomContest/复赛数据/trainData11.csv";
			  public static final String preparedTrainData12="E:/data_contest/ChinaUnicomContest/复赛数据/trainData12.csv";

			  //测试集数据
			  public static final String preparedTestData="E:/data_contest/ChinaUnicomContest/复赛数据/TestData.csv";
			  public static final String preparedTestData2="E:/data_contest/ChinaUnicomContest/复赛数据/TestData2.csv";
			  public static final String preparedTestData3="E:/data_contest/ChinaUnicomContest/复赛数据/TestData3.csv";
			  public static final String preparedTestData4="E:/data_contest/ChinaUnicomContest/复赛数据/TestData4.csv";
			  public static final String preparedTestData5="E:/data_contest/ChinaUnicomContest/复赛数据/TestData5.csv";
			  public static final String preparedTestData6="E:/data_contest/ChinaUnicomContest/复赛数据/TestData6.csv";
			  public static final String preparedTestData7="E:/data_contest/ChinaUnicomContest/复赛数据/TestData7.csv";
			  public static final String preparedTestData9="E:/data_contest/ChinaUnicomContest/复赛数据/TestData9.csv";
			  //xgboost结果数据
			  public static final String xgbResultData="E:/data_contest/ChinaUnicomContest/复赛数据/testResutData.csv";
			  public static final String xgbResultData2="E:/data_contest/ChinaUnicomContest/复赛数据/testResutData2.csv";
//			  public static final String preparedTestData="E:/data_contest/ChinaUnicomContest/复赛数据/preparedData2.csv";
			 
			  //空值文件
			  public static final String nullVale="E:/data_contest/ChinaUnicomContest/复赛数据/nullValue.csv";
			  //没有找到用户轨迹
			  public static final String noTrace="E:/data_contest/ChinaUnicomContest/复赛数据/noTrace.csv";
			  
			  public static final String preparedData3="E:/data_contest/ChinaUnicomContest/复赛数据/preparedData3.csv";
			
			  public static final String preparedCorrecData="E:/data_contest/ChinaUnicomContest/复赛数据/preparedCorrectData.csv";
			  public static final String preparedErrorData="E:/data_contest/ChinaUnicomContest/复赛数据/preparedErrorData.csv";
			 //线上测试数据
			  public static final String testOnLine="E:/data_contest/ChinaUnicomContest/复赛数据/testOnline.csv";
			  public static final String testOnline2="E:/data_contest/ChinaUnicomContest/复赛数据/testOnline2.csv";
			  public static final String testOnline3="E:/data_contest/ChinaUnicomContest/复赛数据/testOnline3.csv";
			  public static final String testOnline4="E:/data_contest/ChinaUnicomContest/复赛数据/testOnline4.csv";
			  public static final String testOnline5="E:/data_contest/ChinaUnicomContest/复赛数据/testOnline5.csv";
			  public static final String testOnline6="E:/data_contest/ChinaUnicomContest/复赛数据/testOnline6.csv";
			  public static final String testOnline7="E:/data_contest/ChinaUnicomContest/复赛数据/testOnline7.csv";
			  public static final String testOnline8="E:/data_contest/ChinaUnicomContest/复赛数据/testOnline8.csv";

			  //模型数据
				public static ArrayList<Vector<Integer>> models=new ArrayList<Vector<Integer>>();
				public static ArrayList<Vector<Integer>> valueOfModels=new ArrayList<Vector<Integer>>();
			  //中间数据
			public static ArrayList<Train> allData;
			public static ArrayList<Train>  trainData;
			public static ArrayList<Train>  basicTest;
			public static TreeMap<Integer,Shop> shopMap;
			public static TreeMap<UserPos,ArrayList<Integer>> userPosHisClass=new TreeMap<UserPos,ArrayList<Integer>>();
			public static TreeMap<Pos,TreeMap<Integer,Integer>> PosHisClass=new TreeMap<Pos,TreeMap<Integer,Integer>>();
		    public static TreeMap<UserTrace,ArrayList<Probabilty>>  result=new TreeMap<UserTrace,ArrayList<Probabilty>>();
			//参数
			public static double d=0;
			public static double[] d2={0.01,0.02,0.03,0.04,0.05,0.06,0.07,0.08,0.09,0.10,0.011,0.12,0.13,0.14,0.15,0.16,0.17,0.18,0.19};
			public static int numOfModel=131;
			public static int directRec=0;
			public static int direcRecCor=0;
			//轨迹中包含正确推荐商户的轨迹
			public static int corInSim;
			//轨迹包含率
			public static double corRatInSim;
			//空值数目
			public static int nullNum=0;
			//只有一条轨迹的数目
			public static int oneTraceNum=0;
			public static int oneTraceCor=0;
			//扩大范围取值的次数
			public static int count=0;
			public static int size=4;
			public static FileWriter notrace;
			
			

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		
		PrePareDataForXgb pp=new PrePareDataForXgb();
//		System.out.println(pp.getWeek(new SimpleDateFormat("yyyyMMddHHmmss").parse("20140817100013").getDate()));
//		       pp.buildModelFromFile(ModelFile);
//		       notrace=new FileWriter(noTrace);
//		       pp.prePareData(preparedTrainData, 10, 20);
//		       pp.prePareData(preparedTestData, 1, 40);
		//加上用户相似度之后
//		       pp.prePareDataPlus(preparedTrainData2, 10, 20);
//		       pp.prePareDataPlus(preparedTestData2, 1, 190);
		//采用每个用户抽一个的规则构造训练集
//		       pp.prePareDataInOne(preparedTrainData3, 4, 20);
//		       pp.prePareDataInOne(preparedTestData3, 1, 90);
//		       pp.prePareDataInOne2(preparedTrainData4, 4, 20);
//		       pp.prePareDataInOne2(preparedTestData4, 1, 20);  
		//每周抽取一天
//		       pp.prePareDataInOneDayPlus( preparedTrainData4, 7, 20);
//		       pp.prePareDataInOneDayPlus( preparedTestData4, 1, 141);
		//
//		       pp.prePareDataInOneDayPlus2( preparedTrainData5, 7, 21);
//		       pp.prePareDataInOneDayPlus2( preparedTestData5, 1, 23);
		       
//		      pp. prePareDataInOneDayPlus3(preparedTrainData7, 7, 89);
		       
		       
		       //在6的基础上加了时间信息的字段
//		       pp.prePareDataInOneDayPlus4(preparedTrainData9, 7, 43);
//		       pp.prePareDataInOneDayPlus4(preparedTrainData11, 7, 999);
//		       pp.prePareDataInOneDayPlus4(preparedTrainData12, 7, 45649);
////		       
//		       int seed=675;
//		       for(int i=1;i<=7;i++){
//		    	   String s="E:/data_contest/ChinaUnicomContest/复赛数据";
//		    	   seed+=1234;
//		    	    pp. prePareDataInOneDayPlus4(s+"/testData12_"+i+".csv", 1, seed);
//		       }
//		     
		      
		       
//		       pp.prepareData(30);
//		       System.out.println("正确率  ：" +basicTest.size()+"   "+direcRecCor+"正确率   ："+(double) direcRecCor/(double)basicTest.size());
//		       System.out.println("直接推荐正确率  ：" +directRec+"   "+direcRecCor);
//		       pp.recomandTest();
//		       pp.readXgbData(xgbResultData);
//		       pp.getRecomandShop(basicTest);
		
//		       pp.prePreTest();
//		       pp.prePreTestPlus();
//		       pp.prePareTestForModel5();
//		       pp.prePareTestData6();
//		       pp.prePreTestPlus4(testOnline5);
//		       pp.prePreTestPlus4(testOnline6);
//		       pp.prePreTestPlus4(testOnline7);
//		       pp.prePreTestPlus4(testOnline8);
//		       notrace.flush();
//		       notrace.close();
		Random random=new Random(45649);
		System.out.println(random.nextInt(7));
		System.out.println(random.nextInt(7));
		System.out.println(random.nextInt(7));
		System.out.println(random.nextInt(7));
		       
		      

	}
	//每个用户抽取一个
//	public void prePareDataInOne(String filesave,int n,int seed) throws IOException
//	{
//		 allData=readTrainDataAll(FILETRAINDATA2);
//		 shopMap=(TreeMap<Integer, Shop>) readShop(FILEPATHSHOPPROFILE2)[1];
//		 FileWriter fw=new FileWriter(filesave);
//		 fw.write("userid,arrTime,dbu,dbs,traceNum,shopHeat,shopHeatPro,shopHisLabelcount,shopHisLabPro,useHisShopCou,useHisClaCou,userHisNum,useHisShopPro,useHisClassPro,label,shopid"+"\n");
//		 while(n-->0)
//		 {
//			 seed+=n*111;
//			 initailData(seed);
//			 TreeMap<Train,ArrayList<MyMap<Train,Double>>> userSim=user_trace_Sim(trainData,basicTest,0,d);
//			 for(Map.Entry<Train, ArrayList<MyMap<Train,Double>>> cas:userSim.entrySet())
//			 {
//				 writeData(fw,cas.getKey(),cas.getValue());
//			 }
//			
//		 }
//		 fw.flush();fw.close();
//	}
//	
//	public void prePareDataInOne2(String filesave,int n,int seed) throws IOException
//	{
//		 allData=readTrainDataAll(FILETRAINDATA2);
//		 shopMap=(TreeMap<Integer, Shop>) readShop(FILEPATHSHOPPROFILE2)[1];
//		 FileWriter fw=new FileWriter(filesave);
//		 fw.write("userid,arrTime,dbu,simbu1,simbu2,simbu3,simbu4,simbu5,dbs,traceNum,shopHeat,shopHeatPro,shopHisLabelcount,shopHisLabPro,useHisShopCou,useHisClaCou,userHisNum,useHisShopPro,useHisClassPro,shopid"+"\n");
//		 while(n-->0)
//		 {
//			 seed+=n*111;
//			 initailData(seed);
//			 TreeMap<Train,ArrayList<MyMap<Train,Double>>> userSim=user_trace_Sim(trainData,basicTest,0,d);
//			 for(Map.Entry<Train, ArrayList<MyMap<Train,Double>>> cas:userSim.entrySet())
//			 {
//				 writeDataPlus(fw,cas.getKey(),cas.getValue());
//			 }
//			
//		 }
//		 fw.flush();fw.close();
//	}
	//每周抽取一次
	public void prePareDataInOneDayPlus(String fileSave,int n,int seed) throws IOException, ParseException{
		 allData=readTrainDataAll(FILETRAINDATA2);
		 shopMap=(TreeMap<Integer, Shop>) readShop(FILEPATHSHOPPROFILE2)[1];
		 FileWriter fw=new FileWriter(fileSave);
		 fw.write("userid,arrTime,dbu,simbu1,simbu2,simbu3,simbu4,simbu5,dbs,traceNum,shopHeat,shopHeatPro,shopHisLabelcount,shopHisLabPro,useHisShopCou,useHisClaCou,userHisNum,useHisShopPro,useHisClassPro,label,shopid"+"\n");
		 while(n-->0)
		 {
			 seed+=n*554;
			 initailDataInOneDay(seed);
			 TreeMap<Train,ArrayList<MyMap<Train,Double>>> userSim=user_trace_Sim(trainData,basicTest,0,d);
			 testUserSim(userSim);
			 for(Map.Entry<Train, ArrayList<MyMap<Train,Double>>> cas:userSim.entrySet())
			 {
				 writeDataPlus(fw,cas.getKey(),cas.getValue());
			 }
			
		 }
		 fw.flush();fw.close();
	}
	public void prePareDataInOneDayPlus2(String fileSave,int n,int seed) throws IOException, ParseException{
		 allData=readTrainDataAll(FILETRAINDATA2);
		 shopMap=(TreeMap<Integer, Shop>) readShop(FILEPATHSHOPPROFILE2)[1];
		 FileWriter fw=new FileWriter(fileSave);
		 fw.write("userid,arrTime,dbu,dbs,"+writeName(5,"userLabel")+writeName(5,"userHis")+writeName(10,"userAtriHis")
		 +writeName(2,"neiRec")+writeName(2,"shopHis")+writeName(5,"simbu")+"neiNum,class,label,shopid"+"\n");
		 while(n-->0)
		 {
			 seed+=n*389;
			 initailDataInOneDay(seed);
			 TreeMap<Train,ArrayList<MyMap<Train,Double>>> userSim=user_trace_Sim(trainData,basicTest,0,d);
			 testUserSim(userSim);
			 for(Map.Entry<Train, ArrayList<MyMap<Train,Double>>> cas:userSim.entrySet())
			 {
				 writeDataPlus2(fw,cas.getKey(),cas.getValue());
			 }
		 }	
		 
		 fw.flush();fw.close();
	}
	public void prePareDataInOneDayPlus3(String fileSave,int n,int seed) throws IOException, ParseException{
		 allData=readTrainDataAll(FILETRAINDATA2);
		 shopMap=(TreeMap<Integer, Shop>) readShop(FILEPATHSHOPPROFILE2)[1];
		 FileWriter fw=new FileWriter(fileSave);
		 fw.write("userid,arrTime,dbu,dbs,"+writeName(5,"userLabel")+writeName(5,"userHis") +writeName(2,"neiRec")+
				 writeName(2,"shopHis")+writeName(5,"simbu")+"neiNum,class,isNotAppear,label,shopid"+"\n");
		 while(n-->0)
		 {
			 seed+=n*141;
			 initailDataInOneDay(seed);
			 TreeMap<Train,ArrayList<MyMap<Train,Double>>> userSim=user_trace_Sim(trainData,basicTest,0,d);
			 testUserSim(userSim);
			 for(Map.Entry<Train, ArrayList<MyMap<Train,Double>>> cas:userSim.entrySet())
			 {
				 writeDataPlus3(fw,cas.getKey(),cas.getValue());
			 }
		 }	
		 
		 fw.flush();fw.close();
	}
	//加了时间信息
	public void prePareDataInOneDayPlus4(String fileSave,int n,int seed) throws IOException, ParseException{
		 allData=readTrainDataAll(FILETRAINDATA2);
		 shopMap=(TreeMap<Integer, Shop>) readShop(FILEPATHSHOPPROFILE2)[1];
		 FileWriter fw=new FileWriter(fileSave);
		 fw.write("userid,arrTime,dbu,dbs,"+writeName(5,"userLabel")+writeName(5,"userHis") +writeName(2,"neiRec")+
				 writeName(2,"shopHis")+writeName(5,"simbu")+"neiNum,class,isNotAppear,isSameUser,"+writeName(3,"userDate")+
				 writeName(3,"neiDate")+writeName(3,"dateDiff")+"duraDiff,user_dur,trace_dur,label,shopid"+"\n");
		 while(n-->0)
		 {
			 seed+=n*232;
			 initailDataInOneDay(seed);
			 TreeMap<Train,ArrayList<MyMap<Train,Double>>> userSim=user_trace_Sim(trainData,basicTest,0,d);
			 testUserSim(userSim);
			 for(Map.Entry<Train, ArrayList<MyMap<Train,Double>>> cas:userSim.entrySet())
			 {
				 writeDataPlus4(fw,cas.getKey(),cas.getValue());
			 }
		 }	
		 
		 fw.flush();fw.close();
	}
	//在每周抽取一个值
	public void initailDataInOneDay(int seed) throws ParseException{
		Random rand=new Random(seed);
		trainData=new ArrayList<Train>();
		basicTest=new ArrayList<Train>();
		for(Train tr:allData){
			Date dat=new SimpleDateFormat("yyyyMMddHHmmss").parse(tr.getArrival_time());
			int week =getWeek(dat.getDate());
			if(week==rand.nextInt(7)+1){
				basicTest.add(tr);
			}
			else trainData.add(tr);
			
		}
		for(Train tr:trainData){
			
			UserPos up=new UserPos(tr.getUser_id(),tr.getUserLongitude(), tr.getUserLatitude());
			Pos p=new Pos(tr.getUserLongitude(), tr.getUserLatitude());
			//如果该地点存在
			//初始化PosHisClass
			if(existInUserProHisClass(PosHisClass.keySet(),p)){
				TreeMap<Integer,Integer> av=PosHisClass.get(p);
				//如果存在该类别
				if(av.keySet().contains(tr.getClassification()))
				    av.put(tr.getClassification(),av.get(tr.getClassification())+1);
				//如果不存在该类别，直接赋值1
				else
					av.put(tr.getClassification(),1);
			
				PosHisClass.put(p, av);
			}
			//如果该地点不存在
			else{
				TreeMap<Integer,Integer> av=new TreeMap<Integer,Integer>();
				av.put(tr.getClassification(),1);
				PosHisClass.put(p, av);
			}
			//初始化userPosHisClass
			if(existInProHis(userPosHisClass.keySet(),up)){
				ArrayList<Integer> al=userPosHisClass.get(up);
				al.add(tr.getClassification());
				userPosHisClass.put(up, al);
			}
			else{
				ArrayList<Integer> al=new ArrayList<Integer>();
				al.add(tr.getClassification());
				userPosHisClass.put(up, al);
			}
		}
		
		System.out.println("测试集数据数目        ："+basicTest.size());
		System.out.println("训练集数据数目        ："+trainData.size());
	}
	public void  initial()
	{
		for(Train tr:trainData){
					
					UserPos up=new UserPos(tr.getUser_id(),tr.getUserLongitude(), tr.getUserLatitude());
					Pos p=new Pos(tr.getUserLongitude(), tr.getUserLatitude());
					//如果该地点存在
					//初始化PosHisClass
					if(existInUserProHisClass(PosHisClass.keySet(),p)){
						TreeMap<Integer,Integer> av=PosHisClass.get(p);
						//如果存在该类别
						if(av.keySet().contains(tr.getClassification()))
						    av.put(tr.getClassification(),av.get(tr.getClassification())+1);
						//如果不存在该类别，直接赋值1
						else
							av.put(tr.getClassification(),1);
					
						PosHisClass.put(p, av);
					}
					//如果该地点不存在
					else{
						TreeMap<Integer,Integer> av=new TreeMap<Integer,Integer>();
						av.put(tr.getClassification(),1);
						PosHisClass.put(p, av);
					}
					//初始化userPosHisClass
					if(existInProHis(userPosHisClass.keySet(),up)){
						ArrayList<Integer> al=userPosHisClass.get(up);
						al.add(tr.getClassification());
						userPosHisClass.put(up, al);
					}
					else{
						ArrayList<Integer> al=new ArrayList<Integer>();
						al.add(tr.getClassification());
						userPosHisClass.put(up, al);
					}
				}
	}
	public boolean existInProHis(Set<UserPos> ups,UserPos up){
		for(UserPos userP:ups){
			if(userP.userid==up.userid && userP.user_lat==up.user_lat&& userP.user_lon==up.user_lon)
				return true;
		}
		return false;
	}
	public boolean existInUserProHisClass(Set<Pos> sp,Pos p){
		for(Pos ps:sp){
			if(ps.user_lat==p.user_lat && ps.user_lon==p.user_lon) return true;
		}
		return false;
	}
	//每个用户抽取一个
//	public void initailData(int seed) {
//		
//		Random rand=new Random(seed);
//		trainData=new ArrayList<Train>();
//		basicTest=new ArrayList<Train>();
//		
//		TreeMap<Integer ,ArrayList<Train>> traces=new TreeMap<Integer,ArrayList<Train>>();
//		for(Train tr:allData){
//			if(traces.keySet().contains(tr.getUser_id())){
//				ArrayList<Train> trace=traces.get(tr.getUser_id());
//				trace.add(tr);
//				traces.put(tr.getUser_id(), trace);
//			}
//			else{
//				ArrayList<Train> trace=new ArrayList<Train>();
//				trace.add(tr);
//				traces.put(tr.getUser_id(), trace);
//			}
//		}
//		
//		for(Map.Entry<Integer, ArrayList<Train>> map:traces.entrySet()){
//			int size=map.getValue().size();
//			if(size<=1) {
//				trainData.addAll(map.getValue());
//				continue;
//			}
//			int j=rand.nextInt(size);
//			basicTest.add(map.getValue().get(j));
//			map.getValue().remove(j);
//			trainData.addAll(map.getValue());
//			
////			for(int index=0;index<size;index++){
////				if(index==j)	basicTest.add(map.getValue().get(index));
////				else            trainData.add(map.getValue().get(index));
////			}
//		}
//		System.out.println("测试集数据数目        ："+basicTest.size());
//		System.out.println("训练集数据数目        ："+trainData.size());
//		
//	}
//    
	//读取线上测试数据
	public void prePreTest() throws IOException{
		FileWriter fw=new FileWriter(testOnLine);
		fw.write("userid,arrTime,dbu,dbs,traceNum,shopHeat,shopHeatPro,shopHisLabelcount,shopHisLabPro,useHisShopCou,useHisClaCou,userHisNum,useHisShopPro,useHisClassPro,shopid"+"\n");
		trainData=readTrainDataAll(FILETRAINDATA2);
		shopMap=(TreeMap<Integer, Shop>) readShop(FILEPATHSHOPPROFILE2)[1];
		basicTest=readTestData(FILETEST2);
		System.out.println("测试集数量 ：" +basicTest.size());
		TreeMap<Train,ArrayList<MyMap<Train,Double>>> userSim=user_trace_Sim(trainData,basicTest,0,d);
		for(Map.Entry<Train, ArrayList<MyMap<Train,Double>>> cas:userSim.entrySet())
		 {
			 writeTetstData(fw,cas.getKey(),cas.getValue());
		 }
		fw.flush();fw.close();
	}
	//读取线上数据后，加上用户相似度
	public void prePreTestPlus() throws IOException{
		FileWriter fw=new FileWriter(testOnline2);
		fw.write("userid,arrTime,dbu,simbu1,simbu2,simbu3,simbu4,simbu5,dbs,traceNum,shopHeat,shopHeatPro,shopHisLabelcount,shopHisLabPro,useHisShopCou,useHisClaCou,userHisNum,useHisShopPro,useHisClassPro,shopid"+"\n");
		trainData=readTrainDataAll(FILETRAINDATA2);
		shopMap=(TreeMap<Integer, Shop>) readShop(FILEPATHSHOPPROFILE2)[1];
		basicTest=readTestData(FILETEST2);
		 System.out.println("测试集数量 ：" +basicTest.size());
			TreeMap<Train,ArrayList<MyMap<Train,Double>>> userSim=user_trace_Sim(trainData,basicTest,0,d);
		for(Map.Entry<Train, ArrayList<MyMap<Train,Double>>> cas:userSim.entrySet())
		 {
			 writeTetstDataPlus(fw,cas.getKey(),cas.getValue());
		 }
		fw.flush();fw.close();
	}
	
	public void prePareTestForModel5() throws IOException{
		 FileWriter fw=new FileWriter(testOnline3);
		 fw.write("userid,arrTime,dbu,dbs,"+writeName(5,"userLabel")+writeName(2,"userHis")+writeName(10,"userAtriHis")
		 +writeName(2,"neiRec")+writeName(2,"shopHis")+writeName(5,"simbu")+"neiNum,class,shopid"+"\n");
		    trainData=readTrainDataAll(FILETRAINDATA2);
			shopMap=(TreeMap<Integer, Shop>) readShop(FILEPATHSHOPPROFILE2)[1];
			basicTest=readTestData(FILETEST2);
			 System.out.println("测试集数量 ：" +basicTest.size());
				TreeMap<Train,ArrayList<MyMap<Train,Double>>> userSim=user_trace_Sim(trainData,basicTest,0,d);
				for(Map.Entry<Train, ArrayList<MyMap<Train,Double>>> cas:userSim.entrySet())
				 {
					 writeTetstDataPlus2(fw,cas.getKey(),cas.getValue());
				 }
				fw.flush();fw.close();
		
	}
	public void prePareTestData6() throws IOException{
		 FileWriter fw=new FileWriter(testOnline4);
		 fw.write("userid,arrTime,dbu,dbs,"+writeName(5,"userLabel")+writeName(5,"userHis") +writeName(2,"neiRec")+
				 writeName(2,"shopHis")+writeName(5,"simbu")+"neiNum,class,isNotAppear,shopid"+"\n");
		    trainData=readTrainDataAll(FILETRAINDATA2);
			shopMap=(TreeMap<Integer, Shop>) readShop(FILEPATHSHOPPROFILE2)[1];
			basicTest=readTestData(FILETEST2);
			 System.out.println("测试集数量 ：" +basicTest.size());
				TreeMap<Train,ArrayList<MyMap<Train,Double>>> userSim=user_trace_Sim(trainData,basicTest,0,d);
				for(Map.Entry<Train, ArrayList<MyMap<Train,Double>>> cas:userSim.entrySet())
				 {
					writeDataPlus3(fw,cas.getKey(),cas.getValue());
				 }
				fw.flush();fw.close();
	}
	public void prePreTestPlus3() throws IOException{
		 FileWriter fw=new FileWriter(testOnline3);
		 fw.write("userid,arrTime,dbu,dbs,"+writeName(5,"userLabel")+writeName(5,"userHis") +writeName(2,"neiRec")+
				 writeName(2,"shopHis")+writeName(5,"simbu")+"neiNum,class,isNotAppear,shopid"+"\n");
		    trainData=readTrainDataAll(FILETRAINDATA2);
			shopMap=(TreeMap<Integer, Shop>) readShop(FILEPATHSHOPPROFILE2)[1];
			basicTest=readTestData(FILETEST2);
			 System.out.println("测试集数量 ：" +basicTest.size());
				TreeMap<Train,ArrayList<MyMap<Train,Double>>> userSim=user_trace_Sim(trainData,basicTest,0,d);
				for(Map.Entry<Train, ArrayList<MyMap<Train,Double>>> cas:userSim.entrySet())
				 {
					 writeTetstDataPlus2(fw,cas.getKey(),cas.getValue());
				 }
				fw.flush();fw.close();
		
	}
	//读取线上数据
	public void prePreTestPlus4(String testOnline) throws IOException, ParseException{
		 FileWriter fw=new FileWriter(testOnline);
		 fw.write("userid,arrTime,dbu,dbs,"+writeName(5,"userLabel")+writeName(5,"userHis") +writeName(2,"neiRec")+
				 writeName(2,"shopHis")+writeName(5,"simbu")+"neiNum,class,isNotAppear,isSameUser,"+writeName(3,"userDate")+
				 writeName(3,"neiDate")+writeName(3,"dateDiff")+"dura_Diff,user_dur,nei_dur,shopid"+"\n");
		    trainData=readTrainDataAll(FILETRAINDATA2);
			shopMap=(TreeMap<Integer, Shop>) readShop(FILEPATHSHOPPROFILE2)[1];
			basicTest=readTestData(FILETEST2);
			//初始化
			initial();
			 System.out.println("测试集数量 ：" +basicTest.size());
				TreeMap<Train,ArrayList<MyMap<Train,Double>>> userSim=user_trace_Sim(trainData,basicTest,0,d);
				for(Map.Entry<Train, ArrayList<MyMap<Train,Double>>> cas:userSim.entrySet())
				 {
					writeDataPlus4(fw,cas.getKey(),cas.getValue());
				 }
				fw.flush();fw.close();
		
	}
	/*
	 * prepareData：文件路径
	 * n,采取多次采样
	 * seed： 种子
	 * */
	public void prePareData(String prepareData,int n,int seed) throws IOException
	{
		
		FileWriter fw=new FileWriter(prepareData);
		fw.write("userid,arrTime,dbu,dbs,traceNum,shopHeat,shopHeatPro,shopHisLabelcount,shopHisLabPro,useHisShopCou,useHisClaCou,userHisNum,useHisShopPro,useHisClassPro,label,shopid"+"\n");
		while(n-->0){
			seed+=n*140;
			prepareData(fw,seed);
		}
		 fw.flush();fw.close();
	}
	/*
	 * 加上了用户相似度
	 * prepareData：文件路径
	 * n,采取多次采样
	 * seed： 种子
	 * */
	public void prePareDataPlus(String prepareData,int n,int seed) throws IOException{
		FileWriter fw=new FileWriter(prepareData);
		fw.write("userid,arrTime,dbu,simbu1,simbu2,simbu3,simbu4,simbu5,dbs,traceNum,shopHeat,shopHeatPro,shopHisLabelcount,shopHisLabPro,useHisShopCou,useHisClaCou,userHisNum,useHisShopPro,useHisClassPro,label,shopid"+"\n");
		while(n-->0){
			seed+=n*100;
			prepareDataPlus(fw,seed);
		}
		 fw.flush();fw.close();
	}
	public void  prepareData( FileWriter fw, int seed) throws IOException
	{
		
		Object[] allData=readTrainData(FILETRAINDATA2,10,seed);
	      basicTest=(ArrayList<Train>) allData[1];
	      System.out.println("测试集数量 ：" +basicTest.size());
		  trainData=(ArrayList<Train>) allData[0];
		  shopMap=(TreeMap<Integer, Shop>) readShop(FILEPATHSHOPPROFILE2)[1];
	 TreeMap<Train,ArrayList<MyMap<Train,Double>>> userSim=user_trace_Sim(trainData,basicTest,0,d);
	 for(Map.Entry<Train, ArrayList<MyMap<Train,Double>>> cas:userSim.entrySet())
	  {
		 writeData(fw,cas.getKey(),cas.getValue());
	  }
	}
	//加上了用户相似度
	public void  prepareDataPlus( FileWriter fw, int seed) throws IOException
	{
		
		Object[] allData=readTrainData(FILETRAINDATA2,10,seed);
	      basicTest=(ArrayList<Train>) allData[1];
	      System.out.println("测试集数量 ：" +basicTest.size());
		  trainData=(ArrayList<Train>) allData[0];
		  shopMap=(TreeMap<Integer, Shop>) readShop(FILEPATHSHOPPROFILE2)[1];
	 TreeMap<Train,ArrayList<MyMap<Train,Double>>> userSim=user_trace_Sim(trainData,basicTest,0,d);
	 for(Map.Entry<Train, ArrayList<MyMap<Train,Double>>> cas:userSim.entrySet())
	  {
		 writeDataPlus(fw,cas.getKey(),cas.getValue());
	  }
	}
	
	
	public void writeTetstData(FileWriter fw,Train test,ArrayList<MyMap<Train,Double>> arr) throws IOException
	{
		ArrayList<String> shopes=new ArrayList<String>();
		StringBuilder sb=new StringBuilder();
		int size=arr.size();
		for(MyMap<Train,Double> neibor: arr)
		{  
			if(shopes.contains(neibor.getKey().getShop_id())) continue;
			else shopes.add(neibor.getKey().getShop_id());
			
			double[] userHis=userHisCharact(test,neibor.getKey());
			//如果用户在该商户以及该商户所对应的类别中的历史行为为0
//			if(userHis[3]==0 && userHis[4]==0) continue;
			
			double dbu=calDistance1(test,neibor.getKey());
			double dbs=neibor.getValue();
			double[] neiborRec=neiborRec(neibor.getKey(),arr);
			double[] shopHis=shopHisCharac(test,neibor.getKey());
//			int label=test.getShop_id().equals(neibor.getKey().getShop_id()) ? 1:0;
			
			sb.append(test.getUser_id()+","+test.getArrival_time()+","+dbu+","+dbs+","+size+","+neiborRec[0]+","+neiborRec[1]+","+
			shopHis[0]+","+shopHis[1]+","+userHis[0]+","+userHis[1]+","+userHis[2]+","+userHis[3]+","+userHis[4]+","+neibor.getKey().getShop_id()+"\n");
			
		}
		fw.write(sb.toString());
	}
	//读取线上数据，加上用户相似度
	public void writeTetstDataPlus(FileWriter fw,Train test,ArrayList<MyMap<Train,Double>> arr) throws IOException
	{
		ArrayList<String> shopes=new ArrayList<String>();
		StringBuilder sb=new StringBuilder();
		int size=arr.size();
		for(MyMap<Train,Double> neibor: arr)
		{  
			if(shopes.contains(neibor.getKey().getShop_id())) continue;
			else shopes.add(neibor.getKey().getShop_id());
			
			double[] userHis=userHisCharact(test,neibor.getKey());
			//如果用户在该商户以及该商户所对应的类别中的历史行为为0
//			if(userHis[3]==0 && userHis[4]==0) continue;
			double[] simbu=simBetweenUsers(test,neibor.getKey());
			double dbu=calDistance1(test,neibor.getKey());
			double dbs=neibor.getValue();
			double[] neiborRec=neiborRec(neibor.getKey(),arr);
			double[] shopHis=shopHisCharac(test,neibor.getKey());
//			int label=test.getShop_id().equals(neibor.getKey().getShop_id()) ? 1:0;
	sb.append(test.getUser_id()+","+test.getArrival_time()+","+dbu+","+simbu[0]+","+simbu[1]+","+simbu[2]+","+simbu[3]+","+simbu[4]+
			   ","+dbs+","+size+","+neiborRec[0]+","+neiborRec[1]+","+	shopHis[0]+","+shopHis[1]+","+userHis[0]+","+userHis[1]+","+
				userHis[2]+","+userHis[3]+","+userHis[4]+","+neibor.getKey().getShop_id()+"\n");
			
			
		}
		fw.write(sb.toString());
	}
	//读取线上数据，加上用户相似度
		public void writeTetstDataPlus2(FileWriter fw,Train test,ArrayList<MyMap<Train,Double>> arr) throws IOException
		{
			ArrayList<String> shopes=new ArrayList<String>();
			StringBuilder sb=new StringBuilder();
			int size=arr.size();
			for(MyMap<Train,Double> neibor: arr)
			{  
				if(shopes.contains(neibor.getKey().getShop_id())) continue;
				else shopes.add(neibor.getKey().getShop_id());
				
				Train nei=neibor.getKey();
				double dbu=calDistance1(test,nei);
				double dbs=neibor.getValue();
				//用户在历史行为
				double[] userHis=userHisCharact(test,nei);
				//如果用户在该商户以及该商户所对应的类别中的历史行为为0
//				if(userHis[3]==0 && userHis[4]==0) continue;							
				//该位置的历史类别信息
//				double[] posHis=posHis(test);
				//用户在该地点的历史类别信息
//				double[] uphis=userHisClass(test);
				//关于用户标签的历史类别信息

				
				
				double[] neiborRec=neiborRec(nei,arr);
				double[] shopHis=shopHisCharac(test,nei);
				double[] simbu=simBetweenUsers(test,nei);
				int classfication=nei.getClassification();
//				int label = test.getShop_id().equals(nei.getShop_id()) ? 1:0;
				
				sb.append(test.getUser_id()+","+test.getArrival_time()+","+dbu+","+dbs+","+test.getIncome()+","+test.getEntermament()+","+test.getBaby()+","+
				     test.getGender()+","+test.getShopping()+","+writeInfo(userHis)+writeInfo(neiborRec)+writeInfo(shopHis)+writeInfo(simbu)+
				     size+","+classfication+","+neibor.getKey().getShop_id()+"\n");
			}
			fw.write(sb.toString());
		}
	public void writeData(FileWriter fw,Train test,ArrayList<MyMap<Train,Double>> arr) throws IOException
	{
		ArrayList<String> shopes=new ArrayList<String>();
		StringBuilder sb=new StringBuilder();
		int size=arr.size();
		for(MyMap<Train,Double> neibor: arr)
		{  
			if(shopes.contains(neibor.getKey().getShop_id())) continue;
			else shopes.add(neibor.getKey().getShop_id());
			
			double[] userHis=userHisCharact(test,neibor.getKey());
			//如果用户在该商户以及该商户所对应的类别中的历史行为为0
//			if(userHis[3]==0 && userHis[4]==0) continue;
			
			double dbu=calDistance1(test,neibor.getKey());
			double dbs=neibor.getValue();
			double[] neiborRec=neiborRec(neibor.getKey(),arr);
			double[] shopHis=shopHisCharac(test,neibor.getKey());
			int label=test.getShop_id().equals(neibor.getKey().getShop_id()) ? 1:0;
			
			sb.append(test.getUser_id()+","+test.getArrival_time()+","+dbu+","+dbs+","+size+","+neiborRec[0]+","+neiborRec[1]+","+
			shopHis[0]+","+shopHis[1]+","+userHis[0]+","+userHis[1]+","+userHis[2]+","+userHis[3]+","+userHis[4]+","+label+","+neibor.getKey().getShop_id()+"\n");
			
		}
		fw.write(sb.toString());
	}
	//加上了用户相似度
	public void writeDataPlus(FileWriter fw,Train test,ArrayList<MyMap<Train,Double>> arr) throws IOException
	{
		ArrayList<String> shopes=new ArrayList<String>();
		StringBuilder sb=new StringBuilder();
		int size=arr.size();
		for(MyMap<Train,Double> neibor: arr)
		{  
			if(shopes.contains(neibor.getKey().getShop_id())) continue;
			else shopes.add(neibor.getKey().getShop_id());
			
			double[] userHis=userHisCharact(test,neibor.getKey());
			//如果用户在该商户以及该商户所对应的类别中的历史行为为0
//			if(userHis[3]==0 && userHis[4]==0) continue;
			
			double dbu=calDistance1(test,neibor.getKey());
			double dbs=neibor.getValue();
			double[] neiborRec=neiborRec(neibor.getKey(),arr);
			double[] shopHis=shopHisCharac(test,neibor.getKey());
			double[] simbu=simBetweenUsers(test,neibor.getKey());
			int label=test.getShop_id().equals(neibor.getKey().getShop_id()) ? 1:0;
			
			sb.append(test.getUser_id()+","+test.getArrival_time()+","+dbu+","+simbu[0]+","+simbu[1]+","+simbu[2]+","+simbu[3]+","+simbu[4]+
					  ","+dbs+","+size+","+neiborRec[0]+","+neiborRec[1]+","+	shopHis[0]+","+shopHis[1]+","+userHis[0]+","+userHis[1]+","+
				 	  userHis[2]+","+userHis[3]+","+userHis[4]+","+label+","+neibor.getKey().getShop_id()+"\n");
			
		}
		fw.write(sb.toString());
	}
	//加上所有提炼出来的信息
	public void writeDataPlus2(FileWriter fw,Train test,ArrayList<MyMap<Train,Double>> arr) throws IOException
	{
		ArrayList<String> shopes=new ArrayList<String>();
		StringBuilder sb=new StringBuilder();
		int size=arr.size();
		for(MyMap<Train,Double> neibor: arr)
		{  
			if(shopes.contains(neibor.getKey().getShop_id())) continue;
			else shopes.add(neibor.getKey().getShop_id());
			Train nei=neibor.getKey();
			double dbu=calDistance1(test,nei);
			double dbs=neibor.getValue();
			//用户在历史行为
			double[] userHis=userHisCharact(test,nei);
			//如果用户在该商户以及该商户所对应的类别中的历史行为为0
//			if(userHis[3]==0 && userHis[4]==0) continue;			
			double[] neiborRec=neiborRec(nei,arr);
			double[] shopHis=shopHisCharac(test,nei);
			double[] simbu=simBetweenUsers(test,nei);
			int classfication=nei.getClassification();
			int label = test.getShop_id().equals(nei.getShop_id()) ? 1:0;
			
			sb.append(test.getUser_id()+","+test.getArrival_time()+","+dbu+","+dbs+","+test.getIncome()+","+test.getEntermament()+","+test.getBaby()+","+
			     test.getGender()+","+test.getShopping()+","+writeInfo(userHis)+writeInfo(neiborRec)+writeInfo(shopHis)+writeInfo(simbu)+
			     size+","+classfication+","+label+","+neibor.getKey().getShop_id()+"\n");
			
		}
		fw.write(sb.toString());
	}
	//
	public void writeDataPlus3(FileWriter fw,Train test,ArrayList<MyMap<Train,Double>> arr) throws IOException
	{
		ArrayList<String> shopes=new ArrayList<String>();
		StringBuilder sb=new StringBuilder();
		int size=arr.size();
		for(MyMap<Train,Double> neibor: arr)
		{  
			if(shopes.contains(neibor.getKey().getShop_id())) continue;
			else shopes.add(neibor.getKey().getShop_id());
			Train nei=neibor.getKey();
			double dbu=calDistance1(test,nei);
			double dbs=neibor.getValue();
			//用户在历史行为
			double[] userHis=userHisCharact(test,nei);
			//如果用户在该商户以及该商户所对应的类别中的历史行为为0
//			if(userHis[3]==0 && userHis[4]==0) continue;			
			double[] neiborRec=neiborRec(nei,arr);
			double[] shopHis=shopHisCharac(test,nei);
			double[] simbu=simBetweenUsers(test,nei);
			
			int classfication=nei.getClassification();
			int isNotAppear=isNotAppearHere(classfication,test) ? 1:0;
			int label = test.getShop_id().equals(nei.getShop_id()) ? 1:0;
			
			sb.append(test.getUser_id()+","+test.getArrival_time()+","+dbu+","+dbs+","+test.getIncome()+","+test.getEntermament()+","+test.getBaby()+","+
			     test.getGender()+","+test.getShopping()+","+writeInfo(userHis)+writeInfo(neiborRec)+writeInfo(shopHis)+writeInfo(simbu)+
			     size+","+classfication+","+isNotAppear+","+label+","+neibor.getKey().getShop_id()+"\n");
			
		}
		fw.write(sb.toString());
	}
	//加入新的特征model9
	public void writeDataPlus4(FileWriter fw,Train test,ArrayList<MyMap<Train,Double>> arr) throws IOException, ParseException
	{
		ArrayList<String> shopes=new ArrayList<String>();
		StringBuilder sb=new StringBuilder();
		int size=arr.size();
		for(MyMap<Train,Double> neibor: arr)
		{  
			if(shopes.contains(neibor.getKey().getShop_id())) continue;
			else shopes.add(neibor.getKey().getShop_id());
			Train nei=neibor.getKey();
			double dbu=calDistance1(test,nei);
			double dbs=neibor.getValue();
			//用户在历史行为
			double[] userHis=userHisCharact(test,nei);
			//如果用户在该商户以及该商户所对应的类别中的历史行为为0
//			if(userHis[3]==0 && userHis[4]==0) continue;			
			double[] neiborRec=neiborRec(nei,arr);
			double[] shopHis=shopHisCharac(test,nei);
			double[] simbu=simBetweenUsers(test,nei);
			
			int classfication=nei.getClassification();
			int isNotAppear=isNotAppearHere(classfication,test) ? 1:0;
			//用户轨迹是否是同一个用户
			int isSameUser=test.getUser_id()==nei.getUser_id() ?1:0;
			double [] userDate=getDateInfo(test);
			double [] neiDate=getDateInfo(nei);
			double [] dateDiff=dataDiff(test,nei);
			double user_dur=test.getDuration();
			double nei_dur=nei.getDuration();
			
//			int label = test.getShop_id().equals(nei.getShop_id()) ? 1:0;
			double duraDiff=Math.abs(test.getDuration()-nei.getDuration());
			
sb.append(test.getUser_id()+","+test.getArrival_time()+","+dbu+","+dbs+","+test.getIncome()+","+
test.getEntermament()+","+test.getBaby()+","+test.getGender()+","+test.getShopping()+","+writeInfo(userHis)
+writeInfo(neiborRec)+writeInfo(shopHis)+writeInfo(simbu)+size+","+classfication+","+isNotAppear+","+isSameUser
+","+writeInfo(userDate)+writeInfo(neiDate)+writeInfo(dateDiff)+duraDiff+","+user_dur+","+nei_dur+","+neibor.getKey().getShop_id()+"\n");
		}
		fw.write(sb.toString());
	}
	public String writeName(int len,String name){
		StringBuilder sb=new StringBuilder();
		int count=1;
		while(count<=len){
			sb.append(name+count+",");
			count++;
		}
		return sb.toString();
	}
	public String writeInfo(double[] info){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<info.length;i++){
			sb.append(info[i]+",");
		}
		return sb.toString();
		
	}
	public double [] getDateInfo(Train tr) throws ParseException
	{
		Date day=new SimpleDateFormat("yyyyMMddHHmmss").parse(tr.getArrival_time());
		double week=getWeek(day.getDate());
		double hour=day.getHours();
		double area=getArea((int)hour);
		return new double[]{week,hour,area};
	}
	//提取日期差距信息
	public double[] dataDiff(Train user,Train nei) throws ParseException
	{
		Date userDat=new SimpleDateFormat("yyyyMMddHHmmss").parse(user.getArrival_time());
		Date neiDat=new SimpleDateFormat("yyyyMMddHHmmss").parse(nei.getArrival_time());
		double weekDiff=Math.abs(getWeek(userDat.getDate())-getWeek(neiDat.getDate()));
		double hourDiff=Math.abs(userDat.getHours()-neiDat.getHours());
		double areaDiff=Math.abs(getArea(userDat.getHours())-getArea(neiDat.getHours()));
		return new double[]{weekDiff,hourDiff,areaDiff};
	}
	private double calBisBeUsers(Train test, Train key) {
		// TODO Auto-generated method stub
		double shop=Math.sqrt(Math.pow(key.getShopLatitude(), 2)+Math.pow(key.getShopLongitude(),2));
		double user=Math.sqrt(Math.pow(test.getUserLatitude(), 2)+Math.pow(test.getUserLongitude(),2));
		return  shop-user;
	}
	
	public double[] simBetweenUsers(Train user1,Train user2 ){
	    double [] simbu=new double[5];
		simbu[0]=Math.abs(user1.getIncome()-user2.getIncome());
		simbu[1]=Math.abs(user1.getEntermament()-user2.getEntermament());
		simbu[2]=Math.abs(user1.getGender()-user2.getGender());
		simbu[3]=Math.abs(user1.getBaby()-user2.getBaby());
		simbu[4]=Math.abs(user1.getShopping()-user2.getShopping());
		
		return simbu;
	}
	public double[] neiborRec(Train shop,ArrayList<MyMap<Train,Double>> arr)
	{
		int shopHeat=0;
		for(MyMap<Train,Double> neibor: arr)
		{
			if(neibor.getKey().getShop_id().equals(shop.getShop_id())){
				shopHeat++;
			}
			
		}
		return new double[]{shopHeat,(double)shopHeat/(double)arr.size()};
	}
	public double [] shopHisCharac(Train user,Train shop)
	{
		int shopHisNum=0;
		int shopUserLabelRec=0;
		int shopheat=0;
		for(Train tr:trainData)
		{
			//如果是相同对象,即为商户历史行为
			if(tr.getShop_id().equals(shop.getShop_id())){
				shopheat++;
				
				if(tr.getBaby()==user.getBaby() && tr.getEntermament()==user.getEntermament() 
				   && tr.getIncome()==user.getIncome()&&tr.getShopping()==user.getShopping()){
				shopUserLabelRec++;	
				}
				
			}
		}
		return new double[]{shopUserLabelRec,(double)shopUserLabelRec/(double)shopheat};
	}
	//计算用户对应标签的历史类别信息
	public ArrayList<Integer> userAtriHis(Train user){
		ArrayList<Integer> ali=new ArrayList<Integer>();
		for(int i=0;i<models.size();i++){
			if(user.getIncome()==models.get(i).get(0) && user.getEntermament()==models.get(i).get(1)&& user.getBaby()==models.get(i).get(2) 
				&& user.getGender()==models.get(i).get(3)&& user.getShopping()==models.get(i).get(4)){
				for(int index:valueOfModels.get(i)){
					ali.add(index);
				}
			}
		}
		return ali;
	}
	//在这个地点出现的类别信息
    public ArrayList<Integer> posHis(Train user){
    	ArrayList<Integer> ali=new ArrayList<Integer>();
    	Pos p=new Pos(user.getUserLongitude(),user.getUserLatitude());
    	TreeMap<Integer,Integer> map=PosHisClass.get(p);
    	for(Map.Entry<Integer, Integer> t:map.entrySet()){
    		ali.add(t.getKey());
    	}
    	return ali;
    }
	 public double []  userHisCharact(Train user,Train shop)
	{
		double[] userHisAct;
		int userHisNum=0;
		int userHisShopRec=0;
		int userHisClassRec=0;
		int classfi=shop.getClassification();
		int classes=0;
		for(Train tr:trainData)
		{
			//如果是相同对象,即为用户历史行为
			if(tr.getUser_id()==user.getUser_id()){
				userHisNum++;
				if(tr.getShop_id().equals(shop.getShop_id())){
					userHisShopRec++;
				}
				if(tr.getClassification()==classfi){
					userHisClassRec++;
				}
			}
		}

		if(userHisNum==0) userHisAct=new double []{0,0,0,0,0};
		//除去2,3,5
		else userHisAct=new double[]{userHisShopRec,userHisClassRec,userHisNum,(double)userHisShopRec/(double)userHisNum,(double)userHisClassRec/(double)userHisNum};
		
		return userHisAct;	
	}
	//计算用户在该地点已经出现出现的商户类别
	 public ArrayList<Integer> userHisClass(Train user){
  	   UserPos up=new UserPos(user.getUser_id(),user.getUserLongitude(),user.getUserLatitude());
  	   ArrayList<Integer> ali=userPosHisClass.get(up);
  	   return ali;
     }
	//用户轨迹中已出现的类别，如果出现了则为1
	//该类别在用户标签对应的类别中存在，但是该用户在该地点的轨迹类别中还没有出现过
	 public boolean isNotAppearHere(int shopClass,Train user){
		 ArrayList<Integer> ali=userAtriHis(user);
		 ArrayList<Integer> userPosHisCla=userHisClass(user);
		 if(userPosHisCla==null) return true;
		 return ali.contains(shopClass) && !userPosHisCla.contains(shopClass);
	 }
	public  TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim(ArrayList<Train> trainArr,ArrayList<Train> testArr,double from,double d) throws IOException{
		TreeMap<Train,ArrayList<MyMap<Train,Double>>> simMap=new TreeMap<Train,ArrayList<MyMap<Train,Double>>>();
		FileWriter fw=new FileWriter(nullVale);
		for(int i=0;i<testArr.size();i++){
			ArrayList<MyMap<Train,Double>> arr=distance(fw, testArr.get(i), trainArr,from,d);
			 count=0;
			//如果不是空值，添加键值对
			if(arr!=null)  simMap.put(testArr.get(i), arr);
//			traceNum+=arr.size();
		}
		fw.flush();fw.close();
		System.out.println("空值数目   ："+nullNum);
		System.out.println("只有一条轨迹数目  ："+oneTraceNum+" 其中正确数   :"+oneTraceCor);
		return simMap;
	}
	//
	public int getWeek(int day)
	{
		switch(Math.abs(day-10)%7)
		{
		case (1):{return 1;}
		case(2):return 2;
		case(3):return 3;
		case(4):return 4;
		case(5):return 5;
		case(6):return 6;
		case(0):return 7;
		default: return 0;
		}
	}
	public int getArea(int hour)
	{
		
		
		if(hour<7 || hour>22) return 0;
		else if(hour<=12 && hour>=7 ) return 1;
		else if(hour<=14&& hour>12)   return 2;
		else if(hour>14 && hour<19)   return 3;
		else if(hour>=19 && hour<=22) return 4;
		else return 5;
		
		
	}
	//函数功能：计算两个用户轨迹之间的距离
       public ArrayList<MyMap<Train,Double>> distance(FileWriter fw,Train test,ArrayList<Train> trainArr,double from,double d) throws IOException{
    	    
    	     //如果是空值
	    	   if(test.getDuration()<16){
					fw.write(test.getUser_id()+",,"+test.getArrival_time()+"\n");
					nullNum++;
					return null;					
				};    	   
    	      ArrayList<MyMap<Train,Double>> arr=new ArrayList<MyMap<Train,Double>>();
				for(int i=0;i<trainArr.size();i++){
					Train train=trainArr.get(i);
//					double charaDis=((double)Math.abs(test.getEntermament()-train.getEntermament()))/7+((double)Math.abs(test.getIncome()-train.getIncome()))/4+
//							((double)Math.abs(test.getShopping()-train.getShopping()))/14+((double)Math.abs(test.getBaby()-train.getBaby()))/2;
//					if(charaDis>disOfAri) continue
					//如果是空值
					
					double longDif=longDis(test, train);
					double latDif=latDis( test, train);
					
					/*参数调节：
					 * 0.01,0.01，2，性能为0.1406.
					 * 0.01，0.01,3,性能为0.104；
					 * 0.02,0.02,2，性能为0.04*/
					//double d=0.0005;
					//拉大d之后对加入进来的轨迹进行模型限制和地理限制
					//&& 
				    // train.getShopLatitude()>test.getUserLatitude() && train.getShopLongitude()>test.getUserLongitude()
					//&& isExisInModel(test,train) 
				if(longDif<=d&&latDif<=d && longDif>=from && latDif>=from && isExisInModel(test,train) && train.getShopLatitude()>test.getUserLatitude() && train.getShopLongitude()>test.getUserLongitude() )
					{
//						double d1=108000*Math.cos(test.getUserLatitude())*longDif;
//						double d2=108000*latDif;
						double dis=calDistance2(test, train);
						MyMap<Train,Double> mm=new MyMap<Train,Double>(train,dis);
						arr.add(mm);
					}
				}
				//分批次扩大搜寻范围
				if(arr.size()<=size && count<d2.length) {
					count++;
					return distance(fw,test,trainArr,d,d2[count-1]);
					}
				//没有找到轨迹
//				if(count>=d2.length){
//					System.out.println(test.getUser_id()+","+test.getArrival_time());
//					notrace.write(test.getUser_id()+","+test.getArrival_time()+"\n");
//					
//				}
				//按照用户轨迹之间的地理距离排序
				Collections.sort(arr,new MyComparator());
//				if(arr.size()==1 && arr.size()>0){
//					fw.write(test.getUser_id()+","+arr.get(0).getKey().getShop_id()+","+test.getArrival_time()+"\n");
//					oneTraceNum++;
//					if(arr.get(0).getKey().getShop_id().equals(test.getShop_id())) oneTraceCor++;
//					return null;
//				}
				ArrayList<MyMap<Train,Double>> newArr=new ArrayList<MyMap<Train,Double>>();
				
				for(int i=0;i<arr.size();i++){
					Train tr=arr.get(i).getKey();
					double dis=calDistance2(test,tr);
					MyMap<Train,Double> mm=new MyMap<Train,Double>(tr,dis);
					newArr.add(mm);
				}
				//按照用户和商户之间的地理距离排序
				Collections.sort(newArr,new MyComparator());

				return newArr;
		}
       //计算用户轨迹
       public ArrayList<MyMap<Train,Double>> distance2(Train test,ArrayList<Train> trainArr,double from,double d){
			ArrayList<MyMap<Train,Double>> arr=new ArrayList<MyMap<Train,Double>>();
			for(int i=0;i<trainArr.size();i++){
				Train train=trainArr.get(i);
				double charaDis=((double)Math.abs(test.getEntermament()-train.getEntermament()))/7+((double)Math.abs(test.getIncome()-train.getIncome()))/4+
						((double)Math.abs(test.getShopping()-train.getShopping()))/14+((double)Math.abs(test.getBaby()-train.getBaby()))/2;
//				if(charaDis>disOfAri) continue;
				double longDif=longDis(test, train);
				double latDif=latDis( test, train);
				/*参数调节：
				 * 0.01,0.01，2，性能为0.1406.
				 * 0.01，0.01,3,性能为0.104；
				 * 0.02,0.02,2，性能为0.04*/
				//double d=0.0005;
				//拉大d之后对加入进来的轨迹进行模型限制和地理限制
				//&& 
			    // train.getShopLatitude()>test.getUserLatitude() && train.getShopLongitude()>test.getUserLongitude()
				//&& isExisInModel(test,train) 
				if(longDif<=d&&latDif<=d && longDif>=from && latDif>=from && isExisInModel(test,train) && train.getShopLatitude()>test.getUserLatitude() && train.getShopLongitude()>test.getUserLongitude() )
				{
//					double d1=108000*Math.cos(test.getUserLatitude())*longDif;
//					double d2=108000*latDif;
					double dis=calDistance2(test, train);
					MyMap<Train,Double> mm=new MyMap<Train,Double>(train,dis);
					arr.add(mm);
				}
			}
			//按照用户轨迹之间的地理距离排序
			Collections.sort(arr,new MyComparator());
			
			ArrayList<MyMap<Train,Double>> newArr=new ArrayList<MyMap<Train,Double>>();
			for(int i=0;i<arr.size();i++){
				Train tr=arr.get(i).getKey();
				double [] shopHeat= neiborRec(tr, arr);
				//排除掉热度过低的商户
				if(shopHeat[1]<0.008) continue;
				double dis=calDistance2(test,tr);
				MyMap<Train,Double> mm=new MyMap<Train,Double>(tr,dis);
				newArr.add(mm);
			}
			//按照用户和商户之间的地理距离排序
			Collections.sort(newArr,new MyComparator());

			return newArr;
	}
    
       //判断是否在用户的该地点历史行为中出现过
       public boolean isExistInUserHisClass(Train user,Train test){
		    UserPos up=new UserPos(user.getUser_id(),user.getUserLongitude(),user.getUserLatitude());
    	   return userPosHisClass.get(up).contains(test.getClassification());
    	   
       }
      
       public boolean isExisInModel(Train test,Train trace){
    	   for(int i=0;i<models.size();i++){
    		   Vector<Integer> model=models.get(i);
    		   //找出该test对应的模型
    		   if(test.getIncome()==model.get(0) && test.getEntermament()==model.get(1) && test.getBaby()==model.get(2)
    		   && test.getGender()==model.get(3)&& test.getShopping()==model.get(4))
    		   {
    			   Vector<Integer> values=valueOfModels.get(i);
    			   for(int j=0;j<values.size();j++){
    				 if(values.get(j)==trace.getClassification())  return true;
    			   }
    			
    		   }
    	   }
    	   return false;
       }
	   public double calDistance1(Train test,Train tr){
			 double u1Long=tr.getUserLongitude();
			  double u1Lati=tr.getUserLatitude();
			  double u2Long=test.getUserLongitude();
			  double u2Lati=test.getUserLatitude();
		      double lonDif=Math.abs(u1Long-u2Long);
		      double latDif=Math.abs(u1Lati-u2Lati);
		      double d=Math.sqrt(Math.pow(lonDif, 2)+Math.pow(latDif, 2));
		      return d;  
		}
		public double calDistance2(Train test,Train tr)
		{
			 double shopLong=tr.getShopLongitude();
			  double shopLati=tr.getShopLatitude();
			  double userLong=test.getUserLongitude();
			  double userLati=test.getUserLatitude();
		      double lonDif=Math.abs(shopLong-userLong);
		      double latDif=Math.abs(shopLati-userLati);
		      double d=Math.sqrt(Math.pow(lonDif, 2)+Math.pow(latDif, 2));
		      return d;  
		}
		//计算用户和商户之间的距离
		public  double calDistance3(Train test,Train tr){
			  double shopLong=tr.getShopLongitude();
			  double shopLati=tr.getShopLatitude();
			  double userLong=test.getUserLongitude();
			  double userLati=test.getUserLatitude();
		      double latDif=Math.abs(shopLong-userLong);
		      double lonDif=Math.abs(shopLati-userLati);
		      double MeLat=108000*latDif;
		      double MeLon=108000*Math.cos(userLati)*lonDif;
		      double d=Math.sqrt(Math.pow(MeLat, 2)+Math.pow(MeLon, 2));
		      return d;  
		}
		public  double  longDis(Train test,Train train){
			double longDif=Math.abs(test.getUserLongitude()-train.getUserLongitude());
			return longDif;
		}
		public  double  latDis(Train test,Train train){
			double latDif=Math.abs(test.getUserLatitude()-train.getUserLatitude());
			return latDif;
		}
	//从文件中读取xgb预测后的数据
		public void readXgbData(String file) throws IOException{
			
			BufferedReader bf=new BufferedReader(new FileReader(file));
			String s=bf.readLine();
			//该文件字段包括 userid,arrivalTime,shopid,label,pro;
			while((s=bf.readLine())!=null){
				String[] str=s.split(",");
				UserTrace ut=new UserTrace(str[0],str[1]);
				Probabilty p=new Probabilty(Double.parseDouble(str[4]),str[2]);
				//如果已经包含这个用户轨迹
				if(result.keySet().contains(ut)){
					ArrayList<Probabilty> ap=result.get(ut);
					ap.add(p);
					Collections.sort(ap);
					result.put(ut, ap);
				}
				//如果该用户轨迹没有包含
				else{
					   ArrayList<Probabilty> ap=new ArrayList<Probabilty>();
					   ap.add(p);
					   result.put(ut, ap);
				}
			}
			bf.close();
			
		}
		//计算要推荐的商户是否包含在模型给定的类别中
		public void testUserSim(TreeMap<Train,ArrayList<MyMap<Train,Double>>> userSim)
			{
				
				corInSim=0;
				int count=0;
				int allTrace=0;
				for(Map.Entry<Train, ArrayList<MyMap<Train,Double>>> map:userSim.entrySet())
				{
					
					Train tr=map.getKey();
					ArrayList<MyMap<Train,Double>> neibors=map.getValue();
					count++;
					allTrace+=neibors.size();
					for(MyMap<Train,Double> trs:neibors)
					{
//						 tr.getEntermament()==trs.getKey().getEntermament() && tr.getBaby()==trs.getKey().getBaby() && tr.getGender()==trs.getKey().getGender()
//						 && tr.getShopping()==trs.getKey().getShopping()
//						 && tr.getShop_id().equals(trs.getKey().getShop_id())
						if( tr.getShop_id().equals(trs.getKey().getShop_id() ))	
						{
							corInSim++;
							break;
						}
					}
				}
				corRatInSim=(double)corInSim/(double)count;
				System.out.println("用户轨迹中准确数目    ：  "+corInSim +"  被推荐用户数目   ：   "+count+"  轨迹包含率  ："+corRatInSim);
				System.out.println("所有轨迹数目    ：  "+allTrace +"  被推荐用户数目   ：   "+count+"  用户平均轨迹数  ："+(double)allTrace/(double)count);
			}
		
	//函数功能：读取训练集,没有将训练集划分为训练集与测试集
		public  ArrayList<Train> readTrainDataAll(String FILETRAINDATA )throws IOException{
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
		//函数功能：读取训练集数据，并将其划分为训练集与测试集,1/k被选为测试集
		public  Object[] readTrainData(String FILETRAINDATA,int K,int seed)throws IOException{
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
		public  ArrayList<Train> readTestData(String FILETEST )throws IOException{
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
//				System.out.println(user_id+","+income+","+entermament+","+baby+","+gender+","+gender+","+shopping+","+userLongitude+","+userLatitude+","+arrival_time+","+duration);
//				int classification=Integer.parseInt(str[10]);
				Train tr=new Train();
				tr.setArrival_time(arrival_time);
				tr.setBaby(baby);
//				tr.setClassification(classification);
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
		public  Object[] readShop(String FILEPATHSHOPPROFILE)throws IOException{
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
		 //从R中得到的数据建立模型
		public void buildModelFromFile(String file) throws IOException{
			BufferedReader br=new BufferedReader(new FileReader(file));
			TreeMap<Vector<Integer>,ArrayList<Integer>> modelsValue=new TreeMap<Vector<Integer>,ArrayList<Integer>>();
			String s=br.readLine();
			int row=0;
			int count=0;
			while((s=br.readLine())!=null &&(count++)<numOfModel){
				String[] str=s.split(",");
				Vector vecModel=new Vector();
			   for(int index=0;index<5;index++) {
				   int x=Integer.parseInt(str[index]);
				   vecModel.add(index,x );
				  
			   } 
			   models.add(vecModel);
				Vector vecValue=new Vector();
			  
				   vecValue.add(0, Integer.parseInt(str[5]));
				   valueOfModels.add(vecValue);
			   
				row++;
			}
			br.close();
		}
		//将最终结果写入到文件中
		public  void writerCSV(TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim) throws IOException{
			File f=new File(ResultFile);
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
