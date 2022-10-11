import java.util.*;
public class Polynome{          //ajouter un tableau de signes / var / les deux condensé en un seul
  public final static int DEG_MAX = 20;
  public final static double X_MIN = -1000;
  public final static double X_MAX = 1000; //périmètre de recherche de solutions
  public final static double PRECISION = 0.0001;//précision des recherches
  //public final Interval vide = new Interval();
  //attributs :

  private double c[] = new double[DEG_MAX];

  //constructeurs:
  
  public Polynome(){
    c[0] = 0;
  }
  
  public Polynome(double x){
    this.c = c;
    c[0] = x;
  }
  
  public Polynome(double[] a){
    if(a.length > DEG_MAX){
      System.out.println("erreur constructeur par tableau");
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
  
  //accesseurs :
  
  public int deg(){
    int e = DEG_MAX - 1;
    while(c[e] == 0 && e > 0){
        
    e--;
    //System.out.println("c[e] " + c[e] + "  e = " + e);
    }
    return e;
  }
  
  public double getCoef(int i){
    if(i >= c.length || i < 0){
      System.out.println("erreur d'accès coefficient n°"+ i);
      return 0;
    }
    return c[i];
  }
  
  public void setCoef(int i, double x){
    if(i < 0){
      System.out.println("erreur d'accès coefficient n°"+ i);
      return;
    }else{
      c[i] = x;
    }
    //System.out.println("coefficient n°" + i + " du polynome "+ this +" établi a " + x);
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
    
    /*if(c[deg()] > 0){
      return res + " + " + c[deg()] + "x^" + deg();
    }else{
      return res + " - " + (-c[deg()]) + "x^" + deg();
    }*/
  }
  
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
      //System.out.println(res);
    }
    return res;
  }
  
  //méthodes :
  
  public Polynome plus(Polynome g){
    Polynome r = new Polynome();
    int m = Math.max(g.deg(), this.deg());
    for(int i = 0 ; i < m ; i++){
      r.c[i] =  g.getCoef(i) + this.getCoef(i);
    }
    return r;
  }
  
  public Polynome moins(Polynome g){
    Polynome r = new Polynome();
    int m = Math.max(g.deg(), this.deg());
    for(int i = 0 ; i <= m ; i++){
      r.setCoef(i, this.getCoef(i) - g.getCoef(i));
      //System.out.println("opération " + this.getCoef(i) + " - " + g.getCoef(i) + " = " + r.c[i] + " pour le rang n°"+i );
    }
    return r;
  }
  
  public Polynome mult(Polynome g){
    Polynome r = new Polynome();
    if(g.deg() + deg() > DEG_MAX){
      System.out.println("erreur dans la méthode mult entre les polynomes : " + this + " et " + g);
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
  
  public Polynome moins(double g){
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

  public Polynome puis(int n){
    Polynome res = new Polynome(this);
    if(n < -1){
      System.out.println("la puissance doit être supérieure à 0");
      return res;
    }
    if(n + this.deg() > DEG_MAX){
      System.out.println("la puissance " + n + " appliquée au polynome " + this + " est superieur au max (" + DEG_MAX + ")");
    }
      //System.out.println(res);
    for(int  i = 1 ; i < n ; i++){
      res = res.mult(this);
    }
    return res;
  }

  public List root(){
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
  
  private void epurerlist(List<Double> l){
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
  
  
  private int sign(double e){
      if(e >= 0){
          return 1;
      }
      return 0;
  }
  
  
  
  public List racines(){
    List<Double> s = new ArrayList<Double>();

    int signe = sign(im(X_MIN));//1 positif ou nul et 0 négatif

    double min = X_MIN;
    double max = X_MIN;
    double sAvg;
    
    while(s.size() <= deg() && max < X_MAX - PRECISION){
      //System.out.println(max);
      if(s.size() != 0){
          //System.out.println("test if");
        signe = sign(im(s.get(s.size() - 1)));
        min = s.get(s.size() - 1);
      }
      //System.out.println("condition for : " + Math.abs(getCoef(deg())*0.1) +" >= " + PRECISION);
      for(double pas = Math.abs(getCoef(deg())*PRECISION) ; pas >= PRECISION ; pas/=10){    //le pas est égal au coef du x de plus haut degré
      //System.out.println("boucle for, pas réglé à " + pas);
        max = min + pas;
        while(sign(im(max)) == signe && max <= X_MAX){
            //System.out.println("boucle while min = " + min + "  max = " + max);
          min += pas;
          max += pas;                             //tant que l'image de pas par f est toujours du meme signe on augmente le max et le min de "pas"
                                                  //System.out.println("min = " + min + "    pas = " + pas + "    max = " + max + "   signe = " +(im(max) >= 0));
        }
                                                  //System.out.println("racine estimée : " + (min + max)/2 + " pour un pas de " + pas);
      }
      sAvg = (min + max)/2.0;
      
      if(sAvg < (X_MAX - PRECISION) && sAvg > (- X_MAX + PRECISION)){
        s.add(sAvg);
      }
    }
    epurerlist(s);// RAJOUTER UN TRI DE LA LISTE !!!! il faut retirer les doublons et les elements proche de X_MAX
    return s;
  }
  
  
  
  
  
  public Polynome derivee(){
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
     // System.out.println(i + "  =  " +c[i-1] );
    }
    return r;
  }
  
  public double integrale(double a, double b){
    return this.primitive().im(b) - this.primitive().im(a);
  }
  
  public List<Point> extremums(){
    List<Point> res = new ArrayList();//liste contenant des tableaux de coordonnées des extremums
    List<Double> abs = new ArrayList(this.derivee().racines());//liste des abscisses
    System.out.println(derivee());
    Point e = new Point();//liste des abscisses
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
    Polynome r = new Polynome(derivee().im(a));
    System.out.println(r);
    Polynome k = new Polynome();
    
    k.setCoef(0, -a);     //T(a) = f'(a)(x-a) + f(a)
    k.setCoef(1, 1);
    r = r.mult(k);
    r = r.plus(im(a));
    
    return r;
  }
  
  public List<Interval> positif(){
    List<Double> c = racines();                     //liste des racines
    List<Interval> res = new ArrayList<Interval>();     //liste du retour qui contiendra les intervals
    Interval a = new Interval();                    //interval à moduler pour pouvoire incrémenter la liste
    int num = 0;              //indice de la derniere valeure aoutée, est impair si 'linterval nest pas fermé'
    //System.out.println("les solutions de la fonction : " + c);
    
    if(im(X_MIN) >= 0){
        //System.out.println("xMin > 0");
        a.setInf(X_MIN, false);     //initialiser l'ouverture du premier interval si la fonction est déja positive
        num++;
        //System.out.println("l'interval est init à " + a);
      }
      
    for(int i = 0 ;  i < c.size() ; i++){
      
      if(num % 2 == 1){
        //System.out.println("tour de boucle n°" + i + " l'interval est en construction et vaut " + a);
        a.setSup(c.get(i), true);
        res.add(new Interval(a)); 
        //System.out.println("tour de boucle n°" + i + " l'interval est en construction et vaut " + a + " il a été ajouté a la liste qui vaut " + res);
        num++;
      }else if(num % 2 == 0){
        a.setInf(c.get(i), true);
        num++;
      }
      //System.out.println("fin de tour de boucle n°" + i + " la liste des intervals vaut : " + res);
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
      return this.moins(k).positif();
  }
  
  public List<Interval> croissant(){
      return this.derivee().positif();// f est croissante si f'(x) > 0
  }
  
  public List<Interval> convexe(){
      return this.derivee().derivee().positif();// f est convexe si f''(x) > 0
  }
  
  boolean pair(){                                                                   //pair <=> f(x) = f(-x)
      double limite = Math.min(Math.abs(X_MIN), Math.abs(X_MAX));
      for(double k = 0 ; k < limite ; k += PRECISION*100){
          if(Math.round(im(k)*100)/100 > Math.round(im(-k)*100)/100 + PRECISION || Math.round(im(k) *100)/100< Math.round(im(-k)*100)/100 - PRECISION){
              //System.out.println("err de parité à x = " + k);
              return false;
          }
      }
      return true;
  }
  
  boolean impair(){                                                                 //impair <=> f(-x) = -f(x)
        double limite = Math.min(Math.abs(X_MIN), Math.abs(X_MAX));
        for(double k = 0 ; k < limite ; k += PRECISION*100){
          if(Math.round(im(-k)*100)/100 > Math.round(-im(k)*100)/100 + PRECISION || Math.round(im(-k) *100)/100< Math.round(-im(k)*100)/100 - PRECISION){
              //System.out.println("err d'imparité à x = " + k);
              return false;
          }
      }
      return true;
  }
  
  public static Polynome regLin(List<Point> l){//le degré du polynome sera l.size() - 1
    Polynome res = new Polynome();
    
    
    if(l.size() < 1){
      System.out.println("la liste ne doit pas être vide");
      return res;
    }
    if(l.size() == 1){
      res.setCoef(0, l.get(0).getOrd()); //s'il y a qu'un point,le polynome est une fonction constante
      return res;
    }
    
    
    double[][] sys = new double[l.size()+1][l.size() + 1];  //tableau qui servera a declarer le système
    
    
    for(int i = 0 ; i < sys.length -1; i++){
      for(int j = 0 ; j < sys[0].length -1 ;j++){     //j ne va pas jusqu'au bout car la derniere colonne est utilisée pour stocker les ordonnées
          sys[i][j] = Math.pow(l.get(i).getAbs(), l.size()-1 -j);
        //System.out.printf("(%d;%d)", i, j);
      }
      sys[i][sys[0].length -1] = -l.get(i).getOrd();
      //System.out.printf("(%d;%d)", i, sys[0].length -1);
      //System.out.println("");
    }
             // sys[i][j] = Math.pow(l.get(i).getAbs(), l.size()-1 -j);
      //sys[i][sys[0].length - 2] = -l.get(i).getOrd();

/*affichage tableau : 
    for(int i = 0 ; i < sys.length ; i++){
      for(int j = 0 ; j < sys[0].length ;j++){//j ne va pas jusqu'au bout car la derniere colonne est utilisée pour stocker les ordonnées
       System.out.printf("%f\t", sys[i][j]);
      }
      System.out.printf("\n");
    }
*/

      // System.out.println("coeff " + sys[i][sys[0].length - 2] + " mis à " + i + " ; " + (sys[0].length - 2));
       // System.out.println("coeff " + sys[i][j] + " mis à " + i + " ; " + j + " puissance = " + (l.size()-1 -j));
    SystemL s = new SystemL(sys);
    //System.out.println(s);
  
    s.echelloner();
    //System.out.println(s);
    
    List<Double> rep = new ArrayList<Double>();
    rep = s.solution();

    //System.out.println(rep);
    
    for(int i = 0 ; i < rep.size() ; i++){
      res.setCoef(i, rep.get(rep.size() - i-1));
    }
    return res;
  }
  
  private int compterChar(double n){
    for(int i = 1 ; i < 13 ; i++){ //on s'arrete au milier de miliard
      if(Math.abs(n) < Math.pow(10, i)){
        if(n >= 0){
          return i;
        }
        return i + 1;
      }
    }
    System.out.println("erreure méthode compterChar dans le nombre : "+ n);
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
    rc = racines();
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
    int largTot = troncon_size*(troncons) + rc.size()*2 + compterChar(X_MAX) + compterChar(X_MIN);
    System.out.println("");//sauter une ligne
    System.out.printf("x");
    System.out.printf(" | ");
    System.out.printf("%.2f", X_MIN); // afficher avec 2 ch apres la virgule
    
    troncon_actuel -= 1 + compterChar(X_MIN) + 3; // 3 pour la virgule + les deux chiffres apres
    
    for(int i = 0; i < rc.size() ; i++){
      troncon_actuel -= compterChar(rc.get(i))/2 + (compterChar(rc.get(i)) % 2);
      espace(troncon_actuel, ' ');
      System.out.printf("%.2f", rc.get(i));
      troncon_actuel = troncon_size - compterChar(rc.get(i))/2;
      
    }
    
    troncon_actuel -= compterChar(X_MAX)+1;
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
     espace(troncon_size/2  +compterChar(X_MAX) , ' ');
     System.out.printf("|");
  }
}
