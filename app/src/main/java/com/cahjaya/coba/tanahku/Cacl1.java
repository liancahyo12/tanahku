package com.cahjaya.coba.tanahku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.transitionseverywhere.Fade;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;
import com.transitionseverywhere.extra.Scale;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    Locale localeID = new Locale("in", "ID");
    @Override
    protected void onCreate(Bundle savedInstanceState) {    
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cacl1);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.hitungbt) void hitungg(){
        String ls = luas1.getText().toString().trim();
        if (!ls.isEmpty()){
            strInput = luas1.getText().toString();
            luasa = Double.parseDouble(strInput);
            if (rdper.isChecked() && !rdnonper.isChecked()){
                hasila = (luasa/500*40000)+100000+50000;
                NumberFormat format = NumberFormat.getCurrencyInstance(localeID);
                hasilcalc.setText(String.valueOf(format.format(hasila)));
            }
            else  if (!rdper.isChecked() && rdnonper.isChecked()){
                hasila = (luasa/500*80000)+100000+50000;
                NumberFormat format = NumberFormat.getCurrencyInstance(localeID);
                hasilcalc.setText(String.valueOf(format.format(hasila)));
            }
            else{
                Toast.makeText(getApplicationContext(), "Masukan Luas dan pilih jenis tanah terlebih dahulu", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Masukan Luas dan pilih jenis tanah terlebih dahulu", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}