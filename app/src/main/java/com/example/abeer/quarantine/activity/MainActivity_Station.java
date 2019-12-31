package com.example.abeer.quarantine.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.abeer.quarantine.R;
import com.example.abeer.quarantine.databinding.ActivityMainStationBinding;
import com.example.abeer.quarantine.functions.Public_function;
import com.example.abeer.quarantine.presenter.IStationConfirm;
import com.example.abeer.quarantine.presenter.IStationPresenter;
import com.example.abeer.quarantine.remote.ApiCall;
import com.example.abeer.quarantine.remote.PlantQurDBHelper;
import com.example.abeer.quarantine.remote.data.DataManger;
import com.example.abeer.quarantine.remote.data.IDataValue;
import com.example.abeer.quarantine.viewmodel.Station;
import com.example.abeer.quarantine.viewmodel.StationConfirmResult;
import com.example.abeer.quarantine.viewmodel.StationDataConfirm;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity_Station extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context = this;
    LocationManager manager;
    Location location;
    Public_function public_function;
    ActivityMainStationBinding activityMainStationBinding;
    DataManger dataManger;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor prefsEditor;
    String Committee_ID;
    String ipadrass;
    DrawerLayout drawer;
    Station StationResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainStationBinding = DataBindingUtil.setContentView(this, R.layout.activity_main__station);
        dataManger = new DataManger(this);
        public_function = new Public_function();
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
        sharedPreferences = getApplicationContext().getSharedPreferences("SharedPreference", 0);
        final String num_Request = sharedPreferences.getString("num_Request", "");
        boolean Isadmin = sharedPreferences.getBoolean("ISAdmin", false);
        ((TextView) findViewById(R.id.value_request)).setText(num_Request);
        ipadrass = sharedPreferences.getString("ipadrass", "");
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        location = public_function.getlocation(context, manager);
        if (location.getLongitude() != 0 && location.getLatitude() != 0) {
            prefsEditor = sharedPreferences.edit();
            prefsEditor.putLong("Latitude", (long) location.getLatitude());
            prefsEditor.putLong("Longitude", (long) location.getLongitude());
            prefsEditor.apply();
//            Toast.makeText(context,""+location.getLatitude()+ location.getLongitude() , Toast.LENGTH_SHORT).show();
        }
        if (Isadmin) {
            findViewById(R.id.station_result).setVisibility(View.VISIBLE);
            findViewById(R.id.station_confirm).setVisibility(View.GONE);
            StationResult = new Station();
            activityMainStationBinding.contentStation.setStationResult(StationResult);
            activityMainStationBinding.contentStation.setIStationPresenter(new IStationPresenter() {
                @Override
                public void OnClickRadioStation(View view, Station station) {
                    boolean checked = ((RadioButton) view).isChecked();
                    switch (view.getId()) {
                        case R.id.radio_accept:
                            if (checked)
                                station.setAccept(true);
                            break;
                        case R.id.radio_reject:
                            if (checked)
                                station.setAccept(false);
                            break;
                    }
                }

                @Override
                public void OnClickSaveStation(View view, Station station) {
                    manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    location = public_function.getlocation(context, manager);
                    if (location.getLatitude() == 0 && location.getLongitude() == 0) {
                        location.setLatitude(sharedPreferences.getLong("Latitude", 0));
                        location.setLongitude(sharedPreferences.getLong("Longitude", 0));
                    }
                    station.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
                    station.setLatitude(location.getLatitude());
                    station.setLongitude(location.getLongitude());
                    station.setCommittee_ID(sharedPreferences.getLong("Committee_ID", 0));
                    station.setEmployeeId(sharedPreferences.getLong("EmpId", 0));
                    final String json = new Gson().toJson(station);
                    Toast.makeText(context, json, Toast.LENGTH_LONG).show();
                    try {
                        JSONObject datasend = new JSONObject(json);
                        public_function.senddataonlinetoserverformoreprocess(datasend, context, ipadrass + ApiCall.UrlStationResult);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void OnClickcancel(View view, Station station) {
                    Intent i = new Intent(context, MainActivity_Listofchipment.class);
                    i.putExtra("type", 3);
                    startActivity(i);
                }
            });
        } else {
            findViewById(R.id.station_result).setVisibility(View.GONE);
            findViewById(R.id.station_confirm).setVisibility(View.VISIBLE);
             StationResult = new Station();
            StationConfirmResult StationConfirmResult = new StationConfirmResult();
            activityMainStationBinding.contentStation.setStationConfirmResult(StationConfirmResult);
            Committee_ID = String.valueOf(sharedPreferences.getLong("Committee_ID", 0));
            dataManger.SendVollyRequestJsonObjectGet(this, Request.Method.GET, ipadrass + ApiCall.UrlStationDataconfirm + Committee_ID + "&IsResult=true", new IDataValue() {
                @Override
                public void Success(Object response) throws JSONException {
                    String result = response.toString();
                    Gson gson = new Gson();
                    StationDataConfirm stationDataConfirm = gson.fromJson(result, StationDataConfirm.class);
                    activityMainStationBinding.contentStation.setStationDataConfirm(stationDataConfirm);
                }

                @Override
                public void Error(VolleyError error) {

                }
            });
            activityMainStationBinding.contentStation.setIStationconfirmPresenter(new IStationConfirm() {
                @Override
                public void OnClickRadioStation(View view, StationConfirmResult station) {
                    boolean checked = ((RadioButton) view).isChecked();
                    switch (view.getId()) {
                        case R.id.radio_accept:
                            if (checked)
                                station.setAccepted(true);
                            break;
                        case R.id.radio_reject:
                            if (checked)
                                station.setAccepted(false);
                            break;
                    }
                }

                @Override
                public void OnClickSaveStation(View view, StationConfirmResult station) {
                    manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    location = public_function.getlocation(context, manager);
                    if (location.getLatitude() == 0 && location.getLongitude() == 0) {
                        location.setLatitude(sharedPreferences.getLong("Latitude", 0));
                        location.setLongitude(sharedPreferences.getLong("Longitude", 0));
                    }
                    station.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
                    station.setLatitude((long) location.getLatitude());
                    station.setLongitude((long) location.getLongitude());
                    station.setStation_Committee_ID(sharedPreferences.getLong("Committee_ID", 0));
                    station.setEmployeeId(sharedPreferences.getLong("EmpId", 0));
                    final String json = new Gson().toJson(station);
//                    Toast.makeText(context, json, Toast.LENGTH_LONG).show();
                    try {
                        Toast.makeText(context, "تم الحفظ بنجاح", Toast.LENGTH_SHORT).show();
                        JSONObject datasend = new JSONObject(json);
//                        PlantQurDBHelper Plant=new PlantQurDBHelper(context);
//                        Plant.Insert_Dashforanything(datasend.toString());
                        Intent i = new Intent(context, MainActivity_Listofchipment.class);
                        i.putExtra("type", 3);
                        startActivity(i);
                        public_function.senddataonlinetoserverformoreprocess(datasend, context, ipadrass + ApiCall.UrlStationResultconfirm);
                    } catch (JSONException e) {


                    }
                }

                @Override
                public void OnClickcancel(View view, StationConfirmResult station) {
                    Intent i = new Intent(context, MainActivity_Listofchipment.class);
                    i.putExtra("type", 3);
                    startActivity(i);
                }
            });
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
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

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void shownav(View view) {
        drawer.openDrawer(GravityCompat.START);
    }
}
