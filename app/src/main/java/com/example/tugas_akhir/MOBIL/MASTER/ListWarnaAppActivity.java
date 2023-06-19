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

import com.example.tugas_akhir.MOBIL.MASTER.ADAPTER.KelengkapanAppAdapter;
import com.example.tugas_akhir.MOBIL.MASTER.ADAPTER.WarnaAppAdapter;
import com.example.tugas_akhir.R;

import java.util.ArrayList;
import java.util.Collections;

public class ListWarnaAppActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView_warnaMobil;
    private ImageView imageView_back;
    private ArrayList<String> warnaMobil;
    private WarnaAppAdapter warnaAppAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_warna_app);

        //Image View
        imageView_back = (ImageView) findViewById(R.id.imageView_back);

        //Recycler View
        recyclerView_warnaMobil = (RecyclerView) findViewById(R.id.recycler_warnaMobil);

        //ArrayList
        warnaMobil = new ArrayList<>();

        try {
            warnaMobil.add("Abu - Abu");
            warnaMobil.add("Silver");
            warnaMobil.add("Putih");
            warnaMobil.add("Hitam");
            warnaMobil.add("Merah");
            warnaMobil.add("Kuning");

            Collections.sort(warnaMobil);

            warnaAppAdapter = new WarnaAppAdapter(ListWarnaAppActivity.this, warnaMobil);
            recyclerView_warnaMobil.setAdapter(warnaAppAdapter);
            recyclerView_warnaMobil.setLayoutManager(new LinearLayoutManager(this));
            recyclerView_warnaMobil.setNestedScrollingEnabled(false);

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            recyclerView_warnaMobil.addItemDecoration(dividerItemDecoration);

            imageView_back.setOnClickListener(this);


        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(ListWarnaAppActivity.this, e.getMessage(), Toast.LENGTH_SHORT)
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