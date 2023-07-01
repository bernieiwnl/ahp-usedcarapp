package com.example.tugas_akhir.MOBIL;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tugas_akhir.ADAPTER.FotoMobilAdapter;
import com.example.tugas_akhir.ADDATA_MOBIL.AddDataMobilActivity;
import com.example.tugas_akhir.MOBIL.MASTER.ADAPTER.KeadaanBodyAppAdapter;
import com.example.tugas_akhir.MOBIL.MASTER.ListKeadaanBodyAppActivity;
import com.example.tugas_akhir.MOBIL.MASTER.ListKelengkapanAppActivity;
import com.example.tugas_akhir.MOBIL.MASTER.ListWarnaAppActivity;
import com.example.tugas_akhir.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

public class TambahMobilAppActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

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
    private FotoMobilAdapter fotoMobilAdapter;

    //arraylist
    private ArrayList<Bitmap> fileImageList;
    private ArrayList<Uri> fileUriList;

    private static final int RESULT_LOAD_IMAGE1 = 1;
    private static final int RESULT_WARNA_MOBIL = 2;
    private static final int RESULT_KEADAAN_MOBIL = 3;
    private static final int RESULT_KELENGKAPAN_MOBIL = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_mobil_app);

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
                startActivityForResult(new Intent(TambahMobilAppActivity.this, ListWarnaAppActivity.class), RESULT_WARNA_MOBIL);
                break;
            }
            case R.id.inputKelengkapanMobil: {
                startActivityForResult(new Intent(TambahMobilAppActivity.this, ListKelengkapanAppActivity.class), RESULT_KELENGKAPAN_MOBIL);
                break;
            }
            case R.id.inputKeadaanBodyMobil: {
                startActivityForResult(new Intent(TambahMobilAppActivity.this, ListKeadaanBodyAppActivity.class), RESULT_KEADAAN_MOBIL);
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
                break;
            }
            case R.id.imageView_tambahMobil: {
                simpanDataMobil();
                break;
            }
            case R.id.imageView_back: {
                finish();
                break;
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
            } else if (requestCode == RESULT_WARNA_MOBIL && resultCode == RESULT_OK) {
                String warnaMobil = data.getStringExtra("warnaMobil");
                txtInputWarna.setText(warnaMobil);
            } else if (requestCode == RESULT_KEADAAN_MOBIL && resultCode == RESULT_OK) {
                String keadaanMobil = data.getStringExtra("keadaanMobil");
                txtInputKeadaanMobil.setText(keadaanMobil);
            } else if (requestCode == RESULT_KELENGKAPAN_MOBIL && resultCode == RESULT_OK) {
                String kelengkapanMobil = data.getStringExtra("kelengkapanMobil");
                txtInputKelengkapanMobil.setText(kelengkapanMobil);
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
                txtKondisiMesin = "Suara Mesin Halus";
                break;
            }
            case R.id.kondisiMesinKasar: {
                txtKondisiMesin = "Suara Mesin Kasar";
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

    //ambil nama pada data foto yang dipilih
    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    private void uploadFotoMobil(final String id) {
        try {
            if (fileUriList.isEmpty()) {
                return;
            }
            //call time
            long current = Calendar.getInstance().getTimeInMillis();
            Integer data = 0;
            for (int i = 0; i < fileUriList.size(); i++) {
                String fileName = getFileName(fileUriList.get(i));
                final StorageReference fileToUpload = storageReference.child("foto_mobil").child(fileName + current + "_" + id + "_" + i);
                storageTask = fileToUpload.putFile(fileUriList.get(i));
                storageTask.continueWithTask((Continuation<UploadTask.TaskSnapshot, Task<Uri>>) task -> {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return fileToUpload.getDownloadUrl();
                }).addOnCompleteListener((OnCompleteListener<Uri>) task -> {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        String mUri = downloadUri.toString();
                        firebaseFirestore.collection("mobil").document(id).update("fotoMobil", FieldValue.arrayUnion(mUri)).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                fotoMobilAdapter.notifyDataSetChanged();
                                Toast.makeText(TambahMobilAppActivity.this,
                                        "Upload foto mobil berhasil", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        fotoMobilAdapter.notifyDataSetChanged();
                        Toast.makeText(TambahMobilAppActivity.this,
                                "Upload foto mobil gagal", Toast.LENGTH_SHORT).show();
                    }
                });
                data++;
            }
        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void simpanDataMobil() {
        try {
            Map<String, Object> mobil = new HashMap<>();
            mobil.put("idMobil", "");
            mobil.put("namaMerkMobil", txtInputNamaMerkMobil.getText().toString().trim());
            mobil.put("tipeMobil", txtTipeMobil);
            mobil.put("transmisiMobil", txtTransmisiMobil);
            mobil.put("tahunMobil", Integer.parseInt(txtInputTahunMobil.getText().toString().trim()));
            mobil.put("kilometerMobil", Integer.parseInt(txtInputKilometerMobil.getText().toString().trim()));
            mobil.put("warnaMobil", txtInputWarna.getText().toString().trim());
            mobil.put("kapasitasMobil", Integer.parseInt(txtInputKapasitasMesinMobil.getText().toString().trim()));
            mobil.put("hargaMobil", Integer.parseInt(txtInputHargaMobil.getText().toString().trim()));
            mobil.put("sejarahMobil", txtInputSejarahMobil.getText().toString().trim());
            mobil.put("kondisiMesinMobil", txtKondisiMesin);
            mobil.put("serviceRecordMobil", txtServiceMobil);
            mobil.put("kondisiInteriorMobil", txtKondisiInterior);
            mobil.put("keadaanMobil", txtInputKeadaanMobil.getText().toString().trim());
            mobil.put("kelengkapanMobil", txtInputKelengkapanMobil.getText().toString().trim());

            firebaseFirestore.collection("mobil").add(mobil).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    if (task.isSuccessful()) {
                        firebaseFirestore.collection("mobil").document(task.getResult().getId()).update("idMobil", task.getResult().getId()).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(), "Tambah Mobil Sukses", Toast.LENGTH_SHORT).show();
                                uploadFotoMobil(task.getResult().getId());
                                finish();
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "Tambah Mobil Gagal", Toast.LENGTH_SHORT).show();
                    }


                }
            });

        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}