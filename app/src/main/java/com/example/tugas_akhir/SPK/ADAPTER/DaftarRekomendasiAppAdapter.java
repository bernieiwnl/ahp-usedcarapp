package com.example.tugas_akhir.SPK.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_akhir.ADAPTER.ImageSliderAdapter;
import com.example.tugas_akhir.MOBIL.CLASS.NewMobil;
import com.example.tugas_akhir.R;
import com.example.tugas_akhir.SPK.CLASS.RekomendasiMobil;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class DaftarRekomendasiAppAdapter extends RecyclerView.Adapter<DaftarRekomendasiAppAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<RekomendasiMobil> rekomendasiMobils;

    public DaftarRekomendasiAppAdapter(Context context, ArrayList<RekomendasiMobil> rekomendasiMobils) {
        this.context = context;
        this.rekomendasiMobils = rekomendasiMobils;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_custom_layout_daftar_rekomendasi_app, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ArrayList<String> daftarFotoMobil = new ArrayList<>();
        daftarFotoMobil.clear();
        daftarFotoMobil.addAll(rekomendasiMobils.get(position).getNewMobil().getFotoMobil());
        ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(context, daftarFotoMobil);
        holder.sliderViewFotoMobil.setSliderAdapter(imageSliderAdapter);
        holder.textViewRate.setText(String.valueOf(rekomendasiMobils.get(position).getNilaiBobot()));
        holder.textViewNamaMerkMobil.setText(rekomendasiMobils.get(position).getNewMobil().getNamaMerkMobil());
        holder.textViewTipeMobil.setText(rekomendasiMobils.get(position).getNewMobil().getTipeMobil());
        holder.textViewTransmisiMobil.setText(rekomendasiMobils.get(position).getNewMobil().getTransmisiMobil());
        holder.textViewTahunMobil.setText(rekomendasiMobils.get(position).getNewMobil().getTahunMobil().toString());
        holder.textViewKilometerMobil.setText(rekomendasiMobils.get(position).getNewMobil().getKilometerMobil().toString());
        holder.textViewWarnaMobil.setText(rekomendasiMobils.get(position).getNewMobil().getWarnaMobil());
        holder.textViewKapasitasMesinMobil.setText(rekomendasiMobils.get(position).getNewMobil().getKapasitasMobil().toString());
        holder.textViewHargaMobil.setText(rekomendasiMobils.get(position).getNewMobil().getHargaMobil().toString());
        holder.textViewKondisiMesin.setText(rekomendasiMobils.get(position).getNewMobil().getKondisiMesinMobil().toString());
        holder.textViewServiceRecordMobil.setText(rekomendasiMobils.get(position).getNewMobil().getServiceRecordMobil());
        holder.textViewKondisiInterior.setText(rekomendasiMobils.get(position).getNewMobil().getKondisiInteriorMobil());
        holder.textViewKeadaanBody.setText(rekomendasiMobils.get(position).getNewMobil().getKeadaanMobil());
        holder.textViewKelengkapanMobil.setText(rekomendasiMobils.get(position).getNewMobil().getKelengkapanMobil());

    }

    @Override
    public int getItemCount() {
        return rekomendasiMobils.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private SliderView sliderViewFotoMobil;
        private TextView textViewNamaMerkMobil,
                textViewRate,
                textViewTipeMobil,
                textViewTransmisiMobil,
                textViewTahunMobil,
                textViewKilometerMobil,
                textViewWarnaMobil,
                textViewKapasitasMesinMobil,
                textViewHargaMobil,
                textViewKondisiMesin,
                textViewServiceRecordMobil,
                textViewKondisiInterior,
                textViewKeadaanBody,
                textViewKelengkapanMobil;

        public MyViewHolder(View view) {
            super(view);

            sliderViewFotoMobil = (SliderView) view.findViewById(R.id.imgSliderMobil);
            textViewRate = (TextView) view.findViewById(R.id.textView_rate);
            textViewNamaMerkMobil = (TextView) view.findViewById(R.id.textViewNamaMerkMobil);
            textViewTipeMobil = (TextView) view.findViewById(R.id.textViewTipeMobil);
            textViewTransmisiMobil = (TextView) view.findViewById(R.id.textViewTransmisiMobil);
            textViewTahunMobil = (TextView) view.findViewById(R.id.textViewTahunMobil);
            textViewKilometerMobil = (TextView) view.findViewById(R.id.textViewKilometerMobil);
            textViewWarnaMobil = (TextView) view.findViewById(R.id.textViewWarnaMobil);
            textViewKapasitasMesinMobil = (TextView) view.findViewById(R.id.textViewTransmisiMobil);
            textViewHargaMobil = (TextView) view.findViewById(R.id.textViewHargaMobil);
            textViewKondisiMesin = (TextView) view.findViewById(R.id.textViewKondisiMesin);
            textViewServiceRecordMobil = (TextView) view.findViewById(R.id.textViewServiceRecordMobil);
            textViewKondisiInterior = (TextView) view.findViewById(R.id.textViewKondisiInterior);
            textViewKeadaanBody = (TextView) view.findViewById(R.id.textViewKeadaanBody);
            textViewKelengkapanMobil = (TextView) view.findViewById(R.id.textViewKelengkapanMobil);
        }
    }

}
