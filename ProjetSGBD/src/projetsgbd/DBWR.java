/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetsgbd;


public class DBWR extends Thread{
    private MemDisque memoireD;
    private SGA sgabuff;
    private int id[];
    private boolean finProgramme;
    
    public DBWR(SGA sgabuff,MemDisque memoireD){
        this.memoireD=memoireD;
        this.sgabuff=sgabuff;
        finProgramme=false;
    }
    
    public void run(){
        while(finProgramme==false){
            if(sgabuff.getModif()){  
                if(memoireD.getLecture()==false){
                    id=sgabuff.getid();
                    Block temp[];
                    for(int i=0;i<sgabuff.getDBBC().getBlock().length;i++){
                        for(int j=0;j<id.length;j++){
                            if(i==id[j]){
                                memoireD.RecopieBlock(sgabuff.getDBBC().getBlock()[j],sgabuff.getDBBC().getBlock()[j].getIndiceDisque());
                                System.out.println("réécriture effectué");
                                System.out.println("\tBloc en mémoire disque: "+memoireD.toString());
                                sgabuff.getDBBC().setModif(false);
                            }
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
}
