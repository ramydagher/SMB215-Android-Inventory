package smb215.isae.inventory.beans;

/**
 * Created by Ramy on 8/31/2014.
 */
public class Product {

    private int ID;
    private String Barcode;
    private String Code;
    private String Description;
    private int Quantity;
    private int Price;
    private int LocationID;

    public Product() {

    }

    public Product(String barcode, String code, String description, int locationID, int quantity, int price) {
        Barcode = barcode;
        Code = code;
        Description = description;
        LocationID = locationID;
        Quantity = quantity;
        Price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getLocationID() {
        return LocationID;
    }

    public void setLocationID(int locationID) {
        LocationID = locationID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

}
