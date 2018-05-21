package encostai.encostai.com.br.encostaai.activity.ParkingList;

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

import java.util.ArrayList;

import encostai.encostai.com.br.encostaai.R;
import encostai.encostai.com.br.encostaai.models.SimpleParking;

public class ParkingListActivity extends AppCompatActivity implements IParkingListView {

    private ListView listView;
    private CheckBox privateCheck;
    private CheckBox publicCheck;
    private ArrayList<SimpleParking> simpleParkingList;
    private ArrayAdapter<SimpleParking> adapter;
    private IParkingListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partking_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.lv_parking);
        privateCheck = (CheckBox) findViewById(R.id.chk_private);
        publicCheck = (CheckBox) findViewById(R.id.chk_public);

        simpleParkingList = new ArrayList<SimpleParking>();
        adapter = new LocalAdapter(ParkingListActivity.this,simpleParkingList);
        listView.setAdapter(adapter);

        presenter = new ParkingListPresenter(this,new ParkingListInteractor(this));

        presenter.getParkingList(publicCheck.isChecked(),privateCheck.isChecked());


        privateCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenter.getParkingList(publicCheck.isChecked(),privateCheck.isChecked());
            }
        });

        publicCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenter.getParkingList(publicCheck.isChecked(),privateCheck.isChecked());
            }
        });
    }


    @Override
    public void showSimpleParkingList(ArrayList<SimpleParking> simpleParkingList){
        this.simpleParkingList.clear();
        this.simpleParkingList.addAll(simpleParkingList);
        adapter.notifyDataSetChanged();
    }

}
