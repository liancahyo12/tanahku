package com.cahjaya.coba.tanahku.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class komen {
    @Expose
    @SerializedName("id") String id;
    @Expose
    @SerializedName("created_at") String created_at;
    @Expose
    @SerializedName("user_id") String user_id;
    @Expose
    @SerializedName("pengaduan_id") String pengaduan_id;
    @Expose
    @SerializedName("komen") String komen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPengaduan_id() {
        return pengaduan_id;
    }

    public void setPengaduan_id(String pengaduan_id) {
        this.pengaduan_id = pengaduan_id;
    }

    public String getKomen() {
        return komen;
    }

    public void setKomen(String komen) {
        this.komen = komen;
    }
}
