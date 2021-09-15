package com.cahjaya.coba.tanahku.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HasilKomen {
    @SerializedName("dataList")
    ArrayList<komen> dataList1;

    public ArrayList<komen> getDataList1() {
        return dataList1;
    }

    public void setDataList1(ArrayList<komen> dataList1) {
        this.dataList1 = dataList1;
    }
}
