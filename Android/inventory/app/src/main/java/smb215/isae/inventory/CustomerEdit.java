package smb215.isae.inventory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import smb215.isae.inventory.beans.Customer;
import smb215.isae.inventory.dataaccess.DatabaseHandler;


public class CustomerEdit extends Activity {

    Customer currentCustomer;
    EditText atName, atCompanyName, atPhone, atEmail, atBillingAddress, atShippingAddress;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit);

        db = new DatabaseHandler(this);

        Intent intent = getIntent();
        currentCustomer = (Customer) intent.getSerializableExtra("Customer");

        atName = (EditText) findViewById(R.id.txt_customer_name);
        atName.setText(currentCustomer.getName());

        atCompanyName = (EditText) findViewById(R.id.txt_customer_company);
        atCompanyName.setText(currentCustomer.getCompanyName());

        atPhone = (EditText) findViewById(R.id.txt_customer_phone);
        atPhone.setText(currentCustomer.getPhone());

        atEmail = (EditText) findViewById(R.id.txt_customer_email);
        atEmail.setText(currentCustomer.getEmail());

        atBillingAddress = (EditText) findViewById(R.id.txt_billing_address);
        atBillingAddress.setText(currentCustomer.getBillingAddress());

        atShippingAddress = (EditText) findViewById(R.id.txt_shipping_address);
        atShippingAddress.setText(currentCustomer.getShippingAddress());
    }

    private void DisplayToast(String message) {
        Toast.makeText(CustomerEdit.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_customer_edit, menu);
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
                String message = "Customer name is required!";
                atName.setError(message);
                DisplayToast(message);
                return false;
            }

            currentCustomer.setName(atName.getText().toString());
            currentCustomer.setCompanyName(atCompanyName.getText().toString());
            currentCustomer.setPhone(atPhone.getText().toString());
            currentCustomer.setEmail(atEmail.getText().toString());
            currentCustomer.setBillingAddress(atBillingAddress.getText().toString());
            currentCustomer.setShippingAddress(atShippingAddress.getText().toString());

            if (db.checkCustomerExists(currentCustomer.getName()))
            {
                String message ="Customer name already exist!";
                atName.setError(message);
                DisplayToast(message);
                return false;
            }

            if (currentCustomer.getID() > 0) {
                db.updateCustomer(currentCustomer);
                DisplayToast("Customer '" + currentCustomer.getName() + "' updated successfully!");
            } else {
                db.addCustomer(currentCustomer);
                DisplayToast("Customer '" + currentCustomer.getName() + "' added successfully!");
            }
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
