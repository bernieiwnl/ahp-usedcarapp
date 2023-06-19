package com.example.tugas_akhir.PELANGGAN;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
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

import com.example.tugas_akhir.CLASS.Firestore;
import com.example.tugas_akhir.CLASS.Pelanggan;
import com.example.tugas_akhir.PELANGGAN.ADAPTER.ListDaftarPelangganAdapter;
import com.example.tugas_akhir.PELANGGAN.ADAPTER.ListUbahPelangganAdapter;
import com.example.tugas_akhir.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class ListDaftarPelangganAppActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference pelangganDb;
    private Firestore firestore;

    private RecyclerView recyclerViewPelanggan;
    private ImageView imgViewKembali, imgViewTambahPelanggan;
    private EditText editTextPencarian;

    private ArrayList<Pelanggan> pelanggans;
    private ListDaftarPelangganAdapter listDaftarPelangganAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_daftar_pelanggan_app);

        //imageView
        imgViewTambahPelanggan = (ImageView) findViewById(R.id.imageView_tambahPelanggan);
        imgViewKembali = (ImageView) findViewById(R.id.imageView_back);

        //ReclyerView
        recyclerViewPelanggan = (RecyclerView) findViewById(R.id.recycler_pelanggan);
        recyclerViewPelanggan.setLayoutManager(new LinearLayoutManager(this));

        //EditTextView
        editTextPencarian = (EditText) findViewById(R.id.editText_pencarian);

        // new Array List
        pelanggans = new ArrayList<>();

        //fetch data
        fetchData();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerViewPelanggan.addItemDecoration(dividerItemDecoration);

        //setOnclick
        imgViewTambahPelanggan.setOnClickListener(this);
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
            case R.id.imageView_tambahPelanggan: {
                try {
                    startActivity(new Intent(ListDaftarPelangganAppActivity.this, TambahPelangganAppActivity.class));
                } catch (Exception e) {
                    Log.e("ErrorMsg", e.getMessage());
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            }
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

    public void fetchData() {
        try {
            firebaseFirestore = FirebaseFirestore.getInstance();
            firestore = new Firestore();
            pelangganDb = firebaseFirestore.collection("pelanggan");

            pelangganDb.addSnapshotListener((queryDocumentSnapshots, error) -> {
                pelanggans.clear();
                if (queryDocumentSnapshots.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Tidak ada data pelanggan", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Pelanggan dataPelanggan = documentSnapshot.toObject(Pelanggan.class);
                    pelanggans.add(dataPelanggan);
                }
                listDaftarPelangganAdapter = new ListDaftarPelangganAdapter(ListDaftarPelangganAppActivity.this, pelanggans);
                recyclerViewPelanggan.setAdapter(listDaftarPelangganAdapter);
            });
        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void filterData(String search) {
        try {
            ArrayList<Pelanggan> pelanggansAfterSearch = new ArrayList<>();
            pelanggansAfterSearch.clear();

            for (Pelanggan pelanggan : pelanggans
            ) {
                if (pelanggan.getNamaPelanggan().toLowerCase().contains(search.toLowerCase())) {
                    pelanggansAfterSearch.add(pelanggan);
                }

                listDaftarPelangganAdapter = new ListDaftarPelangganAdapter(ListDaftarPelangganAppActivity.this, pelanggansAfterSearch);
                recyclerViewPelanggan.setAdapter(listDaftarPelangganAdapter);
            }
        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}