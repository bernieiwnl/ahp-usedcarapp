package com.example.tugas_akhir.CLASS;

public class Roc {

    public Roc(){

    }

    public double nilaiTrasmisi(String data){
        double prioritas = 0.0;
        double bobot = 0.0;
        int jumlahKriteria = 2;
        switch (data){
            case "Manual":
                prioritas = 1.0;
                break;
            case "Automatic":
                prioritas = 2.0;
                break;
        }
        bobot = hitungROC(prioritas, jumlahKriteria);
        return bobot;
    }

    public double nilaiService(String data){
        double prioritas = 0.0;
        double bobot = 0.0;
        int jumlahKriteria = 2;
        switch (data){
            case "Pernah Service Record":
                prioritas = 2.0;
                break;
            case "Tidak Pernah Service Record":
                prioritas = 1.0;
                break;
        }
        bobot = hitungROC(prioritas, jumlahKriteria);
        return bobot;
    }

    public double hitungROC(double prioritas, int jumlahKriteria){
        double bobot = 0.0;
        double roc = 0.0;
        for(double i = prioritas; i<= jumlahKriteria; i++){
            roc = roc + (1.0 / i);
        }
        bobot =roc / jumlahKriteria;
        return bobot;
    }

}
