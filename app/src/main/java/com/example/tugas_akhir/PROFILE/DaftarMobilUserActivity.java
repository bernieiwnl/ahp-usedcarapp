package com.example.tugas_akhir.PROFILE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.tugas_akhir.ADAPTER.DaftarMobilUserAdapter;
import com.example.tugas_akhir.CLASS.Mobil;
import com.example.tugas_akhir.R;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DaftarMobilUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_search;
    private RecyclerView recyclerView_mobil;
    private FirebaseFirestore firebaseFirestore;
    private ArrayList<Mobil> mobils;
    private DaftarMobilUserAdapter daftarMobilUserAdapter;
    private ImageView imgBackToAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_mobil_user);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#3F51B5"));
        }

        imgBackToAddData = (ImageView) findViewById(R.id.imgBackToAddData);
        editText_search = (EditText) findViewById(R.id.editText_searchBar);

        imgBackToAddData.setOnClickListener(this);

        recyclerView_mobil = (RecyclerView) findViewById(R.id.recycler_mobil);

        recyclerView_mobil.setLayoutManager(new LinearLayoutManager(this));
        mobils = new ArrayList<>();

        firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("mobil").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                mobils.clear();
                if(!value.isEmpty()){
                    for(QueryDocumentSnapshot doc : value){
                        Mobil data = doc.toObject(Mobil.class);
                        mobils.add(data);
                    }
                    daftarMobilUserAdapter = new DaftarMobilUserAdapter(mobils, DaftarMobilUserActivity.this);
                    recyclerView_mobil.setAdapter(daftarMobilUserAdapter);
                }
            }
        });


        editText_search.addTextChangedListener(new TextWatcher() {
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
    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<Mobil> data = new ArrayList<>();
        data.clear();
        //looping through existing elements
        for (Mobil c : mobils) {
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

            daftarMobilUserAdapter = new DaftarMobilUserAdapter(data, DaftarMobilUserActivity.this);
            recyclerView_mobil.setAdapter(daftarMobilUserAdapter);
        }
    }

    @Override
    public void onClick(View v) {

    }
}