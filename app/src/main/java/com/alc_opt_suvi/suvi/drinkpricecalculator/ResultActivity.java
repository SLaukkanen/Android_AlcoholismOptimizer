package com.alc_opt_suvi.suvi.drinkpricecalculator;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.alc_opt_suvi.suvi.drinkpricecalculator.R.layout.activity_result);

        TextView rTextDif = (TextView)findViewById(com.alc_opt_suvi.suvi.drinkpricecalculator.R.id.r_text_res);
        TextView rTextMes = (TextView)findViewById(com.alc_opt_suvi.suvi.drinkpricecalculator.R.id.r_text_mes);
        SharedPreferences inputs = MainActivity.prefs;

        String name_dr1 = inputs.getString("Name1","");
        String strAlcVol_dr1 = inputs.getString("AlcoholVolume1","");
        String strVol_dr1 = inputs.getString("Volume1","");
        String strPrice_dr1 = inputs.getString("Price1","");
        String name_dr2 = inputs.getString("Name2","");
        String strAlcVol_dr2 = inputs.getString("AlcoholVolume2","");
        String strVol_dr2 = inputs.getString("Volume2","");
        String strPrice_dr2 = inputs.getString("Price2","");

        name_dr1 = name_dr1.trim();
        strAlcVol_dr1 = strAlcVol_dr1.trim();
        strAlcVol_dr1 = strAlcVol_dr1.replace(",", ".");
        strVol_dr1 = strVol_dr1.trim();
        strVol_dr1 = strVol_dr1.replace(",", ".");
        strPrice_dr1 = strPrice_dr1.trim();
        strPrice_dr1 = strPrice_dr1.replace(",", ".");
        name_dr2 = name_dr2.trim();
        strAlcVol_dr2 = strAlcVol_dr2.trim();
        strAlcVol_dr2 = strAlcVol_dr2.replace(",", ".");
        strVol_dr2 = strVol_dr2.trim();
        strVol_dr2 = strVol_dr2.replace(",", ".");
        strPrice_dr2 = strPrice_dr2.trim();
        strPrice_dr2 = strPrice_dr2.replace(",", ".");

        double alcVol_dr1 = Double.valueOf(strAlcVol_dr1);
        double vol_dr1 = Double.valueOf(strVol_dr1);
        double price_dr1 = Double.valueOf(strPrice_dr1);
        double result_dr1 = (alcVol_dr1*vol_dr1)/price_dr1;
        double alcVol_dr2 = Double.valueOf(strAlcVol_dr2);
        double vol_dr2 = Double.valueOf(strVol_dr2);
        double price_dr2 = Double.valueOf(strPrice_dr2);
        double result_dr2 = (alcVol_dr2*vol_dr2)/price_dr2;

        double result = result_dr1-result_dr2;

        DecimalFormat des = new DecimalFormat("0.00");
        String message = "";
        String difference = "";

        if(result <= -0.3){
            message = String.valueOf(getText(com.alc_opt_suvi.suvi.drinkpricecalculator.R.string.r_res1)+" "+name_dr2+"!");
            difference = "The difference is "+des.format(result*(-1));
        }else if(result >= 0.3){
            message = String.valueOf(getText(com.alc_opt_suvi.suvi.drinkpricecalculator.R.string.r_res2)+" "+name_dr1+"!");
            difference = "The difference is "+des.format(result);
        }else {
            message = String.valueOf(getText(com.alc_opt_suvi.suvi.drinkpricecalculator.R.string.r_res3));
            if(result < 0){
                difference = "The difference is minimal ("+des.format(result*(-1))+" for "+name_dr2+")";
            }else{
                difference = "The difference is minimal ("+des.format(result)+" for "+name_dr1+")";
            }

        }

        rTextDif.setText(difference);
        rTextMes.setText(message);

        Button buttonOk = (Button) findViewById(com.alc_opt_suvi.suvi.drinkpricecalculator.R.id.r_button_ok);
        if (buttonOk != null) {
            buttonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
}