package com.example.abeer.quarantine.viewmodel.confirm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.ArrayList;

public class CommitteData  extends BaseObservable {
    public ArrayList<CommiteeDataDetail> CommitteeData  =new ArrayList<>();

    public CommitteData(ArrayList<CommiteeDataDetail> committeeData) {
        CommitteeData = committeeData;
    }
    public CommitteData() {

    }
    @Bindable
    public CommiteeDataDetail get_obj() {
        CommiteeDataDetail resPlantproduct=new CommiteeDataDetail();
        return resPlantproduct;
    }
    public void set_ListDetail(CommitteData listDetail) {

        this.CommitteeData=listDetail.CommitteeData;

    }
}
