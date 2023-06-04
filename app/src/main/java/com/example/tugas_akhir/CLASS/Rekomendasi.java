package com.example.tugas_akhir.CLASS;

import java.io.Serializable;

public class Rekomendasi implements Serializable {

    private Mobil mobil;
    private double nilaiBobot;

    public Rekomendasi(Mobil mobil, double nilaiBobot) {
        this.mobil = mobil;
        this.nilaiBobot = nilaiBobot;
    }

    public Mobil getMobil() {
        return mobil;
    }

    public void setMobil(Mobil mobil) {
        this.mobil = mobil;
    }

    public double getNilaiBobot() {
        return nilaiBobot;
    }

    public void setNilaiBobot(double nilaiBobot) {
        this.nilaiBobot = nilaiBobot;
    }
}
