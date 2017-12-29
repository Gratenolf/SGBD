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
            blocks[i] = new Block(nbBlocCourant);
            nbBlocCourant+=1;
        }
        nbBlocCourant=0;
    }
    
    public void Remplir(){
        for(int i=0;i<5;i++){
            blocks[i].addEnregistrement("a",nbBlocCourant);
        }
        nbBlocCourant=5;
    }
    public void Remplir(String enr){
        for(int i=0;i<5;i++){
            if(enr.getBytes().length<blocks[i].getTaille())
                blocks[i].addEnregistrement(enr,nbBlocCourant);
        }
    }
    
    
    
    public String getElemMemDisque(String requete){
       String res="Pas de res";
       for(int i=0;i<5;i++){
           if(blocks[i].getEnregistrement(requete))
               res="trouvé";
       }
         
       return res;
    }
    
}
