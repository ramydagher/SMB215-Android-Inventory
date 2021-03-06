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
import java.util.Date;
import java.util.List;

import smb215.isae.inventory.beans.customer;
import smb215.isae.inventory.beans.product;
import smb215.isae.inventory.beans.productLocation;
import smb215.isae.inventory.beans.supplier;
import smb215.isae.inventory.beans.purchaseOrder;
import smb215.isae.inventory.beans.salesOrder;

public class databaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 3;
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

    //Sales Order Table
    private static final String TABLE_PO = "salesorder";
    private static final String COL_PO_ID = "id";
    private static final String COL_PO_SERIAL = "serial";
    private static final String COL_PO_SUPID = "customerid";
    private static final String COL_PO_DATE = "date";
    private static final String COL_PO_TOTALPRICE = "totalprice";
    private static final String COL_PO_DISCOUNT = "discount";
    private static final String COL_PO_NETTOTAL = "nettotal";
    private static final String COL_PO_STATUS = "status";

    //Sales Order Detail
    private static final String TABLE_PODETAIL = "salesorderdetail";
    private static final String COL_PODETAIL_ID = "id";
    private static final String COL_PODETAIL_POID = "soid";
    private static final String COL_PODETAIL_PRODUCTID = "productid";
    private static final String COL_PODETAIL_qty = "date";

    //Sales Order Table
    private static final String TABLE_SO = "salesorder";
    private static final String COL_SO_ID = "id";
    private static final String COL_SO_SERIAL = "serial";
    private static final String COL_SO_CUSTID = "customerid";
    private static final String COL_SO_DATE = "date";
    private static final String COL_SO_TOTALPRICE = "totalprice";
    private static final String COL_SO_DISCOUNT = "discount";
    private static final String COL_SO_NETTOTAL = "nettotal";
    private static final String COL_SO_STATUS = "status";

    //Sales Order Detail
    private static final String TABLE_SODETAIL = "salesorderdetail";
    private static final String COL_SODETAIL_ID = "id";
    private static final String COL_SODETAIL_SOID = "soid";
    private static final String COL_SODETAIL_PRODUCTID = "productid";
    private static final String COL_SODETAIL_qty = "date";

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

        String CREATE_SO_TABLE = "CREATE TABLE " + TABLE_SO + "("
                + COL_SO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_SO_SERIAL + " TEXT,"
                + COL_SO_CUSTID + " INTEGER,"
                + COL_SO_DATE + " TEXT,"
                + COL_SO_TOTALPRICE + " REAL,"
                + COL_SO_DISCOUNT + " REAL,"
                + COL_SO_NETTOTAL + " REAL,"
                + COL_SO_STATUS + " TEXT)";
        db.execSQL(CREATE_SO_TABLE);

        String CREATE_SODETAIL_TABLE = "CREATE TABLE " + TABLE_SODETAIL + "("
                + COL_SODETAIL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_SODETAIL_SOID + " INTEGER,"
                + COL_SODETAIL_PRODUCTID + " INTEGER,"
                + COL_SODETAIL_qty + " INTEGER)";
        db.execSQL(CREATE_SODETAIL_TABLE);


        String CREATE_PO_TABLE = "CREATE TABLE " + TABLE_PO + "("
                + COL_PO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_PO_SERIAL + " TEXT,"
                + COL_PO_SUPID + " INTEGER,"
                + COL_PO_DATE + " TEXT,"
                + COL_PO_TOTALPRICE + " REAL,"
                + COL_PO_DISCOUNT + " REAL,"
                + COL_PO_NETTOTAL + " REAL,"
                + COL_PO_STATUS + " TEXT)";
        db.execSQL(CREATE_PO_TABLE);

        String CREATE_PODETAIL_TABLE = "CREATE TABLE " + TABLE_PODETAIL + "("
                + COL_PODETAIL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_PODETAIL_POID + " INTEGER,"
                + COL_PODETAIL_PRODUCTID + " INTEGER,"
                + COL_PODETAIL_qty + " INTEGER)";
        db.execSQL(CREATE_PODETAIL_TABLE);

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

    public void addPO(purchaseOrder po) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_PO_SERIAL, po.getSerial());
        values.put(COL_PO_SUPID, po.getSupplierID());
        values.put(COL_PO_DATE, po.getDate().toString());
        values.put(COL_PO_TOTALPRICE, po.getTotalPrice());
        values.put(COL_PO_DISCOUNT, po.getDiscount());
        values.put(COL_PO_NETTOTAL, po.getNetTotal());
        values.put(COL_PO_STATUS, po.getStatus());

        db.insert(TABLE_PO, null, values);
        db.close();
    }

    public void addSO(salesOrder so) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_SO_SERIAL, so.getSerial());
        values.put(COL_SO_CUSTID, so.getCustomerID());
        values.put(COL_SO_DATE, so.getDate().toString());
        values.put(COL_SO_TOTALPRICE, so.getTotalPrice());
        values.put(COL_SO_DISCOUNT, so.getDiscount());
        values.put(COL_SO_NETTOTAL, so.getNetTotal());
        values.put(COL_SO_STATUS, so.getStatus());

        db.insert(TABLE_SO, null, values);
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

    public void updatePO(purchaseOrder po) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_PO_SERIAL, po.getSerial());
        values.put(COL_PO_SUPID, po.getSupplierID());
        values.put(COL_PO_DATE, po.getDate().toString());
        values.put(COL_PO_TOTALPRICE, po.getTotalPrice());
        values.put(COL_PO_DISCOUNT, po.getDiscount());
        values.put(COL_PO_NETTOTAL, po.getNetTotal());
        values.put(COL_PO_STATUS, po.getStatus());


        db.update(TABLE_PO, values, COL_PO_ID + " = ? ", new String[]{Integer.toString(po.getID())});
        db.close();
    }

    public void updateSO(salesOrder so) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_SO_SERIAL, so.getSerial());
        values.put(COL_SO_CUSTID, so.getCustomerID());
        values.put(COL_SO_DATE, so.getDate().toString());
        values.put(COL_SO_TOTALPRICE, so.getTotalPrice());
        values.put(COL_SO_DISCOUNT, so.getDiscount());
        values.put(COL_SO_NETTOTAL, so.getNetTotal());
        values.put(COL_SO_STATUS, so.getStatus());


        db.update(TABLE_PO, values, COL_PO_ID + " = ? ", new String[]{Integer.toString(so.getID())});
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

    public boolean deletePO(int poID) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_PO, COL_PO_ID+ " = ?", new String[]{Integer.toString(poID)});
        db.close();
        return true;
    }

    public boolean deleteSO(int soID) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_SO, COL_SO_ID+ " = ?", new String[]{Integer.toString(soID)});
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

    public List<purchaseOrder> getPOs() {

        SQLiteDatabase db = this.getReadableDatabase();
        List<purchaseOrder> list = new ArrayList<purchaseOrder>();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_PO, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            purchaseOrder p = new purchaseOrder();
            p.setID(res.getInt(0));
            p.setSerial(res.getString(1));
            p.setSupplierID(res.getInt(2));
            p.setDate(new Date(res.getString(3)));
            p.setTotalPrice(res.getInt(4));
            p.setDiscount(res.getInt(5));
            p.setNetTotal(res.getDouble(6));
            p.setStatus(res.getString(7));
            list.add(p);
            res.moveToNext();
        }
        return list;
    }

    public List<salesOrder> getSOs() {

        SQLiteDatabase db = this.getReadableDatabase();
        List<salesOrder> list = new ArrayList<salesOrder>();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_PO, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            salesOrder p = new salesOrder();
            p.setID(res.getInt(0));
            p.setSerial(res.getString(1));
            p.setCustomerID(res.getInt(2));
            p.setDate(new Date(res.getString(3)));
            p.setTotalPrice(res.getInt(4));
            p.setDiscount(res.getInt(5));
            p.setNetTotal(res.getDouble(6));
            p.setStatus(res.getString(7));
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

    public boolean checkProductLocationExists(String productLocationName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTLOCATION +" WHERE " + COL_PRODUCTLOCATION_NAME+" = '" +productLocationName+"'", null);
        if ( res.getCount() == 0)
            return false;
        else
            return true;

    }

    public boolean checkProductExists(String productCode)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_PRODUCT +" WHERE " + COL_PRODUCT_CODE+" = '" +productCode+"'", null);
        if ( res.getCount() == 0)
            return false;
        else
            return true;

    }






}