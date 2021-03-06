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

import smb215.isae.inventory.beans.salesOrder;
import smb215.isae.inventory.dataaccess.databaseHandler;


public class salesOrderListView extends Activity {

    private List<salesOrder> salesOrders = new ArrayList<salesOrder>();
    private salesOrder currentSO;
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
        populateSOs();
        populateListView();
    }

    private void registerClick() {
        ListView list = (ListView) findViewById(R.id.mainListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View viewClick, int pos, long id) {
                salesOrder clickedSO = salesOrders.get(pos);
                Intent intent = new Intent(salesOrderListView.this, productView.class);
                intent.putExtra("SO", clickedSO);
                startActivity(intent);
            }
        });
    }


    private void populateListView() {
        ArrayAdapter<salesOrder> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.mainListView);
        list.setAdapter(adapter);
    }

    public class MyListAdapter extends ArrayAdapter<salesOrder> {

        public MyListAdapter() {
            super(salesOrderListView.this, R.layout.list_item_view, salesOrders);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View ItemView = convertView;
            if (ItemView == null)
                ItemView = getLayoutInflater().inflate(R.layout.list_item_view, parent, false);

            currentSO = salesOrders.get(position);

            ImageView imageview = (ImageView) ItemView.findViewById(R.id.itemIcon);
            imageview.setImageResource(R.drawable.product);

            TextView nameText = (TextView) ItemView.findViewById(R.id.BigHeader);
            nameText.setText(currentSO.getSerial() + " : " + currentSO.getStatus());


            //TextView compText = (TextView) ItemView.findViewById(R.id.SmallHeader);
            //compText.setText("Qty:" + currentSO.getQuantity() +"   Price:" + currentSO.getPrice());

            return ItemView;

        }
    }

    private void populateSOs() {

        salesOrders = db.getSOs();
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
            salesOrder newSO = new salesOrder();
            intent.putExtra("SO", newSO);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
