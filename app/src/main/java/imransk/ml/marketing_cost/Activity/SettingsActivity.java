package imransk.ml.marketing_cost.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mynameismidori.currencypicker.CurrencyPicker;
import com.mynameismidori.currencypicker.CurrencyPickerListener;

import androidx.appcompat.app.AppCompatActivity;
import imransk.ml.marketing_cost.R;

public class SettingsActivity extends AppCompatActivity {
    TextView currency_symbol_TV;
    Button save_btn;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //set Title
        getSupportActionBar().setTitle(getResources().getString(R.string.settings_title));

        currency_symbol_TV = findViewById(R.id.currency_name_TV);
        save_btn = findViewById(R.id.save_btn_ID);

         preferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);

//get value from shared preferences
        //set currency name in TV
        setCurrencyName_TV(preferences);



        findViewById(R.id.arrow_button_set_currency).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
   /*
    https://github.com/midorikocak/currency-picker-android
  */
                final CurrencyPicker picker = CurrencyPicker.newInstance("Select Currency");  // dialog title
                picker.setListener(new CurrencyPickerListener() {
                    @Override
                    public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {

                        currency_symbol_TV.setText(symbol);

                        picker.dismiss();

                    }
                });
                picker.show(getSupportFragmentManager(), "CURRENCY_PICKER");
            }
        });



        save_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        // save value into SharedPreferences
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("currency_symbol", currency_symbol_TV.getText().toString());
                        editor.apply();

//get value from shared preferences
                        //set currency name in TV
                        setCurrencyName_TV(preferences);

                        startActivity(new Intent(SettingsActivity.this,MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    }
                });
    }

    private void setCurrencyName_TV(SharedPreferences preferences) {
        String currency_symbol = preferences.getString("currency_symbol", "");
        if(!currency_symbol.isEmpty())
        {
            currency_symbol_TV.setText(currency_symbol);
        }else {
            currency_symbol_TV.setText("TK");
        }
        currency_symbol_TV.setTextColor(Color.parseColor("#FFFFFF"));

    }
}
