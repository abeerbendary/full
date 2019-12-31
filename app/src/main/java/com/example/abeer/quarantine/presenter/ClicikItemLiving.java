package com.example.abeer.quarantine.presenter;

import android.view.View;

import com.example.abeer.quarantine.viewmodel.ItemData;
import com.example.abeer.quarantine.viewmodel.livingobjects.ItemData_LivingObject;
import com.example.abeer.quarantine.viewmodel.livingobjects.ListLivingObjects;

public interface ClicikItemLiving extends ClickCustomItemData {
    void eshtratatLiveObject_click(View view, ItemData_LivingObject itemData_livingObject);
    void comfirm_click(View view,ItemData_LivingObject itemData_livingObject);
}
