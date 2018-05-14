package encostai.encostai.com.br.encostaai.activity.main;

import android.app.Activity;
import android.location.Location;

import com.google.android.gms.maps.GoogleMap;

public interface IMainInteractor {

    interface MainListner{

        void onError();

        void onLocationChange(Location location);

    }
    void signOut();

    GoogleMap onMapReady(MainActivity context, GoogleMap mMap, MainListner listner);
}
