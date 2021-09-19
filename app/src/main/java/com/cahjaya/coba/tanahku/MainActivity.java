package com.cahjaya.coba.tanahku;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cahjaya.coba.tanahku.model.User;
import com.cahjaya.coba.tanahku.network.ApiClient;
import com.cahjaya.coba.tanahku.network.ApiInterface;
import com.cahjaya.coba.tanahku.network.UtilsApi;
import com.cahjaya.coba.tanahku.network.response.UserResponse;
import com.cahjaya.coba.tanahku.utils.SharedPrefManager;
import com.transitionseverywhere.Fade;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;
import com.transitionseverywhere.extra.Scale;

import org.w3c.dom.Text;

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
    @BindView(R.id.container)
    ViewGroup containerr;
    @BindView(R.id.main2bt)
    LinearLayout main2bt;
    @BindView(R.id.akunm)
    LinearLayout akunm;
    @BindView(R.id.profileimg)
    ImageView profileimg;
    @BindView(R.id.homeimg)
    ImageView homeimg;
    @BindView(R.id.hometx)
    TextView hometx;
    @BindView(R.id.profiletx)
    TextView profiletx;
    String resultNama;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Tanahku");
        mContext = this;
        apiInterface = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        tvNama.setText(sharedPrefManager.getSPNama());
        rumahhome();

        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }
        initComponents();

        // untuk mendapatkan data dari activity sebelumnya, yaitu activity login.
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            resultNama = extras.getString("name");
        tvNama.setText(resultNama);
    }
    @OnClick(R.id.homebt) void rumah(){
        TransitionManager.beginDelayedTransition(containerr,new TransitionSet().addTransition(new Scale(0.7f)).addTransition(new Fade()).setInterpolator(true ? new LinearOutSlowInInterpolator() :
                new FastOutLinearInInterpolator()));
        rumahhome();
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }
    }
    @OnClick(R.id.profilebt) void profil(){
        TransitionManager.beginDelayedTransition(containerr,new TransitionSet().addTransition(new Scale(0.7f)).addTransition(new Fade()).setInterpolator(true ? new LinearOutSlowInInterpolator() :
                new FastOutLinearInInterpolator()));
        pertama.setVisibility(View.GONE);
        homea.setVisibility(View.GONE);
        kedua.setVisibility(View.GONE);
        main2bt.setVisibility(View.VISIBLE);
        akunm.setVisibility(View.VISIBLE);
        homeimg.setImageResource(R.drawable.ic_baseline_home_24);
        profileimg.setImageResource(R.drawable.ic_baseline_person_biru_24);
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }
    }
    @OnClick(R.id.pertamabt) void pert() {
        TransitionManager.beginDelayedTransition(containerr,new TransitionSet().addTransition(new Scale(0.7f)).addTransition(new Fade()).setInterpolator(true ? new LinearOutSlowInInterpolator() :
                new FastOutLinearInInterpolator()));
        pertama.setVisibility(View.VISIBLE);
        homea.setVisibility(View.GONE);
        kedua.setVisibility(View.GONE);
        main2bt.setVisibility(View.GONE);
        akunm.setVisibility(View.GONE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
    @OnClick(R.id.keduabt) void kedu(){
        TransitionManager.beginDelayedTransition(containerr,new TransitionSet().addTransition(new Scale(0.7f)).addTransition(new Fade()).setInterpolator(true ? new LinearOutSlowInInterpolator() :
                new FastOutLinearInInterpolator()));
        pertama.setVisibility(View.GONE);
        homea.setVisibility(View.GONE);
        kedua.setVisibility(View.VISIBLE);
        main2bt.setVisibility(View.GONE);
        akunm.setVisibility(View.GONE);
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
        startActivity(new Intent(mContext, DaftarPengaduan.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }

    @Override
    public void onBackPressed() {
        if(!homea.isShown()){
            TransitionManager.beginDelayedTransition(grubbt,new TransitionSet().addTransition(new Scale(0.7f)).addTransition(new Fade()).setInterpolator(true ? new LinearOutSlowInInterpolator() :
                    new FastOutLinearInInterpolator()));
            rumahhome();
            if (getSupportActionBar() != null) {
                getSupportActionBar().show();
            }
        }else{
            finish();
        }
    }
    @OnClick(R.id.btnLogout) void logout() {
        progressDialog.show();
        Call<UserResponse> logout = apiInterface.logout(sharedPrefManager.getSPToken());
        logout.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                progressDialog.dismiss();
                if (response.code() == 200) {
                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                    startActivity(new Intent(MainActivity.this, LoginActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                } else {
                    Toast.makeText(mContext, "Emai/Password salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

    }
//    @OnClick(R.id.btnCekAuth) void cekAuth() {
//        Toast.makeText(this, sharedPrefManager.getSPToken(), Toast.LENGTH_SHORT).show();
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
    public static int dpToPx(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }
    public void rumahhome(){
        pertama.setVisibility(View.GONE);
        homea.setVisibility(View.VISIBLE);
        kedua.setVisibility(View.GONE);
        main2bt.setVisibility(View.VISIBLE);
        akunm.setVisibility(View.GONE);
        homeimg.setImageResource(R.drawable.ic_baseline_home_biru_24);
        profileimg.setImageResource(R.drawable.ic_baseline_person_24);
    }
    private void initComponents(){
        tvNama = (TextView) findViewById(R.id.tvNama);
    }
}