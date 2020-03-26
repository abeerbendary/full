package com.example.abeer.quarantine.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.abeer.quarantine.BR;
import com.example.abeer.quarantine.viewmodel.confirm.CommitteData;
import com.example.abeer.quarantine.viewmodel.confirm.SampleeDataa;
import com.example.abeer.quarantine.viewmodel.confirm.TreatmenttDataa;

import java.util.ArrayList;
import java.util.List;

public class ListItemLotat extends BaseObservable {

    public ArrayList<ItemLotatData> _x0040_temp_table_Lot  =new ArrayList<>();

    public ListItemLotat() {
    }

    public  ListItemLotat(ListItemLotat listItemLotat)
    {
        this._x0040_temp_table_Lot= listItemLotat._x0040_temp_table_Lot;
    }
    @Bindable
    public String getList_ConstrainText_Ar()
    { return _x0040_temp_table_Lot.get(0).Package_Material_Name; }

    @Bindable
    public List<ItemLotatData> get_obj() {
        List<ItemLotatData> ArrayDisplayText=new ArrayList<>();
        try {
            //first item is empty

            for (int i = 0; i <_x0040_temp_table_Lot.size(); i++) {
                //
                ArrayDisplayText.add(_x0040_temp_table_Lot .get(i));
            }
        }
        catch (Exception ex)
        {

        }

        return ArrayDisplayText;
    }

    public ListItemLotat GetSampleData2(SampleeDataa sampleeDataa) {
        List<ItemLotatData> sampledata = new ArrayList<>();
        ListItemLotat listItemLotat=new ListItemLotat();
        try {
            //first item is empty
            int size = sampleeDataa.SampleData.size();
            for (int i = 0; i < size; i++) {
                if((sampleeDataa.SampleData.get(i).Ex_Request_LotData_ID) !=-1)
                {
                    sampledata.add(new ItemLotatData(sampleeDataa.SampleData.get(i)));
                    listItemLotat._x0040_temp_table_Lot.add(sampledata.get(i));
                }

            }
        } catch (Exception ex) {

        }

        return listItemLotat;
    }

    public ListItemLotat GettreatmentData3(TreatmenttDataa treatmenttDataa) {
        List<ItemLotatData> treatment = new ArrayList<>();
        ListItemLotat listItemLotat=new ListItemLotat();
        try {
            //first item is empty
            int size = treatmenttDataa.TreatmentData.size();
            for (int i = 0; i < size; i++) {
                if((treatmenttDataa.TreatmentData.get(i).Ex_Request_LotData_ID) !=-1)
                {
                    treatment.add(new ItemLotatData(treatmenttDataa.TreatmentData.get(i)));
                    listItemLotat._x0040_temp_table_Lot.add(treatment.get(i));
                }

            }
        } catch (Exception ex) {

        }

        return listItemLotat;
    }

    public ListItemLotat GetCommitteData2(CommitteData committeData) {
        List<ItemLotatData> CommitteData = new ArrayList<>();
        ListItemLotat listItemLotat=new ListItemLotat();
        try {
            //first item is empty
            int size = committeData.CommitteeData.size();
            for (int i = 0; i < size; i++) {
                if((committeData.CommitteeData.get(i).Ex_RequestLotData_ID) !=-1){
                    CommitteData.add(new ItemLotatData(committeData.CommitteeData.get(i)));
                    listItemLotat._x0040_temp_table_Lot.add(CommitteData.get(i));
                }

            }
        }
        catch (Exception ex) {

        }

        return listItemLotat;
    }


    @Bindable
    public ArrayList<String> get_ListDetail() {
        ArrayList<String> ArrayDisplayText=new ArrayList<>();
        //first item is empty
        int size=_x0040_temp_table_Lot.size();
        for (int i = 0; i <size; i++) {
            ArrayDisplayText.add(_x0040_temp_table_Lot.get(i).Package_Material_Name);
        }
        return ArrayDisplayText;
    }
    public void set_ListDetail(ListItemLotat listDetail) {
        this._x0040_temp_table_Lot=listDetail._x0040_temp_table_Lot;
    //    notifyPropertyChanged(BR.detailItemLotat3);
    }
    @Bindable
    public int get_totalPackage_Count(){
        int totalPackage_Count=0;
        int size=_x0040_temp_table_Lot.size();
        for (int x=0;x<size;x++){
            totalPackage_Count+=_x0040_temp_table_Lot.get(x).getPackage_Count();
        }
        return totalPackage_Count;
    }
@Bindable
    public double get_totalNet_Weight(){
        double totalNet_Weight=0;
        int size=_x0040_temp_table_Lot.size();
        for (int x=0;x<size;x++){
            totalNet_Weight+=_x0040_temp_table_Lot.get(x).getNet_Weight();
        }
        return totalNet_Weight;
    }
}
