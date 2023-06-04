package com.example.tugas_akhir.PROFILE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tugas_akhir.HOME.HomeActivity;
import com.example.tugas_akhir.R;

public class UbahProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView icon_backHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_profile);
        icon_backHome = (ImageView) findViewById(R.id.icon_backToHome);
        icon_backHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_backToHome:
                startActivity(new Intent(UbahProfileActivity.this, HomeActivity.class));
                break;

        }
    }
}