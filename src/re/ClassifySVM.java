package re;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;
import libsvm.svm_parameter;
import libsvm.svm_problem;

public class ClassifySVM {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int [] classes={1,6};
   ClassifySVM cs=   new ClassifySVM();
    cs.readFile("E:/ChinaUnicomContest/21127_svm.csv",classes);
//    cs.modelTrain(2048.0, 0.5, "E:/ChinaUnicomContest/22015_svm.csv", classes, 104);
	}
	public svm_model  modelTrain(double c,double g,String file,int[] classes,int number,int modelNumber,double r) throws NumberFormatException, IOException
	{
		new Classification().writerDensity(classes[0], classes[1],"E:/ChinaUnicomContest/123_"+modelNumber+".csv" , file,r);
		double[] labeles=new double[number];
		svm_node[][] testData = new svm_node[number][6];
		BufferedReader br=new BufferedReader(new FileReader(file));
		String line=br.readLine();
		int index=0;
		while((line=br.readLine())!=null)
		{
			Vector vec=new Vector();
			String[] information=line.split(",");
			double numberOffirst=log(1+Double.parseDouble(information[17]),100);
			vec.add(0,numberOffirst);
			double orderOffirst=Double.parseDouble(information[18]);
			vec.add(1,orderOffirst);
			double numberOfSec=log(1+Double.parseDouble(information[19]),100);
			vec.add(2,numberOfSec);
			double orderOfSec=Double.parseDouble(information[20]);
			vec.add(3,orderOfSec);
			double diffNum=Double.parseDouble(information[22])/100;
			vec.add(4,diffNum);
			double diffOrder=Double.parseDouble(information[21]);
			vec.add(5,diffOrder);
			for(int j=0;j<6;j++)
			{
				testData[index][j]=new svm_node();
				testData[index][j].index=j+1;
				testData[index][j].value=(double) vec.get(j);
			}
			
			
			int classfication=Integer.parseInt(information[7]);
			int label=0;
			if(classfication==classes[0])
				label=1;
			else
				label=-1;
			labeles[index]=(double)label;	
			index++;
		}
		//初始化模型参数
      	svm_problem sp = new svm_problem(); 
      		sp.x=testData;
      		sp.y=labeles;
      		sp.l=number; 
      		 //初始化svm参数
        svm_parameter prm = new svm_parameter();  
             //
            prm.svm_type = svm_parameter.C_SVC;  
            prm.kernel_type = svm_parameter.RBF;  
            prm.C = c;    
           prm.gamma =9; 
            prm.eps = 0.0001;
            prm.probability=1;
            
            return svm.svm_train(sp, prm); 
	}
	public svm_model  modelTrain(double c,double g,String file,int[] classes,int number,int modelNumber,int neiborNumber) throws NumberFormatException, IOException
	{
		new Classification().writerDensity(classes[0], classes[1],"E:/ChinaUnicomContest/123_"+modelNumber+".csv" , file,neiborNumber);
		double[] labeles=new double[number];
		svm_node[][] testData = new svm_node[number][6];
		BufferedReader br=new BufferedReader(new FileReader(file));
		String line=br.readLine();
		int index=0;
		while((line=br.readLine())!=null)
		{
			Vector vec=new Vector();
			String[] information=line.split(",");
			double numberOffirst=log(1+Double.parseDouble(information[17]),100);
			vec.add(0,numberOffirst);
			double orderOffirst=Double.parseDouble(information[18]);
			vec.add(1,orderOffirst);
			double numberOfSec=log(1+Double.parseDouble(information[19]),100);
			vec.add(2,numberOfSec);
			double orderOfSec=Double.parseDouble(information[20]);
			vec.add(3,orderOfSec);
			double diffNum=Double.parseDouble(information[22])/100;
			vec.add(4,diffNum);
			double diffOrder=Double.parseDouble(information[21]);
			vec.add(5,diffOrder);
			for(int j=0;j<6;j++)
			{
				testData[index][j]=new svm_node();
				testData[index][j].index=j+1;
				testData[index][j].value=(double) vec.get(j);
			}
			
			
			int classfication=Integer.parseInt(information[7]);
			int label=0;
			if(classfication==classes[0])
				label=1;
			else
				label=-1;
			labeles[index]=(double)label;	
			index++;
		}
		//初始化模型参数
      	svm_problem sp = new svm_problem(); 
      		sp.x=testData;
      		sp.y=labeles;
      		sp.l=number; 
      		 //初始化svm参数
        svm_parameter prm = new svm_parameter();  
             //
            prm.svm_type = svm_parameter.C_SVC;  
            prm.kernel_type = svm_parameter.RBF;  
            prm.C = c;    
           prm.gamma =9; 
            prm.eps = 0.0001;
            prm.probability=1;
            
            return svm.svm_train(sp, prm); 
	}
    public void readFile(String file,int[] classes) throws IOException
    {
    	try {
			BufferedReader br=new BufferedReader(new FileReader(file));
			FileWriter fw=new FileWriter("D:/Libsvm/libsvm-3.21/libsvm-3.21/tools/file");
			String line=br.readLine();
			while((line=br.readLine())!=null)
			{
				String[] information=line.split(",");
				
				int classfication=Integer.parseInt(information[0]);
				int label=0;
				if(classfication==classes[0])
					label=1;
				else
					label=-1;
				double numberOffirst=log(1+Double.parseDouble(information[1]),100);
				int orderOffirst=Integer.parseInt(information[2]);
				double numberOfSec=log(1+Double.parseDouble(information[3]),100);
				int orderOfSec=Integer.parseInt(information[4]);
				double diffNum=Double.parseDouble(information[6])/100;
				int diffOrder=Integer.parseInt(information[5]);
				fw.write(label+" "+1+":"+numberOffirst+" "+2+":"+numberOfSec+" "+3+":"+orderOffirst
						+" "+4+":"+orderOfSec+" "+5+":"+diffNum+" "+6+":"+diffOrder +"\n");
				System.out.println(label+" "+1+":"+numberOffirst+" "+2+":"+numberOfSec+" "+3+":"+orderOffirst
						+" "+4+":"+orderOfSec+" "+5+":"+diffNum+" "+6+":"+diffOrder +"\n");				
						
			}
			fw.flush();
			fw.close();
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public double log(double value, double base) {

    	return Math.log(value) / Math.log(base);

    }
}
