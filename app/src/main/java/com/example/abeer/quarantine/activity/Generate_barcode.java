package com.example.abeer.quarantine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.abeer.quarantine.R;
import com.example.abeer.quarantine.adapter.AdapterBarcod;
import com.example.abeer.quarantine.databinding.ActivityGenerateBarcodeBinding;
import com.example.abeer.quarantine.remote.PlantQurDBHelper;
import com.example.abeer.quarantine.viewmodel.sampleWithDraw.Barcod_Card;
import com.example.abeer.quarantine.viewmodel.sampleWithDraw.ListLabName;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class Generate_barcode extends AppCompatActivity {

    List<Barcod_Card> barcod_cards = new ArrayList<>();
    ActivityGenerateBarcodeBinding activityGenerateBarcodeBinding;
    Context context = this;
    String contextsample;
    PlantQurDBHelper plantQurDBHelper;
    SharedPreferences sharedPreferences;
    String ipadrass;
    String Request_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGenerateBarcodeBinding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_generate_barcode);
        Intent i = getIntent();
        barcod_cards = (List<Barcod_Card>) i.getSerializableExtra("barcode");
        contextsample = i.getStringExtra("contextsample");
    }
    @Override
    protected void onStart() {
        super.onStart();
        plantQurDBHelper = new PlantQurDBHelper(context);
        sharedPreferences = getApplicationContext().getSharedPreferences("SharedPreference", 0);
        Request_id = sharedPreferences.getString("checkRequest_Id", "");
        ipadrass = sharedPreferences.getString("ipadrass", "");
        activityGenerateBarcodeBinding.setAdapterBarcod(new AdapterBarcod(barcod_cards, context));
        activityGenerateBarcodeBinding.recyclerbarcod.setLayoutManager(new LinearLayoutManager(context));
    }

    public void printer_barcode(View view) {
        Toast.makeText(this, "لا توجد طابعة متصلة", Toast.LENGTH_LONG).show();
//        int count = plantQurDBHelper.update_counterResultForAdmin(context, ipadrass, "Isanalysis", Long.valueOf(Request_id), sharedPreferences.getLong("Item_id", (long) 0), sharedPreferences.getLong("EmpId", (long) 0));
//        if (count == 0) {
//            Intent i = new Intent(context, MainActivity_Listofchipment.class);
//            startActivity(i);
//        } else {
//            Intent i = new Intent(context, MainActivity_DetailsListOfChimpments.class);
//            startActivity(i);
//        }
    }

    public void cancel(View view) {

        if (contextsample.equals("true")) {
            int count = Integer.parseInt(plantQurDBHelper.Get_Data_for_RequestCommittee_working("Total_process", Long.valueOf(Request_id)));

//            int count = plantQurDBHelper.update_counterResultForAdmin(context, ipadrass, "Isanalysis", Long.valueOf(Request_id), sharedPreferences.getLong("Item_id", (long) 0), sharedPreferences.getLong("EmpId", (long) 0));
            if (count == 0) {
                plantQurDBHelper.update_counterResultForAdmin_New(context, ipadrass, Long.parseLong(Request_id),sharedPreferences.getLong("EmpId", 0),true);
//                Intent i = new Intent(context, MainActivity_Listofchipment.class);
//                startActivity(i);
            } else {
                Intent i = new Intent(context, MainActivity_DetailsListOfChimpments.class);
                startActivity(i);
            }
//          Intent i=new Intent(context,MainActivity_Listofchipment.class);
//          startActivity(i);
        }
        finish();
    }
}
