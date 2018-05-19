package encostai.encostai.com.br.encostaai.activity.ParkingList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import encostai.encostai.com.br.encostaai.R;
import encostai.encostai.com.br.encostaai.models.SimpleParking;

public class ParkingListActivity extends AppCompatActivity implements IParkingListView {

    private ListView listView;
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

        simpleParkingList = new ArrayList<SimpleParking>();
        adapter = new LocalAdapter(ParkingListActivity.this,simpleParkingList);
        listView.setAdapter(adapter);

        presenter = new ParkingListPresenter(this,new ParkingListInteractor(this));

        presenter.getParkingList();


    }

    @Override
    public void showPrivateParkingList(ArrayList<SimpleParking> simplePrivateParkingList) {
        this.simpleParkingList = simplePrivateParkingList;
        Log.i("2Id",this.simpleParkingList.get(0).getId());
        adapter.notifyDataSetChanged();
    }
}
