package wat.learning.android.si.daoprojektsi.Fragments.Main;


import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import wat.learning.android.si.daoprojektsi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SprzetyListFragment extends ListFragment {

    public static interface SprzetyListener{
        void itemClickedS(long id);
    }

    public SprzetyListFragment() {
        // Required empty public constructor
    }

    private SprzetyListener listener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //TODO: Pobieranie listy sprzetow

        String [] sprzety = new String[20 /*length*/];
        for(int i = 0; i < sprzety.length; i++){
            sprzety[i] = "Sprzet: " + i + "\nNazwisko lokatora: " + i;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(), android.R.layout.simple_list_item_1, sprzety);

        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener = (SprzetyListener)activity;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if(listener != null)
            listener.itemClickedS(id);
        //TODO: zmienić aby przekazywało ID wiadomosci
    }

}
