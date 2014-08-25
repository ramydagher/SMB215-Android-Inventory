package smb215.isae.inventory;

/**
 * Created by Ramy on 8/25/2014.
 */
public class Customer {

    private int ID;
    private String Name;
    private String Address;
    private String TelNumber;


    public Customer(int ID, String name, String address, String telNumber) {
        this.ID = ID;
        Name = name;
        Address = address;
        TelNumber = telNumber;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTelNumber() {
        return TelNumber;
    }

    public void setTelNumber(String telNumber) {
        TelNumber = telNumber;
    }




}
