package com.example.tugas_akhir.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_akhir.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EditFotoMobilAdapter extends RecyclerView.Adapter<EditFotoMobilAdapter.MyViewHolder> {

    private Context c;
    private ArrayList<String> fotoPackage;

    public EditFotoMobilAdapter(Context c, ArrayList<String> fotoPackage){
        this.c = c;
        this.fotoPackage = fotoPackage;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EditFotoMobilAdapter.MyViewHolder(LayoutInflater.from(c).inflate(R.layout.list_custom_layout_foto_mobil , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Picasso.get().load(fotoPackage.get(position)).into(holder.imgListFotoMobil);
        holder.imgDeleteFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("hapus-foto");
                intent.putExtra("position", fotoPackage.get(position));
                LocalBroadcastManager.getInstance(c).sendBroadcast(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fotoPackage.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgListFotoMobil;
        private ImageView imgDeleteFoto;

        public MyViewHolder (View view){
            super(view);
            imgListFotoMobil = (ImageView) view.findViewById(R.id.imgFotoMobil);
            imgDeleteFoto = (ImageView) view.findViewById(R.id.deletePicture);
        }
    }

}
