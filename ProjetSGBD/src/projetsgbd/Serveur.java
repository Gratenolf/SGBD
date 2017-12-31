/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetsgbd;

public class Serveur extends Thread{
    private SGA sgabuff;
    private MemDisque memoireD;
    private boolean finProgramme;
    private String requete;
    private String ResRech;
    private Block ResRechBlock; 
    private int choixrequete;
    
    public Serveur(SGA sgabuff,MemDisque memoireD){
        this.sgabuff = sgabuff;
        this.memoireD = memoireD;
        finProgramme=false;
        ResRech="Pas";
        choixrequete=1;
    }
    
    public void update(String reqAvant,String reqApres){
        
    }
    public void recupBuffer(String requete){
        ResRech=sgabuff.rechBlock(requete);
    }
    public void recupDisque(String requete){
        ResRechBlock=memoireD.getElemMemDisque(requete);
    }
    public void run(){
        while(choixrequete<3){//finProgramme==false){
            switch(choixrequete){
                case 1: requete="baaaaaaaaabaaaaaaaaabaaaaaaaap";break;
                case 2: requete="azertyuiopazertyuiopazertyuioa";break;
                case 3: requete="azertyuiopazertyuiopazertyuiob";break;
                case 4: requete="azertyuiopazertyuiopazertyuioc";break;
                case 5: requete="azertyuiopazertyuiopazertyuiod";break;
            }
                
                System.out.println("Serveur effectue un select, enregistrement recherché :"+requete);
                recupBuffer(requete);
                if(ResRech.equals("Pas"))
                    System.out.println("requete: "+requete+" Erreur pas de recherche");
                else if(ResRech.equals("Pas de res")){
                        System.out.println("requete: "+requete+" Erreur pas de resultat dans le buffer");
                        recupDisque(requete);
                        if(ResRechBlock.getNumero()==-1){
                            System.out.println("requete: "+requete+" Erreur pas de resultat sur le disque");
                        }
                        else
                        {
                            System.out.println("requete: "+requete+" Un resultat venant de la mémoire disque!");
                            sgabuff.ajoutDonneeDisque(requete, ResRechBlock);
                        }
                }
                else{
                    System.out.println("requete: "+requete+" Un resultat venant de la mémoire centrale!");
                }
                choixrequete+=1;
                ResRech="Pas";
                System.out.println("Serveur effectue un update, ancien enregistrement :"+requete+" nouvel enrgistrement : aaaaaaaaaaaaaaaaaaaaaaaaaa");
                sgabuff.update(requete, "aaaaaaaaaaaaaaaaaaaaaaaaaa");
                System.out.println("Bloc en mémoire Cache: "+sgabuff.getDBBC().toString());
        }
    }
}
