package com.example.abeer.quarantine.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.abeer.quarantine.BR;
import java.util.ArrayList;
import java.util.List;

public class ListItemConstrains   extends BaseObservable {
    public ArrayList<ItemConstrainsData> _x0040_temp_table_Constrain=new ArrayList<>();
      public  ListItemConstrains(ListItemConstrains listItemConstrains){
         this._x0040_temp_table_Constrain= listItemConstrains._x0040_temp_table_Constrain;

    }

    public ListItemConstrains() {
    }

    @Bindable
    public String getList_ConstrainText_Ar() { return _x0040_temp_table_Constrain.get(0).ConstrainText_Ar; }


    @Bindable
    public List<ItemConstrainsData> get_obj() {
          List<ItemConstrainsData> ArrayDisplayText=new ArrayList<>();
          try {


              for (int i = 0; i <_x0040_temp_table_Constrain.size(); i++) {
                  ArrayDisplayText.add(_x0040_temp_table_Constrain.get(i));

              }
          }catch (Exception ex)
          {

          }

       return ArrayDisplayText;
    }




    @Bindable
    public ArrayList<String> get_ListDetail() {
        ArrayList<String> ArrayDisplayText=new ArrayList<>();
        for (int i = 0; i <_x0040_temp_table_Constrain.size(); i++) {
            ArrayDisplayText.add(_x0040_temp_table_Constrain.get(i).ConstrainText_Ar);
        }
        return ArrayDisplayText;
    }

    public void set_ListDetail(ListItemConstrains listItemConstrains) {

        this._x0040_temp_table_Constrain=listItemConstrains._x0040_temp_table_Constrain;
        //notifyPropertyChanged(BR.listItemConstrains);
    }
}
