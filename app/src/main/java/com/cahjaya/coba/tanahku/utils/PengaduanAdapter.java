package com.cahjaya.coba.tanahku.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cahjaya.coba.tanahku.DaftarPengaduan;
import com.cahjaya.coba.tanahku.KomenActivity;
import com.cahjaya.coba.tanahku.LoginActivity;
import com.cahjaya.coba.tanahku.R;
import com.cahjaya.coba.tanahku.RegisterActivity;
import com.cahjaya.coba.tanahku.model.pengaduan;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class PengaduanAdapter extends RecyclerView.Adapter<PengaduanAdapter.PengaduanViewHolder> {
    private ArrayList<pengaduan> dataList;
    Context context;
    public CardView cvMain;


    public PengaduanAdapter(DaftarPengaduan daftarPengaduan, ArrayList<pengaduan> dataList) {
        super();
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public PengaduanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recypengaduan, parent, false);
        return new PengaduanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PengaduanViewHolder holder, int position) {
        Integer id = dataList.get(position).getId();
        holder.txtNama.setText(dataList.get(position).getNohak());
        holder.txtNoberkas.setText(dataList.get(position).getNoberkas());
        holder.txtPengaduant.setText(dataList.get(position).getDeskripsi());
        if (dataList.get(position).getCase_status()==1){
            holder.txtStatusp.setText("Baru");
        }else if(dataList.get(position).getCase_status()==2){
            holder.txtStatusp.setText("Proses");
        }else{
            holder.txtStatusp.setText("Selesai");
        }

        holder.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Clicked element "+id.toString(), Snackbar.LENGTH_LONG).show();
                Intent intent= new Intent(view.getContext(), KomenActivity.class);
                intent.putExtra("id", id.toString());
                view.getContext().startActivity(intent);
                ((Activity)view.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class PengaduanViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtNoberkas, txtPengaduant, txtStatusp;
        public CardView cvMain;

        public PengaduanViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.txt_nama);
            txtNoberkas = (TextView) itemView.findViewById(R.id.txt_noberkas);
            txtPengaduant = (TextView) itemView.findViewById(R.id.txt_pengaduant);
            txtStatusp = (TextView) itemView.findViewById(R.id.txt_statusp);
            cvMain = (CardView) itemView.findViewById(R.id.cv_main);
        }
    }
}

