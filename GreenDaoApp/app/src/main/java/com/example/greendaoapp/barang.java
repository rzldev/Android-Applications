package com.example.greendaoapp;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity public class barang {
    @Id (autoincrement = true) private Long idBarang;

    private String namaBarang;
    private int hargaBarang;
    @Generated(hash = 1569857360)
    public barang(Long idBarang, String namaBarang, int hargaBarang) {
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
    }
    @Generated(hash = 744589180)
    public barang() {
    }
    public Long getIdBarang() {
        return this.idBarang;
    }
    public void setIdBarang(Long idBarang) {
        this.idBarang = idBarang;
    }
    public String getNamaBarang() {
        return this.namaBarang;
    }
    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }
    public int getHargaBarang() {
        return this.hargaBarang;
    }
    public void setHargaBarang(int hargaBarang) {
        this.hargaBarang = hargaBarang;
    }
}
