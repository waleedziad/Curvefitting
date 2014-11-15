package graphfitting;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.*;


 public class Run_genetics {
	 Random rand;
         int degree ;
	 ArrayList<individual> population;
         ArrayList<Point> points ;
	double crossOver_Probability;
	double mutation_Probability;
	
	int[] select_index=new int [2];
	int[]  selection_result=new int[]{0,0};
 individual Run(ArrayList<Point> _points,int _degree,int iterationNum, int popSize, double _crossOver_Probability, double _mutation_Probability){
		degree = _degree;
                points = _points ;
                population = new ArrayList<individual>();
		crossOver_Probability = _crossOver_Probability;
		mutation_Probability = _mutation_Probability;
		rand = new Random();
		
		for(int i=0;i<popSize;++i)
			population.add(new individual(degree,points));
		
		for (int k=0;k<iterationNum;k++){
			double totalFitness = 0.0;
			for(int i=0;i<popSize;++i)
				totalFitness += population.get(i).FitnessFun();
			
			double total_Probability = 0.0;
		
			for(int i=0;i<popSize;++i){
				population.get(i).selectionProb = population.get(i).FitnessFun() / totalFitness;
				population.get(i).cumulativeProb = (total_Probability += population.get(i).selectionProb);
			}
			
			selection_result=SelectParents();
			CrossOver(selection_result[0], selection_result[1]);
			Mutate(selection_result[0], selection_result[1],k,iterationNum);
		}
		
		int index = 0;
		
		double bestFitness = population.get(0).FitnessFun();
		for(int i=1;i<popSize;++i){
			double tempFitness = population.get(i).FitnessFun();
			if(tempFitness < bestFitness) index = i;
		}
		
		return population.get(index);
	}
	
	int[] SelectParents(){
		
		select_index[0]=0;
		select_index[1]=0;
		
		double random_Probability = rand.nextDouble();
		for(int i=0;i<population.size();i++){
			if(random_Probability <= population.get(i).cumulativeProb){
				select_index[0] = select_index[1] = i;
				break;
			}
		}
		int stp = 0 ;
		while(select_index[0] == select_index[1]){
                    System.out.println(stp++);
			random_Probability = rand.nextDouble();
			for(int i=0;i<population.size();i++){
				if(random_Probability <= population.get(i).cumulativeProb){
					select_index[1] = i;
					break;
				}
			}
		}
		
	
	return select_index;
	}
	
	void CrossOver(int parent_Index1, int parent_Index2){
		if(rand.nextDouble() <= crossOver_Probability) 
		{
		int crossover_index = rand.nextInt(individual.ind_length-2);
		individual parent1 = population.get(parent_Index1);
		individual parent2 = population.get(parent_Index2);
		individual offspring1 = new individual(false,degree,points);
		individual offspring2 = new individual(false,degree,points);
		
		for(int i=0;i<=crossover_index;++i){
			offspring1.genes[i] = parent1.genes[i];
			offspring2.genes[i] = parent2.genes[i];
		}
		
		for(int i=crossover_index+1;i<individual.ind_length;++i){
			offspring1.genes[i] = parent2.genes[i];
			offspring2.genes[i] = parent1.genes[i];
		}
		
		population.add(parent_Index1, offspring1);
		population.remove(parent_Index1+1);
		population.add(parent_Index2, offspring2);
		population.remove(parent_Index2+1);
	}
	}
	void Mutate(int index1, int index2,int current_iteration,int num_iterations){
		individual c1 = population.get(index1);
		individual c2 = population.get(index2);
		double r2=0.0;
		double r3=0.0;
		double y=0.0;
		double deltay=0.0;
		
		for(int i=0;i<individual.ind_length;++i){
			if(rand.nextDouble() <= mutation_Probability)
				r2=rand.nextDouble();
			    r3=rand.nextDouble();
			if(r2<=0.5)
			{
			 y=c1.genes[i]-c1.lower_bound;
			deltay=y*Math.pow(r3,((double)(1-current_iteration)/num_iterations));
			c1.genes[i]+=deltay;
			}
			else if (r2>0.5)
			{
			y=c1.upper_bound-c1.genes[i];
			deltay=y*Math.pow(r3,((double)(1-current_iteration)/num_iterations));
			c1.genes[i]-=deltay;
			}
			
			//////////////////////////////////////////////////////////
		   if(rand.nextDouble() <= mutation_Probability)
					r2=rand.nextDouble();
			        r3=rand.nextDouble();
			if(r2<=0.5)
			{
			 y=c2.genes[i]-c2.lower_bound;
			deltay=y*Math.pow(r3,((double)(1-current_iteration)/num_iterations));
			c2.genes[i]+=deltay;
			}
			else if (r2>0.5)
			{
			y=c2.upper_bound-c2.genes[i];
			deltay=y*Math.pow(r3,((double)(1-current_iteration)/num_iterations));
			c2.genes[i]-=deltay;
			}
							}
	}
	  
}
