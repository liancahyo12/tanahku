package com.cahjaya.coba.tanahku.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;

public class pengaduan {

    @Expose
    @SerializedName("id") int id;
    @Expose
    @SerializedName("nohak") String nohak;
    @Expose
    @SerializedName("noberkas") String noberkas;
    @Expose
    @SerializedName("tahun_berkas") String tahun_berkas;
    @Expose
    @SerializedName("alamat") String alamat;
    @Expose
    @SerializedName("deskripsi") String deskripsi;
    @Expose
    @SerializedName("case_status") int case_status;
    @Expose
    @SerializedName("form") File form;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNohak() {
        return nohak;
    }

    public void setNohak(String nohak) {
        this.nohak = nohak;
    }

    public String getNoberkas() {
        return noberkas;
    }

    public void setNoberkas(String noberkas) {
        this.noberkas = noberkas;
    }

    public String getTahun_berkas() {
        return tahun_berkas;
    }

    public void setTahun_berkas(String tahun_berkas) {
        this.tahun_berkas = tahun_berkas;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getCase_status() {
        return case_status;
    }

    public void setCase_status(int case_status) {
        this.case_status = case_status;
    }

    public File getForm() {
        return form;
    }

    public void setForm(File form) {
        this.form = form;
    }
}
