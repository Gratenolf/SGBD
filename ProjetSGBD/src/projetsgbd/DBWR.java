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
                                //temp=sgabuff.getDBBC().getBlock();
                                //System.out.println("ID "+id[j]+" I "+i+" J "+j+ " DBWR "+sgabuff.getDBBC().getBlock()[j].toString()+" Indice Disque "+sgabuff.getDBBC().getBlock()[j].getIndiceDisque());
                                memoireD.RecopieBlock(sgabuff.getDBBC().getBlock()[j],sgabuff.getDBBC().getBlock()[j].getIndiceDisque());
                                System.out.println("réécriture effectué");
                                System.out.println("\tBloc en mémoire disque: "+memoireD.toString());
                                sgabuff.getDBBC().setModif(false);
                            }
                        }
                    }
                     //parcours des block en buffer
                            //Si le block correspond au tableau d'id
                            //Alors le copie à l'emplacement de son idDisque
                    
                    
                    /*for(int i=0;i<id.length;i++){
                        for(int j=0;j<sgabuff.getDBBC().getBlock().length;j++){
                            
                           
                            
                            for(memoireD)
                                if(i==)
                            memoireD.RecopieBlock(bl);
                            //memoireD.insertEnregistrement((String)sgabuff.getDBBC().getBlock()[id[i]].getEnregistrement(j));
                        }
                    }*/
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
