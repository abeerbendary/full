package com.example.abeer.quarantine.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.example.abeer.quarantine.BR;
public class ListDetailsCheckRequestNew extends BaseObservable {
    public String $id;
    public String CheckRequest_Number;
    public String Reciever_Name;
    public String ImportCompany;
    public String ImportCountry_Name;
    public String Outlet_Name;
    public String Govern_Name;
    public String General_Admin_Name;
    public String PortNational_Shippment_Name;
    public int Exporter_ID;
    public int ExporterType_Id;
    public String  ExporterType_Name;
    public int Importer_Exporter_Id;
    public String Importer_Exporter_Name;
    public String port_arrive_Name;
    public String port_transite_Name;
    public String c_transite_country_Name;
    public String Shipment_Mean_Name;
    public String Transport_Mean_Name;
    public String Ship_Name;
    public int Request_ApprovedUnapprovedID;
    public boolean Request_ApprovedUnapprovedType;
    public String Request_ApprovedUnapproved_StationName;
    public String Request_ApprovedUnapproved_Place_Ar_Name;
    public String Request_ApprovedUnapproved_Place_Address_Ar;
    public String Committee_Type;
    public String Check_Date;
    public String RequestCommittee_Status;
    public String Item_Data;
    public  int CheckRequest_Id;
    public  String Attachment_Data;
    public  String Exporter_Address;
    public String ImportCompany_Address;
    public boolean IsAccepted;
    public int TransitCountry;
    public boolean IsAcceppted;
    public String ImportCompany_data;
    public  String Request_Fees;
    public  String ExportRequest_xml;
    public   String AttachmentData_Xml;
    public     String Item_Data_xml;
    public   String ImportCompany_xml;
    public  int IsExport;

    public ListDetailsCheckRequestNew() {

    }
    //Item_Data=null
    public ListDetailsCheckRequestNew(boolean IsOffline,ListDetailsCheckRequestNew L) {
        this.$id = L.$id;
        CheckRequest_Number = L.CheckRequest_Number;
        Reciever_Name = L.Reciever_Name;
        ImportCompany = L.ImportCompany;
        ImportCountry_Name = L.ImportCountry_Name;
        Outlet_Name = L.Outlet_Name;
        Govern_Name = L.Govern_Name;
        General_Admin_Name = L.General_Admin_Name;
        PortNational_Shippment_Name = L.PortNational_Shippment_Name;
        Exporter_ID = L.Exporter_ID;
        ExporterType_Id =L. ExporterType_Id;
        ExporterType_Name = L.ExporterType_Name;
        Importer_Exporter_Id = L.Importer_Exporter_Id;
        Importer_Exporter_Name = L.Importer_Exporter_Name;
        this.port_arrive_Name = L.port_arrive_Name;
        this.port_transite_Name = L.port_transite_Name;
        this.c_transite_country_Name =L.c_transite_country_Name;
        Shipment_Mean_Name = L.Shipment_Mean_Name;
        Transport_Mean_Name = L.Transport_Mean_Name;
        Ship_Name = L.Ship_Name;
        Request_ApprovedUnapprovedID = L.Request_ApprovedUnapprovedID;
        Request_ApprovedUnapprovedType = L.Request_ApprovedUnapprovedType;
        Request_ApprovedUnapproved_StationName = L.Request_ApprovedUnapproved_StationName;
        Request_ApprovedUnapproved_Place_Ar_Name = L.Request_ApprovedUnapproved_Place_Ar_Name;
        Request_ApprovedUnapproved_Place_Address_Ar =L. Request_ApprovedUnapproved_Place_Address_Ar;
        Committee_Type = L.Committee_Type;
        Check_Date = L.Check_Date;
        RequestCommittee_Status = L.RequestCommittee_Status;
        Item_Data =null;
        CheckRequest_Id = L.CheckRequest_Id;
        Attachment_Data = L.Attachment_Data;
        Exporter_Address = L.Exporter_Address;
        ImportCompany_Address = L.ImportCompany_Address;
        IsAccepted = L.IsAccepted;
        TransitCountry = L.TransitCountry;
        IsAcceppted = L.IsAcceppted;
        ImportCompany_data = L.ImportCompany_data;
        Request_Fees =L. Request_Fees;
        ExportRequest_xml =L. ExportRequest_xml;
        AttachmentData_Xml = L.AttachmentData_Xml;
        Item_Data_xml =L.Item_Data_xml;
        ImportCompany_xml = L.ImportCompany_xml;
        IsExport = L.IsExport;
    }
    public ListDetailsCheckRequestNew(ListDetailsCheckRequestNew L) {
        this.$id = L.$id;
        CheckRequest_Number = L.CheckRequest_Number;
        Reciever_Name = L.Reciever_Name;
        ImportCompany = L.ImportCompany;
        ImportCountry_Name = L.ImportCountry_Name;
        Outlet_Name = L.Outlet_Name;
        Govern_Name = L.Govern_Name;
        General_Admin_Name = L.General_Admin_Name;
        PortNational_Shippment_Name = L.PortNational_Shippment_Name;
        Exporter_ID = L.Exporter_ID;
        ExporterType_Id =L. ExporterType_Id;
        ExporterType_Name = L.ExporterType_Name;
        Importer_Exporter_Id = L.Importer_Exporter_Id;
        Importer_Exporter_Name = L.Importer_Exporter_Name;
        this.port_arrive_Name = L.port_arrive_Name;
        this.port_transite_Name = L.port_transite_Name;
        this.c_transite_country_Name =L.c_transite_country_Name;
        Shipment_Mean_Name = L.Shipment_Mean_Name;
        Transport_Mean_Name = L.Transport_Mean_Name;
        Ship_Name = L.Ship_Name;
        Request_ApprovedUnapprovedID = L.Request_ApprovedUnapprovedID;
        Request_ApprovedUnapprovedType = L.Request_ApprovedUnapprovedType;
        Request_ApprovedUnapproved_StationName = L.Request_ApprovedUnapproved_StationName;
        Request_ApprovedUnapproved_Place_Ar_Name = L.Request_ApprovedUnapproved_Place_Ar_Name;
        Request_ApprovedUnapproved_Place_Address_Ar =L. Request_ApprovedUnapproved_Place_Address_Ar;
        Committee_Type = L.Committee_Type;
        Check_Date = L.Check_Date;
        RequestCommittee_Status = L.RequestCommittee_Status;
        Item_Data =L. Item_Data;
        CheckRequest_Id = L.CheckRequest_Id;
        Attachment_Data = L.Attachment_Data;
        Exporter_Address = L.Exporter_Address;
        ImportCompany_Address = L.ImportCompany_Address;
        IsAccepted = L.IsAccepted;
        TransitCountry = L.TransitCountry;
        IsAcceppted = L.IsAcceppted;
        ImportCompany_data = L.ImportCompany_data;
        Request_Fees =L. Request_Fees;
        ExportRequest_xml =L. ExportRequest_xml;
        AttachmentData_Xml = L.AttachmentData_Xml;
        Item_Data_xml =L.Item_Data_xml;
        ImportCompany_xml = L.ImportCompany_xml;
        IsExport = L.IsExport;
    }

    @Bindable
    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }
    @Bindable
    public String getCheckRequest_Number() {
        return CheckRequest_Number;
    }

    public void setCheckRequest_Number(String checkRequest_Number) {
        CheckRequest_Number = checkRequest_Number;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public String getReciever_Name() {
        return Reciever_Name;
    }

    public void setReciever_Name(String reciever_Name) {
        Reciever_Name = reciever_Name;
        notifyPropertyChanged(BR.detaill);
    }

    @Bindable
    public int getIsExport() {
        return IsExport;
    }

    public void setIsExport(int isExport) {
        IsExport = isExport;
    }

    @Bindable
    public String getImportCompany() {
        return ImportCompany;
    }

    public void setImportCompany(String importCompany) {
        ImportCompany = importCompany;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public String getImportCountry_Name() {
        return ImportCountry_Name;
    }

    public void setImportCountry_Name(String importCountry_Name) {
        ImportCountry_Name = importCountry_Name;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public String getOutlet_Name() {
        return Outlet_Name;
    }

    public void setOutlet_Name(String outlet_Name) {
        Outlet_Name = outlet_Name;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public String getGovern_Name() {
        return Govern_Name;
    }

    public void setGovern_Name(String govern_Name) {
        Govern_Name = govern_Name;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public String getGeneral_Admin_Name() {
        return General_Admin_Name;
    }

    public void setGeneral_Admin_Name(String general_Admin_Name) {
        General_Admin_Name = general_Admin_Name;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public String getPortNational_Shippment_Name() {
        return PortNational_Shippment_Name;
    }

    public void setPortNational_Shippment_Name(String portNational_Shippment_Name) {
        PortNational_Shippment_Name = portNational_Shippment_Name;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public int getExporter_ID() {
        return Exporter_ID;
    }

    public void setExporter_ID(int exporter_ID) {
        Exporter_ID = exporter_ID;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public int getExporterType_Id() {
        return ExporterType_Id;
    }

    public void setExporterType_Id(int exporterType_Id) {
        ExporterType_Id = exporterType_Id;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public String getExporterType_Name() {
        return ExporterType_Name;
    }

    public void setExporterType_Name(String exporterType_Name) {
        ExporterType_Name = exporterType_Name;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public int getImporter_Exporter_Id() {
        return Importer_Exporter_Id;
    }

    public void setImporter_Exporter_Id(int importer_Exporter_Id) {
        Importer_Exporter_Id = importer_Exporter_Id;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public String getImporter_Exporter_Name() {
        return Importer_Exporter_Name;
    }

    public void setImporter_Exporter_Name(String importer_Exporter_Name) {
        Importer_Exporter_Name = importer_Exporter_Name;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public String getPort_arrive_Name() {
        return port_arrive_Name;
    }

    public void setPort_arrive_Name(String port_arrive_Name) {
        this.port_arrive_Name = port_arrive_Name;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public String getPort_transite_Name() {
        return port_transite_Name;
    }

    public void setPort_transite_Name(String port_transite_Name) {
        this.port_transite_Name = port_transite_Name;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public String getC_transite_country_Name() {
        return c_transite_country_Name;
    }

    public void setC_transite_country_Name(String c_transite_country_Name) {
        this.c_transite_country_Name = c_transite_country_Name;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public String getShipment_Mean_Name() {
        return Shipment_Mean_Name;
    }

    public void setShipment_Mean_Name(String shipment_Mean_Name) {
        Shipment_Mean_Name = shipment_Mean_Name;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public String getTransport_Mean_Name() {
        return Transport_Mean_Name;
    }

    public void setTransport_Mean_Name(String transport_Mean_Name) {
        Transport_Mean_Name = transport_Mean_Name;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public String getShip_Name() {
        return Ship_Name;
    }

    public void setShip_Name(String ship_Name) {
        Ship_Name = ship_Name;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public int getRequest_ApprovedUnapprovedID() {
        return Request_ApprovedUnapprovedID;

    }

    public void setRequest_ApprovedUnapprovedID(int request_ApprovedUnapprovedID) {
        Request_ApprovedUnapprovedID = request_ApprovedUnapprovedID;
        notifyPropertyChanged(BR.detaill);
    }

    public boolean isRequest_ApprovedUnapprovedType() {
        return Request_ApprovedUnapprovedType;
    }

    public void setRequest_ApprovedUnapprovedType(boolean request_ApprovedUnapprovedType) {
        Request_ApprovedUnapprovedType = request_ApprovedUnapprovedType;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public String getRequest_ApprovedUnapproved_StationName() {
        return Request_ApprovedUnapproved_StationName;
    }

    public void setRequest_ApprovedUnapproved_StationName(String request_ApprovedUnapproved_StationName) {
        Request_ApprovedUnapproved_StationName = request_ApprovedUnapproved_StationName;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public String getRequest_ApprovedUnapproved_Place_Ar_Name() {
        return Request_ApprovedUnapproved_Place_Ar_Name;
    }

    public void setRequest_ApprovedUnapproved_Place_Ar_Name(String request_ApprovedUnapproved_Place_Ar_Name) {
        Request_ApprovedUnapproved_Place_Ar_Name = request_ApprovedUnapproved_Place_Ar_Name;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public String getRequest_ApprovedUnapproved_Place_Address_Ar() {
        return Request_ApprovedUnapproved_Place_Address_Ar;
    }

    public void setRequest_ApprovedUnapproved_Place_Address_Ar(String request_ApprovedUnapproved_Place_Address_Ar) {
        Request_ApprovedUnapproved_Place_Address_Ar = request_ApprovedUnapproved_Place_Address_Ar;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public String getCommittee_Type() {
        return Committee_Type;
    }

    public void setCommittee_Type(String committee_Type) {
        Committee_Type = committee_Type;
        notifyPropertyChanged(BR.detaill);
    }

    @Bindable
    public String getCheck_Date() {
        return Check_Date;
    }

    public void setCheck_Date(String check_Date) {
        Check_Date = check_Date;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public String getRequestCommittee_Status() {
        return RequestCommittee_Status;
    }

    public void setRequestCommittee_Status(String requestCommittee_Status) {
        RequestCommittee_Status = requestCommittee_Status;
        notifyPropertyChanged(BR.detaill);
    }
    @Bindable
    public String getItem_Data() {
        return Item_Data;
    }

    public void setItem_Data(String item_Data) {
        Item_Data = item_Data;
        notifyPropertyChanged(BR.detaill);
    }

    public int getCheckRequest_Id() {
        return CheckRequest_Id;
    }

    public void setCheckRequest_Id(int checkRequest_Id) {
        CheckRequest_Id = checkRequest_Id;
    }

    public String getAttachment_Data() {
        return Attachment_Data;
    }

    public void setAttachment_Data(String attachment_Data) {
        Attachment_Data = attachment_Data;
    }

    public String getExporter_Address() {
        return Exporter_Address;
    }

    public void setExporter_Address(String exporter_Address) {
        Exporter_Address = exporter_Address;
    }

    public String getImportCompany_Address() {
        return ImportCompany_Address;
    }

    public void setImportCompany_Address(String importCompany_Address) {
        ImportCompany_Address = importCompany_Address;
    }

    public boolean isAccepted() {
        return IsAccepted;
    }

    public void setAccepted(boolean accepted) {
        IsAccepted = accepted;
    }

    public int getTransitCountry() {
        return TransitCountry;
    }

    public void setTransitCountry(int transitCountry) {
        TransitCountry = transitCountry;
    }

    public boolean isAcceppted() {
        return IsAcceppted;
    }

    public void setAcceppted(boolean acceppted) {
        IsAcceppted = acceppted;
    }

    public String getImportCompany_data() {
        return ImportCompany_data;
    }

    public void setImportCompany_data(String importCompany_data) {
        ImportCompany_data = importCompany_data;
    }

    public String getRequest_Fees() {
        return Request_Fees;
    }

    public void setRequest_Fees(String request_Fees) {
        Request_Fees = request_Fees;
    }

    public String getExportRequest_xml() {
        return ExportRequest_xml;
    }

    public void setExportRequest_xml(String exportRequest_xml) {
        ExportRequest_xml = exportRequest_xml;
    }

    public String getAttachmentData_Xml() {
        return AttachmentData_Xml;
    }

    public void setAttachmentData_Xml(String attachmentData_Xml) {
        AttachmentData_Xml = attachmentData_Xml;
    }

    public String getItem_Data_xml() {
        return Item_Data_xml;
    }

    public void setItem_Data_xml(String item_Data_xml) {
        Item_Data_xml = item_Data_xml;
    }

    public String getImportCompany_xml() {
        return ImportCompany_xml;
    }

    public void setImportCompany_xml(String importCompany_xml) {
        ImportCompany_xml = importCompany_xml;
    }

//
//    public String $id;
//    public String CheckRequest_Number;
//    public String Reciever_Name;
//    public String ImportCompany;
//    public String ImportCountry_Name;
//    public String Outlet_Name;
//    public String Govern_Name;
//    public String General_Admin_Name;
//    public String PortNational_Shippment_Name;
//    public int Exporter_ID;
//    public int ExporterType_Id;
//    public String  ExporterType_Name;
//    public int Importer_Exporter_Id;
//    public String Importer_Exporter_Name;
//    public String port_arrive_Name;
//    public String port_transite_Name;
//    public String c_transite_country_Name;
//    public String Shipment_Mean_Name;
//    public String Transport_Mean_Name;
//    public String Ship_Name;
//    public int Request_ApprovedUnapprovedID;
//    public boolean Request_ApprovedUnapprovedType;
//    public String Request_ApprovedUnapproved_StationName;
//    public String Request_ApprovedUnapproved_Place_Ar_Name;
//    public String Request_ApprovedUnapproved_Place_Address_Ar;
//    public String Committee_Type;
//    public String Check_Date;
//    public String RequestCommittee_Status;
//    public String Item_Data;
//
//    @Bindable
//    public String get$id() {
//        return $id;
//    }
//
//    public void set$id(String $id) {
//        this.$id = $id;
//    }
//    @Bindable
//    public String getCheckRequest_Number() {
//        return CheckRequest_Number;
//    }
//
//    public void setCheckRequest_Number(String checkRequest_Number) {
//        CheckRequest_Number = checkRequest_Number;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public String getReciever_Name() {
//        return Reciever_Name;
//    }
//
//    public void setReciever_Name(String reciever_Name) {
//        Reciever_Name = reciever_Name;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public String getImportCompany() {
//        return ImportCompany;
//    }
//
//    public void setImportCompany(String importCompany) {
//        ImportCompany = importCompany;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public String getImportCountry_Name() {
//        return ImportCountry_Name;
//    }
//
//    public void setImportCountry_Name(String importCountry_Name) {
//        ImportCountry_Name = importCountry_Name;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public String getOutlet_Name() {
//        return Outlet_Name;
//    }
//
//    public void setOutlet_Name(String outlet_Name) {
//        Outlet_Name = outlet_Name;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public String getGovern_Name() {
//        return Govern_Name;
//    }
//
//    public void setGovern_Name(String govern_Name) {
//        Govern_Name = govern_Name;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public String getGeneral_Admin_Name() {
//        return General_Admin_Name;
//    }
//
//    public void setGeneral_Admin_Name(String general_Admin_Name) {
//        General_Admin_Name = general_Admin_Name;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public String getPortNational_Shippment_Name() {
//        return PortNational_Shippment_Name;
//    }
//
//    public void setPortNational_Shippment_Name(String portNational_Shippment_Name) {
//        PortNational_Shippment_Name = portNational_Shippment_Name;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public int getExporter_ID() {
//        return Exporter_ID;
//    }
//
//    public void setExporter_ID(int exporter_ID) {
//        Exporter_ID = exporter_ID;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public int getExporterType_Id() {
//        return ExporterType_Id;
//    }
//
//    public void setExporterType_Id(int exporterType_Id) {
//        ExporterType_Id = exporterType_Id;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public String getExporterType_Name() {
//        return ExporterType_Name;
//    }
//
//    public void setExporterType_Name(String exporterType_Name) {
//        ExporterType_Name = exporterType_Name;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public int getImporter_Exporter_Id() {
//        return Importer_Exporter_Id;
//    }
//
//    public void setImporter_Exporter_Id(int importer_Exporter_Id) {
//        Importer_Exporter_Id = importer_Exporter_Id;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public String getImporter_Exporter_Name() {
//        return Importer_Exporter_Name;
//    }
//
//    public void setImporter_Exporter_Name(String importer_Exporter_Name) {
//        Importer_Exporter_Name = importer_Exporter_Name;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public String getPort_arrive_Name() {
//        return port_arrive_Name;
//    }
//
//    public void setPort_arrive_Name(String port_arrive_Name) {
//        this.port_arrive_Name = port_arrive_Name;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public String getPort_transite_Name() {
//        return port_transite_Name;
//    }
//
//    public void setPort_transite_Name(String port_transite_Name) {
//        this.port_transite_Name = port_transite_Name;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public String getC_transite_country_Name() {
//        return c_transite_country_Name;
//    }
//
//    public void setC_transite_country_Name(String c_transite_country_Name) {
//        this.c_transite_country_Name = c_transite_country_Name;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public String getShipment_Mean_Name() {
//        return Shipment_Mean_Name;
//    }
//
//    public void setShipment_Mean_Name(String shipment_Mean_Name) {
//        Shipment_Mean_Name = shipment_Mean_Name;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public String getTransport_Mean_Name() {
//        return Transport_Mean_Name;
//    }
//
//    public void setTransport_Mean_Name(String transport_Mean_Name) {
//        Transport_Mean_Name = transport_Mean_Name;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public String getShip_Name() {
//        return Ship_Name;
//    }
//
//    public void setShip_Name(String ship_Name) {
//        Ship_Name = ship_Name;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public int getRequest_ApprovedUnapprovedID() {
//        return Request_ApprovedUnapprovedID;
//
//    }
//
//    public void setRequest_ApprovedUnapprovedID(int request_ApprovedUnapprovedID) {
//        Request_ApprovedUnapprovedID = request_ApprovedUnapprovedID;
//        notifyPropertyChanged(BR.detaill);
//    }
//
//    public boolean isRequest_ApprovedUnapprovedType() {
//        return Request_ApprovedUnapprovedType;
//    }
//
//    public void setRequest_ApprovedUnapprovedType(boolean request_ApprovedUnapprovedType) {
//        Request_ApprovedUnapprovedType = request_ApprovedUnapprovedType;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public String getRequest_ApprovedUnapproved_StationName() {
//        return Request_ApprovedUnapproved_StationName;
//    }
//
//    public void setRequest_ApprovedUnapproved_StationName(String request_ApprovedUnapproved_StationName) {
//        Request_ApprovedUnapproved_StationName = request_ApprovedUnapproved_StationName;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public String getRequest_ApprovedUnapproved_Place_Ar_Name() {
//        return Request_ApprovedUnapproved_Place_Ar_Name;
//    }
//
//    public void setRequest_ApprovedUnapproved_Place_Ar_Name(String request_ApprovedUnapproved_Place_Ar_Name) {
//        Request_ApprovedUnapproved_Place_Ar_Name = request_ApprovedUnapproved_Place_Ar_Name;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public String getRequest_ApprovedUnapproved_Place_Address_Ar() {
//        return Request_ApprovedUnapproved_Place_Address_Ar;
//    }
//
//    public void setRequest_ApprovedUnapproved_Place_Address_Ar(String request_ApprovedUnapproved_Place_Address_Ar) {
//        Request_ApprovedUnapproved_Place_Address_Ar = request_ApprovedUnapproved_Place_Address_Ar;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public String getCommittee_Type() {
//        return Committee_Type;
//    }
//
//    public void setCommittee_Type(String committee_Type) {
//        Committee_Type = committee_Type;
//        notifyPropertyChanged(BR.detaill);
//    }
//
//    @Bindable
//    public String getCheck_Date() {
//        return Check_Date;
//    }
//
//    public void setCheck_Date(String check_Date) {
//        Check_Date = check_Date;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public String getRequestCommittee_Status() {
//        return RequestCommittee_Status;
//    }
//
//    public void setRequestCommittee_Status(String requestCommittee_Status) {
//        RequestCommittee_Status = requestCommittee_Status;
//        notifyPropertyChanged(BR.detaill);
//    }
//    @Bindable
//    public String getItem_Data() {
//        return Item_Data;
//    }
//
//    public void setItem_Data(String item_Data) {
//        Item_Data = item_Data;
//        notifyPropertyChanged(BR.detaill);
//    }
}
