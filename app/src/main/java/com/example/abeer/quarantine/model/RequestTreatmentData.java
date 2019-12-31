package com.example.abeer.quarantine.model;

public class RequestTreatmentData {
//       //
//         "$id": "2",
//                 "row_num": 1,
//                 "checkRequest_Id": 40035,
//                 "IsExport": 1,
//                 "Item_Data": "<_x0040_Item_Data Item_number=\"57\" Item_Type=\"33\" Item_Id=\"1036\" Item_Name=\"88\" Scientific_Name=\"-----------\" Item_Cat_Name=\"-----------\" Item_Strain=\"-----------\" ItemStatus=\"بند6\" ItemStatus_ID=\"6\" ItemPurpose=\"الزراعة\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                 "<_x0040_Item_Data Item_number=\"58\" Item_Type=\"33\" Item_Id=\"1033\" Item_Name=\"12\" Scientific_Name=\"-----------\" Item_Cat_Name=\"-----------\" ItemPartTypeName=\"23\" Item_Strain=\"-----------\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"111\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" /> " +
//                 "<_x0040_Item_Data Item_number=\"59\" Item_Type=\"33\" Item_Id=\"1033\" Item_Name=\"12\" Scientific_Name=\"-----------\" Item_Cat_Name=\"-----------\" ItemPartTypeName=\"3333\" Item_Strain=\"-----------\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"33\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                 "<_x0040_Item_Data Item_number=\"60\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"8598\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"البحث العلمي\" Item_ShortName=\"الاسم المختصر 4\" Purpose_ID=\"4\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                 "<_x0040_Item_Data Item_number=\"61\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"88\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"البحث العلمي\" Item_ShortName=\"الاسم المختصر 5&#x9;\" Purpose_ID=\"4\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                 "<_x0040_Item_Data Item_number=\"62\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"1111\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"111\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                 "<_x0040_Item_Data Item_number=\"63\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"222\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"22\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                 "<_x0040_Item_Data Item_number=\"64\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"2222\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"الاسم المختصر 3\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                 "<_x0040_Item_Data Item_number=\"65\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"44\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"50\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                 "<_x0040_Item_Data Item_number=\"66\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"44\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"الاسم المختصر 7&#x9;\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                 "<_x0040_Item_Data Item_number=\"67\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"4444\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"4444\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                 "<_x0040_Item_Data Item_number=\"68\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"4444\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"الاسم المختصر 6&#x9;\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                 "<_x0040_Item_Data Item_number=\"69\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"555\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"555\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                 "<_x0040_Item_Data Item_number=\"70\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"5555\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"55\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                 "<_x0040_Item_Data Item_number=\"71\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"667765\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"الاسم المختصر 2\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                 "<_x0040_Item_Data Item_number=\"72\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"76876876\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"عغهغهغف\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />" +
//                 "<_x0040_Item_Data Item_number=\"73\" Item_Type=\"16\" Item_Id=\"4\" Item_Name=\"بند3\" Scientific_Name=\"-----------\" Item_Cat_Name=\"طور2\" ItemPartTypeName=\"99\" Item_Strain=\"سلالة 1\" ItemStatus=\"بند1\" ItemStatus_ID=\"4\" ItemPurpose=\"الزراعة\" Item_ShortName=\"99\" Purpose_ID=\"1\" PlantCat_ID=\"0\" plantPart_ID=\"0\" plantPartType=\"0\" />",
//                 "Analysis_Total": 0,
//                 "Treatment_Total": 0,
//                 "Check_Total": 4
//    //
         String $id;
         int row_num;
         int checkRequest_Id;
         int IsExport;
         String Item_Data;
         int Analysis_Total;
         int Treatment_Total;
         int Check_Total;

    public RequestTreatmentData() {

    }

    public RequestTreatmentData(RequestTreatmentData requestTreatmentData) {
        this.$id = requestTreatmentData.$id;
        this.row_num = requestTreatmentData.row_num;
        this.checkRequest_Id = requestTreatmentData.checkRequest_Id;
        IsExport = requestTreatmentData.IsExport;
        Item_Data = requestTreatmentData.Item_Data;
        Analysis_Total = requestTreatmentData.Analysis_Total;
        Treatment_Total = requestTreatmentData.Treatment_Total;
        Check_Total = requestTreatmentData.Check_Total;
    }

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public int getRow_num() {
        return row_num;
    }

    public void setRow_num(int row_num) {
        this.row_num = row_num;
    }

    public int getCheckRequest_Id() {
        return checkRequest_Id;
    }

    public void setCheckRequest_Id(int checkRequest_Id) {
        this.checkRequest_Id = checkRequest_Id;
    }

    public int getIsExport() {
        return IsExport;
    }

    public void setIsExport(int isExport) {
        IsExport = isExport;
    }

    public String getItem_Data() {
        return Item_Data;
    }

    public void setItem_Data(String item_Data) {
        Item_Data = item_Data;
    }

    public int getAnalysis_Total() {
        return Analysis_Total;
    }

    public void setAnalysis_Total(int analysis_Total) {
        Analysis_Total = analysis_Total;
    }

    public int getTreatment_Total() {
        return Treatment_Total;
    }

    public void setTreatment_Total(int treatment_Total) {
        Treatment_Total = treatment_Total;
    }

    public int getCheck_Total() {
        return Check_Total;
    }

    public void setCheck_Total(int check_Total) {
        Check_Total = check_Total;
    }
}
