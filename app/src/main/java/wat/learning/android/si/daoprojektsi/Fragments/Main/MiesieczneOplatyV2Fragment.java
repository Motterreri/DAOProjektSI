package wat.learning.android.si.daoprojektsi.Fragments.Main;


import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import wat.learning.android.si.daoprojektsi.Activities.MainActivity;
import wat.learning.android.si.daoprojektsi.R;

public class MiesieczneOplatyV2Fragment extends Fragment {

    public View fragView;
    public ArrayList<String> oplaty = new ArrayList<String>();

    public MiesieczneOplatyV2Fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragView = inflater.inflate(R.layout.fragment_miesieczne_oplaty_v2, container, false);

        Button button = (Button)fragView.findViewById(R.id.pokaz_oplaty);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pokazMiesieczneOplaty();
            }
        });

        return fragView;
    }

    public void pokazMiesieczneOplaty(){
        Spinner miesiac = (Spinner)fragView.findViewById(R.id.miesiac);
        String miesiacS = miesiac.getSelectedItem().toString();

        Spinner rok = (Spinner)fragView.findViewById(R.id.rok);
        String rokS = rok.getSelectedItem().toString();

        //TODO: Wyciągnąć z bazy dane dotyczące opłat i przedstawić we fragmencie listy
        //test
        oplaty.clear();
        oplaty.add(rokS + "-" + miesiacS);
        oplaty.add("test");

        Fragment fragment = new MiesieczneOplatyListFragment();
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(R.id.list_fragment, fragment);
        ft.commit();
    }

}
