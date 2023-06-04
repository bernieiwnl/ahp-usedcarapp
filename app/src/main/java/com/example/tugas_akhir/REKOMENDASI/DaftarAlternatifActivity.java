package com.example.tugas_akhir.REKOMENDASI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.RecoverySystem;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.tugas_akhir.ADAPTER.DaftarMobilAdapter;
import com.example.tugas_akhir.ADAPTER.DaftarMobilUserAdapter;
import com.example.tugas_akhir.CLASS.Firestore;
import com.example.tugas_akhir.CLASS.Mobil;
import com.example.tugas_akhir.CLASS.Route;
import com.example.tugas_akhir.PROFILE.DaftarMobilUserActivity;
import com.example.tugas_akhir.R;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DaftarAlternatifActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_Search;
    private RecyclerView recyclerView_alternatif;
    private Firestore firestore;
    private Route route;
    private ArrayList<Mobil> alternatifs;
    private DaftarMobilAdapter daftarMobilAdapter;
    private ImageView imageView_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_alternatif);
        firestore = new Firestore();
        route = new Route();
        alternatifs = new ArrayList<>();
        imageView_back = (ImageView) findViewById(R.id.imgBackToAddData);
        editText_Search = (EditText) findViewById(R.id.editText_searchBar);
        recyclerView_alternatif = (RecyclerView) findViewById(R.id.recycler_mobil);
        recyclerView_alternatif.setLayoutManager(new LinearLayoutManager(this));
        firestore.getFirebaseFirestore().collection("mobil").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                alternatifs.clear();
                if(!value.isEmpty()){
                    for(QueryDocumentSnapshot doc : value){
                        Mobil data = doc.toObject(Mobil.class);
                        alternatifs.add(data);
                    }
                    daftarMobilAdapter = new DaftarMobilAdapter(alternatifs, DaftarAlternatifActivity.this);
                    recyclerView_alternatif.setAdapter(daftarMobilAdapter);
                }
            }
        });
        editText_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
        imageView_back.setOnClickListener(this);
    }
    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<Mobil> data = new ArrayList<>();
        data.clear();
        //looping through existing elements
        for (Mobil c : alternatifs) {
            //if the existing elements contains the search input
            if (c.getMerk().toLowerCase().contains(text.toLowerCase()) ||
                    c.getKapasitas_mesin().toLowerCase().contains(text.toLowerCase()) ||
                    c.getModel().toLowerCase().contains(text.toLowerCase()) ||
                    c.getTahun().toLowerCase().contains(text.toLowerCase()) ||
                    c.getTipe().toLowerCase().contains(text.toLowerCase()) ||
                    c.getTransmisi().toLowerCase().contains(text.toLowerCase()) ) {
                //adding the element to filtered list
                data.add(c);
            }
            daftarMobilAdapter = new DaftarMobilAdapter(alternatifs, DaftarAlternatifActivity.this);
            recyclerView_alternatif.setAdapter(daftarMobilAdapter);
        }
    }
    @Override
    public void onClick(View v) {

    }
}