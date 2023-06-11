package com.example.tugas_akhir.ADAPTER;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_akhir.R;

import java.util.ArrayList;

public class DaftarKriteriaAdapter extends RecyclerView.Adapter<DaftarKriteriaAdapter.MyViewHolder>{
    private Context c;
    private ArrayList<String> kriterias;

    public DaftarKriteriaAdapter(Context c, ArrayList<String> kriterias){
        this.c = c;
        this.kriterias = kriterias;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DaftarKriteriaAdapter.MyViewHolder(LayoutInflater.from(c).inflate(R.layout.list_custom_layout_daftarkriteria, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final int p = position;
        holder.kriteria.setText(kriterias.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("preferensi", kriterias.get(p));
                ((Activity)c).setResult(Activity.RESULT_OK, i);
                ((Activity)c).finish();
            }
        });
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
