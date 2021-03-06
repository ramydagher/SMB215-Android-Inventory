package smb215.isae.inventory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.btnCustomers);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(main.this, customerListView.class);
                startActivity(intent);
            }
        });

        Button btnSupplier = (Button) findViewById(R.id.btnSuppliers);
        btnSupplier.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(main.this, supplierListView.class);
                startActivity(intent);
            }
        });

        Button btnLocations = (Button) findViewById(R.id.btnLocations);
        btnLocations.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(main.this, productLocationListView.class);
                startActivity(intent);
            }
        });

        Button btnProducts = (Button) findViewById(R.id.btnProducts);
        btnProducts.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(main.this, productListView.class);
                startActivity(intent);
            }
        });

        Button btnSO = (Button) findViewById(R.id.btnSO);
        btnSO.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(main.this, salesOrderListView.class);
                startActivity(intent);
            }
        });

        Button btnPO = (Button) findViewById(R.id.btnPO);
        btnSO.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(main.this, purchaseOrderListView.class);
                startActivity(intent);
            }
        });

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
