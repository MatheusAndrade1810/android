package encostai.encostai.com.br.encostaai.models;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import encostai.encostai.com.br.encostaai.utils.FirebaseConfig;

public class PrivateParking {

    private String id;
    private String name;
    private int spaceQnt;
    private MyLatLng cordenate;
    private int EnptySpots;
    private double rating;


    public PrivateParking() {
    }

    public PrivateParking(String id, String name, int spaceQnt, double cordenate1, double cordenate2 ) {
        this.id = id;
        this.name = name;
        this.spaceQnt = spaceQnt;
        this.cordenate = cordenate;
    }

    public void save(){
        DatabaseReference databaseReference = FirebaseConfig.getDatabaseReference();
        databaseReference.child("privateParking").push().setValue(this);
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


    public MyLatLng getCordenate() {
        return cordenate;
    }

    public void setCordenate(MyLatLng cordenate) {
        this.cordenate = cordenate;
    }

    public int getEnptySpots() {
        return EnptySpots;
    }

    public void setEnptySpots(int enptySpots) {
        EnptySpots = enptySpots;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }



}
