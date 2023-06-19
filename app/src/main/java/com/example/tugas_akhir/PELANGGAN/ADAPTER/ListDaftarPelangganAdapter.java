package com.example.tugas_akhir.PELANGGAN.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_akhir.CLASS.Pelanggan;
import com.example.tugas_akhir.PELANGGAN.PREFERENSI.UbahPreferensiPelangganAppActivity;
import com.example.tugas_akhir.PELANGGAN.UbahPelangganAppActivity;
import com.example.tugas_akhir.R;

import java.util.ArrayList;

public class ListDaftarPelangganAdapter extends RecyclerView.Adapter<ListDaftarPelangganAdapter.MyViewHolder> {

    private ArrayList<Pelanggan> pelanggans;
    private Context context;

    public ListDaftarPelangganAdapter(Context context, ArrayList<Pelanggan> pelanggans) {
        this.context = context;
        this.pelanggans = pelanggans;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListDaftarPelangganAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_custom_layout_ubah_pelanggan, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtNamaPelanggan.setText(pelanggans.get(position).getNamaPelanggan());
        holder.txtAlamatPelanggan.setText(pelanggans.get(position).getAlamatPelanggan());

        holder.btnUbahPelanggan.setOnClickListener(v -> {
            Intent i = new Intent(context, UbahPelangganAppActivity.class);
            i.putExtra("idPelanggan", pelanggans.get(position).getIdPelanggan());
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.getApplicationContext().startActivity(i);
        });

        holder.btnPreferensi.setOnClickListener(v -> {
            Intent i = new Intent(context, UbahPreferensiPelangganAppActivity.class);
            i.putExtra("idPelanggan", pelanggans.get(position).getIdPelanggan());
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.getApplicationContext().startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return pelanggans.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamaPelanggan;
        TextView txtAlamatPelanggan;
        Button btnUbahPelanggan, btnPreferensi;

        public MyViewHolder(View view) {
            super(view);

            txtNamaPelanggan = (TextView) view.findViewById(R.id.namaPelanggan);
            txtAlamatPelanggan = (TextView) view.findViewById(R.id.alamatPelanggan);
            btnUbahPelanggan = (Button) view.findViewById(R.id.btnUbahPelanggan);
            btnPreferensi = (Button) view.findViewById(R.id.btnPreferensi);

        }
    }


}
