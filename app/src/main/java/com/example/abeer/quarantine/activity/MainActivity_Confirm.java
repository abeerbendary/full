package com.example.abeer.quarantine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.abeer.quarantine.R;
import com.example.abeer.quarantine.adapter.MyAdapterforRecycler;
import com.example.abeer.quarantine.databinding.ActivityMainConfirmBinding;

import com.example.abeer.quarantine.functions.Public_function;
import com.example.abeer.quarantine.presenter.Clickcustum;
import com.example.abeer.quarantine.presenter.IpresenterConfirm;
import com.example.abeer.quarantine.remote.ApiCall;
import com.example.abeer.quarantine.remote.PlantQurDBHelper;
import com.example.abeer.quarantine.remote.data.DataManger;
import com.example.abeer.quarantine.remote.data.IDataValue;
import com.example.abeer.quarantine.viewmodel.DataForCardItems;
import com.example.abeer.quarantine.viewmodel.ItemData;
import com.example.abeer.quarantine.viewmodel.ItemLotatData;
import com.example.abeer.quarantine.viewmodel.ListDetailsCheckRequestNew;
import com.example.abeer.quarantine.viewmodel.ListItemLotat;
import com.example.abeer.quarantine.viewmodel.confirm.CommiteeDataDetail;
import com.example.abeer.quarantine.viewmodel.confirm.CommitteData;
import com.example.abeer.quarantine.viewmodel.confirm.ConfirmResult;
import com.example.abeer.quarantine.viewmodel.confirm.Confirm_ItemData;
import com.example.abeer.quarantine.viewmodel.confirm.SampleDataDetail;
import com.example.abeer.quarantine.viewmodel.confirm.SampleeDataa;
import com.example.abeer.quarantine.viewmodel.confirm.TreatmentDataDetail;
import com.example.abeer.quarantine.viewmodel.confirm.TreatmenttDataa;
import com.example.abeer.quarantine.viewmodel.livingobjects.ItemData_LivingObject;
import com.example.abeer.quarantine.viewmodel.plantProduct.ItemData_PlantProduct;
import com.example.abeer.quarantine.viewmodel.plantProduct.ListPlantproduct;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity_Confirm extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Context context = this;
    SharedPreferences sharedPreferences;
    SharedPreferences prefsEditor2;
    Public_function public_function;
    String ipadrass;
    String num_Request;
    String Request_id;
    Long EmpId;
    ScrollView Examination_full1;
    ScrollView _Sample_full;
    ScrollView scrollView_Examination_part;
    RadioGroup radioGroup_Examination;
    EditText editText4;
    LinearLayout linearLayout15;
    ScrollView Sample_full;
    LinearLayout linearLayout_Examination_full1;
    LinearLayout linearLayout_Sample_full1;
    LinearLayout linearLayout_Treatment_full;
    ScrollView Treatment_full;
    String data;
    DataManger dataManger;
    JSONObject jsonObject;
    //  String  jsonObj;
    JSONObject jsonObj;
    JSONObject jsonObj_analysis;
    JSONObject jsonObj_treatment;
    JSONObject jsone;
    String part_data;
    Gson gson;
    boolean clicked;
    DrawerLayout drawer;
    NavigationView navigationView = null;
    Toolbar toolbar = null;
    //  final List<ListItemLotat>[] itemData = new List[1];
    final ListItemLotat[] itemData = new ListItemLotat[1];
    ListDetailsCheckRequestNew part_dataa;
    final ItemData[] t = new ItemData[1];
    final ItemData[] itemData1 = new ItemData[1];
    final ItemData_PlantProduct[] itemData_plantProducts = new ItemData_PlantProduct[1];
    final ListPlantproduct[] i = new ListPlantproduct[1];
    final ItemData_LivingObject[] itemData_livingObjects = new ItemData_LivingObject[1];
    final ItemData_LivingObject[] itemData_unlivingObjects = new ItemData_LivingObject[1];
    CommitteData committeData = new CommitteData();
    SampleeDataa sampleeDataas = new SampleeDataa();
    TreatmenttDataa treatmenttDataas = new TreatmenttDataa();
    LinearLayout damg;
    Button examine;
    Long Comite_id;
    Long idReuestItem;
    String confirmdata;
    LinearLayout linearLayout;
    Button Btn_check3_examination1;
    Button Btn_check2_withdraw1;
    Button Btn_check1_treatment;
    RadioButton radioButton;
    RadioButton radioButton2;
    PlantQurDBHelper plantQurDBHelper;
    Button save_ex;
    Button save_sam;
    Button save_tre;
    ActivityMainConfirmBinding activityMainConfirmBinding;
    ConfirmResult confirmResultEx;
    ConfirmResult confirmResultSample;
    ConfirmResult confirmResultTreat;
    // long Item_id;
    Long item_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            activityMainConfirmBinding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_main__confirm);
        } catch (Exception ex) {
            int s = 10;
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Examination_full1 = findViewById(R.id.scrollView_Examination_full1);
        Sample_full = findViewById(R.id.scrollView_Sample_full);
        Treatment_full = findViewById(R.id.scrollView_Treatment_full);
        scrollView_Examination_part = findViewById(R.id.scrollView_Examination_part);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layoutt);
        Btn_check3_examination1 = (Button) findViewById(R.id.Btn_check3_examination);
        Btn_check2_withdraw1 = (Button) findViewById(R.id.Btn_check2_withdraw);
        Btn_check1_treatment = (Button) findViewById(R.id.Btn_check1_treatment);
        radioButton = findViewById(R.id.radio_Examination_yes);
        radioButton2 = findViewById(R.id.radio_Examination_no);
        // editText4= findViewById(R.id.editText4note);
        linearLayout15 = findViewById(R.id.linearLayout15);
        save_ex = findViewById(R.id.Btn_checkTreatment1);
        save_sam = findViewById(R.id.Btn_check1Sample);
        save_tre = findViewById(R.id.Btn_checkExamination);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        sharedPreferences = getApplicationContext().getSharedPreferences("SharedPreference", 0);
        num_Request = sharedPreferences.getString("num_Request", "");
        Request_id = sharedPreferences.getString("checkRequest_Id", "");
        data = sharedPreferences.getString("confirmresult", "");
        // Item_id=sharedPreferences.getLong("Item_id",  0);
        Comite_id = sharedPreferences.getLong("Committee_ID", 0);
        ((TextView) findViewById(R.id.value_request)).setText(num_Request);
        ipadrass = sharedPreferences.getString("ipadrass", "");
        idReuestItem = getIntent().getExtras().getLong("idReuestItem");
        EmpId = sharedPreferences.getLong("EmpId", 0);
        item_id= sharedPreferences.getLong("Item_id",  0);
        damg = findViewById(R.id.damaged);
        dataManger = new DataManger(this);
        public_function = new Public_function();
        confirmResultEx = new ConfirmResult();
        confirmResultSample = new ConfirmResult();
        confirmResultTreat = new ConfirmResult();
        plantQurDBHelper = new PlantQurDBHelper(context);

    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layoutt);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


//        plantQurDBHelper. FinalSEndForConfirm(context,ipadrass,idReuestItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity__confirm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        try {

            public_function.NavMenuClickgetsqlite(context, id, sharedPreferences.getLong("Item_id", (long) 0), sharedPreferences.getLong("EmpId", (long) -1), Long.parseLong(sharedPreferences.getString("checkRequest_Id", "")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void shownav(View view) {
        drawer.openDrawer(GravityCompat.START);
    }


    @Override
    protected void onStart() {
        super.onStart();
        String link = idReuestItem + "&Committee_ID=" + Comite_id + "&Is_Confirm=true";
        dataManger.SendVollyRequestJsonObjectGet(context, Request.Method.GET, ipadrass + ApiCall.uri + link, new IDataValue() {
            @Override
            public void Success(Object response) throws JSONException {
                confirmdata = response.toString();
                Toast.makeText(MainActivity_Confirm.this, "", Toast.LENGTH_SHORT).show();

                gson = new Gson();
                Confirm_ItemData data = gson.fromJson(confirmdata, Confirm_ItemData.class);
                String committee_result = data.getCommittee_result();
                String analysis_result = data.getAnalysis_result();
                String treatment_result = data.getTreatment_result();
                try {
                    if (committee_result != null) {
                        jsonObj = XML.toJSONObject(committee_result);
                        Gson gson1 = new Gson();
                        committeData = gson1.fromJson(jsonObj.toString(), CommitteData.class);
                        if (committeData.CommitteeData.size() == 2) {
                            if (((committeData.CommitteeData.get(0).Ex_RequestLotData_ID == -1) && (committeData.CommitteeData.get(0).Ex_Request_Item_Id == -1)))
                            {
                                if((committeData.CommitteeData.get(1).Ex_RequestLotData_ID == -1) && (committeData.CommitteeData.get(1).Ex_Request_Item_Id == -1))
                                {
                                    Btn_check3_examination1.setVisibility(View.GONE);

                                }
                            }

                        }

                    }
                    if (analysis_result != null) {
                        jsonObj_analysis = XML.toJSONObject(analysis_result);
                        Gson gson2 = new Gson();
                        sampleeDataas = gson2.fromJson(jsonObj_analysis.toString(), SampleeDataa.class);
                        if (sampleeDataas.SampleData.size() == 2) {
                            if (((sampleeDataas.SampleData.get(0).Ex_Request_LotData_ID == -1) && (sampleeDataas.SampleData.get(0).Ex_Request_Item_Id == -1)))
                            {
                                if((sampleeDataas.SampleData.get(1).Ex_Request_LotData_ID == -1) && (sampleeDataas.SampleData.get(1).Ex_Request_Item_Id == -1))
                                {
                                Btn_check2_withdraw1.setVisibility(View.GONE);
                                 }
                            }

                       }
                    }

                    if (treatment_result != null) {
                        jsonObj_treatment = XML.toJSONObject(treatment_result);
                        Gson gson3 = new Gson();
                        treatmenttDataas = gson3.fromJson(jsonObj_treatment.toString(), TreatmenttDataa.class);
                        if (treatmenttDataas.TreatmentData.size() == 2) {
                            if (((treatmenttDataas.TreatmentData.get(0).Ex_Request_LotData_ID == -1) && (treatmenttDataas.TreatmentData.get(0).Ex_Request_Item_Id == -1)))
                            {
                                if((treatmenttDataas.TreatmentData.get(1).Ex_Request_LotData_ID == -1) && (treatmenttDataas.TreatmentData.get(1).Ex_Request_Item_Id == -1))
                                {
                                    Btn_check1_treatment.setVisibility(View.GONE);
                                }
                            }

                        }


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void Error(VolleyError error) {
            }
        });
        EmpId = sharedPreferences.getLong("EmpId", 0);
        activityMainConfirmBinding.contentDetailsListChimpments.setConfirmResultEX(confirmResultEx);
        activityMainConfirmBinding.contentDetailsListChimpments.setConfirmResultSAm(confirmResultSample);
        activityMainConfirmBinding.contentDetailsListChimpments.setConfirmResultTrea(confirmResultTreat);
        activityMainConfirmBinding.contentDetailsListChimpments.setIpresenterConfirmEx(new IpresenterConfirm() {
            @Override
            public void SaveConfirm(View view, ConfirmResult confirmResult) {
                confirmResultEx.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
                confirmResultEx.setCommitteeResult_ID(idReuestItem);
                confirmResultEx.setEmployeeId(EmpId);
                final String json = new Gson().toJson(confirmResultEx);
                public_function.AlertDialog("تم الحفظ بنجاح",context,false);
                findViewById(R.id.Btn_check3_examination).setVisibility(View.GONE);
                findViewById(R.id.scrollView_Examination_full1).setVisibility(View.GONE);
                plantQurDBHelper.InsertConfirmResult("CommitteeResult", idReuestItem, json);

            }

            @Override
            public void OnClickcancel(View view, ConfirmResult confirmResult) {
                confirmResultEx = null;
                finish();
                startActivity(getIntent());
            }
        });


        activityMainConfirmBinding.contentDetailsListChimpments.setIpresenterConfirmSam(new IpresenterConfirm() {
            @Override
            public void SaveConfirm(View view, ConfirmResult confirmResult) {
                confirmResultSample.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
                confirmResultSample.setCommitteeResult_ID(idReuestItem);
                confirmResultSample.setEmployeeId(EmpId);
                final String json = new Gson().toJson(confirmResultSample);
                public_function.AlertDialog("تم الحفظ بنجاح",context,false);
                findViewById(R.id.Btn_check2_withdraw).setVisibility(View.GONE);
                findViewById(R.id.scrollView_Sample_full).setVisibility(View.GONE);
                plantQurDBHelper.InsertConfirmResult("SampleData", idReuestItem, json);

            }

            @Override
            public void OnClickcancel(View view, ConfirmResult confirmResult) {
                confirmResultSample = null;
//                finish();
//                startActivity(getIntent());
            }
        });

        activityMainConfirmBinding.contentDetailsListChimpments.setIpresenterConfirmTreat(new IpresenterConfirm() {
            @Override
            public void SaveConfirm(View view, ConfirmResult confirmResult) {
                confirmResultTreat.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
                confirmResultTreat.setCommitteeResult_ID(idReuestItem);
                confirmResultTreat.setEmployeeId(EmpId);
                final String json = new Gson().toJson(confirmResultTreat);
                public_function.AlertDialog("تم الحفظ بنجاح",context,false);
                findViewById(R.id.Btn_check1_treatment).setVisibility(View.GONE);
                findViewById(R.id.scrollView_Treatment_full).setVisibility(View.GONE);
                plantQurDBHelper.InsertConfirmResult("TreatmentData", idReuestItem, json);
            }

            @Override
            public void OnClickcancel(View view, ConfirmResult confirmResult) {
                confirmResultTreat = null;
//                finish();
//                startActivity(getIntent());
            }
        });
    }

    public void buttonclick(View view) {
        clicked = ((TextView) view).isClickable();
        switch (view.getId()) {
            case R.id.Btn_check3_examination:
                if (clicked) {
                    Examination_full1.setVisibility(View.VISIBLE);
                    Sample_full.setVisibility(View.GONE);
                    Treatment_full.setVisibility(View.GONE);
                    scrollView_Examination_part.setVisibility(View.GONE);

                    Gson gson1 = new Gson();
                    committeData = gson1.fromJson(jsonObj.toString(), CommitteData.class);
                    int size = committeData.CommitteeData.size();
//                    if(size==2) {
//                        if (((committeData.CommitteeData.get(0).CommitteeResultType_ID == -1) && (committeData.CommitteeData.get(0).Ex_Request_Item_Id == -1)) ||
//                                (committeData.CommitteeData.get(1).CommitteeResultType_ID == -1) && (committeData.CommitteeData.get(1).Ex_Request_Item_Id == -1))
//                        {
//                        }
//                    }
                    /// koly m4 lotat
                    if (((committeData.CommitteeData.get(0).Ex_RequestLotData_ID == 0) && (committeData.CommitteeData.get(0).Ex_Request_Item_Id != 0)) ||
                            ((committeData.CommitteeData.get(1).Ex_RequestLotData_ID == 0) && (committeData.CommitteeData.get(1).Ex_Request_Item_Id != 0))) {

                     /*   radioGroup_Examination.setVisibility(View.VISIBLE);
                        editText4.setVisibility(View.VISIBLE);
                        linearLayout15.setVisibility(View.VISIBLE);*/
                        committeData = gson1.fromJson(jsonObj.toString(), CommitteData.class);
                        if ((committeData.CommitteeData.get(0).CommitteeResultType_ID) == Integer.parseInt("2") || (committeData.CommitteeData.get(1).CommitteeResultType_ID) == Integer.parseInt("2")) {
                            damg.setVisibility(View.VISIBLE);
                        } else {
                            damg.setVisibility(View.GONE);
                        }

                        if ((committeData.CommitteeData.get(0).Ex_RequestLotData_ID == 0) && (committeData.CommitteeData.get(0).Ex_Request_Item_Id != 0)) {
                            CommiteeDataDetail commiteeDataDetail = committeData.CommitteeData.get(0);
                            activityMainConfirmBinding.contentDetailsListChimpments.setCcom(commiteeDataDetail);
                        } else if ((committeData.CommitteeData.get(1).Ex_RequestLotData_ID == 0) && (committeData.CommitteeData.get(1).Ex_Request_Item_Id != 0)) {
                            CommiteeDataDetail commiteeDataDetai2 = committeData.CommitteeData.get(1);
                            activityMainConfirmBinding.contentDetailsListChimpments.setCcom(commiteeDataDetai2);
                        }

                    } else {
                        scrollView_Examination_part.setVisibility(View.VISIBLE);
                        Examination_full1.setVisibility(View.GONE);
                        Treatment_full.setVisibility(View.GONE);
                        Sample_full.setVisibility(View.GONE);
                     /*   radioGroup_Examination.setVisibility(View.GONE);
                        editText4.setVisibility(View.GONE);
                        linearLayout15.setVisibility(View.GONE);*/
                        String n = getIntent().getExtras().getString("Item_Namee");
                        String m = getIntent().getExtras().getString("Item_Cat_Namee");
                        int h = getIntent().getExtras().getInt("Isplant");
                        DataForCardItems dataForCardItems = new DataForCardItems(idReuestItem, getIntent().getExtras().getInt("Isplant"), getIntent().getExtras().getString("Item_Cat_Namee"));
                        ListItemLotat listItemLotat = new ListItemLotat();
                        ListItemLotat listItemLotat2 = listItemLotat.GetCommitteData2(committeData);
                        MyAdapterforRecycler dd;
                        dd = new MyAdapterforRecycler(dataForCardItems, listItemLotat2, context, new Clickcustum() {
                            @Override
                            public void button_click(View view, ItemLotatData sampleData_lOts) {
                                Toast.makeText(MainActivity_Confirm.this, "button ", Toast.LENGTH_SHORT).show();
                            }
                        });

                        activityMainConfirmBinding.contentDetailsListChimpments.setMyAdapter(dd);
                        activityMainConfirmBinding.contentDetailsListChimpments.detialsconfirm.setLayoutManager(new LinearLayoutManager(context));

                    }
                }
                break;
            case R.id.Btn_check2_withdraw:
                if (clicked) {
                    Examination_full1.setVisibility(View.GONE);
                    Treatment_full.setVisibility(View.GONE);
                    Sample_full.setVisibility(View.VISIBLE);
                    scrollView_Examination_part.setVisibility(View.GONE);
                    Gson gson2 = new Gson();
                    sampleeDataas = gson2.fromJson(jsonObj_analysis.toString(), SampleeDataa.class);
                    if (sampleeDataas.SampleData.size() == 1 && (sampleeDataas.SampleData.get(0).Ex_Request_LotData_ID == -1) && (sampleeDataas.SampleData.get(0).Ex_Request_Item_Id == -1)) {


                    }
                    if (((sampleeDataas.SampleData.get(0).Ex_Request_LotData_ID == 0) && (sampleeDataas.SampleData.get(0).Ex_Request_Item_Id != 0)) ||
                            ((sampleeDataas.SampleData.get(1).Ex_Request_LotData_ID == 0) && (sampleeDataas.SampleData.get(1).Ex_Request_Item_Id != 0))) {

                  /*      radioGroup_Examination.setVisibility(View.VISIBLE);
                        editText4.setVisibility(View.VISIBLE);
                        linearLayout15.setVisibility(View.VISIBLE);*/

                        if ((sampleeDataas.SampleData.get(0).Ex_Request_LotData_ID == 0) && (sampleeDataas.SampleData.get(0).Ex_Request_Item_Id != 0)) {
                            SampleDataDetail sampleDataDetail = sampleeDataas.SampleData.get(0);
                            activityMainConfirmBinding.contentDetailsListChimpments.setSam(sampleDataDetail);
                        } else if ((sampleeDataas.SampleData.get(1).Ex_Request_LotData_ID == 0) && (sampleeDataas.SampleData.get(1).Ex_Request_Item_Id != 0)) {
                            SampleDataDetail sampleDataDetai2 = sampleeDataas.SampleData.get(1);
                            activityMainConfirmBinding.contentDetailsListChimpments.setSam(sampleDataDetai2);
                        }

                    } else {
                        //   _Sample_full.setVisibility(View.VISIBLE);
                        Examination_full1.setVisibility(View.GONE);
                        Treatment_full.setVisibility(View.GONE);
                        Sample_full.setVisibility(View.GONE);
                        scrollView_Examination_part.setVisibility(View.VISIBLE);
                    /*    radioGroup_Examination.setVisibility(View.GONE);
                        editText4.setVisibility(View.GONE);
                        linearLayout15.setVisibility(View.GONE);*/
                        sampleeDataas = gson2.fromJson(jsonObj_analysis.toString(), SampleeDataa.class);
                        DataForCardItems dataForCardItems = new DataForCardItems(idReuestItem, getIntent().getExtras().getInt("Isplant"), getIntent().getExtras().getString("Item_Cat_Name"));
                        ListItemLotat listItemLotat = new ListItemLotat();
                        ListItemLotat listItemLotat3 = listItemLotat.GetSampleData2(sampleeDataas);
                        MyAdapterforRecycler dd;
                        dd = new MyAdapterforRecycler(dataForCardItems, listItemLotat3, context, new Clickcustum() {
                            @Override
                            public void button_click(View view, ItemLotatData sampleData_lOts) {
                                Toast.makeText(MainActivity_Confirm.this, "button ", Toast.LENGTH_SHORT).show();
                            }
                        });

                        activityMainConfirmBinding.contentDetailsListChimpments.setMyAdapter(dd);
                        activityMainConfirmBinding.contentDetailsListChimpments.detialsconfirm.setLayoutManager(new LinearLayoutManager(context));

                    }

                }
                break;
            case R.id.Btn_check1_treatment:
                if (clicked) {
                    Examination_full1.setVisibility(View.GONE);
                    Sample_full.setVisibility(View.GONE);
                    Treatment_full.setVisibility(View.VISIBLE);
                    scrollView_Examination_part.setVisibility(View.GONE);
                    Gson gson3 = new Gson();
                    treatmenttDataas = gson3.fromJson(jsonObj_treatment.toString(), TreatmenttDataa.class);
                    int size = treatmenttDataas.TreatmentData.size();
                    treatmenttDataas = gson3.fromJson(jsonObj_treatment.toString(), TreatmenttDataa.class);
                    if (((treatmenttDataas.TreatmentData.get(0).Ex_Request_LotData_ID == 0) && (treatmenttDataas.TreatmentData.get(0).Ex_Request_Item_Id != 0)) ||
                            ((treatmenttDataas.TreatmentData.get(1).Ex_Request_LotData_ID == 0) && (treatmenttDataas.TreatmentData.get(1).Ex_Request_Item_Id != 0))) {
                   /*     radioGroup_Examination.setVisibility(View.VISIBLE);
                        editText4.setVisibility(View.VISIBLE);
                        linearLayout15.setVisibility(View.VISIBLE);*/
                        if ((treatmenttDataas.TreatmentData.get(0).Ex_Request_LotData_ID == 0) && (treatmenttDataas.TreatmentData.get(0).Ex_Request_Item_Id != 0)) {
                            TreatmentDataDetail treatmentDataDetail = treatmenttDataas.TreatmentData.get(0);
                            activityMainConfirmBinding.contentDetailsListChimpments.setTtreat(treatmentDataDetail);
                        } else if ((treatmenttDataas.TreatmentData.get(1).Ex_Request_LotData_ID == 0) && (treatmenttDataas.TreatmentData.get(1).Ex_Request_Item_Id != 0)) {
                            TreatmentDataDetail treatmentDataDetail = treatmenttDataas.TreatmentData.get(1);
                            activityMainConfirmBinding.contentDetailsListChimpments.setTtreat(treatmentDataDetail);
                        }

                    } else {

                        Examination_full1.setVisibility(View.GONE);
                        Treatment_full.setVisibility(View.GONE);
                        Sample_full.setVisibility(View.GONE);
                        scrollView_Examination_part.setVisibility(View.VISIBLE);
                    /*    radioGroup_Examination.setVisibility(View.GONE);
                        editText4.setVisibility(View.GONE);
                        linearLayout15.setVisibility(View.GONE);*/
                        treatmenttDataas = gson3.fromJson(jsonObj_treatment.toString(), TreatmenttDataa.class);
                        DataForCardItems dataForCardItems = new DataForCardItems(idReuestItem, getIntent().getExtras().getInt("Isplant")
                                , getIntent().getExtras().getString("Item_Cat_Name"));
                        ListItemLotat listItemLotat = new ListItemLotat();
                        ListItemLotat listItemLotat3 = listItemLotat.GettreatmentData3(treatmenttDataas);
                        MyAdapterforRecycler dd;
                        dd = new MyAdapterforRecycler(dataForCardItems, listItemLotat3, context, new Clickcustum() {
                            @Override
                            public void button_click(View view, ItemLotatData sampleData_lOts) {
                                Toast.makeText(MainActivity_Confirm.this, "button ", Toast.LENGTH_SHORT).show();
                            }
                        });

                        activityMainConfirmBinding.contentDetailsListChimpments.setMyAdapter(dd);
                        activityMainConfirmBinding.contentDetailsListChimpments.detialsconfirm.setLayoutManager(new LinearLayoutManager(context));

                    }
                }
                break;

        }
    }


    public void onRadioButtonClics(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {

            case R.id.radio_Examination_yesex:
                if (checked) {
                    confirmResultEx.setAccepted(true);
                }
                break;
            case R.id.radio_Examination:
                if (checked) {
                    confirmResultEx.setAccepted(false);
                }

                break;
            case R.id.radio_Sample_yes1:
                if (checked) {
                    confirmResultSample.setAccepted(true);
                }

                break;
            case R.id.radio_Sample_:
                if (checked) {
                    confirmResultSample.setAccepted(false);
                }

                break;
            case R.id.radio_Examination_yes:
                if (checked) {
                    confirmResultTreat.setAccepted(true);
                }

                break;
            case R.id.radio_Examination_no:
                if (checked) {
                    confirmResultTreat.setAccepted(false);

                }

                break;


        }
    }

    public void cancelClick(View view) {

    }
}
