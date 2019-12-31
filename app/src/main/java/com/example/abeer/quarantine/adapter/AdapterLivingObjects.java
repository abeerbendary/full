package com.example.abeer.quarantine.adapter;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.abeer.quarantine.R;
import com.example.abeer.quarantine.databinding.ItemDatadatabinding;
import com.example.abeer.quarantine.databinding.LivingObjectsbinding;
import com.example.abeer.quarantine.presenter.ClickCustomItemData;
import com.example.abeer.quarantine.viewmodel.DataForCardItems;
import com.example.abeer.quarantine.viewmodel.Emp_Committe;

import java.util.ArrayList;
import java.util.List;


public class AdapterLivingObjects extends RecyclerView.Adapter<AdapterLivingObjects.Holder>  {

    List<DataForCardItems> itemData=new ArrayList<>();
    Emp_Committe emp_committe;
    Context context;
    ClickCustomItemData clickCustomItemData;

    public AdapterLivingObjects(List<DataForCardItems> itemData, Emp_Committe emp_committe,Context context, ClickCustomItemData clickCustomItemData) {
        this.itemData = itemData;
        this.emp_committe=emp_committe;
        this.context = context;
        this.clickCustomItemData = clickCustomItemData;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LivingObjectsbinding livingObjectsbinding= DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.cardlivingobject,parent,false);
        return new Holder(livingObjectsbinding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        DataForCardItems livingObject=itemData.get(position);
        holder.bind(livingObject, emp_committe, clickCustomItemData);
        holder.livingObjectsbinding.setClicked(clickCustomItemData);
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        LivingObjectsbinding livingObjectsbinding;

        public Holder(@NonNull LivingObjectsbinding livingObjectsbinding) {
            super(livingObjectsbinding.getRoot() );
            this.livingObjectsbinding=livingObjectsbinding;
        }

        public void bind(Object OBJ,Emp_Committe emp_committe,final ClickCustomItemData clickLivingObjectss){
            livingObjectsbinding.setDataForCardItems((DataForCardItems) OBJ);
            livingObjectsbinding.setISadmin(emp_committe);
            livingObjectsbinding.executePendingBindings();
        }

    }
}
