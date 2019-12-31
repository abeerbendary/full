package com.example.abeer.quarantine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.abeer.quarantine.R;
import com.example.abeer.quarantine.adapter.AdapterItemData;
import com.example.abeer.quarantine.adapter.AdapterLivingObjects;
import com.example.abeer.quarantine.adapter.AdapterPlantProduct;
import com.example.abeer.quarantine.adapter.AdapterUnlivingObjects;
import com.example.abeer.quarantine.databinding.ActivityMainDetailsListOfChimpmentsBinding;
import com.example.abeer.quarantine.functions.Public_function;
import com.example.abeer.quarantine.presenter.ClickCustomItemData;
import com.example.abeer.quarantine.remote.ApiCall;
import com.example.abeer.quarantine.remote.PlantQurDBHelper;
import com.example.abeer.quarantine.remote.data.DataManger;
import com.example.abeer.quarantine.remote.data.IDataValue;
import com.example.abeer.quarantine.viewmodel.DataForCardItems;
import com.example.abeer.quarantine.viewmodel.Emp_Committe;
import com.example.abeer.quarantine.viewmodel.ListDetailsCheckRequestNew;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity_DetailsListOfChimpments extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    boolean clicked;
    WebView webView;
    LinearLayout plant, part_Plantproduct, unLiving_Objects, Living_Objects, general_admin, outlet, company, person, admin;
    DataManger dataManger;
    Context context = this;
    ListDetailsCheckRequestNew daa;
    String ipadrass;
    String num_Request;
    String Request_id;
    DrawerLayout drawer;
    JSONObject jsonObj;
    Gson gson;
    long EmpId;
    SharedPreferences sharedPreferences;
    Public_function public_function;
    LocationManager manager;
    PlantQurDBHelper plantQurDBHelper;
    Emp_Committe emp_committe;
    int Committee_Type_Id;
    ActivityMainDetailsListOfChimpmentsBinding activityMainDetailsListOfChimpmentsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainDetailsListOfChimpmentsBinding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_main__details_list_of_chimpments);
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
        num_Request = sharedPreferences.getString("num_Request", "");
        Request_id = sharedPreferences.getString("checkRequest_Id", "");
        ipadrass = sharedPreferences.getString("ipadrass", "");
        EmpId = sharedPreferences.getLong("EmpId", 0);
        Committee_Type_Id=sharedPreferences.getInt("Committee_Type_Id",0);
        public_function = new Public_function();
        dataManger = new DataManger(this);
        plant = findViewById(R.id.plant);
        part_Plantproduct = findViewById(R.id.part_Plantproduct);
        unLiving_Objects = findViewById(R.id.unLiving_Objects);
        Living_Objects = findViewById(R.id.Living_Objects);
        outlet = findViewById(R.id.outlet);
        general_admin = findViewById(R.id.general_admin);
        company = findViewById(R.id.company);
        admin = findViewById(R.id.admin);
        person = findViewById(R.id.person);
        ((TextView) findViewById(R.id.value_request)).setText(num_Request);
    }

    @Override
    protected void onStart() {
        super.onStart();
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = public_function.getlocation(context, manager);
        if (location.getLatitude() != 0 && location.getLongitude() != 0) {
            SharedPreferences sharedPreferences;
            SharedPreferences.Editor prefsEditor;
            sharedPreferences = getApplicationContext().getSharedPreferences("SharedPreference", 0);
            prefsEditor = sharedPreferences.edit();
            prefsEditor.putLong("Latitude", (long) location.getLatitude());
            prefsEditor.putLong("Longitude", (long) location.getLongitude());
            Toast.makeText(this, "" + location.getLatitude() + location.getLongitude(), Toast.LENGTH_LONG).show();
        }
        plantQurDBHelper = new PlantQurDBHelper(context);
        boolean ISadmin = plantQurDBHelper.getISAdmin(EmpId,  Long.parseLong(Request_id));
        emp_committe=new Emp_Committe(ISadmin);
        String Details_json = plantQurDBHelper.Get_Data_for_RequestCommittee_working("Details_json", Long.valueOf(Request_id));

        if (Details_json != null) {
            gson = new Gson();
            daa = gson.fromJson(Details_json, ListDetailsCheckRequestNew.class);
            activityMainDetailsListOfChimpmentsBinding.contentDetailsListChimpments.setDetaill(daa);
            HashMap<Integer, Boolean> types = new HashMap<>();
            types = plantQurDBHelper.SelectTypeItemsforRequest_ItemData(Long.valueOf(Request_id));
            Types(types);
        } else {
            ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWifi.isConnected()) {
                dataManger.SendVollyRequestJsonObjectGet(this, Request.Method.GET, ipadrass + ApiCall.UrlDetailsCheckRequest + Request_id+"&Committee_Type_Id="+Committee_Type_Id, new IDataValue() {
                    @Override
                    public void Success(Object response) throws JSONException {
                        String result = response.toString();
                        gson = new Gson();
                        daa = gson.fromJson(result, ListDetailsCheckRequestNew.class);
                        activityMainDetailsListOfChimpmentsBinding.contentDetailsListChimpments.setDetaill(daa);
                        ListDetailsCheckRequestNew sqlite = new ListDetailsCheckRequestNew(true, daa);
                        JSONObject json = new JSONObject(new Gson().toJson(sqlite, ListDetailsCheckRequestNew.class));
                        plantQurDBHelper.Update_OneColumeAnyTable("Details_json", "RequestCommittee", json.toString(), "_id", Request_id);
                        gson = new Gson();
                        String item_data = daa.getItem_Data();
                        if (item_data != null) {
                            jsonObj = new JSONObject();
                            jsonObj = XML.toJSONObject(item_data);
                            HashMap<Integer, Boolean> types = new HashMap<>();
                            types = plantQurDBHelper.InsertItemsforRequest_ItemData(Long.valueOf(Request_id), jsonObj);
                            Types(types);
                        }
                    }

                    @Override
                    public void Error(VolleyError error) {

                    }
                });

            }else {
                public_function.AlertDialog("برجاء الاتصال بالشبكه", context, false);
            }
        }

    }

    public void Types(HashMap<Integer, Boolean> types) {
        if (!types.get(4).booleanValue()) {
            findViewById(R.id.stitle1).setVisibility(View.GONE);
        }
        if (!types.get(5).booleanValue()) {
            findViewById(R.id.stitle2).setVisibility(View.GONE);
        }
        if (!types.get(16).booleanValue()) {
            findViewById(R.id.stitle3).setVisibility(View.GONE);
        }
        if (!types.get(33).booleanValue()) {
            findViewById(R.id.stitle4).setVisibility(View.GONE);
        }
    }

    public void stitleclick(View view) throws JSONException {
        clicked = ((TextView) view).isClickable();
        switch (view.getId()) {
            case R.id.stitle1:
                if (clicked) {
                    plant.setVisibility(View.VISIBLE);
                    findViewById(R.id.recycler1).setVisibility(View.VISIBLE);
                    List<DataForCardItems> dataForCardItemsList = new ArrayList<>();
                    dataForCardItemsList = plantQurDBHelper.GetDataForItems(Long.valueOf(Request_id), 4);
                    AdapterItemData adapterCheckRequest = new AdapterItemData(dataForCardItemsList,emp_committe, context, new ClickCustomItemData() {
                        @Override
                        public void plant_click(View view, DataForCardItems itemData) {
                            SharedPreferences sharedPreferences;
                            SharedPreferences.Editor prefsEditor;
                            sharedPreferences = getApplicationContext().getSharedPreferences("SharedPreference", 0);
                            prefsEditor = sharedPreferences.edit();
                            prefsEditor.putLong("Item_id", itemData.getRequest_Item_ID());
                            prefsEditor.apply();
                            Intent i = new Intent(MainActivity_DetailsListOfChimpments.this, MainActivity_subdetails.class);
//                            i.putExtra("_id", itemData.getRequest_Item_ID());
                            i.putExtra("typenum", 4);
                            startActivity(i);
                        }

                        @Override
                        public void comfirm_click(View view, DataForCardItems itemData) {
                            plantQurDBHelper = new PlantQurDBHelper(context);
                            ////not need now ////////////
                            int Has_Result = plantQurDBHelper.Get_Data_For_Items_RetutnInt("Has_Result", itemData.Request_Item_ID);
                            ///////////////////////////
                            Intent i = new Intent(MainActivity_DetailsListOfChimpments.this, MainActivity_Confirm.class);
                            startActivity(i);

                        }
                    });
                    activityMainDetailsListOfChimpmentsBinding.contentDetailsListChimpments.setAdapter(adapterCheckRequest);
                    activityMainDetailsListOfChimpmentsBinding.contentDetailsListChimpments.recycler1.setLayoutManager(new LinearLayoutManager(context));

                    part_Plantproduct.setVisibility(View.GONE);
                    unLiving_Objects.setVisibility(View.GONE);
                    Living_Objects.setVisibility(View.GONE);
                    findViewById(R.id.stitle1).setBackgroundResource(R.drawable.btnshadowclicked);
                    findViewById(R.id.stitle1).setEnabled(false);
                    findViewById(R.id.stitle2).setBackgroundResource(R.drawable.btnshadow);
                    findViewById(R.id.stitle2).setEnabled(true);
                    findViewById(R.id.stitle3).setBackgroundResource(R.drawable.btnshadow);
                    findViewById(R.id.stitle3).setEnabled(true);
                    findViewById(R.id.stitle4).setBackgroundResource(R.drawable.btnshadow);
                    findViewById(R.id.stitle4).setEnabled(true);
                }
                break;
            case R.id.stitle2:
                if (clicked) {
                    plant.setVisibility(View.GONE);
                    part_Plantproduct.setVisibility(View.VISIBLE);
                    findViewById(R.id.recycler2).setVisibility(View.VISIBLE);
                    List<DataForCardItems> dataForCardItemsList = new ArrayList<>();
                    dataForCardItemsList = plantQurDBHelper.GetDataForItems(Long.valueOf(Request_id), 5);
                    AdapterPlantProduct adapterCheckRequest = new AdapterPlantProduct(dataForCardItemsList,emp_committe, context, new ClickCustomItemData() {
                        @Override
                        public void plant_click(View view, DataForCardItems itemData) {
                            SharedPreferences sharedPreferences;
                            SharedPreferences.Editor prefsEditor;
                            sharedPreferences = getApplicationContext().getSharedPreferences("SharedPreference", 0);
                            prefsEditor = sharedPreferences.edit();
                            prefsEditor.putLong("Item_id", itemData.getRequest_Item_ID());
                            prefsEditor.apply();
                            Intent i = new Intent(MainActivity_DetailsListOfChimpments.this, MainActivity_subdetails.class);
//                            i.putExtra("_id", itemData.getRequest_Item_ID());
                            i.putExtra("typenum", 5);
                            startActivity(i);
                        }

                        @Override
                        public void comfirm_click(View view, DataForCardItems itemData) {

                        }
                    });
                    activityMainDetailsListOfChimpmentsBinding.contentDetailsListChimpments.setAdapterplant(adapterCheckRequest);
                    activityMainDetailsListOfChimpmentsBinding.contentDetailsListChimpments.recycler2.setLayoutManager(new LinearLayoutManager(context));
                    unLiving_Objects.setVisibility(View.GONE);
                    Living_Objects.setVisibility(View.GONE);
                    findViewById(R.id.stitle2).setBackgroundResource(R.drawable.btnshadowclicked);
                    findViewById(R.id.stitle2).setEnabled(false);
                    findViewById(R.id.stitle1).setBackgroundResource(R.drawable.btnshadow);
                    findViewById(R.id.stitle1).setEnabled(true);
                    findViewById(R.id.stitle3).setBackgroundResource(R.drawable.btnshadow);
                    findViewById(R.id.stitle3).setEnabled(true);
                    findViewById(R.id.stitle4).setBackgroundResource(R.drawable.btnshadow);
                    findViewById(R.id.stitle4).setEnabled(true);

                }
                break;
            case R.id.stitle3:
                if (clicked) {
                    plant.setVisibility(View.GONE);
                    part_Plantproduct.setVisibility(View.GONE);
                    Living_Objects.setVisibility(View.VISIBLE);
                    findViewById(R.id.recycler3).setVisibility(View.VISIBLE);
                    List<DataForCardItems> dataForCardItemsList = new ArrayList<>();
                    dataForCardItemsList = plantQurDBHelper.GetDataForItems(Long.valueOf(Request_id), 16);
                    AdapterLivingObjects adapterCheckRequest = new AdapterLivingObjects(dataForCardItemsList,emp_committe, context, new ClickCustomItemData() {
                        @Override
                        public void plant_click(View view, DataForCardItems itemData) {
                            SharedPreferences sharedPreferences;
                            SharedPreferences.Editor prefsEditor;
                            sharedPreferences = getApplicationContext().getSharedPreferences("SharedPreference", 0);
                            prefsEditor = sharedPreferences.edit();
                            prefsEditor.putLong("Item_id", itemData.getRequest_Item_ID());
                            prefsEditor.apply();
                            Intent i = new Intent(MainActivity_DetailsListOfChimpments.this, MainActivity_subdetails.class);
//                            i.putExtra("_id", itemData.getRequest_Item_ID());
                            i.putExtra("typenum", 16);
                            startActivity(i);
                        }

                        @Override
                        public void comfirm_click(View view, DataForCardItems itemData) {

                        }
                    });
                    activityMainDetailsListOfChimpmentsBinding.contentDetailsListChimpments.setAdapterLiving(adapterCheckRequest);
                    activityMainDetailsListOfChimpmentsBinding.contentDetailsListChimpments.recycler3.setLayoutManager(new LinearLayoutManager(context));
                    unLiving_Objects.setVisibility(View.GONE);
                    findViewById(R.id.stitle3).setBackgroundResource(R.drawable.btnshadowclicked);
                    findViewById(R.id.stitle3).setEnabled(false);
                    findViewById(R.id.stitle2).setBackgroundResource(R.drawable.btnshadow);
                    findViewById(R.id.stitle2).setEnabled(true);
                    findViewById(R.id.stitle1).setBackgroundResource(R.drawable.btnshadow);
                    findViewById(R.id.stitle1).setEnabled(true);
                    findViewById(R.id.stitle4).setBackgroundResource(R.drawable.btnshadow);
                    findViewById(R.id.stitle4).setEnabled(true);
                }
                break;
            case R.id.stitle4:
                if (clicked) {
                    plant.setVisibility(View.GONE);
                    part_Plantproduct.setVisibility(View.GONE);
                    unLiving_Objects.setVisibility(View.VISIBLE);
                    findViewById(R.id.recycler4).setVisibility(View.VISIBLE);
                    List<DataForCardItems> dataForCardItemsList = new ArrayList<>();
                    dataForCardItemsList = plantQurDBHelper.GetDataForItems(Long.valueOf(Request_id), 33);
                    AdapterUnlivingObjects adapterCheckRequest = new AdapterUnlivingObjects(dataForCardItemsList,emp_committe, context, new ClickCustomItemData() {
                        @Override
                        public void plant_click(View view, DataForCardItems itemData) {
                            SharedPreferences sharedPreferences;
                            SharedPreferences.Editor prefsEditor;
                            sharedPreferences = getApplicationContext().getSharedPreferences("SharedPreference", 0);
                            prefsEditor = sharedPreferences.edit();
                            prefsEditor.putLong("Item_id", itemData.getRequest_Item_ID());
                            prefsEditor.apply();
                            Intent i = new Intent(MainActivity_DetailsListOfChimpments.this, MainActivity_subdetails.class);
//                            i.putExtra("_id", itemData.getRequest_Item_ID());
                            i.putExtra("typenum", 33);
                            startActivity(i);
                        }

                        @Override
                        public void comfirm_click(View view, DataForCardItems itemData) {

                        }
                    });
                    activityMainDetailsListOfChimpmentsBinding.contentDetailsListChimpments.setAdapterunLiving(adapterCheckRequest);
                    activityMainDetailsListOfChimpmentsBinding.contentDetailsListChimpments.recycler4.setLayoutManager(new LinearLayoutManager(context));
                }
                Living_Objects.setVisibility(View.GONE);
                findViewById(R.id.stitle4).setBackgroundResource(R.drawable.btnshadowclicked);
                findViewById(R.id.stitle4).setEnabled(false);
                findViewById(R.id.stitle2).setBackgroundResource(R.drawable.btnshadow);
                findViewById(R.id.stitle2).setEnabled(true);
                findViewById(R.id.stitle3).setBackgroundResource(R.drawable.btnshadow);
                findViewById(R.id.stitle3).setEnabled(true);
                findViewById(R.id.stitle1).setBackgroundResource(R.drawable.btnshadow);
                findViewById(R.id.stitle1).setEnabled(true);

                break;
        }
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        plantQurDBHelper = new PlantQurDBHelper(context);
        int count = Integer.parseInt(plantQurDBHelper.Get_Data_for_RequestCommittee_working("Total_process", Long.parseLong(Request_id)));
         if(count==0) {
             plantQurDBHelper.update_counterResultForAdmin_New(context, ipadrass, Long.parseLong(Request_id),EmpId,true);
         }else{
             public_function.AlertDialogTwoButton("برجاء العلم في حالة الخروج لا يمكنك الدخول علي الشحنة مره اخري",context,ipadrass,EmpId,Long.parseLong(Request_id),false);
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
//                public_function.NavMenuClickgetsqlite(context,ipadrass,sharedPreferences.getLong("EmpId", (long) -1));
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


    public void showPDF(View view) {
        String pdf = "http://www.adobe.com/devnet/acrobat/pdfs/pdf_open_parameters.pdf"; // pdf link
        webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf);
    }

    public void shownav(View view) {
        drawer.openDrawer(GravityCompat.START);
    }
}
