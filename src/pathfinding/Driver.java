
package pathfinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.stream.Stream;


public class Driver {

     public static int a[][]=new int [8][8]; //Input array
    
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
    
       Scanner input = new Scanner (new File("src/graph.txt"));
      
       //Graph input from file
       for(int i=0;i<8;i++){
           for(int j=0;j<8;j++){
                if(input.hasNextInt())
                {
                    a[i][j] = input.nextInt();
                }
           }
       }
       int Xs=0,Ys=0,Xd=0,Yd=0;
       
       //Taking source and destination coordinates
       for(int i=0;i<8;i++){
           for(int j=0;j<8;j++){
               if(a[i][j]==0){
                   Xs=i;
                   Ys=j;
               }
               if(a[i][j]==2){
                   Xd=i;
                   Yd=j;
               }
           }
           
       }
       
       State s1=new State(Xs,Ys,Xd,Yd);
       State s2=new State(Xs,Ys,Xd,Yd);
       
       Solver solver1=new Solver();
       System.out.println(solver1.AStar1(s1));
       
       Solver solver2=new Solver();
       System.out.println();
       System.out.println(solver2.AStar2(s2));
      
    }
    
}
