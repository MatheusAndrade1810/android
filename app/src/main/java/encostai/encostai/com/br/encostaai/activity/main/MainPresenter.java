package encostai.encostai.com.br.encostaai.activity.main;

import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainPresenter implements IMainPresenter, IMainInteractor.MainListner{

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
        mainView.addMarker(new LatLng(location.getLatitude(),location.getLongitude()),"Location");
    }
}
