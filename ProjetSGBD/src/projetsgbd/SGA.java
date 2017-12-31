/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetsgbd;
import java.util.ArrayList;
/**
 *
 * @author Adam
 */
public class SGA {
    private DBBufferCache DBBC;//database buffer cache
    private ArrayList MRU;//tableau des requêtes
    private int MRUcourant;//nombre de requêtes dans le tableau MRU
    private int id[];
    
    public SGA(){
        MRUcourant=19;
        DBBC = new DBBufferCache();
        MRU = new ArrayList();
        for(int i=0;i<20;i++){
            MRU.add("Case Vide");
        }
        id = new int[DBBC.getNbBlock()];
    }
    
    public DBBufferCache getDBBC(){
        return DBBC;
    }
    
    public int[] getid(){
        return id;
    }
    
    public boolean getModif(){
        return DBBC.getModif();
    }
    
    public void update(String enr,String newEnr){
        int idtemp[];
        idtemp=DBBC.updateEnregistrement(enr,newEnr);
        for(int i=0;i<idtemp.length;i++)
            id[i]=idtemp[i];
    }
    
    public void ajoutDonneeDisque(String req,Block bl){
        DBBC.ajoutDonneeDisque(req, bl);
    }
    public String rechBlock(String requete){
        String res="Pas de res";
        res=DBBC.getElemMemCentrale(requete);
        return res;
    }
    
    public void Remplir(String enr){
        this.DBBC.insertEnregistrement(enr);
    }
        
        
    public void ajoutSchema(String requete){
        String temp="";
        boolean trouve=false;
        if(MRUcourant==0) {
            if(MRU.contains(requete)){
                int indice=MRU.indexOf(requete);
                if(indice<19){
                    Object MRUSuiv=MRU.get(indice+1);
                    MRU.set(indice+1,requete);
                    MRU.set(indice, MRUSuiv);
                    trouve=true;
                    System.out.println("Ajout effectué, liste pleine, requete déjà présente");
                }
            }
            if(trouve==false){
               int MRUcourantMoit=(int)(MRUcourant/2);
               MRU.add(MRUcourant);
               System.out.println("Ajout effectué liste pleine, requete absente");
            }
        } else {
            if(MRU.contains(requete)){
                int indice=MRU.indexOf(requete);
                if(indice<19){
                    Object MRUSuiv=MRU.get(indice+1);
                    MRU.set(indice+1,requete);
                    MRU.set(indice, MRUSuiv);
                    trouve=true;
                    System.out.println("Ajout effectué, liste non remplie, requete déjà présente"); 
                }
            }
            if(trouve==false){
                MRU.add(MRUcourant, requete);
                MRUcourant-=1;
                System.out.println("Ajout effectué liste non remplie, requete absente");
            }
        }
    }
}
