package com.cahjaya.coba.tanahku.model;

public class pengaduan {

    private String nama;
    private String noberkas;
    private String pengaduant;

    public pengaduan(String nama, String noberkas, String pengaduant) {
        this.nama = nama;
        this.noberkas = noberkas;
        this.pengaduant = pengaduant;

    }
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoberkas() {
        return noberkas;
    }

    public void setNoberkas(String noberkas) {
        this.noberkas = noberkas;
    }

    public String getPengaduant() {
        return pengaduant;
    }

    public void setPengaduant(String pengaduant) {
        this.pengaduant = pengaduant;
    }



}
