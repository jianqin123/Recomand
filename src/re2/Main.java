package re2;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
/*
 * 问题描述
给定一个R行C列的地图，地图的每一个方格可能是'#', '+', '-', '|', '.', 'S', 'T'七个字符中的一个，分别表示如下意思：
'#': 任何时候玩家都不能移动到此方格；
'+': 当玩家到达这一方格后，下一步可以向上下左右四个方向相邻的任意一个非'#'方格移动一格；
'-': 当玩家到达这一方格后，下一步可以向左右两个方向相邻的一个非'#'方格移动一格；
'|': 当玩家到达这一方格后，下一步可以向上下两个方向相邻的一个非'#'方格移动一格；
'.': 当玩家到达这一方格后，下一步只能向下移动一格。如果下面相邻的方格为'#'，则玩家不能再移动；
'S': 玩家的初始位置，地图中只会有一个初始位置。玩家到达这一方格后，下一步可以向上下左右四个方向相邻的任意一个非'#'方格移动一格；
'T': 玩家的目标位置，地图中只会有一个目标位置。玩家到达这一方格后，可以选择完成任务，也可以选择不完成任务继续移动。如果继续移动下一步可以向上下左右四个方向相邻的任意一个非'#'方格移动一格。
此外，玩家不能移动出地图。
请找出满足下面两个性质的方格个数：
1. 玩家可以从初始位置移动到此方格；
2. 玩家不可以从此方格移动到目标位置。
输入格式
输入的第一行包括两个整数R 和C，分别表示地图的行和列数。(1 ≤ R, C ≤ 50)。
接下来的R行每行都包含C个字符。它们表示地图的格子。地图上恰好有一个'S'和一个'T'。
输出格式
如果玩家在初始位置就已经不能到达终点了，就输出“I'm stuck!”（不含双引号）。否则的话，输出满足性质的方格的个数。
样例输入
5 5
--+-+
..|#.
..|##
S-+-T
####.
样例输出
2
样例说明
如果把满足性质的方格在地图上用'X'标记出来的话，地图如下所示：
--+-+
..|#X
..|##
S-+-T
####X
*/

public class Main {
    public  Vector<Integer> start_pos=new Vector<Integer>();
    public  Vector<Integer> target_pos=new Vector<Integer>();
    public ArrayList<Vector<Integer>> availForStart=new ArrayList<Vector<Integer>>();
    public ArrayList<Vector<Integer>> availForend=new ArrayList<Vector<Integer>>();
    public boolean flag=false;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main s=new Main();
		
		int[][] layout=s.getLayout();
		System.out.println(s.getAnswer(layout));
  
	}
	public String getAnswer(int[][] layout)
	{
		String answer="I'm stuck!";
		int count=0;
		calAvailable(layout,start_pos,availForStart);
		availForStart.remove(0);
		if(availForStart.isEmpty())
		   return answer;
		else
		{
			for(Vector<Integer> forStart:availForStart)
			{
				flag=false;
				availForend.clear();
				arrivalEnd(layout,forStart,availForend);
				if(!flag)
					count++;			
			}
		   return count+"";
		}
	}
//	public boolean isSame(V forStart,int[] forTarget)
//	{
//		if(forStart[0]==forTarget[0] && forStart[1]==forTarget[1])
//			return true;
//		return false;
//	}
	//找出所有能够从初始点到达的格子
	public void calAvailable(int[][] layout,Vector<Integer> pos,ArrayList<Vector<Integer>> container)
	{
		int i=(int)pos.get(0);
		int j=(int)pos.get(1);
		//如果超出边界
		if(!(j<layout[0].length && j>=0) || !(i<layout.length && i>=0))
			return;
		//避免循环
		if(container.contains(pos))
			return;
		
	   int element=layout[i][j];
	   switch(element)
		{
		case 0:
			   break;
		case 1 :
			 container.add(pos);
			 
			 Vector<Integer> avilPos1=new Vector<Integer>() ;
			 avilPos1.add(0, i);
			 avilPos1.add(1, j+1);
			 
			 calAvailable(layout,avilPos1, container);
			 
			 Vector<Integer> avilPos2=new Vector<Integer>() ;
			 avilPos2.add(0, i);
			 avilPos2.add(1, j-1);
			 
			 calAvailable(layout,avilPos2,container);

			 Vector<Integer> avilPos3=new Vector<Integer>() ;
			 avilPos3.add(0, i+1);
			 avilPos3.add(1, j);
			 
			 calAvailable(layout,avilPos3,container);
		
			 Vector<Integer> avilPos4=new Vector<Integer>() ;
			 avilPos4.add(0, i-1);
			 avilPos4.add(1, j);


			 calAvailable(layout,avilPos4,container);
			
			 break;
		case 2:
			 container.add(pos);
			 
			 Vector<Integer> avilPos5=new Vector<Integer>() ;
			 avilPos5.add(0, i);
			 avilPos5.add(1, j+1);

			 calAvailable(layout,avilPos5,container);

			 Vector<Integer> avilPos6=new Vector<Integer>() ;
			 avilPos6.add(0, i);
			 avilPos6.add(1, j-1);
//			 container.add(avilPos6);
			 calAvailable(layout,avilPos6,container);
			
			 break;
		case 3:
			 container.add(pos);
			 
			 Vector<Integer> avilPos7=new Vector<Integer>() ;
			 avilPos7.add(0, i+1);
			 avilPos7.add(1, j);

			 calAvailable(layout,avilPos7,container);

			 Vector<Integer> avilPos8=new Vector<Integer>() ;
			 avilPos8.add(0, i-1);
			 avilPos8.add(1, j);
			 
			 calAvailable(layout,avilPos8,container);
			 
			 break;
		case 4:
			 container.add(pos);

			 Vector<Integer> avilPos9=new Vector<Integer>() ;
			 avilPos9.add(0, i+1);
			 avilPos9.add(1, j);
//			 container.add(avilPos9);
			 calAvailable(layout,avilPos9,container);
			 break;
		case 5 :
			 container.add(pos);
			 
			 Vector<Integer> avilPos10=new Vector<Integer>() ;
			 avilPos10.add(0, i);
			 avilPos10.add(1, j+1);
			 
			 calAvailable(layout,avilPos10, container);
			 
			 Vector<Integer> avilPos11=new Vector<Integer>() ;
			 avilPos11.add(0, i);
			 avilPos11.add(1, j-1);
			 
			 calAvailable(layout,avilPos11,container);

			 Vector<Integer> avilPos12=new Vector<Integer>() ;
			 avilPos12.add(0, i+1);
			 avilPos12.add(1, j);
			 
			 calAvailable(layout,avilPos12,container);
		
			 Vector<Integer> avilPos13=new Vector<Integer>() ;
			 avilPos13.add(0, i-1);
			 avilPos13.add(1, j);


			 calAvailable(layout,avilPos13,container);
			 
			 break;
		case 6 :
			container.add(pos);			 
			 Vector<Integer> avilPos14=new Vector<Integer>() ;
			 avilPos14.add(0, i);
			 avilPos14.add(1, j+1);
			 
			 calAvailable(layout,avilPos14, container);
			 
			 Vector<Integer> avilPos15=new Vector<Integer>() ;
			 avilPos15.add(0, i);
			 avilPos15.add(1, j-1);
			 
			 calAvailable(layout,avilPos15,container);

			 Vector<Integer> avilPos16=new Vector<Integer>() ;
			 avilPos16.add(0, i+1);
			 avilPos16.add(1, j);
			 
			 calAvailable(layout,avilPos16,container);
		
			 Vector<Integer> avilPos17=new Vector<Integer>() ;
			 avilPos17.add(0, i-1);
			 avilPos17.add(1, j);


			 calAvailable(layout,avilPos17,container);
			 break;
		}
		
		
		
		
		
		
		
		
	}
	public void arrivalEnd(int[][] layout,Vector<Integer> pos,ArrayList<Vector<Integer>> container)
	{
		int i=(int)pos.get(0);
		int j=(int)pos.get(1);
		//如果超出边界
		if(!(j<layout[0].length && j>=0) || !(i<layout.length && i>=0))
			return;
		//避免循环
		if(container.contains(pos))
			return;
		
	   int element=layout[i][j];
	   switch(element)
		{
		case 0:
			   break;
		case 1 :
			 container.add(pos);
			 
			 Vector<Integer> avilPos1=new Vector<Integer>() ;
			 avilPos1.add(0, i);
			 avilPos1.add(1, j+1);
			 
			 arrivalEnd(layout,avilPos1, container);
			 
			 Vector<Integer> avilPos2=new Vector<Integer>() ;
			 avilPos2.add(0, i);
			 avilPos2.add(1, j-1);
			 
			 arrivalEnd(layout,avilPos2,container);

			 Vector<Integer> avilPos3=new Vector<Integer>() ;
			 avilPos3.add(0, i+1);
			 avilPos3.add(1, j);
			 
			 arrivalEnd(layout,avilPos3,container);
		
			 Vector<Integer> avilPos4=new Vector<Integer>() ;
			 avilPos4.add(0, i-1);
			 avilPos4.add(1, j);


			 arrivalEnd(layout,avilPos4,container);
			
			 break;
		case 2:
			 container.add(pos);
			 
			 Vector<Integer> avilPos5=new Vector<Integer>() ;
			 avilPos5.add(0, i);
			 avilPos5.add(1, j+1);

			 arrivalEnd(layout,avilPos5,container);

			 Vector<Integer> avilPos6=new Vector<Integer>() ;
			 avilPos6.add(0, i);
			 avilPos6.add(1, j-1);
//			 container.add(avilPos6);
			 arrivalEnd(layout,avilPos6,container);
			
			 break;
		case 3:
			 container.add(pos);
			 
			 Vector<Integer> avilPos7=new Vector<Integer>() ;
			 avilPos7.add(0, i+1);
			 avilPos7.add(1, j);

			 arrivalEnd(layout,avilPos7,container);

			 Vector<Integer> avilPos8=new Vector<Integer>() ;
			 avilPos8.add(0, i-1);
			 avilPos8.add(1, j);
			 
			 arrivalEnd(layout,avilPos8,container);
			 
			 break;
		case 4:
			 container.add(pos);

			 Vector<Integer> avilPos9=new Vector<Integer>() ;
			 avilPos9.add(0, i+1);
			 avilPos9.add(1, j);
//			 container.add(avilPos9);
			 arrivalEnd(layout,avilPos9,container);
			 break;
		case 5 :
			 container.add(pos);
			 
			 Vector<Integer> avilPos10=new Vector<Integer>() ;
			 avilPos10.add(0, i);
			 avilPos10.add(1, j+1);
			 
			 arrivalEnd(layout,avilPos10, container);
			 
			 Vector<Integer> avilPos11=new Vector<Integer>() ;
			 avilPos11.add(0, i);
			 avilPos11.add(1, j-1);
			 
			 arrivalEnd(layout,avilPos11,container);

			 Vector<Integer> avilPos12=new Vector<Integer>() ;
			 avilPos12.add(0, i+1);
			 avilPos12.add(1, j);
			 
			 arrivalEnd(layout,avilPos12,container);
		
			 Vector<Integer> avilPos13=new Vector<Integer>() ;
			 avilPos13.add(0, i-1);
			 avilPos13.add(1, j);


			 arrivalEnd(layout,avilPos13,container);
			 
			 break;
		case 6 :
			flag=true;
			break;
		}
	}
	public int[][] getLayout()
	{
		Scanner sc=new Scanner(System.in);
		int row=sc.nextInt();
		int col=sc.nextInt();
		int[][] layout=new int[row][col];
		for(int j=0;j<row;j++)
		{
			char[] chares=sc.next().toCharArray();
			for(int i=0;i<col;i++)
			{
				layout[j][i]=parseChar(chares[i]);
				if(layout[j][i]==5)
				{
					start_pos.add(0, j);
					start_pos.add(1, i);
				}
				if(layout[j][i]==6)
				{
					target_pos.add(0, j);
					target_pos.add(1, i);
				}
			}
		}
		sc.close();
		return layout;
	}
	public int parseChar(char cha)
	{
		int ans=0;
		switch (cha)
		{
		case '#':ans= 0;break;
		case '+':ans= 1;break;
		case '-':ans= 2;break;
		case '|':ans= 3;break;
		case '.':ans= 4;break;
		case 'S':ans= 5;break;
		case 'T':ans= 6;break;
		}
		return ans;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

