package com.example.tugas_akhir.PELANGGAN.ADAPTER;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_akhir.CLASS.Firestore;
import com.example.tugas_akhir.CLASS.Pelanggan;
import com.example.tugas_akhir.PELANGGAN.PREFERENSI.UbahPreferensiPelangganAppActivity;
import com.example.tugas_akhir.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListUbahPelangganAdapter extends RecyclerView.Adapter<ListUbahPelangganAdapter.MyViewHolder> {
    private ArrayList<Pelanggan> pelanggans;
    private Context context;

    public ListUbahPelangganAdapter(Context context, ArrayList<Pelanggan> pelanggans) {
        this.context = context;
        this.pelanggans = pelanggans;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListUbahPelangganAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_custom_layout_pelanggan, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int p) {
        final int position = p;
        holder.txtNamaPelanggan.setText(pelanggans.get(position).getNamaPelanggan());
        holder.txtAlamatPelanggan.setText(pelanggans.get(position).getAlamatPelanggan());

        holder.btnPilihPelanggan.setOnClickListener(v -> updatePelanggan(pelanggans.get(position).getIdPelanggan()));

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


    public void updatePelanggan(String newIdPelanggan) {
        try {
            Firestore firestore = new Firestore();
            String getCurrentUserId = firestore.getFirebaseAuth().getCurrentUser().getUid();
            DocumentReference userDb = FirebaseFirestore.getInstance().collection("user")
                    .document(getCurrentUserId);

            Map<String, Object> updateUsers = new HashMap<>();
            updateUsers.put("idPelanggan", newIdPelanggan);

            userDb.update(updateUsers).addOnSuccessListener(unused -> {
                Toast.makeText(context, "Pilih Pelanggan Berhasil", Toast.LENGTH_SHORT).show();
                ((Activity) context).finish();
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("ErrorMsg", e.getMessage());
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamaPelanggan;
        TextView txtAlamatPelanggan;
        Button btnPilihPelanggan, btnPreferensi;

        public MyViewHolder(View view) {
            super(view);

            txtNamaPelanggan = (TextView) view.findViewById(R.id.namaPelanggan);
            txtAlamatPelanggan = (TextView) view.findViewById(R.id.alamatPelanggan);
            btnPilihPelanggan = (Button) view.findViewById(R.id.btnPilihPelanggan);
            btnPreferensi = (Button) view.findViewById(R.id.btnPreferensi);

        }
    }

}
