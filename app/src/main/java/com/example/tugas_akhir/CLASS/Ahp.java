package com.example.tugas_akhir.CLASS;

public class Ahp {

    public Ahp(){

    }

    public double[] metodeAHP(double[] matrix){
        double[][] matrix2d = new double[matrix.length][matrix.length];
        double[] vEigen;
        //buat matrix 2d
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                matrix2d[i][j] = matrix[i] / matrix[j];
            }
        }
        //perkalian matrix
        matrix2d = perkalian2Matrix(matrix2d, matrix.length);
        //normalisasi matrix
        vEigen = normalisasiMatrix(matrix2d, matrix.length);
        //iterasi matrix
        return vEigen;
    }

    public double[][] perkalian2Matrix(double[][] matrix, int alt){
        double[][] result = new double[alt][alt];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                for(int k = 0; k < matrix.length; k++){
                    result[i][j] += matrix[i][k] * matrix[k][j];
                }
            }
        }
        return result;
    }

    public double[] normalisasiMatrix(double[][] matrix, int alt){
        double total = 0.0;
        double[] hasil = new double[alt];

        //jumlahkan baris pada matrix
        for(int i=0; i < matrix.length; i++){
            double sumRow = 0.0;
            for(int j=0; j < matrix.length;j++){
                sumRow = sumRow + matrix[i][j];
            }
            total = total + sumRow;
        }

        //kalkulasi vector eigen
        for(int v =0 ; v < matrix.length; v++)
        {
            double sumRow = 0.0;
            for(int x = 0; x < matrix.length; x++){
                sumRow = sumRow + matrix[v][x];
            }
            hasil[v] = sumRow / total;
        }
        return hasil;
    }


    public double nilaiHarga(int data){
        double prioritas = 0.0;
        if(data >= 50000000 && data <= 100000000){
            prioritas = 5.0;
        }
        else if(data >= 101000000 && data <= 200000000){
            prioritas = 4.0;
        }
        else if(data >= 201000000 && data <= 300000000){
            prioritas = 3.0;
        }
        else if(data >= 301000000 && data <= 400000000){
            prioritas = 2.0;
        }
        else if(data > 400000000){
            prioritas = 1.0;
        }
        return prioritas;
    }

    public double nilaiKapasitasMesin(int data){
        double prioritas = 0.0;
        if(data >= 500 && data <= 1200){
            prioritas = 1.0;
        }
        else if(data >= 1201 && data <= 1500){
            prioritas = 2.0;
        }
        else if(data >= 1501 && data <= 1800){
            prioritas = 3.0;
        }
        else if(data >= 1801 && data <= 2000){
            prioritas = 4.0;
        }
        else if(data > 2000)
        {
            prioritas = 5.0;
        }
        return prioritas;
    }

    public double nilaiTahun(int data){
        double prioritas = 0.0;
        if(data >= 2016){
            prioritas = 1.0;
        }
        else if(data < 2000){
            prioritas = 2.0;
        }
        else if(data >= 2000 && data <= 2005 ){
            prioritas = 3.0;
        }
        else if(data >= 2006 && data <= 2010){
            prioritas = 4.0;
        }
        else if(data >= 2011 && data <= 2015){
            prioritas = 5.0;
        }
        return prioritas;
    }

    public double nilaiWarna(String data){
        double prioritas = 0.0;
        switch (data){
            case "Abu-abu":
                prioritas = 2.0;
                break;
            case "Silver":
                prioritas = 3.0;
                break;
            case "Putih":
                prioritas = 4.0;
                break;
            case "Hitam":
                prioritas = 5.0;
                break;
            default:
                prioritas = 1.0;
                break;
        }
        return prioritas;
    }

    public static double nilaiKilometerMobil(int data){

        return 0;

    }

    //Kelengkapan Berkas
    public double nilaiKelengkapanBerkas(String data){
        double prioritas = 0.0;
        switch (data){
            case "Lengkap STNK dan BPKB":
                prioritas = 4.0;
                break;
            case "Hanya BPKB":
                prioritas = 3.0;
                break;
            case "Hanya STNK":
                prioritas = 2.0;
                break;
            case "Tidak ada STNK dan BPKB":
                prioritas = 1.0;
                break;
        }
        return prioritas;
    }

    public double nilaiPajak(String data){
        double prioritas = 0.0;
        switch (data){
            case "Pajak Hidup lebih dari 5 Bulan":
                prioritas = 3.0;
                break;
            case "Pajak Hidup kurang dari 3 Bulan":
                prioritas = 2.0;
                break;
            case "Pajak Mati / Balik Nama":
                prioritas = 1.0;
                break;
        }
        return prioritas;
    }

    //Kondisi Mesin / Fisik / UnderSteel
    public double nilaiKondisiMobil(String data, String nama){
        double prioritas = 0.0;
        switch (data){
            case "Sangat Baik":
                prioritas = 5.0;
                break;
            case "Baik":
                prioritas = 4.0;
                break;
            case "Sedang":
                prioritas = 3.0;
                break;
            case "Buruk":
                prioritas = 2.0;
                break;
            case "Sangat Buruk":
                prioritas = 1.0;
                break;
        }
        return prioritas;
    }


}
