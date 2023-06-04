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

public class KondisiFisikActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgBackToAddData;

    private RadioGroup rgBody;
    private RadioButton rbBodySangatBaik;
    private RadioButton rbBodyBaik;
    private RadioButton rbBodySedang;
    private RadioButton rbBodyBuruk;
    private RadioButton rbBodySangatBuruk;

    private RadioGroup rgCat;
    private RadioButton rbCatSangatBaik;
    private RadioButton rbCatBaik;
    private RadioButton rbCatSedang;
    private RadioButton rbCatBuruk;
    private RadioButton rbCatSangatBuruk;

    private RadioGroup rgBan;
    private RadioButton rbBanSangatBaik;
    private RadioButton rbBanBaik;
    private RadioButton rbBanSedang;
    private RadioButton rbBanBuruk;
    private RadioButton rbBanSangatBuruk;

    private Button btnSubmit;

    private int nilaiSubKBody;
    private String ketSubBody;

    private int nilaiSubKCat;
    private String ketSubCat;

    private int nilaiSubKBan;
    private String ketSubBan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kondisi_fisik);
        rgBody = (RadioGroup) findViewById(R.id.rdgBody);
        rbBodySangatBaik = (RadioButton) findViewById(R.id.rbKondisiBody5);
        rbBodyBaik = (RadioButton) findViewById(R.id.rbKondisiBody4);
        rbBodySedang = (RadioButton) findViewById(R.id.rbKondisiBody3);
        rbBodyBuruk = (RadioButton) findViewById(R.id.rbKondisiBody2);
        rbBodySangatBuruk = (RadioButton) findViewById(R.id.rbKondisiBody1);

        rgCat = (RadioGroup) findViewById(R.id.rdgCat);
        rbCatSangatBaik = (RadioButton) findViewById(R.id.rbKondisiCat5);
        rbCatBaik = (RadioButton) findViewById(R.id.rbKondisiCat4);
        rbCatSedang = (RadioButton) findViewById(R.id.rbKondisiCat3);
        rbCatBuruk = (RadioButton) findViewById(R.id.rbKondisiCat2);
        rbCatSangatBuruk = (RadioButton) findViewById(R.id.rbKondisiCat1);

        rgBan = (RadioGroup) findViewById(R.id.rdgBan);
        rbBanSangatBaik = (RadioButton) findViewById(R.id.rbKondisiBan5);
        rbBanBaik = (RadioButton) findViewById(R.id.rbKondisiBan4);
        rbBanSedang = (RadioButton) findViewById(R.id.rbKondisiBan3);
        rbBanBuruk = (RadioButton) findViewById(R.id.rbKondisiBan2);
        rbBanSangatBuruk = (RadioButton) findViewById(R.id.rbKondisiBan1);


        imgBackToAddData = (ImageView) findViewById(R.id.backToAddData);
        btnSubmit = (Button) findViewById(R.id.btnSubmitFisik);
        btnSubmit.setOnClickListener(this);
        imgBackToAddData.setOnClickListener(this);

        rgBody.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rbKondisiBody5:
                        nilaiSubKBody = 5;
                        ketSubBody = "Sangat Baik";
                        break;
                    case R.id.rbKondisiBody4:
                        nilaiSubKBody = 4;
                        ketSubBody = "Baik";
                        break;
                    case R.id.rbKondisiBody3:
                        nilaiSubKBody = 3;
                        ketSubBody = "Sedang";
                        break;
                    case R.id.rbKondisiBody2:
                        nilaiSubKBody = 2;
                        ketSubBody = "Buruk";
                        break;
                    case R.id.rbKondisiBody1:
                        nilaiSubKBody = 1;
                        ketSubBody = "Sangat Buruk";
                        break;
                }
            }
        });

        rgCat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rbKondisiCat5:
                        nilaiSubKCat = 5;
                        ketSubCat = "Sangat Baik";
                        break;
                    case R.id.rbKondisiCat4:
                        nilaiSubKCat = 4;
                        ketSubCat = "Baik";
                        break;
                    case R.id.rbKondisiCat3:
                        nilaiSubKCat = 3;
                        ketSubCat = "Sedang";
                        break;
                    case R.id.rbKondisiCat2:
                        nilaiSubKCat = 2;
                        ketSubCat = "Buruk";
                        break;
                    case R.id.rbKondisiCat1:
                        nilaiSubKCat = 1;
                        ketSubCat  = "Sangat Buruk";
                        break;
                }
            }
        });

        rgBan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rbKondisiBan5:
                        nilaiSubKBan = 5;
                        ketSubBan = "Sangat Baik";
                        break;
                    case R.id.rbKondisiBan4:
                        nilaiSubKBan = 4;
                        ketSubBan = "Baik";
                        break;
                    case R.id.rbKondisiBan3:
                        nilaiSubKBan = 3;
                        ketSubBan = "Sedang";
                        break;
                    case R.id.rbKondisiBan2:
                        nilaiSubKBan = 2;
                        ketSubBan = "Buruk";
                        break;
                    case R.id.rbKondisiBan1:
                        nilaiSubKBan = 1;
                        ketSubBan = "Sangat Buruk";
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
            case R.id.btnSubmitFisik:
                Intent i = new Intent();
                i.putExtra("deskripsi_body", ketSubBan);
                i.putExtra("nilai_body", nilaiSubKBan);
                i.putExtra("deskripsi_cat", ketSubCat);
                i.putExtra("nilai_cat", nilaiSubKCat);
                i.putExtra("deskripsi_ban", ketSubBan);
                i.putExtra("nilai_ban", nilaiSubKBan);
                setResult(Activity.RESULT_OK, i);
                finish();
                break;
        }
    }
}