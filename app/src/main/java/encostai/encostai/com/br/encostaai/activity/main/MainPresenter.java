package encostai.encostai.com.br.encostaai.activity.main;

import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import encostai.encostai.com.br.encostaai.models.PrivateParking;
import encostai.encostai.com.br.encostaai.models.StreetParking;

public class MainPresenter implements IMainPresenter, IMainInteractor.MainListener {

    private IMainView mainView;
    private IMainInteractor mainInteractor;
    private GoogleMap mMap;

    MainPresenter(IMainView mainView, MainInteractor mainInteractor){
        this.mainView = mainView;
        this.mainInteractor = mainInteractor;
    }
    @Override
    public void signOut() {
        mainInteractor.signOut();
    }

    @Override
    public GoogleMap onMapReady(MainActivity context, GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap = mainInteractor.onMapReady(context, mMap, this);
        return mMap;
    }

    @Override
    public void onError() {

    }

    @Override
    public void onLocationChange(Location location){

    }

    @Override
    public void onPrivateParkRecived(ArrayList<PrivateParking> privateParkingList){
        for (PrivateParking privateParking : privateParkingList) {
            mainView.addMarker(new LatLng(Double.parseDouble(privateParking.getLatitude()), Double.parseDouble(privateParking.getLongitude())), privateParking.getName());
        }
    }

    @Override
    public void onStreetParkListRecived(ArrayList<StreetParking> streetParkList) {
        for (StreetParking streetParking : streetParkList) {
            mainView.addLine(new LatLng(Double.parseDouble(streetParking.getLatitude1()),Double.parseDouble(streetParking.getLongitude1())),new LatLng(Double.parseDouble(streetParking.getLatitude2()),Double.parseDouble(streetParking.getLongitude2())),streetParking.getName());
        }
    }

    @Override
    public void drawPrivatePark(){
        mainInteractor.getPrivateParks(this);
    }

    @Override
    public void drawStreetPark() {
        mainInteractor.getStreetParks(this);
    }
}
