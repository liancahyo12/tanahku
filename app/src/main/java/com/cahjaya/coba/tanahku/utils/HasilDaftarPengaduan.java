package com.cahjaya.coba.tanahku.utils;

import com.cahjaya.coba.tanahku.model.pengaduan;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HasilDaftarPengaduan {
    @SerializedName("dataList")
    ArrayList<pengaduan> dataList;

    public ArrayList<pengaduan> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<pengaduan> dataList) {
        this.dataList = dataList;
    }
}
