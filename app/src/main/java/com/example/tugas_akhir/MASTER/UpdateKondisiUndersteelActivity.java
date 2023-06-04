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

public class UpdateKondisiUndersteelActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imgBackToDetailData;

    private RadioGroup rgTransmisi;
    private RadioButton rbTransmisiSangatBaik;
    private RadioButton rbTransmisiBaik;
    private RadioButton rbTransmisiSedang;
    private RadioButton rbTransmisiBuruk;
    private RadioButton rbTransmisiSangatBuruk;

    private RadioGroup rgShockbreaker;
    private RadioButton rbShockbreakerSangatBaik;
    private RadioButton rbShockbreakerBaik;
    private RadioButton rbShockbreakerSedang;
    private RadioButton rbShockbreakerBuruk;
    private RadioButton rbShockbreakerSangatBuruk;

    private RadioGroup rgPowerSteering;
    private RadioButton rbPowerSteeringSangatBaik;
    private RadioButton rbPowerSteeringBaik;
    private RadioButton rbPowerSteeringSedang;
    private RadioButton rbPowerSteeringBuruk;
    private RadioButton rbPowerSteeringSangatBuruk;

    private RadioGroup rgRackSteer;
    private RadioButton rbRackSteerSangatBaik;
    private RadioButton rbRackSteerBaik;
    private RadioButton rbRackSteerSedang;
    private RadioButton rbRackSteerBuruk;
    private RadioButton rbRackSteerSangatBuruk;

    private RadioGroup rgTerod;
    private RadioButton rbTerodSangatBaik;
    private RadioButton rbTerodBaik;
    private RadioButton rbTerodSedang;
    private RadioButton rbTerodBuruk;
    private RadioButton rbTerodSangatBuruk;

    private RadioGroup rgBaljoin;
    private RadioButton rbBaljoinSangatBaik;
    private RadioButton rbBaljoinBaik;
    private RadioButton rbBaljoinSedang;
    private RadioButton rbBaljoinBuruk;
    private RadioButton rbBaljoingSangatBuruk;

    private Button btnUpdate;

    private int nilaiTransmisi;
    private String desTransmisi;
    private int nilaiShockbreaker;
    private String desShockbreaker;
    private int nilaiPowerSteering;
    private String desPowerSteering;
    private int nilaiTerod;
    private String desTerod;
    private int nilaiRacksteer;
    private String desRacksteer;
    private int nilaiBaljoin;
    private String desBaljoin;

    private String idMobil;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kondisi_understeel);
        rgTransmisi = (RadioGroup) findViewById(R.id.rdgTransmisi);
        rbTransmisiSangatBaik = (RadioButton) findViewById(R.id.rbKondisiTransmisi5);
        rbTransmisiBaik = (RadioButton) findViewById(R.id.rbKondisiTransmisi4);
        rbTransmisiSedang = (RadioButton) findViewById(R.id.rbKondisiTransmisi3);
        rbTransmisiBuruk = (RadioButton) findViewById(R.id.rbKondisiTransmisi2);
        rbTransmisiSangatBuruk = (RadioButton) findViewById(R.id.rbKondisiTransmisi1);

        rgShockbreaker = (RadioGroup) findViewById(R.id.rdgShockbreaker);
        rbShockbreakerSangatBaik = (RadioButton) findViewById(R.id.rbKondisiShockbreaker5);
        rbShockbreakerBaik = (RadioButton) findViewById(R.id.rbKondisiShockbreaker4);
        rbShockbreakerSedang = (RadioButton) findViewById(R.id.rbKondisiShockbreaker3);
        rbShockbreakerBuruk = (RadioButton) findViewById(R.id.rbKondisiShockbreaker2);
        rbShockbreakerSangatBuruk = (RadioButton) findViewById(R.id.rbKondisiShockbreaker1);

        rgPowerSteering = (RadioGroup) findViewById(R.id.rdgPowerSteering);
        rbPowerSteeringSangatBaik = (RadioButton) findViewById(R.id.rbKondisiPowerSteering5);
        rbPowerSteeringBaik = (RadioButton) findViewById(R.id.rbKondisiPowerSteering4);
        rbPowerSteeringSedang = (RadioButton) findViewById(R.id.rbKondisiPowerSteering3);
        rbPowerSteeringBuruk = (RadioButton) findViewById(R.id.rbKondisiPowerSteering2);
        rbPowerSteeringSangatBuruk = (RadioButton) findViewById(R.id.rbKondisiPowerSteering1);

        rgRackSteer = (RadioGroup) findViewById(R.id.rdgRacksteer);
        rbRackSteerSangatBaik = (RadioButton) findViewById(R.id.rbKondisiRacksteer5);
        rbRackSteerBaik = (RadioButton) findViewById(R.id.rbKondisiRacksteer4);
        rbRackSteerSedang = (RadioButton) findViewById(R.id.rbKondisiRacksteer3);
        rbRackSteerBuruk = (RadioButton) findViewById(R.id.rbKondisiRacksteer2);
        rbRackSteerSangatBuruk = (RadioButton) findViewById(R.id.rbKondisiRacksteer1);

        rgBaljoin = (RadioGroup) findViewById(R.id.rdgBaljoin);
        rbBaljoinSangatBaik = (RadioButton) findViewById(R.id.rbKondisiBaljoin5);
        rbBaljoinBaik = (RadioButton) findViewById(R.id.rbKondisiBaljoin4);
        rbBaljoinSedang = (RadioButton) findViewById(R.id.rbKondisiBaljoin3);
        rbBaljoinBuruk = (RadioButton) findViewById(R.id.rbKondisiBaljoin2);
        rbBaljoingSangatBuruk = (RadioButton) findViewById(R.id.rbKondisiBaljoin1);

        rgTerod = (RadioGroup) findViewById(R.id.rdgTerod);
        rbTerodSangatBaik = (RadioButton) findViewById(R.id.rbKondisiTerod5);
        rbTerodBaik = (RadioButton) findViewById(R.id.rbKondisiTerod4);
        rbTerodSedang = (RadioButton) findViewById(R.id.rbKondisiTerod3);
        rbTerodBuruk = (RadioButton) findViewById(R.id.rbKondisiTerod2);
        rbTerodBuruk = (RadioButton) findViewById(R.id.rbKondisiTerod1);

        imgBackToDetailData = (ImageView) findViewById(R.id.backToDetailData);
        btnUpdate = (Button) findViewById(R.id.btnUpdateUndersteel);
        btnUpdate.setOnClickListener(this);
        imgBackToDetailData.setOnClickListener(this);

        idMobil = getIntent().getStringExtra("idMobil");

        firebaseFirestore = FirebaseFirestore.getInstance();

        desShockbreaker = getIntent().getStringExtra("deskripsi_shockbreaker");
        if(desShockbreaker.equals("Sangat Baik")){
            rbShockbreakerSangatBaik.setChecked(true);
        }
        else if(desShockbreaker.equals("Baik")){
            rbShockbreakerBaik.setChecked(true);
        }
        else if(desShockbreaker.equals("Sedang")){
            rbShockbreakerSedang.setChecked(true);
        }
        else if(desShockbreaker.equals("Buruk")){
            rbShockbreakerBuruk.setChecked(true);
        }
        else if(desShockbreaker.equals("Sangat Buruk")){
            rbShockbreakerSangatBuruk.setChecked(true);
        }

        desBaljoin = getIntent().getStringExtra("deskripsi_baljoin");
        if(desBaljoin.equals("Sangat Baik")){
            rbBaljoinSangatBaik.setChecked(true);
        }
        else if(desBaljoin.equals("Baik")){
            rbBaljoinBaik.setChecked(true);
        }
        else if(desBaljoin.equals("Sedang")){
            rbBaljoinSedang.setChecked(true);
        }
        else if(desBaljoin.equals("Buruk")){
            rbBaljoinBuruk.setChecked(true);
        }
        else if(desBaljoin.equals("Sangat Buruk")){
            rbBaljoingSangatBuruk.setChecked(true);
        }

        desTransmisi = getIntent().getStringExtra("deskripsi_transmisi");
        if(desTransmisi.equals("Sangat Baik")){
            rbTransmisiSangatBaik.setChecked(true);
        }
        else if(desTransmisi.equals("Baik")){
            rbTransmisiBaik.setChecked(true);
        }
        else if(desTransmisi.equals("Sedang")){
            rbTransmisiSedang.setChecked(true);
        }
        else if(desTransmisi.equals("Buruk")){
            rbTransmisiBuruk.setChecked(true);
        }
        else if(desTransmisi.equals("Sangat Buruk")){
            rbTransmisiSangatBuruk.setChecked(true);
        }

        desTerod = getIntent().getStringExtra("deskripsi_terod");
        if(desTerod.equals("Sangat Baik")){
            rbTerodSangatBaik.setChecked(true);
        }
        else if(desTerod.equals("Baik")){
            rbTerodBaik.setChecked(true);
        }
        else if(desTerod.equals("Sedang")){
            rbTerodSedang.setChecked(true);
        }
        else if(desTerod.equals("Buruk")){
            rbTerodBuruk.setChecked(true);
        }
        else if(desTerod.equals("Sangat Buruk")){
            rbTerodSangatBuruk.setChecked(true);
        }

        desRacksteer = getIntent().getStringExtra("deskripsi_racksteer");
        if(desRacksteer.equals("Sangat Baik")){
            rbRackSteerSangatBaik.setChecked(true);
        }
        else if(desRacksteer.equals("Baik")){
            rbRackSteerBaik.setChecked(true);
        }
        else if(desRacksteer.equals("Sedang")){
            rbRackSteerSedang.setChecked(true);
        }
        else if(desRacksteer.equals("Buruk")){
            rbRackSteerBuruk.setChecked(true);
        }
        else if(desRacksteer.equals("Sangat Buruk")){
            rbRackSteerSangatBuruk.setChecked(true);
        }

        desPowerSteering = getIntent().getStringExtra("deskripsi_powersteering");
        if(desPowerSteering.equals("Sangat Baik")){
            rbPowerSteeringSangatBaik.setChecked(true);
        }
        else if(desPowerSteering.equals("Baik")){
            rbPowerSteeringBaik.setChecked(true);
        }
        else if(desPowerSteering.equals("Sedang")){
            rbPowerSteeringSedang.setChecked(true);
        }
        else if(desPowerSteering.equals("Buruk")){
            rbPowerSteeringBuruk.setChecked(true);
        }
        else if(desPowerSteering.equals("Sangat Buruk")){
            rbPowerSteeringSangatBuruk.setChecked(true);
        }


        rgShockbreaker.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbKondisiShockbreaker5:
                        nilaiShockbreaker = 5;
                        desShockbreaker = "Sangat Baik";
                        break;
                    case R.id.rbKondisiShockbreaker4:
                        nilaiShockbreaker = 4;
                        desShockbreaker = "Baik";
                        break;
                    case R.id.rbKondisiShockbreaker3:
                        nilaiShockbreaker = 3;
                        desShockbreaker = "Sedang";
                        break;
                    case R.id.rbKondisiShockbreaker2:
                        nilaiShockbreaker = 2;
                        desShockbreaker = "Buruk";
                        break;
                    case R.id.rbKondisiShockbreaker1:
                        nilaiShockbreaker = 1;
                        desShockbreaker = "Sangat Buruk";
                        break;
                }
            }
        });
        rgTransmisi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.rbKondisiTransmisi5:
                        nilaiTransmisi = 5;
                        desTransmisi = "Sangat Baik";
                        break;
                    case R.id.rbKondisiTransmisi4:
                        nilaiTransmisi = 4;
                        desTransmisi = "Baik";
                        break;
                    case R.id.rbKondisiTransmisi3:
                        nilaiTransmisi = 3;
                        desTransmisi = "Sedang";
                        break;
                    case R.id.rbKondisiTransmisi2:
                        nilaiTransmisi = 2;
                        desTransmisi = "Buruk";
                        break;
                    case R.id.rbKondisiTransmisi1:
                        nilaiTransmisi = 1;
                        desTransmisi = "Sangat Buruk";
                        break;
                }
            }
        });
        rgTerod.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.rbKondisiTerod5:
                        nilaiTerod = 5;
                        desTerod = "Sangat Baik";
                        break;
                    case R.id.rbKondisiTerod4:
                        nilaiTerod = 4;
                        desTerod = "Baik";
                        break;
                    case R.id.rbKondisiTerod3:
                        nilaiTerod = 3;
                        desTerod = "Sedang";
                        break;
                    case R.id.rbKondisiTerod2:
                        nilaiTerod = 2;
                        desTerod = "Buruk";
                        break;
                    case R.id.rbKondisiTerod1:
                        nilaiTerod = 1;
                        desTerod = "Sangat Buruk";
                        break;
                }
            }
        });
        rgRackSteer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbKondisiRacksteer5:
                        nilaiRacksteer = 5;
                        desRacksteer = "Sangat Baik";
                        break;
                    case R.id.rbKondisiRacksteer4:
                        nilaiRacksteer = 4;
                        desRacksteer = "Baik";
                        break;
                    case R.id.rbKondisiRacksteer3:
                        nilaiRacksteer = 3;
                        desRacksteer = "Sedang";
                        break;
                    case R.id.rbKondisiRacksteer2:
                        nilaiRacksteer = 2;
                        desRacksteer = "Buruk";
                        break;
                    case R.id.rbKondisiRacksteer1:
                        nilaiRacksteer = 1;
                        desRacksteer = "Sangat Buruk";
                        break;
                }
            }
        });
        rgPowerSteering.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.rbKondisiPowerSteering5:
                        nilaiPowerSteering = 5;
                        desPowerSteering = "Sangat Baik";
                        break;
                    case R.id.rbKondisiPowerSteering4:
                        nilaiPowerSteering = 4;
                        desPowerSteering = "Baik";
                        break;
                    case R.id.rbKondisiPowerSteering3:
                        nilaiPowerSteering = 3;
                        desPowerSteering = "Sedang";
                        break;
                    case R.id.rbKondisiPowerSteering2:
                        nilaiPowerSteering = 2;
                        desPowerSteering = "Buruk";
                        break;
                    case R.id.rbKondisiPowerSteering1:
                        nilaiPowerSteering = 1;
                        desPowerSteering = "Sangat Buruk";
                        break;
                }
            }
        });
        rgRackSteer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbKondisiRacksteer5:
                        nilaiRacksteer = 5;
                        desRacksteer = "Sangat Baik";
                        break;
                    case R.id.rbKondisiRacksteer4:
                        nilaiRacksteer = 4;
                        desRacksteer = "Baik";
                        break;
                    case R.id.rbKondisiRacksteer3:
                        nilaiRacksteer = 3;
                        desRacksteer = "Sedang";
                        break;
                    case R.id.rbKondisiRacksteer2:
                        nilaiRacksteer = 2;
                        desRacksteer = "Buruk";
                        break;
                    case R.id.rbKondisiRacksteer1:
                        nilaiRacksteer = 1;
                        desRacksteer = "Sangat Buruk";
                        break;
                }
            }
        });
        rgBaljoin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbKondisiBaljoin5:
                        nilaiBaljoin = 5;
                        desBaljoin = "Sangat Baik";
                        break;
                    case R.id.rbKondisiBaljoin4:
                        nilaiBaljoin = 4;
                        desBaljoin = "Baik";
                        break;
                    case R.id.rbKondisiBaljoin3:
                        nilaiBaljoin = 3;
                        desBaljoin = "Sedang";
                        break;
                    case R.id.rbKondisiBaljoin2:
                        nilaiBaljoin = 2;
                        desBaljoin = "Buruk";
                        break;
                    case R.id.rbKondisiBaljoin1:
                        nilaiBaljoin = 1;
                        desBaljoin = "Sangat Buruk";
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
            case R.id.btnUpdateUndersteel:
                Intent i = new Intent();
                i.putExtra("deskripsi_transmisi", desTransmisi);
                i.putExtra("nilai_transmisi", nilaiTransmisi);
                i.putExtra("deskripsi_powersteering", desPowerSteering);
                i.putExtra("nilai_powersteering", nilaiPowerSteering);
                i.putExtra("deskripsi_shockbreaker", desShockbreaker);
                i.putExtra("nilai_shockbreaker", nilaiShockbreaker);
                i.putExtra("deskripsi_baljoin", desBaljoin);
                i.putExtra("nilai_baljoin", nilaiBaljoin);
                i.putExtra("deskripsi_racksteer", desRacksteer);
                i.putExtra("nilai_racksteer", nilaiRacksteer);
                i.putExtra("deskripsi_terod", desTerod);
                i.putExtra("nilai_terod", nilaiTerod);
                setResult(Activity.RESULT_OK, i);
                finish();
                break;
        }
    }
}