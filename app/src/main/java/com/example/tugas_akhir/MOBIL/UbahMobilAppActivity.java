package com.example.tugas_akhir.MOBIL;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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

import com.example.tugas_akhir.ADAPTER.EditFotoMobilAdapter;
import com.example.tugas_akhir.ADAPTER.FotoMobilAdapter;
import com.example.tugas_akhir.CLASS.Firestore;
import com.example.tugas_akhir.MOBIL.CLASS.NewMobil;
import com.example.tugas_akhir.MOBIL.MASTER.ListKeadaanBodyAppActivity;
import com.example.tugas_akhir.MOBIL.MASTER.ListKelengkapanAppActivity;
import com.example.tugas_akhir.MOBIL.MASTER.ListWarnaAppActivity;
import com.example.tugas_akhir.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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

        fetchDataFoto(RESULT_ID_MOBIL);
        fetchData(RESULT_ID_MOBIL);

        //setAdapter
        fotoMobilAdapter = new EditFotoMobilAdapter(UbahMobilAppActivity.this, fileImageList);
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
                hapusFotoMobil(RESULT_ID_MOBIL, viewHolder.getAdapterPosition());
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
                startActivityForResult(new Intent(UbahMobilAppActivity.this, ListWarnaAppActivity.class), RESULT_WARNA_MOBIL);
                break;
            }
            case R.id.inputKelengkapanMobil: {
                startActivityForResult(new Intent(UbahMobilAppActivity.this, ListKelengkapanAppActivity.class), RESULT_KELENGKAPAN_MOBIL);
                break;
            }
            case R.id.inputKeadaanBodyMobil: {
                startActivityForResult(new Intent(UbahMobilAppActivity.this, ListKeadaanBodyAppActivity.class), RESULT_KEADAAN_MOBIL);
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
                updateDataMobil(RESULT_ID_MOBIL);
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
                fileUriList.clear();
                if (data.getClipData() != null) {
                    int totalItemSelected = data.getClipData().getItemCount();
                    for (int i = 0; i < totalItemSelected; i++) {
                        Uri fileUri = data.getClipData().getItemAt(i).getUri();
                        Bitmap bitmap = null;
                        try {
                            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(fileUri));
                            fileUriList.add(fileUri);
                            uploadFotoMobil(RESULT_ID_MOBIL);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (data.getData() != null) {
                    Uri fileUri = data.getData();
                    Bitmap bitmap = null;
                    try {
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(fileUri));
                        fileUriList.add(fileUri);
                        uploadFotoMobil(RESULT_ID_MOBIL);
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
                                Toast.makeText(UbahMobilAppActivity.this,
                                        "Upload foto mobil berhasil", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        fotoMobilAdapter.notifyDataSetChanged();
                        Toast.makeText(UbahMobilAppActivity.this,
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


    private void hapusFotoMobil(String idMobil, Integer position) {
        try {
            String fotoMobilUrl = fileImageList.get(position);
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
            StorageReference storageReference = firebaseStorage.getReferenceFromUrl(fileImageList.get(position));
            CollectionReference collectionReference = firebaseFirestore.collection("mobil");
            DocumentReference mobilDb = collectionReference.document(idMobil);

            storageReference.delete().addOnSuccessListener(unused -> {
                mobilDb.update("fotoMobil", FieldValue.arrayRemove(fotoMobilUrl)).addOnSuccessListener(unused1 -> {
                    fotoMobilAdapter.notifyItemRangeChanged(position, fotoMobilAdapter.getItemCount());
                    Toast.makeText(getApplicationContext(), "Foto Mobil berhasil dihapus", Toast.LENGTH_SHORT).show();
                });
            });

        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    private void fetchDataFoto(String idMobil) {
        try {
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            CollectionReference collectionReference = firebaseFirestore.collection("mobil");
            DocumentReference mobilDb = collectionReference.document(idMobil);

            mobilDb.addSnapshotListener((documentSnapshot, error) -> {
                if (error != null) {
                    Log.e("ErrorMsg", error.getMessage());
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (documentSnapshot == null && !documentSnapshot.exists()) {
                    return;
                }
                fileImageList.clear();
                NewMobil mobil = documentSnapshot.toObject(NewMobil.class);
                fileImageList.addAll(mobil.getFotoMobil());
                fotoMobilAdapter.notifyDataSetChanged();
            });

        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
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

    private void updateDataMobil(String idMobil) {
        try {
            Map<String, Object> mobil = new HashMap<>();
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

            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            CollectionReference collectionReference = firebaseFirestore.collection("mobil");
            DocumentReference mobilDb = collectionReference.document(idMobil);

            mobilDb.update(mobil).addOnSuccessListener(unused -> {
                Toast.makeText(getApplicationContext(), "Data mobil berhasil di ubah", Toast.LENGTH_SHORT).show();
                finish();
            });

        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}