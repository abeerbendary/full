package com.example.abeer.quarantine.viewmodel;

import com.example.abeer.quarantine.BR;
import com.example.abeer.quarantine.model.ItemDataDetails;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.ArrayList;


public class ListItemDataDetail extends BaseObservable {
//   // public  ArrayList<ItemDataDetails> _x0040_Item_Data=new ArrayList<>();
//    //////////////
// public  ArrayList<ItemData> _x0040_Item_Data=new ArrayList<>();
//    /////////////////
//  // public ArrayList<ItemData> _x0040_Item_Data_test=new ArrayList<>();
//    ListItemDataDetail(){
//
//    }
//  public ListItemDataDetail(ListItemDataDetail itemDataDetail){
//     this._x0040_Item_Data= itemDataDetail._x0040_Item_Data;
//   //  this._x0040_Item_Data_test=itemDataDetail._x0040_Item_Data_test;
//    }
//
//
//    @Bindable
//    public ArrayList<String> get_ListDetail() {
//        ArrayList<String> ArrayDisplayText=new ArrayList<>();
//        for (int i = 0; i <_x0040_Item_Data.size(); i++) {
//            ArrayDisplayText.add(_x0040_Item_Data.get(i).Item_Name);
//        }
//        return ArrayDisplayText;
//    }
//    @Bindable
//    public String getList_ItemPurpose() { return _x0040_Item_Data.get(0).ItemPurpose; }
//    @Bindable
//    public String getList_ItemStatus() { return _x0040_Item_Data.get(0).ItemStatus; }
//    @Bindable
//    public String getList_Item_Type() { return _x0040_Item_Data.get(0).Item_Type; }
//    @Bindable
//    public String getList_Item_Cat_Name() { return _x0040_Item_Data.get(0).Item_Cat_Name; }
//    @Bindable
//    public String getList_ItemPartTypeName() { return _x0040_Item_Data.get(0).ItemPartTypeName; }
//    @Bindable
//    public String getList_Item_ShortName() { return _x0040_Item_Data.get(0).Item_ShortName; }
//    public void set_ListDetail(ListItemDataDetail itemDataDetail) {
//
//        this._x0040_Item_Data=itemDataDetail._x0040_Item_Data;
//        notifyPropertyChanged(BR.detailItemDataList);
//    }
//    ///////////////
//    public ArrayList<ItemData> get_ItemData_test() {
//        ArrayList<ItemData> ArrayDisplayText=new ArrayList<>();
//        for (int i = 0; i <_x0040_Item_Data.size(); i++) {
//            ArrayDisplayText.add(_x0040_Item_Data.get(i));
//        }
//        return ArrayDisplayText;
//    }
//
//
//
//
//
//
/////////////
// public  ArrayList<ItemDataDetails> _x0040_Item_Data=new ArrayList<>();
//////////////

    public ArrayList<ItemData> _x0040_Item_Data = new ArrayList<>();

    public ListItemDataDetail(ListItemDataDetail itemDataDetail) {
        this._x0040_Item_Data = itemDataDetail._x0040_Item_Data;

    }

    public ListItemDataDetail() {

    }

    @Bindable
    public ArrayList<String> get_ListDetail() {
        ArrayList<String> ArrayDisplayText = new ArrayList<>();
        for (int i = 0; i < _x0040_Item_Data.size(); i++) {
            ArrayDisplayText.add(_x0040_Item_Data.get(i).Item_Name);
        }
        return ArrayDisplayText;
    }

    @Bindable
    public String getList_ItemPurpose() {
        return _x0040_Item_Data.get(0).ItemPurpose;
    }

    @Bindable
    public String getList_ItemStatus() {
        return _x0040_Item_Data.get(0).ItemStatus;
    }

    @Bindable
    public String getList_Item_Type() {
        return _x0040_Item_Data.get(0).Item_Type;
    }

    @Bindable
    public String getList_Item_Cat_Name() {
        return _x0040_Item_Data.get(0).Item_Cat_Name;
    }

    @Bindable
    public String getList_ItemPartTypeName() {
        return _x0040_Item_Data.get(0).ItemPartTypeName;
    }

    @Bindable
    public String getList_Item_ShortName() {
        return _x0040_Item_Data.get(0).Item_ShortName;
    }

    public void set_ListDetail(ListItemDataDetail itemDataDetail) {

        this._x0040_Item_Data = itemDataDetail._x0040_Item_Data;
        notifyPropertyChanged(BR.detailItemDataList);
    }

    ///////////////
    public ArrayList<ItemData> get_ItemData_test() {
        ArrayList<ItemData> ArrayDisplayText = new ArrayList<>();
        for (int i = 0; i < _x0040_Item_Data.size(); i++) {
            ArrayDisplayText.add(_x0040_Item_Data.get(i));
        }
        return ArrayDisplayText;
    }


///////////
}
