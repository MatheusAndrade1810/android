package encostai.encostai.com.br.encostaai.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import encostai.encostai.com.br.encostaai.utils.FirebaseConfig;

public class PrivatePark {

    private String id;
    private String name;
    private int spaceQnt;
    private double cordenate;
    private int EnptySpots;
    private double rating;

    public double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public PrivatePark() {
    }

    public PrivatePark(String id, String name, int spaceQnt, double cordenate1, double cordenate2 ) {
        this.id = id;
        this.name = name;
        this.spaceQnt = spaceQnt;
        this.cordenate = cordenate;
    }

    public void save(){
        DatabaseReference databaseReference = FirebaseConfig.getDatabaseReference();
        databaseReference.child("privateParking").child(getId()).setValue(this);
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

    public void setCoordenate1(float coordenadaLocal1) {
        this.cordenate = coordenadaLocal1;
    }

    public double getCoordenate1() {
        return cordenate;
    }

}
