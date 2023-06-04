package com.example.tugas_akhir.ADDATA_MOBIL;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.tugas_akhir.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class KelengkapanBerkas_PajakActivity extends AppCompatActivity implements View.OnClickListener  {
    private ImageView imgBackToAddData;
    private RadioGroup rgKelengkapanBerkas;
    private RadioButton rbLengkapBPKB_STNK;
    private RadioButton rbAdaSTNK;
    private RadioButton rbAdaBPKB;
    private RadioButton rbTidakAdaBPKB_STNK;

    private RadioGroup rgPajak;
    private RadioButton rbHidupLebihDari5Bulan;
    private RadioButton rbHidupKurangDari3Bulan;
    private RadioButton rbMati;

    private Button btnSubmit;

    private int nilaiKelengkapanBerkas;
    private String desKelengkapanBerkas;

    private FirebaseFirestore firebaseFirestore;

    private String idMobil;

    private int nilaiPajak;
    private String desPajak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelengkapan_berkas__pajak);
        imgBackToAddData = (ImageView) findViewById(R.id.backToAddData);
        rgKelengkapanBerkas = (RadioGroup) findViewById(R.id.rdgKelengkapanBerkas);
        rbLengkapBPKB_STNK = (RadioButton) findViewById(R.id.rbLengkapSTNKBPKB);
        rbAdaBPKB = (RadioButton) findViewById(R.id.rbHanyaBPKB);
        rbAdaSTNK = (RadioButton) findViewById(R.id.rbHanyaSTNK);
        rbTidakAdaBPKB_STNK = (RadioButton) findViewById(R.id.rbTidakAdaSTNKdanBPKB);
        btnSubmit = (Button) findViewById(R.id.btnSubmitKelengkapanBerkas);

        rgPajak = (RadioGroup) findViewById(R.id.rdgPajakMobil);
        rbHidupLebihDari5Bulan = (RadioButton) findViewById(R.id.rbPajakLebih5Bulan);
        rbHidupKurangDari3Bulan = (RadioButton) findViewById(R.id.rbPajakKurang3Bulan);
        rbMati = (RadioButton) findViewById(R.id.rbPajakMati);

        imgBackToAddData.setOnClickListener(this);


        rgKelengkapanBerkas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbLengkapSTNKBPKB:
                        nilaiKelengkapanBerkas = 4;
                        desKelengkapanBerkas = "Lengkap STNK dan BPKB";
                        break;
                    case R.id.rbHanyaBPKB:
                        nilaiKelengkapanBerkas = 3;
                        desKelengkapanBerkas = "Hanya BPKB";
                        break;
                    case R.id.rbHanyaSTNK:
                        nilaiKelengkapanBerkas = 2;
                        desKelengkapanBerkas = "Hanya STNK";
                        break;
                    case R.id.rbTidakAdaSTNKdanBPKB:
                        nilaiKelengkapanBerkas = 1;
                        desKelengkapanBerkas = "Tidak ada STNK dan BPKB";
                        break;
                }
            }
        });
        rgPajak.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbPajakLebih5Bulan:
                        nilaiPajak = 3;
                        desPajak = "Pajak Hidup lebih dari 5 Bulan";
                        break;
                    case R.id.rbPajakKurang3Bulan:
                        nilaiPajak = 2;
                        desPajak = "Pajak Hidup kurang dari 3 Bulan";
                        break;
                    case R.id.rbPajakMati:
                        nilaiPajak = 1;
                        desPajak = "Pajak Mati / Balik Nama";
                        break;
                }
            }
        });
        btnSubmit.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backToAddData:
                finish();
                break;
            case R.id.btnSubmitKelengkapanBerkas:
                Intent i = new Intent();
                i.putExtra("deskripsi_pajak", desPajak);
                i.putExtra("nilai_pajak", nilaiPajak);
                i.putExtra("deskripsi_kelengkapan_berkas", desKelengkapanBerkas);
                i.putExtra("nilai_kelengkapan_berkas", nilaiKelengkapanBerkas);
                setResult(Activity.RESULT_OK, i);
                finish();
                break;
        }
    }
}