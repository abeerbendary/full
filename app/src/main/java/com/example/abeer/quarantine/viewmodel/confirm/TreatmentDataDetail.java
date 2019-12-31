package com.example.abeer.quarantine.viewmodel.confirm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.abeer.quarantine.viewmodel.treatmentStatement.TreatmentResult;

public class TreatmentDataDetail extends BaseObservable {
    public   long Ex_Request_Item_Id;
    public  long Ex_Request_LotData_ID;
    public  String TreatmentType_Name;
    public  String Company_Name;
    public  String TreatmentMaterial_Name;
    public  String TreatmentMethods_Name;
    public String Station_Place;
    public String Temperature;
    public String Exposure_Day;
    public  String Exposure_Hour;
    public  String Exposure_Minute;
    public  String TheDose;
    public  String Size;
    public  String TreatmentMat_Amount;
    public  String Note;
    public  String Lot_Number;
    public  long TreatmentData_Id;


    public TreatmentDataDetail(TreatmentResult treatmentResult) {
//        Ex_Request_Item_Id = ex_Request_Item_Id;
        Ex_Request_LotData_ID = treatmentResult.getLot_ID();
//        TreatmentType_Name = treatmentType_Name;
//        Company_Name = company_Name;
//        TreatmentMaterial_Name = treatmentMaterial_Name;
//        TreatmentMethods_Name = treatmentResult.get;
        Temperature = String.valueOf(treatmentResult.getTemperature());
        Exposure_Day = String.valueOf(treatmentResult.getExposure_Day());
        Exposure_Hour = String.valueOf(treatmentResult.getExposure_Hour());
        Exposure_Minute = String.valueOf(treatmentResult.getExposure_Minute());
        TheDose = String.valueOf(treatmentResult.getDosage());
        Size = String.valueOf(treatmentResult.getResala_size());
        TreatmentMat_Amount = String.valueOf(treatmentResult.getQuantity_material());
        Note = treatmentResult.getComment();
//        Lot_Number = lot_Number;
//        TreatmentData_Id = tr;
    }

    public TreatmentDataDetail(TreatmentDataDetail f) {
        this.Ex_Request_Item_Id= f.Ex_Request_Item_Id;
        this.Ex_Request_LotData_ID  =f.Ex_Request_LotData_ID;
        this.TreatmentType_Name = f.TreatmentType_Name;
        this.Company_Name = f.Company_Name;
        this.TreatmentMaterial_Name=f. TreatmentMaterial_Name;
        this.TreatmentMethods_Name =f. TreatmentMethods_Name;
        this.Station_Place = f.Station_Place;
        this.Temperature=f.Temperature;
        this.Exposure_Day=f.Exposure_Day;
        this.Exposure_Hour=f.Exposure_Hour;
        this.Exposure_Minute=f.Exposure_Minute;
        this.TheDose=f.TheDose;
        this.Size=f.Size;
        this.TreatmentMat_Amount=f.TreatmentMat_Amount;
        this.Note=f.Note;
    }
    @Bindable
    public long getTreatmentData_Id() {
        return TreatmentData_Id;
    }

    public void setTreatmentData_Id(long treatmentData_Id) {
        TreatmentData_Id = treatmentData_Id;
    }

    @Bindable
    public String getLot_Number() {
        return Lot_Number;
    }

    public void setLot_Number(String lot_Number) {
        Lot_Number = lot_Number;
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
    public String getTreatmentType_Name() {
        return TreatmentType_Name;
    }

    public void setTreatmentType_Name(String treatmentType_Name) {
        TreatmentType_Name = treatmentType_Name;
    }
    @Bindable
    public String getCompany_Name() {
        return Company_Name;
    }

    public void setCompany_Name(String company_Name) {
        Company_Name = company_Name;
    }
    @Bindable
    public String getTreatmentMaterial_Name() {
        return TreatmentMaterial_Name;
    }

    public void setTreatmentMaterial_Name(String treatmentMaterial_Name) {
        TreatmentMaterial_Name = treatmentMaterial_Name;
    }
    @Bindable
    public String getTreatmentMethods_Name() {
        return TreatmentMethods_Name;
    }

    public void setTreatmentMethods_Name(String treatmentMethods_Name) {
        TreatmentMethods_Name = treatmentMethods_Name;
    }
    @Bindable
    public String getStation_Place() {
        return Station_Place;
    }

    public void setStation_Place(String station_Place) {
        Station_Place = station_Place;
    }
    @Bindable
    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }
    @Bindable
    public String getExposure_Day() {
        return Exposure_Day;
    }

    public void setExposure_Day(String exposure_Day) {
        Exposure_Day = exposure_Day;
    }
    @Bindable
    public String getExposure_Hour() {
        return Exposure_Hour;
    }

    public void setExposure_Hour(String exposure_Hour) {
        Exposure_Hour = exposure_Hour;
    }
    @Bindable
    public String getExposure_Minute() {
        return Exposure_Minute;
    }

    public void setExposure_Minute(String exposure_Minute) {
        Exposure_Minute = exposure_Minute;
    }
    @Bindable
    public String getTheDose() {
        return TheDose;
    }

    public void setTheDose(String theDose) {
        TheDose = theDose;
    }
    @Bindable
    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }
    @Bindable
    public String getTreatmentMat_Amount() {
        return TreatmentMat_Amount;
    }

    public void setTreatmentMat_Amount(String treatmentMat_Amount) {
        TreatmentMat_Amount = treatmentMat_Amount;
    }
    @Bindable
    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }
}
