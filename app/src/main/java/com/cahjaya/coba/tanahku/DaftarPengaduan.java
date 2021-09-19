package com.cahjaya.coba.tanahku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.cahjaya.coba.tanahku.model.pengaduan;
import com.cahjaya.coba.tanahku.network.ApiInterface;
import com.cahjaya.coba.tanahku.network.UtilsApi;
import com.cahjaya.coba.tanahku.utils.HasilDaftarPengaduan;
import com.cahjaya.coba.tanahku.utils.PengaduanAdapter;
import com.cahjaya.coba.tanahku.utils.SharedPrefManager;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarPengaduan extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PengaduanAdapter adapter;
    private ArrayList<pengaduan> pengaduanArrayList;
    Context mContext;
    ApiInterface apiInterface;
    SharedPrefManager sharedPrefManager;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_pengaduan);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Pengaduan");
        mContext = this;
        apiInterface = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        recyclerView = (RecyclerView) findViewById(R.id.dftpengaduan);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DaftarPengaduan.this);
        recyclerView.setLayoutManager(layoutManager);
        addData();
    }
    @OnClick(R.id.tanyabtnnew) void pengaduannn(){
        startActivity(new Intent(getApplicationContext(),Pengaduan.class));
        finish();
    }
    void addData(){
        Call<HasilDaftarPengaduan> getPengaduan = apiInterface.getPengaduan(sharedPrefManager.getSPToken());
        getPengaduan.enqueue(new Callback<HasilDaftarPengaduan>() {
            @Override
            public void onResponse(Call<HasilDaftarPengaduan> call, Response<HasilDaftarPengaduan> response) {
                progressDialog.dismiss();
                PengaduanAdapter adapter = new PengaduanAdapter(DaftarPengaduan.this,response.body().getDataList());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
                if (response.isSuccessful()){
                    Log.i("debug", "onResponse: BERHASIL");
//                    Toast.makeText(mContext, "Pengaduan berhasil diajukan silahkan tunggu respon admin", Toast.LENGTH_SHORT).show();

                } else {
                    Log.i("debug", "onResponse: load data gagal");
//                    String error_message = "Pengajuan pengaduan gagal, Mohon cek ulang data anda";
//                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<HasilDaftarPengaduan> call, Throwable t) {
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