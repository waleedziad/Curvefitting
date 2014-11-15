
package graphfitting;

import java.util.ArrayList;
import java.util.Scanner;

public class GraphFitting {

    public static void main(String[] args) {
        int t ;
        Scanner in = new Scanner(System.in);
        t = in.nextInt();
        while(t>0){
            int n,d ;
            n = in.nextInt();
            d = in.nextInt();
            System.out.println( n+" <-> "+d);
            ArrayList<Point> points=new ArrayList<Point>() ;
            for(int i =0 ; i < n ; i++)
            {
                Point p = new Point(in.nextInt(), in.nextInt());
                System.out.println(p.x);
                points.add(p);
            }
            System.out.println("----------------End Input-------------");
                Run_genetics runner = new Run_genetics();
		individual solution = runner.Run(points,d+1,100, 100, 0.85, 0.01);
		System.out.println(solution);
                t--;
        }
    }
}
