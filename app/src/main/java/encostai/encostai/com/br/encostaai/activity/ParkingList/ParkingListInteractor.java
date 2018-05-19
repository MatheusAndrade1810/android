package encostai.encostai.com.br.encostaai.activity.ParkingList;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import encostai.encostai.com.br.encostaai.models.PrivateParking;
import encostai.encostai.com.br.encostaai.utils.FirebaseConfig;

public class ParkingListInteractor implements IParkingListInteractor {

    private Context context;
    private DatabaseReference databaseReference;
    private ValueEventListener privateParkingListListener;
    private ArrayList<PrivateParking> privateParkingList;

    public ParkingListInteractor(ParkingListActivity parkingListActivity) {
        this.context = parkingListActivity;
        this.databaseReference = FirebaseConfig.getDatabaseReference();
        this.privateParkingList = new ArrayList<PrivateParking>();
    }

    @Override
    public void getPrivateParkingList(final ParkingListListener listener) {
        privateParkingListListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()) {
                    PrivateParking privateParking = data.getValue(PrivateParking.class);
                    privateParking.setId(data.getKey());
                    Log.i("Id",privateParking.getId());
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
}
