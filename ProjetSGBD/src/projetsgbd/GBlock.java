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
public class GBlock extends Thread{
    public boolean travail;
    public boolean finProgramme;
    public String requete;
    
    public GBlock(){
        travail=false;
        finProgramme=false;
    }
    public boolean getTravail(){
        return travail;
    }
    public void setTravail(boolean travail,String requete){
        this.travail=travail;
        this.requete=requete;
    }
    
    public String rechBuffer(String requete,SGA sgabuff){
        String res=sgabuff.rechBlock(requete);
        return res;
    }
    public void run() {
        while(finProgramme==false){
            try{
                while(travail==false){
                    System.out.println("GBlock dort");
                    sleep(1000);
                }
                System.out.println("GBlock s'est réveillé");
                
                
            }
            catch(InterruptedException e){
                System.out.println("Error Catch GBlock");
            }
        }
    }
}
