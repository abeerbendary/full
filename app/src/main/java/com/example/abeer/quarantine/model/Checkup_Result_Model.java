package com.example.abeer.quarantine.model;

import android.content.Context;

import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.Checkup_Result;

import org.w3c.dom.Text;

import java.util.Date;

public class Checkup_Result_Model {


    long ID;
    long Ex_RequestLotData_ID;
    int CommitteeResultType_ID;
   double Weight;
    int QuantitySize;
    String Notes;
    int Item_ID;
    int Item__OrderID;
    byte Result_injuryID;
    String Date;
    double Longitude ,Latitude ;
    public Checkup_Result_Model(Checkup_Result_Model checkupResult) {
        this.ID = checkupResult.ID;
        this.Ex_RequestLotData_ID = checkupResult.Ex_RequestLotData_ID;
//        this.Committee_ID = checkupResult.Committee_ID;
//        this.EmployeeId = checkupResult.EmployeeId;
        this.CommitteeResultType_ID = checkupResult.CommitteeResultType_ID;
        this.Weight = checkupResult.Weight;
        this.QuantitySize = checkupResult.QuantitySize;
        this.Notes = checkupResult.Notes;
        this.Item_ID = checkupResult.Item_ID;
        this.Item__OrderID = checkupResult.Item__OrderID;
        this.Date=checkupResult.Date;
        this.Result_injuryID=checkupResult.Result_injuryID;
        this.Latitude=checkupResult.Latitude;
        this.Longitude=checkupResult.Longitude;

    }

    public Checkup_Result_Model(Checkup_Result checkupResult) {
        this.ID=0;
        this.Ex_RequestLotData_ID =checkupResult.getlot_ID();
//        this.Committee_ID = checkupResult.getCommittee_ID();
//        this.EmployeeId = checkupResult.getEmployeeId();
        this.CommitteeResultType_ID = checkupResult.getResult_ID();
        this.Weight = checkupResult.getWeight_kelo()+(checkupResult.getWeight_ten()/1000)+(checkupResult.getWeight_gram()*1000);
        this.QuantitySize = checkupResult.getCount();
        this.Notes = checkupResult.getComment();
        this.Latitude=checkupResult.getLatitude();
        this.Longitude=checkupResult.getLongitude();
        if(checkupResult.getFamily_ID()!= 0)
        {
            this.Item_ID = checkupResult.getFamily_ID();
            this.Item__OrderID =68;
        }
        else if(checkupResult.getFamily_ID()==0 && checkupResult.getOrder_ID()!=0)
        {
            this.Item_ID = checkupResult.getOrder_ID();
            this.Item__OrderID =67;
        }
        else if(checkupResult.getOrder_ID()==0 && checkupResult.getPhylum_ID() !=0)
        {
            this.Item_ID = checkupResult.getPhylum_ID();
            this.Item__OrderID =66;
        }
        else if(checkupResult.getPhylum_ID()==0 && checkupResult.getKingdom_ID()!=0)
        {
            this.Item_ID = checkupResult.getKingdom_ID();
            this.Item__OrderID =65;
        }
        else if(checkupResult.getKingdom_ID()==0)
        {
            this.Item_ID = 0;
            this.Item__OrderID = 0;
        }
        this.Result_injuryID= (byte) checkupResult.getResult_injury();
        this.Date=checkupResult.getCheckup();
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

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getEx_RequestLotData_ID() {
        return Ex_RequestLotData_ID;
    }

    public void setEx_RequestLotData_ID(long ex_RequestLotData_ID) {
        Ex_RequestLotData_ID = ex_RequestLotData_ID;
    }

//    public long getCommittee_ID() {
//        return Committee_ID;
//    }
//
//    public void setCommittee_ID(long committee_ID) {
//        Committee_ID = committee_ID;
//    }
//
//    public long getEmployeeId() {
//        return EmployeeId;
//    }
//
//    public void setEmployeeId(long employeeId) {
//        EmployeeId = employeeId;
//    }

    public int getCommitteeResultType_ID() {
        return CommitteeResultType_ID;
    }

    public void setCommitteeResultType_ID(int committeeResultType_ID) {
        CommitteeResultType_ID = committeeResultType_ID;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(float weight) {
        Weight = weight;
    }

    public int getQuantitySize() {
        return QuantitySize;
    }

    public void setQuantitySize(int quantitySize) {
        QuantitySize = quantitySize;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public int getItem_ID() {
        return Item_ID;
    }

    public void setItem_ID(int item_ID) {
        Item_ID = item_ID;
    }

    public int getItem__OrderID() {
        return Item__OrderID;
    }

    public void setItem__OrderID(int item__OrderID) {
        Item__OrderID = item__OrderID;
    }

    public byte getResult_injuryID() {
        return Result_injuryID;
    }

    public void setResult_injuryID(byte result_injuryID) {
        Result_injuryID = result_injuryID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
