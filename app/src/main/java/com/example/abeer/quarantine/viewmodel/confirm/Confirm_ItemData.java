package com.example.abeer.quarantine.viewmodel.confirm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class Confirm_ItemData extends BaseObservable {

   String $id;
    String  Ex_Request_Item_Id;
    String Committee_result;
    String Analysis_result;
    String Treatment_result;
    @Bindable
    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }
    @Bindable
    public String getEx_Request_Item_Id() {
        return Ex_Request_Item_Id;
    }

    public void setEx_Request_Item_Id(String ex_Request_Item_Id) {
        Ex_Request_Item_Id = ex_Request_Item_Id;
    }
    @Bindable
    public String getCommittee_result() {
        return Committee_result;
    }

    public void setCommittee_result(String committee_result) {
        Committee_result = committee_result;
    }
    @Bindable
    public String getAnalysis_result() {
        return Analysis_result;
    }

    public void setAnalysis_result(String analysis_result) {
        Analysis_result = analysis_result;
    }
    @Bindable
    public String getTreatment_result() {
        return Treatment_result;
    }

    public void setTreatment_result(String treatment_result) {
        Treatment_result = treatment_result;
    }
}
