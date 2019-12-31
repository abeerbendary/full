package com.example.abeer.quarantine.functions;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.abeer.quarantine.R;
import com.example.abeer.quarantine.activity.Generate_barcode;
import com.example.abeer.quarantine.activity.LogIn;
import com.example.abeer.quarantine.activity.MainActivity_Confirm;
import com.example.abeer.quarantine.activity.MainActivity_DetailsListOfChimpments;
import com.example.abeer.quarantine.activity.MainActivity_Ex_RequestCommitteeResult;
import com.example.abeer.quarantine.activity.MainActivity_Listofchipment;
import com.example.abeer.quarantine.activity.MainActivity_SampleWithDraw;
import com.example.abeer.quarantine.activity.MainActivity_TreatmentStatement;
import com.example.abeer.quarantine.activity.MainActivity_subdetails;
import com.example.abeer.quarantine.remote.ApiCall;
import com.example.abeer.quarantine.remote.PlantQurDBHelper;
import com.example.abeer.quarantine.remote.data.DataManger;
import com.example.abeer.quarantine.remote.data.IDataValue;
import com.example.abeer.quarantine.viewmodel.sampleWithDraw.Barcod_Card;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

public class Public_function {
    DataManger DataManger;
    PlantQurDBHelper plantQurDBHelper;

    public Public_function() {
    }

    public void AlertDialog(String massage, final Context context, final boolean shownavbar) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alertdialog);
        Button dialogButton = dialog.findViewById(R.id.btnalert);
        TextView dialogtext = dialog.findViewById(R.id.detailspopup);
        dialogtext.setText(massage);
        dialogtext.setTextSize(20);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (shownavbar) {
                    ((DrawerLayout) ((Activity) context).findViewById(R.id.drawer_layout)).setVisibility(View.VISIBLE);
                    ((DrawerLayout) ((Activity) context).findViewById(R.id.drawer_layout)).openDrawer(GravityCompat.START);
//              findViewById(R.id.drawer_layout);
//              .openDrawer(GravityCompat.START);
                }
            }
        });
        dialog.getWindow().setLayout(1200, 635);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }


    public void AlertDialogTwoButton(String massage, final Context context, final String ipadress, final long Emp_id, final long request_id, final boolean shownavbar) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alertdialogwithtwobutton);
        Button dialogButtonYes = dialog.findViewById(R.id.btnalertyes);
        Button dialogButtonNo = dialog.findViewById(R.id.btnalertno);
        TextView dialogtext = dialog.findViewById(R.id.detailspopup);
        dialogtext.setText(massage);
        dialogtext.setTextSize(20);
        dialogButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (shownavbar) {
                    ((DrawerLayout) ((Activity) context).findViewById(R.id.drawer_layout)).setVisibility(View.VISIBLE);
                    ((DrawerLayout) ((Activity) context).findViewById(R.id.drawer_layout)).openDrawer(GravityCompat.START);
                }
            }
        });
        dialogButtonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plantQurDBHelper = new PlantQurDBHelper(context);
                plantQurDBHelper.update_counterResultForAdmin_New((Activity) context, ipadress, request_id, Emp_id, false);

            }
        });
        dialog.getWindow().setLayout(1200, 635);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void AlertDialogTwoButton(String massage, final Context context, final boolean shownavbar) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alertdialogwithtwobutton);
        Button dialogButtonYes = dialog.findViewById(R.id.btnalertyes);
        Button dialogButtonNo = dialog.findViewById(R.id.btnalertno);
        TextView dialogtext = dialog.findViewById(R.id.detailspopup);
        dialogtext.setText(massage);
        dialogtext.setTextSize(20);
        dialogButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (shownavbar) {
                    ((DrawerLayout) ((Activity) context).findViewById(R.id.drawer_layout)).setVisibility(View.VISIBLE);
                    ((DrawerLayout) ((Activity) context).findViewById(R.id.drawer_layout)).openDrawer(GravityCompat.START);
                }
            }
        });
        dialogButtonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MainActivity_DetailsListOfChimpments.class);
                (context).startActivity(i);
            }
        });
        dialog.getWindow().setLayout(1200, 635);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    //for sample
    public void AlertDialogTwoButton(String massage, final Context context, final boolean shownavbar, final List<Barcod_Card> Barcod_Cards) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alertdialogwithtwobutton);
        Button dialogButtonYes = dialog.findViewById(R.id.btnalertyes);
        Button dialogButtonNo = dialog.findViewById(R.id.btnalertno);
        TextView dialogtext = dialog.findViewById(R.id.detailspopup);
        dialogtext.setText(massage);
        dialogtext.setTextSize(20);
        dialogButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (shownavbar) {
                    ((DrawerLayout) ((Activity) context).findViewById(R.id.drawer_layout)).setVisibility(View.VISIBLE);
                    ((DrawerLayout) ((Activity) context).findViewById(R.id.drawer_layout)).openDrawer(GravityCompat.START);
                }
            }
        });
        dialogButtonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Generate_barcode.class);
                intent.putExtra("barcode", (Serializable) Barcod_Cards);
                intent.putExtra("contextsample", "true");
                ((Activity) context).startActivity(intent);
            }
        });
        dialog.getWindow().setLayout(1200, 635);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void showbarcodelist(Context context, final List<Barcod_Card> barcod_cards) {
        Intent i = new Intent(context, Generate_barcode.class);
        i.putExtra("barcode", (Serializable) barcod_cards);
        i.putExtra("contextsample", "false");
        ((Activity) context).startActivity(i);
    }
//    public void AlertDialog(String massage , Context context) {
//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(R.layout.alertdialog);
//        Button dialogButton = dialog.findViewById(R.id.btnalert);
//        TextView dialogtext = dialog.findViewById(R.id.detailspopup);
//        dialogtext.setText(massage);
//        dialogtext.setTextSize(20);
//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                dialog.dismiss();
//
//            }
//        });
//        dialog.getWindow().setLayout(1200 , 635);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.show();
//    }

    public void senddataonlinetoserver(JSONArray jsonArray, final Context context, String URL) {
        DataManger = new DataManger(context);
        DataManger.SendVolleyRequestJsonArraypost(context, Request.Method.POST, URL, jsonArray, new IDataValue() {
            @Override
            public void Success(Object response) throws JSONException {
                int state_Code = ((JSONArray) response).getJSONObject(0).getInt("state_Code");
                if (state_Code == 1) {
                    Toast.makeText(context, "تم الحفظ بنجاح", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, MainActivity_Listofchipment.class);
                    ((Activity) context).startActivity(intent);
                } else {
                    Toast.makeText(context, "حدث خطا ما", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void Error(VolleyError error) {

            }
        });
    }

    public void senddataonlinetoserver(JSONArray jsonArray, final Context context, String URL, final List<Barcod_Card> barcod_cards) {
        DataManger = new DataManger(context);
        DataManger.SendVolleyRequestJsonArraypost(context, Request.Method.POST, URL, jsonArray, new IDataValue() {
            @Override
            public void Success(Object response) throws JSONException {
                int state_Code = ((JSONArray) response).getJSONObject(0).getInt("state_Code");
                if (state_Code == 1) {
                    Intent i = new Intent(context, Generate_barcode.class);
                    i.putExtra("barcode", (Serializable) barcod_cards);
                    ((Activity) context).startActivity(i);

//                        Toast.makeText(context, "تم الحفظ بنجاح", Toast.LENGTH_LONG).show();
//                        //  ViewBtnsById.setVisibility(View.GONE);
//                        Intent intent = new Intent(context,MainActivity_Listofchipment.class);
//                        ((Activity) context).startActivity(intent);
                } else {
                    Toast.makeText(context, "حدث خطا ما", Toast.LENGTH_LONG).show();
                    //   ViewBtnsById.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void Error(VolleyError error) {

            }
        });
    }

    /* for chipment*/
    public void senddataonlinetoserverformoreprocess(JSONObject jsonObject, final Context context, String URL, boolean IsFinishedAll) {

//        public void senddataonlinetoserverformoreprocess(JSONObject jsonObject, final Context context, String URL, boolean IsFinishedAll,long RequestID) {
        DataManger = new DataManger(context);
        plantQurDBHelper = new PlantQurDBHelper(context);
        DataManger.SendVolleyRequestJsonObjectpost(context, Request.Method.POST, URL + IsFinishedAll, jsonObject, new IDataValue() {

            @Override
            public void Success(Object response) {
                try {
                    int state_Code = ((JSONObject) response).getInt("state_Code");
                    if (state_Code == 1) {
                        Toast.makeText(context, "تم الحفظ بنجاح", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(context, "حدث خطا ما", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void Error(VolleyError error) {

            }
        });

        Intent i = new Intent(context, MainActivity_Listofchipment.class);
        context.startActivity(i);
//        plantQurDBHelper.Delet_data_For_check(RequestID);
    }

    /*for Station and farm*/
    public void senddataonlinetoserverformoreprocess(JSONObject jsonObject, final Context context, String URL) {
        DataManger = new DataManger(context);
        DataManger.SendVolleyRequestJsonObjectpost(context, Request.Method.POST, URL, jsonObject, new IDataValue() {

            @Override
            public void Success(Object response) {
                try {
                    int state_Code = ((JSONObject) response).getInt("state_Code");
                    if (state_Code == 1) {
                        Toast.makeText(context, "تم الحفظ بنجاح", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(context, MainActivity_Listofchipment.class);
//                        ((Activity) context).startActivity(intent);
                    } else {
                        Toast.makeText(context, "حدث خطا ما", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void Error(VolleyError error) {

            }
        });
    }

    public void senddataonlinetoserverformoreprocess(JSONObject jsonObject, final Context context, String URL, final List<Barcod_Card> barcod_cards) {
        DataManger = new DataManger(context);
        DataManger.SendVolleyRequestJsonObjectpost(context, Request.Method.POST, URL, jsonObject, new IDataValue() {

            @Override
            public void Success(Object response) {
                try {
                    int state_Code = ((JSONObject) response).getInt("state_Code");
                    if (state_Code == 1) {
                        Toast.makeText(context, "تم الحفظ بنجاح", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(context, Generate_barcode.class);
                        i.putExtra("barcode", (Serializable) barcod_cards);
                        i.putExtra("contextsample", "true");
                        ((Activity) context).startActivity(i);
                    } else {
                        Toast.makeText(context, "حدث خطا ما", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void Error(VolleyError error) {

            }
        });
    }


    public void NavMenuClick(int id, final Context context, String Token, Boolean Is_Admin, int RequestCommittee_Status_Id
            , int treatment_data, int sample_data, int request_data, int Committee_Type_Id, String ipadrass) throws JSONException {

        if (id == R.id.language) {
            // Handle the camera action
        } else if (id == R.id.sample_title) {
            if (context instanceof MainActivity_Listofchipment) {
                AlertDialog("رجاء اختيار الشحنة", context, false);
            } else if (context instanceof MainActivity_Ex_RequestCommitteeResult) {
                if (sample_data == -1) {
                    AlertDialog("لا يوجد بالاشتراطات سحب عينة", context, false);
                } else {
                    Intent i = new Intent(context, MainActivity_SampleWithDraw.class);
                    ((Activity) context).startActivity(i);
                }
            } else if (context instanceof MainActivity_TreatmentStatement) {
                if (sample_data == -1) {
                    AlertDialog("لا يوجد سحب عينة", context, false);
                } else if (sample_data != 0) {
                    Intent i = new Intent(context, MainActivity_SampleWithDraw.class);
                    ((Activity) context).startActivity(i);
                }
            } else if (context instanceof MainActivity_SampleWithDraw) {
                if (sample_data > 0) {
                    Intent i = new Intent(context, MainActivity_SampleWithDraw.class);
                    ((Activity) context).startActivity(i);
                }

            } else if (context instanceof MainActivity_DetailsListOfChimpments) {
                AlertDialog("رجاء اختيار النبات او المنتج ....", context, false);
            } else if (context instanceof MainActivity_subdetails) {
                if (Is_Admin) {
                    if (RequestCommittee_Status_Id == 1) {
                        if (sample_data == 0) {
                            AlertDialog("تم سحب العينة", context, false);
                        } else {
                            AlertDialog("لا يوجد بالاشتراطات سحب عينة", context, false);
                        }
                    } else {
                        if (sample_data == -1) {
                            AlertDialog("لا يوجد بالاشتراطات سحب عينة", context, false);
                        } else if (sample_data != 0 && Committee_Type_Id == 3) {

                            Intent i = new Intent(context, MainActivity_SampleWithDraw.class);
                            ((Activity) context).startActivity(i);

                        } else {
                            AlertDialog("برجاء فحص الشحنة اولا ", context, false);
                        }
                    }
//                Intent i = new Intent(context, MainActivity_SampleWithDraw.class);
//                        ((Activity) context).startActivity(i);
                } else {
                    if (RequestCommittee_Status_Id == 1) {
                        Intent i = new Intent(context, MainActivity_Confirm.class);
                        ((Activity) context).startActivity(i);
                    } else if (RequestCommittee_Status_Id == 0) {
                        AlertDialog("لم يتم سحب عينة", context, false);
                    }
                }
            }
        } else if (id == R.id.treatment_title) {

            if (context instanceof MainActivity_Listofchipment) {
                AlertDialog("رجاء اختيار الشحنة", context, false);
            } else if (context instanceof MainActivity_Ex_RequestCommitteeResult) {
                if (treatment_data == -1) {
                    AlertDialog("لا يوجد بالاشتراطات معالجة", context, false);
                } else {
                    Intent i = new Intent(context, MainActivity_TreatmentStatement.class);
                    ((Activity) context).startActivity(i);
                }
            } else if (context instanceof MainActivity_SampleWithDraw) {
                if (treatment_data == -1) {
                    AlertDialog("لا يوجد بالاشتراطات معالجة", context, false);
                } else if (treatment_data != 0) {
                    Intent i = new Intent(context, MainActivity_TreatmentStatement.class);
                    ((Activity) context).startActivity(i);
                }
            } else if (context instanceof MainActivity_TreatmentStatement) {

                if (treatment_data > 0) {
                    Intent i = new Intent(context, MainActivity_TreatmentStatement.class);
                    ((Activity) context).startActivity(i);
                }
            } else if (context instanceof MainActivity_DetailsListOfChimpments) {
                AlertDialog("رجاء اختيار النبات او المنتج ....", context, false);
            } else if (context instanceof MainActivity_subdetails) {
                if (Is_Admin) {
                    if (RequestCommittee_Status_Id == 1) {
                        if (treatment_data == 0) {
                            AlertDialog("تم المعالجة", context, false);
                        } else {
                            AlertDialog("لا يوجد بالاشتراطات معالجة", context, false);
                        }
                    } else {
                        if (treatment_data == -1) {
                            AlertDialog("لا يوجد بالاشتراطات معالجة", context, false);
                        } else if (treatment_data != 0 && Committee_Type_Id == 6) {

                            Intent i = new Intent(context, MainActivity_TreatmentStatement.class);
                            ((Activity) context).startActivity(i);
                        } else {
                            AlertDialog("برجاء فحص الشحنة اولا ", context, false);
                        }
                    }
//                Intent i = new Intent(context, MainActivity_TreatmentStatement.class);
//                       ((Activity) context).startActivity(i);

                } else {
                    if (RequestCommittee_Status_Id == 1) {
                        Intent i = new Intent(context, MainActivity_Confirm.class);
                        ((Activity) context).startActivity(i);
                    } else if (RequestCommittee_Status_Id == 0) {
                        AlertDialog("لم يتم معالجة", context, false);
                    }
                }
            }
        } else if (id == R.id.confirm_title) {

            if (context instanceof MainActivity_Listofchipment) {
                AlertDialog("رجاء اختيار الشحنة", context, false);
            } else if (context instanceof MainActivity_TreatmentStatement) {
                AlertDialog("ليس لديك صلاحية ", context, false);
            } else if (context instanceof MainActivity_SampleWithDraw) {
                AlertDialog("ليس لديك صلاحية ", context, false);
            } else if (context instanceof MainActivity_Ex_RequestCommitteeResult) {
                AlertDialog("ليس لديك صلاحية ", context, false);
            } else if (context instanceof MainActivity_DetailsListOfChimpments) {
                AlertDialog("رجاء اختيار النبات او المنتج ....", context, false);
            } else if (context instanceof MainActivity_subdetails) {
                if (Is_Admin) {
                    if (RequestCommittee_Status_Id == 1) {
                        AlertDialog("ليس لديك صلاحية", context, false);
                    } else if (RequestCommittee_Status_Id == 0) {
                        AlertDialog("برجاء العمل علي الشحنةاولا", context, false);
                    }
//                Intent i = new Intent(context, MainActivity_Ex_RequestCommitteeResult.class);
//                  ((Activity) context).startActivity(i);
                } else {
                    if (RequestCommittee_Status_Id == 1) {
                        Intent i = new Intent(context, MainActivity_Confirm.class);
                        ((Activity) context).startActivity(i);
                    } else if (RequestCommittee_Status_Id == 0) {
                        AlertDialog("لم يتم العمل علي الشحنة", context, false);

                    }
                }


            }


        } else if (id == R.id.Committee_title) {
            if (context instanceof MainActivity_Listofchipment) {
                AlertDialog("رجاء اختيار الشحنة", context, false);
            } else if (context instanceof MainActivity_TreatmentStatement) {
                if (request_data == 0) {
                    AlertDialog("تم الفحص  ", context, false);
                } else {
                    AlertDialog(" تم العمل عليها ", context, false);
                }
            } else if (context instanceof MainActivity_SampleWithDraw) {
                AlertDialog("تم الفحص ", context, false);
            } else if (context instanceof MainActivity_DetailsListOfChimpments) {
                AlertDialog("رجاء اختيار النبات او المنتج ....", context, false);
            } else if (context instanceof MainActivity_subdetails) {
                if (Is_Admin) {
                    if (RequestCommittee_Status_Id == 1) {

                        AlertDialog("تم فحص الشحنة", context, false);
                    } else if (RequestCommittee_Status_Id == 0) {
                        if (Committee_Type_Id == 1 || Committee_Type_Id == 2) {
                            Intent i = new Intent(context, MainActivity_Ex_RequestCommitteeResult.class);
                            ((Activity) context).startActivity(i);
                        } else {
                            AlertDialog("ليس لديك صلاحية", context, false);
                        }
                    }
//                Intent i = new Intent(context, MainActivity_Ex_RequestCommitteeResult.class);
//                  ((Activity) context).startActivity(i);
                } else {
                    if (RequestCommittee_Status_Id == 1) {
                        Intent i = new Intent(context, MainActivity_Confirm.class);
                        ((Activity) context).startActivity(i);
                    } else if (RequestCommittee_Status_Id == 0) {
                        AlertDialog("لم يتم فحص الشحنة", context, false);

                    }
                }


            }

        } else if (id == R.id.todolist) {
            Intent i = new Intent(context, MainActivity_Listofchipment.class);
            ((Activity) context).startActivity(i);
        } else if (id == R.id.logout) {
            logoutoffline(context);
            logout(context, ipadrass, Token);
        } else if (id == R.id.nav_send) {

        }
    }

    public void NavMenuClickgetsqlite(final Context context, int id, Long Item_ID, Long EmpId, long checkRequestId) throws JSONException {
        plantQurDBHelper = new PlantQurDBHelper(context);
        if (id == R.id.language) {

        } else if (id == R.id.sample_title) {
            if (context instanceof MainActivity_Listofchipment) {
                AlertDialog("رجاء اختيار الشحنة", context, false);
            } else if (context instanceof MainActivity_DetailsListOfChimpments) {
                      AlertDialog("رجاء اختيار النبات او المنتج ....", context, false);
            } else if (context instanceof MainActivity_subdetails) {
                boolean ISadmin = plantQurDBHelper.getISAdmin(EmpId, checkRequestId);
                if (ISadmin) {
                    int count = plantQurDBHelper.Get_Data_For_Items_RetutnInt("Ischeck", Item_ID);
                    if (count == 0) {
                        count = plantQurDBHelper.Get_Data_For_Items_RetutnInt("Isanalysis", Item_ID);
                        if (count > 0) {
                            Intent i = new Intent(context, MainActivity_SampleWithDraw.class);
                            ((Activity) context).startActivity(i);
                        } else {
                            AlertDialog("لو يوجد سحب عينات", context, false);
                        }
                    } else {
                        AlertDialog("برجاء الفحص اولا ", context, false);
                    }
                } else {
                    AlertDialog("ليس لديك صلاحية", context, false);
                }
            }
        } else if (id == R.id.treatment_title) {
            if (context instanceof MainActivity_Listofchipment) {
                AlertDialog("رجاء اختيار الشحنة", context, false);
            } else if (context instanceof MainActivity_DetailsListOfChimpments) {
                AlertDialog("رجاء اختيار النبات او المنتج ....", context, false);
            }
//            else if (context instanceof MainActivity_TreatmentStatement) {
//                boolean ISadmin = plantQurDBHelper.getISAdmin(EmpId, checkRequestId);
//                if (ISadmin) {
//                    int treatment_count = plantQurDBHelper.Get_Data_For_Items_RetutnInt("Istreatment", Item_ID);
//                    if (treatment_count > 0) {
//                        Intent i = new Intent(context, MainActivity_TreatmentStatement.class);
//                        ((Activity) context).startActivity(i);
//                    } else {
//
//                    }
//                } else {
//
//                }
//            }
            else if (context instanceof MainActivity_subdetails) {
                boolean ISadmin = plantQurDBHelper.getISAdmin(EmpId, checkRequestId);
                if (ISadmin) {
                    int check_count = plantQurDBHelper.Get_Data_For_Items_RetutnInt("Ischeck", Item_ID);
                    if (check_count == 0) {
                        int treatment_count = plantQurDBHelper.Get_Data_For_Items_RetutnInt("Istreatment", Item_ID);
                        if (treatment_count > 0) {
                            Intent i = new Intent(context, MainActivity_TreatmentStatement.class);
                            ((Activity) context).startActivity(i);
                        } else {
                            AlertDialog("لا يوجد معالجة ", context, false);

                        }
                    } else {
                        AlertDialog("يجب انهاء الفحص اولا", context, false);
                    }
                } else {
                    AlertDialog("ليس لديك صلاحية", context, false);
                }
            }
        } else if (id == R.id.confirm_title) {

            if (context instanceof MainActivity_Listofchipment) {
                AlertDialog("رجاء اختيار الشحنة", context, false);
            } else if (context instanceof MainActivity_TreatmentStatement) {
                AlertDialog("ليس لديك صلاحية ", context, false);
            } else if (context instanceof MainActivity_SampleWithDraw) {
                AlertDialog("ليس لديك صلاحية ", context, false);
            } else if (context instanceof MainActivity_Ex_RequestCommitteeResult) {
                AlertDialog("ليس لديك صلاحية ", context, false);
            } else if (context instanceof MainActivity_DetailsListOfChimpments) {
                AlertDialog("رجاء اختيار النبات او المنتج ....", context, false);
            } else if (context instanceof MainActivity_subdetails) {
                boolean ISadmin = plantQurDBHelper.getISAdmin(EmpId, checkRequestId);
                if (ISadmin) {
                    AlertDialog("ليس لديك صلاحية", context, false);
                } else {
                    int has_result = plantQurDBHelper.Get_Data_For_Items_RetutnInt("Has_Result", Item_ID);
                    if (has_result > 0) {
                        AlertDialog("لم يتم العمل عليه", context, false);
                    } else {
                        Intent i = new Intent(context, MainActivity_Confirm.class);
                        ((Activity) context).startActivity(i);
                    }
                }
            }

        } else if (id == R.id.Committee_title) {
            if (context instanceof MainActivity_Listofchipment) {
                AlertDialog("رجاء اختيار الشحنة", context, false);
            }
//            else if (context instanceof MainActivity_TreatmentStatement) {
//                AlertDialog("تم الفحص ", context, false);
//            } else if (context instanceof MainActivity_SampleWithDraw) {
//                AlertDialog("تم الفحص ", context, false);
//            }
            else if (context instanceof MainActivity_DetailsListOfChimpments) {
                AlertDialog("رجاء اختيار النبات او المنتج ....", context, false);
            } else if (context instanceof MainActivity_subdetails) {
                boolean ISadmin = plantQurDBHelper.getISAdmin(EmpId, checkRequestId);
                if (ISadmin) {
                    int check_count = plantQurDBHelper.Get_Data_For_Items_RetutnInt("Ischeck", Item_ID);
                    if (check_count > 0) {
                        Intent i = new Intent(context, MainActivity_Ex_RequestCommitteeResult.class);
                        ((Activity) context).startActivity(i);
                    } else {
                        AlertDialog("تم الفحص ", context, false);
                    }
                } else {
                    AlertDialog("ليس لديك صلاحية ", context, false);
                }
            }

        } else if (id == R.id.todolist) {
            if (context instanceof MainActivity_Listofchipment) {
                Intent i = new Intent(context, MainActivity_Listofchipment.class);
                ((Activity) context).startActivity(i);
            } else {
                AlertDialog("لا يمكنك الانتقال قبل الانتهاء من العمل", context, false);
            }

        } else if (id == R.id.logout) {
            logoutoffline(context);
        } else if (id == R.id.nav_send) {

        }
    }

    public void NavMenuClickgetsqlite(final Context context) throws JSONException {
        //for offline
        logoutoffline(context);
    }

    //online
//    public void NavMenuClickgetsqlite(final Context context,String ipadrass,long Emp_id) throws JSONException {
//        //for online
//        plantQurDBHelper=new PlantQurDBHelper(context);
//     String Token= plantQurDBHelper.Get_Data_for_Emp_working("EmpToken",Emp_id);
//        logout(context,ipadrass,Token);
//    }
    public void NavMenuClickgetsqlite(final Context context, String ipadrass, String Token) throws JSONException {
        //for online
//        plantQurDBHelper=new PlantQurDBHelper(context);
//     String Token= plantQurDBHelper.Get_Data_for_Emp_working("EmpToken",Emp_id);
        logout(context, ipadrass, Token);
    }

    public Location getlocation(Context context, LocationManager manager) {
        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 200);

        }

        Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location == null) {
            location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        if (location == null) {
            location = new Location("");
            location.setLongitude(0);
            location.setLatitude(0);
        }
        return location;
    }

    public void logout(final Context context, String ipadrass, String Token) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Token", Token);

        DataManger = new DataManger(context);
        DataManger.SendVolleyRequestJsonObjectPut(context, Request.Method.PUT, ipadrass + ApiCall.UrlLogin, jsonObject, new IDataValue() {
            @Override
            public void Success(Object response) {

                Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void Error(VolleyError error) {

            }
        });
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreference", 0);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.clear();
        prefsEditor.commit();
        Intent i = new Intent(context, LogIn.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ((Activity) context).startActivity(i);
        ((Activity) context).finish();

    }

    public void logoutoffline(final Context context) throws JSONException {
        PlantQurDBHelper plantQurDBHelper = new PlantQurDBHelper(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("SharedPreference", 0);
        plantQurDBHelper.ubdateLogout_LoginEmployee(sharedPreferences.getLong("EmpId", 0));
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.clear();
        prefsEditor.commit();
        Intent i = new Intent(context, LogIn.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ((Activity) context).startActivity(i);
        ((Activity) context).finish();

    }
}
