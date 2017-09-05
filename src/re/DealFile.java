package re;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DealFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DealFile df=new DealFile();
//				df.writeFile("K:/dataContest/FR15_2.csv", "K:/dataContest/FRALL_15_2.csv");
//	     df.changeFile("K:/dataContest/finalResult15_2.csv", "C:/Users/Jackokie/Desktop/all/429.csv", "K:/dataContest/noTraceFill15_2.csv");
//	     df.fill("C:/Users/Jackokie/Desktop/modelMerge/fill_240ByModel9.csv", "C:/Users/Jackokie/Desktop/modelMerge/fill_240ByModel12.csv", "C:/Users/Jackokie/Desktop/modelMerge/sameBet9And12.csv");
//	     df.selectBigger("C:/Users/Jackokie/Desktop/modelMerge/fill_240ByModel9.csv", "C:/Users/Jackokie/Desktop/modelMerge/fill_240ByModel12.csv", "C:/Users/Jackokie/Desktop/modelMerge/betterBy9_12.csv");
//	     df.changeFile("C:/Users/Jackokie/Desktop/modelMerge/betterBy9_12.csv", "C:/Users/Jackokie/Desktop/modelMerge/fill_240ByModel12.csv", "C:/Users/Jackokie/Desktop/modelMerge/remInModel12.csv");

//       df.fill("C:/Users/Jackokie/Desktop/modelMerge/ANSWER_170.csv", "C:/Users/Jackokie/Desktop/modelMerge/model12ProResult.csv", "C:/Users/Jackokie/Desktop/modelMerge/fillByModel12_2.csv");	     
//         df.fill("C:/Users/Jackokie/Desktop/modelMerge/ANSWER_170.csv", "C:/Users/Jackokie/Desktop/modelMerge/model9Pro.csv", "C:/Users/Jackokie/Desktop/modelMerge/fill_170ByModel9.csv");	     
//		 df.selectBigger("C:/Users/Jackokie/Desktop/modelMerge/fill_170ByModel9.csv", "C:/Users/Jackokie/Desktop/modelMerge/fill_170ByModel12.csv", "C:/Users/Jackokie/Desktop/modelMerge/better_170By9_12.csv");
//		 df.changeFile("C:/Users/Jackokie/Desktop/modelMerge/better_170By9_12.csv", "C:/Users/Jackokie/Desktop/modelMerge/fill_170ByModel12.csv", "C:/Users/Jackokie/Desktop/modelMerge/rem170InModel12.csv");
//	     df.changeFile("C:/Users/Jackokie/Desktop/modelMerge/fill170.csv", "C:/Users/Jackokie/Desktop/modelMerge/ANSWER_170.csv", "C:/Users/Jackokie/Desktop/modelMerge/remain6.csv");
//	     df.fill("C:/Users/Jackokie/Desktop/modelMerge/ANSWER_236.csv",  "C:/Users/Jackokie/Desktop/modelMerge/model12ProResult.csv", "C:/Users/Jackokie/Desktop/modelMerge/fill236ByModel12.csv");
//       df.fill("C:/Users/Jackokie/Desktop/modelMerge/ANSWER_236.csv", "C:/Users/Jackokie/Desktop/modelMerge/model9Pro.csv", "C:/Users/Jackokie/Desktop/modelMerge/fill_170ByModel9.csv");	     
         
         
         
         
        df.changeFile("C:/Users/Jackokie/Desktop/modelMerge/model12_0.5.csv", "C:/Users/Jackokie/Desktop/modelMerge/score_442.csv", "C:/Users/Jackokie/Desktop/modelMerge/score_442-model12_0.5.csv");
	  
	
	
	}
	public void fillNull(String fillnull,String filesave){
		
	}

	public void writeFile(String from,String to) throws IOException
	{
		BufferedReader bf=new BufferedReader(new FileReader(from));
		BufferedWriter bw=new BufferedWriter(new FileWriter(to));
		String s=bf.readLine();
		bw.write(s+"\n");
		while((s=bf.readLine())!=null){
			String [] names=s.split(",");
			bw.write(names[0]+",");
			if(names[1].equals("NA")) bw.write(",");
			else bw.write(names[1]+",");
			bw.write(names[2]+"\n");
		}
		bf.close();
		bw.flush();bw.close();
	}
	/*
	 * 在file429中找出不存在fileModel中的记录
	 * 
	 * */
	public void changeFile(String fileModel,String file429,String filechange) throws IOException
	{
		BufferedReader bf2=new BufferedReader(new FileReader(file429));
		BufferedWriter bw=new BufferedWriter(new FileWriter(filechange));

		String s2=bf2.readLine();
		bw.write(s2+"\n");
		int num=0;
		int count=0;
		while((s2=bf2.readLine()) != null)
		{
			count++;
			//如果不相同
			if(isSame(s2,fileModel)==null){
				bw.write(s2+"\n");
			}
			//如果相同
			else{
				String[] traces=isSame(s2,fileModel).split(",");
				String[] s=s2.split(",");
				if(Integer.parseInt(traces[1])==Integer.parseInt(s[1])) num++;
			}
		}
		bw.flush();
		bw.close();
		System.out.println(count+"   :   "+num);
	}
	/*
	 * befilled：没有找到推荐店铺的文件
	 * searchFrom；从该文件中搜索对应的用户轨迹并将对应店铺推荐
	 * filechange:保存下来的文件
	 * 
	 * */
	public void fill(String befilled,String searchFrom,String filechange) throws IOException
	{
		BufferedReader bf2=new BufferedReader(new FileReader(searchFrom));
		BufferedWriter bw=new BufferedWriter(new FileWriter(filechange));

		String s2=bf2.readLine();
		bw.write(s2+"\n");
		while((s2=bf2.readLine()) != null)
		{
//			System.out.println(s2);
			if(isSame(s2,befilled)!=null)
			{
				bw.write(s2+"\n");
			}
		}
		bw.flush();
		bw.close();
	}
	public String isSame(String s,String fileModel) throws IOException
	{
		BufferedReader bf1=new BufferedReader(new FileReader(fileModel));
//		System.out.println(s);
		String[] info_s=s.split(",");
		int uid=Integer.parseInt(info_s[0]);
		long arrTime=Long.parseLong(info_s[2]);
		String trace=bf1.readLine();
		while((trace=bf1.readLine())!=null)
		{
//			System.out.println("trace  :"+trace);
			String[] info_trace=trace.split(",");
			if(uid==Integer.parseInt(info_trace[0]) && arrTime==Long.parseLong(info_trace[2])) return trace;
		}
		return null;
		
	}
	//两个模型中选择概率较大的那个
	public void selectBigger(String model1,String model2,String fileFilter) throws IOException
	{
		BufferedReader bf2=new BufferedReader(new FileReader(model1));
		BufferedWriter bw=new BufferedWriter(new FileWriter(fileFilter));
		
		int num=0;

		String s2=bf2.readLine();
		bw.write(s2+"\n");
		while((s2=bf2.readLine()) != null)
		{
//			System.out.println(s2);
			String other=isSame(s2,model2);
			
			//如果相等，选择概率较大的
			if(other!=null)
			{
				String[] otheres=other.split(",");
				String [] thes=s2.split(",");
				if(Double.parseDouble(otheres[3])>Double.parseDouble(thes[3])){ 
					bw.write(other+"\n");
					num++;
					
					}
					
				else bw.write(s2+"\n");
			}
		}
		bw.flush();
		bw.close();
		System.out.println("改善条数    ："+num);
	}

}
