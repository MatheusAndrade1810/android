package encostai.encostai.com.br.encostaai.activity.ParkingList;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import encostai.encostai.com.br.encostaai.models.PrivateParking;
import encostai.encostai.com.br.encostaai.models.StreetParking;
import encostai.encostai.com.br.encostaai.utils.FirebaseConfig;

public class ParkingListInteractor implements IParkingListInteractor {

    private Context context;
    private DatabaseReference databaseReference;
    private ValueEventListener privateParkingListListener;
    private ArrayList<PrivateParking> privateParkingList;
    private ValueEventListener streetParkingListListner;
    private ArrayList<StreetParking> streetParkingList;

    public ParkingListInteractor(ParkingListActivity parkingListActivity) {
        this.context = parkingListActivity;
        this.databaseReference = FirebaseConfig.getDatabaseReference();
        this.privateParkingList = new ArrayList<PrivateParking>();
        this.streetParkingList = new ArrayList<StreetParking>();
    }

    @Override
    public void getPrivateParkingList(final ParkingListListener listener) {
        privateParkingListListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()) {
                    PrivateParking privateParking = data.getValue(PrivateParking.class);
                    privateParking.setId(data.getKey());
                    privateParkingList.add(privateParking);
                }
                if(!privateParkingList.isEmpty()){
                    listener.onPrivateParkingListRecived(privateParkingList);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.child("privateParking").addListenerForSingleValueEvent(privateParkingListListener);
    }

    @Override
    public void getStreetParkingList(final ParkingListListener listener) {

        streetParkingListListner = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()) {
                    StreetParking streetParking = data.getValue(StreetParking.class);
                    streetParking.setId(data.getKey());
                    streetParkingList.add(streetParking);
                }
                if(!streetParkingList.isEmpty()){
                    listener.onStreetParkingRecived(streetParkingList);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.child("streetParking").addListenerForSingleValueEvent(streetParkingListListner);
    }
}
