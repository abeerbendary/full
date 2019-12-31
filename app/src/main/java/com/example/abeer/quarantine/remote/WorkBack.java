package com.example.abeer.quarantine.remote;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WorkBack extends Worker {
    private static final String TAG = "Task";
    Context contextss;
    public WorkBack(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        contextss=context;
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "doWork: entering task");
     //call function to work
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Run your task here
                 Toast.makeText(contextss, "working", Toast.LENGTH_LONG).show();

            }
        }, 1000 );

        return Result.success();
    }
}
