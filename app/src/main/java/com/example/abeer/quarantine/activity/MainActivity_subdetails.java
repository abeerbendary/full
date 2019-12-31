package com.example.abeer.quarantine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abeer.quarantine.R;
import com.example.abeer.quarantine.adapter.AdapterEshtratat;
import com.example.abeer.quarantine.adapter.AdapterLotat;
import com.example.abeer.quarantine.databinding.ActivityMainSubdetailsBinding;
import com.example.abeer.quarantine.functions.Public_function;
import com.example.abeer.quarantine.remote.PlantQurDBHelper;
import com.example.abeer.quarantine.viewmodel.ItemConstrainsData;
import com.example.abeer.quarantine.viewmodel.ItemData;
import com.example.abeer.quarantine.viewmodel.ItemLotatData;
import com.example.abeer.quarantine.viewmodel.ListItemConstrains;
import com.example.abeer.quarantine.viewmodel.ListItemLotat;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_subdetails extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Context context = this;
    boolean clicked;
    LinearLayout linearLayoutconstrans;
    LinearLayout linearLayoutlotss;
    ListItemConstrains listItemConstrains = new ListItemConstrains();
    ListItemLotat listItemLotat = new ListItemLotat();
    List<ItemConstrainsData> itemConstrainsData22 = null;
    List<ItemLotatData> itemLotatData22 = null;
    DrawerLayout drawer;
    String ipadrass;
    String num_Request;
    AdapterEshtratat adapterEshtratat;
    AdapterLotat adapterLotat2;
    Public_function public_function;
    LocationManager manager;
    Location location;
    SharedPreferences sharedPreferences;
    Gson gson;
    int typenum;
    long ID_Item;
    PlantQurDBHelper plantQurDBHelper;
    String JsonTextDetails;
    ItemData ItemData;
    LinearLayout plant, product, live, unlive;
    ActivityMainSubdetailsBinding activityMainSubdetailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainSubdetailsBinding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_main_subdetails);
        linearLayoutconstrans = findViewById(R.id.Linearconstrans);
        linearLayoutlotss = findViewById(R.id.Linearlotss);
        gson = new Gson();
        sharedPreferences = getApplicationContext().getSharedPreferences("SharedPreference", 0);
        ipadrass = sharedPreferences.getString("ipadrass", "");
        num_Request = sharedPreferences.getString("num_Request", "");
        public_function = new Public_function();
        typenum = getIntent().getExtras().getInt("typenum");
        ID_Item = sharedPreferences.getLong("Item_id", 0);
        plantQurDBHelper = new PlantQurDBHelper(context);
        JsonTextDetails = plantQurDBHelper.Get_Data_For_ItemsReturnString("JsonTextDetails", ID_Item);
        gson = new Gson();
        ItemData = new ItemData();
        ItemData = gson.fromJson(JsonTextDetails, ItemData.class);
        plant = findViewById(R.id.Linear_Plant);
        product = findViewById(R.id.Linear_product);
        live = findViewById(R.id.Linear_live);
        unlive = findViewById(R.id.Linear_unlive);
        if (typenum == 4) {
            plant.setVisibility(View.VISIBLE);
            product.setVisibility(View.GONE);
            live.setVisibility(View.GONE);
            unlive.setVisibility(View.GONE);
        } else if (typenum == 5) {
            plant.setVisibility(View.GONE);
            product.setVisibility(View.VISIBLE);
            live.setVisibility(View.GONE);
            unlive.setVisibility(View.GONE);
        } else if (typenum == 16) {
            plant.setVisibility(View.GONE);
            product.setVisibility(View.GONE);
            live.setVisibility(View.VISIBLE);
            unlive.setVisibility(View.GONE);
        } else if (typenum == 33) {
            plant.setVisibility(View.GONE);
            product.setVisibility(View.GONE);
            live.setVisibility(View.GONE);
            unlive.setVisibility(View.VISIBLE);
        }
        activityMainSubdetailsBinding.contentMainActivitySubdetails.setItemm(ItemData);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        location = public_function.getlocation(context, manager);
        if (location.getLongitude() != 0 && location.getLatitude() != 0) {
            SharedPreferences.Editor prefsEditor;
            sharedPreferences = getApplicationContext().getSharedPreferences("SharedPreference", 0);
            prefsEditor = sharedPreferences.edit();
            prefsEditor.putLong("Latitude", (long) location.getLatitude());
            prefsEditor.putLong("Longitude", (long) location.getLongitude());
            Toast.makeText(this, "" + location.getLatitude() + location.getLongitude(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        try {
            if (id == R.id.logout) {
                //for online
                // public_function.NavMenuClickgetsqlite(context);
//                forOffline
                public_function.NavMenuClickgetsqlite(context,ipadrass,sharedPreferences.getString("Token",""));
            } else {
                public_function.NavMenuClickgetsqlite(context, id, sharedPreferences.getLong("Item_id", (long) 0), sharedPreferences.getLong("EmpId", (long) -1), Long.parseLong(sharedPreferences.getString("checkRequest_Id", "")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onclicktext(View view) throws JSONException {
        clicked = ((TextView) view).isClickable();
        switch (view.getId()) {

            case R.id.constrans:
                if (clicked) {
                    findViewById(R.id.Linearconstrans).setVisibility(View.VISIBLE);
                    findViewById(R.id.Linearlotss).setVisibility(View.GONE);
                    itemConstrainsData22 = new ArrayList<>();
                    Object n = ItemData.getConstrain_Data();
                    String d = gson.toJson(n);
                    listItemConstrains = gson.fromJson(d, ListItemConstrains.class);
                    int size = listItemConstrains._x0040_temp_table_Constrain.size();
                    if (size > 0) {
                        if (listItemConstrains._x0040_temp_table_Constrain.get(1).getConstrainText_Ar().equals("-------------")) {
                            itemConstrainsData22.add(new ItemConstrainsData("لا توجد اشتراطات"));
                            adapterEshtratat = new AdapterEshtratat(itemConstrainsData22, context, ItemData);
                        } else {
                            adapterEshtratat = new AdapterEshtratat(listItemConstrains._x0040_temp_table_Constrain, context, ItemData);

                        }
                    }
                    activityMainSubdetailsBinding.contentMainActivitySubdetails.setAdapter2(adapterEshtratat);
                    activityMainSubdetailsBinding.contentMainActivitySubdetails.detialsconstrans.setLayoutManager(new LinearLayoutManager(context));
                    findViewById(R.id.constrans).setBackgroundResource(R.drawable.btnshadowclicked);
                    findViewById(R.id.constrans).setEnabled(false);
                    findViewById(R.id.lotss).setBackgroundResource(R.drawable.btnshadow);
                    findViewById(R.id.lotss).setEnabled(true);
                }

                break;
            case R.id.lotss:
                if (clicked) {
                    itemLotatData22 = new ArrayList<>();
                    findViewById(R.id.Linearconstrans).setVisibility(View.GONE);
                    findViewById(R.id.Linearlotss).setVisibility(View.VISIBLE);
                    Object n = ItemData.getLot_Data();
                    String d = gson.toJson(n);
                    listItemLotat = gson.fromJson(d, ListItemLotat.class);
                    int size = listItemLotat._x0040_temp_table_Lot.size();
//                    if (size > 0) {
                    if (size == 2) {
                        if (listItemLotat._x0040_temp_table_Lot.get(1).getLot_Number() == 0 &&
                                listItemLotat._x0040_temp_table_Lot.get(0).getLot_Number() == 0) {
                            itemLotatData22.add(new ItemLotatData("لا توجد لوطات"));
                            adapterLotat2 = new AdapterLotat(itemLotatData22, context, ItemData);
                        } else if (listItemLotat._x0040_temp_table_Lot.get(1).getLot_Number() == 0) {
                            adapterLotat2 = new AdapterLotat(listItemLotat._x0040_temp_table_Lot.subList(0, 1), context, ItemData);

                        } else {
                            adapterLotat2 = new AdapterLotat(listItemLotat._x0040_temp_table_Lot, context, ItemData);
                        }

                    } else {

                        adapterLotat2 = new AdapterLotat(listItemLotat._x0040_temp_table_Lot, context, ItemData);
                    }

//                    }
                    activityMainSubdetailsBinding.contentMainActivitySubdetails.setAdapter7(adapterLotat2);
                    activityMainSubdetailsBinding.contentMainActivitySubdetails.detialslotss.setLayoutManager(new LinearLayoutManager(context));
                    findViewById(R.id.lotss).setBackgroundResource(R.drawable.btnshadowclicked);
                    findViewById(R.id.lotss).setEnabled(false);
                    findViewById(R.id.constrans).setBackgroundResource(R.drawable.btnshadow);
                    findViewById(R.id.constrans).setEnabled(true);
                    break;
                }
        }
    }

    public void shownav(View view) {
        drawer.openDrawer(GravityCompat.START);
    }
}
