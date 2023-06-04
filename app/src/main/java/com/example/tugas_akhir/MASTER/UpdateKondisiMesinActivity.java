package com.example.tugas_akhir.MASTER;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.tugas_akhir.CLASS.Mobil;
import com.example.tugas_akhir.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class UpdateKondisiMesinActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgBackToDetailData;

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

    private Button btnUpdate;

    private int nilaiSubKKelistrikan;
    private String desSubKelistrikan;

    private int nilaiSubKPembakaran;
    private String desSubPembakaran;

    private int nilaiSubKRadiator;
    private String desSubRadiator;

    private String idMobil;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kondisi_mesin);

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

        imgBackToDetailData = (ImageView) findViewById(R.id.backToDetailData);
        btnUpdate = (Button) findViewById(R.id.btnUpdateMesin);

        btnUpdate.setOnClickListener(this);
        imgBackToDetailData.setOnClickListener(this);

        idMobil = getIntent().getStringExtra("idMobil");

        firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("mobil").document(idMobil).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                Mobil data = value.toObject(Mobil.class);

                desSubKelistrikan = getIntent().getStringExtra("deskripsi_kelistrikan");
                if(desSubKelistrikan.equals("Sangat Baik")){
                    rbKelistrikanSangatBaik.setChecked(true);
                }
                else if(desSubKelistrikan.equals("Baik")){
                    rbKelistrikanBaik.setChecked(true);
                }
                else if(desSubKelistrikan.equals("Sedang")){
                    rbKelistrikanSedang.setChecked(true);
                }
                else if(desSubKelistrikan.equals("Buruk")){
                    rbKelistrikanBuruk.setChecked(true);
                }
                else if(desSubKelistrikan.equals("Sangat Buruk")){
                    rbKelistrikanSangatBuruk.setChecked(true);
                }

                desSubPembakaran = getIntent().getStringExtra("deskripsi_pembakaran");
                if(desSubPembakaran.equals("Sangat Baik")){
                    rbPembakaranSangatBaik.setChecked(true);
                }
                else if(desSubPembakaran.equals("Baik")){
                    rbPembakaranBaik.setChecked(true);
                }
                else if(desSubPembakaran.equals("Sedang")){
                    rbPembakaranSedang.setChecked(true);
                }
                else if(desSubPembakaran.equals("Buruk")){
                    rbPembakaranBuruk.setChecked(true);
                }
                else if(desSubPembakaran.equals("Sangat Buruk")){
                    rbPembakaranSangatBuruk.setChecked(true);
                }

                desSubRadiator = getIntent().getStringExtra("deskripsi_radiator");
                if(desSubRadiator.equals("Sangat Baik")){
                    rbRadiatorSangatBaik.setChecked(true);
                }
                else if(desSubRadiator.equals("Baik")){
                    rbRadiatorBaik.setChecked(true);
                }
                else if(desSubRadiator.equals("Sedang")){
                    rbRadiatorSedang.setChecked(true);
                }
                else if(desSubRadiator.equals("Buruk")){
                    rbRadiatorBuruk.setChecked(true);
                }
                else if(desSubRadiator.equals("Sangat Buruk")){
                    rbRadiatorSangatBuruk.setChecked(true);
                }
            }
        });



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
            case R.id.backToDetailData:
                finish();
                break;
            case R.id.btnUpdateMesin:
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