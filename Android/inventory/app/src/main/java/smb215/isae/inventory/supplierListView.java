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

import smb215.isae.inventory.beans.supplier;
import smb215.isae.inventory.dataaccess.databaseHandler;


public class supplierListView extends Activity {

    private List<supplier> suppliers = new ArrayList<supplier>();
    private supplier currentSupplier;
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
        populateSuppliers();
        populateListView();
    }

    private void registerClick() {
        ListView list = (ListView) findViewById(R.id.mainListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View viewClick, int pos, long id) {
                supplier clickedSupplier = suppliers.get(pos);
                Intent intent = new Intent(supplierListView.this, supplierView.class);
                intent.putExtra("Supplier", clickedSupplier);
                startActivity(intent);
            }
        });
    }


    private void populateListView() {
        ArrayAdapter<supplier> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.mainListView);
        list.setAdapter(adapter);
    }

    public class MyListAdapter extends ArrayAdapter<supplier> {

        public MyListAdapter() {
            super(supplierListView.this, R.layout.list_item_view, suppliers);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View ItemView = convertView;
            if (ItemView == null)
                ItemView = getLayoutInflater().inflate(R.layout.list_item_view, parent, false);

            currentSupplier = suppliers.get(position);

            ImageView imageview = (ImageView) ItemView.findViewById(R.id.itemIcon);
            imageview.setImageResource(R.drawable.supplier);

            TextView nameText = (TextView) ItemView.findViewById(R.id.BigHeader);
            nameText.setText(currentSupplier.getName());


            TextView compText = (TextView) ItemView.findViewById(R.id.SmallHeader);
            compText.setText(currentSupplier.getCompanyName());

            return ItemView;

        }
    }

    private void populateSuppliers() {
        //        Supplier cust = new Supplier();
        //        cust.setName("Supplier " + Calendar.getInstance().getTimeInMillis());
        //        cust.setEmail("Supplier@gmail.com");
        //        cust.setCompanyName("Active Identity");
        //        cust.setPhone("03729905");
        //        cust.setShippingAddress("Zouk Mikael , Al Boustan Street , Kazzi Building");
        //        cust.setBillingAddress("Berytech Mar roukoz");
        //        db.addSupplier(cust);
        suppliers = db.getSuppliers();
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
            Intent intent = new Intent(this, supplierEdit.class);
            supplier newSupplier = new supplier();
            intent.putExtra("Supplier", newSupplier);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
