package com.example.abeer.quarantine.presenter;

import android.view.View;

import com.example.abeer.quarantine.viewmodel.livingobjects.ItemData_LivingObject;

public interface ClickItemUnliving {
    void unLiveObject_click(View view , ItemData_LivingObject itemData_livingObject);
    void comfirm_click(View view ,ItemData_LivingObject itemData_livingObject);

}
