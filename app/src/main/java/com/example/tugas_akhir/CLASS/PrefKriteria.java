package com.example.tugas_akhir.CLASS;

import java.io.Serializable;

public class PrefKriteria implements Serializable {
    private String nama_kriteria;
    private Double bobot_kriteria;

    public PrefKriteria(){

    }

    public PrefKriteria(String nama, Double bobot){
        this.setNama_kriteria(nama);
        this.setBobot_kriteria(bobot);
    }

    public String getNama_kriteria() {
        return nama_kriteria;
    }

    public void setNama_kriteria(String nama_kriteria) {
        this.nama_kriteria = nama_kriteria;
    }

    public Double getBobot_kriteria() {
        return bobot_kriteria;
    }

    public void setBobot_kriteria(Double bobot_kriteria) {
        this.bobot_kriteria = bobot_kriteria;
    }
}
