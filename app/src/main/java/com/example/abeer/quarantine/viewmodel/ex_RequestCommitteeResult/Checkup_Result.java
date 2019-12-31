package com.example.abeer.quarantine.viewmodel.ex_RequestCommitteeResult;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class Checkup_Result extends BaseObservable{

     String Checkup;
     int Count;
     double Weight_ten;
    double Weight_kelo;
    double Weight_gram;
     int Result_ID;
     String  Comment ;
     int Kingdom_ID;
     int Phylum_ID;
     int Order_ID;
     int Family_ID;
     int Result_injury;
     Long lot_ID;
     String Address;
     double Longitude ,Latitude ;
    public  Checkup_Result()
    {

    }

    public Checkup_Result(Checkup_Result Checkup_Result) {

        Checkup = Checkup_Result.Checkup;
        Count = Checkup_Result.Count;
        Weight_gram=Checkup_Result.Weight_gram;
        Weight_kelo=Checkup_Result.Weight_kelo;
        Weight_ten=Checkup_Result.Weight_ten;
        Result_ID = Checkup_Result.Result_ID;
        Comment = Checkup_Result.Comment;
        Kingdom_ID = Checkup_Result.Kingdom_ID;
        Phylum_ID = Checkup_Result.Phylum_ID;
        Order_ID = Checkup_Result.Order_ID;
        Family_ID = Checkup_Result.Family_ID;
        Result_injury=Checkup_Result.Result_injury;
        lot_ID=Checkup_Result.lot_ID;
        Latitude=Checkup_Result.Latitude;
        Longitude=Checkup_Result.Longitude;
        Address=Checkup_Result.Address;
//        Committee_ID=Checkup_Result.Committee_ID;
//        EmployeeId=Checkup_Result.EmployeeId;
//        notifyPropertyChanged(BR.checkUpResult);

    }

    @Bindable
    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
//        notifyPropertyChanged(BR.address);
    }
@Bindable
    public double getWeight_ten() {
        return Weight_ten;
    }

    public void setWeight_ten(double weight_ten) {
        Weight_ten = weight_ten;
    }
@Bindable
    public double getWeight_kelo() {
        return Weight_kelo;
    }
    public double getWeight() {
        return getWeight_kelo()+(getWeight_gram()*1000)+(getWeight_ten()/1000);
    }
    public void setWeight_kelo(double weight_kelo) {
        Weight_kelo = weight_kelo;
    }
@Bindable
    public double getWeight_gram() {
        return Weight_gram;
    }

    public void setWeight_gram(double weight_gram) {
        Weight_gram = weight_gram;
    }

    public Long getLot_ID() {
        return lot_ID;
    }

    public void setLot_ID(Long lot_ID) {
        this.lot_ID = lot_ID;
    }

    @Bindable
    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
//        notifyPropertyChanged(BR.latitude);
    }


    @Bindable
    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
//        notifyPropertyChanged(BR.longitude);
    }

    @Bindable
    public String getCheckup() {
        return Checkup;
    }


    public void setCheckup(String checkup) {
        Checkup = checkup;
//        notifyPropertyChanged(BR.checkup);

    }

//    public long getCommittee_ID() {
//        return Committee_ID;
//    }
//
//    public void setCommittee_ID(long committee_ID) {
//        Committee_ID = committee_ID;
//    }
//
//    public long getEmployeeId() {
//        return EmployeeId;
//    }
//
//    public void setEmployeeId(long employeeId) {
//        EmployeeId = employeeId;
//    }

    @Bindable
    public int getResult_injury() {
        return Result_injury;
    }

    public void setResult_injury(int result_injury) {
        Result_injury = result_injury;
//        notifyPropertyChanged(BR.result_injury);
    }

    @Bindable
    public int getCount() {
        return Count;
    }

    @Bindable
    public long getlot_ID() {
        return lot_ID;
    }

    public void setlot_ID(long lotID) {
        lot_ID = lotID;
//        notifyPropertyChanged(BR.lot_ID);
    }

    public void setCount(int count) {
        Count =count;
//        notifyPropertyChanged(BR.count);
    }

//    @Bindable
//    public Double getWeight() {
//        return Weight;
//    }
//
//
//    public void setWeight(Double weight) {
//       Weight =weight;
////      notifyPropertyChanged(BR.weight);
//
//    }

    @Bindable
    public int getResult_ID() {
        return Result_ID;
    }


    public void setResult_ID(int result_ID) {
        Result_ID = result_ID;
//      notifyPropertyChanged(BR.result_ID);

    }

    @Bindable
    public String getComment() {
        return Comment;
    }


    public void setComment(String comment) {
        Comment = comment;
//        notifyPropertyChanged(BR.comment);

    }

    @Bindable
    public int getKingdom_ID() {
        return Kingdom_ID;
    }


    public void setKingdom_ID(int kingdom_ID) {
        Kingdom_ID = kingdom_ID;
//      notifyPropertyChanged(BR.kingdom_ID);

    }

    @Bindable
    public int getPhylum_ID() {
        return Phylum_ID;
    }


    public void setPhylum_ID(int phylum_ID) {
        Phylum_ID = phylum_ID;
//         notifyPropertyChanged(BR.phylum_ID);

    }

    @Bindable
    public int getOrder_ID() {
        return Order_ID;
    }

  public void setOrder_ID(int order_ID) {
        Order_ID = order_ID;
//     notifyPropertyChanged(BR.order_ID);

    }

    @Bindable
    public int getFamily_ID() {
        return Family_ID;
    }


    public void setFamily_ID(int family_ID) {
        Family_ID = family_ID;
//         notifyPropertyChanged(BR.family_ID);
    }
}
