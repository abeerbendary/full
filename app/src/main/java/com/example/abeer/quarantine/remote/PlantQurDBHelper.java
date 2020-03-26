package com.example.abeer.quarantine.remote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.abeer.quarantine.functions.Public_function;
import com.example.abeer.quarantine.model.RequestTreatmentData;
import com.example.abeer.quarantine.viewmodel.DataForCardItems;
import com.example.abeer.quarantine.viewmodel.Emp_Committe;
import com.example.abeer.quarantine.viewmodel.ExportCheckRequest;
import com.example.abeer.quarantine.viewmodel.ItemData;
import com.example.abeer.quarantine.viewmodel.ListItemDataDetail;
import com.example.abeer.quarantine.viewmodel.livingobjects.ItemData_LivingObject;
import com.example.abeer.quarantine.viewmodel.plantProduct.ItemData_PlantProduct;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PlantQurDBHelper {

    private Engine engine;
    private SQLiteDatabase database;
    Long ID_RequestCommittee;
    long ID_Emp;
    Long ID_RequestCommitteeEmployee;
    ContentValues values;
    private static final String ENCODING_SETTING = "PRAGMA encoding ='windows-1256'";

    public PlantQurDBHelper(Context context) {
        engine = new Engine(context);
//        database.execSQL(ENCODING_SETTING);
    }

    public boolean Insert_CommitteeRequestEmployee(Context context, ExportCheckRequest exportCheckRequest, ArrayList<Emp_Committe> emp_committeArrayList) {
        //used it to insert committee request and employee and employee_requestcommittee
        boolean Result;
        Cursor cursor = engine.getReadableDatabase().rawQuery("select _id from RequestCommittee where _id=" + exportCheckRequest.getCheckRequest_Id(), null);
        int count = cursor.getCount();
        if (count == 0) {
            ID_RequestCommittee = Insert_CommitteeReques(exportCheckRequest);
        } else {
            ID_RequestCommittee = exportCheckRequest.getCheckRequest_Id();
        }

        if (ID_RequestCommittee != -1) {
            for (Emp_Committe Emp_Committe : emp_committeArrayList) {
                cursor = engine.getReadableDatabase().rawQuery("select _id from Employee where _id=" + Emp_Committe.getEmployee_Id(), null);
                count = cursor.getCount();
                if (count == 0) {
                    ID_Emp = Insert_Employee(Emp_Committe);
                } else {
                    ID_Emp = Emp_Committe.getEmployee_Id();
                }
                if (ID_Emp != -1) {
                    cursor = engine.getReadableDatabase().rawQuery("select _id from RequestCommitteeEmployee where EmployeeId=" + ID_Emp + " and checkRequestId=" + ID_RequestCommittee, null);
                    count = cursor.getCount();
                    if (count == 0) {
                        ID_RequestCommitteeEmployee = Insert_Emp_request(Emp_Committe);
                    } else {

                    }

                }
            }
            Result = true;
        } else {
            Result = false;
//            Toast.makeText(context, " حدث خطا ما ", Toast.LENGTH_SHORT).show();
        }
        return Result;
    }

    public long Insert_CommitteeReques(ExportCheckRequest exportCheckRequest) {
        database = engine.getWritableDatabase();
        values = new ContentValues();
        RequestTreatmentData requestTreatmentData = new RequestTreatmentData();
        requestTreatmentData = exportCheckRequest.getRequest_Treatment_Data();
        int treatment_data = requestTreatmentData.getTreatment_Total();
        int sample_data = requestTreatmentData.getAnalysis_Total();
//        int total = 0;
//        int Check_data = requestTreatmentData.getCheck_Total();
//        if (treatment_data == -1) {
//            treatment_data = 0;
//        }
//        if (sample_data == -1) {
//            sample_data = 0;
//        }
//        if (Check_data == -1) {
//            Check_data = 0;
//        }
//        total = treatment_data + sample_data + Check_data;

        values.put("_id", exportCheckRequest.getCheckRequest_Id());
        values.put("CheckRequest_Number", exportCheckRequest.getCheckRequest_Number());
        values.put("BarCode", exportCheckRequest.getBarCode());
        values.put("Committee_Type_Id", exportCheckRequest.getCommittee_Type_Id());
//        values.put("Total_process", total);
        values.put("checkdate", exportCheckRequest.getCheck_Date());
        values.put("IsExport", exportCheckRequest.getIsExport());
        values.put("Committee_ID", exportCheckRequest.getCommittee_ID());
        values.put("RequestCommittee_Status_Id", exportCheckRequest.getRequestCommittee_Status_Id());
        JSONObject json = null;
        try {
            json = new JSONObject(new Gson().toJson(exportCheckRequest, ExportCheckRequest.class));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        values.put("TextJson", json.toString());
        ID_RequestCommittee = database.insert("RequestCommittee", null, values);
        database.close();
        return ID_RequestCommittee;
    }

    public void Update_OneColumeAnyTable(String NameColumn, String TableName, String Value, String NameColumnCondation, String valuecondition) {
        database = engine.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NameColumn, Value);
        database.update(TableName, values, NameColumnCondation + "=" + valuecondition, null);
        database.close();
    }

    public long Insert_Employee(Emp_Committe Emp_Committe) {
        database = engine.getWritableDatabase();
        values = new ContentValues();
        values.put("_id", Emp_Committe.getEmployee_Id());
        values.put("Name", Emp_Committe.getFullName());
        values.put("Password", Emp_Committe.getPassword());
        values.put("Username", Emp_Committe.getLoginName());
        ID_Emp = database.insert("Employee", null, values);
        database.close();

        return ID_Emp;
    }

    public long Insert_Emp_request(Emp_Committe Emp_Committe) {
        database = engine.getWritableDatabase();
        values = new ContentValues();
        values.put("EmployeeId", ID_Emp);
        values.put("checkRequestId", ID_RequestCommittee);
        values.put("Isadmin", Emp_Committe.getISAdmin());
        ID_RequestCommitteeEmployee = database.insert("RequestCommitteeEmployee", null, values);
//       if(Emp_Committe.getISAdmin()==0) {

        Long ID_LoginEmployee = Insert_LoginEmployee_noLoginDate(Emp_Committe);
//       }else {
//           Insert_LoginEmployee(Emp_Committe);
//       }
        database.close();
        return ID_RequestCommitteeEmployee;
    }

    public long Insert_LoginEmployee_noLoginDate(Emp_Committe Emp_Committe) {
        database = engine.getWritableDatabase();
        values = new ContentValues();
        values.put("Emp_ID", Emp_Committe.getEmployee_Id());
        values.put("EmpToken", Emp_Committe.getAccessToken());
        long ID_LoginEmployee = database.insert("Login_Employee", null, values);
        database.close();
        return ID_LoginEmployee;
    }

    public void Insert_LoginEmployee(Emp_Committe Emp_Committe) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String checkdate = String.valueOf(formatter.format(date));
        database = engine.getWritableDatabase();
        values = new ContentValues();
        values.put("Emp_ID", Emp_Committe.getEmployee_Id());
        values.put("Login_Date", checkdate);
        values.put("EmpToken", Emp_Committe.getAccessToken());
        database.insert("Login_Employee", null, values);
        database.close();
//        return ID_LoginEmployee;
    }

    public List<ExportCheckRequest> GetExportCheckRequest(long EmployeeId, String checkdate) {
        Cursor cursor = engine.getReadableDatabase().rawQuery("select TextJson from  RequestCommittee where checkdate='" + checkdate + "'and  _id in (select checkRequestId from  RequestCommitteeEmployee where EmployeeId='" + EmployeeId + "')", null);
        int count = cursor.getCount();
        List<ExportCheckRequest> List_ExportCheckRequest = null;
        if (count > 0) {


            Gson gson = new Gson();
            List_ExportCheckRequest = new ArrayList<>(count);
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                List_ExportCheckRequest.add(gson.fromJson(String.valueOf(cursor.getString(0)), ExportCheckRequest.class));
            }
        }
        return List_ExportCheckRequest;
    }

    public Long insertLogin_LoginEmployee_for_user(String username, String password) {
        Long Emp_ID = getID_Emp(username, password);
        if (Emp_ID != 0) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            String checkdate = String.valueOf(formatter.format(date));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String check = String.valueOf(format.format(date));
            Cursor cursor = engine.getReadableDatabase().rawQuery("select L.EmpToken,E.checkRequestId from  RequestCommitteeEmployee E INNER JOIN  Login_Employee  L ON E.EmployeeId==L.Emp_ID where EmployeeId=" + Emp_ID + " and Isadmin=0 and  checkRequestId in (select _id from  RequestCommittee where checkdate=" + check + ")", null);
            int count = cursor.getCount();
//            if (count > 0) {
            database = engine.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Emp_ID", Emp_ID);
            values.put("EmpToken", cursor.getString(1));
            values.put("Login_Date", checkdate);
            database.insert("Login_Employee", null, values);
            database.close();
//            }
        }
        return Emp_ID;
    }

    public Long insertLogin_LoginEmployee(String username, String password) {
        Long Emp_ID = getID_Emp(username, password);
        if (Emp_ID != 0) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            String checkdate = String.valueOf(formatter.format(date));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String check = String.valueOf(format.format(date));
            Cursor cursor = engine.getReadableDatabase().rawQuery("select * from  Login_Employee where LogOut_Date =0 and Emp_ID=" + Emp_ID + " and Login_Date=0", null);
            int count = cursor.getCount();
            if (count > 0) {
                database = engine.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("Login_Date", checkdate);
                database.update("Login_Employee", values, "Emp_ID=" + Emp_ID, null);
                database.close();
            } else {
                cursor = engine.getReadableDatabase().rawQuery("select EmpToken from  Login_Employee where LogOut_Date !=0 and Emp_ID=" + Emp_ID, null);
                count = cursor.getCount();
                if (count > 0) {
                    cursor.moveToFirst();
                    database = engine.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("Login_Date", checkdate);
                    values.put("Emp_ID", Emp_ID);
                    values.put("EmpToken", cursor.getString(0));
                    database.insert("Login_Employee", null, values);
                    database.close();
                } else {
                    Emp_ID = Long.valueOf(0);
                }
            }


        }
        return Emp_ID;
    }

    public void ubdateLogout_LoginEmployee(long EmployeeId) {
        Cursor cursor = engine.getReadableDatabase().rawQuery("select * from  Login_Employee where Emp_ID=" + EmployeeId, null);
        int count = cursor.getCount();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String checkdate = String.valueOf(formatter.format(date));
        JSONObject jsonObject = null;
        if (count > 0) {
            {
                jsonObject = new JSONObject();
                cursor.moveToNext();
                try {
                    jsonObject.put("Emp_ID", cursor.getLong(1));
                    jsonObject.put("Login_Date", cursor.getString(2));
                    jsonObject.put("LogOut_Date", checkdate);
                    jsonObject.put("EmpToken", cursor.getString(4));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        database = engine.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("LogOut_Date", checkdate);
        values.put("TextJson", jsonObject.toString());
        database.update("Login_Employee", values, "Emp_ID=" + EmployeeId, null);
        database.close();
    }

    public long getID_Emp(String username, String password) {
        Cursor cursor = engine.getReadableDatabase().rawQuery("select _id from Employee where username='" + username + "' and password=" + password, null);
        int count = cursor.getCount();
        Long Emp_ID = null;
        if (count > 0) {
            cursor.moveToNext();
            Emp_ID = cursor.getLong(0);
            return Emp_ID;
        }
        return 0;
    }

    public void InsertFarmAndStationTable(long ID_RequestCommittee, String JsonResult, String JsonConfirm) {
        database = engine.getWritableDatabase();
        values = new ContentValues();
        values.put("RequestCommittee_ID", ID_RequestCommittee);
        values.put("JsonResult", JsonResult);
        values.put("JsonConfirm", JsonConfirm);
        database.insert("FarmAndStation", null, values);
        database.close();
    }

    public void UpdateFarmAndStationTable(long ID_RequestCommittee, String JsonConfirmResult) {
        database = engine.getWritableDatabase();
        values = new ContentValues();
        values.put("JsonConfirmResult", JsonConfirmResult);
        database.update("FarmAndStation", values, "RequestCommittee_ID=" + ID_RequestCommittee, null);
        database.close();
    }

    public String GetConfirmDataFarmStation(long ID_RequestCommittee) {
        Cursor cursor = engine.getReadableDatabase().rawQuery("select JsonConfirm from FarmAndStation where RequestCommittee_ID=" + ID_RequestCommittee, null);
        int count = cursor.getCount();
        if (count > 0) {
            cursor.moveToNext();
            String JsonConfirm = cursor.getString(0);
            return JsonConfirm;
        }
        return "";
    }

    public <T> void insertItemsforrequest_ItemData(long requset_id, List<T> items) {
        database = engine.getWritableDatabase();
        values = new ContentValues();
        for (T item : items) {
            //Ischeck DEFAULT 1 from sqlite

            values.put("_id", ((ItemData) item).getRequest_Item_ID());
            values.put("ProdPlant_ID", ((ItemData) item).getItem_Id());
            values.put("Isplant", ((ItemData) item).getItem_Type());
            values.put("Isanalysis", ((ItemData) item).getIsAnalysis());
            values.put("Istreatment", ((ItemData) item).getIsTreatment());
            values.put("Has_Result", ((ItemData) item).getHas_Result());
            values.put("RequestCommittee_ID", requset_id);
            values.put("Ischeck", 1);

            database.insert("Items", null, values);
        }
        database.close();
    }

    //return right count for type
    public HashMap<Integer, Boolean> InsertItemsforRequest_ItemData(long requset_id, JSONObject jsonObj) {
        HashMap<Integer, Boolean> counters = new HashMap();
        counters.put(4, false);
        counters.put(5, false);
        counters.put(16, false);
        counters.put(33, false);
        ListItemDataDetail listItemDataDetail = new ListItemDataDetail();
        Gson gson = new Gson();
        listItemDataDetail = gson.fromJson(jsonObj.toString(), ListItemDataDetail.class);
        int Size = listItemDataDetail._x0040_Item_Data.size();
        if (Size > 0) {
            database = engine.getWritableDatabase();
            values = new ContentValues();
            int s = Size - 1;
            int total_process = 0;
            for (int i = s; i >= 0; i--) {
                values.put("_id", listItemDataDetail._x0040_Item_Data.get(i).getRequest_Item_ID());
                values.put("ProdPlant_ID", listItemDataDetail._x0040_Item_Data.get(i).getItem_Id());
                values.put("Isplant", listItemDataDetail._x0040_Item_Data.get(i).getItem_Type());
                values.put("Isanalysis", listItemDataDetail._x0040_Item_Data.get(i).getIsAnalysis());
                values.put("Istreatment", listItemDataDetail._x0040_Item_Data.get(i).getIsTreatment());
                values.put("Has_Result", listItemDataDetail._x0040_Item_Data.get(i).getHas_Result());
                values.put("RequestCommittee_ID", requset_id);
                values.put("Ischeck", 1);
                values.put("IsExport", listItemDataDetail._x0040_Item_Data.get(i).getIsExport());
                JSONObject json = null;
                try {
                    json = new JSONObject(new Gson().toJson(listItemDataDetail._x0040_Item_Data.get(i), ItemData.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                values.put("JsonTextDetails", String.valueOf(json));
                values.put("Item_Name", listItemDataDetail._x0040_Item_Data.get(i).getItem_Name());
                values.put("Item_Cat_Name", listItemDataDetail._x0040_Item_Data.get(i).getItem_Cat_Name());
                total_process += listItemDataDetail._x0040_Item_Data.get(i).getIsAnalysis() +
                        listItemDataDetail._x0040_Item_Data.get(i).getIsTreatment() + 1;
                int x = 0;
                try {
                    x = (int) database.insert("Items", null, values);

                } catch (Exception ex) {
                    int nj = 0;
                    nj += 1;
                }

                if (x != -1) {
                    //plant
                    if (listItemDataDetail._x0040_Item_Data.get(i).getItem_Type().equals("4")) {
                        counters.put(4, true);
                    }
                    //product
                    else if (listItemDataDetail._x0040_Item_Data.get(i).getItem_Type().equals("5")) {
                        counters.put(5, true);
                    }
                    //alive
                    else if (listItemDataDetail._x0040_Item_Data.get(i).getItem_Type().equals("16")) {
                        counters.put(16, true);
                    }
                    //unlive
                    else if (listItemDataDetail._x0040_Item_Data.get(i).getItem_Type().equals("33")) {
                        counters.put(33, true);
                    }
                }

            }
            ContentValues total = new ContentValues();
            total.put("Total_process", total_process);
            database.update("RequestCommittee", total, "_id=" + requset_id, null);
            database.close();
        }

        return counters;
    }

    ////return  true or false  type
    public HashMap<Integer, Boolean> SelectTypeItemsforRequest_ItemData(long requset_id) {
        HashMap<Integer, Boolean> counters = new HashMap();
        counters.put(4, false);
        counters.put(5, false);
        counters.put(16, false);
        counters.put(33, false);
        Cursor cursor = engine.getReadableDatabase().rawQuery("select Isplant from Items where RequestCommittee_ID='" + requset_id + "'", null);
        int count = cursor.getCount();
        int Counter;
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                Counter = cursor.getInt(0);
                if (Counter == 4) {
                    counters.put(4, true);
                } else if (Counter == 5) {
                    counters.put(5, true);
                } else if (Counter == 16) {
                    counters.put(16, true);
                } else if (Counter == 33) {
                    counters.put(33, true);
                }
            }
        }

        return counters;
    }

    public <T> void insertItemsforrequest_ItemData_PlantProduct(long requset_id, List<T> items) {
        database = engine.getWritableDatabase();
        values = new ContentValues();
        for (T item : items) {
            //Ischeck DEFAULT 1 from sqlite
            values.put("_id", ((ItemData_PlantProduct) item).getRequest_Item_ID());
            values.put("ProdPlant_ID", ((ItemData_PlantProduct) item).getItem_Id());
            values.put("Isplant", ((ItemData_PlantProduct) item).getItem_Type());
            values.put("Isanalysis", ((ItemData_PlantProduct) item).getIsAnalysis());
            values.put("Istreatment", ((ItemData_PlantProduct) item).getIsTreatment());
            values.put("Has_Result", ((ItemData_PlantProduct) item).getHas_Result());
            values.put("RequestCommittee_ID", requset_id);
            values.put("Ischeck", 1);
            database.insert("Items", null, values);
        }
        database.close();
    }

    public <T> void insertItemsforrequest_ItemData_LivingObject(long requset_id, List<T> items) {
        database = engine.getWritableDatabase();
        values = new ContentValues();
        for (T item : items) {
            //Ischeck DEFAULT 1 from sqlite
            values.put("_id", ((ItemData_LivingObject) item).getRequest_Item_ID());
            values.put("ProdPlant_ID", ((ItemData_LivingObject) item).getItem_Id());
            values.put("Isplant", ((ItemData_LivingObject) item).getItem_Type());
            values.put("Isanalysis", ((ItemData_LivingObject) item).getIsAnalysis());
            values.put("Istreatment", ((ItemData_LivingObject) item).getIsTreatment());
            values.put("Has_Result", ((ItemData_LivingObject) item).getHas_Result());
            values.put("RequestCommittee_ID", requset_id);
            values.put("Ischeck", 1);
            database.insert("Items", null, values);
        }
        database.close();
    }

    public int Get_Data_For_Items_RetutnInt(String column_name, Long ItemID) {

        Cursor cursor = engine.getReadableDatabase().rawQuery("select " + column_name + " from Items where _id='" + ItemID + "'", null);
        int count = cursor.getCount();
        int counter = 0;
        if (count > 0) {
            cursor.moveToNext();
            counter = cursor.getInt(0);
        }
        return counter;
    }

    public HashMap<String, Integer> Get_Data_For_Items(Long ItemID) {

        Cursor cursor = engine.getReadableDatabase().rawQuery("select Ischeck,Isanalysis ,Istreatment,Has_Result,IsExport from Items where _id='" + ItemID + "'", null);
        int count = cursor.getCount();
        int counter = 0;
        if (count > 0) {
            HashMap<String, Integer> detailscounter = new HashMap<>();
            cursor.moveToNext();
            detailscounter.put("Ischeck", cursor.getInt(0));
            detailscounter.put("Isanalysis", cursor.getInt(1));
            detailscounter.put("Istreatment", cursor.getInt(2));
            detailscounter.put("Has_Result", cursor.getInt(3));
            //  detailscounter.put("Ischeck",cursor.getInt(0));
            return detailscounter;
        }
        return null;
    }

    public String Get_Data_For_ItemsReturnString(String column_name, Long ItemID) {

        Cursor cursor = engine.getReadableDatabase().rawQuery("select " + column_name + " from Items where _id='" + ItemID + "'", null);
        int count = cursor.getCount();
        String Text = "";
        if (count > 0) {
            cursor.moveToNext();
            Text = cursor.getString(0);
        }
        return Text;
    }

    public String Get_Data_for_Emp_working(String column_name, long Emp_ID) {
        Cursor cursor = engine.getReadableDatabase().rawQuery("select " + column_name + " from Login_Employee where Emp_ID='" + Emp_ID + "'and LogOut_Date= 0", null);
        int count = cursor.getCount();
        String data = "";
        if (count > 0) {
            cursor.moveToNext();
            data = cursor.getString(0);
        }
        return data;
    }

    public String Get_Data_for_RequestCommittee_working(String column_name, long RequestID) {
        Cursor cursor = engine.getReadableDatabase().rawQuery("select " + column_name + " from RequestCommittee where _id=" + RequestID, null);
        int count = cursor.getCount();
        String data = "";
        if (count > 0) {
            cursor.moveToNext();
            data = cursor.getString(0);
        }
        return data;
    }

    public boolean getISAdmin(long EmployeeId, long checkRequestId) {
        Cursor cursor = engine.getReadableDatabase().rawQuery("select Isadmin from  RequestCommitteeEmployee where  EmployeeId ='" + EmployeeId + "' and checkRequestId ='" + checkRequestId + "'", null);
        int count = cursor.getCount();
        int Isadmin = 0;
        if (count > 0) {
            cursor.moveToNext();
            Isadmin = cursor.getInt(0);
        }
        if (Isadmin == 0) {
            return false;
        } else {
            return true;
        }
    }

    public int Insert_WorkMangmentTable(String Result, String type) {
        database = engine.getWritableDatabase();
        values = new ContentValues();
        values.put("TextJsoinSendServer", Result);
        values.put("TypeProcess", type);
        int ID = (int) database.insert("WorkMangmentTable", null, values);
        database.close();

        return ID;
    }

    public int update_counterResultForAdmin(Context context, String ipadrass, String CountColumnName, long checkRequestId, long Items_ID, long EmpId, boolean IsFinishedAll) {

        int count = Integer.parseInt(Get_Data_for_RequestCommittee_working("Total_process", checkRequestId));
        if (count == 0) {
            JSONObject totaldata = new JSONObject();
            try {

                totaldata.put("Committe_Dto", Getdatatosendservercheckrequset(checkRequestId, EmpId));
                totaldata.put("SampleDto", Getdatatosendserversampledata(checkRequestId, EmpId));
                totaldata.put("Treatment_Dto", Getdatatosendservertreatmentdata(checkRequestId, EmpId));
                Insert_WorkMangmentTable(totaldata.toString(), "CommitteeResult");
                Public_function public_function = new Public_function();
                public_function.senddataonlinetoserverformoreprocess(totaldata, context, ipadrass + ApiCall.UrlSavemultprocess, IsFinishedAll);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public void update_counterResultForAdmin_New(Context context, String ipadrass, long checkRequestId, long EmpId, boolean IsFinishedAll) {

        JSONObject totaldata = new JSONObject();
        try {
            totaldata.put("Committe_Dto", Getdatatosendservercheckrequset(checkRequestId, EmpId));
            totaldata.put("SampleDto", Getdatatosendserversampledata(checkRequestId, EmpId));
            totaldata.put("Treatment_Dto", Getdatatosendservertreatmentdata(checkRequestId, EmpId));
            Insert_WorkMangmentTable(totaldata.toString(), "CommitteeResult");
            Public_function public_function = new Public_function();
            Delet_data_For_check(checkRequestId);
//            public_function.senddataonlinetoserverformoreprocess(totaldata, context, ipadrass + ApiCall.UrlSavemultprocess, IsFinishedAll,checkRequestId);
            public_function.senddataonlinetoserverformoreprocess(totaldata, context, ipadrass + ApiCall.UrlSavemultprocess, IsFinishedAll);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void Delet_data_For_check(long Request_ID) {
        database = engine.getWritableDatabase();
        database.execSQL("DELETE FROM  CommitteeResult where Items_ID in (SELECT I._id FROM Items  I INNER JOIN RequestCommittee  RC on  RC._id==I.RequestCommittee_ID where  RC._id=" + Request_ID + ")");
        database.execSQL("DELETE FROM SampleData where  Items_ID in (SELECT I._id FROM  Items I INNER JOIN RequestCommittee  RC on  RC._id==I.RequestCommittee_ID where  RC._id=" + Request_ID + ")");
        database.execSQL("DELETE FROM  TreatmentData where Items_ID in (SELECT I._id FROM  Items I INNER JOIN RequestCommittee  RC on  RC._id==I.RequestCommittee_ID where  RC._id=" + Request_ID + ")");
        database.execSQL("DELETE FROM Items WHERE RequestCommittee_ID=" + Request_ID);
        database.execSQL(" DELETE FROM RequestCommitteeEmployee WHERE checkRequestId=" + Request_ID);
        database.execSQL("DELETE FROM RequestCommittee WHERE _id=" + Request_ID);
        database.close();
    }

    public void Insert_result(String TableName, long Items_ID, long LotID, String Result, String JsonConfirm) {
        database = engine.getWritableDatabase();
        values = new ContentValues();
        values.put("Items_ID", Items_ID);
        values.put("LotID", LotID);
        values.put("JsonResult", Result);
        values.put("JsonConfirm", JsonConfirm);
        database.insert(TableName, null, values);
//        // ubdate count for check
//        HashMap<String, Integer> detailscounter = new HashMap<>();
//        detailscounter = Get_Data_For_Items(Items_ID);
//        int counter = 0;
//        if (CountColumnName.equals("Ischeck")) {
//            counter = detailscounter.get("Ischeck").intValue() - 1 + detailscounter.get("Isanalysis").intValue() + detailscounter.get("Istreatment").intValue();
//            values = new ContentValues();
//            if (counter == 0) {
//                values.put("Ischeck", detailscounter.get("Ischeck").intValue() - 1);
//                values.put("Has_Result", 1);
//            } else {
//                values.put("Ischeck", detailscounter.get("Ischeck").intValue() - 1);
//            }
//            database.update("Items", values, "_id=" + Items_ID, null);
//        } else if (CountColumnName.equals("Isanalysis")) {
//            counter = detailscounter.get("Isanalysis").intValue() - 1 + detailscounter.get("Ischeck").intValue() + detailscounter.get("Istreatment").intValue();
//            values = new ContentValues();
//            if (counter == 0) {
//                values.put("Isanalysis", detailscounter.get("Isanalysis").intValue() - 1);
//                values.put("Has_Result", 1);
//            } else {
//                values.put("Isanalysis", detailscounter.get("Isanalysis").intValue() - 1);
//            }
//            database.update("Items", values, "_id=" + Items_ID, null);
//        } else {
//            counter = detailscounter.get("Istreatment").intValue() - 1 + detailscounter.get("Isanalysis").intValue() + detailscounter.get("Ischeck").intValue();
//            values = new ContentValues();
//            if (counter == 0) {
//                values.put("Istreatment", detailscounter.get("Istreatment").intValue() - 1);
//                values.put("Has_Result", 1);
//            } else {
//                values.put("Istreatment", detailscounter.get("Istreatment").intValue() - 1);
//            }
//            database.update("Items", values, "_id=" + Items_ID, null);
//        }
//
//        // ubdate total process
//        counter = Integer.parseInt(Get_Data_for_RequestCommittee_working("Total_process", checkRequestId));
//        values = new ContentValues();
//        values.put("Total_process", counter - 1);
//        database.update("RequestCommittee", values, "_id=" + checkRequestId, null);
    }

    public void UbdateConterForItemResult(String CountColumnName, long Items_ID, long checkRequestId) {
        database = engine.getWritableDatabase();
        // ubdate count for check
        HashMap<String, Integer> detailscounter = new HashMap<>();
        detailscounter = Get_Data_For_Items(Items_ID);
        int counter = 0;
        if (CountColumnName.equals("Ischeck")) {
            counter = detailscounter.get("Ischeck").intValue() - 1 + detailscounter.get("Isanalysis").intValue() + detailscounter.get("Istreatment").intValue();
            values = new ContentValues();
            if (counter == 0) {
                values.put("Ischeck", detailscounter.get("Ischeck").intValue() - 1);
                values.put("Has_Result", 1);
            } else {
                values.put("Ischeck", detailscounter.get("Ischeck").intValue() - 1);
            }
            database.update("Items", values, "_id=" + Items_ID, null);
        } else if (CountColumnName.equals("Isanalysis")) {
            counter = detailscounter.get("Isanalysis").intValue() - 1 + detailscounter.get("Ischeck").intValue() + detailscounter.get("Istreatment").intValue();
            values = new ContentValues();
            if (counter == 0) {
                values.put("Isanalysis", detailscounter.get("Isanalysis").intValue() - 1);
                values.put("Has_Result", 1);
            } else {
                values.put("Isanalysis", detailscounter.get("Isanalysis").intValue() - 1);
            }
            database.update("Items", values, "_id=" + Items_ID, null);
        } else {
            counter = detailscounter.get("Istreatment").intValue() - 1 + detailscounter.get("Isanalysis").intValue() + detailscounter.get("Ischeck").intValue();
            values = new ContentValues();
            if (counter == 0) {
                values.put("Istreatment", detailscounter.get("Istreatment").intValue() - 1);
                values.put("Has_Result", 1);
            } else {
                values.put("Istreatment", detailscounter.get("Istreatment").intValue() - 1);
            }
            database.update("Items", values, "_id=" + Items_ID, null);
        }

        // ubdate total process
        counter = Integer.parseInt(Get_Data_for_RequestCommittee_working("Total_process", checkRequestId));
        values = new ContentValues();
        values.put("Total_process", counter - 1);
        database.update("RequestCommittee", values, "_id=" + checkRequestId, null);
    }

    public JSONArray Getdatatosendservercheckrequset(long RequestCommittee_ID, Long ID_Emp) throws JSONException {
        JSONObject plant = null;
        JSONArray plants = null;
        Cursor cursor = engine.getReadableDatabase().rawQuery("select I._id,I.Isplant,I.ProdPlant_ID ,R.Committee_ID from RequestCommittee R INNER JOIN Items I ON R._id==I.RequestCommittee_ID where I.Has_Result=1 and I.RequestCommittee_ID=" + RequestCommittee_ID, null);
        Cursor items;
        int count_items;
        int count = cursor.getCount();
        if (count > 0) {
            plants = new JSONArray();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                long Ex_Request_Item_Id = cursor.getLong(0);
                items = engine.getReadableDatabase().rawQuery("select JsonResult from CommitteeResult where Items_ID =" + Ex_Request_Item_Id, null);
                count_items = items.getCount();
                JSONArray datasendarray = null;
                if (count_items > 0) {
                    plant = new JSONObject();
                    plant.put("Ex_Request_Item_Id", Ex_Request_Item_Id);
                    plant.put("IsPlant", cursor.getInt(1));
                    plant.put("ProdPlant_ID ", cursor.getInt(2));
                    plant.put("Committee_ID", cursor.getLong(3));
                    plant.put("EmployeeId", ID_Emp);
                    datasendarray = new JSONArray();
                    for (int c = 0; c < count_items; c++) {
                        items.moveToNext();
                        datasendarray.put(new JSONObject(items.getString(0)));
                    }
                    plant.put("ComResult", datasendarray);

                }
                plants.put(plant);
            }

        }
        return plants;
    }

    public JSONArray Getdatatosendserversampledata(long RequestCommittee_ID, Long ID_Emp) throws JSONException {
        JSONObject plant = null;
        JSONArray plants = null;
        Cursor cursor = engine.getReadableDatabase().rawQuery("select I._id,I.Isplant,I.ProdPlant_ID ,R.Committee_ID from RequestCommittee R INNER JOIN Items I ON R._id==I.RequestCommittee_ID where I.Has_Result=1 and I.RequestCommittee_ID=" + RequestCommittee_ID, null);
        Cursor items;
        int count_items;
        int count = cursor.getCount();
        if (count > 0) {
            plants = new JSONArray();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                long Ex_Request_Item_Id = cursor.getLong(0);
                items = engine.getReadableDatabase().rawQuery("select JsonResult from SampleData where Items_ID =" + Ex_Request_Item_Id, null);
                count_items = items.getCount();
                JSONArray datasendarray = null;
                if (count_items > 0) {
                    plant = new JSONObject();
                    plant.put("Ex_Request_Item_Id", Ex_Request_Item_Id);
                    plant.put("IsPlant", cursor.getInt(1));
                    plant.put("ProdPlant_ID ", cursor.getInt(2));
                    plant.put("Committee_ID", cursor.getLong(3));
                    plant.put("User_Creation_Id", ID_Emp);
                    datasendarray = new JSONArray();
                    for (int c = 0; c < count_items; c++) {
                        items.moveToNext();
                        datasendarray.put(new JSONObject(items.getString(0)));
                    }
                    plant.put("SampleResult", datasendarray);
                }
                plants.put(plant);
            }
        }
        return plants;
    }

    public JSONArray Getdatatosendservertreatmentdata(long RequestCommittee_ID, Long ID_Emp) throws JSONException {
        JSONObject plant = null;
        JSONArray plants = null;
        Cursor cursor = engine.getReadableDatabase().rawQuery("select I._id,I.Isplant,I.ProdPlant_ID ,R.Committee_ID from RequestCommittee R INNER JOIN Items I ON R._id==I.RequestCommittee_ID where I.Has_Result=1 and I.RequestCommittee_ID=" + RequestCommittee_ID, null);
        int count = cursor.getCount();
        Cursor items;
        int count_items;
        if (count > 0) {
            plants = new JSONArray();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                long Ex_Request_Item_Id = cursor.getLong(0);
                items = engine.getReadableDatabase().rawQuery("select JsonResult from TreatmentData where Items_ID =" + Ex_Request_Item_Id, null);
                count_items = items.getCount();
                JSONArray datasendarray = null;
                if (count_items > 0) {
                    plant = new JSONObject();
                    plant.put("Ex_Request_Item_Id", Ex_Request_Item_Id);
                    plant.put("IsPlant", cursor.getInt(1));
                    plant.put("ProdPlant_ID ", cursor.getInt(2));
                    plant.put("Committee_ID", cursor.getLong(3));
                    plant.put("User_Creation_Id", ID_Emp);
                    datasendarray = new JSONArray();
                    for (int c = 0; c < count_items; c++) {
                        items.moveToNext();
                        datasendarray.put(new JSONObject(items.getString(0)));
                    }
                    plant.put("TreatResult", datasendarray);
                }
                plants.put(plant);
            }
        }
        return plants;
    }

    public List<DataForCardItems> GetDataForItems(Long RequestID, int ItemType) {
        Cursor cursor = engine.getReadableDatabase().rawQuery("select  _id,Ischeck,Isanalysis ,Istreatment,Has_Result,IsExport,Item_Name,Item_Cat_Name from  Items where RequestCommittee_ID='" + RequestID + "' and Isplant='" + ItemType + "'", null);
        int count = cursor.getCount();
        List<DataForCardItems> List_DataForCardItems = null;
        if (count > 0) {
            List_DataForCardItems = new ArrayList<>(count);
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                List_DataForCardItems.add(new DataForCardItems(cursor.getLong(0), cursor.getInt(1), cursor.getInt(2),
                        cursor.getInt(3), cursor.getShort(4), cursor.getInt(5),
                        cursor.getString(6), cursor.getString(7)));
            }
        }
        return List_DataForCardItems;
    }

    public DataForCardItems GetDataForItems(long ID_Item) {
        Cursor cursor = engine.getReadableDatabase().rawQuery("select  _id,Ischeck,Isanalysis ,Istreatment,Has_Result,IsExport,Item_Name,Item_Cat_Name,Isplant from  Items where _id=" + ID_Item, null);
        int count = cursor.getCount();
        DataForCardItems DataForCardItems = null;
        if (count > 0) {
            DataForCardItems = new DataForCardItems();
            cursor.moveToNext();
            DataForCardItems = new DataForCardItems(cursor.getLong(0), cursor.getInt(1), cursor.getInt(2),
                    cursor.getInt(3), cursor.getShort(4), cursor.getInt(5),
                    cursor.getString(6), cursor.getString(7), cursor.getInt(8));
        }
        return DataForCardItems;
    }

}

