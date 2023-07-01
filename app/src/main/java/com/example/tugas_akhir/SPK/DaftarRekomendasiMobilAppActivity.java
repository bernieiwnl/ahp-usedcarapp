package com.example.tugas_akhir.SPK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tugas_akhir.ADAPTER.DaftarRekomendasiMobilAdapter;
import com.example.tugas_akhir.CLASS.PrefKriteria;
import com.example.tugas_akhir.MOBIL.CLASS.NewMobil;
import com.example.tugas_akhir.R;
import com.example.tugas_akhir.REKOMENDASI.RekomendasiAlternatifActivity;
import com.example.tugas_akhir.SPK.ADAPTER.DaftarRekomendasiAppAdapter;
import com.example.tugas_akhir.SPK.CLASS.RekomendasiMobil;
import com.example.tugas_akhir.SPK.CLASS.SpkAhp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class DaftarRekomendasiMobilAppActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageViewBack;
    private RecyclerView recyclerViewRekomendasi;
    private ArrayList<NewMobil> mobils;
    private ArrayList<PrefKriteria> prefKriterias;
    private ArrayList<RekomendasiMobil> rekomendasis;
    private double[] matrixHarga;
    private double[] matrixTransmisi;
    private double[] matrixKilometer;
    private double[] matrixServiceRecord;
    private double[] matrixKondisiMesin;
    private double[] matrixKapasitasMesin;
    private double[] matrixKondisiBody;
    private double[] matrixWarna;
    private double[] matrixKelengkapan;
    private double[] matrixKondisiInterior;
    private double[] matrixTahun;
    private DaftarRekomendasiAppAdapter daftarRekomendasiAppAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_rekomendasi_mobil_app);

        //ImageView
        imageViewBack = (ImageView) findViewById(R.id.imageView_back);

        //RecyclerView
        recyclerViewRekomendasi = (RecyclerView) findViewById(R.id.recycler_rekomendasi);

        //Get Intent
        Bundle bundleObject = getIntent().getExtras();
        prefKriterias = (ArrayList<PrefKriteria>) bundleObject.getSerializable("preferensi");
        mobils = (ArrayList<NewMobil>) bundleObject.getSerializable("alternatif");

        //Set On Click
        imageViewBack.setOnClickListener(this);

        //Inisialisasi Array
        rekomendasis = new ArrayList<>();
        matrixHarga = new double[mobils.size()];
        matrixTransmisi = new double[mobils.size()];
        matrixKilometer = new double[mobils.size()];
        matrixServiceRecord = new double[mobils.size()];
        matrixKondisiMesin = new double[mobils.size()];
        matrixKapasitasMesin = new double[mobils.size()];
        matrixKondisiBody = new double[mobils.size()];
        matrixWarna = new double[mobils.size()];
        matrixKelengkapan = new double[mobils.size()];
        matrixKondisiInterior = new double[mobils.size()];
        matrixTahun = new double[mobils.size()];

        //SPK
        SpkAhp spkAhp = new SpkAhp();
        int index = 0;
        for (NewMobil alternatif : mobils) {
            for (PrefKriteria preferensi : prefKriterias) {
                switch (preferensi.getNama_kriteria()) {
                    case "Harga": {
                        double harga = spkAhp.bobotHarga(alternatif.getHargaMobil());
                        matrixHarga[index] = harga;
                        break;
                    }
                    case "Transmisi": {
                        double transmisi = spkAhp.bobotTransmisiMobil(alternatif.getTransmisiMobil());
                        matrixTransmisi[index] = transmisi;
                        break;
                    }
                    case "Kilometer": {
                        double kilometer = spkAhp.bobotKilometer(alternatif.getKilometerMobil(), alternatif.getTahunMobil());
                        matrixKilometer[index] = kilometer;
                        break;
                    }
                    case "Service Record": {
                        double serviceRecord = spkAhp.bobotServiceRecord(alternatif.getServiceRecordMobil());
                        matrixServiceRecord[index] = serviceRecord;
                        break;
                    }
                    case "Kondisi Mesin": {
                        double kondisiMesin = spkAhp.bobotKondisiMesin(alternatif.getKondisiMesinMobil());
                        matrixKondisiMesin[index] = kondisiMesin;
                        break;
                    }
                    case "Kapasitas Mesin": {
                        double kapasitasMesin = spkAhp.bobotKapasitasMesin(alternatif.getKapasitasMobil());
                        matrixKapasitasMesin[index] = kapasitasMesin;
                        break;
                    }
                    case "Kondisi Body": {
                        double kondisiBody = spkAhp.bobotKondisiMesin(alternatif.getKeadaanMobil());
                        matrixKondisiBody[index] = kondisiBody;
                        break;
                    }
                    case "Warna": {
                        double warna = spkAhp.bobotWarna(alternatif.getWarnaMobil());
                        matrixWarna[index] = warna;
                        break;
                    }
                    case "Kelengkapan": {
                        double kelengkapan = spkAhp.bobotKelengkapan(alternatif.getKelengkapanMobil());
                        matrixKelengkapan[index] = kelengkapan;
                        break;
                    }
                    case "Kondisi Interior": {
                        double kondisiInterior = spkAhp.bobotInterior(alternatif.getKondisiInteriorMobil());
                        matrixKondisiInterior[index] = kondisiInterior;
                        break;
                    }
                    default: {
                        double tahun = spkAhp.bobotTahunPembuatan(alternatif.getTahunMobil());
                        matrixTahun[index] = tahun;
                        break;
                    }
                }
            }
            index++;
        }
        // check fungsi ahp untuk tiap kriteria yang dipilih oleh pelanggan
        for (PrefKriteria preferensi : prefKriterias) {
            switch (preferensi.getNama_kriteria()) {
                case "Harga": {
                    matrixHarga = spkAhp.hitungAHP(matrixHarga);
                    break;
                }
                case "Transmisi": {
                    matrixTransmisi = spkAhp.hitungAHP(matrixTransmisi);
                    break;
                }
                case "Kilometer": {
                    matrixKilometer = spkAhp.hitungAHP(matrixKilometer);
                    break;
                }
                case "Service Record": {
                    matrixServiceRecord = spkAhp.hitungAHP(matrixServiceRecord);
                    break;
                }
                case "Kondisi Mesin": {
                    matrixKondisiMesin = spkAhp.hitungAHP(matrixKondisiMesin);
                    break;
                }
                case "Kapasitas Mesin": {
                    matrixKapasitasMesin = spkAhp.hitungAHP(matrixKapasitasMesin);
                    break;
                }
                case "Kondisi Body": {
                    matrixKondisiBody = spkAhp.hitungAHP(matrixKondisiBody);
                    break;
                }
                case "Warna": {
                    matrixWarna = spkAhp.hitungAHP(matrixWarna);
                    break;
                }
                case "Kelengkapan": {
                    matrixKelengkapan = spkAhp.hitungAHP(matrixKelengkapan);
                    break;
                }
                case "Kondisi Interior": {
                    matrixKondisiInterior = spkAhp.hitungAHP(matrixKondisiInterior);
                    break;
                }
                default: {
                    matrixTahun = spkAhp.hitungAHP(matrixTahun);
                    break;
                }
            }
        }

        checkRekomendasiMobil();
        rekomendasis.sort((s1, s2) ->
                Double.compare(s2.getNilaiBobot(), s1.getNilaiBobot()));

        Log.i("Array", rekomendasis.get(0).getNilaiBobot() + " Total Bobot mobil " + rekomendasis.get(0).getNewMobil().getNamaMerkMobil());
        Log.i("Array", rekomendasis.get(0).getNilaiHarga() + " Bobot Harga mobil " + rekomendasis.get(0).getNewMobil().getNamaMerkMobil());
        Log.i("Array", rekomendasis.get(0).getNilaiKelengkapan() + " Bobot Kelengkapan mobil " + rekomendasis.get(0).getNewMobil().getNamaMerkMobil());
        Log.i("Array", "-------------------------------------------------------------------------------");
        Log.i("Array", rekomendasis.get(1).getNilaiBobot() + " Total Bobot mobil " + rekomendasis.get(1).getNewMobil().getNamaMerkMobil());
        Log.i("Array", rekomendasis.get(1).getNilaiHarga() + " Bobot Harga mobil " + rekomendasis.get(1).getNewMobil().getNamaMerkMobil());
        Log.i("Array", rekomendasis.get(1).getNilaiKelengkapan() + " Bobot Kelengkapan mobil " + rekomendasis.get(1).getNewMobil().getNamaMerkMobil());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DaftarRekomendasiMobilAppActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewRekomendasi.setLayoutManager(linearLayoutManager);

        daftarRekomendasiAppAdapter = new DaftarRekomendasiAppAdapter(DaftarRekomendasiMobilAppActivity.this, rekomendasis);
        recyclerViewRekomendasi.setAdapter(daftarRekomendasiAppAdapter);

    }

    private void checkRekomendasiMobil() {
        int index = 0;
        for (NewMobil alternatif : mobils) {
            double nilai = 0.0;
            double nilaiHarga = 0.0;
            double nilaiTransmisi = 0.0;
            double nilaiKilometer = 0.0;
            double nilaiServiceRecord = 0.0;
            double nilaiKondisiMesin = 0.0;
            double nilaiKapasitasMesin = 0.0;
            double nilaiKondisiBody = 0.0;
            double nilaiWarna = 0.0;
            double nilaiKelengkapan = 0.0;
            double nilaiKondisiInterior = 0.0;
            double nilaiTahun = 0.0;

            NewMobil dataMobil = alternatif;
            for (PrefKriteria preferensi : prefKriterias) {
                switch (preferensi.getNama_kriteria()) {
                    case "Harga": {
                        nilai += (matrixHarga[index] * preferensi.getBobot_kriteria());
                        nilaiHarga = matrixHarga[index];
                        break;
                    }
                    case "Transmisi": {
                        nilai += (matrixTransmisi[index] * preferensi.getBobot_kriteria());
                        nilaiTransmisi = matrixTransmisi[index];
                        break;
                    }
                    case "Kilometer": {
                        nilai += (matrixKilometer[index] * preferensi.getBobot_kriteria());
                        nilaiKilometer = matrixKilometer[index];
                        break;
                    }
                    case "Service Record": {
                        nilai += (matrixServiceRecord[index] * preferensi.getBobot_kriteria());
                        nilaiServiceRecord = matrixServiceRecord[index];
                        break;
                    }
                    case "Kondisi Mesin": {
                        nilai += (matrixKondisiMesin[index] * preferensi.getBobot_kriteria());
                        nilaiKondisiMesin = matrixKondisiMesin[index];
                        break;
                    }
                    case "Kapasitas Mesin": {
                        nilai += (matrixKapasitasMesin[index] * preferensi.getBobot_kriteria());
                        nilaiKapasitasMesin = matrixKapasitasMesin[index];
                        break;
                    }
                    case "Kondisi Body": {
                        nilai += (matrixKondisiBody[index] * preferensi.getBobot_kriteria());
                        nilaiKondisiBody = matrixKondisiBody[index];
                        break;
                    }
                    case "Warna": {
                        nilai += (matrixWarna[index] * preferensi.getBobot_kriteria());
                        nilaiWarna = matrixWarna[index];
                        break;
                    }
                    case "Kelengkapan": {
                        nilai += (matrixKelengkapan[index] * preferensi.getBobot_kriteria());
                        nilaiKelengkapan = matrixKelengkapan[index];
                        break;
                    }
                    case "Kondisi Interior": {
                        nilai += (matrixKondisiInterior[index] * preferensi.getBobot_kriteria());
                        nilaiKondisiInterior = matrixKondisiInterior[index];
                        break;
                    }
                    default: {
                        nilai += (matrixTahun[index] * preferensi.getBobot_kriteria());
                        nilaiTahun = matrixTahun[index];
                        break;
                    }
                }
            }
            index++;
            RekomendasiMobil rekomendasiMobil = new RekomendasiMobil(dataMobil, nilai, nilaiHarga, nilaiTransmisi, nilaiKilometer, nilaiServiceRecord, nilaiKondisiMesin, nilaiKapasitasMesin, nilaiKondisiBody, nilaiWarna, nilaiKelengkapan, nilaiKondisiInterior, nilaiTahun);
            rekomendasis.add(rekomendasiMobil);
        }
    }

    @Override
    public void onClick(View v) {

    }
}