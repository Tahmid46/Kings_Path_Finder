
package pathfinding;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;


public class Solver {
    
    PriorityQueue<State> open_list;
    HashSet<String> closed_list;
    double cost=1;
    private class ComapareState implements Comparator<State>{

        @Override
        public int compare(State t, State t1) {
            
            if(t.f > t1.f){
                return 1;
            }
            
            if(t.f < t1.f){
                return -1;
            }
            else{
                return 0;
            }
        }
        
    }
    
   
        
    public Solver()
    {
        open_list=new PriorityQueue<>(new ComapareState());
        closed_list=new HashSet<>();
        cost=0;
    }
    
    //For octile
    public boolean AStar1(State start)
    {
        System.out.println("Octile");
        start.g=0;
        start.f=start.g+start.h_octile();
        open_list.add(start);
        
        while(!open_list.isEmpty()){
            
            State current=open_list.poll();
            
            //Checking destination
            if(Driver.a[current.xs][current.ys]==2){ //Destination
                System.out.println("Reached Goal..");
                
                octile_path_cost(current); //To calculate path cost
                System.out.println("Path Cost: "+cost);
                printPath(current); //print path
                return true;
            }
            
            closed_list.add(current.toString());
            
            for(State next: current.genNextState()){
                if(closed_list.contains(next.toString())){
                    continue;
                }
                
                next.f=next.g+next.h_octile();
                
                if(next.f>=100.0){ // >100 means block
                    closed_list.add(next.toString());
                    continue;
                }
                
                //If not a block then adding to open list
                if(open_list.contains(next)==false){
                   
                    open_list.add(next);
                }

            }
            
        }
        open_list.clear();
        closed_list.clear();
        return false;
    }
    
    
    //For manhattan
    public boolean AStar2(State start)
    {
        System.out.println("Manhattan");
        start.g=0;
        start.f=start.g+start.h_manhattan();
        open_list.add(start);
        int c=0;
        
        while(!open_list.isEmpty()){
            
            State current=open_list.poll();
            //System.out.println(current.f);
            if(Driver.a[current.xs][current.ys]==2){ //Destination
                System.out.println("Reached Goal");
                manhattan_path_cost(current);
                System.out.println("Path cost: "+cost);
                printPath(current);
                return true;
            }
            
            closed_list.add(current.toString());
            
            for(State next: current.genNextState()){
                if(closed_list.contains(next.toString())){
                    continue;
                }
                
                next.f=next.g+next.h_manhattan();
                if(next.f>=100.0){
                    closed_list.add(next.toString());
                    continue;
                }
                if(open_list.contains(next)==false){
                    
                    open_list.add(next);
                }

            }
            
        }
        
        return false;
    }
    
    
     //Calculates heuristics between current cell and it's parent cell
    double solverOctile(int xs,int ys,int xd,int yd)
    {
        double dist=0;
        
        dist=(Math.max(Math.abs(xd-xs),Math.abs(yd-ys))+0.41*Math.min(Math.abs(xd-xs),Math.abs(yd-ys)));
        
        return dist;
    }
    
    //Calculates heuristics between current cell and it's parent cell
     int solverManhattan(int xs,int ys,int xd,int yd)
    {
        int dist=0;
        dist=Math.abs(xd-xs)+Math.abs(yd-ys);
        return dist;
    }
    
     //This is used to calculate heuristic path cost for octile
    void octile_path_cost(State s)
    {
        if(s==null){
            return ;
        }
        if(s.parent!=null){
          //System.out.println(cost);
          cost= cost+solverOctile(s.xs,s.ys,s.parent.xs,s.parent.ys);
        }
         octile_path_cost(s.parent);
        
    }
    
    //This is used to calculate heuristic path cost for manhattan
    void manhattan_path_cost(State s)
    {
        if(s==null){
            return ;
        }
        if(s.parent!=null){
          //System.out.println(cost);
          cost= cost+solverManhattan(s.xs,s.ys,s.parent.xs,s.parent.ys);
        }
          manhattan_path_cost(s.parent);
    }
    
    void printPath(State x)
    {
        
        if(x==null) return;
        printPath(x.parent);
        System.out.println(x.toString());
    }
}
