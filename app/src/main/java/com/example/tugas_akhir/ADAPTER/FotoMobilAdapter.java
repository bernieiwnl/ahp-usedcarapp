package com.example.tugas_akhir.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_akhir.R;

import java.util.List;
import java.util.concurrent.FutureTask;

public class FotoMobilAdapter extends RecyclerView.Adapter<FotoMobilAdapter.MyViewHolder> {

    private List<Bitmap> listFotoMobil;
    private Context activityFotoLayout;

    public FotoMobilAdapter(List<Bitmap> list, Context c){
        this.listFotoMobil = list;
        this.activityFotoLayout = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FotoMobilAdapter.MyViewHolder(LayoutInflater.from(activityFotoLayout).inflate(R.layout.list_custom_layout_foto_mobil, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Bitmap bitmap = listFotoMobil.get(position);
        //render bitmap ke image view
        holder.imgListFotoMobil.setImageBitmap(bitmap);
        holder.imgDeleteFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("hapus-foto");
                intent.putExtra("position", position);
                LocalBroadcastManager.getInstance(activityFotoLayout).sendBroadcast(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFotoMobil.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgListFotoMobil;
        private ImageView imgDeleteFoto;

        public MyViewHolder (View view){
            super(view);
            imgListFotoMobil = (ImageView) view.findViewById(R.id.imgFotoMobil);
            imgDeleteFoto = (ImageView) view.findViewById(R.id.deletePicture);
        }
    }
}
