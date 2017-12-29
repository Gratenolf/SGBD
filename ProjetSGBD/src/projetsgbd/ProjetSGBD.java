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
public class ProjetSGBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        GBlock testGBlock=new GBlock();
        testGBlock.start();
        Serveur testServ=new Serveur();
        testServ.start();
        try{
            testGBlock.sleep(100);
        }catch(InterruptedException e){}
        
        // TODO code application logic here
    }
    
}
