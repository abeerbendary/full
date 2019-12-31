package com.example.abeer.quarantine.presenter;

import android.view.View;
import android.widget.AdapterView;

import com.example.abeer.quarantine.viewmodel.FarmSample;

public interface IFarmPresenter {
    public  void OnItemSelectedSpinner_Treatment(AdapterView<?> parent, View view, int pos, long id, FarmSample farmSample);
    public  void OnItemSelectedSpinner_laboratory(AdapterView<?> parent, View view, int pos, long id, FarmSample farmSample);
    public  void OnClickSaveFarm(View view, FarmSample farmSample);
    public  void OnClickcancel(View view, FarmSample farmSample);
}
