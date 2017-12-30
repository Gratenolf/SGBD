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
public class MemDisque {
    public Block[] blocks;
    public int nbBlocCourant;
    
    public MemDisque(){
        blocks = new Block[5];
        for(int i=0;i<5;i++){
            blocks[i] = new Block(i);
        }
        nbBlocCourant=0;
    }
    
    public void remplir(String enr){
        boolean rempli=false;
        for(int i=0;i<5;i++){
            if(rempli==false){
                
                if(enr.getBytes().length < blocks[i].getTaille()){
                    blocks[i].addEnregistrement(enr,nbBlocCourant);
                    rempli=true;
                }
                else
                {
                    nbBlocCourant+=1;
                }
            }
        }
        nbBlocCourant=0;
    }
    
    
    
    public String getElemMemDisque(String requete){
       String res="Pas de res";
       System.out.println("Sur le point Disque requete: "+requete);
       for(int i=0;i<5;i++){
           if(blocks[i].getEnregistrement(requete))
               res="trouvé";
       }
         
       return res;
    }
    
}
