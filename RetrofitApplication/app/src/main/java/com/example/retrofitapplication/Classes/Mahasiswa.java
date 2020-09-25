package com.example.retrofitapplication.Classes;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Mahasiswa {

    @org.greenrobot.greendao.annotation.Id (autoincrement = true)
    private Long id;
    private String nama;
    private String nim;
    @Generated(hash = 654997619)
    public Mahasiswa(Long id, String nama, String nim) {
        this.id = id;
        this.nama = nama;
        this.nim = nim;
    }
    @Generated(hash = 735477542)
    public Mahasiswa() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNama() {
        return this.nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getNim() {
        return this.nim;
    }
    public void setNim(String nim) {
        this.nim = nim;
    }
    
}
