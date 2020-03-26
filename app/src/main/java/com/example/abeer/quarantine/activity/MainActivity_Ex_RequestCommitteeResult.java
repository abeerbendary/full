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
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.abeer.quarantine.R;
import com.example.abeer.quarantine.adapter.AdapterLotat;
import com.example.abeer.quarantine.adapter.MyAdapterforRecycler;
import com.example.abeer.quarantine.databinding.ActivityMainExRequestCommitteeResultBinding;
import com.example.abeer.quarantine.functions.Public_function;
import com.example.abeer.quarantine.model.Checkup_Result_Model;
import com.example.abeer.quarantine.model.InputFilterMinMax;
import com.example.abeer.quarantine.presenter.Clickcustum;
import com.example.abeer.quarantine.presenter.IPresenter;
import com.example.abeer.quarantine.remote.ApiCall;
import com.example.abeer.quarantine.remote.PlantQurDBHelper;
import com.example.abeer.quarantine.remote.data.DataManger;
import com.example.abeer.quarantine.remote.data.IDataValue;
import com.example.abeer.quarantine.viewmodel.DataForCardItems;
import com.example.abeer.quarantine.viewmodel.ItemData;
import com.example.abeer.quarantine.viewmodel.ItemLotatData;
import com.example.abeer.quarantine.viewmodel.ListItemLotat;
import com.example.abeer.quarantine.viewmodel.confirm.CommiteeDataDetail;
import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.Checkup_Result;
import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.CommitteeResultType;
import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.LISTFamily;
import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.LISTIm_ProcedureType;
import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.LISTMKingdom;
import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.LISTOrder;
import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.LISTPhylum;
import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.SampleData_LOts;
import com.google.common.collect.Range;
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

import static com.basgeekball.awesomevalidation.ValidationStyle.TEXT_INPUT_LAYOUT;

public class MainActivity_Ex_RequestCommitteeResult extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Context context = this;
    String num_Request;
    String Request_id;
    private DataManger DataManger;
    Gson gson;
    String data;
    String IDItemSelect;
    LinearLayout linear_Layout_Examination_full;
    LinearLayout linear_Layout_btns;
    LinearLayout linear_Layout_Examination_part;
    LinearLayout linear_Layout_Damaged;
    TextView title_radio_group;
    RadioGroup radioGroup;
    boolean checked;
    String ipadrass;
    LocationManager manager;
    Location location;
    JSONArray datasendarray = new JSONArray();
    RadioButton Radio_full;
    RadioButton Radio_part;
    Checkup_Result checkup_result;
    final LISTMKingdom[] LISTMKingdom = new LISTMKingdom[1];
    final LISTPhylum[] LISTPhylum = new LISTPhylum[1];
    final LISTOrder[] LISTOrder = new LISTOrder[1];
    final LISTFamily[] LISTFamily = new LISTFamily[1];
    CommitteeResultType[] CommitteeResultTypeLIST = new CommitteeResultType[1];
    final LISTIm_ProcedureType[] LISTIm_ProcedureType = new LISTIm_ProcedureType[1];
    PlantQurDBHelper plantQurDBHelper;
    View viewforbutton;
    TextView value_request;
    Checkup_Result_Model checkupResultModel;
    Public_function public_function = new Public_function();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor prefsEditor;
    ItemData ItemData;
    ListItemLotat listItemLotat;
    String JsonTextDetails;
    Long ID_Item;
    int lengthsave = 0;
    ArrayList<ItemLotatData> itemLotatData22 = null;
    int size;
    ActivityMainExRequestCommitteeResultBinding activityMainExRequestCommitteeResultBinding;
    CommiteeDataDetail commiteeDataDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataManger = new DataManger(this);
        activityMainExRequestCommitteeResultBinding =
                DataBindingUtil.setContentView((Activity) context, R.layout.activity_main__ex__request_committee_result);
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
        sharedPreferences = getApplicationContext().getSharedPreferences("SharedPreference", 0);
        num_Request = sharedPreferences.getString("num_Request", "");
        Request_id = sharedPreferences.getString("checkRequest_Id", "");
        ipadrass = sharedPreferences.getString("ipadrass", "");
        ID_Item = sharedPreferences.getLong("Item_id", 0);
        linear_Layout_Examination_full = findViewById(R.id.linear_Layout_Examination_full);
        linear_Layout_btns = findViewById(R.id.btns);
        linear_Layout_Damaged = findViewById(R.id.damaged);
        linear_Layout_Examination_part = findViewById(R.id.linear_Layout_Examination_part);
        radioGroup = findViewById(R.id.radioGroup_Examination);
        Radio_full = findViewById(R.id.radio_Examination_full);
        Radio_part = findViewById(R.id.radio_Examination_part);
        title_radio_group = findViewById(R.id.title_radio_group);
        value_request = findViewById(R.id.value_request);
        value_request.setText(num_Request);
    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode == 100) {
//            if (grantResults != null && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
//                try {
//                    manager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, (LocationListener) this, null);
//                    Toast.makeText(this, "phase 2", Toast.LENGTH_SHORT).show();
//
//                } catch (SecurityException e) {
//
//                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }

    @Override
    protected void onStart() {
        super.onStart();
        checkup_result = new Checkup_Result();
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
//        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            String[] permissions={Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};
//            ActivityCompat.requestPermissions(this,permissions,100);
//
//            Toast.makeText(this, "phase 1", Toast.LENGTH_SHORT).show();
//        }
//        else{
//            manager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, (LocationListener) this,null);
//            Toast.makeText(this, "sent direct", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                lengthsave += 1;
                viewforbutton.findViewById(R.id.buttsss).setVisibility(View.GONE);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                viewforbutton.findViewById(R.id.buttsss).setVisibility(View.VISIBLE);
                Toast.makeText(this, "nhvgbn", Toast.LENGTH_SHORT).show();
            }
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

    public void Examination_full() {
        commiteeDataDetail = new CommiteeDataDetail();
        EditText edit_num = findViewById(R.id.edit_num);
        final EditText edit_ten = findViewById(R.id.edit_ten);
        final EditText edit_kelo = findViewById(R.id.edit_kelo);
        final EditText edit_gram = findViewById(R.id.edit_gram);
        int successcount = listItemLotat.get_totalPackage_Count();
        final double[] kelowaite = new double[1];
        edit_num.setFilters(new InputFilter[]{new InputFilterMinMax("1", String.valueOf(successcount))});
        final double totalNet_Weight = listItemLotat.get_totalNet_Weight();
        edit_ten.setFilters(new InputFilter[]{new InputFilterMinMax(0, (int) (totalNet_Weight / 1000))});
        edit_kelo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edit_ten.getText().toString().isEmpty()) {
                    kelowaite[0] = totalNet_Weight - ((Double.valueOf(edit_ten.getText().toString())) * 1000);
                    edit_kelo.setFilters(new InputFilter[]{new InputFilterMinMax(0, (int) (kelowaite[0]))});
                } else {
                    edit_kelo.setFilters(new InputFilter[]{new InputFilterMinMax(0, (int) (totalNet_Weight))});
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edit_gram.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edit_kelo.getText().toString().isEmpty()) {
                    double gramewaite = (kelowaite[0] - (Double.valueOf(edit_kelo.getText().toString()))) * 1000;
                    edit_gram.setFilters(new InputFilter[]{new InputFilterMinMax(0, (int) gramewaite)});
                } else {
                    edit_gram.setFilters(new InputFilter[]{new InputFilterMinMax(0, (int) (kelowaite[0] * 1000))});
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
//        if (!edit_ten.getText().toString().isEmpty()) {
//            rightwight += Double.valueOf(edit_ten.getText().toString()) * 1000;
//            double kelowaite = totalNet_Weight - ((Double.valueOf(edit_ten.getText().toString())) * 1000);
//            edit_kelo.setFilters(new InputFilter[]{new InputFilterMinMax(0, (int) (kelowaite))});
//            if (!edit_kelo.getText().toString().isEmpty()) {
//                rightwight += Double.valueOf(edit_kelo.getText().toString());
//                double gramewaite = (kelowaite - (Double.valueOf(edit_kelo.getText().toString()))) * 1000;
//                edit_gram.setFilters(new InputFilter[]{new InputFilterMinMax(0, (int) gramewaite)});
//                if (!edit_gram.getText().toString().isEmpty()) {
//                    rightwight += Double.valueOf(edit_gram.getText().toString()) / 1000;
//                }
//            } else {
//                edit_gram.setFilters(new InputFilter[]{new InputFilterMinMax(0, (int) kelowaite)});
//                if (!edit_gram.getText().toString().isEmpty()) {
//                    rightwight += Double.valueOf(edit_gram.getText().toString()) / 1000;
//                }
//            }
//        } else {
//            edit_kelo.setFilters(new InputFilter[]{new InputFilterMinMax(0, (int) (totalNet_Weight))});
//            if (!edit_kelo.getText().toString().isEmpty()) {
//                rightwight += Double.valueOf(edit_kelo.getText().toString());
//                double gramewaite = (totalNet_Weight - (Double.valueOf(edit_kelo.getText().toString()))) * 1000;
//                edit_gram.setFilters(new InputFilter[]{new InputFilterMinMax(0, (int) (gramewaite))});
//                if (!edit_gram.getText().toString().isEmpty()) {
//                    rightwight += Double.valueOf(edit_gram.getText().toString()) / 1000;
//                }
//            } else {
//                edit_gram.setFilters(new InputFilter[]{new InputFilterMinMax(0, (int) (totalNet_Weight * 1000))});
//                if (!edit_gram.getText().toString().isEmpty()) {
//                    rightwight += Double.valueOf(edit_gram.getText().toString()) / 1000;
//                }
//            }
//        }
        ((TextView) findViewById(R.id.type)).setText(listItemLotat._x0040_temp_table_Lot.get(0).getPackage_Type_Name());

        DataManger.SendVollyRequestJsonObjectGet(context, Request.Method.GET, ipadrass + ApiCall.UrlCommitteeResultType, new IDataValue() {
            @Override
            public void Success(Object response) {
                data = response.toString();
                gson = new Gson();
                CommitteeResultTypeLIST[0] = gson.fromJson(data, CommitteeResultType.class);
                activityMainExRequestCommitteeResultBinding.contentrequestCommitteeResult.setCommitteeResultType(CommitteeResultTypeLIST[0]);
            }

            @Override
            public void Error(VolleyError error) {

            }
        });

        activityMainExRequestCommitteeResultBinding.contentrequestCommitteeResult.setCheckUpResult(checkup_result);

        activityMainExRequestCommitteeResultBinding.contentrequestCommitteeResult.setPresenter(new IPresenter() {

            @Override
            public void OnItemSelectedSpinner_CommitteeResultType(AdapterView<?> parent, View view, int pos, long id, Checkup_Result CheckUpResult) {
                if (pos != 0) {
                    IDItemSelect = String.valueOf(CommitteeResultTypeLIST[0].obj.get(pos).Value);
                    if (IDItemSelect == null) {
                        IDItemSelect = String.valueOf(0);
                    }
                    CheckUpResult.setResult_ID(Integer.parseInt(IDItemSelect));
                    commiteeDataDetail.setName_Ar(CommitteeResultTypeLIST[0].obj.get(pos).getDisplayText());
                    if (IDItemSelect.equals("1") || IDItemSelect.equals("6")) {
                        linear_Layout_Damaged.setVisibility(View.GONE);

                    } else {
                        linear_Layout_Damaged.setVisibility(View.VISIBLE);

                        DataManger.SendVollyRequestJsonObjectGet(context, Request.Method.GET, ipadrass + ApiCall.UrlPlantKingdom, new IDataValue() {
                            @Override
                            public void Success(Object response) {
                                data = response.toString();
                                gson = new Gson();
                                LISTMKingdom[0] = gson.fromJson(data, LISTMKingdom.class);
                                activityMainExRequestCommitteeResultBinding.contentrequestCommitteeResult.setLISTMKingdom((LISTMKingdom[0]));

                            }

                            @Override
                            public void Error(VolleyError error) {

                            }
                        });


                        DataManger.SendVollyRequestJsonObjectGet(context, Request.Method.GET, ipadrass + ApiCall.UrlIm_ProcedureType, new IDataValue() {
                            @Override
                            public void Success(Object response) {
                                data = response.toString();
                                gson = new Gson();
                                LISTIm_ProcedureType[0] = gson.fromJson(data, LISTIm_ProcedureType.class);
                                activityMainExRequestCommitteeResultBinding.contentrequestCommitteeResult.setLISTImProcedureType(LISTIm_ProcedureType[0]);
                            }

                            @Override
                            public void Error(VolleyError error) {

                            }
                        });
                    }
                }
            }


            @Override
            public void OnItemSelectedSpinner_Kingdom(AdapterView<?> parent, View view, int pos, long id, Checkup_Result CheckUpResult) {
                IDItemSelect = String.valueOf(LISTMKingdom[0].obj.get(pos).Value);
                CheckUpResult.setKingdom_ID(Integer.parseInt(IDItemSelect));
                 commiteeDataDetail.setInfection_Type(LISTMKingdom[0].obj.get(pos).DisplayText);
                DataManger.SendVollyRequestJsonObjectGet(context, Request.Method.GET, ipadrass + ApiCall.UrlPlantPhylum + IDItemSelect, new IDataValue() {
                    @Override
                    public void Success(Object response) {
                        data = response.toString();
                        gson = new Gson();
                        LISTPhylum[0] = gson.fromJson(data, LISTPhylum.class);
                        activityMainExRequestCommitteeResultBinding.contentrequestCommitteeResult.setLISTPhylums(LISTPhylum[0]);
                    }

                    @Override
                    public void Error(VolleyError error) {

                    }
                });
            }

            @Override
            public void OnItemSelectedSpinner_Phylum(AdapterView<?> parent, View view, int pos, long id, Checkup_Result CheckUpResult) {
                IDItemSelect = String.valueOf(LISTPhylum[0].obj.get(pos).Value);
                CheckUpResult.setPhylum_ID(Integer.parseInt(IDItemSelect));
                commiteeDataDetail.setInfection_Name(LISTPhylum[0].obj.get(pos).getDisplayText());
                DataManger.SendVollyRequestJsonObjectGet(context, Request.Method.GET, ipadrass + ApiCall.UrlPlantOrder + IDItemSelect, new IDataValue() {
                    @Override
                    public void Success(Object response) {
                        data = response.toString();
                        gson = new Gson();
                        LISTOrder[0] = gson.fromJson(data, LISTOrder.class);
                        activityMainExRequestCommitteeResultBinding.contentrequestCommitteeResult.setLISTOrder(LISTOrder[0]);
                    }

                    @Override
                    public void Error(VolleyError error) {

                    }
                });
            }

            @Override
            public void OnItemSelectedSpinner_Order(AdapterView<?> parent, View view, int pos, long id, Checkup_Result CheckUpResult) {
                IDItemSelect = String.valueOf(LISTOrder[0].obj.get(pos).Value);
                CheckUpResult.setOrder_ID(Integer.parseInt(IDItemSelect));
//                commiteeDataDetail.set
                DataManger.SendVollyRequestJsonObjectGet(context, Request.Method.GET, ipadrass + ApiCall.UrlPlantFamily + IDItemSelect, new IDataValue() {
                    @Override
                    public void Success(Object response) {
                        data = response.toString();
                        gson = new Gson();
                        LISTFamily[0] = gson.fromJson(data, LISTFamily.class);
                        activityMainExRequestCommitteeResultBinding.contentrequestCommitteeResult.setLISTFamily(LISTFamily[0]);
                    }

                    @Override
                    public void Error(VolleyError error) {

                    }
                });
            }

            @Override
            public void OnItemSelectedSpinner_Family(AdapterView<?> parent, View view, int pos, long id, Checkup_Result CheckUpResult) {
                IDItemSelect = String.valueOf(LISTFamily[0].obj.get(pos).Value);
                CheckUpResult.setFamily_ID(Integer.parseInt(IDItemSelect));

            }

            @Override
            public void OnItemSelectedSpinner_Im_ProcedureType(AdapterView<?> parent, View view, int pos, long id, Checkup_Result CheckUpResult) {
                IDItemSelect = String.valueOf(LISTIm_ProcedureType[0].obj.get(pos).Value);
                CheckUpResult.setResult_injury(Integer.parseInt(IDItemSelect));
                commiteeDataDetail.setResult_injury_Name(LISTIm_ProcedureType[0].obj.get(pos).DisplayText);
            }


            @Override
            public void OnClickSaveLots(View view, Checkup_Result checkupResult) {
                manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                location = public_function.getlocation(context, manager);
                if (location.getLatitude() == 0 && location.getLongitude() == 0) {
                    location.setLatitude(sharedPreferences.getLong("Latitude", 0));
                    location.setLongitude(sharedPreferences.getLong("Longitude", 0));
                }
                checkupResult.setlot_ID(0);
                checkupResult.setLatitude(location.getLatitude());
                checkupResult.setLongitude(location.getLongitude());
                checkupResultModel = new Checkup_Result_Model(checkupResult);
//                commiteeDataDetail.set
                if (linear_Layout_Damaged.getVisibility() == View.VISIBLE && (checkupResultModel.getItem__OrderID() == 0 || checkupResultModel.getResult_injuryID() == 0)) {
                    public_function.AlertDialog("برجاء تحديد نوع الاصابة ونتيجة الاصابة", context, false);
                } else if (linear_Layout_Damaged.getVisibility() == View.GONE && (checkupResultModel.getWeight() == 0 || checkupResultModel.getQuantitySize() == 0 || checkupResultModel.getCommitteeResultType_ID() == 0)) {

                    public_function.AlertDialog(" برجاءإستكمال البيانات ", context, false);

                } else if (linear_Layout_Damaged.getVisibility() == View.VISIBLE && (checkupResultModel.getWeight() == 0 || checkupResultModel.getQuantitySize() == 0)) {

                    public_function.AlertDialog(" برجاءإستكمال البيانات العدد والوزن ", context, false);

                } else {
                    checkupResultModel.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
                    final String json = new Gson().toJson(checkupResultModel);

                    plantQurDBHelper.Insert_result("CommitteeResult",sharedPreferences.getLong("Item_id", (long) 0), 0, json, json);
                    plantQurDBHelper.UbdateConterForItemResult("Ischeck",sharedPreferences.getLong("Item_id", (long) 0),Long.valueOf(Request_id));

                    int count = Integer.parseInt(plantQurDBHelper.Get_Data_for_RequestCommittee_working("Total_process", Long.valueOf(Request_id)));
//                    int count = plantQurDBHelper.update_counterResultForAdmin(context, ipadrass, "Ischeck", Long.valueOf(Request_id), sharedPreferences.getLong("Item_id", (long) 0), sharedPreferences.getLong("EmpId", (long) 0));
                    if (count == 0) {
                        plantQurDBHelper.update_counterResultForAdmin_New(context, ipadrass, Long.parseLong(Request_id), sharedPreferences.getLong("EmpId", 0), true);
//                        Intent i = new Intent(context, MainActivity_Listofchipment.class);
//                        startActivity(i);
                    } else {
                        Intent i = new Intent(context, MainActivity_DetailsListOfChimpments.class);
                        startActivity(i);
                    }
                }

            }

            @Override
            public void OnClickcancel(View view, Checkup_Result CheckUpResult) {
                checkup_result = null;
                datasendarray = null;
                finish();
                startActivity(getIntent());
            }

        });

    }

    public void Examination_part() {
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
                    Intent i = new Intent(context, CheckUp_Lots.class);
                    i.putExtra("ID", sampleData_lOts.Lot_ID);
                    i.putExtra("LOTS_NUM", "" + sampleData_lOts.getLot_Number());
                    i.putExtra("Net_Weight", sampleData_lOts.getNet_Weight());
                    i.putExtra("Package_Count", sampleData_lOts.getPackage_Count());
                    i.putExtra("type",sampleData_lOts.getPackage_Type_Name());
                    viewforbutton = view;
                    startActivityForResult(i, 1);
                }
            }
            );
        }
        activityMainExRequestCommitteeResultBinding.contentrequestCommitteeResult.setMyAdapter(dd);
        activityMainExRequestCommitteeResultBinding.contentrequestCommitteeResult.resycler.setLayoutManager(new LinearLayoutManager(context));

        activityMainExRequestCommitteeResultBinding.contentrequestCommitteeResult.setCheckUpResult(checkup_result);
        activityMainExRequestCommitteeResultBinding.contentrequestCommitteeResult.setPresenter(new IPresenter() {
            @Override
            public void OnItemSelectedSpinner_Kingdom(AdapterView<?> parent, View view, int pos, long id, Checkup_Result CheckUpResult) {

            }

            @Override
            public void OnItemSelectedSpinner_Phylum(AdapterView<?> parent, View view, int pos, long id, Checkup_Result CheckUpResult) {

            }

            @Override
            public void OnItemSelectedSpinner_Order(AdapterView<?> parent, View view, int pos, long id, Checkup_Result CheckUpResult) {

            }

            @Override
            public void OnItemSelectedSpinner_Family(AdapterView<?> parent, View view, int pos, long id, Checkup_Result CheckUpResult) {

            }

            @Override
            public void OnItemSelectedSpinner_Im_ProcedureType(AdapterView<?> parent, View view, int pos, long id, Checkup_Result CheckUpResult) {

            }

            @Override
            public void OnItemSelectedSpinner_CommitteeResultType(AdapterView<?> parent, View view, int pos, long id, Checkup_Result CheckUpResult) {

            }

            @Override
            public void OnClickSaveLots(View view, Checkup_Result CheckUpResult) {

//                plantQurDBHelper.update_counterResultForAdmin(context, ipadrass, "Ischeck", Long.valueOf(Request_id), sharedPreferences.getLong("Item_id", (long) 0), sharedPreferences.getLong("EmpId", (long) 0));
////                ////////////start code shared preference ////////////////////////////////////////////////////////////////
////
////                prefsEditor = sharedPreferences.edit();
////                prefsEditor.putInt("request_data", 0);
////                prefsEditor.putInt("totalprocess", sharedPreferences.getInt("totalprocess", 0) - 1);
////                prefsEditor.apply();
////                prefsEditor.commit();
////                try {
////                    if (sharedPreferences.getInt("totalprocess", -2) == 0) {
////                        // public_function.senddataonlinetoserver(datasendarray, context, ipadrass + ApiCall.UrlCommitteeResult);
////                        JSONObject jsonObject = new JSONObject();
////                        jsonObject.put("Committe_Dto", datasendarray);
////                        jsonObject.put("SampleDto", "");
////                        jsonObject.put("Treatment_Dto", "");
////                        public_function.senddataonlinetoserverformoreprocess(jsonObject, context, ipadrass + ApiCall.UrlSavemultprocess);
////                    } else {
////                        //totalprocess != 0
////                        prefsEditor = sharedPreferences.edit();
////                        prefsEditor.putString("Committe_Dto", String.valueOf(datasendarray));
////                        prefsEditor.apply();
////                        prefsEditor.commit();
////                        public_function.AlertDialog("برجاء اتمام العمليات ", context, true);
////                    }
////
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
////                ////////////end code shared preference ////////////////////////////////////////////////////////////////

                if (size == lengthsave) {
                    plantQurDBHelper.UbdateConterForItemResult("Ischeck",sharedPreferences.getLong("Item_id", (long) 0),Long.valueOf(Request_id));
                    int count = Integer.parseInt(plantQurDBHelper.Get_Data_for_RequestCommittee_working("Total_process", Long.valueOf(Request_id)));
//                    int count = plantQurDBHelper.update_counterResultForAdmin(context, ipadrass, "Ischeck", Long.valueOf(Request_id), sharedPreferences.getLong("Item_id", (long) 0), sharedPreferences.getLong("EmpId", (long) 0));
                    if (count == 0) {
                        plantQurDBHelper.update_counterResultForAdmin_New(context, ipadrass, Long.parseLong(Request_id), sharedPreferences.getLong("EmpId", 0), true);
//                        Intent i = new Intent(context, MainActivity_Listofchipment.class);
//                        startActivity(i);
                    } else {
                        Intent i = new Intent(context, MainActivity_DetailsListOfChimpments.class);
                        startActivity(i);
                    }
                } else {
                    public_function.AlertDialogTwoButton("يوجد لوطات لم يتم فحصها", context, false);
                }

                datasendarray = null;
            }

            @Override
            public void OnClickcancel(View view, Checkup_Result CheckUpResult) {

                checkup_result = null;
                datasendarray = null;
                finish();
                startActivity(getIntent());

            }
        });
    }

    public void onRadioButtonClicked(View view) {
        checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {

            case R.id.radio_Examination_full:
                if (checked)
                    linear_Layout_Examination_full.setVisibility(View.VISIBLE);
                linear_Layout_btns.setVisibility(View.VISIBLE);
                title_radio_group.setText("فحص كلي");
                radioGroup.setVisibility(View.GONE);
                linear_Layout_Examination_part.setVisibility(View.GONE);
                Examination_full();
                break;
            case R.id.radio_Examination_part:
                if (checked)
                    linear_Layout_Examination_full.setVisibility(View.GONE);
                linear_Layout_btns.setVisibility(View.VISIBLE);
                radioGroup.setVisibility(View.GONE);
                title_radio_group.setText("فحص اللوطات");
                Examination_part();
                linear_Layout_Examination_part.setVisibility(View.VISIBLE);
                break;

            default:
                linear_Layout_Examination_full.setVisibility(View.GONE);
                linear_Layout_btns.setVisibility(View.GONE);
                title_radio_group.setText("نوع الفحص");
                radioGroup.setVisibility(View.VISIBLE);
                linear_Layout_Examination_part.setVisibility(View.GONE);
                break;
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
//                public_function.NavMenuClickgetsqlite(context,ipadrass,sharedPreferences.getLong("EmpId", (long) -1));
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
}
