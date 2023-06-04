package com.example.tugas_akhir.CLASS;

import java.util.ArrayList;

public class Preferensi {
    private String idUser;
    private ArrayList<PrefKriteria> preferensi_kriterias;
    private ArrayList<PrefSubKriteria> preferensi_kondisiFisik;
    private ArrayList<PrefSubKriteria> preferensi_kondisiUndersteel;
    private ArrayList<PrefSubKriteria> preferensi_kondisiMesin;

    private Preferensi(){

    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }


    public ArrayList<PrefKriteria> getPreferensi_kriterias() {
        return preferensi_kriterias;
    }

    public void setPreferensi_kriterias(ArrayList<PrefKriteria> preferensi_kriterias) {
        this.preferensi_kriterias = preferensi_kriterias;
    }

    public ArrayList<PrefSubKriteria> getPreferensi_kondisiFisik() {
        return preferensi_kondisiFisik;
    }

    public void setPreferensi_kondisiFisik(ArrayList<PrefSubKriteria> preferensi_kondisiFisik) {
        this.preferensi_kondisiFisik = preferensi_kondisiFisik;
    }

    public ArrayList<PrefSubKriteria> getPreferensi_kondisiUndersteel() {
        return preferensi_kondisiUndersteel;
    }

    public void setPreferensi_kondisiUndersteel(ArrayList<PrefSubKriteria> preferensi_kondisiUndersteel) {
        this.preferensi_kondisiUndersteel = preferensi_kondisiUndersteel;
    }

    public ArrayList<PrefSubKriteria> getPreferensi_kondisiMesin() {
        return preferensi_kondisiMesin;
    }

    public void setPreferensi_kondisiMesin(ArrayList<PrefSubKriteria> preferensi_kondisiMesin) {
        this.preferensi_kondisiMesin = preferensi_kondisiMesin;
    }
}
