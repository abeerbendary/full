package com.example.abeer.quarantine.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
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
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.abeer.quarantine.R;
import com.example.abeer.quarantine.adapter.AdapterCheckRequest;
import com.example.abeer.quarantine.databinding.ActivityMainListofchipmentBinding;
import com.example.abeer.quarantine.functions.Public_function;
import com.example.abeer.quarantine.model.RequestTreatmentData;
import com.example.abeer.quarantine.presenter.ClickCustomCheckRequest;
import com.example.abeer.quarantine.remote.ApiCall;
import com.example.abeer.quarantine.remote.PlantQurDBHelper;
import com.example.abeer.quarantine.remote.data.DataManger;
import com.example.abeer.quarantine.remote.data.IDataValue;
import com.example.abeer.quarantine.viewmodel.Emp_Committe;
import com.example.abeer.quarantine.viewmodel.ExportCheckRequest;
import com.example.abeer.quarantine.viewmodel.ListItemDataDetail;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity_Listofchipment extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DataManger dataManger;
    List<ExportCheckRequest>[] totalexportCheckRequestList = new List[1];
    List<ExportCheckRequest> EX_exportCheckRequestList = new ArrayList<>();
    List<ExportCheckRequest> IM_exportCheckRequestList = new ArrayList<>();
    List<ExportCheckRequest> EXTRA_exportCheckRequestList = new ArrayList<>();
    RequestTreatmentData requestTreatmentData = new RequestTreatmentData();
    ActivityMainListofchipmentBinding activityMainListofchipmentBinding;
    String ipadrass;
    Gson gson;
    Context context = this;
    Button shownavbar;
    DrawerLayout drawer;
    LocationManager manager;
    Location location;
    boolean checked;
    AdapterCheckRequest adapterCheckRequest;
    Long EmpId;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor prefsEditor;
    Public_function public_function = new Public_function();
    PlantQurDBHelper plantQurDBHelper;
    String checkdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            activityMainListofchipmentBinding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_main__listofchipment);
        } catch (Exception ex) {
            int s = 10;
        }
        shownavbar = findViewById(R.id.shownavbar);
        dataManger = new DataManger(this);
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
        sharedPreferences = getApplicationContext().getSharedPreferences("SharedPreference", 0);
        ipadrass = sharedPreferences.getString("ipadrass", "");
        EmpId = sharedPreferences.getLong("EmpId", 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        get_list_chipment();
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        location = public_function.getlocation(context, manager);
    }

    public void get_list_chipment() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date(System.currentTimeMillis());
        checkdate = String.valueOf(formatter.format(date));
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileData = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mWifi.isConnected()||mobileData.isConnected()) {
            String url = ipadrass + ApiCall.UrlListOfChipment + "User_Id=" + EmpId + "&Check_Date=" + checkdate;
            dataManger.SendVollyRequestJsonArrayGet(this, Request.Method.GET, url, new IDataValue() {
                @Override
                public void Success(Object response) throws JSONException {
                    String Result = response.toString();
                    gson = new Gson();
                    totalexportCheckRequestList[0] = Arrays.asList(gson.fromJson(Result, ExportCheckRequest[].class));
                    Fill_Data_Dependant_Type(totalexportCheckRequestList);
                }

                @Override
                public void Error(VolleyError error) {

                }
            });
        } else {
            PlantQurDBHelper plantQurDBHelper = new PlantQurDBHelper(context);
            totalexportCheckRequestList[0] = plantQurDBHelper.GetExportCheckRequest(EmpId, checkdate);
            if (!totalexportCheckRequestList[0].isEmpty()) {
                Fill_Data_Dependant_Type(totalexportCheckRequestList);
            } else {
                public_function.AlertDialog("برجاء الاتصال بالشبكه", context, false);
            }
        }
    }

    public void Fill_Data_Dependant_Type(List<ExportCheckRequest>[] totalexportCheckRequestList) {
        int size = totalexportCheckRequestList[0].size();
        int isExport;
        for (int x = 0; x < size; x++) {
            isExport = totalexportCheckRequestList[0].get(x).getIsExport();
            if (isExport == 1) {
                EX_exportCheckRequestList.add(totalexportCheckRequestList[0].get(x));
            } else if (isExport == 2) {
                IM_exportCheckRequestList.add(totalexportCheckRequestList[0].get(x));
            } else {
                EXTRA_exportCheckRequestList.add(totalexportCheckRequestList[0].get(x));
            }
        }
        filladapter(EX_exportCheckRequestList);
    }

    public void filladapter(final List<ExportCheckRequest> CheckRequestList) {

        adapterCheckRequest = new AdapterCheckRequest(CheckRequestList, context, new ClickCustomCheckRequest() {
            @Override
            public void CheckRequest_click(View view, ExportCheckRequest exportCheckRequest) {
                prefsEditor = sharedPreferences.edit();
                prefsEditor.putString("num_Request", exportCheckRequest.getCheckRequest_Number());        // Saving integer
                prefsEditor.putString("checkRequest_Id", String.valueOf(exportCheckRequest.getCheckRequest_Id()));
                prefsEditor.putLong("Committee_ID", Long.valueOf(exportCheckRequest.getCommittee_ID()));
                prefsEditor.putInt("RequestCommittee_Status_Id", exportCheckRequest.getRequestCommittee_Status_Id());
                prefsEditor.putString("BarCode", exportCheckRequest.getBarCode());
                prefsEditor.putInt("Committee_Type_Id", exportCheckRequest.getCommittee_Type_Id());
                if (location.getLongitude() != 0 && location.getLatitude() != 0) {
                    prefsEditor.putLong("Latitude", (long) location.getLatitude());
                    prefsEditor.putLong("Longitude", (long) location.getLongitude());
                }
                ArrayList<Emp_Committe> emp_committeArrayList = new ArrayList<>();
                HashMap<String, Integer> hashMap = new HashMap<>();
                try {
                    emp_committeArrayList.addAll(exportCheckRequest.Handle_Emp_Committe());
                    for (int j = 0; j < emp_committeArrayList.size(); j++) {
                        if (emp_committeArrayList.get(j).getISAdmin().equals((short) 1) && emp_committeArrayList.get(j).getEmployee_Id().equals(EmpId)) {
                            prefsEditor.putBoolean("ISAdmin", true);
                        } else if (emp_committeArrayList.get(j).getISAdmin().equals((short) 0) && emp_committeArrayList.get(j).getEmployee_Id().equals(EmpId)) {
                            prefsEditor.putBoolean("ISAdmin", false);
                        }
                    }
                    prefsEditor.apply();
                    plantQurDBHelper = new PlantQurDBHelper(context);
                    exportCheckRequest.setCheck_Date(checkdate);
                    if (plantQurDBHelper.Insert_CommitteeRequestEmployee(context, exportCheckRequest, emp_committeArrayList)) {
                        Toast.makeText(context, "good", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "حدث خطا ما ", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {

                }
                if (exportCheckRequest.getIsExport() == 2) {
                    public_function.AlertDialog("الصفحه جاري العمل عليها ", context, false);
                } else if (exportCheckRequest.getIsExport() == 3) {
//                    prefsEditor = sharedPreferences.edit();
//                    prefsEditor.putString("num_Request", exportCheckRequest.getCheckRequest_Number());        // Saving integer
//                    prefsEditor.putString("checkRequest_Id", String.valueOf(exportCheckRequest.getCheckRequest_Id()));
//                    prefsEditor.putLong("Committee_ID", Long.valueOf(exportCheckRequest.getCommittee_ID()));
//                    prefsEditor.putInt("RequestCommittee_Status_Id", exportCheckRequest.getRequestCommittee_Status_Id());
//                    prefsEditor.putString("BarCode", exportCheckRequest.getBarCode());
//                    prefsEditor.putInt("Committee_Type_Id", exportCheckRequest.getCommittee_Type_Id());
//                    if (location.getLongitude() != 0 && location.getLatitude() != 0) {
//                        prefsEditor.putLong("Latitude", (long) location.getLatitude());
//                        prefsEditor.putLong("Longitude", (long) location.getLongitude());
//                    }
//                    ArrayList<Emp_Committe> emp_committeArrayList = new ArrayList<>();
//                    HashMap<String, Integer> hashMap = new HashMap<>();
//                    try {
//                        emp_committeArrayList.addAll(exportCheckRequest.Handle_Emp_Committe());
//                        for (int j = 0; j < emp_committeArrayList.size(); j++) {
//                            if (emp_committeArrayList.get(j).getISAdmin().equals((short) 1) && emp_committeArrayList.get(j).getEmployee_Id().equals(EmpId)) {
//                                prefsEditor.putBoolean("ISAdmin", true);
//                            } else if (emp_committeArrayList.get(j).getISAdmin().equals((short) 0) && emp_committeArrayList.get(j).getEmployee_Id().equals(EmpId)) {
//                                prefsEditor.putBoolean("ISAdmin", false);
//                            }
//                        }
//                        prefsEditor.apply();
                    //اعتماد محطات
                    if (exportCheckRequest.getCommittee_Type_Id() == 4) {
                        if (sharedPreferences.getBoolean("ISAdmin", false)) {
                            if (exportCheckRequest.getRequestCommittee_Status_Id() == 0) {
                                Intent i = new Intent(context, MainActivity_Station.class);
                                startActivity(i);
                            } else {
                                public_function.AlertDialog("تم تسجيل المحطة", context, false);
                            }
                        } else {
                            if (exportCheckRequest.getRequestCommittee_Status_Id() == 1) {
                                Intent i = new Intent(context, MainActivity_Station.class);
                                startActivity(i);
                            } else {
                                public_function.AlertDialog("لم يتم تسجيل المحطة", context, false);
                            }
                        }
                    }
                    //اعتماد مزارع
                    else if (exportCheckRequest.getCommittee_Type_Id() == 5) {
                        if (sharedPreferences.getBoolean("ISAdmin", false)) {
                            if (exportCheckRequest.getRequestCommittee_Status_Id() == 0) {
                                Intent i = new Intent(context, MainActivity_Farm.class);
                                startActivity(i);
                            } else {
                                public_function.AlertDialog("تم سحب العينات", context, false);
                            }
                        } else {
                            if (exportCheckRequest.getRequestCommittee_Status_Id() == 1) {
                                Intent i = new Intent(context, MainActivity_Farm.class);
                                startActivity(i);
                            } else {
                                public_function.AlertDialog("لم يتم سحب العينات", context, false);
                            }
                        }
                    }
                } else {
//                    prefsEditor = sharedPreferences.edit();
//                    prefsEditor.putString("num_Request", exportCheckRequest.getCheckRequest_Number());        // Saving integer
//                    prefsEditor.putString("checkRequest_Id", String.valueOf(exportCheckRequest.getCheckRequest_Id()));
//                    prefsEditor.putLong("Committee_ID", Long.valueOf(exportCheckRequest.getCommittee_ID()));
//                    prefsEditor.putInt("RequestCommittee_Status_Id", exportCheckRequest.getRequestCommittee_Status_Id());
//                    prefsEditor.putString("BarCode", exportCheckRequest.getBarCode());
//                    prefsEditor.putInt("Committee_Type_Id", exportCheckRequest.getCommittee_Type_Id());
//                    prefsEditor.putInt("request_data", 1);
//                    if (location.getLongitude() != 0 && location.getLatitude() != 0) {
//                        prefsEditor.putLong("Latitude", (long) location.getLatitude());
//                        prefsEditor.putLong("Longitude", (long) location.getLongitude());
//                        Toast.makeText(MainActivity_Listofchipment.this, "" + location.getLatitude() + location.getLongitude(), Toast.LENGTH_LONG).show();
//                    }
//                    ArrayList<Emp_Committe> emp_committeArrayList = new ArrayList<>();
//                    HashMap<String, Integer> hashMap = new HashMap<>();
//                    try {
//                        emp_committeArrayList.addAll(exportCheckRequest.Handle_Emp_Committe());
//                        for (int j = 0; j < emp_committeArrayList.size(); j++) {
//                            if (emp_committeArrayList.get(j).getISAdmin().equals((short) 1) && emp_committeArrayList.get(j).getEmployee_Id().equals(EmpId)) {
//                                prefsEditor.putBoolean("ISAdmin", true);
//                            } else if (emp_committeArrayList.get(j).getISAdmin().equals((short) 0) && emp_committeArrayList.get(j).getEmployee_Id().equals(EmpId)) {
//                                prefsEditor.putBoolean("ISAdmin", false);
//                            }
//                        }
//                        requestTreatmentData = exportCheckRequest.getRequest_Treatment_Data();
//                        int treatment_data = requestTreatmentData.getTreatment_Total();
//                        int sample_data = requestTreatmentData.getAnalysis_Total();
//                        int request_data = 1;
//                        if (treatment_data == -1) {
//                            treatment_data = 0;
//                        }
//                        if (sample_data == -1) {
//                            sample_data = 0;
//                        }
//                        if (request_data == -1) {
//                            request_data = 0;
//                        }
//                        prefsEditor.putInt("treatment_data", treatment_data);
//                        prefsEditor.putInt("sample_data", sample_data);
//                        prefsEditor.putInt("request_data", request_data);
//                        prefsEditor.putInt("totalprocess", treatment_data + sample_data + request_data);

                    ///////////////////////////////////////////////////////////
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    prefsEditor.apply();
                    Intent i = new Intent(context, MainActivity_DetailsListOfChimpments.class);
                    startActivity(i);
                }
            }
        });
        activityMainListofchipmentBinding.contentListofchipment.setAdapter(adapterCheckRequest);
        activityMainListofchipmentBinding.contentListofchipment.recycler.setLayoutManager(new LinearLayoutManager(context));

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            try {
                public_function.logoutoffline(context);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //                public_function.logout(context, ipadrass, sharedPreferences.getString("Token", ""));
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
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void shownav(View view) {
        drawer.openDrawer(GravityCompat.START);
    }

    public void onRadioclicks(View view) {
        checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {

            case R.id.ExportCheck:
                if (checked)
                    filladapter(EX_exportCheckRequestList);
                break;
            case R.id.ImportCheck:
                if (checked)
                    filladapter(IM_exportCheckRequestList);
                break;
            case R.id.ExtraCheck:
                if (checked)
                    filladapter(EXTRA_exportCheckRequestList);
                break;
            default:
                get_list_chipment();
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

}
