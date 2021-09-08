package com.cahjaya.coba.tanahku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cahjaya.coba.tanahku.model.pengaduan;
import com.cahjaya.coba.tanahku.utils.PengaduanAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DaftarPengaduan extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PengaduanAdapter adapter;
    private ArrayList<pengaduan> pengaduanArrayList;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_pengaduan);
        addData();
        ButterKnife.bind(this);
        mContext = this;
        recyclerView = (RecyclerView) findViewById(R.id.dftpengaduan);

        adapter = new PengaduanAdapter(pengaduanArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DaftarPengaduan.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
    @OnClick(R.id.tanyabtnnew) void pengaduannn(){
        startActivity(new Intent(getApplicationContext(),Pengaduan.class));
        finish();
    }
    void addData(){
        pengaduanArrayList = new ArrayList<>();
        pengaduanArrayList.add(new pengaduan("Dimas Maulana", "1414370309", "123456789"));
        pengaduanArrayList.add(new pengaduan("Fadly Yonk", "1214234560", "987654321"));
        pengaduanArrayList.add(new pengaduan("Ariyandi Nugraha", "1214230345", "987648765"));
        pengaduanArrayList.add(new pengaduan("Aham Siswana", "1214378098", "098758124"));
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}