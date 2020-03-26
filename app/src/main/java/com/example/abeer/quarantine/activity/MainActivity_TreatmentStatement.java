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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.abeer.quarantine.R;
import com.example.abeer.quarantine.adapter.MyAdapterforRecycler;
import com.example.abeer.quarantine.databinding.ActivityMainTreatmentStatementBinding;
import com.example.abeer.quarantine.functions.Public_function;
import com.example.abeer.quarantine.model.Treatment_Result_Model;
import com.example.abeer.quarantine.presenter.Clickcustum;
import com.example.abeer.quarantine.presenter.ITreatmentPresenter;
import com.example.abeer.quarantine.remote.ApiCall;
import com.example.abeer.quarantine.remote.PlantQurDBHelper;
import com.example.abeer.quarantine.remote.data.DataManger;
import com.example.abeer.quarantine.remote.data.IDataValue;
import com.example.abeer.quarantine.viewmodel.DataForCardItems;
import com.example.abeer.quarantine.viewmodel.ItemData;
import com.example.abeer.quarantine.viewmodel.ItemLotatData;
import com.example.abeer.quarantine.viewmodel.ListItemLotat;
import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.SampleData_LOts;
import com.example.abeer.quarantine.viewmodel.treatmentStatement.ListTreatmentCompany;
import com.example.abeer.quarantine.viewmodel.treatmentStatement.ListTreatmentMaterial;
import com.example.abeer.quarantine.viewmodel.treatmentStatement.ListTreatmentMethod;
import com.example.abeer.quarantine.viewmodel.treatmentStatement.ListTreatmentPlace;
import com.example.abeer.quarantine.viewmodel.treatmentStatement.ListTreatmentType;
import com.example.abeer.quarantine.viewmodel.treatmentStatement.TreatmentResult;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

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

public class MainActivity_TreatmentStatement extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener//,LocationListener
{
    Context context = this;
    ActivityMainTreatmentStatementBinding activityMainTreatmentStatementBinding;
    DataManger dataManger;
    TreatmentResult treatmentResult;
    RadioGroup radioGroup_Treatment;
    LinearLayout linear_Layout_Treatment_full;
    LinearLayout linear_Layout_Treatment_part;
    LinearLayout linear_Layout_btns_Treatment;
    TextView title_radio_group;
    LocationManager manager;
    String num_Request;
    boolean checked;
    Gson gson;
    String data;
    final ListTreatmentType[] ListTreatmentType = new ListTreatmentType[1];
    final ListTreatmentCompany[] ListTreatmentCompany = new ListTreatmentCompany[1];
    final ListTreatmentMethod[] listTreatmentMethods = new ListTreatmentMethod[1];
    final ListTreatmentMaterial[] listTreatmentMaterials = new ListTreatmentMaterial[1];
    final ListTreatmentPlace[] listTreatmentPlaces = new ListTreatmentPlace[1];
    TextView text;
    TextView treatment_value;
    JSONArray datasendarray = new JSONArray();
    View viewforbutton;
    Location location;
    SharedPreferences.Editor prefsEditor;
    String ID_itemSelected;
    SharedPreferences sharedPreferences;
    Public_function public_function;
    String Request_id;
    String ipadrass;
    Treatment_Result_Model treatmentResultModel;
    PlantQurDBHelper plantQurDBHelper;
    ItemData ItemData;
    ListItemLotat listItemLotat;
    String JsonTextDetails;
    Long ID_Item;
    int lengthsave = 0;
    int size;
    ArrayList<ItemLotatData> itemLotatData22 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainTreatmentStatementBinding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_main__treatment_statement);
        dataManger = new DataManger(this);
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

        dataManger = new DataManger(this);
        treatmentResult = new TreatmentResult();
        context = this;
        sharedPreferences = getApplicationContext().getSharedPreferences("SharedPreference", 0);
        num_Request = sharedPreferences.getString("num_Request", "");
        Request_id = sharedPreferences.getString("checkRequest_Id", "");
        ipadrass = sharedPreferences.getString("ipadrass", "");
        ID_Item = sharedPreferences.getLong("Item_id", 0);
        radioGroup_Treatment = findViewById(R.id.radioGroup_Treatment);
        text = findViewById(R.id.text);
        linear_Layout_Treatment_full = findViewById(R.id.linear_Layout_Treatment_full);
        linear_Layout_Treatment_part = findViewById(R.id.linear_Layout_Treatment_part);
        linear_Layout_btns_Treatment = findViewById(R.id.btns_Treatment);
        title_radio_group = findViewById(R.id.title_radio_group_Treatment);
        treatment_value = findViewById(R.id.title_value);
        treatment_value.setText(num_Request);
        public_function = new Public_function();

    }

    @Override
    protected void onStart() {
        super.onStart();
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        location = public_function.getlocation(context, manager);
        plantQurDBHelper = new PlantQurDBHelper(context);
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
                lengthsave += 1;
                viewforbutton.findViewById(R.id.buttsss).setVisibility(View.GONE);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                viewforbutton.findViewById(R.id.buttsss).setVisibility(View.VISIBLE);
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

    public void onRadioButtonClicked(View view) {

        checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {

            case R.id.radio_Treatment_full:
                if (checked)
                    linear_Layout_Treatment_full.setVisibility(View.VISIBLE);
                linear_Layout_btns_Treatment.setVisibility(View.VISIBLE);
                title_radio_group.setText("بيان معالجة للشحنة");
                radioGroup_Treatment.setVisibility(View.GONE);
                linear_Layout_Treatment_part.setVisibility(View.GONE);
                Treatment_full();
                break;
            case R.id.radio_Treatment_part:
                if (checked)
                    linear_Layout_Treatment_full.setVisibility(View.GONE);
                linear_Layout_btns_Treatment.setVisibility(View.VISIBLE);
                radioGroup_Treatment.setVisibility(View.GONE);
                title_radio_group.setText("بيان معالجة للوطات");
                Treatment_part();
                linear_Layout_Treatment_part.setVisibility(View.VISIBLE);
                break;

            default:
                linear_Layout_Treatment_full.setVisibility(View.GONE);
                linear_Layout_btns_Treatment.setVisibility(View.GONE);
                title_radio_group.setText("بيان المعالجة");
                radioGroup_Treatment.setVisibility(View.VISIBLE);
                linear_Layout_Treatment_part.setVisibility(View.GONE);
                break;

        }
    }

    private void Treatment_part() {
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
                    Intent i = new Intent(context, Treatment_Lots.class);
                    i.putExtra("ID", sampleData_lOts.Lot_ID);
                    i.putExtra("LOTS_NUM", "" + sampleData_lOts.getLot_Number());
                    viewforbutton = view;
                    startActivityForResult(i, 1);
                }
            }
            );
        }
        activityMainTreatmentStatementBinding.contentTreatmentStatement.setMyAdapter(dd);
        activityMainTreatmentStatementBinding.contentTreatmentStatement.recyclerViewTreatment.setLayoutManager(new LinearLayoutManager(context));

        activityMainTreatmentStatementBinding.contentTreatmentStatement.setTreatmentResult(treatmentResult);
        activityMainTreatmentStatementBinding.contentTreatmentStatement.setITreatmentPresenter(new ITreatmentPresenter() {
            @Override
            public void OnItemSelectedSpinner_Treatment(AdapterView<?> parent, View view, int pos, long id, TreatmentResult treatmentResult) {

            }

            @Override
            public void OnItemSelectedSpinner_Treatmentcompany(AdapterView<?> parent, View view, int pos, long id, TreatmentResult treatmentResult) {

            }

            @Override
            public void OnItemSelectedSpinner_treatmentplace(AdapterView<?> parent, View view, int pos, long id, TreatmentResult treatmentResult) {

            }

            @Override
            public void OnItemSelectedSpinner_treatmentmethod(AdapterView<?> parent, View view, int pos, long id, TreatmentResult treatmentResult) {

            }

            @Override
            public void OnItemSelectedSpinner_treatmentmaterial(AdapterView<?> parent, View view, int pos, long id, TreatmentResult treatmentResult) {

            }

            @Override
            public void OnClickSaveTreatment(View view, TreatmentResult TreatmentResult) {

                if (size == lengthsave) {
                    plantQurDBHelper.UbdateConterForItemResult("Istreatment",sharedPreferences.getLong("Item_id", (long) 0),Long.valueOf(Request_id));
                    int count = Integer.parseInt(plantQurDBHelper.Get_Data_for_RequestCommittee_working("Total_process", Long.valueOf(Request_id)));
                    if (count == 0) {
                        plantQurDBHelper.update_counterResultForAdmin_New(context, ipadrass, Long.parseLong(Request_id),sharedPreferences.getLong("EmpId", 0),true);
//                        Intent i = new Intent(context, MainActivity_Listofchipment.class);
//                        startActivity(i);
                    } else {
                        Intent i = new Intent(context, MainActivity_DetailsListOfChimpments.class);
                        startActivity(i);
                    }
                } else {
                    public_function.AlertDialogTwoButton("يوجد لوطات لم يتم عمل معالجة لها",context,false);
                }

                datasendarray = null;

            }

            @Override
            public void OnClickcancel(View view, TreatmentResult TreatmentResult) {
                datasendarray = null;
                finish();
                startActivity(getIntent());
            }
        });


    }

    private void Treatment_full() {
        dataManger.SendVollyRequestJsonObjectGet(this, Request.Method.GET, ipadrass + ApiCall.UrlTreatmentType, new IDataValue() {
            @Override
            public void Success(Object response) {
                data = response.toString();
                gson = new Gson();
                ListTreatmentType[0] = gson.fromJson(data, ListTreatmentType.class);
                activityMainTreatmentStatementBinding.contentTreatmentStatement.setTreatmentType(ListTreatmentType[0]);
            }

            @Override
            public void Error(VolleyError error) {


            }
        });
        dataManger.SendVollyRequestJsonObjectGet(this, Request.Method.GET, ipadrass + ApiCall.UrlTreatmentCompany, new IDataValue() {
            @Override
            public void Success(Object response) {

                data = response.toString();
                gson = new Gson();
                ListTreatmentCompany[0] = gson.fromJson(data, ListTreatmentCompany.class);
                activityMainTreatmentStatementBinding.contentTreatmentStatement.setTreatmentCompany(ListTreatmentCompany[0]);

            }

            @Override
            public void Error(VolleyError error) {


            }
        });

        activityMainTreatmentStatementBinding.contentTreatmentStatement.setTreatmentResult(treatmentResult);
        activityMainTreatmentStatementBinding.contentTreatmentStatement.setITreatmentPresenter(new ITreatmentPresenter() {
            @Override
            public void OnItemSelectedSpinner_Treatment(AdapterView<?> parent, View view, int pos, long id, TreatmentResult treatmentResult) {

                ID_itemSelected = String.valueOf(ListTreatmentType[0].obj.get(pos).Value);
                treatmentResult.setTreatment_Type_ID(Byte.parseByte(ID_itemSelected));
                dataManger.SendVollyRequestJsonObjectGet(context, Request.Method.GET, ipadrass + ApiCall.UrlTreatmentMethod + ID_itemSelected, new IDataValue() {
                    @Override
                    public void Success(Object response) {

                        gson = new Gson();
                        data = response.toString();
                        listTreatmentMethods[0] = gson.fromJson(data, ListTreatmentMethod.class);
                        activityMainTreatmentStatementBinding.contentTreatmentStatement.setTreatmentMethod(listTreatmentMethods[0]);
                    }

                    @Override
                    public void Error(VolleyError error) {

                    }
                });

                dataManger.SendVollyRequestJsonObjectGet(context, Request.Method.GET, ipadrass + ApiCall.UrlTreatmentMaterial + ID_itemSelected, new IDataValue() {
                    @Override
                    public void Success(Object response) throws JSONException {

                        gson = new Gson();
                        data = response.toString();
                        listTreatmentMaterials[0] = gson.fromJson(data, ListTreatmentMaterial.class);
                        activityMainTreatmentStatementBinding.contentTreatmentStatement.setTreatmentMaterial(listTreatmentMaterials[0]);

                    }

                    @Override
                    public void Error(VolleyError error) {

                    }
                });

            }

            @Override
            public void OnItemSelectedSpinner_Treatmentcompany(AdapterView<?> parent, View view, int pos, long id, TreatmentResult treatmentResult) {
                treatmentResult.setTreatment_company_ID(Integer.parseInt("" + ListTreatmentCompany[0].obj.get(pos).Value));
            }

            @Override
            public void OnItemSelectedSpinner_treatmentplace(AdapterView<?> parent, View view, int pos, long id, TreatmentResult treatmentResult) {
                treatmentResult.setCertified_place_ID(Long.parseLong("" + listTreatmentPlaces[0].obj.get(pos).Value));
            }

            @Override
            public void OnItemSelectedSpinner_treatmentmethod(AdapterView<?> parent, View view, int pos, long id, TreatmentResult treatmentResult) {
                treatmentResult.setTreatment_method_ID(Byte.parseByte("" + listTreatmentMethods[0].obj.get(pos).ID));
            }

            @Override
            public void OnItemSelectedSpinner_treatmentmaterial(AdapterView<?> parent, View view, int pos, long id, TreatmentResult treatmentResult) {
                treatmentResult.setTreatment_material_ID(Byte.parseByte("" + listTreatmentMaterials[0].obj.get(pos).ID));
            }

            @Override
            public void OnClickSaveTreatment(View view, TreatmentResult TreatmentResult) {
                if (TreatmentResult.getTreatment_Type_ID() == 0) {

                    public_function.AlertDialog(" برجاء تحديد نوع المعالجه ", context, false);
                } else if (TreatmentResult.getTreatment_company_ID() == 0) {
                    public_function.AlertDialog(" برجاء تحديد شركه المعالجة", context, false);
                } else if (!((RadioButton) findViewById(R.id.certified_place)).isChecked() && !((RadioButton) findViewById(R.id.uncertified_place)).isChecked()) {
                    public_function.AlertDialog(" برجاء تحديد شركه المعالجة معتمدة او غير معتمدة", context, false);
                } else if (findViewById(R.id.spinner_treatment_place).getVisibility() == View.VISIBLE && TreatmentResult.getCertified_place_ID() == 0) {
                    public_function.AlertDialog(" برجاء تسجيل مكان المعالجه المعتمد  ", context, false);
                } else if (findViewById(R.id.treatment_place).getVisibility() == View.VISIBLE && TreatmentResult.getUncertified_place() == null) {
                    public_function.AlertDialog(" برجاء تسجيل مكان المعالجه الغير معتمدة  ", context, false);
                } else if (TreatmentResult.getTreatment_Type_ID() == 0) {
                    public_function.AlertDialog(" برجاء تحديد طرق المعالجة ", context, false);

                } else if (TreatmentResult.getResala_size() == 0) {
                    public_function.AlertDialog(" برجاء تحديد حجم الرسالة ", context, false);

                } else if (TreatmentResult.getTreatment_material_ID() == 0) {
                    public_function.AlertDialog(" برجاء تحديد مادة المعالجة ", context, false);
                } else if (TreatmentResult.getDosage() == 0) {
                    public_function.AlertDialog(" برجاء ادخال الجرعة ", context, false);

                } else if (TreatmentResult.getExposure_Day() == 0 && TreatmentResult.getExposure_Hour() == 0 && TreatmentResult.getExposure_Minute() == 0) {
                    public_function.AlertDialog(" برجاء تحديد مدة التعرض ", context, false);

                } else if (TreatmentResult.getTemperature() == 0) {
                    public_function.AlertDialog(" برجاء تحديد درجة الحرارة", context, false);

                } else if (TreatmentResult.getThermalSealNumber() == 0) {
                    public_function.AlertDialog(" برجاءادخال رقم الختم الحراري", context, false);

                } else if (TreatmentResult.getQuantity_material() == 0) {
                    public_function.AlertDialog(" برجاء تحديد كمية المادة المستخدمة", context, false);

                } else {
                    ///////////////////////
                    manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    location = public_function.getlocation(context, manager);
                    if (location.getLongitude() == 0 && location.getLatitude() == 0) {
                        location.setLatitude(sharedPreferences.getLong("Latitude", 0));
                        location.setLongitude(sharedPreferences.getLong("Longitude", 0));
                    }
                    TreatmentResult.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
                    TreatmentResult.setLot_ID((long) 0);
                    TreatmentResult.setLatitude(location.getLatitude());
                    TreatmentResult.setLongitude(location.getLongitude());
                    treatmentResultModel = new Treatment_Result_Model(TreatmentResult);
                    treatmentResultModel.setIsLot(18);
                    final String json = new Gson().toJson(treatmentResultModel);
                    plantQurDBHelper.Insert_result("TreatmentData", sharedPreferences.getLong("Item_id", (long) 0), 0, json, json);
                    plantQurDBHelper.UbdateConterForItemResult("Istreatment",sharedPreferences.getLong("Item_id", (long) 0),Long.valueOf(Request_id));

//       int count = plantQurDBHelper.update_counterResultForAdmin(context, ipadrass, "Istreatment", Long.valueOf(Request_id), sharedPreferences.getLong("Item_id", (long) 0), sharedPreferences.getLong("EmpId", (long) 0));
                    int count = Integer.parseInt(plantQurDBHelper.Get_Data_for_RequestCommittee_working("Total_process", Long.valueOf(Request_id)));
                    if (count == 0) {
                        plantQurDBHelper.update_counterResultForAdmin_New(context, ipadrass, Long.parseLong(Request_id),sharedPreferences.getLong("EmpId", 0),true);
//                        Intent i = new Intent(context, MainActivity_Listofchipment.class);
//                        startActivity(i);
                    } else {
                        Intent i = new Intent(context, MainActivity_DetailsListOfChimpments.class);
                        startActivity(i);
                    }
                    datasendarray = null;
                }
            }

            @Override
            public void OnClickcancel(View view, TreatmentResult TreatmentResult) {
                datasendarray = null;
                finish();
                startActivity(getIntent());
            }
        });
    }

    private void startActivityForResult(Intent intent, Context context, JSONObject jsonObject) {
        if (context instanceof MainActivity_TreatmentStatement) {
            datasendarray.put(jsonObject);
        }
    }

    public void onRadioButtonClickedplace(View view) {
        checked = ((RadioButton) view).isChecked();
        final EditText edittext_place = findViewById(R.id.treatment_place);
        final Spinner spinner_place = findViewById(R.id.spinner_treatment_place);
        final LinearLayout linear_treatment_place = findViewById(R.id.linear_treatment_place);


        switch (view.getId()) {

            case R.id.certified_place:
                if (checked)
                    linear_treatment_place.setVisibility(View.VISIBLE);
                spinner_place.setVisibility(View.VISIBLE);
                edittext_place.setVisibility(View.INVISIBLE);
                dataManger.SendVollyRequestJsonObjectGet(context, Request.Method.GET, ipadrass + ApiCall.UrlTreatmentPlace, new IDataValue() {
                    @Override
                    public void Success(Object response) {
                        try {
                            data = response.toString();
                            gson = new Gson();
                            listTreatmentPlaces[0] = gson.fromJson(data, ListTreatmentPlace.class);
                            activityMainTreatmentStatementBinding.contentTreatmentStatement.setTreatmentPlace(listTreatmentPlaces[0]);
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void Error(VolleyError error) {
                    }
                });
                break;
            case R.id.uncertified_place:
                if (checked)
                    linear_treatment_place.setVisibility(View.VISIBLE);
                edittext_place.setVisibility(View.VISIBLE);
                spinner_place.setVisibility(View.INVISIBLE);
                break;
            default:
                linear_treatment_place.setVisibility(View.VISIBLE);
                edittext_place.setVisibility(View.GONE);
                spinner_place.setVisibility(View.GONE);
        }
    }

}

