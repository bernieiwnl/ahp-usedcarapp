package com.example.tugas_akhir.SPK.CLASS;

import com.example.tugas_akhir.MOBIL.CLASS.NewMobil;

public class RekomendasiMobil {

    private NewMobil newMobil;
    private double nilaiBobot;
    private double nilaiHarga;
    private double nilaiTransmisi;
    private double nilaiKilometer;
    private double nilaiServiceRecord;
    private double nilaiKondisiMesin;
    private double nilaiKapasitasMesin;
    private double nilaiKondisiBody;
    private double nilaiWarna;
    private double nilaiKelengkapan;
    private double nilaiKondisiInterior;
    private double nilaiTahun;


    public RekomendasiMobil(NewMobil newMobil, double nilaiBobot, double nilaiHarga, double nilaiTransmisi, double nilaiKilometer, double nilaiServiceRecord, double nilaiKondisiMesin, double nilaiKapasitasMesin, double nilaiKondisiBody, double nilaiWarna, double nilaiKelengkapan, double nilaiKondisiInterior, double nilaiTahun) {
        this.setNewMobil(newMobil);
        this.setNilaiBobot(nilaiBobot);
        this.setNilaiHarga(nilaiHarga);
        this.setNilaiTransmisi(nilaiTransmisi);
        this.setNilaiKilometer(nilaiKilometer);
        this.setNilaiServiceRecord(nilaiServiceRecord);
        this.setNilaiKondisiMesin(nilaiKondisiMesin);
        this.setNilaiKapasitasMesin(nilaiKapasitasMesin);
        this.setNilaiKondisiBody(nilaiKondisiBody);
        this.setNilaiWarna(nilaiWarna);
        this.setNilaiKelengkapan(nilaiKelengkapan);
        this.setNilaiKondisiInterior(nilaiKondisiInterior);
        this.setNilaiTahun(nilaiTahun);
    }

    public NewMobil getNewMobil() {
        return newMobil;
    }

    public void setNewMobil(NewMobil newMobil) {
        this.newMobil = newMobil;
    }

    public double getNilaiBobot() {
        return nilaiBobot;
    }

    public void setNilaiBobot(double nilaiBobot) {
        this.nilaiBobot = nilaiBobot;
    }

    public double getNilaiHarga() {
        return nilaiHarga;
    }

    public void setNilaiHarga(double nilaiHarga) {
        this.nilaiHarga = nilaiHarga;
    }

    public double getNilaiTransmisi() {
        return nilaiTransmisi;
    }

    public void setNilaiTransmisi(double nilaiTransmisi) {
        this.nilaiTransmisi = nilaiTransmisi;
    }

    public double getNilaiKilometer() {
        return nilaiKilometer;
    }

    public void setNilaiKilometer(double nilaiKilometer) {
        this.nilaiKilometer = nilaiKilometer;
    }

    public double getNilaiServiceRecord() {
        return nilaiServiceRecord;
    }

    public void setNilaiServiceRecord(double nilaiServiceRecord) {
        this.nilaiServiceRecord = nilaiServiceRecord;
    }

    public double getNilaiKondisiMesin() {
        return nilaiKondisiMesin;
    }

    public void setNilaiKondisiMesin(double nilaiKondisiMesin) {
        this.nilaiKondisiMesin = nilaiKondisiMesin;
    }

    public double getNilaiKapasitasMesin() {
        return nilaiKapasitasMesin;
    }

    public void setNilaiKapasitasMesin(double nilaiKapasitasMesin) {
        this.nilaiKapasitasMesin = nilaiKapasitasMesin;
    }

    public double getNilaiKondisiBody() {
        return nilaiKondisiBody;
    }

    public void setNilaiKondisiBody(double nilaiKondisiBody) {
        this.nilaiKondisiBody = nilaiKondisiBody;
    }

    public double getNilaiWarna() {
        return nilaiWarna;
    }

    public void setNilaiWarna(double nilaiWarna) {
        this.nilaiWarna = nilaiWarna;
    }

    public double getNilaiKelengkapan() {
        return nilaiKelengkapan;
    }

    public void setNilaiKelengkapan(double nilaiKelengkapan) {
        this.nilaiKelengkapan = nilaiKelengkapan;
    }

    public double getNilaiKondisiInterior() {
        return nilaiKondisiInterior;
    }

    public void setNilaiKondisiInterior(double nilaiKondisiInterior) {
        this.nilaiKondisiInterior = nilaiKondisiInterior;
    }

    public double getNilaiTahun() {
        return nilaiTahun;
    }

    public void setNilaiTahun(double nilaiTahun) {
        this.nilaiTahun = nilaiTahun;
    }
}
