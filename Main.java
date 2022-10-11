import java.util.*;
public class Main
{
  
  public static void main (String[]args){
   
    
    Polynome f = new Polynome();
    f.setCoef(1, -6);
    f.setCoef(2, -1);
    f.setCoef(3, 1);
    
    //System.out.println(f.racines());
    
    f.tableauSigne();
    

    
    
    /*//test de la regression linéaire :
    
    List<Point> p = new ArrayList<Point>();
    
    p.add(new Point(4, 2));
    p.add(new Point(10, 2));
   // p.add(new Point(1, 2));
   // p.add(new Point(5, 9));
   // p.add(new Point(2, -10));
    
    
    System.out.println(p);
    Polynome f = new Polynome();
    f = Polynome.regLin(p);
    
    System.out.println(f);
    
    
    */
    
     /*
    double[][] t = new double[4][4]; //systeme qui a pour solution 3 , -1, 0.5
    t[0][0] = 1;
    t[0][1] = 2;
    t[0][2] = 2;
    t[0][3] = -2;
    
    t[1][0] = 1;
    t[1][1] = 3;
    t[1][2] = -2;
    t[1][3] = 1;
    
    t[2][0] = 3;
    t[2][1] = 5;
    t[2][2] = 8;
    t[2][3] = -8;
  
    double[][] lie = new double[5][5];
    
    for(int i = 0 ; i < lie.length ; i++){//sélectionner toutes les lignes
        for(int j = 0 ; j < lie[i].length ; j++){  
          lie[i][j] = (i+1)*(j+1);
        }
    }
          
          
          
          
    SystemL s = new SystemL(t);
    System.out.println(s);
  
      
    
    s.echelloner();
    System.out.println(s);
    System.out.println(s.solution());
    */
    
    
    /*Polynome f = new Polynome();
    
    f.setCoef(4, 1);
    f.setCoef(2, -5);
    f.setCoef(0, 4);
    
    
    
    Polynome g = new Polynome();
        
    //g.setCoef(3, 1);
    g.setCoef(11, 1);
   
   
   
   
   
    System.out.println("g(x) = " + g);
    System.out.println("f(x) = " + f);*/
    
    
    
    /*
    System.out.println("f(x) - g(x) = " + f.moins(g));
    System.out.println("f(x) > g(x) si x € " + f.superieur(g));
    System.out.println("g est pair : " + g.pair());
    System.out.println("g est impair : " + g.impair());
    System.out.println("f croissante sur " + f.croissant());
    System.out.println("f convexe sur " + f.convexe());
    System.out.println("g(x) + 5 = " + g.plus(5));
    System.out.println("g(x) x 4 = " + g.mult(4));
    System.out.println("f(x) + g(x) = " + f.plus(g));
    System.out.println("f(x) x g(x) = " + f.mult(g));
    System.out.println("f(x) ^ 3 = " + f.puis(3));
    System.out.println("f(-1) = " + f.im(-1));
    System.out.println("f(19) = " + f.im(19));
    System.out.println("g(19) = " + g.im(19));
    System.out.println("degré de f = " + f.deg());
    System.out.println("f("+f.racines() + ") = 0");
    System.out.println("g'(x) = " +  g.derivee());
    System.out.println("G(x) = " +  g.primitive());
    System.out.println("integrale de -4 à 6 de f = " +  f.integrale(-4, 6));
    System.out.println("f("+f.regLin() + ") = 0");
    System.out.println("extremums de f = " +  f.extremums());
    System.out.println("tangente à f en 1 = " + f.tangente(1));
    System.out.println("f'(x) = " +  f.derivee());
    System.out.println("g("+g.regLin() + ") = 0");
    System.out.println("extremums de f = " +  f.derivee().regLin());
    System.out.println("extremums de f = " +  f.extremums());
    */

  }
}
