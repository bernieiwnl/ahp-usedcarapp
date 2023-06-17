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

public class KelengkapanAppAdapter extends RecyclerView.Adapter<KelengkapanAppAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> kelengkapanMobil;

    public KelengkapanAppAdapter(Context context, ArrayList<String> kelengkapanMobil) {
        this.context = context;
        this.kelengkapanMobil = kelengkapanMobil;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KelengkapanAppAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_custom_data_mobil, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtKelengkapanMobil.setText(kelengkapanMobil.get(position));
    }

    @Override
    public int getItemCount() {
        return kelengkapanMobil.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtKelengkapanMobil;

        public MyViewHolder(View view) {
            super(view);
            txtKelengkapanMobil = (TextView) view.findViewById(R.id.txtData);
        }

    }

}
