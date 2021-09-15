package com.cahjaya.coba.tanahku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.cahjaya.coba.tanahku.model.pengaduan;
import com.cahjaya.coba.tanahku.network.ApiInterface;
import com.cahjaya.coba.tanahku.network.UtilsApi;
import com.cahjaya.coba.tanahku.network.response.UserResponse;
import com.cahjaya.coba.tanahku.utils.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pengaduan extends AppCompatActivity {

    @BindView(R.id.nohak)
    EditText nohak;
    @BindView(R.id.noberkas)
    EditText noberkas;
    @BindView(R.id.tahunberkas)
    EditText tahunberkas;
    @BindView(R.id.alamat)
    EditText alamat;
    @BindView(R.id.pertanyaan)
    EditText pertanyaan;

    Context mContext;
    ApiInterface apiInterface;
    SharedPrefManager sharedPrefManager;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaduan);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        mContext = this;
        apiInterface = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
    }
    @OnClick(R.id.tanyakanbt) void tanya() {
        progressDialog.show();
        Call<pengaduan> postPengaduan = apiInterface.postPengaduan(sharedPrefManager.getSPToken(),
                nohak.getText().toString(),
                noberkas.getText().toString(),
                tahunberkas.getText().toString(),
                alamat.getText().toString(),
                pertanyaan.getText().toString());
        postPengaduan.enqueue(new Callback<pengaduan>() {
            @Override
            public void onResponse(Call<pengaduan> call, Response<pengaduan> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()){
                    Log.i("debug", "onResponse: BERHASIL");
                    Toast.makeText(mContext, "Pengaduan berhasil diajukan silahkan tunggu respon admin", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(mContext, DaftarPengaduan.class));

                } else {
                    Log.i("debug", "onResponse: Pendaftaran gagal");
                    String error_message = "Pengajuan pengaduan gagal, Mohon cek ulang data anda";
                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<pengaduan> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}