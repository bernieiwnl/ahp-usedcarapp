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

import com.example.tugas_akhir.CLASS.TipeMobil;
import com.example.tugas_akhir.R;

import java.util.ArrayList;


public class TipeMobilAdapter extends RecyclerView.Adapter<TipeMobilAdapter.MyViewHolder> {

    private ArrayList<TipeMobil> tipe_mobils;
    private Context context;

    public TipeMobilAdapter(ArrayList<TipeMobil> list, Context c){
        this.tipe_mobils = list;
        this.context = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TipeMobilAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_list_tipe_mobil, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.listModel.setText(tipe_mobils.get(position).getModel());
        holder.listMerk.setText(tipe_mobils.get(position).getMerk());
        holder.listTahun.setText(tipe_mobils.get(position).getTahun());
        holder.listTransmisi.setText(tipe_mobils.get(position).getTransmisi());
        holder.listTipe.setText(tipe_mobils.get(position).getTipe());
        holder.listKapasitasMesin.setText(tipe_mobils.get(position).getKapasitas_mesin());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("model", tipe_mobils.get(position).getModel());
                intent.putExtra("merk",tipe_mobils.get(position).getMerk());
                intent.putExtra("tahun", tipe_mobils.get(position).getTahun());
                intent.putExtra("transmisi", tipe_mobils.get(position).getTransmisi());
                intent.putExtra("tipe", tipe_mobils.get(position).getTipe());
                intent.putExtra("kapasitas", tipe_mobils.get(position).getKapasitas_mesin());
                intent.putExtra("id", tipe_mobils.get(position).getIdMobil());
                ((Activity)context).setResult(Activity.RESULT_OK, intent);
                ((Activity)context).finish();
            }
        });
    }

    public int getItemCount() {
        return tipe_mobils.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView listModel;
        private TextView listMerk;
        private TextView listTahun;
        private TextView listTransmisi;
        private TextView listTipe;
        private TextView listKapasitasMesin;

        public MyViewHolder (View view){
            super(view);
            listModel = (TextView) view.findViewById(R.id.model_mobil);
            listMerk = (TextView) view.findViewById(R.id.merk_mobil);
            listTahun  = (TextView) view.findViewById(R.id.tahun_mobil);
            listTransmisi = (TextView) view.findViewById(R.id.transmisi_mobil);
            listTipe = (TextView) view.findViewById(R.id.tipe_mobil);
            listKapasitasMesin = (TextView) view.findViewById(R.id.kapasitas_mesin_mobil);

        }
    }


}
