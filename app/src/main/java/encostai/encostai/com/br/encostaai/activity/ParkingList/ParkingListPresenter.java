package encostai.encostai.com.br.encostaai.activity.ParkingList;

import android.util.Log;

import java.util.ArrayList;

import encostai.encostai.com.br.encostaai.models.PrivateParking;
import encostai.encostai.com.br.encostaai.models.SimpleParking;

public class ParkingListPresenter implements IParkingListPresenter, IParkingListInteractor.ParkingListListener {

    private IParkingListView parkingListView;
    private IParkingListInteractor parkingListInteractor;

    private ArrayList<SimpleParking> simplePrivateParkingList;
    private ArrayList<PrivateParking> privateParkingList;
    private ArrayList<SimpleParking> SimpleParkingList;

    public ParkingListPresenter(IParkingListView parkingListView, ParkingListInteractor parkingListInteractor) {
        this.parkingListView = parkingListView;
        this.parkingListInteractor = parkingListInteractor;
        this.simplePrivateParkingList = new ArrayList<SimpleParking>();
    }

    @Override
    public void getParkingList() {
        parkingListInteractor.getPrivateParkingList(this);

    }

    @Override
    public void onPrivateParkingListRecived(ArrayList<PrivateParking> privateParkingList) {
        for (PrivateParking privateParking:privateParkingList) {
            SimpleParking simpleParking = new SimpleParking();
            simpleParking.setId(privateParking.getId());
            simpleParking.setName(privateParking.getName());
            simpleParking.setRating(privateParking.getRating());
            simplePrivateParkingList.add(simpleParking);
            Log.i("2Id",simpleParking.getId());
        }

        parkingListView.showPrivateParkingList(simplePrivateParkingList);
    }
}
