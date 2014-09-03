package smb215.isae.inventory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import smb215.isae.inventory.beans.product;
import smb215.isae.inventory.dataaccess.databaseHandler;


public class productEdit extends Activity {

    product currentProduct;
    EditText atCode,atDesc,atBarcode,atQty,atPrice;
    Spinner atLocation;
    databaseHandler db;
    boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        db = new databaseHandler(this);

        Intent intent = getIntent();
        currentProduct = (product) intent.getSerializableExtra("Product");

        if (currentProduct.getID() > 0)
            isEdit = true;

        if (isEdit) {
            setTitle(R.string.title_activity_product_edit);

            atCode= (EditText)findViewById(R.id.txt_product_code);
            atCode.setText(currentProduct.getCode());

            atDesc = (EditText)findViewById(R.id.txt_product_desc);
            atDesc.setText(currentProduct.getDescription());

            atBarcode = (EditText)findViewById(R.id.txt_product_barcode);
            atBarcode.setText(currentProduct.getBarcode());

            atQty = (EditText)findViewById(R.id.txt_product_qty);
            atQty.setText(currentProduct.getQuantity());

            atPrice = (EditText)findViewById(R.id.txt_product_price);
            atPrice.setText(currentProduct.getPrice());

            atLocation = (Spinner)findViewById(R.id.sp_product_location);
            // atLocation.(currentProduct.getLocationID());
        }
        else
            setTitle(R.string.title_activity_product_add);


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

            if (atCode.getText().toString().trim().equals("")) {
                String message = "Product code is required!";
                atCode.setError(message);
                utils.DisplayToast(this, message);
                return false;
            }

            currentProduct.setCode(atCode.getText().toString());
            currentProduct.setBarcode(atBarcode.getText().toString());
            currentProduct.setQuantity(Integer.parseInt(atQty.getText().toString()));
            currentProduct.setPrice(Integer.parseInt(atPrice.getText().toString()));
           // currentProduct.setLocationID();



            if (isEdit) {
                db.updateProduct(currentProduct);
                String message ="Product '" + currentProduct.getCode() + "' updated successfully!";
                utils.DisplayToast(this, message);
            } else {

                if (db.checkProductExists(currentProduct.getCode()))
                {
                    String message ="Product name already exist!";
                    atCode.setError(message);
                    utils.DisplayToast(this, message);
                    return false;
                }

                db.addProduct(currentProduct);
                String message ="Product '" + currentProduct.getCode() + "' added successfully!";
                utils.DisplayToast(this, message);
            }
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
