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
public class ProjetSGBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        MemDisque testDisque = new MemDisque();
        testDisque.Remplir();
        String req="blabla";
        SGA testSGA= new SGA();
        testSGA.Ajoutschema(req);
        Serveur testServ=new Serveur(testSGA,testDisque);
        testServ.start();
        req="blablabla";
        testSGA.Ajoutschema(req);
        
        
        // TODO code application logic here
    }
    
}
