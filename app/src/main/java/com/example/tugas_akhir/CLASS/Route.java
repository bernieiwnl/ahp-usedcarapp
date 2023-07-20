package com.example.tugas_akhir.CLASS;

import android.content.Context;
import android.content.Intent;

public class Route {

    public Route(){

    }

    public void routeTo(Context from, Class to){
        from.startActivity(new Intent(from, to));
    }
}
