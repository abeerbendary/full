package com.example.abeer.quarantine.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.abeer.quarantine.R;
import com.example.abeer.quarantine.databinding.ActivityLogInBinding;
import com.example.abeer.quarantine.functions.Public_function;
import com.example.abeer.quarantine.model.Subclass_Response_Login;
import com.example.abeer.quarantine.presenter.IClickLogin;
import com.example.abeer.quarantine.remote.ApiCall;
import com.example.abeer.quarantine.remote.PlantQurDBHelper;
import com.example.abeer.quarantine.remote.data.DataManger;
import com.example.abeer.quarantine.remote.data.IDataValue;
import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.CommitteeResultType;
import com.example.abeer.quarantine.viewmodel.login.Request_login;
import com.example.abeer.quarantine.viewmodel.login.Response_login;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class LogIn extends AppCompatActivity {
    Context context;
    EditText ipadrass;
    ActivityLogInBinding activityLogInBinding;
    Request_login requestLogin;
    DataManger DataManger;
    Gson gson;
    String data;
    Location location;
    Response_login responseLogin;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor prefsEditor;
    LocationManager manager;
    Public_function public_function = new Public_function();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        activityLogInBinding =
                DataBindingUtil.setContentView((Activity) context, R.layout.activity_log_in);
        sharedPreferences = context.getSharedPreferences("SharedPreference", MODE_PRIVATE);
        DataManger = new DataManger(this);
        requestLogin = new Request_login();
        responseLogin = new Response_login();
        gson = new Gson();
        ipadrass = findViewById(R.id.ipadarss);
    }

    @Override
    protected void onStart() {
        super.onStart();
        activityLogInBinding.setLoginRequest(requestLogin);
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        location = public_function.getlocation(context, manager);
        activityLogInBinding.setClicLogin(new IClickLogin() {
            @Override
            public void OnClickLogin(View view, final Request_login requestLogin) {
                if (requestLogin.getLoginName().equals("admin")) {
                    Intent i = new Intent(LogIn.this, Admin.class);
                    startActivity(i);
                } else if (requestLogin.getLoginName().equals("") || requestLogin.getPassword().equals("")) {
                    public_function.AlertDialog("أدخل إسم المستخدم وكلمه المرور", context, false);
                } else if (requestLogin.getLoginName() != "" && requestLogin.getPassword() != "") {
                    data = gson.toJson(requestLogin);
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(data);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                    if (mWifi.isConnected()) {
                        LOginOnline(jsonObject);
                    }else {
                        LOginOffline(requestLogin);
                    }
                }
            }
        });
    }

public void LOginOnline(JSONObject jsonObject){
    DataManger.SendVolleyRequestJsonObjectpost(context, Request.Method.POST, "http://" + ipadrass.getText().toString() + ApiCall.UrlLogin, jsonObject, new IDataValue() {

        @Override
        public void Success(Object response) {
            data = response.toString();
            responseLogin = gson.fromJson(data, Response_login.class);
            if (responseLogin.getState_Code() == 1) {
                prefsEditor = sharedPreferences.edit();
                prefsEditor.putLong("EmpId", responseLogin.getObj().getEmpId());
                prefsEditor.putString("Token",responseLogin.getObj().getToken());
                prefsEditor.putString("ipadrass", "http://" + ipadrass.getText().toString());
                if (location.getLongitude() != 0 && location.getLatitude() != 0) {
                    prefsEditor.putLong("Latitude", (long) location.getLatitude());
                    prefsEditor.putLong("Longitude", (long) location.getLongitude());
                }
                prefsEditor.commit();
                Intent intent = new Intent(context, MainActivity_Listofchipment.class);
                startActivity(intent);
            } else if (responseLogin.getState_Code() == 2) {
                //not right username password
                public_function.AlertDialog("اسم المستخدم وكلمه المرور خطا", context, false);
            } else if (responseLogin.getState_Code() == 5) {
                // user register from ather device
                public_function.AlertDialog("تم تسجيل الدخول من جهاز اخر", context, false);
            } else {
                public_function.AlertDialog("حدث خطا ما", context, false);
            }
        }

        @Override
        public void Error(VolleyError error) {

        }
    });
}
public void LOginOffline(Request_login requestLogin){
    PlantQurDBHelper plantQurDBHelper=new PlantQurDBHelper(context);
   Long Emp_ID = plantQurDBHelper.insertLogin_LoginEmployee(requestLogin.getLoginName(),requestLogin.getPassword());
    if(Emp_ID==0){
        public_function.AlertDialog("برجاء الاتصال بالشبكه", context, false);
    }else {
        prefsEditor = sharedPreferences.edit();
        prefsEditor.putLong("EmpId", Emp_ID);
        prefsEditor.putString("ipadrass", "http://" + ipadrass.getText().toString());
        if (location.getLongitude() != 0 && location.getLatitude() != 0) {
            prefsEditor.putLong("Latitude", (long) location.getLatitude());
            prefsEditor.putLong("Longitude", (long) location.getLongitude());
        }
        prefsEditor.commit();
    Intent intent = new Intent(context, MainActivity_Listofchipment.class);
    startActivity(intent);
    }
}

}
