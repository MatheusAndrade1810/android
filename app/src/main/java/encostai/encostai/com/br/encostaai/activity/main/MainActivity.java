package encostai.encostai.com.br.encostaai.activity.main;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

import encostai.encostai.com.br.encostaai.R;
import encostai.encostai.com.br.encostaai.activity.ParkingList.ParkingListActivity;
import encostai.encostai.com.br.encostaai.activity.login.LoginActivity;
import encostai.encostai.com.br.encostaai.activity.profile.ProfileActivity;
import encostai.encostai.com.br.encostaai.activity.termsPolicies.TermsPoliciesActivity;
import encostai.encostai.com.br.encostaai.utils.Permission;

public class MainActivity extends AppCompatActivity implements IMainView, OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    //AppCompatActivity
    private GoogleMap mMap;
    private String[] permissions = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private IMainPresenter presenter;
    private Location location;


    private static final LatLng PERTH = new LatLng(-8.0630769, -34.87146094);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        Permission.permissionsValidate(1, this, permissions);

        presenter = new MainPresenter(this, new MainInteractor(this));
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
    public void onMapReady(final GoogleMap googleMap) {

        mMap = presenter.onMapReady(this, googleMap);

        presenter.drawPrivatePark();
        presenter.drawStreetPark();

        goTo(new LatLng(-8.06301848, -34.8714073));

        presenter.getLocation();



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

       } else if (id == R.id.nav_ParkList) {
            intent = new Intent(MainActivity.this, ParkingListActivity.class);
            if(location!=null){
                Bundle bundle = new Bundle();
                bundle.putDouble("latitude", this.location.getLatitude());
                bundle.putDouble("longitude", this.location.getLongitude());
                intent.putExtras(bundle);
            }
            startActivity(intent);

        } else if (id == R.id.nav_Favorites) {


        } else if (id == R.id.nav_Payment) {


        } else if (id == R.id.nav_TermsPolicies) {
            intent = new Intent(MainActivity.this, TermsPoliciesActivity.class);
            startActivity(intent);

        } else if(id == R.id.nav_SignOut){
            presenter.signOut();
            intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void addMarker(LatLng latLng, String tittle){
        mMap.addMarker(new MarkerOptions().position(latLng).alpha((float) 0.75).title(tittle));
    }

    @Override
    public void addLine(LatLng latLng1, LatLng latLng2, String tittle){
        mMap.addPolyline(new PolylineOptions()
            .add(latLng1,latLng2)
        .width(25)
        .color(Color.BLUE)
        .geodesic(true));
        mMap.addMarker(new MarkerOptions().position(latLng1).alpha((float) 0.75).title(tittle));
    }

    @Override
    public void goTo(LatLng latLng){
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));
    }

    @Override
    public void newLocation(Location location){
        this.location = location;
    }
}