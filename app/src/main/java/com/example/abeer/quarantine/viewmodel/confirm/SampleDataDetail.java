package com.example.abeer.quarantine.viewmodel.confirm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class SampleDataDetail  extends BaseObservable {
    public long Ex_Request_Item_Id;
    public long  Ex_Request_LotData_ID;
    public long SampeData_Id;
    public  String Sample_BarCode;
    public String Notes_Ar;
    public  String WithDrawPlace;
    public String SampleSize;
    public  String SampleRatio;
    public  String AnalysisType_Name;
    public  String AnalysisLab_Name;
    public  String Lot_Number;

    @Bindable
    public long getSampeData_Id() {
        return SampeData_Id;
    }

    public void setSampeData_Id(long sampeData_Id) {
        SampeData_Id = sampeData_Id;
    }
    @Bindable
    public String getLot_Number() {
        return Lot_Number;
    }

    public void setLot_Number(String lot_Number) {
        Lot_Number = lot_Number;
    }

    public SampleDataDetail( ) {

    }

    public SampleDataDetail(SampleDataDetail f) {
        this.Ex_Request_Item_Id= f.Ex_Request_Item_Id;
        this.Ex_Request_LotData_ID  =f.Ex_Request_LotData_ID;
        this.Sample_BarCode = f.Sample_BarCode;
        this.Ex_Request_Item_Id  =f.Ex_Request_Item_Id;
        this.Notes_Ar = f.Notes_Ar;
        this.WithDrawPlace=f. WithDrawPlace;
        this.SampleSize =f. SampleSize;
        this.SampleRatio = f.SampleRatio;
        this.AnalysisType_Name=f.AnalysisType_Name;
        this.AnalysisLab_Name=f.AnalysisLab_Name;
    }
    @Bindable
    public long getEx_Request_Item_Id() {
        return Ex_Request_Item_Id;
    }

    public void setEx_Request_Item_Id(long ex_Request_Item_Id) {
        Ex_Request_Item_Id = ex_Request_Item_Id;
    }
    @Bindable
    public long getEx_Request_LotData_ID() {
        return Ex_Request_LotData_ID;
    }

    public void setEx_Request_LotData_ID(long ex_Request_LotData_ID) {
        Ex_Request_LotData_ID = ex_Request_LotData_ID;
    }
    @Bindable
    public String getSample_BarCode() {
        return Sample_BarCode;
    }

    public void setSample_BarCode(String sample_BarCode) {
        Sample_BarCode = sample_BarCode;
    }
    @Bindable
    public String getNotes_Ar() {
        return Notes_Ar;
    }

    public void setNotes_Ar(String notes_Ar) {
        Notes_Ar = notes_Ar;
    }
    @Bindable
    public String getWithDrawPlace() {
        return WithDrawPlace;
    }

    public void setWithDrawPlace(String withDrawPlace) {
        WithDrawPlace = withDrawPlace;
    }
    @Bindable
    public String getSampleSize() {
        return SampleSize;
    }

    public void setSampleSize(String sampleSize) {
        SampleSize = sampleSize;
    }
    @Bindable
    public String getSampleRatio() {
        return SampleRatio;
    }

    public void setSampleRatio(String sampleRatio) {
        SampleRatio = sampleRatio;
    }
    @Bindable
    public String getAnalysisType_Name() {
        return AnalysisType_Name;
    }

    public void setAnalysisType_Name(String analysisType_Name) {
        AnalysisType_Name = analysisType_Name;
    }
    @Bindable
    public String getAnalysisLab_Name() {
        return AnalysisLab_Name;
    }

    public void setAnalysisLab_Name(String analysisLab_Name) {
        AnalysisLab_Name = analysisLab_Name;
    }
}
