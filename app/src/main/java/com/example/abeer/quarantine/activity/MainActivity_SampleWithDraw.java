package com.example.abeer.quarantine.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.abeer.quarantine.R;
import com.example.abeer.quarantine.adapter.MyAdapterforRecycler;
import com.example.abeer.quarantine.databinding.ActivityMainSampleWithDrawBinding;
import com.example.abeer.quarantine.functions.Public_function;
import com.example.abeer.quarantine.model.Checkup_Result_Model;
import com.example.abeer.quarantine.model.Sample_Result_Model;
import com.example.abeer.quarantine.presenter.Clickcustum;
import com.example.abeer.quarantine.presenter.ISamplePresenter;
import com.example.abeer.quarantine.remote.ApiCall;
import com.example.abeer.quarantine.remote.PlantQurDBHelper;
import com.example.abeer.quarantine.remote.data.DataManger;
import com.example.abeer.quarantine.remote.data.IDataValue;
import com.example.abeer.quarantine.viewmodel.DataForCardItems;
import com.example.abeer.quarantine.viewmodel.ItemData;
import com.example.abeer.quarantine.viewmodel.ItemLotatData;
import com.example.abeer.quarantine.viewmodel.ListItemLotat;
import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.SampleData_LOts;
import com.example.abeer.quarantine.viewmodel.sampleWithDraw.Barcod_Card;
import com.example.abeer.quarantine.viewmodel.sampleWithDraw.ListAnalysis;
import com.example.abeer.quarantine.viewmodel.sampleWithDraw.ListLabName;
import com.example.abeer.quarantine.viewmodel.sampleWithDraw.Sample_Result;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity_SampleWithDraw extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener//,LocationListener
{
    Context context = this;
    String num_Request;
    ActivityMainSampleWithDrawBinding activityMainSampleWithDrawBinding;
    DataManger dataManger;
    Gson gson;
    String ID_itemSelected;
    RadioGroup radioGroup_Samples;
    LinearLayout linear_Layout_Sample_full;
    LinearLayout linear_Layout_Sample_part;
    LinearLayout linear_Layout_btns_Sample;
    LinearLayout linear_Layout_num_talab;
    LocationManager manager;
    TextView title_radio_group;
    String data;
    String ipadrass;
    Sample_Result_Model sampleResultModel;
    boolean checked;
    Sample_Result sampleResult;
    final ListLabName[] listLabs = new ListLabName[1];
    final ListAnalysis[] listAnalysis = new ListAnalysis[1];
    JSONObject datas;
    View viewforbutton;
    TextView value_sample;
    Location location;
    PlantQurDBHelper plantQurDBHelper;
    SharedPreferences sharedPreferences;
    JSONArray datasendarray = new JSONArray();
    Public_function public_function;
    SharedPreferences.Editor prefsEditor;
    List<Barcod_Card> barcod_cards = new ArrayList<>();
    String Request_id;
    ItemData ItemData;
    ListItemLotat listItemLotat;
    String JsonTextDetails;
    Long ID_Item;
    ArrayList<ItemLotatData> itemLotatData22 = null;
    int lengthsave = 0;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainSampleWithDrawBinding = DataBindingUtil.setContentView(this, R.layout.activity_main__sample_with_draw);
        dataManger = new DataManger(this);
        sampleResult = new Sample_Result();
        radioGroup_Samples = findViewById(R.id.radioGroup_Samples);
        linear_Layout_Sample_full = findViewById(R.id.linear_Layout_Sample_full);
        linear_Layout_Sample_part = findViewById(R.id.linear_Layout_Sample_part);
        linear_Layout_btns_Sample = findViewById(R.id.btns_Sample);
        linear_Layout_num_talab = findViewById(R.id.num);
        title_radio_group = findViewById(R.id.title_radio_group_Sample);
        sharedPreferences = getApplicationContext().getSharedPreferences("SharedPreference", 0);
        num_Request = sharedPreferences.getString("num_Request", "");
        Request_id = sharedPreferences.getString("checkRequest_Id", "");
        ipadrass = sharedPreferences.getString("ipadrass", "");
        ID_Item = sharedPreferences.getLong("Item_id", 0);
        value_sample = findViewById(R.id.value_sample);
        value_sample.setText(num_Request);
        public_function = new Public_function();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        location = public_function.getlocation(context, manager);
        if (location.getLongitude() != 0 && location.getLatitude() != 0) {
            prefsEditor = sharedPreferences.edit();
            prefsEditor.putLong("Latitude", (long) location.getLatitude());
            prefsEditor.putLong("Longitude", (long) location.getLongitude());
            prefsEditor.apply();
            Toast.makeText(context, "" + location.getLatitude() + location.getLongitude(), Toast.LENGTH_SHORT).show();
        }
        JsonTextDetails = plantQurDBHelper.Get_Data_For_ItemsReturnString("JsonTextDetails", ID_Item);
        gson = new Gson();
        ItemData = new ItemData();
        ItemData = gson.fromJson(JsonTextDetails, ItemData.class);
        itemLotatData22 = new ArrayList<>();
        Object n = ItemData.getLot_Data();
        String d = gson.toJson(n);

        listItemLotat = gson.fromJson(d, ListItemLotat.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String result;
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
//                result = data.getStringExtra("ValuesPopUpLots");
                String LOTS_NUM = data.getStringExtra("Num_Lots");
                String barcode=data.getStringExtra("barcode");
//                try {
//                    datas = new JSONObject(result);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                lengthsave += 1;
//                if (datas != null) {
//                    if (datasendarray != null) {
//                        datasendarray.put(datas);
//                    } else {
//                        datasendarray = new JSONArray();
//                        datasendarray.put(datas);
//                    }
//                    try {
                        barcod_cards.add(new Barcod_Card(num_Request, LOTS_NUM,barcode));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                viewforbutton.findViewById(R.id.buttsss).setVisibility(View.GONE);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                viewforbutton.findViewById(R.id.buttsss).setVisibility(View.VISIBLE);
                Toast.makeText(this, "nhvgbn", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void Sample_full() {
        dataManger.SendVollyRequestJsonObjectGet(this, Request.Method.GET, ipadrass + ApiCall.AnalysisType, new IDataValue() {
            @Override
            public void Success(Object response) {
                data = response.toString();
                gson = new Gson();
                listAnalysis[0] = gson.fromJson(data, ListAnalysis.class);
                activityMainSampleWithDrawBinding.contentSampleWithDraw.setAnalysis(listAnalysis[0]);
            }

            @Override
            public void Error(VolleyError error) {


            }
        });


        activityMainSampleWithDrawBinding.contentSampleWithDraw.setSampleResult(sampleResult);
        activityMainSampleWithDrawBinding.contentSampleWithDraw.setISamplePresenter(new ISamplePresenter() {
            @Override
            public void OnItemSelectedSpinner_Treatment(AdapterView<?> parent, View view, int pos, long id, Sample_Result sample_result) {
                ID_itemSelected = String.valueOf(listAnalysis[0].obj.get(pos).Value);
                sample_result.setAnalysisType_ID(Short.parseShort(ID_itemSelected));
                Toast.makeText(MainActivity_SampleWithDraw.this, "" + ID_itemSelected, Toast.LENGTH_SHORT).show();
                dataManger.SendVollyRequestJsonObjectGet(MainActivity_SampleWithDraw.this, Request.Method.GET, ipadrass + ApiCall.UrlLabName + ID_itemSelected, new IDataValue() {
                    @Override
                    public void Success(Object response) {
                        data = response.toString();
                        gson = new Gson();
                        listLabs[0] = gson.fromJson(data, ListLabName.class);
                        activityMainSampleWithDrawBinding.contentSampleWithDraw.setLabs(listLabs[0]);
                    }

                    @Override
                    public void Error(VolleyError error) {
                        Toast.makeText(MainActivity_SampleWithDraw.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void OnItemSelectedSpinner_laboratory(AdapterView<?> parent, View view, int pos, long id, Sample_Result sample_result) {

                ID_itemSelected = String.valueOf(listLabs[0].obj.get(pos).ID);
                sample_result.setLaboratory_ID(Integer.parseInt(ID_itemSelected));
            }

            @Override
            public void OnClickSaveLots(View view, Sample_Result sampleResult) {
                manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                location = public_function.getlocation(context, manager);
                if (location.getLatitude() == 0 && location.getLongitude() == 0) {
                    location.setLatitude(sharedPreferences.getLong("Latitude", 0));
                    location.setLongitude(sharedPreferences.getLong("Longitude", 0));
                }
                sampleResult.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
                sampleResult.setLot_ID((long) 0);
                sampleResult.setLatitude(location.getLatitude());
                sampleResult.setLongitude(location.getLongitude());
                sampleResult.setBarCode(sharedPreferences.getString("BarCode", "") + 0);
                sampleResultModel = new Sample_Result_Model(sampleResult);
                if (sampleResult.getAnalysisType_ID() == 0) {

                    public_function.AlertDialog("برجاء تحديد نوع التحليل ", context, false);
                } else {
                    final String json = new Gson().toJson(sampleResultModel);
                    plantQurDBHelper.Insert_result("SampleData", Long.valueOf(Request_id), "Isanalysis", sharedPreferences.getLong("Item_id", (long) 0), 0, json, json);

                    barcod_cards.add(new Barcod_Card(num_Request, "0", sampleResult.getBarCode() + "0"));
                    Intent intent = new Intent(context, Generate_barcode.class);
                    intent.putExtra("barcode", (Serializable) barcod_cards);
                    intent.putExtra("contextsample", "true");
                    ((Activity) context).startActivity(intent);
                    barcod_cards = null;
                    datasendarray = null;
                }
            }

            @Override
            public void OnClickcancel(View view, Sample_Result sampleResult) {
                datasendarray = null;
                finish();
                startActivity(getIntent());
            }
        });
    }

    public void Sample_part() {
        size = listItemLotat._x0040_temp_table_Lot.size();
        MyAdapterforRecycler dd = null;
        if (size == 2) {
            if (listItemLotat._x0040_temp_table_Lot.get(1).getLot_Number() == 0 &&
                    listItemLotat._x0040_temp_table_Lot.get(0).getLot_Number() == 0) {
                itemLotatData22.add(new ItemLotatData("لا توجد لوطات"));
                dd = new MyAdapterforRecycler(itemLotatData22, context);
                size = 0;
            } else {
                if (listItemLotat._x0040_temp_table_Lot.get(1).getLot_Number() == 0) {
                    listItemLotat._x0040_temp_table_Lot.remove(1);
                    size -= 1;
                }
            }
        }
        if (dd == null) {
            DataForCardItems dataForCardItems = plantQurDBHelper.GetDataForItems(ID_Item);
            dd = new MyAdapterforRecycler(dataForCardItems, listItemLotat, context, new Clickcustum() {
                @Override
                public void button_click(View view, ItemLotatData sampleData_lOts) {
                    Intent i = new Intent(context, Sample_Lots.class);
                    i.putExtra("ID", sampleData_lOts.Lot_ID);
                    i.putExtra("LOTS_NUM", "" + sampleData_lOts.getLot_Number());
                    viewforbutton = view;
                    startActivityForResult(i, 1);
                }
            }
            );
        }
        activityMainSampleWithDrawBinding.contentSampleWithDraw.setMyAdapter(dd);
                activityMainSampleWithDrawBinding.contentSampleWithDraw.recyclerViewSample.setLayoutManager(new LinearLayoutManager(context));

        activityMainSampleWithDrawBinding.contentSampleWithDraw.setSampleResult(sampleResult);
        activityMainSampleWithDrawBinding.contentSampleWithDraw.setISamplePresenter(new ISamplePresenter() {
            @Override
            public void OnItemSelectedSpinner_Treatment(AdapterView<?> parent, View view, int pos, long id, Sample_Result sample_result) {

            }

            @Override
            public void OnItemSelectedSpinner_laboratory(AdapterView<?> parent, View view, int pos, long id, Sample_Result sample_result) {

            }

            @Override
            public void OnClickSaveLots(View view, Sample_Result sampleResult) {
//                Intent intent = new Intent(context, Generate_barcode.class);
//                intent.putExtra("barcode", (Serializable) barcod_cards);
//                intent.putExtra("contextsample", "true");
//                ((Activity) context).startActivity(intent);
                ////////////start code shared preference ////////////////////////////////////////////////////////////////
//                try {
//                    prefsEditor = sharedPreferences.edit();
//                    prefsEditor.putInt("sample_data", sharedPreferences.getInt("sample_data", -2) - 1);
//                    prefsEditor.putInt("totalprocess", sharedPreferences.getInt("totalprocess", -2) - 1);
//                    prefsEditor.apply();
//                    String SampleDto = sharedPreferences.getString("SampleDto", "");
//                    if (SampleDto == "") {
//                        prefsEditor = sharedPreferences.edit();
//                        prefsEditor.putString("SampleDto", String.valueOf(datasendarray));
//                        prefsEditor.apply();
//                    } else {
//                        JSONArray jsonArray = new JSONArray(SampleDto);
//                        for (int i = 0; i < datasendarray.length(); i++) {
//                            JSONObject jsonObject = datasendarray.getJSONObject(i);
//                            jsonArray.put(jsonObject);
//                        }
//                        prefsEditor = sharedPreferences.edit();
//                        prefsEditor.putString("SampleDto", String.valueOf(jsonArray));
//                        prefsEditor.apply();
//
//                    }
//                    int totalprocess = sharedPreferences.getInt("totalprocess", 0);
//
//                    if (totalprocess == 0) {
//                        JSONObject jsonObject = new JSONObject();
//                        jsonObject.put("Committe_Dto", sharedPreferences.getString("Committe_Dto", ""));
//                        jsonObject.put("SampleDto", sharedPreferences.getString("SampleDto", ""));
//                        jsonObject.put("Treatment_Dto", sharedPreferences.getString("Treatment_Dto", ""));
//                        Toast.makeText(context, jsonObject.toString(), Toast.LENGTH_LONG).show();
//                        public_function.senddataonlinetoserverformoreprocess(jsonObject, context, ipadrass + ApiCall.UrlSavemultprocess, barcod_cards);
//                        prefsEditor.clear();
//                    } else {
//                        public_function.showbarcodelist(context, barcod_cards);
//                        public_function.AlertDialog("برجاء اتمام العمليات ", context, true);
//
//                    }
// } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                ////////////end code shared preference ////////////////////////////////////////////////////////////////
                if (size == lengthsave) {
                    Intent intent = new Intent(context, Generate_barcode.class);
                    intent.putExtra("barcode", (Serializable) barcod_cards);
                    intent.putExtra("contextsample", "true");
                    ((Activity) context).startActivity(intent);
//                    int count = plantQurDBHelper.update_counterResultForAdmin(context, ipadrass, "Isanalysis", Long.valueOf(Request_id), sharedPreferences.getLong("Item_id", (long) 0), sharedPreferences.getLong("EmpId", (long) 0));
//                    if (count == 0) {
//                        Intent i = new Intent(context, MainActivity_Listofchipment.class);
//                        startActivity(i);
//                    } else {
//                        Intent i = new Intent(context, MainActivity_DetailsListOfChimpments.class);
//                        startActivity(i);
//                    }
                } else {
                    public_function.AlertDialogTwoButton("يوجد لوطات لم يتم عمل سحب عينه عليها",context,false,barcod_cards);
                }
                datasendarray = null;
                datasendarray = null;
                barcod_cards = null;
            }

            @Override
            public void OnClickcancel(View view, Sample_Result sampleResult) {
                datasendarray = null;
                finish();
                startActivity(getIntent());
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radio_Sample_full:
                if (checked)
                    linear_Layout_Sample_full.setVisibility(View.VISIBLE);
                linear_Layout_btns_Sample.setVisibility(View.VISIBLE);
                title_radio_group.setText("سحب عينة للشحنة");
                radioGroup_Samples.setVisibility(View.GONE);
                linear_Layout_Sample_part.setVisibility(View.GONE);
                linear_Layout_num_talab.setVisibility(View.VISIBLE);
                Sample_full();
                break;
            case R.id.radio_Sample_part:
                if (checked)
                    linear_Layout_Sample_full.setVisibility(View.GONE);
                linear_Layout_btns_Sample.setVisibility(View.VISIBLE);
                radioGroup_Samples.setVisibility(View.GONE);
                title_radio_group.setText("سحب عينات للوطات");
                Sample_part();
                linear_Layout_Sample_part.setVisibility(View.VISIBLE);
                linear_Layout_num_talab.setVisibility(View.VISIBLE);
                break;

            default:
                linear_Layout_Sample_full.setVisibility(View.GONE);
                linear_Layout_btns_Sample.setVisibility(View.GONE);
                title_radio_group.setText("سحب العينات ");
                radioGroup_Samples.setVisibility(View.VISIBLE);
                linear_Layout_Sample_part.setVisibility(View.GONE);
                linear_Layout_num_talab.setVisibility(View.GONE);
                break;

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void Generat_Barcode() {

        Intent i = new Intent(context, Generate_barcode.class);
        i.putExtra("barcode", sharedPreferences.getString("BarCode", "") + 0);
        startActivity(i);
    }
}
