package com.example.abeer.quarantine.viewmodel.confirm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;


public class CommiteeDataDetail extends BaseObservable {
    public String   Infection_Type;
    public int   CommitteeResultType_ID;
    public String  Name_Ar;
    public long    Ex_Request_Item_Id;
    public long   Ex_RequestLotData_ID;
    public double  Weight;
    public String  QuantitySize;
    public String  Date;
    public String   Infection_Name;
    public String  Result_injury_Name;
    public  String Lot_Number;
    public  String Notes;
    public  long CommitteeResult_Id;
    @Bindable
    public long getCommitteeResult_Id() {
        return CommitteeResult_Id;
    }

    public void setCommitteeResult_Id(long committeeResult_Id) {
        CommitteeResult_Id = committeeResult_Id;
    }


    @Bindable
    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }


    @Bindable
    public String getQuantitySize() {
        return QuantitySize;
    }

    public void setQuantitySize(String quantitySize) {
        QuantitySize = quantitySize;
    }



    public CommiteeDataDetail( ) {

    }

    public CommiteeDataDetail(CommiteeDataDetail f) {
        this.CommitteeResultType_ID= f.CommitteeResultType_ID;
        this.Infection_Type  =f.Infection_Type;
        this.QuantitySize=f.QuantitySize;
        this.Name_Ar = f.Name_Ar;
        this.Ex_Request_Item_Id  =f.Ex_Request_Item_Id;
        this.Ex_RequestLotData_ID = f.Ex_RequestLotData_ID;
        this.Weight=f. Weight;
        this.Infection_Name =f. Infection_Name;
        this.Date = f.Date;
        this.Result_injury_Name=f.Result_injury_Name;
        this.Lot_Number=f.Lot_Number;
    }
    @Bindable
    public String getLot_Number() {
        return Lot_Number;
    }

    public void setLot_Number(String lot_Number) {
        Lot_Number = lot_Number;
    }

    @Bindable
    public String getInfection_Type() {
        return Infection_Type;
    }

    public void setInfection_Type(String infection_Type) {
        Infection_Type = infection_Type;
    }
    @Bindable
    public int getCommitteeResultType_ID() {
        return CommitteeResultType_ID;
    }

    public void setCommitteeResultType_ID(int committeeResultType_ID) {
        CommitteeResultType_ID = committeeResultType_ID;
    }
    @Bindable
    public String getName_Ar() {
        return Name_Ar;
    }

    public void setName_Ar(String name_Ar) {
        Name_Ar = name_Ar;
    }
    @Bindable
    public long getEx_Request_Item_Id() {
        return Ex_Request_Item_Id;
    }

    public void setEx_Request_Item_Id(long ex_Request_Item_Id) {
        Ex_Request_Item_Id = ex_Request_Item_Id;
    }
    @Bindable
    public long getEx_RequestLotData_ID() {
        return Ex_RequestLotData_ID;
    }

    public void setEx_RequestLotData_ID(long ex_RequestLotData_ID) {
        Ex_RequestLotData_ID = ex_RequestLotData_ID;
    }
    @Bindable
    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }
    @Bindable
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
    @Bindable
    public String getInfection_Name() {
        return Infection_Name;
    }

    public void setInfection_Name(String infection_Name) {
        Infection_Name = infection_Name;
    }
    @Bindable
    public String getResult_injury_Name() {
        return Result_injury_Name;
    }

    public void setResult_injury_Name(String result_injury_Name) {
        Result_injury_Name = result_injury_Name;
    }

}