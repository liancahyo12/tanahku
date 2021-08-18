package com.cahjaya.coba.tanahku.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cahjaya.coba.tanahku.R;
import com.cahjaya.coba.tanahku.model.pengaduan;

import java.util.ArrayList;


public class PengaduanAdapter extends RecyclerView.Adapter<PengaduanAdapter.PengaduanViewHolder> {
    private ArrayList<pengaduan> dataList;
    public PengaduanAdapter(ArrayList<pengaduan> dataList) {
        this.dataList = dataList;
    }

    @Override
    public PengaduanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recypengaduan, parent, false);
        return new PengaduanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PengaduanViewHolder holder, int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtNoberkas.setText(dataList.get(position).getNoberkas());
        holder.txtPengaduant.setText(dataList.get(position).getPengaduant());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class PengaduanViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtNoberkas, txtPengaduant;

        public PengaduanViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.txt_nama);
            txtNoberkas = (TextView) itemView.findViewById(R.id.txt_noberkas);
            txtPengaduant = (TextView) itemView.findViewById(R.id.txt_pengaduant);
        }
    }
}

