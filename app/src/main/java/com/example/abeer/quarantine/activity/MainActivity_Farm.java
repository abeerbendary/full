package com.example.abeer.quarantine.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.abeer.quarantine.R;
import com.example.abeer.quarantine.databinding.ActivityMainFarmBinding;
import com.example.abeer.quarantine.functions.Public_function;
import com.example.abeer.quarantine.presenter.IFarmPresenter;
import com.example.abeer.quarantine.presenter.ISampleFarmConfirm;
import com.example.abeer.quarantine.remote.ApiCall;
import com.example.abeer.quarantine.remote.PlantQurDBHelper;
import com.example.abeer.quarantine.remote.data.DataManger;
import com.example.abeer.quarantine.remote.data.IDataValue;
import com.example.abeer.quarantine.viewmodel.FarmConfirm;
import com.example.abeer.quarantine.viewmodel.FarmDataConfirm;
import com.example.abeer.quarantine.viewmodel.FarmSample;
import com.example.abeer.quarantine.viewmodel.sampleWithDraw.Barcod_Card;
import com.example.abeer.quarantine.viewmodel.sampleWithDraw.ListAnalysis;
import com.example.abeer.quarantine.viewmodel.sampleWithDraw.ListLabName;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity_Farm extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Context context = this;
    LocationManager manager;
    Location location;
    Public_function public_function;
    ActivityMainFarmBinding activityMainFarmBinding;
    DataManger dataManger;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor prefsEditor;
    String Committee_ID;
    String ipadrass;
    DrawerLayout drawer;
    String data;
    Gson gson;
    final ListLabName[] listLabs = new ListLabName[1];
    final ListAnalysis[] listAnalysis = new ListAnalysis[1];
    PlantQurDBHelper plantQurDBHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainFarmBinding = DataBindingUtil.setContentView(this, R.layout.activity_main__farm);
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
        plantQurDBHelper = new PlantQurDBHelper(context);
        sharedPreferences = getApplicationContext().getSharedPreferences("SharedPreference", 0);
        final String num_Request = sharedPreferences.getString("num_Request", "");
        boolean Isadmin = sharedPreferences.getBoolean("ISAdmin", false);
        ((TextView) findViewById(R.id.value_sample_farm)).setText(num_Request);
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
            findViewById(R.id.LayoutResult).setVisibility(View.VISIBLE);
            findViewById(R.id.Layoutconfirm).setVisibility(View.GONE);
            FarmSample farmSample = new FarmSample();
            dataManger.SendVollyRequestJsonObjectGet(this, Request.Method.GET, ipadrass + ApiCall.AnalysisType, new IDataValue() {

                @Override
                public void Success(Object response) {
                    data = response.toString();
                    gson = new Gson();
                    listAnalysis[0] = gson.fromJson(data, ListAnalysis.class);
                    activityMainFarmBinding.contentFarm.setAnalysis(listAnalysis[0]);
                }

                @Override
                public void Error(VolleyError error) {


                }
            });
            activityMainFarmBinding.contentFarm.setFarmResult(farmSample);
            final FarmDataConfirm farmDataConfirm = new FarmDataConfirm();
            activityMainFarmBinding.contentFarm.setIFarmPresenter(new IFarmPresenter() {
                @Override
                public void OnItemSelectedSpinner_Treatment(AdapterView<?> parent, View view, int pos, long id, FarmSample farmSample) {
                    String ID_itemSelected = String.valueOf(listAnalysis[0].obj.get(pos).Value);
                    farmSample.setAnalysisType_ID(Short.parseShort(ID_itemSelected));
                    farmDataConfirm.setAnalysisType(listAnalysis[0].obj.get(pos).DisplayText);
                    dataManger.SendVollyRequestJsonObjectGet(context, Request.Method.GET, ipadrass + ApiCall.UrlLabName + ID_itemSelected, new IDataValue() {
                        @Override
                        public void Success(Object response) {
                            data = response.toString();
                            gson = new Gson();
                            listLabs[0] = gson.fromJson(data, ListLabName.class);
                            activityMainFarmBinding.contentFarm.setLabs(listLabs[0]);
                        }

                        @Override
                        public void Error(VolleyError error) {
                        }
                    });

                }

                @Override
                public void OnItemSelectedSpinner_laboratory(AdapterView<?> parent, View view, int pos, long id, FarmSample farmSample) {
                    farmSample.setAnalysisLabType_ID(listLabs[0].obj.get(pos).ID);
                    farmDataConfirm.setAnalysisLabType_ID(listLabs[0].obj.get(pos).Name_Ar);
                }

                @Override
                public void OnClickSaveFarm(View view, FarmSample farmSample) {
                    manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    location = public_function.getlocation(context, manager);
                    if (location.getLatitude() == 0 && location.getLongitude() == 0) {
                        location.setLatitude(sharedPreferences.getLong("Latitude", 0));
                        location.setLongitude(sharedPreferences.getLong("Longitude", 0));
                    }
                    farmSample.setWithdrawDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
                    farmSample.setLatitude(location.getLatitude());
                    farmSample.setUser_Creation_Date(farmSample.getWithdrawDate());
                    farmSample.setLongitude(location.getLongitude());
                    farmSample.setFarmCode_14("5555555555555");
                    farmSample.setSample_BarCode(sharedPreferences.getString("BarCode", ""));
                    farmSample.setFarmCommittee_ID(sharedPreferences.getLong("Committee_ID", 0));
                    farmSample.setUser_Creation_Id(sharedPreferences.getLong("EmpId", 0));
                    if (farmSample.getAnalysisType_ID() == 0) {
                        public_function.AlertDialog("برجاء تحديد نوع التحليل ", context, false);
                    } else {
                        final String json = new Gson().toJson(farmSample);
                        Toast.makeText(context, json, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject datasend = new JSONObject(json);
                            List<Barcod_Card> barcod_cards = new ArrayList<>();
                            barcod_cards.add(new Barcod_Card(num_Request, "0", farmSample.getSample_BarCode()));
                            plantQurDBHelper.InsertFarmAndStationTable(Long.parseLong(sharedPreferences.getString("checkRequest_Id", "")), json, new Gson().toJson(new FarmDataConfirm(farmSample, farmDataConfirm)));
                            public_function.senddataonlinetoserverformoreprocess(datasend, context, ipadrass + ApiCall.UrlFarmResult, barcod_cards);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }

                @Override
                public void OnClickcancel(View view, FarmSample farmSample) {
                    Intent i = new Intent(context, MainActivity_Listofchipment.class);
                    i.putExtra("type", 3);
                    startActivity(i);
                }

            });
        } else {
            //not Admin
            findViewById(R.id.LayoutResult).setVisibility(View.GONE);
            findViewById(R.id.Layoutconfirm).setVisibility(View.VISIBLE);
            FarmConfirm farmConfirm = new FarmConfirm();
            activityMainFarmBinding.contentFarm.setFarmConfirmResult(farmConfirm);
///////////fill farmDataConfirm / checkRequest_Id or Committee_ID//////////////////not complet data
            Committee_ID = String.valueOf(sharedPreferences.getLong("Committee_ID", 0));
            ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWifi.isConnected()) {
                dataManger.SendVollyRequestJsonObjectGet(this, Request.Method.GET, ipadrass + ApiCall.UrlFarmDataconfirm + Committee_ID + "&IsResult=true", new IDataValue() {
                    @Override
                    public void Success(Object response) throws JSONException {
                        String result = response.toString();
                        gson = new Gson();
                        FarmDataConfirm farmDataConfirm = gson.fromJson(result, FarmDataConfirm.class);
                        plantQurDBHelper.Update_OneColumeAnyTable("JsonConfirm","FarmAndStation",result,"RequestCommittee_ID", sharedPreferences.getString("checkRequest_Id", ""));
                        activityMainFarmBinding.contentFarm.setFarmConfirmdata(farmDataConfirm);
                    }

                    @Override
                    public void Error(VolleyError error) {

                    }
                });
            } else {
                String JsonConfirmData = plantQurDBHelper.GetConfirmDataFarmStation(Long.parseLong(sharedPreferences.getString("checkRequest_Id", "")));
                gson = new Gson();
                FarmDataConfirm farmDataConfirm = gson.fromJson(JsonConfirmData, FarmDataConfirm.class);
                activityMainFarmBinding.contentFarm.setFarmConfirmdata(farmDataConfirm);
            }
            activityMainFarmBinding.contentFarm.setISampleFarmConfirm(new ISampleFarmConfirm() {
                @Override
                public void OnClickRadioFarmConfirm(View view, FarmConfirm farmConfirm) {
                    boolean checked = ((RadioButton) view).isChecked();
                    switch (view.getId()) {
                        case R.id.radio_Is_accept:
                            if (checked)
                                farmConfirm.setAccepted(true);
                            break;
                        case R.id.radio_Is_reject:
                            if (checked)
                                farmConfirm.setAccepted(false);
                            break;
                    }
                }

                @Override
                public void OnClickSaveFarmConfirm(View view, FarmConfirm farmConfirm) {
                    manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    location = public_function.getlocation(context, manager);
                    if (location.getLatitude() == 0 && location.getLongitude() == 0) {
                        location.setLatitude(sharedPreferences.getLong("Latitude", 0));
                        location.setLongitude(sharedPreferences.getLong("Longitude", 0));
                    }
                    farmConfirm.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
                    farmConfirm.setEmployeeId(sharedPreferences.getLong("EmpId", 0));
                    farmConfirm.setFarm_Committee_ID(sharedPreferences.getLong("Committee_ID", 0));
                    farmConfirm.setID(0);
                    farmConfirm.setLatitude(location.getLatitude());
                    farmConfirm.setLongitude(location.getLongitude());
                    if (String.valueOf(farmConfirm.isAccepted()).isEmpty()) {
                        public_function.AlertDialog("برجاء تسجيل رايك ", context, false);
                    } else {
                        final String json = new Gson().toJson(farmConfirm);
                        // Toast.makeText(context, json, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject datasend = new JSONObject(json);
                            plantQurDBHelper.Update_OneColumeAnyTable("JsonConfirmResult","FarmAndStation",json,"RequestCommittee_ID", sharedPreferences.getString("checkRequest_Id", ""));
                            public_function.senddataonlinetoserverformoreprocess(datasend, context, ipadrass + ApiCall.UrlFarmResultconfirm);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }

                @Override
                public void OnClickcancel(View view, FarmConfirm farmConfirm) {
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
                public_function.NavMenuClickgetsqlite(context, ipadrass, sharedPreferences.getString("Token", ""));
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
