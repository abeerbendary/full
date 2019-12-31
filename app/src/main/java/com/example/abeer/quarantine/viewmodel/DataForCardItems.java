package com.example.abeer.quarantine.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.abeer.quarantine.BR;


public class DataForCardItems extends BaseObservable {
    public  long Request_Item_ID;
    public int Ischeck;
    public int IsAnalysis;
    public int IsTreatment;
    public short Has_Result;
    public int IsExport;
    public String  Item_Name;
    public String Item_Cat_Name;
    public int Isplant;
    public DataForCardItems() {

    }
    public DataForCardItems(long request_Item_ID, int ischeck, int isAnalysis,
                            int isTreatment, short has_Result, int isExport, String item_Name,
                            String item_Cat_Name) {
        Request_Item_ID = request_Item_ID;
        Ischeck = ischeck;
        IsAnalysis = isAnalysis;
        IsTreatment = isTreatment;
        Has_Result = has_Result;
        IsExport = isExport;
        Item_Name = item_Name;
        Item_Cat_Name = item_Cat_Name;
        notifyPropertyChanged(BR.DataForCardItems);
    }
    public DataForCardItems(long request_Item_ID, int ischeck, int isAnalysis,
                            int isTreatment, short has_Result, int isExport, String item_Name,
                            String item_Cat_Name,int Isplant) {
        Request_Item_ID = request_Item_ID;
        Ischeck = ischeck;
        IsAnalysis = isAnalysis;
        IsTreatment = isTreatment;
        Has_Result = has_Result;
        IsExport = isExport;
        Item_Name = item_Name;
        Item_Cat_Name = item_Cat_Name;
        this.Isplant=Isplant;
        notifyPropertyChanged(BR.DataForCardItems);
    }
    public DataForCardItems(DataForCardItems dataForCardItems) {
        Item_Name = dataForCardItems.Item_Name;
        Item_Cat_Name = dataForCardItems.Item_Cat_Name;
        IsExport = dataForCardItems.IsExport;
        Has_Result = dataForCardItems.Has_Result;
        IsAnalysis = dataForCardItems.IsAnalysis;
        IsTreatment = dataForCardItems.IsTreatment;
        Request_Item_ID =dataForCardItems.Request_Item_ID;
        notifyPropertyChanged(BR.DataForCardItems);
    }
    @Bindable
    public String getItem_Name() {
        return Item_Name;
    }

    public void setItem_Name(String item_Name) {
        Item_Name = item_Name;
        notifyPropertyChanged(BR.item_Name);
    }
    @Bindable
    public String getItem_Cat_Name() {
        return Item_Cat_Name;
    }

    public void setItem_Cat_Name(String item_Cat_Name) {
        Item_Cat_Name = item_Cat_Name;
        notifyPropertyChanged(BR.item_Cat_Name);
    }
    @Bindable
    public int getIsExport() {
        return IsExport;
    }

    public void setIsExport(int isExport) {
        IsExport = isExport;
        notifyPropertyChanged(BR.isExport);
    }
    @Bindable
    public short getHas_Result() {
        return Has_Result;
    }

    public void setHas_Result(short has_Result) {
        Has_Result = has_Result;
        notifyPropertyChanged(BR.has_Result);
    }
    @Bindable
    public int getIsAnalysis() {
        return IsAnalysis;
    }

    public void setIsAnalysis(int isAnalysis) {
        IsAnalysis = isAnalysis;
        notifyPropertyChanged(BR.isAnalysis);
    }
    @Bindable
    public int getIsTreatment() {
        return IsTreatment;
    }

    public void setIsTreatment(int isTreatment) {
        IsTreatment = isTreatment;
        notifyPropertyChanged(BR.isTreatment);
    }
    @Bindable
    public long getRequest_Item_ID() {
        return Request_Item_ID;
    }

    public void setRequest_Item_ID(long request_Item_ID) {
        Request_Item_ID = request_Item_ID;
        notifyPropertyChanged(BR.request_Item_ID);
    }

    @Bindable
    public String getcounters(){
        int count=0;
        String text="";
        if(getHas_Result()==0){
            count=getIscheck();
            text="لم يتم العمل عليه"+"\n";
            text+= count+"فحص"+"/";
            count=getIsAnalysis();
//            if(count>0){
                text+=count+"سحب عينة"+"/";
//            }
            count=getIsTreatment();
//            if(count>0){
                text+=count+"معالجة";
//            }
            return text;
        }else {
            return "تم العمل عليها";
        }
    }
@Bindable
    public int getIscheck() {
        return Ischeck;
    }

    public void setIscheck(int ischeck) {
        Ischeck = ischeck;
       notifyPropertyChanged(BR.ischeck);
    }
@Bindable
    public int getIsplant() {
        return Isplant;
    }

    public void setIsplant(int isplant) {
        Isplant = isplant;
//        notifyPropertyChanged(BR.);
    }
}
