package com.example.tugas_akhir.HOME;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tugas_akhir.ADDATA_MOBIL.AddDataMobilActivity;
import com.example.tugas_akhir.CLASS.Firestore;
import com.example.tugas_akhir.CLASS.Route;
import com.example.tugas_akhir.CLASS.User;
import com.example.tugas_akhir.LOGIN.LoginActivity;
import com.example.tugas_akhir.PREFERENSI.UbahKriteriaActivity;
import com.example.tugas_akhir.PROFILE.DaftarMobilUserActivity;
import com.example.tugas_akhir.PROFILE.UbahProfileActivity;
import com.example.tugas_akhir.R;
import com.example.tugas_akhir.REKOMENDASI.PemilihanAlternatifActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView rekomendasiCardview, daftarMobilCardview, tambahMobilCardView, prioritasCardView, profileCardView;
    private Route route;
    private Firestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firestore = new Firestore();
        route = new Route();
        rekomendasiCardview = (CardView) findViewById(R.id.rekomendasiCardView);
        daftarMobilCardview = (CardView) findViewById(R.id.daftarMobilCardView);
        tambahMobilCardView = (CardView) findViewById(R.id.tambahMobilCardView);
        prioritasCardView = (CardView) findViewById(R.id.prioritasKriteriaCardView);
        profileCardView = (CardView) findViewById(R.id.profileCardView);
        if (firestore.getFirebaseAuth().getCurrentUser() == null) {
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            this.finish();
        }
        rekomendasiCardview.setOnClickListener(this);
        daftarMobilCardview.setOnClickListener(this);
        tambahMobilCardView.setOnClickListener(this);
        prioritasCardView.setOnClickListener(this);
        profileCardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rekomendasiCardView:
                route.routeTo(this, PemilihanAlternatifActivity.class);
                break;
            case R.id.daftarMobilCardView:
                route.routeTo(this, DaftarMobilUserActivity.class);
                break;
            case R.id.tambahMobilCardView:
                route.routeTo(this, AddDataMobilActivity.class);
                break;
            case R.id.prioritasKriteriaCardView:
                route.routeTo(this, UbahKriteriaActivity.class);
                break;
            case R.id.profileCardView:
                route.routeTo(this, UbahProfileActivity.class);
        }
    }
}