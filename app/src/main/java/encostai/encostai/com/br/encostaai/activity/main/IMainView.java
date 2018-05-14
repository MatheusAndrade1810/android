package encostai.encostai.com.br.encostaai.activity.main;

import android.view.View;

import com.google.android.gms.maps.model.LatLng;

public interface IMainView {
    void onClick(View v);

    void addMarker(LatLng latLng, String tittle);

    void goTo(LatLng latLng);
}
