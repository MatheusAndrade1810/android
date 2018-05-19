package encostai.encostai.com.br.encostaai.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import encostai.encostai.com.br.encostaai.utils.FirebaseConfig;

public class PrivateParking {

    private String id;
    private String name;
    private String spaceQnt;
    private String latitude;
    private String longitude;
    private String EnptySpots;
    private String rating;


    public PrivateParking() {
    }

    public PrivateParking(String id, String name, String spaceQnt, String latitude, String longetude ) {
        this.id = id;
        this.name = name;
        this.spaceQnt = spaceQnt;
        this.latitude = latitude;
        this.longitude = longetude;
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

    public void setSpaceQnt(String spaceQnt) {
        this.spaceQnt = spaceQnt;
    }

    public String getSpaceQnt() {
        return spaceQnt;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getEnptySpots() {
        return EnptySpots;
    }

    public void setEnptySpots(String enptySpots) {
        EnptySpots = enptySpots;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
