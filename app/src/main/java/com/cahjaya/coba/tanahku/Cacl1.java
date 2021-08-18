package com.cahjaya.coba.tanahku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.transitionseverywhere.Fade;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;
import com.transitionseverywhere.extra.Scale;

import butterknife.BindView;
import butterknife.OnClick;

public class Cacl1 extends AppCompatActivity {
    @BindView(R.id.rdgrub)
    RadioGroup rdgrubb;
    @BindView(R.id.radioPertanian)
    RadioButton rdper;
    @BindView(R.id.radioNonpertanian)
    RadioButton rdnonper;
    @BindView(R.id.hasilcalc1)
    TextView hasilcalc;
    @BindView(R.id.luas1)
    EditText luas1;
    double hasila, luasa;
    String strInput="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cacl1);

    }
    @OnClick(R.id.hitungbt) void hitungg(){
        if (luas1.getText().equals("")){
            hasilcalc.setText(0);
        }else{
            strInput = luas1.getText().toString();
            luasa = Double.parseDouble(strInput );
            if (rdper.isChecked() && !rdnonper.isChecked()){
                hasila = (luasa*40000)+100000;
                hasilcalc.setText(String.valueOf(hasila));
            }
            else  if (!rdper.isChecked() && rdnonper.isChecked()){
                hasila = (luasa*80000)+100000;
                hasilcalc.setText(String.valueOf(hasila));
            }
            else{
                hasilcalc.setText(0);
            }
        }
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}