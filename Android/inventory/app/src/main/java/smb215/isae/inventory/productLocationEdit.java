package smb215.isae.inventory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import smb215.isae.inventory.beans.productLocation;
import smb215.isae.inventory.dataaccess.databaseHandler;


public class productLocationEdit extends Activity {

    productLocation currentLocation;
    EditText atName;
    databaseHandler db;
    boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_view);

        db = new databaseHandler(this);

        Intent intent = getIntent();
        currentLocation = (productLocation) intent.getSerializableExtra("productLocation");

        if (currentLocation.getID() > 0)
            isEdit = true;

        if (isEdit)
            setTitle(R.string.title_activity_location_edit);
        else
            setTitle(R.string.title_activity_location_add);

        atName = (EditText) findViewById(R.id.txt_productLocation_name);
        atName.setText(currentLocation.getName());

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
                String message = "Location name is required!";
                atName.setError(message);
                utils.DisplayToast(this, message);
                return false;
            }

            currentLocation.setName(atName.getText().toString());

            if (isEdit) {
                db.updateProductLocation(currentLocation);
                String message ="Location '" + currentLocation.getName() + "' updated successfully!";
                utils.DisplayToast(this, message);
            } else {

                if (db.checkProductLocationExists(currentLocation.getName()))
                {
                    String message ="Location name already exist!";
                    atName.setError(message);
                    utils.DisplayToast(this, message);
                    return false;
                }

                db.addProductLocation(currentLocation);
                String message ="Location '" + currentLocation.getName() + "' added successfully!";
                utils.DisplayToast(this, message);
            }
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
