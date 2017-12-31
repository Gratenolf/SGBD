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
    
    public void RecopieBlock(Block bl,int indice){
    for(int j=0;j<bl.getEnregistrement().size();j++){
            block[indice].getEnregistrement().remove(j);
            block[indice].getEnregistrement().add(j,bl.getEnregistrement(j).toString());
        }
    }
    
     public void insertEnregistrement(String enr){
        ecriture=true;
        int res = remplir(enr, 0, false);
        if(res == -1)
            System.out.println("Erreur : mémoire dépassée.");
        ecriture=false;
    }
    
    private int remplir(String enr, int id, boolean decoup){
        boolean pos = false;
        int retour = 0;
        if((enr.getBytes().length + block[id].getMemoirePrise()) <= block[id].getTaille()){
            block[id].addEnregistrement(enr, -1);
            pos = true;
            if(decoup)
                return id;
            else
                return 0;
        }
        else if(enr.length() >= 40){
            int nbDecoup = 0;
            if(block[id].Pourcentage() < 95){
                if(block[id].Pourcentage() >= 90){
                    nbDecoup = 20;
                }
                else{
                    int bytesRestant = block[id].getTaille() - block[id].getMemoirePrise();
                    int seuilDecoup = bytesRestant;
                    if(enr.length() - seuilDecoup < 20)
                        nbDecoup = enr.length() - 20;
                    else
                        nbDecoup = seuilDecoup;
                }
                String subStr = enr.substring(0,nbDecoup);
                int lien = remplir(enr.substring(nbDecoup), (id + 1), true);
                block[id].addEnregistrement(subStr, lien);
                pos = true;
            }
        }
        if(id < block.length && pos == false)
            retour = remplir(enr, (id + 1), decoup);
        else if(retour == 0 && pos == false)
            retour = -1;
        pourcentage();
        return retour;
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
       if(ecriture==false){
            for(int i=0;i<5;i++){
                if(block[i].getEnregistrement(requete)){
                    copie(temp,block[i]);
                    temp.setIndiceDisque(i);
                }
                    
            }
       }
       else{
           System.out.println("Ecriture sur le disque en cours, veuillez réessayer plus tard");
       }
       lecture=false;
       return temp;
    }
    
    public void pourcentage(){
        int pct = 0;
        for(int i = 0; i < block.length; i++){
            System.out.println("Bloc "+ i +" : occupé à "+ block[i].Pourcentage() +"%.");
            pct += block[i].Pourcentage();
        }
        System.out.println("mémoire occupé à "+ (pct / block.length) +"%.");
    }
    
    public String toString(){
        String s = new String();
        for(int i=0;i<block.length;i++)
            s+=block[i].toString();
        return s;
    }
    
}
