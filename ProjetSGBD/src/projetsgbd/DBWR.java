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
public class DBWR extends Thread{
    private MemDisque memoireD;
    private SGA sgabuff;
    public DBWR(MemDisque memoireD,SGA sgabuff){
        this.memoireD=memoireD;
        this.sgabuff=sgabuff;
    }
    
    public void run(){
        if(sgabuff.getModif()){
            if(memoireD.getLecture()==false){
                
            }
                
                //memoireD.remplir(); //remplir avec les éléments modifier de DBBufferCache(SGA)
        }
            
    }
}
