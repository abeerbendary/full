package com.example.abeer.quarantine.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.abeer.quarantine.R;
import com.example.abeer.quarantine.databinding.LOTSdatabinding;
import com.example.abeer.quarantine.presenter.Clickcustum;
import com.example.abeer.quarantine.viewmodel.DataForCardItems;
import com.example.abeer.quarantine.viewmodel.ItemData;
import com.example.abeer.quarantine.viewmodel.ItemLotatData;
import com.example.abeer.quarantine.viewmodel.ListItemLotat;
import com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult.SampleData_LOts;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterforRecycler extends RecyclerView.Adapter<MyAdapterforRecycler.Holder> {


    ListItemLotat Sampledata_lots;
    Clickcustum clickcustum;
    Context context;
DataForCardItems itemData;
    public MyAdapterforRecycler(DataForCardItems itemData, ListItemLotat sampledata_lots, Context context, Clickcustum clickcustum) {

      this.itemData=itemData;
        Sampledata_lots = sampledata_lots;
        this.clickcustum = clickcustum;
        this.context = context;
    }
    public MyAdapterforRecycler(ArrayList<ItemLotatData> sampledata_lots, Context context) {
        Sampledata_lots._x0040_temp_table_Lot = sampledata_lots;

        this.context = context;
    }
    public MyAdapterforRecycler() {
    }



    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

     LOTSdatabinding lotsdatabinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.innercard, viewGroup, false);
        return new Holder(lotsdatabinding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        ItemLotatData sampleData_lOts = Sampledata_lots._x0040_temp_table_Lot.get(i);
        holder.bind(itemData,sampleData_lOts,clickcustum);
        holder.lotsdatabinding.setClicked(clickcustum);
//        if(context instanceof MainActivity_TreatmentStatement ||context instanceof MainActivity_Ex_RequestCommitteeResult) {
//            holder.lotsdatabinding.btnGeneratLots.setVisibility(View.GONE);
//        }
   }

    @Override
    public int getItemCount() {
        return Sampledata_lots._x0040_temp_table_Lot.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        LOTSdatabinding lotsdatabinding;
        public Holder(@NonNull LOTSdatabinding lotsdatabinding) {
            super(lotsdatabinding.getRoot());
            this.lotsdatabinding=lotsdatabinding;
        }
        public void bind(DataForCardItems item,Object obj, final Clickcustum clickcustum) {
           lotsdatabinding.setLOTS((ItemLotatData) obj);
           lotsdatabinding.setType(item);
           lotsdatabinding.executePendingBindings();
        }
    }
}
