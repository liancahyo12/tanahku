package com.cahjaya.coba.tanahku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Calc2 extends AppCompatActivity {
    @BindView(R.id.rdgrub1)
    RadioGroup rdgrubb;
    @BindView(R.id.radioPertanian1)
    RadioButton rdper;
    @BindView(R.id.radioNonpertanian1)
    RadioButton rdnonper;
    @BindView(R.id.hasilcalc2)
    TextView hasilcalc;
    @BindView(R.id.luas2)
    EditText luas1;
    double hasila, luasa;
    String strInput="0";
    Locale localeID = new Locale("in", "ID");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc2);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.hitungbt1) void hitungg(){
        String ls = luas1.getText().toString().trim();
        if (!ls.isEmpty()){
            strInput = luas1.getText().toString();
            luasa = Double.parseDouble(strInput);
            if (rdper.isChecked() && !rdnonper.isChecked()){
                hasila = (luasa/500*40000)+350000+50000;
                NumberFormat format = NumberFormat.getCurrencyInstance(localeID);
                hasilcalc.setText(String.valueOf(format.format(hasila)));
            }
            else  if (!rdper.isChecked() && rdnonper.isChecked()){
                hasila = (luasa/500*80000)+350000+50000;
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