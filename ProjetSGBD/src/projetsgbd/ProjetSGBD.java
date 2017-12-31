/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetsgbd;
public class ProjetSGBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        MemDisque testDisque = new MemDisque();
        testDisque.insertEnregistrement("baaaaaaaaabaaaaaaaaabaaaaaaaap");
        //Recopiez cette ligne pour insérer d'autres valeurs dans la mémoire disque
        SGA testSGA= new SGA();
        Serveur testServ=new Serveur(testSGA,testDisque);
        testServ.start();
        DBWR testDBWR = new DBWR(testSGA,testDisque);
        testDBWR.start();

    }
    
}
