package wat.learning.android.si.daoprojektsi.Fragments.Main;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import wat.learning.android.si.daoprojektsi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SzczegolyFakturyFragment extends Fragment {


    public SzczegolyFakturyFragment() {
        // Required empty public constructor
    }

    public long id;

    public void setId(long i){
        id = i;
    }

  public View fragView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragView = inflater.inflate(R.layout.fragment_szczegoly_faktury, container, false);

        TextView nrF = (TextView) fragView.findViewById(R.id.numer_faktury);
        TextView podmiotWystawiajacy = (TextView) fragView.findViewById(R.id.podmiot_wystawiajacy);
        TextView terminPlatnosci = (TextView) fragView.findViewById(R.id.termin_platnosci);
        TextView dataWystawienia = (TextView) fragView.findViewById(R.id.data_wystawienia);

        return fragView;
    }

}
