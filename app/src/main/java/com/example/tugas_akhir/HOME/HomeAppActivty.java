package com.example.tugas_akhir.HOME;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugas_akhir.CLASS.Firestore;
import com.example.tugas_akhir.LOGIN.LoginActivity;
import com.example.tugas_akhir.PELANGGAN.ListPelangganAppActivity;
import com.example.tugas_akhir.R;
import com.example.tugas_akhir.REGISTER.RegisterAppActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class HomeAppActivty extends AppCompatActivity implements View.OnClickListener {

    private FirebaseFirestore firebaseFirestoredb;
    private Firestore firestore;
    private DocumentReference documentReference;
    private String getCurrentUserUid;
    private TextView txtViewNamaUser, txtViewUbahPelanggan, txtViewNamaPelanggan, txtViewAlamatPelanggan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);

        //text view
        txtViewNamaUser = (TextView) findViewById(R.id.textViewNamaUser);
        txtViewUbahPelanggan = (TextView) findViewById(R.id.txtUbahPelanggan);
        txtViewNamaPelanggan = (TextView) findViewById(R.id.txtNamaPelanggan);
        txtViewAlamatPelanggan = (TextView) findViewById(R.id.txtAlamatPelanggan);

        //fetch data
        fetchData();

        //set OnClick
        txtViewUbahPelanggan.setOnClickListener(this);

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
        }
    }

    public void fetchData() {
        // setting firebase Firestore
        firebaseFirestoredb = FirebaseFirestore.getInstance();
        firestore = new Firestore();
        getCurrentUserUid = firestore.getFirebaseAuth().getCurrentUser().getUid();
        documentReference = firebaseFirestoredb.collection("user").document(getCurrentUserUid);

        documentReference.addSnapshotListener((documentSnapshot, error) -> {
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
                String getIdPelanggan = documentSnapshot.getString("idPelanggan");
                // Do something with the retrieved data
                txtViewNamaUser.setText(namaUser);

                if (!TextUtils.isEmpty(getIdPelanggan)) {
                    DocumentReference pelangganDb = firebaseFirestoredb.collection("pelanggan").document(getIdPelanggan);
                    pelangganDb.get().addOnSuccessListener(documentSnapshotPelanggan -> {
                        if (documentSnapshotPelanggan == null && !documentSnapshotPelanggan.exists()) {
                            return;
                        }
                        // Document exists, retrieve the data
                        String namaPelanggan = documentSnapshotPelanggan.getString("namaPelanggan");
                        String alamatPelanggan = documentSnapshotPelanggan.getString("alamatPelanggan");
                        // Do something with the retrieved data
                        txtViewNamaPelanggan.setText(namaPelanggan);
                        txtViewAlamatPelanggan.setText(alamatPelanggan);
                    }).addOnFailureListener(e -> {
                        Log.e("ErrorMsg", e.getMessage());
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                }

            } catch (Exception e) {
                Log.e("ErrorMsg", e.getMessage());
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}