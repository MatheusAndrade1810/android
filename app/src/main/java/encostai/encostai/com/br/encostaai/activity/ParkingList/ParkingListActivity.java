package encostai.encostai.com.br.encostaai.activity.ParkingList;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;

import encostai.encostai.com.br.encostaai.R;
import encostai.encostai.com.br.encostaai.models.SimpleParking;

public class ParkingListActivity extends AppCompatActivity implements IParkingListView {

    private ListView listView;
    private CheckBox privateCheck;
    private CheckBox publicCheck;
    private RadioGroup sortRadioGrouop;
    private ArrayList<SimpleParking> simpleParkingList;
    private ArrayAdapter<SimpleParking> adapter;
    private IParkingListPresenter presenter;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partking_list);

        Intent intent = getIntent();
        if (intent.hasExtra("latitude")) {
            Bundle bundle = intent.getExtras();
            location = new Location("");
            location.setLatitude(bundle.getDouble("latitude"));
            location.setLongitude(bundle.getDouble("longitude"));
            Log.i("Flag", location.toString());
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.lv_parking);
        privateCheck = (CheckBox) findViewById(R.id.chk_private);
        publicCheck = (CheckBox) findViewById(R.id.chk_public);
        sortRadioGrouop = (RadioGroup) findViewById(R.id.rgp_sort);
        sortRadioGrouop.check(R.id.rbt_id);

        simpleParkingList = new ArrayList<SimpleParking>();
        adapter = new LocalAdapter(ParkingListActivity.this, simpleParkingList);
        listView.setAdapter(adapter);

        presenter = new ParkingListPresenter(this, new ParkingListInteractor(this), location);

        presenter.getParkingList(publicCheck.isChecked(), privateCheck.isChecked(), sortRadioGrouop);

        privateCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public synchronized void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenter.getParkingList(publicCheck.isChecked(), isChecked, sortRadioGrouop);
            }
        });

        publicCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public synchronized void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenter.getParkingList(isChecked, privateCheck.isChecked(), sortRadioGrouop);
            }
        });

        sortRadioGrouop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rbt_range && location==null){
                    Log.i("Flag","no gps");
                }else{
                    presenter.getParkingList(publicCheck.isChecked(), privateCheck.isChecked(), sortRadioGrouop);
                }
            }
        });

    }


    @Override
    public synchronized void showSimpleParkingList(ArrayList<SimpleParking> simpleParkingList) {
        this.simpleParkingList.clear();
        adapter.notifyDataSetChanged();
        this.simpleParkingList.addAll(simpleParkingList);
        adapter.notifyDataSetChanged();
    }
}
