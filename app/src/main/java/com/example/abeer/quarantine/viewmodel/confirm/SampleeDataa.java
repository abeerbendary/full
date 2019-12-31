package com.example.abeer.quarantine.viewmodel.confirm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.ArrayList;

public class SampleeDataa extends BaseObservable {
    public ArrayList<SampleDataDetail> SampleData  =new ArrayList<>();

    public  SampleeDataa(ArrayList<SampleDataDetail>  sampleeDataa)
    {
        this.SampleData= sampleeDataa;
    }

    public SampleeDataa() {

    }

    @Bindable
    public SampleDataDetail get_obj() {
        SampleDataDetail sampleDataDetail=new SampleDataDetail();
        return sampleDataDetail;
    }
    public void set_ListDetail(SampleeDataa listDetail) {

        this.SampleData=listDetail.SampleData;
        //notifyPropertyChanged(BR.listItemConstrains);
    }

}
