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
    private int id[];
    public DBWR(MemDisque memoireD,SGA sgabuff){
        this.memoireD=memoireD;
        this.sgabuff=sgabuff;
    }
    
    public void run(){
        if(sgabuff.getModif()){
            if(memoireD.getLecture()==false){
                id=sgabuff.getid();
                for(int i=0;i<id.length;i++){
                    for(int j=0;j<sgabuff.getDBBC().getBlock().length;j++){
                        memoireD.insertEnregistrement((String)sgabuff.getDBBC().getBlock()[id[i]].getEnregistrement(j));
                    }
                }
            }
            else{
                try{
                    sleep(100);
                }
                catch(InterruptedException e){
                    System.out.println("Error sleep");
                }
            }
         //remplir avec les éléments modifier de DBBufferCache(SGA)
        }
        else{
            try{
                sleep(100);
            }
            catch(InterruptedException e){
                System.out.println("Error sleep");
            }
        }
            
    }
}
