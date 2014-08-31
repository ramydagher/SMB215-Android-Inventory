package smb215.isae.inventory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import smb215.isae.inventory.beans.Customer;


public class CustomerView extends Activity {

    Customer currentCustomer;
    EditText atName,atCompanyName,atPhone,atEmail,atBillingAddress,atShippingAddress;
    boolean isEditable = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);

        Intent intent = getIntent();
        currentCustomer = (Customer) intent.getSerializableExtra("Customer");



        atName= (EditText)findViewById(R.id.txt_customer_name);
        atName.setText(currentCustomer.getName());

         atCompanyName = (EditText)findViewById(R.id.txt_customer_company);
        atCompanyName.setText(currentCustomer.getCompanyName());

         atPhone = (EditText)findViewById(R.id.txt_customer_phone);
        atPhone.setText(currentCustomer.getPhone());

         atEmail = (EditText)findViewById(R.id.txt_customer_email);
        atEmail.setText(currentCustomer.getEmail());

         atBillingAddress = (EditText)findViewById(R.id.txt_billing_address);
        atBillingAddress.setText(currentCustomer.getBillingAddress());

         atShippingAddress = (EditText)findViewById(R.id.txt_shipping_address);
        atShippingAddress.setText(currentCustomer.getShippingAddress());


        setViewEditable(isEditable);

    }

    private void setViewEditable(boolean readonly) {

        atName.setFocusable(readonly);
        atName.setFocusableInTouchMode(readonly);

        atCompanyName.setFocusable(readonly);
        atCompanyName.setFocusableInTouchMode(readonly);

        atPhone.setFocusable(readonly);
        atPhone.setFocusableInTouchMode(readonly);

        atEmail.setFocusable(readonly);
        atEmail.setFocusableInTouchMode(readonly);

        atBillingAddress.setFocusable(readonly);
        atBillingAddress.setFocusableInTouchMode(readonly);

        atShippingAddress.setFocusable(readonly);
        atShippingAddress.setFocusableInTouchMode(readonly);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_customer_view, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_edit) {
            Intent intent = new Intent(CustomerView.this ,CustomerEdit.class);
            intent.putExtra("Customer", currentCustomer);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
