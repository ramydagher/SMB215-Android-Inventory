package smb215.isae.inventory.beans;

import java.io.Serializable;

/**
 * Created by Ramy on 8/25/2014.
 */
public class Customer implements Serializable {

    private int ID;
    private String Name;
    private String CompanyName;
    private String Phone;
    private String Fax;
    private String Email;
    private String BillingAddress;
    private String ShippingAddress;

    public Customer() {
    }

    public Customer( String name, String companyName, String phone, String fax, String email, String billingAddress,String shippingAddress) {

        Name = name;
        CompanyName = companyName;
        Phone = phone;
        Fax = fax;
        Email = email;
        BillingAddress = billingAddress;
        ShippingAddress = shippingAddress;
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

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
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

    public String getShippingAddress() {
        return ShippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        ShippingAddress = shippingAddress;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", CompanyName='" + CompanyName + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Fax='" + Fax + '\'' +
                ", Email='" + Email + '\'' +
                ", BillingAddress='" + BillingAddress + '\'' +
                ", ShippingAddress='" + ShippingAddress + '\'' +
                '}';
    }
}
