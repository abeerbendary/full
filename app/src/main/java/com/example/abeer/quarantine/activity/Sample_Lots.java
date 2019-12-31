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
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.abeer.quarantine.R;
import com.example.abeer.quarantine.databinding.ActivitySampleLotsBinding;
import com.example.abeer.quarantine.functions.Public_function;
import com.example.abeer.quarantine.model.Sample_Result_Model;
import com.example.abeer.quarantine.presenter.ISamplePresenter;
import com.example.abeer.quarantine.remote.ApiCall;
import com.example.abeer.quarantine.remote.PlantQurDBHelper;
import com.example.abeer.quarantine.remote.data.DataManger;
import com.example.abeer.quarantine.remote.data.IDataValue;
import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.Checkup_Result;
import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.SampleData_LOts;
import com.example.abeer.quarantine.viewmodel.sampleWithDraw.ListAnalysis;
import com.example.abeer.quarantine.viewmodel.sampleWithDraw.ListLabName;
import com.example.abeer.quarantine.viewmodel.sampleWithDraw.Sample_Result;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Sample_Lots extends AppCompatActivity //implements LocationListener
{
    ActivitySampleLotsBinding ActivitySampleLotsBinding;
    Sample_Result sampleResult;
    DataManger dataManger;
    Context context= this;
    TextView LotsSamplevalue;
    TextView title_value;
    String Num_Lots;
    Long ID_lots;
    String data;
    String ID_itemSelected;
    boolean checked;
    Gson gson;
    String ipadrass;
    Public_function public_function;
    double lat,longg;
    LocationManager manager;
    String Request_id;

    SharedPreferences sharedPreferences;
    final ListLabName[] listLabs = new ListLabName[1];
    final ListAnalysis[] listAnalysis = new ListAnalysis[1];
    final List<SampleData_LOts>[] SampleData_LOts = new List[1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sampleResult =new Sample_Result( );
        dataManger = new DataManger(this);

        ActivitySampleLotsBinding =
                DataBindingUtil.setContentView((Activity) context, R.layout.activity_sample__lots);

        LotsSamplevalue = findViewById(R.id.Lots_Samplevalue);
        title_value=findViewById(R.id.title_value);
        Intent intent = getIntent();

        sharedPreferences = getApplicationContext().getSharedPreferences("SharedPreference",0);
        ipadrass= sharedPreferences.getString("ipadrass","");
         Num_Lots = intent.getStringExtra("LOTS_NUM");
         ID_lots =  intent.getLongExtra("ID",0);
        Request_id = sharedPreferences.getString("checkRequest_Id", "");
        LotsSamplevalue.setText(Num_Lots);
        public_function=new Public_function();
        title_value.setText(sharedPreferences.getString("num_Request",""));
    }

    @Override
    protected void onStart() {
        super.onStart();
        dataManger.SendVollyRequestJsonObjectGet(this, Request.Method.GET, ipadrass+ApiCall.AnalysisType, new IDataValue() {
            @Override
            public void Success(Object response) {
                gson = new Gson();
                data=response.toString();
                listAnalysis[0] = gson.fromJson(data, ListAnalysis.class);
                ActivitySampleLotsBinding.setAnalysis(listAnalysis[0]);
            }
            @Override
            public void Error(VolleyError error) {

            }
        });
        ActivitySampleLotsBinding.setSampleResult(sampleResult);
        ActivitySampleLotsBinding.setISamplePresenter(new ISamplePresenter() {
            @Override
            public void OnItemSelectedSpinner_Treatment(AdapterView<?> parent, View view, int pos, long id, Sample_Result sample_result) {
                ID_itemSelected = String.valueOf(listAnalysis[0].obj.get(pos).Value);
                sample_result.setAnalysisType_ID(Short.parseShort(ID_itemSelected));
                Toast.makeText(context, ""+ID_itemSelected, Toast.LENGTH_SHORT).show();

                dataManger.SendVollyRequestJsonObjectGet(context, Request.Method.GET, ipadrass+ApiCall.UrlLabName+ID_itemSelected, new IDataValue() {
                    @Override
                    public void Success(Object response) {
                        gson = new Gson();
                        data = response.toString();
                        listLabs[0] = gson.fromJson(data, ListLabName.class);
                        ActivitySampleLotsBinding.setLabs(listLabs[0]);
                    }
                    @Override
                    public void Error(VolleyError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
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

                if(sampleResult.getAnalysisType_ID()==0) {

                    public_function.AlertDialog("برجاء تحديد نوع التحليل ",context,false);
                }else {
                    manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                  Location  location=public_function.getlocation(context,manager);
                    if(location.getLatitude()==0 &&location.getLongitude()==0){
                        location.setLatitude(sharedPreferences.getLong("Latitude",0));
                        location.setLongitude(sharedPreferences.getLong("Longitude",0));
                    }
                    sampleResult.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
                    sampleResult.setLot_ID(ID_lots);
                    sampleResult.setBarCode(sharedPreferences.getString("BarCode","")+ID_lots);
                    Sample_Result_Model sampleResultModel = new Sample_Result_Model(sampleResult);
                    sampleResultModel.setLatitude(location.getLatitude());
                    sampleResultModel.setLongitude(location.getLongitude());
                    String jsonInString = gson.toJson(sampleResultModel);
                    PlantQurDBHelper plantQurDBHelper=new PlantQurDBHelper(context);
                    plantQurDBHelper.Insert_result("SampleData",Long.valueOf(Request_id),"Isanalysis",sharedPreferences.getLong("Item_id", (long) 0),ID_lots,jsonInString,jsonInString);
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("Num_Lots",Num_Lots);
                    resultIntent.putExtra("barcode",sampleResult.getBarCode());
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
            }

            @Override
            public void OnClickcancel(View view, Sample_Result SampleResult) {

                sampleResult =null;
                finish();

            }
        });
    }
}
