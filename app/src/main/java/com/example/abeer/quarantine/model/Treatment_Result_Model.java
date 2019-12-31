package com.example.abeer.quarantine.model;

import android.net.wifi.WifiManager;

import com.example.abeer.quarantine.viewmodel.treatmentStatement.TreatmentResult;

public class Treatment_Result_Model {

//    public long ID { get; set; }
//    public long Committee_ID { get; set; }
//    public Nullable<byte> TreatmentType_ID { get; set; }
//    public Nullable<long> Company_ID { get; set; }
//    public Nullable<long> Station_ID { get; set; }
//    public string Station_Place { get; set; }
//    public byte TreatmentMethod_ID { get; set; }
//    public Nullable<byte> TreatmentMat_ID { get; set; }
//    public Nullable<decimal> Size { get; set; }
//    public Nullable<decimal> TreatmentMat_Amount { get; set; }
//    public Nullable<decimal> TheDose { get; set; }
//    public Nullable<int> Exposure_Minute { get; set; }
//    public Nullable<int> Exposure_Hour { get; set; }
//    public Nullable<int> Exposure_Day { get; set; }
//    public Nullable<decimal> Temperature { get; set; }
//    public int IsLot { get; set; }
//    public long Ex_Request_LotData_ID { get; set; }
//    public Nullable<decimal> ThermalSealNumber { get; set; }
//    public short User_Creation_Id { get; set; }
//    public System.DateTime User_Creation_Date { get; set; }
//    public string Note { get; set; }


public  long ID;
public  long Committee_ID;
public byte TreatmentType_ID;
public int Company_ID;
public  long Station_ID;
public String Station_Place;
public byte TreatmentMethod_ID;
public byte TreatmentMat_ID;
public float Size;
public float TreatmentMat_Amount;
public float TheDose;
public  int Exposure_Minute;
public int Exposure_Hour;
public  int Exposure_Day;
public  float Temperature;
public  int IsLot;
public  long Ex_Request_LotData_ID;
public  float ThermalSealNumber;
//public  long User_Creation_Id;
public  String User_Creation_Date;
public  String Note;
    double Longitude ,Latitude ;

    public Treatment_Result_Model(Treatment_Result_Model treatment_result_model) {
        this.ID = treatment_result_model.ID;
        Committee_ID = treatment_result_model.Committee_ID;
        TreatmentType_ID = treatment_result_model.TreatmentType_ID;
       Company_ID = treatment_result_model.Company_ID;
        Station_ID = treatment_result_model.Station_ID;
        Station_Place = treatment_result_model.Station_Place;
        TreatmentMethod_ID =treatment_result_model.TreatmentMethod_ID ;
        TreatmentMat_ID = treatment_result_model.TreatmentMat_ID;
        Size = treatment_result_model.Size;
        TreatmentMat_Amount = treatment_result_model.TreatmentMat_Amount;
        TheDose = treatment_result_model.TheDose;
        Exposure_Minute = treatment_result_model.Exposure_Minute;
        Exposure_Hour = treatment_result_model.Exposure_Hour;
        Exposure_Day = treatment_result_model.Exposure_Day;
        Temperature = treatment_result_model.Temperature;
        IsLot = treatment_result_model.IsLot;
        Ex_Request_LotData_ID = treatment_result_model.Ex_Request_LotData_ID;
        ThermalSealNumber = treatment_result_model.ThermalSealNumber;
        Latitude=treatment_result_model.Latitude;
        Longitude=treatment_result_model.Longitude;
    }

    public Treatment_Result_Model(TreatmentResult treatmentResult) {

       this.ID=0;
//       this.Committee_ID=treatmentResult.getCommittee_ID();
       this.TreatmentType_ID=treatmentResult.getTreatment_Type_ID();
       this.Company_ID=treatmentResult.getTreatment_company_ID();
       this.Station_ID=treatmentResult.getCertified_place_ID();
       this.Longitude=treatmentResult.getLongitude();
       this.Latitude=treatmentResult.getLatitude();
       if(Station_ID==0)
       {
           this.Station_Place=treatmentResult.getUncertified_place();
       }else {
           this.Station_Place="";
       }
       this.TreatmentMethod_ID=treatmentResult.getTreatment_method_ID();
       this.TreatmentMat_ID=treatmentResult.getTreatment_material_ID();
       this.Size=treatmentResult.getResala_size();
       this.TreatmentMat_Amount=treatmentResult.getQuantity_material();
       this.TheDose=treatmentResult.getDosage();
       this.Exposure_Minute=treatmentResult.getExposure_Minute();
       this.Exposure_Day=treatmentResult.getExposure_Day();
       this.Exposure_Hour=treatmentResult.getExposure_Hour();
       this.Temperature=treatmentResult.getTemperature();
       this.Ex_Request_LotData_ID=treatmentResult.getLot_ID();
       this.ThermalSealNumber=treatmentResult.getThermalSealNumber();
       this.User_Creation_Date=treatmentResult.getDate();
       this.Note=treatmentResult.getComment();

    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getCommittee_ID() {
        return Committee_ID;
    }

    public void setCommittee_ID(long committee_ID) {
        Committee_ID = committee_ID;
    }

    public byte getTreatmentType_ID() {
        return TreatmentType_ID;
    }

    public void setTreatmentType_ID(byte treatmentType_ID) {
        TreatmentType_ID = treatmentType_ID;
    }

    public int getCompany_ID() {
        return Company_ID;
    }

    public void setCompany_ID(int company_ID) {
        Company_ID = company_ID;
    }

    public long getStation_ID() {
        return Station_ID;
    }

    public void setStation_ID(long station_ID) {
        Station_ID = station_ID;
    }

    public String getStation_Place() {
        return Station_Place;
    }

    public void setStation_Place(String station_Place) {
        Station_Place = station_Place;
    }

    public byte getTreatmentMethod_ID() {
        return TreatmentMethod_ID;
    }

    public void setTreatmentMethod_ID(byte treatmentMethod_ID) {
        TreatmentMethod_ID = treatmentMethod_ID;
    }

    public byte getTreatmentMat_ID() {
        return TreatmentMat_ID;
    }

    public void setTreatmentMat_ID(byte treatmentMat_ID) {
        TreatmentMat_ID = treatmentMat_ID;
    }

    public float getSize() {
        return Size;
    }

    public void setSize(float size) {
        Size = size;
    }

    public float getTreatmentMat_Amount() {
        return TreatmentMat_Amount;
    }

    public void setTreatmentMat_Amount(float treatmentMat_Amount) {
        TreatmentMat_Amount = treatmentMat_Amount;
    }

    public float getTheDose() {
        return TheDose;
    }

    public void setTheDose(float theDose) {
        TheDose = theDose;
    }

    public int getExposure_Minute() {
        return Exposure_Minute;
    }

    public void setExposure_Minute(int exposure_Minute) {
        Exposure_Minute = exposure_Minute;
    }

    public int getExposure_Hour() {
        return Exposure_Hour;
    }

    public void setExposure_Hour(int exposure_Hour) {
        Exposure_Hour = exposure_Hour;
    }

    public int getExposure_Day() {
        return Exposure_Day;
    }

    public void setExposure_Day(int exposure_Day) {
        Exposure_Day = exposure_Day;
    }

    public float getTemperature() {
        return Temperature;
    }

    public void setTemperature(float temperature) {
        Temperature = temperature;
    }

    public int getIsLot() {
        return IsLot;
    }

    public void setIsLot(int isLot) {
        IsLot = isLot;
    }

    public long getEx_Request_LotData_ID() {
        return Ex_Request_LotData_ID;
    }

    public void setEx_Request_LotData_ID(long ex_Request_LotData_ID) {
        Ex_Request_LotData_ID = ex_Request_LotData_ID;
    }

    public float getThermalSealNumber() {
        return ThermalSealNumber;
    }

    public void setThermalSealNumber(float thermalSealNumber) {
        ThermalSealNumber = thermalSealNumber;
    }

//    public long getUser_Creation_Id() {
//        return User_Creation_Id;
//    }
//
//    public void setUser_Creation_Id(long user_Creation_Id) {
//        User_Creation_Id = user_Creation_Id;
//    }

    public String getUser_Creation_Date() {
        return User_Creation_Date;
    }

    public void setUser_Creation_Date(String user_Creation_Date) {
        User_Creation_Date = user_Creation_Date;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }
}
