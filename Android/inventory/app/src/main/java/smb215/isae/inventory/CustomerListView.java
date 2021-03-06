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

import smb215.isae.inventory.beans.customer;
import smb215.isae.inventory.dataaccess.databaseHandler;


public class customerListView extends Activity {

    private List<customer> customers = new ArrayList<customer>();
    private customer currentCustomer;
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
        populateCustomers();
        populateListView();
    }

    private void registerClick() {
        ListView list = (ListView) findViewById(R.id.mainListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View viewClick, int pos, long id) {
                customer clickedCustomer = customers.get(pos);
                //String message = "you clicked position " + pos+" which is the customer "+ clickedCustomer.getName();
                //Toast.makeText(CustomerActivity.this,message, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(customerListView.this, customerView.class);
                intent.putExtra("Customer", clickedCustomer);
                startActivity(intent);
            }
        });
    }


    private void populateListView() {
        ArrayAdapter<customer> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.mainListView);
        list.setAdapter(adapter);
    }

    public class MyListAdapter extends ArrayAdapter<customer> {

        public MyListAdapter() {
            super(customerListView.this, R.layout.list_item_view, customers);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View ItemView = convertView;
            if (ItemView == null)
                ItemView = getLayoutInflater().inflate(R.layout.list_item_view, parent, false);

            currentCustomer = customers.get(position);

            ImageView imageview = (ImageView) ItemView.findViewById(R.id.itemIcon);
            imageview.setImageResource(R.drawable.customer);

            TextView nameText = (TextView) ItemView.findViewById(R.id.BigHeader);
            nameText.setText(currentCustomer.getName());


            TextView compText = (TextView) ItemView.findViewById(R.id.SmallHeader);
            compText.setText(currentCustomer.getCompanyName());

            return ItemView;

        }
    }

    private void populateCustomers() {
        //        Customer cust = new Customer();
        //        cust.setName("Customer " + Calendar.getInstance().getTimeInMillis());
        //        cust.setEmail("Customer@gmail.com");
        //        cust.setCompanyName("Active Identity");
        //        cust.setPhone("03729905");
        //        cust.setShippingAddress("Zouk Mikael , Al Boustan Street , Kazzi Building");
        //        cust.setBillingAddress("Berytech Mar roukoz");
        //        db.addCustomer(cust);
        customers = db.getCustomers();
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
            Intent intent = new Intent(this, customerEdit.class);
            customer newCustomer = new customer();
            intent.putExtra("Customer", newCustomer);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
