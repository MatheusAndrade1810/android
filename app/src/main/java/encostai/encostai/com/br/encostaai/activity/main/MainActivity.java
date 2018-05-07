package encostai.encostai.com.br.encostaai.activity.main;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import encostai.encostai.com.br.encostaai.R;
import encostai.encostai.com.br.encostaai.activity.ListMapedSpots.ListPrivatesMapedSpots;
import encostai.encostai.com.br.encostaai.activity.ListMapedSpots.ListPublicMapedSpots;
import encostai.encostai.com.br.encostaai.activity.bestsPlaces.BestsSpotsActivity;
import encostai.encostai.com.br.encostaai.activity.login.LoginActivity;
import encostai.encostai.com.br.encostaai.activity.profile.ProfileActivity;
import encostai.encostai.com.br.encostaai.activity.signUp.SignUpActivity;
import encostai.encostai.com.br.encostaai.activity.termsPolicies.TermsPoliciesActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    //AppCompatActivity
    private GoogleMap mMap;
    private LocationManager locationManager;

    private static final LatLng PERTH = new LatLng(-8.0630769, -34.87146094);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng antigo = new LatLng(-8.06301848, -34.8714073);
        mMap.addMarker(new MarkerOptions().position(antigo).title("Recife Antigo "));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(antigo));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));


    }

    @Override
    public void onMapClick(LatLng latLng) {

    }


    // Navegação lateral
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent;
        int id = item.getItemId();

        if (id == R.id.nav_Profile) {
            intent = new Intent(MainActivity.this, ProfileActivity.class);
           startActivity(intent);

       } else if (id == R.id.nav_BestsPlaces) {
            intent = new Intent(MainActivity.this, BestsSpotsActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_Favorites) {


        } else if (id == R.id.nav_Payment) {


        } else if (id == R.id.nav_TermsPolicies) {
            intent = new Intent(MainActivity.this, TermsPoliciesActivity.class);
            startActivity(intent);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()) {

            case R.id.ListaPublicos:
                intent = new Intent(MainActivity.this, ListPublicMapedSpots.class);
                startActivity(intent);
                break;
            case R.id.ListaPrivados:
                intent = new Intent(MainActivity.this, ListPrivatesMapedSpots.class);
                startActivity(intent);
                break;
        }


    }
}