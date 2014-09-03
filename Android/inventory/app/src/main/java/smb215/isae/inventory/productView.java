package smb215.isae.inventory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import smb215.isae.inventory.beans.product;
import smb215.isae.inventory.dataaccess.databaseHandler;

import static smb215.isae.inventory.R.layout.activity_product_view;


public class productView extends Activity {

    product currentProduct;
    EditText atCode,atDesc,atBarcode,atQty,atPrice;
    Spinner atLocation;
    boolean isEditable = false;
    databaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_product_view);



        Intent intent = getIntent();
        currentProduct = (product) intent.getSerializableExtra("Product");
        setTitle(currentProduct.getCode());

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

        setViewEditable(isEditable);

    }

    private void setViewEditable(boolean readonly) {

        atCode.setFocusable(readonly);
        atCode.setFocusableInTouchMode(readonly);

        atDesc.setFocusable(readonly);
        atDesc.setFocusableInTouchMode(readonly);

        atBarcode.setFocusable(readonly);
        atBarcode.setFocusableInTouchMode(readonly);

        atQty.setFocusable(readonly);
        atQty.setFocusableInTouchMode(readonly);

        atPrice.setFocusable(readonly);
        atPrice.setFocusableInTouchMode(readonly);

        atLocation.setFocusable(readonly);
        atLocation.setFocusableInTouchMode(readonly);
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
            Intent intent = new Intent(productView.this ,productEdit.class);
            intent.putExtra("Product", currentProduct);
            startActivity(intent);
        }
        if (id == R.id.action_delete) {
            ConfirmDeleteProduct();
        }
        return super.onOptionsItemSelected(item);
    }

    private void ConfirmDeleteProduct() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Product Confirmation");
        builder.setMessage("Are you sure you want to delete the product :'"+ currentProduct.getCode()+"'?")
                .setCancelable(false)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DeleteProduct();
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

    private void DeleteProduct() {
        db = new databaseHandler(this);
        db.deleteProduct(currentProduct.getID());
        String message = "Product '" + currentProduct.getCode() + "' deleted successfully!";
        utils.DisplayToast(this, message);
        this.finish();
    }

}
