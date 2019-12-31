package com.example.abeer.quarantine.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class FarmSample  extends BaseObservable {

    Short AnalysisType_ID;
    int AnalysisLabType_ID;
    String WithdrawDate;
    String User_Creation_Date;
    double SampleSize;
    double SampleRatio;
    String Sample_BarCode;
    String  Notes_Ar ;
    Long FarmCommittee_ID;
    double Longitude ,Latitude ;
    long User_Creation_Id;
    public long ID;
    public String FarmCode_14;

    public FarmSample() {

    }

    public FarmSample(FarmSample FarmSample) {

        AnalysisType_ID = FarmSample.AnalysisType_ID;
        AnalysisLabType_ID = FarmSample.AnalysisLabType_ID;
        WithdrawDate = FarmSample.WithdrawDate;
        User_Creation_Date=FarmSample.WithdrawDate;
        SampleSize = FarmSample.SampleSize;
        SampleRatio = FarmSample.SampleRatio;
        Latitude=FarmSample.Latitude;
        FarmCode_14=FarmSample.FarmCode_14;
        Notes_Ar = FarmSample.Notes_Ar;
        Longitude=FarmSample.Longitude;
        FarmCommittee_ID=FarmSample.FarmCommittee_ID;
        User_Creation_Id=FarmSample.User_Creation_Id;
        Sample_BarCode=FarmSample.Sample_BarCode;
        ID=FarmSample.ID;
    }
    @Bindable
    public Short getAnalysisType_ID() {
        return AnalysisType_ID;
    }

    public void setAnalysisType_ID(Short analysisType_ID) {
        AnalysisType_ID = analysisType_ID;
    }
    @Bindable
    public int getAnalysisLabType_ID() {
        return AnalysisLabType_ID;
    }

    public void setAnalysisLabType_ID(int analysisLabType_ID) {
        AnalysisLabType_ID = analysisLabType_ID;
    }
    @Bindable
    public String getWithdrawDate() {
        return WithdrawDate;
    }

    public void setWithdrawDate(String withdrawDate) {
        WithdrawDate = withdrawDate;
    }
    @Bindable
    public String getUser_Creation_Date() {
        return User_Creation_Date;
    }

    public void setUser_Creation_Date(String user_Creation_Date) {
        User_Creation_Date = user_Creation_Date;
    }
    @Bindable
    public double getSampleSize() {
        return SampleSize;
    }

    public void setSampleSize(double sampleSize) {
        SampleSize = sampleSize;
    }
    @Bindable
    public double getSampleRatio() {
        return SampleRatio;
    }

    public void setSampleRatio(double sampleRatio) {
        SampleRatio = sampleRatio;
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
    public Long getFarmCommittee_ID() {
        return FarmCommittee_ID;
    }

    public void setFarmCommittee_ID(Long farmCommittee_ID) {
        FarmCommittee_ID = farmCommittee_ID;
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
    public long getUser_Creation_Id() {
        return User_Creation_Id;
    }

    public void setUser_Creation_Id(long user_Creation_Id) {
        User_Creation_Id = user_Creation_Id;
    }
    @Bindable
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
    @Bindable
    public String getFarmCode_14() {
        return FarmCode_14;
    }

    public void setFarmCode_14(String farmCode_14) {
        FarmCode_14 = farmCode_14;
    }
}
