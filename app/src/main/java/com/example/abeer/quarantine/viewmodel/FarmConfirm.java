package com.example.abeer.quarantine.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class FarmConfirm extends BaseObservable {

    double Longitude;
    double Latitude;
    boolean IsAccepted;
    String Notes;
    long EmployeeId;
    String Date;
    int ID;
    long Farm_Committee_ID;

    public FarmConfirm() {
    }

    public FarmConfirm(FarmConfirm farmConfirm) {
        Longitude = farmConfirm.Longitude;
        Latitude = farmConfirm.Latitude;
        IsAccepted = farmConfirm.IsAccepted;
        Notes = farmConfirm.Notes;
        EmployeeId = farmConfirm.EmployeeId;
        Date = farmConfirm.Date;
        this.ID = farmConfirm.ID;
        Farm_Committee_ID = farmConfirm.Farm_Committee_ID;
    }

    public FarmConfirm(double longitude, double latitude, boolean isAccepted,
                       String notes, long employeeId, String date, int ID, long farm_Committee_ID) {
        Longitude = longitude;
        Latitude = latitude;
        IsAccepted = isAccepted;
        Notes = notes;
        EmployeeId = employeeId;
        Date = date;
        this.ID = ID;
        Farm_Committee_ID = farm_Committee_ID;
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
    public boolean isAccepted() {
        return IsAccepted;
    }

    public void setAccepted(boolean accepted) {
        IsAccepted = accepted;
    }

    @Bindable
    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    @Bindable
    public long getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(long employeeId) {
        EmployeeId = employeeId;
    }

    @Bindable
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Bindable
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Bindable
    public long getFarm_Committee_ID() {
        return Farm_Committee_ID;
    }

    public void setFarm_Committee_ID(long farm_Committee_ID) {
        Farm_Committee_ID = farm_Committee_ID;
    }
}
