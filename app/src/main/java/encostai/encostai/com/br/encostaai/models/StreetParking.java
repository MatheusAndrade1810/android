package encostai.encostai.com.br.encostaai.models;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import encostai.encostai.com.br.encostaai.utils.FirebaseConfig;

public class StreetParking {

    private String id;
    private String name;
    private int spaceQnt;
    private MyLatLng cordenate1;
    private MyLatLng cordenate2;
    private double probability;
    private double rating;

    public double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public StreetParking() {
    }

    public StreetParking(String id, String name, int spaceQnt, MyLatLng cordenate1, MyLatLng cordenate2 ) {
        this.id = id;
        this.name = name;
        this.spaceQnt = spaceQnt;
        this.cordenate1 = cordenate1;
        this.cordenate2 = cordenate2;
    }

    public void save(){
        DatabaseReference databaseReference = FirebaseConfig.getDatabaseReference();
        databaseReference.child("streetParking").push().setValue(this);
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

    public void setCoordenate1(MyLatLng coordenadaLocal1) {
        this.cordenate1 = coordenadaLocal1;
    }

    public MyLatLng getCoordenate1() {
        return cordenate1;
    }

    public void setCoordenate2(MyLatLng coordenadaLocal2) {
        this.cordenate2 = coordenadaLocal2;
    }

    public MyLatLng getCoordenate2() {
        return cordenate2;
    }

}
