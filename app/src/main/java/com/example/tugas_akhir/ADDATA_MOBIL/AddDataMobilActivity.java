package com.example.tugas_akhir.ADDATA_MOBIL;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugas_akhir.ADAPTER.FotoMobilAdapter;
import com.example.tugas_akhir.MainActivity;
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

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddDataMobilActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView listFotoMobil;
    private FotoMobilAdapter adapterFoto;
    private ArrayList<Bitmap> fileImageList;
    private ArrayList<Uri> fileUriList;
    private CardView cardViewTambahFotoMobil;
    private CardView cardViewDetail;
    private CardView cardViewBerkas;
    private CardView cardViewKonFisik;
    private CardView cardViewKonMesin;
    private CardView cardViewKonUnderSteel;
    private TextView txtDetailMobil;
    private TextView txtBerkas;
    private TextView txtKonFisik;
    private TextView txtKonMesin;
    private TextView txtKonUnderSteel;
    private TextInputEditText txtInputHarga;
    private TextInputEditText txtInputJarakTempuh;
    private TextInputEditText txtInputKeterangan;
    private RadioButton rbPernahService;
    private RadioButton rbTdkPernahService;
    private RadioGroup rgServiceRecord;
    private Button btnTambahData;

    private FirebaseFirestore firebaseFirestore;
    private StorageReference storageReference;
    private StorageTask storageTask;

    private String txtHarga;
    private String txtJarakTempuh;
    private String txtServiceRecord;
    private String txtKeterangan;

    //detail mobil
    private String merk = "";
    private String model = "";
    private String tahun = "";
    private String transmisi = "";
    private String tipe = "";
    private String kapasitas = "";

    //kelengkapan pajak;
    private Integer nilaiPajak = 0;
    private String desPajak = "";

    private Integer nilaiKelengkapanBerkas = 0;
    private String desKelengkapanBerkas = "";

    //kondisi Fisik
    private Integer nilaiBody = 0;
    private String desBody = "";

    private Integer nilaiCat = 0;
    private String desCat = "";

    private Integer nilaiBan = 0;
    private String desBan = "";

    //kondisi Mesin
    private Integer nilaiKelistrikan = 0;
    private String desKelistrikan = "";

    private Integer nilaiPembakaran = 0;
    private String desPembakaran = "";

    private Integer nilaiRadiator = 0;
    private String desRadiator = "";

    //kondisi Understeel
    private Integer nilaiTransmisi = 0;
    private String desTransmisi = "";

    private Integer nilaiPowerSteering = 0;
    private String desPowerSteering = "";

    private Integer nilaiShockbreaker = 0;
    private String desShockbreaker = "";

    private Integer nilaiBaljoin = 0;
    private String desBaljoin = "";

    private Integer nilaiRacksteer = 0;
    private String desRacksteer = "";

    private Integer nilaiTerod = 0;
    private String desTerod  = "";

    private static final int RESULT_LOAD_IMAGE1 = 1;
    private static final int RESULT_KELENGKAPAN_BERKAS = 2;
    private static final int RESULT_DETAIL_MOBIL = 3;
    private static final int RESULT_KONDISI_FISIK = 4;
    private static final int RESULT_KONDISI_MESIN = 5;
    private static final int RESULT_KONDISI_UNDERSTEEL = 6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data_mobil);

        listFotoMobil = (RecyclerView) findViewById(R.id.photo_layout);
        cardViewTambahFotoMobil = (CardView) findViewById(R.id.cardTambahFotoMobil);
        cardViewDetail = (CardView) findViewById(R.id.cardDetailMobil);
        cardViewBerkas = (CardView) findViewById(R.id.cardBerkas);
        cardViewKonFisik = (CardView) findViewById(R.id.cardKondisiFisik);
        cardViewKonMesin = (CardView) findViewById(R.id.cardKondisiMesin);
        cardViewKonUnderSteel = (CardView) findViewById(R.id.cardKondisiUndersteel);
        txtKonFisik = (TextView) findViewById(R.id.txtKondisiFisik);
        txtKonMesin = (TextView) findViewById(R.id.txtKondisiMesin);
        txtKonUnderSteel = (TextView) findViewById(R.id.txtKondisiUndersteel);
        txtDetailMobil = (TextView) findViewById(R.id.txtDetailMobil);
        txtBerkas = (TextView) findViewById(R.id.txtBerkas);
        txtInputHarga = (TextInputEditText) findViewById(R.id.inputTextHarga);
        txtInputJarakTempuh = (TextInputEditText) findViewById(R.id.inputTextJarakTempuh);
        txtInputKeterangan = (TextInputEditText) findViewById(R.id.inputKeterangan);
        rbPernahService = (RadioButton) findViewById(R.id.rbSRPernah);
        rbTdkPernahService = (RadioButton) findViewById(R.id.rbSRTidakPernah);
        rgServiceRecord = (RadioGroup) findViewById(R.id.rdgSeviceRecord);
        btnTambahData = (Button) findViewById(R.id.btnTambahData);

        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        //set onclick
        cardViewTambahFotoMobil.setOnClickListener(this);
        cardViewDetail.setOnClickListener(this);
        cardViewBerkas.setOnClickListener(this);
        cardViewKonFisik.setOnClickListener(this);
        cardViewKonMesin.setOnClickListener(this);
        cardViewKonUnderSteel.setOnClickListener(this);
        btnTambahData.setOnClickListener(this);

        //untuk meyimpan sementara gambar yang diupload
        fileImageList = new ArrayList<>();
        fileUriList = new ArrayList<>();

        //untuk render tampilan grid foto sebanyak 2
        listFotoMobil.setLayoutManager(new GridLayoutManager(this , 2));
        listFotoMobil.setHasFixedSize(true);

        //recyclerView
        adapterFoto = new FotoMobilAdapter(fileImageList, AddDataMobilActivity.this);
        listFotoMobil.setAdapter(adapterFoto);

        LocalBroadcastManager.getInstance(this).registerReceiver(hapusFoto,
                new IntentFilter("hapus-foto"));

        rgServiceRecord.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbSRPernah:
                            txtServiceRecord = "Pernah Service Record";
                        break;
                    case R.id.rbSRTidakPernah:
                            txtServiceRecord = "Tidak Pernah Service Record";
                        break;
                }
            }
        });
    }

    public BroadcastReceiver hapusFoto = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            Integer position = intent.getIntExtra("position", 0);
            fileImageList.remove(intent.getIntExtra("position", 0));
            adapterFoto.notifyDataSetChanged();
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cardTambahFotoMobil:
                openFoto();
                break;
            case R.id.cardDetailMobil:
                startActivityForResult(new Intent(AddDataMobilActivity.this, AddDetailMobilActivity.class), RESULT_DETAIL_MOBIL);
                break;
            case R.id.cardBerkas:
                startActivityForResult(new Intent(AddDataMobilActivity.this, KelengkapanBerkas_PajakActivity.class), RESULT_KELENGKAPAN_BERKAS);
                break;
            case R.id.cardKondisiFisik:
                startActivityForResult(new Intent(AddDataMobilActivity.this, KondisiFisikActivity.class), RESULT_KONDISI_FISIK);
                break;
            case R.id.cardKondisiMesin:
                startActivityForResult(new Intent(AddDataMobilActivity.this, KondisiMesinActivity.class), RESULT_KONDISI_MESIN);
                break;
            case R.id.cardKondisiUndersteel:
                startActivityForResult(new Intent(AddDataMobilActivity.this, KondisiUnderSteelActivity.class), RESULT_KONDISI_UNDERSTEEL);
                break;
            case R.id.btnTambahData:
                simpanData();
                break;
        }
    }

    public void uploadFoto(final String id) {

        if(fileUriList.isEmpty()){

        }
        else{
            //call time
            long current = Calendar.getInstance().getTimeInMillis();
            Integer data = 0;

            for (int i = 0; i < fileUriList.size(); i++) {
                String fileName = getFileName(fileUriList.get(i));
                final StorageReference fileToUpload = storageReference.child("foto_mobil").child(fileName + current + "_" + id + "_" + i);
                storageTask = fileToUpload.putFile(fileUriList.get(i));
                storageTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return fileToUpload.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                            String mUri = downloadUri.toString();
                            firebaseFirestore.collection("mobil").document(id).update("fotoMobil", FieldValue.arrayUnion(mUri)).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    adapterFoto.notifyDataSetChanged();
                                    Toast.makeText(AddDataMobilActivity.this,
                                            "SUKSES!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else{
                            adapterFoto.notifyDataSetChanged();
                            Toast.makeText(AddDataMobilActivity.this,
                                    "Gagal!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                data++;
            }
        }
    }

    //method untuk mengambil foto di folder(galeri / storage)
    protected  void openFoto(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE1);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE1 && resultCode == RESULT_OK){
            if(data.getClipData() != null){
                listFotoMobil.setVisibility(View.VISIBLE);
                int totalItemSelected = data.getClipData().getItemCount();
                for(int i = 0; i < totalItemSelected; i++){
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
            }
            else if(data.getData() != null){
                Uri fileUri = data.getData();
                Bitmap bitmap = null;
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(fileUri));
                    fileImageList.add(bitmap);
                    fileUriList.add(fileUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                adapterFoto.notifyDataSetChanged();
            }
        }
        else if(requestCode == 2 && resultCode == RESULT_OK){

            desPajak = data.getStringExtra("deskripsi_pajak");
            nilaiPajak = data.getIntExtra("nilai_pajak", 0);
            desKelengkapanBerkas = data.getStringExtra("deskripsi_kelengkapan_berkas");
            nilaiKelengkapanBerkas = data.getIntExtra("nilai_kelengkapan_berkas", 0);
            txtBerkas.setText( desKelengkapanBerkas+ " ," + desPajak);

        }
        else if(requestCode == 3 && resultCode == RESULT_OK){
            merk = data.getStringExtra("merk");
            Log.d("MERK", merk);
            model = data.getStringExtra("model");
            tahun = data.getStringExtra("tahun");
            transmisi = data.getStringExtra("transmisi");
            tipe = data.getStringExtra("tipe");
            kapasitas = data.getStringExtra("kapasitas");
            txtDetailMobil.setText(merk + " " + model + " " + " " + tipe + " " + tahun + " " + transmisi + " " + kapasitas);
        }
        else if(requestCode == 4 && resultCode == RESULT_OK){

            desBody = data.getStringExtra("deskripsi_body");
            nilaiBody = data.getIntExtra("nilai_body", 0);
            desCat = data.getStringExtra("deskripsi_cat");
            nilaiCat = data.getIntExtra("nilai_cat", 0);
            desBan = data.getStringExtra("deskripsi_ban");
            nilaiBan = data.getIntExtra("nilai_ban",0);
            txtKonFisik.setText( desBody+ " ," + desCat + " ," + desBan);
        }
        else if(requestCode == 5 && resultCode == RESULT_OK){

            desKelistrikan = data.getStringExtra("deskripsi_kelistrikan");
            nilaiKelistrikan = data.getIntExtra("nilai_kelistrikan", 0);
            desPembakaran = data.getStringExtra("deskripsi_pembakaran");
            nilaiPembakaran = data.getIntExtra("nilai_pembakaran", 0);
            desRadiator = data.getStringExtra("deskripsi_radiator");
            nilaiRadiator = data.getIntExtra("nilai_radiator",0);
            txtKonMesin.setText( desKelistrikan+ " ," + desPembakaran + " ," + desRadiator);
        }
        else if(requestCode == 6 && resultCode == RESULT_OK){

            desTransmisi = data.getStringExtra("deskripsi_transmisi");
            nilaiTransmisi = data.getIntExtra("nilai_transmisi", 0);
            desPowerSteering = data.getStringExtra("deskripsi_powersteering");
            nilaiPowerSteering = data.getIntExtra("nilai_powersteering", 0);
            desShockbreaker = data.getStringExtra("deskripsi_shockbreaker");
            nilaiShockbreaker = data.getIntExtra("nilai_shockbreaker",0);
            desBaljoin = data.getStringExtra("deskripsi_baljoin");
            nilaiBaljoin = data.getIntExtra("nilai_baljoin",0);
            desRacksteer = data.getStringExtra("deskripsi_racksteer");
            nilaiRacksteer = data.getIntExtra("nilai_racksteer",0);
            desTerod = data.getStringExtra("deskripsi_terod");
            nilaiTerod = data.getIntExtra("nilai_terod",0);
            txtKonUnderSteel.setText( desTransmisi+ " ," + desShockbreaker + " ," + desPowerSteering+ " ," + desRacksteer+ " ," + desTerod+ " ," + desBaljoin);
        }
    }

    //ambil nama pada data foto yang dipilih
    public String getFileName(Uri uri) {
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

    public void simpanData(){
        txtHarga = txtInputHarga.getText().toString().trim();
        txtKeterangan = txtInputKeterangan.getText().toString().trim();
        txtJarakTempuh = txtInputJarakTempuh.getText().toString().trim();

        Map<String, Object> mobil = new HashMap<>();
        mobil.put("idMobil", "");
        mobil.put("kapasitas_mesin", kapasitas);
        mobil.put("merk", merk);
        mobil.put("model", model);
        mobil.put("tahun",tahun);
        mobil.put("tipe",tipe);
        mobil.put("transmisi", transmisi);
        mobil.put("kelengkapanBerkas", desKelengkapanBerkas);
        mobil.put("pajak", desPajak);
        mobil.put("harga", txtHarga);
        mobil.put("jarakTempuh", txtJarakTempuh);
        mobil.put("keterangan", txtKeterangan);
        mobil.put("serviceRecord", txtServiceRecord);
        mobil.put("kondisiKelistrikan", desKelistrikan);
        mobil.put("kondisiPembakaran", desPembakaran);
        mobil.put("kondisiRadiator", desRadiator);
        mobil.put("kondisiBody", desBody);
        mobil.put("kondisiCat", desCat);
        mobil.put("kondisiBan", desBan);
        mobil.put("kondisiTransmisi", desTransmisi);
        mobil.put("kondisiShockbreaker", desShockbreaker);
        mobil.put("kondisiPowersteering", desPowerSteering);
        mobil.put("kondisiRacksteer", desRacksteer);
        mobil.put("kondisiTerod", desTerod);
        mobil.put("kondisiBaljoin", desBaljoin);

        firebaseFirestore.collection("mobil").add(mobil).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                firebaseFirestore.collection("mobil").document(task.getResult().getId()).update("idMobil", task.getResult().getId());
                uploadFoto(task.getResult().getId());
            }
        });
    }
}