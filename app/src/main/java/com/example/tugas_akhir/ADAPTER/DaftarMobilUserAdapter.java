package com.example.tugas_akhir.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_akhir.CLASS.Mobil;
import com.example.tugas_akhir.MASTER.UpdateDaftarMobilActivity;
import com.example.tugas_akhir.R;
import com.google.android.material.button.MaterialButton;
import com.smarteist.autoimageslider.SliderView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DaftarMobilUserAdapter  extends RecyclerView.Adapter<DaftarMobilUserAdapter.MyViewHolder> {

    private ArrayList<Mobil> mobils;
    private ArrayList<String> foto;
    private Context context;
    private ImageSliderAdapter imageSliderAdapter;

    public DaftarMobilUserAdapter(ArrayList<Mobil> list, Context c){
        this.mobils = list;
        this.context = c;
    }

    @NonNull
    @Override
    public DaftarMobilUserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DaftarMobilUserAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_custom_layout_daftar_user_mobil, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarMobilUserAdapter.MyViewHolder holder, int position) {
        final int data = position;
        holder.listFoto.setAutoCycle(false);
        foto = new ArrayList<>();
        foto.clear();
        for(int i = 0; i < mobils.get(position).getFotoMobil().size(); i++){
            foto.add(mobils.get(position).getFotoMobil().get(i));
        }
        imageSliderAdapter = new ImageSliderAdapter(context, foto);
        holder.listFoto.setSliderAdapter(imageSliderAdapter);
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String yourFormattedString = formatter.format(Integer.parseInt(mobils.get(position).getJarakTempuh()));
        holder.listJudul.setText(mobils.get(position).getMerk() + " " + mobils.get(position).getModel() + " Type " + mobils.get(position).getTipe() + " " + mobils.get(position).getKapasitas_mesin() + " (" + mobils.get(position).getTahun() + ")");
        holder.listDeskripsi.setText(yourFormattedString + " Km, Transmisi : " + mobils.get(position).getTransmisi());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, UpdateDaftarMobilActivity.class);
                i.putExtra("id", mobils.get(data).getIdMobil());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mobils.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private SliderView listFoto;
        private TextView listJudul,listHarga,listDeskripsi;
        private ImageView btnEdit;
        public MyViewHolder (View view){
            super(view);
            listFoto = (SliderView) view.findViewById(R.id.imgSliderMobil);
            listJudul = (TextView) view.findViewById(R.id.txtJudulMobil);
            listDeskripsi = (TextView) view.findViewById(R.id.txtSubDeskripsiMobil);
        }

    }
}
