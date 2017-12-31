/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetsgbd;
import java.util.ArrayList;

public class DBBufferCache {
    private Block block [];
    private int nbBlockList;
    private int nbBlocCourant;
    private boolean ecriture;
    private boolean modif;
   
    
    public DBBufferCache(){
        block = new Block[5];
        for(int i=0;i<5;i++){
            block[i] = new Block(nbBlocCourant);
        }
        nbBlocCourant=0;
        modif=false;
        nbBlockList=4;
    }
    
    public Block[] getBlock(){
        return block;
    }
    
    public int getNbBlock(){
        return block.length;
    }
    
    public boolean getModif(){
        return modif;
    }
    
    public void setModif(boolean modif){
        this.modif=modif;
    }
  
    public void insertEnregistrement(String enr){
        int res = insertBlock(enr, 0, false);
        if(res == -1)
            System.out.println("Erreur : mémoire dépassée.");
    }
    
    private int insertBlock(String enr, int id, boolean decoup){
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
                    int seuilDecoup = (bytesRestant - (bytesRestant % 4)) / 4;
                    if(enr.length() - seuilDecoup < 20)
                        nbDecoup = enr.length() - 20;
                    else
                        nbDecoup = seuilDecoup;
                }
                String subStr = enr.substring(0,nbDecoup);
                int lien = insertBlock(enr.substring(nbDecoup), (id + 1), true);
                block[id].addEnregistrement(subStr, lien);
                pos = true;
            }
        }
        if(id < block.length && pos == false)
            retour = insertBlock(enr, (id + 1), decoup);
        else if(retour == 0)
            retour = -1;
        pourcentage();
        return retour;
    }
    public void removeEnregistrement(String s){
        int i = 0;
        boolean rm = false;
        while(i < block.length && rm == false){
            if(block[i].getEnregistrement(s)){
                int tmp = block[i].removeEnregistrement(s);
                if(tmp == -1)
                    rm = true;
                else
                    i = tmp;
            }
            else
                i++;
        }
    }
     
    public String getElemMemCentrale(String requete){
        String res="Pas de res";
        for(int i=0;i<5;i++){
            if(block[i].getEnregistrement(requete))
               res="trouvé";
       }
         
       return res;
    }
    
    public void copie(Block Source){
        for(int j=0;j<Source.getEnregistrement().size();j++){
            block[nbBlockList].addEnregistrement(Source.getEnregistrement(j).toString(), j);
        }
        block[nbBlockList].setIndiceDisque(Source.getIndiceDisque());

    }
    
    public void copie(Block Dest, Block Source){
        for(int j=0;j<Source.getEnregistrement().size();j++){
            Dest.addEnregistrement(Source.getEnregistrement(j).toString(), j);
        }
        Dest.setIndiceDisque(Source.getIndiceDisque());
    }
    
    public void ajoutDonneeDisque(String req,Block bl){
        boolean trouve=false;
        boolean CopieFaite=false;
        Block temp;
        if(nbBlockList==0) {
            for(int i=0;i<5;i++){
                if(CopieFaite==false){
                    if(block[i].getEnregistrement(req)){
                        //alors on l'incrémente
                        if(i<4){
                            temp = new Block(i);
                            copie(temp,block[i+1]);
                            copie(block[i+1],block[i]); 
                            copie(block[i],temp); 
                            CopieFaite=true;
                        }
                    }
                    else{
                        //on ajoute le bloc au mileu du tableau et on décale les autres (ce qui en dégage 1)
                        copie(block[0],block[1]);
                        copie(block[1],block[2]);
                        copie(block[2],bl);
                        CopieFaite=true;
                    }    
                }
            }
        }
        else{
            for(int i=4;i>=0;i--){
                if(CopieFaite==false){
                    if(block[i].getEnregistrement(req)){
                        //alors on l'incrémente
                        if(i<4){
                            temp = new Block(i);
                            copie(temp,block[i+1]);
                            copie(block[i+1],block[i]); 
                            copie(block[i],temp); 
                            CopieFaite=true;
                        }
                    }
                    else{
                            CopieFaite=true;
                            copie(bl);
                            nbBlockList-=1;
                            //on ajoute le bloc au début du tableau  
                    }
                }
            }
        }
        
        
    }
    
    public int[] updateEnregistrement(String obj, String newEnr){
        int i = 0;
        boolean rm = false;
        int idSuivant = 0;
        String currentObj = obj;
        
        String currentNewEnr = newEnr;

        int id[] = new int[block.length];
        while(i < block.length && rm == false){
            if(block[i].getEnregistrement(currentObj)){
                id[i] = 1;
                int s_id = block[i].getId(currentObj);
                String s = (String)block[i].getEnregistrement().get(s_id);
                idSuivant = block[i].removeEnregistrement(currentObj);
                if(currentNewEnr.length() <= s.length()){
                    block[i].addEnregistrement(currentNewEnr, -1);
                    modif=true;
                    rm = true;
                }
                else if(idSuivant == -1){
                        this.block[i].addEnregistrement(currentNewEnr, this.insertBlock(currentNewEnr.substring(s.length()), 0, true));
                        modif=true;
                        rm = true;
                }
                else if(idSuivant != -1){
                        this.removeEnregistrement(currentObj.substring(s.length()));
                        modif=true;
                        rm = true;
                    }   
                else{
                    block[i].addEnregistrement(currentNewEnr.substring(0,s.length()), -1);
                    currentNewEnr = currentNewEnr.substring(s.length());
                    i = idSuivant;
                    modif=true;
                }
            }
            else{
                id[i]=-1;
                i++;
            }
        }
        if(!rm)
            System.out.println("Erreur : element inexistant.");
        return id;
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
