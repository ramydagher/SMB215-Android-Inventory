package smb215.isae.inventory.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ramy on 10/9/2014.
 */
public class salesOrder implements Serializable{

    private int ID;
    private String Serial;
    private int CustomerID;
    private Date Date;
    private double TotalPrice;
    private double Discount;
    private double NetTotal;
    private String Status;

    public salesOrder() {

    }
    public salesOrder(String serial, int customerID, java.util.Date date, float totalPrice, float discount, float netTotal, String status) {
        Serial = serial;
        CustomerID = customerID;
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

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
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
        return "salesOrder{" +
                "ID=" + ID +
                ", Serial='" + Serial + '\'' +
                ", CustomerID=" + CustomerID +
                ", Date=" + Date +
                ", TotalPrice=" + TotalPrice +
                ", Discount=" + Discount +
                ", NetTotal=" + NetTotal +
                ", Status='" + Status + '\'' +
                '}';
    }
}
