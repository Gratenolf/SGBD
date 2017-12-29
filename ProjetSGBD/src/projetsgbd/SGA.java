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
    DBBufferCache DBBC = new DBBufferCache();//database buffer cache
    ArrayList MRU = new ArrayList();//tableau des requêtes
    int MRUcourant;//nombre de requêtes dans le tableau MRU
    
    public SGA(){
        MRUcourant=19;
    }
    public void schema(String requete){
        String temp="";
        boolean trouve=false;
        if(MRUcourant==20) {
            if(MRU.contains(requete)){
                int indice=MRU.indexOf(requete);
                if(indice<19){
                    Object MRUSuiv=MRU.get(indice+1);
                    MRU.set(indice+1,requete);
                    MRU.set(indice, MRUSuiv);
                    trouve=true;
                }
            }
            if(trouve==false){
               int MRUcourantMoit=(int)(MRUcourant/2);
               MRU.add(MRUcourant);
            }
        } else {
             MRU.add(MRUcourant, requete);
             MRUcourant-=1;  
        }
    }
}
