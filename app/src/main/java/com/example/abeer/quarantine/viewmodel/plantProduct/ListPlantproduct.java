package com.example.abeer.quarantine.viewmodel.plantProduct;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.abeer.quarantine.BR;
import com.example.abeer.quarantine.viewmodel.ItemData;
import com.example.abeer.quarantine.viewmodel.ListItemDataDetail;

import java.util.ArrayList;

public class ListPlantproduct  extends BaseObservable {
    public ArrayList<ItemData_PlantProduct> _x0040_Item_Data=new ArrayList<>();
    {

    }
    public ListPlantproduct(ListPlantproduct listPlantproduct){
        this._x0040_Item_Data= listPlantproduct._x0040_Item_Data;
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
    public String getList_ItemPartTypeName() { return _x0040_Item_Data.get(0).ItemPartTypeName; }
    @Bindable
    public void set_ListDetail(ListPlantproduct listPlantproduct) {

        this._x0040_Item_Data=listPlantproduct._x0040_Item_Data;
       // notifyPropertyChanged(BR.detailList);
    }
    public ArrayList<ItemData_PlantProduct> get_ItemData_test() {
        ArrayList<ItemData_PlantProduct> ArrayDisplayText=new ArrayList<>();
        for (int i = 0; i <_x0040_Item_Data.size(); i++) {
            ArrayDisplayText.add(_x0040_Item_Data.get(i));
        }
        return ArrayDisplayText;
    }
}