package com.example.abeer.quarantine.model;

import java.util.ArrayList;

public class Committe_Dto_object {

//    "PlantProductID":123,"IsPlant":123,"Committee_ID":50024,"EmployeeId":31810,
//            "ComResult":[
     long Committee_ID;
     long EmployeeId;
     short IsPlant;
     long PlantProductID;
     ArrayList<Object> ComResult=new ArrayList<>();

    public Committe_Dto_object() {

    }

    public Committe_Dto_object(Committe_Dto_object committe_dto_object) {
        Committee_ID = committe_dto_object.Committee_ID;
        EmployeeId = committe_dto_object.EmployeeId;
        IsPlant = committe_dto_object.IsPlant;
        PlantProductID = committe_dto_object.PlantProductID;
        ComResult = committe_dto_object.ComResult;
    }

    public long getCommittee_ID() {
        return Committee_ID;
    }

    public void setCommittee_ID(long committee_ID) {
        Committee_ID = committee_ID;
    }

    public long getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(long employeeId) {
        EmployeeId = employeeId;
    }

    public short getIsPlant() {
        return IsPlant;
    }

    public void setIsPlant(short isPlant) {
        IsPlant = isPlant;
    }

    public long getPlantProductID() {
        return PlantProductID;
    }

    public void setPlantProductID(long plantProductID) {
        PlantProductID = plantProductID;
    }

    public ArrayList<Object> getComResult() {
        return ComResult;
    }

    public void setComResult(ArrayList<Object> comResult) {
        ComResult = comResult;
    }
}
