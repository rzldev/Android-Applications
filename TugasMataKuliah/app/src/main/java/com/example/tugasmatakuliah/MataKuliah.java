package com.example.tugasmatakuliah;

public class MataKuliah {

    String Matakuliah;
    int gambar, sks;
    int tugas, uts, uas;

    public String getMatakuliah() {
        return Matakuliah;
    }

    public void setMatakuliah(String matakuliah) {
        Matakuliah = matakuliah;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }

    public int getTugas() {
        return tugas;
    }

    public void setTugas(int tugas) {
        this.tugas = tugas;
    }

    public int getUts() {
        return uts;
    }

    public void setUts(int uts) {
        this.uts = uts;
    }

    public int getUas() {
        return uas;
    }

    public void setUas(int uas) {
        this.uas = uas;
    }

    public double NilaiAKhir() {
        double nilaiAkhir = (this.tugas * 0.4) + (this.uts * 0.4) + (this.uas * 0.4);
        return nilaiAkhir;
    }

    public String Grade(int nilaiAkhir) {
        String grade = "";
        if (nilaiAkhir >= 80) {
            grade = "A";
        } else if (nilaiAkhir >= 75) {
            grade = "B+";
        } else if (nilaiAkhir >= 65) {
            grade = "B";
        } else if (nilaiAkhir >= 60) {
            grade = "C+";
        } else if (nilaiAkhir >= 50) {
            grade = "C";
        } else if (nilaiAkhir >= 40) {
            grade = "D";
        } else if (nilaiAkhir >= 0) {
            grade = "E";
        }

        return grade;
    }

}
