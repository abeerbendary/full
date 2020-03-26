package com.example.abeer.quarantine.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.abeer.quarantine.R;
import com.example.abeer.quarantine.databinding.ActivityCheckUpLotsBinding;
import com.example.abeer.quarantine.functions.Public_function;
import com.example.abeer.quarantine.model.Checkup_Result_Model;
import com.example.abeer.quarantine.model.InputFilterMinMax;
import com.example.abeer.quarantine.model.Plant;
import com.example.abeer.quarantine.presenter.IPresenter;
import com.example.abeer.quarantine.remote.ApiCall;
import com.example.abeer.quarantine.remote.PlantQurDBHelper;
import com.example.abeer.quarantine.remote.data.DataManger;
import com.example.abeer.quarantine.remote.data.IDataValue;
import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.Checkup_Result;
import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.CommitteeResultType;
import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.LISTFamily;
import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.LISTIm_ProcedureType;
import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.LISTMKingdom;
import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.LISTOrder;
import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.LISTPhylum;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//112121
public class CheckUp_Lots extends AppCompatActivity //implements LocationListener
{
    ActivityCheckUpLotsBinding ActivityCheckUpLotsBinding;
    TextView Lotsvalue;
    Gson gson;
    String data;
    DataManger DataManger;
    Context context;
    String IDItemSelect;
    Checkup_Result checkup_result;
    LinearLayout linear_Layout_Damaged;
    final LISTMKingdom[] LISTMKingdom = new LISTMKingdom[1];
    final LISTPhylum[] LISTPhylum = new LISTPhylum[1];
    final LISTOrder[] LISTOrder = new LISTOrder[1];
    final LISTFamily[] LISTFamily = new LISTFamily[1];
    final CommitteeResultType[] CommitteeResultTypeLIST = new CommitteeResultType[1];
    final LISTIm_ProcedureType[] LISTIm_ProcedureType = new LISTIm_ProcedureType[1];
    String Num_Lots;
    LocationManager manager;
    Location location;
    long ID_lots;
    String ipadrass;
    int Package_Count;
    double Net_Weight;
    String type;
    SharedPreferences sharedPreferences;
    Checkup_Result_Model checkupResultModel;
    TextView num_Request;
  String  Request_id;
    Public_function public_function;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        checkup_result = new Checkup_Result();
        DataManger = new DataManger(this);
        ActivityCheckUpLotsBinding =
                DataBindingUtil.setContentView((Activity) context, R.layout.activity_check_up__lots);
        Intent intent = getIntent();
        sharedPreferences = getApplicationContext().getSharedPreferences("SharedPreference", 0);
        public_function=new Public_function();
        ipadrass = sharedPreferences.getString("ipadrass", "");
        Package_Count = intent.getIntExtra("Package_Count", 0);
        Net_Weight = intent.getDoubleExtra("Net_Weight",0);
        Num_Lots = intent.getStringExtra("LOTS_NUM");
        ID_lots =  intent.getLongExtra("ID",0);
        type=intent.getStringExtra("type");
        Request_id = sharedPreferences.getString("checkRequest_Id", "");
        linear_Layout_Damaged = findViewById(R.id.damaged);
    }


    @Override
    protected void onStart() {
        super.onStart();
        num_Request = findViewById(R.id.lotvalue);
        Lotsvalue = findViewById(R.id.Lotss_value);
        Lotsvalue.setText(Num_Lots);
        ((TextView) findViewById(R.id.lottype)).setText(type);
        num_Request.setText(sharedPreferences.getString("num_Request", ""));
        EditText edit_num = findViewById(R.id.lotedit_num);
        final EditText edit_ten = findViewById(R.id.lotedit_ten);
        final EditText edit_kelo = findViewById(R.id.lotedit_kelo);
        final EditText edit_gram = findViewById(R.id.lotedit_gram);
        final double[] kelowaite = new double[1];
        edit_num.setFilters(new InputFilter[]{new InputFilterMinMax("1", String.valueOf(Package_Count))});
        edit_ten.setFilters(new InputFilter[]{new InputFilterMinMax(0, (int) (Net_Weight / 1000))});
        edit_kelo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edit_ten.getText().toString().isEmpty()) {
                    kelowaite[0] = Net_Weight - ((Double.valueOf(edit_ten.getText().toString())) * 1000);
                    edit_kelo.setFilters(new InputFilter[]{new InputFilterMinMax(0, (int) (kelowaite[0]))});
                } else {
                    edit_kelo.setFilters(new InputFilter[]{new InputFilterMinMax(0, (int) (Net_Weight))});
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
                    edit_gram.setFilters(new InputFilter[]{new InputFilterMinMax(0, (int)( kelowaite[0]*1000))});
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        DataManger.SendVollyRequestJsonObjectGet(context, Request.Method.GET, ipadrass + ApiCall.UrlCommitteeResultType, new IDataValue() {
            @Override
            public void Success(Object response) {
                data = response.toString();
                gson = new Gson();
                CommitteeResultTypeLIST[0] = gson.fromJson(data, CommitteeResultType.class);
                ActivityCheckUpLotsBinding.setCommitteeResultType(CommitteeResultTypeLIST[0]);

            }

            @Override
            public void Error(VolleyError error) {

            }
        });

        ActivityCheckUpLotsBinding.setCheckUpResult(checkup_result);

        ActivityCheckUpLotsBinding.setPresenter(new IPresenter() {


            @Override
            public void OnItemSelectedSpinner_CommitteeResultType(AdapterView<?> parent, View view, int pos, long id, Checkup_Result CheckUpResult) {
                if (pos != 0) {
                    IDItemSelect = String.valueOf(CommitteeResultTypeLIST[0].obj.get(pos).Value);
                    CheckUpResult.setResult_ID(Integer.parseInt(IDItemSelect));
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
                                ActivityCheckUpLotsBinding.setLISTMKingdom((LISTMKingdom[0]));
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
                                ActivityCheckUpLotsBinding.setLISTImProcedureType(LISTIm_ProcedureType[0]);
                            }

                            @Override
                            public void Error(VolleyError error) {

                            }
                        });
                    }
                }
            }

            @Override
            public void OnItemSelectedSpinner_Kingdom(AdapterView<?> parent, View view, int pos, long id, Checkup_Result checkupResult) {
                IDItemSelect = String.valueOf(LISTMKingdom[0].obj.get(pos).Value);
                checkupResult.setKingdom_ID(Integer.parseInt(IDItemSelect));
                DataManger.SendVollyRequestJsonObjectGet(context, Request.Method.GET, ipadrass + ApiCall.UrlPlantPhylum + IDItemSelect, new IDataValue() {
                    @Override
                    public void Success(Object response) {
                        data = response.toString();
                        gson = new Gson();
                        LISTPhylum[0] = gson.fromJson(data, LISTPhylum.class);
                        ActivityCheckUpLotsBinding.setLISTPhylums(LISTPhylum[0]);
                    }

                    @Override
                    public void Error(VolleyError error) {

                    }
                });
            }

            @Override
            public void OnItemSelectedSpinner_Phylum(AdapterView<?> parent, View view, int pos, long id, Checkup_Result checkupResult) {
                IDItemSelect = String.valueOf(LISTPhylum[0].obj.get(pos).Value);
                checkupResult.setPhylum_ID(Integer.parseInt(IDItemSelect));
                DataManger.SendVollyRequestJsonObjectGet(context, Request.Method.GET, ipadrass + ApiCall.UrlPlantOrder + IDItemSelect, new IDataValue() {
                    @Override
                    public void Success(Object response) {
                        data = response.toString();
                        gson = new Gson();
                        LISTOrder[0] = gson.fromJson(data, LISTOrder.class);
                        ActivityCheckUpLotsBinding.setLISTOrder(LISTOrder[0]);
                    }

                    @Override
                    public void Error(VolleyError error) {

                    }
                });
            }

            @Override
            public void OnItemSelectedSpinner_Order(AdapterView<?> parent, View view, int pos, long id, Checkup_Result checkupResult) {
                IDItemSelect = String.valueOf(LISTOrder[0].obj.get(pos).Value);
                checkupResult.setOrder_ID(Integer.parseInt(IDItemSelect));
                DataManger.SendVollyRequestJsonObjectGet(context, Request.Method.GET, ipadrass + ApiCall.UrlPlantFamily + IDItemSelect, new IDataValue() {
                    @Override
                    public void Success(Object response) {
                        data = response.toString();
                        gson = new Gson();
                        LISTFamily[0] = gson.fromJson(data, LISTFamily.class);
                        ActivityCheckUpLotsBinding.setLISTFamily(LISTFamily[0]);
                    }

                    @Override
                    public void Error(VolleyError error) {

                    }
                });
            }

            @Override
            public void OnItemSelectedSpinner_Family(AdapterView<?> parent, View view, int pos, long id, Checkup_Result checkupResult) {
                IDItemSelect = String.valueOf(LISTFamily[0].obj.get(pos).Value);
                checkupResult.setFamily_ID(Integer.parseInt(IDItemSelect));
            }

            @Override
            public void OnItemSelectedSpinner_Im_ProcedureType(AdapterView<?> parent, View view, int pos, long id, Checkup_Result CheckUpResult) {

                IDItemSelect = String.valueOf(LISTIm_ProcedureType[0].obj.get(pos).Value);
                CheckUpResult.setResult_injury(Integer.parseInt(IDItemSelect));
            }


            @Override
            public void OnClickSaveLots(View view, Checkup_Result checkupResult) {
                checkupResult.setlot_ID(ID_lots);
                manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
               location=public_function.getlocation(context,manager);
                if(location.getLatitude()==0&&location.getLongitude()==0){
                    location.setLatitude(sharedPreferences.getLong("Latitude",0));
                    location.setLongitude(sharedPreferences.getLong("Longitude",0));
                }
                checkupResultModel=new Checkup_Result_Model(checkupResult);
                if( linear_Layout_Damaged.getVisibility()==View.VISIBLE && (checkupResultModel.getItem__OrderID()==0 ||checkupResultModel.getResult_injuryID()==0)) {
                    public_function.AlertDialog("برجاء تحديد نوع الاصابة ونتيجة الاصابة",context,false);
                }else if(linear_Layout_Damaged.getVisibility()==View.GONE &&(checkupResultModel.getWeight()==0||checkupResultModel.getQuantitySize()==0||checkupResultModel.getCommitteeResultType_ID()==0)){

                    public_function.AlertDialog(" برجاءإستكمال البيانات ",context,false);

                } else if(linear_Layout_Damaged.getVisibility()==View.VISIBLE &&(checkupResultModel.getWeight()==0||checkupResultModel.getQuantitySize()==0)){

                    public_function.AlertDialog(" برجاءإستكمال البيانات العدد والوزن ",context,false);

                }
                else {

                    checkupResultModel.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
                    checkupResultModel.setLongitude(location.getLongitude());
                    checkupResultModel.setLatitude( location.getLatitude());
                    String jsonInString = gson.toJson(checkupResultModel);
                    PlantQurDBHelper plantQurDBHelper=new PlantQurDBHelper(context);
                    plantQurDBHelper.Insert_result("CommitteeResult",sharedPreferences.getLong("Item_id", (long) 0),ID_lots,jsonInString,jsonInString);
                    Intent resultIntent = new Intent();
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
            }

            @Override
            public void OnClickcancel(View view, Checkup_Result CheckUpResult) {
                checkup_result=null;
                finish();
            }

        });
    }

}
