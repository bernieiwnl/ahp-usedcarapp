package com.example.tugas_akhir.PELANGGAN.PREFERENSI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tugas_akhir.ADAPTER.KriteriaMobilAdapter;
import com.example.tugas_akhir.CLASS.Firestore;
import com.example.tugas_akhir.CLASS.PrefKriteria;
import com.example.tugas_akhir.PELANGGAN.TambahPelangganAppActivity;
import com.example.tugas_akhir.R;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class PreferensiPelangganAppActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView_back, imageView_tambahPreferensi;
    private RecyclerView recyclerView_preferensiPelanggan;
    private KriteriaMobilAdapter preferensiMobilAdapter;
    private ArrayList<String> preferensis;
    private FloatingActionButton btnSimpanPelanggan;
    private String getCurrentPelangganID;
    private String namaPelanggan;
    private String alamatPelanggan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferensi_pelanggan_app);

        try {
            //Image View
            imageView_back = (ImageView) findViewById(R.id.imageView_back);
            imageView_tambahPreferensi = (ImageView) findViewById(R.id.imageView_tambahPreferensi);

            //Recyler View
            recyclerView_preferensiPelanggan = (RecyclerView) findViewById(R.id.recycler_preferensiPelanggan);

            //float action button
            btnSimpanPelanggan = (FloatingActionButton) findViewById(R.id.btnSimpanPelanggan);

            //arrayList
            preferensis = new ArrayList<>();

            //get data from last activity
            namaPelanggan = (String) getIntent().getStringExtra("namaPelanggan");
            alamatPelanggan = (String) getIntent().getStringExtra("alamatPelanggan");

            //Set Onclick Listener
            imageView_back.setOnClickListener(this);
            imageView_tambahPreferensi.setOnClickListener(this);
            btnSimpanPelanggan.setOnClickListener(this);

            //Set Layout recylerview
            recyclerView_preferensiPelanggan.setLayoutManager(new LinearLayoutManager(this));
            recyclerView_preferensiPelanggan.setNestedScrollingEnabled(false);

            //swipe up and down / left or right
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemCallBack);
            itemTouchHelper.attachToRecyclerView(recyclerView_preferensiPelanggan);

        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String dataKriteria = (String) data.getSerializableExtra("preferensi");
            preferensis.add(dataKriteria);
            preferensiMobilAdapter = new KriteriaMobilAdapter(PreferensiPelangganAppActivity.this, preferensis);
            recyclerView_preferensiPelanggan.setAdapter(preferensiMobilAdapter);
        }

    }

    ItemTouchHelper.SimpleCallback itemCallBack = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();

            Collections.swap(preferensis, fromPosition, toPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            preferensis.remove(viewHolder.getAdapterPosition());
            recyclerView_preferensiPelanggan.getAdapter().notifyDataSetChanged();
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_back: {
                this.finish();
                break;
            }
            case R.id.imageView_tambahPreferensi: {
                try {
                    Intent i = new Intent(PreferensiPelangganAppActivity.this, ListPreferensiPelangganAppActivity.class);
                    i.putExtra("dataPreferensis", preferensis);
                    startActivityForResult(i, 1);
                } catch (Exception e) {
                    Log.e("ErrorMsg", e.getMessage());
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.btnSimpanPelanggan: {
                try {
                    if (preferensis.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Preferensi Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    getCurrentPelangganID = tambahPelanggan(namaPelanggan, alamatPelanggan);
                    updateIdPelanggan(getCurrentPelangganID);
                    hitungBobotKriteria(getCurrentPelangganID, preferensis);

                } catch (Exception e) {
                    Log.e("ErrorMsg", e.getMessage());
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                ;
                break;
            }
        }
    }

    private void updateIdPelanggan(String idPelanggan) {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionRef = firebaseFirestore.collection("pelanggan");
        DocumentReference pelangganDb = collectionRef.document(idPelanggan);

        pelangganDb.update("idPelanggan", idPelanggan).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getApplicationContext(), "Update id Sukses", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Update id Gagal", Toast.LENGTH_SHORT).show();
            }

        }).addOnFailureListener(e -> {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        });

    }

    private String tambahPelanggan(String namaPelanggan, String alamatPelanggan) {
        try {
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            CollectionReference collectionRef = firebaseFirestore.collection("pelanggan");
            DocumentReference pelangganDb = collectionRef.document();

            final String idPelanggan = pelangganDb.getId();

            Map<String, Object> pelanggan = new HashMap<>();
            pelanggan.put("idPelanggan", "");
            pelanggan.put("namaPelanggan", namaPelanggan);
            pelanggan.put("alamatPelanggan", alamatPelanggan);

            pelangganDb.set(pelanggan).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Tambah Pelanggan Sukses", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Tambah Pelanggan Gagal", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener((OnFailureListener) error -> {
                Log.e("ErrorMsg", error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            });
            return idPelanggan;
        } catch (Exception e) {
            return null;
        }
    }

    private void hitungBobotKriteria(String pelangganId, ArrayList<String> preferensis) {
        //ambil preferensi yang sudah diurutkan
        ArrayList<PrefKriteria> dataPreferensi = new ArrayList<>();

        for (int i = 0; i < preferensis.size(); i++) {
            double bobot = 0.0;
            double totalBobot = 0.0;
            // ambil bobot preferensi yang sudah diurutkan
            for (int j = i + 1; j <= preferensis.size(); j++) {
                bobot = bobot + (1.0 / j); //menggunakan rumus rank order centroid
            }
            totalBobot = bobot / preferensis.size();
            String namaKriteria = preferensis.get(i);

            dataPreferensi.add(new PrefKriteria(namaKriteria, totalBobot));
        }

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionRef = firebaseFirestore.collection("preferensi");
        DocumentReference preferensiDb = collectionRef.document(pelangganId);

        Map<String, Object> preferensi = new HashMap<>();
        preferensi.put("idPelanggan", pelangganId);
        preferensi.put("preferensi_kriterias", dataPreferensi);

        preferensiDb.set(preferensi).addOnCompleteListener(task -> {
            try {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Tambah Peferensi Sukses", Toast.LENGTH_SHORT).show();
                    TambahPelangganAppActivity.getInstance().finish();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Tambah Peferensi Gagal", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception error) {
                Log.e("ErrorMsg", error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        });

    }
}