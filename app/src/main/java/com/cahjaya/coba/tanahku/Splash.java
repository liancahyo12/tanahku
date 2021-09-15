package com.cahjaya.coba.tanahku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.cahjaya.coba.tanahku.model.User;
import com.cahjaya.coba.tanahku.network.ApiClient;
import com.cahjaya.coba.tanahku.network.ApiInterface;
import com.cahjaya.coba.tanahku.network.UtilsApi;
import com.cahjaya.coba.tanahku.network.response.UserResponse;
import com.cahjaya.coba.tanahku.utils.HasilDaftarPengaduan;
import com.cahjaya.coba.tanahku.utils.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Splash extends AppCompatActivity {
    private static Tanahku instance;
    Context mContext;
    ApiInterface apiInterface;
    SharedPrefManager sharedPrefManager;
    ProgressDialog progressDialog;
    private int waktu_loading=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splasha);
        getSupportActionBar().hide();
         mContext = this;
        apiInterface = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refresh();
//                if (sharedPrefManager.getSPSudahLogin()){
//                    startActivity(new Intent(Splash.this, MainActivity.class)
//                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//                    finish();
//                }else{

//                }
//
            }
        },waktu_loading);
    }
    public void refresh(){
        Call<UserResponse> refreshToken = apiInterface.refreshToken(sharedPrefManager.getSPToken());
        refreshToken.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                progressDialog.dismiss();
                if (response.code() == 200) {
                    User user = response.body().getUser();
                    sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, user.getName());
                    sharedPrefManager.saveSPString(SharedPrefManager.SP_TOKEN, "Bearer" +response.body().getToken());
                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                    startActivity(new Intent(mContext, MainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                } else {
                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                    startActivity(new Intent(Splash.this, LoginActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                    Toast.makeText(mContext, "Sesi anda berakhir silahkan login ulang", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}