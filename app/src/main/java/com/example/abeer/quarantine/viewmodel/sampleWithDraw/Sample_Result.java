package com.example.abeer.quarantine.viewmodel.sampleWithDraw;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.example.abeer.quarantine.BR;

public class Sample_Result  extends BaseObservable {

    int Inspection_num;
    Short AnalysisType_ID;
    int Laboratory_ID;
    String Date;
    String place;
    double SampleSize;
    double SampleUnderSize;
    //String Address;
    Long lot_ID;
    String BarCode;
    String  Comment ;
//    Long Committee_ID;
    double Longitude ,Latitude ;
//    long EmployeeId;

    public Sample_Result() {

    }

    public Sample_Result(Sample_Result sampleresult) {
        Inspection_num = sampleresult.Inspection_num;
        AnalysisType_ID = sampleresult.AnalysisType_ID;
        Laboratory_ID = sampleresult.Laboratory_ID;
        Date = sampleresult.Date;
        this.place = sampleresult.place;
        SampleSize = sampleresult.SampleSize;
        SampleUnderSize = sampleresult.SampleUnderSize;
    //    Address =sampleresult.Address;
        Latitude=sampleresult.Latitude;
        BarCode=sampleresult.BarCode;
        Comment = sampleresult.Comment;
        Longitude=sampleresult.Longitude;
        lot_ID=sampleresult.lot_ID;
//        Committee_ID=sampleresult.Committee_ID;
//        EmployeeId=sampleresult.EmployeeId;
//        notifyPropertyChanged(BR.SampleResult);

    }

    @Bindable
    public Long getLot_ID() {
        return lot_ID;
    }

    public void setLot_ID(Long lot_ID) {
        this.lot_ID = lot_ID;
//        notifyPropertyChanged(BR.lot_ID);
    }

//    @Bindable
//    public String getAddress() {
//        return Address;
//
//    }

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String barCode) {
        BarCode = barCode;
    }

//    public Long getCommittee_ID() {
//        return Committee_ID;
//    }
//
//    public void setCommittee_ID(Long committee_ID) {
//        Committee_ID = committee_ID;
//    }

//    public void setAddress(String address) {
//        Address = address;
//        notifyPropertyChanged(BR.address);
//    }


    @Bindable
    public String getComment() {
        return Comment;
    }


    public void setComment(String comment) {
        Comment = comment;
//        notifyPropertyChanged(BR.comment);

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

    @Bindable
    public int getInspection_num() {
        return Inspection_num;
    }

    public void setInspection_num(int inspection_num) {
        Inspection_num = inspection_num;
//        notifyPropertyChanged(BR.inspection_num);

    }

    @Bindable
    public Short getAnalysisType_ID() {
        return AnalysisType_ID;
    }

    public void setAnalysisType_ID(Short analysisType_ID) {
        AnalysisType_ID = analysisType_ID;
//        notifyPropertyChanged(BR.analysisType_ID);
    }

    @Bindable
    public int getLaboratory_ID() {
        return Laboratory_ID;
    }

    public void setLaboratory_ID(int laboratory_ID) {
        Laboratory_ID = laboratory_ID;
//        notifyPropertyChanged(BR.laboratory_ID);
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
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
//        notifyPropertyChanged(BR.place);
    }

    @Bindable
    public double getSampleSize() {
        return SampleSize;
    }

    public void setSampleSize(double sampleSize) {
        SampleSize = sampleSize;
//        notifyPropertyChanged(BR.sampleSize);
    }

    @Bindable
    public double getSampleUnderSize() {
        return SampleUnderSize;
    }

    public void setSampleUnderSize(double sampleUnderSize) {
        SampleUnderSize = sampleUnderSize;
//        notifyPropertyChanged(BR.sampleUnderSize);
    }

//    public long getEmployeeId() {
//        return EmployeeId;
//    }
//
//    public void setEmployeeId(long employeeId) {
//        EmployeeId = employeeId;
//    }
}
