/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetsgbd;

/**
 *
 * @author Adam
 */
public class Serveur extends Thread{
    public SGA sgabuff;
    public MemDisque memoireD;
    public boolean finProgramme;
    public String requete;
    public String ResRech;
    
    public Serveur(SGA sgabuff,MemDisque memoireD){
        this.sgabuff = sgabuff;
        this.memoireD = memoireD;
        finProgramme=false;
        ResRech="Pas";
    }
    public void recupBuffer(String requete){
        System.out.println("recupBuffer requete: "+requete);
        ResRech=sgabuff.rechBlock(requete);
    }
    public void recupDisque(String requete){
        System.out.println("recupDisque requete: "+requete);
        ResRech=memoireD.getElemMemDisque(requete);
    }
    public void run(){
        //while(finProgramme==false){
                requete="azertyuiopazertyuiopazertyuiop";
                System.out.println("Serveur s'est réveillé");
                recupBuffer(requete);
                if(ResRech.equals("Pas"))
                    System.out.println("Erreur pas de recherche");
                else if(ResRech.equals("Pas de res")){
                        System.out.println("Erreur pas de resultat dans le buffer");
                        recupDisque(requete);
                        if(ResRech.equals("Pas de res")){
                            System.out.println("Erreur pas de resultat sur le disque");
                        }
                        else
                        {
                            System.out.println("Un resultat venant de la mémoire disque!");
                        }
                }
                else
                    System.out.println("Un resultat venant de la mémoire centrale!");
        //}
    }
}
