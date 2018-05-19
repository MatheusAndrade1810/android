package encostai.encostai.com.br.encostaai.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import encostai.encostai.com.br.encostaai.utils.FirebaseConfig;

public class StreetParking {

    private String id;
    private String name;
    private String spaceQnt;
    private String latitude1;
    private String longitude1;
    private String latitude2;
    private String longitude2;
    private String probability;
    private String rating;


    public StreetParking() {
    }

    public StreetParking(String id, String name, String spaceQnt, String latitude1, String longetude1, String latitude2, String longetude2 ) {
        this.id = id;
        this.name = name;
        this.spaceQnt = spaceQnt;
        this.latitude1 = latitude1;
        this.longitude1 = longetude1;
        this.latitude2 = latitude2;
        this.longitude2 = longetude2;
    }

    public void save(){
        DatabaseReference databaseReference = FirebaseConfig.getDatabaseReference();
        databaseReference.child("streetParking").child(getId()).setValue(this);
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

    public String getLatitude1() {
        return latitude1;
    }

    public void setLatitude1(String latitude1) {
        this.latitude1 = latitude1;
    }

    public String getLongitude1() {
        return longitude1;
    }

    public void setLongitude1(String longitude1) {
        this.longitude1 = longitude1;
    }

    public String getLatitude2() {
        return latitude2;
    }

    public void setLatitude2(String latitude2) {
        this.latitude2 = latitude2;
    }

    public String getLongitude2() {
        return longitude2;
    }

    public void setLongitude2(String longitude2) {
        this.longitude2 = longitude2;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public String getProbability() {
        return probability;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

}
