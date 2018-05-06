package encostai.encostai.com.br.encostaai.models;

public class Awnser {
    private String userId;
    private String streetParkingId;
    private boolean empty;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStreetParkingId() {
        return streetParkingId;
    }

    public void setStreetParkingId(String streetParkingId) {
        this.streetParkingId = streetParkingId;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
