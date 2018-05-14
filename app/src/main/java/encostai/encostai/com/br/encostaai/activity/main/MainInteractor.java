package encostai.encostai.com.br.encostaai.activity.main;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

import encostai.encostai.com.br.encostaai.utils.FirebaseConfig;
import encostai.encostai.com.br.encostaai.utils.Preferences;

public class MainInteractor implements IMainInteractor{

    private FirebaseAuth firebaseAuth;
    private Preferences preferences;

    MainInteractor(Activity MainActivity) {
        this.firebaseAuth = FirebaseConfig.getFirebaseAuth();
        preferences = new Preferences(MainActivity);
    }

    @Override
    public void signOut() {
        firebaseAuth = FirebaseConfig.getFirebaseAuth();
        firebaseAuth.signOut();
        preferences.clearData();
    }

    @Override
    public GoogleMap onMapReady(MainActivity context, final GoogleMap mMap, final MainListner listner) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }else{
            if(!mMap.isMyLocationEnabled()){
                mMap.setMyLocationEnabled(true);

                LocationManager locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
                Location myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER); }

        }

        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                listner.onLocationChange(location);

            }
        });

        return mMap;
    }
}
