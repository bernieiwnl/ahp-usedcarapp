package com.example.tugas_akhir.REKOMENDASI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tugas_akhir.ADAPTER.DaftarAlternatifMobilAdapter;
import com.example.tugas_akhir.CLASS.Firestore;
import com.example.tugas_akhir.CLASS.Mobil;
import com.example.tugas_akhir.CLASS.PrefKriteria;
import com.example.tugas_akhir.CLASS.Preferensi;
import com.example.tugas_akhir.CLASS.Route;
import com.example.tugas_akhir.R;
import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

public class PemilihanAlternatifActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView_tambahAlternatif, imageView_back;
    private FloatingActionButton floatingActionButton_simpanData;
    private RecyclerView recyclerView_alternatif;
    private ArrayList<Mobil> arrayList_mobil;
    private ArrayList<PrefKriteria> arrayList_kriteria;
    private DaftarAlternatifMobilAdapter daftarAlternatifMobilAdapter;
    private ItemTouchHelper itemTouchHelper;
    private Route route;
    private Firestore firestore;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemilihan_alternatif);
        arrayList_mobil = new ArrayList<>();
        arrayList_kriteria = new ArrayList<>();
        route = new Route();
        firestore = new Firestore();
        imageView_tambahAlternatif = (ImageView) findViewById(R.id.imageView_tambahAlternatif);
        recyclerView_alternatif = (RecyclerView) findViewById(R.id.recycler_alternatif_mobil);
        floatingActionButton_simpanData = (FloatingActionButton) findViewById(R.id.floating_simpanAlternatif);
        imageView_back = (ImageView) findViewById(R.id.imageView_back);
        recyclerView_alternatif.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_alternatif.setNestedScrollingEnabled(false);
        itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView_alternatif);
        imageView_tambahAlternatif.setOnClickListener(this);
        imageView_back.setOnClickListener(this);
        floatingActionButton_simpanData.setOnClickListener(this);
        userId = firestore.getFirebaseAuth().getUid();
        firestore.getFirebaseFirestore().collection("preferensi").document(userId).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                Preferensi data = value.toObject(Preferensi.class);
                arrayList_kriteria = data.getPreferensi_kriterias();
            }
        });

    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            arrayList_mobil.remove(viewHolder.getAdapterPosition());
            recyclerView_alternatif.getAdapter().notifyDataSetChanged();
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_tambahAlternatif:
                Intent intentA = new Intent(PemilihanAlternatifActivity.this, DaftarAlternatifActivity.class);
                intentA.putExtra("alternatif", arrayList_mobil);
                startActivityForResult(intentA, 1);
                break;
            case R.id.imageView_back:
                this.finish();
                break;
            case R.id.floating_simpanAlternatif:
                Intent intentB = new Intent(PemilihanAlternatifActivity.this, RekomendasiAlternatifActivity.class);
                intentB.putExtra("alternatif", arrayList_mobil);
                intentB.putExtra("kriteria", arrayList_kriteria);
                startActivity(intentB);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            Mobil alternatif = (Mobil) data.getSerializableExtra("alt");
            arrayList_mobil.add(alternatif);
            daftarAlternatifMobilAdapter = new DaftarAlternatifMobilAdapter(PemilihanAlternatifActivity.this, arrayList_mobil);
            recyclerView_alternatif.setAdapter(daftarAlternatifMobilAdapter);
        }
    }
}