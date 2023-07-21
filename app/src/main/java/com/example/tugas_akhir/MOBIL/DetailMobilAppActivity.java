package com.example.tugas_akhir.MOBIL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugas_akhir.ADAPTER.FotoMobilAdapter;
import com.example.tugas_akhir.MOBIL.CLASS.NewMobil;
import com.example.tugas_akhir.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Collections;

public class DetailMobilAppActivity extends AppCompatActivity {

    private TextView textViewInputNamaMobil, textViewInputTipeMobil, textViewInputTransmisiMobil, textViewInputTahunMobil, textViewInputKilometerMobil, textViewInputWarnaMobil, textViewInputKapasitasMesinMobil, textViewInputHargaMobil, textViewInputKondisiMesinMobil, textViewInputServiceRecordMobil, textViewInputKondisiInteriorMobil, textViewInputKeadaanBodyMobil, textViewInputKelengkapanMobil;
    private SliderView sliderViewImageMobil;
    private FotoMobilAdapter fotoMobilAdapter;
    private ArrayList<String> fotoMobils;
    private ImageView imageViewBack;
    private String GET_ID_MOBIL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mobil_app);

//        TextView
        textViewInputNamaMobil = (TextView) findViewById(R.id.textViewNamaMerkMobil);
        textViewInputTipeMobil = (TextView) findViewById(R.id.textViewTipeMobil);
        textViewInputTransmisiMobil = (TextView) findViewById(R.id.textViewTransmisiMobil);
        textViewInputTahunMobil = (TextView) findViewById(R.id.textViewTahunMobil);
        textViewInputKilometerMobil = (TextView) findViewById(R.id.textViewKilometerMobil);
        textViewInputWarnaMobil = (TextView) findViewById(R.id.textViewWarnaMobil);
        textViewInputKapasitasMesinMobil = (TextView) findViewById(R.id.textViewKapasitasMesinMobil);
        textViewInputHargaMobil = (TextView) findViewById(R.id.textViewHargaMobil);
        textViewInputKondisiMesinMobil = (TextView) findViewById(R.id.textViewKondisiMesin);
        textViewInputServiceRecordMobil = (TextView) findViewById(R.id.textViewServiceRecordMobil);
        textViewInputKondisiInteriorMobil = (TextView) findViewById(R.id.textViewKondisiInterior);
        textViewInputKeadaanBodyMobil = (TextView) findViewById(R.id.textViewKeadaanBody);
        textViewInputKelengkapanMobil = (TextView) findViewById(R.id.textViewKelengkapanMobil);

//        SliderView
        sliderViewImageMobil = (SliderView) findViewById(R.id.imgSliderMobil);

//        Imageview
        imageViewBack = (ImageView) findViewById(R.id.imageView_back);

//        Inisialisasi Array
        fotoMobils = new ArrayList<>();

//        Get intent ID Mobil;
        GET_ID_MOBIL = getIntent().getStringExtra("idMobil");

//        Method get data mobil
        fetchData(GET_ID_MOBIL);
    }

    private void fetchData(String GET_ID_MOBIL) {
        try {
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            CollectionReference collectionReference = firebaseFirestore.collection("mobil");
            DocumentReference mobilDB = collectionReference.document(GET_ID_MOBIL);

            mobilDB.get().addOnSuccessListener(documentSnapshot -> {
                if(!documentSnapshot.exists() || documentSnapshot == null){
                    return;
                }
                NewMobil newMobil = documentSnapshot.toObject(NewMobil.class);
                textViewInputNamaMobil.setText(newMobil.getNamaMerkMobil());
                textViewInputTipeMobil.setText(newMobil.getTipeMobil());
                textViewInputTransmisiMobil.setText(newMobil.getTransmisiMobil());
                textViewInputTahunMobil.setText(newMobil.getTahunMobil().toString());
                textViewInputKilometerMobil.setText(newMobil.getKilometerMobil().toString());
                textViewInputWarnaMobil.setText(newMobil.getWarnaMobil());
                textViewInputKapasitasMesinMobil.setText(newMobil.getKapasitasMobil().toString());
                textViewInputHargaMobil.setText(newMobil.getHargaMobil().toString());
                textViewInputKondisiMesinMobil.setText(newMobil.getKondisiMesinMobil().toString());
                textViewInputServiceRecordMobil.setText(newMobil.getServiceRecordMobil());
                textViewInputKondisiInteriorMobil.setText(newMobil.getKondisiInteriorMobil());
                textViewInputKeadaanBodyMobil.setText(newMobil.getKeadaanMobil());
                textViewInputKelengkapanMobil.setText(newMobil.getKelengkapanMobil());

            }).addOnFailureListener(e -> {
                Log.i("ErrorMsg", e.getMessage());
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        } catch (Exception e) {
            Log.i("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}