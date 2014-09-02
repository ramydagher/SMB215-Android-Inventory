package smb215.isae.inventory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import smb215.isae.inventory.beans.supplier;
import smb215.isae.inventory.dataaccess.databaseHandler;


public class supplierEdit extends Activity {

    supplier currentSupplier;
    EditText atName, atCompanyName, atPhone, atEmail, atBillingAddress, atShippingAddress;
    databaseHandler db;
    boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_edit);

        db = new databaseHandler(this);

        Intent intent = getIntent();
        currentSupplier = (supplier) intent.getSerializableExtra("Supplier");

        if (currentSupplier.getID() > 0)
            isEdit = true;

        if (isEdit)
            setTitle(R.string.title_activity_supplier_edit);
        else
            setTitle(R.string.title_activity_supplier_add);

        atName = (EditText) findViewById(R.id.txt_supplier_name);
        atName.setText(currentSupplier.getName());

        atCompanyName = (EditText) findViewById(R.id.txt_supplier_company);
        atCompanyName.setText(currentSupplier.getCompanyName());

        atPhone = (EditText) findViewById(R.id.txt_supplier_phone);
        atPhone.setText(currentSupplier.getPhone());

        atEmail = (EditText) findViewById(R.id.txt_supplier_email);
        atEmail.setText(currentSupplier.getEmail());

        atBillingAddress = (EditText) findViewById(R.id.txt_billing_address);
        atBillingAddress.setText(currentSupplier.getBillingAddress());
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_cancel) {
            this.finish();
        }
        if (id == R.id.action_save) {

            if (atName.getText().toString().trim().equals("")) {
                String message = "Supplier name is required!";
                atName.setError(message);
                utils.DisplayToast(this, message);
                return false;
            }

            currentSupplier.setName(atName.getText().toString());
            currentSupplier.setCompanyName(atCompanyName.getText().toString());
            currentSupplier.setPhone(atPhone.getText().toString());
            currentSupplier.setEmail(atEmail.getText().toString());
            currentSupplier.setBillingAddress(atBillingAddress.getText().toString());



            if (isEdit) {
                db.updateSupplier(currentSupplier);
                String message ="Supplier '" + currentSupplier.getName() + "' updated successfully!";
                utils.DisplayToast(this, message);
            } else {

                if (db.checkSupplierExists(currentSupplier.getName()))
                {
                    String message ="Supplier name already exist!";
                    atName.setError(message);
                    utils.DisplayToast(this, message);
                    return false;
                }

                db.addSupplier(currentSupplier);
                String message ="Supplier '" + currentSupplier.getName() + "' added successfully!";
                utils.DisplayToast(this, message);
            }
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
