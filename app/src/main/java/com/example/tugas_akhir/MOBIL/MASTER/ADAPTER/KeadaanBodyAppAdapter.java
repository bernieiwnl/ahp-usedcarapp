package com.example.tugas_akhir.MOBIL.MASTER.ADAPTER;

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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("keadaanMobil", keadaanMobil.get(position));
                ((Activity) context).setResult(Activity.RESULT_OK, i);
                ((Activity) context).finish();
            }
        });
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
