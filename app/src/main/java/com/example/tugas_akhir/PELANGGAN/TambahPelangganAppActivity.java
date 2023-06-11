package com.example.tugas_akhir.PELANGGAN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugas_akhir.PELANGGAN.PREFERENSI.ListPreferensiPelangganAppActivity;
import com.example.tugas_akhir.PELANGGAN.PREFERENSI.PreferensiPelangganAppActivity;
import com.example.tugas_akhir.R;
import com.google.android.material.textfield.TextInputEditText;

public class TambahPelangganAppActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageViewBack;
    private Button btnTambahPreferensi;
    private TextInputEditText txtViewNamaPelanggan, txtViewAlamatPelanggan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pelanggan_app);

        //TextView
        txtViewNamaPelanggan = (TextInputEditText) findViewById(R.id.inputNamaUser);
        txtViewAlamatPelanggan = (TextInputEditText) findViewById(R.id.inputAlamatUser);

        //imageView
        imageViewBack = (ImageView) findViewById(R.id.imageView_back);

        //button
        btnTambahPreferensi = (Button) findViewById(R.id.btnTambahPreferensi);

        //setOnClickListener
        imageViewBack.setOnClickListener(this);
        btnTambahPreferensi.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_back: {
                this.finish();
                break;
            }
            case R.id.btnTambahPreferensi: {
                try {
                    String namaPelanggan = txtViewNamaPelanggan.getText().toString();
                    String alamatPelanggan = txtViewAlamatPelanggan.getText().toString();
                    if (TextUtils.isEmpty(namaPelanggan)) {
                        Toast.makeText(getApplicationContext(), "Nama Pelanggan Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (TextUtils.isEmpty(alamatPelanggan)) {
                        Toast.makeText(getApplicationContext(), "Alamat Pelanggan Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Intent i = new Intent(TambahPelangganAppActivity.this, PreferensiPelangganAppActivity.class);
                    i.putExtra("namaPelanggan", namaPelanggan);
                    i.putExtra("alamatPelanggan", alamatPelanggan);
                    startActivityForResult(i, 1);


                } catch (Exception e) {
                    Log.e("ErrorMsg", e.getMessage());
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}