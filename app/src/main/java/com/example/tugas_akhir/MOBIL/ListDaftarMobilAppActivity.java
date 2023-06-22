package com.example.tugas_akhir.MOBIL;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tugas_akhir.CLASS.Mobil;
import com.example.tugas_akhir.CLASS.Pelanggan;
import com.example.tugas_akhir.MOBIL.ADAPTER.DaftarMobilAppAdapter;
import com.example.tugas_akhir.MOBIL.CLASS.NewMobil;
import com.example.tugas_akhir.PELANGGAN.ADAPTER.ListUbahPelangganAdapter;
import com.example.tugas_akhir.PELANGGAN.ListPelangganAppActivity;
import com.example.tugas_akhir.PELANGGAN.TambahPelangganAppActivity;
import com.example.tugas_akhir.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListDaftarMobilAppActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private RecyclerView recyclerViewMobil;
    private ImageView imgViewKembali, imgViewTambahMobil;
    private EditText editTextPencarian;
    private ArrayList<NewMobil> mobils;
    private DaftarMobilAppAdapter daftarMobilAppAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_daftar_mobil_app);

        //imageView
        imgViewTambahMobil = (ImageView) findViewById(R.id.imageView_tambahMobil);
        imgViewKembali = (ImageView) findViewById(R.id.imageView_back);

        //recyclerView
        recyclerViewMobil = (RecyclerView) findViewById(R.id.recycler_mobil);
        recyclerViewMobil.setLayoutManager(new LinearLayoutManager(this));

        //EditTextView
        editTextPencarian = (EditText) findViewById(R.id.editText_pencarian);

        //ArrayList
        mobils = new ArrayList<>();

        //
        fetchData();

        //onclick listener
        imgViewTambahMobil.setOnClickListener(this);
        imgViewKembali.setOnClickListener(this);
        editTextPencarian.addTextChangedListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_back: {
                this.finish();
                break;
            }
            case R.id.imageView_tambahMobil: {
                try {
                    startActivity(new Intent(ListDaftarMobilAppActivity.this, TambahMobilAppActivity.class));
                } catch (Exception e) {
                    Log.e("ErrorMsg", e.getMessage());
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private void fetchData() {
        try {
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            CollectionReference mobilCollections = firebaseFirestore.collection("mobil");
            mobilCollections.addSnapshotListener((documentSnapshots, error) -> {
                mobils.clear();
                if (documentSnapshots.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Tidak ada data mobil", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (QueryDocumentSnapshot data : documentSnapshots) {
                    NewMobil dataMobil = data.toObject(NewMobil.class);
                    mobils.add(dataMobil);
                }

                daftarMobilAppAdapter = new DaftarMobilAppAdapter(ListDaftarMobilAppActivity.this, mobils);
                recyclerViewMobil.setAdapter(daftarMobilAppAdapter);
            });
        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        filterData(s.toString());
    }

    private void filterData(String search) {
        try {
            ArrayList<NewMobil> mobilsAfterSearch = new ArrayList<>();
            mobilsAfterSearch.clear();

            for (NewMobil mobil : mobils
            ) {
                if (mobil.getNamaMerkMobil().toLowerCase().contains(search.toLowerCase())) {
                    mobilsAfterSearch.add(mobil);
                }

                daftarMobilAppAdapter = new DaftarMobilAppAdapter(ListDaftarMobilAppActivity.this, mobilsAfterSearch);
                recyclerViewMobil.setAdapter(daftarMobilAppAdapter);
            }
        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}