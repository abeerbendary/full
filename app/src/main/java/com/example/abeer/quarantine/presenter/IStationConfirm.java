package com.example.abeer.quarantine.presenter;

import android.view.View;

import com.example.abeer.quarantine.viewmodel.StationConfirmResult;

public interface IStationConfirm {
    public  void OnClickRadioStation(View view, StationConfirmResult station);
    public  void OnClickSaveStation(View view, StationConfirmResult station);
    public  void OnClickcancel(View view, StationConfirmResult station);
}
