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

import smb215.isae.inventory.beans.productLocation;
import smb215.isae.inventory.dataaccess.databaseHandler;


public class productLocationListView extends Activity {

    private List<productLocation> productLocations = new ArrayList<productLocation>();
    private productLocation currentproductLocation;
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
        populateproductLocations();
        populateListView();
    }

    private void registerClick() {
        ListView list = (ListView) findViewById(R.id.mainListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View viewClick, int pos, long id) {
                productLocation clickedproductLocation = productLocations.get(pos);
                //String message = "you clicked position " + pos+" which is the productLocation "+ clickedproductLocation.getName();
                //Toast.makeText(productLocationActivity.this,message, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(productLocationListView.this, productLocationView.class);
                intent.putExtra("productLocation", clickedproductLocation);
                startActivity(intent);
            }
        });
    }


    private void populateListView() {
        ArrayAdapter<productLocation> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.mainListView);
        list.setAdapter(adapter);
    }

    public class MyListAdapter extends ArrayAdapter<productLocation> {

        public MyListAdapter() {
            super(productLocationListView.this, R.layout.list_item_view, productLocations);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View ItemView = convertView;
            if (ItemView == null)
                ItemView = getLayoutInflater().inflate(R.layout.list_item_view, parent, false);

            currentproductLocation = productLocations.get(position);

            ImageView imageview = (ImageView) ItemView.findViewById(R.id.itemIcon);
            imageview.setImageResource(R.drawable.productlocation);

            TextView nameText = (TextView) ItemView.findViewById(R.id.BigHeader);
            nameText.setText(currentproductLocation.getName());


            TextView compText = (TextView) ItemView.findViewById(R.id.SmallHeader);
            compText.setText("Warehouse");

            return ItemView;

        }
    }

    private void populateproductLocations() {
        //        productLocation cust = new productLocation();
        //        cust.setName("productLocation " + Calendar.getInstance().getTimeInMillis());
        //        cust.setEmail("productLocation@gmail.com");
        //        cust.setCompanyName("Active Identity");
        //        cust.setPhone("03729905");
        //        cust.setShippingAddress("Zouk Mikael , Al Boustan Street , Kazzi Building");
        //        cust.setBillingAddress("Berytech Mar roukoz");
        //        db.addproductLocation(cust);
        productLocations = db.getProductLocations();
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
            Intent intent = new Intent(this, productLocationEdit.class);
            productLocation newproductLocation = new productLocation();
            intent.putExtra("productLocation", newproductLocation);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
