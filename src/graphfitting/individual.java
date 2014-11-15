package graphfitting;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class individual {
	public double genes[];
        ArrayList<Point> points ;
	public double fitness_Value;
	public double selectionProb;
	public double cumulativeProb;
	public double lower_bound=-10;
	public double upper_bound=10;
	public static int ind_length ; 
	
	public individual(int _ind_length, ArrayList<Point> _points ){
                points = _points;
                ind_length=_ind_length;
		genes = new double[ind_length];
                
                upper_bound++;
		
		
		int idx=0;
               

		while( idx < ind_length){
                    double value =  lower_bound + (upper_bound - lower_bound) * new Random().nextDouble();
                    System.out.println(value);
                    // value-=10;
                    genes[idx++]=value;
                    //System.out.println(idx++);
		}			
                System.out.println(this.toString());
	}
	
	public individual(boolean flag,int _ind_length, ArrayList<Point> _points ){
                points = _points; 
                ind_length=_ind_length;
		genes = new double[ind_length];
	}
	

	public double FitnessFun(){
            double sum = 0.0 ;
            for(int i =0 ; i < points.size() ; i++)
            {
                double yCalc = 0.0;
                for(int j =0 ; j < genes.length ; j++)
                {
                    yCalc+=genes[j]*Math.pow(points.get(i).x,(double)j);
                  //  System.out.println(points.get(i).x+" "+Math.pow(points.get(i).x,(double)j));
                }
               // System.out.println(yCalc+" "+points.get(i).y);
                sum+=(yCalc - points.get(i).y)*(yCalc - points.get(i).y);
            }
            fitness_Value = sum/(double)points.size();
            return fitness_Value;
	}
    @Override
        public String toString()
        {
            String out = "";
            for(int i =0 ; i < genes.length ; i++){out+=" ";out+=Double.toString(genes[i]);}
            out+=" "+FitnessFun();
            return out;
        }
	
}

