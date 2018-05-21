package encostai.encostai.com.br.encostaai.activity.ParkingList;

import android.util.Log;

import java.util.ArrayList;

import encostai.encostai.com.br.encostaai.models.PrivateParking;
import encostai.encostai.com.br.encostaai.models.SimpleParking;
import encostai.encostai.com.br.encostaai.models.StreetParking;
import encostai.encostai.com.br.encostaai.utils.KeyWords;

public class ParkingListPresenter implements IParkingListPresenter, IParkingListInteractor.ParkingListListener {

    private IParkingListView parkingListView;
    private IParkingListInteractor parkingListInteractor;

    private ArrayList<SimpleParking> simpleParkingList;
    private ArrayList<SimpleParking> privateParkingList;
    private boolean privateReady = false;
    private boolean privateChecked;
    private ArrayList<SimpleParking> streetParkingList;
    private boolean streetReady = false;
    private boolean publicChecked;

    public ParkingListPresenter(IParkingListView parkingListView, ParkingListInteractor parkingListInteractor) {
        this.parkingListView = parkingListView;
        this.parkingListInteractor = parkingListInteractor;
        this.simpleParkingList = new ArrayList<SimpleParking>();
        this.privateParkingList = new ArrayList<SimpleParking>();
        this.streetParkingList = new ArrayList<SimpleParking>();
    }

    @Override
    public void getParkingList(boolean publicChecked, boolean privateChecked) {
        if (publicChecked) {
            this.publicChecked = publicChecked;
            parkingListInteractor.getStreetParkingList(this);
        }
        if (privateChecked) {
            this.privateChecked = privateChecked;
            parkingListInteractor.getPrivateParkingList(this);
        }
    }

    @Override
    public void onPrivateParkingListRecived(ArrayList<PrivateParking> privateParkingList) {
    this.privateParkingList.clear();
        for (PrivateParking privateParking : privateParkingList) {
            SimpleParking simpleParking = new SimpleParking();
            simpleParking.setId(privateParking.getId());
            simpleParking.setName(privateParking.getName());
            simpleParking.setRating(privateParking.getRating());
            simpleParking.setType(KeyWords.PRIVATEPARKING);
            this.privateParkingList.add(simpleParking);
        }
        privateReady = true;
        joinParkingList();

    }

    @Override
    public void onStreetParkingRecived(ArrayList<StreetParking> streetParkingList) {
        this.simpleParkingList.clear();
        for (StreetParking streetParking : streetParkingList) {
            SimpleParking simpleParking = new SimpleParking();
            simpleParking.setId(streetParking.getId());
            simpleParking.setName(streetParking.getName());
            simpleParking.setRating(streetParking.getRating());
            simpleParking.setType(KeyWords.STREETPARKING);
            this.streetParkingList.add(simpleParking);
        }
        streetReady = true;
        joinParkingList();


    }

    @Override
    public void joinParkingList() {
        simpleParkingList.clear();
        Log.i("Flag", "JOIN COUNT" + streetReady + privateReady);
        if (streetReady == publicChecked && privateReady == privateChecked) {
            simpleParkingList.addAll(streetParkingList);
            simpleParkingList.addAll(privateParkingList);
            parkingListView.showSimpleParkingList(simpleParkingList);
        }
    }


}
