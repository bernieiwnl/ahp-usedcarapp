package com.example.tugas_akhir.CLASS;

public class TipeMobil {
    private String idMobil;
    private String kapasitas_mesin;
    private String merk;
    private String model;
    private String tahun;
    private String tipe;
    private String transmisi;

    public String getKapasitas_mesin() {
        return kapasitas_mesin;
    }

    public void setKapasitas_mesin(String kapasitas_mesin) {
        this.kapasitas_mesin = kapasitas_mesin;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getTransmisi() {
        return transmisi;
    }

    public void setTransmisi(String transmisi) {
        this.transmisi = transmisi;
    }

    public String getIdMobil() {
        return idMobil;
    }

    public void setIdMobil(String idMobil) {
        this.idMobil = idMobil;
    }

    public TipeMobil(){
        this.setIdMobil("");
        this.setKapasitas_mesin("");
        this.setMerk("");
        this.setModel("");
        this.setTahun("");
        this.setTipe("");
        this.setTransmisi("");
    }
}
