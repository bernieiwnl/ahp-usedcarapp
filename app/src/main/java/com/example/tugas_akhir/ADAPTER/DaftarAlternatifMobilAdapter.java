package com.example.tugas_akhir.ADAPTER;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_akhir.CLASS.Mobil;
import com.example.tugas_akhir.R;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class DaftarAlternatifMobilAdapter extends RecyclerView.Adapter<DaftarAlternatifMobilAdapter.MyViewHolder> {
    private ArrayList<Mobil> arrayList_mobil;
    private ArrayList<String> arrayList_foto;
    private Context context;
    private ImageSliderAdapter imageSliderAdapter;

    public DaftarAlternatifMobilAdapter(Context c, ArrayList<Mobil> arrayList_m){
        this.context = c;
        this.arrayList_mobil = arrayList_m;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DaftarAlternatifMobilAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_custom_layout_daftar_alternatif, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final int p = position;
        holder.sliderView_foto.startAutoCycle();
        holder.sliderView_foto.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        arrayList_foto = new ArrayList<>();
        arrayList_foto.clear();
        for(int i = 0; i < arrayList_mobil.get(p).getFotoMobil().size(); i++){
            arrayList_foto.add(arrayList_mobil.get(p).getFotoMobil().get(i));
        }
        imageSliderAdapter = new ImageSliderAdapter(context, arrayList_foto);
        holder.sliderView_foto.setSliderAdapter(imageSliderAdapter);
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String yourFormattedString = formatter.format(Integer.parseInt(arrayList_mobil.get(p).getJarakTempuh()));
        holder.textView_judul.setText(arrayList_mobil.get(p).getMerk() + " " + arrayList_mobil.get(p).getModel() + " Type " + arrayList_mobil.get(p).getTipe() + " " + arrayList_mobil.get(p).getKapasitas_mesin() + " (" + arrayList_mobil.get(p).getTahun() + ")");
        holder.textview_deskripsi.setText(yourFormattedString + " Km, Transmisi : " + arrayList_mobil.get(p).getTransmisi());
    }

    @Override
    public int getItemCount() {
        return arrayList_mobil.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private SliderView sliderView_foto;
        private TextView textView_judul, textview_deskripsi;
        private Button button_lihatAlt, button_hapusAlt;

        public MyViewHolder(View view) {
            super(view);
            sliderView_foto = (SliderView) view.findViewById(R.id.imgSliderMobil);
            textView_judul = (TextView) view.findViewById(R.id.txtJudulMobil);
            textview_deskripsi = (TextView) view.findViewById(R.id.txtSubDeskripsiMobil);
            button_lihatAlt = (Button) view.findViewById(R.id.btnPilihAlt);
            button_hapusAlt = (Button) view.findViewById(R.id.btnHapusAlt);
        }

    }

}
