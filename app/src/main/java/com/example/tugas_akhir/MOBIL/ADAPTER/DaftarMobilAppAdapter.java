package com.example.tugas_akhir.MOBIL.ADAPTER;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_akhir.ADAPTER.ImageSliderAdapter;
import com.example.tugas_akhir.MOBIL.CLASS.NewMobil;
import com.example.tugas_akhir.MOBIL.DetailMobilAppActivity;
import com.example.tugas_akhir.MOBIL.UbahMobilAppActivity;
import com.example.tugas_akhir.PELANGGAN.UbahPelangganAppActivity;
import com.example.tugas_akhir.R;
import com.smarteist.autoimageslider.SliderView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DaftarMobilAppAdapter extends RecyclerView.Adapter<DaftarMobilAppAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<NewMobil> mobils;

    public DaftarMobilAppAdapter() {

    }

    public DaftarMobilAppAdapter(Context context, ArrayList<NewMobil> mobils) {
        this.context = context;
        this.mobils = mobils;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DaftarMobilAppAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_custom_daftar_mobilapp, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final int p = position;
        ArrayList<String> daftarFotoMobil = new ArrayList<>();
        daftarFotoMobil.clear();
        daftarFotoMobil.addAll(mobils.get(position).getFotoMobil());
        ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(context, daftarFotoMobil);
        holder.sliderViewFotoMobil.setSliderAdapter(imageSliderAdapter);

        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String yourFormattedString = formatter.format(mobils.get(p).getKilometerMobil());
        holder.textViewJudulMobil.setText(mobils.get(p).getNamaMerkMobil() + " " + mobils.get(p).getWarnaMobil());
        holder.textViewSubDeskripsiMobil.setText(yourFormattedString + " Km, Transmisi : " + mobils.get(p).getTransmisiMobil());

        holder.buttonUbahDataMobil.setOnClickListener(v -> {
            Intent i = new Intent(context, UbahMobilAppActivity.class);
            i.putExtra("idMobil", mobils.get(position).getIdMobil());
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.getApplicationContext().startActivity(i);
        });

        holder.buttonDetailDataMobil.setOnClickListener(v -> {
            Intent i = new Intent(context, DetailMobilAppActivity.class);
            i.putExtra("idMobil", mobils.get(position).getIdMobil());
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.getApplicationContext().startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return mobils.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private SliderView sliderViewFotoMobil;
        private Button buttonUbahDataMobil, buttonDetailDataMobil;
        private TextView textViewJudulMobil, textViewSubDeskripsiMobil;

        public MyViewHolder(View view) {
            super(view);

            sliderViewFotoMobil = (SliderView) view.findViewById(R.id.imgSliderMobil);
            buttonDetailDataMobil = (Button) view.findViewById(R.id.btnDetailMobil);
            buttonUbahDataMobil = (Button) view.findViewById(R.id.btnUbahDataMobil);
            textViewJudulMobil = (TextView) view.findViewById(R.id.txtJudulMobil);
            textViewSubDeskripsiMobil = (TextView) view.findViewById(R.id.txtSubDeskripsiMobil);

        }
    }


}
