import java.util.*;
public class Polynome{
  public final static int MAX_DEG = 20;             //max degree of the polynome
  public final static double X_MIN = -1000;
  public final static double X_MAX = 1000;          //working interval
  public final static double PRECISION = 0.0001;    //précision des recherches




  //parameter :

  private double c[] = new double[MAX_DEG];   //array to keep the coefficients of the polynome like this : c[0]*x^0 + c[1]*x^1 + c[2]*x^2......

  //builder:
  
  public Polynome(){
    c[0] = 0;
  }
  
  public Polynome(double x){
    c[0] = x;
  }
  
  public Polynome(double[] a){
    if(a.length > MAX_DEG){
      System.out.println("error on the array bulider in Polynome Class");
      return;
    }
    for(int i = 0 ; i < a.length; i++){
      c[i] = a[i];
    }
  }
  
  
  
  public Polynome(Polynome p){
    for(int i = 0 ; i <= p.deg() ; i++){
      c[i] = p.getCoef(i);
    }
  }
  
  
  
  //access :
  
  /**
   * @return degree of the current polynome
   */
  public int deg(){
    int e = MAX_DEG - 1;
    while(c[e] == 0 && e > 0){
    e--;
    }
    return e;
  }
  
  public double getCoef(int i){
    if(i >= c.length || i < 0){
      System.out.println("error access coefficient n°"+ i);
      return 0;
    }
    return c[i];
  }
  
  public void setCoef(int i, double x){
    if(i < 0){
      System.out.println("error access coefficient n°"+ i);
      return;
    }else{
      c[i] = x;
    }
  }
  
  public String toString(){
    String res = "";
    if(deg() == 0){
      if(c[deg()] >= 0){
        return res + " + "  + c[deg()];
      }else{
        return res + " - "  + (-c[deg()]);
      }
    }
    for(int i = c.length - 1 ; i >= 0 ; i--){
      if(c[i] > 0){
        res += " + " + c[i] + "x^" + i;
      }else if(c[i] < 0){
        res += " - " + (-c[i]) + "x^" + i;
      }
    }
    return res;
  }
  
  /**
   * @return the coefficients in an array form
   */
  public double[] getCoefTab(){
    double r[] = new double[c.length];
    for(int i = 0 ; i < c.length ; i++){
      r[i]= c[i];
    }
    return r;
  }
  
  public double im(double x){
    double res = 0;
    for(int i = 0 ; i <= deg(); i++){
      res += c[i]*Math.pow(x, i);
    }
    return res;
  }
  
  //functions :
  
  public Polynome plus(Polynome g){
    Polynome r = new Polynome();
    int m = Math.max(g.deg(), this.deg());
    for(int i = 0 ; i < m ; i++){
      r.c[i] =  g.getCoef(i) + this.getCoef(i);
    }
    return r;
  }
  
  public Polynome minus(Polynome g){
    Polynome r = new Polynome();
    int m = Math.max(g.deg(), this.deg());
    for(int i = 0 ; i <= m ; i++){
      r.setCoef(i, this.getCoef(i) - g.getCoef(i));
    }
    return r;
  }
  
  public Polynome mult(Polynome g){
    Polynome r = new Polynome();
    if(g.deg() + deg() > MAX_DEG){
      System.out.println("error in multiplication function between polynome : " + this + " and " + g);
      return r;
    }
    for(int i = 0 ; i <= deg() ; i++){
      for(int j = 0 ; j <= g.deg() ; j++){
        r.c[i+j] += getCoef(i)*g.getCoef(j);
      }
    }
    return r;
  }
  
  public Polynome plus(double g){
    Polynome r = new Polynome(this);
    r.setCoef(0, r.getCoef(0) + g);
    return r;
  }
  
  public Polynome minus(double g){
    Polynome r = new Polynome(this);
    r.setCoef(0, r.getCoef(0) - g);
    return r;
  }
  
  public Polynome mult(double g){
    Polynome r = new Polynome();
    for(int i = 0 ; i < this.deg() ; i++){
      r.c[i] += this.getCoef(i)*g;
    }
    return r;
  }

  public Polynome pow(int n){
    Polynome res = new Polynome(this);
    if(n < -1){
      System.out.println("power must be > 0");
      return res;
    }
    if(n + this.deg() > MAX_DEG){
      System.out.println("power " + n + " applied to " + this + " is superior to the max (" + MAX_DEG + ")");
    }
    for(int  i = 1 ; i < n ; i++){
      res = res.mult(this);
    }
    return res;
  }
/*
  public List root(){               //use the discriminent methode to determinate roots of a polynome with deg < 3
    List<Double> s = new ArrayList<Double>();
    double d;
    if(deg() == 0){
      return s;
    }
    if(deg() == 1){
      s.add(-getCoef(0)/getCoef(1));
      return s;      
    }
    if(deg() == 2){
      d = getCoef(1)*getCoef(1) - 4*getCoef(2)*getCoef(0);
      if(d < 0){
        return s;
      }
      s.add((-getCoef(1) - Math.sqrt(d))/2*getCoef(2));
      s.add((-getCoef(1) + Math.sqrt(d))/2*getCoef(2));
      Collections.sort(s);
      return s;
    }

    System.out.println("utiliser la fonction racines pour calculer les racines d'un polynome de degré supérieur à 2");
    return s;
  }
  
  private void pureList(List<Double> l){
    if(!l.isEmpty()){
      for(int j = 0 ; j < l.size(); j++){
        for(int i = j+1 ; i < l.size() ; i++){
          if((l.get(i) - PRECISION) < l.get(j) && (l.get(i) + PRECISION) > l.get(j)){
            l.remove(i);        //retirer les doublons ( +/- marge)
          }
        }
      l.set(j, Math.round(l.get(j)*1/PRECISION)*PRECISION);     //arrondir
      }
    }
  }
  */
  
  private int sign(double e){
      if(e >= 0){
          return 1;
      }
      return 0;
  }
  
  
  
  /**
   * @return the solutions of f(x) = 0 in an ArrayList form
   */
  public List root()){
    List<Double> s = new ArrayList<Double>();

    int signe = sign(im(X_MIN));//1 if >= 0 ; 0 if < 0

    double min = X_MIN;
    double max = X_MIN;
    double sAvg;
    
    while(s.size() <= deg() && max < X_MAX - PRECISION){
      if(s.size() != 0){
        signe = sign(im(s.get(s.size() - 1)));
        min = s.get(s.size() - 1);
      }
      for(double pace = Math.abs(getCoef(deg())*PRECISION) ; pace >= PRECISION ; pace/=10){//pace is linked to polynome's degree
        max = min + pace;
        while(sign(im(max)) == signe && max <= X_MAX){
          min += pace;
          max += pace;
        }
      }
      sAvg = (min + max)/2.0;
      if(sAvg < (X_MAX - PRECISION) && sAvg > (- X_MAX + PRECISION)){
        s.add(sAvg);
      }
    }
    pureList(s); //my algorithm returns the same value +/- pace sometimes in the list which is not what we want to we "pure" the list
    return s;
  }
  
  
  
  
  
  public Polynome derivate(){
    Polynome r = new Polynome();
    for(int i = 0 ; i < deg() ; i++){
      r.setCoef(i, (i+1)*c[i+1]);
    }
    return r;
  }
  
  public Polynome primitive(){
    Polynome r = new Polynome();
    for(int i = 1 ; i <= deg()+1 ; i++){
      r.setCoef(i, (1.0/i)*c[i-1]);
    }
    return r;
  }
  
  public double integrale(double a, double b){
    return this.primitive().im(b) - this.primitive().im(a);
  }
  
  public List<Point> extremums(){
    List<Point> res = new ArrayList();//list with the coordinate of the extremums
    List<Double> abs = new ArrayList(this.derivate().root()));//x-axis list
    System.out.println(derivate());
    Point e = new Point();
    if(deg() <= 2){
      if(deg() == 2){
        e.setAbs(-getCoef(1)/(2.0*getCoef(2)));
        e.setOrd(this.im(e.getAbs()));
        res.add(e);
        return res;
      }
      return res;
    }
    for(int i = 0 ; i < abs.size() ; i++){
      e.setAbs(abs.get(i));
      e.setOrd(im(e.getAbs()));
      res.add(e);
    }
    return res;
  }
  
  public Polynome tangente(double a){
    Polynome r = new Polynome(derivate().im(a));
    System.out.println(r);
    Polynome k = new Polynome();
    
    k.setCoef(0, -a);     //T(a) = f'(a)(x-a) + f(a)
    k.setCoef(1, 1);
    r = r.mult(k);
    r = r.plus(im(a));
    
    return r;
  }
  
  /**
   * @return a list of interval where the polynome is > 0 that means if x in the intervals => f(x) > 0
   */
  public List<Interval> positif(){
    List<Double> c = root());                     //root list
    List<Interval> res = new ArrayList<Interval>();     //to be retured list
    Interval a = new Interval();                    //interval to modify
    int num = 0;              //index of the last value
    if(im(X_MIN) >= 0){
        a.setInf(X_MIN, false);     //initialise the opening of the interval if f(X_MIN) > 0
        num++;
      }
      
    for(int i = 0 ;  i < c.size() ; i++){
      
      if(num % 2 == 1){
        a.setSup(c.get(i), true);
        res.add(new Interval(a)); 
        num++;
      }else if(num % 2 == 0){
        a.setInf(c.get(i), true);
        num++;
      }
    }
    
    if(im(X_MAX) >= 0 ){
      a.setSup(X_MAX, false);
    }
    
    res.add(a);
    return res;
  }
  
  
  public List<Interval> superieur(double a){
    return this.plus(a).positif();
  }
  
  public List<Interval> superieur(Polynome k){
      return this.minus(k).positif();
  }
  
  public List<Interval> croissant(){
      return this.derivate().positif();// f asc if f'(x) > 0
  }
  
  public List<Interval> convexe(){
      return this.derivate().derivate().positif();// f convexe if f''(x) > 0
  }
  
  boolean even(){                                                                   //even <=> f(x) = f(-x)
      double limite = Math.min(Math.abs(X_MIN), Math.abs(X_MAX));
      for(double k = 0 ; k < limite ; k += PRECISION*100){
          if(Math.round(im(k)*100)/100 > Math.round(im(-k)*100)/100 + PRECISION || Math.round(im(k) *100)/100< Math.round(im(-k)*100)/100 - PRECISION){
              return false;
          }
      }
      return true;
  }
  
  boolean nonEven(){                                                                 //non-even <=> f(-x) = -f(x)
        double limite = Math.min(Math.abs(X_MIN), Math.abs(X_MAX));
        for(double k = 0 ; k < limite ; k += PRECISION*100){
          if(Math.round(im(-k)*100)/100 > Math.round(-im(k)*100)/100 + PRECISION || Math.round(im(-k) *100)/100< Math.round(-im(k)*100)/100 - PRECISION){
              return false;
          }
      }
      return true;
  }
  
  /**
   * @param list of points
   * @return a polynome that passes through all points 
   */
  public static Polynome regression(List<Point> l){
    Polynome res = new Polynome();
    
    
    if(l.size() < 1){
      System.out.println("la liste ne doit pace être vide");
      return res;
    }
    if(l.size() == 1){
      res.setCoef(0, l.get(0).getOrd()); //polynome is constant if one point only
      return res;
    }
    
    
    double[][] sys = new double[l.size()+1][l.size() + 1];  //array that will be used to build the system
    
    
    for(int i = 0 ; i < sys.length -1; i++){
      for(int j = 0 ; j < sys[0].length -1 ;j++){     //j deosn't go to the end because we use the end to keep y-axis
          sys[i][j] = Math.pow(l.get(i).getAbs(), l.size()-1 -j);
      }
      sys[i][sys[0].length -1] = -l.get(i).getOrd();
    }
    SystemL s = new SystemL(sys);
    s.scale();
    List<Double> rep = new ArrayList<Double>();
    rep = s.solution();

    for(int i = 0 ; i < rep.size() ; i++){
      res.setCoef(i, rep.get(rep.size() - i-1));
    }
    return res;
  }
  
  private int countChar(double n){
    for(int i = 1 ; i < 13 ; i++){ //stop at 1 000 000 000 000 = 10^{13 - 1}
      if(Math.abs(n) < Math.pow(10, i)){
        if(n >= 0){
          return i;
        }
        return i + 1;
      }
    }
    System.out.println("erreure méthode countChar dans le nombre : "+ n);
    return -1;
  }
  
  private void espace(int e, char c){
    for(int i = 0; i < e ; i++){
      System.out.printf("%c", c);
    }
  }
  
  public void tableauSigne(){
    int troncon_size = 20;
    int troncon_actuel = troncon_size;
    List<Interval> in = new ArrayList<Interval>();
    List<Double> rc = new ArrayList<Double>();
    rc = root());
    in = positif();
    System.out.println(rc);//récuperer les intervals ou la fonction est positive
    int troncons;
    if(in.size() == 1){
      if(in.get(0).getInf() == -in.get(0).getSup()){//si la fonction est de même signe
        troncons = 1;
      }else{
        troncons =2;
      }
    }else{
      troncons = rc.size() + 1;
    }
    
    //construire la première ligne :
    int largTot = troncon_size*(troncons) + rc.size()*2 + countChar(X_MAX) + countChar(X_MIN);
    System.out.println("");//sauter une ligne
    System.out.printf("x");
    System.out.printf(" | ");
    System.out.printf("%.2f", X_MIN); // afficher avec 2 ch apres la virgule
    
    troncon_actuel -= 1 + countChar(X_MIN) + 3; // 3 pour la virgule + les deux chiffres apres
    
    for(int i = 0; i < rc.size() ; i++){
      troncon_actuel -= countChar(rc.get(i))/2 + (countChar(rc.get(i)) % 2);
      espace(troncon_actuel, ' ');
      System.out.printf("%.2f", rc.get(i));
      troncon_actuel = troncon_size - countChar(rc.get(i))/2;
    }
    
    troncon_actuel -= countChar(X_MAX)+1;
    espace(troncon_actuel, ' ');
    System.out.printf("%.2f |\n", X_MAX);
    espace(largTot, '_');
    System.out.printf("\nsi|");
    espace(largTot -4, ' ');
    System.out.printf("|\ngn|");
    
    double test = X_MIN/2 + rc.get(0)/2;
    
    for(int i = 0 ; i < rc.size() ; i++){
      espace(troncon_size/2, ' ');
      if(im(test) > 0){
        System.out.printf("+");
      }else{
        System.out.printf("-");
      }
      espace(troncon_size/2, ' ');
      System.out.printf("0");
      test = rc.get(i)/2.0 ;
      if(i < rc.size() -1){
        test += rc.get(i+1)/2.0; 
      }else{
        test += X_MAX/2.0;
      }
    }
    espace(troncon_size/2, ' ');
    
    if(im(test) > 0){
        System.out.printf("+");
      }else{
        System.out.printf("-");
      }
     espace(troncon_size/2  +countChar(X_MAX) , ' ');
     System.out.printf("|");
  }
}
