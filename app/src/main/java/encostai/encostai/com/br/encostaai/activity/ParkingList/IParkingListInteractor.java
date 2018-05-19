package encostai.encostai.com.br.encostaai.activity.ParkingList;

import java.util.ArrayList;

import encostai.encostai.com.br.encostaai.models.PrivateParking;

public interface IParkingListInteractor {

    void getPrivateParkingList(final ParkingListListener listener);

    interface ParkingListListener{
        void onPrivateParkingListRecived(ArrayList<PrivateParking> privateParkingList);
    }
}
