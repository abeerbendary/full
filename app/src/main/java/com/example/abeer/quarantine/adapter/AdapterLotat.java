package com.example.abeer.quarantine.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.abeer.quarantine.R;
import com.example.abeer.quarantine.databinding.ItemDatadaLotattabinding;
import com.example.abeer.quarantine.viewmodel.ItemData;
import com.example.abeer.quarantine.viewmodel.ItemLotatData;

import java.util.ArrayList;
import java.util.List;

public class AdapterLotat extends RecyclerView.Adapter<AdapterLotat.Holder> {
    List<ItemLotatData> itemData;
    Context context;
    ItemData t;
    public AdapterLotat(List<ItemLotatData> itemDatas, Context context,ItemData item) {
        itemData = itemDatas;
        this.context = context;
        t=item;
    }
    public AdapterLotat(ArrayList<ItemLotatData> itemDatas, Context context,ItemData item) {
        itemData = itemDatas;
        this.context = context;
        t=item;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDatadaLotattabinding itemDatadaconstraintabinding= DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.cardlotat,parent,false);
        return new AdapterLotat.Holder(itemDatadaconstraintabinding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ItemLotatData itemConstrainsData=itemData.get(position);
        holder.bind(itemConstrainsData);
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ItemDatadaLotattabinding itemDatadaLotattabinding;
        public Holder( ItemDatadaLotattabinding itemDatadaLotattabinding1) {
            super(itemDatadaLotattabinding1.getRoot());
            this.itemDatadaLotattabinding=itemDatadaLotattabinding1;
        }

        public void bind(Object o)
        {
            itemDatadaLotattabinding.setDetaillotat((ItemLotatData)o);
            itemDatadaLotattabinding.setPlantItem(t);
        }
    }
}
