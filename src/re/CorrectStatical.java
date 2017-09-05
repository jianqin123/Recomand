package re;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CorrectStatical {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
         readFile("E:/data_contest/ChinaUnicomContest/复赛数据/testResutData.csv");
	}
	public static  void readFile(String f) throws IOException
	{
		BufferedReader bf=new BufferedReader(new FileReader(f));
		ArrayList<String> users=new ArrayList<String>();
		String s=bf.readLine();
		int count=0;
		int correct=0;
		while((s=bf.readLine())!=null)
		{
			String [] str=s.split(",");
			String userid=str[0];
			if(users.contains(userid)) continue;
			else
			{
				users.add(str[0]);
				if(Integer.parseInt(str[3])==1){
					correct++;
					System.out.println(s);
				}
			}
				
			
		}
		System.out.println(correct);
	}

}
