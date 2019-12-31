package com.example.abeer.quarantine.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class StationDataConfirm extends BaseObservable {

    long ID;
    long Station_Accreditation_CommitteeResult_ID;
    //     String Date ;
    long EmployeeId;
    String Notes_Ar;
    Boolean IsAccepted;

    public StationDataConfirm() {
    }

    public StationDataConfirm(StationDataConfirm stationDataConfirm) {
        ID = stationDataConfirm.ID;
        Station_Accreditation_CommitteeResult_ID = stationDataConfirm.Station_Accreditation_CommitteeResult_ID;
        EmployeeId = stationDataConfirm.EmployeeId;
        Notes_Ar = stationDataConfirm.Notes_Ar;
        IsAccepted = stationDataConfirm.IsAccepted;
    }

    public StationDataConfirm(long ID, long station_Accreditation_CommitteeResult_ID, long employeeId, String notes, Boolean isAccepted) {
        this.ID = ID;
        Station_Accreditation_CommitteeResult_ID = station_Accreditation_CommitteeResult_ID;
        EmployeeId = employeeId;
        Notes_Ar = notes;
        IsAccepted = isAccepted;
    }

    @Bindable
    public long getID() {
        return ID;
    }

    @Bindable
    public long getStation_Accreditation_CommitteeResult_ID() {
        return Station_Accreditation_CommitteeResult_ID;
    }

    @Bindable
    public long getEmployeeId() {
        return EmployeeId;
    }

    @Bindable
    public String getNotes() {
        return Notes_Ar;
    }

    @Bindable
    public Boolean getAccepted() {
        return IsAccepted;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setStation_Accreditation_CommitteeResult_ID(long station_Accreditation_CommitteeResult_ID) {
        Station_Accreditation_CommitteeResult_ID = station_Accreditation_CommitteeResult_ID;
    }

    public void setEmployeeId(long employeeId) {
        EmployeeId = employeeId;
    }

    public void setNotes(String notes) {
        Notes_Ar = notes;
    }

    public void setAccepted(Boolean accepted) {
        IsAccepted = accepted;
    }
}
