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
import com.example.tugas_akhir.R;

import java.util.ArrayList;
import java.util.Collections;

public class ListKeadaanBodyAppActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView_keadaanBody;
    private ImageView imageView_back;
    private ArrayList<String> keadaanMobil;
    private KeadaanBodyAppAdapter keadaanBodyAppAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_keadaan_body_app);

        //Image view
        imageView_back = (ImageView) findViewById(R.id.imageView_back);

        //Recycler view
        recyclerView_keadaanBody = (RecyclerView) findViewById(R.id.recycler_keadaanMobil);

        //ArrayList
        keadaanMobil = new ArrayList<>();

        try {
            keadaanMobil.add("Lecet halus");
            keadaanMobil.add("Lecet kasar");
            keadaanMobil.add("Lekuk kecil");
            keadaanMobil.add("Lecet halus dan lekuk kecil");
            keadaanMobil.add("Lecet kasar dan lekuk kecil");
            keadaanMobil.add("Pernah mengalami kerusakan");
            keadaanMobil.add("Tidak pernah mengalami kerusakan (kecelakaan)");

            Collections.sort(keadaanMobil);

            keadaanBodyAppAdapter = new KeadaanBodyAppAdapter(ListKeadaanBodyAppActivity.this, keadaanMobil);
            recyclerView_keadaanBody.setAdapter(keadaanBodyAppAdapter);
            recyclerView_keadaanBody.setLayoutManager(new LinearLayoutManager(this));
            recyclerView_keadaanBody.setNestedScrollingEnabled(false);

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
            recyclerView_keadaanBody.addItemDecoration(dividerItemDecoration);

            imageView_back.setOnClickListener(this);

        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
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