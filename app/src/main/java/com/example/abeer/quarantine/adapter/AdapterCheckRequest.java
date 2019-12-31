package com.example.abeer.quarantine.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.abeer.quarantine.R;
import com.example.abeer.quarantine.presenter.ClickCustomCheckRequest;
import com.example.abeer.quarantine.databinding.CheckRequestdatabinding;
import com.example.abeer.quarantine.viewmodel.ExportCheckRequest;

import java.util.List;

public class AdapterCheckRequest extends RecyclerView.Adapter<AdapterCheckRequest.Holder> {
    List<ExportCheckRequest>exportCheckRequests;
    Context context;
    ClickCustomCheckRequest clickcustum;
    public AdapterCheckRequest(List<ExportCheckRequest> sampledata_lots, Context context, ClickCustomCheckRequest clickCustomCheckRequest) {
        exportCheckRequests = sampledata_lots;
        this.context = context;
        this.clickcustum = clickCustomCheckRequest;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CheckRequestdatabinding checkRequestdatabinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.card_listofchipments, viewGroup, false);
        return new Holder(checkRequestdatabinding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        ExportCheckRequest exportCheckRequest = exportCheckRequests.get(i);
        holder.bind(exportCheckRequest,clickcustum);
        holder.checkRequestdatabinding.setClicked(clickcustum);
    }

    @Override
    public int getItemCount() {
        return exportCheckRequests.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        CheckRequestdatabinding checkRequestdatabinding;
        public Holder(@NonNull CheckRequestdatabinding checkRequestdatabinding1) {
            super(checkRequestdatabinding1.getRoot());
            this.checkRequestdatabinding=checkRequestdatabinding1;
        }
        public void bind(Object obj,final ClickCustomCheckRequest clickcustum) {

            checkRequestdatabinding.setCheckRequest((ExportCheckRequest) obj);
            checkRequestdatabinding.executePendingBindings();
        }
    }
}

/*
public class AdapterCheckRequest extends RecyclerView.Adapter<AdapterCheckRequest.Holder> {
    List<ExportCheckRequest>exportCheckRequests;
    Context context;

    public   AdapterCheckRequest( List<ExportCheckRequest>exportCheckRequests,Context context){
        exportCheckRequests=exportCheckRequests;
        this.context=context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CheckRequestdatabinding checkRequestdatabinding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.innercardcheckrequest,viewGroup,false);
        return new Holder(checkRequestdatabinding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
         ExportCheckRequest exportCheckRequest=exportCheckRequests.get(i);
         holder.bind(exportCheckRequest);
    }

    @Override
    public int getItemCount() {
        return exportCheckRequests.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        CheckRequestdatabinding checkRequestdatabinding;
        public Holder(@NonNull CheckRequestdatabinding checkRequestdatabinding )
        {
            super(checkRequestdatabinding.getRoot());
            this.checkRequestdatabinding=checkRequestdatabinding;
        }
        public void bind(Object object){
            checkRequestdatabinding.setCheckRequestNumber((ExportCheckRequest) object);
            checkRequestdatabinding.executePendingBindings();

        }
    }
//    List<ExportCheckRequest>exportCheckRequests;
//    Context context;
//    ClickCustomCheckRequest clickcustum;
//    public AdapterCheckRequest(List<ExportCheckRequest> sampledata_lots, Context context, ClickCustomCheckRequest clickCustomCheckRequest) {
//        exportCheckRequests = sampledata_lots;
//        this.context = context;
//        this.clickcustum = clickCustomCheckRequest;
//    }
//    @NonNull
//    @Override
//    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        CheckRequestdatabinding checkRequestdatabinding = DataBindingUtil.inflate(
//                LayoutInflater.from(viewGroup.getContext()),
//                R.layout.card_listofchipments, viewGroup, false);
//        return new Holder(checkRequestdatabinding);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull Holder holder, int i) {
//        ExportCheckRequest exportCheckRequest = exportCheckRequests.get(i);
//        holder.bind(exportCheckRequest,clickcustum);
//        holder.checkRequestdatabinding.setClicked(clickcustum);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return exportCheckRequests.size();
//    }
//
//    public class Holder extends RecyclerView.ViewHolder {
//        CheckRequestdatabinding checkRequestdatabinding;
//        public Holder(@NonNull CheckRequestdatabinding checkRequestdatabinding1) {
//            super(checkRequestdatabinding1.getRoot());
//            this.checkRequestdatabinding=checkRequestdatabinding1;
//        }
//        public void bind(Object obj,final ClickCustomCheckRequest clickcustum) {
//            checkRequestdatabinding.setCheckRequest((ExportCheckRequest) obj);
//            checkRequestdatabinding.executePendingBindings();
//        }
//    }
}
/*
public class AdapterCheckRequest extends RecyclerView.Adapter<AdapterCheckRequest.Holder> {
    List<ExportCheckRequest>exportCheckRequests;
    Context context;

    public   AdapterCheckRequest( List<ExportCheckRequest>exportCheckRequests,Context context){
        exportCheckRequests=exportCheckRequests;
        this.context=context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CheckRequestdatabinding checkRequestdatabinding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.innercardcheckrequest,viewGroup,false);
        return new Holder(checkRequestdatabinding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
         ExportCheckRequest exportCheckRequest=exportCheckRequests.get(i);
         holder.bind(exportCheckRequest);
    }

    @Override
    public int getItemCount() {
        return exportCheckRequests.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        CheckRequestdatabinding checkRequestdatabinding;
        public Holder(@NonNull CheckRequestdatabinding checkRequestdatabinding )
        {
            super(checkRequestdatabinding.getRoot());
            this.checkRequestdatabinding=checkRequestdatabinding;
        }
        public void bind(Object object){
            checkRequestdatabinding.setCheckRequestNumber((ExportCheckRequest) object);
            checkRequestdatabinding.executePendingBindings();

        }
    }
}
*/