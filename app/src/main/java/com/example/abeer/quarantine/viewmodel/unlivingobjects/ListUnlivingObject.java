package com.example.abeer.quarantine.viewmodel.unlivingobjects;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.abeer.quarantine.BR;
import com.example.abeer.quarantine.viewmodel.livingobjects.ItemData_LivingObject;
import com.example.abeer.quarantine.viewmodel.livingobjects.ListLivingObjects;

import java.util.ArrayList;
public class ListUnlivingObject extends BaseObservable {

    public ArrayList<ItemData_LivingObject> _x0040_Item_Data=new ArrayList<>();
    {

    }
    public ListUnlivingObject(ListUnlivingObject listUnlivingObjectextends){
        this._x0040_Item_Data= listUnlivingObjectextends._x0040_Item_Data;
    }

    @Bindable
    public ArrayList<String> get_ListDetail() {
        ArrayList<String> ArrayDisplayText=new ArrayList<>();
        for (int i = 0; i <_x0040_Item_Data.size(); i++) {
            ArrayDisplayText.add(_x0040_Item_Data.get(i).Item_Name);
        }
        return ArrayDisplayText;
    }
    @Bindable
    public String getList_Item_Name() { return _x0040_Item_Data.get(0).Item_Name; }

    @Bindable
    public String getList_ItemPurpose() { return _x0040_Item_Data.get(0).ItemPurpose; }
    @Bindable
    public String getList_ItemStatus() { return _x0040_Item_Data.get(0).ItemStatus; }
    @Bindable
    public String getList_Item_Type() { return _x0040_Item_Data.get(0).Item_Type; }
    @Bindable
    public String getList_Item_Cat_Name() { return _x0040_Item_Data.get(0).Item_Cat_Name; }

    @Bindable
    public String getList_Item_Strain() { return _x0040_Item_Data.get(0).Item_Strain; }
    @Bindable
    public String getList_ItemPartTypeName() { return _x0040_Item_Data.get(0).ItemPartTypeName; }
    @Bindable
    public void set_ListDetail(ListLivingObjects listLivingObjects) {

        this._x0040_Item_Data=listLivingObjects._x0040_Item_Data;
      //  notifyPropertyChanged(BR.unliist);
    }
    public ArrayList<ItemData_LivingObject> get_ItemData_test() {
        ArrayList<ItemData_LivingObject> ArrayDisplayText=new ArrayList<>();
        for (int i = 0; i <_x0040_Item_Data.size(); i++) {
            ArrayDisplayText.add(_x0040_Item_Data.get(i));
        }
        return ArrayDisplayText;
    }


}
