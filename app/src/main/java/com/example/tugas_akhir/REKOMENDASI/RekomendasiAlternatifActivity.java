package com.example.tugas_akhir.REKOMENDASI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.tugas_akhir.ADAPTER.DaftarRekomendasiMobilAdapter;
import com.example.tugas_akhir.CLASS.Ahp;
import com.example.tugas_akhir.CLASS.Firestore;
import com.example.tugas_akhir.CLASS.Kriteria;
import com.example.tugas_akhir.CLASS.Mobil;
import com.example.tugas_akhir.CLASS.PrefKriteria;
import com.example.tugas_akhir.CLASS.Preferensi;
import com.example.tugas_akhir.CLASS.Rekomendasi;
import com.example.tugas_akhir.CLASS.Roc;
import com.example.tugas_akhir.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RekomendasiAlternatifActivity extends AppCompatActivity {

    private ArrayList<Mobil> arrayList_mobil;
    private ArrayList<PrefKriteria> arrayList_kriteria;
    private ArrayList<Rekomendasi> arrayList_rekomendasi;
    private Ahp ahp;
    private Roc roc;
    private Rekomendasi rekomendasi;
    private DaftarRekomendasiMobilAdapter daftarRekomendasiMobilAdapter;
    private RecyclerView recyclerView_rekomendasi;
    private double[] arrayHarga;
    private double[] arrayKapasitasMesin;
    private double[] arrayTahun;
    private double[] arrayTransmisi;
    private double[] arrayServis;
    private double[] arrayBerkas;
    private double[] arrayPajak;
    private double[] arrayKelistrikan;
    private double[] arrayPembakaran;
    private double[] arrayRadiator;
    private double[] arrayBody;
    private double[] arrayCat;
    private double[] arrayBan;
    private double[] arrayKtransmisi;
    private double[] arrayShock;
    private double[] arrayPowerSteering;
    private double[] arrayRackSteering;
    private double[] arrayTerod;
    private double[] arrayBaljoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekomendasi_alternatif);
        Bundle bundleObject = getIntent().getExtras();
        arrayList_kriteria = (ArrayList<PrefKriteria>) bundleObject.getSerializable("kriteria");
        arrayList_mobil = (ArrayList<Mobil>) bundleObject.getSerializable("alternatif");
        arrayList_rekomendasi = new ArrayList<>();
        ahp = new Ahp();
        roc = new Roc();
        arrayHarga = new double[arrayList_mobil.size()];
        arrayKapasitasMesin = new double[arrayList_mobil.size()];
        arrayTahun = new double[arrayList_mobil.size()];
        arrayTransmisi = new double[arrayList_mobil.size()];
        arrayServis = new double[arrayList_mobil.size()];
        arrayBerkas = new double[arrayList_mobil.size()];
        arrayPajak = new double[arrayList_mobil.size()];
        arrayKelistrikan = new double[arrayList_mobil.size()];
        arrayPembakaran = new double[arrayList_mobil.size()];
        arrayRadiator = new double[arrayList_mobil.size()];
        arrayBody = new double[arrayList_mobil.size()];
        arrayCat = new double[arrayList_mobil.size()];
        arrayBan = new double[arrayList_mobil.size()];
        arrayKtransmisi = new double[arrayList_mobil.size()];
        arrayShock = new double[arrayList_mobil.size()];
        arrayPowerSteering = new double[arrayList_mobil.size()];
        arrayRackSteering = new double[arrayList_mobil.size()];
        arrayTerod = new double[arrayList_mobil.size()];
        arrayBaljoin = new double[arrayList_mobil.size()];
        recyclerView_rekomendasi = (RecyclerView) findViewById(R.id.recycler_rekomendasi_mobil);
        recyclerView_rekomendasi.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_rekomendasi.setNestedScrollingEnabled(false);

        int index = 0;
        for (Mobil alternatif : arrayList_mobil) {
            for (PrefKriteria kriteria : arrayList_kriteria) {
                switch (kriteria.getNama_kriteria()) {
                    case "Kapasitas Mesin":
                        double kapasitasMesin = ahp.nilaiKapasitasMesin(Integer.parseInt(alternatif.getKapasitas_mesin()));
                        arrayKapasitasMesin[index] = kapasitasMesin;
                        break;
                    case "Harga":
                        double harga = ahp.nilaiHarga(Integer.parseInt(alternatif.getHarga()));
                        arrayHarga[index] = harga;
                        break;
                    case "Tahun":
                        double tahun = ahp.nilaiTahun(Integer.parseInt(alternatif.getTahun()));
                        arrayTahun[index] = tahun;
                        break;
                    case "Transmisi":
                        double transmisi = roc.nilaiTrasmisi(alternatif.getTransmisi());
                        arrayTransmisi[index] = transmisi;
                        break;
                    case "Servis":
                        double servis = roc.nilaiService(alternatif.getServiceRecord());
                        arrayServis[index] = servis;
                        break;
                    case "Kelengkapan Dokumen dan Pajak":
                        double berkas = ahp.nilaiKelengkapanBerkas(alternatif.getKelengkapanBerkas());
                        double pajak = ahp.nilaiPajak(alternatif.getPajak());
                        arrayBerkas[index] = berkas;
                        arrayPajak[index] = pajak;
                        break;
                    case "Kondisi Mesin":
                        double kelistrikan = ahp.nilaiKondisiMobil(alternatif.getKondisiKelistrikan(), "Kelistrikan");
                        double pembakaran = ahp.nilaiKondisiMobil(alternatif.getKondisiPembakaran(), "Pembakaran");
                        double radiator = ahp.nilaiKondisiMobil(alternatif.getKondisiRadiator(), "Radiator");
                        arrayKelistrikan[index] = kelistrikan;
                        arrayPembakaran[index] = pembakaran;
                        arrayRadiator[index] = radiator;
                        break;
                    case "Kondisi Fisik":
                        double body = ahp.nilaiKondisiMobil(alternatif.getKondisiBody(), "Body");
                        double cat = ahp.nilaiKondisiMobil(alternatif.getKondisiCat(), "Cat");
                        double ban = ahp.nilaiKondisiMobil(alternatif.getKondisiBan(), "Ban");
                        arrayBody[index] = body;
                        arrayCat[index] = cat;
                        arrayBan[index] = ban;
                        break;
                    case "Kondisi Understeel":
                        double kTransmisi = ahp.nilaiKondisiMobil(alternatif.getKondisiTransmisi(), "Transmisi");
                        double shock = ahp.nilaiKondisiMobil(alternatif.getKondisiShockbreaker(), "Shock");
                        double powerSteering = ahp.nilaiKondisiMobil(alternatif.getKondisiPowersteering(), "PowerSteering");
                        double rackSteering = ahp.nilaiKondisiMobil(alternatif.getKondisiRacksteer(), "RackSteering");
                        double terod = ahp.nilaiKondisiMobil(alternatif.getKondisiTerod(), "Terod");
                        double baljoin = ahp.nilaiKondisiMobil(alternatif.getKondisiBaljoin(), "Baljoin");
                        arrayKtransmisi[index] = kTransmisi;
                        arrayShock[index] = shock;
                        arrayPowerSteering[index] = powerSteering;
                        arrayRackSteering[index] = rackSteering;
                        arrayTerod[index] = terod;
                        arrayBaljoin[index] = baljoin;
                        break;
                }
            }
            index++;
        }
        //check fungsi ahp untuk tiap kriteria yang dipilih oleh user
        for (PrefKriteria kriteria : arrayList_kriteria) {
            switch (kriteria.getNama_kriteria()) {
                case "Kapasitas Mesin":
                    arrayKapasitasMesin = ahp.metodeAHP(arrayKapasitasMesin);
                    break;
                case "Harga":
                    arrayHarga = ahp.metodeAHP(arrayHarga);
                    break;
                case "Tahun":
                    arrayTahun = ahp.metodeAHP(arrayTahun);
                    break;
                case "Kondisi Understeel":
                    arrayKtransmisi = ahp.metodeAHP(arrayKtransmisi);
                    arrayShock = ahp.metodeAHP(arrayShock);
                    arrayPowerSteering = ahp.metodeAHP(arrayPowerSteering);
                    arrayRackSteering = ahp.metodeAHP(arrayRackSteering);
                    arrayTerod = ahp.metodeAHP(arrayTerod);
                    arrayBaljoin = ahp.metodeAHP(arrayBaljoin);
                    break;
                case "Kondisi Mesin":
                    arrayKelistrikan = ahp.metodeAHP(arrayKelistrikan);
                    arrayPembakaran = ahp.metodeAHP(arrayPembakaran);
                    arrayRadiator = ahp.metodeAHP(arrayRadiator);
                    break;
                case "Kelengkapan Dokumen dan Pajak":
                    arrayBerkas = ahp.metodeAHP(arrayBerkas);
                    arrayPajak = ahp.metodeAHP(arrayPajak);
                    break;
                case "Kondisi Fisik":
                    arrayBody = ahp.metodeAHP(arrayBody);
                    arrayCat = ahp.metodeAHP(arrayCat);
                    arrayBan = ahp.metodeAHP(arrayBan);
                    break;

            }
        }
        checkRekomendasiMobil();
        Collections.sort(arrayList_rekomendasi, (s1, s2) ->
                Double.compare(s2.getNilaiBobot(), s1.getNilaiBobot()));
        daftarRekomendasiMobilAdapter = new DaftarRekomendasiMobilAdapter(RekomendasiAlternatifActivity.this, arrayList_rekomendasi);
        recyclerView_rekomendasi.setAdapter(daftarRekomendasiMobilAdapter);
    }

    void checkRekomendasiMobil() {
        for (int i = 0; i < arrayList_mobil.size(); i++) {
            double nilai = 0.0;
            Mobil dataMobil = arrayList_mobil.get(i);
            for (PrefKriteria kriteria : arrayList_kriteria) {
                switch (kriteria.getNama_kriteria()) {
                    case "Kapasitas Mesin":
                        nilai = nilai + (arrayKapasitasMesin[i] * kriteria.getBobot_kriteria());
                        break;
                    case "Harga":
                        nilai = nilai + (arrayHarga[i] * kriteria.getBobot_kriteria());
                        break;
                    case "Tahun":
                        nilai = nilai + (arrayTahun[i] * kriteria.getBobot_kriteria());
                        break;
                    case "Transmisi":
                        nilai = nilai + (arrayTransmisi[i] * kriteria.getBobot_kriteria());
                        break;
                    case "Servis":
                        nilai = nilai + (arrayServis[i] * kriteria.getBobot_kriteria());
                        break;
                    case "Kelengkapan Dokumen dan Pajak":
                        nilai = nilai + (arrayBerkas[i] * kriteria.getBobot_kriteria());
                        nilai = nilai + (arrayPajak[i] * kriteria.getBobot_kriteria());
                        break;
                    case "Kondisi Mesin":
                        nilai = nilai + (arrayKelistrikan[i] * kriteria.getBobot_kriteria());
                        nilai = nilai + (arrayPembakaran[i] * kriteria.getBobot_kriteria());
                        nilai = nilai + (arrayRadiator[i] * kriteria.getBobot_kriteria());
                        break;
                    case "Kondisi Fisik":
                        nilai = nilai + (arrayBan[i] * kriteria.getBobot_kriteria());
                        nilai = nilai + (arrayCat[i] * kriteria.getBobot_kriteria());
                        nilai = nilai + (arrayBody[i] * kriteria.getBobot_kriteria());
                        break;
                    case "Kondisi Understeel":
                        nilai = nilai + (arrayKtransmisi[i] * kriteria.getBobot_kriteria());
                        nilai = nilai + (arrayShock[i] * kriteria.getBobot_kriteria());
                        nilai = nilai + (arrayPowerSteering[i] * kriteria.getBobot_kriteria());
                        nilai = nilai + (arrayRackSteering[i] * kriteria.getBobot_kriteria());
                        nilai = nilai + (arrayTerod[i] * kriteria.getBobot_kriteria());
                        nilai = nilai + (arrayBaljoin[i] * kriteria.getBobot_kriteria());
                        break;
                }
            }
            rekomendasi = new Rekomendasi(dataMobil, nilai);
            arrayList_rekomendasi.add(rekomendasi);
        }
    }


}