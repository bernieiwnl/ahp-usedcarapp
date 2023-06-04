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

public class KondisiMesinActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgBackToAddData;

    private RadioGroup rgKelistrikan;
    private RadioButton rbKelistrikanSangatBaik;
    private RadioButton rbKelistrikanBaik;
    private RadioButton rbKelistrikanSedang;
    private RadioButton rbKelistrikanBuruk;
    private RadioButton rbKelistrikanSangatBuruk;

    private RadioGroup rgPembakaran;
    private RadioButton rbPembakaranSangatBaik;
    private RadioButton rbPembakaranBaik;
    private RadioButton rbPembakaranSedang;
    private RadioButton rbPembakaranBuruk;
    private RadioButton rbPembakaranSangatBuruk;

    private RadioGroup rgRadiator;
    private RadioButton rbRadiatorSangatBaik;
    private RadioButton rbRadiatorBaik;
    private RadioButton rbRadiatorSedang;
    private RadioButton rbRadiatorBuruk;
    private RadioButton rbRadiatorSangatBuruk;

    private Button btnSubmit;

    private int nilaiSubKKelistrikan;
    private String desSubKelistrikan;

    private int nilaiSubKPembakaran;
    private String desSubPembakaran;

    private int nilaiSubKRadiator;
    private String desSubRadiator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kondisi_mesin);
        rgKelistrikan = (RadioGroup) findViewById(R.id.rdgKelistrikan);
        rbKelistrikanSangatBaik = (RadioButton) findViewById(R.id.rbKondisiKelistrikan5);
        rbKelistrikanBaik = (RadioButton) findViewById(R.id.rbKondisiKelistrikan4);
        rbKelistrikanSedang = (RadioButton) findViewById(R.id.rbKondisiKelistrikan3);
        rbKelistrikanBuruk = (RadioButton) findViewById(R.id.rbKondisiKelistrikan2);
        rbKelistrikanSangatBuruk = (RadioButton) findViewById(R.id.rbKondisiKelistrikan1);

        rgPembakaran = (RadioGroup) findViewById(R.id.rdgPembakaran);
        rbPembakaranSangatBaik = (RadioButton) findViewById(R.id.rbKondisiPembakaran5);
        rbPembakaranBaik = (RadioButton) findViewById(R.id.rbKondisiPembakaran4);
        rbPembakaranSedang = (RadioButton) findViewById(R.id.rbKondisiPembakaran3);
        rbPembakaranBuruk = (RadioButton) findViewById(R.id.rbKondisiPembakaran2);
        rbPembakaranSangatBuruk = (RadioButton) findViewById(R.id.rbKondisiPembakaran1);

        rgRadiator = (RadioGroup) findViewById(R.id.rdgRadiator);
        rbRadiatorSangatBaik = (RadioButton) findViewById(R.id.rbKondisiRadiator5);
        rbRadiatorBaik = (RadioButton) findViewById(R.id.rbKondisiRadiator4);
        rbRadiatorSedang = (RadioButton) findViewById(R.id.rbKondisiRadiator3);
        rbRadiatorBuruk = (RadioButton) findViewById(R.id.rbKondisiRadiator2);
        rbRadiatorSangatBuruk = (RadioButton) findViewById(R.id.rbKondisiRadiator1);

        imgBackToAddData = (ImageView) findViewById(R.id.backToAddData);
        btnSubmit = (Button) findViewById(R.id.btnSubmitMesin);

        btnSubmit.setOnClickListener(this);
        imgBackToAddData.setOnClickListener(this);

        rgKelistrikan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbKondisiKelistrikan5:
                        nilaiSubKKelistrikan = 5;
                        desSubKelistrikan = "Sangat Baik";
                        break;
                    case R.id.rbKondisiKelistrikan4:
                        nilaiSubKKelistrikan = 4;
                        desSubKelistrikan = "Baik";
                        break;
                    case R.id.rbKondisiKelistrikan3:
                        nilaiSubKKelistrikan = 3;
                        desSubKelistrikan = "Sedang";
                        break;
                    case R.id.rbKondisiKelistrikan2:
                        nilaiSubKKelistrikan = 2;
                        desSubKelistrikan = "Buruk";
                        break;
                    case R.id.rbKondisiKelistrikan1:
                        nilaiSubKKelistrikan = 1;
                        desSubKelistrikan = "Sangat Buruk";
                        break;
                }
            }
        });
        rgPembakaran.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbKondisiPembakaran5:
                        nilaiSubKPembakaran = 5;
                        desSubPembakaran = "Sangat Baik";
                        break;
                    case R.id.rbKondisiPembakaran4:
                        nilaiSubKPembakaran = 4;
                        desSubPembakaran = "Baik";
                        break;
                    case R.id.rbKondisiPembakaran3:
                        nilaiSubKPembakaran = 3;
                        desSubPembakaran = "Sedang";
                        break;
                    case R.id.rbKondisiPembakaran2:
                        nilaiSubKPembakaran = 2;
                        desSubPembakaran = "Buruk";
                        break;
                    case R.id.rbKondisiPembakaran1:
                        nilaiSubKPembakaran = 1;
                        desSubPembakaran = "Sangat Buruk";
                        break;
                }
            }
        });

        rgRadiator.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.rbKondisiRadiator5:
                        nilaiSubKRadiator = 5;
                        desSubRadiator = "Sangat Baik";
                        break;
                    case R.id.rbKondisiRadiator4:
                        nilaiSubKRadiator = 4;
                        desSubRadiator = "Baik";
                        break;
                    case R.id.rbKondisiRadiator3:
                        nilaiSubKRadiator = 3;
                        desSubRadiator = "Sedang";
                        break;
                    case R.id.rbKondisiRadiator2:
                        nilaiSubKRadiator = 2;
                        desSubRadiator = "Buruk";
                        break;
                    case R.id.rbKondisiRadiator1:
                        nilaiSubKRadiator = 1;
                        desSubRadiator = "Sangat Buruk";
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backToAddData:
                finish();
                break;
            case R.id.btnSubmitMesin:
                Intent i = new Intent();
                i.putExtra("deskripsi_kelistrikan", desSubKelistrikan);
                i.putExtra("nilai_kelistrikan", nilaiSubKKelistrikan);
                i.putExtra("deskripsi_pembakaran", desSubPembakaran);
                i.putExtra("nilai_pembakaran", nilaiSubKPembakaran);
                i.putExtra("deskripsi_radiator", desSubRadiator);
                i.putExtra("nilai_radiator", nilaiSubKRadiator);
                setResult(Activity.RESULT_OK, i);
                finish();
                break;
        }
    }
}