package smb215.isae.inventory.beans;

import java.io.Serializable;

/**
 * Created by Ramy on 8/31/2014.
 */
public class productLocation implements Serializable {

    private int ID;
    private String Name;

    public productLocation() {
    }

    public productLocation(int ID, String name) {
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

    @Override
    public String toString() {
        return "productLocation{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                '}';
    }
}
