package com.example.abeer.quarantine.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class FarmDataConfirm  extends BaseObservable {
//     "$id": "1",
//             "ID": 0,
//             "AnalysisLabType_ID": null,
//             "FarmCommittee_ID": 0,
//             "WithdrawDate": "2020-02-02T00:00:00",
//             "Sample_BarCode": "3320202000",
//             "IsAccepted": false,
//             "RejectReason_Ar": null,
//             "RejectReason_En": null,
//             "Notes_Ar": "jddjdj",
//             "Notes_En": null,
//             "User_Updation_Id": null,
//             "User_Updation_Date": null,
//             "User_Deletion_Id": null,
//             "User_Deletion_Date": null,
//             "User_Creation_Id": 0,
//             "User_Creation_Date": "0001-01-01T00:00:00",
//             "FarmCode_14": null,
//             "Latitude": 0.0,
//             "Longitude": 0.0,
//             "AnalysiLab_Name_Ar": "قسم الفلورا بمعهد بحوث البساتين",
//             "AnalysiType_Name_Ar": "8"



    String AnalysiType_Name_Ar;
    String AnalysiLab_Name_Ar;
    String SampleSize;
    String SampleRatio;
    String Sample_BarCode;
    String Notes_Ar;
//    public String FarmCode_14;
public FarmDataConfirm(FarmSample farmSample,FarmDataConfirm farmDataConfirm) {
    this.SampleRatio= String.valueOf(farmSample.SampleRatio);
    this.Notes_Ar=farmSample.Notes_Ar;
    this.SampleSize= String.valueOf(farmSample.SampleSize);
    this.Sample_BarCode=farmSample.getSample_BarCode();
    this.AnalysiLab_Name_Ar=farmDataConfirm.AnalysiLab_Name_Ar;
    this.AnalysiType_Name_Ar=farmDataConfirm.AnalysiType_Name_Ar;
}


    public FarmDataConfirm() {
    }

    public FarmDataConfirm(String analysisType, String analysisLabType_ID, String sampleSize, String sampleRatio, String sample_BarCode, String notes_Ar, String farmCode_14) {
        AnalysiType_Name_Ar = analysisType;
        AnalysiLab_Name_Ar = analysisLabType_ID;
        SampleSize = sampleSize;
        SampleRatio = sampleRatio;
        Sample_BarCode = sample_BarCode;
        Notes_Ar = notes_Ar;
//        FarmCode_14 = farmCode_14;
    }

    public FarmDataConfirm(FarmDataConfirm farmDataConfirm) {
        AnalysiType_Name_Ar = farmDataConfirm.AnalysiType_Name_Ar;
        AnalysiLab_Name_Ar = farmDataConfirm.AnalysiLab_Name_Ar;
        SampleSize = farmDataConfirm.SampleSize;
        SampleRatio = farmDataConfirm.SampleRatio;
        Sample_BarCode = farmDataConfirm.Sample_BarCode;
        Notes_Ar = farmDataConfirm.Notes_Ar;
//        FarmCode_14 = farmDataConfirm.FarmCode_14;
    }

    @Bindable
    public String getAnalysisType() {
        return AnalysiType_Name_Ar;
    }

    public void setAnalysisType(String analysisType) {
        AnalysiType_Name_Ar = analysisType;
    }

    @Bindable
    public String getAnalysisLabType_ID() {
        return AnalysiLab_Name_Ar;
    }

    public void setAnalysisLabType_ID(String analysisLabType_ID) {
        AnalysiLab_Name_Ar = analysisLabType_ID;
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

//    @Bindable
//    public String getFarmCode_14() {
//        return FarmCode_14;
//    }
//
//    public void setFarmCode_14(String farmCode_14) {
//        FarmCode_14 = farmCode_14;
//    }
}
