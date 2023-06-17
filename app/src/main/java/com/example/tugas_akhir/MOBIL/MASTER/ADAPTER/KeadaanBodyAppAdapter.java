package com.example.tugas_akhir.MOBIL.MASTER.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_akhir.R;

import java.util.ArrayList;

public class KeadaanBodyAppAdapter extends RecyclerView.Adapter<KeadaanBodyAppAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> keadaanMobil;

    public KeadaanBodyAppAdapter(Context context, ArrayList<String> keadaanMobil) {
        this.context = context;
        this.keadaanMobil = keadaanMobil;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KeadaanBodyAppAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_custom_data_mobil, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtKeadaanBody.setText(keadaanMobil.get(position));
    }

    @Override
    public int getItemCount() {
        return keadaanMobil.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtKeadaanBody;

        public MyViewHolder(View view) {
            super(view);
            txtKeadaanBody = (TextView) view.findViewById(R.id.txtData);
        }

    }
}
