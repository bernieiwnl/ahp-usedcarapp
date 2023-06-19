package com.example.tugas_akhir.MOBIL.MASTER;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tugas_akhir.MOBIL.MASTER.ADAPTER.KeadaanBodyAppAdapter;
import com.example.tugas_akhir.MOBIL.MASTER.ADAPTER.KelengkapanAppAdapter;
import com.example.tugas_akhir.R;

import java.util.ArrayList;
import java.util.Collections;

public class ListKelengkapanAppActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView_kelengkapanMobil;
    private ImageView imageView_back;
    private ArrayList<String> kelengkapanMobil;
    private KelengkapanAppAdapter kelengkapanAppAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kelengkapan_app);

        //Image View
        imageView_back = (ImageView) findViewById(R.id.imageView_back);

        //Recycler View
        recyclerView_kelengkapanMobil = (RecyclerView) findViewById(R.id.recycler_kelengkapanMobil);

        //ArrayList
        kelengkapanMobil = new ArrayList<>();

        try {
            kelengkapanMobil.add("Kunci ganda dan dongkrak tersedia");
            kelengkapanMobil.add("Kunci ganda tidak tersedia dan dongkrak tersedia");
            kelengkapanMobil.add("Kunci ganda tersedia dan dongkrak tidak tersedia");
            kelengkapanMobil.add("Kunci ganda dan dongkrak tidak tersedia");

            Collections.sort(kelengkapanMobil);

            kelengkapanAppAdapter = new KelengkapanAppAdapter(ListKelengkapanAppActivity.this, kelengkapanMobil);
            recyclerView_kelengkapanMobil.setAdapter(kelengkapanAppAdapter);
            recyclerView_kelengkapanMobil.setLayoutManager(new LinearLayoutManager(this));
            recyclerView_kelengkapanMobil.setNestedScrollingEnabled(false);

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
            recyclerView_kelengkapanMobil.addItemDecoration(dividerItemDecoration);

            imageView_back.setOnClickListener(this);

        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(ListKelengkapanAppActivity.this, e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
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
        }
    }
}