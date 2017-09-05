package re;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import java.util.Map.Entry;

public class RecomandShop2 {
	//初赛数据
		public static final String FILETRAINDATA="E:\\data_contest\\ChinaUnicomContest\\123.csv";
		public static final String FILETEST="E:\\data_contest\\ChinaUnicomContest\\testResult.csv";
		public static final String FILEPATHSHOPPROFILE="E:\\data_contest\\ChinaUnicomContest\\shop_good.csv";
		
		
		//复赛数据
		public static final String FILETRAINDATA2= "E:/data_contest/ChinaUnicomContest/复赛数据/123.csv";
		public static final String FILETEST2="E:/data_contest/ChinaUnicomContest/复赛数据/testpre.csv";
		public static final String FILEPATHSHOPPROFILE2="E:/data_contest/ChinaUnicomContest/复赛数据/shop_profile.csv";
		
		public static final String ResultFile="E:/data_contest/ChinaUnicomContest/复赛数据/result11_29_1.csv";
		public static final String FILEUSERSIM="E:\\data_contest\\ChinaUnicomContest\\user_simNeibor.csv";
		public static final String OptimazeParam="E:/data_contest/ChinaUnicomContest/复赛数据/OptimazeParam.csv";
		public static final String ModelFile="E:/data_contest/ChinaUnicomContest/复赛数据/model.csv";
		
		//生成结果数据
		  public static final String resultFile="E:/data_contest/ChinaUnicomContest/复赛数据/resultAnalysis.csv";
		//中间数据
		public static ArrayList<Train>  trainData;
		public static ArrayList<Train>  basicTest;
		public static TreeMap<Integer,Shop> shopMap;
		//参数
		public static double d=0;
		public static double d2=0;
		
    //指标参数
		//空值数目
		public  int numOfNull=0;
		
		//训练集数据数目
		public int trainNum=0;
		//测试数据量
		public  int testNum=0;
		//测试数据中准确数目
		public  int testCorrect=0;
		//标签第一次出现的次数
		public int firstNum=0;
		public int firstCorrect=0;
		//
		//用户轨迹中有相似标签，其中正确数
		public int direct=0;
		public int directCorrect;
		//总正确率
		public  double corRatio;
		public int traceNum=0;
		//交叉验证的准确率
		public double crossVaildRate;
		public ArrayList<String> correctRecomand=new ArrayList<String>();
		public ArrayList<String> errorRecomand=new ArrayList<String>();
		
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 RecomandShop2 res=new RecomandShop2();
		 res.recomandShop(FILETRAINDATA2,FILEPATHSHOPPROFILE2,20);
		 res.testResult(basicTest);
	}
	public  void testResult(ArrayList<Train> basicTest) throws IOException
	{
	    FileWriter fw=new FileWriter(resultFile);  
	    fw.write("uid,"+"user_lon,"+"user_latti,"+"recomandShop,"+"label"+"\n");
		for(Train tra:basicTest)
		{
			if(tra.getNewShop_id()!=null  )
			{
				if(tra.getNewShop_id().equals(tra.getShop_id())){
					testCorrect++;
					fw.write(tra.getUser_id()+","+tra.getUserLongitude()+","+tra.getUserLatitude()+","+tra.getNewShop_id()+",1"+"\n");
				}	
				else 
					fw.write(tra.getUser_id()+","+tra.getUserLongitude()+","+tra.getUserLatitude()+","+tra.getNewShop_id()+",0"+"\n");
				//如果为空值
				if(tra.getNewShop_id()==null) numOfNull++;
				
			}
			
		}
		fw.flush();fw.close();
	    corRatio=(double)testCorrect/(double)testNum;
		System.out.println("测试数据量："+testNum+"   正确数："+testCorrect+"   正确率 ："+corRatio);
		System.out.println("直接推荐数目："+direct+"   正确数："+directCorrect+"   正确率 ："+(double)directCorrect/(double)direct);
		System.out.println("间接推荐数目："+firstNum+"   正确数："+firstCorrect+"   正确率 ："+(double)firstCorrect/(double)firstNum);
		System.out.println("空值数目   :"+numOfNull);
		System.out.println("所有轨迹数   :"+traceNum);
	}
	public  TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim(ArrayList<Train> trainArr,ArrayList<Train> testArr,double from,double d){
		TreeMap<Train,ArrayList<MyMap<Train,Double>>> simMap=new TreeMap<Train,ArrayList<MyMap<Train,Double>>>();
		for(int i=0;i<testArr.size();i++){
			ArrayList<MyMap<Train,Double>> arr=distance( testArr.get(i), trainArr,from,d);
			simMap.put(testArr.get(i), arr);
			traceNum+=arr.size();
		}
		return simMap;
	}
	
	public void recomandShop(String FILETRAINDATA2,String FILEPATHSHOPPROFILE2,int seed ) throws IOException
	{

		Object[] allData=readTrainData(FILETRAINDATA2,4,seed);
	      basicTest=(ArrayList<Train>) allData[1];
		  testNum=basicTest.size();
		  trainData=(ArrayList<Train>) allData[0];
		  trainNum=trainData.size();
		  shopMap=(TreeMap<Integer, Shop>) readShop(FILEPATHSHOPPROFILE2)[1];
	
		TreeMap<Train,ArrayList<MyMap<Train,Double>>> userSim=user_trace_Sim(trainData,basicTest,0,d);
		for(Map.Entry<Train, ArrayList<MyMap<Train,Double>>> map2:userSim.entrySet())
		{
			Train tr=map2.getKey();
			ArrayList<MyMap<Train,Double>> arr=map2.getValue();
			getRecomandShop(tr,arr);
		}
	}
   //获取完全历史用户
	public void getRecomandShop(Train test,ArrayList<MyMap<Train,Double>> arr)
	{
		TreeMap<String,Integer> map=new TreeMap<String,Integer>();
		for(MyMap<Train,Double> neibor:arr)
		{
			if(isSame(test,neibor.getKey()))
			{
				if(map.keySet().contains(neibor.getKey().getShop_id()))
					map.put(neibor.getKey().getShop_id(), map.get(neibor.getKey().getShop_id())+1);
				else
					map.put(neibor.getKey().getShop_id(), 1);
			}
		}
		//如果该用户标签第一次出现
		if(map.isEmpty()){
			  firstNum++;
			  getRecomandShopWithNull(test,arr);
			  return;
		}
		//String 为商户名，Integer为用户轨迹中该商户出现的次数
		List<Entry<String,Integer>> list=new ArrayList<Entry<String,Integer>>(map.entrySet());
		   
		Collections.sort(list,new CompareWithCount());
		//如果
		if(list.size()==1){
//			direct++;
//			test.setNewShop_id(list.get(0).getKey());
		} 
		else if((double)(list.get(0).getValue()-list.get(1).getValue())/(double)list.get(1).getValue()>3 && list.get(0).getValue()>4 && list.size()<6) {
//			test.setNewShop_id(list.get(0).getKey());
//			direct++;
		}
		else
		{
			//从训练集中得出该用户的历史推荐类别，并按照推荐的类别次数排序
			List<Entry<Integer,Integer>> classes=userHistory(test);
			 for(Entry<Integer,Integer> entry:classes)
			 {
				 int clasfi=entry.getKey();
				 boolean flag=false;
				for(Entry<String,Integer> shop:list)
				{
					String shopid=shop.getKey();
					if(shopMap.get(Integer.parseInt(shopid)).getClassification()==clasfi && entry.getValue()>3 ){
						test.setNewShop_id(shop.getKey());
						direct++;
						flag=true;
						break;
					}
				}
				if(flag) break;
			 }
			
		}
//		test.setNewShop_id(list.get(0).getKey());
		if(test.getNewShop_id()!=null)
		 if(test.getNewShop_id().equals(test.getShop_id())) directCorrect++;
		
		
	}
	public List<Entry<Integer,Integer>> userHistory(Train user)
	{
		TreeMap<Integer,Integer> al=new TreeMap<Integer,Integer>();
		for(Train tr:trainData)
		{
			//如果是相同对象
			if(tr.getUser_id()==user.getUser_id()){
				int classfi=tr.getClassification();
				if(al.keySet().contains(classfi)){
					al.put(classfi, al.get(classfi)+1);
				}
				else al.put(classfi, 1);
				
			}
		}
		List<Entry<Integer,Integer>> list=new ArrayList<Entry<Integer,Integer>>(al.entrySet());
		   
		Collections.sort(list,new CompareWithCount());
		return list;
	}
	public void getRecomandShopWithNull(Train test,ArrayList<MyMap<Train,Double>> arr)
	{
		TreeMap<String,Integer> map=new TreeMap<String,Integer>();
		for(MyMap<Train,Double> neibor:arr)	{
			Vector vec=existInTrainData(test,neibor.getKey());
			if((boolean) vec.get(0))  map.put(neibor.getKey().getShop_id(), (Integer) vec.get(1));
		}
		List<Entry<String,Integer>> list=new ArrayList<Entry<String,Integer>>(map.entrySet());
		   
		Collections.sort(list,new CompareWithCount());
		if(!list.isEmpty())
		test.setNewShop_id(list.get(0).getKey());
		//如果第一次标签出现推荐正确
		if(test.getNewShop_id()!=null)
		if(test.getNewShop_id().equals(test.getShop_id())) firstCorrect++;
	}
	//计算训练集中用户相似度
	public double calSimDeg(Train test,Train train)
	{
		double enterDiff=Math.abs(test.getEntermament()-train.getEntermament())/7+0.1;
		double incomeDiff=((double)Math.abs(test.getIncome()-train.getIncome()))/4+0.1;
		double shopDiff=((double)Math.abs(test.getShopping()-train.getShopping()))/14+0.1;
		double babyDiff=((double)Math.abs(test.getBaby()-train.getBaby()))/2+0.1;
		double genderDiff=((double)Math.abs(test.getGender()-train.getGender()))/2+0.1;
		return  1/(0.2*incomeDiff+0.2*enterDiff+0.4*babyDiff+0.4*genderDiff+0.3*shopDiff+1);
	}
	//从训练集中该商户是否有相同标签组合的用户去过该
	public Vector existInTrainData(Train test,Train neibor)
	{
		Vector vec=new Vector();
		vec.add(0, false);
		int count=0;
		for(Train tr:trainData){
		 if(tr.getShop_id().equals(neibor.getShop_id()) && isSame(tr,test)) count++;
		}
		
		
		if(count>0){
			vec.set(0, true);
			vec.add(1, count);
		}
		return vec;
	}
	
	public boolean isSim(Train test,Train neibor)
	{
	double enterDiff=Math.abs(test.getEntermament()-neibor.getEntermament())/7+0.1;
	double incomeDiff=((double)Math.abs(test.getIncome()-neibor.getIncome()))/4+0.1;
	double shopDiff=((double)Math.abs(test.getShopping()-neibor.getShopping()))/14+0.1;
	double babyDiff=((double)Math.abs(test.getBaby()-neibor.getBaby()))/2+0.1;
	double genderDiff=((double)Math.abs(test.getGender()-neibor.getGender()))/2+0.1;
	return  1/(0.4*babyDiff+0.4*genderDiff+0.3*shopDiff+1)>0.5;
	}
	public boolean isSame(Train test,Train neibor)
	{
	return  test.getBaby()==neibor.getBaby() && test.getIncome()==neibor.getIncome() && test.getEntermament()==neibor.getEntermament()
			&& test.getGender()==neibor.getGender() && test.getShopping()==neibor.getShopping(); 
	}
	
	
	
	
	//函数功能：计算两个用户轨迹之间的距离
public ArrayList<MyMap<Train,Double>> distance(Train test,ArrayList<Train> trainArr,double from,double d){
				ArrayList<MyMap<Train,Double>> arr=new ArrayList<MyMap<Train,Double>>();
				for(int i=0;i<trainArr.size();i++){
					Train train=trainArr.get(i);
					double charaDis=((double)Math.abs(test.getEntermament()-train.getEntermament()))/7+((double)Math.abs(test.getIncome()-train.getIncome()))/4+
							((double)Math.abs(test.getShopping()-train.getShopping()))/14+((double)Math.abs(test.getBaby()-train.getBaby()))/2;
//					if(charaDis>disOfAri) continue;
					double longDif=longDis(test, train);
					double latDif=latDis( test, train);
					/*参数调节：
					 * 0.01,0.01，2，性能为0.1406.
					 * 0.01，0.01,3,性能为0.104；
					 * 0.02,0.02,2，性能为0.04*/
					//double d=0.0005;
					if(longDif<=d&&latDif<=d && longDif>=from && latDif>=from && charaDis<2){
//						double d1=108000*Math.cos(test.getUserLatitude())*longDif;
//						double d2=108000*latDif;
						double dis=Math.sqrt(Math.pow(longDif, 2)+Math.pow(latDif, 2));
						MyMap<Train,Double> mm=new MyMap<Train,Double>(train,dis);
						arr.add(mm);
					}
				}
				//按照用户轨迹之间的地理距离排序
				Collections.sort(arr,new MyComparator());
				
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
		public double calDistance2(Train test,Train tr)
		{
			 double shopLong=tr.getShopLongitude();
			  double shopLati=tr.getShopLatitude();
			  double userLong=test.getShopLongitude();
			  double userLati=test.getUserLatitude();
		      double latDif=Math.abs(shopLong-userLong);
		      double lonDif=Math.abs(shopLati-userLati);
		      double d=Math.sqrt(Math.pow(lonDif, 2)+Math.pow(latDif, 2));
		      return d;  
		}
		//计算用户和商户之间的距离
		public  double calDistance3(Train test,Train tr){
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
		public  double  longDis(Train test,Train train){
			double longDif=Math.abs(test.getUserLongitude()-train.getUserLongitude());
			return longDif;
		}
		public  double  latDis(Train test,Train train){
			double latDif=Math.abs(test.getUserLatitude()-train.getUserLatitude());
			return latDif;
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
		//找出所有用户最近邻商户的类别
		public  void writeFileNeiborALL(TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim ,String file) throws IOException
		{
			FileWriter fw=new FileWriter(file);
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



}
