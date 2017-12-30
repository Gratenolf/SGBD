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
        testDisque.remplir("azertyuiopazertyuiopazertyuiop");
        //String req="blabla";
        SGA testSGA= new SGA();
        //testSGA.Remplir("azertyuiopazertyuiopazertyuiop");
        /*testSGA.ajoutSchema(req);
        
        req="blablabla";
        testSGA.ajoutSchema(req);
        req="blablablB";
        testSGA.ajoutSchema(req);
        req="blablablC";
        testSGA.ajoutSchema(req);
        req="blablablD";
        testSGA.ajoutSchema(req);
        req="blablablE";
        testSGA.ajoutSchema(req);
        req="blablablF";
        testSGA.ajoutSchema(req);
        req="blablablG";
        testSGA.ajoutSchema(req);
        req="blablablH";
        testSGA.ajoutSchema(req);
        req="blablablI";
        testSGA.ajoutSchema(req);
        req="blablablJ";
        testSGA.ajoutSchema(req);
        req="blablablK";
        testSGA.ajoutSchema(req);
        req="blablablL";
        testSGA.ajoutSchema(req);
        req="blablablM";
        testSGA.ajoutSchema(req);
        req="blablablN";
        testSGA.ajoutSchema(req);
        req="blablablO";
        testSGA.ajoutSchema(req);
        req="blablablP";
        testSGA.ajoutSchema(req);
        req="blablablQ";
        testSGA.ajoutSchema(req);
        req="blablablR";
        testSGA.ajoutSchema(req);
        req="blablablS";
        testSGA.ajoutSchema(req);
        req="blablablT";
        testSGA.ajoutSchema(req);
        req="blablablU";
        testSGA.ajoutSchema(req);
        req="blablablV";
        testSGA.ajoutSchema(req);
        req="blablablW";
        testSGA.ajoutSchema(req);*/
        
        Serveur testServ=new Serveur(testSGA,testDisque);
        testServ.start();
        
        
        
        // TODO code application logic here
    }
    
}
