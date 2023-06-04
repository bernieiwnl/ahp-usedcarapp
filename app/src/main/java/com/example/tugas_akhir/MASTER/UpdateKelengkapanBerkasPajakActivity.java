package com.example.tugas_akhir.MASTER;

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

public class UpdateKelengkapanBerkasPajakActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imgBackToDetailData;
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

    private int nilaiPajak;
    private String desPajak;

    private FirebaseFirestore firebaseFirestore;
    private String idMobil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kelengkapan_berkas_pajak);

        imgBackToDetailData = (ImageView) findViewById(R.id.backToDetailData);
        rgKelengkapanBerkas = (RadioGroup) findViewById(R.id.rdgKelengkapanBerkas);
        rbLengkapBPKB_STNK = (RadioButton) findViewById(R.id.rbLengkapSTNKBPKB);
        rbAdaBPKB = (RadioButton) findViewById(R.id.rbHanyaBPKB);
        rbAdaSTNK = (RadioButton) findViewById(R.id.rbHanyaSTNK);
        rbTidakAdaBPKB_STNK = (RadioButton) findViewById(R.id.rbTidakAdaSTNKdanBPKB);
        btnSubmit = (Button) findViewById(R.id.btnUpdateKelengkapanBerkas);

        rgPajak = (RadioGroup) findViewById(R.id.rdgPajakMobil);
        rbHidupLebihDari5Bulan = (RadioButton) findViewById(R.id.rbPajakLebih5Bulan);
        rbHidupKurangDari3Bulan = (RadioButton) findViewById(R.id.rbPajakKurang3Bulan);
        rbMati = (RadioButton) findViewById(R.id.rbPajakMati);

        imgBackToDetailData.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        firebaseFirestore = FirebaseFirestore.getInstance();

        idMobil = getIntent().getStringExtra("idMobil");


        desKelengkapanBerkas = getIntent().getStringExtra("deskripsi_kelengkapan_berkas");

        if(desKelengkapanBerkas.equals("Lengkap STNK dan BPKB"))
        {
            rbLengkapBPKB_STNK.setChecked(true);
        }
        else if(desKelengkapanBerkas.equals("Hanya BPKB"))
        {
            rbAdaBPKB.setChecked(true);
        }
        else if(desKelengkapanBerkas.equals("Hanya STNK"))
        {
            rbAdaSTNK.setChecked(true);
        }
        else if (desKelengkapanBerkas.equals("Tidak ada STNK dan BPKB"))
        {
            rbTidakAdaBPKB_STNK.setChecked(true);
        }

        desPajak = getIntent().getStringExtra("deskripsi_pajak");

        if(desPajak.equals("Pajak Hidup lebih dari 5 Bulan")){
            rbHidupLebihDari5Bulan.setChecked(true);
        }
        else if(desPajak.equals("Pajak Hidup kurang dari 3 Bulan")){
            rbHidupKurangDari3Bulan.setChecked(true);
        }
        else if(desPajak.equals("Pajak Mati / Balik Nama")){
            rbMati.setChecked(true);
        }

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backToDetailData:
                finish();
                break;
            case R.id.btnUpdateKelengkapanBerkas:
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