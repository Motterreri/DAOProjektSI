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

import java.util.ArrayList;

import wat.learning.android.si.daoprojektsi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FakturyListFragment extends ListFragment {


    public FakturyListFragment() {
        // Required empty public constructor
    }

    public static interface FakturyListener{
        void itemClickedF(long id);
    }

    private FakturyListener listener;

    public ArrayList<String> faktury = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        faktury = ((FinanseFragment)getParentFragment()).finanse;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(), android.R.layout.simple_list_item_1,faktury
        );
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        listener = (FakturyListener)activity;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener != null) {
            //TODO: przekazać ID faktury aby wczytać ją w kolejnym fragmencie
            listener.itemClickedF(id);
        }
    }
}
