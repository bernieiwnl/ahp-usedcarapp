package com.example.tugas_akhir.MOBIL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tugas_akhir.ADAPTER.EditFotoMobilAdapter;
import com.example.tugas_akhir.ADAPTER.FotoMobilAdapter;
import com.example.tugas_akhir.CLASS.Firestore;
import com.example.tugas_akhir.MOBIL.CLASS.NewMobil;
import com.example.tugas_akhir.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class UbahMobilAppActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private FirebaseFirestore firebaseFirestore;
    private StorageReference storageReference;
    private StorageTask storageTask;

    private TextInputEditText txtInputWarna,
            txtInputKeadaanMobil,
            txtInputKelengkapanMobil,
            txtInputNamaMerkMobil,
            txtInputTahunMobil,
            txtInputKilometerMobil,
            txtInputKapasitasMesinMobil,
            txtInputHargaMobil,
            txtInputSejarahMobil;

    //tipe mobil
    private RadioGroup radioGroupTipeMobil;
    private RadioButton radioButtonTipeSuv, radioButtonTipeHatchBack, radioButtonTipeSedan;
    private String txtTipeMobil;

    //transmisi mpbil
    private RadioGroup radioGroupTransmisiMobil;
    private RadioButton radioButtonTransmisiOtomatis, radioButtonTransmisiManual, radioButtonTransmisiKeduanya;
    private String txtTransmisiMobil;

    //service record
    private RadioGroup radioGroupServiceRecordMobil;
    private RadioButton radioButtonServiceRutin, radioButtonServiceTerkadang, radioButtonServiceJarang, radioButtonServiceTidakPernah;
    private String txtServiceMobil;

    //kondisi mesin
    private RadioGroup radioGroupKondisiMesinMobil;
    private RadioButton radioButtonKondisiMesinKasar, radioButtonKondisiMesinHalus;
    private String txtKondisiMesin;

    //kondisi interior
    private RadioGroup radioGroupKondisiInterior;
    private RadioButton radioButtonInteriorAsli, radioButtonInteriorTidakAsli;
    private String txtKondisiInterior;

    //button
    private Button btnTambahFoto;

    //imageView
    private ImageView imageViewSimpanMobil;
    private ImageView imageViewBack;

    //Recylerview
    private RecyclerView recyclerView_fotoMobil;

    //adapter
    private EditFotoMobilAdapter fotoMobilAdapter;

    //arraylist
    private ArrayList<String> fileImageList;
    private ArrayList<Uri> fileUriList;

    private static final int RESULT_LOAD_IMAGE1 = 1;
    private static final int RESULT_WARNA_MOBIL = 2;
    private static final int RESULT_KEADAAN_MOBIL = 3;
    private static final int RESULT_KELENGKAPAN_MOBIL = 4;
    private static final String RESULT_ID_MOBIL = "p7junUOjy64Jz49sWedJ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_mobil_app);

        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        //Text input editext
        txtInputWarna = (TextInputEditText) findViewById(R.id.inputWarnaMobil);
        txtInputKelengkapanMobil = (TextInputEditText) findViewById(R.id.inputKelengkapanMobil);
        txtInputKeadaanMobil = (TextInputEditText) findViewById(R.id.inputKeadaanBodyMobil);
        txtInputNamaMerkMobil = (TextInputEditText) findViewById(R.id.inputNamaMerkMobil);
        txtInputTahunMobil = (TextInputEditText) findViewById(R.id.inputTahunMobil);
        txtInputKilometerMobil = (TextInputEditText) findViewById(R.id.inputKilometerMobil);
        txtInputKapasitasMesinMobil = (TextInputEditText) findViewById(R.id.inputKapasitasMesin);
        txtInputHargaMobil = (TextInputEditText) findViewById(R.id.inputHargaMobil);
        txtInputSejarahMobil = (TextInputEditText) findViewById(R.id.inputSejarahMobil);

        //string
        txtTipeMobil = null;
        txtTransmisiMobil = null;
        txtKondisiMesin = null;
        txtServiceMobil = null;
        txtKondisiInterior = null;

        //radiobutton & grup
        //tipe mobil
        radioGroupTipeMobil = (RadioGroup) findViewById(R.id.radioGrup_tipeMobil);
        radioButtonTipeSuv = (RadioButton) findViewById(R.id.tipeSUV);
        radioButtonTipeHatchBack = (RadioButton) findViewById(R.id.tipeHatchBack);
        radioButtonTipeSedan = (RadioButton) findViewById(R.id.tipeSedan);
        //transmisi mobil
        radioGroupTransmisiMobil = (RadioGroup) findViewById(R.id.radioGrup_transmisiMobil);
        radioButtonTransmisiOtomatis = (RadioButton) findViewById(R.id.transmisiAutomatic);
        radioButtonTransmisiManual = (RadioButton) findViewById(R.id.transmisiManual);
        radioButtonTransmisiKeduanya = (RadioButton) findViewById(R.id.transmisiKeduanya);
        //kondisi mesin
        radioGroupKondisiMesinMobil = (RadioGroup) findViewById(R.id.radioGrup_kondisiMesinMobil);
        radioButtonKondisiMesinKasar = (RadioButton) findViewById(R.id.kondisiMesinKasar);
        radioButtonKondisiMesinHalus = (RadioButton) findViewById(R.id.kondisiMesinHalus);
        //Service Record Mobil
        radioGroupServiceRecordMobil = (RadioGroup) findViewById(R.id.radioGrup_serviceRecordMobil);
        radioButtonServiceRutin = (RadioButton) findViewById(R.id.serviceRutin);
        radioButtonServiceTerkadang = (RadioButton) findViewById(R.id.serviceTerkadang);
        radioButtonServiceJarang = (RadioButton) findViewById(R.id.serviceJarang);
        radioButtonServiceTidakPernah = (RadioButton) findViewById(R.id.serviceTidakPernah);
        //kondisi interior
        radioGroupKondisiInterior = (RadioGroup) findViewById(R.id.radioGrup_kondisiInterior);
        radioButtonInteriorAsli = (RadioButton) findViewById(R.id.interiorAsli);
        radioButtonInteriorTidakAsli = (RadioButton) findViewById(R.id.interiorTidakAsli);

        //Set On Check Change Listener
        radioGroupTipeMobil.setOnCheckedChangeListener(this);
        radioGroupTransmisiMobil.setOnCheckedChangeListener(this);
        radioGroupKondisiMesinMobil.setOnCheckedChangeListener(this);
        radioGroupServiceRecordMobil.setOnCheckedChangeListener(this);
        radioGroupKondisiInterior.setOnCheckedChangeListener(this);

        //RecylerView
        recyclerView_fotoMobil = (RecyclerView) findViewById(R.id.recycler_fotoMobil);

        //Button
        btnTambahFoto = (Button) findViewById(R.id.btnTambahFoto);

        //Imageview
        imageViewBack = (ImageView) findViewById(R.id.imageView_back);
        imageViewSimpanMobil = (ImageView) findViewById(R.id.imageView_tambahMobil);

        //Array List untuk meyimpan sementara gambar yang diupload
        fileImageList = new ArrayList<>();
        fileUriList = new ArrayList<>();

        //set On Click listener
        txtInputWarna.setOnClickListener(this);
        txtInputKelengkapanMobil.setOnClickListener(this);
        txtInputKeadaanMobil.setOnClickListener(this);
        btnTambahFoto.setOnClickListener(this);
        imageViewSimpanMobil.setOnClickListener(this);
        imageViewBack.setOnClickListener(this);

        //setAdapter
        fotoMobilAdapter = new EditFotoMobilAdapter(UbahMobilAppActivity.this, fileImageList);
        recyclerView_fotoMobil.setAdapter(fotoMobilAdapter);
        recyclerView_fotoMobil.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_fotoMobil.setNestedScrollingEnabled(false);

        fetchData(RESULT_ID_MOBIL);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }


    @SuppressLint("SetTextI18n")
    private void fetchData(String idMobil) {
        try {
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            CollectionReference collectionReference = firebaseFirestore.collection("mobil");
            DocumentReference mobilDb = collectionReference.document(idMobil);

            mobilDb.get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot == null & !documentSnapshot.exists()) {
                    return;
                }

                NewMobil mobil = documentSnapshot.toObject(NewMobil.class);
                fileImageList.addAll(mobil.getFotoMobil());
                fotoMobilAdapter.notifyDataSetChanged();

                txtInputNamaMerkMobil.setText(mobil.getNamaMerkMobil());
                txtTipeMobil = mobil.getTipeMobil();
                switch (txtTipeMobil) {
                    case "SUV":
                        radioButtonTipeSuv.setChecked(true);
                        break;
                    case "Hatchback":
                        radioButtonTipeHatchBack.setChecked(true);
                        break;
                    default:
                        radioButtonTipeSedan.setChecked(true);
                        break;
                }
                txtTransmisiMobil = mobil.getTransmisiMobil();
                switch (txtTransmisiMobil) {
                    case "Matic":
                        radioButtonTransmisiOtomatis.setChecked(true);
                        break;
                    case "Manual":
                        radioButtonTransmisiManual.setChecked(true);
                        break;
                    default:
                        radioButtonTransmisiKeduanya.setChecked(true);
                        break;
                }
                txtInputTahunMobil.setText(mobil.getTahunMobil().toString());
                txtInputKilometerMobil.setText(mobil.getKilometerMobil().toString());
                txtInputWarna.setText(mobil.getWarnaMobil());
                txtInputKapasitasMesinMobil.setText(mobil.getKapasitasMobil().toString());
                txtInputHargaMobil.setText(mobil.getHargaMobil().toString());
                txtInputSejarahMobil.setText(mobil.getSejarahMobil());
                txtKondisiMesin = mobil.getKondisiMesinMobil();
                switch (txtKondisiMesin) {
                    case "Suara Mesin Halus":
                        radioButtonKondisiMesinHalus.setChecked(true);
                        break;
                    default:
                        radioButtonKondisiMesinKasar.setChecked(true);
                        break;
                }
                txtServiceMobil = mobil.getServiceRecordMobil();
                switch (txtServiceMobil) {
                    case "Rutin":
                        radioButtonServiceRutin.setChecked(true);
                        break;
                    case "Terkadang":
                        radioButtonServiceTerkadang.setChecked(true);
                        break;
                    case "Jarang":
                        radioButtonServiceJarang.setChecked(true);
                        break;
                    default:
                        radioButtonServiceTidakPernah.setChecked(true);
                        break;
                }
                txtKondisiInterior = mobil.getKondisiInteriorMobil();
                switch (txtKondisiInterior) {
                    case "Interior Asli":
                        radioButtonInteriorAsli.setChecked(true);
                        break;
                    default:
                        radioButtonInteriorTidakAsli.setChecked(true);
                        break;
                }
                txtInputKeadaanMobil.setText(mobil.getKeadaanMobil());
                txtInputKelengkapanMobil.setText(mobil.getKelengkapanMobil());
            }).addOnFailureListener(error -> {
                Log.e("ErrorMsg", error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            });


        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}