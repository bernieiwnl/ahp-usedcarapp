package com.example.tugas_akhir.SPK.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_akhir.ADAPTER.ImageSliderAdapter;
import com.example.tugas_akhir.MOBIL.CLASS.NewMobil;
import com.example.tugas_akhir.R;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class DaftarAlternatifAppAdapter extends RecyclerView.Adapter<DaftarAlternatifAppAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<NewMobil> mobils;

    public DaftarAlternatifAppAdapter() {

    }

    public DaftarAlternatifAppAdapter(Context context, ArrayList<NewMobil> mobils) {
        this.context = context;
        this.mobils = mobils;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_custom_layout_alternatif_mobil_app, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ArrayList<String> daftarFotoMobil = new ArrayList<>();
        daftarFotoMobil.clear();
        daftarFotoMobil.addAll(mobils.get(position).getFotoMobil());
        ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(context, daftarFotoMobil);
        holder.sliderViewFotoMobil.setSliderAdapter(imageSliderAdapter);

        holder.textViewNamaMerkMobil.setText(mobils.get(position).getNamaMerkMobil());
        holder.textViewTipeMobil.setText(mobils.get(position).getTipeMobil());
        holder.textViewTransmisiMobil.setText(mobils.get(position).getTransmisiMobil());
        holder.textViewTahunMobil.setText(mobils.get(position).getTahunMobil().toString());
        holder.textViewKilometerMobil.setText(mobils.get(position).getKilometerMobil().toString());
        holder.textViewWarnaMobil.setText(mobils.get(position).getWarnaMobil());
        holder.textViewKapasitasMesinMobil.setText(mobils.get(position).getKapasitasMobil().toString());
        holder.textViewHargaMobil.setText(mobils.get(position).getHargaMobil().toString());
        holder.textViewKondisiMesin.setText(mobils.get(position).getKondisiMesinMobil().toString());
        holder.textViewServiceRecordMobil.setText(mobils.get(position).getServiceRecordMobil());
        holder.textViewKondisiInterior.setText(mobils.get(position).getKondisiInteriorMobil());
        holder.textViewKeadaanBody.setText(mobils.get(position).getKeadaanMobil());
        holder.textViewKelengkapanMobil.setText(mobils.get(position).getKelengkapanMobil());

        holder.buttonHapusAlt.setOnClickListener(v -> {
            deleteItem(position);
        });

    }

    private void deleteItem(int position) {
        mobils.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mobils.size());
    }


    @Override
    public int getItemCount() {
        return mobils.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private SliderView sliderViewFotoMobil;
        private TextView textViewNamaMerkMobil,
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

        private Button buttonHapusAlt;

        public MyViewHolder(View view) {
            super(view);

            sliderViewFotoMobil = (SliderView) view.findViewById(R.id.imgSliderMobil);
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
            buttonHapusAlt = (Button) view.findViewById(R.id.btnHapusAlt);
        }
    }


}
