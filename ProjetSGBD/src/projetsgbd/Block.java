package projetsgbd;
import java.util.ArrayList;

public class Block {
    private int numero;
    private ArrayList suivant = new ArrayList();
    private final int taille = 1000;
    private int memoirePrise;
    private ArrayList enregistrement = new ArrayList();
    
    public Block(int num){
        this.numero = num;
        this.memoirePrise = 0;
    }
    
    public int getNumero(){
        return this.numero;
    }
    
    public boolean getEnregistrement(String req){
        int i = 0;
        while(!req.contains((String) this.enregistrement.get(i)) && i < this.enregistrement.size())
            i++;
        if(i >= this.enregistrement.size())
            return false;
        return true;
    }
    
    private Object getEnregistrement(int i){
        return this.enregistrement.get(i);
    }
    
    private void setEnregistrement(String s){
        this.enregistrement.add(s);
    }
    
    public Object getSuivant(int i){
        return this.suivant.get(i);
    }
    
    private void  setSuivant(int i){
        this.suivant.add(i);
    }
    
    public int Pourcentage(){
        return (100 * memoirePrise / taille);
    }
    
    public int getMemoirePrise(){
        return memoirePrise;
    }
    
    public int getTaille(){
        return this.taille;
    }
    
    public ArrayList getEnregistrement(){
        return enregistrement;
    }
    
    public void addEnregistrement(String s, int i){
        if(this.memoirePrise + (s.getBytes().length) <= this.taille){
            this.setEnregistrement(s);
            this.memoirePrise += s.getBytes().length;
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
                this.memoirePrise -= s.getBytes().length;
                this.enregistrement.remove(i);
                return (int) this.suivant.get(i);
            }
        }
        return -1;
    }
}