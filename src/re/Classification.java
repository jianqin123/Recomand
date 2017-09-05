package re;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
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
import java.util.Map.Entry;




//class MyMap<K,V>{
//	private K key;
//	private V value;
//	MyMap(K key ,V value){
//		this.key=key;
//		this.value=value;
//	}
//	public K getKey() {
//		return key;
//	}
//	public void setKey(K key) {
//		this.key = key;
//	}
//	public V getValue() {
//		return value;
//	}
//	public void setValue(V value) {
//		this.value = value;
//	}
//}

//class MyComparator implements Comparator<MyMap<Train,Double>>{
//
//	@Override
//	public int compare(MyMap arg0, MyMap arg1) {
//		// TODO Auto-generated method stub
//		if((double)arg0.getValue()<(double)arg1.getValue())
//			return -1;
//		else if((double)arg0.getValue()>(double)arg1.getValue())
//			return +1;
//		else
//			return 0;
//	}
//}

//class CompareWithVectorCount implements Comparator<Map.Entry<Shop, Vector>>
//{
//
//	@Override
//	public int compare(Entry<Shop, Vector> o1, Entry<Shop, Vector> o2) {
//		// TODO Auto-generated method stub
//		return new Integer((int) o2.getValue().get(1)).compareTo(new Integer((int) o1.getValue().get(1)));
//	}
//	
//}

//class CompareWithCount implements  Comparator<Map.Entry<Integer,Integer>>
//{
//
//	@Override
//	public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
//		// TODO Auto-generated method stub
//		return o2.getValue().compareTo(o1.getValue());
//	}
//	
//}
/**
 * @author SJP
 * 版本号：10-23
 *
 */

public class Classification {
	public static final String FILETRAINDATA="E:\\ChinaUnicomContest\\123.csv";
	public static final String FILETEST="E:\\ChinaUnicomContest\\testResult.csv";
	public static final String FILEPATHSHOPPROFILE="E:\\ChinaUnicomContest\\shop_good.csv";
	public static void main(String[] args) throws IOException{
//		int K=1;
//		Object[] obj=readTrainData(K);
	  //训练集中随机分出来的1800条数据	
//		ArrayList<Train> trainArr=(ArrayList<Train>)obj[0];
     //训练集中随机分出来的200条数据
//		ArrayList<Train> basicTestArr=(ArrayList<Train>)obj[1];
//		
//		Object[] Obj1= readShop();
//	 //用户数据
//		ArrayList<Shop> shopArr=(ArrayList<Shop>)Obj1[0];
//		//System.out.println(shopArr.size());
//		//TreeMap<Integer,Shop> shopMap=(TreeMap<Integer,Shop>)obj[1];
//		//System.out.println(trainArr.size()+"  "+basicTestArr.size());
//     //用来预测的数据
//		ArrayList<Train> testArr=readTestData();
//		double d=0.0005;
//		//将划分的200条basicTestArr中的train利用1800条trainArr中的数据找出对应的推荐shopid
//		TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim= user_trace_Sim(trainArr, basicTestArr,d);
//		writeHeatAndOrder(user_trace_Sim);
//		writeClassAndOrderToFile(user_trace_Sim(trainArr,trainArr,d), shopArr);
//		int num=0;
////		for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map1:user_trace_Sim.entrySet()){
////			Train trs=map1.getKey();
////			if(map1.getValue().size()==0){
////				map1.getKey().setNewShop_id("");
////			}
////			else
////				map1.getKey().setNewShop_id(map1.getValue().get(0).getKey().getShop_id());
////		}
//		fillValue(user_trace_Sim);
//		test(user_trace_Sim, shopArr);
//		int count=0;
//		//将类别写到和排序写到文件中
//		
//		for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map1:user_trace_Sim.entrySet()){
//			
//			Train trs=map1.getKey();
//			ArrayList<MyMap<Train,Double>> arr=map1.getValue();
//			
//			if(trs.getNewShop_id().equals("")||trs.getNewShop_id()==null){
//				num+=1;
//			}
//			if(trs.getShop_id().equals(trs.getNewShop_id()))
//			{
//				count+=1;	   		   
//			}
//				
//		}
//		
//		
//		
//		
//		
//		
//		System.out.println(num+"==="+count);
//		
		//新数据
//		int num1=0;
//		int count1=0;
		//ff表示新数据中用户组合没有在trainArr中找到的数目
//		int ff=0;
//		TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Simnew= user_trace_Sim(trainArr, testArr,d);
		
//		for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map1:user_trace_Simnew.entrySet()){
//			if(map1.getValue().size()==0)
//				ff+=1;
//		}
		//System.out.println(ff+"****");
//		fillValue(user_trace_Simnew);
//		for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map1:user_trace_Simnew.entrySet()){
//			Train trs=map1.getKey();
//			//if(trs.getNewShop_id().equals(""))
//				count+=1;
//		}
		//fillNull( user_trace_Simnew);
//		int s=0;
//		for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map1:user_trace_Simnew.entrySet()){
//			if(map1.getKey().getNewShop_id()==null)
//				s+=1;
//		}
		//System.out.println(s);
//		test(user_trace_Simnew, shopArr);
//		writerCSV( user_trace_Simnew) ;
//		writerHeatAndOrder();
		writerDensity(6,8,"E:/ChinaUnicomContest/123_22015.csv","E:/ChinaUnicomContest/123_22015_density.csv",60);
//		writerDensity(1,8,"E:/ChinaUnicomContest/123_12115.csv","E:/ChinaUnicomContest/123_12115_density.csv");
//		writerDensity(1,6,"E:/ChinaUnicomContest/123_21127.csv","E:/ChinaUnicomContest/123_21127_density.csv");
	}	
	public static void writeHeatAndOrder(TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Simnew)
	{
		try{
			FileWriter fw=new FileWriter("E:/ChinaUnicomContest/123_plus.csv");
			fw.write("userid,income,entermament,baby,gender,shopping,shop_id,classification,order,heat,order_heat "+"\n");
			for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map1:user_trace_Simnew.entrySet())
			{
				Train tra=map1.getKey();
				Vector vec=calHeatAndOrder(tra,map1.getValue());
				fw.write(tra.getUser_id()+","+tra.getIncome()+","+tra.getEntermament()+","+tra.getBaby()+","+tra.getGender()+","+tra.getShopping()
				+","+tra.getShop_id()+","+tra.getClassification()+","+vec.get(0)+","+vec.get(1)+","+vec.get(2)+"\n");	
			}
			fw.flush();
			fw.close();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void writerDensity(int classfication1,int classfication2,String fileOrigin,String fileSave,double r)
	{
		try {
			FileReader fr=new FileReader(fileOrigin);
			FileWriter fw=new FileWriter(fileSave);
			BufferedReader bf=new BufferedReader(fr);
			String train=bf.readLine();
			fw.write(train+",densityOf_"+classfication1+",densityOrderOf_"+classfication1+",densityOf_ "+classfication2+",densityOrderOf_"+classfication2+"\n");
//			System.out.print(train+",densityOf_"+classfication1+",densityOrderOf_"+classfication1+",densityOf_ "+classfication2+",densityOrderOf_"+classfication2+"diffOrder"+"diffcount"+"\n");
			while((train=bf.readLine())!=null)
			{
				String[] information=train.split(",");
				int userid=Integer.parseInt(information[0]);
//				int classfication=Integer.parseInt(information[7]);
				int shopid=Integer.parseInt(information[6]);
				Vector pos_user=new Vector();
				pos_user.add(0, Double.parseDouble(information[8]));//用户经度
				pos_user.add(1, Double.parseDouble(information[9]));//用户纬度
//				int order1=calOrder(userid,shopid,classfication);
//				System.out.println("用户经纬度：："+pos_user.get(0)+","+pos_user.get(1));
				Vector vec1=calDensityOrder(pos_user,userid,classfication1,classfication2,r);
//				Vector vec2=calDensityOrder(userid,shopid,classfication2);
				fw.write(train+","+vec1.get(0)+","+vec1.get(1)+","+vec1.get(2)+","+vec1.get(3)+","+((int)vec1.get(1)-(int)vec1.get(3))+","+((int)vec1.get(0)-(int)vec1.get(2))+"\n");
				
			}
			fw.flush();
			fw.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//计算用户周围不同类别的商户
	public static void writerDensity(int classfication1,int classfication2,String fileOrigin,String fileSave,int neiborNumber)
	{
		try {
			FileReader fr=new FileReader(fileOrigin);
			FileWriter fw=new FileWriter(fileSave);
			BufferedReader bf=new BufferedReader(fr);
			String train=bf.readLine();
			fw.write(train+",densityOf_"+classfication1+",densityOrderOf_"+classfication1+",densityOf_ "+classfication2+",densityOrderOf_"+classfication2+"\n");
//			System.out.print(train+",densityOf_"+classfication1+",densityOrderOf_"+classfication1+",densityOf_ "+classfication2+",densityOrderOf_"+classfication2+"diffOrder"+"diffcount"+"\n");
			while((train=bf.readLine())!=null)
			{
				String[] information=train.split(",");
				int userid=Integer.parseInt(information[0]);
//				int classfication=Integer.parseInt(information[7]);
				int shopid=Integer.parseInt(information[6]);
				Vector pos_user=new Vector();
				pos_user.add(0, Double.parseDouble(information[8]));//用户经度
				pos_user.add(1, Double.parseDouble(information[9]));//用户纬度
//				int order1=calOrder(userid,shopid,classfication);
//				System.out.println("用户经纬度：："+pos_user.get(0)+","+pos_user.get(1));
				Vector vec1=calDensityOrder(pos_user,userid,classfication1,classfication2,neiborNumber);
//				Vector vec2=calDensityOrder(userid,shopid,classfication2);
				fw.write(train+","+vec1.get(0)+","+vec1.get(1)+","+vec1.get(2)+","+vec1.get(3)+","+((int)vec1.get(1)-(int)vec1.get(3))+","+((int)vec1.get(0)-(int)vec1.get(2))+"\n");
				
			}
			fw.flush();
			fw.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//    public static int getClassification(Vector pos_user,int userid,int classfication1,int classfication2)
//    {
//    	Vector vec=calDensityOrder(pos_user, userid, classfication1, classfication2);
//    	if((int)vec.get(0)<(int)vec.get(2))
//    		return classfication2;
//    	else
//    		return classfication1;
//    	
//    }
	//根据用户位置信息计算用户周边特定类别商户的密度，距离范围通过前辐射半径来限制
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
	//根据用户位置信息计算用户周边特定类别商户的密度，距离范围通过前neiborNumber名商户来限制
	public static Vector calDensityOrder(Vector pos_user,int userid,int classfication1,int classfication2,int neiborNumber)
	{
		
		TreeMap<Integer,Integer> map=new TreeMap<Integer,Integer>();
		List<java.util.Map.Entry<Integer, Double>> neibors=new FilterShop().findNeibors((double)pos_user.get(0),(double)pos_user.get(1),neiborNumber);
		 
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
	//计算用户所选商户的热度和距离排序
	public static void writerHeatAndOrder()
	{
		try {
			FileReader fr=new FileReader("E:/ChinaUnicomContest/123_33019.csv");
			FileWriter fw=new FileWriter("E:/ChinaUnicomContest/123_33019_plus.csv");
			BufferedReader bf=new BufferedReader(fr);
			String train=bf.readLine();
			fw.write(train+",order,heat,order_heat "+"\n");
			System.out.print(train+",order,heat,"+"order_heat"+"\n");
			while((train=bf.readLine())!=null)
			{
				String[] information=train.split(",");
				int userid=Integer.parseInt(information[0]);
				int classfication=Integer.parseInt(information[7]);
				int shopid=Integer.parseInt(information[6]);
				int order=calOrder(userid,shopid,classfication);
				Vector point=calHeat(shopid,calHeatList(userid,classfication));
				int heat=(int) point.get(1);
				int order_heat=(int) point.get(0);
				fw.write(train+","+order+","+heat+","+order_heat+"\n");
				System.out.print(train+","+order+","+heat+","+order_heat+"\n");
			}
			fw.flush();
			fw.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Vector calHeat(int shopid,List<Entry<Integer,Integer>> heates)
	{
		Vector point=new Vector();
		point.add(0,  0);
		point.add(1, 0);
		int order=0;
		for(Entry<Integer,Integer> entry:heates)
		{
			order++;
			if(entry.getKey()==shopid)
			{
				point.set(0,  new Integer(order));
				point.set(1, new Integer(entry.getValue()));
				break;
			}
		}
		
		return point;
	}
	//计算用户辐射范围内的该类别商户的热度
	public static List<Entry<Integer,Integer>> calHeatList(int userid,int classfication)
	{
		//第一个Integer表示商户id,第二个Integer用户进入该商户的次数
		TreeMap<Integer,Integer>  heates=new TreeMap<Integer,Integer>();
//		String sql="select shopid from user_match_train where class= "+classfication;
//			ResultSet res=new FilterShop().conn.createStatement().executeQuery(sql);
			
			for(Map.Entry<Integer,Double> neibor:new FilterShop().findNeibors(userid,classfication))
			{
//				System.out.println(" calHeatList：：while(res.next())：");
				int shopid_=neibor.getKey();
				if(!heates.keySet().contains(new Integer(shopid_)))
				{
//					System.out.println(" calHeatList：：while(res.next())：if");
					String countsql="select count(*) from user_match_train where class= "+classfication+" and "+"shopid= "+shopid_;
					int count=0;
					try {
						ResultSet res=new FilterShop().conn.createStatement().executeQuery(countsql);
						res.next();
						count=res.getInt(1);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					heates.put(new Integer(shopid_), count);
				}
//				else 
//				{
////					System.out.println(" calHeatList：：while(res.next())：else");
//					heates.put(new Integer(shopid_),heates.get(new Integer(shopid_))+1 );
//				}
//				
			}
		
		List<Entry<Integer,Integer>> list=new ArrayList<Entry<Integer,Integer>>(heates.entrySet());
		Collections.sort(list,new CompareWithCount());
		return list;
		
	}
	//根据用户id,shopid,以及实际类别找出实际推荐商户在用户周边商户中的排序
	public static int calOrder(int userid,int shopid,int classfication)
	{
		int order=0;
		for(java.util.Map.Entry<Integer, Double> neibor:new FilterShop().findNeibors(userid, classfication))
		{
			order++;
			if(neibor.getKey()==shopid)
			{
				break;
			}
		}
		return order;
	}
	public static void writeClassAndOrderToFile(TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim,ArrayList<Shop> shopArr) throws IOException
	{
		
		fillValue(user_trace_Sim);
		test(user_trace_Sim, shopArr);
		FileWriter fw=new FileWriter("E:/ChinaUnicomContest/classAndOrder");
		fw.write("userid ,");
		fw.write("shopid ,");
		fw.write("classfication ,");
		fw.write("distance  ,");
		fw.write("order"+"\n");
		for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map1:user_trace_Sim.entrySet()){
			
			Train trs=map1.getKey();
			ArrayList<MyMap<Train,Double>> arr=map1.getValue();
			
			
			if(trs.getShop_id().equals(trs.getNewShop_id()))
			{
//				count+=1;
			int classfication=	trs.getClassification();
			int order=0;
			double distance=0;
			   for(int index=0;index<arr.size();index++)
			   {
				   if(arr.get(index).getKey().getShop_id().equals(trs.getShop_id()))
				   {
					   order=index;
				       distance= arr.get(index).getValue();
					   break;
				   }
					   
			   }
			  
			   fw.write(trs.getUser_id()+",");
			   fw.write(trs.getShop_id()+",");
			   fw.write(classfication+",");
			   fw.write(distance+",");
			   fw.write(order+"\n");
			   
			   
			}
				
		}
		
		fw.flush();
		fw.close();
	}
	//函数功能：填空值总程序
	public static void test(TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim,ArrayList<Shop> shopArr){
		TreeMap<Train,ArrayList<MyMap<Shop,Double>>> tm= fillNull( user_trace_Sim, shopArr);
		shopIDValue( tm);
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
					}
				}

			}
			else if(tr.getIncome()==2&&tr.getEntermament()==1&&tr.getBaby()==1&&tr.getGender()==2&&tr.getShopping()==2){//21122
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==1){
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
					}
				}

			}
			else if(tr.getIncome()==2&&tr.getEntermament()==1&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==5){//21015
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==8){
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
					}
				}
			}
			else if(tr.getIncome()==2&&tr.getEntermament()==1&&tr.getBaby()==1&&tr.getGender()==2&&tr.getShopping()==7){//21127
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==1||arr.get(i).getKey().getClassification()==6){
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
					}
				}
			}
			else if(tr.getIncome()==2&&tr.getEntermament()==2&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==5){//22015
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==8){
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
					}
				}
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==6&&map.getKey().getNewShop_id()==null){
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
					}
				}
			}
			else if(tr.getIncome()==1&&tr.getEntermament()==2&&tr.getBaby()==1&&tr.getGender()==1&&tr.getShopping()==5){//12115
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==1||arr.get(i).getKey().getClassification()==8){
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
					}
				}

			}
			else if(tr.getIncome()==1&&tr.getEntermament()==1&&tr.getBaby()==1&&tr.getGender()==2&&tr.getShopping()==1){//11121
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==3){
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
					}
				}
			}
			else if (tr.getIncome()==2&&tr.getEntermament()==6&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==5){//26015
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==1||arr.get(i).getKey().getClassification()==6||arr.get(i).getKey().getClassification()==8){
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
					}
				}
			}
			else if(tr.getIncome()==2&&tr.getEntermament()==7&&tr.getBaby()==0&&tr.getGender()==1&&tr.getShopping()==5){//27015
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==5){
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id()+"");
					}
				}
			}		
			if(map.getKey().getNewShop_id().equals("")||map.getKey().getNewShop_id()==null&&arr.size()!=0)
			{
				map.getKey().setNewShop_id(arr.get(0).getKey().getShop_id()+"");
				}
		}
		
		for(Map.Entry<Train,ArrayList<MyMap<Shop,Double>>> map:tm.entrySet()){
			if(map.getKey().getNewShop_id().equals(""))
				num+=1;
		}
		System.out.println(num);
	}
	//函数功能：空值填充.找出最近的店铺
	public static TreeMap<Train,ArrayList<MyMap<Shop,Double>>> fillNull(TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim,ArrayList<Shop> shopArr){
		ArrayList<Train> notNullArr=new ArrayList<Train>();
		ArrayList<Train>  nullArr=new ArrayList<Train>();
		
		for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map:user_trace_Sim.entrySet()){
			if(map.getKey().getNewShop_id()==null){
				nullArr.add(map.getKey());
			}
			else
				notNullArr.add(map.getKey());
		}
		System.out.println(nullArr.size()+"-----");
		TreeMap<Train,ArrayList<MyMap<Shop,Double>>> tm=new TreeMap<Train,ArrayList<MyMap<Shop,Double>>>();
		for(int i=0;i<nullArr.size();i++){
			ArrayList<MyMap<Shop,Double>> arr=getDisArr(nullArr.get(i), shopArr);
			tm.put(nullArr.get(i), arr);
		}
		return tm;
	}
	//求出满足距离的N个店铺
	public static ArrayList<MyMap<Shop,Double>> getDisArr(Train tr,ArrayList<Shop> shopArr){
	
		ArrayList<MyMap<Shop,Double>> arr=new ArrayList<MyMap<Shop,Double>>();
		for(int i=0;i<shopArr.size();i++){
			double d=calDistance2(tr,shopArr.get(i));
			{
				MyMap<Shop,Double> mm=new MyMap(shopArr.get(i),d);
				arr.add(mm);
			}
		}
		Collections.sort(arr,new MyComparators());
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
	public static void fillValue(TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim){
		//记录空值的数目
		int num=0;
		for(Map.Entry<Train,ArrayList<MyMap<Train,Double>>> map:user_trace_Sim.entrySet()){
			Train tr=map.getKey();
			ArrayList<MyMap<Train,Double>> arr=map.getValue();
			//21122
			if(tr.getIncome()==2&&tr.getEntermament()==1&&tr.getBaby()==1&&tr.getGender()==2&&tr.getShopping()==2){//2,1,1,2,2
				
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==1){//判断类别
						map.getKey().setNewShop_id(arr.get(i).getKey().getShop_id());
						break;
					}
					else
						continue;
						
				}
				//如果没有找到符合条件的商户id,则取第一个shopid
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
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==6||arr.get(i).getKey().getClassification()==8){//判断类别
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
				for(int i=0;i<arr.size();i++){
					if(arr.get(i).getKey().getClassification()==1||arr.get(i).getKey().getClassification()==8){//判断类别
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
	public static Object[] readTrainData(int K)throws IOException{
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
			if(rand.nextInt(10)==K)
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
	//根据训练集上的train数据
	public static Vector calHeatAndOrder (Train test,ArrayList<MyMap<Train,Double>> shopes)
	{
		Vector vec=new Vector();
		vec.add(0, 0);//记录距离排序
		vec.add(1, 0);//记录热度
		vec.add(2, 0);//记录热度排序
		TreeMap<Shop,Vector> neiborShopes=new TreeMap<Shop,Vector>();
		  System.out.println("calHeatAndOrder::");
		  for(MyMap<Train,Double> train:shopes)
		  {
			  Train tra=train.getKey();
			  if(!neiborShopes.keySet().contains(tra.getShop()))
			  {
				  System.out.println("calHeatAndOrder::"+"for(MyMap<Train,Double> train:shopes)::if(!");
				  Vector vc=new Vector();
				  vc.add(0, 1);//记录次数
			      //记录距离
				  vc.add(1,getDistance(test.getUserLongitude(),test.getUserLatitude(),tra.getShopLongitude(),tra.getShopLatitude()));
			      neiborShopes.put(tra.getShop(),vc);
			  }
			  else
			  {
				  System.out.println("calHeatAndOrder::"+"for(MyMap<Train,Double> train:shopes)::else");
				Vector vc=  neiborShopes.get(tra.getShop()); 
				  vc.set(0, (int)vc.get(0)+1);
				 neiborShopes.put(tra.getShop(),vc);
			  }
			  
		  }
		  List<Map.Entry<Shop, Vector>> list=new ArrayList<Map.Entry<Shop, Vector>>(neiborShopes.entrySet());
		Collections.sort(list,new CompareWithVectorCount());
		int order=0;
		for(Map.Entry<Shop, Vector> shop:list)
		{
			order++;
			if(shop.getKey().getShop_id()==Integer.parseInt(test.getShop_id()))
			{
				//记录热度排序
				vec.set(2, order);
				//记录热度值
				vec.set(1, shop.getValue().get(0));
				 break;
			}
		}
		order=0;
		Collections.sort(list,new CompareWithDistance());
		for(Map.Entry<Shop, Vector> shop:list)
		{
			order++;
			if(shop.getKey().getShop_id()==Integer.parseInt(test.getShop_id()))
			{
				vec.set(0, order);
				 break;
			}
		}
		return vec;
	}
	public static double getDistance(double user_lon,double user_atitu,double shop_lon,double shop_atitu)
	{
		double d1=user_lon-shop_lon;
		double d2=user_atitu-shop_atitu;
		double sum=Math.sqrt(Math.pow(d1, 2)+Math.pow(d2, 2));
		return sum;
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
	
//函数功能：计算两个轨迹之间的距离，找出test的满足longDif<d&&latDif<d的邻居train,double表示的是两条train中shop之间的地理距离
	public static ArrayList<MyMap<Train,Double>> distance(Train test,ArrayList<Train> trainArr,double d){
			ArrayList<MyMap<Train,Double>> arr=new ArrayList<MyMap<Train,Double>>();
			for(int i=0;i<trainArr.size();i++){
				Train train=trainArr.get(i);
				double longDif=longDis( test, train);
				double latDif=latDis( test, train);
				double incomeDif=incomeDif( test, train);
				double entermamentDif= entermamentDif( test, train);
				double babyDif=babyDif( test, train);
				double genderDif= genderDif( test, train);
				double shoppingDif= shoppingDif( test, train);
				double sum=incomeDif+entermamentDif+babyDif+genderDif+shoppingDif;
				/*参数调节：
				 * 0.01,0.01，2，性能为0.1406.
				 * 0.01，0.01,3,性能为0.104；
				 * 0.02,0.02,2，性能为0.04*/
				//double d=0.0005;
				if(longDif<d&&latDif<d){
					double d1=108000*Math.cos(test.getUserLatitude())*longDif;
					double d2=108000*latDif;
					double dis=Math.sqrt(Math.pow(d1, 2)+Math.pow(d2, 2));
					MyMap<Train,Double> mm=new MyMap<Train,Double>(train,dis);
					arr.add(mm);
				}
			}
			Collections.sort(arr,new MyComparator());
			return arr;
	}
	public static double  longDis(Train test,Train train){
		double longDif=Math.abs(test.getUserLongitude()-train.getUserLongitude());
		return longDif;
	}
	public static double  latDis(Train test,Train train){
		double latDif=Math.abs(test.getUserLatitude()-train.getUserLatitude());
		return latDif;
	}
	public static double incomeDif(Train test,Train train){
		if(test.getIncome()!=train.getIncome()){
			return 1;
		}
		else
			return 0;
	}
	public static double entermamentDif(Train test,Train train){
		if(test.getEntermament()!=train.getEntermament()){
			return 1;
		}
		else
			return 0;
	}
	public static double babyDif(Train test,Train train){
		if(test.getBaby()!=train.getBaby()){
			return 1;
		}
		else
			return 0;
	}
	public static double genderDif(Train test,Train train){
		if(test.getGender()!=train.getGender()){
			return 1;
		}
		else
			return 0;
	}
	public static double shoppingDif(Train test,Train train){
		if(test.getShopping()!=train.getShopping()){
			return 1;
		}
		else
			return 0;
}
	//将测试集的结果写到文件中
	public static void writerCSV(TreeMap<Train,ArrayList<MyMap<Train,Double>>> user_trace_Sim) throws IOException{
		File f=new File("E:\\ChinaUnicomContest\\finalResylt1030.csv");
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




























