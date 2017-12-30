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
    public Block[] block;
    private int nbBlocCourant;
    private boolean lecture;
    private boolean ecriture;
    
    public MemDisque(){
        block = new Block[5];
        for(int i=0;i<5;i++){
            block[i] = new Block(i);
        }
        nbBlocCourant=0;
        lecture=false;
        ecriture=false;
    }
    
    public boolean getLecture(){
        return lecture;
    }
    
    public void remplir(String enr){
        ecriture=true;
        boolean rempli=false;
        if(lecture==false){
            for(int i=0;i<5;i++){
                if(rempli==false){

                    if(enr.getBytes().length < block[i].getTaille()){
                        block[i].addEnregistrement(enr,nbBlocCourant);
                        rempli=true;
                    }
                    else
                    {
                        nbBlocCourant+=1;
                    }
                }
            }
        }
        ecriture=false;
        nbBlocCourant=0;
    }
    
    
    public void copie(Block Dest, Block Source){
        for(int j=0;j<Source.getEnregistrement().size();j++){
            Dest.addEnregistrement(Source.getEnregistrement(j).toString(), j);
        }
        Dest.setNumero(Source.getNumero());
    }
        
    public Block getElemMemDisque(String requete){
       lecture=true;
       Block temp = new Block(-1);
       System.out.println("Sur le point Disque requete: "+requete);
       if(ecriture==false){
            for(int i=0;i<5;i++){
                if(block[i].getEnregistrement(requete)){
                    copie(temp,block[i]);  
                }
                    
            }
       }
       else{
           System.out.println("Ecriture sur le disque en cours, veuillez réessayer plus tard");
       }
       lecture=false;
       return temp;
    }
    
}
