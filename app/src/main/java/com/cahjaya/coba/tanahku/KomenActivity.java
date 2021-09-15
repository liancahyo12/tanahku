package com.cahjaya.coba.tanahku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.cahjaya.coba.tanahku.model.HasilKomen;
import com.cahjaya.coba.tanahku.model.komen;
import com.cahjaya.coba.tanahku.model.pengaduan;
import com.cahjaya.coba.tanahku.network.ApiInterface;
import com.cahjaya.coba.tanahku.network.UtilsApi;
import com.cahjaya.coba.tanahku.utils.HasilDaftarPengaduan;
import com.cahjaya.coba.tanahku.utils.KomenAdapter;
import com.cahjaya.coba.tanahku.utils.PengaduanAdapter;
import com.cahjaya.coba.tanahku.utils.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KomenActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    Context mContext;
    ApiInterface apiInterface;
    SharedPrefManager sharedPrefManager;
    ProgressDialog progressDialog;
    String id;
    @BindView(R.id.koment)
    EditText etKomen;
    Handler handler = new Handler();
    int apiDelayed = 5*1000; //1 second=1000 milisecond, 5*1000=5seconds
    Runnable runnable;
    KomenAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komen);
        ButterKnife.bind(this);
        mContext = this;
        Intent i = getIntent();
        id = i.getStringExtra("id");
        apiInterface = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        recyclerView = (RecyclerView) findViewById(R.id.dftkomen);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(KomenActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        addData1();
    }

    @Override
    protected void onResume() {
        super.onResume();

        handler.postDelayed( runnable = new Runnable() {
            public void run() {
                //do your function;
                addData1();
                handler.postDelayed(runnable, apiDelayed);
            }
        }, apiDelayed); // so basically after your getHeroes(), from next time it will be 5 sec repeated
    }
    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable); //stop handler when activity not visible
    }
    void addData1(){
        Call<HasilKomen> getPengaduanKomen = apiInterface.getPengaduanKomen(id,sharedPrefManager.getSPToken());
        getPengaduanKomen.enqueue(new Callback<HasilKomen>() {
            @Override
            public void onResponse(Call<HasilKomen> call, Response<HasilKomen> response) {
                progressDialog.dismiss();
                adapter = new KomenAdapter(KomenActivity.this,response.body().getDataList1());
                recyclerView.scrollToPosition(adapter.getItemCount()-1);
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
            public void onFailure(Call<HasilKomen> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.komenbt) void tanya() {
        progressDialog.show();
        Call<komen> postPengaduanKomen = apiInterface.postPengaduanKomen(id, sharedPrefManager.getSPToken(),
                etKomen.getText().toString());
        postPengaduanKomen.enqueue(new Callback<komen>() {
            @Override
            public void onResponse(Call<komen> call, Response<komen> response) {
                etKomen.setText("");

                addData1();
                progressDialog.dismiss();
                if (response.isSuccessful()){
                    Log.i("debug", "onResponse: BERHASIL");
//                    Toast.makeText(mContext, "Pengaduan berhasil diajukan silahkan tunggu respon admin", Toast.LENGTH_SHORT).show();

                } else {
                    Log.i("debug", "onResponse: komen gagal");
//                    String error_message = "Pengajuan pengaduan gagal, Mohon cek ulang data anda";
//                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<komen> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),DaftarPengaduan.class));
        finish();
    }

}