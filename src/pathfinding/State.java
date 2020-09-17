
package pathfinding;


public class State {
    
    int xs,ys,xd,yd;
    double g,f;
    State parent;
    //int grid[][];
    
    public State()
    {
        parent=null;
    }
    
    public State(int xs,int ys,int xd,int yd)
    {
        parent=null;
        this.xs=xs;
        this.ys=ys;
        this.xd=xd;
        this.yd=yd;
    }
    
    public State(State block)
    {
        parent=null;
        xs=block.xs;
        ys=block.ys;
        xd=block.xd;
        yd=block.yd;
        g=0;
    }
    
    double h_octile()
    {
        double dist=0;
        
        dist=(Math.max(Math.abs(xd-xs),Math.abs(yd-ys))+0.41*Math.min(Math.abs(xd-xs),Math.abs(yd-ys)));
        
        return dist;
    }
    
    int h_manhattan()
    {
        int dist=0;
        dist=Math.abs(xd-xs)+Math.abs(yd-ys);
        return dist;
    }
    
    public State[] genNextState()
    {
        State[] childs=new State[8];
        
         for(int i=0;i<8;i++){
            childs[i]=new State(this);
            childs[i].parent=this;
            //childs[i].g=this.g+1.0;
        }
        
         //Moving Up
         if(xs>0){
             childs[0].xs-=1;
             childs[0].ys=ys;
             childs[0].g=this.g+Driver.a[childs[0].xs][childs[0].ys];
         }
         
         //Moving Up-right
         if(xs>0 && ys < 7){
             childs[1].xs=childs[1].xs-1;
             childs[1].ys=childs[1].ys+1;
             childs[1].g=this.g+Driver.a[childs[1].xs][childs[1].ys];
         }
         
         //Moving Right
         if(ys < 7){
             childs[2].ys=childs[2].ys+1;
             childs[2].g=this.g+Driver.a[childs[2].xs][childs[2].ys];
         }
         
         //Moving Down-right
         if(xs < 7 && ys <7){
             childs[3].xs=childs[3].xs+1;
             childs[3].ys=childs[3].ys+1;
             childs[3].g=this.g+Driver.a[childs[3].xs][childs[3].ys];
         }
         
         //Moving Down
         if(xs < 7){
             childs[4].xs=childs[4].xs+1;
             childs[4].g=this.g+Driver.a[childs[4].xs][childs[4].ys];
         }
         
         //Moving Down-left
         if(xs < 7 && ys >0){
              childs[5].xs=childs[5].xs+1;
              childs[5].ys=childs[5].ys-1;
              childs[5].g=this.g+Driver.a[childs[5].xs][childs[5].ys];
         }
         
         //Moving Left
         if(ys > 0){
             childs[6].ys=childs[6].ys-1;
             childs[6].g=this.g+Driver.a[childs[6].xs][childs[6].ys];
         }
         
         //Moving Up-left
         if(xs > 0 && ys > 0){
             childs[7].xs=childs[7].xs-1;
             childs[7].ys=childs[7].ys-1;
             childs[7].g=this.g+Driver.a[childs[7].xs][childs[7].ys];
         }
         
        return childs;
    }
    
    @Override
    public String toString()
    {
        String s=""+xs+","+ys;
        return s;
    }

       
}
