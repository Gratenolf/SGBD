package projetsgbd;
import java.util.ArrayList;

public class Block {
    private int numero;
    private ArrayList suivant;
    private final int MAX = 1000;
    private int taille;
    public ArrayList enregistrement = new ArrayList();
    
    public Block(int num){
        this.numero = num;
        this.taille = 0;
    }
    
    public int getNumero(){
        return this.numero;
    }
    
    private Object getEnregistrement(int i){
        return this.enregistrement.get(i);
    }
    
    private void setEnregistrement(String s){
        this.enregistrement.add(s);
    }
    
    private Object getSuivant(int i){
        return this.suivant.get(i);
    }
    
    private void  setSuivant(int i){
        this.suivant.add(i);
    }
    
    public void addEnregistrement(String s, int i){
        if(taille + (s.getBytes().length) <= this.taille){
            this.setEnregistrement(s);
            this.taille += s.getBytes().length;
            this.setSuivant(i);
        }
        else{
            System.out.println("Error : Taille de bloc trop petite.");
        }
    }
    
    public int removeEnregistrement(String s){
        int i = 0;
        while(i < this.enregistrement.size()){
            if(s.contains((String) this.enregistrement.get(i))){
                this.taille -= s.getBytes().length;
                this.enregistrement.remove(i);
                return (int) this.suivant.get(i);
            }
        }
        return -1;
    }
}
