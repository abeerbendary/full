package com.example.abeer.quarantine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
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
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.abeer.quarantine.R;
import com.example.abeer.quarantine.databinding.ActivityMainConfirmBinding;

import com.example.abeer.quarantine.functions.Public_function;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity_Confirm extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Context context=this;
    SharedPreferences sharedPreferences;
    Public_function public_function;
    String ipadrass;
    String num_Request;
    String Request_id;
    DrawerLayout drawer;
    ScrollView Examination_full1;
    ScrollView Sample_full ;
    ScrollView Treatment_full;
    ActivityMainConfirmBinding activityMainConfirmBinding;
    String data;
    JSONObject jsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.content_main_activity__confirm);
       //activityMainConfirmBinding = DataBindingUtil.setContentView((Activity) this, R.layout.activity_main__confirm);
        try {
            activityMainConfirmBinding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_main__confirm);
        } catch (Exception ex) {
            int s = 10;
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        Examination_full1=findViewById(R.id.scrollView_Examination_full1);
        Sample_full =findViewById(R.id.scrollView_Sample_full);
        Treatment_full=findViewById(R.id.scrollView_Treatment_full);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layoutt);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        sharedPreferences = getApplicationContext().getSharedPreferences("SharedPreference",0);
        num_Request = sharedPreferences.getString("num_Request","");
        Request_id = sharedPreferences.getString("checkRequest_Id","");
        data = sharedPreferences.getString("confirmresult","");
        ((TextView)findViewById(R.id.value_request)).setText(num_Request);
        ipadrass= sharedPreferences.getString("ipadrass","");
        public_function=new Public_function();

    }

    @Override
    public void onBackPressed() {
       drawer = (DrawerLayout) findViewById(R.id.drawer_layoutt);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity__confirm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void shownav(View view) {
        drawer.openDrawer(GravityCompat.START);
    }

    public void Exrequest(View view) {
        public_function.AlertDialog("لم يتم الانتهاء من الصفحه ", context,false);

     Examination_full1.setVisibility(View.VISIBLE);
     Sample_full.setVisibility(View.GONE);
     Treatment_full.setVisibility(View.GONE);
    }

    public void sample(View view) {
        public_function.AlertDialog("لم يتم الانتهاء من الصفحه ", context,false);

        Examination_full1.setVisibility(View.GONE);
        Sample_full.setVisibility(View.VISIBLE);
        Treatment_full.setVisibility(View.GONE);
    }

    public void treatment(View view) {
        public_function.AlertDialog("لم يتم الانتهاء من الصفحه ", context,false);

        Examination_full1.setVisibility(View.GONE);
        Sample_full.setVisibility(View.GONE);
        Treatment_full.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        public_function.AlertDialog("لم يتم الانتهاء من الصفحه ", context,false);


//        try {
//             jsonObject = new JSONObject(data);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        try {
//
//            if(jsonObject.get("Committe_Dto").equals("")){
//
//                ((TextView)findViewById(R.id.Btn_check3)).setVisibility(View.GONE);
//
//            }
//            if(jsonObject.get("SampleDto").equals("")){
//                ((TextView)findViewById(R.id.Btn_check2)).setVisibility(View.GONE);
//
//            }
//            if(jsonObject.get("Treatment_Dto").equals("")){
//                ((TextView)findViewById(R.id.Btn_check1)).setVisibility(View.GONE);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//


    }
}
