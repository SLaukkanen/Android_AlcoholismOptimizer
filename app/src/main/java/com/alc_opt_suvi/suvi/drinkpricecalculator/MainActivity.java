package com.alc_opt_suvi.suvi.drinkpricecalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText name_dr1, alcVol_dr1, vol_dr1, price_dr1, name_dr2, alcVol_dr2, vol_dr2, price_dr2;
    Button buttonCalc;
    public static SharedPreferences prefs;
    public static final String preferences = "Inputs" ;
    public static final String name_dr1_key = "Name1";
    public static final String alcVol_dr1_key = "AlcoholVolume1";
    public static final String vol_dr1_key = "Volume1";
    public static final String price_dr1_key = "Price1";
    public static final String name_dr2_key = "Name2";
    public static final String alcVol_dr2_key = "AlcoholVolume2";
    public static final String vol_dr2_key = "Volume2";
    public static final String price_dr2_key = "Price2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.alc_opt_suvi.suvi.drinkpricecalculator.R.layout.activity_main);

        name_dr1 = (EditText)findViewById(com.alc_opt_suvi.suvi.drinkpricecalculator.R.id.m_name_dr1);
        alcVol_dr1 = (EditText)findViewById(com.alc_opt_suvi.suvi.drinkpricecalculator.R.id.m_alcVol_dr1);
        vol_dr1 = (EditText)findViewById(com.alc_opt_suvi.suvi.drinkpricecalculator.R.id.m_vol_dr1);
        price_dr1 = (EditText)findViewById(com.alc_opt_suvi.suvi.drinkpricecalculator.R.id.m_price_dr1);
        name_dr2 = (EditText)findViewById(com.alc_opt_suvi.suvi.drinkpricecalculator.R.id.m_name_dr2);
        alcVol_dr2 = (EditText)findViewById(com.alc_opt_suvi.suvi.drinkpricecalculator.R.id.m_alcVol_dr2);
        vol_dr2 = (EditText)findViewById(com.alc_opt_suvi.suvi.drinkpricecalculator.R.id.m_vol_dr2);
        price_dr2 = (EditText)findViewById(com.alc_opt_suvi.suvi.drinkpricecalculator.R.id.m_price_dr2);
        prefs = getSharedPreferences(preferences, Context.MODE_PRIVATE);

        buttonCalc = (Button) findViewById(com.alc_opt_suvi.suvi.drinkpricecalculator.R.id.m_bt_comp);
        if (buttonCalc != null) {
            buttonCalc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(name_dr1.getText().toString().isEmpty() || alcVol_dr1.getText().toString().isEmpty() || vol_dr1.getText().toString().isEmpty() || price_dr1.getText().toString().isEmpty() || name_dr2.getText().toString().isEmpty() || alcVol_dr2.getText().toString().isEmpty() || vol_dr2.getText().toString().isEmpty() || price_dr2.getText().toString().isEmpty()) {
                        showToast(String.valueOf(getText(com.alc_opt_suvi.suvi.drinkpricecalculator.R.string.m_err7)));
                    }else if(!checkDigits(alcVol_dr1.getText().toString()) || !checkDigits(vol_dr1.getText().toString()) || !checkDigits(price_dr1.getText().toString()) || !checkDigits(alcVol_dr2.getText().toString()) || !checkDigits(vol_dr2.getText().toString()) || !checkDigits(price_dr2.getText().toString())){
                        showToast(String.valueOf(getText(com.alc_opt_suvi.suvi.drinkpricecalculator.R.string.r_err)));
                    }else {
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString(name_dr1_key, name_dr1.getText().toString());
                        editor.putString(alcVol_dr1_key, alcVol_dr1.getText().toString());
                        editor.putString(vol_dr1_key, vol_dr1.getText().toString());
                        editor.putString(price_dr1_key, price_dr1.getText().toString());
                        editor.putString(name_dr2_key, name_dr2.getText().toString());
                        editor.putString(alcVol_dr2_key, alcVol_dr2.getText().toString());
                        editor.putString(vol_dr2_key,vol_dr2.getText().toString());
                        editor.putString(price_dr2_key, price_dr2.getText().toString());
                        editor.apply();

                        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void showToast(String text) {
        int duration = Toast.LENGTH_SHORT;
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public boolean checkDigits(String digit){
        String regex = "[\\d\\,\\.]{1,5}";
        if (Pattern.matches(regex, digit)){
            return true;
        }else{
            return false;
        }
    }


}