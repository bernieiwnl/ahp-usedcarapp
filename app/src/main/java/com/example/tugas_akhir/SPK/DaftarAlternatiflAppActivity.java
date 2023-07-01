package com.example.tugas_akhir.SPK;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.tugas_akhir.MOBIL.CLASS.NewMobil;
import com.example.tugas_akhir.R;
import com.example.tugas_akhir.REKOMENDASI.DaftarAlternatifActivity;
import com.example.tugas_akhir.REKOMENDASI.PemilihanAlternatifActivity;
import com.example.tugas_akhir.SPK.ADAPTER.DaftarAlternatifAppAdapter;

import java.util.ArrayList;

public class DaftarAlternatiflAppActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView imageViewBack, imageViewTambahAlternatif;
    private RecyclerView recyclerViewAlternatif;
    private ArrayList<NewMobil> mobils;
    private DaftarAlternatifAppAdapter daftarAlternatifAppAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_alternatif_app);

        //Image View
        imageViewBack = (ImageView) findViewById(R.id.imageView_back);
        imageViewTambahAlternatif = (ImageView) findViewById(R.id.imageView_tambahAlternatif);

        //RecyClerView
        recyclerViewAlternatif = (RecyclerView) findViewById(R.id.recycler_alternatif);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DaftarAlternatiflAppActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewAlternatif.setLayoutManager(linearLayoutManager);


        //ArrayList
        mobils = new ArrayList<>();

        //setOnClickListener
        imageViewBack.setOnClickListener(this);
        imageViewTambahAlternatif.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            NewMobil alternatif = (NewMobil) data.getSerializableExtra("alt");
            mobils.add(alternatif);
            daftarAlternatifAppAdapter = new DaftarAlternatifAppAdapter(DaftarAlternatiflAppActivity.this, mobils);
            recyclerViewAlternatif.setAdapter(daftarAlternatifAppAdapter);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_back: {
                try {
                    finish();
                } catch (Exception e) {
                    Log.e("ErrorMsg", e.getMessage());
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.imageView_tambahAlternatif: {
                try {
                    Intent intent = new Intent(DaftarAlternatiflAppActivity.this, DaftarPilihanAlternatifAppActivity.class);
                    intent.putExtra("alternatif", mobils);
                    startActivityForResult(intent, 1);
                } catch (Exception e) {
                    Log.e("ErrorMsg", e.getMessage());
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}