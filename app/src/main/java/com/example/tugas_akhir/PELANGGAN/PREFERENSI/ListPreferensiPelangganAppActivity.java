package com.example.tugas_akhir.PELANGGAN.PREFERENSI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tugas_akhir.ADAPTER.DaftarKriteriaAdapter;
import com.example.tugas_akhir.R;

import java.util.ArrayList;
import java.util.Collections;

public class ListPreferensiPelangganAppActivity extends AppCompatActivity {

    private RecyclerView recyclerView_preferensi;
    private ImageView imageView_back;
    private ArrayList<String> preferensis;
    private DaftarKriteriaAdapter daftarPreferensiAdapter;
    private ArrayList<String> preferensiPelanggan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_preferensi_pelanggan_app);

        //Image View
        imageView_back = (ImageView) findViewById(R.id.imageView_back);

        //Recyler view
        recyclerView_preferensi = (RecyclerView) findViewById(R.id.recycler_preferensi);

        //ArrayList
        preferensis = new ArrayList<>();


        try {
            //Data last Activity
            preferensiPelanggan = (ArrayList<String>) getIntent().getSerializableExtra("dataPreferensis");
            Log.e("ErrorMsg", preferensiPelanggan.size() + "");
            preferensis.add("Harga");
            preferensis.add("Transmisi");
            preferensis.add("Kilometer");
            preferensis.add("Service Record");
            preferensis.add("Kondisi Mesin");
            preferensis.add("Tipe Mobil");
            preferensis.add("Kapasitas Mesin");
            preferensis.add("Kondisi Body");
            preferensis.add("Warna");
            preferensis.add("Kelengkapan");
            preferensis.add("Kondisi Interior");
            preferensis.add("Tahun Pembuatan");

            Collections.sort(preferensis);

            if (!preferensiPelanggan.isEmpty()) {
                for (String preferensiPel : preferensiPelanggan) {
                    preferensis.remove(preferensiPel);
                }
            }


            daftarPreferensiAdapter = new DaftarKriteriaAdapter(ListPreferensiPelangganAppActivity.this, preferensis);
            recyclerView_preferensi.setAdapter(daftarPreferensiAdapter);
            recyclerView_preferensi.setLayoutManager(new LinearLayoutManager(this));
            recyclerView_preferensi.setNestedScrollingEnabled(false);

        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}