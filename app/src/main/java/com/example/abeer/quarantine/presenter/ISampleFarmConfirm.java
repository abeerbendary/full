package com.example.abeer.quarantine.presenter;

import android.view.View;

import com.example.abeer.quarantine.viewmodel.FarmConfirm;

public interface ISampleFarmConfirm {
    public  void OnClickRadioFarmConfirm(View view, FarmConfirm farmConfirm);
    public  void OnClickSaveFarmConfirm(View view, FarmConfirm farmConfirm);
    public  void OnClickcancel(View view, FarmConfirm farmConfirm);

}
