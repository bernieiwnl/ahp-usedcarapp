package com.example.tugas_akhir.HOME;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugas_akhir.CLASS.Firestore;
import com.example.tugas_akhir.CLASS.PrefKriteria;
import com.example.tugas_akhir.CLASS.Preferensi;
import com.example.tugas_akhir.LOGIN.LoginActivity;
import com.example.tugas_akhir.MOBIL.ListDaftarMobilAppActivity;
import com.example.tugas_akhir.MOBIL.TambahMobilAppActivity;
import com.example.tugas_akhir.MOBIL.UbahMobilAppActivity;
import com.example.tugas_akhir.PELANGGAN.ADAPTER.ListUbahPelangganAdapter;
import com.example.tugas_akhir.PELANGGAN.ListDaftarPelangganAppActivity;
import com.example.tugas_akhir.PELANGGAN.ListPelangganAppActivity;
import com.example.tugas_akhir.PELANGGAN.PREFERENSI.UbahPreferensiPelangganAppActivity;
import com.example.tugas_akhir.R;
import com.example.tugas_akhir.REGISTER.RegisterAppActivity;
import com.example.tugas_akhir.SPK.DaftarAlternatiflAppActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.Collections;

public class HomeAppActivty extends AppCompatActivity implements View.OnClickListener {

    private FirebaseFirestore firebaseFirestoredb;
    private Button btnPreferensiPelanggan;
    private Firestore firestore;
    private DocumentReference userDb;
    private String getCurrentUserUid, getIdPelanggan;
    private TextView txtViewNamaUser, txtViewUbahPelanggan, txtViewNamaPelanggan, txtViewAlamatPelanggan;
    private CardView cardViewDaftarPelanggan, cardViewDaftarMobil, cardViewRekomendasi;
    private ArrayList<PrefKriteria> prefKriterias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);

        //text view
        txtViewNamaUser = (TextView) findViewById(R.id.textViewNamaUser);
        txtViewUbahPelanggan = (TextView) findViewById(R.id.txtUbahPelanggan);
        txtViewNamaPelanggan = (TextView) findViewById(R.id.txtNamaPelanggan);
        txtViewAlamatPelanggan = (TextView) findViewById(R.id.txtAlamatPelanggan);

        //cardview
        cardViewDaftarPelanggan = (CardView) findViewById(R.id.daftarPelangganCardView);
        cardViewDaftarMobil = (CardView) findViewById(R.id.daftarMobilCardView);
        cardViewRekomendasi = (CardView) findViewById(R.id.rekomendasiCardView);

        //button
        btnPreferensiPelanggan = (Button) findViewById(R.id.btnPreferensi);

        //fetch data
        fetchData();

        //set OnClick
        txtViewUbahPelanggan.setOnClickListener(this);
        btnPreferensiPelanggan.setOnClickListener(this);
        cardViewDaftarPelanggan.setOnClickListener(this);
        cardViewDaftarMobil.setOnClickListener(this);
        cardViewRekomendasi.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtUbahPelanggan: {
                try {
                    startActivity(new Intent(HomeAppActivty.this, ListPelangganAppActivity.class));
                } catch (Exception e) {
                    Log.e("ErrorMsg", e.getMessage());
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.btnPreferensi: {
                try {
                    //check jika user baru belum memili pelanggan
                    if (TextUtils.isEmpty(getIdPelanggan)) {
                        Toast.makeText(HomeAppActivty.this, "Pilih Pelanggan terlebih dahulu", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Intent i = new Intent(HomeAppActivty.this, UbahPreferensiPelangganAppActivity.class);
                    i.putExtra("idPelanggan", getIdPelanggan);
                    startActivityForResult(i, 1);
                } catch (Exception e) {
                    Log.e("ErrorMsg", e.getMessage());
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.daftarMobilCardView: {
                try {
                    startActivity(new Intent(HomeAppActivty.this, ListDaftarMobilAppActivity.class));
                } catch (Exception e) {
                    Log.e("ErrorMsg", e.getMessage());
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.rekomendasiCardView: {
                try {
                    //check jika user baru belum memili pelanggan
                    if (TextUtils.isEmpty(getIdPelanggan)) {
                        Toast.makeText(HomeAppActivty.this, "Pilih Pelanggan terlebih dahulu", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Intent intent = new Intent(HomeAppActivty.this, DaftarAlternatiflAppActivity.class);
                    intent.putExtra("idPelanggan", getIdPelanggan);
                    startActivityForResult(intent, 1);

                } catch (Exception e) {
                    Log.e("ErrorMsg", e.getMessage());
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.daftarPelangganCardView: {
                try {
                    startActivity(new Intent(HomeAppActivty.this, ListPelangganAppActivity.class));
                } catch (Exception e) {
                    Log.e("ErrorMsg", e.getMessage());
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    public void fetchData() {
        // setting firebase Firestore
        firebaseFirestoredb = FirebaseFirestore.getInstance();
        firestore = new Firestore();
        getCurrentUserUid = firestore.getFirebaseAuth().getCurrentUser().getUid();
        userDb = firebaseFirestoredb.collection("user").document(getCurrentUserUid);

        userDb.addSnapshotListener((documentSnapshot, error) -> {
            try {
                if (error != null) {
                    Log.e("ErrorMsg", error.getMessage());
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (documentSnapshot == null && !documentSnapshot.exists()) {
                    return;
                }
                // Document exists, retrieve the data
                String namaUser = documentSnapshot.getString("namaUser");
                getIdPelanggan = documentSnapshot.getString("idPelanggan");

                // Do something with the retrieved data
                txtViewNamaUser.setText(namaUser);


                if (!TextUtils.isEmpty(getIdPelanggan)) {
                    DocumentReference pelangganDb = firebaseFirestoredb.collection("pelanggan").document(getIdPelanggan);
                    pelangganDb.addSnapshotListener((documentSnapshotPelanggan, error1) -> {
                        if (documentSnapshotPelanggan == null && !documentSnapshotPelanggan.exists()) {
                            return;
                        }
                        // Document exists, retrieve the data
                        String namaPelanggan = documentSnapshotPelanggan.getString("namaPelanggan");
                        String alamatPelanggan = documentSnapshotPelanggan.getString("alamatPelanggan");
                        // Do something with the retrieved data
                        txtViewNamaPelanggan.setText(namaPelanggan);
                        txtViewAlamatPelanggan.setText(alamatPelanggan);
                    });
                }

            } catch (Exception e) {
                Log.e("ErrorMsg", e.getMessage());
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}