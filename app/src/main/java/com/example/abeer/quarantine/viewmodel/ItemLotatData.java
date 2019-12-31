package com.example.abeer.quarantine.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.adapters.Converters;

import com.example.abeer.quarantine.BR;

public class ItemLotatData  extends BaseObservable {
    public  int  Lot_Number;
    public  int   Item_number;
    public  int   Package_Count;
    public double Net_Weight;
    public double Gross_Weight;
    public  String   IsAccepted;
    public  String    Package_Material_Name;
    public  String    Package_Type_Name;
    public  String    tem_number;
    public  long Lot_ID;

////////////////////



    public ItemLotatData(String package_Type_Name) {
        this.Lot_Number=0;
        Item_number=0;
        Package_Count=0;
        Net_Weight=0.00;
        Gross_Weight=0.00;
        Package_Type_Name=package_Type_Name;
        Package_Material_Name="";

    }

    public ItemLotatData(ItemLotatData ItemLotatData) {
        this.Lot_Number = ItemLotatData.Lot_Number;
        this.Item_number=ItemLotatData.Item_number;
        this.Package_Count = ItemLotatData.Package_Count;
        this.Net_Weight = ItemLotatData.Net_Weight;
        this.Gross_Weight = ItemLotatData.Gross_Weight;
        this.IsAccepted = ItemLotatData.IsAccepted;
        this.Package_Material_Name = ItemLotatData.Package_Material_Name;
        this.Package_Type_Name = ItemLotatData.Package_Type_Name;
        this.tem_number = ItemLotatData.tem_number;
        this.Lot_ID=ItemLotatData.Lot_ID;
    }
    ////////////////
    public ItemLotatData(int lot_Number,int Lot_ID, int package_Count, double net_Weight, double gross_Weight, String isAccepted, String package_Material_Name, String package_Type_Name, String tem_number) {
        this.Lot_Number = lot_Number;
        this.Package_Count = package_Count;
        this.Net_Weight = net_Weight;
        this.Gross_Weight = gross_Weight;
        this.IsAccepted = isAccepted;
        this.Package_Material_Name = package_Material_Name;
        this.Package_Type_Name = package_Type_Name;
        this.tem_number = tem_number;
        this.Lot_ID=Lot_ID;
    }

    public int getItem_number() {
        return Item_number;
    }

    public void setItem_number(int item_number) {
        Item_number = item_number;
    }

    @Bindable
    public int getLot_Number() {
        return Lot_Number;
    }
@Bindable
    public long getLot_ID() {
        return Lot_ID;
    }

    public void setLot_ID(long lot_ID) {
        Lot_ID = lot_ID;
        notifyPropertyChanged(BR.lot_ID);
    }

    public void setLot_Number(int lot_Number) {
        Lot_Number = lot_Number;
    }
    @Bindable
    public int getPackage_Count() {
        return Package_Count;
    }

    public void setPackage_Count(int package_Count) {
        Package_Count = package_Count;
    }
    @Bindable
    public double getNet_Weight() {
        return Net_Weight;
    }

    public void setNet_Weight(double net_Weight) {
        Net_Weight = net_Weight;
    }
    @Bindable
    public double getGross_Weight() {
        return Gross_Weight;
    }

    public void setGross_Weight(double gross_Weight) {
        Gross_Weight = gross_Weight;
    }
    @Bindable
    public String getIsAccepted() {
        return IsAccepted;
    }

    public void setIsAccepted(String isAccepted) {
        IsAccepted = isAccepted;
    }
    @Bindable
    public String getPackage_Material_Name() {
        if(Package_Material_Name.isEmpty()) {

            Package_Material_Name="لا يوجد لوطات";
        }
        return Package_Material_Name;
    }

    public void setPackage_Material_Name(String package_Material_Name) {
        Package_Material_Name = package_Material_Name;
       // notifyPropertyChanged(BR.package_Material_Name);
    }
    @Bindable
    public String getPackage_Type_Name() {
        return Package_Type_Name;
    }

    public void setPackage_Type_Name(String package_Type_Name) {
        Package_Type_Name = package_Type_Name;
    }
    @Bindable
    public String getTem_number() {
        return tem_number;
    }

    public void setTem_number(String tem_number) {
        this.tem_number = tem_number;
    }
    @Bindable
    public String getLotdata() {
        String data  ;
        if (Item_number == 0 && Package_Count == 0 && Net_Weight == 0.00 && Gross_Weight == 0.00 && Package_Type_Name == "" && Package_Material_Name == "") {
           data=Package_Type_Name;
        }else {
//         data="م: " + Item_number + "\n" +"رقم اللوط: "+ Lot_Number + "\n" +"عدد العبوات: "+  Package_Count + "\n" + "الوزن الصافي: "+Net_Weight +
//                    "\n" + "الوزن القائم: "+Gross_Weight + "\n" + "نوع العبوة: "+Package_Type_Name + "\n"
//                    + "نوع مادة العبوة: "+ Package_Material_Name;

            data="رقم اللوط: "+ Lot_Number + "\n" +"عدد العبوات: "+ Package_Count + "\n" + "الوزن الصافي: "+Net_Weight +"  كيلوجرام"+
                    "\n" + "الوزن القائم: "+Gross_Weight +"  كيلوجرام"+ "\n" + "نوع العبوة: "+Package_Type_Name + "\n"
                    + "نوع مادة العبوة: "+ Package_Material_Name;
        }

    return data;
    }

}
