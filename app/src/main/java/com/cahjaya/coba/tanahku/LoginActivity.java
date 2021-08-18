package com.cahjaya.coba.tanahku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.cahjaya.coba.tanahku.model.User;
import com.cahjaya.coba.tanahku.network.ApiClient;
import com.cahjaya.coba.tanahku.network.ApiInterface;
import com.cahjaya.coba.tanahku.network.response.UserResponse;
import com.cahjaya.coba.tanahku.utils.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.username)
    EditText etEmail;
    @BindView(R.id.password)
    EditText etPassword;

    Context mContext;
    ApiInterface apiInterface;
    SharedPrefManager sharedPrefManager;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        mContext = this;

        ButterKnife.bind(this);
//        apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        sharedPrefManager = new SharedPrefManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);

//        if (sharedPrefManager.getSPSudahLogin()){
//            startActivity(new Intent(LoginActivity.this, MainActivity.class)
//                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//            finish();
//        }
    }
//    @OnClick(R.id.login) void login() {
//        progressDialog.show();
//        Call<UserResponse> postLogin = apiInterface.postLogin(etEmail.getText().toString(),
//                etPassword.getText().toString());
//        postLogin.enqueue(new Callback<UserResponse>() {
//            @Override
//            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
//                progressDialog.dismiss();
//
//                if (response.code() == 200) {
//                    User user = response.body().getUser();
//                    sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, user.getName());
//                    sharedPrefManager.saveSPString(SharedPrefManager.SP_TOKEN, "Bearer " +response.body().getToken());
//                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
//                    startActivity(new Intent(mContext, MainActivity.class)
//                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//                    finish();
//                } else {
//                    Toast.makeText(mContext, "Emai/Password salah", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<UserResponse> call, Throwable t) {
//                progressDialog.dismiss();
//            }
//        });
//    }

    @OnClick(R.id.login) void login() {
                    startActivity(new Intent(mContext, MainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
    }

    @OnClick(R.id.daftarbtn) void daftar(){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }

}