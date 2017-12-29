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
    String MRU[] = new String[20];//tableau des requêtes
    int MRUcourant;//nombre de requêtes dans le tableau MRU
    
    public SGA(){
        MRUcourant=0;
        for(int i=0;i<20;i++)
            MRU[i]="CaseVide";
    }
    public void schema(String requete){
        String temp="";
        if(MRUcourant==20) {
            for(int i=0;i<20;i++){
                if(requete.equals(MRU[i]))
                    temp=MRU[i+1];
                MRU[i+1]=MRU[i];
                MRU[i]=temp;
            }
        } else {
        
        }
    }
}
