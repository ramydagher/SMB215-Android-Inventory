package smb215.isae.inventory;

import android.app.Activity;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CustomerActivity extends Activity {

    private List<Customer> Customers = new ArrayList<Customer>();
    private Customer CurrentCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_activity);
        populateCustomers();
        populateListView();
        registerClick();
    }



    private void registerClick() {
        ListView list = (ListView)findViewById(R.id.mainListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View viewClick, int pos, long id) {
                Customer clickedCustomer = Customers.get(pos);
                String message = "you clicked position " + pos+" which is the customer "+ clickedCustomer.getName();
                Toast.makeText(CustomerActivity.this,message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void populateListView() {
        ArrayAdapter<Customer> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.mainListView);
        list.setAdapter(adapter);
    }

    public class MyListAdapter extends ArrayAdapter<Customer> {


        public MyListAdapter() {
            super(CustomerActivity.this, R.layout.item_view,Customers);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View ItemView = convertView;
            if ( ItemView == null)
            {
                ItemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);

            }

            CurrentCustomer = Customers.get(position);

            ImageView imageview = (ImageView)ItemView.findViewById(R.id.itemIcon);
            imageview.setImageResource(R.drawable.ic_launcher);

            TextView nameText =  (TextView)ItemView.findViewById(R.id.CustomerName);
            nameText.setText(CurrentCustomer.getName());


            TextView telText =  (TextView)ItemView.findViewById(R.id.CustomerTel);
            telText.setText(CurrentCustomer.getTelNumber());

            return ItemView;

        }
    }
    private void populateCustomers() {
        Customers.add(new Customer(0,"john","a","b"));
        Customers.add(new Customer(0,"ramy","ccccc","dsasad"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
