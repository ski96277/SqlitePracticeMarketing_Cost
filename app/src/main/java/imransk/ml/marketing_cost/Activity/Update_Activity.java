package imransk.ml.marketing_cost.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import imransk.ml.marketing_cost.Databse.DataOperation;
import imransk.ml.marketing_cost.POJOClass.Info_POJO;
import imransk.ml.marketing_cost.R;


import androidx.appcompat.app.AppCompatActivity;

public class Update_Activity extends AppCompatActivity {

    String id;
    String item;
    String quantity;
    String price;
    String spinner;
    private android.widget.TextView textView3;
    private android.widget.EditText itemnameET;
    private android.widget.TextView textView4;
    private android.widget.EditText porimanET;
    private android.widget.Spinner spinnerID;
    private android.widget.TextView textView5;
    private android.widget.EditText priceET;
    private android.widget.TextView textView6;
    private android.widget.Button updatebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        //set title
        getSupportActionBar().setTitle(getResources().getString(R.string.update_list_title));


        this.updatebutton = (Button) findViewById(R.id.update_button);
        this.textView6 = (TextView) findViewById(R.id.textView6);
        this.priceET = (EditText) findViewById(R.id.price_ET);
        this.textView5 = (TextView) findViewById(R.id.textView5);
        this.spinnerID = (Spinner) findViewById(R.id.spinner_ID);
        this.porimanET = (EditText) findViewById(R.id.poriman_ET);
        this.textView4 = (TextView) findViewById(R.id.textView4);
        this.itemnameET = (EditText) findViewById(R.id.item_name_ET);
        this.textView3 = (TextView) findViewById(R.id.textView3);

        //set currency name in TV
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String currency_symbol = preferences.getString("currency_symbol", "");
        if(!currency_symbol.isEmpty())
        {
            textView6.setText(currency_symbol);
        }else {
            textView6.setText("TK");
        }

        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        item=intent.getStringExtra("item");
        quantity=intent.getStringExtra("quantity");
        price=intent.getStringExtra("price");
        spinner=intent.getStringExtra("spinner");
        priceET.setText(price);
        porimanET.setText(quantity);
        itemnameET.setText(item);

        String[] list={"kg",
        "gm"
        ,"piece"};

        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,list);

//        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerID.setAdapter(spinnerArrayAdapter);



    }

    public void update_btn(View view) {

        price=priceET.getText().toString();
        item=itemnameET.getText().toString();
        quantity=porimanET.getText().toString();
        spinner=spinnerID.getSelectedItem().toString();


        Info_POJO info_pojo=new Info_POJO(id,item,quantity,price,spinner);

        DataOperation dataOperation=new DataOperation(this);
        boolean status = dataOperation.update(info_pojo);
        if (status){
            Toast.makeText(this, getResources().getString(R.string.updated_text), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,EditListActivity.class)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK));
            finish();
        }else {
            Toast.makeText(this, getResources().getString(R.string.update_failed_text), Toast.LENGTH_SHORT).show();
        }



    }
}
