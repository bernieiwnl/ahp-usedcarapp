package com.example.tugas_akhir.PREFERENSI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tugas_akhir.ADAPTER.KriteriaMobilAdapter;
import com.example.tugas_akhir.CLASS.PrefKriteria;
import com.example.tugas_akhir.R;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;

public class KriteriaActivity extends AppCompatActivity {
    private RecyclerView listKriteriaMobil;
    private KriteriaMobilAdapter kriteriaMobilAdapter;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private ArrayList<String> kriterias;
    private ArrayList<PrefKriteria> preferensi_kriterias;
    private FloatingActionButton btnSimpan;
    private ImageView imgTambahKriteria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kriteria);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#ffd95a"));
        }

        listKriteriaMobil = findViewById(R.id.listKriteriaMobil);
        imgTambahKriteria = findViewById(R.id.imgTambahKriteria);
        btnSimpan = findViewById(R.id.btnSimpan);
        kriterias = new ArrayList<>();

        listKriteriaMobil.setLayoutManager(new LinearLayoutManager(this));
        listKriteriaMobil.setNestedScrollingEnabled(false);


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(listKriteriaMobil);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanPrefensiKriteria();
            }
        });
        imgTambahKriteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(KriteriaActivity.this, ListKriteriaActivity.class);
                i.putExtra("data", kriterias);
                startActivityForResult(i,1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK) {
            String dataKriteria = (String) data.getSerializableExtra("kriteria");
            kriterias.add(dataKriteria);
            kriteriaMobilAdapter = new KriteriaMobilAdapter(KriteriaActivity.this, kriterias);
            listKriteriaMobil.setAdapter(kriteriaMobilAdapter);
        }
    }
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN , ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();   

            Collections.swap(kriterias, fromPosition, toPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            kriterias.remove(viewHolder.getAdapterPosition());
            listKriteriaMobil.getAdapter().notifyDataSetChanged();
        }

    };

    private void simpanPrefensiKriteria(){
        hitungBobotKriteria();

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        firebaseFirestore.collection("preferensi").document(firebaseAuth.getCurrentUser().getUid()).update("preferensi_kriterias", preferensi_kriterias).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void hitungBobotKriteria(){
        // buat tampungan untuk subkriteria
        preferensi_kriterias = new ArrayList<>();
        //ambil subkriteria yang sudah diurutkan
        for(int i = 0; i < kriterias.size(); i++){
            double bobot = 0.0;
            double totalBobot = 0.0;
            // ambil bobot subkriteria yang sudah diurutkan
            for(int j = i + 1 ; j <= kriterias.size(); j++)
            {
                bobot = bobot + (1.0/ j); //menggunakan rumus rank order centroid
            }
            totalBobot = bobot / kriterias.size();
            String namaKriteria = kriterias.get(i);
            preferensi_kriterias.add(new PrefKriteria(namaKriteria,totalBobot));
        }
    }
}