package com.example.tugas_akhir.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_akhir.CLASS.Kriteria;
import com.example.tugas_akhir.R;

import java.util.ArrayList;

public class UbahKriteriaAdapter extends RecyclerView.Adapter<UbahKriteriaAdapter.MyViewHolder>{
    private Context c;
    private ArrayList<Kriteria> kriterias;

    public UbahKriteriaAdapter(Context c, ArrayList<Kriteria> kriterias){
        this.c = c;
        this.kriterias = kriterias;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UbahKriteriaAdapter.MyViewHolder(LayoutInflater.from(c).inflate(R.layout.list_custom_layout_daftarpreferensi, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.kriteria.setText(kriterias.get(position).getNama_Kriteria());
    }

    @Override
    public int getItemCount() {
        return kriterias.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView kriteria;

        public MyViewHolder(View view){
            super(view);
            kriteria = (TextView) view.findViewById(R.id.kriteriaMobil);
        }
    }
}
