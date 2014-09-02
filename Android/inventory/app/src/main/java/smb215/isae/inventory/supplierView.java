package smb215.isae.inventory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import smb215.isae.inventory.beans.supplier;
import smb215.isae.inventory.dataaccess.databaseHandler;

import static smb215.isae.inventory.R.layout.activity_supplier_view;


public class supplierView extends Activity {

    supplier currentSupplier;
    EditText atName,atCompanyName,atPhone,atEmail,atBillingAddress;
    boolean isEditable = false;
    databaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_supplier_view);



        Intent intent = getIntent();
        currentSupplier = (supplier) intent.getSerializableExtra("Supplier");
        setTitle(currentSupplier.getName());

        atName= (EditText)findViewById(R.id.txt_supplier_name);
        atName.setText(currentSupplier.getName());

         atCompanyName = (EditText)findViewById(R.id.txt_supplier_company);
        atCompanyName.setText(currentSupplier.getCompanyName());

         atPhone = (EditText)findViewById(R.id.txt_supplier_phone);
        atPhone.setText(currentSupplier.getPhone());

         atEmail = (EditText)findViewById(R.id.txt_supplier_email);
        atEmail.setText(currentSupplier.getEmail());

         atBillingAddress = (EditText)findViewById(R.id.txt_billing_address);
        atBillingAddress.setText(currentSupplier.getBillingAddress());


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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_view, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_edit) {
            Intent intent = new Intent(supplierView.this ,supplierEdit.class);
            intent.putExtra("Supplier", currentSupplier);
            startActivity(intent);
        }
        if (id == R.id.action_delete) {
            ConfirmDeleteSupplier();
        }
        return super.onOptionsItemSelected(item);
    }

    private void ConfirmDeleteSupplier() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Supplier Confirmation");
        builder.setMessage("Are you sure you want to delete the supplier :'"+ currentSupplier.getName()+"'?")
                .setCancelable(false)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DeleteSupplier();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void DeleteSupplier() {
        db = new databaseHandler(this);
        db.deleteSupplier(currentSupplier.getID());
        String message = "Supplier '" + currentSupplier.getName() + "' deleted successfully!";
        utils.DisplayToast(this, message);
        this.finish();
    }

}
