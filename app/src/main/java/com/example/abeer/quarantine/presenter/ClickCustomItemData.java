package com.example.abeer.quarantine.presenter;

import android.view.View;

import com.example.abeer.quarantine.viewmodel.DataForCardItems;
import com.example.abeer.quarantine.viewmodel.ExportCheckRequest;
import com.example.abeer.quarantine.viewmodel.ItemData;

public interface ClickCustomItemData {
    void plant_click(View view, DataForCardItems itemData);
    void comfirm_click(View view, DataForCardItems itemData);
}
