package com.example.abeer.quarantine.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.ViewGroup;

import com.example.abeer.quarantine.R;
import com.example.abeer.quarantine.databinding.UnLivingObjectsbinding;
import com.example.abeer.quarantine.presenter.ClickCustomItemData;
import com.example.abeer.quarantine.viewmodel.DataForCardItems;
import com.example.abeer.quarantine.viewmodel.Emp_Committe;

import java.util.List;

public class AdapterUnlivingObjects extends RecyclerView.Adapter<AdapterUnlivingObjects.Holder> {
    List<DataForCardItems> itemData_livingObject;
    Emp_Committe emp_committe;
    Context context;
    ClickCustomItemData clickItemUnliving;

    public AdapterUnlivingObjects( List<DataForCardItems>  itemData_livingObjects, Emp_Committe emp_committe,Context context, ClickCustomItemData clickItemUnlivings) {
        itemData_livingObject = itemData_livingObjects;
        this.emp_committe=emp_committe;
        this.context = context;
        this.clickItemUnliving = clickItemUnlivings;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UnLivingObjectsbinding unlivingObjectsbinding= DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.cardunlivingobject,parent,false);
        return new Holder(unlivingObjectsbinding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        DataForCardItems livingObject=itemData_livingObject.get(position);
        holder.bind(livingObject,emp_committe,clickItemUnliving);
        holder.unLivingObjectsbinding.setClicked(clickItemUnliving);
    }

    @Override
    public int getItemCount() {
        return itemData_livingObject.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        UnLivingObjectsbinding unLivingObjectsbinding;
        public Holder(@NonNull UnLivingObjectsbinding unLivingObjectsbinding1) {
            super(unLivingObjectsbinding1.getRoot());
            this.unLivingObjectsbinding=unLivingObjectsbinding1;
        }

        public void bind(Object OBJ,Emp_Committe emp_committe,final ClickCustomItemData clickunLivingObjectss){
            unLivingObjectsbinding.setDataForCardItems((DataForCardItems) OBJ);
            unLivingObjectsbinding.setISadmin(emp_committe);
            unLivingObjectsbinding.executePendingBindings();
        }
    }
}
