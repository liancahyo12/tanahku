package com.cahjaya.coba.tanahku;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cahjaya.coba.tanahku.model.HasilKomen;
import com.cahjaya.coba.tanahku.model.User;
import com.cahjaya.coba.tanahku.model.pengaduan;
import com.cahjaya.coba.tanahku.network.ApiClient;
import com.cahjaya.coba.tanahku.network.ApiInterface;
import com.cahjaya.coba.tanahku.network.UtilsApi;
import com.cahjaya.coba.tanahku.network.response.UserResponse;
import com.cahjaya.coba.tanahku.utils.KomenAdapter;
import com.cahjaya.coba.tanahku.utils.SharedPrefManager;
import com.transitionseverywhere.Fade;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;
import com.transitionseverywhere.extra.Scale;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
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
    @BindView(R.id.formbtn1)
    TextView formbtn1;
    @BindView(R.id.formbtn2)
    TextView formbtn2;
    String resultNama;
    ProgressDialog progressDialog;
    DownloadManager manager;

    private Callback<ResponseBody> downloadCallback;
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

    @OnClick(R.id.formbtn1) void form1(){
        unduhform();
    }

    @OnClick(R.id.formbtn2) void form2(){
        unduhform();
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

    @OnClick(R.id.ubahpaswbt) void ubahpass(){
        startActivity(new Intent(mContext, UbahpassActivity.class)
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
                    Toast.makeText(mContext, "Email/Password salah", Toast.LENGTH_SHORT).show();
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

    public void getfrom(){
        progressDialog.show();
        Call<ResponseBody> getUnduhform = apiInterface.getUnduhform(sharedPrefManager.getSPToken());
        getUnduhform.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()){
                    Log.i("debug", "onResponse: BERHASIL");
                    Log.d("debug", "server contacted and has file");

                    boolean writtenToDisk = writeResponseBodyToDisk(response.body());

                    Log.d("debug", "file download was a success? " + writtenToDisk);
                    progressDialog.dismiss();
                    Toast.makeText(mContext, "Unduh form.pdf tersimpan pada folder Downloads", Toast.LENGTH_SHORT).show();
//                    bukaform();
//                    Toast.makeText(mContext, "Pengaduan berhasil diajukan silahkan tunggu respon admin", Toast.LENGTH_SHORT).show();

                } else {
                    Log.i("debug", "onResponse: load data gagal");
//                    String error_message = "Pengajuan pengaduan gagal, Mohon cek ulang data anda";
//                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
            File formpdf = new File(Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS) + File.separator + "form.pdf");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(formpdf);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                    Log.d("debug", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
    public void bukaform(){
        File file = new File(Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS) + File.separator + "form.pdf");
        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.fromFile(file),"application/pdf");
        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        Intent intent = Intent.createChooser(target, "Open File");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Instruct the user to install a PDF reader here, or something
        }
    }

    public void unduhform(){
        new AlertDialog.Builder(this)
                .setTitle("Konfirmasi Unduh")
                .setMessage("Unduh form permohonan?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        getfrom();
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }
}