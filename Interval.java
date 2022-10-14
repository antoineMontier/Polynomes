public class Interval{
  
  //parameters : 
  
  private double a, b;
  private boolean takeA, takeB;              //take means if the value is inside the interval
  
  //builder : 
  
  public Interval(){
    a = 0;
    b = 0;
    takeA = true;
    takeB = true;
  }
  
  public Interval(double x, double y){
    if(x > y){//in case the arguments are passed in the wrong order
      b = x;
      a = y;
    }else{
      a = x;
      b = y;
    }
    takeA = true;
    takeB = true;
  }
  
  public Interval(double x, double y, boolean tA, boolean tB ){
    if(x > y){//in case the arguments are passed in the wrong order
      b = x;
      a = y;
      takeA = tB;
      takeB = tA;
    }else{
      a = x;
      b = y;
      takeA = tA;
      takeB = tB;
    }
  }
  
  public Interval(Interval i){
    a = i.getInf();
    b = i.getSup();
    takeA = i.infTake();
    takeB = i.supTake();
  }
  
  //acceseurs :
  
  public String toString(){
    if(a == b){                   //1st case: the bounds are equal
      if(takeB && takeA){
        return"{" + a + "}";
      }
        return "{∅}";
    }
    String res = "";
    if(takeA){
      res += "[";
    }else{
      res += "]";
    }
    res += a + " ; " + b;
    if(takeB){
      res += "]";
    }else{
      res += "[";
    }
    return res;
  }
  
  public void setInf(double x){
    a = x;
  }
  
  public void setInf(double x, boolean tA){
    a = x;
    takeA = tA;
  }
  
    public void setSup(double x){
    b = x;
  }
  
  public void setSup(double x, boolean tB){
    b = x;
    takeB = tB;
  }
  
  public void setInterval(double x, double y){
    if(x > y){
      b = x;
      a = y;
    }else{
      a = x;
      b = y;
    }
  }
    
  public void setInterval(double x, double y, boolean tA, boolean tB){
    if(x > y){
      b = x;
      a = y;
      takeA = tB;
      takeB = tA;
    }else{
      a = x;
      b = y;
      takeA = tA;
      takeB = tB;
    }
  }
  
  public double getInf(){//returns the lower bound of the interval
      return a;
  }
  
  public double getSup(){//returns the upper bound of the interval
    return b;
  }
  
  public boolean infTake(){//returns if the lower bound of the interval is inside the interval
    return takeA;
  }
  
  public boolean supTake(){//returns if the upper bound of the interval is inside the interval
    return takeB;
  }
  
  public boolean empty(){//returns if the interval is empty
    return a == b && takeB == false && takeA == false;
  }
  
  
}

//union methode, to meditate :....

/*public void union(Interval k){    //first check if it is really necessary to add a second interval (instead of enlarging the current interval)
    if(k.empty()){
      return this;//no union needed if k is empty;
    }
    boolean disjoint = a > k.getSup() || b < k.getMin() || (a = k.getSup() && takeA == false && k.supTake() == false) || (b = k.getInf() && takeB == false && k.infTake() == false);//4 cas ou les intervals sont disjoints
    Interval res = new Interval;
    if(!disjoint){                  //if the two intervals have at least one element in common
      if(a < k.getInf()){           // put the lower bound of the union as the min bound of the two intervals
        res.setInf(a, takeA);
      }else if(a > k.getInf()){
        res.setInf(k.getInf(), k.infTake());
      }else{
        res.setInf(a, k.infTake()||takeA);          //if the min is equal, we just change the inclusion of the brone min if necessary
      }
      
      if(b > k.getSup()){               //borne sup
        res.setSup(b, takeB);
      }else if( b < k.getSup()){
        res.setSup(k.getSup(), k.supTake());
      }else{
        res.setSup(b, takeB || supTake())
      }
      this.union = res;
      return;
    }else{
      
    }
  }
  */
