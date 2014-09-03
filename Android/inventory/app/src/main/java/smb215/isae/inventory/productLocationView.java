package smb215.isae.inventory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import smb215.isae.inventory.beans.productLocation;
import smb215.isae.inventory.dataaccess.databaseHandler;


public class productLocationView extends Activity {

    productLocation currentproductLocation;
    EditText atName;
    boolean isEditable = false;
    databaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_view);



        Intent intent = getIntent();
        currentproductLocation = (productLocation) intent.getSerializableExtra("productLocation");
        setTitle(currentproductLocation.getName());

        atName= (EditText)findViewById(R.id.txt_productLocation_name);
        atName.setText(currentproductLocation.getName());


        setViewEditable(isEditable);

    }

    private void setViewEditable(boolean readonly) {

        atName.setFocusable(readonly);
        atName.setFocusableInTouchMode(readonly);

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
            Intent intent = new Intent(productLocationView.this ,productLocationEdit.class);
            intent.putExtra("productLocation", currentproductLocation);
            startActivity(intent);
        }
        if (id == R.id.action_delete) {
            ConfirmDeleteProductLocation();
        }
        return super.onOptionsItemSelected(item);
    }

    private void ConfirmDeleteProductLocation() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete productLocation Confirmation");
        builder.setMessage("Are you sure you want to delete the productLocation :'"+ currentproductLocation.getName()+"'?")
                .setCancelable(false)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DeleteproductLocation();
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

    private void DeleteproductLocation() {
        db = new databaseHandler(this);
        db.deleteProductLocation(currentproductLocation.getID());
        String message = "Location '" + currentproductLocation.getName() + "' deleted successfully!";
        utils.DisplayToast(this, message);
        this.finish();
    }

}
