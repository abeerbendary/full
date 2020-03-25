package com.example.abeer.quarantine.viewmodel.confirm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.ArrayList;

public class TreatmenttDataa  extends BaseObservable {
    public ArrayList<TreatmentDataDetail> TreatmentData  =new ArrayList<>();
    public  TreatmenttDataa(ArrayList<TreatmentDataDetail> treatmenttDataa)
    {
        this.TreatmentData= treatmenttDataa;
    }
    public  TreatmenttDataa(){

    }
    @Bindable
    public TreatmentDataDetail get_obj() {
        TreatmentDataDetail treatmentDataDetail=new TreatmentDataDetail();
        return treatmentDataDetail;
    }
    public void set_ListDetail(TreatmenttDataa listDetail) {

        this.TreatmentData=listDetail.TreatmentData;
        //notifyPropertyChanged(BR.listItemConstrains);
    }


}
