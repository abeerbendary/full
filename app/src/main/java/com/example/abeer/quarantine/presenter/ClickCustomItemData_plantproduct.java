package com.example.abeer.quarantine.presenter;

import android.view.View;

import com.example.abeer.quarantine.viewmodel.ItemData;
import com.example.abeer.quarantine.viewmodel.plantProduct.ItemData_PlantProduct;

public interface ClickCustomItemData_plantproduct {
    void plantProduct_click(View view, ItemData_PlantProduct itemData_plantProduct);
    void comfirm_click(View view, ItemData_PlantProduct itemData_plantProduct);
}
