package smb215.isae.inventory.beans;

/**
 * Created by Ramy on 8/31/2014.
 */
public class ProductLocation {

    private int ID;
    private String Name;

    public ProductLocation() {
    }

    public ProductLocation(int ID, String name) {
        this.ID = ID;
        Name = name;
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

}
