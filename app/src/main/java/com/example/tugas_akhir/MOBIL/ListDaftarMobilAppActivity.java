package com.example.tugas_akhir.MOBIL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tugas_akhir.PELANGGAN.ListPelangganAppActivity;
import com.example.tugas_akhir.PELANGGAN.TambahPelangganAppActivity;
import com.example.tugas_akhir.R;

public class ListDaftarMobilAppActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerViewMobil;
    private ImageView imgViewKembali, imgViewTambahMobil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_daftar_mobil_app);

        //imageView
        imgViewTambahMobil = (ImageView) findViewById(R.id.imageView_tambahMobil);
        imgViewKembali = (ImageView) findViewById(R.id.imageView_back);

        //onclick listener
        imgViewTambahMobil.setOnClickListener(this);
        imgViewKembali.setOnClickListener(this);

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
}