package com.example.tugas_akhir.MOBIL;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tugas_akhir.ADAPTER.FotoMobilAdapter;
import com.example.tugas_akhir.MOBIL.MASTER.ADAPTER.KeadaanBodyAppAdapter;
import com.example.tugas_akhir.MOBIL.MASTER.ListKeadaanBodyAppActivity;
import com.example.tugas_akhir.MOBIL.MASTER.ListKelengkapanAppActivity;
import com.example.tugas_akhir.MOBIL.MASTER.ListWarnaAppActivity;
import com.example.tugas_akhir.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class TambahMobilAppActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

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

    private Button btnTambahFoto;
    private RecyclerView recyclerView_fotoMobil;
    private FotoMobilAdapter fotoMobilAdapter;

    private ArrayList<Bitmap> fileImageList;
    private ArrayList<Uri> fileUriList;

    private static final int RESULT_LOAD_IMAGE1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_mobil_app);

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

        //Array List untuk meyimpan sementara gambar yang diupload
        fileImageList = new ArrayList<>();
        fileUriList = new ArrayList<>();

        //set On Click listener
        txtInputWarna.setOnClickListener(this);
        txtInputKelengkapanMobil.setOnClickListener(this);
        txtInputKeadaanMobil.setOnClickListener(this);
        btnTambahFoto.setOnClickListener(this);

        //setAdapter
        fotoMobilAdapter = new FotoMobilAdapter(fileImageList, TambahMobilAppActivity.this);
        recyclerView_fotoMobil.setAdapter(fotoMobilAdapter);
        recyclerView_fotoMobil.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_fotoMobil.setNestedScrollingEnabled(false);

        //swipe up and down / left or right
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemCallBack);
        itemTouchHelper.attachToRecyclerView(recyclerView_fotoMobil);
    }

    ItemTouchHelper.SimpleCallback itemCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            try {
                fileImageList.remove(viewHolder.getAdapterPosition());
                fileUriList.remove(viewHolder.getAdapterPosition());
                recyclerView_fotoMobil.getAdapter().notifyDataSetChanged();
            } catch (Exception e) {
                Log.e("ErrorMsg", e.getMessage());
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inputWarnaMobil: {
                startActivity(new Intent(TambahMobilAppActivity.this, ListWarnaAppActivity.class));
                break;
            }
            case R.id.inputKelengkapanMobil: {
                startActivity(new Intent(TambahMobilAppActivity.this, ListKelengkapanAppActivity.class));
                break;
            }
            case R.id.inputKeadaanBodyMobil: {
                startActivity(new Intent(TambahMobilAppActivity.this, ListKeadaanBodyAppActivity.class));
                break;
            }
            case R.id.btnTambahFoto: {
                try {
                    //Klik button to open galery
                    Intent i = new Intent();
                    i.setType("image/*");
                    i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    i.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(i, "Select Picture"), RESULT_LOAD_IMAGE1);
                } catch (Exception e) {
                    Log.e("ErrorMsg", e.getMessage());
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == RESULT_LOAD_IMAGE1 && resultCode == RESULT_OK) {
                if (data.getClipData() != null) {
                    int totalItemSelected = data.getClipData().getItemCount();
                    for (int i = 0; i < totalItemSelected; i++) {
                        Uri fileUri = data.getClipData().getItemAt(i).getUri();
                        Bitmap bitmap = null;
                        try {
                            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(fileUri));
                            fileImageList.add(bitmap);
                            fileUriList.add(fileUri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (data.getData() != null) {
                    Uri fileUri = data.getData();
                    Bitmap bitmap = null;
                    try {
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(fileUri));
                        fileImageList.add(bitmap);
                        fileUriList.add(fileUri);
                        fotoMobilAdapter.notifyDataSetChanged();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.tipeSUV: {
                txtTipeMobil = "SUV";
                break;
            }
            case R.id.tipeHatchBack: {
                txtTipeMobil = "Hatchback";
                break;
            }
            case R.id.tipeSedan: {
                txtTipeMobil = "Sedan";
                break;
            }
            case R.id.transmisiAutomatic: {
                txtTransmisiMobil = "Matic";
                break;
            }
            case R.id.transmisiManual: {
                txtTransmisiMobil = "Manual";
                break;
            }
            case R.id.transmisiKeduanya: {
                txtTransmisiMobil = "Keduanya";
                break;
            }
            case R.id.kondisiMesinHalus: {
                txtKondisiMesin = "Suara Mesin Kasar";
                break;
            }
            case R.id.kondisiMesinKasar: {
                txtKondisiMesin = "Suara Mesin Halus";
                break;
            }
            case R.id.serviceRutin: {
                txtServiceMobil = "Rutin";
                break;
            }
            case R.id.serviceTerkadang: {
                txtServiceMobil = "Terkadang";
                break;
            }
            case R.id.serviceJarang: {
                txtServiceMobil = "Jarang";
                break;
            }
            case R.id.serviceTidakPernah: {
                txtServiceMobil = "Tidak Pernah";
                break;
            }
            case R.id.interiorAsli: {
                txtKondisiInterior = "Interior Asli";
                break;
            }
            case R.id.interiorTidakAsli: {
                txtKondisiInterior = "Interior Palsu";
                break;
            }
        }
    }
}