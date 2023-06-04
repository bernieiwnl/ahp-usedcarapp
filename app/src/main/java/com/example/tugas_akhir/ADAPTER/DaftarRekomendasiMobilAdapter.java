package com.example.tugas_akhir.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_akhir.CLASS.Rekomendasi;
import com.example.tugas_akhir.R;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class DaftarRekomendasiMobilAdapter extends RecyclerView.Adapter<DaftarRekomendasiMobilAdapter.MyViewHolder> {

    private ArrayList<Rekomendasi> arrayList_rekomendasi;
    private ArrayList<String> arrayList_foto;
    private Context context;
    private ImageSliderAdapter imageSliderAdapter;

    public DaftarRekomendasiMobilAdapter(Context c, ArrayList<Rekomendasi> array){
        this.context = c;
        this.arrayList_rekomendasi = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DaftarRekomendasiMobilAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_custom_layout_daftar_rekomendasi, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final int p = position;
        holder.sliderView_foto.startAutoCycle();
        holder.sliderView_foto.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        arrayList_foto = new ArrayList<>();
        arrayList_foto.clear();
        for(int i = 0; i < arrayList_rekomendasi.get(p).getMobil().getFotoMobil().size(); i++){
            arrayList_foto.add(arrayList_rekomendasi.get(p).getMobil().getFotoMobil().get(i));
        }
        imageSliderAdapter = new ImageSliderAdapter(context, arrayList_foto);
        holder.sliderView_foto.setSliderAdapter(imageSliderAdapter);
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String yourFormattedString = formatter.format(Integer.parseInt(arrayList_rekomendasi.get(p).getMobil().getJarakTempuh()));
        holder.textView_judul.setText(arrayList_rekomendasi.get(p).getMobil().getMerk() + " " + arrayList_rekomendasi.get(p).getMobil().getModel() + " Type " + arrayList_rekomendasi.get(p).getMobil().getTipe() + " " + arrayList_rekomendasi.get(p).getMobil().getKapasitas_mesin() + " (" + arrayList_rekomendasi.get(p).getMobil().getTahun() + ")");
        holder.textview_deskripsi.setText(yourFormattedString + " Km, Transmisi : " + arrayList_rekomendasi.get(p).getMobil().getTransmisi());
        holder.textView_rate.setText(arrayList_rekomendasi.get(p).getNilaiBobot() + "");
    }

    @Override
    public int getItemCount() {
        return arrayList_rekomendasi.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private SliderView sliderView_foto;
        private TextView textView_judul, textview_deskripsi, textView_rate;

        public MyViewHolder(View view){
            super(view);
            sliderView_foto = (SliderView) view.findViewById(R.id.imgSliderMobil);
            textView_judul = (TextView) view.findViewById(R.id.txtJudulMobil);
            textview_deskripsi = (TextView) view.findViewById(R.id.txtSubDeskripsiMobil);
            textView_rate = (TextView) view.findViewById(R.id.textView_rate);
        }
    }
}
