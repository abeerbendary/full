package com.example.abeer.quarantine.viewmodel.confirm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class ConfirmResult extends BaseObservable {
            long CommitteeResult_ID;
          String  Date;
         long   EmployeeId;
          String  Notes;
           boolean IsAccepted;
    public ConfirmResult() {

    }
    public ConfirmResult(long CommitteeResult_ID, String date, long employeeId, String notes, boolean isAccepted) {
        CommitteeResult_ID = CommitteeResult_ID;
        Date = date;
        EmployeeId = employeeId;
        Notes = notes;
        IsAccepted = isAccepted;
    }
    public ConfirmResult(ConfirmResult confirmResult) {
        CommitteeResult_ID = confirmResult.CommitteeResult_ID;
        Date = confirmResult.Date;
        EmployeeId = confirmResult.EmployeeId;
        Notes = confirmResult.Notes;
        IsAccepted = confirmResult.IsAccepted;
    }
    @Bindable
    public long getCommitteeResult_ID() {
        return CommitteeResult_ID;
    }

    public void setCommitteeResult_ID(long ex_Request_TreatmentData_ID) {
        CommitteeResult_ID = ex_Request_TreatmentData_ID;
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
    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }
    @Bindable
    public boolean isAccepted() {
        return IsAccepted;
    }

    public void setAccepted(boolean accepted) {
        IsAccepted = accepted;
    }
}
