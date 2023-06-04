package com.example.tugas_akhir.PREFERENSI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tugas_akhir.ADAPTER.DaftarKriteriaAdapter;
import com.example.tugas_akhir.R;

import java.util.ArrayList;

public class ListKriteriaActivity extends AppCompatActivity {
    private RecyclerView listKriteriaMobil;
    private ArrayList<String> kriterias;
    private DaftarKriteriaAdapter daftarKriteriaAdapter;
    private ArrayList<String> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kriteria);

        listKriteriaMobil = findViewById(R.id.listKriteriaMobil);
        kriterias = new ArrayList<>();
        data = (ArrayList<String>) getIntent().getSerializableExtra("data");

        kriterias.add("Kapasitas Mesin");
        kriterias.add("Harga");
        kriterias.add("Tahun");
        kriterias.add("Transmisi");
        kriterias.add("Kondisi Understeel");
        kriterias.add("Kondisi Mesin");
        kriterias.add("Kelengkapan Dokumen dan Pajak");
        kriterias.add("Servis");
        kriterias.add("Kondisi Fisik");



        for(String kriteria : data){
            if(kriterias.contains(kriteria)){
                kriterias.remove(kriteria);
            }
        }
        daftarKriteriaAdapter = new DaftarKriteriaAdapter(ListKriteriaActivity.this, kriterias);
        listKriteriaMobil.setAdapter(daftarKriteriaAdapter);
        listKriteriaMobil.setLayoutManager(new LinearLayoutManager(this));
        listKriteriaMobil.setNestedScrollingEnabled(false);


    }
}