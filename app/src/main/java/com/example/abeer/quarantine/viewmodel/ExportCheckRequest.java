package com.example.abeer.quarantine.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;

import com.example.abeer.quarantine.BR;
import com.example.abeer.quarantine.model.RequestTreatmentData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ExportCheckRequest extends BaseObservable {
    //
//      "$id": "1",
//              "checkRequest_Id": 40034,
//              "CheckRequest_Number": "132472019",
//              "Committee_Type_Name": "لجنة فحص",
//              "Check_Date": null,
//              "RequestCommittee_Status": "لم يتم الفحص",
//              "Committee_ID": 40010
   // RequestCommittee_Status_Id=false
    //
    //IsExport
////////////////

//    {
//
//            "Emp_Committe": "<fn_CommitteEmployee_GetData Employee_Id=\"10614\" FullName=\"حسين عيد سليمان محمد\" ISAdmin=\"0\" /><fn_CommitteEmployee_GetData Employee_Id=\"10614\" FullName=\"حسين عيد سليمان محمد\" ISAdmin=\"0\" /><fn_CommitteEmployee_GetData Employee_Id=\"14444\" FullName=\"حسين عيد سليمان محمد\" ISAdmin=\"0\" />" +
//            "<fn_CommitteEmployee_GetData Employee_Id=\"23824\" FullName=\"حسين عيد سليمان محمد\" ISAdmin=\"0\" />" +
//            "<fn_CommitteEmployee_GetData Employee_Id=\"31810\" FullName=\"محمد السيد محمد حجاب\" ISAdmin=\"1\" />",
//
//
//            "Request_Treatment": "<root>" +
//            "<ecd checkRequest_Id=\"40035\" IsExport=\"1\" />" +
//            "<ecd checkRequest_Id=\"40056\" IsExport=\"1\" />" +
//            "<ecd checkRequest_Id=\"40079\" IsExport=\"1\" />" +
//            "<ecd checkRequest_Id=\"40083\" IsExport=\"1\" />" +
//            "<ecd checkRequest_Id=\"40084\" IsExport=\"1\" />" +
//            "<ecd checkRequest_Id=\"40085\" IsExport=\"1\" />" +
//            "<ecd checkRequest_Id=\"50087\" IsExport=\"1\" />" +
//            "<ecd checkRequest_Id=\"50088\" IsExport=\"1\" />" +
//            "<ecd checkRequest_Id=\"50096\" IsExport=\"1\" />" +
//            "<ecd checkRequest_Id=\"90116\" IsExport=\"1\" />" +
//            "<ecd checkRequest_Id=\"130178\" IsExport=\"1\" />" +
//            "<ecd checkRequest_Id=\"130209\" IsExport=\"1\" />" +
//            "<ecd checkRequest_Id=\"130211\" IsExport=\"1\" />" +
//            "</root>",
//
//
//            "Request_Treatment_Data": {
//                 "$id": "2",
//                "row_num": 1,
//                "checkRequest_Id": 40035,
//                "IsExport": 1,
//                "Item_Data": "<_x0040_Item_Data Item_number=\"57\" Item_Type=\"33\" Item_Id=\"1036\" Item_Name=\"88\" Scientific_Name=\"-----------\" Item_Cat_Name=\"-----------\" Item_Strain=\"-----------\" ItemStatus=\"بند6\" ItemStatus_ID=\"6\" ItemPurpose=\"الزراعة\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                             "<_x0040_Item_Data Item_number=\"58\" Item_Type=\"33\" Item_Id=\"1033\" Item_Name=\"12\" Scientific_Name=\"-----------\" Item_Cat_Name=\"-----------\" ItemPartTypeName=\"23\" Item_Strain=\"-----------\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"111\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" /> " +
//                             "<_x0040_Item_Data Item_number=\"59\" Item_Type=\"33\" Item_Id=\"1033\" Item_Name=\"12\" Scientific_Name=\"-----------\" Item_Cat_Name=\"-----------\" ItemPartTypeName=\"3333\" Item_Strain=\"-----------\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"33\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                             "<_x0040_Item_Data Item_number=\"60\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"8598\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"البحث العلمي\" Item_ShortName=\"الاسم المختصر 4\" Purpose_ID=\"4\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                             "<_x0040_Item_Data Item_number=\"61\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"88\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"البحث العلمي\" Item_ShortName=\"الاسم المختصر 5&#x9;\" Purpose_ID=\"4\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                             "<_x0040_Item_Data Item_number=\"62\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"1111\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"111\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                             "<_x0040_Item_Data Item_number=\"63\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"222\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"22\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                             "<_x0040_Item_Data Item_number=\"64\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"2222\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"الاسم المختصر 3\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                             "<_x0040_Item_Data Item_number=\"65\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"44\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"50\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                             "<_x0040_Item_Data Item_number=\"66\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"44\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"الاسم المختصر 7&#x9;\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                             "<_x0040_Item_Data Item_number=\"67\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"4444\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"4444\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                             "<_x0040_Item_Data Item_number=\"68\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"4444\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"الاسم المختصر 6&#x9;\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                             "<_x0040_Item_Data Item_number=\"69\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"555\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"555\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                             "<_x0040_Item_Data Item_number=\"70\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"5555\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"55\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                             "<_x0040_Item_Data Item_number=\"71\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"667765\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"الاسم المختصر 2\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                             "<_x0040_Item_Data Item_number=\"72\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"76876876\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"عغهغهغف\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                             "<_x0040_Item_Data Item_number=\"73\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"99\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"99\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />",
//                "Analysis_Total": 0,
//                "Treatment_Total": 0,
//                "Check_Total": 4
//               }
//    }
    //////////////////

    String $id;
    long checkRequest_Id;
    String CheckRequest_Number;
    String Committee_Type_Name;
    String Check_Date;
    String RequestCommittee_Status;
    int RequestCommittee_Status_Id;
    long Committee_ID;
    String BarCode;
    int Row_Num;
    int IsExport;
    int Committee_Type_Id;
   public String  Emp_Committe;
    String Request_Treatment;
    RequestTreatmentData Request_Treatment_Data;


    public ExportCheckRequest() {
    }

    public ExportCheckRequest(ExportCheckRequest exportCheckRequest) {
        this.$id = exportCheckRequest.$id;
        checkRequest_Id=exportCheckRequest.checkRequest_Id;
        CheckRequest_Number = exportCheckRequest.CheckRequest_Number;
        this.Committee_Type_Name = exportCheckRequest.Committee_Type_Name;
        CheckRequest_Number = exportCheckRequest.CheckRequest_Number;
        RequestCommittee_Status = exportCheckRequest.RequestCommittee_Status;
        RequestCommittee_Status_Id=exportCheckRequest.RequestCommittee_Status_Id;
        Committee_ID=exportCheckRequest.Committee_ID;
        BarCode=exportCheckRequest.BarCode;
        Committee_Type_Id=exportCheckRequest.Committee_Type_Id;
        Row_Num=exportCheckRequest.Row_Num;
        Emp_Committe=exportCheckRequest.Emp_Committe;
        Request_Treatment=exportCheckRequest.Request_Treatment;
        IsExport =exportCheckRequest.IsExport;
        Request_Treatment_Data=new RequestTreatmentData(exportCheckRequest.Request_Treatment_Data);
//        Request_Treatment_Data=exportCheckRequest.Request_Treatment_Data;
        notifyPropertyChanged(BR.checkRequest_Number);

    }
    public String get$id() {
        return $id;
    }

    public long getCheckRequest_Id() {
        return checkRequest_Id;
    }

    public void setCheckRequest_Id(long checkRequest_Id) {
        this.checkRequest_Id = checkRequest_Id;
    }

    public RequestTreatmentData getRequest_Treatment_Data() {
        return Request_Treatment_Data;
    }

    public void setRequest_Treatment_Data(RequestTreatmentData request_Treatment_Data) {
        Request_Treatment_Data = request_Treatment_Data;
    }

    public HashMap Handel_Request_Treatment()throws JSONException{
        JSONObject jsonObj = new JSONObject();
        jsonObj = XML.toJSONObject(Request_Treatment);
        Object f = null;
        f = jsonObj.get("dbo.check_request_GetHasTreatment");
        HashMap<String,Integer> hashMap =new HashMap<>();
        hashMap.put("treatment_data",Integer.valueOf((((JSONObject) f).get("treatment_data").toString())));
        hashMap.put("sample_data",Integer.valueOf(((JSONObject) f).get("sample_data").toString()));
        hashMap.put("request_data",Integer.valueOf(((JSONObject) f).get("request_data").toString()));
        //       HashMap<String,Integer>[] ArrayHash=new HashMap[2];
//        ArrayHash[0].put("treatment_data",Integer.valueOf((((JSONObject) f).get("treatment_data").toString())));
//        ArrayHash[1].put("sample_data",Integer.valueOf(((JSONObject) f).get("sample_data").toString()));
//
//        /////
//        List<HashMap<String,Integer>> hashMaps=new ArrayList<>();
//        hashMaps.add(0,new HashMap<String, Integer>().put("treatment_data",Integer.valueOf((((JSONObject) f).get("treatment_data").toString()))));
        //    return  ArrayHash;
        return hashMap;
    }
   @Bindable
    public int getIsExport() {
        return IsExport;
    }

    public void setIsExport(int isExport) {
        IsExport = isExport;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }
    @Bindable
    public String getCheckRequest_Number() {
        return CheckRequest_Number;
    }
    @Bindable
    public String getCommitte_Type_Name() {
        return Committee_Type_Name;
    }

    public String getCheck_Date() {
        return Check_Date;
    }

    public void setCheck_Date(String check_Date) {
        Check_Date = check_Date;
    }
    @Bindable
    public String getRequestCommittee_Status() {
//        HashMap<String,Integer>hashMap=new HashMap<>();
//        try {
//            hashMap.putAll(Handel_Request_Treatment());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        RequestTreatmentData requestTreatmentData=new RequestTreatmentData(getRequest_Treatment_Data());
//        int count=0;
//        String text="";
//       if(RequestCommittee_Status_Id==0){

//           text=RequestCommittee_Status+"\n";
//           count=requestTreatmentData.getCheck_Total();
//           if(count>0){
//            text+= count+" فحص"+"/";
//               //text+= "1 فحص"+"/";
//           }
//           count=requestTreatmentData.getTreatment_Total();
//           if(count>0){
//            text+=count+"معالجة"+"/";
//           }
//           count=requestTreatmentData.getAnalysis_Total();
//           if(count>0){
//               text+=count+"سحب عينة";
//           }

//         return text;
//       }else {
           return RequestCommittee_Status;
//       }
    }

    public int getCommittee_Type_Id() {
        return Committee_Type_Id;
    }

    public void setCommittee_Type_Id(int committee_Type_Id) {
        Committee_Type_Id = committee_Type_Id;
    }

    public void setCheckRequest_Number(Long CheckRequest_Number) {
        CheckRequest_Number = CheckRequest_Number;
      notifyPropertyChanged(BR.checkRequest_Number);
    }

    @Bindable
    public String getRequest_Treatment() {
     String text="";
    HashMap<String,Integer>hashMap=new HashMap<>();
        try {
            hashMap.putAll(Handel_Request_Treatment());
        } catch (JSONException e) {
            e.printStackTrace();
        }
      //  Textsss+=""
        // return Request_Treatment;
        return text;
    }

    public void setRequest_Treatment(String request_Treatment) {
        Request_Treatment = request_Treatment;
      //  notifyPropertyChanged(BR.Request_Treatment);
    }

    public void setCommitte_Type_Name(String  Committe_Type_Name) {
        Committe_Type_Name =  Committe_Type_Name;
          notifyPropertyChanged(BR.CommitteeResultType);
    }

    public void setRequestCommittee_Status(String RequestCommittee_Status) {
        RequestCommittee_Status = RequestCommittee_Status;
      //    notifyPropertyChanged(BR.lot_Number);
    }

    public long getCommittee_ID() {
        return Committee_ID;
    }

    public void setCommittee_ID(long committee_ID) {
        Committee_ID = committee_ID;
    }

    public void setCheckRequest_Number(String checkRequest_Number) {
        CheckRequest_Number = checkRequest_Number;
    }

    public String getCommittee_Type_Name() {
        return Committee_Type_Name;
    }

    public void setCommittee_Type_Name(String committee_Type_Name) {
        Committee_Type_Name = committee_Type_Name;
    }

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String barCode) {
        BarCode = barCode;
    }

    @Bindable
    public int getRequestCommittee_Status_Id() {
        return RequestCommittee_Status_Id;
    }

    public void setRequestCommittee_Status_Id(int requestCommittee_Status_Id) {
        RequestCommittee_Status_Id = requestCommittee_Status_Id;
       // notifyPropertyChanged(BR.RequestCommittee_Status_Id);
    }

    public int getRow_Num() {
        return Row_Num;
    }

    public void setRow_Num(int row_Num) {
        Row_Num = row_Num;
    }


    public ArrayList<Emp_Committe> Handle_Emp_Committe() throws JSONException {
        ArrayList<Emp_Committe> emp_committeArrayList =new ArrayList<>();
      //  Emp_Committe emp_committe;
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj = XML.toJSONObject(Emp_Committe);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Object f = null;
        try {
            f = jsonObj.get("fn_CommitteEmployee_GetData");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (f instanceof JSONArray) {
            for (int i = 0; i < ((JSONArray) f).length(); i++) {
                emp_committeArrayList.add(new Emp_Committe(((JSONArray) f).getJSONObject(i).get("Employee_Id").toString(),((JSONArray) f).getJSONObject(i).get("ISAdmin").toString(),
                        ((JSONArray) f).getJSONObject(i).get("FullName").toString(),((JSONArray) f).getJSONObject(i).get("LoginName").toString(),((JSONArray) f).getJSONObject(i).get("Password").toString(),((JSONArray) f).getJSONObject(i).get("EmpToken").toString()));
            }

       }else {
            emp_committeArrayList.add(new Emp_Committe(((JSONObject) f).get("Employee_Id").toString(),((JSONObject) f).get("ISAdmin").toString(),((JSONObject) f).get("FullName").toString(),((JSONObject) f).get("LoginName").toString(),((JSONObject) f).get("Password").toString(),((JSONObject) f).get("EmpToken").toString()));
        }
        return  emp_committeArrayList;
    }


    @Bindable
    public String getEmp_Committe() {
        ///////////////////call function Handle_Emp_Committe get null///////////////////
        String data="";
        ArrayList<Emp_Committe>emp_committeArray = new ArrayList<>();
      //  List<Emp_Committe>emp_committe= new ArrayList<>();
        try {
            emp_committeArray.addAll( Handle_Emp_Committe());
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        try {
//         //   emp_committeArray = new ArrayList<>();
//          //  emp_committeArray.addAll(Handle_Emp_Committe());
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        for (int i=0;i<emp_committeArray.size();i++)
        {
            data+= emp_committeArray.get(i).FullName+"\n";
        }
        return data;

///////////////////////////////////////////////////////

//        JSONObject jsonObj = new JSONObject();
//  //      HashMap<Integer, String> hashMap = null;
//        try {
//            jsonObj = XML.toJSONObject(Emp_Committe);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        String data="";
//        Object f = null;
//        try {
//            f = jsonObj.get("com");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        if (f instanceof JSONArray) {
//            // hashMap = new HashMap<>();
//            for (int i = 0; i < ((JSONArray) f).length(); i++) {
//                //لو هعرض في Listview مش textview
//               // hashMap.put(Integer.parseInt(((JSONArray) f).getJSONObject(i).get("Employee_Id").toString()), ((JSONObject) ((JSONArray) f).getJSONObject(i).get("u")).get("FullName").toString());
//                try {
//                  //  String s=;
//                    data+=((JSONObject) ((JSONArray) f).getJSONObject(i).get("u")).get("FullName").toString()+"\n";
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//     //   ArrayList<String> mData=new ArrayList<>();
//    //    mData.addAll(hashMap.values());
//        return data;

    }

    public void setEmp_Committe(String emp_Committe) {
        Emp_Committe = emp_Committe;
    }
}