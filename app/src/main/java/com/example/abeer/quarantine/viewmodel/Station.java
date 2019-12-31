package com.example.abeer.quarantine.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class Station extends BaseObservable {
   long ID;
    String User_Creation_Date;
    String Notes_Ar;
    Long Committee_ID;
    double Longitude, Latitude;
    long User_Creation_Id;
    boolean IsAccepted;
    public Station() {

    }

    public Station(Station station) {
        User_Creation_Date = station.User_Creation_Date;
        Notes_Ar= station.Notes_Ar;
        Committee_ID = station.Committee_ID;
        Longitude = station.Longitude;
        Latitude = station.Latitude;
        User_Creation_Id = station.User_Creation_Id;
        IsAccepted=station.IsAccepted;
    }

    public Station(String date, String comment, Long committee_ID, double longitude, double latitude, long employeeId,boolean accept) {
        User_Creation_Date = date;
        Notes_Ar = comment;
        Committee_ID = committee_ID;
        Longitude = longitude;
        Latitude = latitude;
        User_Creation_Id = employeeId;
       this.IsAccepted=accept;
    }

    public boolean isAccept() {
        return IsAccepted;
    }

    public void setAccept(boolean accept) {
        this.IsAccepted = accept;
    }

    @Bindable
    public String getDate() {
        return User_Creation_Date;
    }

    public void setDate(String date) {
        User_Creation_Date = date;
    }

    @Bindable
    public String getComment() {
        return Notes_Ar;
    }

    public void setComment(String comment) {
        Notes_Ar = comment;
    }

    @Bindable
    public Long getCommittee_ID() {
        return Committee_ID;
    }

    public void setCommittee_ID(Long committee_ID) {
        Committee_ID = committee_ID;
    }

    @Bindable
    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    @Bindable
    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    @Bindable
    public long getEmployeeId() {
        return User_Creation_Id;
    }

    public void setEmployeeId(long employeeId) {
        User_Creation_Id = employeeId;
    }
}
