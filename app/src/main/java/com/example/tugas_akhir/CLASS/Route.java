package com.example.tugas_akhir.CLASS;

import android.content.Context;
import android.content.Intent;

import com.example.tugas_akhir.HOME.HomeActivity;
import com.example.tugas_akhir.PROFILE.UbahProfileActivity;

public class Route {

    public Route(){

    }

    public void routeTo(Context from, Class to){
        from.startActivity(new Intent(from, to));
    }
}
