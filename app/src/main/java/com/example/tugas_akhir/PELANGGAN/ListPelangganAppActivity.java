package com.example.tugas_akhir.PELANGGAN;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.tugas_akhir.CLASS.Firestore;
import com.example.tugas_akhir.CLASS.Pelanggan;
import com.example.tugas_akhir.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListPelangganAppActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private Firestore firestore;
    private CollectionReference pelangganDb;

    private RecyclerView recyclerViewPelanggan;
    private ArrayList<Pelanggan> pelanggans;
    private ListPelangganAdapter listPelangganAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pelanggan_app);

        //ReclyerView
        recyclerViewPelanggan = (RecyclerView) findViewById(R.id.recycler_pelanggan);
        recyclerViewPelanggan.setLayoutManager(new LinearLayoutManager(this));

        // new Array List
        pelanggans = new ArrayList<>();

        //fetch data
        fetchData();

    }

    public void fetchData() {
        try {
            firebaseFirestore = FirebaseFirestore.getInstance();
            firestore = new Firestore();
            pelangganDb = firebaseFirestore.collection("pelanggan");

            pelangganDb.get().addOnSuccessListener(queryDocumentSnapshots -> {
                pelanggans.clear();
                if (queryDocumentSnapshots.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Tidak ada data pelanggan", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Pelanggan dataPelanggan = documentSnapshot.toObject(Pelanggan.class);
                    pelanggans.add(dataPelanggan);
                }
                listPelangganAdapter = new ListPelangganAdapter(ListPelangganAppActivity.this, pelanggans);
                recyclerViewPelanggan.setAdapter(listPelangganAdapter);

            }).addOnFailureListener(e -> {
                Log.e("ErrorMsg", e.getMessage());
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}