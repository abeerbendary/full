package com.example.abeer.quarantine.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class StationConfirmResult extends BaseObservable {
    long ID;
    long Station_Accreditation_CommitteeResult_ID;
    String Date ;
    long EmployeeId;
    String Notes_Ar;
    Boolean IsAccepted;
    double Latitude,Longitude;
    long Station_Committee_ID;

    public StationConfirmResult() {
    }
    public StationConfirmResult(StationConfirmResult stationConfirmResult) {
        this.ID = stationConfirmResult.ID;
        Station_Accreditation_CommitteeResult_ID = stationConfirmResult.Station_Accreditation_CommitteeResult_ID;
        Date = stationConfirmResult.Date;
        EmployeeId = stationConfirmResult.EmployeeId;
        Notes_Ar = stationConfirmResult.Notes_Ar;
        IsAccepted = stationConfirmResult.IsAccepted;
        Latitude = stationConfirmResult.Latitude;
        Longitude = stationConfirmResult.Longitude;
        Station_Committee_ID=stationConfirmResult.Station_Committee_ID;
    }
    public StationConfirmResult(long ID, long station_Accreditation_CommitteeResult_ID, String date, long employeeId, String notes_Ar, Boolean isAccepted, long latitude, long longitude) {
        this.ID = ID;
        Station_Accreditation_CommitteeResult_ID = station_Accreditation_CommitteeResult_ID;
        Date = date;
        EmployeeId = employeeId;
        Notes_Ar = notes_Ar;
        IsAccepted = isAccepted;
        Latitude = latitude;
        Longitude = longitude;
    }
@Bindable
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
    @Bindable
    public long getStation_Accreditation_CommitteeResult_ID() {
        return Station_Accreditation_CommitteeResult_ID;
    }

    public void setStation_Accreditation_CommitteeResult_ID(long station_Accreditation_CommitteeResult_ID) {
        Station_Accreditation_CommitteeResult_ID = station_Accreditation_CommitteeResult_ID;
    }
    @Bindable
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
    @Bindable
    public long getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(long employeeId) {
        EmployeeId = employeeId;
    }
@Bindable
    public long getStation_Committee_ID() {
        return Station_Committee_ID;
    }

    public void setStation_Committee_ID(long station_Committee_ID) {
        Station_Committee_ID = station_Committee_ID;
    }

    @Bindable
    public String getNotes_Ar() {
        return Notes_Ar;
    }

    public void setNotes_Ar(String notes_Ar) {
        Notes_Ar = notes_Ar;
    }
    @Bindable

    public Boolean getAccepted() {
        return IsAccepted;
    }

    public void setAccepted(Boolean accepted) {
        IsAccepted = accepted;
    }
    @Bindable

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }
    @Bindable

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }
}
