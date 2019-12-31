package com.example.abeer.quarantine.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import com.example.abeer.quarantine.databinding.ActivityTreatmentLotsBinding;
import com.example.abeer.quarantine.functions.Public_function;
import com.example.abeer.quarantine.model.Treatment_Result_Model;
import com.example.abeer.quarantine.presenter.ITreatmentPresenter;
import com.example.abeer.quarantine.remote.ApiCall;
import com.example.abeer.quarantine.remote.PlantQurDBHelper;
import com.example.abeer.quarantine.remote.data.DataManger;
import com.example.abeer.quarantine.remote.data.IDataValue;
import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.SampleData_LOts;
import com.example.abeer.quarantine.viewmodel.treatmentStatement.ListTreatmentCompany;
import com.example.abeer.quarantine.viewmodel.treatmentStatement.ListTreatmentMaterial;
import com.example.abeer.quarantine.viewmodel.treatmentStatement.ListTreatmentMethod;
import com.example.abeer.quarantine.viewmodel.treatmentStatement.ListTreatmentPlace;
import com.example.abeer.quarantine.viewmodel.treatmentStatement.ListTreatmentType;
import com.example.abeer.quarantine.viewmodel.treatmentStatement.TreatmentResult;
import com.google.gson.Gson;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Treatment_Lots extends AppCompatActivity  //implements LocationListener
{

    ActivityTreatmentLotsBinding ActivityTreatmentLotsBinding;
    DataManger dataManger;
    TreatmentResult treatmentResult;
    Context context;
    TextView title_valuelot;
    TextView Lots_Treatmentvalue;
    String Num_Lots;
    Long ID_lots;
    boolean checked;
    String ID_itemSelected;
    Gson gson;
    String data;
    LocationManager manager;
    SharedPreferences sharedPreferences;
    String ipadrass;
    double lat,longg;
    Public_function public_function;
    final ListTreatmentType[] ListTreatmentType = new ListTreatmentType[1];
    final ListTreatmentCompany[] ListTreatmentCompany = new ListTreatmentCompany[1];
    final ListTreatmentMethod[] listTreatmentMethods = new ListTreatmentMethod[1];
    final ListTreatmentMaterial[] listTreatmentMaterials = new ListTreatmentMaterial[1];
    final ListTreatmentPlace[] listTreatmentPlaces = new ListTreatmentPlace[1];
    final List<com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.SampleData_LOts>[] SampleData_LOts = new List[1]  ;
    TextView text;
    String Request_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataManger = new DataManger(this);
        treatmentResult =new TreatmentResult();
        context=this;
        ActivityTreatmentLotsBinding = DataBindingUtil.setContentView(this,R.layout.activity_treatment__lots);
        title_valuelot =findViewById(R.id.title_valuelot);
        Lots_Treatmentvalue = findViewById(R.id.Lots_Treatmentvalue);
        text=findViewById(R.id.text);
        Intent intent = getIntent();
        public_function=new Public_function();
        sharedPreferences = getApplicationContext().getSharedPreferences("SharedPreference",0);
        ipadrass= sharedPreferences.getString("ipadrass","");
        ID_lots = intent.getLongExtra("ID",0);
        Num_Lots = intent.getStringExtra("LOTS_NUM");
        Request_id = sharedPreferences.getString("checkRequest_Id","");
        Lots_Treatmentvalue.setText(Num_Lots);
        title_valuelot.setText(sharedPreferences.getString("num_Request",""));
    }

    @Override
    protected void onStart() {
        super.onStart();
        dataManger.SendVollyRequestJsonObjectGet(this, Request.Method.GET, ipadrass+ApiCall.UrlTreatmentType, new IDataValue() {
            @Override
            public void Success(Object response) {
                data=response.toString();
                gson = new Gson();
                ListTreatmentType[0] = gson.fromJson(data, ListTreatmentType.class);
                ActivityTreatmentLotsBinding.setTreatmentType(ListTreatmentType[0]);
            }
            @Override
            public void Error(VolleyError error) {


            }
        });

        dataManger.SendVollyRequestJsonObjectGet(this, Request.Method.GET, ipadrass+ApiCall.UrlTreatmentCompany, new IDataValue() {
            @Override
            public void Success(Object response) {

                data=response.toString();
                gson = new Gson();
                ListTreatmentCompany[0] = gson.fromJson(data, ListTreatmentCompany.class);
                ActivityTreatmentLotsBinding.setTreatmentCompany(ListTreatmentCompany[0]);

            }
            @Override
            public void Error(VolleyError error) {


            }
        });

        ActivityTreatmentLotsBinding.setTreatmentResult(treatmentResult);
        ActivityTreatmentLotsBinding.setITreatmentPresenter(new ITreatmentPresenter() {
            @Override
            public void OnItemSelectedSpinner_Treatment(AdapterView<?> parent, View view, int pos, long id, TreatmentResult treatmentResult) {

                ID_itemSelected =String.valueOf(ListTreatmentType[0].obj.get(pos).Value);
                treatmentResult.setTreatment_Type_ID(Byte.parseByte(ID_itemSelected));
                dataManger.SendVollyRequestJsonObjectGet(context, Request.Method.GET, ipadrass+ApiCall.UrlTreatmentMethod+ID_itemSelected, new IDataValue() {
                    @Override
                    public void Success(Object response) {

                        gson=new Gson();
                        data = response.toString();
                        listTreatmentMethods[0] = gson.fromJson(data, ListTreatmentMethod.class);
                        ActivityTreatmentLotsBinding.setTreatmentMethod (listTreatmentMethods[0]);
                    }
                    @Override
                    public void Error(VolleyError error) {

                    }
                });

                dataManger.SendVollyRequestJsonObjectGet(context, Request.Method.GET, ipadrass+ApiCall.UrlTreatmentMaterial + ID_itemSelected, new IDataValue() {
                    @Override
                    public void Success(Object response) throws JSONException {

                        gson=new Gson();
                        data = response.toString();
                        listTreatmentMaterials[0] = gson.fromJson(data, ListTreatmentMaterial.class);
                        ActivityTreatmentLotsBinding.setTreatmentMaterial (listTreatmentMaterials[0]);

                    }

                    @Override
                    public void Error(VolleyError error) {

                    }
                });

            }

            @Override
            public void OnItemSelectedSpinner_Treatmentcompany(AdapterView<?> parent, View view, int pos, long id, TreatmentResult treatmentResult) {
                treatmentResult.setTreatment_company_ID(Integer.parseInt(""+ListTreatmentCompany[0].obj.get(pos).Value));
            }

            @Override
            public void OnItemSelectedSpinner_treatmentplace(AdapterView<?> parent, View view, int pos, long id, TreatmentResult treatmentResult) {
                treatmentResult.setCertified_place_ID(Long.parseLong(""+listTreatmentPlaces[0].obj.get(pos).Value));
            }

            @Override
            public void OnItemSelectedSpinner_treatmentmethod(AdapterView<?> parent, View view, int pos, long id, TreatmentResult treatmentResult) {
                treatmentResult.setTreatment_method_ID(Byte.parseByte(""+listTreatmentMethods[0].obj.get(pos).ID));
            }

            @Override
            public void OnItemSelectedSpinner_treatmentmaterial(AdapterView<?> parent, View view, int pos, long id, TreatmentResult treatmentResult) {
                treatmentResult.setTreatment_material_ID(Byte.parseByte(""+listTreatmentMaterials[0].obj.get(pos).ID));
            }

            @Override
            public void OnClickSaveTreatment(View view, TreatmentResult TreatmentResult) {

                if(TreatmentResult.getTreatment_Type_ID()==0){

                    public_function.AlertDialog(" برجاء تحديد نوع المعالجه ",context,false);
                }else if(TreatmentResult.getTreatment_company_ID()==0)
                {
                    public_function.AlertDialog(" برجاء تحديد شركه المعالجة",context,false);
                }
                else if(!((RadioButton)findViewById( R.id.certified_place)).isChecked()&& !((RadioButton)findViewById( R.id.uncertified_place)).isChecked())
                {
                    public_function.AlertDialog(" برجاء تحديد شركه المعالجة معتمدة او غير معتمدة",context,false);
                }
                else if(findViewById(R.id.spinner_treatment_place).getVisibility()==View.VISIBLE&&TreatmentResult.getCertified_place_ID()==0 )
                {
                    public_function.AlertDialog(" برجاء تسجيل مكان المعالجه المعتمد  ",context,false);
                }
                else if(findViewById(R.id.treatment_place).getVisibility()==View.VISIBLE&& TreatmentResult.getUncertified_place()==null)
                {
                    public_function.AlertDialog(" برجاء تسجيل مكان المعالجه الغير معتمدة  ",context,false);
                }
                else  if(TreatmentResult.getTreatment_Type_ID()==0)
                {
                    public_function.AlertDialog(" برجاء تحديد طرق المعالجة ",context,false);

                }
                else if (TreatmentResult.getResala_size()==0)
                {
                    public_function.AlertDialog(" برجاء تحديد حجم الرسالة ",context,false);

                }
                else if (TreatmentResult.getTreatment_material_ID()==0)
                {
                    public_function.AlertDialog(" برجاء تحديد مادة المعالجة ",context,false);
                }
                else if (TreatmentResult.getDosage()==0)
                {
                    public_function.AlertDialog(" برجاء ادخال الجرعة ",context,false);

                }
                else if (TreatmentResult.getExposure_Day()==0 && TreatmentResult.getExposure_Hour()==0 && TreatmentResult.getExposure_Minute()==0 )
                {
                    public_function.AlertDialog(" برجاء تحديد مدة التعرض ",context,false);

                }
                else if (TreatmentResult.getTemperature()==0)
                {
                    public_function.AlertDialog(" برجاء تحديد درجة الحرارة",context,false);

                }
                else if(TreatmentResult.getThermalSealNumber()==0)
                {
                    public_function.AlertDialog(" برجاءادخال رقم الختم الحراري",context,false);

                }
                else if (TreatmentResult.getQuantity_material()==0)
                {
                    public_function.AlertDialog(" برجاء تحديد كمية المادة المستخدمة",context,false);

                }else {
                    manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                   Location location = public_function.getlocation(context,manager);
                    if(location.getLatitude()==0&&location.getLongitude()==0){
                        location.setLatitude(sharedPreferences.getLong("Latitude",0));
                        location.setLongitude(sharedPreferences.getLong("Longitude",0));
                    }

                    TreatmentResult.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
                    TreatmentResult.setLatitude(location.getLatitude());
                    TreatmentResult.setLongitude(location.getLongitude());
                    TreatmentResult.setLot_ID(ID_lots);
                    Treatment_Result_Model treatmentResultModel = new Treatment_Result_Model(TreatmentResult);
                    String jsonInString = gson.toJson(treatmentResultModel);
                    PlantQurDBHelper plantQurDBHelper=new PlantQurDBHelper(context);
                    plantQurDBHelper.Insert_result("TreatmentData",Long.valueOf(Request_id),"Istreatment",sharedPreferences.getLong("Item_id", (long) 0),ID_lots,jsonInString,jsonInString);
                    Intent resultIntent = new Intent();
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
            }

            @Override
            public void OnClickcancel(View view, TreatmentResult TreatmentResult) {

                treatmentResult =null;
                finish();
            }
        });

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
                dataManger.SendVollyRequestJsonObjectGet(context, Request.Method.GET, ipadrass+ApiCall.UrlTreatmentPlace, new IDataValue() {
                    @Override
                    public void Success(Object response) {
                        try {
                            data=response.toString();
                            gson = new Gson();
                            listTreatmentPlaces[0] = gson.fromJson(data , ListTreatmentPlace.class);
                            ActivityTreatmentLotsBinding.setTreatmentPlace(listTreatmentPlaces[0]);
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

