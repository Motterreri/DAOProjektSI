package wat.learning.android.si.daoprojektsi.Activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import wat.learning.android.si.daoprojektsi.ActionCode;
import wat.learning.android.si.daoprojektsi.Database.DatabaseConnection;
import wat.learning.android.si.daoprojektsi.Database.DatabaseService;
import wat.learning.android.si.daoprojektsi.Database.MyResultReceiver;
import wat.learning.android.si.daoprojektsi.Fragments.Main.ButtonsFragment;
import wat.learning.android.si.daoprojektsi.Fragments.Main.FakturyListFragment;
import wat.learning.android.si.daoprojektsi.Fragments.Main.FinanseFragment;
import wat.learning.android.si.daoprojektsi.Fragments.Main.GlosowanieFragment;
import wat.learning.android.si.daoprojektsi.Fragments.Main.GlosowanieFragment.GlosowanieListener;
import wat.learning.android.si.daoprojektsi.Fragments.Main.MiesieczneOplatyListFragment;
import wat.learning.android.si.daoprojektsi.Fragments.Main.MiesieczneOplatyV2Fragment;
import wat.learning.android.si.daoprojektsi.Fragments.Main.NowaWiadomoscFragment;
import wat.learning.android.si.daoprojektsi.Fragments.Main.OdbiorWiadomosciFragment;
import wat.learning.android.si.daoprojektsi.Fragments.Main.OdczytLicznikowFragment;
import wat.learning.android.si.daoprojektsi.Fragments.Main.ProjektFragment;
import wat.learning.android.si.daoprojektsi.Fragments.Main.SkrzynkaOdbiorczaFragment;
import wat.learning.android.si.daoprojektsi.Fragments.Main.SzczegolyFakturyFragment;
import wat.learning.android.si.daoprojektsi.Fragments.Main.SzczegolyGlosowaniaFragment;
import wat.learning.android.si.daoprojektsi.R;

public class MainActivity extends AppCompatActivity implements MyResultReceiver.Receiver,
        SkrzynkaOdbiorczaFragment.SkrzynkaListListener, GlosowanieListener, FakturyListFragment.FakturyListener {

    private int lokatorId;
    private DatabaseConnection dbConn;
    private MyResultReceiver mReceiver;
    private ArrayList<String> list = new ArrayList<>();

//    private String[] titles;
//    private ListView drawerList;
//    private DrawerLayout drawerLayout;
//    private ActionBarDrawerToggle drawerToggle;
//
//    private class DrawerItemClickListener implements ListView.OnItemClickListener{
//
//        @Override
//        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//            selectItem(i);
//        }
//    }
//
//    private void selectItem(int position){
//        Fragment fragment;
//        switch (position){
//            case 0:
//                fragment = new OdczytLicznikowFragment();
//                break;
//            case 1:
//                fragment = new MiesieczneOplatyV2Fragment();
//                break;
//            case 2:
//                fragment = new NowaWiadomoscFragment();
//                break;
//            default:
//                fragment = new OdczytLicznikowFragment();
//        }
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.frameMain, fragment);
//        ft.addToBackStack(null);
//        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//        ft.commit();
//
//        drawerLayout.closeDrawer(drawerList);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String jsonConnection = getIntent().getExtras().getString("Connection");
        dbConn = new Gson().fromJson(jsonConnection, DatabaseConnection.class);

        mReceiver = new MyResultReceiver(new Handler());
        mReceiver.setReceiver(this);

        lokatorId = getIntent().getIntExtra("id", 0);

//        titles = getResources().getStringArray(R.array.menu);
//        drawerList = (ListView)findViewById(R.id.drawer);
//        drawerList.setAdapter(new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_activated_1, titles));
//        drawerList.setOnItemClickListener(new DrawerItemClickListener());
//
//        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if(savedInstanceState == null){
//            selectItem(0);
//        }
//
//        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
//                R.string.open_drawer, R.string.close_drawer){
//            @Override
//            public void onDrawerClosed(View view){
//                super.onDrawerClosed(view);
//                invalidateOptionsMenu();
//            }
//
//            @Override
//            public void onDrawerOpened(View view){
//                super.onDrawerOpened(view);
//                invalidateOptionsMenu();
//            }
//        };
//        drawerLayout.setDrawerListener(drawerToggle);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setHomeButtonEnabled(true);

        mediaList();
        showButtons();
    }

    public void showNowaWiadomosc(){
        NowaWiadomoscFragment nowaWiadomoscFragment = new NowaWiadomoscFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frameMain, nowaWiadomoscFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        drawerToggle.syncState();
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        drawerToggle.onConfigurationChanged(newConfig);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        if(drawerToggle.onOptionsItemSelected(item))
//            return true;
//        return false;
//    }

    public void showButtons(){
        ButtonsFragment buttonsFragment = new ButtonsFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frameMain, buttonsFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public void showOdczyt(){
        OdczytLicznikowFragment odczytLicznikowFragment = new OdczytLicznikowFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frameMain, odczytLicznikowFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public void showSkrzynka(){
        SkrzynkaOdbiorczaFragment skrzynkaOdbiorczaFragment = new SkrzynkaOdbiorczaFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frameMain, skrzynkaOdbiorczaFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public void showGlosowanie(){
        GlosowanieFragment glosowanieFragment = new GlosowanieFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frameMain, glosowanieFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public void showOplaty(){
        MiesieczneOplatyV2Fragment miesieczneOplatyFragment = new MiesieczneOplatyV2Fragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frameMain, miesieczneOplatyFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public void dodajOdczyt(String media, String odczyt, String dataOdczytu){
        //TODO: co ma robić po dodaniu
    }

    public void nowaWiadomosc(String odbiorca, String temat, String wiadomosc){
        //TODO: wysylanie wiadomosci (narazie bez zalacznikow)
    }

    public void odpowiedz(String odbiorca, String temat){
        NowaWiadomoscFragment nowaWiadomoscFragment = new NowaWiadomoscFragment();
        nowaWiadomoscFragment.odbiorcaO = odbiorca;
        nowaWiadomoscFragment.tematO = temat;
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frameMain, nowaWiadomoscFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public void usun(long id){
        //TODO: Usun wiadomosc ze skrzynki odbiorczej
    }

    public void pokazProjekt(long id)
    {
        ProjektFragment projektFragment = new ProjektFragment();
        projektFragment.setId(id);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frameMain, projektFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public void showFinanse(){
        FinanseFragment finanseFragment = new FinanseFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frameMain, finanseFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
//    @Override
//    public void onBackPressed() {
////        if(lokatorId != 0) {
////            Intent logoutIntent = new Intent(this, DatabaseService.class);
////            logoutIntent.putExtra("code", ActionCode.LOGOUT);
////            logoutIntent.putExtra("id", lokatorId);
////            startService(logoutIntent);
////        }
//        //TODO: FIX! Logout from db - dziala ale wywala apke
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//        finish();
//        System.exit(0);
//    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {

        switch (resultCode){
            case 6:
                list = resultData.getStringArrayList("MediaList");
        }

    }

    private void mediaList(){
        Intent intent = new Intent(this, DatabaseService.class);
        intent.putExtra("receiverTag", mReceiver);
        intent.putExtra("code", ActionCode.GET_MEDIA);
        intent.putExtra("Connection", new Gson().toJson(dbConn));
        startService(intent);
    }

    public ArrayList<String> getMediaList(){
        return list;
    }

    @Override
    public void itemClicked(long id) {
        //TODO: przekazanie ID wiadomosci do fragmentu odbioru wiadomosci
        OdbiorWiadomosciFragment odbiorWiadomosciFragment = new OdbiorWiadomosciFragment();
        odbiorWiadomosciFragment.setId(id);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frameMain, odbiorWiadomosciFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    @Override
    public void itemClickedG(long id) {
        //TODO: przekazanie ID glosowania do fragmentu szegółów głosowania
        SzczegolyGlosowaniaFragment szczegolyGlosowaniaFragment = new SzczegolyGlosowaniaFragment();
        szczegolyGlosowaniaFragment.setId(id);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frameMain, szczegolyGlosowaniaFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }


    @Override
    public void itemClickedF(long id) {
        //TODO: przekazanie ID faktury do fragmentu szczegółów fakury
        SzczegolyFakturyFragment szczegolyFakturyFragment = new SzczegolyFakturyFragment();
        szczegolyFakturyFragment.setId(id);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frameMain, szczegolyFakturyFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}
