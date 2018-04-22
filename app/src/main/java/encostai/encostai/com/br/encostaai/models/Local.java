package encostai.encostai.com.br.encostaai.models;
/*
 * Esta classe contempla o modelo de um local (estacionamento ou rua) e suas variáveis são:
 * ID -> serve apenas pra controle interno
 *
 * Nome - nome do estabelecimento ou da rua
 *
 * Quantdiade de Vagas - serve pra obter a quantidade de vagas que o local possui
 *
 * Coordenadas 1 e 2
 * 1 -> se for estacionamento só precisa de 1
 * 2 - se for rua, precisará das 2 (1 e 2)
 *
 * Tipo do Local -> é um booleano que server pra dizer TRUE ou FALSE, onde
 * TRUE - Estacionamento
 * FALSE - Rua
 * */

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import encostai.encostai.com.br.encostaai.utils.FirebaseConfig;

public class Local {

    private String id;
    private String name;
    private int spaceQnt;
    private double cordenate1;
    private double cordenate2;
    private boolean type;

    public Local() {
    }

    public Local(String id, String name, int spaceQnt, double cordenate ) {
        this.id = id;
        this.name = name;
        this.spaceQnt = spaceQnt;
        this.cordenate1 = cordenate;
        this.type = true;
    }

    public Local(String id, String name, int spaceQnt, double cordenate1, double cordenate2 ) {
        this.id = id;
        this.name = name;
        this.spaceQnt = spaceQnt;
        this.cordenate1 = cordenate1;
        this.cordenate2 = cordenate2;
        this.type = false;
    }

    public void save(){
        DatabaseReference databaseReference = FirebaseConfig.getDatabaseReference();
        databaseReference.child("spots").child(getId()).setValue(this);
    }

    public void setId(String id) {
        this.id = id;
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSpaceQnt(int spaceQnt) {
        this.spaceQnt = spaceQnt;
    }

    public int getSpaceQnt() {
        return spaceQnt;
    }

    public void setCoordenadaLocal1(float coordenadaLocal1) {
        this.cordenate1 = coordenadaLocal1;
    }

    public double getCoordenadaLocal1() {
        return cordenate1;
    }

    public void setCoordenadaLocal2(float coordenadaLocal2) {
        this.cordenate2 = coordenadaLocal2;
    }

    public double getCoordenadaLocal2() {
        return cordenate2;
    }

    public boolean getType() {
        return type;
    }

    @Exclude
    public String getTypeName(){
        if (type){
            return "park";
        }else{
            return "street";
        }
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public void setType(String type){
        if(type.equals("park")){
            this.type = true;
        }else{
            this.type = false;
        }
    }

}
