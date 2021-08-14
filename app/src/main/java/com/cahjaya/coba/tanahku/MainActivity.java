package com.cahjaya.coba.tanahku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cahjaya.coba.tanahku.model.User;
import com.cahjaya.coba.tanahku.network.ApiClient;
import com.cahjaya.coba.tanahku.network.ApiInterface;
import com.cahjaya.coba.tanahku.utils.SharedPrefManager;
import com.transitionseverywhere.Fade;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;
import com.transitionseverywhere.extra.Scale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;
    ApiInterface apiInterface;
    Context mContext;
    @BindView(R.id.tvNama)
    TextView tvNama;
    @BindView(R.id.homea)
    LinearLayout homea;
    @BindView(R.id.pertama)
    LinearLayout pertama;
    @BindView(R.id.kedua)
    LinearLayout kedua;
    @BindView(R.id.grubbt)
    ViewGroup grubbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
//        sharedPrefManager = new SharedPrefManager(this);
//        apiInterface = ApiClient.getClient().create(ApiInterface.class);

//        tvNama.setText(sharedPrefManager.getSPNama());
        pertama.setVisibility(View.GONE);
        homea.setVisibility(View.VISIBLE);
        kedua.setVisibility(View.GONE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }
    }
    @OnClick(R.id.pertamabt) void pert() {
        TransitionManager.beginDelayedTransition(grubbt,new TransitionSet().addTransition(new Scale(0.7f)).addTransition(new Fade()).setInterpolator(true ? new LinearOutSlowInInterpolator() :
                new FastOutLinearInInterpolator()));
        pertama.setVisibility(View.VISIBLE);
        homea.setVisibility(View.GONE);
        kedua.setVisibility(View.GONE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
    @OnClick(R.id.keduabt) void kedu(){
        TransitionManager.beginDelayedTransition(grubbt,new TransitionSet().addTransition(new Scale(0.7f)).addTransition(new Fade()).setInterpolator(true ? new LinearOutSlowInInterpolator() :
                new FastOutLinearInInterpolator()));
        pertama.setVisibility(View.GONE);
        homea.setVisibility(View.GONE);
        kedua.setVisibility(View.VISIBLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
    @OnClick(R.id.calc1_btn) void calc1(){
        startActivity(new Intent(mContext, Cacl1.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }
    @OnClick(R.id.calc2_btn) void calc2(){
        startActivity(new Intent(mContext, Calc2.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }
    @OnClick(R.id.tanyabt) void pengaduan(){
        startActivity(new Intent(mContext, Pengaduan.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }

    @Override
    public void onBackPressed() {
        if(!homea.isShown()){
            TransitionManager.beginDelayedTransition(grubbt,new TransitionSet().addTransition(new Scale(0.7f)).addTransition(new Fade()).setInterpolator(true ? new LinearOutSlowInInterpolator() :
                    new FastOutLinearInInterpolator()));
            pertama.setVisibility(View.GONE);
            homea.setVisibility(View.VISIBLE);
            kedua.setVisibility(View.GONE);
            if (getSupportActionBar() != null) {
                getSupportActionBar().show();
            }
        }else{
            finish();
        }
    }
//    @OnClick(R.id.btnLogout) void logout() {
//        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
//        startActivity(new Intent(MainActivity.this, LoginActivity.class)
//                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//        finish();
//    }
//    @OnClick(R.id.btnCekAuth) void cekAuth() {
////        Toast.makeText(this, sharedPrefManager.getSPToken(), Toast.LENGTH_SHORT).show();
//        Call<User> getUser = apiInterface.getUser(sharedPrefManager.getSPToken());
//        getUser.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                if (response.code() == 200) {
//                    Toast.makeText(MainActivity.this, response.body().getEmail(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//
//            }
//        });
//
//    }
}