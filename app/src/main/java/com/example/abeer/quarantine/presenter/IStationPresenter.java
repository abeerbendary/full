package com.example.abeer.quarantine.presenter;

import android.view.View;

import com.example.abeer.quarantine.viewmodel.Station;

public interface IStationPresenter {
    public  void OnClickRadioStation(View view, Station station);
    public  void OnClickSaveStation(View view, Station station);
    public  void OnClickcancel(View view, Station station);
}
