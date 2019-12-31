package com.example.abeer.quarantine.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.abeer.quarantine.R;
import com.example.abeer.quarantine.databinding.ItemDatadaconstraintabinding;
import com.example.abeer.quarantine.viewmodel.ItemConstrainsData;
import com.example.abeer.quarantine.viewmodel.ItemData;

import java.util.ArrayList;
import java.util.List;


public class AdapterEshtratat extends RecyclerView.Adapter<AdapterEshtratat.Holder>  {

    List<ItemConstrainsData> itemData;
    Context context;
    ItemData t;

    public AdapterEshtratat(List<ItemConstrainsData> itemDatas, Context context,ItemData item) {
        itemData = itemDatas;
        this.context = context;
        t=item;
    }
    public AdapterEshtratat(ArrayList<ItemConstrainsData> itemDatas, Context context,ItemData item) {
        itemData = itemDatas;
        this.context = context;
        t=item;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDatadaconstraintabinding itemDatadaconstraintabinding= DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.cardeshtratat,parent,false);

        return new Holder(itemDatadaconstraintabinding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ItemConstrainsData itemConstrainsData=itemData.get(position);
        holder.bind(itemConstrainsData);
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        ItemDatadaconstraintabinding itemDatadaconstraintabinding;
        public Holder(@NonNull ItemDatadaconstraintabinding itemView) {
            super(itemView.getRoot());
            this.itemDatadaconstraintabinding=itemView;
        }
        public void bind(Object o)
        {
            itemDatadaconstraintabinding.setDetailItemDataList2((ItemConstrainsData)o);
            itemDatadaconstraintabinding.setPlantItem(t);
        }

    }
}
