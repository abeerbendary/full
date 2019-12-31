package com.example.abeer.quarantine.remote;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.abeer.quarantine.activity.LogIn;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by abeer on 27/03/2019.
 */

public class ApiCall {
    public static String UrlLogin = "/api/Login_out";
    public static String UrlLogoutList="/api/Login_out?IsList=true";
    public static String UrlPlantKingdom = "/api/PlantKingdom?List=2";
    public static String UrlPlantPhylum = "/api/PhylumSubphylum?List=2&Kingdom_ID=";
    public static String UrlPlantOrder = "/api/PlantOrder?List=2&Phylum_ID=";
    public static String UrlPlantFamily = "/api/PlantFamily?List=2&Order_ID=";
    public static String UrlIm_ProcedureType = "/api/Im_ProcedureType?List=2";
    public static String UrlCommitteeResultType = "/api/CommitteeResultType?List=2";
    public static String UrlLabName = "/api/AnalysisLabType?common=1 &&AnalysisType=";
    public static String UrlTreatmentMethod = "/api/TreatmentMethod?TreatmentType_ID=";
    public static String UrlTreatmentMaterial = "/api/TreatmentMaterial?TreatmentType_ID=";
    public static String AnalysisType = "/api/AnalysisType?AddEdit= 1";
    public static String UrlTreatmentType = "/api/TreatmentType?AddEdit=1";
    public static String UrlTreatmentCompany = "/api/Company_National?List=1&IsTreatment=true";
    public static String UrlTreatmentPlace = "/api/Station?Accridated=1";
    public static String UrlDetailsCheckRequest = "/api/Export_CheckRequest?CheckRequest_Id=";
    public static String UrlListOfChipment = "/api/ExportImportActivity?";
    public static String UrlSavemultprocess = "/API/V2/CommitteeResult?IsFinishedAll=";
    public static String UrlFarmResult  ="/api/FarmCommittee";
    public static String UrlFarmResultconfirm="/api/FarmCommittee?Is_Confirm=true";
    public static String UrlFarmDataconfirm="/api/FarmCommittee?FramCommitte_Id=";
    public static String UrlStationResult  ="/api/stationCommittee";
    public static String UrlStationResultconfirm="/api/stationCommittee?Is_Confirm=true";
    public static String UrlStationDataconfirm="/api/stationCommittee?StationCommitte_Id=";
}

