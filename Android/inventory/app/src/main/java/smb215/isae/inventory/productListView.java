package smb215.isae.inventory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import smb215.isae.inventory.beans.product;
import smb215.isae.inventory.dataaccess.databaseHandler;


public class productListView extends Activity {

    private List<product> products = new ArrayList<product>();
    private product currentProduct;
    databaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        db = new databaseHandler(getApplicationContext());


        registerClick();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateProducts();
        populateListView();
    }

    private void registerClick() {
        ListView list = (ListView) findViewById(R.id.mainListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View viewClick, int pos, long id) {
                product clickedProduct = products.get(pos);
                Intent intent = new Intent(productListView.this, productView.class);
                intent.putExtra("Product", clickedProduct);
                startActivity(intent);
            }
        });
    }


    private void populateListView() {
        ArrayAdapter<product> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.mainListView);
        list.setAdapter(adapter);
    }

    public class MyListAdapter extends ArrayAdapter<product> {

        public MyListAdapter() {
            super(productListView.this, R.layout.list_item_view, products);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View ItemView = convertView;
            if (ItemView == null)
                ItemView = getLayoutInflater().inflate(R.layout.list_item_view, parent, false);

            currentProduct = products.get(position);

            ImageView imageview = (ImageView) ItemView.findViewById(R.id.itemIcon);
            imageview.setImageResource(R.drawable.product);

            TextView nameText = (TextView) ItemView.findViewById(R.id.BigHeader);
            nameText.setText(currentProduct.getCode() + " : " + currentProduct.getDescription());


            TextView compText = (TextView) ItemView.findViewById(R.id.SmallHeader);
            compText.setText("Qty:" + currentProduct.getQuantity() +"   Price:" + currentProduct.getPrice());

            return ItemView;

        }
    }

    private void populateProducts() {
        //        Product cust = new Product();
        //        cust.setName("Product " + Calendar.getInstance().getTimeInMillis());
        //        cust.setEmail("Product@gmail.com");
        //        cust.setCompanyName("Active Identity");
        //        cust.setPhone("03729905");
        //        cust.setShippingAddress("Zouk Mikael , Al Boustan Street , Kazzi Building");
        //        cust.setBillingAddress("Berytech Mar roukoz");
        //        db.addProduct(cust);
        products = db.getProducts();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_add) {
            Intent intent = new Intent(this, productEdit.class);
            product newProduct = new product();
            intent.putExtra("Product", newProduct);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
