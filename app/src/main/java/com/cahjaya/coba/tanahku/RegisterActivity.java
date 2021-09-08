package com.cahjaya.coba.tanahku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.cahjaya.coba.tanahku.model.User;
import com.cahjaya.coba.tanahku.network.ApiInterface;
import com.cahjaya.coba.tanahku.network.UtilsApi;
import com.cahjaya.coba.tanahku.network.response.UserResponse;
import com.cahjaya.coba.tanahku.utils.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.username)
    EditText etName;
    @BindView(R.id.email)
    EditText etEmail;
    @BindView(R.id.password)
    EditText etPassword;
    @BindView(R.id.confirm_password)
    EditText etPassword_conf;

    Context mContext;
    ApiInterface apiInterface;
    SharedPrefManager sharedPrefManager;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        mContext = this;
        apiInterface = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.daftarbtn) void register() {
        progressDialog.show();
        Call<UserResponse> postRegister = apiInterface.postRegister(etName.getText().toString(),
                etEmail.getText().toString(),
                etPassword.getText().toString(),
                etPassword_conf.getText().toString());
        postRegister.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()){
                    Log.i("debug", "onResponse: BERHASIL");
//                    try {
//                        JSONObject jsonRESULTS = new JSONObject(response.body().toString());
//                        if (jsonRESULTS.getString("error").equals("false")){
                            Toast.makeText(mContext, "Pendaftaran Berhasil, silakan login untuk melanjutkan", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(mContext, LoginActivity.class));
//                        } else {
////                            String error_message = jsonRESULTS.getString("error_msg");
//                            String error_message = "Pendaftaran gagal, Mohon cek ulang data anda";
//                            Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                } else {
                    Log.i("debug", "onResponse: Pendaftaran gagal");
                    String error_message = "Pendaftaran gagal, Mohon cek ulang data anda";
                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        finish();
    }
}