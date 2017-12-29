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
    public GBlock(){
        
    }
    public void run(){
        for(int i=0;i<100;i++)
        {
            System.out.println("test thread " + i);
        }
    }
}
