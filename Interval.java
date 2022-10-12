public class Interval{
  //attributs : 
  private double a, b;
  private boolean takeA, takeB;              //take signifie si la valeur est incluse dans l'interval
                                                    //private Interval union; //l'interval union est égaal à {vide} si pas d'union prend une valeur de l'interval suivant et est appelable récursivement
  
  //constructeurs : 
  public Interval(){
    a = 0;
    b = 0;
    takeA = true;
    takeB = true;
  }
  
  public Interval(double x, double y){
    if(x > y){//au cas ou les arguments sont passés dans le mauvais ordre
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
    if(x > y){//au cas ou les arguments sont passés dans le mauvais ordre
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
    if(a == b){                   //1e cas : les bornes sont égales
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
    if(x > y){      //au cas ou les arguments sont passés dans le mauvais ordre
      b = x;
      a = y;
    }else{
      a = x;
      b = y;
    }
  }
    
  public void setInterval(double x, double y, boolean tA, boolean tB){
    if(x > y){      //au cas ou les arguments sont passés dans le mauvais ordre
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
  
  public double getInf(){//renvoies la borne inférieure de l'interval
      return a;
  }
  
  public double getSup(){//renvoies la borne supérieure de l'interval
    return b;
  }
  
  public boolean infTake(){//renvoies si la borne inférieure de l'interval est comprise
    return takeA;
  }
  
  public boolean supTake(){//renvoies si la borne supérieure de l'interval est comprise
    return takeB;
  }
  
  public boolean empty(){//retourne si l'interval est vide
    return a == b && takeB == false && takeA == false;
  }
  
  
}

//méthode union, à méditer :....

/*public void union(Interval k){    //d'abord vérifier si c'est vraiment nécessaire d'ajouter un deuxieme interval (au lieu d'agrandir l'interval actuel)
    if(k.empty()){
      return this;//pas d'union nécessaire si k est vide;
    }
    boolean disjoint = a > k.getSup() || b < k.getMin() || (a = k.getSup() && takeA == false && k.supTake() == false) || (b = k.getInf() && takeB == false && k.infTake() == false);//4 cas ou les intervals sont disjoints
    Interval res = new Interval;
    if(!disjoint){                  //si les deux intervals ont au moins un élt en commun
      if(a < k.getInf()){           // mettre la borne inf de l'union comme borne min des deux intervals
        res.setInf(a, takeA);
      }else if(a > k.getInf()){
        res.setInf(k.getInf(), k.infTake());
      }else{
        res.setInf(a, k.infTake()||takeA);          //si le min est égal, on change juste l'inclusion de la brone min si nécessaire
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
