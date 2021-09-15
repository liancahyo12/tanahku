package com.cahjaya.coba.tanahku.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cahjaya.coba.tanahku.DaftarPengaduan;
import com.cahjaya.coba.tanahku.KomenActivity;
import com.cahjaya.coba.tanahku.R;
import com.cahjaya.coba.tanahku.model.komen;
import com.cahjaya.coba.tanahku.model.pengaduan;

import java.util.ArrayList;

public class KomenAdapter extends RecyclerView.Adapter<KomenAdapter.KomenViewHolder>{
    private ArrayList<komen> dataList1;
    Context context;

    public KomenAdapter(KomenActivity komenActivity, ArrayList<komen> dataList1) {
        super();
        this.dataList1 = dataList1;
        this.context = context;
    }

    @NonNull
    @Override
    public KomenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recykomen, parent, false);
        return new KomenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KomenAdapter.KomenViewHolder holder, int position) {
        String user = dataList1.get(position).getUser_id();
        if(user !=null)
        {
            holder.cvMain2.setCardBackgroundColor(Color.parseColor("#FFC249"));
        }
        else
        {
            holder.cvMain2.setCardBackgroundColor(Color.parseColor("#6200EE"));
        }
        holder.txtKomen.setText(dataList1.get(position).getKomen());
        holder.txtWaktu.setText(dataList1.get(position).getCreated_at());

    }

    @Override
    public int getItemCount() {
        return dataList1.size();
    }

    public class KomenViewHolder extends RecyclerView.ViewHolder {
        private TextView txtKomen, txtWaktu;
        public CardView cvMain2;

        public KomenViewHolder(@NonNull View itemView) {
            super(itemView);
            txtKomen = (TextView) itemView.findViewById(R.id.txt_komena);
            txtWaktu = (TextView) itemView.findViewById(R.id.txt_waktu);
            cvMain2 = (CardView) itemView.findViewById(R.id.cv_main2);
        }
    }
}
