package smb215.isae.inventory.beans;

import java.io.Serializable;

/**
 * Created by Ramy on 8/25/2014.
 */
public class supplier implements Serializable {

    private int ID;
    private String Name;
    private String CompanyName;
    private String Phone;
    private String Email;
    private String BillingAddress;


    public supplier() {
    }

    public supplier(String name, String companyName, String phone, String fax, String email, String billingAddress) {

        Name = name;
        CompanyName = companyName;
        Phone = phone;
        Email = email;
        BillingAddress = billingAddress;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getBillingAddress() {
        return BillingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        BillingAddress = billingAddress;
    }

    @Override
    public String toString() {
        return "supplier{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", CompanyName='" + CompanyName + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Email='" + Email + '\'' +
                ", BillingAddress='" + BillingAddress + '\'' +
                '}';
    }
}
