package smb215.isae.inventory.dataaccess;

/**
 * Created by Ramy on 8/25/2014.
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import smb215.isae.inventory.beans.customer;
import smb215.isae.inventory.beans.product;
import smb215.isae.inventory.beans.productLocation;
import smb215.isae.inventory.beans.supplier;

public class databaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "inventory";

    // Tables Structure

    //Product Table
    private static final String TABLE_PRODUCT = "product";
    private static final String COL_PRODUCT_ID = "id";
    private static final String COL_PRODUCT_BARCODE = "barcode";
    private static final String COL_PRODUCT_CODE = "code";
    private static final String COL_PRODUCT_DESCRIPTION = "description";
    private static final String COL_PRODUCT_QUANTITY = "qty";
    private static final String COL_PRODUCT_PRICE = "price";
    private static final String COL_PRODUCT_LOCID = "locationID";

    //Product Location Table
    private static final String TABLE_PRODUCTLOCATION = "productLocation";

    private static final String COL_PRODUCTLOCATION_ID = "id";
    private static final String COL_PRODUCTLOCATION_NAME = "name";

    //Customer Table
    private static final String TABLE_CUSTOMER = "customer";

    private static final String COL_CUSTOMER_ID = "id";
    private static final String COL_CUSTOMER_NAME = "name";
    private static final String COL_CUSTOMER_COMPANYNAME = "companyname";
    private static final String COL_CUSTOMER_PHONE = "phone";
    private static final String COL_CUSTOMER_EMAIL = "email";
    private static final String COL_CUSTOMER_BILLINGADDRESS = "billingAddress";
    private static final String COL_CUSTOMER_SHIPPINGADDRESS = "shippingAddress";


    //Supplier Table
    private static final String TABLE_SUPPLIER = "supplier";

    private static final String COL_SUPPLIER_ID = "id";
    private static final String COL_SUPPLIER_NAME = "name";
    private static final String COL_SUPPLIER_COMPANYNAME = "companyname";
    private static final String COL_SUPPLIER_PHONE = "phone";
    private static final String COL_SUPPLIER_EMAIL = "email";
    private static final String COL_SUPPLIER_BILLINGADDRESS = "billingAddress";


    public databaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCT + "("
                + COL_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_PRODUCT_BARCODE + " TEXT,"
                + COL_PRODUCT_CODE + " TEXT,"
                + COL_PRODUCT_DESCRIPTION + " TEXT,"
                + COL_PRODUCT_QUANTITY + " INTEGER,"
                + COL_PRODUCT_PRICE + " INTEGER,"
                + COL_PRODUCT_LOCID + " INTEGER" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);

        String CREATE_PRODUCTLOCATION_TABLE = "CREATE TABLE " + TABLE_PRODUCTLOCATION + "("
                + COL_PRODUCTLOCATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_PRODUCTLOCATION_NAME + " TEXT)";
        db.execSQL(CREATE_PRODUCTLOCATION_TABLE);

        String CREATE_CUSTOMER_TABLE = "CREATE TABLE " + TABLE_CUSTOMER + "("
                + COL_CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_CUSTOMER_NAME + " TEXT,"
                + COL_CUSTOMER_COMPANYNAME + " TEXT,"
                + COL_CUSTOMER_EMAIL + " TEXT,"
                + COL_CUSTOMER_PHONE + " TEXT,"
                + COL_CUSTOMER_SHIPPINGADDRESS + " TEXT,"
                + COL_CUSTOMER_BILLINGADDRESS + " TEXT)";
        db.execSQL(CREATE_CUSTOMER_TABLE);

        String CREATE_SUPPLIER_TABLE = "CREATE TABLE " + TABLE_SUPPLIER + "("
                + COL_SUPPLIER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_SUPPLIER_NAME + " TEXT,"
                + COL_SUPPLIER_COMPANYNAME + " TEXT,"
                + COL_SUPPLIER_EMAIL + " TEXT,"
                + COL_SUPPLIER_PHONE + " TEXT,"
                + COL_SUPPLIER_BILLINGADDRESS + " TEXT)";
        db.execSQL(CREATE_SUPPLIER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTLOCATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUPPLIER);

        onCreate(db);

    }

    public void addProduct(product product) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_PRODUCT_BARCODE, product.getBarcode());
        values.put(COL_PRODUCT_CODE, product.getCode());
        values.put(COL_PRODUCT_DESCRIPTION, product.getDescription());
        values.put(COL_PRODUCT_QUANTITY, product.getQuantity());
        values.put(COL_PRODUCT_PRICE, product.getPrice());
        values.put(COL_PRODUCT_LOCID, product.getLocationID());

        db.insert(TABLE_PRODUCT, null, values);
        db.close();
    }

    public void addProductLocation(productLocation productLocation) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_PRODUCTLOCATION_NAME, productLocation.getName());

        db.insert(TABLE_PRODUCTLOCATION, null, values);
        db.close();
    }

    public void addCustomer(customer customer) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put(COL_CUSTOMER_NAME, customer.getName());
        values.put(COL_CUSTOMER_COMPANYNAME, customer.getCompanyName());
        values.put(COL_CUSTOMER_EMAIL, customer.getEmail());
        values.put(COL_CUSTOMER_PHONE, customer.getPhone());
        values.put(COL_CUSTOMER_BILLINGADDRESS, customer.getBillingAddress());
        values.put(COL_CUSTOMER_SHIPPINGADDRESS, customer.getShippingAddress());
        
        db.insert(TABLE_CUSTOMER, null, values);
        db.close();
    }

    public void addSupplier(supplier supplier) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_SUPPLIER_NAME, supplier.getName());
        values.put(COL_SUPPLIER_COMPANYNAME, supplier.getCompanyName());
        values.put(COL_SUPPLIER_EMAIL, supplier.getEmail());
        values.put(COL_SUPPLIER_PHONE, supplier.getPhone());
        values.put(COL_SUPPLIER_BILLINGADDRESS, supplier.getBillingAddress());

        db.insert(TABLE_SUPPLIER, null, values);
        db.close();
    }
    
    public void updateProduct(product product) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_PRODUCT_BARCODE, product.getBarcode());
        values.put(COL_PRODUCT_CODE, product.getCode());
        values.put(COL_PRODUCT_DESCRIPTION, product.getDescription());
        values.put(COL_PRODUCT_QUANTITY, product.getQuantity());
        values.put(COL_PRODUCT_PRICE, product.getPrice());
        values.put(COL_PRODUCT_LOCID, product.getLocationID());

        db.update(TABLE_PRODUCT, values, COL_PRODUCT_ID + " = ? ", new String[]{Integer.toString(product.getID())});
        db.close();
    }

    public void updateProductLocation(productLocation productLocation) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_PRODUCTLOCATION_NAME, productLocation.getName());

        db.update(TABLE_PRODUCTLOCATION, values, COL_PRODUCTLOCATION_ID + " = ? ", new String[]{Integer.toString(productLocation.getID())});
        db.close();
    }

    public void updateCustomer(customer customer) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_CUSTOMER_NAME, customer.getName());
        values.put(COL_CUSTOMER_COMPANYNAME, customer.getCompanyName());
        values.put(COL_CUSTOMER_EMAIL, customer.getEmail());

        values.put(COL_CUSTOMER_PHONE, customer.getPhone());
        values.put(COL_CUSTOMER_BILLINGADDRESS, customer.getBillingAddress());
        values.put(COL_CUSTOMER_SHIPPINGADDRESS, customer.getShippingAddress());

        db.update(TABLE_CUSTOMER, values, COL_CUSTOMER_ID + " = ? ", new String[]{Integer.toString(customer.getID())});
        db.close();
    }

    public void updateSupplier(supplier supplier) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_SUPPLIER_NAME, supplier.getName());
        values.put(COL_SUPPLIER_COMPANYNAME, supplier.getCompanyName());
        values.put(COL_SUPPLIER_EMAIL, supplier.getEmail());
        values.put(COL_SUPPLIER_PHONE, supplier.getPhone());
        values.put(COL_SUPPLIER_BILLINGADDRESS, supplier.getBillingAddress());


        db.update(TABLE_SUPPLIER, values, COL_SUPPLIER_ID + " = ? ", new String[]{Integer.toString(supplier.getID())});
        db.close();
    }


    public boolean deleteProduct(int productID) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_PRODUCT, COL_PRODUCT_ID + " = ?", new String[]{Integer.toString(productID)});
        db.close();
        return true;
    }

    public boolean deleteProductLocation(int productLocationID) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_PRODUCTLOCATION, COL_PRODUCTLOCATION_ID + " = ?", new String[]{Integer.toString(productLocationID)});
        db.close();
        return true;
    }

    public boolean deleteCustomer(int customerID) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_CUSTOMER, COL_CUSTOMER_ID + " = ?", new String[]{Integer.toString(customerID)});
        db.close();
        return true;
    }

    public boolean deleteSupplier(int supplierID) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_SUPPLIER, COL_SUPPLIER_ID+ " = ?", new String[]{Integer.toString(supplierID)});
        db.close();
        return true;
    }

    public List<product> getProducts() {

        SQLiteDatabase db = this.getReadableDatabase();
        List<product> list = new ArrayList<product>();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_PRODUCT, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            product p = new product();
            p.setID(res.getInt(0));
            p.setBarcode(res.getString(1));
            p.setCode(res.getString(2));
            p.setDescription(res.getString(3));
            p.setQuantity(res.getInt(4));
            p.setPrice(res.getInt(5));
            p.setLocationID(res.getInt(6));
            list.add(p);
            res.moveToNext();
        }
        return list;
    }

    public List<productLocation> getProductLocations() {

        SQLiteDatabase db = this.getReadableDatabase();
        List<productLocation> list = new ArrayList<productLocation>();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTLOCATION, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            productLocation p = new productLocation();
            p.setID(res.getInt(0));
            p.setName(res.getString(1));
            list.add(p);
            res.moveToNext();
        }
        return list;
    }

    public List<customer> getCustomers() {

        SQLiteDatabase db = this.getReadableDatabase();
        List<customer> list = new ArrayList<customer>();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_CUSTOMER, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            customer p = new customer();
            p.setID(res.getInt(0));
            p.setName(res.getString(1));
            p.setCompanyName(res.getString(2));
            p.setPhone(res.getString(3));
            p.setEmail(res.getString(4));
            p.setBillingAddress(res.getString(5));
            p.setShippingAddress(res.getString(6));
            list.add(p);
            res.moveToNext();
        }
        return list;
    }

    public List<supplier> getSuppliers() {

        SQLiteDatabase db = this.getReadableDatabase();
        List<supplier> list = new ArrayList<supplier>();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_SUPPLIER, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            supplier p = new supplier();
            p.setID(res.getInt(0));
            p.setName(res.getString(1));
            p.setCompanyName(res.getString(2));
            p.setPhone(res.getString(3));
            p.setEmail(res.getString(4));
            p.setBillingAddress(res.getString(5));

            list.add(p);
            res.moveToNext();
        }
        return list;
    }

    public boolean checkCustomerExists(String customerName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_CUSTOMER +" WHERE " + COL_CUSTOMER_NAME+" = '" +customerName+"'", null);
        if ( res.getCount() == 0)
            return false;
        else
            return true;

    }

    public boolean checkSupplierExists(String supplierName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_SUPPLIER +" WHERE " + COL_SUPPLIER_NAME+" = '" +supplierName+"'", null);
        if ( res.getCount() == 0)
            return false;
        else
            return true;

    }






}