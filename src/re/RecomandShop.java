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
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;


public class RecomandShop {
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
	
	
	
	public static int [] classes22015={6,8};
	public static int[] classes12115={1,8};
	public static int[] classes21127={1,6};
	//设置svm模型采用的概率间隔 顺序为21127,12115,22015
	public static double[] proInterval={0.30,0.15,0.1};
	
	public static ArrayList<Train>  trainArr;
	public static ArrayList<Train>  basicTestArr;
	public static TreeMap<Integer,Shop> shopMap;
	
	
	public static svm_model model22015;
	public static svm_model model12115;
	public static svm_model model21127;
	
	//模型
	public static ArrayList<Vector<Integer>> models=new ArrayList<Vector<Integer>>();
	public static ArrayList<Vector<Integer>> valueOfModels=new ArrayList<Vector<Integer>>();
	
	//模型参数
	/*
	 * d=0.0002;
	 * ratio=0.001;
	 * defOfSame=0.008    :0.4933 不加属性相似度
	 * 
	 * d=0.001,d1=0.001,ratio=0.001,disOfAri=1.5,defOfSame=0.008    ：0.4716  1:3
	 * d=0.001,d2=3,ratio=1,disOfAri=1.55;defOfSame=0.008 numOfModel=131  :0.4776  1:3
	 * d=0.001,d1=3,ratio=0.3,disOfAri=1.55,defOfSame=0.008,numOfModel=131:0.4842 1:3
	 * */
	//模型参数
	public static double d=0.005;
	public static double d1=2;
	public static double ratio=0.35;
	public static double disOfAri=1.55;
	public static double defOfSame=0.008;
	public static int numOfModel=131;
	
	public static ArrayList<Train> trainData;
	
	//空值数目
	public  int numOfNull=0;
	//直接推荐的数据
	public  int numOfSameTrain=0;
	//直接推荐并且正确的数据
	public  int numOfSameCorrectRecomand=0;
	//没有匹配项之后直接最近推荐的数目
	public  int numOfNearestRecomand=0;
	//测试数据量
	public  int testNum=0;
	//测试数据中准确数目
	public  int testCorrect=0;
	//直接推荐正确率
	public  double corSameRec;
	//总准确率
	public  double corRatio;
	//用户轨迹正确数
	public   int   corInSim=0;
	//用户轨迹正确数包含率
	public double corRatInSim;
	//交叉验证的准确率
	public double crossVaildRate;
	public int traceNum=0;
	

	
	
	
	
	public static void main(String[] args) throws IOException {
		 
		// TODO Auto-generated method stub
	    RecomandShop res=new RecomandShop(); 
             res.initialPara();
            res.buildModelFromFile(ModelFile);
//             res.buildModel();
		res.recomandShop(FILETRAINDATA2,FILEPATHSHOPPROFILE2,null,20);
//		res.crossVaild(10);
		
//		res.recomandShop(FILETEST2);
//	res.optimaziPara(OptimazeParam, 0.00002, 1.4,0,0.0001);

	}
	
	//初始化参数
	public void initialPara() throws IOException
	{
		shopMap=(TreeMap<Integer, Shop>) readShop(FILEPATHSHOPPROFILE2)[1];
	}
	public void initialPara(double d,double ratio,double defOfSame) throws IOException
	{
		this.d=d;
		this.ratio=ratio;
		this.defOfSame=defOfSame;
		shopMap=(TreeMap<Integer, Shop>) readShop(FILEPATHSHOPPROFILE2)[1];
	}
	//优化参数
	public void optimaziPara(String file,double d,double ratio,double defOfSame,double interva) throws IOException
	{
		double d1=d;double ratio1=ratio;double defOfSame1=defOfSame;
		FileWriter fw=new FileWriter(file);
//	fw.write("d,ratio,defOfSame,corRatio,corSameRec,corRatInSim,testCorrect,corInSim,testNum,numOfNearestRecomand,numOfNull \n");
	fw.write("d,ratio,corRatio,corRatInSim,testCorrect,corInSim,testNum,numOfNull \n");
		
//		for(int index=0;index<10;index++)
//		{
			
			for(int index2=0;index2<100;index2++)
			{
				
//				for(int index3=0;index3<100;index3++)
//				{
					RecomandShop rs=new RecomandShop();
					rs.initialPara(d, ratio, defOfSame);
					rs.recomandShop(FILETRAINDATA2,FILEPATHSHOPPROFILE2,null,20);
					fw.write(rs.d+","+rs.ratio+","+rs.defOfSame+","+rs.corRatio+","+rs.corSameRec+","+rs.corRatInSim+","+rs.testCorrect+","+rs.corInSim+","+rs.testNum+","+rs.numOfNearestRecomand+","+rs.numOfNull+"\n");
//					fw.write(rs.d+","+rs.ratio+","+rs.corRatio+","+rs.corRatInSim+","+rs.testCorrect+","+rs.corInSim+","+rs.testNum+","+rs.numOfNull+"\n");
					System.out.println(" index2   :"+index2);
					defOfSame+=interva;
//				}
//				defOfSame=defOfSame1;
//				d+=interva;
			}
			defOfSame=defOfSame1;
			ratio=ratio1;
			ratio+=interva;
//		}
		fw.flush();
		fw.close();
		   
	
	}
	//采用5次交叉验证
 public void crossVaild( int count) throws IOException
	{
		double [] rate= new double[count];
		int seed=10;
		for(int index=0;index<count;index++){
			seed+=index*10;
			  RecomandShop res=new RecomandShop(); 
	             res.initialPara();
	            res.buildModelFromFile(ModelFile);
//	             res.buildModel();
			res.recomandShop(FILETRAINDATA2,FILEPATHSHOPPROFILE2,null,seed);
			rate[index]=res.corRatio;
		}
		double rateAll=0;
		for(int index=0;index<count;index++)
		{
			rateAll+=rate[index];
		}
		System.out.println("平均准确率   ："+(rateAll)/count);
	}
	public  void  recomandShop(String FILETRAINDATA2,String FILEPATHSHOPPROFILE2,String FILETEST2,int seed ) throws IOException
	{
	
		Object[] allData=readTrainData(FILETRAINDATA2,10,seed);
		ArrayList<Train> basicTest=(ArrayList<Train>) allData[1];
		  testNum=basicTest.size();
		System.out.println("测试集数据量    ："+testNum);
		trainData=(ArrayList<Train>) allData[0];
		System.out.println("训练集数据量    ："+trainData.size());
		TreeMap<Train,ArrayList<MyMap<Train,Double>>> userSim=user_trace_Sim(trainData,basicTest,0,d);
		testUserSim(userSim);
		fillValue(userSim,shopMap);
		fillNull(userSim);
		testResult(basicTest);
	
//		writeFileNeiborALL(userSim, FILEUSERSIM);
	}
	
	/*根据模型计算推荐用户
	 * tr：被推荐的用户轨迹
	 * arr:该用户的相似用户轨迹
	 * model:对应的模型标签组合值
    */
	public void recomandShop(String filetest) throws IOException
	{
		ArrayList<Train> trainDataNew=readTrainDataAll(FILETRAINDATA2);
		ArrayList<Train> testDataNew=readTestData(filetest);
		TreeMap<Train,ArrayList<MyMap<Train,Double>>> userSim=user_trace_Sim(trainDataNew,testDataNew,0,d);
		fillValue(userSim,shopMap);
		fillNull(userSim);
		writerCSV(userSim);
		
		
		
	}
	public  boolean getRecomandShop(Train tr,ArrayList<MyMap<Train,Double>> arr,Vector<Integer> model,Vector classfi)
	{
		boolean flag=false;
		String shop="";
		tr.setNewShop_id(shop);
		
		if(tr.getIncome()==model.get(0)&&tr.getEntermament()==model.get(1)&&tr.getBaby()==model.get(2)&&tr.getGender()==model.get(3)&&tr.getShopping()==model.get(4)){
			//计算在一定范围内的店铺密度
			flag=true;
			TreeMap<String,Integer> tm=new TreeMap<String,Integer>();
			for(int i=0;i<arr.size();i++){
				
				if((arr.get(i).getValue()-arr.get(0).getValue())/arr.get(0).getValue()<ratio){
					String shop_id=arr.get(i).getKey().getShop_id();
					if(tm.get(shop_id)==null){
						tm.put(shop_id, 0);
					}
					tm.put(shop_id,tm.get(shop_id)+1);
				}
			}
			//选取出密度最大的
			int max=-1;
			
			for(Map.Entry<String, Integer> maps:tm.entrySet()){
				int classfication=shopMap.get(Integer.parseInt(maps.getKey())).getClassification();
				if(maps.getValue()>max && isClassInclude(classfication,classfi)){
					max=maps.getValue();
					shop=maps.getKey();
				}
			}
			tr.setNewShop_id(shop);
			
//			if(tr.getNewShop_id()==""&&arr.size()!=0){
//				tr.setNewShop_id(arr.get(0).getKey().getShop_id());
//				numOfNearestRecomand+=1;
//			}
			
		}
		 return flag;
	}
		//更具用户相似度做推荐
	public  boolean getRecomandShopWithUerSim(Train tr,ArrayList<MyMap<Train,Double>> arr,Vector<Integer> model,Vector classfi)
		{
			boolean flag=false;
			String shop="";
			tr.setNewShop_id(shop);
			if(tr.getIncome()==model.get(0)&&tr.getEntermament()==model.get(1)&&tr.getBaby()==model.get(2)&&tr.getGender()==model.get(3)&&tr.getShopping()==model.get(4)){
				//计算在一定范围内的店铺密度
				flag=true;
				TreeMap<String,Double> tm=new TreeMap<String,Double>();
//				System.out.println("----------------------------------------------");
				for(int i=0;i<arr.size();i++){	
					double [] userHis=userHisCharact(tr,arr.get(i).getKey());
					if(userHis[3]==0 && userHis[4]==0) continue;
					if(userHis[3]==1 && userHis[4]==1) {
						tr.setNewShop_id(arr.get(i).getKey().getShop_id());
						return true;
					}
					if((arr.get(i).getValue()-arr.get(0).getValue())/arr.get(0).getValue()<ratio){
						String shop_id=arr.get(i).getKey().getShop_id();
						if(tm.get(shop_id)==null){
							tm.put(shop_id, 1/(arr.get(i).getValue()+1)+calSimDeg(tr,arr.get(i).getKey()));
//							System.out.println(1/(arr.get(i).getValue()+1) +"  dfadfa   "+calSimDeg(tr,arr.get(i).getKey()));
						}
						tm.put(shop_id,tm.get(shop_id)+1/(arr.get(i).getValue()+1)+calSimDeg(tr,arr.get(i).getKey()));
					}
				}
				//选取出密度最大的
				double max=-1;
				
				for(Map.Entry<String, Double> maps:tm.entrySet()){
					int classfication=shopMap.get(Integer.parseInt(maps.getKey())).getClassification();
					if(maps.getValue()>max && isClassInclude(classfication,classfi)){
						max=maps.getValue();
						shop=maps.getKey();
					}
				}
				tr.setNewShop_id(shop);
				
				if(tr.getNewShop_id()==""&&arr.size()!=0){
					tr.setNewShop_id(arr.get(0).getKey().getShop_id());
					numOfNearestRecomand+=1;
				}
				
			}
		//如果不为空值
//		if(tr.getNewShop_id()!="") flag=true;
		  return flag;
			
	}
	public double calSimDeg(Train test,Train train)
	{
		double enterDiff=Math.abs(test.getEntermament()-train.getEntermament())/7+0.1;
		double incomeDiff=((double)Math.abs(test.getIncome()-train.getIncome()))/4+0.1;
		double shopDiff=((double)Math.abs(test.getShopping()-train.getShopping()))/14+0.1;
		double babyDiff=((double)Math.abs(test.getBaby()-train.getBaby()))/2+0.1;
		double genderDiff=((double)Math.abs(test.getGender()-train.getGender()))/2+0.1;
		return  0.20*(1/incomeDiff)+0.20*(1/enterDiff)+40*(1/babyDiff)+0.38*(1/genderDiff)+0.27*(1/shopDiff);
	}
	//计算要推荐的商户是否包含在模型给定的类别中
	public void testUserSim(TreeMap<Train,ArrayList<MyMap<Train,Double>>> userSim)
		{
			
			corInSim=0;
			int count=0;
			for(Map.Entry<Train, ArrayList<MyMap<Train,Double>>> map:userSim.entrySet())
			{
				Train tr=map.getKey();
				ArrayList<MyMap<Train,Double>> neibors=map.getValue();
				count++;
				for(MyMap<Train,Double> trs:neibors)
				{
//					 tr.getEntermament()==trs.getKey().getEntermament() && tr.getBaby()==trs.getKey().getBaby() && tr.getGender()==trs.getKey().getGender()
//					 && tr.getShopping()==trs.getKey().getShopping()
//					 && tr.getShop_id().equals(trs.getKey().getShop_id())
					if( tr.getShop_id().equals(trs.getKey().getShop_id() ))	
					{
						corInSim++;
						break;
					}
				}
			}
			corRatInSim=(double)corInSim/(double)count;
			System.out.println("用户轨迹中准确数目    ：  "+corInSim +"  轨迹总数   ：   "+count+"  轨迹包含率  ："+corRatInSim);
		}
	//测试填充值后的准确度
	public  void testResult(ArrayList<Train> basicTest)
	{
	        
		for(Train tra:basicTest)
		{
			if(tra.getNewShop_id()!=null  )
			{
				if(tra.getNewShop_id().equals(tra.getShop_id()))
					testCorrect++;
				//如果为空值
				if(tra.getNewShop_id().equals("")) numOfNull++;
				
			}
			
		}
	    corRatio=(double)testCorrect/(double)testNum;
	    corSameRec=(double)numOfSameCorrectRecomand/(double)numOfSameTrain;
		System.out.println("测试数据量："+testNum+"   正确数："+testCorrect+"   正确率 ："+corRatio);
		System.out.println("直接推荐数目："+numOfSameTrain+" 其中正确数："+numOfSameCorrectRecomand+"   正确率 ："+corSameRec);
		System.out.println("没有匹配情况下的最近推荐数目   :"+numOfNearestRecomand);
		System.out.println("空值数目   :"+numOfNull);
		System.out.println("所有轨迹数目  :"+traceNum);
		
	}
	//计算推荐用户
	public   void fillValue(TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim,TreeMap<Integer,Shop> shopMap) throws NumberFormatException, IOException{
		for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map:user_trace_Sim.entrySet()){
			Train tr=map.getKey();
			ArrayList<MyMap<Train,Double>> arr=map.getValue();
			//如果该用户能够在原训练集中找到相似的则直接推荐
			Vector vec=sameTraceRecomand(tr,arr);
			if((boolean) vec.get(0))
			{
				tr.setNewShop_id((String) vec.get(1));
				numOfSameTrain++;
				if(tr.getNewShop_id().equals(tr.getShop_id())) numOfSameCorrectRecomand++;
				continue;
			}
//			//采用
//			TreeMap<String,Integer> m=new TreeMap<String,Integer>();
//			for(MyMap<Train,Double> neibor:arr)
//			{
//				if(isSame(tr,neibor.getKey()))
//				{
//					if(m.keySet().contains(neibor.getKey().getShop_id()))
//						m.put(neibor.getKey().getShop_id(), m.get(neibor.getKey().getShop_id())+1);
//					else
//						m.put(neibor.getKey().getShop_id(), 1);
//				}
//			}	
//			//String 为商户名，Integer为用户轨迹中该商户出现的次数
//			List<Entry<String,Integer>> list=new ArrayList<Entry<String,Integer>>(m.entrySet());
//			   
//			Collections.sort(list,new CompareWithCount());
			//如果
//			if(list.size()==1){
//				
//				tr.setNewShop_id(list.get(0).getKey());
//			} 
//			else if((double)(list.get(0).getValue()-list.get(1).getValue())/(double)list.get(1).getValue()>3 && list.get(0).getValue()>4 && list.size()<6) {
//				tr.setNewShop_id(list.get(0).getKey());
//				
//			}
//			else{			
			
				for(int index=0;index<models.size();index++)
				{
	//				if(getRecomandShop(tr,arr,models.get(index),valueOfModels.get(index))) 
					if(getRecomandShopWithUerSim(tr,arr,models.get(index),valueOfModels.get(index))) 
						break;
				
				}
//			}

	  }

	}
	public boolean isSame(Train test,Train neibor)
	{
	return  test.getBaby()==neibor.getBaby() && test.getIncome()==neibor.getIncome() && test.getEntermament()==neibor.getEntermament()
			&& test.getGender()==neibor.getGender() && test.getShopping()==neibor.getShopping(); 
	}
	//填充空值
	public void fillNull(TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim)
	{
		int num=0;
		int nullToCorrect=0;
		for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map:user_trace_Sim.entrySet()){
			Train tr=map.getKey();
			if(tr.getNewShop_id().equals(""))
			{
				num++;
				ArrayList<MyMap<Train,Double>> arr=distance(tr,trainData,0,d1);
				for(int index=0;index<models.size();index++)
				{
					if(getRecomandShopWithUerSim(tr,arr,models.get(index),valueOfModels.get(index)))  break;
				
				}
				if(tr.getNewShop_id().equals(tr.getShop_id())) nullToCorrect++;
			}
			
			
			
			
		}
		System.out.println("空值数目2     ："+num+" 空值填充正确数目   ："+nullToCorrect);
		
		
	}
	//根据完全相似的用户轨迹推荐出推荐热度最高的店铺
	public  Vector sameTraceRecomand(Train tr,ArrayList<MyMap<Train,Double>> arr)
	{
		Vector vec=new Vector();
		vec.add(0,false);
		ArrayList<Train> sameTrace=getSameTrace(tr,arr);
		if(sameTrace.isEmpty())	  return vec;
		vec.set(0, true);
		TreeMap<String,Integer> sameTraceShop=new TreeMap<String,Integer>();
		for(Train train:sameTrace)
		{
			String shopid=train.getShop_id();
			if(sameTraceShop.keySet().contains(shopid))
			{
				sameTraceShop.put(shopid, sameTraceShop.get(shopid)+1);	
			}
			else
			{
				sameTraceShop.put(shopid, 1);	
			}
				
		}
		
		
		int max=-1;
		String shopRec="";
		for(Map.Entry<String, Integer> me:sameTraceShop.entrySet())
		{
			if(me.getValue()>max)
			{
				max=me.getValue();
				shopRec=me.getKey();
			}
		}
		
		vec.add(1, shopRec);
		return vec;
	}
	//获取完全一致的用户轨迹
	public  ArrayList<Train> getSameTrace(Train tr,ArrayList<MyMap<Train,Double>> arr)
	{
		ArrayList<Train> sameTrace=new ArrayList<Train>();
		for(MyMap<Train,Double> userSim:arr)
		{
			Train trs=userSim.getKey();
			if(tr.getUser_id()==trs.getUser_id() && calDistance2(tr,trs)<=defOfSame)
			{
				sameTrace.add(trs);
			}
		}
		return sameTrace;
		
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
		else userHisAct=new double[]{userHisShopRec,userHisClassRec,userHisNum,(double)userHisShopRec/(double)userHisNum,(double)userHisClassRec/(double) userHisNum};
		
		return userHisAct;	
	}
	
	public  Vector getSimShop(int userid,double lon,double lati)
	{
		Vector vec=new Vector();
		vec.add(0,false);
		vec.add(1, 0);
		for(Train train:trainData)
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
	public  boolean isClassInclude(int classfication,Vector classfi)
	{
		boolean flag=false;
		for(int index=0;index<classfi.size();index++)
		{
			flag=flag || classfication==(int)classfi.get(index);
		}
		return flag;
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
	//建立模型
		public  void buildModel()
		{
			int[][] a=new int[][]{
								 {2,1,1,2,2},
								 {3,3,0,1,9},
								 {2,1,0,1,5},
								 {1,1,1,2,1},
								 {2,1,1,2,7},
								 {2,2,0,1,5},
								 {1,2,1,1,5},
								 {2,7,0,1,5}
			 };
			 for(int index=0;index<a.length;index++)
			 {
				 Vector model=new Vector();
				for(int index1=0;index1<a[0].length;index1++){
					model.add(index1,a[index][index1]);
				}
				models.add(model);
			 }
			 Vector vec1=new Vector();
			        vec1.add( 0, 1);
			        valueOfModels.add(vec1);
			 Vector vec2=new Vector();
			        vec2.add( 0, 6);
			        valueOfModels.add(vec2);
			Vector vec3=new Vector();
			        vec3.add( 0, 8);
			        valueOfModels.add(vec3);
			Vector vec4=new Vector();
			        vec4.add( 0, 3);
			        valueOfModels.add(vec4);
			Vector vec5=new Vector();
			        vec5.add( 0, 1);
			        vec5.add( 1, 6);
			        valueOfModels.add(vec5);
		    Vector vec6=new Vector();
			        vec6.add( 0, 6);
			        vec6.add( 1, 8);
			        valueOfModels.add(vec6);
			Vector vec7=new Vector();
			       vec7.add(0, 1);
			       vec7.add(1, 8);
			       valueOfModels.add(vec7);
		   Vector vec8=new Vector();
			        vec8.add( 0, 5);
			        valueOfModels.add(vec8);
			        System.out.println(models.size()+"    "+valueOfModels.size());
//			
//			
		}
		       
		
	//计算用户轨迹
	public  TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim(ArrayList<Train> trainArr,ArrayList<Train> testArr,double from,double d){
		TreeMap<Train,ArrayList<MyMap<Train,Double>>> simMap=new TreeMap<Train,ArrayList<MyMap<Train,Double>>>();
		for(int i=0;i<testArr.size();i++){
			ArrayList<MyMap<Train,Double>> arr=distance( testArr.get(i), trainArr,from,d);
			simMap.put(testArr.get(i), arr);
		    traceNum+=arr.size();
		}
		return simMap;
	}
   //获取完全历史用户
	
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
					if(longDif<=d&&latDif<=d && longDif>=from && latDif>=from && charaDis<6 ) {
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
