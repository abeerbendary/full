package com.example.abeer.quarantine.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.abeer.quarantine.R;
import com.example.abeer.quarantine.databinding.Plantproductbinding;
import com.example.abeer.quarantine.presenter.ClickCustomItemData;
import com.example.abeer.quarantine.viewmodel.DataForCardItems;
import com.example.abeer.quarantine.viewmodel.Emp_Committe;


import java.util.List;


public class AdapterPlantProduct extends RecyclerView.Adapter<AdapterPlantProduct.Holder> {
    List<DataForCardItems> itemData_plantProduct;
    Emp_Committe emp_committe;
    Context context;
    ClickCustomItemData clickCustomItemData_plantproduct;

    public AdapterPlantProduct(List<DataForCardItems> itemData_plantProducts, Emp_Committe emp_committe, Context context, ClickCustomItemData clickCustomItemData_plantproduct) {
        this.emp_committe = emp_committe;
        itemData_plantProduct = itemData_plantProducts;
        this.context = context;
        this.clickCustomItemData_plantproduct = clickCustomItemData_plantproduct;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Plantproductbinding plantproductbinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.cardplantproduct, parent, false);
        return new Holder(plantproductbinding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        DataForCardItems item_plantProduct = itemData_plantProduct.get(position);
        holder.bind(item_plantProduct,emp_committe ,clickCustomItemData_plantproduct);
        holder.plantproductbinding.setClicksed(clickCustomItemData_plantproduct);
    }

    @Override
    public int getItemCount() {
        return itemData_plantProduct.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        Plantproductbinding plantproductbinding;

        public Holder(@NonNull Plantproductbinding itemView) {
            super(itemView.getRoot());
            this.plantproductbinding = itemView;
        }

        public void bind(Object OBJ,Emp_Committe emp_committe ,final ClickCustomItemData clickCustom) {
            plantproductbinding.setDataForCardItems((DataForCardItems) OBJ);
            plantproductbinding.setISadmin(emp_committe);
            plantproductbinding.executePendingBindings();
        }

    }
}
