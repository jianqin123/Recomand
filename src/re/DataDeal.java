package re;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataDeal {
	public static final String file_123="E:/data_contest/ChinaUnicomContest/复赛数据/123.csv";
	public static final String file_123_to="E:/data_contest/ChinaUnicomContest/复赛数据/123_date.csv";
	public static final String Total="E:/data_contest/ChinaUnicomContest/复赛数据/Total.csv";
	public static final String Total_date="E:/data_contest/ChinaUnicomContest/复赛数据/Total_date.csv";

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		 DataDeal dd=new  DataDeal();
		   dd.dealData(file_123, file_123_to, 12);
		   dd.dealData(Total, Total_date, 12);

	}
	public void dealData(String from,String to,int order) throws IOException, ParseException
	{
		BufferedReader fr=new BufferedReader(new FileReader(from));
		BufferedWriter fw=new BufferedWriter(new FileWriter(to));
		String s=fr.readLine();
		fw.write(s+",week,timeArea"+"\n");
		while((s=fr.readLine())!=null)
		{
			String[] str=s.split(",");
			Date date=new SimpleDateFormat("yyyymmddHHmmss").parse(str[order]);
			fw.write(s+","+getWeek(date.getDate())+","+getArea(date.getHours())+"\n");
		}
		
		
	}
	public int getWeek(int day)
	{
		switch((day-10)%7)
		{
		case (1):return 1;
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

}
