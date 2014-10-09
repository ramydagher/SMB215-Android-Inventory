package smb215.isae.inventory.beans;

import java.util.Date;

/**
 * Created by Ramy on 10/9/2014.
 */
public class purchaseOrder {

    private int ID;
    private String Serial;
    private int SupplierID;
    private java.util.Date Date;
    private double TotalPrice;
    private double Discount;
    private double NetTotal;
    private String Status;

    public purchaseOrder() {

    }

    public purchaseOrder(String serial, int supplierID, java.util.Date date, double totalPrice, double discount, double netTotal, String status) {
        Serial = serial;
        SupplierID = supplierID;
        Date = date;
        TotalPrice = totalPrice;
        Discount = discount;
        NetTotal = netTotal;
        Status = status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSerial() {
        return Serial;
    }

    public void setSerial(String serial) {
        Serial = serial;
    }

    public int getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(int supplierID) {
        SupplierID = supplierID;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        Date = date;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public double getNetTotal() {
        return NetTotal;
    }

    public void setNetTotal(double netTotal) {
        NetTotal = netTotal;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "purchaseOrder{" +
                "ID=" + ID +
                ", Serial='" + Serial + '\'' +
                ", SupplierID=" + SupplierID +
                ", Date=" + Date +
                ", TotalPrice=" + TotalPrice +
                ", Discount=" + Discount +
                ", NetTotal=" + NetTotal +
                ", Status='" + Status + '\'' +
                '}';
    }
}
