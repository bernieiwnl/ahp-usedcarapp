package com.example.tugas_akhir.CLASS;

import java.io.Serializable;
import java.util.List;

public class Mobil implements Serializable {

    private String idMobil;
    private List<String> fotoMobil;
    private String kapasitas_mesin;
    private String merk;
    private String model;
    private String tahun;
    private String tipe;
    private String transmisi;
    private String kelengkapanBerkas;
    private String pajak;
    private String harga;
    private String jarakTempuh;
    private String keterangan;
    private String serviceRecord;
    private String kondisiKelistrikan;
    private String kondisiPembakaran;
    private String kondisiRadiator;
    private String kondisiBody;
    private String kondisiCat;
    private String kondisiBan;
    private String kondisiTransmisi;
    private String kondisiShockbreaker;
    private String kondisiPowersteering;
    private String kondisiRacksteer;
    private String kondisiTerod;
    private String kondisiBaljoin;

    public List<String> getFotoMobil() {
        return fotoMobil;
    }

    public void setFotoMobil(List<String> fotoMobil) {
        this.fotoMobil = fotoMobil;
    }

    public String getKelengkapanBerkas() {
        return kelengkapanBerkas;
    }

    public void setKelengkapanBerkas(String kelengkapanBerkas) {
        this.kelengkapanBerkas = kelengkapanBerkas;
    }

    public String getPajak() {
        return pajak;
    }

    public void setPajak(String pajak) {
        this.pajak = pajak;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getJarakTempuh() {
        return jarakTempuh;
    }

    public void setJarakTempuh(String jarakTempuh) {
        this.jarakTempuh = jarakTempuh;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getServiceRecord() {
        return serviceRecord;
    }

    public void setServiceRecord(String serviceRecord) {
        this.serviceRecord = serviceRecord;
    }

    public String getKondisiKelistrikan() {
        return kondisiKelistrikan;
    }

    public void setKondisiKelistrikan(String kondisiKelistrikan) {
        this.kondisiKelistrikan = kondisiKelistrikan;
    }

    public String getKondisiPembakaran() {
        return kondisiPembakaran;
    }

    public void setKondisiPembakaran(String kondisiPembakaran) {
        this.kondisiPembakaran = kondisiPembakaran;
    }

    public String getKondisiRadiator() {
        return kondisiRadiator;
    }

    public void setKondisiRadiator(String kondisiRadiator) {
        this.kondisiRadiator = kondisiRadiator;
    }

    public String getKondisiBody() {
        return kondisiBody;
    }

    public void setKondisiBody(String kondisiBody) {
        this.kondisiBody = kondisiBody;
    }

    public String getKondisiCat() {
        return kondisiCat;
    }

    public void setKondisiCat(String kondisiCat) {
        this.kondisiCat = kondisiCat;
    }

    public String getKondisiBan() {
        return kondisiBan;
    }

    public void setKondisiBan(String kondisiBan) {
        this.kondisiBan = kondisiBan;
    }

    public String getKondisiTransmisi() {
        return kondisiTransmisi;
    }

    public void setKondisiTransmisi(String kondisiTransmisi) {
        this.kondisiTransmisi = kondisiTransmisi;
    }

    public String getKondisiShockbreaker() {
        return kondisiShockbreaker;
    }

    public void setKondisiShockbreaker(String kondisiShockbreaker) {
        this.kondisiShockbreaker = kondisiShockbreaker;
    }

    public String getKondisiPowersteering() {
        return kondisiPowersteering;
    }

    public void setKondisiPowersteering(String kondisiPowersteering) {
        this.kondisiPowersteering = kondisiPowersteering;
    }

    public String getKondisiRacksteer() {
        return kondisiRacksteer;
    }

    public void setKondisiRacksteer(String kondisiRacksteer) {
        this.kondisiRacksteer = kondisiRacksteer;
    }

    public String getKondisiTerod() {
        return kondisiTerod;
    }

    public void setKondisiTerod(String kondisiTerod) {
        this.kondisiTerod = kondisiTerod;
    }

    public String getKondisiBaljoin() {
        return kondisiBaljoin;
    }

    public void setKondisiBaljoin(String kondisiBaljoin) {
        this.kondisiBaljoin = kondisiBaljoin;
    }

    public String getIdMobil() {
        return idMobil;
    }

    public void setIdMobil(String idMobil) {
        this.idMobil = idMobil;
    }

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
    public Mobil(){
        this.setIdMobil("");

        this.setKelengkapanBerkas("");
        this.setPajak("");
        this.setHarga("");
        this.setJarakTempuh("");
        this.setKeterangan("");
        this.setServiceRecord("");
        this.setKondisiKelistrikan("");
        this.setKondisiPembakaran("");
        this.setKondisiRadiator("");
        this.setKondisiBody("");
        this.setKondisiCat("");
        this.setKondisiBan("");
        this.setKondisiTransmisi("");
        this.setKondisiShockbreaker("");
        this.setKondisiPowersteering("");
        this.setKondisiRacksteer("");
        this.setKondisiTerod("");
        this.setKondisiBaljoin("");
        this.setKapasitas_mesin("");
        this.setMerk("");
        this.setModel("");
        this.setTahun("");
        this.setTipe("");
        this.setTransmisi("");
    }
}
