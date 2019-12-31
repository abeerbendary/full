package com.example.abeer.quarantine.model;

import java.util.ArrayList;

public class Committe_Dto_Array  {
    ArrayList<Object> Committe_Dto=new ArrayList<>();

    public Committe_Dto_Array(ArrayList<Object> committe_Dto) {
        Committe_Dto = committe_Dto;
    }

    public Committe_Dto_Array() {

    }

    public ArrayList<Object> getCommitte_Dto() {
        return Committe_Dto;
    }

    public void setCommitte_Dto(ArrayList<Object> committe_Dto) {
        Committe_Dto = committe_Dto;
    }
}
