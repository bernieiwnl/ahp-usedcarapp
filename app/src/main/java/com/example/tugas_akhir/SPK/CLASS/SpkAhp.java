package com.example.tugas_akhir.SPK.CLASS;

public class SpkAhp {

    public SpkAhp() {

    }

    public double[] hitungAHP(double[] matrix) {
        double[] vectorEigen = new double[matrix.length];

        //buat pairWisComparison
        double[][] pairwiseMatrix = new double[matrix.length][matrix.length];
        pairwiseMatrix = buatPairwiseComparison(matrix);

        //normalisasi pairwise matrix dengan cara pendekatan dan hitung vector eigennya
        vectorEigen = normalisasiMatrix(pairwiseMatrix);

        return vectorEigen;
    }

    public static double[][] buatPairwiseComparison(double[] matrix) {
        double[][] pairwiseMatrix = new double[matrix.length][matrix.length];
        //buat 1 Dimensy Array menjadi 2 Dimensy Array sesuai rumus Pairwise Comparison
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                pairwiseMatrix[i][j] = matrix[i] / matrix[j];
            }
        }
        return pairwiseMatrix;
    }

    public static double[] normalisasiMatrix(double[][] matrix) {
        double[] simpanTotal = new double[matrix.length];
        double[][] matrix2d = new double[matrix.length][matrix.length];
        double[] vectorEigen = new double[matrix.length];

        //jumlahkan baris pada matrix
        for (int i = 0; i < matrix.length; i++) {
            double sumRow = 0.0;

            for (int j = 0; j < matrix.length; j++) {
                sumRow = sumRow + matrix[j][i];
            }
            simpanTotal[i] = sumRow;
        }

        //normalisasi matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix2d[i][j] = matrix[i][j] / simpanTotal[j];
            }
        }

        //hitung vector Eigen
        for (int i = 0; i < matrix2d.length; i++) {
            double sumBaris = 0.0;
            for (int j = 0; j < matrix2d.length; j++) {
                sumBaris += matrix2d[i][j];
            }
            vectorEigen[i] = (sumBaris / matrix.length);
        }

        return vectorEigen;
    }


    public double bobotHarga(int harga) {
        double bobotHarga = 0.0;
        if (harga >= 401000000) {
            bobotHarga = 1.0;
        } else if (harga <= 400999999 && harga >= 301000000) {
            bobotHarga = 2.0;
        } else if (harga <= 300999999 && harga >= 201000000) {
            bobotHarga = 3.0;
        } else if (harga <= 200999999 && harga >= 101000000) {
            bobotHarga = 5.0;
        } else {
            bobotHarga = 6.0;
        }
        return bobotHarga;
    }

    public double bobotTahunPembuatan(int tahun) {
        double bobotTahun = 0.0;
        if (tahun == 2023) {
            bobotTahun = 1.0;
        } else if (tahun < 2005) {
            bobotTahun = 2.0;
        } else if (tahun <= 2010 && tahun >= 2005) {
            bobotTahun = 3.0;
        } else if (tahun <= 2022 && tahun >= 2017) {
            bobotTahun = 4.0;
        } else if (tahun <= 2016 && tahun >= 2011) {
            bobotTahun = 5.0;
        }
        return bobotTahun;
    }

    public double bobotKapasitasMesin(int kapasitasMesin) {
        double bobotKapasitasMesin = 0.0;
        if (kapasitasMesin > 2000) {
            bobotKapasitasMesin = 1.0;
        } else if (kapasitasMesin == 2000) {
            bobotKapasitasMesin = 2.0;
        } else if (kapasitasMesin == 1800) {
            bobotKapasitasMesin = 3.0;
        } else if (kapasitasMesin == 1200) {
            bobotKapasitasMesin = 4.0;
        } else if (kapasitasMesin == 1500) {
            bobotKapasitasMesin = 5.0;
        }
        return bobotKapasitasMesin;
    }

    public double bobotTipeMobil(String tipeMobil) {
        double bobotTipeMobil = 0.0;
        if (tipeMobil.equals("SUV")) {
            bobotTipeMobil = 3.0;
        } else if (tipeMobil.equals("Hatchback")) {
            bobotTipeMobil = 2.0;
        } else if (tipeMobil.equals("Sedan")) {
            bobotTipeMobil = 1.0;
        }
        return bobotTipeMobil;
    }

    public double bobotWarna(String warnaMobil) {
        double bobotWarna = 0.0;
        if (warnaMobil.equals("Putih")) {
            bobotWarna = 5.0;
        } else if (warnaMobil.equals("Hitam")) {
            bobotWarna = 4.0;
        } else if (warnaMobil.equals("Abu-abu")) {
            bobotWarna = 3.0;
        } else if (warnaMobil.equals("Silver")) {
            bobotWarna = 1.0;
        } else {
            bobotWarna = 2.0;
        }

        return bobotWarna;
    }

    public double bobotKondisiMesin(String kondisiMesin) {
        double bobotKondisiMesin = 0.0;
        if (kondisiMesin.equals("Suara mesin kasar")) {
            bobotKondisiMesin = 1.0;
        } else {
            bobotKondisiMesin = 2.0;
        }
        return bobotKondisiMesin;
    }

    public double bobotTransmisiMobil(String transMisi) {
        double bobotTransmisiMobil = 0.0;
        if (transMisi.equals("Manual")) {
            bobotTransmisiMobil = 3.0;
        } else if (transMisi.equals("Keduanya")) {
            bobotTransmisiMobil = 2.0;
        } else {
            bobotTransmisiMobil = 1.0;
        }
        return bobotTransmisiMobil;
    }

    public double bobotKilometer(int kilometer, int tahun) {
        int kilometerMobil = kilometer / (2023 - tahun);
        double bobotKilometerMobil = 0.0;
        if (kilometerMobil < 10000) {
            bobotKilometerMobil = 3.0;
        } else if (kilometerMobil <= 20000 & kilometerMobil >= 10000) {
            bobotKilometerMobil = 2.0;
        } else if (kilometerMobil > 20001) {
            bobotKilometerMobil = 1.0;
        }
        return bobotKilometerMobil;
    }

    public double bobotKeadaanBody(String keadaanBody) {
        double bobotKeadaanBody = 0.0;
        if (keadaanBody.equals("Tidak pernah mengalami kerusakan (kecelakaan)")) {
            bobotKeadaanBody = 7.0;
        } else if (keadaanBody.equals("Lekuk kecil")) {
            bobotKeadaanBody = 6.0;
        } else if (keadaanBody.equals("Lecet halus")) {
            bobotKeadaanBody = 5.0;
        } else if (keadaanBody.equals("Lecet kasar")) {
            bobotKeadaanBody = 4.0;
        } else if (keadaanBody.equals("Lecet halus dan lekuk kecil")) {
            bobotKeadaanBody = 3.0;
        } else if (keadaanBody.equals("Lecet kasar dan lekuk kecil")) {
            bobotKeadaanBody = 2.0;
        } else {
            bobotKeadaanBody = 1.0;
        }
        return bobotKeadaanBody;
    }

    public double bobotInterior(String kondisiInterior) {
        double bobotInterior = 0.0;
        if (kondisiInterior.equals("Interior Asli")) {
            bobotInterior = 2.0;
        } else {
            bobotInterior = 1.0;
        }
        return bobotInterior;
    }

    public double bobotKelengkapan(String kelengkapanMobil) {
        double bobotKelengkapan = 0.0;
        if (kelengkapanMobil.equals("Kunci ganda dan dongkrak tersedia")) {
            bobotKelengkapan = 4.0;
        } else if (kelengkapanMobil.equals("Kunci ganda tersedia dan dongkrak tidak tersedia")) {
            bobotKelengkapan = 3.0;
        } else if (kelengkapanMobil.equals("Kunci ganda tidak tersedia dan dongkrak tersedia")) {
            bobotKelengkapan = 2.0;
        } else {
            bobotKelengkapan = 1.0;
        }
        return bobotKelengkapan;
    }

    public double bobotServiceRecord(String serviceRecord) {
        double bobotServiceRecord = 0.0;
        if (serviceRecord.equals("Rutin")) {
            bobotServiceRecord = 4.0;
        } else if (serviceRecord.equals("Terkadang")) {
            bobotServiceRecord = 3.0;
        } else if (serviceRecord.equals("Jarang")) {
            bobotServiceRecord = 2.0;
        } else {
            bobotServiceRecord = 1.0;
        }
        return bobotServiceRecord;
    }


}
