package encostai.encostai.com.br.encostaai.activity.inicial;

import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import encostai.encostai.com.br.encostaai.R;

public class InicialActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private LocationManager locationManager;

    private static final LatLng PERTH = new LatLng(-31.952854, 115.857342);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng antigo = new LatLng(-8.06301848,-34.8714073);
        mMap.addMarker(new MarkerOptions().position(antigo).title("Recife Antigo "));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(antigo));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 18.0f ) );


    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

}
