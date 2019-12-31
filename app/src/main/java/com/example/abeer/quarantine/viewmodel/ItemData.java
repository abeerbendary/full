package com.example.abeer.quarantine.viewmodel;

import com.example.abeer.quarantine.BR;
import com.example.abeer.quarantine.model.RequestTreatmentData;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class ItemData extends BaseObservable {
    public String  Item_number;
    public String Item_Type;
    public String  Item_Name;
    public String Item_Cat_Name;
    public String ItemPartTypeName;
    public String  ItemStatus;
    public String ItemPurpose;
    public String Item_ShortName;
    public String  Item_Strain;
    public int IsExport;
    public long Item_Id;
    public short Has_Result;
    public int IsAnalysis;
    public int IsTreatment;
    public  long Request_Item_ID;
    public  Object Constrain_Data;
    public Object Lot_Data;
    public Object Item_result;
    public ItemData(){

    }
    public ItemData(ItemData itemData){
        this.Item_number=itemData.Item_number;
    this.Item_Cat_Name   = itemData.Item_Cat_Name;
     this.Item_Name  = itemData.Item_Name;
    this.Item_Type  =  itemData.Item_Type;
      this.ItemPartTypeName = itemData.ItemPartTypeName;
     this.ItemPurpose  = itemData.ItemPurpose;
    this.ItemStatus =itemData.ItemStatus;
    this.Item_ShortName=itemData.Item_ShortName;
    this.IsExport=itemData.IsExport;
    this.Has_Result=itemData.Has_Result;
    this.IsAnalysis=itemData.IsAnalysis;
    this.IsTreatment=itemData.IsTreatment;
    this.Item_Id=itemData.Item_Id;
    this.Request_Item_ID=itemData.Request_Item_ID;
    Constrain_Data=itemData.Constrain_Data;
    Lot_Data=itemData.Lot_Data;
    Item_result=itemData.Item_result;
    notifyPropertyChanged(BR.itemm);
    }

    public Object getConstrain_Data() {
        return Constrain_Data;
    }

    public void setConstrain_Data(Object constrain_Data) {
        Constrain_Data = constrain_Data;
    }

    public Object getLot_Data() {
        return Lot_Data;
    }

    public void setLot_Data(Object lot_Data) {
        Lot_Data = lot_Data;
    }

    public Object getItem_result() {
        return Item_result;
    }

    public void setItem_result(Object item_result) {
        Item_result = item_result;
    }

    @Bindable
    public int getIsExport() {
        return IsExport;
    }

    public void setIsExport(int isExport) {
        IsExport = isExport;
    }
@Bindable
    public long getRequest_Item_ID() {
        return Request_Item_ID;
    }

    public void setRequest_Item_ID(long request_Item_ID) {
        Request_Item_ID = request_Item_ID;
    }

    @Bindable
    public String getItem_ShortName() {
        return Item_ShortName;
    }

    public void setItem_ShortName(String item_ShortName) {
        Item_ShortName = item_ShortName;
        notifyPropertyChanged(BR.item_ShortName);
    }
@Bindable
    public String getItem_Type() {
        return Item_Type;
    }

    public void setItem_Type(String item_Type) {
        Item_Type = item_Type;
        notifyPropertyChanged(BR.item_Type);
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
    public String getItemPartTypeName() {
        return ItemPartTypeName;
    }

    public void setItemPartTypeName(String itemPartTypeName) {
        ItemPartTypeName = itemPartTypeName;
        notifyPropertyChanged(BR.itemPartTypeName);
    }
    @Bindable
    public String getItemStatus() {
        return ItemStatus;
    }

    public void setItemStatus(String itemStatus) {
        ItemStatus = itemStatus;
        notifyPropertyChanged(BR.itemStatus);
    }
    @Bindable
    public String getItemPurpose() {
        return ItemPurpose;
    }

    public void setItemPurpose(String itemPurpose) {
        ItemPurpose = itemPurpose;
        notifyPropertyChanged(BR.itemPurpose);
    }

    @Bindable
    public String getItem_number() {
        return Item_number;
    }

    public void setItem_number(String item_number) {
        Item_number = item_number;
        notifyPropertyChanged(BR.item_number);
    }

    @Bindable
    public long getItem_Id() {
        return Item_Id;
    }

    public void setItem_Id(long item_Id) {
        Item_Id = item_Id;
        notifyPropertyChanged(BR.item_Id);

    }

    @Bindable
    public int getHas_Result() {
        return (int)Has_Result;
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
    public String getcounters(){
        int count=0;
        String text="";
        if(getHas_Result()==0){

            text="لم يتم العمل عليه"+"\n";
                text+= "1 فحص"+"/";

            count=getIsAnalysis();
            if(count>0){
                text+=count+"سحب عينة"+"/";
            }
            count=getIsTreatment();
            if(count>0){
                text+=count+"معالجة";
            }
            return text;
        }else {
            return "تم العمل عليها";
        }
    }

    @Bindable
    public String getItem_Strain() {
        return Item_Strain;
    }

    public void setItem_Strain(String item_Strain) {
        Item_Strain = item_Strain;
    }
}
