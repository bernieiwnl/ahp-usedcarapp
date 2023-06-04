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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DaftarMobilAdapter extends RecyclerView.Adapter<DaftarMobilAdapter.MyViewHolder> {

    private ArrayList<Mobil> mobils;
    private ArrayList<String> foto;
    private Context context;
    private ImageSliderAdapter imageSliderAdapter;

    public DaftarMobilAdapter(ArrayList<Mobil> list, Context c){
        this.mobils = list;
        this.context = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DaftarMobilAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_custom_layout_daftar_mobil, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final int p = position;
        holder.listFoto.startAutoCycle();
        holder.listFoto.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        foto = new ArrayList<>();
        foto.clear();
        for(int i = 0; i < mobils.get(p).getFotoMobil().size(); i++){
            foto.add(mobils.get(p).getFotoMobil().get(i));
        }
        imageSliderAdapter = new ImageSliderAdapter(context, foto);
        holder.listFoto.setSliderAdapter(imageSliderAdapter);
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String yourFormattedString = formatter.format(Integer.parseInt(mobils.get(p).getJarakTempuh()));
        holder.listJudul.setText(mobils.get(p).getMerk() + " " + mobils.get(p).getModel() + " Type " + mobils.get(p).getTipe() + " " + mobils.get(p).getKapasitas_mesin() + " (" + mobils.get(p).getTahun() + ")");
        holder.listDeskripsi.setText(yourFormattedString + " Km, Transmisi : " + mobils.get(p).getTransmisi());
        holder.btn_pilihAlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("alt", (Serializable) mobils.get(p));
                ((Activity)context).setResult(Activity.RESULT_OK, i);
                ((Activity)context).finish();
            }
        });
        holder.btn_lihatAlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mobils.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        private SliderView listFoto;
        private TextView listJudul,listDeskripsi;
        private Button btn_pilihAlt,btn_lihatAlt;

        public MyViewHolder (View view){
            super(view);
            listFoto = (SliderView) view.findViewById(R.id.imgSliderMobil);
            listJudul = (TextView) view.findViewById(R.id.txtJudulMobil);
            listDeskripsi = (TextView) view.findViewById(R.id.txtSubDeskripsiMobil);
            btn_pilihAlt = (Button) view.findViewById(R.id.btnPilihAlt);
            btn_lihatAlt = (Button) view.findViewById(R.id.btnLihatAlt);
        }
    }
}
