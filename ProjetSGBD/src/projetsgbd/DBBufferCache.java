package projetsgbd;

public class DBBufferCache {
    private Block block [];
    private int nbBlocCourant;
    
    public DBBufferCache(){
        block = new Block[5];
        for(int i=0;i<5;i++){
            block[i] = new Block(nbBlocCourant++);
        }
        nbBlocCourant=0;
    }
     
    public void remplir(String enr){
        int res = insertBlock(enr, 0, false);
        if(res == -1)
            System.out.println("Erreur : mémoire dépassée.");
    }
    
    private int insertBlock(String enr, int id, boolean decoup){
        boolean pos = false;
        int retour = 0;
        if((enr.getBytes().length + block[id].getMemoirePrise()) <= block[id].getTaille()){
            block[id].addEnregistrement(enr, -1);
            pos = true;
            if(decoup)
                return id;
            else
                return 0;
        }
        else if(enr.length() >= 40){
            int nbDecoup = 0;
            if(block[id].Pourcentage() < 95){
                if(block[id].Pourcentage() >= 90){
                    nbDecoup = 20;
                }
                else{
                    int bytesRestant = block[id].getTaille() - block[id].getMemoirePrise();
                    int seuilDecoup = (bytesRestant - (bytesRestant % 4)) / 4;
                    if(enr.length() - seuilDecoup < 20)
                        nbDecoup = enr.length() - 20;
                    else
                        nbDecoup = seuilDecoup;
                }
                String subStr = enr.substring(0,nbDecoup);
                int lien = insertBlock(enr.substring(nbDecoup), (id + 1), true);
                block[id].addEnregistrement(enr, lien);
                pos = true;
            }
        }
        else{
            if(id < block.length && pos == false)
                retour = insertBlock(enr, (id + 1), decoup);
            else if(retour == 0)
                retour = -1;
        }
        return retour;
    }
     
    public String getElemMemCentrale(String requete){
        String res="Pas de res";
        for(int i=0;i<5;i++){
            if(block[i].getEnregistrement(requete))
               res="trouvé";
       }
       return res;
    }
}