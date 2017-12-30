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
public class DBBufferCache {
    private Block blocks [];
    private int nbBlocCourant;
    
    public DBBufferCache(){
        blocks = new Block[5];
        for(int i=0;i<5;i++){
            blocks[i] = new Block(nbBlocCourant);
        }
        nbBlocCourant=0;
    }
  
    public void remplir(String enr){
        boolean rempli=false;
        for(int i=0;i<5;i++){
            if(rempli==false){
                if(enr.getBytes().length<blocks[i].getTaille()){
                    blocks[i].addEnregistrement(enr,nbBlocCourant);
                    
                    rempli=true;
                }
                else{
                    nbBlocCourant+=1;
                }
            }
        }
        nbBlocCourant=0;
    }
     
    public String getElemMemCentrale(String requete){
        String res="Pas de res";
        for(int i=0;i<5;i++){
            if(blocks[i].getEnregistrement(requete))
               res="trouvé";
       }
         
       return res;
    }
}