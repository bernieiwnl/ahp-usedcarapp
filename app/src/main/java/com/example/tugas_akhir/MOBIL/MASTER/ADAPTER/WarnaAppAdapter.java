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

public class WarnaAppAdapter extends RecyclerView.Adapter<WarnaAppAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> warnaMobil;

    public WarnaAppAdapter(Context context, ArrayList<String> warnaMobil) {
        this.context = context;
        this.warnaMobil = warnaMobil;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WarnaAppAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_custom_data_mobil, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtWarnaMobil.setText(warnaMobil.get(position));
        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent();
            i.putExtra("warnaMobil", warnaMobil.get(position));
            ((Activity) context).setResult(Activity.RESULT_OK, i);
            ((Activity) context).finish();
        });
    }

    @Override
    public int getItemCount() {
        return warnaMobil.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtWarnaMobil;

        public MyViewHolder(View view) {
            super(view);
            txtWarnaMobil = (TextView) view.findViewById(R.id.txtData);
        }

    }
}
