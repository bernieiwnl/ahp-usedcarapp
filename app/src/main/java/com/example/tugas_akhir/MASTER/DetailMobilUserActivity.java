package com.example.tugas_akhir.MASTER;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tugas_akhir.R;
import com.smarteist.autoimageslider.SliderView;

public class DetailMobilUserActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView judul;
    private TextView harga;
    private TextView kelengkapanBerkas;
    private TextView pajak;
    private TextView transmisi;
    private TextView kilometer;
    private TextView tahun;
    private TextView kapasitasMesin;
    private TextView serviceRecord;
    private TextView kondisiKelistrikan;
    private TextView kondisiPembakaran;
    private TextView kondisiRadiator;
    private TextView kondisiBody;
    private TextView kondisiCat;
    private TextView kondisiBan;
    private TextView kondisiTransmisi;
    private TextView kondisiShockbreaker;
    private TextView kondisiPowersteering;
    private TextView kondisiRacksteer;
    private TextView kondisiTerod;
    private TextView kondisiBaljoin;
    private SliderView imgFotoMobil;
    private ImageView imgBackToHome;
    private ImageView imgEditDetailMobil;
    private Button btnBanding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mobil_user);

        judul = (TextView) findViewById(R.id.txtJudul);
        harga = (TextView) findViewById(R.id.txtHarga);
        kelengkapanBerkas = (TextView) findViewById(R.id.txtKelengkapanBerkas);
        pajak = (TextView) findViewById(R.id.txtPajakMobil);
        transmisi = (TextView) findViewById(R.id.txtTransmisi);
        kilometer = (TextView) findViewById(R.id.txtKilometer);
        tahun = (TextView) findViewById(R.id.txtTahun);
        kapasitasMesin = (TextView) findViewById(R.id.txtKapasitasMesin);
        serviceRecord = (TextView) findViewById(R.id.txtService);
        kondisiKelistrikan = (TextView) findViewById(R.id.txtKondisiKelistrikan);
        kondisiPembakaran = (TextView) findViewById(R.id.txtKondisiPembakaran);
        kondisiRadiator = (TextView) findViewById(R.id.txtKondisiRadiator);
        kondisiBody = (TextView) findViewById(R.id.txtKondisiBody);
        kondisiCat = (TextView) findViewById(R.id.txtKondisiCat);
        kondisiBan = (TextView) findViewById(R.id.txtKondisiBan);
        kondisiTransmisi = (TextView) findViewById(R.id.txtKondisiTransmisi);
        kondisiShockbreaker = (TextView) findViewById(R.id.txtKondisiShockbreaker);
        kondisiPowersteering = (TextView) findViewById(R.id.txtKondisiPowerSteering);
        kondisiRadiator = (TextView) findViewById(R.id.txtKondisiRadiator);
        kondisiRacksteer = (TextView) findViewById(R.id.txtKondisiRacksteer);
        kondisiTerod = (TextView) findViewById(R.id.txtKondisiTerod);
        kondisiBaljoin = (TextView) findViewById(R.id.txtKondisiBaljoin);
        imgFotoMobil = (SliderView) findViewById(R.id.imgSliderMobil);
        btnBanding = (Button) findViewById(R.id.btnBandingData);
        imgBackToHome = (ImageView) findViewById(R.id.icon_backToHome);
        imgEditDetailMobil = (ImageView) findViewById(R.id.icon_editDetail);

        imgEditDetailMobil.setOnClickListener(this);
        btnBanding.setOnClickListener(this);
        imgBackToHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}