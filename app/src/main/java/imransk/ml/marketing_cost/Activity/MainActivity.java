package imransk.ml.marketing_cost.Activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import imransk.ml.marketing_cost.Databse.DataOperation;
import imransk.ml.marketing_cost.POJOClass.Info_POJO;
import imransk.ml.marketing_cost.R;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private TextView textView2;
    private TextView textView3;
    private EditText itemnameET;
    private TextView textView4;
    private EditText porimanET;
    private Spinner spinnerID;
    private TextView textView5;
    private EditText priceET;
    private TextView textView6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        String[] list = {"kg",
                "gm"
                , "piece"};


        //set currency name in TV
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String currency_symbol = preferences.getString("currency_symbol", "");
        if(!currency_symbol.isEmpty())
        {
            textView6.setText(currency_symbol);
        }else {
            textView6.setText("TK");
        }

        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, list);

//        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerID.setAdapter(spinnerArrayAdapter);
    }

    private void initialize() {

        this.textView6 = (TextView) findViewById(R.id.textView6);
        this.priceET = (EditText) findViewById(R.id.price_ET);
        this.textView5 = (TextView) findViewById(R.id.textView5);
        this.spinnerID = (Spinner) findViewById(R.id.spinner_ID);
        this.porimanET = (EditText) findViewById(R.id.poriman_ET);
        this.textView4 = (TextView) findViewById(R.id.textView4);
        this.itemnameET = (EditText) findViewById(R.id.item_name_ET);
        this.textView3 = (TextView) findViewById(R.id.textView3);
        this.textView2 = (TextView) findViewById(R.id.textView2);


    }

    public void editButton(View view) {
        startActivity(new Intent(this, EditListActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

    public void save_all(View view) {

        String item = itemnameET.getText().toString();
        String quantity = porimanET.getText().toString();
        String price = priceET.getText().toString();
        String spinner = spinnerID.getSelectedItem().toString();

        if (item.isEmpty()) {
            itemnameET.requestFocus();
            itemnameET.setError(getResources().getString(R.string.enter_item_text));
            return;
        }
        if (quantity.isEmpty()) {
            porimanET.setError(getResources().getString(R.string.enter_quientity_text));
            porimanET.requestFocus();
            return;
        }
        if (price.isEmpty()) {
            priceET.setError(getResources().getString(R.string.enter_price_text));
            priceET.requestFocus();
            return;
        }
        Info_POJO info_pojo = new Info_POJO(item, quantity, price, spinner);

        DataOperation dataOperation = new DataOperation(this);

        boolean status = dataOperation.insertDB(info_pojo);
        if (status) {
            itemnameET.setText("");
            porimanET.setText("");
            priceET.setText("");
            Toast.makeText(this, getResources().getString(R.string.saved_text), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getResources().getString(R.string.failed_text), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings_btn_ID:

startActivity(new Intent(this,SettingsActivity.class));

                break;
            default:

                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void see_All_List(View view) {

        startActivity(new Intent(this, All_TableActivity.class));
    }
    private void show_Alert_to_Exit_The_App() {

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit App")
                .setMessage("Are you sure you want to Exit this App?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
//                super.onBackPressed();
            //additional code
            show_Alert_to_Exit_The_App();
        } else {
            getSupportFragmentManager().popBackStack();
        }

    }
}
