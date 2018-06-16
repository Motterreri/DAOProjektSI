package wat.learning.android.si.daoprojektsi.Fragments.Main;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import wat.learning.android.si.daoprojektsi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinanseFragment extends Fragment {


    public FinanseFragment() {
        // Required empty public constructor
    }

    public ArrayList<String> finanse = new ArrayList<String>();
    public View fragView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragView = inflater.inflate(R.layout.fragment_finanse, container, false);

        //TODO: Pobrać wszystkie id obrotu (zakładam że są dwa rodzaje obrotów - przychody i obciążenia)
        //TODO: Poza tym pobrać id faktury i kwotę
        finanse.clear();
        finanse.add("Testowy przychód\n+120.34zł\nID faktury: 1");
        finanse.add("Testowe obciążenie\n-120.43zł\n ID faktury: 2");

        Fragment fragment = new FakturyListFragment();
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.commit();

        return fragView;
    }

}
