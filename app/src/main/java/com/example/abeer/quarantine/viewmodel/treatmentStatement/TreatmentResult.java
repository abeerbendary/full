package com.example.abeer.quarantine.viewmodel.treatmentStatement;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.abeer.quarantine.BR;

public class TreatmentResult extends BaseObservable {
//    Long Committee_ID;
   byte treatment_Type_ID;
   int treatment_company_ID;
   Long certified_place_ID;
   String uncertified_place;
   byte treatment_method_ID;
   float  resala_size;
   byte treatment_material_ID;
   float quantity_material;
   float Dosage;
  int Exposure_Minute;
   int Exposure_Hour;
      int Exposure_Day;
   float temperature;
  // String Address;
    String Date;
   Long lot_ID;
    String  Comment ;
    float ThermalSealNumber;
    double Longitude ,Latitude ;
//    long EmployeeId;


    public TreatmentResult() {

    }

    public TreatmentResult(TreatmentResult treatmentResult) {
//        Committee_ID=treatmentResult.Committee_ID;
        treatment_Type_ID = treatmentResult.treatment_Type_ID;
        treatment_company_ID = treatmentResult.treatment_company_ID;
        certified_place_ID = treatmentResult.certified_place_ID;
        uncertified_place = treatmentResult.uncertified_place;
        treatment_method_ID = treatmentResult.treatment_method_ID;
        resala_size = treatmentResult.resala_size;
        treatment_material_ID = treatmentResult.treatment_material_ID;
        quantity_material = treatmentResult.quantity_material;
        Dosage = treatmentResult.Dosage;
        Exposure_Minute=treatmentResult.Exposure_Minute;
        Exposure_Hour=treatmentResult.Exposure_Hour;
        Exposure_Day=treatmentResult.Exposure_Day;
        temperature = treatmentResult.temperature;
      //  Address = treatmentResult.Address;
        lot_ID = treatmentResult.lot_ID;
        Comment = treatmentResult.Comment;
        Date=treatmentResult.Date;
        Latitude = treatmentResult.Latitude;
        Longitude = treatmentResult.Longitude;
        ThermalSealNumber=treatmentResult.ThermalSealNumber;
//        EmployeeId=treatmentResult.EmployeeId;
//        notifyPropertyChanged(BR.treatmentResult);
    }

    @Bindable
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
//        notifyPropertyChanged(BR.date);
    }
@Bindable
    public int getExposure_Minute() {
        return Exposure_Minute;
    }
@Bindable
    public float getThermalSealNumber() {
        return ThermalSealNumber;
    }

    public void setThermalSealNumber(float thermalSealNumber) {
        ThermalSealNumber = thermalSealNumber;
    }

    public void setExposure_Minute(int exposure_Minute) {
        Exposure_Minute = exposure_Minute;
      //  notifyPropertyChanged(BR.ex);
    }

@Bindable
    public int getExposure_Hour() {
        return Exposure_Hour;
    }

//    public long getEmployeeId() {
//        return EmployeeId;
//    }
//
//    public void setEmployeeId(long employeeId) {
//        EmployeeId = employeeId;
//    }

    public void setExposure_Hour(int exposure_Hour) {
        Exposure_Hour = exposure_Hour;
    }

//    public Long getCommittee_ID() {
//        return Committee_ID;
//    }
//
//    public void setCommittee_ID(Long committee_ID) {
//        Committee_ID = committee_ID;
//    }

    @Bindable
    public int getExposure_Day() {
        return Exposure_Day;
    }

    public void setExposure_Day(int exposure_Day) {
        Exposure_Day = exposure_Day;
    }

    @Bindable
    public byte getTreatment_Type_ID() {
        return treatment_Type_ID;
    }

    public void setTreatment_Type_ID(byte treatment_Type_ID) {
        this.treatment_Type_ID = treatment_Type_ID;
//        notifyPropertyChanged(BR.treatment_Type_ID);
    }

    @Bindable
    public int getTreatment_company_ID() {
        return treatment_company_ID;
    }

    public void setTreatment_company_ID(int treatment_company_ID) {
        this.treatment_company_ID = treatment_company_ID;
//        notifyPropertyChanged(BR.treatment_company_ID);
    }

    @Bindable
    public Long getCertified_place_ID() {
        return certified_place_ID;
    }

    public void setCertified_place_ID(Long certified_place_ID) {
        if(certified_place_ID==null)
        {
            this.certified_place_ID = Long.valueOf(0);
        }else {
            this.certified_place_ID = certified_place_ID;
        }
//        notifyPropertyChanged(BR.certified_place_ID);
    }

    @Bindable
    public String getUncertified_place() {
        return uncertified_place;
    }

    public void setUncertified_place(String uncertified_place) {
        this.uncertified_place = uncertified_place;
//        notifyPropertyChanged(BR.uncertified_place);
    }


    @Bindable
    public String getComment() {
        return Comment;
    }


    public void setComment(String comment) {
        Comment = comment;
//        notifyPropertyChanged(BR.comment);

    }
    @Bindable
    public byte getTreatment_method_ID() {
        return treatment_method_ID;
    }

    public void setTreatment_method_ID(byte treatment_method_ID) {
        this.treatment_method_ID = treatment_method_ID;
//        notifyPropertyChanged(BR.treatment_method_ID);
    }

    @Bindable
    public float getResala_size() {
        return resala_size;
    }

    public void setResala_size(float resala_size) {
        this.resala_size = resala_size;
//        notifyPropertyChanged(BR.resala_size);
    }

    @Bindable
    public byte getTreatment_material_ID() {
        return treatment_material_ID;
    }

    public void setTreatment_material_ID(byte treatment_material_ID) {
        this.treatment_material_ID = treatment_material_ID;
//        notifyPropertyChanged(BR.treatment_material_ID);
    }

    @Bindable
    public float getQuantity_material() {
        return quantity_material;
    }


    public void setQuantity_material(float quantity_material) {
        this.quantity_material = quantity_material;
//        notifyPropertyChanged(BR.quantity_material);
    }

    @Bindable
    public float getDosage() {
        return Dosage;
    }

    public void setDosage(float dosage) {
        Dosage = dosage;
//        notifyPropertyChanged(BR.dosage);
    }


    @Bindable
    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
//        notifyPropertyChanged(BR.temperature);
    }

//    @Bindable
//    public String getAddress() {
//        return Address;
//    }
//
//    public void setAddress(String address) {
//        Address = address;
//        notifyPropertyChanged(BR.address);
//    }

    @Bindable
    public Long getLot_ID() {
        return lot_ID;
    }

    public void setLot_ID(Long lot_ID) {
        this.lot_ID = lot_ID;
//        notifyPropertyChanged(BR.lot_ID);
    }

    @Bindable
    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
//        notifyPropertyChanged(BR.latitude);
    }

    @Bindable
    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
//        notifyPropertyChanged(BR.longitude);
    }
}
