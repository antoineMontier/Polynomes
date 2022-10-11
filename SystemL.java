import java.util.*;
public class SystemL{//UTILISER DES MATRICES POUR RESOUDRE + PLUS SIMPLE
    //attributs :
    private double c[][]; // double tableau pour stocher les coefficients, l'opposé de la derniere case designe le nombrer auquel la ligne doit être égale /ou plutot {..... + c[i][length] = 0 
    private List<String> inconnues = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g","h","i","j","k","l", "m", "n", "o", "p", "q", "r", "s", "t"));//20 inconnues
    private int nbL, nbC; //nombre de lignes et colonnes -1 à chaque fois


    //constructeurs :
   
    public SystemL(double[][] k){
        if(k[0].length-1 > k.length){         //si le nombre de coef esty superieur au nb de lignes, solutions infinies...
            System.out.print("Systeme lie");
            c = new double[0][0];
            return;
        }
        c = new double[k.length][k[0].length];
        for(int i = 0 ; i < k.length ; i++){
            for(int j = 0 ; j < k[i].length ; j++){ //k[0] aurait du etre correct car les lignes doivent faire la meme taille (coefficients = 0 sinon)
                c[i][j] = k[i][j];
            }
        }
        nbC = k[0].length - 1;
        nbL = k.length - 2;
    }
    
    //accesseurs :
    
    public String toString(){
        String res = "";
        for(int i = 0 ; i <= nbL ; i++){
            res += "{";
            for(int j = 0 ; j < nbC -1; j++){ //k[0] aurait du etre correct car les lignes doivent faire la meme taille (coefficients = 0 sinon)
                res += c[i][j] + "*" + inconnues.get(j) + " + ";
            }
            res += c[i][nbC -1] + "*" + inconnues.get(nbC-1) + " + " + (c[i][nbC])  + " = 0\n";
        }
        return res;
    }
    
    //méthodes :
    public void supprimerCol(int n){
      if(n < 0 || n > nbC){
        System.out.println("impossible de supprimer la colonne " + n);
        return;
      }
      for(int i = 0 ; i <= nbL ; i++){//sélectionner toutes les lignes
        for(int j = n+1 ; j <= nbC ; j++){
          //System.out.println("dans la ligne " + i + " j'écrase le coef n°"+ (j-1)+" en le remplacant par le coef n°" + j);
          c[i][j - 1] = c[i][j];
        }
      }
      inconnues.remove(n);
      nbC--; //le nombre de colonnes décrémente de 1
    }
    
    public void supprimerLigne(int n){
      if(n < 0 || n > nbL){
        System.out.println("impossible de supprimer la ligne " + n);
        return;
      }
      for(int j = 0 ; j <= nbC ; j++){//sélectionner toutes les colonnes
        for(int i = n+1 ; i <= nbL ; i++){ // sélectionner les bonnes lignes
          c[i-1][j] = c[i][j];
        }
      }
      nbL--;
    }
    
    public void multiplierLigne(int n, double x){
      if(n < 0 || n > nbL){
        System.out.println("impossible de multiplier la ligne " + n + " par " + x);
        return;
      }
      for(int j = 0 ; j <= nbC ; j++){
        c[n][j] *= x;
      }
    }
    
    public void swapLigne(int dest, int src){
      if(dest < 0 || dest > nbL){
        System.out.println("impossible d'echanger la ligne " + dest +" par " + src);
        return;
      }
      if(src < 0 || src > nbL){
        System.out.println("impossible d'echanger la ligne " + dest +" par " + src);
        return;
      }
      double tmp;
      for(int j = 0 ; j <=nbC ; j++){
        tmp = c[dest][j];
        c[dest][j] = c[src][j];
        c[src][j] = tmp;
      }
    }
    
    public void addLigne(int dest, int src, double coef){ //<=>  L(dest) <- L(dest) + coef * L(source)
      if(coef == 0){
        return;//pas de modif à faire si on multiplie la ligne par 0
      }
      if(dest < 0 || dest > nbL){
        System.out.println("impossible d'additionner la ligne " + dest + " " + coef +" fois par " + src);
        return;
      }
      if(src < 0 || src > nbL){
        System.out.println("impossible d'additionner la ligne " + dest + " " + coef +" fois par " + src);
        return;
      }
      for(int j = 0 ; j <=nbC ; j++){
        c[dest][j] += coef*c[src][j];
      }
    }
    
    public void supprimerLigneVide(){
      List<Integer> l = new ArrayList<Integer>(); //stocker le n° des lignes vides
      boolean vide;
      for(int i = 0 ; i <= nbL ; i++){
        vide = true;//vide est vrai au debut de l'analyse de chaque ligne
        for(int j = 0 ; (j <= nbC) && vide ; j++){
            vide = vide && (c[i][j] == 0);
        }
        if(vide){
          l.add(i);//ajouter i si la ligne i est vide
        }
      }
      for(int i = l.size() -1 ; i >= 0 ; i--){
        supprimerLigne(l.get(i));
      }
    }
    
    public void supprimerColVide(){
      List<Integer> l = new ArrayList<Integer>(); //stocker le n° des colonnes vides
      boolean vide;
      for(int j = 0 ; j <= nbC ; j++){
        vide = true;//vide est vrai au debut de l'analyse de chaque colonne
        for(int i = 0 ; (i <= nbL) && vide ; i++){
            vide = vide && (c[i][j] == 0);
        }
        if(vide){
          l.add(j);//ajouter j si la colonne j est vide
        }
      }
      for(int i = l.size() -1 ; i >= 0 ; i--){
        supprimerCol(l.get(i));
      }
    }
    
    
    
    //solution :
    
    public void echelloner(){                 //méthode de gauss
       // List<Double> r = new ArrayList<Double>();   //stocker les réponses
         //réduire la taille du systeme si des lignes/colonnes sont inutiles
      supprimerColVide();
      supprimerLigneVide();
      int pivot;
      
      for(int k = 0  ; k < nbL ; k++){//il faut repeter la boucle principale n-2 fois (soit nbL -1)
      
        
        
        pivot = k;
        
        while(c[pivot][k] == 0){//tant que le coef qui fait office de début de ligne est égal à 0 on cherche en dessous pour trouver le pivot
            pivot++;      //trouver pivot
          }
          
        //System.out.println("le pivot au rang " + k + " est " + pivot);
        if(pivot != k){
            swapLigne(k, pivot);    //mettre le pivot en tête du système
            pivot = k;
          }
        
        multiplierLigne(pivot, 1/c[pivot][k]);  //le premier coef du pivot doit être 1
        
        for(int l = k+1 ; l <= nbL ; l++){
          addLigne(l, pivot, -c[l][k]);   //echelloner les lignes en dessous
          }
        supprimerColVide();
        supprimerLigneVide(); //re-supprimer les lignes/colonnes vides permet de retirer si jamais certainnes lignes sont liées
        }
    }
    
    
    public List<Double> solution(){
      List<Double> sol = new ArrayList<Double>();
      
      //résoudre la derniere ligne "à la main":
      sol.add(-c[nbL][nbC]/c[nbL][nbC - 1]);
      double r = 0;
      for(int l = nbL-1 ; l >= 0 ; l--){//principe de la remontée en partant du bas    ;   l est le n° de ligne sur laquelle on travaille
        r = -c[l][nbC]; // faire passer le terme constant en negatif
        //System.out.println("ligne " + l + " avant multiplication des coefficients, le resultat est egal a "+ r);
        for(int b = nbL ; b > l ; b--){
          r -= sol.get(nbL - b)*c[l][b]; 
          //System.out.println("b = " + b + " pour l = " + l);
        }
        //System.out.println("ligne " + l + " avant division, le resultat est egal a "+ r);
        r/=c[l][l];
        sol.add(r);
      }
      if(sol.size() == nbC){
        Collections.reverse(sol);//renverser car on part de la fin avec le principe de la remontée
        return sol; 
      }
      System.out.println("systeme insolvable");     //si le nombre de solutions ne coincide pas avec le nombre d'inconnues, on abaandonne
      sol.clear();
      return sol;
    }
    
    
    
    
    
    
    
    
    
}



